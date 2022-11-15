package springboot.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单
 * </p>
 * @since 2022-11-08
 */
@Data
public class TblResource implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 显示名称
     */
    private String title;

    /**
     * 权限代码
     */
    private String permission;

    /**
     * 前端路径
     */
    private String path;

    /**
     * 前端组件路径
     */
    private String component;

    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 级别
     */
    private Integer classes;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 逻辑删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Object children;

    @Override
    public String toString() {
        return "TblResource{" +
        "id=" + id +
        ", name=" + name +
        ", title=" + title +
        ", permission=" + permission +
        ", path=" + path +
        ", component=" + component +
        ", icon=" + icon +
        ", sort=" + sort +
        ", type=" + type +
        ", parentId=" + parentId +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
