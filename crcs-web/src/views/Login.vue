<template>
  <div class="bg">
    <el-row>
      <el-col :span="17">
        <div class="logo">
          <!--<img style="width:80%;height:auto;max-width:100%;" src="../assets/logo.png" alt />-->
          <img style="width:80%;height:auto;max-width:100%;" src="../assets/logo2.png" alt />
        </div>
      </el-col>
    </el-row>
    <el-row class="locale">
      <el-col>
        <el-form
          :model="loginForm"
          :rules="rules2"
          ref="loginForm"
          label-position="left"
          label-width="0px"
          class="demo-ruleForm login-container"
          @submit.native.prevent
        >
          <h3 class="title" style>
            <span style="display:inline-block;width:100px;">登</span>
            <span style="display:inline-block;width:100px">录</span>
          </h3>
          <el-form-item prop="account">
            <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginForm.checkPass"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
          <el-form-item style="width:100%;">
            <el-button
              type="primary"
              native-type="submit"
              style="width:40%;"
              @click.native.prevent="handleSubmit"
              :loading="logining"
            >登录</el-button>
            <!--<el-button @click.native.prevent="handleReset">重置</el-button>-->
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row class="button">
      <el-col>
        <div style="text-align: center;font-weight: bold;">版权所有：顺德区市场监督管理局 | 技术支持：广东众望通科技股份有限公司</div>
      </el-col>
      <el-col>
        <!--style="text-indent: 20px"-->
        <div class="copyRight">浏览器使用说明：本系统支持QQ浏览器、谷歌、火狐浏览器等主流浏览器</div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { test, requestLogin } from "../api/api";
import { setToken, setFlag } from "../utils/auth";
import { login, userLogin,getUserInfo } from "../api/user";
//import NProgress from 'nprogress'
export default {
  data() {
    return {
      logining: false,
      loginForm: {
        account: "",
        checkPass: ""
      },
      rules2: {
        account: [
          { required: true, message: "请输入账号", trigger: "blur" }
          //{ validator: validaePass }
        ],
        checkPass: [
          { required: true, message: "请输入密码", trigger: "blur" }
          //{ validator: validaePass2 }
        ]
      },
      checked: true
    };
  },
  methods: {
    // 保存cookie
    setCookie(cname, cvalue, pwd, pvalue, exdays) {
      let d = new Date();
      d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
      let expires = "expires=" + d.toUTCString();
      document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
      document.cookie = pwd + "=" + pvalue + "; " + expires + "; path=/";
    },
    //删除 cookie
    clearCookie(name, pwd) {
      this.setCookie(name, "", pwd, "", -1);
    },
    //获取cookie
    getCookie(cname) {
      let name = cname + "=";
      let ca = document.cookie.split(";");
      for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == " ") c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
      }
      return "";
    },
    handleReset() {
      this.$refs.loginForm.resetFields();
    },
    handleSubmit(ev) {
      var _this = this;
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          //_this.$router.replace('/table');
          this.logining = true;
          //NProgress.start();
          let username = this.loginForm.account.trim();
          var loginParams = {
            username: username,
            password: this.loginForm.checkPass
          };
          userLogin(loginParams)
            .then(data => {
              this.logining = false;
              //NProgress.done();
              let { msg, code } = data;
              // let {msg, code,user} = data;
              if (code !== 0) {
                this.$alert(msg, "提示", {
                  confirmButtonText: "确定",
                  type: "error"
                });
              } else {
                // 记录密码
                if (this.checked) {
                  this.setCookie(
                    "username",
                    this.loginForm.account,
                    "pwd",
                    this.loginForm.checkPass,
                    30
                  );
                } else {
                  this.clearCookie("username", "pwd");
                }

                //记录token
                setToken(data.data.token);
                setFlag('true');
                let user =  data.data.user;
                if(user.aicPermissions.leadershipPerspective != undefined || user.aicPermissions.leadershipPerspective != null){
                    this.$router.push({ path: "/tjfx/tjbb/cybb/Advancedbusiness" });
                }else {
                    this.$router.push({ path: "/" });
                }
              }
            })
            .catch(res => {
              console.log(res);
              this.$alert("连接失败，请检查网络！", "提示", {
                confirmButtonText: "确定",
                type: "error"
              });
              this.logining = false;
            });


        } else {
          return false;
        }
      });
    }
  },
  mounted() {
    this.loginForm.account = this.getCookie("username");
    this.loginForm.checkPass = this.getCookie("pwd");
  }
};
</script>

<style lang="scss" scoped>
body {
  /* position: relative; */
}
.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 110px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  /* background: #fff; */
  background: rgba(255, 255, 255, 1);
  /* border: 1px solid rgba(255,255,255,0.7); */
  /* box-shadow: 0 0 25px #cac6c6; */

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
    span {
      font-size: 30px;
    }
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }
}

.el-col-17 {
  width: 100%;
  .logo {
    width: 100%;
    height: 80px;
    /* line-height: 100px; */
    /* background:red; */
    margin: 50px auto 0;
    text-align: right;
  }
}

.bg {
  background-image: url(../assets/bg2017.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 100%;
  min-height: 100%;
  position: relative;
}

.button {
  color: #505458;
  /*bottom: 0;*/
  position: absolute;
  bottom: 20px;
  height: 55px;
  width: 100%;
}
.copyRight {
  text-align: center;
  margin: 17px;
  font-weight: bold;
}
.el-form-item {
  text-align: center !important;
}
.login-container .title[data-v-fe037c3c] {
  font-size: 30px;
}
/* .locale {
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
} */
</style>
