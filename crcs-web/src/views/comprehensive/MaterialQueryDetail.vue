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
              prop="attachfilename"
              label="文件标题"
              min-width="30%"
              align="center"
              header-align="center"
              show-overflow-tooltip
            ></el-table-column>

            <!-- <el-table-column
              prop="tserialnum"
              label="工单号"
              min-width="15%"
              align="center"
              header-align="center"
              show-overflow-tooltip
            ></el-table-column>-->
            <el-table-column
              prop="updatadate"
              label="上传时间"
              min-width="30%"
              align="center"
              :formatter="formatDate"
            ></el-table-column>

            <el-table-column label="操作" min-width>
              <template slot-scope="scope">
                <!-- <el-button
                  type="primary"
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
                >查看</el-button> -->
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDownload(scope.$index, scope.row)"
                >下载</el-button>
              </template>
            </el-table-column>
            <!-- </el-table-column> -->
          </el-table>
          <!--分页组件开始-->
          <el-col style="text-align: right;margin-top: 10px">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :total="this.total"
              :page-size="size"
              :page-sizes="[10, 20, 30, 40,50]"
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
import { getMaterialLists } from "../../api/api";
import moment from "moment";
export default {
  name: "ComprehensiveList",
  data() {
    return {
      // pageNum: 1,
      // 每页默认显示10
      PageSize: 10,
      materialData: [],
      total: 0,
      current: 1,
      size: 10,
      activeName: "file",
      complaintListLoading: false
    };
  },

  methods: {
    handleEdit(index, row) {
      console.log(index, row);
      const rowId = this.materialData.id;
      const rowName = this.materialData.attachfilename;
      let url = row.filesavepath;
      window.open(`${process.env.Download_URL}` + url);
    },
    handleDownload(index, row) {
      const rowId = this.materialData.id;
      const rowName = this.materialData.attachfilename;
      let url = row.filesavepath;
      // console.log(url)
      // console.log(index)
      // console.log(row)
      window.location.href = `${process.env.Download_URL}` + url;
    },
    handleSelectionChange(val) {
      // this.multipleSelection = val;
      this.PageSize = val;
      this.getMaterialLists(
        this.$route.query.tserialnum,
        this.$route.query.rqsttitle,
        this.$route.query.begindate,
        this.$route.query.enddate,
        this.PageSize,
        this.current,
        this.$route.query.caseguid
      );
    },
    formatDate(row, column) {
      console.log(row.uploadtime);
      console.log(row.updatedate);
      return moment(row.uploadtime).format("YYYY-MM-DD HH:mm:ss");
    },

    handleSizeChange(val) {},
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      let currentpage = val;
      this.getMaterialLists(
        this.$route.query.tserialnum,
        this.$route.query.rqsttitle,
        this.$route.query.begindate,
        this.$route.query.enddate,
        this.PageSize,
        currentpage,
        this.$route.query.caseguid
      );
    },
    tomaterialDetail(row, column, cell, event) {},
    handleClick(tab, event) {
      console.log(tab, event);
    },
    downLoadFiles() {},
    getMaterialLists(
      tserialnum,
      rqsttitle,
      begindate,
      enddate,
      pageSize,
      current,
      caseguid
    ) {
      let params = {
        tserialnum: tserialnum,
        rqsttitle: rqsttitle,
        begindate: begindate,
        enddate: enddate,
        size: pageSize,
        page: current,
        caseguid: caseguid
      };
      getMaterialLists(params).then(res => {
        if (res.code === 0) {
          this.materialData = res.data.records;
          this.total = res.data.total;
        }
      });
    }
  },

  mounted() {
    // let caseguid = this.$route.query.caseguid;
    // console.log(caseguid);
    this.getMaterialLists(
      this.$route.query.tserialnum,
      this.$route.query.rqsttitle,
      this.$route.query.begindate,
      this.$route.query.enddate,
      this.PageSize,
      this.current,
      this.$route.query.caseguid
    );
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
