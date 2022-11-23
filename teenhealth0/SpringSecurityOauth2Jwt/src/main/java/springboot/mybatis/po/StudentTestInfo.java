package springboot.mybatis.po;

import lombok.Data;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class StudentTestInfo {
    // 学生id
    private Long userId;

    // 测试动作id
    private Long testId;

    // 测试动作name
    private String testName;

    // 测试动作计数
    private Long count;
}
