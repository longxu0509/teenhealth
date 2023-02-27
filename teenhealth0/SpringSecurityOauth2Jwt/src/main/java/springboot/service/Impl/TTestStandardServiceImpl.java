package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.Action;
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
    public Integer getTestScore(Long testId, int gender, int age_group_id, Long count) {
        int score = tTestStandardMapper.getTestScore(testId, gender, age_group_id, count);

        if (score < 100)
            return score;
        Long maxCount;
        // 获得100分的及格线
        if (testId == Action.LRUNNING.getIndex() || testId == Action.SRUNNING.getIndex()) {
            maxCount = tTestStandardMapper.getMinTime(testId, gender, age_group_id);
        } else {
            maxCount = tTestStandardMapper.getMaxCount(testId, gender, age_group_id);
        }

        if (testId == Action.PUSHUP.getIndex()) {
            score += (count - maxCount) / 2;
        } else if (testId == Action.PULLUP.getIndex()) {
            score += (count - maxCount);
        } else if (testId == Action.SITUP.getIndex()) {
            score += (count - maxCount) / 2;
        } else if (testId == Action.LEGHIGBRIDGE.getIndex()) {
            // 获得100分的及格线
            score += (count - maxCount) / 2;
        } else if (testId == Action.LRUNNING.getIndex()) {
            score += (maxCount - count) / 5;
        } else if (testId == Action.SRUNNING.getIndex()) {
            score += (maxCount - count) / 10;
        }
        return score;
    }
}
