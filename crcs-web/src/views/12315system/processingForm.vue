<template>
  <section class="work_review" v-loading="loading" element-loading-text="拼命加载中" id="whole">
    <!--<el-card class="box-card">-->
      <!--<div class="text item">-->
        <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick"
                 style="width: 96%;margin: 15px auto;">
          <el-tab-pane label="基本信息" name="first" >

            <div style="text-align: right;margin-bottom:20px" :class="{'fixed':scrollTop > height}" >
              <el-button type="primary" @click="save" :loading="submitLoading">保存</el-button>
              <el-button type="primary" @click="triggerTab('tabAcceptance')" >
                受理
              </el-button>
              <el-button type="primary" @click="triggerTab('tabAssignment')"
                         v-if="user.aicPermissions.subordinate&&unitList.length>0">分派下级
              </el-button>
              <el-button type="primary" @click="triggerTab('tabNotAcceptance')">
                不受理
              </el-button>
              <el-button type="primary" @click="toOperating('back')">退回</el-button>
            </div>

            <el-collapse v-model="collapseValues" class="accordion">
              <el-collapse-item name="orderInfo">
                <div slot="title">
                  <el-row class="accordion_title mb10">
                    <el-col :span="12">
                      <em>1.</em> 定单信息
                    </el-col>
                    <!-- <el-col :span="10" class="right">定单号：</el-col> -->
                  </el-row>
                </div>
                <el-row>
                  <el-form :model="orderInfo">
                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                      <el-form-item
                        label="登记编号："
                        class="label"
                        label-position="right"
                        label-width="120px"
                      >
                        <el-input
                          v-model="orderInfo.registerNum"
                          class="inputWidth"
                          placeholder="请输入登记编号"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                      <el-form-item
                        label="记录类型: "
                        class="label"
                        label-position="right"
                        label-width="120px"
                      >
                        <el-select
                          v-model="orderInfo.recordType"
                          class="inputWidth"
                          placeholder="请选择"
                        >
                          <el-option
                            v-for="item in recordTypeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                      <el-form-item
                        label="登记日期: "
                        class="label"
                        label-position="right"
                        label-width="120px"
                      >
                        <el-date-picker
                          v-model="orderInfo.registerDate"
                          type="date"
                          placeholder="选择日期"
                          :picker-options="registerDateOptions"
                          class="inputWidth"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                      <el-form-item
                        label="接收方式："
                        class="label"
                        label-width="120px"
                        label-position="right"
                      >
                        <el-select
                          v-model="orderInfo.acceptType"
                          placeholder="请选择"
                          class="inputWidth"
                        >
                          <el-option
                            v-for="item in acceptTypeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                      <el-form-item
                        label="来电号码："
                        class="label"
                        label-width="120px"
                        label-position="right"
                      >
                        <el-input
                          v-model="orderInfo.phoneNum"
                          placeholder="请输入来电号码"
                          class="inputWidth"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-form>
                </el-row>
              </el-collapse-item>
              <el-collapse-item name="formContent">
                <div slot="title" @click.stop>
                  <el-row class="el-accordion title_accordion">
                    <el-col :span="12">
                      <em>2.</em>
                      消费投诉表(固)
                      <!--{{formTypeStr}}-->
                    </el-col>
                    <el-col
                            :span="10"
                            style="color: #48576a;text-align: right"
                    >表单号：<!--{{tasklist.serialnum}}-->
                    </el-col>
                  </el-row>
                </div>
                <!-- 12315消费投诉 -->
                <complaintmoduleForm :complaintform="complaintform"  v-if="complaintform"/>
                <!--<ComplaintmoduleInfo :complaintform="complaintform"  v-if="complaintform"/>-->
                <!-- 12315经济违法 -->
                <!--<economyform :economyform="economyform" v-if="economyform"/>-->
                <!--<EconomyInfo :economyform="economyform" v-if="economyform"/>-->
              </el-collapse-item>
              <el-collapse-item name="officeRequirements">
                <div slot="title" @click.stop>
                  <el-row class="el-accordion title_accordion title_accordion">
                    <el-col :span="12">
                      <em>3.</em> 处办要求
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                  </el-row>
                </div>
                <el-form :model="handledemand" label-width="220px" ref="handledemand">
                  <el-row>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="初步意见应反馈时限：" class="bt">
                        <el-date-picker
                                v-model="handledemand.opiniontimeshould"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="初步意见反馈时间：" class="bt">
                        <el-date-picker
                                v-model="handledemand.opiniontime"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="应受理时限：" class="bt">
                        <el-date-picker
                                v-model="handledemand.accepttimeshould"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="应受理到期时间：" class="bt">
                        <el-date-picker
                                v-model="handledemand.accepttime"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="应立案时限：" class="bt">
                        <el-date-picker
                                v-model="handledemand.caseputtimeshould"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="应立案到期时间：" class="bt">
                        <el-date-picker
                                v-model="handledemand.caseputtime"
                                type="datetime"
                                class="input-text"
                                placeholder="选择时间"
                        ></el-date-picker>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </el-collapse-item>
              <el-collapse-item name="businessInfo">
                <div slot="title" @click.stop>
                  <el-row class="el-accordion title_accordion">
                    <el-col :span="12">
                      <em>4.</em> 办理跟踪
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                  </el-row>
                </div>
                <tracking :data="opinionList"/>
              </el-collapse-item>
            </el-collapse>
          </el-tab-pane>
          <el-tab-pane name="operating" label="退回" v-if="operating==='back'" class="operating">
            <el-form
                    :model="backForm"
                    ref="backForm"
                    label-width="200px"
                    label-suffix="："
                    :rules="backFormRules"
            >
              <el-row>
                <el-col :xs="24" :sm="24" :md="16" :lg="16">
                  <el-form-item label="退回原因" class="form_bt" prop="reason">
                    <el-select v-model="backForm.reason" placeholder="请选择退回原因" @change="reasonChange()">
                      <el-option label="缺失要素" value="缺失要素"></el-option>
                      <el-option label="表述不清" value="表述不清"></el-option>
                      <el-option label="类型有误" value="类型有误"></el-option>
                      <el-option label="地址不详" value="地址不详"></el-option>
                      <el-option label="已有知识" value="已有知识"></el-option>
                      <el-option label="重复受理" value="重复受理"></el-option>
                      <el-option label="市民撤诉" value="市民撤诉"></el-option>
                      <el-option label="不属本职" value="不属本职"></el-option>
                      <el-option label="其他原因" value="其他原因"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="16" :lg="16">
                  <el-form-item label="退回描述" class="form_bt" prop="opinion">
                    <el-input
                            type="textarea"
                            v-model="backForm.opinion"
                            placeholder="请输入退回描述（1000字内）"
                            :rows="5"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="16" :lg="16" v-if="user.regionID != '440681'">
                  <el-form-item label="签收人" prop="leader" class="form_bt">
                    <el-select v-model="backForm.leader" placeholder="请选择签收人">
                      <el-option
                              v-for="item in leaderLists"
                              :key="item.region"
                              :label="item.name"
                              :value="item.primaryKey"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="16" :lg="16">
                  <el-form-item label="附件清单" class="form_bt">
                    <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            multiple
                    >
                      <el-button size="small" type="primary">选择附件</el-button>
                    </el-upload>
                  </el-form-item>
                </el-col>
                <el-col class="centered">
                  <el-button type="primary" @click="submitbackForm" :loading="operatingLoading">退回</el-button>
                  <el-button @click="resetOperating">取消</el-button>
                </el-col>
              </el-row>
            </el-form>
          </el-tab-pane>
          <!-- 受理 -->
          <el-tab-pane label="受理" name="tabAcceptance" v-if="tabAction==='tabAcceptance'">
            <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                     class="text_bold" label-suffix="：">
              <el-row>
                <el-col>
                  <el-form-item label="是否受理" prop="isAccep">
                    <div style="line-height: 35px">受理</div>
                  </el-form-item>
                </el-col>
                <el-col>
                  <el-form-item label="受理方式" prop="mode">
                    <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px;">
                      <el-option label="调解" value="调解"></el-option>
                      <el-option label="查处" value="查处"></el-option>
                      <el-option label="调查" value="调查"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col class="centered">
                  <el-button type="primary" @click="submitAcceptance" :loading="submitLoading">确定</el-button>
                  <el-button @click="cancelTab">取消</el-button>
                </el-col>
              </el-row>
            </el-form>
          </el-tab-pane>
          <!-- 不受理 -->
          <el-tab-pane label="不受理" name="tabNotAcceptance" v-if="tabAction==='tabNotAcceptance'">
            <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                     class="text_bold" label-suffix="：">
              <el-row>
                <el-col>
                  <el-form-item label="是否受理" prop="isAccep">
                    <div style="line-height: 35px">不受理</div>
                  </el-form-item>
                </el-col>
                <el-col>
                  <el-form-item label="不受理原因" prop="reason">
                    <el-select v-model="acceptForm.reason" placeholder="请选择不受理原因">
                      <el-option label="交付单位不正确" value="交付单位不正确"></el-option>
                      <el-option label="可通过查阅知识库解决" value="可通过查阅知识库解决"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="16">
                  <el-form-item label="不受理内容" prop="opinion">
                    <el-input type="textarea" v-model="acceptForm.opinion" placeholder="请输入不受理内容（1000字内）"
                              :rows="5"></el-input>
                  </el-form-item>
                </el-col>
                <el-col class="centered">
                  <el-button type="primary" @click="submitNotAcceptance" :loading="submitLoading">确定
                  </el-button>
                  <el-button @click="cancelTab">取消</el-button>
                </el-col>
              </el-row>
            </el-form>
          </el-tab-pane>
          <!-- 分派下级 -->
          <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabAction==='tabAssignment'">
            <el-form :model="assignmentForm" label-width="200px" ref="assignmentForm" label-suffix="："
                     :rules="assignmentFormRules" class="text_bold">
              <el-row>
                <el-col :xs="24" :sm="24" :md="24" :lg="16">
                  <el-form-item label="成员单位" prop="unit">
                    <el-select v-model="assignmentForm.unit" placeholder="请选择成员单位" style="width:100%">
                      <el-option
                              v-for="item in unitList"
                              :key="item.region"
                              :label="item.name"
                              :value="item.primaryKey"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="16">
                  <el-form-item label="抄送部门" class="form_bt" prop="ortherDept">
                    <el-select v-model="assignmentForm.ortherDept" multiple placeholder="请选择抄送部门"
                               style="width:100%" @change="orgPermission()">
                      <el-option
                              v-for="item in deptList"
                              :key="item.region"
                              :label="item.name"
                              :value="item.orgunitID"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="16">
                  <el-form-item label="分派意见" prop="opinion">
                    <el-input type="textarea" :rows="5" placeholder="请输入分派意见（3000字内）"
                              v-model="assignmentForm.opinion"></el-input>
                  </el-form-item>
                </el-col>
                <el-col class="centered">
                  <el-button type="primary" @click="submitAssignment" :loading="submitLoading">确定</el-button>
                  <el-button @click="cancelTab">取消</el-button>
                </el-col>
              </el-row>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      <!--</div>-->
    <!--</el-card>-->
  </section>
