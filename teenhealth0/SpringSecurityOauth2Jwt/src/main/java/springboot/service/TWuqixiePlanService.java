package springboot.service;

import springboot.mybatis.po.TWQXPlanListCustom;
import springboot.mybatis.po.TWqxplanPersonal;
import springboot.mybatis.po.TWuqixiePlan;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TWuqixiePlanService {

    //查询所有学生无器械处方
    List<TWQXPlanListCustom> ListTWQXPlan();

    //查询XXX学生历史处方
    public List<TWQXPlanListCustom> OneListTWQXPlan(Long studentid);

    //新增无器械处方
    Integer insertWQXplan(TWuqixiePlan tWuqixiePlan);

    //根据student_id查询用户所有无器械处方
    List<TWuqixiePlan> selectWQXPlan(Long studentId);

    //根据id查询无器械训练处方
    TWuqixiePlan selectWQXplanbyid(Long planId);

    //根据student_id查询用户最新处方
    TWuqixiePlan selectWQXPlanbyStudentId1(Long studentId);

    //根据id删除处方
    boolean deleteWQXplan(Long planId);
}
