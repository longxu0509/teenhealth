package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TUserEval;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Mapper
public interface TUserEvalMapper extends BaseMapper<TUserEval> {

    int insertUserEval(TUserEval tUserEval);

    TUserEval selectByUserID(Long userID);

    List<TUserEval> selectAll(Long userId);

    TUserEval selectByReportId(Long reportId);

    TUserEval selectLatest(Long userId);
}
