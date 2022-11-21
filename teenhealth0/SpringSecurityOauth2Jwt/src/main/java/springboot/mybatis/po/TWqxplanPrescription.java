package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-11-10
 */
@Data
public class TWqxplanPrescription implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方名称对应的id
     */
    @TableField("NP_id")
    private Long npId;

    private Long trainvideoId;

    /**
     * 训练名称（1：开合跳训练；2：双臂伸展运动；3：深蹲训练；4：跳绳训练；5：高抬腿训练）
     */
    private String sort;

    /**
     * 训练项目序号
     */
    @TableField("indexNO")
    private Long indexNO;

    /**
     * 训练时长（单位秒）
     */
    private Integer time;

    /**
     * 目标次数
     */
    private Integer target;

    /**
     * 休息时间
     */
    private Integer rest;

    private Date updateTime;

    private Date createTime;
}
