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
 * @since 2022-11-14
 */
@Data
public class TTestCardior implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 心肺耐力
     */
    private Long id;

    /**
     * 测试视频id
     */
    private Long testId;

    /**
     * 测试项目
     */
    private String testName;

    /**
     * 优秀 良好 平均水平 低于平均水平 弱
     */
    private String level;

    /**
     * leve1-leve2:良好
     */
    private Integer testResult1;

    /**
     * leve2-leve3:平均水平
     */
    private Integer testResult2;

    private Integer score;

    /**
     * 简介
     */
    private String remark;

    private Date updateTime;

    private Date createTime;
}
