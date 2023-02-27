package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TActionEvent;
import springboot.mybatis.mapper.TActionEventMapper;
import springboot.service.TActionEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Service
public class TActionEventServiceImpl extends ServiceImpl<TActionEventMapper, TActionEvent> implements TActionEventService {

    @Autowired
    private TActionEventMapper tActionEventMapper;

    @Override
    public int insertActionEvent(TActionEvent tActionEvent) {
        return tActionEventMapper.insertActionEvent(tActionEvent);
    }

    @Override
    public int updateByID(TActionEvent tActionEvent) {
        return tActionEventMapper.updateByID(tActionEvent);
    }

    @Override
    public int getSuccessCnt(Long evalEventId) {
        return tActionEventMapper.getSuccessCnt(evalEventId);
    }

    @Override
    public List<TActionEvent> selectList(Long evalEventID) {
        return tActionEventMapper.selectListByEvalID(evalEventID);
    }

    @Override
    public TActionEvent selectNext(Long evalEventId) {
        return tActionEventMapper.selectNext(evalEventId);
    }

    @Override
    public TActionEvent selectCurrent(Long evalEventId) {
        return tActionEventMapper.selectCurrent(evalEventId);
    }
}
