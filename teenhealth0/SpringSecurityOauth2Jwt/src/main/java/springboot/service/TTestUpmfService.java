package springboot.service;

import springboot.mybatis.po.TTestUpmf;
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
public interface TTestUpmfService extends IService<TTestUpmf> {

    TTestUpmf getAdviceAndScore(Long count);

    List<TTestUpmf> testList();

    int updateTestUpmf(TTestUpmf tTestUpmf, long id);
}
