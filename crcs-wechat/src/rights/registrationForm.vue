<template>
<!-- 12345待分派咨询表 -->
  <div>
    <!--站点 -->
    <card :header="{title: '站点'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <popup-picker
          :title="'<span class=\'important\'>*</span>所属辖区'"
          placeholder="请选择所属辖区"
          :data="langOptions"
          :key="dataForm.region[index]"
          v-model="dataForm.region"
          value-text-align="left"
          @on-change="toFindOperatorsList(dataForm.region)"
        ></popup-picker>
        <popup-picker
          :title="'<span class=\'important\'>*</span>经营主体'"
          placeholder="请选择经营主体"
          :data="operatorsList"
          :key="dataForm.enterprise[index]"
          v-model="dataForm.enterprise"
          value-text-align="left"
        ></popup-picker>
        <datetime
          v-model="$moment(dataForm.registrationdate).format('YYYY-MM-DD')"

          :title="'<span class=\'important\'>*</span>登记时间'"
          placeholder="请选择登记时间"
          value-align="right"
        >
          <!--@on-change="change"-->
          <!--@on-confirm="onConfirm"-->
        </datetime>

        <x-input :title="'<span class=\'important\'>*</span>登记人'"
                 v-model="dataForm.staffname"
                 value-align="left"
                 placeholder="请输入登记人" :required="true">

        </x-input>
        <x-input :title="'<span class=\'important\'>*</span>标题'"
                 v-model="dataForm.rqsttitle"
                 value-align="left"
                 placeholder="请输入标题" :required="true">

        </x-input>
        </group>
      </div>
    </card>

    <!-- <div class="dispatchedWhole">
      <div class="header">
        <div class="title">站点</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <popup-picker
          title="所属辖区"
          placeholder="请选择所属辖区"
          :data="langOptions"
          :key="dataForm.region[index]"
          v-model="dataForm.region"
          value-text-align="left"
          @on-change="toFindOperatorsList(dataForm.region)"
        ></popup-picker>
        <popup-picker
          title="经营主体"
          placeholder="请选择经营主体"
          :data="operatorsList"
          :key="dataForm.enterprise[index]"
          v-model="dataForm.enterprise"
          value-text-align="left"
        ></popup-picker>
        <datetime
          v-model="$moment(dataForm.registrationdate).format('YYYY-MM-DD')"

          title="登记时间"
          placeholder="请选择登记时间"
          value-align="right"
        >
        </datetime>
        <x-input title="登记人"
                 v-model="dataForm.staffname"
                 value-align="left"
                 placeholder="请输入登记人">
        </x-input>
        <x-input title="标题"
                 v-model="dataForm.rqsttitle"
                 value-align="left"
                 placeholder="请输入标题">

        </x-input>

      </group>
    </div> -->

    <!-- 投诉人信息 -->
    <card :header="{title: '投诉人信息'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <x-input v-model="dataForm.name" :title="'<span class=\'important\'>*</span>姓名/名称'" placeholder="请输入姓名/名称" :required="true"></x-input>
          <x-input v-model="dataForm.phone" :title="'<span class=\'important\'>*</span>联系电话'" :type="'tel'" placeholder="请输入联系电话" :required="true"></x-input>
          <x-textarea v-model="dataForm.address" :title="'具体地址'" placeholder="请输入具体地址" :autosize="true"></x-textarea>
        </group>
      </div>
    </card>

    <!-- 被投诉人信息 -->
    <card :header="{title: '被投诉人信息'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <x-input v-model="dataForm.vendorname" :title="'<span class=\'important\'>*</span>姓名/名称'" placeholder="请输入姓名" :required="true"></x-input>
          <popup-picker title="所属区" placeholder="请选择所属区" :required="true" :show-name="true" :data="respondentRegionOptions" v-model="dataForm.vendorregion" value-text-align="left"></popup-picker>
          <popup-picker title="所在镇街" placeholder="请选择所在镇街" :required="true" :show-name="true" :data="respondentTownOptions" v-model="dataForm.vendortown" value-text-align="left"></popup-picker>
          <!--<x-input v-model="dataForm.vendortown" :title="'所在镇'" placeholder="请输入所在镇"></x-input>-->
          <x-input v-model="dataForm.vendorphone" :title="'<span class=\'important\'>*</span>联系电话'" :type="'tel'" placeholder="请输入联系电话" :required="true"></x-input>
          <x-textarea v-model="dataForm.vendoraddress" :title="'具体地址'" placeholder="请输入具体地址" :autosize="true"></x-textarea>
        </group>
      </div>
    </card>

    <!-- 投诉货品或服务 -->
    <card :header="{title: '投诉货品或服务'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <x-input v-model="dataForm.goodsname" :title="'<span class=\'important\'>*</span>名称/类别'" placeholder="请输入名称/类别" :required="true"></x-input>
          <x-input v-model="dataForm.goodsmodel" :title="'型号'" placeholder="请输入型号"></x-input>
          <popup-picker v-model="dataForm.goodstype" :title="'<span class=\'important\'>*</span>投诉类型'" :show-name="true" :data="goodsTypeOptions" placeholder="请选择投诉类型" :required="true" value-text-align="left"></popup-picker>
          <popup-picker v-model="dataForm.nature" :title="'<span class=\'important\'>*</span>投诉性质'" :show-name="true" :data="natureOptions" placeholder="请选择投诉性质" value-text-align="left"></popup-picker>
          <x-input v-model="dataForm.number" :title="'数量'" :type="'number'" placeholder="请输入数量"></x-input>
          <x-input v-model="dataForm.money" :title="'涉及金额'" :type="'number'" placeholder="请输入涉及金额"></x-input>
          <datetime v-model="dataForm.shoppingdate" :title="'购买时间'" :format="'YYYY-MM-DD'"/>
          <datetime v-model="dataForm.happendate" :title="'发生时间'" :format="'YYYY-MM-DD'"/>
        </group>
      </div>
    </card>
    <!-- 个案详情 -->
    <card :header="{title: '个案详情'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <x-textarea v-model="dataForm.rqstcontent" :title="'个案详情'" placeholder="请输入个案详情" :autosize="true"></x-textarea>
        </group>
      </div>
    </card>

    <!-- 诉求内容 -->
    <card :header="{title: '诉求内容'}">
      <div slot="content">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
          <x-textarea v-model="dataForm.appeal" :title="'诉求内容'" placeholder="请输入诉求内容" :autosize="true"></x-textarea>
        </group>
      </div>
    </card>



    <!--<div>-->
      <!--<div class="formContent" style="margin-bottom:30px">-->
        <!--<ul>-->
          <!--&lt;!&ndash; 登记信息 &ndash;&gt;-->
          <!--<li v-show="nowIndex == 0">-->
            <!--<div class="register">-->
              <!--<div class="registerInfo">-->
                <!--<div class="provider">-->
                  <!--<div class="title">信息登记</div>-->
                  <!--<div class="content">-->
                    <!--<group label-width="4.5em" label-margin-right="2em" label-align="right">-->
                      <!--<x-input title="姓名" v-model="dataForm.name" value-align="left" placeholder="请输入姓名"></x-input>-->
                      <!--<popup-picker-->
                        <!--title="性别"-->
                        <!--placeholder="请选择性别"-->
                        <!--:data="genderOptions"-->
                        <!--:key="dataForm.gender[index]"-->
                        <!--v-model="dataForm.gender"-->
                        <!--value-text-align="left"-->
                      <!--&gt;</popup-picker>-->
                      <!--<x-input title="电话" v-model="dataForm.phone" value-align="left" placeholder="请输入电话号码"></x-input>-->
                      <!--<x-input title="商家名称" v-model="dataForm.vendorname" value-align="left" placeholder="请输入商家名称"></x-input>-->
                      <!--<popup-picker-->
                        <!--title="投诉问题"-->
                        <!--placeholder="请选择投诉问题"-->
                        <!--:data="regionOptions"-->
                        <!--:key="dataForm.problem[index]"-->
                        <!--v-model="dataForm.problem"-->
                        <!--value-text-align="left"-->
                      <!--&gt;</popup-picker>-->
                      <!--<popup-picker-->
                        <!--title="是否违法"-->
                        <!--placeholder="请选择是否违法"-->
                        <!--:data="illegals"-->
                        <!--:key="dataForm.isillegal[index]"-->
                        <!--v-model="dataForm.isillegal"-->
                        <!--value-text-align="left"-->
                      <!--&gt;</popup-picker>-->


                      <!--<x-textarea-->
                        <!--title="问题描述"-->
                        <!--v-model="dataForm.rqstcontent"-->
                        <!--placeholder="请填写问题描述"-->
                        <!--:show-counter="true"-->
                        <!--:rows="20"-->
                      <!--&gt;</x-textarea>-->
                    <!--</group>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->


        <!--</ul>-->
      <!--</div>-->
    <!--</div>-->


    <!--  -->
    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">提交</x-button>
    </div>
  </div>
