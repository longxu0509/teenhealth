package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWqxplanRecord;
import springboot.mybatis.mapper.TWqxplanRecordMapper;
import springboot.mybatis.po.TWqxplanRecordCustom;
import springboot.mybatis.po.TWqxplanRecordCustom1;
import springboot.service.TWqxplanRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
@Service
public class TWqxplanRecordServiceImpl extends ServiceImpl<TWqxplanRecordMapper, TWqxplanRecord> implements TWqxplanRecordService {
    @Autowired
    private TWqxplanRecordMapper tWqxplanRecordMapper;

    //查询所有学生信息及训练记录
    public List<TWqxplanRecordCustom> selectAllStudent(String id){
        return tWqxplanRecordMapper.selectAllStudent(id);
    }

    //插入训练记录
    public Integer insertWqxplanRecord (TWqxplanRecord tWqxplanRecord){
        return tWqxplanRecordMapper.insertWqxplanRecord(tWqxplanRecord);
    }

    //根据处方id查询训练记录
    public List<TWqxplanRecordCustom> selectTrainRecord(Long id){
        return tWqxplanRecordMapper.selectTrainRecord(id);
    }

    @Override
    public List<TWqxplanRecordCustom1> getStudentWQXTrainingRecord(Long id, Long planId) {
        return tWqxplanRecordMapper.getStudentWQXTrainingRecord(id, planId);
    }

    @Override
    public TWqxplanRecord getMapByRecordId(Long id) {
        return tWqxplanRecordMapper.getMapByRecordId(id);
    }

}
