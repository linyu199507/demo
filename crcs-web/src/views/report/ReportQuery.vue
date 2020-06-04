<!--报表查询-->
<template>
  <el-card id="report_query">
    <div slot="header" class="title">报表综合查询</div>
    <el-row>
      <el-col :xs="24" :sm="24" :md="{span:16,offset:4}" :lg="{span:14,offset:4}">
        <el-form
          :model="dataForm"
          :rules="rules"
          label-suffix="："
          ref="dataForm"
          label-width="200px"
          class="text_bold"
        >
          <el-form-item label="请选择报表业务类型" prop="reportType">
            <el-select
              v-model="dataForm.reportType"
              @change="changeReportType"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option label="消保报表" value="xbbb"></el-option>
              <el-option label="区市监局月报" value="qsjjyb"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择报表名称" prop="tableName">
            <el-select
              ref="tableNameRef"
              v-model="dataForm.tableName"
              placeholder="可搜索"
              filterable
              style="width: 100%"
              @visible-change="ifReportType"
            >
              <el-option
                v-for="item in tableNames"
                :label="item.name"
                :disabled="item.disabled"
                :value="item.tableFile"
                :key="item.tableNames"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择报表周期" prop="cycle">
            <el-col :span="11">
              <el-date-picker
                type="datetime"
                placeholder="开始时间"
                v-model="dataForm.begindate"
                style="width: 100%;"
                :picker-options="pickerOptions"
              ></el-date-picker>
            </el-col>
            <el-col style="text-align:center" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker
                type="datetime"
                placeholder="结束时间"
                v-model="dataForm.enddate"
                style="width: 100%;"
                :picker-options="pickerOptions"
              ></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="只查询汇总报表" prop="pool">
            <el-select v-model="dataForm.pool" placeholder="请选择" style="width: 100%">
              <el-option label="一般汇总" value="commonly"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择报表策略" prop="strategy">
            <el-select v-model="dataForm.strategy" placeholder="请选择" style="width: 100%">
              <el-option label="默认策略" value="default"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="请选择区域" prop="area" v-if="areaSelectShow">
            <el-select v-model="dataForm.area" placeholder="请选择" style="width: 100%">
              <el-option label="顺德区市场监督管理局" value="440681"></el-option>
              <el-option label="大良镇" value="440681001"></el-option>
              <el-option label="容桂镇" value="440681002"></el-option>
              <el-option label="伦教镇" value="440681003"></el-option>
              <el-option label="北滘镇" value="440681004"></el-option>
              <el-option label="陈村镇" value="440681005"></el-option>
              <el-option label="乐从镇" value="440681006"></el-option>
              <el-option label="勒流镇" value="440681007"></el-option>
              <el-option label="龙江镇" value="440681008"></el-option>
              <el-option label="杏坛镇" value="440681009"></el-option>
              <el-option label="均安镇" value="440681010"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">查询</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "ReportQuery",
  data() {
    return {
      display: "",
      tableNames: [],
      areaSelectShow: true,
      dataForm: {
        reportType: undefined,
        area: undefined,
        strategy: "default",
        pool: "commonly",
        tableName: undefined
      },
      rules: {
        reportType: [
          { required: true, message: "请选择报业务类型", trigger: "change" }
        ],
        area: [{ required: true, message: "请选择区域", trigger: "change" }],
        strategy: [
          { required: true, message: "请选择报表策略", trigger: "change" }
        ],
        pool: [{ required: true, message: "请选择", trigger: "change" }],
        tableName: [
          { required: true, message: "请选择报表名称", trigger: "change" }
        ],
        cycle: [
          {
            required: true,
            validator: (rule, value, callback) => {
              if (!this.dataForm.begindate)
                callback(new Error("请选择开始时间"));
              if (!this.dataForm.enddate) callback(new Error("请选择结束时间"));
              if (this.dataForm.begindate > this.dataForm.enddate) {
                callback(new Error("开始时间不能大于结束时间"));
              }
              callback();
            }
          }
        ]
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          let body = this.dataForm;

            if(typeof body.begindate === 'object'){
                body.begindate = this.$moment(body.begindate.getTime()).format(
                    "YYYY-MM-DD HH:mm:ss"
                );
            }
            if(typeof body.enddate === 'object'){
                body.enddate = this.$moment(body.enddate.getTime()).format(
                    "YYYY-MM-DD HH:mm:ss"
                );
            }
          // this.$router.push({
            //   path: "/tjfx/tjbb/bbcx/cxjg",
            //   query: body
            // });
            let url = this.$router.resolve({
                path: "/tjfx/tjbb/bbcx/cxjg",
                query: body
            });
            window.open(url.href,"_blank")
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.dataForm.resetFields();
    },
    changeReportType(value) {
      this.dataForm.tableName = undefined;
      if ("xbbb" === value) {
        this.areaSelectShow = true;
        this.tableNames = [
          {
            name: "综合3表-消费维权基本情况统计表",
            tableFile: "消费维权基本情况统计表(综合3表).ureport.xml"
          },
          {
            name: "消保1表-查处侵害消费者权益案件情况统计表",
            tableFile: "查处侵害消费者权益案件情况统计表(消保1表).ureport.xml"
          },
          {
            name: "消保2表-处理消费者申诉情况统计表",
            tableFile: "处理消费者申诉情况统计表(消保2表).ureport.xml"
          },
          {
            name: "消保3表-处理商品消费申诉情况统计表",
            tableFile: "处理商品消费申诉情况统计表(消保3表).ureport.xml"
          },
          {
            name: "消保4表-处理服务消费申诉情况统计表",
            tableFile: "处理服务消费申诉情况统计表(消保4表).ureport.xml"
          },
          {
            name: "消保5表-受理处理举报情况统计表",
            tableFile: "受理处理举报情况统计表(消保5表).ureport.xml"
          },
          {
            name: "消保6表-受理消费者咨询情况统计表",
            tableFile: "受理消费者咨询情况统计表(消保6表).ureport.xml"
          }
        ];
      } else if ("qsjjyb" === value) {
        this.areaSelectShow = false;
        this.tableNames = [
          {
            name: "一、各途径情况统计表",
            tableFile: "一、各途径情况统计表.ureport.xml"
          },
          {
            name: "二、各单位办结情况统计表",
            tableFile: "二、各单位办结情况统计表.ureport.xml"
          },
          {
            name: "三、各类型投诉举报情况汇总表",
            tableFile: "三、各类型投诉举报情况汇总表.ureport.xml"
          },
          {
            name: "四、各类投诉情况统计表",
            tableFile: "四、各类投诉情况统计表.ureport.xml"
          },
          {
            name: "五、各类举报线索情况统计表",
            tableFile: "五、各类举报线索情况统计表.ureport.xml"
          },
          // {
          //   name: "六、立案情况汇总表",
          //   tableFile: "六、立案情况汇总表.ureport.xml"
          // },
            {
            name: "六、立案情况汇总表(新)",
            tableFile: "六、立案情况汇总表(新).ureport.xml"
          },
          {
            name: "七、诉转案情况统计表",
            tableFile: "七、诉转案情况统计表.ureport.xml"
          },
          {
            name: "八、消费维权投诉举报办理情况统计表",
            tableFile: "八、消费维权投诉举报办理情况统计表.ureport.xml"
          }
        ];
      }
    },
    ifReportType() {
      //判断是否选择部门
      if (!this.dataForm.reportType) {
        this.$refs.tableNameRef.visible = false;
        this.$alert("请先选择报表业务类型", "提示", {
          closeOnClickModal: true,
          confirmButtonText: "确定",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style lang="scss" scoped>
#report_query {
  margin: 20px 20px 0 20px;
  box-shadow: none;

  .el-form-item {
    margin-bottom: 20px;
  }
}
</style>
