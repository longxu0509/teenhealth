package springboot.service;

import springboot.mybatis.po.TWqxplanStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TWqxplanStudentCustom;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
public interface TWqxplanStudentService extends IService<TWqxplanStudent> {

    List<TWqxplanStudentCustom> getStudentWQXPlan(Long studentId);
}
