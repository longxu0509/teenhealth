package springboot.mybatis.po;

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
 * @since 2023-02-08
 */
@Data
public class TActionEvent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评估报告id
     */
    private Long id;

    private Long evalEventId;

    /**
     * 测试动作编码
     */
    private Integer actionId;


    /**
     * 测试动作名
     */
    private String actionName;


    /**
     * 动作完成个数或时间
     */
    private Double actionCnt;

    /**
     * 动作评级
     */
    private String actionGrade;

    /**
     * 动作得分
     */
    private Integer actionScore;

    /**
     * 动作是否完成测试
     */
    private Integer actionStatus;

    private String type;

    private String position;

    private Integer calories;

    private String advice;

    private String duration;

    private String img;

    private String unit;

    private Integer maxScore;

    /**
     * 测试该动作的开始时间
     */
    private Date startTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
