package springboot.service;

import com.github.pagehelper.PageInfo;
import springboot.mybatis.po.TStudent;

import java.util.List;

public interface StudentService {
    //查询所有学生列表
    public PageInfo<TStudent> listStudent(PageInfo pageInfo);

    //根据学号查询学生信息
    TStudent selectByStudentNo(String studentNo);

    //根据姓名或学号模糊查询学生信息
    public List<TStudent> selectByNameStudentNo(String NameStudentNo)throws Exception;


    //修改学生信息
    public Integer updateStudent(Long id, TStudent tStudent)throws Exception;

    //删除学生信息
    public Integer deleteStudent(Long id)throws Exception;

    //添加单个学生用户
    public Integer insertStudent(TStudent tStudent)throws Exception;

    //根据学生id查询学生信息
    TStudent selectById(Long id);

    //根据学号修改学生信息
    Integer updateStudentbyStudengNO(TStudent tStudent);

    TStudent getStudentByNo(String studentNo);
}
