<template>
<!-- 12345待分派咨询表 -->
  <div>
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">定单信息</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="接听坐席"
                 v-model="customerinfoOne.seatsname"
                 value-align="left"
                 placeholder="必填"></x-input>
        <datetime
          v-model="$moment(customerinfoOne.seatsregistrationtime).format('YYYY-MM-DD')"
          @on-change="change"
          title="登记时间"
          placeholder="请选择登记时间"
          value-align="right"
          @on-confirm="onConfirm"
        ></datetime>
        <popup-picker
          title="性别"
          placeholder="必填"
          :data="genderOptions"
          :key="customerinfoOne.seatssex[index]"
          v-model="customerinfoOne.seatssex"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="语言"
          placeholder="必填"
          :data="langOptions"
          :key="customerinfoOne.seatslanguage[index]"
          v-model="customerinfoOne.seatslanguage"
          value-text-align="left"
        ></popup-picker>
      </group>
    </div>

    <div>
      <ul class="tabs">
        <li
          class="li-tab"
          v-for="(item,index) in scrollItem"
          @click="toggleTabs(index)"
          :class="{active:index!=nowIndex}"
          :key="index"
        >{{item}}</li>
      </ul>
      <div class="formContent" style="margin-bottom:60px">
        <ul>
          <!-- 登记信息 -->
          <li v-show="nowIndex == 0">
            <div class="register">
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">提供方信息</div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <x-input title="表单类型" v-model="commomformOne.formtype" value-align="left" placeholder="必填"></x-input>
                      <x-input
                        title="表单模板"
                        v-model="commomformOne.formmodule"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input title="标题" v-model="commomformOne.rqsttitle" value-align="left" placeholder="必填"></x-input>
                      <x-input title="姓名" v-model="commomformOne.name" value-align="left" placeholder="必填"></x-input>
                      <popup-picker
                        title="性别"
                        placeholder="必填"
                        :data="genderOptions"
                        :key="genderOne[index]"
                        v-model="genderOne"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="区域"
                        placeholder="必填"
                        :data="regionOptions"
                        :key="regionOne[index]"
                        v-model="regionOne"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="是否有效"
                        placeholder="必填"
                        :data="effectiveOption"
                        :key="isvalidOne[index]"
                        v-model="isvalidOne"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input title="来电号码" v-model="commomformOne.rqstphonenumber" value-align="left" placeholder="必填"></x-input>
                      <x-input
                        title="联系电话"
                        v-model="commomformOne.linkphonenumber"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input
                        title="政务标签"
                        v-model="commomformOne.govlabel"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <popup-picker
                        title="反映类型"
                        placeholder="必填"
                        :data="reflectionTypeOption"
                        :key="usertypeOne[index]"
                        v-model="usertypeOne"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="工单分类"
                        placeholder="必填"
                        :data="jobClassificationOptions"
                        :key="focusOne[index]"
                        v-model="focusOne"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="回复方式"
                        placeholder="必填"
                        :data="recoveryModeOption"
                        :key="answertypeOne[index]"
                        v-model="answertypeOne"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="企业类型"
                        placeholder="必填"
                        :data="typetsOfEnterprisesOption"
                        :key="entitytypeOne[index]"
                        v-model="entitytypeOne"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="企业全称"
                        v-model="commomformOne.entname"
                        placeholder="请填写公司全称(必填)"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="企业登记机关"
                        v-model="commomformOne.aicid"
                        placeholder="请填写企业登记机关(必填)"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="企业经营地址"
                        v-model="commomformOne.address"
                        placeholder="请填写企业经营地址(必填)"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                      <x-textarea
                        title="问题描述"
                        v-model="commomformOne.rqstcontent"
                        placeholder="请填写问题描述"
                        :show-counter="false"
                        :rows="20"
                      ></x-textarea>
                    </group>
                  </div>
                </div>
              </div>
            </div>
          </li>

          <!-- 处办要求 -->
          <li v-show="nowIndex == 1">
            <div class="register">
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 处办要求
                  </div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">应受理时限:</div>
                        <div
                          class="Procontent"
                        >{{$moment(processingDemand.accepttimesurplus).format('YYYY-MM-DD HH:mm:ss')}}</div>
                      </li>
                      <li>
                        <div class="Protitle">应受理到期时间:</div>
                        <div
                          class="Procontent"
                        >{{$moment(processingDemand.accepttime).format('YYYY-MM-DD HH:mm:ss')}}</div>
                      </li>
                      <li>
                        <div class="Protitle">应立案时限:</div>
                        <div
                          class="Procontent"
                        >{{$moment(processingDemand.caseputtime).format('YYYY-MM-DD HH:mm:ss')}}</div>
                      </li>
                      <li>
                        <div class="Protitle">应立案到期时间:</div>
                        <div
                          class="Procontent"
                        >{{$moment(processingDemand.accepttime).format('YYYY-MM-DD HH:mm:ss')}}</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!-- 流程跟踪 -->
          <li v-show="nowIndex == 2">
            <flow
              orientation="vertical"
              :style="`height:${6.3*opinionList.length}rem`"
              class="mb100"
            >
              <template v-for="(item,index) in opinionList">
                <flow-state :state="index+1" is-done>
                  <template slot="title">
                    <div class="processContent">
                      <ul>
                        <li>
                          <span>步骤：</span>
                          {{item.nodename}}
                        </li>
                        <li>
                          <span>处理人员：</span>
                          {{item.handler}}
                        </li>
                        <li>
                          <!-- <span>处理部门：</span> -->
                          <x-textarea
                            title="处理部门"
                            disabled
                            v-model="item.handledept"
                            :show-counter="false"
                            :rows="1"
                            style="padding:0"
                          ></x-textarea>
                          <!-- {{item.handledept}} -->
                        </li>
                        <li>
                          <span>操作：</span>
                          {{item.operate}}
                        </li>
                        <li>
                          <span>处理时间：</span>
                          {{item.handletime}}
                        </li>
                        <li class="fontStyle">
                          <x-textarea
                            title="处理意见"
                            disabled
                            v-model="item.handleopinion"
                            :show-counter="false"
                            :rows="4"
                            style="padding:0"
                          ></x-textarea>
                          <!-- <span>处理意见：</span>
                          版权声明版权声明版权声明版权声明版权声明版权声明版权声明版
                          权声明版权声明版权声明版权声明版权声明版权声明版权声明-->
                        </li>
                      </ul>
                    </div>
                  </template>
                </flow-state>
                <flow-line is-done v-if="index+1!=opinionList.length"></flow-line>
              </template>
            </flow>
          </li>
        </ul>
      </div>
    </div>
    <!--  -->
    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">处理</x-button>
    </div>
    <!-- 弹窗 -->
    <popup v-model="show">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="comfirm"
        @on-click-left="show = false"
        @on-click-right="show = false"
      ></popup-header>
      <group gutter="0">
        <radio :options="stepOptions" v-model="stepValue"></radio>
      </group>
    </popup>
  </div>
