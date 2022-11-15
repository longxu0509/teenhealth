package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import springboot.mybatis.po.TTrainingVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import springboot.mybatis.po.TrainingVideoExample;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-02
 */
public interface TTrainingVideoMapper extends BaseMapper<TTrainingVideo> {
    long countByExample(TrainingVideoExample example);

    int deleteByExample(TrainingVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTrainingVideo record);

    int insertSelective(TTrainingVideo record);

    List<TTrainingVideo> selectByExample(TrainingVideoExample example);

    TTrainingVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TTrainingVideo record, @Param("example") TrainingVideoExample example);

    int updateByExample(@Param("record") TTrainingVideo record, @Param("example") TrainingVideoExample example);

    int updateByPrimaryKeySelective(TTrainingVideo record);

    int updateByPrimaryKey(TTrainingVideo record);

    //获取视频列表
    List<TTrainingVideo> findTrainingVideo();

    //批量删除
    //根据视频名称、难度、训练时长、消耗卡路里检索训练视频
    List<TTrainingVideo> findVideoby(@Param("sort")String sort);
}
