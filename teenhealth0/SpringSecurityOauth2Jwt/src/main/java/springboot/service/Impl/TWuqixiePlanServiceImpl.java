package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWQXPlanListCustom;
import springboot.mybatis.po.TWuqixiePlan;
import springboot.mybatis.mapper.TWuqixiePlanMapper;
import springboot.service.TWuqixiePlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
@Service
public class TWuqixiePlanServiceImpl extends ServiceImpl<TWuqixiePlanMapper, TWuqixiePlan> implements TWuqixiePlanService {
    @Autowired
    private TWuqixiePlanMapper tWuqixiePlanMapper;

    @Override
    //查询所有学生无器械处方
    public List<TWQXPlanListCustom> ListTWQXPlan(){
        return tWuqixiePlanMapper.ListTWQXPlan();
    }

    @Override
    //查询XXX学生无器械历史处方
    public List<TWQXPlanListCustom> OneListTWQXPlan(Long studentid){
        return tWuqixiePlanMapper.OneListTWQXPlan(studentid);
    }

    @Override
    //新增无器械处方
    public Integer insertWQXplan(TWuqixiePlan tWuqixiePlan){
        return tWuqixiePlanMapper.insertWQXplan(tWuqixiePlan);
    }

    @Override
    //根据student_id查询用户所有无器械处方
    public List<TWuqixiePlan> selectWQXPlan(Long studentId){
        return tWuqixiePlanMapper.selectWQXPlan(studentId);
    }

    @Override
    //根据id查询无器械训练处方
    public TWuqixiePlan selectWQXplanbyid(Long planId){
        return tWuqixiePlanMapper.selectByplanId(planId);
    }

    @Override
    //根据student_id查询用户最新处方
    public TWuqixiePlan selectWQXPlanbyStudentId1(Long studentId){
        return tWuqixiePlanMapper.selectWQXPlanbyStudentId1(studentId);
    }

    @Override
    //根据id删除处方
    public boolean deleteWQXplan(Long planId){
        return tWuqixiePlanMapper.deleteWQXplan(planId);
    }
}
