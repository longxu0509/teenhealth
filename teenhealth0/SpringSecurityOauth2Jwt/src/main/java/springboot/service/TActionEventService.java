package springboot.service;

import springboot.mybatis.po.TActionEvent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
public interface TActionEventService extends IService<TActionEvent> {

    int insertActionEvent(TActionEvent tActionEvent);

    int updateByID(TActionEvent tActionEvent);

    int getSuccessCnt(Long evalEventId);

    List<TActionEvent> selectList(Long evalEventID);

    TActionEvent selectNext(Long evalEventId);

    TActionEvent selectCurrent(Long evalEventId);
}
