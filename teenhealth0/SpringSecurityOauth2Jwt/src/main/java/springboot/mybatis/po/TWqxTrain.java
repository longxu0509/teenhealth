package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-11-10
 */
@Data
public class TWqxTrain implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 训练文件保存地址
     */
    private String trainingPath;

    /**
     * 用户id
     */
    private Long studentId;

    /**
     * 训练视频id
     */
    private Long trainingVideoId;

    /**
     * 消耗卡路里
     */
    private String trainingCalorie;

    /**
     * 训练得分
     */
    private Integer trainingScore;

    private Date uploadTime;

    private Date createTime;
}
