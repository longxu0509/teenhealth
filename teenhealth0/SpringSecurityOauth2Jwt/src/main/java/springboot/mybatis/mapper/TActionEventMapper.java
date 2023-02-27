package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TActionEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Mapper
public interface TActionEventMapper extends BaseMapper<TActionEvent> {

    int insertActionEvent(TActionEvent tActionEvent);

    int updateByID(TActionEvent tActionEvent);

    int getSuccessCnt(Long evalEventId);

    List<TActionEvent> selectListByEvalID(Long evalEventID);

    TActionEvent selectNext(Long evalEventId);

    TActionEvent selectCurrent(Long evalEventId);
}
