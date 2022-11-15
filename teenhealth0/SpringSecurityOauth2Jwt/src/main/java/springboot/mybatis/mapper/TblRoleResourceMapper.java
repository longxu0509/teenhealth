package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TblRoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-08
 */
@Mapper
public interface TblRoleResourceMapper extends BaseMapper<TblRoleResource> {

    //查询与role_id对应的resource_id
    List<Long> selectResourceIdbyRoleId(Long roleId);
}
