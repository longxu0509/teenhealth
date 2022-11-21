package springboot.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.*;
import springboot.service.*;
import springboot.utils.RedisUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

//无器械训练计划管理
@RestController
@Slf4j
@RequestMapping("/teacher")
public class TWqxplanController {
    @Autowired
    private TWqxplanRecordService tWqxplanRecordService;

    @Autowired
    private TWqxplanNpService tWqxplanNpService;

    @Autowired
    private TWqxplanPersonalService tWqxplanPersonalService;

    @Autowired
    private TWqxplanPrescriptionService tWqxplanPrescriptionService;

    @Autowired
    RedisUtil redisUtil;

    //查询所有无器械处方
    @RequestMapping("/WQXPlanList")
    public CommonResult WQXPlanList(@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TWqxplanNp> list= tWqxplanNpService.ListTWQXPlan();
        PageInfo pageInfo1=new PageInfo(list);
        return CommonResult.success(pageInfo1);
    }

//    //根据studentId查询学生无器械历史处方
//    @RequestMapping("/WQXHistoryPlan/{id}")
//    public CommonResult WQXHistoryPlan(@PathVariable("id")Long studentid,@RequestBody PageInfo pageInfo){
//        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
//        List<TWQXPlanListCustom> WQXHistoryPlan= tTWuqixiePlanService.OneListTWQXPlan(studentid);
//        PageInfo pageInfo1=new PageInfo(WQXHistoryPlan);
//        return CommonResult.success(pageInfo1);
//    }

    //根据处方id查询xx学生无器械处方训练记录
    @RequestMapping("/WQXTrainingRecord/{id}")
    public CommonResult WQXTrainingRecord(@PathVariable("id")Long id,@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TWqxplanRecordCustom> list=tWqxplanRecordService.selectTrainRecord(id);
        PageInfo pageInfo1=new PageInfo(list);
        return CommonResult.success(pageInfo1);
    }

