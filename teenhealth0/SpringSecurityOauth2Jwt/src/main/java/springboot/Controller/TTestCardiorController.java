package springboot.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
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

    @PostMapping("/updateTestCardior/{id}")
    @ResponseBody
    public CommonResult updateTestCardior(@RequestBody TTestCardior tTestCardior, @PathVariable("id") long id) throws Exception{
        if(tTestCardiorService.updateTestCardior(tTestCardior, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestCorestrength/{id}")
    @ResponseBody
    public CommonResult updateTestCorestrength(@RequestBody TTestCorestrength tTestCorestrength, @PathVariable("id") long id) throws Exception{
        if(tTestCorestrengthService.updateTestCorestrength(tTestCorestrength, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestCoordinate/{id}")
    @ResponseBody
    public CommonResult updateTestCoordinate(@RequestBody TTestCoordinate tTestCoordinate, @PathVariable("id") long id) throws Exception{
        if(tTestCoordinateService.updateTestCoordinate(tTestCoordinate, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }


    @PostMapping("/updateTestUpmf/{id}")
    @ResponseBody
    public CommonResult updateTestUpmf(@RequestBody TTestUpmf tTestCardior, @PathVariable("id") long id) throws Exception{
        if(tTestUpmfService.updateTestUpmf(tTestCardior, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestLowermf/{id}")
    @ResponseBody
    public CommonResult updateTestLowermf(@RequestBody TTestLowermf tTestLowermf, @PathVariable("id") long id) throws Exception{
        if(tTestLowermfService.updateTestLowermf(tTestLowermf, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }



}

