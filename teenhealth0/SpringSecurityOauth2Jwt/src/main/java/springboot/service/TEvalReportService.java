package springboot.service;

import springboot.mybatis.po.TEvalReport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
public interface TEvalReportService extends IService<TEvalReport> {

     Integer insertReport(TEvalReport teValReport);

    int updateReport(TEvalReport tEvalReport);

    TEvalReport selectByrReportId(Long reportId);
}
