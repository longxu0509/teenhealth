package springboot.service;

import springboot.mybatis.po.TTestStandard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
public interface TTestStandardService extends IService<TTestStandard> {

    int getTestScore(Long testId, int gender, int age_group_id, Long count);
}
