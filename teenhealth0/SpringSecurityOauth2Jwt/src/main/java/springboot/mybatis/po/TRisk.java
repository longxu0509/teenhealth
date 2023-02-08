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
 * @since 2022-12-29
 */
@Data
public class TRisk implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 运动风险评估成绩表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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
     * 俯卧撑
     */
    private Integer pushUpCnt;

    /**
     * 俯卧撑得分
     */
    private Integer pushUpScore;

    private String pushUpGrade;

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
     * 坐位体前屈
     */
    private Integer sitForward;

    /**
     * 坐位体前屈 得分
     */
    private Integer sitForwardScore;

    private String sitForwardGrade;

    /**
     * 闭眼单腿站立 单位秒
     */
    private Integer eyesCloseStand;

    /**
     * 闭眼单腿站立 得分
     */
    private Integer eyesCloseStandScore;

    private String eyesCloseStandGrade;

    /**
     * 纵跳 单位cm
     */
    private Integer jump;

    /**
     * 纵跳 得分
     */
    private Integer jumpScore;

    private String jumpGrade;

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
     * 总成绩
     */
    private Integer score;

    /**
     * 结果等级
     */
    private String result;

    /**
     * 风险评估报告
     */
    private String report;

    /**
     * 推荐的课程名id
     */
    private Integer course1Id;

    /**
     * 推荐的课程名id
     */
    private Integer course2Id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
