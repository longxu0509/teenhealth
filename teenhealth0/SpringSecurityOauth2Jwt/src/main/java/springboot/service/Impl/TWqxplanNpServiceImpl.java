package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWqxplanNp;
import springboot.mybatis.mapper.TWqxplanNpMapper;
import springboot.service.TWqxplanNpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
@Service
public class TWqxplanNpServiceImpl extends ServiceImpl<TWqxplanNpMapper, TWqxplanNp> implements TWqxplanNpService {
    @Autowired
    private TWqxplanNpMapper tWqxplanNpMapper;

    @Override
    public List<TWqxplanNp> ListTWQXPlan() {
        return tWqxplanNpMapper.listWqxTrainNp();
    }

    @Override
    public boolean deleteWQXplan(Long planId) {
        return tWqxplanNpMapper.deleteWQXplan(planId);
    }

    @Override
    public int insertWQXplan(TWqxplanNp tWqxplanNp) {
        return tWqxplanNpMapper.insertWQXplan(tWqxplanNp);
    }

    @Override
    public int updatePlan(TWqxplanNp tWqxplanNp) {
        return tWqxplanNpMapper.updatePlanByPK(tWqxplanNp);
    }
}
