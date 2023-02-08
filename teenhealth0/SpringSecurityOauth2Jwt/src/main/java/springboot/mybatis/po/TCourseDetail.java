package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-01-12
 */
@Data
public class TCourseDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 当前视频id
     */
    private Long videoId;

    /**
     * 视频动作名
     */
    private String actionName;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 动作描述
     */
    private Integer actionCnt;

    /**
     * 动作训练部位
     */
    private String position;

    /**
     * 训练项目序号
     */
    @TableField("indexNO")
    private Integer indexNO;
}