    //删除处方
    @RequestMapping("/deleteWQXTrainPlan/{planId}")
    public CommonResult deleteWQXplan(@PathVariable("planId") Long planId){
        if (tWqxplanNpService.deleteWQXplan(planId)){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //修改训练处方内容
    @PostMapping("/editWQXTrainPlan/{id}")
    public CommonResult editPlanContent(@RequestBody TWqxplanNp TWqxplanNp, @PathVariable("id")Long id){
        TWqxplanNp.setId(id);
        if (tWqxplanNpService.updatePlan(TWqxplanNp)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //新增无器械处方
    @PostMapping("/addWQXplan")
    public CommonResult addWQXplan(@RequestBody TWqxplanNp TWqxplanNp){
            TWqxplanNp.setCreateTime(new Date());
            if (tWqxplanNpService.insertWQXplan(TWqxplanNp)==1){
                return CommonResult.success();
            }else {
                return CommonResult.fail();
            }
    }

    //根据PlanId查询处方内容
    @RequestMapping("/WQXPlanDetail/{id}")
    public CommonResult WQXPersonalPlan(@PathVariable("id")Long planId,@RequestBody PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<TWqxplanPrescription> tWqxplanPrescriptions = tWqxplanPrescriptionService.PlanDetail(planId);
        PageInfo pageInfo1=new PageInfo(tWqxplanPrescriptions);
        return CommonResult.success(pageInfo1);
    }

    // 修改训练处方内容
    @PostMapping("/editPlanContent/{id}")
    public CommonResult editPlanContent(@RequestBody TWqxplanPrescription tWqxplanPrescription,@PathVariable("id")Long id){
        tWqxplanPrescription.setId(id);
        if(tWqxplanPrescriptionService.updatePlanContent(tWqxplanPrescription)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    // 新增训练处方内容
    @PostMapping("/addPlanContent")
    public CommonResult addPlanContent(@RequestBody TWqxplanPrescription tWqxplanPrescription){
        tWqxplanPrescription.setCreateTime(new Date());
        if(tWqxplanPrescriptionService.insertPlanContent(tWqxplanPrescription)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    // 新增训练处方内容
    @PostMapping("/insertPlanContent")
    public CommonResult insertPlanContent(@RequestBody TWqxplanPrescription tWqxplanPrescription){
        tWqxplanPrescription.setCreateTime(new Date());
        Long indexNO = tWqxplanPrescription.getIndexNO();
        Long npId = tWqxplanPrescription.getNpId();
        // 查询是否存在>=indexNO 则不是在最后一条记录后插入 需要更新后面的indexNo
        if(tWqxplanPrescriptionService.findPlanContByIndexNO(npId, indexNO) != null) {
            //如果存在后面 >= indexNo的记录 indexNO+1
            tWqxplanPrescriptionService.updateIndexNO(npId, indexNO);
        }

        if(tWqxplanPrescriptionService.insertPlanContent(tWqxplanPrescription)==1){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    // 根据id查询训练处方动作
    @RequestMapping("/findPlanContentById/{id}")
    public CommonResult findPlanContentById(@PathVariable("id")long id){
        TWqxplanPrescription tWqxplanPrescription = tWqxplanPrescriptionService.findPlanContentById(id);
        if (tWqxplanPrescription != null){
            return CommonResult.success(tWqxplanPrescription);
        }else {
            return CommonResult.fail();
        }
    }

    //删除训练处方内容
    @RequestMapping("/deletePlanContent/{id}")
    public CommonResult deletePlanContent(@PathVariable("id")long id){
        TWqxplanPrescription tWqxplanPrescription = tWqxplanPrescriptionService.findPlanContentById(id);
        Long indexNO = tWqxplanPrescription.getIndexNO();
        Long npId = tWqxplanPrescription.getNpId();
        if (tWqxplanPrescriptionService.deletePlanContent(id)==1){
            // 如果存在记录indexNo >= 删除记录的indexNo
            if(tWqxplanPrescriptionService.findPlanContByIndexNO(npId, indexNO) != null) {
                //如果存在后面 >= indexNo的记录 indexNO-1
                tWqxplanPrescriptionService.subIndexNO(npId, indexNO);
            }
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }


//    Redis
    //添加处方内容页面（从查询redis中查询）
    @RequestMapping("/getWQXPlan/{id}")
    public CommonResult getWQXPlan(@PathVariable("id") String id){
        return CommonResult.success(redisUtil.hmget(id));
    }

    //处方内容从前端添加到redis
    @RequestMapping("/saveWQXPlan")
    public CommonResult saveWQXPlan(@RequestBody TWqxplanPersonal tWqxplanPersonal) throws Exception{
        String key=String.valueOf(tWqxplanPersonal.getPlanId());    //redis的Key设置为PlanId
        if (!redisUtil.hHasKey(key,tWqxplanPersonal.getIndexNO())){    //判断该条处方是否存在
            redisUtil.hset(key,tWqxplanPersonal.getIndexNO(),tWqxplanPersonal);   //插入
            redisUtil.expire(key,300);  //redis有效期为5分钟
            return CommonResult.success(redisUtil.hmget(key));
        }else { //已经存在
            log.info(tWqxplanPersonal.getPlanId()+"已经在redis中存在");
            return CommonResult.fail(redisUtil.hmget(key));
        }
    }

    //替换redis中的一条处方
    @RequestMapping("/updateWQXplan")
    public CommonResult updateWQXplan(@RequestBody TWqxplanPersonal tWqxplanPersonal){
        String key=String.valueOf(tWqxplanPersonal.getPlanId());    //redis的Key设置为PlanId
        if(redisUtil.hasKey(key)){
            redisUtil.hset(key,tWqxplanPersonal.getIndexNO(),tWqxplanPersonal);
            return CommonResult.success(redisUtil.hmget(key));
        }else {
            return CommonResult.fail("处方不存在");
        }
    }

    //在redisc处方中间插入一条处方，并把后面的序号+1
    @RequestMapping("/insertWQXplan")
    public CommonResult insertWQXplan(@RequestBody TWqxplanPersonal tWqxplanPersonal){
        String key=String.valueOf(tWqxplanPersonal.getPlanId());    //redis的Key设置为PlanId
        if(redisUtil.hasKey(key)){
            Map<Object,Object> map=redisUtil.hmget(key);    //map取出redis所有的处方
            Set keySet = map.keySet();  //获取index集合对象
            List<Integer> list=new ArrayList<>();
            for (Object i :keySet){ //将set集合中所有index转为integer
                list.add(Integer.parseInt(i.toString()));
            }
            ObjectMapper objectMapper=new ObjectMapper();   //object转实体类
            for (int a=0;a<list.size();a++){
                if (Integer.valueOf(tWqxplanPersonal.getIndexNO())<=list.get(a)){
                    String b= String.valueOf(1+list.get(a));
                    //object转TWqxplanPersonal实体类
                    TWqxplanPersonal tWqxplanPersonal1=objectMapper.convertValue(map.get(String.valueOf(list.get(a))),TWqxplanPersonal.class);
                    tWqxplanPersonal1.setIndexNO(b);
                    redisUtil.hset(key,b,tWqxplanPersonal1);   //插入
                }
            }
            redisUtil.hset(key,tWqxplanPersonal.getIndexNO(),tWqxplanPersonal);   //插入
            return CommonResult.success(redisUtil.hmget(key));
        }else {
            return CommonResult.fail("处方不存在");
        }

    }

    //前端删除redis中处方的某一条
    @RequestMapping("/deleteWQXPlan")
    public CommonResult deleteWQXPlan(@RequestBody Map<String,String> map){
        String key=map.get("planId");
        String indexNO=map.get("indexNO");
        if (redisUtil.hHasKey(key,indexNO)){
            redisUtil.hdel(key,indexNO);  //删除表中index对应的值
            Map<Object,Object> map1=redisUtil.hmget(key);    //map取出redis所有的处方
            // 将序号大于index的全部做index-1处理
            ObjectMapper objectMapper=new ObjectMapper();   //object转实体类
            Map<String,Object> map2=new HashMap<>();
            Integer j=0;    //新redis对应的key
            for (int i=1;i<=map1.size()+1;i++){
                //object转TWqxplanPersonal实体类
                TWqxplanPersonal tWqxplanPersonal=objectMapper.convertValue(map1.get(String.valueOf(i)),TWqxplanPersonal.class);//object转实体类
                if(null!=tWqxplanPersonal){
                    Integer a=Integer.valueOf(tWqxplanPersonal.getIndexNO());
                    j=j+1;
                    if (a>Integer.valueOf(indexNO)){
                        tWqxplanPersonal.setIndexNO(String.valueOf(a-1));
                        map2.put( String.valueOf(j),tWqxplanPersonal);
                    }else {
                        map2.put( String.valueOf(j),tWqxplanPersonal);
                    }
                }
            }
            redisUtil.del(key); //删除原处方
            redisUtil.hmset(key,map2);   //插入新处方
            return CommonResult.success(redisUtil.hmget(key));
        }else {
            return CommonResult.fail("该条记录不存在");
        }
    }

    //根据planId删除处方表
    @RequestMapping("/deleteWQXPlan/{planId}")
    public CommonResult deleteWQXPlanbyplanId(@PathVariable("planId") String planId){
        redisUtil.del(planId);
        if (!redisUtil.hasKey(planId)){
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }

    //提交无器械处方
    @RequestMapping("/submitWQXPlan/{id}")
    public CommonResult submitWQXPlan(@PathVariable("id") String id){
        Map map=redisUtil.hmget(id);
        List<TWqxplanPersonal> list=new ArrayList<>();
        for (int i=1;i<=map.size();i++){
            String j=String.valueOf(i);
            ObjectMapper objectMapper=new ObjectMapper();   //object转TWqxplanPersonal实体类
            list.add(objectMapper.convertValue(redisUtil.hget(id,j),TWqxplanPersonal.class));
        }
        if (tWqxplanPersonalService.insertPlanContent(list)==list.size()){    //写入数据库
            redisUtil.del(id);  //删除redis中的处方
            return CommonResult.success();
        }else {
            return CommonResult.fail();
        }
    }
}

