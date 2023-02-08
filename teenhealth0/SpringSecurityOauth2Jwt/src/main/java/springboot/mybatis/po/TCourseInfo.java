package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Data
public class TCourseInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * （课程，动作，教学视频）名称
     */
    private String courseName;

    private String courseImg;

    /**
     * 课程处方频率
     */
    private Integer courseFreq;

    /**
     * 课程处方周期
     */
    private Integer coursePeriod;

    /**
     * 推荐的课程时间单位分钟
     */
    private Integer courseTime;

    /**
     * 推荐的课程名卡路里
     */
    private Integer courseCalories;

    /**
     * 课程的路径
     */
    private String coursePath;

    /**
     * 是否是AI课程
     */
    private Integer isAi;

    /**
     * 课程详情
     */
    private String detail;

    private Date updateTime;

    private Date createTime;
}
