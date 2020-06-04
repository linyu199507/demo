<template>
  <div>
    <el-row type="flex" class="row-bg" justify="space-between">
      <el-form label-width="80px">
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="标题：">
            <el-input placeholder="请输入标题" @change="getsldjList" v-model="sldjForm.title" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="工单号：">
            <el-input placeholder="请输入工单号" @change="getsldjList" v-model="sldjForm.number" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="时间：">
            <el-date-picker
              style="width:300px"
              v-model="sldjForm.currentDate"
              type="datetimerange"
              placeholder="选择时间范围"
              @change="getsldjList"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="电话：">
            <el-input placeholder="请输入电话" @change="getsldjList" v-model="sldjForm.tel" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="关键词：">
            <el-input placeholder="请输入关键词" @change="getsldjList" v-model="sldjForm.keyword" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item>
            <el-button type="primary" @click="getsldjList">查询</el-button>
            <!-- <el-button type="danger">删除</el-button> -->
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-table
      :data="listData"
      highlight-current-row
      v-loading="complaintListLoading"
      element-loading-text="拼命加载中"
      @selection-change="selsChange"
      border
      style="width: 100%"
      :row-style="{cursor:'pointer'}"
    >
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>

      <el-table-column prop="bustype" label="业务类型" min-width="5%" align="center"></el-table-column>

      <el-table-column prop="focus" label="工单分类" min-width="8%" align="center"></el-table-column>
      <el-table-column prop="tserialnum" label="工单号" min-width="13%" align="center"></el-table-column>

      <el-table-column prop="rqsttitle" label="工单标题" min-width="10%" align="center"></el-table-column>
      <!-- 当前环节 -->
      <el-table-column
        prop="operate"
        label="当前环节"
        min-width="6%"
        align="center"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <!-- 业务分类 -->
      <el-table-column
        prop="accordtype"
        label="业务分类"
        min-width="6%"
        align="center"
        show-overflow-tooltip
      ></el-table-column>
      <!-- 办理时间 -->
      <el-table-column
        prop="assigndate"
        label="办理时间"
        min-width="12%"
        header-align="center"
        show-overflow-tooltip
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
import { getFinishListData } from "../../../api/api";
export default {
  data() {
    return {
      sldjForm: {
        title: "",
        number: "",
        currentDate: "",
        tel: "",
        keyword: ""
      },
      listData: [],
      complaintListLoading: false,
      systemsource: "01",
      currentPage: 1,
      size: 10,
      total: 0,
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      }
    };
  },
  mounted() {
    this.getsldjList();
  },
  methods: {
    handleEdit(index, row) {},
    selsChange: function(sels) {
      this.sels = sels;
    },
    handleSizeChange(val) {
      // let changeSize = val;
      this.size = val;
      this.getsldjList();
    },
    getLists() {},
    formatDate(row, column) {
      return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getsldjList();
    },
    getlistData() {},
    getsldjList() {
      // this.systemsource = "01"
      let user = JSON.parse(localStorage.getItem("user"));
      if (
        this.sldjForm.currentDate[0] !== undefined &&
        this.sldjForm.currentDate[1] !== undefined
      ) {
        var begindate = moment(this.sldjForm.currentDate[0]).format(
          "YYYY-MM-DD HH:mm:ss"
        );
        var enddate = moment(this.sldjForm.currentDate[1]).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
      let params = {
        systemsource: this.systemsource,
        page: this.currentPage,
        total: this.total,
        size: this.size,
        user: user,
        rqsttitle:this.sldjForm.title,
        tserialnum:this.sldjForm.number,
        begindate:begindate,
        enddate: enddate,
        tel:this.sldjForm.tel,
        keyword: this.sldjForm.keyword
      };
      getFinishListData(params).then(res => {
        if (res.code === 0) {
          console.log(res);
          this.listData = res.data.records;
          this.currentPage = res.data.current;
          this.current = res.data.current;
          this.size = res.data.size;
          this.total = res.data.total;
        }
      });
    },
    formatDates(row, column) {
      if (row.sdexpirationdate != null) {
        return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
      } else {
        return "";
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
  margin-bottom: 20px;
}
.el-input__inner {
  width: 200px !important;
}
.el-pagination {
  white-space: nowrap;
  padding: 2px 5px;
  color: #48576a;
  text-align: right;
  margin-top: 20px;
}
</style>