package springboot.service;

import springboot.mybatis.po.TTrainRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
public interface TTrainRecordService extends IService<TTrainRecord> {

    void insertRecord(TTrainRecord tTrainRecord);

    TTrainRecord selectByUserId(Long userID, Long code);
}
