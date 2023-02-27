package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TActionGuideCustom implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 动作指导表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作id
     */
    private Integer nextActionId;

    /**
     * 时长
     */
    private String time;

    /**
     * 设备要求
     */
    private String requirement;

    /**
     * 呼吸
     */
    private String respiration;

    // 当前动作
    private TActionEvent currentAction;

    private TActionEvent tActionEvent;
}
