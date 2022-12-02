package springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TTrainingVideo;
import springboot.service.TTrainingVideoService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/teacher")
public class TTrainingVideoController {
    @Autowired
    private TTrainingVideoService tTrainingVideoService;

//    private String localpath = ("D:\\Works\\WQXtest\\JYWQXtest\\");//本地视频地址
    String localpath =("/media/AITraining/uploads/");     //阿里云视频存放地址

    //分页查询显示训练视频列表
    @RequestMapping("/page4VideoList")
    public CommonResult page4list(@RequestBody PageInfo pageInfo) throws Exception{
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TTrainingVideo> trainingVideoList=tTrainingVideoService.findTrainingVideo();
        PageInfo pageInfo1=new PageInfo(trainingVideoList);
        return CommonResult.success(pageInfo1);
    }

    @GetMapping("/videoList")
    public CommonResult videolist() throws Exception{
        List<TTrainingVideo> trainingVideoList=tTrainingVideoService.findTrainingVideo();
        return CommonResult.success(trainingVideoList);
    }


    //删除训练视频
    @RequestMapping(value = "/deleteVideo/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteVideo(@PathVariable("id") Integer trainingVideo_id)throws Exception{
        TTrainingVideo trainingVideo=tTrainingVideoService.findFileName(trainingVideo_id);
        String fileName=trainingVideo.getFileName();
        File file=new File(localpath,fileName);
        if(!file.exists()){
            tTrainingVideoService.deleteVideo(trainingVideo_id);
            return CommonResult.success("文件不存在!");
        }else {
            tTrainingVideoService.deleteVideo(trainingVideo_id);
            file.delete();
            return CommonResult.success("删除成功！");
        }
    }

    //查询单个训练视频信息
    @GetMapping(value = "/selectVideo/{id}")
    public CommonResult selectTraninVideo(@PathVariable("id") Integer id){  //@PathVariable("id")：从上面路径中找到id
        TTrainingVideo trainingVideo=tTrainingVideoService.selectTraninVideo(id);
        return CommonResult.success(trainingVideo);
    }

    //修改训练视频信息
    @PostMapping(value = "/updateVideo")
    public CommonResult updateVideo(@RequestBody TTrainingVideo trainingVideo){
        if (tTrainingVideoService.updateTrainingVideo(trainingVideo)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //上传视频
    @PostMapping(value = "/fileupload")
    public CommonResult fileupload2(@RequestParam(value = "file",required = false) MultipartFile multipartFile, TTrainingVideo trainingVideo) throws IOException{
        if (multipartFile.isEmpty()){
            return CommonResult.fail("文件为空");
        }else {
            //上传位置
            //获取上传文件的名称
            String filename= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String suffixName=filename.substring(filename.lastIndexOf("."));
            if (suffixName.equals(".mp4")||suffixName.equals(".rmvb")||suffixName.equals(".avi")){
                //使用uuid设置文件名称为唯一值
                String uuid= UUID.randomUUID().toString().replace("-","");
                filename=uuid+"-"+filename;
//                    File dest =new File(new File(path+filename).getAbsolutePath());
                File dest =new File(new File(localpath+filename).getAbsolutePath());
                if (!dest.getParentFile().exists()){    //使用.getParentFile(取文件前面目录的路径
                    dest.getParentFile().mkdirs();  // 检测是否存在目录
                }
                //完成文件上传
                try {
                    multipartFile.transferTo(dest);
                }catch (Exception e){
                    return CommonResult.fail("上传失败");
                }
                //文件信息写入数据库
                Date date = new Date();
                trainingVideo.setCreateTime(date);
                trainingVideo.setFileName(filename);
                trainingVideo.setSavePath(localpath);
                System.out.println(trainingVideo);
                tTrainingVideoService.insertTrainingVideo(trainingVideo);
                return CommonResult.success();
            }else {
                return CommonResult.fail("文件格式错误");
            }
        }
    }

    //文件下载
    @RequestMapping(value = "/filedownload/{videoId}")
    @ResponseBody
    public CommonResult filetdownload(@PathVariable("videoId") Integer videoId, HttpServletResponse response)
            throws IOException {
        TTrainingVideo trainingVideo=tTrainingVideoService.findFileName(videoId);
        if (null==trainingVideo){
            return CommonResult.fail();
        }else {
            String filename =trainingVideo.getFileName();     //id查询文件名称
            System.out.println(filename);
            File file = new File(localpath + File.separator + filename);
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

    //批量删除视频
    @RequestMapping("/deleteVideos")
    @ResponseBody
    public CommonResult deleteVideos(String ids) {
        try {
            String[] idss=ids.split(",");
//            tTrainingVideoService.deleteVideos(idss);
        }catch (Exception e){
            return CommonResult.fail();
        }
        return CommonResult.success();
    }

    //    根据视频名称检索训练视频
    @RequestMapping("/findTranVideos")
    @ResponseBody
    public CommonResult findTranVideos(@RequestBody Map map){
        Integer pageSize=(Integer)map.get("pageSize");
        Integer pageNum=(Integer)map.get("pageNum");
        PageHelper.startPage(pageNum,pageSize);
        String sort=(String) map.get("sort");
        List<TTrainingVideo> trainingVideoList=tTrainingVideoService.findVideoby(sort);
        PageInfo pageInfo = new PageInfo(trainingVideoList);
        return CommonResult.success(pageInfo);
    }

}

