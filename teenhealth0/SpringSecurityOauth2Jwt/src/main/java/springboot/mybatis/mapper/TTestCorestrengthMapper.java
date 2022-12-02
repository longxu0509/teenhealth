package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestCorestrength;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Mapper
public interface TTestCorestrengthMapper extends BaseMapper<TTestCorestrength> {

    TTestCorestrength getAdviceAndScore(Long count);

    List<TTestCorestrength> testList();

    int updateTestCorestrength(TTestCorestrength tTestCorestrength);
}
