package springboot.service;

import springboot.mybatis.po.TCourseMeta;
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
public interface TCourseMetaService extends IService<TCourseMeta> {


    List<TCourseMeta> selectByConditinon(String type, String position, int level);

    TCourseMeta selectByCode(String courseCode);

    List<TCourseMeta> getCourseList(Integer isAI);
}
