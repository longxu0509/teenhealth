package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TUserEval;
import springboot.mybatis.mapper.TUserEvalMapper;
import springboot.service.TUserEvalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-08
 */
@Service
public class TUserEvalServiceImpl extends ServiceImpl<TUserEvalMapper, TUserEval> implements TUserEvalService {

    @Autowired
    private TUserEvalMapper tUserEvalMapper;

    @Override
    public int insertUserEval(TUserEval tUserEval) {
        return tUserEvalMapper.insertUserEval(tUserEval);
    }

    @Override
    public TUserEval selectByUserID(Long userID) {
        return tUserEvalMapper.selectByUserID(userID);
    }

    @Override
    public List<TUserEval> selectAll(Long userId) {
        return tUserEvalMapper.selectAll(userId);
    }

    @Override
    public TUserEval selectByReportId(Long reportId) {
        return tUserEvalMapper.selectByReportId(reportId);
    }

    @Override
    public TUserEval selectLatest(Long userId) {
        return tUserEvalMapper.selectLatest(userId);
    }
}
