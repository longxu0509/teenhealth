package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.xml.internal.rngom.binary.DataExceptPattern;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author ${author}
 * @since 2022-11-10
 */
@Data
public class TStudent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String studentNo;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 2保密，1男，0女
     */
    private Integer sex;

    /**
     * 身高
     */
    private Double stature;

    /**
     * 体重
     */
    private Double weight;

    /**
     * BMI身体指数
     */
    private String bmi;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 病史
     */
    private String medical;

    /**
     * 个人用户手机号登录校验码
     */
    private Integer phoneNo;
}
