<template>
  <section class="backlogNotarize">
    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-form :inline="true" :model="search" @submit.native.prevent>
        <el-form-item>
          <el-col>基础内容查询:</el-col>
        </el-form-item>
        <el-form-item>
          <el-input v-model="search.rqsttitle" @change="change(search.rqsttitle)" placeholder="请输入查询内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" native-type="submit" @click="selectlist">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <el-col :span="24" class="toolbar">
      <el-form :inline="true" :model="search" @submit.native.prevent>
        <el-form-item>
        <el-button style="float: right;" type="info" @click="home">返回首页</el-button>
        </el-form-item>
        <!--<el-form-item>-->
          <!--<el-button type="primary" native-type="submit" @click="openadd">添加</el-button>-->
        <!--</el-form-item>-->
        <!--<el-form-item>-->
          <!--<el-button type="primary" native-type="submit" @click="update">修改</el-button>-->
        <!--</el-form-item>-->
        <!--&lt;!&ndash;<el-button style="float: right;" type="danger" @click="batchRemove">删除</el-button>&ndash;&gt;-->
        <!--<el-form-item>-->
        <!--<el-button type="danger" @click="batchRemove">删除</el-button>-->
        <!--</el-form-item>-->
      </el-form>
    </el-col>

    <!--列表-->
    <el-table
      :data="basecodeform"
      highlight-current-row
      v-loading="complaintListLoading"
      @selection-change="selsChange"
      border
      style="width: 100%"
      :row-style="{cursor:'pointer'}"
    > <!--@cell-click="toFind"-->
      <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>
      <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
      <el-table-column prop="type" label="类型" min-width="6%" align="center"></el-table-column>
      <el-table-column prop="codeid" label="ID" min-width="12%" header-align="center"></el-table-column>
      <el-table-column prop="comment" label="名称" min-width="12%" header-align="center"></el-table-column>
      <!--<el-table-column prop="updatedate" label="更新时间" min-width="10%" align="center" :formatter="formatDate">-->
       <!--</el-table-column>-->
    </el-table>
    <br />
    <!--分页组件开始-->
    <el-col style="text-align: right;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 30, 50, 100,200]"
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
import {     listBasicData, deleteBasicData ,selectBasicData  } from "../../api/api";
import moment from "moment";

export default {
  data() {
    return {
      search: {
        serialnum: "",
        tserialnum: "",
        rqsttitle: ""
      },
      basecodeform: [],
        /*basecodeform:{
            comment:'',
            codeid:'',
            type:'',
        },*/

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
      //返回首页按钮
      home(){
          this.search.rqsttitle = ""
          this.findList()
      },
      //添加按钮
      openadd(){
          console.log(this.$route.query)
          this.$router.push({path: '/xtgl/jcsj/pz', query: {parentid:this.$route.query.parentid,type:this.$route.query.type,orglevel:this.$route.query.orglevel}});
          // this.$router.push({path: '/xtgl/jcsj/pz', query:{}});
      },
      //修改按钮
      update(){
          var id = this.sels.map(item => item.id).toString();
          var type = this.sels.map(item => item.type).toString();
          var codeid = this.sels.map(item => item.codeid).toString();
          var comment = this.sels.map(item => item.comment).toString();
          var parentid = this.sels.map(item => item.parentid).toString();
          var orglevel = this.sels.map(item => item.orglevel).toString();
          let param  = {
            id: id,
            type: type,
            codeid: codeid,
            comment: comment,
            parentid: parentid,
            orglevel: orglevel,
          }
          this.$router.push({path: '/xtgl/jcsj/edit', query: param});
      },

    handleCurrentChange(Page) {
          console.log(Page)
      this.pagination.current = Page;//页数
      console.log("下一页")
        this.findList();
        // this.selectlist();
    },
      handleSizeChange(size) {
          console.log("调整页面条数")
          this.pagination.size = size;
          this.findList();
      },
    // 输入时联动查询
    change(title){
      this.findList()
    },

    findList() {

        let params = {
            // page:'1',
            // size:'10',
            orglevel:"",
            page:this.pagination.current,
            size:this.pagination.size,
            comment:this.search.rqsttitle
        };
        this.complaintListLoading = true;
        listBasicData(params).then(res => {
            // console.log(res)
            this.pagination.page = res.data.current;
            this.pagination.total = res.data.total;
            this.pagination.size = res.data.size;
            // this.complaintList = res.data.records;
            this.basecodeform = res.data.records;
            this.complaintListLoading = false;
        });
    },
    toFind(row, column) {
        console.log(row)

        //阻止勾选复选框跳转
      if (column.property !== "select") {
          // console.log(row)
          // console.log(row.orglevel)
        let para = {
            page:1,
            size:'10',
            parentid:row.id,
            orglevel:row.orglevel,
            type:row.type,

        };console.log(para)
          listBasicData(para).then(res =>  {

            this.$router.push({path:"/xtgl/jcsj",query:{parentid:row.id,type:row.type,orglevel:row.orglevel}});
              console.log(res)
            //   console.log(row.size)

              this.pagination.page = row.current;
              // this.pagination.total = row.total;
              this.pagination.size = row.size;
              this.basecodeform  = res.data.records

        });

        // this.$router.push({ path: "/wddb/gdqr", query: para });
      }
    },
    formatDate(row, column) {
      return moment(row.resulttimemin).format("YYYY-MM-DD HH:ss");
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
          deleteBasicData(para).then(res => {
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
    },

      selectlist(){
          console.log(this.pagination.page)
          let params = {

              // orglevel:"",
              // page:this.pagination.current,this.pagination.current
              // page: this.pagination.current,
              // size:this.pagination.size,
              queries:"true",
              page:1,
              size:10,
              comment:this.search.rqsttitle
          };
          console.log(params)
          this.complaintListLoading = true;
          selectBasicData(params).then(res => {
              // console.log(res)
              this.pagination.page = res.data.current;
              this.pagination.total = res.data.total;
              this.pagination.size = res.data.size;
              // this.complaintList = res.data.records;
              this.basecodeform = res.data.records;
              this.complaintListLoading = false;
          });
      }

  },


//查询基础数据信息
    created() {
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
