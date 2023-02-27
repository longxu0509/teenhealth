package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TActionGuide;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Mapper
public interface TActionGuideMapper extends BaseMapper<TActionGuide> {

    TActionGuide selectNext(Integer actionId);
}
