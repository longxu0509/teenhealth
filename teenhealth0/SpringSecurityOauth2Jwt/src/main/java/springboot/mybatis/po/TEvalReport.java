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
public class TEvalReport implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评估报告id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评估总分
     */
    private Integer score;

    /**
     * 评估评级
     */
    private String grade;

    private String report;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
