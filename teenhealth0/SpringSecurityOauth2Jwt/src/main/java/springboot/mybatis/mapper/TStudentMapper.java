package springboot.mybatis.mapper;

import org.springframework.stereotype.Repository;
import springboot.mybatis.po.TStudent;
import springboot.mybatis.po.TStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface TStudentMapper {
    int countByExample(TStudentExample example);

    int deleteByExample(TStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStudent record);

    int insertSelective(TStudent record);

    List<TStudent> selectByExample(TStudentExample example);

    TStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByExample(@Param("record") TStudent record, @Param("example") TStudentExample example);

    //修改学生信息
    int updateByPrimaryKeySelective(TStudent record);

    int updateByPrimaryKey(TStudent record);

    //获取学生基本信息列表
    List<TStudent> selectStudent();

    //根据姓名或学号模糊查询学生信息
    List<TStudent> selectByNameStudentNo(String NameStudentNo);

    //根据学号查询学生信息
    TStudent selectByStudentNo(String studentNo);

    //根据学号修改学生信息
    Integer updateByStudentNO(TStudent tStudent);

    TStudent getStudentByNo(String studentNo);
}