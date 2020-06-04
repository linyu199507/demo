<template>
  <div>
    <div class="subJectInvolved">
      <div class="header">
        <span></span>
        <div class="title">涉及主体信息</div>
      </div>
      <!--  -->
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="涉及主体" v-model="subjectInvolved" value-align="right" placeholder="必填"></x-input>
        <popup-picker
          title="涉及主体类别和类型"
          placeholder="必填"
          :data="typeofSubjectInvolved"
          v-model="type"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="经营地址"
          v-model="address"
          placeholder="请填写详细信息(必填)"
          :show-counter="false"
          :rows="3"
        ></x-textarea>
        <x-address
          title="所在地区"
          v-model="region"
          raw-value
          :list="addressData"
          value-text-align="left"
        ></x-address>
        <x-input title="详细地址" v-model="detailAddress" value-align="right" placeholder="必填"></x-input>
        <x-textarea
          title="核查经营地址"
          v-model="businessName"
          value-align="right"
          :rows="3"
          placeholder="必填"
        ></x-textarea>
      </group>
    </div>

    <div class="subJectInvolved">
      <div class="header">
        <span></span>调解情况
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="行政调解文书号" value-align="right" placeholder="请输入" v-model="documentNo"></x-input>
        <popup-picker
          title="侵权类型"
          placeholder="必填"
          :data="typeOfInfringementOptions"
          v-model="typeOfInfringement"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="欺诈标志"
          placeholder="必填"
          :data="fraudLogoOptions"
          v-model="fraudLogo"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="调解方式"
          placeholder="必填"
          :data="styleOfconciliationOptions"
          v-model="styleOfconciliation"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="未履行义务"
          placeholder="必填"
          :data="failureToObligationOptions"
          v-model="failureToObligation"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="调解结果"
          placeholder="必填(如选其他是为调解不成功)"
          :data="resultOfconciliationOptions"
          v-model="resultOfconciliation"
          value-text-align="left"
        ></popup-picker>
        <x-input title="争议金额(元)" v-model="moneyInControversy" value-align="right" placeholder="必填"></x-input>
        <x-input
          title="精神赔偿金额(元)"
          v-model="mentalCompensation"
          value-align="right"
          placeholder="必填"
        ></x-input>
        <x-input title="加倍赔偿金额(元)" v-model="doubleIndemnity" value-align="right" placeholder="必填"></x-input>
        <x-input
          title="挽回经济损失(元)"
          v-model="recoupEconomicLosses"
          value-align="right"
          placeholder="必填"
        ></x-input>
        <datetime
          v-model="transferredToDate"
          @on-change="change"
          title="办结日期"
          @on-cancel="log('cancel')"
          @on-confirm="onConfirm"
          @on-hide="log('hide', $event)"
        ></datetime>
        <x-textarea
          title="调解情况"
          placeholder="请填写调解情况(1200字以内)"
          :show-counter="false"
          :rows="10"
          v-model="Mediation"
        ></x-textarea>
      </group>
    </div>

    <div class="subJectInvolved">
      <div class="header">
        <span></span>处理信息
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-textarea
          title="处理结果"
          placeholder="请填写调解情况(1200字以内)"
          :show-counter="false"
          :rows="10"
          v-model="resultOfHandling"
        ></x-textarea>
        <popup-picker
          title="是否诉转案"
          placeholder="必选"
          :data="chooseOption"
          v-model="turnEnd"
          value-text-align="left"
        ></popup-picker>
        <div class="upload">
          <div class="uploadTitle">相关附件</div>

          <div class="chooseFile" ref="file">
            <input type="file" @click="opeanFile()" ref="file" multiple="multiple" class="file" />
          </div>
        </div>
      </group>
    </div>
    <div class="detail">
      <x-button type="primary" @click.native="showChoose">处理</x-button>
    </div>

    <!-- 弹窗 -->
    <popup v-model="show">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="comfirm"
        @on-click-left="cancel"
        @on-click-right="show = false"
      ></popup-header>
      <group gutter="0">
        <radio :options="stepOptions" v-model="dealType"></radio>
      </group>
    </popup>
    <!-- 处理 -->
    <popup v-model="deal">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="chooseDealPeople1"
        @on-click-left="cancel"
        @on-click-right="comfirmDealPerson"
      ></popup-header>
      <group gutter="0">
        <radio v-if="dealType==='送区局处理'" :options="dealPeople" v-model="dealPerson"></radio>
        <radio v-if="dealType === '送分局领导审批'" :options="dealLeaders" v-model="dealPersonTion"></radio>
      </group>
    </popup>
  </div>
</template>
<script>

  import { searchBaseInfo, nextWork,findUsersByDeptIdAndPermission,findUsersByPermission } from '../api/convenient12345';

