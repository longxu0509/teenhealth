package springboot.service;

import springboot.mybatis.po.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
public interface TUserService extends IService<TUser> {

    TUser selectById(Long userID);

    TUser selectByNo(String userNo);
}
