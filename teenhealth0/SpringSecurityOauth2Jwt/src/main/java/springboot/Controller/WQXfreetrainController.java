package springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TWqxTrain;
import springboot.mybatis.po.TWqxTrainCustom;
import springboot.service.TWqxTrainService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teacher")
public class WQXfreetrainController {
    @Autowired
    private TWqxTrainService tWqxTrainService;

//    获取所有用户最新无器械自由训练列表
    @RequestMapping("/WQXfreetrainlist")
    public CommonResult WQXfreetrainlist(@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TWqxTrainCustom> list=tWqxTrainService.listWqxTrainStudent();
        PageInfo pageInfo1=new PageInfo(list);
        return CommonResult.success(pageInfo1);
    }

//    根据studentId获取用户所有训练记录
    @RequestMapping("/WQXStudentfreetrain/{studentId}")
    public CommonResult WQXstudentfreetrainl (@PathVariable("studentId") Long studentId, @RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TWqxTrainCustom> list=tWqxTrainService.listWQXsutdentfreetrain(studentId);
        PageInfo pageInfo1=new PageInfo(list);
        return CommonResult.success(pageInfo1);
    }

//    播放用户训练视频
    @GetMapping("/WQXStudenttrainfile/{wqxtrainId}")
    public CommonResult WQXStudenttrainfile(@PathVariable("wqxtrainId") Long wqxtrainId,HttpServletResponse response)throws IOException {
        TWqxTrain tWqxTrain=tWqxTrainService.selectWqxTrainbyId(wqxtrainId);
        if (null==tWqxTrain){
            return CommonResult.fail();
        }else {
            String filename =tWqxTrain.getTrainingPath();     //id查询文件名称
            System.out.println(filename);
            File file = new File(filename);
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
            }

        }
        return CommonResult.fail();
    }

}
