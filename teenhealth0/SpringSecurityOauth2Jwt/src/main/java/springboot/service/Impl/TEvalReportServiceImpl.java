package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TEvalReport;
import springboot.mybatis.mapper.TEvalReportMapper;
import springboot.service.TEvalReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Service
public class TEvalReportServiceImpl extends ServiceImpl<TEvalReportMapper, TEvalReport> implements TEvalReportService {

    @Autowired
    private TEvalReportMapper tEvalReportMapper;

    @Override
    public Integer insertReport(TEvalReport teValReport) {
        return tEvalReportMapper.insertReport(teValReport);
    }

    @Override
    public int updateReport(TEvalReport tEvalReport) {
        return tEvalReportMapper.updateReport(tEvalReport);
    }

    @Override
    public TEvalReport selectByrReportId(Long reportId) {
        return tEvalReportMapper.selectByrReportId(reportId);
    }
}
