package springboot.mybatis.mapper;

import springboot.mybatis.po.TWQXPlanListCustom;
import springboot.mybatis.po.TWuqixiePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TWuqixiePlanMapper extends BaseMapper<TWuqixiePlan> {

    //查询所有学生无器械处方
    List<TWQXPlanListCustom> ListTWQXPlan();

    //查询XXX学生无器械处方
    public List<TWQXPlanListCustom> OneListTWQXPlan(Long studentid);

    //新增无器械处方
    Integer insertWQXplan(TWuqixiePlan tWuqixiePlan);

    //根据studentId查询用户所有处方
    List<TWuqixiePlan> selectWQXPlan(Long studentId);

    //根据id查询无器械训练处方
    TWuqixiePlan selectByplanId(Long planId);

    //根据student_id查询用户最新处方
    TWuqixiePlan selectWQXPlanbyStudentId1(Long studentId);

    //根据id删除处方
    boolean deleteWQXplan(Long planId);
}
