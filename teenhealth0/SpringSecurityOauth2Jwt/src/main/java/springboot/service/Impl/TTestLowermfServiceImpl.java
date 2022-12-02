package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestLowermf;
import springboot.mybatis.mapper.TTestLowermfMapper;
import springboot.service.TTestLowermfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Service
public class TTestLowermfServiceImpl extends ServiceImpl<TTestLowermfMapper, TTestLowermf> implements TTestLowermfService {

    @Autowired
    private TTestLowermfMapper tTestLowermfMapper;
    @Override
    public TTestLowermf getAdviceAndScore(Long count) {
        return tTestLowermfMapper.getAdviceAndScore(count);
    }

    @Override
    public List<TTestLowermf> testList() {
        return tTestLowermfMapper.testList();
    }
}
