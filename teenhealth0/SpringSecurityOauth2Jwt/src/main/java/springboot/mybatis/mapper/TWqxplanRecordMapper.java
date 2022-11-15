package springboot.mybatis.mapper;

import org.springframework.stereotype.Repository;
import springboot.mybatis.po.TWqxplanRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import springboot.mybatis.po.TWqxplanRecordCustom;

import java.util.List;

@Repository
public interface TWqxplanRecordMapper extends BaseMapper<TWqxplanRecord> {

    //查询所有学生信息及训练记录
    List<TWqxplanRecordCustom> selectAllStudent(String id);

    //插入训练记录
    Integer insertWqxplanRecord(TWqxplanRecord tWqxplanRecord);

    //根据处方id查询训练记录
    List<TWqxplanRecordCustom> selectTrainRecord(Long id);
}
