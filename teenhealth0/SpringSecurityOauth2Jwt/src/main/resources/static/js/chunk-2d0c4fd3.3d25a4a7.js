(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c4fd3"],{"3cd1":function(e,t,l){"use strict";l.r(t),l.d(t,"crudOptions",(function(){return o}));var a=l("6f23"),o=(l("22ce"),{options:{height:"100%"},viewOptions:{disabled:!1},pageOptions:{export:{local:!0,title:"无器械历史处方数据",formatter:function(e,t){e["createTime"]=Object(a["a"])(e.createTime,"yyyy-MM-DD HH:mm:ss"),0===e.isEnable&&(e["isEnable"]="已失效"),1===e.isEnable&&(e["isEnable"]="可用")}}},rowHandle:{width:470,view:{show:!1},edit:{show:!1},remove:{},custom:[{text:"新增处方训练项目",type:"warning",size:"small",emit:"customHandleBtn",icon:"el-icon-plus"},{thin:!0,text:"查看处方内容",type:"primary",size:"small",emit:"AddPlan",icon:"el-icon-search"},{thin:!0,text:"训练记录",type:"success",size:"small",emit:"plantraining",icon:"el-icon-bicycle"}]},columns:[{title:"学号",key:"tStudent.studentNo",sortable:!0,form:{disabled:!0}},{title:"姓名",key:"tStudent.studentName",sortable:!1,addForm:{component:{disabled:!0}},editForm:{component:{disabled:!0}}},{title:"性别",key:"tStudent.sex",sortable:!1,type:"select",dict:{data:[{value:0,label:"未知"},{value:1,label:"男"},{value:2,label:"女"}]},form:{component:{show:!1}}},{title:"planId",key:"planId",show:!1,form:{disabled:!0}},{title:"难度等级",key:"difficultyLevel",sortable:!0,type:"select",dict:{data:[{value:1,label:"1",color:"primary"},{value:2,label:"2",color:"primary"},{value:3,label:"3",color:"primary"},{value:4,label:"4",color:"primary"},{value:5,label:"5",color:"primary"},{value:6,label:"6",color:"primary"}]}},{title:"处方当前状态",key:"isEnable",sortable:!1,type:"select",dict:{data:[{value:0,label:"已失效",color:"danger"},{value:1,label:"可用"}]},addForm:{component:{show:!1}},editForm:{component:{show:!1}}},{title:"处方开始时间",key:"createTime",type:"datetime",sortable:!0,addForm:{component:{show:!1}},editForm:{component:{show:!1}}}]})}}]);