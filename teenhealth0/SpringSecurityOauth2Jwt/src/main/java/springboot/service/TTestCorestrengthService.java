package springboot.service;

import springboot.mybatis.po.TTestCorestrength;
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
public interface TTestCorestrengthService extends IService<TTestCorestrength> {

    TTestCorestrength getAdviceAndScore(Long count);

    List<TTestCorestrength> testList();

    int updateTestCorestrength(TTestCorestrength tTestCorestrength, long id);
}
