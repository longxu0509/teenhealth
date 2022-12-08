package springboot.service;

import springboot.mybatis.po.TTestItems;
import com.baomidou.mybatisplus.extension.service.IService;
import springboot.mybatis.po.TTestItemsCustom;

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

    List<TTestItemsCustom> testItemstList();

    int editTestItem(TTestItems testItems);
}
