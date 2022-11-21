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
 * @since 2022-11-17
 */
@Data
public class TTestItems implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 测试项目名称
     */
    private String testName;

    /**
     * 测试视频id
     */
    private Long videoId;

    /**
     * 简介
     */
    private String remark;

    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
