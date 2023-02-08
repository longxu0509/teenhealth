package springboot.service;

import springboot.mybatis.po.TCourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
public interface TCourseDetailService extends IService<TCourseDetail> {

    List<TCourseDetail> getDetail(Long courseId);
}
