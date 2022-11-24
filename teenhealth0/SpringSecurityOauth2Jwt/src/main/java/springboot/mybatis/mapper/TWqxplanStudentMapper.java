package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TWqxplanStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import springboot.mybatis.po.TWqxplanStudentCustom;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-16
 */
@Mapper
public interface TWqxplanStudentMapper extends BaseMapper<TWqxplanStudent> {
    

    List<TWqxplanStudentCustom> getStudentWQXPlan(Long studentId);

    List<TWqxplanStudentCustom> getWQXPlanLatest();

    int addStudentWQXplan(TWqxplanStudent tWqxplanStudent);
}
