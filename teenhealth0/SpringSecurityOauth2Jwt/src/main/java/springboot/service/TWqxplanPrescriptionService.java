package springboot.service;

import springboot.mybatis.po.TWqxplanPersonal;
import springboot.mybatis.po.TWqxplanPrescription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
public interface TWqxplanPrescriptionService extends IService<TWqxplanPrescription> {

    List<TWqxplanPrescription> PlanDetail(Long planId);

    int updatePlanContent(TWqxplanPrescription tWqxplanPrescription);

    int deletePlanContent(long id);

    int insertPlanContent(TWqxplanPrescription tWqxplanPrescription);

    List<TWqxplanPrescription> findPlanContByIndexNO(Long npId, Long indexNO);

    int updateIndexNO(Long npId, Long indexNO);

    TWqxplanPrescription findPlanContentById(long id);

    int subIndexNO(Long npId, Long indexNO);

}
