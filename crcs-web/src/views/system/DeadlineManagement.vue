<template>
  <section class="backlogNotarize">
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-form :inline="true" :model="search" @submit.native.prevent>
        <el-form-item label="工单级别：" prop="level">
          <el-select v-model="search.level" placeholder="请选择" style="width: 100%">
            <el-option label="普通" value="01"></el-option>
            <el-option label="紧急" value="02"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务分类：" prop="bustype">
          <el-select v-model="search.bustype" placeholder="请选择" style="width: 100%">
            <el-option label="咨询类" value="01"></el-option>
            <el-option label="消费投诉类" value="02"></el-option>
            <el-option label="经济违法类" value="03"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时限类型：" prop="focus">
          <el-select v-model="search.deadlinetype" placeholder="请选择" style="width: 100%">
            <el-option label="正常" value="normal"></el-option>
            <el-option label="黄牌" value="yellow"></el-option>
            <el-option label="红牌" value="red"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit" @click="findList">查询</el-button>
        </el-form-item>
        <el-form-item style="float: right;">
          <el-button type="primary" native-type="submit" @click="add">增加</el-button>
          <el-button type="danger" @click="batchRemove">删除</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table
      :data="deadlineManagementList"
      highlight-current-row
      v-loading="deadlineManagementListLoading"
      @selection-change="selsChange"
      border
      style="width: 100%"
    >
      <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
      <el-table-column
        prop="level"
        label="工单级别"
        min-width="7%"
        align="center"
        :formatter="formatLevel"
      ></el-table-column>
      <el-table-column
        prop="bustype"
        label="业务分类"
        min-width="8%"
        align="center"
        :formatter="formatBustype"
      ></el-table-column>
      <el-table-column
        prop="deadlinetype"
        label="时限类型"
        min-width="8%"
        align="center"
        :formatter="formatDeadlinetype"
      ></el-table-column>
      <el-table-column
        prop="interimreplytime"
        label="初步意见时限"
        min-width="8%"
        align="center"
        :formatter="formatInterimreplyUnit"
      ></el-table-column>
      <!--            <el-table-column prop="interimreplyunit" label="初步意见时限单位" min-width="10%" align="center" :formatter="formatUnit"></el-table-column>-->
      <el-table-column
        prop="accepttime"
        label="是否受理时限"
        min-width="8%"
        align="center"
        :formatter="formatAcceptUnit"
      ></el-table-column>
      <!--            <el-table-column prop="acceptunit" label="是否受理时限单位" min-width="10%" align="center" :formatter="formatUnit"></el-table-column>-->
      <el-table-column
        prop="handletime"
        label="处理结果/是否立案时限"
        min-width="12%"
        align="center"
        :formatter="formatHandleUnit"
      ></el-table-column>
      <!--            <el-table-column prop="handleunit" label="处理结果/是否立案时限单位" min-width="14%" align="center" :formatter="formatUnit"></el-table-column>-->
      <el-table-column
        prop="warningtime"
        label="预警时间"
        min-width="7%"
        align="center"
        :formatter="formatWarningUnit"
      ></el-table-column>
      <!--            <el-table-column prop="warningunit" label="预警时间单位" min-width="8%" align="center" :formatter="formatUnit"></el-table-column>-->
      <el-table-column label="操作" min-width="5%" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="goEdit(scope.row)">编辑</el-button>
        </template>
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
  searchDeadlinemanagement,
  deleteDeadlinemanagement
} from "../../api/api";
import moment from "moment";

export default {
  data() {
    return {
      search: {
        level: "",
        bustype: "",
        deadlinetype: ""
      },
      deadlineManagementList: [],
      pagination: {
        total: 0,
        size: 10,
        page: 1
      },
      deadlineManagementListLoading: false,
      sels: [] //列表选中列
    };
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.pagination.page = currentPage;
      this.findList();
    },
    // 输入时联动查询
    change(title) {
      this.findList();
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
      this.deadlineManagementListLoading = true;
      searchDeadlinemanagement(params).then(res => {
        // this.pagination = res.data.pagination;
        // this.deadlineManagementList = res.data.list;
        this.pagination.page = res.data.current;
        this.pagination.total = res.data.total;
        this.pagination.size = res.data.size;
        this.deadlineManagementList = res.data.records;
        this.deadlineManagementListLoading = false;
      });
    },
    // toFind(row, column) {
    //     //阻止勾选复选框跳转
    //     if (column.property !== "select") {
    //         let para = {
    //             id: row.id,
    //             caseguid: row.caseguid
    //         };
    //         this.$router.push({ path: "/wddb/gdqr", query: para });
    //     }
    // },
    formatDate(row, column) {
      return moment(row.createdate).format("YYYY-MM-DD");
    },
    selsChange: function(sels) {
      this.sels = sels;
    },
    //批量删除
    batchRemove: function() {
      var ids = this.sels.map(item => item.id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.deadlineManagementListLoading = true;
          //NProgress.start();
          let para = { idList: ids };
          deleteDeadlinemanagement(para).then(res => {
            this.deadlineManagementListLoading = false;
            this.$alert("删除成功!", "提示", {
              confirmButtonText: "确定",
              type: "success"
            });
            this.findList();
          });
        })
        .catch(() => {});
    },
    //增加
    add: function() {
      this.$router.push({ path: "/xtgl/ywsxpz/add" });
    },
    formatLevel(row, column) {
      if (row.level === "01") {
        return `普通`;
      } else {
        return `紧急`;
      }
    },
    formatBustype(row, column) {
      if (row.bustype === "01") {
        return `咨询类`;
      } else if (row.bustype === "02") {
        return `消费投诉类`;
      } else {
        return `经济违法类`;
      }
    },
    formatDeadlinetype(row, column) {
      if (row.deadlinetype === "normal") {
        return `正常`;
      } else if (row.deadlinetype === "yellow") {
        return `黄牌`;
      } else {
        return `红牌`;
      }
    },
    formatInterimreplyUnit(row, column) {
      if (row.interimreplyunit === "day") {
        return row.interimreplytime + `个工作日`;
      } else if (row.interimreplyunit === "hour") {
        return row.interimreplytime + `小时`;
      } else if (row.interimreplyunit === "minute") {
        return row.interimreplytime + `分钟`;
      }
    },
    formatAcceptUnit(row, column) {
      if (row.acceptunit === "day") {
        return row.accepttime + `个工作日`;
      } else if (row.acceptunit === "hour") {
        return row.accepttime + `小时`;
      } else if (row.acceptunit === "minute") {
        return row.accepttime + `分钟`;
      }
    },
    formatHandleUnit(row, column) {
      if (row.handleunit === "day") {
        return row.handletime + `个工作日`;
      } else if (row.handleunit === "hour") {
        return row.handletime + `小时`;
      } else if (row.handleunit === "minute") {
        return row.handletime + `分钟`;
      }
    },
    formatWarningUnit(row, column) {
      if (row.warningunit === "day") {
        return `还有` + row.warningtime + `个工作日`;
      } else if (row.warningunit === "hour") {
        return `还有` + row.warningtime + `小时`;
      } else if (row.warningunit === "minute") {
        return `还有` + row.warningtime + `分钟`;
      }
    },
    goEdit(item) {
      this.$router.push({ path: "/xtgl/ywsxpz/edit", query: item });
    }
  },
  mounted() {
    this.findList();
  }
};
</script>

<style lang="scss" scoped>
</style>
