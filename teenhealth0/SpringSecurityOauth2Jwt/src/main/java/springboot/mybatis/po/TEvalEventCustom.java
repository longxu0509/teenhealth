package springboot.mybatis.po;

import io.swagger.models.auth.In;
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
public class TEvalEventCustom implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评估事件id
     */
    private Long id;

    /**
     * 整个评估是否完成状态
     */
    private Integer status;

    private Integer countdown;

    /**
     * 评估开始时间
     */
    private Date startTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer totalCalories;

    // 总时间 单位s
    private Integer totalTime;

    private List<TActionEvent> tActionEventList;
}
