package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestCardior;
import springboot.mybatis.mapper.TTestCardiorMapper;
import springboot.service.TTestCardiorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Service
public class TTestCardiorServiceImpl extends ServiceImpl<TTestCardiorMapper, TTestCardior> implements TTestCardiorService {

    @Autowired
    private TTestCardiorMapper tTestCardiorMapper;

    @Override
    public TTestCardior getAdviceAndScore(Long count) {
        return tTestCardiorMapper.getAdviceAndScore(count);
    }
}
