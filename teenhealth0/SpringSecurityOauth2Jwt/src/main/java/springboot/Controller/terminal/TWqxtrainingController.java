package springboot.Controller.terminal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import io.swagger.models.auth.In;
import javafx.util.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.*;
import springboot.service.*;

import javax.naming.InsufficientResourcesException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/tph/service")
public class TWqxtrainingController {
    @Autowired
    private TTrainingVideoService trainingVideoService;
    @Autowired
    private TWqxTrainService tWqxTrainService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TWqxplanRecordService tWqxplanRecordService;

    @Autowired
    private TWqxplanStudentService tWqxplanStudentService;

    @Autowired
    private TWqxplanPrescriptionService tWqxplanPrescriptionService;

    @Autowired
    private  TWqxplanNpService tWqxplanNpService;

    @Autowired
    private TTestItemsService tTestItemsService;


    @Autowired
    private TTestCardiorService tTestCardiorService;  // 1

    @Autowired
    private TTestCorestrengthService tTestCorestrengthService; // 2

    @Autowired
    private TTestCoordinateService tTestCoordinateService; // 3

    @Autowired
    private TTestUpmfService tTestUpmfService; // 4

    @Autowired
    private TTestLowermfService tTestLowermfService; // 5

    @Autowired
    private TStudentPftestService tStudentPftestService;

    @Autowired
    private TGuideVideosService tGuideVideosService;

    @Autowired
    private TTestStandardService tTestStandardService;

    @Autowired
    private TCapabilityService tCapabilityService;

    @Autowired
    private TRiskService tRiskService;

    @Autowired
    private TExamineService tExamineService;

    @Autowired
    private TCourseInfoService tCourseInfoService;

    @Autowired
    private TCourseDetailService tCourseDetailService;

    private String userUploadpath="/media/AITraining/userUploads/";    //阿里云上传用户训练文件地址
    private String moxingpath="/usr/java/xunlianmoxing/";   //阿里云算法模型文件地址
//    private String uploadpath = "D:\\Works\\WQXtest\\uploadpath\\";    //本地地址教学视频地址
//    private String userUploadpath = "D:\\Works\\WQXtest\\JYWQXtest\\";    //本地地址上传用户训练文件地址
//    private String moxingpath="D:\\Works\\WQXtest\\xunlianmoxing\\";   //阿里云算法模型文件地址

    //客户端获取AI训练视频列表
    @GetMapping(value = "/trainingVideoList", produces = {"application/json;charset=UTF-8"})
    public CommonResult trainingVideoList()throws Exception{
        List<TTrainingVideo> trainingVideoList=trainingVideoService.findTrainingVideo();
        if (trainingVideoList.size()==0){
            return CommonResult.fail();
        }else {
            return CommonResult.success(trainingVideoList);
        }
    }

    //客户端获取测试视频列表
    @GetMapping(value = "/testingVideoList", produces = {"application/json;charset=UTF-8"})
    public CommonResult testingVideoList()throws Exception{
        List<TTestItems> testingVideoList =tTestItemsService.findTestingVideo();
        if (testingVideoList.size()==0){
            return CommonResult.fail();
        }else {
            return CommonResult.success(testingVideoList);
        }
    }
    // 客户端上传test结果
    @PostMapping(value = "/testTrainingUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult testTrainingResult(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long studentId = studentTestInfo.getUserId();
        Long testId = studentTestInfo.getTestId();
        Long count = studentTestInfo.getCount();
        String level = null;
        Integer score = 0;
        Object o = null;
        TStudentPftest studentPftest = new TStudentPftest();
        studentPftest.setStudentId(studentId);
        switch (testId.intValue()) {
            case 1: {
                TTestCardior adviceAndScore = tTestCardiorService.getAdviceAndScore(count);
                studentPftest.setCardiorLevel(adviceAndScore.getLevel());
                studentPftest.setCardiorScore(adviceAndScore.getScore());
                o = adviceAndScore;
            } break;
            case 2: {
                TTestCoordinate tTestCoordinate = tTestCoordinateService.getAdviceAndScore(count);
                studentPftest.setCoordinateLevel(tTestCoordinate.getLevel());
                studentPftest.setCoordinateScore(tTestCoordinate.getScore());
                o = tTestCoordinate;
            } break;
            case 3: {
                TTestCorestrength tTestCorestrength = tTestCorestrengthService.getAdviceAndScore(count);
                studentPftest.setCorestrengthLevel(tTestCorestrength.getLevel());
                studentPftest.setCorestrengthScore(tTestCorestrength.getScore());
                o = tTestCorestrength;
            } break;
            case 4: {
                TTestUpmf tTestUpmf = tTestUpmfService.getAdviceAndScore(count);
                studentPftest.setUpmfLevel(tTestUpmf.getLevel());
                studentPftest.setUpmfScore(tTestUpmf.getScore());
                o = tTestUpmf;
            } break;
            case 5: {
                TTestLowermf testLowermf = tTestLowermfService.getAdviceAndScore(count);
                studentPftest.setLowermfLevel(testLowermf.getLevel());
                studentPftest.setLowermfScore(testLowermf.getScore());
                o = testLowermf;
            } break;
            default:
                break;
        }

        System.out.println(studentId + " " + testId + " " + count);
        // 存在就update
        // 否则就修改
        if (o != null) {
            // 根据studentId查询 t_student_pftest 表中是否有对应的记录 没有则插入 一条 有则update
            if (tStudentPftestService.findByStudentId(studentId) != null) {
                tStudentPftestService.addTestItem(studentPftest);
            } else {
                // insert
                tStudentPftestService.insertTestRecord(studentPftest);
            }
            return CommonResult.success(o);
        } else {
            return CommonResult.fail();
        }
    }

