<template>
  <section id="comprehensive_list">
    <el-card class="box-card pb20 mt20">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="材料文件" name="file">
          <el-table
            :data="materialData"
            highlight-current-row
            v-if="activeName === 'file'"
            @cell-click="tomaterialDetail"
            v-loading="complaintListLoading"
            @selection-change="handleSelectionChange"
            border
            :row-style="{cursor:'pointer'}"
          >
            <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>

            <el-table-column type="index" label="序号" min-width="8%" align="center"></el-table-column>
            <el-table-column
              prop="rqsttitle"
              label="工单标题"
              min-width="15%"
              align="center"
              header-align="center"
              show-overflow-tooltip
            ></el-table-column>
            <el-table-column
              prop="tserialnum"
              label="工单号"
              min-width="15%"
              align="center"
              header-align="center"
              show-overflow-tooltip
            ></el-table-column>
            <el-table-column
              prop="assigndate"
              label="登记时间"
              min-width="8%"
              align="center"
              :formatter="formatDate"
            ></el-table-column>
          </el-table>
          <!--分页组件开始-->
          <el-col style="text-align: right;margin-top: 10px">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :total="this.total"
              :page-size="size"
              :page-sizes="[1, 2, 30, 40,50]"
              layout="slot,total, sizes, prev, pager, next, jumper"
            >
              <span>
                第 {{this.current}} 页 /
                共 {{(parseInt((this.total + this.size - 1)/this.size))}} 页
              </span>
            </el-pagination>
          </el-col>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </section>
</template>

<script>
import { businessList, getMaterialList } from "../../api/api";
import moment from "moment";
export default {
  name: "ComprehensiveList",
  data() {
    return {
      // 每页默认显示10
      PageSize: 10,
      //
      // pageNum: 1,
      materialData: [],
      total: 0,
      current: 1,
      size: 1,
      activeName: "file",
      complaintListLoading: false
    };
  },

  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
    formatDate(row, column) {
      return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
    },
    getFileLists(enddate, begindate, rqsttitle, tserialnum) {
      let params = {
        tserialnum: tserialnum,
        rqsttitle: rqsttitle,
        begindate: begindate,
        enddate: enddate,
        size: 10,
        page: 1
      };
      businessList(params).then(res => {
        console.log(res);
        if (res.code === 0) {
          this.materialData = res.data.records;
          this.total = res.data.total;
        }
      });
    },
    handleSizeChange(val) {
      this.size = val;
      console.log(val);
      let params = {
        tserialnum: this.$route.query.tserialnum,
        rqsttitle: this.$route.query.rqsttitle,
        begindate: this.$route.query.begindate,
        enddate: this.$route.query.enddate,
        size: this.size,
        page: this.$route.query.page
      };
      businessList(params).then(res => {
        // console.log(res);
        if (res.code === 0) {
          this.materialData = res.data.records;
          this.total = res.data.total;
        }
      });
      // console.log(`每页 ${val} 条`);
      // console.log(val);
    },
    handleCurrentChange(val) {
      let current = val;
      let params = {
        tserialnum: "",
        rqsttitle: "",
        begindate: "",
        enddate: "",
        size: "",
        page: current
      };
      businessList(params).then(res => {
        // console.log(res);
        if (res.code === 0) {
          this.materialData = res.data.records;
          this.total = res.data.total;
        }
      });
    },
    tomaterialDetail(row, column, cell, event) {
      // let caseguid = row.caseguid
      let params = {
        tserialnum: "",
        rqsttitle: "",
        begindate: "",
        enddate: "",
        size: 10,
        page: 1,
        caseguid: row.caseguid
      };
      this.$router.push({
        path: "/tjfx/zhcx/cljlcx/clxq",
        query: { caseguid: params.caseguid }
      });
    },
    handleClick(tab, event) {
      console.log(tab, event);
    }
  },

  mounted() {
    console.log(this.$route.query);
    // if (JSON.stringify(this.$route.query) != "{}") {
      this.getFileLists(
        this.$route.query.enddate,
        this.$route.query.begindate,
        this.$route.query.rqsttitle,
        this.$route.query.tserialnum
      );
    // }
  },
  created() {}
};
</script>

<style lang="scss" scoped>
.operation {
  /*background: #f2f2f2;*/
  margin: 10px 0 10px 0;
}
</style>
<style>
#comprehensive_list .el-table__body-wrapper {
  overflow-x: auto;
}
.el-card {
  /* padding: 0 20px; */
}
</style>
