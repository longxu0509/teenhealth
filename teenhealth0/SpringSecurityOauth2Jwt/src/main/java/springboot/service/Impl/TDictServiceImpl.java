package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TDict;
import springboot.mybatis.mapper.TDictMapper;
import springboot.service.TDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-15
 */
@Service
public class TDictServiceImpl extends ServiceImpl<TDictMapper, TDict> implements TDictService {

    @Autowired
    private TDictMapper tDictMapper;

    @Override
    public String getVal(String countdown) {
        return tDictMapper.getVal(countdown);
    }
}