    public String getGrade(int score) {
        String str;
        if (score >= 90) {
            str = "优秀";
        } else if (score >= 80) {
            str = "良好";
        } else if (score >= 70) {
            str = "平均";
        } else if (score >= 60) {
            str = "低于平均";
        } else {
            str = "弱";
        }
        return str;
    }

    public String getRiskGrade(int score) {
        String str;
        if (score >= 90) {
            str = "低风险";
        } else if (score >= 80) {
            str = "中风险";
        } else {
            str = "高风险";
        }
        return str;
    }

    // 客户端上传test结果
    @PostMapping(value = "/testCapabilityUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult testCapabilityUpload(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long userID = studentTestInfo.getUserId();
        Long testId = studentTestInfo.getTestId();
        Long count = studentTestInfo.getCount();
        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
        int gender = tuser.getGender();
        int age_group_id = 1;
        if (age > 24)
            age_group_id += Math.ceil((age - 24)/3.0);

        TCapability tCapability = new TCapability();
        tCapability.setUserId(userID);

        Integer score = tTestStandardService.getTestScore(testId, gender, age_group_id, count);

        switch (testId.intValue()) {
            case 1: {  // 俯卧撑
                tCapability.setPushUpCnt(count.intValue());
                tCapability.setPushUpScore(score);
                tCapability.setPushUpGrade(getGrade(score));
            } break;
            case 2: { // 仰卧起做
                tCapability.setSitUpsCnt(count.intValue());
                tCapability.setSitUpsScore(score);
                tCapability.setSitUpsGrade(getGrade(score));
            } break;
            case 3: { // 原地纵跳
                tCapability.setJump(count.intValue());
                tCapability.setJumpScore(score);
                tCapability.setJumpGrade(getGrade(score));
            } break;
            case 4: { // 坐位体前屈
                tCapability.setSitForward(count.intValue());
                tCapability.setSitForwardScore(score);
                tCapability.setSitForwardGrade(getGrade(score));
            } break;
            case 6: { // 引体向上
                tCapability.setPullUpCnt(count.intValue());
                tCapability.setPullUpScore(score);
                tCapability.setPullUpGrade(getGrade(score));
            } break;
            case 7: { // 跑步
                tCapability.setRuning(count.intValue());
                tCapability.setRuningScore(score);
                tCapability.setRuningGrade(getGrade(score));
            } break;
            default:
                break;
        }

        System.out.println(userID + " " + testId + " " + count);
        // 存在就update
        // 否则就修改

        // 根据userId查询 t_capability 表中是否有对应的记录 没有则插入 一条 有则update
        if (tCapabilityService.findByUserId(userID) != null && tCapabilityService.findByUserId(userID).getScore() == null) {
            tCapabilityService.addTestItem(tCapability);
        } else {
            // insert
            tCapability.setCreateTime(new Date());
            tCapabilityService.insertTestRecord(tCapability);
        }
        return CommonResult.success();
    }

