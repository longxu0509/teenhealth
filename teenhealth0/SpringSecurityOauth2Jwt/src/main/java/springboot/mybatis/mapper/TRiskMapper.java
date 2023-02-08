package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TRisk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Mapper
public interface TRiskMapper extends BaseMapper<TRisk> {

    TRisk findByUserId(Long userID);

    void addTestItem(TRisk tRisk);

    void insertTestRecord(TRisk tRisk);

    List<TRisk> getRiskList(Long userID);
}
