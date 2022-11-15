package springboot.service;

import springboot.mybatis.po.TWqxplanPersonal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-14
 */
public interface TWqxplanPersonalService extends IService<TWqxplanPersonal> {
    //查询XXX学生处方内容
    List<TWqxplanPersonal> PersonalPlan(Long planId);

    //批量插入XXX同学无器械处方训练内容
    Integer insertPlanContent(List<TWqxplanPersonal> list);

    //修改训练处方内容
    Integer updatePlanContent(TWqxplanPersonal tWqxplanPersonal);

    //删除训练处方内容
    Integer deletePlanContent(long id);
}