    // 根据用户id 获取用户运动能力报告
    @GetMapping("/getCapabilityReport/{userId}")
    public CommonResult getCapabilityReport(@PathVariable("userId") Long userId){
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        TCapability tCapability = tCapabilityService.findByUserId(userId);
        int totalScore = 0;
        String report = "";
        if (tCapability.getPushUpScore() != null) {
            totalScore += tCapability.getPushUpScore();
            list.add(new Pair<>(1, tCapability.getPushUpScore()));
            String re = "上肢力量";
            if (tCapability.getPushUpGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getPushUpGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getPushUpGrade();
            report += re + ";";
        }

        if (tCapability.getSitUpsScore() != null) {
            totalScore += tCapability.getSitUpsScore();
            list.add(new Pair<>(2, tCapability.getSitUpsScore()));
            String re = "腰腹核心力量";
            if (tCapability.getSitUpsGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getSitUpsGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getSitUpsGrade();
            report += re + ";";
        }

        if (tCapability.getJumpScore() != null) {
            totalScore += tCapability.getJumpScore();
            list.add(new Pair<>(3, tCapability.getJumpScore()));
            String re = "下肢力量";
            if (tCapability.getJumpGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getJumpGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getJumpGrade();
            report += re + ";";
        }

        if (tCapability.getSitForwardScore() != null) {
            totalScore += tCapability.getSitForwardScore();
            list.add(new Pair<>(4, tCapability.getSitForwardScore()));
            String re = "身体柔韧性";
            if (tCapability.getSitForwardGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getSitForwardGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getSitForwardGrade();
            report += re + ";";
        }

        if (tCapability.getPullUpScore() != null) {
            totalScore += tCapability.getPullUpScore();
            list.add(new Pair<>(6, tCapability.getPullUpScore()));
            String re = "肩部力量";
            if (tCapability.getPullUpGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getPullUpGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getPullUpGrade();
            report += re + ";";
        }

        if (tCapability.getRuningScore() != null) {
            totalScore += tCapability.getRuningScore();
            list.add(new Pair<>(7, tCapability.getRuningScore()));
            String re = "心肺耐力";
            if (tCapability.getRuningGrade().equals("平均"))
                re += "处于平均水平";
            else if (tCapability.getRuningGrade().equals("低于平均"))
                re += "低于平均水平";
            else
                re += tCapability.getRuningGrade();
            report += re + ";";
        }
        tCapability.setReport(report);
        tCapability.setScore(totalScore);
        String tmp;
        if (totalScore >= 550) {
            tmp = "A";
        } else if (totalScore >= 500) {
            tmp = "B";
        } else if (totalScore >= 450) {
            tmp = "C";
        } else if (totalScore >= 400) {
            tmp = "D";
        } else
            tmp = "E";

        // 按成绩从小到大排序
        Collections.sort(list, (p1, p2) -> p1.getValue().compareTo(p2.getValue()));

        for (Pair<Integer, Integer> pair : list) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
        int minAction1 = list.get(0).getKey();
        int minScore1 = list.get(0).getValue();
        int minAction2 = list.get(1).getKey();

        if (minAction1 == 2 || minAction1 == 4 || minScore1 == 100) {
            // TODO 课程的id记得换
            tCapability.setNpId(17L);
        } else if (minAction1 == 7) {
            tCapability.setNpId(18L);
        } else if (minAction1 == 1 || minAction1 == 6) {
            tCapability.setNpId(19L);
        } else if (minAction1 == 3) {
            tCapability.setNpId(20L);
        }
        if (minAction2 == 2 || minAction2 == 4 || minAction2 == 7) {
            tCapability.setCourse1Id(1L);
            tCapability.setCourse2Id(3L);
        } else if (minAction1 == 1 || minAction1 == 6) {
            tCapability.setCourse1Id(15L);
        } else if (minAction1 == 3) {
            tCapability.setCourse1Id(2L);
            tCapability.setCourse2Id(16L);
        }
        tCapability.setResult(tmp);
        tCapabilityService.addTestItem(tCapability);
        return CommonResult.success(transferCustom(tCapability));
    }


    public TCapabilityCustom transferCustom(TCapability tCapability) {
        TCapabilityCustom tCapabilityCustom = new TCapabilityCustom();
        List<TCourseInfo> list1 = new ArrayList<>();
        if (tCapability.getNpId() != null) {
            TCourseInfo tAiCourse = tCourseInfoService.getCourseById(tCapability.getNpId());
            list1.add(tAiCourse);
        }

        if (tCapability.getCourse1Id() != null) {
            TCourseInfo tCourse1 = tCourseInfoService.getCourseById(tCapability.getCourse1Id());
            list1.add(tCourse1);
        }
        if (tCapability.getCourse2Id() != null) {
            TCourseInfo tCourse2 = tCourseInfoService.getCourseById(tCapability.getCourse2Id());
            list1.add(tCourse2);
        }
        BeanUtils.copyProperties(tCapability, tCapabilityCustom);
        tCapabilityCustom.setCourseList(list1);
        return tCapabilityCustom;
    }

    public TRiskCustom transferRiskCustom(TRisk tRisk) {
        List<TCourseInfo> tCourseInfoList = new ArrayList<>();
        if (tRisk.getCourse1Id() != null) {
            TCourseInfo course1 = tCourseInfoService.getCourseById(tRisk.getCourse1Id().longValue());
            tCourseInfoList.add(course1);
        }
        if (tRisk.getCourse2Id() != null) {
            TCourseInfo course2 = tCourseInfoService.getCourseById(tRisk.getCourse2Id().longValue());
            tCourseInfoList.add(course2);
        }
        TRiskCustom tRiskCustom = new TRiskCustom();
        BeanUtils.copyProperties(tRisk, tRiskCustom);
        tRiskCustom.setCourseList(tCourseInfoList);
        return tRiskCustom;
    }

    //客户端获取指定用户运动能力运动风险历史评估列表
    @GetMapping(value = "/getCapabilityRiskList/{userId}", produces = {"application/json;charset=UTF-8"})
    public CommonResult getCapabilityList(@PathVariable("userId") Long userId)throws Exception{
        List<TCapability> tCapabilities =tCapabilityService.getCapabilityList(userId);
        List<TRisk> tRiskList =tRiskService.getRiskList(userId);
        List<Object> list = new ArrayList<>();
        for (TCapability t : tCapabilities)
            list.add(transferCustom(t));
        for (TRisk r : tRiskList)
            list.add(transferRiskCustom(r));
        return CommonResult.success(list);
    }

    // 客户端上传风险评估动作个数
    @PostMapping(value = "/testRiskUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult testRiskUpload(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long userID = studentTestInfo.getUserId();
        Long testId = studentTestInfo.getTestId();
        Long count = studentTestInfo.getCount();
        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
        int gender = tuser.getGender();
        int age_group_id = 1;
        if (age > 24)
            age_group_id += Math.ceil((age - 24)/3.0);

        TRisk tRisk = new TRisk();
        tRisk.setUserId(userID);

        Integer score = tTestStandardService.getTestScore(testId, gender, age_group_id, count);

        switch (testId.intValue()) {
            case 1: {  // 俯卧撑
                tRisk.setPushUpCnt(count.intValue());
                tRisk.setPushUpScore(score);
                tRisk.setPushUpGrade(getRiskGrade(score));
            } break;
            case 2: { // 仰卧起做
                tRisk.setSitUpsCnt(count.intValue());
                tRisk.setSitUpsScore(score);
                tRisk.setSitUpsGrade(getRiskGrade(score));
            } break;
            case 3: { // 原地纵跳
                tRisk.setJump(count.intValue());
                tRisk.setJumpScore(score);
                tRisk.setJumpGrade(getRiskGrade(score));
            } break;
            case 4: { // 坐位体前屈
                tRisk.setSitForward(count.intValue());
                tRisk.setSitForwardScore(score);
                tRisk.setSitForwardGrade(getRiskGrade(score));
            } break;
            case 5: { // 单腿闭眼栈
                tRisk.setEyesCloseStand(count.intValue());
                tRisk.setEyesCloseStandScore(score);
                tRisk.setEyesCloseStandGrade(getRiskGrade(score));
            } break;
            case 6: { // 引体向上
                tRisk.setPullUpCnt(count.intValue());
                tRisk.setPullUpScore(score);
                tRisk.setPullUpGrade(getRiskGrade(score));
            } break;
            case 7: { // 3000米
                tRisk.setRuning(count.intValue());
                tRisk.setRuningScore(score);
                tRisk.setRuningGrade(getRiskGrade(score));
            } break;
            default:
                break;
        }

        System.out.println(userID + " " + testId + " " + count);
        // 存在就update
        // 否则就修改

        // 根据userId查询 t_capability 表中是否有对应的记录 没有则插入 一条 有则update
        if (tRiskService.findByUserId(userID) != null && tRiskService.findByUserId(userID).getScore() == null) {
            tRiskService.addTestItem(tRisk);
        } else {
            // insert
            tRisk.setCreateTime(new Date());
            tRiskService.insertTestRecord(tRisk);
        }
        return CommonResult.success();
    }


    // 根据用户id 获取用户运动风险报告
    @GetMapping("/getRiskReport/{userId}")
    public CommonResult getRiskReport(@PathVariable("userId") Long userId){
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        TRisk tRisk = tRiskService.findByUserId(userId);
        int totalScore = 0;
        String report = "";
        if (tRisk.getPushUpScore() != null) {
            totalScore += tRisk.getPushUpScore();
            list.add(new Pair<>(1, tRisk.getPushUpScore()));
            String re = "上肢近端运动风险等级";
            if (tRisk.getPushUpGrade().equals("低风险"))
                re += "低";
            else if (tRisk.getPushUpGrade().equals("中风险"))
                re += "中";
            else
                re += "高";
            report += re + ";";
        }

        if (tRisk.getSitUpsScore() != null) {
            totalScore += tRisk.getSitUpsScore();
            list.add(new Pair<>(2, tRisk.getSitUpsScore()));
            String re = "躯干前侧运动风险等级";
            if (tRisk.getSitUpsGrade().equals("低风险"))
                re += "低";
            else if (tRisk.getSitUpsGrade().equals("中风险"))
                re += "中";
            else
                re += "高";
            report += re + ";";
        }

        if (tRisk.getJumpScore() != null) {
            totalScore += tRisk.getJumpScore();
            list.add(new Pair<>(3, tRisk.getJumpScore()));
            String re = "下肢运动风险等级";
            if (tRisk.getJumpGrade().equals("低风险"))
                re += "低";
            else if (tRisk.getJumpGrade().equals("中风险"))
                re += "中";
            else
                re += "高";
            report += re + ";";
        }

        if (tRisk.getSitForwardScore() != null) {
            totalScore += tRisk.getSitForwardScore();
            list.add(new Pair<>(4, tRisk.getSitForwardScore()));
            String re = "身体柔韧性";
            if (tRisk.getSitForwardGrade().equals("低风险"))
                re += "优秀";
            else if (tRisk.getSitForwardGrade().equals("中风险"))
                re += "良好";
            else
                re += "较弱";
            report += re + ";";
        }

        if (tRisk.getEyesCloseStand() != null) {
            totalScore += tRisk.getEyesCloseStandScore();
            list.add(new Pair<>(5, tRisk.getEyesCloseStandScore()));
            String re = "身体平衡能力";
            if (tRisk.getEyesCloseStandGrade().equals("低风险"))
                re += "优秀";
            else if (tRisk.getEyesCloseStandGrade().equals("中风险"))
                re += "良好";
            else
                re += "较弱";
            report += re + ";";
        }

        if (tRisk.getPullUpScore() != null) {
            totalScore += tRisk.getPullUpScore();
            list.add(new Pair<>(6, tRisk.getPullUpScore()));
            String re = "身体协调与力量";
            if (tRisk.getPullUpGrade().equals("低风险"))
                re += "优秀";
            else if (tRisk.getPullUpGrade().equals("中风险"))
                re += "良好";
            else
                re += "较弱";
            report += re + ";";
        }

        if (tRisk.getRuningScore() != null) {
            totalScore += tRisk.getRuningScore();
            list.add(new Pair<>(7, tRisk.getRuningScore()));
            String re = "运动心肺风险等级";
            if (tRisk.getRuningGrade().equals("低风险"))
                re += "低";
            else if (tRisk.getRuningGrade().equals("中风险"))
                re += "中";
            else
                re += "高";
            report += re + ";";
        }
        tRisk.setReport(report);
        tRisk.setScore(totalScore);
        String tmp;
        if (totalScore >= 630) {
            tmp = "低";
        } else if (totalScore >= 560) {
            tmp = "中";
        } else
            tmp = "高";

        // 按成绩从小到大排序
        Collections.sort(list, (p1, p2) -> p1.getValue().compareTo(p2.getValue()));

        for (Pair<Integer, Integer> pair : list) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
        int minAction1 = list.get(0).getKey();

        String courseName = "";
        if (minAction1 == 1) {
            tRisk.setCourse1Id(4);
            tRisk.setCourse2Id(5);
        } else if (minAction1 == 2) {
            tRisk.setCourse1Id(6);
            tRisk.setCourse2Id(7);
        } else if (minAction1 == 3)  {
            tRisk.setCourse1Id(8);
            tRisk.setCourse2Id(9);
        } else if (minAction1 == 4) {
            tRisk.setCourse1Id(10);
            tRisk.setCourse2Id(11);
        } else if (minAction1 == 5) {
            tRisk.setCourse1Id(12);
            tRisk.setCourse2Id(13);
        } else if (minAction1 == 6) {
            tRisk.setCourse1Id(4);
            tRisk.setCourse2Id(5);
        } else {
            tRisk.setCourse1Id(14);
            tRisk.setCourse2Id(11);
        }
        tRisk.setResult(tmp);
        tRiskService.addTestItem(tRisk);  // update到数据库
        return CommonResult.success(transferRiskCustom(tRisk));
    }

    //客户端获取运动能力评估列表
    @GetMapping(value = "/getRiskList/{userId}", produces = {"application/json;charset=UTF-8"})
    public CommonResult getRiskList(@PathVariable("userId") Long userId)throws Exception{
        List<TRisk> tRiskList =tRiskService.getRiskList(userId);
        return CommonResult.success(tRiskList);
    }

    // 判断单项是否及格
    public Boolean isStandard(String type, int score) {
        if (type.equals("一类")) {
            return score >= 65;
        } else if (type.equals("二类")) {
            return score >= 60;
        }
        return score >= 55;
    }
    // 客户端上传自主考核结果
    @PostMapping(value = "/testExamineUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult testExamineUpload(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long userID = studentTestInfo.getUserId();
        Long testId = studentTestInfo.getTestId();
        Long count = studentTestInfo.getCount();
        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
        int gender = tuser.getGender();
        int age_group_id = 1;
        if (age > 24)
            age_group_id += Math.ceil((age - 24)/3.0);

        TExamine tExamine = new TExamine();
        tExamine.setUserId(userID);
        Integer score = 0;
        if (testId != 11L)
            tExamineService.getTestScore(testId, gender, age_group_id, count);

        switch (testId.intValue()) {
            case 2: { // 仰卧起做
                tExamine.setSitUpsCnt(count.intValue());
                tExamine.setSitUpsScore(score);
                tExamine.setSitUpsGrade(getGrade(score));
            } break;
            case 6: { // 引体向上
                tExamine.setPullUpCnt(count.intValue());
                tExamine.setPullUpScore(score);
                tExamine.setPullUpGrade(getGrade(score));
            } break;
            case 7: { // 3000跑步
                tExamine.setRuning(count.intValue());
                tExamine.setRuningScore(score);
                tExamine.setRuningGrade(getGrade(score));
            } break;
            case 8: { // 蛇形跑
                tExamine.setSnakeRunning(count.intValue());
                tExamine.setSnakeRuningScore(score);
                tExamine.setSnakeRuningGrade(getGrade(score));
            } break;
            case 11: { // 负重组合
                tExamine.setLoadComb(count.intValue());
                String tmpGrade = "";
                if (age_group_id == 1) {
                    if (count.intValue() < 2500)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2700)
                        tmpGrade = "良好";
                    else if (count.intValue() < 2900)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                } else if (age_group_id == 2) {
                    if (count.intValue() < 2540)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2740)
                        tmpGrade = "良好";
                    else if (count.intValue() < 2940)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                } else if (age_group_id == 3) {
                    if (count.intValue() < 2580)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2780)
                        tmpGrade = "良好";
                    else if (count.intValue() < 2980)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                } else if (age_group_id == 4) {
                    if (count.intValue() < 2620)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2820)
                        tmpGrade = "良好";
                    else if (count.intValue() < 3020)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                } else if (age_group_id == 5) {
                    if (count.intValue() < 2660)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2860)
                        tmpGrade = "良好";
                    else if (count.intValue() < 3060)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                } else if (age_group_id == 6) {
                    if (count.intValue() < 2660)
                        tmpGrade = "优秀";
                    else if (count.intValue() < 2860)
                        tmpGrade = "良好";
                    else if (count.intValue() < 3060)
                        tmpGrade = "及格";
                    else
                        tmpGrade = "不及格";
                }
                tExamine.setLoadGrade(tmpGrade);
            } break;
            default:
                break;
        }

        System.out.println(userID + " " + testId + " " + count);
        // 存在就update
        // 否则就修改
        // 根据userId查询 t_capability 表中是否有对应的记录 没有则插入 一条 有则update
        if (tExamineService.findByUserId(userID) != null && tExamineService.findByUserId(userID).getScore() == null) {
            tExamineService.addTestItem(tExamine);
        } else {
            // insert
            tExamine.setCreateTime(new Date());
            //TODO 这里需要获取用户bmi数据进行等级判定
            tExamine.setBmiGrade("合格");
            tExamineService.insertTestRecord(tExamine);
        }
        return CommonResult.success();
    }

