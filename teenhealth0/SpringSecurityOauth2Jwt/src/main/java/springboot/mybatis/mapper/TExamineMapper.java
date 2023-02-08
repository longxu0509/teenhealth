package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TExamine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-01-04
 */
@Mapper
public interface TExamineMapper extends BaseMapper<TExamine> {

    Integer getTestScore(Long testId, int gender, int age_group_id, Long count);

    TExamine findByUserId(Long userID);

    void addTestItem(TExamine tExamine);

    void insertTestRecord(TExamine tExamine);
}
