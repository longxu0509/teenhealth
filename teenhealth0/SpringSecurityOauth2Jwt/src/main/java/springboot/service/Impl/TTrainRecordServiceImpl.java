package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTrainRecord;
import springboot.mybatis.mapper.TTrainRecordMapper;
import springboot.service.TTrainRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
@Service
public class TTrainRecordServiceImpl extends ServiceImpl<TTrainRecordMapper, TTrainRecord> implements TTrainRecordService {

    @Autowired
    private TTrainRecordMapper tTrainRecordMapper;

    @Override
    public void insertRecord(TTrainRecord tTrainRecord) {
        tTrainRecordMapper.insertRecord(tTrainRecord);
    }

    @Override
    public TTrainRecord selectByUserId(Long userID, Long code) {
        return tTrainRecordMapper.selectByUserId(userID, code);
    }
}
