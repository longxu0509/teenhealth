package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TAction;
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
public interface TActionMapper extends BaseMapper<TAction> {

    TAction selectByCode(String actionCode);

    List<TAction> getAIList(Integer isAI);
}
