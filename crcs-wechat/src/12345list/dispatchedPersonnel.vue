<template>
  <div>
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">定单信息</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <popup-picker
          title="主要人员"
          placeholder="必填"
          :data="MembersOptions"
          v-model="menber"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="抄送"
          placeholder="请选择抄送人员"
          :data="copyClerk"
          v-model="copyMenber"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="分派意见"
          v-model="opionion"
          placeholder="请填写分派意见(必填)"
          :show-counter="false"
          :rows="3"
        ></x-textarea>
      </group>
    </div>
    <div class="deal">
      <x-button type="primary" @click.native="showChoose">确定</x-button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      menber: [],
      opionion: '',
      copyMenber: [],
      MembersOptions: [['顺德区管理员']],
      copyClerk: [['办公室']],
    };
  },
  methods: {
    showChoose() {
      // alert();
      this.$router.push({ path: '/main/Pending' });
    },
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 40px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);
.dispatchedWhole {
  width: 90%;
  margin: 0 auto;
  border-radius: 20px;
  padding-bottom: 20px;
  margin-top: 20px;
  background: #fff;
  box-shadow: #eee 2px 2px 5px 2px;
  .header {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border-bottom: 2px solid #d9d9d9;
    font-size: @fontStyleOfTitle;
    padding: 20px 0 0 40px;
    span {
      display: inline-block;
      width: 6px;
      height: 40px;
      margin-right: 20px;
      background: @color;
      vertical-align: middle;
    }
    .title {
      vertical-align: middle;
    }
  }
}
.weui-cells:before {
  border-top: none !important;
}
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.deal {
  width: 80%;
  margin: 0 auto;
}
.weui-btn_primary {
  margin-top: 100px;
  margin-bottom: 100px;
  background: @backgroundColor!important;
}
</style>