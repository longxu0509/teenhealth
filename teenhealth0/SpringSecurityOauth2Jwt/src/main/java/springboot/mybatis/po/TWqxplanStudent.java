package springboot.mybatis.po;

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
public class TWqxplanStudent implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 用户id
     */
    private Long studentId;

    /**
     * 训练处方id
     */
    @TableField("NP_id")
    private Long npId;

    /**
     * 训练周期-单位：天
     */
    private Integer trainPeriod;

    /**
     * 训练频率-单位：天/次
     */
    private Integer trainFrequency;

    /**
     * 1：可用；0：不可用
     */
    private Integer isEnable;

    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
