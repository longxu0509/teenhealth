package springboot.service;

import springboot.mybatis.po.TTestItems;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-17
 */
public interface TTestItemsService extends IService<TTestItems> {

    List<TTestItems> findTestingVideo();
}
