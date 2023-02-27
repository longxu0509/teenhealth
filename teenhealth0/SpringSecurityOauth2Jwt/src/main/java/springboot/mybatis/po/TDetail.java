package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
public class TDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 课程详情表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程代码
     */
    private String courseCode;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 动作编号
     */
    private String actionCode;

    /**
     * 动作个数
     */
    private Integer count;

    /**
     * 动作做几组
     */
    private Integer times;

    /**
     * 组间歇 单位s
     */
    private Integer gap;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    @Override
    public String toString() {
        return "TDetail{" +
        "id=" + id +
        ", courseCode=" + courseCode +
        ", courseName=" + courseName +
        ", actionCode=" + actionCode +
        ", count=" + count +
        ", times=" + times +
        ", gap=" + gap +
        "}";
    }
}
