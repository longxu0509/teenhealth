package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TWqxTrainCustom;
import springboot.mybatis.po.TWqxplanNp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
@Mapper
public interface TWqxplanNpMapper extends BaseMapper<TWqxplanNp> {
    //    获取所有用户最新无器械自由训练列表
    List<TWqxplanNp> listWqxTrainNp();

    boolean deleteWQXplan(Long planId);

    int insertWQXplan(TWqxplanNp tWqxplanNp);

    int updatePlanByPK(TWqxplanNp tWqxplanNp);
}
