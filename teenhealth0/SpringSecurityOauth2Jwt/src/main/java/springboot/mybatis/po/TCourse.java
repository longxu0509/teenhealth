package springboot.mybatis.po;

import lombok.Data;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TCourse {

    private Integer courseFreq;
    /**
     * 课程处方周期
     */
    private Integer coursePeriod;

    /**
     * 推荐的课程名id
     */
    private Integer courseId;

    /**
     * 推荐的课程名
     */
    private String courseName;

    /**
     * 推荐的课程时间单位分钟
     */
    private Integer courseTime;

    /**
     * 推荐的课程名卡路里
     */
    private Integer courseCalories;
}
