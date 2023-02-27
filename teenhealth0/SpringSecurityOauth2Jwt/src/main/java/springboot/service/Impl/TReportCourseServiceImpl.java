package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TReportCourse;
import springboot.mybatis.mapper.TReportCourseMapper;
import springboot.service.TReportCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Service
public class TReportCourseServiceImpl extends ServiceImpl<TReportCourseMapper, TReportCourse> implements TReportCourseService {

    @Autowired
    TReportCourseMapper tReportCourseMapper;

    @Override
    public int selectByReportId(Long reportId) {
        return tReportCourseMapper.selectByReportId(reportId);
    }

    @Override
    public void insertItem(TReportCourse tReportCourse) {
        tReportCourseMapper.insertItem(tReportCourse);
    }

    @Override
    public List<TReportCourse> selectListById(Long reportId) {
        return tReportCourseMapper.selectListById(reportId);
    }
}
