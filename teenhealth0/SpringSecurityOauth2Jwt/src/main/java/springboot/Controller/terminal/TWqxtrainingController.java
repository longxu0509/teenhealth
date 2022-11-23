package springboot.Controller.terminal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.*;
import springboot.service.*;

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
    private TWuqixiePlanService tWuqixiePlanService;
    @Autowired
    private TWqxplanPersonalService tWqxplanPersonalService;
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


    private String userUploadpath="/media/userUploads/";    //阿里云上传用户训练文件地址
    private String moxingpath="/usr/java/xunlianmoxing/";   //阿里云算法模型文件地址
//    private String uploadpath = "D:\\Works\\WQXtest\\uploadpath\\";    //本地地址教学视频地址
//    private String userUploadpath = "D:\\Works\\WQXtest\\JYWQXtest\\";    //本地地址上传用户训练文件地址
//    private String moxingpath="D:\\Works\\WQXtest\\xunlianmoxing\\";   //阿里云算法模型文件地址

    //客户端获取视频列表
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
                TTestCorestrength tTestCorestrength = tTestCorestrengthService.getAdviceAndScore(count);
                studentPftest.setCorestrengthLevel(tTestCorestrength.getLevel());
                studentPftest.setCorestrengthScore(tTestCorestrength.getScore());
                o = tTestCorestrength;
            } break;
            case 3: {
                TTestCoordinate tTestCoordinate = tTestCoordinateService.getAdviceAndScore(count);
                studentPftest.setCoordinateLevel(tTestCoordinate.getLevel());
                studentPftest.setCoordinateScore(tTestCoordinate.getScore());
                o = tTestCoordinate;
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
}
