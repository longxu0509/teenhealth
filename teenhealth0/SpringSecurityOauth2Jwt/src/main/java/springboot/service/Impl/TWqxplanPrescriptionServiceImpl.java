package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWqxplanPrescription;
import springboot.mybatis.mapper.TWqxplanPrescriptionMapper;
import springboot.service.TWqxplanPrescriptionService;
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
public class TWqxplanPrescriptionServiceImpl extends ServiceImpl<TWqxplanPrescriptionMapper, TWqxplanPrescription> implements TWqxplanPrescriptionService {

    @Autowired
    private TWqxplanPrescriptionMapper tWqxplanPrescriptionMapper;

    @Override
    public List<TWqxplanPrescription> PlanDetail(Long planId) {
        return tWqxplanPrescriptionMapper.PlanDetail(planId);
    }

    @Override
    public int updatePlanContent(TWqxplanPrescription tWqxplanPrescription) {
        return tWqxplanPrescriptionMapper.updatePlanContent(tWqxplanPrescription);
    }

    @Override
    public int deletePlanContent(long id) {
        return tWqxplanPrescriptionMapper.deletePlanContent(id);
    }

    @Override
    public int insertPlanContent(TWqxplanPrescription tWqxplanPrescription) {
        return tWqxplanPrescriptionMapper.insertPlanContent(tWqxplanPrescription);
    }
}
