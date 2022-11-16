package springboot.service;

import springboot.mybatis.po.TWQXPlanListCustom;
import springboot.mybatis.po.TWqxplanNp;
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
public interface TWqxplanNpService extends IService<TWqxplanNp> {
    //查询所无器械处方
    List<TWqxplanNp> ListTWQXPlan();

    //根据id删除处方
    boolean deleteWQXplan(Long planId);

    //新增无器械处方
    int insertWQXplan(TWqxplanNp tWqxplanNp);

    // 修改处方
    int updatePlan(TWqxplanNp tWqxplanNp);
}
