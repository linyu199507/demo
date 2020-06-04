<template>
  <div>
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">定单信息</div>
      </div>

      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input
          title="接听坐席"
          v-model="economyForm.orderInfo.seatsname"
          value-align="left"
          placeholder="必填"
        ></x-input>
        <datetime
          v-model="$moment(economyForm.orderInfo.seatsregistrationtime).format('YYYY-MM-DD')"
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
          :key="detailDataForm.gender[index]"
          v-model="detailDataForm.gender"
          value-text-align="left"
          @on-change="changeGender"
        ></popup-picker>
        <popup-picker
          title="语种"
          placeholder="必填"
          :data="langOptions"
          :key="detailDataForm.lang[index]"
          v-model="detailDataForm.lang"
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
                      <x-input
                        title="表单类型"
                        v-model="economyForm.EconomyBaseInfo.formtype"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input
                        title="标题"
                        v-model="economyForm.EconomyBaseInfo.rqsttitle"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input
                        title="举报人姓名"
                        v-model="economyForm.EconomyBaseInfo.rqstperson"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input
                        title="年龄"
                        v-model="economyForm.EconomyBaseInfo.age"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="民族"
                        v-model="economyForm.EconomyBaseInfo.nation"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="来电号码"
                        v-model="economyForm.EconomyBaseInfo.rqstnumber"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <x-input
                        title="联系电话"
                        v-model="economyForm.EconomyBaseInfo.linknumber"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <popup-picker
                        title="性别"
                        placeholder="必填"
                        :data="genderOptions"
                        :key="detailDataForm.economyGender[index]"
                        v-model="detailDataForm.economyGender"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input
                        title="身份"
                        v-model="economyForm.EconomyBaseInfo.status"
                        value-align="left"
                        placeholder="必填"
                      ></x-input>
                      <popup-picker
                        title="证件类型"
                        placeholder="必填"
                        :data="documentTypeOptions"
                        :key="detailDataForm.documentType[index]"
                        v-model="detailDataForm.documentType"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="证件号码"
                        v-model="economyForm.EconomyBaseInfo.idnumber"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <popup-picker
                        title="职业"
                        placeholder="必填"
                        :data="occupationOptions"
                        :key="detailDataForm.occupation[index]"
                        v-model="detailDataForm.occupation"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="工作单位"
                        v-model="economyForm.EconomyBaseInfo.company"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <popup-picker
                        title="反映类型"
                        placeholder="必填"
                        :data="reflectionTypeOption"
                        :key="detailDataForm.reflectionType[index]"
                        v-model="detailDataForm.reflectionType"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="回复方式"
                        placeholder="必填"
                        :data="recoveryModeOption"
                        :key="detailDataForm.recoveryMode[index]"
                        v-model="detailDataForm.recoveryMode"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="工单分类"
                        placeholder="必填"
                        :data="jobClassificationOptions"
                        :key="detailDataForm.jobClassification[index]"
                        v-model="detailDataForm.jobClassification"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="类别"
                        placeholder="必填"
                        :data="categoryOptions"
                        :key="detailDataForm.category[index]"
                        v-model="detailDataForm.category"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="投诉票据"
                        v-model="economyForm.EconomyBaseInfo.complaintpaper"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="残疾人标识"
                        v-model="economyForm.EconomyBaseInfo.disabilityidentification"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="举报人地址"
                        v-model="economyForm.EconomyBaseInfo.rqstaddress"
                        placeholder="请填写证件号码"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                      <x-textarea
                        title="联系地址"
                        v-model="economyForm.EconomyBaseInfo.contactaddress"
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
                        v-model="economyForm.EconomyBaseInfo.subjectname"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-textarea
                        title="联系方式"
                        v-model="economyForm.EconomyBaseInfo.linktype"
                        placeholder="请填写联系方式"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="经营地址"
                        v-model="economyForm.EconomyBaseInfo.subjectaddress"
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
                        v-model="economyForm.EconomyBaseInfo.objname"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="商品品牌"
                        v-model="economyForm.EconomyBaseInfo.brand"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <!-- 基础数据 -->
                      <popup-picker
                        title="涉及客体分类"
                        placeholder="请选择涉及客体分类"
                        :data="objectClassification"
                        v-model="economyForm.EconomyBaseInfo.objectClassify"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input
                        title="涉及金额(元)"
                        v-model="economyForm.EconomyBaseInfo.objmoney"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-textarea
                        title="生产企业"
                        v-model="economyForm.EconomyBaseInfo.producecompany"
                        placeholder="请填写生产企业"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>

                      <popup-picker
                        title="是否网购"
                        placeholder="请选择"
                        :data="onlineShoppingOptions"
                        :key="detailDataForm.onlineShopping[index]"
                        v-model="detailDataForm.onlineShopping"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="商品进口标志"
                        v-model="economyForm.EconomyBaseInfo.importmark"
                        placeholder="请填写商品进口标志"
                        :show-counter="false"
                        :rows="3"
                      ></x-textarea>
                      <x-input
                        title="型号规格"
                        v-model="economyForm.EconomyBaseInfo.typespecification"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="商品数量"
                        v-model="economyForm.EconomyBaseInfo.quantity"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="计量单位"
                        v-model="economyForm.EconomyBaseInfo.measureunit"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="商品/服务价格(元)"
                        v-model="economyForm.EconomyBaseInfo.price"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <datetime
                        v-if="economyForm.EconomyBaseInfo.purchasingdate !=='null'|| economyForm.EconomyBaseInfo.purchasingdate !==''"
                        @on-change="change"
                        title="购买日期"
                        @on-cancel="log('cancel')"
                        @on-confirm="onConfirm"
                        @on-hide="log('hide', $event)"
                      ></datetime>
                      <datetime
                        v-else
                        v-model="$moment(economyForm.EconomyBaseInfo.purchasingdate).format('YYYY-MM-DD')"
                        @on-change="change"
                        title="购买日期"
                        @on-cancel="log('cancel')"
                        @on-confirm="onConfirm"
                        @on-hide="log('hide', $event)"
                      ></datetime>
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
                        placeholder="请选择"
                        :data="questionTypeOptions"
                        v-model="economyForm.EconomyBaseInfo.questionType"
                        value-text-align="left"
                      ></popup-picker>
                      <!-- 基础数据 -->
                      <popup-picker
                        title="涉嫌违法行为"
                        placeholder="请选择涉嫌违法行为"
                        :data="illegalOptions"
                        v-model="economyForm.EconomyBaseInfo.illegal"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="事件级别"
                        placeholder="请选择"
                        :data="eventLevelOptions"
                        :key="detailDataForm.eventLevel[index]"
                        v-model="detailDataForm.eventLevel"
                        value-text-align="left"
                      ></popup-picker>
                      <x-textarea
                        title="关键字"
                        v-model="economyForm.EconomyBaseInfo.keyword"
                        placeholder="请填写关键字"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <x-textarea
                        title="政务标签"
                        v-model="economyForm.EconomyBaseInfo.govlabel"
                        placeholder="请填写政务标签"
                        :show-counter="false"
                        :rows="2"
                      ></x-textarea>
                      <!-- 基础数据 -->
                      <popup-picker
                        title="业务分类"
                        placeholder="请选择业务分类"
                        :data="businessOptions"
                        v-model="economyForm.EconomyBaseInfo.business"
                        value-text-align="left"
                      ></popup-picker>

                      <popup-picker
                        title="侵权分类"
                        placeholder="请选择侵权分类"
                        :data="tortTypeOptions"
                        :key="detailDataForm.tortType"
                        v-model="detailDataForm.tortType"
                        value-text-align="left"
                      ></popup-picker>
                      <x-input
                        title="案值(元)"
                        v-model="economyForm.EconomyBaseInfo.anestimate"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-input
                        title="经济损失值(元)"
                        v-model="economyForm.EconomyBaseInfo.economicloss"
                        value-align="left"
                        placeholder="请输入"
                      ></x-input>
                      <x-textarea
                        title="问题描述"
                        v-model="economyForm.EconomyBaseInfo.problemdescribe"
                        placeholder="请输入问题描述"
                        :show-counter="false"
                        :rows="10"
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
                        :key="detailDataForm.outOfTheScene[index]"
                        v-model="detailDataForm.outOfTheScene"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="是否反馈"
                        placeholder="请选择涉嫌违法行为"
                        :data="feekbackOptions"
                        :key="detailDataForm.feekbacks[index]"
                        v-model="detailDataForm.feekbacks"
                        value-text-align="left"
                      ></popup-picker>
                      <popup-picker
                        title="是否需要奖励"
                        placeholder="请选择"
                        :data="needsRewardOptions"
                        :key="detailDataForm.needsReward[index]"
                        v-model="detailDataForm.needsReward"
                        value-text-align="left"
                      ></popup-picker>
                      <div class="borderTop">
                        <ul>
                          <li>
                            <div class="titleLsit" style="width:4.5em;">相关附件</div>
                            <div class="materialList">
                              <ul>
                                <li></li>
                              </ul>
                            </div>
                          </li>
                        </ul>
                      </div>
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
        <radio v-if="currentLink ==='begin' " :options="stepOptions" v-model="stepValue"></radio>
         <!--<radio v-if="currentLink ==='begin' " :options="stepOptions" v-model="stepValue"></radio>-->
      </group>
    </popup>
  </div>
