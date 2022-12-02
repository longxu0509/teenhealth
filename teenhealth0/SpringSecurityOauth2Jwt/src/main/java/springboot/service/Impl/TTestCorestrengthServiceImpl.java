package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestCorestrength;
import springboot.mybatis.mapper.TTestCorestrengthMapper;
import springboot.service.TTestCorestrengthService;
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
public class TTestCorestrengthServiceImpl extends ServiceImpl<TTestCorestrengthMapper, TTestCorestrength> implements TTestCorestrengthService {

    @Autowired
    private TTestCorestrengthMapper tTestCorestrengthMapper;
    @Override
    public TTestCorestrength getAdviceAndScore(Long count) {
        return tTestCorestrengthMapper.getAdviceAndScore(count);
    }

    @Override
    public List<TTestCorestrength> testList() {
        return tTestCorestrengthMapper.testList();
    }

    @Override
    public int updateTestCorestrength(TTestCorestrength tTestCorestrength, long id) {
        tTestCorestrength.setId(id);
        return tTestCorestrengthMapper.updateTestCorestrength(tTestCorestrength);
    }
}