</template>
<script>

  import { listDetail, saveBmfwBaseInfo } from '../api/api';

export default {



  data() {
    return {
      stepOptions: ['保存','处理反馈', '分派下级', '分派部门' ],
      customerinfoOne:{
        id:"",
        caseguid:"",
        seatsname:"",
        seatsregistrationtime:"",
        seatssex:[],
        seatslanguage:[],

      },
      commomformOne:{
        formtype:'',
        formmodule:'',
        rqsttitle:'',
        name:'',
        rqstphonenumber:'',
        linkphonenumber:'',
        entname:'',
        aicid:'',
        address:'',
        rqstcontent:'',
        govlabel:'',

      },
      genderOne:[],
      regionOne:[],
      isvalidOne:[],
      usertypeOne:[],
      answertypeOne:[],
      entitytypeOne:[],
      focusOne:[],
      // 处理要求
      processingDemand: {},
      // 流程
      opinionList: [],
      // 当前节点
      currentLink: '',
      scrollItem: ['登记信息', '处理信息', '流程跟踪'],
      regionOptions: [['顺德区', '双水区', '禅城区', '南海区', '高明区']],
      demo2: '登记信息',
      langOptions: [['普通话', '粤语']],
      deptOptions: [['消保科']],
      jobClassificationOptions: [['普通', '特殊']],
      businessLicenseOptions: [['是', '否']],
      genderOptions: [['男', '女']],
      effectiveOption: [['是', '否']],
      reflectionTypeOption: [['企业', '个人']],
      recoveryModeOption: [['电话', '网上', '信件']],
      industryCategory: [['IT']],
      concatTypeOptions: [['固定电话', '移动电话']],
      locationOptions: [['顺德区', '禅城区', '南海区', '三水区', '高明区']],
      disposalStatusOptions: [['一级', '二级']],
      typeOfAssistanceOptions: [['协助单位类型']],
      grougComplaintsOptions: [['群体投诉案件']],
      foreignComplaintsOptions: [['涉外投诉案件']],
      dealTypeOptions: [['是', '否']],
      transferCaseOptions: [['是', '否']],
      administrativeTreatmentOptions: [['是', '否']],
      typetsOfEnterprisesOption: [
        [
          '企业',
          '个体工商户',
          '自然人',
          '内资企业',
          '外资投资企业',
          '农民专业合作社',
          '其他',
        ],
      ],
      index: 0,
      nowIndex: 0,
      show: false,
      marketName: '',
      acceptPeople: '',
      formTemplate: '',
      registerDate: '',
      causeOfAction: '',
      registerFormNumber: '',
      formType: '',
      Investigators: '',
      title: '',
      name: '',
      tel: '',
      creditCode: '',
      phoneNumber: '',
      detailAddress: '',
      descriptionOfQuestion: '',
      nameOfEnterprise: '',
      contactNumber: '',
      enterpriseRegistrationOptions: '',
      InvestigatorsNum: '',
      feekbackGegistrant: '',
      enterpriseRegistration: '',
      governmentTags: '',
      sex: [],
      region: [],
      dealType: [],
      effective: [],
      jobClassification: [],
      disposalStatus: [],
      reflectionType: [],
      businessLicense: [],
      foreignComplaints: [],
      administrativeTreatment: [],
      typetsOfEnterprises: [],
      transferCase: [],
      dept: [],
      recoveryMode: [],
      businessLicensie: [],
      grougComplaints: [],
      typeOfAssistance: [],
      location: [],
      concatType: [],
      stepValue: '保存',
      gender: [],
      lang: [],
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
    };
  },
  methods: {
    toggleTabs(index) {
      this.nowIndex = index;
    },
    showChoose() {
      this.show = true;
    },
    change() {},

    comfirm() {

      // 获取的选项框数据
      this.commomformOne.gender = this.genderOne[0]
      this.commomformOne.region = this.regionOne[0]
      this.commomformOne.isvalid = this.isvalidOne[0]
      this.commomformOne.usertype = this.usertypeOne[0]
      this.commomformOne.answertype = this.answertypeOne[0]
      this.commomformOne.entitytype = this.entitytypeOne[0]
      this.commomformOne.focus = this.focusOne[0]

      const data = JSON.parse(JSON.stringify(this.commomformOne));
      const customerinfoData = JSON.parse(JSON.stringify(this.customerinfoOne));

      this.changeArray(data)
      this.changeArray(customerinfoData)
      sessionStorage.setItem('commomform', JSON.stringify(data));
      sessionStorage.setItem('customerinfo', JSON.stringify(customerinfoData));
      console.log(customerinfoData)
      console.log(data)

      if (this.stepValue === '保存') {
        const params = {
          handledemand: this.processingDemand,
          customerinfo: customerinfoData,
          commomform: data,
        };
        console.log(params)
        // return " ";
        saveBmfwBaseInfo(params).then((res) => {
          console.log(res);
          if (res.code === 0) {
            this.$vux.alert.show({
              title: '提示',
              content: '保存成功',
            });
          }
        });
      } else if (this.stepValue === '处理反馈') {
        const params = {
          stepValue: this.stepValue,
          formType: 'commommodule',
          node: this.currentLink,
          id:data.id,
          caseguid:data.caseguid,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派下级') {
        const params = {
          stepValue: this.stepValue,
          fromType: 'commommodule',
          node: this.currentLink,
          id:data.id,
          caseguid:data.caseguid,
        };
        console.log(params)
        // return " ";
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派部门') {
        console.log('分派部门')
        const params = {
          stepValue: this.stepValue,
          fromType: 'commommodule',
          node: this.currentLink,
          id:data.id,
          caseguid:data.caseguid,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else {
        console.log(this.stepValue);
      }
    },
    onConfirm(val) {
      console.log(val);
    },
    changeArray(data) {
      for (const key in data) {
        if (data[key] instanceof Array) {
          if (data[key].length > 0) {
            data[key] = data[key].pop();
          } else {
            data[key] = null;
          }
        }
      }
      return data;
    },
    // 查询列表详情
    getListDetail() {
      const params = {
        caseguid: this.$route.query.caseguid,
        id: this.$route.query.id,
      };
      listDetail(params).then((res) => {
        console.log(res);
        if (res.code === 0) {
          console.log(res.data)
          this.customerinfoOne.id = res.data.customerinfo.id;
          this.customerinfoOne.caseguid = res.data.customerinfo.caseguid;
          this.customerinfoOne.seatsname = res.data.customerinfo.seatsname;
          this.customerinfoOne.seatsregistrationtime = res.data.customerinfo.seatsregistrationtime;
          this.customerinfoOne.seatssex[0] = res.data.customerinfo.seatssex;
          this.customerinfoOne.seatslanguage[0] = res.data.customerinfo.seatslanguage;
          console.log(res.data.customerinfo)
          console.log(this.customerinfoOne)

          this.commomformOne = res.data.commomform;
          console.log(this.commomformOne)

          this.genderOne[0] = res.data.commomform.gender
          this.regionOne[0] = res.data.commomform.region
          this.isvalidOne[0] = res.data.commomform.isvalid
          this.usertypeOne[0] = res.data.commomform.usertype
          this.answertypeOne[0] = res.data.commomform.answertype
          this.entitytypeOne[0] = res.data.commomform.entitytype
          this.focusOne[0] = res.data.commomform.focus
          console.log(this.commomformOne)
          console.log(this.commomformOne.gender)
          // 处办要求
          this.processingDemand = res.data.handledemand;
          console.log(this.processingDemand)
          // 流程跟踪
          this.opinionList = res.data.opinionList;
          this.currentLink = res.data.tasklist.nextnodeid;
        }
      });
    },
  },
  mounted(){
      this.getListDetail();
  }
};
</script>
<style lang="less">
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

span {
  color: @color;
}
.provider {
  .title {
    border-bottom: 1px solid #d9d9d9;
    font-size: 0.53rem;
    display: flex;
    align-items: center;
    padding-bottom: 5px;
    span {
      display: block;
      width: 5px;
      background: #666;
      height: 0.53rem;
      margin-right: 0.266667rem;
    }
  }
}

ul {
  li {
    list-style: none;
  }
}
.fontStyle {
  width: 100%;
  display: block;
  white-space: initial;
}
.registerInfo {
  background: #fff;
  width: 90%;
  padding: 20px;
  margin: 20px auto;
  border-radius: 20px;
}
.detail {
  text-align: center;
  margin-top: 100px;
}

.dispatchedWhole {
  width: 90%;
  margin: 0 auto;
  border-radius: 20px;
  padding-bottom: 20px;
  margin-top: 20px;
  background: #fff;
  box-shadow: #eee 2px 2px 5px 2px;
  font-size: @fontStyleOfTitle;
  padding: 20px 0 0 40px;
  .header {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border-bottom: 2px solid #d9d9d9;
    span {
      display: inline-block;
      width: 5px;
      height: 40px;
      margin-right: 20px;
      background: @color;
      vertical-align: middle;
    }
    .title {
      vertical-align: middle;
      font-size: 40px;
    }
  }
}
.tabs {
  height: 100px;
  line-height: 100px;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  border-top: 2px solid @backgroundColor;
  margin-bottom: 50px;
}
.vux-popup-header-right,
.weui-search-bar__cancel-btn {
  color: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: @backgroundColor!important;
}
.mb100 {
  margin-bottom: 300px;
}
.weui-btn_primary {
  background: @backgroundColor!important;
}

.li-tab {
  flex: 1;
  text-align: center;
  background: @backgroundColor;
}
.weui-wepay-flow__li_done .weui-wepay-flow__state {
  background: @backgroundColor!important;
}
.weui-wepay-flow__process {
  background: @backgroundColor!important;
}
.active {
  background: #fff;
  color: @backgroundColor;
  font-weight: 600;
}

.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.vux-cell-box:not(:first-child):before {
  left: 0 !important;
}
.weui-cell:before {
  left: 0 !important;
}
.weui-cells:before {
  border-top: none !important;
}
.weui-wepay-flow,
.weui-wepay-flow-auto {
  padding: 150px 40px 20px 40px !important;
}
.weui-wepay-flow_vertical .weui-wepay-flow__bd {
  width: 10px;
}
.weui-cell__ft {
  text-align: left !important;
}
.processContent {
  background: #fff;
  border-radius: 20px;
  padding: 20px;
  width: 500px;
  font-size: @fontStyleOfTitle;
  color: @color;

  ul {
    li {
      span {
        color: #000;
      }
    }
  }
}
</style>
