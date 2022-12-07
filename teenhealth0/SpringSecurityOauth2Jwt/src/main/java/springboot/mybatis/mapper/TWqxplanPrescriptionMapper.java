package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TWqxplanPrescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
@Mapper
public interface TWqxplanPrescriptionMapper extends BaseMapper<TWqxplanPrescription> {

    List<TWqxplanPrescription> PlanDetail(Long planId);

    int updatePlanContent(TWqxplanPrescription tWqxplanPrescription);

    int deletePlanContent(long id);

    int insertPlanContent(TWqxplanPrescription tWqxplanPrescription);

    List<TWqxplanPrescription> findPlanContByIndexNO(Long npId, Long indexNO);

    int updateIndexNO(Long npId, Long indexNO);

    TWqxplanPrescription findPlanContentById(long id);

    int subIndexNO(Long npId, Long indexNO);

    int insertPlanContentList(List<TWqxplanPrescription> list);

    TWqxplanPrescription findPlanContentBySort(String sort);

    int deleteByNpID(String id);
}
