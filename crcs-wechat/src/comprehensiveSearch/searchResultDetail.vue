<template>
  <div ref="main-container">
    <!-- <x-button class="control-btn" type="primary" @click.native="triggerBus('tabBus')" v-if="user.primaryKey=='80b309e6-bb1d-49ba-81e8-f87c542e4f2c'">在办意见</x-button>
    <x-button class="control-btn" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0' && nodeStr != 'end'&& user.aicPermissions.dealdepartment" type="primary" @click.native="submitWithdraw">待办收回</x-button> -->
    <!--受理登记待办--经济违法行为表单-->
    <economyInfoLocal
      :data="comeconomicmodule"
      :task="tasklist"
      v-if="formType==='comeconomicmodule' && systemsource==='01'"
    />
    <!--受理登记待办--咨询登记表单-->
    <consultInfoLocal
      :data="commommodule"
      :task="tasklist"
      v-if="formType==='commommodule' && systemsource==='01'"
    />
    <!--受理登记待办--消费投诉登记-->
    <consumeInfoLocal
      :data="complaintmodule"
      :task="tasklist"
      v-if="formType==='complaintmodule' && systemsource==='01'"
    />
    <!-- 12345系统--经济违法行为表单 -->
    <economyInfo12345
      :data="comeconomicmodule"
      :task="tasklist"
      v-if="formType==='comeconomicmodule' && systemsource==='02'"
    />
    <!--12345系统--消费表单-->
    <complaintmoduleInfo12345
      :data="complaintmodule"
      :task="tasklist"
      v-if="formType==='complaintmodule' && systemsource==='02'"
    />
    <!--12345系统--咨询表单-->
    <consultationInfo12345
      :data="commommodule"
      :task="tasklist"
      v-if="formType==='commommodule' && systemsource==='02'"
    />
    <!--12315系统--投诉表单-->
    <complaintmoduleInfo12315
      :data="complaintmodule"
      :task="tasklist"
      v-if="formType==='complaintmodule' && systemsource==='03'"
    />
    <!--12315系统--举报表单-->
    <economyInfo12315
      :data="comeconomicmodule"
      :task="tasklist"
      v-if="formType==='comeconomicmodule' && systemsource==='03'"
    />
    <card :header="{title: '办理跟踪'}">
      <div slot="content" class="card-box">
        <businessTracking :data="opinionList" />
      </div>
    </card>
    <!-- <group :title="'待办收回'" v-if="operating==='takeBack'">
      <popup-picker
        title="收回原因"
        :show-name="true"
        v-model="takeBack.reason"
        :data="takeBackOptions00"
        placeholder="请选择待办收回原因"
        v-if="systemsource!=='03'"
      ></popup-picker>
      <popup-picker
        title="收回原因"
        :show-name="true"
        v-model="takeBack.reason"
        :data="takeBackOptions03"
        placeholder="请选择待办收回原因"
        v-if="systemsource==='03'"
      ></popup-picker>
      <x-textarea title="收回描述" v-model="takeBack.opinion" placeholder="请输入收回描述（1000字内）"></x-textarea>
        <div class="submit">
            <x-button type="primary" mini @click.native="submitApproval">提交</x-button>
            <x-button type="mini" @click.native="cancelTab">取消</x-button>
        </div>
    </group>
    <group :title="'在办意见'" v-if="operating==='tabBus'">
      <x-textarea title="反馈内容" v-model="opinionForm.opinion" placeholder="请输入反馈内容（3000字内）"></x-textarea>
        <div class="submit">
            <x-button type="primary" mini @click.native="copyOpinion('tabBus')">提交</x-button>
            <x-button type="mini" @click.native="cancelTab">取消</x-button>
        </div>
    </group> -->
  </div>
</template>

