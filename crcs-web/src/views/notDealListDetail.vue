<template>
  <div>
    <div class="notDealDetail">
      <router-view></router-view>
    </div>
  </div>
</template>
<style lang="scss">
@media screen and (max-width: 1300px) {
  /*当屏幕尺寸小于1300px时，应用下面的CSS样式*/
  .notDealDetail {
    margin-left: 25px;
    margin-right: 25px;
  }
}
@media screen and (min-width: 1300px) {
  /*当屏幕尺寸大于1300px时，应用下面的CSS样式*/
  .notDealDetail {
    margin-left: 150px;
    margin-right: 150px;
  }
}
</style>