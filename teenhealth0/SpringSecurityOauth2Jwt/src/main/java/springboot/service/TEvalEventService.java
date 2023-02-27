package springboot.service;

import io.swagger.models.auth.In;
import springboot.mybatis.po.TEvalEvent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
public interface TEvalEventService extends IService<TEvalEvent> {

    Integer insertEvent(TEvalEvent tEvalEvent);

    TEvalEvent selectByID(Long evalEventId);

    int updateStatusByID(Long evalEventId);

    Integer getTotalTime(Long evalEventID);

    Integer getTotalCalories(Long evalEventID);
}
