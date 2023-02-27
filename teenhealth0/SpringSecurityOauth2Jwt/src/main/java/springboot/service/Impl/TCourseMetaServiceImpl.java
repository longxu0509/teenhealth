package springboot.service.Impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TCourseMeta;
import springboot.mybatis.mapper.TCourseMetaMapper;
import springboot.service.TCourseMetaService;
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
public class TCourseMetaServiceImpl extends ServiceImpl<TCourseMetaMapper, TCourseMeta> implements TCourseMetaService {

    @Autowired
    private TCourseMetaMapper tCourseMetaMapper;

    @Override
    public List<TCourseMeta> selectByConditinon(String type, String position, int level) {
        return tCourseMetaMapper.selectByConditinon(type, position, level);
    }

    @Override
    public TCourseMeta selectByCode(String courseCode) {
        return tCourseMetaMapper.selectByCode(courseCode);
    }

    @Override
    public List<TCourseMeta> getCourseList(Integer isAI) {
        return tCourseMetaMapper.getCourseList(isAI);
    }
}
