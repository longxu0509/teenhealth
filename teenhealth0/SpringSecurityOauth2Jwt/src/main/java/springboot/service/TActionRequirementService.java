package springboot.service;

import springboot.mybatis.po.TActionRequirement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
public interface TActionRequirementService extends IService<TActionRequirement> {

    TActionRequirement getByCode(String code);
}
