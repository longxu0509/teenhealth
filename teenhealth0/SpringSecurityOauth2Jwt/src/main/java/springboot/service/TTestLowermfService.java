package springboot.service;

import springboot.mybatis.po.TTestLowermf;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
public interface TTestLowermfService extends IService<TTestLowermf> {

    TTestLowermf getAdviceAndScore(Long count);
}
