package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TUser;
import springboot.mybatis.mapper.TUserMapper;
import springboot.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser selectById(Long userID) {
        return tUserMapper.selectById(userID);
    }

    @Override
    public TUser selectByNo(String userNo) {
        return tUserMapper.selectByNo(userNo);
    }
}
