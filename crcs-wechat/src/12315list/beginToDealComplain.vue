<template>
  <!-- 分流完的页面 -->
  <div class="mt30">
    <!-- 系统信息 -->
    <div class="registerInfo">
      <div class="provider">
        <div class="title"><span></span> 系统信息</div>
        <div class="content">
          <ul>
            <li>
              <div class="Protitle">登记编号:</div>
              <div class="Procontent">1440606002019111458846178</div>
            </li>
            <li>
              <div class="Protitle">记录类型:</div>
              <div class="Procontent">投诉</div>
            </li>
            <li>
              <div class="Protitle">登记日期:</div>
              <div class="Procontent">2019-11-14 08:31:00</div>
            </li>

            <li>
              <div class="Protitle">接收方式:</div>
              <div class="Procontent">互联网平台</div>
            </li>
            <li>
              <div class="Protitle">来电号码:</div>
              <div class="Procontent">13650738837</div>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!--  -->
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
        <!-- 系统信息 -->
        <li v-show="nowIndex == 0">
          <div class="register">
            <div class="registerInfo">
              <div class="provider">
                <div class="title"><span></span> 提供方信息</div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <x-textarea
                      title="标题"
                      v-model="title"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <x-input
                      title="投诉人姓名/单位"
                      v-model="unitOfComplainant"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <popup-picker
                      title="性别"
                      placeholder="请选择"
                      :data="genderOptions"
                      v-model="gender"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input
                      title="投诉人联系电话"
                      v-model="contactNumber"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <popup-picker
                      title="证件类型"
                      placeholder="请选择"
                      :data="documentTypeOptions"
                      v-model="documentType"
                      value-text-align="left"
                    ></popup-picker>
                    <x-textarea
                      title="证件号码"
                      v-model="idNum"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <popup-picker
                      title="提供方类型"
                      placeholder="请选择"
                      :data="providerTypeOptions"
                      v-model="providerType"
                      value-text-align="left"
                    ></popup-picker>
                    <popup-picker
                      title="年龄段"
                      placeholder="请选择"
                      :data="ageGroupOptions"
                      v-model="ageGroup"
                      value-text-align="left"
                    ></popup-picker>
                    <x-textarea
                      title="电子邮箱"
                      v-model="mailBox"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <x-input title="国籍" v-model="nationality" value-align="left" placeholder="必填"></x-input>
                    <x-input title="民族" v-model="nation" value-align="left" placeholder="必填"></x-input>
                    <popup-picker
                      title="身份"
                      placeholder="请选择"
                      :data="identityOptions"
                      v-model="identity"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input title="邮编" v-model="zipCode" value-align="left" placeholder="必填"></x-input>
                    <x-input
                      title="其他联系方式"
                      v-model="otherContactInfo"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-textarea
                      title="投诉人地址"
                      v-model="addressOfComplainant"
                      :show-counter="false"
                      :rows="4"
                      placeholder="请输入"
                    ></x-textarea>
                  </group>
                </div>
              </div>
            </div>

            <div class="registerInfo">
              <div class="provider">
                <div class="title"><span></span> 涉及主体信息</div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <x-textarea
                      title="被投诉对象名称"
                      v-model="complainedObject"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <x-input title="联系人" v-model="contacts" value-align="left" placeholder="必填"></x-input>
                    <x-input title="联系电话" v-model="phoneNumber" value-align="left" placeholder="必填"></x-input>
                    <x-textarea
                      title="社会信用代码"
                      v-model="socialCreditCode"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <popup-picker
                      title="市场主体类型"
                      placeholder="请选择"
                      :data="marketSubjectTypeOptions"
                      v-model="marketSubjectType"
                      value-text-align="left"
                    ></popup-picker>
                    <popup-picker
                      title="行业分类"
                      placeholder="请选择"
                      :data="industryClassificationOptions"
                      v-model="industryClassification"
                      value-text-align="left"
                    ></popup-picker>
                    <x-textarea
                      title="被投诉对象地址"
                      v-model="complainAddress"
                      :show-counter="false"
                      :rows="4"
                      placeholder="请输入"
                    ></x-textarea>
                  </group>
                </div>
              </div>
            </div>
            <!-- 投诉类业务信息 -->
            <div v-if="" class="registerInfo">
              <div class="provider">
                <div class="title"><span></span> 业务信息</div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <x-input
                      title="电商平台"
                      v-model="electronicPlatform"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <popup-picker
                      title="客体类别"
                      placeholder="请选择"
                      :data="objectClassOptions"
                      v-model="objectClass"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input title="商品品牌" v-model="brand" value-align="left" placeholder="必填"></x-input>
                    <popup-picker
                      title="销售方式"
                      placeholder="请选择"
                      :data="sellTypeOptions"
                      v-model="sellType"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input
                      title="消费金额(元)"
                      v-model="consumptionAmount"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-textarea
                      title="订单号"
                      v-model="OrderNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <datetime
                      v-model="timeOfDispute"
                      @on-change="change"
                      title="争议发生时间"
                      placeholder="请选择反馈期限"
                      value-align="right"
                      @on-confirm="onConfirm"
                    ></datetime>
                    <x-input
                      title="诉求内容"
                      v-model="contentOfAppeal"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <popup-picker
                      title="投诉问题类别"
                      placeholder="请选择"
                      :data="questionTypeOptions"
                      v-model="questionType"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input title="产品名称" v-model="productName" value-align="left" placeholder="必填"></x-input>
                    <x-textarea
                      title="批准文号"
                      v-model="approvalNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <x-textarea
                      title="产品批号"
                      v-model="productBatchNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <x-input
                      title="产品规格"
                      v-model="productSpecification"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-input
                      title="生产厂家"
                      v-model="Manufacturer"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-input title="关键字" v-model="keyWord" value-align="left" placeholder="必填"></x-input>
                    <x-textarea
                      title="销售企业"
                      v-model="salesEnterprise"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <datetime
                      v-model="productValidity"
                      @on-change="change"
                      title="产品有效期"
                      placeholder="请选择反馈期限"
                      value-align="right"
                      @on-confirm="onConfirm"
                    ></datetime>
                    <x-textarea
                      title="举报内容"
                      v-model="reportContent"
                      :show-counter="false"
                      :rows="12"
                      placeholder="请输入"
                    ></x-textarea>
                  </group>
                </div>
              </div>
            </div>
            <!-- 举报涉及客体信息 -->
            <div v-if="" class="registerInfo">
              <div class="provider">
                <div class="title"><span></span> 涉及客体信息</div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <popup-picker
                      title="客体类别"
                      placeholder="请选择"
                      :data="objectClassOptions"
                      v-model="objectClass"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input
                      title="商品/服务名称"
                      v-model="serviceName"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-input title="商品品牌" v-model="brand" value-align="left" placeholder="必填"></x-input>
                    <popup-picker
                      title="销售方式"
                      placeholder="请选择"
                      :data="sellTypeOptions"
                      v-model="sellType"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input
                      title="消费金额(元)"
                      v-model="consumptionAmount"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-textarea
                      title="订单号"
                      v-model="OrderNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <popup-picker
                      title="举报问题类别"
                      placeholder="请选择"
                      :data="typesOfReportedProblemsOptions"
                      v-model="typesOfReportedProblems"
                      value-text-align="left"
                    ></popup-picker>
                    <x-input title="产品名称" v-model="productName" value-align="left" placeholder="必填"></x-input>
                     <x-textarea
                      title="批准文号"
                      v-model="approvalNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                     <x-textarea
                      title="产品批号"
                      v-model="productBatchNumber"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                     <x-input
                      title="产品规格"
                      v-model="productSpecification"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-input
                      title="生产厂家"
                      v-model="Manufacturer"
                      value-align="left"
                      placeholder="必填"
                    ></x-input>
                    <x-input title="关键字" v-model="keyWord" value-align="left" placeholder="必填"></x-input>
                    <x-textarea
                      title="销售企业"
                      v-model="salesEnterprise"
                      :show-counter="false"
                      :rows="2"
                      placeholder="请输入"
                    ></x-textarea>
                    <datetime
                      v-model="productValidity"
                      @on-change="change"
                      title="产品有效期"
                      placeholder="请选择反馈期限"
                      value-align="right"
                      @on-confirm="onConfirm"
                    ></datetime>
                    <x-textarea
                      title="举报内容"
                      v-model="reportContent"
                      :show-counter="false"
                      :rows="12"
                      placeholder="请输入"
                    ></x-textarea>

                  </group>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!-- 流程跟踪 -->
        <li v-show="nowIndex == 1">
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
    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">处理</x-button>
      <x-button type="default" mini @click.native="showChoose">取消</x-button>
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
      scrollItem: ['登记信息', '办理跟踪'],
      genderOptions: [['男', '女']],
      documentTypeOptions: [['身份证', '军官证', '其他']],
      providerTypeOptions: [['企业', '个人']],
      ageGroupOptions: [['少年(19岁一下)', '中青年(19-65岁)', '老年人']],
      identityOptions: [['城镇', '乡村']],
      marketSubjectTypeOptions: [['marketSubjectTypeOptions']],
      industryClassificationOptions: [['自然人']],
      objectClassOptions: [['商品', '服务']],
      sellTypeOptions: [['网购', '实体店']],
      stepOptions: ['保存', '受理', '分派下级', '分派部门', '不受理', '退回'],
      questionTypeOptions: [],
      nowIndex: 0,
      title: '',
      contactNumber: '',
      mailBox: '',
      idNum: '',
      unitOfComplainant: '',
      consumptionAmount: '',
      complainAddress: '',
      zipCode: '',
      nation: '',
      electronicPlatform: '',
      OrderNumber: '',
      otherContactInfo: '',
      addressOfComplainant: '',
      nationality: '',
      contacts: '',
      timeOfDispute: '',
      brand: '',
      complainedObject: '',
      phoneNumber: '',
      productName: '',
      productBatchNumber: '',
      productSpecification: '',
      productValidity: '',
      salesEnterprise: '',
      keyWord: '',
      approvalNumber: '',
      contentOfAppeal: '',
      socialCreditCode: '',
      reportContent: '',
      Manufacturer: '',
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      gender: [],
      ageGroup: [],
      marketSubjectType: [],
      objectClass: [],
      sellType: [],
      questionType: [],
      identity: [],
      providerType: [],
      documentType: [],
      industryClassification: [],
      show: false,
      stepValue: '保存',
      // 举报涉及客体信息
      serviceName: '',
      typesOfReportedProblemsOptions: [['其他']],
      typesOfReportedProblems: [],
    };
  },
  methods: {
    toggleTabs(index) {
      this.nowIndex = index;
    },
    change() {},
    onConfirm() {},
    showChoose() {
      this.show = true;
    },
    comfirm() {
      if (this.stepValue === '保存') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '受理') {
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
      } else if (this.stepValue === '分派部门') {
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
      } else {
        console.log(this.stepValue);
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