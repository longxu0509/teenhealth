package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TEvalReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Mapper
public interface TEvalReportMapper extends BaseMapper<TEvalReport> {

    Integer insertReport(TEvalReport teValReport);

    int updateReport(TEvalReport tEvalReport);


    TEvalReport selectByrReportId(Long reportId);
}
