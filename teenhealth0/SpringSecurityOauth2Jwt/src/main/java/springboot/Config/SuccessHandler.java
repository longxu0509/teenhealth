package springboot.Config;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import springboot.mybatis.po.CommonResult;
import springboot.mybatis.po.TblResource;
import springboot.service.MyUserDetailsService;
import springboot.service.TblResourceService;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    TblResourceService tblResourceService;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String roleName=String.valueOf(authentication.getAuthorities());
        roleName=roleName.replace("[","").replace("]","");  //去除roleName [ROLE_ADMIN]的中括号
        //截取ROLE_ADMIN中_之后的字符串ADMIN
        roleName=roleName.substring(roleName.indexOf("_")+1,roleName.length());
        Long roleId=myUserDetailsService.selectRoleIdbyRoleName(roleName);   //根据角色名称查询角色id
        if (roleId==2){
            HashMap<Integer,ArrayList<TblResource>> ResourceMap1=new HashMap<>();
            List<Integer> classeslist=myUserDetailsService.selectClasses(roleId);   //获取roleId对应的所有角色type
            //根据classes将所有的resource进行分类
            for (int i=0;i<classeslist.size();i++){
                if (classeslist.get(i)>=0){
                    ArrayList<TblResource> list=myUserDetailsService.selectResourcebyClasses(classeslist.get(i));//根据type查询resource
                    ResourceMap1.put(classeslist.get(i),list);
                }
            }

            //将所有classes=3的resource写入classes=2对应的resource中
            for (int i=0;i<ResourceMap1.get(2).size();i++){
                ArrayList<TblResource> list23=new ArrayList<>();
                for (int j=0;j<ResourceMap1.get(3).size();j++){
                    if (ResourceMap1.get(3).get(j).getParentId()==ResourceMap1.get(2).get(i).getId()){
                        list23.add(ResourceMap1.get(3).get(j));
                    }
                }
                ResourceMap1.get(2).get(i).setChildren(list23);
            }

            //将所有classes=2的resource写入classes=1对应的resource中
            for (int i=0;i<ResourceMap1.get(1).size();i++){
                ArrayList<TblResource> list12=new ArrayList<>();
                for (int j=0;j<ResourceMap1.get(2).size();j++){
                    if (ResourceMap1.get(2).get(j).getParentId()==ResourceMap1.get(1).get(i).getId()){
                        list12.add(ResourceMap1.get(2).get(j));
                    }
                }
                ResourceMap1.get(1).get(i).setChildren(list12);
            }

            //将所有classes=1的resource写入classes=0对应的resource中

            for (int i=0;i<ResourceMap1.get(0).size();i++){
                ArrayList<TblResource> list01=new ArrayList<>();
                for (int j=0;j<ResourceMap1.get(1).size();j++){
                    if (ResourceMap1.get(1).get(j).getParentId()==ResourceMap1.get(0).get(i).getId()){
                        list01.add(ResourceMap1.get(1).get(j));
                    }
                }
                ResourceMap1.get(0).get(i).setChildren(list01);
            }

            String jsonArray=JSONArray.toJSONString(CommonResult.success(ResourceMap1.get(0)));
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter writer=httpServletResponse.getWriter();
            writer.write(jsonArray);
            writer.flush();
            writer.close();
        }else if (roleId==1){
            String jsonArray=JSONArray.toJSONString(CommonResult.success());
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter writer=httpServletResponse.getWriter();
            writer.write(jsonArray);
            writer.flush();
            writer.close();
        }

//        List<Long> resourceIdlist=myUserDetailsService.selectResourceIdbyRoleId(roleId);    //查询与role_id对应的resource_id
//        List<Long> resourceParentId=myUserDetailsService.selectresourceParentId();  //查询所有ParentId种类
//        List<TblResource> tblResourcesList=myUserDetailsService.selectResourcebyRoleId(roleId); //查询与role_id对应的resource集合

    }

    //找到所有parent_id=resourceParentId集合的TblResource
    public ArrayList<TblResource> Resourcelist(Long parentId,List<TblResource> tblResourceList){
        ArrayList<TblResource> list1=new ArrayList<>();
        for (int i=0;i<tblResourceList.size();i++){
            if (tblResourceList.get(i).getParentId()==parentId){
                list1.add(tblResourceList.get(i));
            }
        }
        return list1;
    }

}
