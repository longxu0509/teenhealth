package springboot.mybatis.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备表
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@Data
public class TEquipment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 设备类型id
     */
    private String typeId;

    /**
     * 设备编号
     */
    private String equipmentNo;

    /**
     * 状态:1正常，2：故障
     */
    private Integer status;

    /**
     * 设备安置位置
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createTime;
}
