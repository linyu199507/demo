<!--综合查询-->
<template>
  <el-card class="comprehensive_query">
    <div slot="header" class="title">材料综合条件搜索查询</div>
    <el-form :model="materialLists" label-suffix="：" ref="materialLists" label-width="200px" style>
      <el-row>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="标题" prop="materialName">
            <el-input v-model="materialLists.materialName" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="工单号" prop="materialNum">
            <el-input v-model="materialLists.materialNum" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="登记时间" prop="registerDate">
            <el-date-picker
              v-model="materialLists.registerDate"
              type="datetimerange"
              align="right"
              placeholder="选择日期范围"
              :picker-options="pickerRegister"
            ></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col style="text-align: center">
          <el-button type="primary" @click="submitForm">查询</el-button>
          <el-button @click="resetForm('materialLists')">重置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>

<script>
import moment from "moment";
import { businessList } from "../../api/api";
export default {
  name: "MaterialQuery",
  data() {
    return {
      materialLists: {
        materialName: "",
        materialNum: "",
        registerDate: ""
      },
      dataForm: {
        materialName: "",
        materialNum: "",
        registerDate: ""
      }
    };
  },
  methods: {
    submitForm() {
      if (
        this.materialLists.materialName == "" &&
        this.materialLists.materialNum == "" &&
        this.materialLists.registerDate == ""
      ) {
        this.$message({
          showClose: true,
          message: "请输入至少一项你要查询的内容",
          type: "error"
        });
        return;
      } else {
        // console.log(this.materialLists.registerDate);
        if (
          this.materialLists.registerDate[0] != null &&
          this.materialLists.registerDate[1] != null
        ) {
          var begin = moment(this.materialLists.registerDate[0]).format(
            "YYYY-MM-DD hh:mm:ss"
          );
          var end = moment(this.materialLists.registerDate[1]).format(
            "YYYY-MM-DD hh:mm:ss"
          );
        } else {
          var begin = "";
          var end = "";
        }
       
       
        let params = {
         tserialnum:this.materialLists.materialNum,
         rqsttitle:this.materialLists.materialName,
         begindate:"",
         enddate:"",
         size: 10,
         page: 1
        };
        this.$router.push({
          path:"/tjfx/zhcx/cljlcx/cljlcxjg",
          query: params
        });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    pickerRegister() {
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
      ];
    },
  }
};
</script>

<style lang="scss">
.comprehensive_query {
  margin: 20px 20px 0 20px;
  box-shadow: none;
  min-height: 70vh;

  .el-form {
    margin-left: -40px;
    margin-right: 40px;
  }

  .el-form-item {
    margin-bottom: 20px;
  }
  .el-form-item1 {
    margin-bottom: 0px !important;
  }
  .el-form-item__content {
    line-height: normal;
    /*margin-left: 0px;*/
  }

  .el-date-editor.el-input {
    width: 100% !important;
  }
  .line {
    text-align: center;
    line-height: 36px;
    font-weight: bold;
  }
}
</style>
