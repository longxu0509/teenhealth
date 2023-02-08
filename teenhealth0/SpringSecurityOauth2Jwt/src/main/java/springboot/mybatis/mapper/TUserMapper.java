package springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.po.TStudent;
import springboot.mybatis.po.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

    TUser selectById(Long userId);

    TUser selectByNo(String userNo);
}
