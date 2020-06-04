<template>
  <div>
    <!-- 受理登记 -->
    

<!-- 12315 -->
    <el-table
      :data="listData"
      highlight-current-row
      v-loading="complaintListLoading"
      element-loading-text="拼命加载中"
      @selection-change="selsChange"
      border
      style="width: 100%"
      :row-style="{cursor:'pointer'}"
      v-if="tableTitle.type === '12315Dealist'"
    >
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>

      <el-table-column prop="serialnum" label="登记编号" min-width="15%" align="center"></el-table-column>
      <el-table-column prop="bustype" label="类型" min-width="5%" align="center"></el-table-column>
      <el-table-column prop="operate" label="当前环节" min-width="13%" align="center"></el-table-column>
      <el-table-column prop="assigndate" :formatter="formatDate" label="登记时间" min-width="10%" align="center"></el-table-column>
      <el-table-column
        prop="complainant"
        label="提供方"
        min-width="6%"
        align="center"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="complainanted"
        label="涉及主体"
        min-width="6%"
        align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="registrant"
        label="登记人"
        min-width="5%"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="content"
        label="登记内容"
        min-width="12%"
        align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="handledeptname"
        label="处理部门"
        min-width="8%"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="sdexpirationdate"
        label="到期时间"
        min-width="13%"
        :formatter="formatDates"
        align="center"
        sortable="custom"
      ></el-table-column>
      <el-table-column label="操作" min-width="8%" align="center">
        <template scope="scope">
          <el-button size="small" @click="gotoDeal(scope,scope.row)" type="primary">处理</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 12345 -->
     <el-table
      :data="listData"
      :tableTitle="tableTitle"
      highlight-current-row
      v-loading="complaintListLoading"
      element-loading-text="拼命加载中"
      @selection-change="selsChange"
      border
      style="width: 100%"
      :row-style="{cursor:'pointer'}"
      v-if="tableTitle.type === '12345Dealist'"
    >
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
      <el-table-column prop="bustype" label="业务类型" min-width="5%" align="center"></el-table-column>
      <el-table-column prop="focus" label="表单号" min-width="8%" align="center"></el-table-column>
      <el-table-column prop="tserialnum" label="工单号" min-width="13%" align="center"></el-table-column>
      <el-table-column prop="rqsttitle" label="工单标题" min-width="10%" align="center"></el-table-column>
      <el-table-column
        prop="operate"
        label="登记内容"
        min-width="4%"
        align="center"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="accordtype"
        label="当前环节"
        min-width="6%"
        align="center"
        show-overflow-tooltip
      ></el-table-column>
      <!-- 办理时间 -->
      <el-table-column
        prop="assigndate"
        label="交办时间"
        min-width="12%"
        header-align="center"
        :formatter="formatDate"
      ></el-table-column>
      <!-- 到期时间 -->
      <el-table-column
        prop="sdexpirationdate"
        label="到期时间"
        min-width="12%"
        align="center"
        :formatter="formatDate"
      ></el-table-column>
      <el-table-column label="操作" min-width="8%" align="center">
        <template scope="scope">
          <el-button size="small" @click="gotoDeal(scope,scope.row)" type="primary">处理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    ></el-pagination>
  </div>
</template>
<script>
import {getFinishListData} from "../../../api/api"
export default {
  data() {
    return {
      listData: [],
      complaintListLoading: false,
      systemsource: '',
      currentPage: 1,
      size: 10,
      total: 0
    };
  },

  // mounted() {
  //   this.getsldjList();
  // },
  // created() {
  //   console.log(this.tableTitle)
  // },
  // methods: {
    
  //   handleEdit(index, row) {
  //   },
  //   selsChange: function(sels) {
  //     this.sels = sels;
  //   },
  //   handleSizeChange(val) {
  //     // let changeSize = val;
  //     this.size = val;
  //     this.getsldjList();
      
  //   },
  //   formatDate(row, column) {
  //     return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
  //   },
  //   handleCurrentChange(val) {
  //     this.currentPage = val;
  //     this.getsldjList();
     
  //   },
  //   getlistData() {
       
  //   },
  //   getsldjList(){
  //           let user = JSON.parse(localStorage.getItem('user'));
  //           if(this.tableTitle.type === 'sldj'){
  //               this.systemsource = "02"
  //           }else if(this.tableTitle.type === '12345Dealist'){
  //               this.systemsource = "01"
  //           }else {
  //             this.systemsource = "03"
  //           }
  //           let params = {
  //               systemsource: this.systemsource,
  //               page: this.currentPage,
  //               total: this.total,
  //               size: this.size,
  //               user: user
  //           }
  //           getFinishListData(params).then(res=>{
  //               if(res.code === 0){
  //                   console.log(res)
  //                   this.listData  = res.data.records;
  //                   this.currentPage = res.data.current;
  //                   this.current = res.data.current;
  //                   this.size = res.data.size;
  //                   this.total = res.data.total;
                    
  //               }
  //           })
  //       },
  //   formatDates(row, column) {
  //     if (row.sdexpirationdate != null) {
  //       return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
  //     } else {
  //       return "";
  //     }
  //   }
  // }
};
</script>
<style lang="scss">
.el-pagination {
  text-align: right;
  margin-top: 10px;
}
</style>