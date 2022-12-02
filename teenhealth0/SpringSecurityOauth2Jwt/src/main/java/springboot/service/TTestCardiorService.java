package springboot.service;

import springboot.mybatis.po.TTestCardior;
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
public interface TTestCardiorService extends IService<TTestCardior> {

    TTestCardior getAdviceAndScore(Long count);

    List<TTestCardior> testList();

    int updateTestCardior(TTestCardior tTestCardior);
}
