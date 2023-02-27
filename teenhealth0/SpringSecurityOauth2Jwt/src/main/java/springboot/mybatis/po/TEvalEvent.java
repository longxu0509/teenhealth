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
public class TEvalEvent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评估报告id
     */
    private Long id;

    /**
     * 整个评估是否完成状态
     */
    private Integer status;
    private Integer countdown;

    /**
     * 评估开始时间
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
