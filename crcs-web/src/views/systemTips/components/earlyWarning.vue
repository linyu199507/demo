<template>
  <!-- 预警页 -->
  <div>
    <div class="dataUpDown">
      <div class="header">本月投诉举报热点</div>
      <div class="upDown">
        <el-row :gutter="20">
          <el-col
            style="width:20%;margin-bottom:20px"
            class="layout"
            v-for="(item,index) in listHos"
            :key="index"
             v-loading="loading"
          >
            <el-card class="box-card" style="height:150px">
              <div class="title"><span class="iconfont el-icon-erpshuju colorBlue"></span> {{item.label}}</div>
              <div>
                <!-- 如果数量超标 -->
                <div class="fontStyle">
                  {{item.currCount}}
                  <span style="color:#000">同比</span>
                  <template v-if="item.yearOnYear>=0">
                    <span class="iconfont el-icon-erpshangsheng colorRed"></span>
                    <span class="fontStyle colorRed" style="font-size:22px">{{item.yearOnYear}}%</span>
                  </template>
                  <template v-else>
                    <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="fontStyle colorGreen" style="font-size:22px">{{item.yearOnYear}}%</span>
                  </template>
                </div>
                <!-- <div class="colorRed" style="font-size:12px;text-align:center">
                  <span class="colorRed iconfont el-icon-erpxiaoxi-jinggao"></span>
                  数量超标</div> -->
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>
<script>
import { getCompAndReportHotByLabel } from "../../../../src/api/analyse";

export default {
  data() {
    return {
      listHos:[
        {label:null,
          currCount:null,
          lastCount:null,
          yearOnYear:null
        }
      ],
      loading:false
    };
  },
  mounted(){
    this.hotList();
  },
  methods:{
     hotList(){
       this.loading=true
      //本月热点
      getCompAndReportHotByLabel().then(res=>{
          this.listHos=res.data
           this.loading=false

      })
    }

  }
};
</script>
<style lang="scss" scoped>
.dataUpDown {
  width: 100%;
  .header {
    border-bottom: 1px solid #c7c7c7;
    padding-bottom: 10px;
    font-size: 16px;
    margin-bottom: 10px;
  }
}
.title {
  font-size: 16px;
  margin-bottom: 30px;
}
.colorRed {
  color: #ff4949;
}
.colorBlue {
  color:#20a0ff;
}
.colorGreen {
  color: #1aad19;
}
.fontStyle {
  font-size: 26px;
  text-align: center;
  /* height: 80px; */
  /* line-height: 80px; */
}
</style>