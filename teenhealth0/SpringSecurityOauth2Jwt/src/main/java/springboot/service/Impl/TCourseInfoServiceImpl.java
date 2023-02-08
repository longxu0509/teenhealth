package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TCourseInfo;
import springboot.mybatis.mapper.TCourseInfoMapper;
import springboot.service.TCourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Service
public class TCourseInfoServiceImpl extends ServiceImpl<TCourseInfoMapper, TCourseInfo> implements TCourseInfoService {

    @Autowired
    private TCourseInfoMapper tCourseInfoMapper;

    @Override
    public TCourseInfo getCourseById(Long courseId) {
        return tCourseInfoMapper.getCourseById(courseId);
    }
}
