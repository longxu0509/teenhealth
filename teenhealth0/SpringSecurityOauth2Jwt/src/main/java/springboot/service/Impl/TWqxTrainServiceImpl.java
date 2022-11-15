package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWqxTrain;
import springboot.mybatis.mapper.TWqxTrainMapper;
import springboot.mybatis.po.TWqxTrainCustom;
import springboot.service.TWqxTrainService;
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
public class TWqxTrainServiceImpl extends ServiceImpl<TWqxTrainMapper, TWqxTrain> implements TWqxTrainService {
    @Autowired
    private TWqxTrainMapper tWqxTrainMapper;

    //    插入无器械自由训练记录
    @Override
    public boolean insertTrainRecord(TWqxTrain tWqxTrain){
        return tWqxTrainMapper.insertTrainRecord(tWqxTrain);
    }

    //    获取所有用户最新无器械自由训练列表
    @Override
    public List<TWqxTrainCustom> listWqxTrainStudent(){
        return tWqxTrainMapper.listWqxTrainStudent();
    }

    //    根据studentId获取用户所有训练记录
    public List<TWqxTrainCustom> listWQXsutdentfreetrain(Long studentId){
        return tWqxTrainMapper.listWQXsutdentfreetrain(studentId);
    }

    //    根据id查询训练记录
    public TWqxTrain selectWqxTrainbyId(Long wqxtrainId){
        return tWqxTrainMapper.selectWqxTrainbyId(wqxtrainId);
    }

}
