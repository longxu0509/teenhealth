package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTrainRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
@Mapper
public interface TTrainRecordMapper extends BaseMapper<TTrainRecord> {

    void insertRecord(TTrainRecord tTrainRecord);

    TTrainRecord selectByUserId(Long userId, Long code);
}
