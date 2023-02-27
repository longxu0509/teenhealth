package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TCourseMeta;
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
public interface TCourseMetaMapper extends BaseMapper<TCourseMeta> {

    List<TCourseMeta> selectByConditinon(String type, String position, int level);

    TCourseMeta selectByCode(String courseCode);

    List<TCourseMeta> getCourseList(Integer isAI);
}
