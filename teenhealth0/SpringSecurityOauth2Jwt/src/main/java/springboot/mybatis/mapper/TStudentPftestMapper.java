package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TStudentPftest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import springboot.mybatis.po.TStudentPftestCustom;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Mapper
public interface TStudentPftestMapper extends BaseMapper<TStudentPftest> {

    int addTestItem(TStudentPftest tStudentPftest);

    TStudentPftest findByStudentId(Long studentId);

    int insertTestRecord(TStudentPftest studentPftest);

    List<TStudentPftestCustom> getStudentPftestLatest();
}
