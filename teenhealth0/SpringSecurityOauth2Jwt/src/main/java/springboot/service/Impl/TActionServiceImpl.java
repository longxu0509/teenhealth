package springboot.service.Impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.stylesheets.LinkStyle;
import springboot.mybatis.po.TAction;
import springboot.mybatis.mapper.TActionMapper;
import springboot.service.TActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Service
public class TActionServiceImpl extends ServiceImpl<TActionMapper, TAction> implements TActionService {

    @Autowired
    private TActionMapper tActionMapper;

    @Override
    public TAction selectByCode(String actionCode) {
        return tActionMapper.selectByCode(actionCode);
    }

    @Override
    public List<TAction> getAIList(Integer isAI) {
        return tActionMapper.getAIList(isAI);
    }
}
