package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
public class TActionRequirement implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 动作指导表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 动作id
     */
    private String code;

    /**
     * 设备要求
     */
    private String requirement;

    /**
     * 说明
     */
    private String detail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "TActionRequirement{" +
        "id=" + id +
        ", code=" + code +
        ", requirement=" + requirement +
        ", detail=" + detail +
        "}";
    }
}
