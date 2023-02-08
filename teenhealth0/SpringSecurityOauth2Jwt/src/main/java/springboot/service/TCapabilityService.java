package springboot.service;

import springboot.mybatis.po.TCapability;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
public interface TCapabilityService extends IService<TCapability> {

    TCapability findByUserId(Long userID);

    void addTestItem(TCapability tCapability);

    void insertTestRecord(TCapability tCapability);

    List<TCapability> getCapabilityList(Long userID);

    TCapability getCapabilityReport(Long userId);
}
