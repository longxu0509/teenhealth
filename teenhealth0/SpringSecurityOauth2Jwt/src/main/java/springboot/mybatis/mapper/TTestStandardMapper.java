package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestStandard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Mapper
public interface TTestStandardMapper extends BaseMapper<TTestStandard> {

    int getTestScore(Long testId, int gender, int age_group_id, Long count);
}
