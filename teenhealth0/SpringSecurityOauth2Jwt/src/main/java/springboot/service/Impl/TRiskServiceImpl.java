package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TRisk;
import springboot.mybatis.mapper.TRiskMapper;
import springboot.service.TRiskService;
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
public class TRiskServiceImpl extends ServiceImpl<TRiskMapper, TRisk> implements TRiskService {

    @Autowired
    private TRiskMapper tRiskMapper;

    @Override
    public TRisk findByUserId(Long userID) {
        return tRiskMapper.findByUserId(userID);
    }

    @Override
    public void addTestItem(TRisk tRisk) {
        tRiskMapper.addTestItem(tRisk);
    }

    @Override
    public void insertTestRecord(TRisk tRisk) {
        tRiskMapper.insertTestRecord(tRisk);
    }

    @Override
    public List<TRisk> getRiskList(Long userID) {
        return tRiskMapper.getRiskList(userID);
    }
}
