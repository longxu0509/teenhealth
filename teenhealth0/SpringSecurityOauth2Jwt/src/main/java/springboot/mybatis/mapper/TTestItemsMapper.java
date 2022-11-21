package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TTestItems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import springboot.mybatis.po.TTrainingVideo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-17
 */
@Mapper
public interface TTestItemsMapper extends BaseMapper<TTestItems> {

    //获取视频列表
    List<TTestItems> findTestingVideo();
}