    // 客户端上传自主考核结果 专项
    @PostMapping(value = "/testExamineSpecialUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult testExamineSpecialUpload(@RequestBody Map<String, String> params)throws Exception{
        Long userID = Long.valueOf(params.get("userId"));
        Long testId = Long.valueOf(params.get("testId"));
        String grade = params.get("grade");

        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        if (tuser == null)
            return CommonResult.fail("该用户不存在");

        TExamine tExamine = new TExamine();
        tExamine.setUserId(userID);

        switch (testId.intValue()) {
            case 9: { // 木马2
                tExamine.setHorseGrade(grade);
            } break;
            case 10: { // 双杠3
                tExamine.setParallelBarsGrade(grade);
            }
            default:
                break;
        }

        System.out.println(userID + " " + testId + " " + grade);
        // 存在就update
        // 否则就修改
        // 根据userId查询 t_capability 表中是否有对应的记录 没有则插入 一条 有则update
        if (tExamineService.findByUserId(userID) != null && tExamineService.findByUserId(userID).getScore() == null) {
            tExamineService.addTestItem(tExamine);
        } else {
            // insert
            tExamine.setCreateTime(new Date());
            //TODO 这里需要获取用户bmi数据进行等级判定
            tExamine.setBmiGrade("合格");
            tExamineService.insertTestRecord(tExamine);
        }
        return CommonResult.success();
    }

