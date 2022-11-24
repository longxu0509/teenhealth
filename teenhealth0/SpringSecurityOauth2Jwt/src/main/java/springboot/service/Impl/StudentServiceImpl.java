package springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import springboot.service.StudentService;
import springboot.mybatis.mapper.TStudentMapper;
import springboot.mybatis.po.TStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {
    @Autowired TStudentMapper tStudentMapper;

//平台端
    //查询所有学生列表
    @Override
    public PageInfo<TStudent> listStudent(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        PageInfo pageInfo1=new PageInfo(tStudentMapper.selectStudent());
        return pageInfo1;
    }

    //根据学号查询学生信息
    @Override
    public TStudent selectByStudentNo(String studentNo){
        return tStudentMapper.selectByStudentNo(studentNo);
    }

    //根据姓名或学号模糊查询学生信息
    @Override
    public List<TStudent> selectByNameStudentNo(String NameStudentNo)throws Exception{
        List<TStudent> list= tStudentMapper.selectByNameStudentNo(NameStudentNo);
        return list;
    }


    //修改学生信息
    @Override
    public Integer updateStudent(Long id,TStudent tStudent)throws Exception{
        tStudent.setId(id);
        return tStudentMapper.updateByPrimaryKeySelective(tStudent);
    }

    //删除学生信息
    @Override
    public Integer deleteStudent(Long id)throws Exception{
        return tStudentMapper.deleteByPrimaryKey(id);
    }

    //添加单个学生用户
    @Override
    public Integer insertStudent(TStudent tStudent)throws Exception{
        return tStudentMapper.insertSelective(tStudent);
    }

    @Override
    //根据学生id查询学生信息
    public TStudent selectById(Long id){
        return tStudentMapper.selectByPrimaryKey(id);
    }

    @Override
    //根据学号修改学生信息
    public Integer updateStudentbyStudengNO(TStudent tStudent){
        return tStudentMapper.updateByStudentNO(tStudent);
    }

    @Override
    public TStudent getStudentByNo(String studentNo) {
        return tStudentMapper.getStudentByNo(studentNo);
    }

}