</template>
<script>
import { listDetail, saveBmfwBaseInfo } from '../api/api';
import { parse } from 'path';

export default {
  data() {
    return {
      detailDataForm: {
        // 处理过的数据
        gender: [],
        lang: [],
        economyGender: [],
        documentType: [],
        occupation: [],
        reflectionType: [],
        recoveryMode: [],
        jobClassification: [],
        category: [],
        onlineShopping: [],
        eventLevel: [],
        outOfTheScene: [],
        feekbacks: [],
        needsReward: [],
      },
      economyForm: {
        // 订单信息
        orderInfo: {
          seatsname: '',
          seatsregistrationtime: '',
        },
        EconomyBaseInfo: {
          formtype: '',
          rqsttitle: '',
          rqstperson: '',
          age: '',
          nation: '',
          rqstnumber: '',
          linknumber: '',
          status: '',
          idnumber: '',
          company: '',
          complaintpaper: '',
          disabilityidentification: '',
          rqstaddress: '',
          contactaddress: '',
          subjectname: '',
          linktype: '',
          subjectaddress: '',
          objname: '',
          brand: '',
          objmoney: '',
          producecompany: '',
          importmark: '',
          typespecification: '',
          quantity: '',
          measureunit: '',
          price: '',
          keyword: '',
          govlabel: '',
          anestimate: '',
          economicloss: '',
          problemdescribe: '',
          accepttimesurplus: '',
          accepttime: '',
          caseputtime: '',
          accepttime: '',
        },
      },
      // 处理要求
      processingDemand: {},
      // 流程
      opinionList: [],
      // 当前节点
      currentLink: '',
      scrollItem: ['登记信息', '处理信息', '流程跟踪'],
      stepOptions: ['保存', '受理', '分派下级', '分派部门'],
      demo2: '登记信息',
      illegalOptions: [['其他']],
      tortTypeOptions: [['其他']],
      businessOptions: [['其他']],
      questionTypeOptions: [['其他']],
      langOptions: [['普通话', '粤语']],
      deptOptions: [['消保科']],
      jobClassificationOptions: [['普通', '特殊']],
      businessLicenseOptions: [['是', '否']],
      genderOptions: [['男', '女']],
      reflectionTypeOption: [['企业', '个人']],
      recoveryModeOption: [['电话', '网上', '信件']],
      industryCategory: [['IT']],
      documentTypeOptions: [['身份证', '军官证', '其他证件']],
      eventLevelOptions: [['特别紧急事件', '紧急事件', '一般事件']],
      categoryOptions: [['老人', '妇女', '儿童', '其他']],
      occupationOptions: [['其他']],
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
      needsRewardOptions: [['是', '否']],
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
      foreignComplaintsOptions: [['涉外投诉案件']],
      dealTypeOptions: [['是', '否']],
      objectClassification: [['商品', '服务']],
      hurtOptions: [['其他']],
      transferCaseOptions: [['是', '否']],
      feekbackOptions: [['是', '否']],
      administrativeTreatmentOptions: [['是', '否']],
      consumptionTypeOptions: [['其他']],
      outOfTheSceneOptions: [['是', '否']],
      show: false,
      index: 0,
      nowIndex: 0,
      stepValue: '保存',
      // stepValue: ['保存', '受理'],
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],

      // 列表传过来的值
      caseguid: '',
      id: '',
      ismain: '',

    };
  },
  watch: {},
  methods: {
    toggleTabs(index) {
      this.nowIndex = index;
    },
    showChoose() {
      this.show = true;
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
    },
    change() {},
    comfirm() {
      // 订单信息
      this.economyForm.orderInfo.gender = this.detailDataForm.gender;
      this.economyForm.orderInfo.seatslanguage = this.detailDataForm.lang;

      // 经济表单
      this.economyForm.EconomyBaseInfo.gender = this.detailDataForm.economyGender;
      this.economyForm.EconomyBaseInfo.certificate = this.detailDataForm.documentType;
      this.economyForm.EconomyBaseInfo.career = this.detailDataForm.occupation;
      this.economyForm.EconomyBaseInfo.usertype = this.detailDataForm.reflectionType;
      this.economyForm.EconomyBaseInfo.answertype = this.detailDataForm.recoveryMode;
      this.economyForm.EconomyBaseInfo.focus = this.detailDataForm.jobClassification;
      this.economyForm.EconomyBaseInfo.providelb = this.detailDataForm.category;
      this.economyForm.EconomyBaseInfo.isweb = this.detailDataForm.onlineShopping;
      this.economyForm.EconomyBaseInfo.eventlevel = this.detailDataForm.eventLevel;
      this.economyForm.EconomyBaseInfo.isoutmall = this.detailDataForm.outOfTheScene;
      this.economyForm.EconomyBaseInfo.isfeedback = this.detailDataForm.feekbacks;
      this.economyForm.EconomyBaseInfo.isaward = this.detailDataForm.needsReward;

      // 深度拷贝，防止更改economyform原先的表单值
      const data = JSON.parse(JSON.stringify(this.economyForm.EconomyBaseInfo));
      const orderData = JSON.parse(JSON.stringify(this.economyForm.orderInfo));

      // 转换数组
      this.changeArray(data);
      this.changeArray(orderData);
      sessionStorage.setItem('EconomyOrderInfo', JSON.stringify(data));
      sessionStorage.setItem('economyFormData', JSON.stringify(orderData));



      if (this.stepValue === '保存') {
        console.log("保存");
        const params = {
          handledemand: this.processingDemand,
          customerinfo: orderData,
          economyform: data,
        };
        console.log(params)
        saveBmfwBaseInfo(params).then((res) => {
          console.log(res);
          if (res.code === 0) {
            this.$vux.alert.show({
              title: '提示',
              content: '保存成功',
            });
          }
        });
      } else if (this.stepValue === '受理') {
        const params = {
          stepValue: this.stepValue,
          fromType: 'economyform',
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
      } else if (this.stepValue === '处理反馈') {
        const params = {
          stepValue: this.stepValue,
          fromType: 'economyform',
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
          fromType: 'economyform',
          node: this.currentLink,
          id:data.id,
          caseguid:data.caseguid,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '不受理') {
        const params = {
          stepValue: this.stepValue,
          fromType: 'economyform',
          node: this.currentLink,
          id:data.id,
          caseguid:data.caseguid,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派部门') {
        const params = {
          stepValue: this.stepValue,
          fromType: 'economyform',
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
          this.economyForm.orderInfo = res.data.customerinfo;
          this.economyForm.EconomyBaseInfo = res.data.economyform;
          // 处办要求
          this.processingDemand = res.data.handledemand;
          // 流程跟踪
          this.opinionList = res.data.opinionList;
          this.currentLink = res.data.tasklist.nextnodeid;

          this.detailDataForm.gender[0] = this.economyForm.orderInfo.seatssex;
          this.detailDataForm.lang[0] = this.economyForm.orderInfo.seatslanguage;
          this.detailDataForm.economyGender[0] = this.economyForm.EconomyBaseInfo.gender;
          // 将字符串处理成数组
          if (this.economyForm.EconomyBaseInfo.certificate !== '') {
            this.detailDataForm.documentType[0] = this.economyForm.EconomyBaseInfo.certificate;
          }
          if (this.economyForm.EconomyBaseInfo.career !== '') {
            this.detailDataForm.occupation[0] = this.economyForm.EconomyBaseInfo.career;
          }
          if (this.economyForm.EconomyBaseInfo.usertype !== '') {
            this.detailDataForm.reflectionType[0] = this.economyForm.EconomyBaseInfo.usertype;
          }
          if (this.economyForm.EconomyBaseInfo.answertype !== '') {
            this.detailDataForm.recoveryMode[0] = this.economyForm.EconomyBaseInfo.answertype;
          }
          if (this.economyForm.EconomyBaseInfo.focus !== '') {
            this.detailDataForm.jobClassification[0] = this.economyForm.EconomyBaseInfo.focus;
          }
          if (this.economyForm.EconomyBaseInfo.providelb !== '') {
            this.detailDataForm.category[0] = this.economyForm.EconomyBaseInfo.providelb;
          }
          if (this.economyForm.EconomyBaseInfo.isweb !== '') {
            this.detailDataForm.onlineShopping[0] = this.economyForm.EconomyBaseInfo.isweb;
          }
          if (this.economyForm.EconomyBaseInfo.eventlevel !== '') {
            this.detailDataForm.eventLevel[0] = this.economyForm.EconomyBaseInfo.eventlevel;
          }
          if (this.economyForm.EconomyBaseInfo.tortclassify !== '') {
            this.detailDataForm.eventLevel[0] = this.economyForm.EconomyBaseInfo.tortclassify;
          }
          if (this.economyForm.EconomyBaseInfo.isoutmall !== '') {
            this.detailDataForm.outOfTheScene[0] = this.economyForm.EconomyBaseInfo.isoutmall;
          }
          if (this.economyForm.EconomyBaseInfo.isfeedback !== '') {
            this.detailDataForm.feekbacks[0] = this.economyForm.EconomyBaseInfo.isfeedback;
          }
          if (this.economyForm.EconomyBaseInfo.isaward !== '') {
            this.detailDataForm.needsReward[0] = this.economyForm.EconomyBaseInfo.isaward;
          }
        }
      });
    },
    onConfirm(val) {
      console.log(val);
    },
    changeGender() {
      console.log(this.detailDataForm.gender);
    },
  },
  mounted() {
    console.log(this.$route.query);
    this.getListDetail();
    // console.log(this.economyForm.gender);
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

.weui-cell {
  // padding: 0!important;
}
span {
  color: @color;
}
.borderTop {
  border-top: 1px solid #d9d9d9;
  padding: 20px 30px;
}

.titleLsit {
  font-size: 0.4rem;
  text-align: right;
  margin-right: 2em;
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
  width: 550px;
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
textarea:disabled {
  background: #fff !important;
}
.weui-label {
  color: #000;
}
textarea {
  font-family: "microsoft yahei";
}
</style>
