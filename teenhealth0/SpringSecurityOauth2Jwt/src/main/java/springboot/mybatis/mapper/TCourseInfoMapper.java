package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TCourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Mapper
public interface TCourseInfoMapper extends BaseMapper<TCourseInfo> {

    TCourseInfo getCourseById(Long courseId);
}
