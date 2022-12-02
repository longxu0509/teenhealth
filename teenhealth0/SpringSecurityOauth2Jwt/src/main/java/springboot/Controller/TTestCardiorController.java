package springboot.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springboot.mybatis.po.*;
import springboot.service.*;

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
@RequestMapping("/tTest")
public class TTestCardiorController {

    @Autowired
    private TTestCardiorService tTestCardiorService;

    @Autowired
    private TTestCorestrengthService tTestCorestrengthService;

    @Autowired
    private TTestCoordinateService tTestCoordinateService;

    @Autowired
    private TTestUpmfService tTestUpmfService;

    @Autowired
    private TTestLowermfService tTestLowermfService;

    //查询所有测试标准
    @RequestMapping("/testList/{id}")
    public CommonResult testList(@PathVariable("id") int id, @RequestBody PageInfo pageInfo) throws Exception{
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        PageInfo pageInfo1=new PageInfo();
        switch (id) {
            case 1: {
                List<TTestCardior> list =tTestCardiorService.testList();
                pageInfo1.setList(list);
            } break;
            case 2: {
                List<TTestCorestrength> list =tTestCorestrengthService.testList();
                pageInfo1.setList(list);
            } break;
            case 3: {
                List<TTestCoordinate> list =tTestCoordinateService.testList();
                pageInfo1.setList(list);
            } break;
            case 4: {
                List<TTestUpmf> list =tTestUpmfService.testList();
                pageInfo1.setList(list);
            } break;
            case 5: {
                List<TTestLowermf> list =tTestLowermfService.testList();
                pageInfo1.setList(list);
            } break;
            default:
                break;
        }
        return CommonResult.success(pageInfo1);
    }

    @RequestMapping("/updateTestCriteria/{id}")
    public CommonResult updateTestCriteria(@RequestBody Object obj, @PathVariable("id") int id) throws Exception{
        int result = 0;
        switch (id) {
            case 1: {
                TTestCardior tTestCardior = (TTestCardior) obj;
                result = tTestCardiorService.updateTestCriteria(tTestCardior);
            } break;
            case 2: {
//                result=tTestCorestrengthService.updateTestCriteria();
            } break;
            case 3: {
//                result =tTestCoordinateService.updateTestCriteria();
            } break;
            case 4: {
//                result =tTestUpmfService.updateTestCriteria();
            } break;
            case 5: {
//                result =tTestLowermfService.updateTestCriteria();
            } break;
            default:
                break;
        }
        if (result == 1) {
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

}

