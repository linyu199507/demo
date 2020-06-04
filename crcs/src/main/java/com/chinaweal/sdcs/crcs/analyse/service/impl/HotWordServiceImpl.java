package com.chinaweal.sdcs.crcs.analyse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.analyse.entity.HotWord;
import com.chinaweal.sdcs.crcs.analyse.mapper.HotWordMapper;
import com.chinaweal.sdcs.crcs.analyse.service.IHotWordService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.util.MyStaticValue;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.LongStream;

/**
 * 热词统计
 *
 * @author HuangXiao
 * @since 2020-01-14
 */
@Service
public class HotWordServiceImpl extends ServiceImpl<HotWordMapper, HotWord> implements IHotWordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HotWordMapper hotWordMapper;

    @Resource
    private BasecodeformMapper basecodeformMapper;

    private static StopRecognition filter = new StopRecognition();

    static {
        MyStaticValue.putLibrary(DicLibrary.DEFAULT, HotWordServiceImpl.class.getClassLoader().getResource("library/default.dic").getPath());

        //加入停用词屏蔽
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(new File(HotWordServiceImpl.class.getClassLoader().getResource("library/stop.dic").getPath()), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        filter.insertStopWords(lines);
        filter.insertStopRegexes("[~|`|@|#|$|^|*|(|)|（|）|<|>|《|》|、|，|,|.|？|?|：|:|{|}|。| |/|“|”|！|；|-|【|】|[|]|=|&|;|_|]");
    }

    public List<Term> analysisHot(String text) {
        return new DicAnalysis().parseStr(text).recognition(filter).getTerms();
    }

    public List<HotWord> groupCount(List<Term> terms) {
        Map<String, HotWord> keyList = new HashMap<>();
        HotWord hotWord;
        for (Term term : terms) {
            if (term.getName().length() <= 1) {
                //长度小于一的不要
                continue;
            } else if ("m".equals(term.getNatureStr())) {
                //数字跳出
                continue;
            }
            //判断是否原先存在了
            if (keyList.containsKey(term.getName())) {
                hotWord = keyList.get(term.getName());
                hotWord.setCount(hotWord.getCount() + 1);
            } else {
                hotWord = new HotWord();
                hotWord.setId(UUID.randomUUID().toString());
                hotWord.setKeyword(term.getName());
                hotWord.setCount(1L);
                hotWord.setCreateTime(LocalDateTime.now());
                hotWord.setUpdateTime(LocalDateTime.now());
                hotWord.setTagging(term.getNatureStr());
                keyList.put(term.getName(), hotWord);
            }

        }
        return new ArrayList<>(keyList.values());
    }

    @Override
    public void test(String text) {
        //进行分词
        List<Term> terms = analysisHot(text);
        //根据分词分组统计
        List<HotWord> hotWords = groupCount(terms);
        System.out.println(hotWords.toString());
    }

    @Override
    public void analyseHotWord(LocalDateTime begin, LocalDateTime end) {
        String[] hotWords;
        List<Map<String, String>> dataListMap = hotWordMapper.listComplaintContent(begin, end);
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"2");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);

        for (Map<String, String> data : dataListMap) {
            String baseType = null;
            String comment = "";

            for (Basecodeform bf:basecodeforms) {
                //取出父类类型
                String codeType = data.get("code_type");
                if(codeType.startsWith(bf.getCodeid())){
                    baseType=bf.getCodeid();
                    comment=bf.getComment();
                }else if(codeType.startsWith("010407")){
                    baseType="010407";
                    comment="化妆品";
                }
            }
                //累加值
                String text = data.getOrDefault("keyword", "");
                if (StringUtils.isBlank(text)) {
                    continue;
                }
                hotWords = text.split("[\\s|，|,|、]");
                for (String hotWordStr : hotWords) {
                    if (StringUtils.isBlank(hotWordStr)) {
                        continue;
                    }
                    //查询原先的关键字是否存在记录
                    HotWord lastHotWord = getOne(new QueryWrapper<HotWord>().lambda()
                            .eq(HotWord::getCodeType, baseType)
                            .eq(HotWord::getComment, comment)
                            .eq(HotWord::getKeyword, hotWordStr));
                    if (lastHotWord != null) {
                        //累加
                        lastHotWord.setCount(lastHotWord.getCount() + 1);
                        lastHotWord.setUpdateTime(LocalDateTime.now());
                        updateById(lastHotWord);
                    } else {
                        HotWord hotWord = new HotWord();
                        hotWord.setId(UUID.randomUUID().toString());
                        hotWord.setCodeType(baseType);
                        hotWord.setKeyword(hotWordStr);
                        hotWord.setCount(1L);
                        hotWord.setCreateTime(LocalDateTime.now());
                        hotWord.setUpdateTime(LocalDateTime.now());
                        hotWord.setComment(comment);
                        save(hotWord);
                    }
                }

        }
    }
    @Override
    public List<Map<String, Object>> getTop100() {
        List<Map<String, Object>> listBody = new ArrayList<>();
        int size = 100;
        List<String> notWords = new ArrayList<>();
        notWords.add("");
        List<HotWord> hotWords = hotWordMapper.listHowTop(size, notWords);
        Collections.reverse(hotWords);
        for (HotWord record : hotWords) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", record.getKeyword());
            map.put("value", record.getCount());
            listBody.add(map);
        }
        return listBody;
    }

    @Override
    public List<Map<String, Object>> getSameTop100(Map<String, Object> maps) {
        List<Map<String, Object>> listBody = new ArrayList<>();
        int size = 100;
        String type = maps.get("type").toString();
        List<String> notWords = new ArrayList<>();
        notWords.add("");
        List<HotWord> hotWords = hotWordMapper.listCommonTop(size, notWords,type);
        Collections.reverse(hotWords);
        for (HotWord record : hotWords) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", record.getKeyword());
            map.put("value",record.getCount());
            listBody.add(map);
        }
        return listBody;
    }

    @Override
    public List<Map<String, Object>> getComplaintHotWord(Map<String, Object> maps) {
        List<Map<String, Object>> listBody = new ArrayList<>();
        //String type = maps.get("type").toString();
        List<String> notWords = new ArrayList<>();
        notWords.add("");
        QueryWrapper<Basecodeform> basecodeformQueryWrapper=new QueryWrapper<>();
        basecodeformQueryWrapper.lambda().eq(Basecodeform::getType,"object12345").eq(Basecodeform::getOrglevel,"2");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(basecodeformQueryWrapper);

        for(Basecodeform basecodeform:basecodeforms){
        maps.put("type",basecodeform.getCodeid());
        List<HotWord> hotWords = hotWordMapper.listComplaintHotWord(maps);
        Collections.reverse(hotWords);
        Long stream = hotWords.stream().mapToLong(HotWord::getCount).sum();
        if(hotWords.size()>0) {
            String comment = hotWords.get(0).getComment();
            System.out.println("调试");
            System.out.println(stream);
            Map<String, Object> map = new HashMap<>();
            map.put("name", comment);
            map.put("value", stream);
            listBody.add(map);
            System.out.println(listBody);
            }
        }
        return listBody;
    }

}
