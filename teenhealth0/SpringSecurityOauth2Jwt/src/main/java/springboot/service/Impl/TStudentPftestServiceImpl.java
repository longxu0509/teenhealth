package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TStudentPftest;
import springboot.mybatis.mapper.TStudentPftestMapper;
import springboot.service.TStudentPftestService;
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
public class TStudentPftestServiceImpl extends ServiceImpl<TStudentPftestMapper, TStudentPftest> implements TStudentPftestService {

    @Autowired
    private TStudentPftestMapper tStudentPftestMapper;
    @Override
    public TStudentPftest findByStudentId(Long studentId) {
        return tStudentPftestMapper.findByStudentId(studentId);
    }

    @Override
    public int addTestItem(TStudentPftest tStudentPftest) {
        return tStudentPftestMapper.addTestItem(tStudentPftest);
    }

    @Override
    public int insertTestRecord(TStudentPftest studentPftest) {
        return tStudentPftestMapper.insertTestRecord(studentPftest);
    }
}