export default {

  watch: {
    dealPersonTion: function (newvalue) {
      console.log(newvalue)
      console.log(newvalue.toString())
      console.log(this.leaderList)
      for (let key in this.leaderList) {
        if (this.leaderList[key].name == newvalue.toString()) {
          console.log(this.leaderList[key].name)
          this.DealPersonTion = this.leaderList[key]
          console.log(this.DealPersonTion)
        }
      }
    },
    dealPerson: function (newvalue) {
      console.log(newvalue)
      console.log(newvalue.toString())
      console.log(this.leaderLists)
      for (let key in this.leaderLists) {
        if (this.leaderLists[key].name == newvalue.toString()) {
          console.log(this.leaderLists[key].name)
          this.DealPerson = this.leaderLists[key]
          console.log(this.DealPerson)
        }
      }
    },
  },

  data() {
    return {
      nodeStr:"",
      formType:"",
      formTypeStr:"",
      economyform:{},
      complaintform:{},
      consultationform:{},
      commomform:{},
      caseprocesform:{},
      customerinfo:{},
      tasklist:{},
      opinionList:[],
      handledemand:{},
      ordernumber:"",
      user:{},
      leaderList:[],
      leaderLists:[[]],
      show: false,
      deal: false,
      subjectInvolved: '',
      address: '',
      documentNo: '',
      detailAddress: '',
      businessName: '',
      Mediation: '',
      transferredToDate: '',
      resultOfHandling: '',
      dealType: '',
      dealPerson: '',
      DealPerson:[],
      dealPersonTion: '',
      DealPersonTion:[],
      turnEnd: [],
      recoupEconomicLosses: 0,
      doubleIndemnity: 0,
      moneyInControversy: 0,
      mentalCompensation: 0,
      type: [],
      typeOfInfringement: [],
      region: [],
      fraudLogo: [],
      styleOfconciliation: [],
      failureToObligation: [],
      resultOfconciliation: [],
      dealLeader: [],
      dealLeaders:[],
      chooseOption: [['是', '否']],
      dealPeople: [],
      stepOptions: ['送区局处理', '送分局领导审批'],
      resultOfconciliationOptions: [
        ['达成调解协议', '未达成调解协议', '撤回', '联系不到当事人', '其他'],
      ],
      failureToObligationOptions: [
        [
          '不得履行不公平、不合理的格式合同、店堂告示等',
          '遵守法律和履行合同义务',
          '尊重消费者的人格尊严',
          '听取消费者意见，接受监督',
        ],
      ],
      styleOfconciliationOptions: [
        ['即时现场调解', '现场调解', '即时调解', '其他'],
      ],
      fraudLogoOptions: [['其他']],
      typeOfInfringementOptions: [['知情权', '安全权', '其他']],
      typeofSubjectInvolved: [['个体工商户', '农民专业合作社', '自然人']],
      addressData: ['禅城区', '南海区', '顺德区', '高明区', '三水区'],
    };
  },
  methods: {
    onShow() {
      console.log('on show');
    },
    onHide(type) {
      console.log('on hide', type);
    },
    onChange(val) {
      console.log('val change', val);
    },
    change(value) {
      console.log('change', value);
    },
    onConfirm(val) {
      console.log('on-confirm arg', val);
      console.log('current value', this.value1);
    },
    opeanFile() {
      this.$refs.file.click();
    },
    comfirmDealPerson() {
      // this.deal = false;linyu
      console.log(this.dealType)
      if (this.dealType === '送分局领导审批') {
//       送领导审批
//       let validation;
//       this.$refs.feedbackForm.validate(valid => (validation = valid));
//       this.$refs.feedbackLeaderForm.validate(
//         valid => (validation = valid && validation)
//       );
//       if (!validation) return;
        this.$confirm("请确认无误提交?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          let data;
          if ("commommodule" === this.formType) {
            if (this.commomform != null) {
              data = this.changeArray(this.commomform);
            } else {
              data = this.changeArray(this.consultationform);
            }
          } else if ("complaintmodule" === this.formType) {

            data = this.changeArray(this.complaintform);
          } else if ("comeconomicmodule" === this.formType) {

            data = this.changeArray(this.economyform);
          }
          this.caseprocesform = this.changeArray(this.caseprocesform);

          let params = {
            user: this.user,
            formType: this.formType,
            node: this.tasklist.nextnodeid,
            nextNodeId: "leaderApr",
            data: data,
            opinion: this.resultOfHandling,
            deptid: this.DealPersonTion.primaryKey,
            caseprocesform: this.caseprocesform,
          };
          console.log(params)
          // return " ";
          nextWork(params)
            .then(data => {
              if (data.code === 0) {
                this.$message({
                  message: "提交成功！",
                  type: "success",
                  duration: "1000"
                });
                this.$router.push("/main/Pending");
              } else {
                this.errorAlert();
              }
            })
            .catch(data => {
              this.errorAlert();
            });
        });
      }
    },
    showChoose() {
      this.show = true;
    },
    cancel() {
      this.show = false;
      this.deal = false;
      this.dealType = '';
      this.dealPerson = '';
    },
    comfirm() {
      if (this.dealType === '送区局处理') {
        this.deal = true;
      } else if (this.dealType === '送分局领导审批') {
        this.deal = true;
      } else {
        this.deal = false;
      }
    },
  changeArray(data) {
    //遍历去除数组格式
    for (const key in data) {
      if (data[key] instanceof Array) {
        if (data[key].length > 0) {
          data[key] = data[key].pop();
        } else {
          data[key] = null;
        }
      }
    }
    console.log(data)
    return data ;
  },
    getLeaderList() {
       this.user = JSON.parse(localStorage.getItem('user'));
      console.log(this.user)
      //请求领导列表
      let params = {
        deptid: this.user.unitID,
        permission: "leadershipApproval"
      };
      findUsersByDeptIdAndPermission(params).then(data => {
        if (data.data) {
          this.leaderList = data.data;
          for (let key in this.leaderList) {
            if (this.leaderList.length > 0) {
              this.dealLeaders.push(this.leaderList[key].name);
            }
          }
        }
      });
    },
    //根据当前用户权限查询区局处理人员列表
    delayPersonnel() {
      let para = {
        permission: "bmfwDispatch"
      };
      findUsersByPermission(para).then(res => {
        this.leaderLists = res.data;
        console.log(this.leaderLists)
        this.dealPeople.push(this.leaderLists[0].name)
        console.log(this.dealPeople)
      });
    },
    chooseDealPeople() {
      console.log(this.dealType)
      // this.$router.push({ path: '/main/Pending' });
    },
    getBaseInfo() {
      this.loading = true;
      //获取id，否则跳转
      console.log(111)
      console.log(this.$route.query)
      let {id, caseguid} = this.$route.query;
      if (!id || !caseguid) {
        this.$router.push("/main/Pending");
        return;
      }
      // //获取工单信息
      searchBaseInfo({id, caseguid, systemsource: "02"})
        .then(({code, data}) => {
          console.log(data)
          // this.nodeStr = data.tasklist.nextnodeid;
          // if (
          //   this.nodeStr !== "handleFeedback" &&
          //   data.tasklist.isaccept === "1"
          // ) {
          //   //节点与当前对应不上并不是属于没有受理
          //   this.$router.push("/wddb/12345db");
          //   return;
          // }
          if (data.caseprocesform != null) {
            this.caseprocesform = data.caseprocesform;
          }
          this.customerinfo = data.customerinfo;
          this.tasklist = data.tasklist;
          this.opinionList = data.opinionList;
          this.handledemand = data.handledemand;
          if (code === 0) {
            if (data.economyform != null) {
              //经济表单
              this.formType = "comeconomicmodule";
              this.formTypeStr = "经济违法行为";
              this.economyform = data.economyform;
              this.ordernumber = this.economyform.ordernumber;
              console.log(this.economyform )
            } else if (data.complaintform != null) {
              //消费投诉表单
              this.formType = "complaintmodule";
              this.formTypeStr = "消费投诉表单";
              this.complaintform = data.complaintform;
              this.ordernumber = this.complaintform.ordernumber;

            } else if (data.consultationform != null) {
              //咨询
              this.formType = "commommodule";
              this.formTypeStr = data.consultationform.formtype;
              this.consultationform = data.consultationform;
              this.ordernumber = this.consultationform.ordernumber;


            } else if (data.commomform != null) {
              //通用表单
              this.formType = "commommodule";
              this.formTypeStr = data.commomform.formtype;
              this.commomform = data.commomform;
              this.ordernumber = this.commomform.ordernumber;

            } else {
              //找不到对的工单信息
              this.$alert("找不到对应的工单信息!", "提示", {
                confirmButtonText: "确定",
                type: "warning"
              });
              this.$router.push("/wddb/12345db");
            }
          }
          this.loading = false;
        })
        .catch(error => {
          console.error(error);
          this.$message({
            message: "请求失败",
            type: "error"
          });
          this.loading = false;
        });
      console.log(this.formType)
    },
  },
  mounted() {
    this.delayPersonnel();
    this.getLeaderList();
    console.log(this.$route.query);
    this.getBaseInfo();
  },
};
</script>
<style lang="less" scoped>
@import url('../assets/font/common.less');
@color: #666;
@fontStyleOfTitle: 0.4rem;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);
.upload {
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  border-top: 1px solid #d9d9d9;
  .uploadTitle {
    // width: 4.5em;
    text-align: left;
  }
  .chooseFile {
    margin-top: 20px;
  }
}




.detail {
  width: 80%;
  margin: 100px auto;
}

</style>
<style lang="less">
@import url('../assets/font/commonOfCommon.less');
// .weui-cells {
//   font-size: 30px !important;
//   margin-top: 0 !important;
//   margin-bottom: 30px;
//   left: 0 !important;
// }
// .vux-cell-box:not(:first-child):before {
//   left: 0 !important;
// }
// .weui-cell:before {
//   left: 0 !important;
// }
// .weui-cells:before {
//   border-top: none !important;
// }
// .weui-btn_primary {
//   margin-top: 100px;
//   margin-bottom: 100px;
//   background: @backgroundColor!important;
// }
</style>
