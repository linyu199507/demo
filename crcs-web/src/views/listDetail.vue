<template>
  <div>
    <div class="whole">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      height: 120,
      scrollTop: 0
    };
  },
  methods: {
    getScroll() {
      this.scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      //  console.log(this.scrollTop)
    }
  },
  mounted() {
    window.addEventListener("scroll", this.getScroll);
  }
};
</script>
<style lang="scss">
@media screen and (max-width: 1300px) {
  /*当屏幕尺寸小于1300px时，应用下面的CSS样式*/
  .whole {
    margin-left: 25px;
    margin-right: 25px;
  }
}
@media screen and (min-width: 1300px) {
  /*当屏幕尺寸大于1300px时，应用下面的CSS样式*/
  .whole {
    margin-left: 150px;
    margin-right: 150px;
  }
}
// .whole {
//   margin-left: 150px;
//   margin-right: 150px;
// }
.fixed {
  text-align: right;
  height: 50px;
  background: #fff;
  line-height: 50px !important;
  position: fixed;
  z-index: 344;
  top: 0;
  right: 0;
  left: 0;
  padding-left: 180px;
  box-shadow: 1px 0px 10px 0;
  .el-button:last-child {
    margin-right: 220px !important;
  }
}
</style>