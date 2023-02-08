package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Data
public class TUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用于存放头像的路径
     */
    private String imgPath;

    /**
     * 用户账号
     */
    private String userNo;
    private String userPassword;


    /**
     * 姓名
     */
    private String userName;

    /**
     * 军种
     */
    private String services;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别 2保密，1男，0女
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 类别名
     */
    private String typeName;

    /**
     * 类别
     */
    private String type;

    /**
     * 身高
     */
    private Double stature;

    /**
     * 体重
     */
    private Double weight;

    /**
     * bmi
     */
    private Double bmi;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
