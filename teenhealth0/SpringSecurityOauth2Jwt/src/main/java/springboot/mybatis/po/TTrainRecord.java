package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
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
 * @since 2023-02-24
 */
@Data
public class TTrainRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 训练记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 动作ID编码
     */
    private Long actionId;

    private String actionName;

    /**
     * 训练得分
     */
    private Integer actionScore;

    /**
     * 动作完成个数或时间
     */
    private Integer actionCnt;

    /**
     * 动作评级
     */
    private String actionGrade;


    /**
     * 消耗卡路里
     */
    private Integer calories;

    /**
     * 持续时间
     */
    private String duration;


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
