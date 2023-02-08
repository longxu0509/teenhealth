package springboot.service;

import springboot.mybatis.po.TCourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
public interface TCourseInfoService extends IService<TCourseInfo> {

    TCourseInfo getCourseById(Long courseId);
}
