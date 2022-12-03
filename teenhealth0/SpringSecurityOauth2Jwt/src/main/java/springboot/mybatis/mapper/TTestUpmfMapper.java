package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestUpmf;
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
public interface TTestUpmfMapper extends BaseMapper<TTestUpmf> {

    TTestUpmf getAdviceAndScore(Long count);

    List<TTestUpmf> testList();

    int updateTestUpmf(TTestUpmf tTestUpmf);

    TTestUpmf getTestById(long id);
}
