<template>
  <div class="bg">
    <div class="header">
      <ul>
        <li>
          <div class="avatar">头像</div>
          <div class="avatarImg">
            <img src="../assets/logo.png" alt />
          </div>
        </li>
        <li>
          <div class="userTitle">用户名</div>
          <div class="username">用户名</div>
        </li>
        <li @click="changeNickName">
          <div class="gender">昵称</div>
          <div class="genderContent">{{gender}}</div>
          <div class="arrow">
            <img src="../assets/imgs/arrowLeft.png" alt />
          </div>
        </li>
        <li @click="chooseGender">
          <div class="gender">性别</div>
          <div class="genderContent">{{gender}}</div>
          <div class="arrow">
            <img src="../assets/imgs/arrowLeft.png" alt />
          </div>
        </li>
      </ul>
    </div>
    <div class="loginOut">
      <x-button type="primary" @click.native="logout">退出登录</x-button>
    </div>
     <tabbar v-model="sele" style="position:fixed;bottom:0;left:0">
      <tabbar-item @on-item-click="cs" link="/main/mainIndex" v-if="userInfo===''">
        <img slot="icon-active" src="../assets/imgs/home.png" />
        <img slot="icon" src="../assets/imgs/homenot.png" />
        <span slot="label" >首页</span>
      </tabbar-item>
      <tabbar-item @on-item-click="cs" link="/main/MainLeader" v-else> 
        <img slot="icon-active" src="../assets/imgs/home.png" />
        <img slot="icon" src="../assets/imgs/homenot.png" />
        <span slot="label" >首页</span>
      </tabbar-item>
      <tabbar-item link="/main/Pending">
        <img slot="icon-active" src="../assets/imgs/listPerson.png" />
        <img slot="icon" src="../assets/imgs/list.png" />
        <span slot="label" >待办列表</span>
      </tabbar-item>
      <tabbar-item show-dot link="/main/user" selected>
        <img slot="icon-active" src="../assets/imgs/personActive.png" />
        <img slot="icon" src="../assets/imgs/person.png" />
        <span slot="label" >个人中心</span>
      </tabbar-item>
    </tabbar>
    <!-- 弹窗 -->
    <popup v-model="show">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="comfirm"
        @on-click-left="show = false"
        @on-click-right="show = false"
      ></popup-header>
      <group gutter="0">
        <radio :options="genderOptions" v-model="genders"></radio>
      </group>
    </popup>
  </div>
</template>
<script>
import { removeToken } from '../utils/auth';

export default {
  data() {
    return {
      gender: '',
      genders: '',
      userInfo:'',
      show: false,
      genderOptions: ['男', '女'],
      sele: '/main/Pending',
    };
  },
  methods: {
    onShow() {
      console.log('on show');
    },
    cs() {
      //   alert(1);
    },
    onHide(type) {
      console.log('on hide', type);
    },
    onChange(val) {
      console.log('val change', val);
    },
    chooseGender() {
      this.show = true;
    },
    comfirm() {
      this.show = false;
      this.gender = this.genders;
    },
    changeNickName() {
      // alert('');
      this.showPlugin();
    },
    showPlugin() {
      this.$vux.alert.show({
        title: '提示',
        content: '是否退出',
        onShow() {
          // console.log("Plugin: I'm showing");
        },
        onHide() {
          // this.$router.push('/login');
        },
      });
    },
    logout() {
      // this.showPlugin();

      removeToken();
      localStorage.removeItem('user');
      sessionStorage.removeItem('LeaderInfo')
      this.$router.push('/login');

    },
  },
  mounted(){
    if(sessionStorage.getItem('LeaderInfo') !== null){
      this.userInfo = sessionStorage.getItem('LeaderInfo')
      console.log(this.userInfo)
    }
  }
};
</script>
<style lang="less" scoped>
.loginOut {
  width: 80%;
  margin: 60px auto 0;
}
.bg {
  background: #fbf9fe;
}
.header {
  ul {
    list-style: none;
    li {
      width: 90%;
      margin: 0 auto;
      background: #fff;
      display: flex;
      justify-content: space-between;
      padding: 20px 20px;
      align-items: center;
      border-bottom: 1px solid #fbf9fe;
      .avatar {
        font-size: 0.4rem;
        background: #fff;
      }
      .avatarImg {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        overflow: hidden;
        border: 1px solid #ccc;
        img {
          width: 100%;
          height: 100%;
        }
      }
    }
  }
}
.gender {
  flex: 1;
}
.genderContent {
  flex: 1;
  text-align: right;
}
.arrow {
  width: 40px;
  height: 40px;
  img {
    width: 100%;
  }
}
</style>
<style lang="less">
.weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon, .weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon > i, .weui-tabbar__item.weui-bar__item_on .weui-tabbar__label{
  color:#1296db!important;
}
</style>
