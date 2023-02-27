package springboot.service;

import springboot.mybatis.po.TUserEval;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
public interface TUserEvalService extends IService<TUserEval> {

    int insertUserEval(TUserEval tUserEval);

    // 根据userID 查询出该用户最新的一条评估记录
    TUserEval selectByUserID(Long userID);

    List<TUserEval> selectAll(Long userId);

    // reportId 一条评估记录
    TUserEval selectByReportId(Long reportId);

    TUserEval selectLatest(Long userId);
}
