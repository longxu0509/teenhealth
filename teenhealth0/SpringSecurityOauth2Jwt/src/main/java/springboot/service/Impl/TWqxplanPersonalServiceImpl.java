package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.mapper.TWuqixiePlanMapper;
import springboot.mybatis.po.TWQXPlanListCustom;
import springboot.mybatis.po.TWqxplanPersonal;
import springboot.mybatis.mapper.TWqxplanPersonalMapper;
import springboot.service.TWqxplanPersonalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-14
 */
@Service
public class TWqxplanPersonalServiceImpl extends ServiceImpl<TWqxplanPersonalMapper, TWqxplanPersonal> implements TWqxplanPersonalService {

    @Autowired
    private TWqxplanPersonalMapper tWqxplanPersonalMapper;

    @Override
    //查询XXX学生处方内容
    public List<TWqxplanPersonal> PersonalPlan(Long planId){
        return tWqxplanPersonalMapper.PersonalPlan(planId);
    }

    @Override
    //新增XXX同学无器械处方训练内容
    public Integer insertPlanContent(List<TWqxplanPersonal> list){
        return tWqxplanPersonalMapper.insertPlanContent(list);
    }

    @Override
    //修改训练处方内容
    public Integer updatePlanContent(TWqxplanPersonal tWqxplanPersonal){
        return tWqxplanPersonalMapper.updatePlanContent(tWqxplanPersonal);
    }

    @Override
    //删除训练处方内容
    public Integer deletePlanContent(long id){
        return tWqxplanPersonalMapper.deletePlanContent(id);
    }
}
