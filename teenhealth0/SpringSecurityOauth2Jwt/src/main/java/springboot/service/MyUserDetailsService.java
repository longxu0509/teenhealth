package springboot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springboot.mybatis.po.TblResource;

import java.util.ArrayList;
import java.util.List;


public interface MyUserDetailsService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    //根据角色名称查询角色id
    Long selectRoleIdbyRoleName(String roleName);

    //查询与role_id对应的resource_id
    List<Long> selectResourceIdbyRoleId(Long roleId);

    //查询与role_id对应的resource集合
    List<TblResource> selectResourcebyRoleId(Long roleId);

    List<Long> selectresourceParentId();

    //获取所有类型的type
    List<Integer> selectType(long roleId);

    //根据type查询resource
    ArrayList<TblResource> selectResourcebyClasses(Integer classes);

    //获取所有类型的classes
    List<Integer> selectClasses(Long roleId);
}
