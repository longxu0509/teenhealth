package springboot.service;

import springboot.mybatis.po.TAction;
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
public interface TActionService extends IService<TAction> {

    TAction selectByCode(String actionCode);

    List<TAction> getAIList(Integer isAI);
}
