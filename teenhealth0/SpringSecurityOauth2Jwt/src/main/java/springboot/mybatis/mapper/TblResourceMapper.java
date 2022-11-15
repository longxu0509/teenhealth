package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TblResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-08
 */
@Mapper
public interface TblResourceMapper extends BaseMapper<TblResource> {

    //查询与role_id对应的resource集合
    List<TblResource> selectResourcebyRoleId(Long roleId);

    List<Long> selectresourceParentId();

    //获取所有roleId对应的类型type
    List<Integer> selectType(long roleId);

    ArrayList<TblResource> selectResourcebyType(Integer classes);

    //获取所有类型的classes
    List<Integer> selectClasses(Long roleId);
}
