package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestCardior;
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
public interface TTestCardiorMapper extends BaseMapper<TTestCardior> {

    TTestCardior getAdviceAndScore(Long count);

    List<TTestCardior> testList();

    int updateTestCriteria(TTestCardior tTestCardior);
}
