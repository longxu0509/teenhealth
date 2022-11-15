package springboot.service.Impl;

import springboot.mybatis.po.TblResource;
import springboot.mybatis.mapper.TblResourceMapper;
import springboot.service.TblResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-11-08
 */
@Service
public class TblResourceServiceImpl extends ServiceImpl<TblResourceMapper, TblResource> implements TblResourceService {

}
