package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-04-12
 */
@Data
public class TWqxplantrainContent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 训练处方id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方训练id
     */
    private Long planRecordId;

    /**
     * 训练视频的id
     */
    private Long trainingVideoId;

    /**
     * 训练排序
     */
    @TableField("indexNO")
    private String indexNO;

    /**
     * 训练名称
     */
    private String sort;

    /**
     * 训练数据保存地址
     */
    private String planTrainingPath;

    private String trainingCalorie;

    @TableField("completionNum")
    private Integer completionNum;

    private Integer trainingScore;

    private Date createTime;
}
