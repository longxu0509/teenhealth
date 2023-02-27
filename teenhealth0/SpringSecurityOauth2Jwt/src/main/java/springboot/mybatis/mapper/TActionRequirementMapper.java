package springboot.mybatis.mapper;

import springboot.mybatis.po.TActionRequirement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
public interface TActionRequirementMapper extends BaseMapper<TActionRequirement> {

    TActionRequirement getByCode(String code);
}
