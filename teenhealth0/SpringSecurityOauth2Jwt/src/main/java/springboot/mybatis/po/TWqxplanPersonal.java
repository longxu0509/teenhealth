package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王君洪
 * @since 2022-03-14
 */
@Data
public class TWqxplanPersonal implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生用户id
     */
    private String studentId;

    /**
     * 处方id
     */
    private Long planId;

    /**
     * 排序
     */
    private String indexNO;

    /**
     * 训练名称
     */
    private String sort;

    /**
     * 训练视频id
     */
    private Long trainvideoId;

    /**
     * 训练时间
     */
    private Integer time;

    /**
     * 目标次数
     */
    private Integer target;

    /**
     * 休息时间
     */
    private Integer rest;

}
