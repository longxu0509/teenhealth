package springboot.Config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import springboot.mybatis.po.CommonResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        CommonResult commonResult=null;
        if (e instanceof InternalAuthenticationServiceException){
            commonResult=CommonResult.loginfail1("//用户不存在");
        }else if (e instanceof BadCredentialsException){
            commonResult=CommonResult.loginfail2("//密码错误");
        }else if (e instanceof CredentialsExpiredException){
            commonResult=CommonResult.loginfail3("//密码过期");
        }else if (e instanceof DisabledException){
            commonResult=CommonResult.loginfail4("//账号不可用");
        }else if (e instanceof LockedException){
            commonResult=CommonResult.loginfail5("//账号锁定");
        }else {
            commonResult=CommonResult.loginfail7("//其他错误");
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(commonResult));
    }
}
