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
        switch (id) {
            case 1: {
                List<TTestCardior> list =tTestCardiorService.testList();
                PageInfo pageInfo1=new PageInfo(list);
                return CommonResult.success(pageInfo1);
            }
            case 2: {
                List<TTestCorestrength> list =tTestCorestrengthService.testList();
                PageInfo pageInfo1=new PageInfo(list);
                return CommonResult.success(pageInfo1);
            }
            case 3: {
                List<TTestCoordinate> list =tTestCoordinateService.testList();
                PageInfo pageInfo1=new PageInfo(list);
                return CommonResult.success(pageInfo1);
            }
            case 4: {
                List<TTestUpmf> list =tTestUpmfService.testList();
                PageInfo pageInfo1=new PageInfo(list);
                return CommonResult.success(pageInfo1);
            }
            case 5: {
                List<TTestLowermf> list =tTestLowermfService.testList();
                PageInfo pageInfo1=new PageInfo(list);
                return CommonResult.success(pageInfo1);
            }
            default:
                break;
        }
        return CommonResult.fail();
    }

    @RequestMapping("/updateTestCardior/{id}")
    public CommonResult updateTestCardior(@RequestBody TTestCardior tTestCardior, @PathVariable("id") int id) throws Exception{
        int result = 0;
        switch (id) {
            case 1: {
                result = tTestCardiorService.updateTestCardior(tTestCardior);
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

