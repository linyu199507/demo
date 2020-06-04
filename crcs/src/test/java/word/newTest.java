package word;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.controller.ScreenDisplayController;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IScreenDisplayService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.ComplaintSituation;
import com.chinaweal.sdcs.crcs.system.entity.TimesVo;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.ComplaintSituationMapper;
import com.chinaweal.sdcs.crcs.system.mapper.OverallSituationMapper;
import com.chinaweal.sdcs.crcs.util.CreateEntityInfoUtil;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.unit.DataUnit;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class newTest {


    @Resource
    private OverallSituationMapper statisticsDateMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Resource
    private ComplaintSituationMapper complaintSituationMapper;

    @Test
    public void TimeTest() {

        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        String first = format.format(c.getTime());
        System.out.println("===============本月first day:"+first);
        map.put("first",first);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY,23);
        ca.set(Calendar.MINUTE,59);
        ca.set(Calendar.SECOND,59);
        String last = format.format(ca.getTime());
        System.out.println("===============本月last day:"+last);
        map.put("last",last);
        System.out.println(map);

    }

    @Test
    public  void  dateTwe()throws Exception{
            Map<String,Object> map = new HashMap<>();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            String start = format.format(calendar.getTime());
            map.put("start",start);
            System.out.println("上个月第一天："+format.format(calendar.getTime()));

        Calendar c=Calendar.getInstance();
        int month=c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        String end = format.format(c.getTime());
        map.put("end",end);
        System.out.println("上个月最后一天："+format.format(c.getTime()));
        System.out.println(map);


    }

    @Test
    public void testt(){

//        double q = 868;
//        double w = 4123;
//        BigDecimal bignum1 = new BigDecimal(Double.toString(q*100));
//        BigDecimal bignum2 = new BigDecimal(Double.toString(w));
//        System.out.println(bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP));
//
//        BigDecimal bignum3 = bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP);
//        System.out.println(bignum3+"%");

        int number1 = 0;
        int number2 = 0;
        double double1 = (double) number1;
        double double12 = (double) number2;
        BigDecimal bignum1 = new BigDecimal(Double.toString(double1*100));
        BigDecimal bignum2 = new BigDecimal(Double.toString(double12));
        BigDecimal bignum3 = bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP);
        System.out.println(bignum3+"%");




    }

    @Test
    public  void  dateTwe123()throws Exception{
       Map<String,Object> map = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

       Date date = df.parse("2020-03-10");
        System.out.println(date);
        date = CreateEntityInfoUtil.getWorkDay(date,45,0,0,0);
        System.out.println(date);

    }
    @Qualifier("screenDisplayServiceImpl")
    @Autowired
    private IScreenDisplayService iScreenDisplayService;
    @Test
    public  void  dateTwe1234()throws Exception {
        int a =10;
        int b =15;

        Double a1 = Double.valueOf(a);
        Double b1 = Double.valueOf(b);

        Double q = (a1-b1)/b1;

        System.out.println((a1-b1)/b1);

        }


}
