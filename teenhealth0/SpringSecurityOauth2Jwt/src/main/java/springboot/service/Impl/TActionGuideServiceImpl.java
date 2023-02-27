package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TActionGuide;
import springboot.mybatis.mapper.TActionGuideMapper;
import springboot.service.TActionGuideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Service
public class TActionGuideServiceImpl extends ServiceImpl<TActionGuideMapper, TActionGuide> implements TActionGuideService {

    @Autowired
    private TActionGuideMapper tActionGuideMapper;

    @Override
    public TActionGuide selectById(Integer actionId) {
        return tActionGuideMapper.selectNext(actionId);
    }
}
