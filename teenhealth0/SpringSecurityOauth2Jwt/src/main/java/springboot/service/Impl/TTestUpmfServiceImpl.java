package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestUpmf;
import springboot.mybatis.mapper.TTestUpmfMapper;
import springboot.service.TTestUpmfService;
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
public class TTestUpmfServiceImpl extends ServiceImpl<TTestUpmfMapper, TTestUpmf> implements TTestUpmfService {

    @Autowired
    private TTestUpmfMapper tTestUpmfMapper;
    @Override
    public TTestUpmf getAdviceAndScore(Long count) {
        return tTestUpmfMapper.getAdviceAndScore(count);
    }

    @Override
    public List<TTestUpmf> testList() {
        return tTestUpmfMapper.testList();
    }

    @Override
    public int updateTestUpmf(TTestUpmf tTestUpmf, long id) {
        tTestUpmf.setId(id);
        return tTestUpmfMapper.updateTestUpmf(tTestUpmf);
    }

    @Override
    public TTestUpmf getTestById(long id) {
        return tTestUpmfMapper.getTestById(id);
    }
}
