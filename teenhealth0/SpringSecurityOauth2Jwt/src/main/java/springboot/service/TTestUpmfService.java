package springboot.service;

import springboot.mybatis.po.TTestUpmf;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
public interface TTestUpmfService extends IService<TTestUpmf> {

    TTestUpmf getAdviceAndScore(Long count);
}
