package springboot.mybatis.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TEvalReportCustom implements Serializable {
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

    private List<TActionEvent> tActionEventList;

    private List<TCourseMeta> tCourseList;
}
