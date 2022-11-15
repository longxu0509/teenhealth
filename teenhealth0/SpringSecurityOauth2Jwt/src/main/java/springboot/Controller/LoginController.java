package springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import springboot.mybatis.po.CommonResult;
import springboot.service.Impl.MyUserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public void login(HttpServletRequest request){
        String username=request.getParameter("username");
        //String password=request.getParameter("password");
        myUserDetailsService.loadUserByUsername(username);
    }

    @RequestMapping("/loginError")
    @ResponseBody
    public CommonResult loginError(){
        return CommonResult.fail();
    }

    @RequestMapping("/loginSuccess")
    @ResponseBody
    public CommonResult loginSuccess(){
        return CommonResult.success();
    }
}
