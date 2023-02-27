package springboot.service;

import springboot.mybatis.po.TDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-15
 */
public interface TDictService extends IService<TDict> {

    String getVal(String key);
}
