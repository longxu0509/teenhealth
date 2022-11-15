package springboot.service.Impl;

import springboot.mybatis.mapper.TblUserMapper;
import springboot.mybatis.mapper.TblUserRoleMapper;
import springboot.mybatis.po.TblUser;
import springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private TblUserMapper tblUserMapper;
    @Autowired
    private TblUserRoleMapper tblUserRoleMapper;

    @Override
    public TblUser selectUserByloginName(String loginName){
        return tblUserMapper.selectUserByloginName(loginName);
    }

    //查看所有管理员列表
    @Override
    public List<TblUser> selectAllUser(){
        return tblUserMapper.selectAllUser();
    }

    //修改管理员用户信息
    @Override
    public Integer updateUserInfo(TblUser tblUser){
        return tblUserMapper.updateUserInfo(tblUser);
    }

    //添加管理员用户
    @Override
    public Integer addUser(TblUser tblUser){
        return tblUserMapper.addUser(tblUser);
    }

    //添加管理员身份
    @Override
    public Integer addUserRole(Integer id){
        return tblUserRoleMapper.addUserRole(id);
    }

    //删除管理员用户
    @Override
    public Integer deleteUser(Integer userId){
        return tblUserMapper.deleteUserbyId(userId);
    }

}
