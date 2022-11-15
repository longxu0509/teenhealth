package springboot.mybatis.mapper;

import springboot.mybatis.po.TWqxplanPersonal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-14
 */
public interface TWqxplanPersonalMapper extends BaseMapper<TWqxplanPersonal> {
    //查询XXX学生处方内容
    List<TWqxplanPersonal> PersonalPlan(Long planId);

    //新增XXX同学无器械处方训练内容
    Integer insertPlanContent(List<TWqxplanPersonal> list);

    //修改训练处方内容
    Integer updatePlanContent(TWqxplanPersonal tWqxplanPersonal);

    //删除训练处方内容
    int deletePlanContent(long id);
}
