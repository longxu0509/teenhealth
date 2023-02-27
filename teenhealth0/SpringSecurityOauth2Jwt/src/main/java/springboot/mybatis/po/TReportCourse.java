package springboot.mybatis.po;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
@Data
public class TReportCourse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 报告id
     */
    private Long reportId;

    /**
     * 课程代码
     */
    private String courseCode;

    private Date updateTime;

    private Date createTime;

}
