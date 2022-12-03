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
        // 校验是否合规  左曲间必须大于下一等级的右区间
        Integer preRes1 = Integer.MAX_VALUE;
        Integer postRes2 = -1;

        if (id-1 >= 1) {
            TTestCardior  tTestCardior1 = tTestCardiorService.getTestById(id-1);
            preRes1 = tTestCardior1.getTestResult1();
        }
        if (id+1 <= 5) {
            TTestCardior tTestCardior2 = tTestCardiorService.getTestById(id+1);
            postRes2 = tTestCardior2.getTestResult2();
        }
        Integer curRes1 = tTestCardior.getTestResult1();
        Integer curRes2 = tTestCardior.getTestResult2();
        if (curRes1 > curRes2)
            return CommonResult.fail("当前等级左区间应小于等于右区间");

        if (curRes1 <= postRes2)
            return CommonResult.fail("当前等级左区间应大于后一等级右区间");

        if (curRes2 >= preRes1)
            return CommonResult.fail("当前等级右区间应小于前一等级左区间");

        if(tTestCardiorService.updateTestCardior(tTestCardior, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestCorestrength/{id}")
    @ResponseBody
    public CommonResult updateTestCorestrength(@RequestBody TTestCorestrength tTestCorestrength, @PathVariable("id") long id) throws Exception{
        Integer preRes1 = Integer.MAX_VALUE;
        Integer postRes2 = -1;

        if (id-1 >= 1) {
            TTestCorestrength tTestCorestrength1 = tTestCorestrengthService.getTestById(id-1);
            preRes1 = tTestCorestrength1.getTestResult1();
        }
        if (id+1 <= 5) {
            TTestCorestrength tTestCorestrength2 = tTestCorestrengthService.getTestById(id+1);
            postRes2 = tTestCorestrength2.getTestResult2();
        }
        Integer curRes1 = tTestCorestrength.getTestResult1();
        Integer curRes2 = tTestCorestrength.getTestResult2();
        if (curRes1 > curRes2)
            return CommonResult.fail("当前等级左区间应小于等于右区间");

        if (curRes1 <= postRes2)
            return CommonResult.fail("当前等级左区间应大于后一等级右区间");

        if (curRes2 >= preRes1)
            return CommonResult.fail("当前等级右区间应小于前一等级左区间");

        if(tTestCorestrengthService.updateTestCorestrength(tTestCorestrength, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestCoordinate/{id}")
    @ResponseBody
    public CommonResult updateTestCoordinate(@RequestBody TTestCoordinate tTestCoordinate, @PathVariable("id") long id) throws Exception{
        Integer preRes1 = Integer.MAX_VALUE;
        Integer postRes2 = -1;

        if (id-1 >= 1) {
            TTestCoordinate tTestCoordinate1 = tTestCoordinateService.getTestById(id-1);
            preRes1 = tTestCoordinate1.getTestResult1();
        }
        if (id+1 <= 5) {
            TTestCoordinate tTestCoordinate2 = tTestCoordinateService.getTestById(id+1);
            postRes2 = tTestCoordinate2.getTestResult2();
        }
        Integer curRes1 = tTestCoordinate.getTestResult1();
        Integer curRes2 = tTestCoordinate.getTestResult2();
        if (curRes1 > curRes2)
            return CommonResult.fail("当前等级左区间应小于等于右区间");

        if (curRes1 <= postRes2)
            return CommonResult.fail("当前等级左区间应大于后一等级右区间");

        if (curRes2 >= preRes1)
            return CommonResult.fail("当前等级右区间应小于前一等级左区间");

        if(tTestCoordinateService.updateTestCoordinate(tTestCoordinate, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }


    @PostMapping("/updateTestUpmf/{id}")
    @ResponseBody
    public CommonResult updateTestUpmf(@RequestBody TTestUpmf tTestUpmf, @PathVariable("id") long id) throws Exception{
        Integer preRes1 = Integer.MAX_VALUE;
        Integer postRes2 = -1;

        if (id-1 >= 1) {
            TTestUpmf tTestUpmf1 = tTestUpmfService.getTestById(id-1);
            preRes1 = tTestUpmf1.getTestResult1();
        }
        if (id+1 <= 5) {
            TTestUpmf tTestUpmf2 = tTestUpmfService.getTestById(id+1);
            postRes2 = tTestUpmf2.getTestResult2();
        }
        Integer curRes1 = tTestUpmf.getTestResult1();
        Integer curRes2 = tTestUpmf.getTestResult2();
        if (curRes1 > curRes2)
            return CommonResult.fail("当前等级左区间应小于等于右区间");

        if (curRes1 <= postRes2)
            return CommonResult.fail("当前等级左区间应大于后一等级右区间");

        if (curRes2 >= preRes1)
            return CommonResult.fail("当前等级右区间应小于前一等级左区间");

        if(tTestUpmfService.updateTestUpmf(tTestUpmf, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    @PostMapping("/updateTestLowermf/{id}")
    @ResponseBody
    public CommonResult updateTestLowermf(@RequestBody TTestLowermf tTestLowermf, @PathVariable("id") long id) throws Exception{
        Integer preRes1 = Integer.MAX_VALUE;
        Integer postRes2 = -1;

        if (id-1 >= 1) {
            TTestLowermf testLowermf1 = tTestLowermfService.getTestById(id-1);
            preRes1 = testLowermf1.getTestResult1();
        }
        if (id+1 <= 5) {
            TTestLowermf testLowermf2 = tTestLowermfService.getTestById(id+1);
            postRes2 = testLowermf2.getTestResult2();
        }
        Integer curRes1 = tTestLowermf.getTestResult1();
        Integer curRes2 = tTestLowermf.getTestResult2();
        if (curRes1 > curRes2)
            return CommonResult.fail("当前等级左区间应小于等于右区间");

        if (curRes1 <= postRes2)
            return CommonResult.fail("当前等级左区间应大于后一等级右区间");

        if (curRes2 >= preRes1)
            return CommonResult.fail("当前等级右区间应小于前一等级左区间");
        if(tTestLowermfService.updateTestLowermf(tTestLowermf, id)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }
}

