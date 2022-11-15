package springboot.service;

import springboot.mybatis.po.TWqxTrain;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TWqxTrainCustom;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TWqxTrainService extends IService<TWqxTrain> {

//    插入无器械自由训练记录
    boolean insertTrainRecord(TWqxTrain tWqxTrain);

//    获取所有用户最新无器械自由训练列表
    List<TWqxTrainCustom> listWqxTrainStudent();

//    根据studentId获取用户所有训练记录
    List<TWqxTrainCustom> listWQXsutdentfreetrain(Long studentId);

//    根据id查询训练记录
    TWqxTrain selectWqxTrainbyId(Long wqxtrainId);
}
