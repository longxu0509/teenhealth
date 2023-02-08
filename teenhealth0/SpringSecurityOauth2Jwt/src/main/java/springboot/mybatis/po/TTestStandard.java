package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-12-26
 */
@Data
public class TTestStandard implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 测试动作id
     */
    private Long testId;

    /**
     * 测试动作名
     */
    private String testName;

    /**
     * 0 女生 1 男 2 男女
     */
    private Integer gender;

    /**
     * 年龄段id
     */
    private Integer ageGroupId;

    /**
     * 上界
     */
    private Integer testResult1;

    /**
     * 下界
     */
    private Integer testResult2;

    /**
     * 得分
     */
    private Integer score;

    private Date updateTime;

    private Date createTime;
}
