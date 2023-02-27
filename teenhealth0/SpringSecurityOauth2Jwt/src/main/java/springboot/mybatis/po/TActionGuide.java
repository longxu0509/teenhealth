package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Data
public class TActionGuide implements Serializable {

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
}
