package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TExamine;
import springboot.mybatis.mapper.TExamineMapper;
import springboot.service.TExamineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-04
 */
@Service
public class TExamineServiceImpl extends ServiceImpl<TExamineMapper, TExamine> implements TExamineService {

    @Autowired
    private TExamineMapper tExamineMapper;

    @Override
    public Integer getTestScore(Long testId, int gender, int age_group_id, Long count) {
        return tExamineMapper.getTestScore(testId, gender, age_group_id, count);
    }

    @Override
    public TExamine findByUserId(Long userID) {
        return tExamineMapper.findByUserId(userID);
    }

    @Override
    public void addTestItem(TExamine tExamine) {
        tExamineMapper.addTestItem(tExamine);
    }

    @Override
    public void insertTestRecord(TExamine tExamine) {
        tExamineMapper.insertTestRecord(tExamine);
    }
}
