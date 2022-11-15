package springboot.Controller.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TStudent;
import springboot.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("/tph/service")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //终端设备学生学号登录
    @RequestMapping(value = {"/student/student_login"},produces="application/json;charset=UTF-8")
    public CommonResult studentLogin(@RequestBody Map<String, String> requestMap)throws Exception{
        String studentNo=requestMap.get("studentNo");
        TStudent tStudent=studentService.selectByStudentNo(studentNo);
        if(tStudent==null){
            return CommonResult.fail();
        }else {
            return CommonResult.success(tStudent);
        }
    }
}
