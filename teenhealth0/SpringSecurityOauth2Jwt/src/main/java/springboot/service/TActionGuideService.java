package springboot.service;

import springboot.mybatis.po.TActionGuide;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
public interface TActionGuideService extends IService<TActionGuide> {

    TActionGuide selectById(Integer actionId);
}
