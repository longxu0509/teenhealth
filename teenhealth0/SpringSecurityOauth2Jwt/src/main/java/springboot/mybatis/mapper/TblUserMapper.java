package springboot.mybatis.mapper;

import springboot.mybatis.po.TblUser;
import springboot.mybatis.po.TblUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblUserMapper {

    TblUser selectUserByloginName(String loginName);

    List<TblUser> selectAllUser();

    Integer updateUserInfo(TblUser tblUser);

    Integer addUser(TblUser tblUser);

    Integer deleteUserbyId(Integer userId);
}