package springboot.service;

import springboot.mybatis.po.TStudentPftest;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TStudentPftestCustom;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
public interface TStudentPftestService extends IService<TStudentPftest> {

    TStudentPftest findByStudentId(Long studentId);

    int addTestItem(TStudentPftest tStudentPftest);

    int insertTestRecord(TStudentPftest studentPftest);

    List<TStudentPftestCustom> getStudentPftestLatest();

    List<TStudentPftestCustom> getStudentPftestById(Long id);

    boolean deleteStudentPftest(Long id);
}
