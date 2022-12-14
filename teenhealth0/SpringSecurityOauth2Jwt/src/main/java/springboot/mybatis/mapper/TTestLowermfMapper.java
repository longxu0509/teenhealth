package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestLowermf;
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
public interface TTestLowermfMapper extends BaseMapper<TTestLowermf> {

    TTestLowermf getAdviceAndScore(Long count);

    List<TTestLowermf> testList();

    int updateTestLowermf(TTestLowermf tTestLowermf);

    TTestLowermf getTestById(long id);
}
