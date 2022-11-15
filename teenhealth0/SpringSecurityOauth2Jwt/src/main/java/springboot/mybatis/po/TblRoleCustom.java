package springboot.mybatis.po;

import lombok.Data;

@Data
public class TblRoleCustom extends TblRole {
    private TblUser tblUser;

    private TblRole tblRole;

}
