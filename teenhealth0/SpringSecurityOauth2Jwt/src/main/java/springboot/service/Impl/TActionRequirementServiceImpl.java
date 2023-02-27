package springboot.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.mybatis.po.TActionRequirement;
import springboot.mybatis.mapper.TActionRequirementMapper;
import springboot.service.TActionRequirementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-02-24
 */
@Service
public class TActionRequirementServiceImpl extends ServiceImpl<TActionRequirementMapper, TActionRequirement> implements TActionRequirementService {

    @Autowired
    private TActionRequirementMapper tActionRequirementMapper;

    @Override
    public TActionRequirement getByCode(String code) {
        return tActionRequirementMapper.getByCode(code);
    }
}
