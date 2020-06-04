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
          :data="subjectTypedOptions"
          v-model="subjectTyped"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="经营地址"
          v-model="businessAddress"
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

    <!-- 调查情况 -->
    <div class="subJectInvolved">
      <div class="header">
        <span></span>
        <div class="title">调查情况</div>
      </div>
      <!--  -->
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="行政调解文书号" v-model="documentNo" value-align="right" placeholder="必填"></x-input>
        <popup-picker
          title="是否无照经营"
          placeholder="请选择"
          :data="unlicensedOperationOption"
          v-model="unlicensedOperation"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="侵权类型"
          placeholder="必选"
          :data="typeOfInfringementOptions"
          v-model="typeOfInfringement"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="欺诈标志"
          placeholder="请选择"
          :data="fraudSignOption"
          v-model="fraudSign"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="调解方式"
          placeholder="必选"
          :data="mediationModeOptions"
          v-model="mediationMode"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="未履行义务"
          placeholder="必选"
          :data="unfulfilledObligationsOption"
          v-model="unfulfilledObligations"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="调解结果"
          placeholder="必选"
          :data="resultsOfConciliationOptions"
          v-model="resultsOfConciliation"
          value-text-align="left"
        ></popup-picker>
        <x-input title="赔偿方式" v-model="waysOfCompensation" value-align="right" placeholder="必填"></x-input>
        <popup-picker
          title="证据"
          placeholder="必选"
          :data="evidenceOptions"
          v-model="evidence"
          value-text-align="left"
        ></popup-picker>
        <x-input
          title="销售地行政区划"
          v-model="administrativeDivision"
          value-align="right"
          placeholder="必填"
        ></x-input>
        <x-input title="销售地" v-model="placeOfSale" value-align="right" placeholder="必填"></x-input>
        <x-input title="争议金额(元)" v-model="contentiousAmount" value-align="right" placeholder="必填"></x-input>
        <x-input
          title="精神赔偿金额(元)"
          v-model="mentalCompensationAmount"
          value-align="right"
          placeholder="必填"
        ></x-input>
        <x-input title="加倍赔偿金额(元)" v-model="doubleTheAmount" value-align="right" placeholder="必填"></x-input>
        <x-input
          title="挽回经济损失(元)"
          v-model="retrieveTheEconomy"
          value-align="right"
          placeholder="必填"
        ></x-input>
        <datetime
          v-model="dateOfCompletion"
          @on-change="change"
          title="办结日期"
          placeholder="请选择办结日期"
          value-align="right"
          @on-confirm="onConfirm"
        ></datetime>
        <x-textarea
          title="订单号"
          v-model="OrderNumber"
          :show-counter="false"
          :rows="2"
          placeholder="请输入"
        ></x-textarea>
      </group>
    </div>

    <!-- 处理信息 -->
    <div class="subJectInvolved">
      <div class="header">
        <span></span>
        <div class="title">处理信息</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input title="办理部门" v-model="managementDepartment" value-align="right" placeholder="必填"></x-input>
        <x-input title="经办人" v-model="personInCharge" value-align="right" placeholder="必填"></x-input>
        <x-input title="经办人电话" v-model="telOfOperator" value-align="right" placeholder="请填入"></x-input>
        <popup-picker
          title="是否诉转案"
          placeholder="请选择(必选)"
          :data="transferTheCaseOptions"
          v-model="transferTheCase"
          value-text-align="left"
          @on-change="changeCaseStatus"
        ></popup-picker>
        <x-textarea
          title="经办人意见"
          v-model="opinionsOfhandler"
          :show-counter="false"
          :rows="2"
          placeholder="请输入"
        ></x-textarea>
        <x-input v-if="transferCaseId" title="立案号" v-model="registrationNumber" value-align="right" placeholder="必填"></x-input>
        <x-textarea
          title="处理结果"
          v-model="processingResult"
          :show-counter="false"
          :rows="2"
          placeholder="请输入(必填)"
        ></x-textarea>
        <x-textarea
          v-if="transferCaseId"
          title="案由"
          v-model="causeOfAction"
          :show-counter="false"
          :rows="2"
          placeholder="请输入(必填)"
        ></x-textarea>
        <x-textarea
          title="送审附言"
          v-model="postscript"
          :show-counter="false"
          :rows="2"
          placeholder="请输入(必填)"
        ></x-textarea>
        <x-input title="相关附件" value-align="right" placeholder="必填">
          <input type="file" />
        </x-input>
      </group>
    </div>

    <div class="detail">
      <x-button type="primary" @click.native="showChoose">处理</x-button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      subjectInvolved: '',
      businessAddress: '',
      detailAddress: '',
      administrativeDivision: '',
      businessName: '',
      documentNo: '',
      managementDepartment: '',
      waysOfCompensation: '',
      placeOfSale: '',
      mentalCompensationAmount: '',
      telOfOperator: '',
      personInCharge: '',
      doubleTheAmount: '',
      contentiousAmount: '',
      registrationNumber: '',
      opinionsOfhandler: '',
      processingResult: '',
      dateOfCompletion: '',
      OrderNumber: '',
      retrieveTheEconomy: '',
      causeOfAction: '',
      postscript: '',
      typeOfInfringement: [],
      region: [],
      subjectTyped: [],
      evidence: [],
      fraudSign: [],
      resultsOfConciliation: [],
      transferTheCase: [],
      unlicensedOperation: [],
      mediationMode: [],
      unfulfilledObligations: [],
      fraudSignOption: [['其他']],
      evidenceOptions: [
        ['发票', '收据', '收银小票', '录音', '录像', '证人', '无'],
      ],
      unfulfilledObligationsOption: [['其他']],
      transferTheCaseOptions: [['是', '否']],
      unlicensedOperationOption: [['是', '否']],
      mediationModeOptions: [['即时现场调解', '现场调解', '即时调解', '其他']],
      resultsOfConciliationOptions: [['其他']],
      typeOfInfringementOptions: [
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
      addressData: ['禅城区', '南海区', '顺德区', '高明区', '三水区'],
      subjectTypedOptions: [['企业', '个人']],
      transferCaseId: false,
    };
  },
  methods: {
    change() {},
    onConfirm() {},
    changeCaseStatus() {
      if (this.transferTheCase[0] === '是') {
        // alert(1);
        this.transferCaseId = true;
      } else {
        this.transferCaseId = false;
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import url("../assets/font/common.less");
</style>
<style lang="less">
@import url("../assets/font/commonOfCommon.less");
</style>