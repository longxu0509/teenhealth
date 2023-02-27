package springboot.Controller.terminal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import com.sun.xml.internal.dtdparser.DTDEventListener;
import io.swagger.models.auth.In;
import javafx.util.Pair;
import org.apache.commons.beanutils.locale.converters.DoubleLocaleConverter;
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
import java.text.DateFormat;
import java.text.ParseException;
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


    @Autowired
    private TUserEvalService tUserEvalService;

    @Autowired
    private TEvalEventService tEvalEventService;

    @Autowired
    private TEvalReportService tEvalReportService;

    @Autowired
    private TActionEventService tActionEventService;

    @Autowired
    private TDictService tDictService;

    @Autowired
    private TActionService tActionService;

    @Autowired
    private TCourseMetaService tCourseMetaService;

    @Autowired
    private TReportCourseService tReportCourseService;

    @Autowired
    private TActionGuideService tActionGuideService;

    @Autowired
    private TDetailService tDetailService;

    @Autowired
    private TTrainRecordService tTrainRecordService;

    @Autowired
    private TActionRequirementService tActionRequirementService;

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
        Long testId = studentTestInfo.getActionId();
        Long count = studentTestInfo.getCount().longValue();
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

    public String getGrade(String userType, Integer score) {
        String str = "";
        if (userType.equals("一类")) {
            if (score >= 105) {
                str = Grade.FIVE.getName();
            } else if (score >= 85) {
                str = Grade.FOUR.getName();
            } else if (score >= 65) {
                str = Grade.THREE.getName();
            } else if (score >= 55) {
                str = Grade.TWO.getName();
            } else {
                str = Grade.ONE.getName();
            }
        } else if (userType.equals("二类")) {
            if (score >= 100) {
                str = Grade.FIVE.getName();
            } else if (score >= 80) {
                str = Grade.FOUR.getName();
            } else if (score >= 60) {
                str = Grade.THREE.getName();
            } else if (score >= 50) {
                str = Grade.TWO.getName();
            } else {
                str = Grade.ONE.getName();
            }
        } else if (userType.equals("三类")) {
            if (score >= 95) {
                str = Grade.FIVE.getName();
            } else if (score >= 75) {
                str = Grade.FOUR.getName();
            } else if (score >= 55) {
                str = Grade.THREE.getName();
            } else if (score >= 45) {
                str = Grade.TWO.getName();
            } else {
                str = Grade.ONE.getName();
            }
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
        Long testId = studentTestInfo.getActionId();
        Long count = studentTestInfo.getCount().longValue();
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
                tCapability.setPushUpGrade(getGrade("二类", score));
            } break;
            case 2: { // 仰卧起做
                tCapability.setSitUpsCnt(count.intValue());
                tCapability.setSitUpsScore(score);
                tCapability.setSitUpsGrade(getGrade("二类", score));
            } break;
            case 3: { // 原地纵跳
                tCapability.setJump(count.intValue());
                tCapability.setJumpScore(score);
                tCapability.setJumpGrade(getGrade("二类", score));
            } break;
            case 4: { // 坐位体前屈
                tCapability.setSitForward(count.intValue());
                tCapability.setSitForwardScore(score);
                tCapability.setSitForwardGrade(getGrade("二类", score));
            } break;
            case 6: { // 引体向上
                tCapability.setPullUpCnt(count.intValue());
                tCapability.setPullUpScore(score);
                tCapability.setPullUpGrade(getGrade("二类", score));
            } break;
            case 7: { // 跑步
                tCapability.setRuning(count.intValue());
                tCapability.setRuningScore(score);
                tCapability.setRuningGrade(getGrade("二类", score));
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
        Long testId = studentTestInfo.getActionId();
        Long count = studentTestInfo.getCount().longValue();
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
        Long testId = studentTestInfo.getActionId();
        Double count = studentTestInfo.getCount();
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
        if (testId != 11L) {
            if (testId == 8L)
                score = tExamineService.getTestScore(testId, gender, age_group_id, new Double(count*100).longValue());
            else
                score = tExamineService.getTestScore(testId, gender, age_group_id, count.longValue());
        }

        switch (testId.intValue()) {
            case 2: { // 仰卧起做
                tExamine.setSitUpsCnt(count.intValue());
                tExamine.setSitUpsScore(score);
                tExamine.setSitUpsGrade(getGrade("二类",score));
            } break;
            case 6: { // 引体向上
                tExamine.setPullUpCnt(count.intValue());
                tExamine.setPullUpScore(score);
                tExamine.setPullUpGrade(getGrade("二类", score));
            } break;
            case 7: { // 3000跑步
                tExamine.setRuning(count.intValue());
                tExamine.setRuningScore(score);
                tExamine.setRuningGrade(getGrade("二类", score));
            } break;
            case 8: { // 蛇形跑
                tExamine.setSnakeRunning(count.intValue());
                tExamine.setSnakeRuningScore(score);
                tExamine.setSnakeRuningGrade(getGrade("二类", score));
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
    @RequestMapping(value = "/downloadVideo/{videoCode}")
    public CommonResult getVideo(@PathVariable("videoCode") String videoCode, HttpServletResponse response)throws IOException {
        TAction tAction = tActionService.selectByCode(videoCode);
        if (null==tAction){
            return CommonResult.fail("文件不存在!");
        }else {
            String path= "/media/AITraining/uploads/" + videoCode + "-" + tAction.getActionName() + ".mp4.mp4";
            String filename = tAction.getActionName() + ".mp4";
            File file = new File(path);
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


//    //指导视频文件下载
//    @RequestMapping(value = "/videodownload/{videoId}")
//    public CommonResult videoDownload(@PathVariable("videoId") Integer videoId, HttpServletResponse response)throws IOException {
//        TGuideVideos tGuideVideos = tGuideVideosService.findFileName(videoId);
//
//        if (null==tGuideVideos){
//            return CommonResult.fail("文件不存在!");
//        }else {
//            String filename =tGuideVideos.getFileName();     //+".mp4" 感觉id查询文件名称
//            String path=tGuideVideos.getSavePath();
//            File file = new File(path + filename);
//            if (file.exists()){
//                String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");     //防止中文乱码
//                response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
//                byte[] buffer=new byte[1024];
//                FileInputStream fis=null;
//                BufferedInputStream bis=null;
//                try {
//                    fis=new FileInputStream(file);
//                    bis=new BufferedInputStream(fis);
//                    OutputStream os=response.getOutputStream();
//                    int i=bis.read(buffer);
//                    while (i!=-1){
//                        os.write(buffer,0,i);
//                        i=bis.read(buffer);
//                    }
//                    return CommonResult.success();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    if (bis!=null){
//                        try{
//                            bis.close();
//                        }catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis!=null){
//                        try{
//                            fis.close();
//                        }catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                return CommonResult.success();
//            }else {
//                return CommonResult.fail();
//            }
//        }
//    }

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
    @GetMapping("/getCourseDetail_bak/{courseId}")
    public CommonResult getCourseDetail_bak(@PathVariable("courseId") Long courseId){
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

    // 根据userID创建评估事件
    @PostMapping(value = "/createEvalEvent")
    public CommonResult createEvalEvent(@RequestBody Map<String, Object> params)throws Exception{
        Long userID = ((Integer)params.get("userID")).longValue();
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse((String) params.get("startTime"));

        // t_eval_event表中插入一条记录
        TEvalEvent tEvalEvent = new TEvalEvent();
        tEvalEvent.setCreateTime(new Date());
        // 获取字典里的配置倒计时
        String countdown = tDictService.getVal("countdown");
        tEvalEvent.setCountdown(Integer.valueOf(countdown));
        tEvalEvent.setStatus(0);
        tEvalEvent.setStartTime(startTime);
        tEvalEventService.insertEvent(tEvalEvent);

        // 获取刚刚插入记录的id
        Long eventId = tEvalEvent.getId();

        // 向t_eval_report表中插入一条记录，包含userID
        TEvalReport teValReport = new TEvalReport();
        teValReport.setUserId(userID);
        if (tEvalReportService.insertReport(teValReport) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();
        Long reportId = teValReport.getId();

        // 向 t_user_eval表插入一条数据
        TUserEval tUserEval = new TUserEval();
        tUserEval.setUserId(userID);
        tUserEval.setReportId(reportId);
        tUserEval.setEvalEventId(eventId);
        tUserEval.setCreateTime(new Date());
        if (tUserEvalService.insertUserEval(tUserEval) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        // 向t_action_event表插入6记录
        TActionEvent tActionEvent = new TActionEvent();

        tActionEvent.setActionStatus(0); // 0表示没有测试
        tActionEvent.setMaxScore(300);
        tActionEvent.setEvalEventId(eventId);
        tActionEvent.setCreateTime(new Date());
        // 3000米
        tActionEvent.setActionName(Action.LRUNNING.getName());
        tActionEvent.setActionId(Action.LRUNNING.getIndex());
        tActionEvent.setType(Action.LRUNNING.getType());
        tActionEvent.setPosition(Action.LRUNNING.getPosition());
        tActionEvent.setUnit("秒");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        // 30米*2 蛇形跑
        tActionEvent.setActionName(Action.SRUNNING.getName());
        tActionEvent.setActionId(Action.SRUNNING.getIndex());
        tActionEvent.setType(Action.SRUNNING.getType());
        tActionEvent.setPosition(Action.SRUNNING.getPosition());
        tActionEvent.setUnit("秒");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        // 俯卧撑
        tActionEvent.setMaxScore(150);
        tActionEvent.setActionName(Action.PUSHUP.getName());
        tActionEvent.setActionId(Action.PUSHUP.getIndex());
        tActionEvent.setType(Action.PUSHUP.getType());
        tActionEvent.setPosition(Action.PUSHUP.getPosition());
        tActionEvent.setImg("http://47.122.6.103:8080/tph/service/JTFileDownload/action-fwc.jpg");
        tActionEvent.setDuration("120");
        tActionEvent.setUnit("个");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();
        // 引体向上
        tActionEvent.setActionName(Action.PULLUP.getName());
        tActionEvent.setActionId(Action.PULLUP.getIndex());
        tActionEvent.setType(Action.PULLUP.getType());
        tActionEvent.setPosition(Action.PULLUP.getPosition());
        tActionEvent.setImg("http://47.122.6.103:8080/tph/service/JTFileDownload/action-ytxs.jpg");
        tActionEvent.setDuration("120");
        tActionEvent.setUnit("个");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        // 仰卧起做
        tActionEvent.setActionName(Action.SITUP.getName());
        tActionEvent.setActionId(Action.SITUP.getIndex());
        tActionEvent.setType(Action.SITUP.getType());
        tActionEvent.setPosition(Action.SITUP.getPosition());
        tActionEvent.setImg("http://47.122.6.103:8080/tph/service/JTFileDownload/action-ywqz.jpg");
        tActionEvent.setDuration("120");
        tActionEvent.setUnit("个");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        // 单腿臀桥
        tActionEvent.setActionName(Action.LEGHIGBRIDGE.getName());
        tActionEvent.setActionId(Action.LEGHIGBRIDGE.getIndex());
        tActionEvent.setType(Action.LEGHIGBRIDGE.getType());
        tActionEvent.setPosition(Action.LEGHIGBRIDGE.getPosition());
        tActionEvent.setImg("http://47.122.6.103:8080/tph/service/JTFileDownload/action-dttq.jpg");
        tActionEvent.setDuration("120");
        tActionEvent.setUnit("个");
        if (tActionEventService.insertActionEvent(tActionEvent) != 0)
            System.out.println("插入成功");
        else
            return CommonResult.fail();

        return CommonResult.success(startTime);
    }


    // 客户端上传动作评估结果
    @PostMapping(value = "/evalActionUpload", produces = {"application/json;charset=UTF-8"})
    public CommonResult evalActionUpload(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long userID = studentTestInfo.getUserId();
        Long actionId = studentTestInfo.getActionId();
        Double count = studentTestInfo.getCount();
        Integer calories = studentTestInfo.getCalories();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse(studentTestInfo.getStartTime());  // 动作测试时间

        // 根据传入的userid 查询 t_user_eval表的最新的一条评估事件id eval_event_id
        TUserEval tUserEval = tUserEvalService.selectByUserID(userID);
        // 根据id 获取t_eval_event 获取评估事件的开始时间 判断是否超时
        TEvalEvent tEvalEvent = tEvalEventService.selectByID(tUserEval.getEvalEventId());

        if (isOverTime(startTime, tEvalEvent.getStartTime()) == true)
            return CommonResult.fail("超时, 请重新评估");

        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        String userType = tuser.getType();

        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
        int gender = tuser.getGender();
        int age_group_id = 1;
        if (age > 24)
            age_group_id += Math.ceil((age - 24)/3.0);


        // 创建一个动作测试事件
        TActionEvent tActionEvent = new TActionEvent();

        tActionEvent.setEvalEventId(tUserEval.getEvalEventId());
        tActionEvent.setStartTime(startTime);
        tActionEvent.setCalories(calories);

        Integer score = 0;
        if (actionId == Action.SRUNNING.getIndex())
            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, (long)(count*100));
        else
            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, count.longValue());
        tActionEvent.setActionId(actionId.intValue());
        tActionEvent.setActionCnt(count);
        tActionEvent.setActionScore(score);
        tActionEvent.setActionGrade(getGrade(userType, score));  // 根据不同让人员类别 评定等级
        if (Grade.getGrade(getGrade(userType, score)) <= 2)  // 不合格需要加入 advice
            tActionEvent.setAdvice(tDictService.getVal(Action.getName(actionId.intValue())));
        tActionEvent.setActionStatus(1);
        System.out.println(userID + " " + actionId + " " + count);

        // 向测试动作事件表中更新一条记录 根据评估事件id 测试动作id
        if (tActionEventService.updateByID(tActionEvent) == 0)
            return CommonResult.fail("更新数据失败");

        // 每次更新完 获取t_action_event表中当前评估事件id下 action_status为1的个数 个数为6则更新
        // t_eval_event表中的所有评估时间完成的状态值为1
        if (tActionEventService.getSuccessCnt(tUserEval.getEvalEventId()) == 6) {
            //
            if (tEvalEventService.updateStatusByID(tUserEval.getEvalEventId()) == 0)
                return CommonResult.fail("更新数据失败");
        }

        return CommonResult.success();
    }


    // 客户端上传动作评估结果
    @PostMapping(value = "/AIFreeTraining", produces = {"application/json;charset=UTF-8"})
    public CommonResult AIFreeTraining(@RequestBody StudentTestInfo studentTestInfo)throws Exception{
        Long userID = studentTestInfo.getUserId();
        Long actionId = studentTestInfo.getActionId();
        Double count = studentTestInfo.getCount();
        Integer calories = studentTestInfo.getCalories();
        String duration = studentTestInfo.getDuration();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = fmt.parse(studentTestInfo.getStartTime());  // 动作测试时间

        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
//        String userType = tuser.getType();

        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
//        int gender = tuser.getGender();
//        int age_group_id = 1;
//        if (age > 24)
//            age_group_id += Math.ceil((age - 24)/3.0);

        TAction tAction = tActionService.selectByCode(actionId.toString());
        // 创建训练记录
        TTrainRecord tTrainRecord = new TTrainRecord();
        tTrainRecord.setUserId(userID);
        tTrainRecord.setActionId(actionId);
        tTrainRecord.setStartTime(startTime);
        tTrainRecord.setDuration(duration);
        tTrainRecord.setCalories(calories);
        tTrainRecord.setActionName(tAction.getActionName());
        tTrainRecord.setActionCnt(count.intValue());

        tTrainRecord.setCreateTime(new Date());

//        Integer score = 0;
//        if (actionId == Action.SRUNNING.getIndex())
//            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, (long)(count*100));
//        else
//            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, count.longValue());
//        tTrainRecord.setActionScore(score);
//        tTrainRecord.setActionGrade(getGrade(userType, score));

        tTrainRecordService.insertRecord(tTrainRecord);

        return CommonResult.success(tTrainRecord);
    }


    @PostMapping("/test")
    public CommonResult TestApi(@RequestBody StudentTestInfo studentTestInfo) throws ParseException {
        Long userID = studentTestInfo.getUserId();
        Long actionId = studentTestInfo.getActionId();
        Double count = studentTestInfo.getCount();
        // 根据userID 查找出用户的性别年龄信息。
        TUser tuser = tUserService.selectById(userID);
        if (tuser == null)
            return CommonResult.fail("该用户不存在");
        int age = tuser.getAge();
        int gender = tuser.getGender();
        int age_group_id = 1;
        if (age > 24)
            age_group_id += Math.ceil((age - 24)/3.0);

        Integer score = 0;
        if (actionId == Action.SRUNNING.getIndex())
            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, (long)(count*100));
        else
            score = tTestStandardService.getTestScore(actionId, gender, age_group_id, count.longValue());
        return CommonResult.success(score);
    }

    public  boolean isOverTime(Date nowDate, Date startDate) {

        long nd = 1000 * 24 * 60 * 60;//每天毫秒数

        long nh = 1000 * 60 * 60;//每小时毫秒数

        long nm = 1000 * 60;//每分钟毫秒数

        long diff = nowDate.getTime() - startDate.getTime(); // 获得两个时间的毫秒时间差异

        long day = diff / nd;   // 计算差多少天

        long hour = diff % nd / nh; // 计算差多少小时

        long min = diff % nd % nh / nm;  // 计算差多少分钟

        System.out.println(day + "天" + hour + "小时" + min + "分钟");
        if (day == 0 && hour < 4)
            return false;
        return true;
    }


    //根据user_id获取用户最新一次运动评估的信息
    @GetMapping("/getEvalInfo/{userId}")
    public CommonResult getEvalInfo(@PathVariable("userId") Long userId){
        // 1. 根据userID 获取用户最新的一条评估事件
        TUserEval tUserEval = tUserEvalService.selectByUserID(userId);
        Long evalEventID = tUserEval.getEvalEventId();

        TEvalEventCustom tEvalEventCustom = new TEvalEventCustom();
        // 2. 查询evalEventID 对应的所有评估事件
        BeanUtils.copyProperties(tEvalEventService.selectByID(evalEventID), tEvalEventCustom);
        Integer totalTime = tEvalEventService.getTotalTime(evalEventID);
        Integer totalCalories = tEvalEventService.getTotalCalories(evalEventID);
        List<TActionEvent> tActionEventList = tActionEventService.selectList(evalEventID);
        tEvalEventCustom.setTActionEventList(tActionEventList);
        tEvalEventCustom.setTotalTime(totalTime);
        tEvalEventCustom.setTotalCalories(totalCalories);
        return CommonResult.success(tEvalEventCustom);
    }

    private String getTotalGrade(int totalScore) {
        String tmp;
        if (totalScore >= 550) {
            tmp = "优秀";
        } else if (totalScore >= 500) {
            tmp = "良好";
        } else if (totalScore >= 450) {
            tmp = "处于平均";
        } else if (totalScore >= 400) {
            tmp = "低于平均";
        } else
            tmp = "不合格";
        return tmp;
    }

    // 根据用户id获取用户运动能力评评估报告 返回user最新的评估报告
    @GetMapping("/getEvalReport/{userId}")
    public CommonResult getEvalReport(@PathVariable("userId") Long userId){
        // 1. 根据userID 获取用户最新的一条评估事件id 和 最新的评估报告id
        TUserEval tUserEval = tUserEvalService.selectByUserID(userId);
        Long evalEventID = tUserEval.getEvalEventId();
        Long reportId = tUserEval.getReportId();
        // 2. 判断评估事件状态是否完成
        TEvalEvent tEvalEvent = tEvalEventService.selectByID(evalEventID);
        if (tEvalEvent.getStatus() == 0)
            return CommonResult.fail("评估未完成");

        List<TActionEvent> tActionEventList = tActionEventService.selectList(evalEventID);
        if (tReportCourseService.selectByReportId(reportId) == 0) {
            // 没有生成课程开始生成课程
            // 3. 计算评估总分 总等级 报告到report表中
            int totalScore = 0;
            boolean gradeFlage = false; // 默认为false 如果有一项不合格 为true
            StringBuilder report = new StringBuilder();  // 文字评估结果
//            List<TActionEvent> tActionEventList = tActionEventService.selectList(evalEventID);
            TReportCourse tReportCourse = new TReportCourse();
            tReportCourse.setReportId(reportId);
//            List<Pair<Integer, Integer>> list = new ArrayList<>();
            for (TActionEvent actionEvent : tActionEventList) {
                totalScore += actionEvent.getActionScore();
                report.append(actionEvent.getType());
//                list.add(new Pair<>(actionEvent.getActionId(), actionEvent.getActionScore()));
                // 获得单项评估的等级1 2 3 4 5
                int level = Grade.getGrade(actionEvent.getActionGrade());
                report.append(actionEvent.getActionGrade() + ",");
                if (level <= 2)
                    gradeFlage = true;
                // 根据评估的等级和部位 去课程库里找课程  热身 拉伸的课程全部范围 力量随机找两个
                String position = actionEvent.getPosition();
                System.out.println("position:" + position);
                System.out.println("level:" + level);
                // 根据部位+等级+热身 三个字段查询 所有热身动作课程 加入到course列表
                List<TCourseMeta> tCourses = tCourseMetaService.selectByConditinon("热身", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);

                // 根据部位+等级+拉升 三个字段查询 所有拉伸动作课程 加入到course列表
                tCourses = tCourseMetaService.selectByConditinon("拉伸", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);

                // 根据部位+等级+力量 三个字段查询 所有拉伸动作课程 在返回列表里随机选择两个课程
                tCourses = tCourseMetaService.selectByConditinon("力量", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);
                //
                System.out.println("创建课程……");
            }
            report.append("建议您根据系统推荐的训练处方进行运动能力提升/运动风险改善训练。");

            TEvalReport tEvalReport = new TEvalReport();
            tEvalReport.setId(reportId);
            tEvalReport.setUserId(userId);
            tEvalReport.setCreateTime(new Date());
            tEvalReport.setScore(totalScore);
            if (gradeFlage)
                tEvalReport.setGrade("不合格（因单项不合格）");
            else
                tEvalReport.setGrade(getTotalGrade(totalScore));

            String preReport = "您的整体运动评估结果: " + tEvalReport.getGrade() + "。其中, 您的";
            tEvalReport.setReport(preReport + report);
            if (tEvalReportService.updateReport(tEvalReport) == 0)
                return CommonResult.fail("评估报告创建失败");
        }
        TEvalReportCustom tEvalReportCustom = new TEvalReportCustom();
        TEvalReport tEvalReport = tEvalReportService.selectByrReportId(reportId);
        BeanUtils.copyProperties(tEvalReport, tEvalReportCustom);

        List<TCourseMeta> tCourseMetaList = new ArrayList<>();
        List<TReportCourse> tReportCourses = tReportCourseService.selectListById(reportId);

        for (TReportCourse tReportCourse : tReportCourses) {
            TCourseMeta tCourseMeta = tCourseMetaService.selectByCode(tReportCourse.getCourseCode());
            tCourseMetaList.add(tCourseMeta);
        }
        tEvalReportCustom.setTCourseList(tCourseMetaList);
        tEvalReportCustom.setTActionEventList(tActionEventList);
//        // 按成绩从小到大排序
//        Collections.sort(list, (p1, p2) -> p1.getValue().compareTo(p2.getValue()));
//
//        for (Pair<Integer, Integer> pair : list) {
//            System.out.println(pair.getKey() + ": " + pair.getValue());
//        }
//        int minAction1 = list.get(0).getKey();
//        if (minAction1 == Action.SRUNNING.getIndex() || minAction1 == Action.SITUP.getIndex() || minAction1 == Action.LEGHIGBRIDGE.getIndex()) {
//            // 推荐 1 课程
//            System.out.println("课程1");
//        } else if (minAction1 == Action.PUSHUP.getIndex() || minAction1 == Action.PULLUP.getIndex()) {
//            // 推荐 2 课程
//            System.out.println("课程2");
//        } else if (minAction1 == Action.LRUNNING.getIndex()) {
//            // 推荐 2 课程
//            System.out.println("课程3");
//        }
        return CommonResult.success(tEvalReportCustom);
    }

    // 根据用户id获取用户运动能力评评估报告 返回user所有
    @GetMapping("/getHistoryEvalReport/{userId}")
    public CommonResult getHistoryEvalReport(@PathVariable("userId") Long userId){
        // 1. 根据userID 获取用户所有的按时间倒序排列的评估事件id 和评估报告id
        List<TUserEval> tUserEvalList = tUserEvalService.selectAll(userId);
        List<TEvalReport> tEvalReportList = new ArrayList<>();
        for (TUserEval tUserEval : tUserEvalList) {
            Long reportId = tUserEval.getReportId();
            TEvalReport tEvalReport = tEvalReportService.selectByrReportId(reportId);
            if (tEvalReport.getCreateTime() == null)
                continue;
            tEvalReportList.add(tEvalReport);
        }
        return CommonResult.success(tEvalReportList);
    }

    // 根据用户id获取用户运动能力评评估报告 返回user所有
    @GetMapping("/getLatestEvalReport/{userId}")
    public CommonResult getLatestEvalReport(@PathVariable("userId") Long userId){
        // 1. 根据userID 获取用户所有的按时间倒序排列的评估事件id 和评估报告id
        TUserEval tUserEval = tUserEvalService.selectLatest(userId);

        Long reportId = tUserEval.getReportId();
        TEvalReport tEvalReport = tEvalReportService.selectByrReportId(reportId);
        if (tEvalReport.getCreateTime() == null)
            return CommonResult.success(null);
        return CommonResult.success(tEvalReport);
    }


    // 根据用户id获取用户运动能力评评估报告 返回user所有
    @GetMapping("/getActionDetail/{code}")
    public CommonResult getActionDetail(@PathVariable("code") String code){

        TActionRequirement tActionRequirement = tActionRequirementService.getByCode(code);
        TAction tAction = tActionService.selectByCode(code);
        if (null == tAction)
            return CommonResult.fail("查询详情失败");
        TActionDetail tActionDetail = new TActionDetail();
        BeanUtils.copyProperties(tAction, tActionDetail);
        tActionDetail.setRequirement(tActionRequirement.getRequirement());
        tActionDetail.setDetail(tActionRequirement.getDetail());
        return CommonResult.success(tActionDetail);
    }

    // 根据reportId 获取报告
    @GetMapping("/getEvalReportById/{reportId}")
    public CommonResult getEvalReportById(@PathVariable("reportId") Long reportId){
        // 1. 根据reportId 获取评估事件id 和 userId
        TUserEval tUserEval = tUserEvalService.selectByReportId(reportId);
        Long userId = tUserEval.getUserId();
        Long evalEventID = tUserEval.getEvalEventId();

        // 2. 判断评估事件状态是否完成
        TEvalEvent tEvalEvent = tEvalEventService.selectByID(evalEventID);
        if (tEvalEvent.getStatus() == 0)
            return CommonResult.fail("评估未完成");

        List<TActionEvent> tActionEventList = tActionEventService.selectList(evalEventID);
        if (tReportCourseService.selectByReportId(reportId) == 0) {
            // 没有生成课程开始生成课程
            // 3. 计算评估总分 总等级 报告到report表中
            int totalScore = 0;
            boolean gradeFlage = false; // 默认为false 如果有一项不合格 为true
            StringBuilder report = new StringBuilder();  // 文字评估结果
            TReportCourse tReportCourse = new TReportCourse();
            tReportCourse.setReportId(reportId);
            for (TActionEvent actionEvent : tActionEventList) {
                totalScore += actionEvent.getActionScore();
                report.append(actionEvent.getType());
                int level = Grade.getGrade(actionEvent.getActionGrade());
                report.append(actionEvent.getActionGrade() + ",");
                if (level <= 2)
                    gradeFlage = true;
                // 根据评估的等级和部位 去课程库里找课程  热身 拉伸的课程全部范围 力量随机找两个
                String position = actionEvent.getPosition();
                System.out.println("position:" + position);
                System.out.println("level:" + level);
                // 根据部位+等级+热身 三个字段查询 所有热身动作课程 加入到course列表
                List<TCourseMeta> tCourses = tCourseMetaService.selectByConditinon("热身", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);

                // 根据部位+等级+拉升 三个字段查询 所有拉伸动作课程 加入到course列表
                tCourses = tCourseMetaService.selectByConditinon("拉伸", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);

                // 根据部位+等级+力量 三个字段查询 所有拉伸动作课程 在返回列表里随机选择两个课程
                tCourses = tCourseMetaService.selectByConditinon("力量", position, level);
                tReportCourse.setCourseCode(tCourses.get(0).getCode());
                tReportCourseService.insertItem(tReportCourse);
                tReportCourse.setCourseCode(tCourses.get(1).getCode());
                tReportCourseService.insertItem(tReportCourse);
                //
                System.out.println("创建课程……");
            }
            report.append("建议您根据系统推荐的训练处方进行运动能力提升/运动风险改善训练。");

            TEvalReport tEvalReport = new TEvalReport();
            tEvalReport.setId(reportId);
            tEvalReport.setUserId(userId);
            tEvalReport.setCreateTime(new Date());
            tEvalReport.setScore(totalScore);
            if (gradeFlage)
                tEvalReport.setGrade("不合格（因单项不合格）");
            else
                tEvalReport.setGrade(getTotalGrade(totalScore));

            String preReport = "您的整体运动评估结果: " + tEvalReport.getGrade() + "。其中, 您的";
            tEvalReport.setReport(preReport + report);
            if (tEvalReportService.updateReport(tEvalReport) == 0)
                return CommonResult.fail("评估报告创建失败");
        }
        TEvalReportCustom tEvalReportCustom = new TEvalReportCustom();
        TEvalReport tEvalReport = tEvalReportService.selectByrReportId(reportId);
        BeanUtils.copyProperties(tEvalReport, tEvalReportCustom);

        List<TCourseMeta> tCourseMetaList = new ArrayList<>();
        List<TReportCourse> tReportCourses = tReportCourseService.selectListById(reportId);

        for (TReportCourse tReportCourse : tReportCourses) {
            TCourseMeta tCourseMeta = tCourseMetaService.selectByCode(tReportCourse.getCourseCode());
            tCourseMetaList.add(tCourseMeta);
        }
        tEvalReportCustom.setTCourseList(tCourseMetaList);
        tEvalReportCustom.setTActionEventList(tActionEventList);
        return CommonResult.success(tEvalReportCustom);
    }


    // 根据用户id获取用户运动能力评评估报告 返回user最新的评估报告
    @GetMapping("/getNextAction/{evalEventId}")
    public CommonResult getNextAction(@PathVariable("evalEventId") Long evalEventId) {
        // 根据评估事件id 获取未完成评估的动作
        TActionEvent tActionEvent = tActionEventService.selectNext(evalEventId);
        // 根据评估事件id 获取当前已完成的时间最新的
        TActionEvent currentActionEvent = tActionEventService.selectCurrent(evalEventId);

        if (tActionEvent == null)
            return CommonResult.fail("评估动作已完成");
        // 根据actionID 去查询设备指导
        TActionGuide tActionGuide = tActionGuideService.selectById(tActionEvent.getActionId());
        // 封装对象
        TActionGuideCustom tActionGuideCustom = new TActionGuideCustom();
        BeanUtils.copyProperties(tActionGuide, tActionGuideCustom);
        tActionGuideCustom.setTActionEvent(tActionEvent);
        tActionGuideCustom.setCurrentAction(currentActionEvent);
        return CommonResult.success(tActionGuideCustom);
    }

    // 根据course_Code获取课程详情
    @GetMapping("/getCourseDetail/{courseCode}")
    public CommonResult getCourseDetail(@PathVariable("courseCode") String courseCode){
        TCourseMeta tCourseMeta = tCourseMetaService.selectByCode(courseCode);
        if (tCourseMeta == null){
            return CommonResult.fail("课程不存在");
        }else {
            List<TDetail> tDetailList = tDetailService.selectByCode(courseCode);
            List<TActionCustom> tActionCustomList = new ArrayList<>();
            TCourseMetaCustom tCourseMetaCustom = new TCourseMetaCustom();
            BeanUtils.copyProperties(tCourseMeta, tCourseMetaCustom);

            for (int i = 0; i < tDetailList.size(); i++) {
                TActionCustom tActionCustomTmp = new TActionCustom();
                String actionCode = tDetailList.get(i).getActionCode();
                TAction tAction = tActionService.selectByCode(actionCode);
                BeanUtils.copyProperties(tAction, tActionCustomTmp);
                tActionCustomTmp.setCount(tDetailList.get(i).getCount());
                tActionCustomTmp.setTimes(tDetailList.get(i).getTimes());
                tActionCustomTmp.setGap(tDetailList.get(i).getGap());
                tActionCustomList.add(tActionCustomTmp);
            }
            tCourseMetaCustom.setTActionCustomList(tActionCustomList);
            return CommonResult.success(tCourseMetaCustom);
        }
    }

    // 根据course_Code获取课程详情
    @GetMapping("/getActionList/{isAI}")
    public CommonResult getAIActions(@PathVariable("isAI") Integer isAI){
        List<TAction> tActionList = tActionService.getAIList(isAI);
        if (tActionList == null){
            return CommonResult.fail("获取课程列表失败");
        }else {
            return CommonResult.success(tActionList);
        }
    }

    // 根据course_Code获取课程详情
    @GetMapping("/getCourseList/{isAI}")
    public CommonResult getAICourseList(@PathVariable("isAI") Integer isAI){
        List<TCourseMeta> tCourseMetaList = tCourseMetaService.getCourseList(isAI);
        if (tCourseMetaList == null){
            return CommonResult.fail("获取课程失败");
        }else {
            return CommonResult.success(tCourseMetaList);
        }
    }


    @GetMapping("/getTrainResult/{userId}/{code}")
    public CommonResult getAICourseList(@PathVariable("userId") Long userId, @PathVariable("code") Long code){
        TAction tAction = tActionService.selectByCode(code.toString());
        TTrainRecordCustom tTrainRecordCustom = new TTrainRecordCustom();
        TTrainRecord tTrainRecord = tTrainRecordService.selectByUserId(userId, code);
        BeanUtils.copyProperties(tTrainRecord, tTrainRecordCustom);
        tTrainRecordCustom.setActionImg(tAction.getActionImg());
        if (tTrainRecord == null){
            return CommonResult.fail("");
        }else {
            return CommonResult.success(tTrainRecordCustom);
        }
    }


}
