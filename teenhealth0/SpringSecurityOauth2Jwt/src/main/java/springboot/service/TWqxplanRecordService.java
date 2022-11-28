package springboot.service;

import io.swagger.models.auth.In;
import springboot.mybatis.po.TWqxplanRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TWqxplanRecordCustom;
import springboot.mybatis.po.TWqxplanRecordCustom1;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TWqxplanRecordService extends IService<TWqxplanRecord> {

    //查询所有学生信息及训练记录
   List<TWqxplanRecordCustom> selectAllStudent(String id);

   //插入训练记录
   Integer insertWqxplanRecord (TWqxplanRecord tWqxplanRecord);

   //根据处方id查询训练记录
    List<TWqxplanRecordCustom> selectTrainRecord(Long id);

    List<TWqxplanRecordCustom1> getStudentWQXTrainingRecord(Long id, Long planId);
}
