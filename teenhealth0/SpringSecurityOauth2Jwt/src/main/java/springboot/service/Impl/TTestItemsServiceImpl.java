package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TTestItems;
import springboot.mybatis.mapper.TTestItemsMapper;
import springboot.mybatis.po.TTestItemsCustom;
import springboot.service.TTestItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-17
 */
@Service
public class TTestItemsServiceImpl extends ServiceImpl<TTestItemsMapper, TTestItems> implements TTestItemsService {

    @Autowired
    private TTestItemsMapper tTestItemsMapper;

    @Override
    public List<TTestItems> findTestingVideo() {
        return tTestItemsMapper.findTestingVideo();
    }

    @Override
    public List<TTestItemsCustom> testItemstList() {
        return tTestItemsMapper.testItemstList();
    }

    @Override
    public int editTestItem(TTestItems testItems) {
        return tTestItemsMapper.editTestItem(testItems);
    }
}
