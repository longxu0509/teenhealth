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
public class TTestLowermf implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long testId;

    /**
     * 下肢肌肉适能
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

    /**
     * leve3-leve4:低于平均水平;<leve4:弱
     */
    private Integer score;

    /**
     * 简介
     */
    private String remark;

    private Date updateTime;

    private Date createTime;
}
