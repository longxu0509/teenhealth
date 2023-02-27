package springboot.mybatis.po;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class StudentTestInfo {
    // 学生账户
    private Long userId;

    // 测试动作id
    private Long actionId;

    private Integer calories;

    private String startTime;
    // 测试动作计数
    private Double count;

    private String duration;
}
