package springboot.mybatis.po;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;

    /**
     * 1：可用，0：不可用
     */
    private Integer isEnable;
}
