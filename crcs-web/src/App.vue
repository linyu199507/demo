<template>
  <div id="app">
    <transition name="fade"
                mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
  export default {
    name: 'app',
    components: {}
  }

</script>

<style lang="scss">
  body {
    margin: 0;
    padding: 0;
    /*background: url(assets/bg1.jpg) center !important;
        background-size: cover;*/
    // background: #1F2D3D;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
    font-size: 14px;
    -webkit-font-smoothing: antialiased;
  }

  /*#app {*/
  /*	position: absolute;*/
  /*	top: 1px;*/
  /*	bottom: 1px;*/
  /*	width: 100%;*/
  /*	!*height: 100%;*!*/
  /*	overflow: hidden;*/
  /*}*/
  #app {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
  }

  .el-submenu [class^=fa] {
    vertical-align: baseline;
    margin-right: 10px;
  }

  .el-menu-item [class^=fa] {
    vertical-align: baseline;
    margin-right: 10px;
  }

  .toolbar {
    background: #f2f2f2;
    padding: 10px;
    //border:1px solid #dfe6ec;
    margin: 10px 0;
    .el-form-item {
      margin-bottom: 0;
    }
  }

  .fade-enter-active,
  .fade-leave-active {
    transition: all .2s ease;
  }

  .fade-enter,
  .fade-leave-active {
    opacity: 0;
  }

  /* 公共样式 */
  .mt10{
    margin-top: 10px;
  }
  .mb10 {
    margin-bottom: 10px;
  }
  .ml10 {
    margin-left: 10px;
  }
  .mr10 {
    margin-right: 10px;
  }
  .ml-15 {
    margin-left: -15px
  }
   .mt20{
    margin-top: 20px;
  }
  .mb20 {
    margin-bottom: 20px;
  }
  .ml20 {
    margin-left: 20px;
  }
  .mr20 {
    margin-right: 20px;
  }

   .pt10{
    padding-top: 10px;
  }
  .pb10 {
    padding-bottom: 10px;
  }
  .pl10 {
    margin-left: 10px;
  }
  .pr10 {
    padding-right: 10px;
  }
.w110 {
  width: 110px;
}
   .pt20{
    padding-top: 20px;
  }
  .pb20 {
    padding-bottom: 20px;
  }
  .pl20 {
    padding-left: 20px;
  }
  .pr20 {
    padding-right: 20px;
  }
</style>
