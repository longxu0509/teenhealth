package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TActionDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 动作库id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作编号
     */
    private String code;

    /**
     * 动作名称
     */
    private String actionName;

    /**
     * 动作类型
     */
    private String actionType;



    /**
     * 动作部位
     */
    private String actionPosition;

    /**
     * 动作视频路径
     */
    private String actionPath;

    /**
     * 动作缩略图
     */
    private String actionImg;

    /**
     * 1 计次  0
     */
    private Integer isCount;

    private Integer calories;

    private String duration;

    private Integer isAi;

    private Integer actionCnt;

    private String requirement;

    private String detail;
}

