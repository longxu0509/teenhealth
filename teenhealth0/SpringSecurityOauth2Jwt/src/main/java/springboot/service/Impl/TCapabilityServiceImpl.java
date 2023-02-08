package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TCapability;
import springboot.mybatis.mapper.TCapabilityMapper;
import springboot.service.TCapabilityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Service
public class TCapabilityServiceImpl extends ServiceImpl<TCapabilityMapper, TCapability> implements TCapabilityService {

    @Autowired
    TCapabilityMapper tCapabilityMapper;

    @Override
    public TCapability findByUserId(Long userID) {
        return tCapabilityMapper.findByUserId(userID);
    }

    @Override
    public void addTestItem(TCapability tCapability) {
        tCapabilityMapper.addTestItem(tCapability);
    }

    @Override
    public void insertTestRecord(TCapability tCapability) {
        tCapabilityMapper.insertTestRecord(tCapability);
    }

    @Override
    public List<TCapability> getCapabilityList(Long userID) {
        return tCapabilityMapper.getCapabilityList(userID);
    }

    @Override
    public TCapability getCapabilityReport(Long userId) {
        return tCapabilityMapper.getCapabilityReport(userId);
    }
}
