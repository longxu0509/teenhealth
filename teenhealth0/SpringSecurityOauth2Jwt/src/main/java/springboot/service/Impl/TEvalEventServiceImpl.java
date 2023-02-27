package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TEvalEvent;
import springboot.mybatis.mapper.TEvalEventMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springboot.service.TEvalEventService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Service
public class TEvalEventServiceImpl extends ServiceImpl<TEvalEventMapper, TEvalEvent> implements TEvalEventService {

    @Autowired
    private TEvalEventMapper tEvalEventMapper;

    @Override
    public Integer insertEvent(TEvalEvent tEvalEvent) {
        return tEvalEventMapper.insertEvent(tEvalEvent);
    }

    @Override
    public TEvalEvent selectByID(Long evalEventId) {
        return tEvalEventMapper.selectByID(evalEventId);
    }

    @Override
    public int updateStatusByID(Long evalEventId) {
        return tEvalEventMapper.updateStatusByID(evalEventId);
    }

    @Override
    public Integer getTotalTime(Long evalEventID) {
        return tEvalEventMapper.getTotalTime(evalEventID);
    }

    @Override
    public Integer getTotalCalories(Long evalEventID) {
        return tEvalEventMapper.getTotalCalories(evalEventID);
    }
}
