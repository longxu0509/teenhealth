(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1eb498b8","chunk-10f76bbe"],{2973:function(e,t,s){},3547:function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"page-login"},[s("div",{staticClass:"page-login--layer page-login--layer-area"},[s("ul",{staticClass:"circles"},e._l(10,(function(e){return s("li",{key:e})})),0)]),s("div",{staticClass:"page-login--layer page-login--layer-time",attrs:{flex:"main:center cross:center"}},[e._v(" "+e._s(e.time)+" ")]),s("div",{staticClass:"page-login--layer"},[s("div",{staticClass:"page-login--content",attrs:{flex:"dir:top main:justify cross:stretch box:justify"}},[s("div",{staticClass:"page-login--content-header"}),s("div",{staticClass:"page-login--content-main",attrs:{flex:"dir:top main:center cross:center"}},[e._m(0),s("div",{staticClass:"page-login--form"},[s("el-card",{attrs:{shadow:"never"}},[s("el-form",{ref:"loginForm",attrs:{"label-position":"top",rules:e.rules,model:e.formLogin,size:"default"}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{type:"text",placeholder:"用户名"},model:{value:e.formLogin.username,callback:function(t){e.$set(e.formLogin,"username",t)},expression:"formLogin.username"}},[s("i",{staticClass:"fa fa-user-circle-o",attrs:{slot:"prepend"},slot:"prepend"})])],1),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:e.formLogin.password,callback:function(t){e.$set(e.formLogin,"password",t)},expression:"formLogin.password"}},[s("i",{staticClass:"fa fa-keyboard-o",attrs:{slot:"prepend"},slot:"prepend"})])],1),s("el-button",{staticClass:"button-login",attrs:{size:"default",type:"primary"},on:{click:e.submit}},[e._v(" 登录 ")])],1)],1),s("p",{staticClass:"page-login--options",attrs:{flex:"main:justify cross:center"}},[s("span",[s("d2-icon",{attrs:{name:"question-circle"}}),e._v(" 忘记密码")],1),s("span",[e._v("注册用户")])])],1)]),s("div",{staticClass:"page-login--content-footer"},[s("p",{staticClass:"page-login--content-footer-locales"}),s("p",{staticClass:"page-login--content-footer-copyright"},[e._v(" 2020 "),s("d2-icon",{attrs:{name:"copyright"}}),e._v(" 中科院合肥技术创新工程院 "),s("a",{attrs:{href:"http://www.hfiti.cn/"}},[e._v(" 创新院主页 ")])],1),s("p",{staticClass:"page-login--content-footer-options"})])])])])},o=[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("h2",{staticStyle:{color:"#409EFF"}},[e._v("AI体能测训系统")])])}],a=(s("ac1f"),s("5319"),s("5530")),n=s("5a0c"),i=s.n(n),l=s("2f62"),c=s("1353"),m=s("22ce"),u={name:"login",mixins:[c["a"]],data:function(){return{timeInterval:null,time:i()().format("HH:mm:ss"),formLogin:{username:"",password:""},responseResult:[],rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}]}}},mounted:function(){var e=this;this.timeInterval=setInterval((function(){e.refreshTime()}),1e3)},beforeDestroy:function(){clearInterval(this.timeInterval)},methods:Object(a["a"])(Object(a["a"])({},Object(l["b"])("d2admin/account",["login"])),{},{refreshTime:function(){this.time=i()().format("HH:mm:ss")},handleUserBtnClick:function(e){this.formLogin.username=e.username,this.formLogin.password=e.password,this.submit()},submit:function(){var e=this,t=this;return console.log(this.$store.state),console.log(this.formLogin.username),Object(m["a"])({url:"/userlogin",method:"post",params:{username:this.formLogin.username,password:this.formLogin.password}}).then((function(s){if(200===s.code){console.log("登录成功",s),e.$message.success("登录成功！"),t.$store.commit("login",t.formLogin);var r=e.$route.query.redirect;e.$router.replace({path:"/"===r||void 0===r?"/index":r})}else 301===s.code?e.$message.error("用户不存在!"):302===s.code?e.$message.error("密码错误!"):303===s.code?e.$message.error("密码过期!"):304===s.code?e.$message.error("账号不可用!"):305===s.code?e.$message.error("账号锁定!"):306===s.code?e.$message.error("账号在其他设备登录，被迫下线!"):307===s.code&&e.$message.error("其他错误!")})).catch((function(e){}))}})},p=u,f=(s("60d0"),s("2877")),g=Object(f["a"])(p,r,o,!1,null,null,null);t["default"]=g.exports},"60d0":function(e,t,s){"use strict";var r=s("2973"),o=s.n(r);o.a},"99c7":function(e,t,s){"use strict";s.r(t);var r=s("3547");t["default"]=r["default"]}}]);