package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TDetail;
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
public interface TDetailMapper extends BaseMapper<TDetail> {

    List<TDetail> selectByCode(String courseCode);
}
