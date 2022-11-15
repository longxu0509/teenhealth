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
public class TWqxplanRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生用户id
     */
    private Long studentId;

    /**
     * 无器械处方id
     */
    private Long wqxplanId;

    /**
     * 各项训练实际完成情况
     */
    @TableField("completionNum")
    private String completionNum;

    /**
     * 训练完成评分
     */
    private Integer score;

    private String trainCalorie;

    private Date createTime;

    private Date uploadTime;

    /**
     * 1：可用；0：不可用
     */
    private Integer isEnable;
}
