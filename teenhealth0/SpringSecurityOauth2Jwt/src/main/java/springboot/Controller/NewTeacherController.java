package springboot.Controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import springboot.mybatis.po.*;
import springboot.service.*;
import springboot.utils.RedisUtil;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/teacher")
public class NewTeacherController {
    @Autowired
    private StudentService studentService;
    @Autowired
    RedisUtil redisUtil;

    // 显示学生列表
    @RequestMapping("/studentList1")
    @ResponseBody
    public CommonResult StudentInfoList(@RequestBody PageInfo pageInfo)throws Exception{
        return CommonResult.success(studentService.listStudent(pageInfo));
    }

    //根据姓名或学号模糊查询学生信息
    @RequestMapping("/selectByNameStudentNo1")
    @ResponseBody
    public CommonResult SelectByNameStudentNo(@RequestBody Map map) throws Exception{
        String NameStudentNo=(String) map.get("NameStudentNo");
        Integer pageNum=(Integer) map.get("pageNum");
        Integer pageSize=(Integer) map.get("pageSize");
        PageHelper.startPage(pageNum,pageSize);
        List<TStudent> studentList=studentService.selectByNameStudentNo(NameStudentNo);
        PageInfo pageInfo=new PageInfo(studentList);
        return CommonResult.success(pageInfo);
    }

    // 根据ID查询用户
    @RequestMapping("/getStudentByNo/{studentNo}")
    @ResponseBody
    public CommonResult getStudentByNo(@PathVariable("studentNo")String studentNo) throws Exception{
        TStudent student =studentService.getStudentByNo(studentNo);
        if (student != null) {
            return CommonResult.success(student);
        }else {
            return CommonResult.fail();
        }
    }

    //添加并保存学生信息
    @PostMapping("/addstudent1")
    @ResponseBody
    public CommonResult addStudent(@RequestBody TStudent tStudent)throws Exception{
        double bmi = tStudent.getWeight()/ (tStudent.getStature() * tStudent.getStature());
        tStudent.setBmi(String.valueOf((int)(bmi*10000)));
        Date date = new Date();
        tStudent.setCreateTime(date);
        if (studentService.insertStudent(tStudent)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //修改学生信息
    @PostMapping("/editstudent1/{id}")
    @ResponseBody
    public CommonResult editStudent(@RequestBody TStudent tStudent,@PathVariable("id")Long id)throws Exception{
        if(studentService.updateStudent(id, tStudent)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //删除学生信息
    @GetMapping("/deletestudent1/{id}")
    @ResponseBody
    public CommonResult deleteStudent(@PathVariable("id") Long id)throws Exception{
        if (studentService.deleteStudent(id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //查询学生帐号是否重复
    @GetMapping("/checkstudentNo1/{studentNo}")
    @ResponseBody
    public CommonResult checkStudentNo(@PathVariable("studentNo") String studentNo)throws Exception{
        System.out.println(studentNo);
        TStudent tStudent=studentService.selectByStudentNo(studentNo);
        if(tStudent==null){
            return CommonResult.success();
        }else {
            return CommonResult.fail1(tStudent);
        }
    }

}
