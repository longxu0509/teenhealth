package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestStandard;
import springboot.mybatis.mapper.TTestStandardMapper;
import springboot.service.TTestStandardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Service
public class TTestStandardServiceImpl extends ServiceImpl<TTestStandardMapper, TTestStandard> implements TTestStandardService {

    @Autowired
    private TTestStandardMapper tTestStandardMapper;
    @Override
    public int getTestScore(Long testId, int gender, int age_group_id, Long count) {
        return tTestStandardMapper.getTestScore(testId, gender, age_group_id, count);
    }
}
