package springboot.mybatis.po;

import lombok.Data;

import java.util.List;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TCourseInfoCustom extends TCourseInfo{
    List<TCourseDetail> tCourseDetailList;
}
