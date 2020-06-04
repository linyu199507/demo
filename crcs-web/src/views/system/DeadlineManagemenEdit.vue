<!--业务时限编辑页面-->
<template>
  <el-card class="business_edit">
    <div slot="header" class="title">业务时限配置编辑</div>
    <el-form
      :model="deadlinemanagement"
      label-suffix="："
      ref="deadlineManagement"
      label-width="200px"
      :rules="deadlineRules"
    >
      <el-row>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="业务分类" prop="bustype">
            <el-select v-model="deadlinemanagement.bustype" placeholder="请选择" style="width: 100%">
              <el-option label="咨询类" value="01"></el-option>
              <el-option label="消费投诉类" value="02"></el-option>
              <el-option label="经济违法类" value="03"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="工单级别" prop="level">
            <el-select v-model="deadlinemanagement.level" placeholder="请选择" style="width: 100%">
              <el-option label="普通" value="01"></el-option>
              <el-option label="紧急" value="02"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="时限类型" prop="deadlinetype">
            <el-select
              v-model="deadlinemanagement.deadlinetype"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option label="正常" value="normal"></el-option>
              <el-option label="黄牌" value="yellow"></el-option>
              <el-option label="红牌" value="red"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="初步意见时限" prop="interimreplytime">
            <el-input
              v-model.number="deadlinemanagement.interimreplytime"
              placeholder="请输入初步意见时限"
              class="input-text"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="4">
          <el-form-item prop="interimreplyunit" label-width="20px">
            <el-select
              v-model="deadlinemanagement.interimreplyunit"
              placeholder="请选择时限单位"
              style="width: 100%"
            >
              <el-option label="工作日" value="day"></el-option>
              <el-option label="小时" value="hour"></el-option>
              <el-option label="分钟" value="minute"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="是否受理时限" prop="accepttime">
            <el-input
              v-model.number="deadlinemanagement.accepttime"
              placeholder="请输入是否受理时限"
              class="input-text"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="4">
          <el-form-item prop="acceptunit" label-width="20px">
            <el-select
              v-model="deadlinemanagement.acceptunit"
              placeholder="请选择时限单位"
              style="width: 100%"
            >
              <el-option label="工作日" value="day"></el-option>
              <el-option label="小时" value="hour"></el-option>
              <el-option label="分钟" value="minute"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="处理结果/是否立案时限" prop="handletime">
            <el-input
              v-model.number="deadlinemanagement.handletime"
              placeholder="请输入处理结果/是否立案时限"
              class="input-text"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="4">
          <el-form-item prop="handleunit" label-width="20px">
            <el-select
              v-model="deadlinemanagement.handleunit"
              placeholder="请选择时限单位"
              style="width: 100%"
            >
              <el-option label="工作日" value="day"></el-option>
              <el-option label="小时" value="hour"></el-option>
              <el-option label="分钟" value="minute"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="预警时间" prop="warningtime">
            <el-input
              v-model.number="deadlinemanagement.warningtime"
              placeholder="预警时间"
              class="input-text"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="4">
          <el-form-item prop="warningunit" label-width="20px">
            <el-select
              v-model="deadlinemanagement.warningunit"
              placeholder="请选择时限单位"
              style="width: 100%"
            >
              <el-option label="工作日" value="day"></el-option>
              <el-option label="小时" value="hour"></el-option>
              <el-option label="分钟" value="minute"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col style="text-align: center">
          <el-button type="primary" @click="submitForm" :loading="loading">修改</el-button>
          <el-button @click="resetForm('deadlineManagement')">重置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>

<script>
import {
  saveDeadlinemanagement,
  searchDeadlinemanagement
} from "../../api/api";
import moment from "moment";

export default {
  name: "DeadlineManagemenEdit",
  data() {
    return {
      loading: false,
      deadlinemanagement:this.$route.query,
      deadlineRules: {
        level: [
          { required: true, message: "请选择工单级别", trigger: "change" }
        ],
        bustype: [
          { required: true, message: "请选择业务分类", trigger: "change" }
        ],
        deadlinetype: [
          { required: true, message: "请选择时限类型", trigger: "change" }
        ],
        interimreplytime: [
          {
            required: true,
            type: "number",
            message: "请输入初步意见时限",
            trigger: "blur"
          }
        ],
        interimreplyunit: [
          {
            required: true,
            message: "请选择初步意见时限单位",
            trigger: "change"
          }
        ],
        accepttime: [
          {
            required: true,
            type: "number",
            message: "请输入是否受理时限",
            trigger: "blur"
          }
        ],
        acceptunit: [
          { required: true, message: "是否受理时限单位", trigger: "change" }
        ],
        handletime: [
          {
            required: true,
            type: "number",
            message: "请输入处理结果/是否立案时限",
            trigger: "blur"
          }
        ],
        handleunit: [
          {
            required: true,
            message: "请选择处理结果/是否立案时限单位",
            trigger: "change"
          }
        ],
        warningtime: [
          {
            required: true,
            type: "number",
            message: "请输入预警时间",
            trigger: "blur"
          }
        ],
        warningunit: [
          { required: true, message: "请选择预警时间单位", trigger: "change" }
        ]
      }
    };
  },
  methods: {
    submitForm() {
      let validation = false;
      this.$refs.deadlineManagement.validate(valid => (validation = valid));
      //通过提交
      if (validation) {
        this.$confirm("请确认无误提交?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.loading = true;
          let para = {
            deadlinemanagement: this.deadlinemanagement
          };
          saveDeadlinemanagement(para).then(res => {
            if (res.code === 0) {
               this.$message({
                  message: '修改成功！',
                  type: 'success',
                  duration:'1000'
              });
              this.$router.push("/xtgl/ywsxpz");

            } else {
              this.$alert("修改失败！", "提示", {
                type: "error",
                confirmButtonText: "确定"
              });
            }
            this.loading = false;
          });
        });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    //转换数字
    let data = this.$route.query;
    data.interimreplytime = Number(data.interimreplytime);
    data.accepttime = Number(data.accepttime);
    data.handletime = Number(data.handletime);
    data.warningtime = Number(data.warningtime);
    this.deadlinemanagement = data;
  }
};
</script>

<style lang="scss">
.business_edit {
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

  .el-form-item__content {
    line-height: normal;
    margin-left: 20px;
  }

  .title {
    /*font-weight: bold;*/
    /*text-align: center;*/
  }

  .el-card__header {
    /*padding: 0;*/
  }

  .line {
    text-align: center;
    line-height: 36px;
    font-weight: bold;
  }
}
</style>
