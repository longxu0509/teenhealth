package springboot.mybatis.po;

import java.time.LocalDateTime;
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
 * @since 2022-11-14
 */
@Data
public class TStudentPftest implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long studentId;

    private String cardiorLevel;

    private Integer cardiorScore;

    private String corestrengthLevel;

    private Integer corestrengthScore;

    private String coordinateLevel;

    private Integer coordinateScore;

    @TableField("lowerMF_level")
    private String lowermfLevel;

    @TableField("lowerMF_score")
    private Integer lowermfScore;

    @TableField("upMF_level")
    private String upmfLevel;

    @TableField("upMF_score")
    private Integer upmfScore;

    private Date updateTime;
}
