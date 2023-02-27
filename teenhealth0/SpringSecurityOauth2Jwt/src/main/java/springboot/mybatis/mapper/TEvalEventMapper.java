package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TEvalEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Mapper
public interface TEvalEventMapper extends BaseMapper<TEvalEvent> {

    Integer insertEvent(TEvalEvent tEvalEvent);

    TEvalEvent selectByID(Long evalEventId);

    int updateStatusByID(Long evalEventId);

    Integer getTotalTime(Long evalEventID);

    Integer getTotalCalories(Long evalEventID);
}
