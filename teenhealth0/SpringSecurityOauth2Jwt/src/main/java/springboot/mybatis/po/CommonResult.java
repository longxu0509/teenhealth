package springboot.mybatis.po;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static CommonResult success(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(200);
        commonResult.setMsg("操作成功");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult success(){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(200);
        commonResult.setMsg("操作成功");
        return commonResult;
    }

    public static CommonResult fail(){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(300);
        commonResult.setMsg("操作失败");
        return commonResult;
    }

    public static CommonResult fail(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(300);
        commonResult.setMsg("操作失败");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult fail1(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(311);
        commonResult.setMsg("已有记录");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail1(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(301);
        commonResult.setMsg("用户不存在");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail2(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(302);
        commonResult.setMsg("密码错误");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail3(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(303);
        commonResult.setMsg("密码过期");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail4(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(304);
        commonResult.setMsg("账号不可用");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail5(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(305);
        commonResult.setMsg("账号锁定");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail6(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(306);
        commonResult.setMsg("账号在其他设备登录，被迫下线");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult loginfail7(Object data){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(307);
        commonResult.setMsg("其他错误");
        commonResult.setData(data);
        return commonResult;
    }
}
