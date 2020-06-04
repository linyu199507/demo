<template>
  <div class="whole">
    <div class="login">
      <h1>顺德区消费维权一体化平台</h1>
      <div class="loginFrom">
        <ul>
          <li>
            <input type="text" placeholder="请输入用户名" v-model="username" />
          </li>
          <li>
            <input type="password" placeholder="请输入用户密码" v-model="password" />
          </li>
        </ul>
        <x-button type="primary" style="width:50%" @click.native="userLogin">登录</x-button>
      </div>
    </div>
  </div>
</template>
<script>
// import { test, requestLogin } from '../api/api';
import { setToken } from '../utils/auth';
import { login } from '../api/user';

export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    showPlugin() {
      this.$vux.alert.show({
        title: '提示',
        content: '请输入账号密码',
        onShow() {
        },
        onHide() {
        },
      });
    },
    userLogin() {
      console.log('12');
      if (this.username === '' || this.login === '') {
        this.showPlugin();
      } else {
        const params = {
          username: this.username,
          password: this.password,
        };
        login(params)
          .then((data) => {
            const { msg, code } = data;
            console.log(msg)
            console.log(code)
            if (code === 0) {
              // setToken(data.data);
               //记录token
                setToken(data.data.token);
                let user =  data.data.user;
                if(user.aicPermissions.leadershipPerspective != undefined || user.aicPermissions.leadershipPerspective != null){
                  // console.log(user.aicPermissions.leadershipPerspective)
                  sessionStorage.setItem('LeaderInfo',JSON.stringify(user.aicPermissions.leadershipPerspective))
                    this.$router.push({ path: "/main/MainLeader" });
                }else {
                    this.$router.push({ path: "/" });
                }
              // this.$router.push({ path: '/' });
            }
          })
          .catch((res) => {
            console.log(res);
          });
      }
    },
  },
};
</script>
<style lang="less" scoped>
.whole {
  background: url("../assets/imgs/login.jpg") no-repeat center;
  background-size: 100% 100%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  width: 100%;
  height: 100%;
}
.login {
  h1 {
    color: #fff;
    font-size: 50px;
    text-align: center;
    margin-bottom: 30px;
    padding-left: 50px;
    padding-right: 50px;
  }
  .loginFrom {
    width: 80%;
    padding: 100px 0;
    margin: 0 auto;
    text-align: center;
    background: rgba(255, 255, 255, 0.4);
    border-radius: 20px;
    // position: absolute;
    // justify-content: center;
    // margin-top: 50%;
    // margin-left: 50%;
    // transform: translate(-50%, -50%);

    ul {
      list-style: none;
      width: 100%;
      margin-bottom: 50px;
      li {
        margin-bottom: 50px;
        height: 80px;
        input {
          width: 80%;
          height: 100%;
          padding: 0 10px;
          border-radius: 5px;
          border: none;
          outline: none;
          background: rgba(255, 255, 255, 0.6);
          margin: 0 auto;
          color: rgb(146, 153, 156);
        }
      }
    }
    .weui-btn_primary {
      background: rgb(24, 144, 255);
    }
  }
}
</style>
