package springboot.mybatis.po;

import lombok.Data;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TWqxplanStudentCustom extends TWqxplanStudent{
    private TWqxplanNp tWqxplanNp;
    private TStudent tStudent;
}
