<template>
  <div>
    <search
      @result-click="resultClick"
      @on-change="getResult"
      :results="results"
      v-model="value"
      position="absolute"
      auto-scroll-to-top
      @on-focus="onFocus"
      @on-cancel="onCancel"
      @on-submit="onSubmit"
      ref="search"
    ></search>
    <div class="container">
      <router-view></router-view>
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
      <tabbar-item @on-item-click="cs" link="/main/Pending">
        <img slot="icon-active" src="../assets/imgs/listPerson.png" />
        <img slot="icon" src="../assets/imgs/list.png" />
        <span slot="label" >待办列表</span>
      </tabbar-item>
      <tabbar-item show-dot @on-item-click="cs" link="/main/user">
        <img slot="icon-active" src="../assets/imgs/personActive.png" />
        <img slot="icon" src="../assets/imgs/person.png" />
        <span slot="label" >个人中心</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>
<script>
function getResult(val) {
  const rs = [];
  for (let i = 0; i < 20; i++) {
    rs.push({
      title: `${val} result: ${i + 1} `,
      other: i,
    });
  }
  return rs;
}
export default {
  data() {
    return {
      results: [],
      value: 'test',
      sele: 0,
      userInfo:''
    };
  },
  methods: {
    resultClick(item) {
      window.alert(`you click the result item: ${JSON.stringify(item)}`);
    },
    cs() {
      //   alert(1);
    },
    getResult(val) {
      console.log('on-change', val);
      this.results = val ? getResult(this.value) : [];
    },
    onSubmit() {
      this.$refs.search.setBlur();
      this.$vux.toast.show({
        type: 'text',
        position: 'top',
        text: 'on submit',
      });
    },
    onFocus() {
      console.log('on focus');
    },
    onCancel() {
      console.log('on cancel');
    },
  },
  mounted(){
    // console.log(sessionStorage.getItem('LeaderInfo'))
    if(sessionStorage.getItem('LeaderInfo') !== null){
      this.userInfo = sessionStorage.getItem('LeaderInfo')
      console.log(this.userInfo)
    }
  }
};
</script>
<style lang="less">
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
.weui-search-bar__cancel-btn {
  color: rgb(45, 161, 223);
}

.weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon,
.weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon > i,
.weui-tabbar__item.weui-bar__item_on .weui-tabbar__label {
    color: rgb(45, 161, 223)!important
}
</style>
