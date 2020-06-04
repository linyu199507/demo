package word;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.analyse.entity.TimeRecord;
import com.chinaweal.sdcs.crcs.analyse.service.ITimeRecordService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.ComplaintSituation;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.ComplaintSituationMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WoedTest {

    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Resource
    private ComplaintSituationMapper complaintSituationMapper;

    @Test
    public void SimpleWordExport() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
//        map.put("time", format.format(new Date()));
        map.put("me", "JueYue");
        map.put("date", "2015-01-03");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "cn/afterturn/easypoi/test/word/doc/Simple.docx", map);
            FileOutputStream fos = new FileOutputStream("C:/Users/lin99/Desktop/SDCS/rcssimple.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        JSONArray jsonArray = JSONArray.parseArray("[\n" +
                "\t[\n" +
                "\t\t\"93480c826d6f7b8e016e8292bd7e64f5\",\n" +
                "\t\t\"2019111915272878090\",\n" +
                "\t\t\"廖先生\",\n" +
                "\t\t\"18873879889\",\n" +
                "\t\t\"投诉\",\n" +
                "\t\t\"登记\",\n" +
                "\t\t\"何梓浩\",\n" +
                "\t\t\"2019-11-1915:31:25\",\n" +
                "\t\t\"2019-11-2015:31:25\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"0\",\n" +
                "\t\t\"0\",\n" +
                "\t\t\"feedback_info!view.action?id=93480c826d6f7b8e016e8292bd7e64f5\"\n" +
                "\t],\n" +
                "\t[\n" +
                "\t\t\"93480c826d6f7b8e016e82921b0c64d8\",\n" +
                "\t\t\"2019111915262332986\",\n" +
                "\t\t\"钟先生\",\n" +
                "\t\t\"13929925977\",\n" +
                "\t\t\"咨询\",\n" +
                "\t\t\"登记\",\n" +
                "\t\t\"梁倩潼\",\n" +
                "\t\t\"2019-11-1915:30:43\",\n" +
                "\t\t\"2019-11-2015:30:43\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"0\",\n" +
                "\t\t\"0\",\n" +
                "\t\t\"feedback_info!view.action?id=93480c826d6f7b8e016e82921b0c64d8\"\n" +
                "\t]\n" +
                "]");

        for (int i = 0; i < jsonArray.size(); i++) {
            String[] arrays = jsonArray.getObject(i, String[].class);
            System.out.println("------------------第" + i + "行----------------");
            for (int j = 0; j < arrays.length; j++) {
                System.out.println("第" + j + "列：" + arrays[j]);
            }
        }
    }


    @Resource
    private ITimeRecordService iTimeRecordService;
    @Test
    public void qwqw(){
        TimeRecord timeRecord = iTimeRecordService.getById(ITimeRecordService.HOT_WORD);
        LocalDateTime end = LocalDateTime.now();
        Date enddate = (Date) Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
        Date begindate = (Date) Date.from(timeRecord.getLastDate().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(enddate);
        System.out.println(begindate);
    }

}
