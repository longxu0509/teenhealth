package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestCoordinate;
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
public interface TTestCoordinateMapper extends BaseMapper<TTestCoordinate> {

    TTestCoordinate getAdviceAndScore(Long count);

    List<TTestCoordinate> testList();

    int updateTestCoordinate(TTestCoordinate tTestCoordinate);

    TTestCoordinate getTestById(long id);
}