    //客户端获取自主考核结果
    @GetMapping("/getExamineReport/{userId}")
    public CommonResult getExamineReport(@PathVariable("userId") Long userId){
        TExamine tExamine = tExamineService.findByUserId(userId);
        int totalScore = 0;
        TUser tUser = tUserService.selectById(userId);
        String type = tUser.getType();
        boolean flag = true;  // 默认合格
        if (tExamine.getSitUpsScore() != null) {
            totalScore += tExamine.getSitUpsScore();
            flag = flag && isStandard(type, tExamine.getSitUpsScore());
        }

        if (tExamine.getPullUpScore() != null) {
            totalScore += tExamine.getPullUpScore();
            flag = flag && isStandard(type, tExamine.getPullUpScore());
        }

        if (tExamine.getSnakeRuningScore() != null) {
            totalScore += tExamine.getSnakeRuningScore();
            flag = flag && isStandard(type, tExamine.getSnakeRuningScore());
        }

        if (tExamine.getRuningScore() != null) {
            totalScore += tExamine.getRuningScore();
            flag = flag && isStandard(type, tExamine.getRuningScore());
        }
        if (tExamine.getHorseGrade() != null && tExamine.getHorseGrade().equals("不及格"))
            flag = false;  // 不及格

        if (tExamine.getParallelBarsGrade()!= null && tExamine.getParallelBarsGrade().equals("不及格"))
            flag = false;  // 不及格

        if (tExamine.getLoadGrade() != null && tExamine.getLoadGrade().equals("不及格"))
            flag = false;
        tExamine.setScore(totalScore);
        String tmp;

        if (type.equals("一类")) {
            if (totalScore < 260)
                tmp = "不及格";
            else if (totalScore < 340)
                tmp = "及格";
            else if (totalScore < 380)
                tmp = "良好";
            else if (totalScore < 440)
                tmp = "优秀";
            else if (totalScore < 480)
                tmp = "特3级";
            else if (totalScore < 500)
                tmp = "特2级";
            else
                tmp = "特1级";
        } else if (type.equals("二类")) {
            if (totalScore < 240)
                tmp = "不及格";
            else if (totalScore < 320)
                tmp = "及格";
            else if (totalScore < 360)
                tmp = "良好";
            else if (totalScore < 440)
                tmp = "优秀";
            else if (totalScore < 480)
                tmp = "特3级";
            else if (totalScore < 500)
                tmp = "特2级";
            else
                tmp = "特1级";
        } else {
            if (totalScore < 220)
                tmp = "不及格";
            else if (totalScore < 300)
                tmp = "及格";
            else if (totalScore < 340)
                tmp = "良好";
            else if (totalScore < 440)
                tmp = "优秀";
            else if (totalScore < 480)
                tmp = "特3级";
            else if (totalScore < 500)
                tmp = "特2级";
            else
                tmp = "特1级";
        }

        if (flag == false)
            tExamine.setResult("不及格");
        else
            tExamine.setResult(tmp);
        tExamineService.addTestItem(tExamine);
        return CommonResult.success(tExamine);
    }

