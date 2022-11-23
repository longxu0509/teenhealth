package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestCoordinate;
import springboot.mybatis.mapper.TTestCoordinateMapper;
import springboot.service.TTestCoordinateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Service
public class TTestCoordinateServiceImpl extends ServiceImpl<TTestCoordinateMapper, TTestCoordinate> implements TTestCoordinateService {

    @Autowired
    private TTestCoordinateMapper tTestCoordinateMapper;

    @Override
    public TTestCoordinate getAdviceAndScore(Long count) {
        return tTestCoordinateMapper.getAdviceAndScore(count);
    }
}
