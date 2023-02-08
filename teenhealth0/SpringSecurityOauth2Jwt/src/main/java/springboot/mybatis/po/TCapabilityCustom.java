package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TCapabilityCustom {
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
     * 仰卧起做
     */
    private Integer sitUpsCnt;

    /**
     * 仰卧起做得分
     */
    private Integer sitUpsScore;

    private String sitUpsGrade;

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
     * 3000米
     */
    private Integer runing;

    /**
     * 3000米分数
     */
    private Integer runingScore;

    private String runingGrade;

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
     * 坐位体前屈
     */
    private Integer sitForward;

    /**
     * 坐位体前屈 得分
     */
    private Integer sitForwardScore;

    private String sitForwardGrade;

    /**
     * 总成绩
     */
    private Integer score;

    /**
     * 结果总评级
     */
    private String result;

    /**
     * 结果报告，以逗号分割开
     */
    private String report;

    private List<TCourseInfo> courseList;

    private Date createTime;

    private Date updateTime;
}
