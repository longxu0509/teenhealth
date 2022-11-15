package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 设备表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Mapper
public interface TEquipmentMapper extends BaseMapper<TEquipment> {

}
