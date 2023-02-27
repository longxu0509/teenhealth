package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TCourseMetaCustom implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 课程id
     */
    private Long id;

    /**
     * 课程编码
     */
    private String code;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程类型
     */
    private String type;

    /**
     * 课程部位
     */
    private String position;

    /**
     * 课程难度等级
     */
    private Integer level;

    /**
     * 课程编号 A B C D E
     */
    private String number;

    /**
     * 课程缩略图
     */
    private String img;

    /**
     * 课程url路径
     */
    private String path;

    private Integer isAi;

    /**
     * 课程详情
     */
    private String detail;

    /**
     * 课程频率
     */
    private Integer freq;

    /**
     * 课程周期
     */
    private Integer period;

    /**
     * 课程时间单位分钟

     */
    private Integer time;

    /**
     * 推荐的课程名卡路里
     */
    private Integer calories;

    private List<TActionCustom> tActionCustomList;

    private Date updateTime;

    private Date createTime;
}
