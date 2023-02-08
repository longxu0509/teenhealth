package springboot.service;

import springboot.mybatis.po.TExamine;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-04
 */
public interface TExamineService extends IService<TExamine> {

    Integer getTestScore(Long testId, int gender, int age_group_id, Long count);

    TExamine findByUserId(Long userID);

    void addTestItem(TExamine tExamine);

    void insertTestRecord(TExamine tExamine);
}
