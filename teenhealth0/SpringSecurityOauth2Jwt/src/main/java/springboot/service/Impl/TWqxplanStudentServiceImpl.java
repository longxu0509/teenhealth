package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TWqxplanStudent;
import springboot.mybatis.mapper.TWqxplanStudentMapper;
import springboot.mybatis.po.TWqxplanStudentCustom;
import springboot.service.TWqxplanStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
@Service
public class TWqxplanStudentServiceImpl extends ServiceImpl<TWqxplanStudentMapper, TWqxplanStudent> implements TWqxplanStudentService {

    @Autowired
    private TWqxplanStudentMapper tWqxplanStudentMapper;

    @Override
    public List<TWqxplanStudentCustom> getStudentWQXPlan(Long studentId) {
        return tWqxplanStudentMapper.getStudentWQXPlan(studentId);
    }
}
