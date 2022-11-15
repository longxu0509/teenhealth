package springboot.mybatis.po;

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
public class TWqxplanNp implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String prescriptionName;

    /**
     * 训练处方简介
     */
    private String remark;

    private Date createTime;

    private Date updateTime;

    /**
     * 1：可用，0：不可用
     */
    private Integer isEnable;

}
