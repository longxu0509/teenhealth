package springboot.service;

import springboot.mybatis.po.TDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-16
 */
public interface TDetailService extends IService<TDetail> {

    List<TDetail> selectByCode(String courseCode);
}
