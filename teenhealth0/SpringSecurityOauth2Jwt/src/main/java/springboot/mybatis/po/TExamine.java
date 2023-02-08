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
 * @since 2023-01-04
 */
@Data
public class TExamine implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 自主考核成绩表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * bmi评级
     */
    private String bmiGrade;

    /**
     * 单杠 引体向上
     */
    private Integer pullUpCnt;

    /**
     * 单杠 引体向上得分
     */
    private Integer pullUpScore;

    /**
     * 评级
     */
    private String pullUpGrade;

    /**
     * 仰卧起做
     */
    private Integer sitUpsCnt;

    /**
     * 仰卧起做得分
     */
    private Integer sitUpsScore;

    private String sitUpsGrade;

    /**
     * 蛇形跑
     */
    private Integer snakeRunning;

    /**
     * 蛇形跑分数
     */
    private Integer snakeRuningScore;

    private String snakeRuningGrade;

    /**
     * 3000米
     */
    private Integer runing;

    /**
     * 3000米分数
     */
    private Integer runingScore;

    private String runingGrade;

    /**
     * 木马2
     */
    private String horseGrade;

    /**
     * 双杠
     */
    private String parallelBarsGrade;

    /**
     * 负重组合 单位秒
     */
    private Integer loadComb;

    /**
     * 负重组合 评级

     */
    private String loadGrade;

    /**
     * 总成绩
     */
    private Integer score;

    /**
     * 结果总评级
     */
    private String result;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
