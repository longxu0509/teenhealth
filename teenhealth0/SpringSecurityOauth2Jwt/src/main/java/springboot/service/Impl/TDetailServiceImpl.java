package springboot.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TDetail;
import springboot.mybatis.mapper.TDetailMapper;
import springboot.service.TDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Service
public class TDetailServiceImpl extends ServiceImpl<TDetailMapper, TDetail> implements TDetailService {

    @Autowired
    private TDetailMapper tDetailMapper;

    @Override
    public List<TDetail> selectByCode(String courseCode) {
        return tDetailMapper.selectByCode(courseCode);
    }
}
