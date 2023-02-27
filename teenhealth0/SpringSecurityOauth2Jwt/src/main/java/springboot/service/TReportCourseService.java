package springboot.service;

import springboot.mybatis.po.TReportCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
public interface TReportCourseService extends IService<TReportCourse> {

    int selectByReportId(Long reportId);

    void insertItem(TReportCourse tReportCourse);

    List<TReportCourse> selectListById(Long reportId);
}
