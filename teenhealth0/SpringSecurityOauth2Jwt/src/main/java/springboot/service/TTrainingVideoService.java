package springboot.service;

import springboot.mybatis.po.TTrainingVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TTrainingVideoService extends IService<TTrainingVideo> {

    //上传视频，添加视频信息
    public void insertTrainingVideo(TTrainingVideo tTrainingVideo);

    //获取视频列表
    public List<TTrainingVideo> findTrainingVideo()throws Exception;

    //根据视频id删除视频
    public void deleteVideo(Integer trainingVideo_id) throws Exception;

    //根据视频id获取视频信息
    public TTrainingVideo selectTraninVideo(Integer trainingVideo_id);

    //修改视频名称信息
    public Integer updateTrainingVideo(TTrainingVideo tTrainingVideo);

    //根据视频id获取视频文件名称
    public TTrainingVideo findFileName(Integer trainingVideo_id) ;

    //根据id数组批量删除视频
//    public int deleteVideos(String[] ids);

    //根据视频名称、难度、训练时长、消耗卡路里检索训练视频
    List<TTrainingVideo> findVideoby(String sort);
}
