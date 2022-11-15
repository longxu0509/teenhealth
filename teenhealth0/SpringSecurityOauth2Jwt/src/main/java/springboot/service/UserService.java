package springboot.service;

import io.swagger.models.auth.In;
import springboot.mybatis.po.TblUser;

import java.util.List;


public interface UserService {
    //根据管理员用户姓名查询用户信息
    public TblUser selectUserByloginName(String loginName);

    //查看所有管理员列表
    List<TblUser> selectAllUser();

    //修改管理员用户信息
    Integer updateUserInfo(TblUser tblUser);

    //添加管理员用户
    Integer addUser(TblUser tblUser);

    //添加管理员身份
    Integer addUserRole(Integer id);

    //删除管理员用户
    Integer deleteUser(Integer userId);
}
