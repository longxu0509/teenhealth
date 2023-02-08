package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TCourseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Mapper
public interface TCourseDetailMapper extends BaseMapper<TCourseDetail> {

    List<TCourseDetail> getDetail(Long courseId);
}