</template>
<script>

  import { listDetail, saveBmfwBaseInfo, selectEnterprise, save } from '../api/api';

export default {



  data() {
    return {
      // stepOptions: ['保存','处理反馈', '分派下级', '分派部门' ],
      // 被投诉人所在地区候选项
      respondentRegionOptions:[[
        { name: '顺德区', value: '顺德区' },
      ]],
     // 被投诉人所在地区镇选项
      respondentTownOptions:[[
        { name: '乐从', value: '乐从' },{ name: '陈村', value: '陈村' },{ name: '勒流', value: '勒流' },{ name: '大良', value: '大良' },
        { name: '杏坛', value: '杏坛' },{ name: '均安', value: '均安' },{ name: '容桂', value: '容桂' },{ name: '龙江', value: '龙江' },
        { name: '伦教', value: '伦教' },{ name: '北滘', value: '北滘' },{ name: '其他', value: '其他' },
      ]],
      // 投诉
      goodsTypeOptions: [[
        { name: '商品类', value: '商品类' } , { name: '服务类', value: '服务类' }, { name: '其他',value: '其他' },
      ]],
      // 投诉
      natureOptions:[[
        { name: '发票', value: '发票' } , { name: '质量', value: '质量' }, { name: '广告',value: '广告' },
        { name: '商标', value: '商标' } , { name: '无照经营', value: '无照经营' }, { name: '包装标识',value: '包装标识' },
        { name: '计量', value: '计量' } , { name: '价格', value: '价格' }, { name: '售后服务',value: '售后服务' },
        { name: '安全', value: '安全' } , { name: '环保投诉', value: '环保投诉' }, { name: '无产品合格证',value: '无产品合格证' },
        { name: '其他',value: '其他' },
      ]],
      // 个案详情
      caseDetail: '',
      dataForm: {
        rqsttitle: '',
        region: [],
        enterprise: [],
        registrationdate: new Date(),
        staffname: '',
        name: '',
        gender: [],
        phone: '',
        vendorname: '',
        problem: [],
        isillegal: [],
        rqstcontent: '',
        address: '',
        // 被投诉人信息
        vendorname: '',
        vendorregion: [],
        vendortown: [],
        vendoraddress: '',
        vendorphone: '',
        // 投诉货品或服务信息
        goodsname: '',
        goodsmodel: '',
        goodstype: [],
        nature: [],
        number: '',
        money: '',
        shoppingdate: '',
        happendate: '',
        rqstcontent: '',
        appeal: '',
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
      regionOptions: [['价格', '质量', '售后服务', '其他']],
      illegals: [['否', '是', '待确认']],
      demo2: '登记信息',
      langOptions: [['顺德区市监局大良分局', '顺德区市监局容桂分局','顺德区市监局伦教分局','顺德区市监局北滘分局',
                      '顺德区市监局陈村分局','顺德区市监局乐从分局','顺德区市监局龙江分局','顺德区市监局勒流分局',
                      '顺德区市监局杏坛分局','顺德区市监局均安分局']],
      operatorsList: [[]],
      // operatorsList: [['广东省佛山市顺德区深国投商用置业有限公司','顺德区电信分公司','安利顺德专卖店']],
      genderOptions: [['男', '女']],



      deptOptions: [['消保科']],
      jobClassificationOptions: [['普通', '特殊']],
      businessLicenseOptions: [['是', '否']],

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

    toFindOperatorsList(value) {

      let params ={
        region: value.toString()
      }

      selectEnterprise(params).then((res) => {
        // console.log(res);
        if (res.code === 0) {

          this.operatorsList = res.data

          // console.log(this.operatorsList)
        }
      });


    },


    toggleTabs(index) {
      this.nowIndex = index;
    },
    showChoose() {

      // 参数校验
      if(this.dataForm.region.length < 1 || this.dataForm.region[0].length < 1){
        return this.$vux.alert.show({title:'提示', content:'请选择所属辖区'})
      }
      if(this.dataForm.enterprise.length < 1 || this.dataForm.enterprise[0].length < 1){
        return this.$vux.alert.show({title:'提示', content:'请选择经营主体'})
      }
      if(!this.dataForm.registrationdate || this.dataForm.registrationdate.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请选择登记时间'})
      }
      if(!this.dataForm.staffname || this.dataForm.staffname.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写登记人'})
      }
      if(!this.dataForm.rqsttitle || this.dataForm.rqsttitle.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写标题'})
      }
      if(!this.dataForm.name || this.dataForm.name.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写投诉人姓名/名称'})
      }
      if(!this.dataForm.phone || this.dataForm.phone.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写投诉人联系电话'})
      }
      if(!this.dataForm.vendorname || this.dataForm.vendorname.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写被投诉人姓名/名称'})
      }
      if(!this.dataForm.vendorphone || this.dataForm.vendorphone.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写被投诉人联系电话'})
      }
      if(!this.dataForm.goodsname || this.dataForm.goodsname.length < 1){
        return this.$vux.alert.show({title:'提示', content:'请填写投诉货品服务名称'})
      }
      console.log(this.dataForm.goodstype)
      if(this.dataForm.goodstype.length < 1 || this.dataForm.goodstype[0].length < 1){
        return this.$vux.alert.show({title:'提示', content:'请选择投诉类型'})
      }

      if(this.dataForm.nature.length < 1 || this.dataForm.nature[0].length < 1){
        return this.$vux.alert.show({title:'提示', content:'请选择投诉性质'})
      }

      let tempParams = {};

      for(let key in this.dataForm){
        tempParams[key] = this.dataForm[key];
      }

      if(tempParams.registrationdate != null){
        tempParams.registrationdate =  tempParams.registrationdate.getTime();
      }
      if(tempParams.shoppingdate != null){
        tempParams.shoppingdate =  new Date(tempParams.shoppingdate).getTime();
      }
      if(tempParams.happendate != null){
        tempParams.happendate =  new Date(tempParams.happendate).getTime();
      }

      let datas = JSON.parse(JSON.stringify(tempParams))

        for( let key in datas){
            if( datas[key] instanceof Array){
              if( datas[key].length > 0 ){
                datas[key] = datas[key].pop();
              }else{
                datas[key] = null
              }
            }
        }

        let params = {  data: datas ,  user:JSON.parse(localStorage.getItem('user')) }
      console.log(params)
      const cur = this;
      save(params).then((res) => {
        // console.log(res);
        if (res.code === 0) {
          this.$vux.alert.show({
            title: '提示',
            content: '提交成功',
            onShow() {
            },
            onHide() {
              // 返回上一页/首页
              cur.$router.back();
            },
          });

        }
      });



      // this.show = true;
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
      }else {
        console.log(this.stepValue);
      }
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

  },
  mounted(){
      // this.getListDetail();
  },
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
span.important{color:red}
</style>