    //客户端上传自由训练的训练文件
    @PostMapping(value = "/trainingUpload",consumes = {"multipart/form-data"})
    public CommonResult trainingResult(TWqxTrain tWqxTrain, @RequestParam("file") MultipartFile file)throws Exception{
        String format=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String path=format;
        //获取上传文件的名称
        String filename=file.getOriginalFilename();
        if(file.isEmpty()){
            return CommonResult.fail();
        }else {
            String uuid=UUID.randomUUID().toString().replace("-","");
            String videoName=uuid+"-"+filename;
            System.out.println(videoName);
            path= path+videoName;
            File dest=new File(new File(userUploadpath+filename).getAbsolutePath());// dist为文件，有多级目录的文件
            if(!dest.getParentFile().exists()){//这里使用.getParentFile()，目的就是取文件前面目录的路径
                dest.getParentFile().mkdirs();//如果路径不存在创建路径
            }
            //完成文件上传
            file.transferTo(dest);
            //上传用户训练信息
            tWqxTrain.setTrainingPath(userUploadpath+filename);
            if (tWqxTrainService.insertTrainRecord(tWqxTrain)){
                return CommonResult.success();
            }else {
                return CommonResult.fail();
            }

        }
    }

    //指导视频文件下载
    @RequestMapping(value = "/filedownload/{videoId}")
    public CommonResult Videodownload(@PathVariable("videoId") Integer videoId, HttpServletResponse response)throws IOException {
        TTrainingVideo trainingVideo=trainingVideoService.findFileName(videoId);
        if (null==trainingVideo){
            return CommonResult.fail("文件不存在!");
        }else {
            String filename =trainingVideo.getFileName();     //+".mp4" 感觉id查询文件名称
            String path=trainingVideo.getSavePath();
            File file = new File(path + filename);
            if (file.exists()){
                String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");     //防止中文乱码
                response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
                byte[] buffer=new byte[1024];
                FileInputStream fis=null;
                BufferedInputStream bis=null;
                try {
                    fis=new FileInputStream(file);
                    bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                    int i=bis.read(buffer);
                    while (i!=-1){
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                    return CommonResult.success();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (bis!=null){
                        try{
                            bis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (fis!=null){
                        try{
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                return CommonResult.success();
            }else {
                return CommonResult.fail();
            }
        }
    }


    //指导视频文件下载
    @RequestMapping(value = "/videodownload/{videoId}")
    public CommonResult videoDownload(@PathVariable("videoId") Integer videoId, HttpServletResponse response)throws IOException {
        TGuideVideos tGuideVideos = tGuideVideosService.findFileName(videoId);

        if (null==tGuideVideos){
            return CommonResult.fail("文件不存在!");
        }else {
            String filename =tGuideVideos.getFileName();     //+".mp4" 感觉id查询文件名称
            String path=tGuideVideos.getSavePath();
            File file = new File(path + filename);
            if (file.exists()){
                String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");     //防止中文乱码
                response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
                byte[] buffer=new byte[1024];
                FileInputStream fis=null;
                BufferedInputStream bis=null;
                try {
                    fis=new FileInputStream(file);
                    bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                    int i=bis.read(buffer);
                    while (i!=-1){
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                    return CommonResult.success();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (bis!=null){
                        try{
                            bis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (fis!=null){
                        try{
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                return CommonResult.success();
            }else {
                return CommonResult.fail();
            }
        }
    }

    //指导视频文件下载
    @RequestMapping(value = "/JTFileDownload/{filename}")
    public CommonResult JTFileDownload(@PathVariable("filename") String filename, HttpServletResponse response)throws IOException {
        String path="/media/JTresource/";
        File file = new File(path + filename);
        if (file.exists()){
            String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");     //防止中文乱码
            response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
            byte[] buffer=new byte[1024];
            FileInputStream fis=null;
            BufferedInputStream bis=null;
            try {
                fis=new FileInputStream(file);
                bis=new BufferedInputStream(fis);
                OutputStream os=response.getOutputStream();
                int i=bis.read(buffer);
                while (i!=-1){
                    os.write(buffer,0,i);
                    i=bis.read(buffer);
                }
                return CommonResult.success();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (bis!=null){
                    try{
                        bis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (fis!=null){
                    try{
                        fis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }

    }

    //识别模型文件下载
    @GetMapping("/filedownload/xunlianmoxing/{filename}")
    public void filedownload(@PathVariable("filename") String filename, HttpServletResponse response)throws IOException {
//        String filename ="training1.csv";     //文件名称
        System.out.println(filename);
        File file = new File(moxingpath + filename);
        if (file.exists()){
            String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");     //防止中文乱码
            response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
            byte[] buffer=new byte[1024];
            FileInputStream fis=null;
            BufferedInputStream bis=null;
            try {
                fis=new FileInputStream(file);
                bis=new BufferedInputStream(fis);
                OutputStream os=response.getOutputStream();
                int i=bis.read(buffer);
                while (i!=-1){
                    os.write(buffer,0,i);
                    i=bis.read(buffer);
                }
//                    return CommonResult.success();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (bis!=null){
                    try{
                        bis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (fis!=null){
                    try{
                        fis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
//                return CommonResult.success();
        }else {

        }
    }


    //根据student_id获取xxx用户运动处方
    @GetMapping("/WQXtrainplan/{studentId}")
    public CommonResult GetWQXtrainplan(@PathVariable("studentId") Long studentId){
        if (studentService.selectById(studentId)==null){
            return CommonResult.fail("该用户不存在");
        }else {
            List<TWqxplanStudentCustom> list = tWqxplanStudentService.getStudentWQXPlan(studentId);
            return CommonResult.success(list);
        }
    }

    //根据plan_id获取处方详细内容
    @GetMapping("/getWQXplanDetail/{planId}")
    public CommonResult GetWQXplan(@PathVariable("planId") Long planId){
        if (tWqxplanNpService.getWQXplanById(planId)==null){
            return CommonResult.fail("处方不存在");
        }else {
            List<TWqxplanPrescription> list=tWqxplanPrescriptionService.PlanDetail(planId);
            return CommonResult.success(list);
        }
    }
//    @GetMapping("/testInterface/{planId}")
//    public CommonResult testInterface(@PathVariable("planId") Long planId){
//        TWqxplanNp tWqxplanNp = tWqxplanNpService.getWQXplanById(planId);
//        if (tWqxplanNp != null){
//            return CommonResult.success(tWqxplanNp);
//        }else {
//            return CommonResult.fail("处方不存在");
//        }
//    }
    //处方训练记录上传
    @PostMapping("/uploadWQXtrainRecord")
    public CommonResult uploadWQXtrainRecord(@RequestBody TWqxplanRecord tWqxplanRecord){
        if (tWqxplanNpService.getWQXplanById(tWqxplanRecord.getWqxplanId()) != null &&
                studentService.selectById(tWqxplanRecord.getStudentId())!=null) {
            tWqxplanRecord.setCreateTime(new Date());
            tWqxplanRecord.setUploadTime(new Date());
            if (tWqxplanRecordService.insertWqxplanRecord(tWqxplanRecord)==1){
                return CommonResult.success();
            } else {
                return CommonResult.fail();
            }
        } else {
            return CommonResult.fail();
        }
    }


    // 根据传递的参数获取资源库中的视频
    @PostMapping(value = "/getGuideVideoList", produces = {"application/json;charset=UTF-8"})
    public CommonResult getGuideVideoList(@RequestBody Map<String, Integer> params)throws Exception{
        Integer actionTypeId = params.get("action_type_id");
        Integer positionId = params.get("position_id");
        List<TGuideVideos> tGuideVideosList =tGuideVideosService.getGuideVideoListByid(actionTypeId, positionId);
        if (tGuideVideosList.size()==0){
            return CommonResult.fail();
        }else {
            return CommonResult.success(tGuideVideosList);
        }
    }

    // 根据用户id 获取用户信息
    //根据plan_id获取处方详细内容
    @GetMapping("/getUserInfo/{userId}")
    public CommonResult getUserInfo(@PathVariable("userId") Long userId){
            return CommonResult.success(tUserService.selectById(userId));
    }

    @GetMapping("/getUserInfoByNo/{userNo}")
    public CommonResult getUserInfoByNo(@PathVariable("userNo") String userNo){
        return CommonResult.success(tUserService.selectByNo(userNo));
    }

    //根据course_id获取课程详情
    @GetMapping("/getCourseDetail/{courseId}")
    public CommonResult getCourseDetail(@PathVariable("courseId") Long courseId){
        TCourseInfo tCourseInfo = tCourseInfoService.getCourseById(courseId);
        if (tCourseInfo == null){
            return CommonResult.fail("课程不存在");
        }else {
            List<TCourseDetail> tCourseDetailList = tCourseDetailService.getDetail(courseId);
            TCourseInfoCustom tCourseInfoCustom = new TCourseInfoCustom();
            BeanUtils.copyProperties(tCourseInfo, tCourseInfoCustom);
            tCourseInfoCustom.setTCourseDetailList(tCourseDetailList);
            return CommonResult.success(tCourseInfoCustom);
        }
    }

}
