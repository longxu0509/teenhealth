package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TCapability;
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
public interface TCapabilityMapper extends BaseMapper<TCapability> {

    TCapability findByUserId(Long userID);

    void addTestItem(TCapability tCapability);

    void insertTestRecord(TCapability tCapability);

    List<TCapability> getCapabilityList(Long userID);

    TCapability getCapabilityReport(Long userId);
}
