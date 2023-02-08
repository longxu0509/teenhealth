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
 * @since 2022-12-22
 */
@Data
public class TGuideVideos implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作库类型id(热身及拉伸：1， 力量训练：2， 增强式训练：3， 放松拉伸：4
     */
    private Integer actionTypeId;

    /**
     * 动作库细分（核心力量：1， 上肢力量：2，下肢力量: 3
     */
    private Integer positionId;

    /**
     * （课程，动作，教学视频）名称
     */
    private String sort;

    private String fileName;

    private String savePath;

    /**
     * 详情
     */
    private String detail;

    private Date updateTime;

    private Date createTime;
}
