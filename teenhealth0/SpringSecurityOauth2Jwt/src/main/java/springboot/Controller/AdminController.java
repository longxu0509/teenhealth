package springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TblUser;
import springboot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    //查看所有管理员列表
    @RequestMapping("/selectAllUser")
    public CommonResult selectAllUser(@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TblUser> list=userService.selectAllUser();
        PageInfo pageInfo1=new PageInfo(list);
        return CommonResult.success(pageInfo1);
    }

    //修改管理员用户信息
    @RequestMapping("/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody TblUser tblUser){
        String password=tblUser.getPassword();
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //加密
        String newPassword = passwordEncoder.encode(password);
        tblUser.setPassword(newPassword);
        if (userService.updateUserInfo(tblUser)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //添加管理员用户
    @RequestMapping("/addUser")
    public CommonResult addUser(@RequestBody TblUser tblUser){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //加密
        String newPassword = passwordEncoder.encode(tblUser.getPassword());
        tblUser.setPassword(newPassword);
        if (userService.addUser(tblUser)==1){
            tblUser=userService.selectUserByloginName(tblUser.getLoginName());
            if (userService.addUserRole(tblUser.getId())==1){
                return CommonResult.success();
            }else {
                return CommonResult.fail();
            }
        }else {
            return CommonResult.fail();
        }
    }

    //删除管理员用户
    @RequestMapping("/deleteUser/{userId}")
    public CommonResult deleteUser(@PathVariable("userId") Integer userId){
        if (userService.deleteUser(userId)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //校验用户名是否重复
    @RequestMapping("/LoginName/{loginname}")
    public CommonResult verifyLoginName(@PathVariable("loginname") String loginname){
        TblUser tblUser=userService.selectUserByloginName(loginname);
        if (tblUser==null){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }


}
