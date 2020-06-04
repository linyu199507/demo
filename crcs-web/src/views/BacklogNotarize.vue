<template>
  <section class="backlogNotarize">
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-form :inline="true" :model="search" @submit.native.prevent>
        <el-form-item>
          <el-col>标题:</el-col>
        </el-form-item>
        <el-form-item>
          <el-input v-model="search.rqsttitle" @change="change(search.rqsttitle)" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item>
          <el-col>表单号:</el-col>
        </el-form-item>
        <el-form-item>
          <el-input v-model="search.serialnum" @change="change(search.serialnum)" placeholder="表单号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-col>工单号:</el-col>
        </el-form-item>
        <el-form-item>
          <el-input v-model="search.tserialnum" @change="change(search.tserialnum)" placeholder="工单号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit" @click="findList">查询</el-button>
        </el-form-item>
        <el-button style="float: right;" type="danger" @click="batchRemove">删除</el-button>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table
      :data="complaintList"
      highlight-current-row
      v-loading="complaintListLoading"
      @selection-change="selsChange"
      border
      style="width: 100%"
      @cell-click="toFind"
      :row-style="{cursor:'pointer'}"
    >
      <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
      <el-table-column prop="focus" label="工单分类" min-width="6%" align="center"></el-table-column>
      <el-table-column prop="serialnum" label="表单号" min-width="12%" header-align="center"></el-table-column>
      <el-table-column prop="tserialnum" label="工单号" min-width="12%" header-align="center"></el-table-column>
      <el-table-column
        prop="rqsttitle"
        label="工单标题"
        min-width="20%"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="accordtype"
        label="业务分类"
        min-width="10%"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="labelname"
        label="政务标签"
        min-width="10%"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="createdate"
        label="交办时间"
        min-width="10%"
        align="center"

        :formatter="formatDate"
      ></el-table-column>
      <el-table-column prop="resulttimemin"
      label="到期时间"
       min-width="10%" align="center">
       </el-table-column>
    </el-table>
    <br />
    <!--分页组件开始-->
    <el-col style="text-align: right;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 20, 30, 40,50]"
        layout="slot,total, sizes, prev, pager, next, jumper"
      >
        <span>
          第 {{pagination.page}} 页 /
          共 {{(parseInt((pagination.total + pagination.size - 1)/pagination.size))}} 页
        </span>
      </el-pagination>
    </el-col>
    <!--分页组件结束-->
  </section>
</template>

<script>
import {
  listComplaints,
  removeUser,
  batchRemoveUser,
  deleteTasklist,
  selectDatagriddataId
} from "../api/api";
import moment from "moment";

export default {
  data() {
    return {
      search: {
        serialnum: "",
        tserialnum: "",
        rqsttitle: ""
      },
      complaintList: [],
      pagination: {
        total: 0,
        size: 10,
        page: 1
      },
      complaintListLoading: false,
      sels: [] //列表选中列
    };
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.pagination.page = currentPage;
      this.findList();

    },
    // 输入时联动查询
    change(title){
      this.findList()
    },
    handleSizeChange(size) {
      this.pagination.size = size;
      this.findList();
    },
    findList() {
      let params = {
        ...this.search,
        ...this.pagination
      };
      this.complaintListLoading = true;
      listComplaints(params).then(res => {
        // this.pagination = res.data.pagination;
        // this.complaintList = res.data.list;
        this.pagination.page = res.data.current;
        this.pagination.total = res.data.total;
        this.pagination.size = res.data.size;
        this.complaintList = res.data.records;
        this.complaintListLoading = false;
      });
    },
    toFind(row, column) {
      //阻止勾选复选框跳转
      if (column.property !== "select") {
        let para = {
          id: row.id,
          caseguid: row.caseguid
        };
        // selectDatagriddataId(para).then(() =>  {
        //     this.$router.push('/wddb/gdqr')
        // });

        this.$router.push({ path: "/wddb/gdqr", query: para });
      }
    },
    formatDate(row, column) {
      return moment(row.createdate).format("YYYY-MM-DD");
    },
    selsChange: function(sels) {
      this.sels = sels;
    },
    //批量删除
    batchRemove: function() {
      let user = localStorage.getItem('user');
      var ids = this.sels.map(item => item.id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.complaintListLoading = true;
          //NProgress.start();
          let para = {
            ids: ids,
            user: JSON.parse(user)
          };
          deleteTasklist(para).then(res => {
            this.complaintListLoading = false;
            //NProgress.done();
            this.$alert("删除成功!", "提示", {
              confirmButtonText: "确定",
              type: "success"
            });
            this.findList();
          });
        })
        .catch(() => {});
    }
  },
  mounted() {
    this.findList();
  }
};
</script>

<style lang="scss" scoped>
/* .el-table__header thead th{*/
/*	 text-align: center;*/
/* }*/
/* .el-table__body tbody tr td div{*/
/*	 float: left;*/
/*	 margin-left: 10px;*/
/* }*/
/*.el-form-item__content {*/
/*	margin-right: 10px;*/
/*}*/

</style>
