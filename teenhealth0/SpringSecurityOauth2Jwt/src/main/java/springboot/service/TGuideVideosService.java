package springboot.service;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TGuideVideos;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TTrainingVideo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-22
 */
public interface TGuideVideosService extends IService<TGuideVideos> {

    public List<TGuideVideos> getGuideVideoListByid(Integer actionTypeId, Integer positionId) throws Exception;

    public TGuideVideos findFileName(Integer videoId);
}
