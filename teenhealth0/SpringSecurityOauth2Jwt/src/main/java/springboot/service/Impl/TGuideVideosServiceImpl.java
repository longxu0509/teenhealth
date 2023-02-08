package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TGuideVideos;
import springboot.mybatis.mapper.TGuideVideosMapper;
import springboot.service.TGuideVideosService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-22
 */
@Service
public class TGuideVideosServiceImpl extends ServiceImpl<TGuideVideosMapper, TGuideVideos> implements TGuideVideosService {

    @Autowired
    private TGuideVideosMapper tGuideVideosMapper;

    @Override
    public List<TGuideVideos> getGuideVideoListByid(Integer actionTypeId, Integer positionId) throws Exception {
        return tGuideVideosMapper.getGuideVideoListByid(actionTypeId, positionId);
    }

    @Override
    public TGuideVideos findFileName(Integer videoId) {
        return tGuideVideosMapper.findFileName(videoId);
    }
}
