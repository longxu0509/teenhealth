package springboot.service;

import springboot.mybatis.po.TRisk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-12-26
 */
public interface TRiskService extends IService<TRisk> {

    TRisk findByUserId(Long userID);

    void addTestItem(TRisk tRisk);

    void insertTestRecord(TRisk tRisk);

    List<TRisk> getRiskList(Long userID);
}
