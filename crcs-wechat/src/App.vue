<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
};
</script>

<style lang="less">
@import '~vux/src/styles/reset.less';

body {
  background-color: #fbf9fe;
}
.mt20 {
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

.pt20 {
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
