package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TGuideVideos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.annotation.ManagedBean;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-12-22
 */
@Mapper
public interface TGuideVideosMapper extends BaseMapper<TGuideVideos> {

    List<TGuideVideos> getGuideVideoListByid(Integer actionTypeId, Integer positionId);

    TGuideVideos findFileName(Integer videoId);
}
