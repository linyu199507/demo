<template>
  <div>
    <!-- 搜索框 -->
    <search
      @result-click="resultClick"
      @on-change="getResult"
      :results="results"
      v-model="value"
      position="absolute"
      auto-scroll-to-top
      top="46px"
      @on-focus="onFocus"
      @on-cancel="onCancel"
      @on-submit="onSubmit"
      ref="search"
    ></search>
  </div>
</template>
<script>
export default {
  data() {
    return {
      results: [],
      value: "test"
    };
  },
  methods: {
    resultClick(item) {
      window.alert("you click the result item: " + JSON.stringify(item));
    },
    getResult(val) {
      console.log("on-change", val);
      this.results = val ? getResult(this.value) : [];
    }
  }
};
</script>
<style lang="less" scoped>
</style>
<style lang="less">
</style>