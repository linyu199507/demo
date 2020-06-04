<template>
  <!-- 12345待分流的投诉表单 -->
  <div>
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">定单信息</div>
      </div>

      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="接听坐席" v-model="acceptPeople" value-align="left" placeholder="必填"></x-input>
        <datetime
          v-model="registerDate"
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
          v-model="gender"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="语种"
          placeholder="必填"
          :data="langOptions"
          v-model="lang"
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
              <!-- 提供方信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 提供方信息
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <x-input title="表单类型" v-model="formType" value-align="left" placeholder="必填"></x-input>
                      <x-input title="标题" v-model="title" value-align="left" placeholder="必填"></x-input>
                      <popup-picker
                        title="表单模板"
                        placeholder="请选择"
                        :data="modelOptions"
                        v-model="modelValue"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input title="投诉人姓名" v-model="name" value-align="left" placeholder="必填"></x-input>
                      <popup-picker
                        title="性别"
                        placeholder="必填"
                        :data="genderOptions"
                        v-model="sex"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input title="来电号码" v-model="tel" value-align="left" placeholder="必填"></x-input>
                      <x-input
                        title="联系电话"
                        v-model="contactNumber"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <popup-picker
                        title="反映类型"
                        placeholder="必填"
                        :data="reflectionTypeOption"
                        v-model="reflectionType"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="回复方式"
                        placeholder="请选择"
                        :data="recoveryModeOption"
                        v-model="recoveryMode"
                        value-text-align="left"
                      ></popup-picker>
                      <!-- 基础数据 -->
                      <popup-picker
                        title="市场主体类型"
                        placeholder="请选择"
                        :data="MarketSubjectTypeOption"
                        v-model="MarketSubjectType"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="类别"
                        placeholder="请选择"
                        :data="categoryOptions"
                        v-model="category"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="工单分类"
                        placeholder="必填"
                        :data="jobClassificationOptions"
                        v-model="jobClassification"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="区域"
                        placeholder="必填"
                        :data="regionOptions"
                        v-model="region"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="投诉人地址"
                        v-model="reporterAddress"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                    </group>
                  </div>
                </div>
              </div>
              <!-- 涉及主体信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 涉及主体信息
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <x-input
                        title="名称"
                        v-model="subjectInfoName"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-textarea
                        title="联系电话"
                        v-model="contactInformation"
                        placeholder="请填写联系电话"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="经营地址"
                        v-model="businessAddress"
                        placeholder="请填写经营地址"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                    </group>
                  </div>
                </div>
              </div>
              <!-- 涉及客体信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 涉及客体信息
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <x-input
                        title="商品/服务名称"
                        v-model="serviceName"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input title="商品品牌" v-model="brand" value-align="left" placeholder="请输入"></x-input>
                      <x-input title="涉及金额(元)" v-model="money" value-align="left" placeholder="请输入"></x-input>
                      <!-- 基础数据 -->
                      <popup-picker
                        title="涉及客体分类"
                        placeholder="请选择涉及客体分类"
                        :data="classificationOfObjects"
                        v-model="objectValue"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="生产企业"
                        v-model="manufacturingEnterprise"
                        placeholder="请填写生产企业"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <popup-picker
                        title="是否网购"
                        placeholder="请选择"
                        :data="onlineShoppingOptions"
                        v-model="onlineShopping"
                        value-text-align="left"
                      ></popup-picker>
                    </group>
                  </div>
                </div>
              </div>

              <!-- 业务具体信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 业务具体信息
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <!-- 基础数据 -->
                      <popup-picker
                        title="涉及问题分类"
                        placeholder="请选择涉及问题分类"
                        :data="objectClassification"
                        v-model="objectClassify"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="事件级别"
                        placeholder="请选择事件级别"
                        :data="eventLevelOptions"
                        v-model="eventLevel"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="政务标签"
                        v-model="governmentTags"
                        placeholder="请填写政务标签"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                      <x-textarea
                        title="问题陈述"
                        v-model="probplemStatement"
                        placeholder="请填写问题陈述"
                        :show-counter="false"
                        :rows="13"
                      ></x-textarea>
                    </group>
                  </div>
                </div>
              </div>

              <!-- 处理相关信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 处理相关信息
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <popup-picker
                        title="是否出现场"
                        placeholder="请选择"
                        :data="outOfTheSceneOptions"
                        v-model="outOfTheScene"
                        value-text-align="left"
                      ></popup-picker>
                    </group>
                  </div>
                </div>
              </div>

              <div class="registerInfo">
                <div class="provider">
                  <div class="title">
                    <span></span> 上传相关文件
                  </div>
                  <div class="content">
                    <group label-width="4.5em" label-margin-right="2em" label-align="right">
                      <el-upload
                        class="upload-demo"
                        ref="upload"
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :file-list="fileList"
                        :auto-upload="false"
                      >
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        <el-button
                          style="margin-left: 10px;"
                          size="small"
                          type="success"
                          @click="submitUpload"
                        >上传到服务器</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                      </el-upload>
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
                        <div class="Procontent">2019-11-11</div>
                      </li>
                      <li>
                        <div class="Protitle">应受理到期时间:</div>
                        <div class="Procontent">2019-11-11 12:00:00</div>
                      </li>
                      <li>
                        <div class="Protitle">应立案时限:</div>
                        <div class="Procontent">2019-11-11 12:00:00</div>
                      </li>
                      <li>
                        <div class="Protitle">应立案到期时间:</div>
                        <div class="Procontent">2019-11-11 12:00:00</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!-- 流程跟踪 -->
          <li v-show="nowIndex == 2">
            <flow orientation="vertical" :style="`height:${6.3*flowList.length}rem`" class="mb100">
              <template v-for="(item,index) in flowList">
                <flow-state :state="index+1" is-done>
                  <template slot="title">
                    <div class="processContent">
                      <ul>
                        <li>
                          <span>步骤：</span> 受理登记
                        </li>
                        <li>
                          <span>处理人员：</span> 张倩华
                        </li>
                        <li>
                          <span>处理部门：</span>
                          消费维权业务队列
                        </li>
                        <li>
                          <span>操作：</span>
                          受理登记
                        </li>
                        <li>
                          <span>处理时间：</span>
                          2019-11-19 15:35:53
                        </li>
                        <li class="fontStyle">
                          <span>处理意见：</span>
                          版权声明版权声明版权声明版权声明版权声明版权声明版权声明版
                          权声明版权声明版权声明版权声明版权声明版权声明版权声明
                        </li>
                      </ul>
                    </div>
                  </template>
                </flow-state>
                <flow-line is-done v-if="index+1!=flowList.length"></flow-line>
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
export default {
  data() {
    return {
      registerDate: '',
      scrollItem: ['登记信息', '处理信息', '流程跟踪'],
      modelOptions: [['消费投诉业务-质量投诉', '消费投诉业务-价格投诉']],
      stepOptions: [
        '保存',
        '受理',
        '分派下级',
        '分派部门',
        '处理反馈',
        '不受理',
        '退回',
      ],
      demo2: '登记信息',
      langOptions: [['普通话', '粤语']],
      classificationOfObjects: [['其他']],
      deptOptions: [['消保科']],
      jobClassificationOptions: [['普通', '特殊']],
      businessLicenseOptions: [['是', '否']],
      genderOptions: [['男', '女']],
      reflectionTypeOption: [['企业', '个人']],
      recoveryModeOption: [['电话', '网上', '信件']],
      industryCategory: [['IT']],
      eventLevelOptions: [['特别紧急事件', '紧急事件', '一般事件']],
      categoryOptions: [['老人', '妇女', '儿童', '其他']],
      concatTypeOptions: [['固定电话', '移动电话']],
      businessClassifyOptions: [
        ['销售', '服务', '仓储', '运输', '其他', '金融', '生产'],
      ],
      locationOptions: [['顺德区', '禅城区', '南海区', '三水区', '高明区']],
      disposalStatusOptions: [['一级', '二级']],
      tortClassification: [
        [
          '知情权',
          '安全权',
          '选择权',
          '公平交易权',
          '求偿权',
          '结社权',
          '受教育权',
          '受尊重权',
          '监督权',
          '其他',
        ],
      ],
      onlineShoppingOptions: [['是', '否']],
      typeOfAssistanceOptions: [['协助单位类型']],
      illegalActOptions: [['其他']],
      questionClassifyOptions: [
        [
          '不正当竞争及限制竞争',
          '发票',
          '质量',
          '广告',
          '商标',
          '价格',
          '人身权利',
          '售后服务',
          '其他',
          '安全',
          '金融',
          '无照经营',
          '传销',
          '合同',
          '包装标识',
          '计量',
          '环保投诉',
          '无产品合格证',
          '专利',
          '个人信息保护',
        ],
      ],
      grougComplaintsOptions: [['群体投诉案件']],
      objectClassification: [['其他']],
      foreignComplaintsOptions: [['涉外投诉案件']],
      dealTypeOptions: [['是', '否']],
      MarketSubjectTypeOption: [['其他']],
      hurtOptions: [['其他']],
      transferCaseOptions: [['是', '否']],
      administrativeTreatmentOptions: [['是', '否']],
      needsReward: [['是', '否']],
      consumptionTypeOptions: [['其他']],
      outOfTheSceneOptions: [['是', '否']],
      regionOptions: [['禅城区', '南海区', '顺德区', '三水区', '高明区']],
      show: false,
      index: 0,
      nowIndex: 0,
      marketName: '',
      economicLossValue: '',
      serviceName: '',
      money: '',
      brand: '',
      acceptPeople: '',
      caseValue: '',
      questionDesc: '',
      briefSutation: '',
      goodsNum: '',
      probplemStatement: '',
      manufacturingEnterprise: '',
      registerDate: '',
      causeOfAction: '',
      businessAddress: '',
      contactInformation: '',
      contactinfo: '',
      reporterAddress: '',
      subjectInfoName: '',
      dateOfbuy: '',
      registerFormNumber: '',
      formType: '',
      Investigators: '',
      title: '',
      name: '',
      tel: '',
      creditCode: '',
      phoneNumber: '',
      detailAddress: '',
      contactNumber: '',
      injuryDegree: '',
      InvestigatorsNum: '',
      feekbackGegistrant: '',
      keyWord: '',
      safeDegree: '',
      injuryDegree: '',
      nation: '',
      governmentTags: '',
      injuryNum: '',
      sex: [],
      dealType: [],
      modelValue: [],
      jobClassification: [],
      fileList: [],
      eventLevel: [],
      region: [],
      hurt: [],
      reflectionType: [],
      illegalAct: [],
      MarketSubjectType: [],
      outOfTheScene: [],
      objectClassify: [],
      businessClassify: [],
      objectValue: [],
      feekbacks: [],
      onlineShopping: [],
      disposalStatus: [],
      consumptionType: [],
      businessLicense: [],
      foreignComplaints: [],
      category: [],
      administrativeTreatment: [],
      transferCase: [],
      dept: [],
      recoveryMode: [],
      businessLicensie: [],
      questionClassify: [],
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
    submitUpload() {
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    toggleTabs(index) {
      this.nowIndex = index;
    },
    showChoose() {
      this.show = true;
    },
    change() {},
    comfirm() {
      if (this.stepValue === '保存') {
      } else if (this.stepValue === '处理反馈') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派下级') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '不受理') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派部门') {
        const params = {
          stepValue: this.stepValue,
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
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

span {
  color: @color;
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
  margin: 0 auto 20px;
  border-radius: 20px;
  padding-bottom: 20px;
  margin-top: 20px;
  background: #fff;
  box-shadow: #eee 2px 2px 5px 2px;
  font-size: @fontStyleOfTitle;
  padding-top: 20px;

  .header {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border-bottom: 2px solid #d9d9d9;
    span {
      display: inline-block;
      width: 5px;
      height: 40px;
      margin: 0 20px;
      background: @color;
      vertical-align: middle;
    }
    .title {
      vertical-align: middle;
      font-size: 40px;
    }
  }
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

.active {
  background: #fff;
  color: @backgroundColor;
  font-weight: 600;
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
.content {
  ul {
    list-style: none;
    margin: 0;
    padding: 10px 0 0 0;
    li {
      display: flex;
      justify-content: space-between;
      // align-items: center;
      font-size: 30px;
      margin-bottom: 5px;
      .Protitle {
        flex: 1;
        text-align: left;
      }
      .Procontent {
        flex: 1;
        text-align: right;
        color: @color;
      }
    }
  }
}
</style>
<style lang="less" >
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

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
.weui-cell:before {
  left: 0 !important;
}
.weui-cells:before {
  border-top: none !important;
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
.weui-wepay-flow__li_done .weui-wepay-flow__state {
  background: @backgroundColor!important;
}
.weui-wepay-flow__process {
  background: @backgroundColor!important;
}
.vux-popup-header-right {
  color: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: @backgroundColor!important;
}
</style>
