package springboot.service.Impl;

import springboot.mybatis.mapper.*;
import springboot.mybatis.po.TblResource;
import springboot.mybatis.po.TblRole;
import springboot.mybatis.po.TblUser;
import springboot.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private TblRoleMapper tblRoleMapper;
    @Autowired
    private TblUserMapper tblUserMapper;
    @Autowired
    private TblUserRoleMapper tblUserRoleMapper;
    @Autowired
    private TblRoleResourceMapper tblRoleResourceMapper;
    @Autowired
    private TblResourceMapper tblResourceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUser tblUser = tblUserMapper.selectUserByloginName(username);
        TblRole tblRole = tblRoleMapper.selectRoleName(username);
        //tblUser.setPassword(passwordEncoder.encode(tblUser.getPassword()));
        try {
            if (tblUser == null) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+tblRole.getRoleName()));
            return new User(tblUser.getLoginName(), tblUser.getPassword(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
            //认证失败
            return null;
        }
    }

    @Override
    //根据角色名称查询角色id
    public Long selectRoleIdbyRoleName(String roleName){
        return tblRoleMapper.selectRoleIdbyRoleName(roleName);
    }

    //查询与role_id对应的resource_id
    @Override
    public List<Long> selectResourceIdbyRoleId(Long roleId){
        return tblRoleResourceMapper.selectResourceIdbyRoleId(roleId);
    }

    //查询与role_id对应的resource集合
    @Override
    public List<TblResource> selectResourcebyRoleId(Long roleId){
        return tblResourceMapper.selectResourcebyRoleId(roleId);
    }

    @Override
    public List<Long> selectresourceParentId(){
        return tblResourceMapper.selectresourceParentId();
    }

    //获取所有类型的type
    @Override
    public List<Integer> selectType(long roleId){
        return tblResourceMapper.selectType(roleId);
    }

    //根据type查询resource
    @Override
    public ArrayList<TblResource> selectResourcebyClasses(Integer classes){
        return tblResourceMapper.selectResourcebyType(classes);
    }

    //获取所有类型的classes
    @Override
    public List<Integer> selectClasses(Long roleId){
        return tblResourceMapper.selectClasses(roleId);
    }

}
