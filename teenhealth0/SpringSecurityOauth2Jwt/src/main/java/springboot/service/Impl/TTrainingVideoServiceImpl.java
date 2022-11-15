package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTrainingVideo;
import springboot.mybatis.mapper.TTrainingVideoMapper;
import springboot.service.TTrainingVideoService;
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
public class TTrainingVideoServiceImpl extends ServiceImpl<TTrainingVideoMapper, TTrainingVideo> implements TTrainingVideoService {

    @Autowired
    private TTrainingVideoMapper tTrainingVideoMapper;

    //网页上传训练教学视频,插入视频信息到数据库
    @Override
    public void insertTrainingVideo(TTrainingVideo trainingVideo){
        tTrainingVideoMapper.insertSelective(trainingVideo);
    }
    //
    @Override
    public List<TTrainingVideo> findTrainingVideo() throws Exception{
        return tTrainingVideoMapper.findTrainingVideo();
    }
    @Override
    public void deleteVideo(Integer trainingVideo_id) throws Exception{
        tTrainingVideoMapper.deleteByPrimaryKey(trainingVideo_id);
    }
    @Override
    public TTrainingVideo selectTraninVideo(Integer trainingVideo_id) {
        return tTrainingVideoMapper.selectByPrimaryKey(trainingVideo_id);
    }
    @Override
    public Integer updateTrainingVideo(TTrainingVideo trainingVideo){
        return tTrainingVideoMapper.updateByPrimaryKeySelective(trainingVideo);
    }
    @Override
    public TTrainingVideo findFileName(Integer trainingVideo_id) {
        return tTrainingVideoMapper.selectByPrimaryKey(trainingVideo_id);
    }

//    @Override
//    public int deleteVideos(String[] ids) {
//        return tTrainingVideoMapper.deleteByPrimaryKeys(ids);
//    }

    //根据视频名称、难度、训练时长、消耗卡路里检索训练视频
    @Override
    public List<TTrainingVideo> findVideoby(String sort){
        return tTrainingVideoMapper.findVideoby(sort);
    }
}
