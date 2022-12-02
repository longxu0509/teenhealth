package springboot.service;

import springboot.mybatis.po.TTestCoordinate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
public interface TTestCoordinateService extends IService<TTestCoordinate> {

    TTestCoordinate getAdviceAndScore(Long count);

    List<TTestCoordinate> testList();
}
