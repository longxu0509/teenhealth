import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.Controller.terminal.TWqxtrainingController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class test {

    @Test
    public static void main(String[] args) throws ParseException {
        String data="��\u0013 o[{\"ItemNO\":7,\"MachineNO\":\"3\",\"SchoolNO\":\"a\",\"StuNO\":\"12\",\"TestResult\":\"16.9\",\"TestTime\":\"2021-12-16 16:30:42\"}]";
        Pattern pattern=Pattern.compile("\"(.*?)\"");
        Matcher matcher=pattern.matcher(data);
        ArrayList<String> list=new ArrayList<String>();
        while (matcher.find()){
            list.add(matcher.group().trim().replace("\"","")+" ");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=simpleDateFormat.parse(list.get(10));
        if (list.get(2).equals("3 ")){
            Integer Ma=3;
        }

        for (String s : list) {
            System.out.println(s);
        }
    }

//    @Test
//    public void TimeTest() throws ParseException {
//        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startTime = fmt.parse("2023-02-13 15:50:34");
//        Date endTime = fmt.parse("2023-02-13 18:59:34");
//        if (TWqxtrainingController.isOverTime(endTime, startTime) == false)
//            System.out.println("未超时");
//        else
//            System.out.println("超时");
//
//    }


}

