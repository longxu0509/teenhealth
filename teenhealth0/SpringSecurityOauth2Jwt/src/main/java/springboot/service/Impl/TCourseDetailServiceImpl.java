package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TCourseDetail;
import springboot.mybatis.mapper.TCourseDetailMapper;
import springboot.service.TCourseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Service
public class TCourseDetailServiceImpl extends ServiceImpl<TCourseDetailMapper, TCourseDetail> implements TCourseDetailService {
    @Autowired
    private TCourseDetailMapper tCourseDetailMapper;

    @Override
    public List<TCourseDetail> getDetail(Long courseId) {
        return tCourseDetailMapper.getDetail(courseId);
    }
}