<script>
import { searchBaseInfo } from "../api/convenient12345";
import { next } from "../api/api";
import economyInfoLocal from "../components/task-list-detail/economyInfoLocal";
import consultInfoLocal from "../components/task-list-detail/consultInfoLocal";
import consumeInfoLocal from "../components/task-list-detail/consumeInfoLocal";
import economyInfo12345 from "../components/task-list-detail/economyInfo12345";
import complaintmoduleInfo12345 from "../components/task-list-detail/complaintmoduleInfo12345";
import consultationInfo12345 from "../components/task-list-detail/consultationInfo12345";
import complaintmoduleInfo12315 from "../components/task-list-detail/complaintmoduleInfo12315";
import economyInfo12315 from "../components/task-list-detail/economyInfo12315";
import businessTracking from "../components/businessTracking";
export default {
  components: {
    economyInfoLocal,
    consultInfoLocal,
    consumeInfoLocal,
    economyInfo12345,
    complaintmoduleInfo12345,
    consultationInfo12345,
    complaintmoduleInfo12315,
    economyInfo12315,
    businessTracking
  },
  data() {
    return {
      takeBackOptions00: [
        [
          { name: "缺失要素", value: "缺失要素" },
          { name: "表述不清", value: "表述不清" },
          { name: "类型有误", value: "类型有误" },
          { name: "地址不详", value: "地址不详" },
          { name: "已有知识", value: "已有知识" },
          { name: "重复受理", value: "重复受理" },
          { name: "市民撤诉", value: "市民撤诉" },
          { name: "不属本职", value: "不属本职" },
          { name: "其他原因", value: "其他原因" }
        ]
      ],
      takeBackOptions03: [
        [
          { name: "不属实", value: "不属实" },
          { name: "其他", value: "其他" }
        ]
      ],
      takeBack: {
        reason: [""],
        opinion: ""
      },
      commomform: null,
      complaintform: null,
      economyform: null,
      processingCollapse: ["processing", "tracking"],
      tasklist: {},
      casedata: {},
      loading: true,
      formType: "",
      exportLoading: false,
      formTypeStr: "",
      systemsource: this.$route.query.systemsource,
      opinionList: [],
      aictabs: "economyFrom",
      isOperation: false,
      dialogFormVisible: false,
      operationType: "",
      commommodule: {},
      comeconomicmodule: {},
      complaintmodule: {},
      feedbackForm: {
        isCase: undefined,
        resolution: ""
      },
      opinionForm: {},
      opinionFormRules: {
        opinion: [{ required: true, message: "请输入内容", trigger: "blur" }]
      },
      feedbackFormRules: {
        isCase: [{ required: true, message: "请选择是否销案" }],
        leader: [{ required: true, message: "请选择领导" }],
        resolution: [
          { required: true, message: "请输入审核意见", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ]
      },
      goBackForm: {
        reason: undefined
      },
      goBackFormRules: {
        reason: [
          { required: true, message: "请输入退回理由", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ]
      },
      assignmentForm: {
        unit: undefined,
        opinion: undefined
      },
      assignmentFormRules: {
        unit: [{ required: true, message: "请选择成员单位" }],
        opinion: [
          { required: true, message: "请输入分派意见", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ]
      },
      delayForm: {
        unit: undefined,
        limit: undefined,
        reason: undefined
      },
      delayFormRules: {
        unit: [{ required: true, message: "请输入延期时限" }],
        limit: [{ required: true, message: "请选择延期时间单位" }],
        reason: [
          { required: true, message: "请输入延期原因", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ]
      },
      user: JSON.parse(localStorage.getItem("user")),
      nodeStr: undefined,
      operating: "",
      takeBackRules: {
        reason: [
          { required: true, message: "请选择收回原因", trigger: "change" }
        ],
        opinion: [
          { required: true, message: "请输入收回描述", trigger: "blur" },
          { max: 1000, message: "不能超过1000字符", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    //获取id，否则跳转
    let { id, caseguid, systemsource } = this.$route.query;
    if (!id || !caseguid) {
      // this.$router.push('/wddb/bxtdb')
      return;
    }
    // let systemsource = '01'
    // //获取工单信息
    searchBaseInfo({ id, caseguid, systemsource })
      .then(res => {
        if (res.code === 0) {
          if (res.data.economyform != null) {
            //经济表单
            this.formType = "comeconomicmodule";
            this.formTypeStr = "经济违法行为表单";
            this.comeconomicmodule = res.data.economyform;
            console.log(this.comeconomicmodule);
          } else if (res.data.complaintform != null) {
            //消费投诉表单
            this.formType = "complaintmodule";
            this.formTypeStr = "消费投诉表单";
            this.complaintmodule = res.data.complaintform;
            this.$router.resolve({
              path: "tjfx/zhcx/ywjlcx/cxjg/ywjlxq",
              query: this.serialnum
            });
          } else if (res.data.commomform) {
            //咨询登记
            this.formType = "commommodule";
            this.formTypeStr = "咨询登记";
            this.commommodule = res.data.commomform;
          } else {
            //找不到对的工单信息
            alert("找不到对应的工单");
            // this.$router.push('/wddb/bxtdb')
          }
          this.opinionList = res.data.opinionList;
          this.tasklist = res.data.tasklist;
          console.log("=============");
          console.log(this.opinionList);
          console.log(this.systemsource);
          console.log(this.formType);
          this.nodeStr = res.data.tasklist.nextnodeid;
          this.loading = false;
        }
      })
      .catch(error => {
        console.error(error);
        alert("请求失败");
      });
  },
  methods: {
    flow({ name }) {
      // if ('operationDom' !== name) {
      //     this.isOperation = false;
      // }
      if (name !== "takeBack") {
        this.operating = "";
        this.aictabs = "economyFrom";
      }
    },
    reasonChange() {
      let reason = this.takeBack.reason[0] ? this.takeBack.reason[0] : "";
      if ("缺失要素" == reason) {
        this.takeBack.opinion = "此单收回，请核实补充以下要素：xxx。";
      } else if ("表述不清" == reason) {
        this.takeBack.opinion = "此单收回，请核实修改以下内容：xxx。";
      } else if ("类型有误" == reason) {
        this.takeBack.opinion = "此单收回，请修改表单类型为：xxx。";
      } else if ("地址不详" == reason) {
        this.takeBack.opinion = "此单收回，地址不详细，请核实详细地址。";
      } else if ("已有知识" == reason) {
        this.takeBack.opinion =
          "此单收回，根据知识库信息编码：xxx，请按知识点指引市民。";
      } else if ("重复受理" == reason) {
        this.takeBack.opinion =
          "此单收回，此工单与另一工单（表单号：xxx）属重复受理。";
      } else if ("市民撤诉" == reason) {
        this.takeBack.opinion =
          "此单收回，经我局与市民核实，市民表示无需我局继续跟进此工单。";
      } else if ("不属本职" == reason) {
        this.takeBack.opinion =
          "此单收回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。";
      } else {
        this.takeBack.opinion = "其它原因";
      }
    },
    // 待办收回
    submitWithdraw() {
      this.operating = "takeBack";
      this.aictabs = "takeBack";
    },
    //在办反馈 msx
    triggerBus(value) {
      this.operating = "tabBus";
      this.aictabs = "tabBus";
    },
    //确认收回
    submitApproval() {
      //工单收回
      let flag = confirm("是否将该工单收回到您的待办中?");
      if (!flag) return;
      this.submitLoading = true;
      let data;
      if ("commommodule" === this.formType) {
        data = this.commommodule;
      } else if ("complaintmodule" === this.formType) {
        data = this.complaintmodule;
      } else if ("comeconomicmodule" === this.formType) {
        data = this.comeconomicmodule;
      }
      let params = {
        user: this.user,
        formType: this.formType,
        node: this.nodeStr,
        nextNodeId: "withdraw",
        data: data,
        opinion: this.takeBack.opinion,
        backreason: this.takeBack.reason[0] ? this.takeBack.reason[0]:''
      };
      next(params)
        .then(data => {
          if (data.code === 0) {
            alert('提交成功')
            // if (this.systemsource === "02") {
            //   this.$router.push("/tjfx/zhcx/cqjlcx");
            // } else if (this.systemsource === "03") {
            //   this.$router.push("/tjfx/zhcx/wbjlcx");
            // } else {
            //   this.$router.push("/tjfx/zhcx/sldjcx");
            // }
            this.$router.back(-1);
          } else {
            alert('提交失败！')
          }
          this.submitLoading = false;
        })
        .catch(data => {
          alert('提交失败！')
          this.submitLoading = false;
        });
    },

    copyOpinion(value) {
      //反馈意见填写
      // return
      let validation = false;
      this.$refs.opinionForm.validate(valid => (validation = valid));
      if (!validation) return;
      this.$confirm("请确认无误提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.submitLoading = true;
        let params = {
          user: this.user,
          caseguid: this.$route.query.caseguid,
          // node: this.nodeStr,
          // nextNodeId: "tabBus",
          opinion: this.opinionForm.opinion,
          type: value,
          formtype: this.formType
        };
        console.log(params);
        toProcessing(params)
          .then(data => {
            if (data.code == 0) {
              this.$message({
                message: "提交成功！",
                type: "success",
                duration: "1000"
              });
              // this.cancelTab()
              location.reload();
            } else {
              alert('提交失败！')
            }
            this.submitLoading = false;
          })
          .catch(data => {
            alert('提交失败！')
            this.submitLoading = false;
          });
      });
    },
    //取消收回
    cancelTab() {
      this.operating = "";
      this.aictabs = "economyFrom";
    }
  }
};
</script>

<style lang="less" scoped>
.control-btn{
    position: absolute;
    right: 7PX;
    z-index: 99;
    width: 30%;
    padding-left: 10px;
    padding-right: 10px;
}
.submit{text-align:center;padding:10PX;border-top:1PX solid rgb(233, 233, 233)}
</style>