<template>
  <div>
    <el-row type="flex" class="row-bg" justify="space-between">
      <el-form label-width="120px">
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="标题：">
            <el-input placeholder="请输入标题" @change="getSearchList" v-model="searchForm.title" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="表单号：">
            <el-input placeholder="请输入表单号" @change="getSearchList" v-model="searchForm.number" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="关键词：">
            <el-input placeholder="请输入关键词" @change="getSearchList" v-model="searchForm.keyword" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="电话：">
            <el-input placeholder="请输入电话" @change="getSearchList" v-model="searchForm.tel" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="工单号：">
            <el-input placeholder="请输入工单号" @change="getSearchList" v-model="searchForm.tserialnum" style="width:200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="当前环节：">
            <el-select
                  v-model="searchForm.currentLink"
                  clearable
                  placeholder="请选择"
                  style="width: 200px"
                  @change="getSearchList"
                >
                  <el-option
                    v-for="item in currentLinkOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item label="登记时间：">
            <el-date-picker
              style="width:300px"
              v-model="searchForm.currentDate"
              type="datetimerange"
              placeholder="选择时间范围"
              @change="getSearchList"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="8">
          <el-form-item>
            <el-button type="primary" @click="getSearchList">查询</el-button>
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
      <el-table-column prop="focus" label="表单号" min-width="8%" align="center"></el-table-column>
      <el-table-column prop="tserialnum" label="工单号" min-width="13%" align="center"></el-table-column>
      <el-table-column prop="rqsttitle" label="工单标题" min-width="10%" align="center"></el-table-column>
      <el-table-column
        prop="operate"
        label="当前环节"
        min-width="4%"
        align="center"
        header-align="center"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="accordtype"
        label="登记内容"
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
      registerDate: "",
      listData: [],
      complaintListLoading: false,
      systemsource: "02",
      currentPage: 1,
      size: 10,
      total: 0,
      searchForm: {
        title:'',
        number: '',
        keyword:'',
        tel: '',
        tserialnum: '',
        currentLink: '',
        currentDate:''
      },
      currentLinkOptions: [
        {
          value: "begin",
          label: "开始"
        },
        {
          value: "accept",
          label: "受理"
        },
        {
          value: "todispatch",
          label: "待分流"
        },
        {
          value: "noAccept",
          label: "不受理"
        },
        {
          value: "registerCase",
          label: "立案"
        },
        {
          value: "noRegisterCase",
          label: "不予立案"
        },
        {
          value: "leaderApr",
          label: "领导审核"
        },
        {
          value: "moveToLowerOrg",
          label: "分派下级机关"
        },
        {
          value: "moveToLowerDept",
          label: "分派其他部门"
        },
        {
          value: "moveToLowerPersonnel",
          label: "分派人员"
        },
        {
          value: "regionAudit",
          label: "区局处理"
        },
        {
          value: "handleFeedback",
          label: "处理反馈"
        },
        {
          value: "end",
          label: "结束"
        },
        {
          value: "backForward",
          label: "退回"
        }
      ],
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
  created() {
    console.log(this.tableTitle);
  },
  methods: {
    handleEdit(index, row) {},
    selsChange: function(sels) {
      this.sels = sels;
    },
    getSearchList(){
      this.getsldjList();
    },
    handleSizeChange(val) {
      // let changeSize = val;
      this.size = val;
      this.getsldjList();
    },
    formatDate(row, column) {
      return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getsldjList();
    },
    getlistData() {},
    getsldjList() {
      let user = JSON.parse(localStorage.getItem("user"));
      if (
        this.searchForm.currentDate[0] !== undefined &&
        this.searchForm.currentDate[1] !== undefined
      ) {
        var begindate = moment(this.searchForm.currentDate[0]).format(
          "YYYY-MM-DD HH:mm:ss"
        );
        var enddate = moment(this.searchForm.currentDate[1]).format(
          "YYYY-MM-DD HH:mm:ss"
        );
      }
      let params = {
        systemsource: this.systemsource,
        page: this.currentPage,
        total: this.total,
        size: this.size,
        user: user,
        rqsttitle:this.searchForm.title,
        serialnum:this.searchForm.number,
        keyword:this.searchForm.keyword,
        tel:this.searchForm.tel,
        tserialnum:this.searchForm.tserialnum,
        currentLink:this.searchForm.currentLink,
        begindate: begindate,
        enddate: enddate
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