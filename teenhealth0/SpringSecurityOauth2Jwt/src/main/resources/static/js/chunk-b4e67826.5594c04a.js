(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b4e67826","chunk-2d0d7a91","chunk-2d0b2c70"],{"06f5":function(e,t,r){"use strict";var s=r("2d3b"),n=r.n(s);n.a},"260f":function(e,t,r){"use strict";r.r(t),r.d(t,"crudOptions",(function(){return s}));r("6f23");var s={pageOptions:{compact:!0},options:{lineEdit:{validation:!0},height:"100%"},formOptions:{type:"drawer",draggable:!1,fullscreen:!1,size:"50%",saveRemind:!0,defaultSpan:12},viewOptions:{disabled:!1},rowHandle:{width:260,fixed:"right",edit:{show:!1},view:{show:!1},remove:{show:!1},lineEdit:{show:!0}},columns:[{title:"测试名称",key:"testName",sortable:!1,form:{disabled:!0}},{title:"等级",key:"level",sortable:!1,form:{disabled:!0}},{title:"分数",key:"score",sortable:!1,type:"select",form:{disabled:!0}},{title:"指导建议",key:"remark",sortable:!1,width:260},{title:"标准个数 (≥) ",key:"testResult1",type:"number",sortable:!0,form:{}},{title:"标准个数 (≤) ",key:"testResult2",type:"number",form:{},sortable:!0},{title:"更新时间",key:"updateTime",sortable:!0,type:"datetime",form:{disabled:!0}}]}},"2d3b":function(e,t,r){},7882:function(e,t,r){"use strict";r.r(t),r.d(t,"GetList",(function(){return n})),r.d(t,"UpdateObj",(function(){return u}));var s=r("22ce");function n(e,t){return console.log(e),Object(s["a"])({url:"/tTest/testList/1",method:"post",data:{pageSize:20,pageNum:1}})}function u(e){return console.log(e),Object(s["a"])({url:"/tTest/updateTestCardior/"+e.id,method:"post",data:{testId:e.testId,testResult1:e.testResult1,testResult2:e.testResult2,remark:e.remark}})}},"8a33":function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("d2-container",{class:{"page-compact":e.crud.pageOptions.compact}},[r("d2-crud-x",e._g(e._b({ref:"d2Crud",attrs:{"edit-rules":e.editRules}},"d2-crud-x",e._crudProps,!1),e._crudListeners),[r("div",{attrs:{slot:"header"},slot:"header"})])],1)},n=[],u=r("260f"),d=r("1fbf"),i=r("7882"),a={name:"somaticTest",mixins:[d["d2CrudPlus"].crud],data:function(){return{needValidation:!0,editRules:{testResult1:[{required:!0,message:"请输入非负整数",pattern:/^[1-9]\d*|0$/}],testResult2:[{required:!0,message:"请输入非负整数",pattern:/^[1-9]\d*|0$/}],remark:[{required:!0,message:"请输入指导建议"}]}}},methods:{getCrudOptions:function(){return u["crudOptions"]},pageRequest:function(e){return Object(i["GetList"])(this.$route.query.studentId,e)},addRequest:function(e){return AddObj(e).then((function(t){return e.id=t.data,{row:e}}))},updateRequest:function(e){return Object(i["UpdateObj"])(e).then((function(t){return{row:e}}))},delRequest:function(e){return DelObj(e.id)}}},o=a,l=(r("06f5"),r("2877")),c=Object(l["a"])(o,s,n,!1,null,null,null);t["default"]=c.exports}}]);