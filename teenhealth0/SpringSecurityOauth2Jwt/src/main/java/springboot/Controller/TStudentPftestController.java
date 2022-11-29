package springboot.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TStudentPftestCustom;
import springboot.mybatis.po.TWqxplanStudentCustom;
import springboot.service.TStudentPftestService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-11-14
 */
@RestController
@Slf4j
@RequestMapping("/tStudentPftest")
public class TStudentPftestController {
    @Autowired
    private TStudentPftestService tStudentPftestService;

    // 查询所有用户最近一条测试记录
    @RequestMapping("/getStudentPftestLatest")
    public CommonResult getStudentPftestLatest(@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TStudentPftestCustom> studentPftestLatest = tStudentPftestService.getStudentPftestLatest();
        PageInfo pageInfo1=new PageInfo(studentPftestLatest);
        return CommonResult.success(pageInfo1);
    }

}

