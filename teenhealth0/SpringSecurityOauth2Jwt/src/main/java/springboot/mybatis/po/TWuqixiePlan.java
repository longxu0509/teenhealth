package springboot.mybatis.po;

import lombok.Data;

import java.util.Date;

@Data
public class TWuqixiePlan {

    private Long id;

    private Long studentId;

    private Integer difficultyLevel;

    private String trainingContent;

    private Date createTime;

    /**
     * 1为可用，0不可用
     */
    private Integer isEnable;

}
