package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @since 2022-11-08
 */
@Data
public class TblRoleResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long resourceId;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "TblRoleResource{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", resourceId=" + resourceId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
