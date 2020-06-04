<template>
  <!-- 意见分析 -->
  <el-row :gutter="25">
    <!--意见分析-->
    <el-col>
      <el-card>
        <el-form label-suffix="：" :inline="true">
          <el-form-item label="问题分类" class="bt">
            <el-select placeholder="请选择问题分类" filterable v-model="formData.codeId">
              <el-option label="全部" value="all"></el-option>
              <el-option label="发票" value="01"></el-option>
              <el-option label="质量" value="02"></el-option>
              <el-option label="广告" value="03"></el-option>
              <el-option label="商标" value="04"></el-option>
              <el-option label="不正当竞争及限制竞争" value="05"></el-option>
              <el-option label="无照经营" value="06"></el-option>
              <el-option label="传销" value="07"></el-option>
              <el-option label="合同" value="08"></el-option>
              <el-option label="包装标识" value="09"></el-option>
              <el-option label="计量" value="10"></el-option>
              <el-option label="价格" value="11"></el-option>
              <el-option label="人身权利" value="12"></el-option>
              <el-option label="售后服务" value="13"></el-option>
              <el-option label="其他" value="14"></el-option>
              <el-option label="安全" value="15"></el-option>
              <el-option label="金融" value="16"></el-option>
              <el-option label="环保投诉" value="17"></el-option>
              <el-option label="无产品合格证" value="18"></el-option>
              <el-option label="专利" value="19"></el-option>
              <el-option label="个人信息保护" value="20"></el-option>
            </el-select>
          </el-form-item>
          <!-- <el-form-item label="时间" class="bt">
                            <el-input placeholder="请输入" class="input-text"/>
          </el-form-item>-->
          <el-form-item>
            <el-button type="primary" icon="search" @click="search">搜索</el-button>
          </el-form-item>
        </el-form>
        <el-row :gutter="20">
          <el-col :xs="24" :sm="16" :md="16" :lg="18">
            <el-card>
              <div class="flexRow">
                <div class="da_text">
                  <div class="title">系统投诉举报总数</div>
                  <div class="da_content">{{data.count}} 单</div>
                </div>
                <div class="da_text">
                  <div class="title">本月同比</div>
                  <!-- 上升 -->
                  <div v-if="data.yearOnYear>=0">
                    <span class="iconfont el-icon-erpshangsheng colorRed"></span>
                    <span class="colorRed fontStyle">{{data.yearOnYear}}%</span>
                  </div>
                  <!-- 下降 -->
                  <div v-else class="da_content">
                      <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="colorGreen fontStyle">{{data.yearOnYear}}%</span>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
           <el-card :xs="24" :sm="6" :md="6" :lg="6">
              <div class="flexRow">
                <div class="da_text">
                  <div class="title">系统调解数</div>
                  <div class="da_content">{{data.mediation}} 单</div>
                </div>
              </div>
            </el-card>
        </el-row>
        <el-row :gutter="20">
          <el-col style="font-size: 16px;margin-top:15px">意见分析：</el-col>
          <el-col :span="10">
            <el-button v-for="item in data.listLabel" :key="item.label" :type="formData.label!==item.label?'primary':''" size="mini" class="tag_hit" @click="getTag(item.label)">{{`${item.label}（${item.count}）`}}</el-button>
            &nbsp;
          </el-col>
          <el-col :span="14">
            <el-table :data="listContent" style="width: 100%" :show-header="false" v-loading="loading">
              <el-table-column prop="content" label="内容"></el-table-column>
            </el-table>
            <!--分页组件开始-->
            <el-col style="text-align: right;margin-top: 10px">
                <el-pagination
                  @current-change="handleCurrentChange"
                  :page-size="pagination.size"
                  :total="pagination.total"
                  layout="prev, pager, next">
                </el-pagination>
            </el-col>
            <!-- 分页组件结束-->
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col class="mt20">
      <el-card></el-card>
    </el-col>
  </el-row>
</template>
<script>
import { getOpinAnalysisAndLabel,listContentByOpLabel } from "../../../../src/api/analyse";

export default {
  data() {
    return {
      formData: {
        codeId: "all",
        label:null
      },
      listContent: [],
      pagination:{
          size:0,
          total:5,
          page:0
      },
      data:{
        yearOnYear:0,
        mediation:0,
        count:0
      },
      loading:false,
    };
  },
  methods: {
    getData(query){
      let {data}= getOpinAnalysisAndLabel(query).then(res=>{
        this.data=res.data;
      })
    },
    findListContent(){
      this.loading=true
      let query={
        codeId:this.formData.codeId,
        label:this.formData.label,
        page:this.pagination.page
      }
      listContentByOpLabel(query).then(res=>{
        this.pagination.page=res.data.page
        this.pagination.total=res.data.total
        this.pagination.size=res.data.size
        this.listContent=res.data.list
        this.loading=false
      })
    },
    getTag(label){
      //获取投诉内容列表
      if(label===this.formData.label){
        this.formData.label=null
      }else{
        this.formData.label=label
      }
      this.pagination.page=0
      this.findListContent()
    },
    handleCurrentChange(page){
        this.pagination.page = page
        this.findListContent();
    },
    search(){
      //重置
      this.formData.label=null
      this.getData({codeId:this.formData.codeId});
      this.findListContent(null);
    }
  },
  mounted() {
    this.search();

  }
};
</script>
<style lang="scss" scoped>
.header {
  border-bottom: 1px solid #c7c7c7;
  padding-bottom: 10px;
  font-size: 16px;
  margin-bottom: 10px;
}
.tag_hit {
  margin: 20px 5px 0 5px;
}
.flexRow {
  display: flex;
  align-items: center;
  justify-content: center;
}
.da_text {
  width: 100%;
  height: 80px;
  .title {
    font-size: 16px;
    margin-bottom: 10px;
  }
  .da_content {
    color: #c7c7c7;
    font-size: 20px;
  }
}
.fontStyle {
  font-size: 26px;
}
.colorRed {
  color: #ff4949;
}
.colorGreen {
  color: #1aad19;
}
</style>