</template>
<script>

    import {
        nextWork, findOrgUnitByRegionId, findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,
    } from "../../api/convenient12315";

    import ComplaintmoduleForm from "./components/ComplaintmoduleForm";
    import ComplaintmoduleInfo from "./components/ComplaintmoduleInfo";
    import Economyform from "./components/Economyform";
    import EconomyInfo from "./components/EconomyInfo";
    import Tracking from "../../components/Tracking";
    import {myMixin } from "../../assets/mixin/mixin"

export default {
    mixins:[myMixin],
    name: "WorkReview",
    components: {
        ComplaintmoduleInfo,
        ComplaintmoduleForm,
        Economyform,
        EconomyInfo,
        Tracking
    },
  data() {
    return {
        user: JSON.parse(localStorage.getItem("user")),
        formType: "", //表单类型：economyform:经济违法，complaintmodule:消费投诉。
        formTypeStr: "",
        unitList: [],
        deptList: [],
        depts: '',
        leaderLists: [],
        acceptForm: {
            isAccep: "",
            opinion: "",
            department: "",
            content: "",
            reason: ""
        },
        operating: "",
      loading: false,
        submitLoading: false,
      activeName: "first",
        tabAction: "basicInfo",
        assignmentForm: {
            ortherDept: [],
        },
        acceptFormRules: {
            isAccep: [{required: true}],
            reason: [
                {required: true, message: "请选择不受理原因", trigger: "change"}
            ],
            mode: [
                {required: true, message: "请选择受理方式", trigger: "change"}
            ],
            opinion: [{required: true, message: "请输入内容", trigger: "blur"},
                {max: 1000, message: "不能超过1000字符", trigger: "blur"}
            ]
        },
        //办理追踪
        opinionList: [],
        //待办表单
        tasklist: {
            id: "",
            caseguid: "",
            focus: "",
            seriaopinionlnum: "",
            tserialnum: "",
            rqsttitle: "",
            accordtype: "",
            labelname: "",
            assigndate: "",
            expiredate: "",
            resulttimemin: "",
            systemsource: "",
            issignin: "",
            signinname: "",
            signinuserid: "",
            signindeptid: "",
            signdate: "",
            updatedate: ""
        },
        //消費
        complaintform: {
            id: "",
            caseguid: "",
            formtype: "",
            rqsttitle: "",
            rqstperson: "",
            gender: "",
            rqstnumber: "",
            linknumber: "",
            usertype: "",
            answertype: "",
            providetype: "",
            providelb: "",
            focus: "",
            region: "",
            age: "",
            nation: "",
            status: "",
            complaintpaper: "",
            disabilityidentification: "",
            certificate: "",
            codepostal: "",
            idnumber: "",
            career: "",
            company: "",
            subjectname: "",
            linktype: "",
            subjectphone: "",
            aicstationid: "",
            postcode: "",
            jyareacode: "",
            subjectaddress: "",
            zcareacode: "",
            subjectzcadd: "",
            cksubjectname: "",
            ckjyareacode: "",
            cksubjectaddress: "",
            objname: "",
            brand: "",
            objclassify: "",
            objmoney: 0,
            producecompany: "",
            isweb: "",
            importmark: "",
            typespecification: "",
            quantity: "",
            measureunit: "",
            price: "",
            purchasingdate: "",
            problemtype: "",
            problemdescribe: "",
            keyword: "",
            govlabeloutput: "",
            differserv: "",
            briefing: "",
            anestimate: "",
            economicloss: "",
            tortclassify: "",
            personalinjury: "",
            injurydegree: "",
            injuries: "",
            consumptionsafety: "",
            consumptionsafetytype: "",
            even: "",
            reduction: "",
            report: "",
            isoutmall: "",
            attachfilename: "",
            updatedate: "",
            appealsource: "",
            informationopen: "",
            businesstype: "",
            eventlevel: "",
            rqstaddress: "",
            registrationnumber: "",
            recorddate: "",
            // recorddate: "",
            receivemode: "",
            linkaddress: "",
            emaiaddress: "",
            registerno: "",
            entitytype: "",
            shoppingmode: "",
            servicetype: "",
            complaintstype: "",
            contentrs: "",
            disputedate: "",
            ordernumber: "",
            businesstypecodeid: null,
            entitytypecodeid: null,
            servicetypecodeid: null,
            complaintstypecodeid: null,
            worktype: "",
            providetypecodeid: null,
            objclassifycodeid:null,
        },
        economyform: {
            id: "",
            caseguid: "",
            formtype: "",
            rqsttitle: "",
            rqstperson: "",
            gender: "",
            businesstype: "",
            rqstaddress: "",
            rqstnumber: "",
            linknumber: "",
            usertype: "",
            rqsttype: "",
            focus: "",
            providelb: "",
            appealsource: "",
            informationOpen: "",
            subjectname: "",
            subjectphone: "",
            aicstationid: "",
            postcode: "",
            subjectaddress: "",
            subjectzcadd: "",
            objname: "",
            brand: "",
            objclassify: "",
            // objmoney: "",
            producecompany: "",
            isweb: "",
            importmark: "",
            typespecification: "",
            quantity: "",
            measureunit: "",
            price: "",
            purchasingdate: "",
            problemtype: "",
            illegalrules: "",
            eventlevel: "",
            problemdescribe: "",
            keyword: "",
            isoutmall: "",
            isfeedback: "",
            complaintpaper: "",
            isaward: "",
            attachfilename: "",
            updatedate: "",
            recorddate: "",
            worktype: "",
            sdexpirationdate: "",
            expirationdate: "",
            tortclassify: "",
            providetypecodeid: null,
            businesstypecodeid: null,
            reportprovidelbcodeid: null,
            illegalrulescodeid:null,
            region: "",
            objectcategory: "",
            objectcategorycodeid:null,
            problemtypecodeid:null,
            tortclassifycodeid:null,
        },
        handledemand: {
            id: "",
            caseguid: "",
            opiniontimeshould: "",
            opiniontimesurplus: "",
            opiniontime: "",
            accepttimeshould: "",
            accepttimesurplus: "",
            accepttime: "",
            caseputtimeshould: "",
            caseputtimesurplus: "",
            caseputtime: "",
            resulttimeshould: "",
            resulttimesurplus: "",
            resulttime: "",
            opiniontimeminstr: "",
            accepttimeminstr: "",
            caseputtimeminstr: "",
            resulttimeminstr: "",
            updatedate: ""
        },
      dealListDetail: {
        registerNum: 0
      },
      collapseValues: [
        "orderInfo",
        "formContent",
        "officeRequirements",
        "businessInfo",
        "processTracking"
      ],
      orderInfo: {
        registerNum: "",
        recordType: "",
        registerDate: "",
        acceptType: ""
      },
      complainantInfo: {
        name: "",
        tel: 0,
        gender: "",
        address: "",
        documentType: "",
        documentNum: "",
        providerType: "",
        inageGroupOptions: "",
        email: "",
        nationality: "",
        Nation: "",
        identity: "",
        otherContactInfo: ""
      },
      objectOfComplaint: {
        name: "",
        address: "",
        marketSubjectType: "",
        industryClassification: "",
        tel: "",
        identificationNumber: "",
        Contacts: "",
        email: ""
      },
      businessInfo: {
        objectClass: "",
        goodsName: "",
        brand: "",
        salesWay: "",
        payMoney: "",
        orderNum: "",
        date: "",
        contentOfAppeal: "",
        questionCategory: "",
        productName: "",
        approvalNumber: "",
        productBatchNumber: "",
        productSpecification: "",
        salesEnterprise: "",
        productValidity: "",
        keyWord: "",
        questionType:"",
        producer:"",
        sell:""
      },
        questionTypeoOptions:[],
      questionCategoryOptions: [],
      acceptTypeOptions: [],
      recordTypeOptions: [],
      questionCategoryOptions: [],
      salesWayOptions: [],
      objectClassOptions: [],
      industryClassificationOptions: [],
      nationalityOptions: [],
      marketSubjectTypeOptions: [],
      inageGroupOptions: [],
      documentTypeOptions: [],
      identityOptions: [],
      providerTypeOptions: [],
      nationality: [],
      NationOptions: [],
      genderOptions: [
        {
          label: "女",
          value: "weman"
        },
        {
          label: "男",
          value: "man"
        }
      ],
      registerDateOptions: {
        // disabledDate(time) {
        //   return time.getTime() < Date.now() - 8.64e7;
        // }
      },
      backForm:{
          opinion:"",
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
      findUnit() {
          //获取下级单位列表
          findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
              if (data.data) {
                  this.unitList = data.data;
              }
          });
      },
      unitChange() {
          //获取部门列表
          findOrgDeptByOrgId({orgId: this.user.orgID}).then(res => {
              this.deptList = res.data;
          });
      },
      orgPermission() {
          let unit = this.assignmentForm.ortherDept[this.assignmentForm.ortherDept.length - 1]
          let para = {
              deptid: unit,
              permission: "dealdepartment"
          };
          if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
              findUsersByDeptIdAndPermission(para).then(res => {
                  if (res.data == null) {
                      this.$alert("该部门没有查看该业务权限的人员，请先添加权限!", "提示", {
                          confirmButtonText: "确定"
                      });
                      this.assignmentForm.ortherDept.splice(this.assignmentForm.ortherDept.indexOf(this.assignmentForm.ortherDept[this.assignmentForm.ortherDept.length - 1]))
                  }
              });
          }
      },
      cancelTab() {
          this.tabAction = "basicInfo";
      },
      toOperating(value) {
          this.operating = value;
          this.tabAction = "operating";
          this.delayPersonnel();
      },
      submitAcceptance() {
          //受理
          let validation = false;
          this.$refs.acceptForm.validate(valid => (validation = valid));
          if (!validation) return;
          this.$confirm("请确认无误提交?", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              this.submitLoading = true;
              let data;
               if ("complaintmodule" === this.formType) {
                  if(this.complaintform.providetypecodeid != null)(
                      this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[this.complaintform.providetypecodeid.length - 1].toString()
                  )
                  if(this.complaintform.objclassifycodeid != null)(
                      this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[this.complaintform.objclassifycodeid.length - 1].toString()
                  )
                  if(this.complaintform.complaintstypecodeid != null)(
                      this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[this.complaintform.complaintstypecodeid.length - 1].toString()
                  )
                  data = this.complaintform;

              } else if ("comeconomicmodule" === this.formType) {
                  if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                      this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                      this.economyform.objectcategorycodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                      this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                      this.economyform.problemtypecodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                      this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                      this.economyform.illegalrulescodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                      this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                      this.economyform.businesstypecodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                      this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                      this.economyform.tortclassifycodeid.length - 1
                          ].toString();
                  }

                  console.log(this.econmyform)
                  data = this.economyform;
              }
              let params = {
                  user: this.user,
                  formType: this.formType,
                  node: this.nodeStr,
                  nextNodeId: "accept",
                  data: data,
                  opinion: this.acceptForm.mode
              };
              nextWork(params)
                  .then(data => {
                      if (data.code === 0) {
                          let params = {
                              id: this.$route.query.id,
                              caseguid: this.$route.query.caseguid,
                              ismain: "1",
                          };
                          this.$alert("提交成功！", "提示", {
                              confirmButtonText: "确定",
                              type: "success",
                              callback: action => {
                                  if ("comeconomicmodule" === this.formType) {
                                      this.$router.push({
                                          path: "/listDetail/lacl",
                                          query: params
                                      });
                                  } else {
                                      this.$router.push({
                                          path: "/listDetail/clfpysth",
                                          query: params
                                      });
                                  }
                              }
                          });
                      } else {
                          this.errorAlert();
                      }
                      this.submitLoading = false;
                  })
                  .catch(data => {
                      this.errorAlert();
                      this.submitLoading = false;
                  });
          });
      },
      submitNotAcceptance() {
          //不受理
          let validation = false;
          this.$refs.acceptForm.validate(valid => (validation = valid));
          if (!validation) return;
          this.$confirm("请确认无误提交?", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              this.submitLoading = true;
              let params;
              let data;
              if ("complaintmodule" === this.formType) {
                  data = this.complaintform;

              } else  {
                  data = this.economyform;
              }
              if (this.user.regionID == "440681") {
                  params = {
                      user: this.user,
                      formType: this.formType,
                      node: this.nodeStr,
                      nextNodeId: "noAccept",
                      data,
                      opinion: this.acceptForm.opinion,
                      noAcceptreason: this.acceptForm.reason,
                      isEnd: '1',
                  };
              } else {
                  params = {
                      user: this.user,
                      formType: this.formType,
                      node: this.nodeStr,
                      nextNodeId: "noAccept",
                      data,
                      opinion: this.acceptForm.opinion,
                      noAcceptreason: this.acceptForm.reason,
                  };
              }
              nextWork(params)
                  .then(data => {
                      if (data.code === 0) {
                          this.$alert("提交成功！", "提示", {
                              confirmButtonText: "确定",
                              type: "success",
                              callback: action => {
                                  this.$router.push("/wddb/12345db");
                              }
                          });
                      } else {
                          this.errorAlert();
                      }
                      this.submitLoading = false;
                  })
                  .catch(data => {
                      this.errorAlert();
                      this.submitLoading = false;
                  });
          });
      },
      triggerTab(value) {
          this.tabAction = value;
          if ("tabAcceptance" === value) {
              this.acceptForm.isAccep = "受理";
          } else if ("tabNotAcceptance" === value) {
              this.acceptForm.isAccep = "不受理";
          }
          this.assignmentForm.opinion = "受理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
      },
      save() {
          this.$confirm("请确认无误提交?", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              this.submitLoading = true;
              let handledemand = this.handledemand;
              for (let key in handledemand) {
                  if (handledemand[key] instanceof Date) {
                      handledemand[key] = handledemand[key].getTime();
                  }
              }
              let params = {
                  handledemand,
                  customerinfo: this.customerinfo
              };
              params.handledemand = params.handledemand;
              if (this.complaintform) {
                  if (this.complaintform.providetypecodeid != null && this.complaintform.providetypecodeid != "") {
                      this.providetypecodeid1 = this.complaintform.providetypecodeid
                      this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[
                      this.complaintform.providetypecodeid.length - 1
                          ].toString();
                  }
                  if (this.complaintform.objclassifycodeid != null && this.complaintform.objclassifycodeid != "") {
                      this.objclassifycodeid1 = this.complaintform.objclassifycodeid
                      this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[
                      this.complaintform.objclassifycodeid.length - 1
                          ].toString();
                  }
                  if (this.complaintform.complaintstypecodeid != null && this.complaintform.complaintstypecodeid != "") {
                      this.complaintstypecodeid1 = this.complaintform.complaintstypecodeid
                      this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[
                      this.complaintform.complaintstypecodeid.length - 1
                          ].toString();
                  }console.log(this.complaintform)
                  params.complaintform = this.complaintform;
              } else if (this.economyform) {
                  if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                      this.objclassifycodeid1 = this.economyform.objectcategorycodeid
                      this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                      this.economyform.objectcategorycodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                      this.problemtypecodeid1 = this.economyform.problemtypecodeid
                      this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                      this.economyform.problemtypecodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                      this.illegalrulescodeid1 = this.economyform.illegalrulescodeid
                      this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                      this.economyform.illegalrulescodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                      this.businesstypecodeid1 = this.economyform.businesstypecodeid
                      this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                      this.economyform.businesstypecodeid.length - 1
                          ].toString();
                  }
                  if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                      this.tortclassifycodeid1 = this.economyform.tortclassifycodeid
                      this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                      this.economyform.tortclassifycodeid.length - 1
                          ].toString();
                  }

                  console.log(this.econmyform)
                  params.economyform = this.economyform;
              }
              saveBmfwBaseInfo(params).then(data => {
                  this.submitLoading = false;
                  this.$alert("保存成功!", "提示", {
                      confirmButtonText: "确定",
                      type: "success"
                  });
                  console.log(123)
              });


          });
      },
    reasonChange(){
      if('缺失要素' == this.backForm.reason){
        console.log(this.backForm.reason)
        this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。"
      }else if ('表述不清' == this.backForm.reason){
        this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。"
      }else if ('类型有误' == this.backForm.reason){
        this.backForm.opinion = "此单退回，请修改表单类型为：xxx。"
      }else if ('地址不详' == this.backForm.reason){
        this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。"
      }else if ('已有知识' == this.backForm.reason){
        this.backForm.opinion = "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。"
      }else if ('重复受理' == this.backForm.reason){
        this.backForm.opinion = "此单退回，此工单与另一工单（表单号：xxx）属重复受理。"
      }else if ('市民撤诉' == this.backForm.reason){
        this.backForm.opinion = "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。"
      }else if ('不属本职' == this.backForm.reason){
        this.backForm.opinion = "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。"
      }else{
        this.backForm.opinion = "其它原因"
      }
    },

  }
};
</script>
<style lang="scss" scope>
.el-form-item__content {
  text-align: center;
}
.el-date-editor.el-input {
  width: 200px;
}

.work_review {
  margin-top: 10px;
  overflow-x: hidden;

  .el-table__body-wrapper {
    overflow: hidden;
  }

  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .el-input input {
    height: 30px;
  }
  .bt {
    font-weight: bold;
    text-align: right;
  }

  .clearfix {
    font-weight: bold;
  }

  .el-accordion {
    font-size: 14px;
    font-weight: bold;
    color: #1e1f20;
    cursor: auto;
  }

  .title_accordion {
    color: #40a9ff;
  }

  .element.style {
    margin-left: 150px;
    float: left;
  }
  .operating .el-form .el-row {
    margin: 0;
  }
  .form_bt {
    font-size: 13px;
    color: #48576a;
    font-weight: bold;
  }
}
.label {
  font-size: 13px;
  color: #48576a;
  font-weight: bold;
}
.el-col {
  border-radius: 4px;
}
.inputWidth {
  width: 200px;
  height: 40px;
}
.work_review .el-input input {
  width: 100%;
  height: 40px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>