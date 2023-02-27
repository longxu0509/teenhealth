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
public class TUserEval implements Serializable {

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
     * 评估报告id  查询报告表加1
     */
    private Long reportId;

    /**
     * 评估事件id
     */
    private Long evalEventId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
