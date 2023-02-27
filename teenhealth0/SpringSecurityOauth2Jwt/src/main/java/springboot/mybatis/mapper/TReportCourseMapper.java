package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TReportCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Mapper
public interface TReportCourseMapper extends BaseMapper<TReportCourse> {

    int selectByReportId(Long reportId);

    void insertItem(TReportCourse tReportCourse);

    List<TReportCourse> selectListById(Long reportId);
}
