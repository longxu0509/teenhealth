package springboot.mybatis.po;

import lombok.Data;

import java.util.Date;

@Data
public class TblUser {

    private Integer id;

    private String loginName;

    private String password;

    private String phone;

    private String realName;

    private Integer sex;

    private String headPortrait;

    private Date createTime;

    private Date updateTime;

    private Integer isValid;

    private String remark;


}