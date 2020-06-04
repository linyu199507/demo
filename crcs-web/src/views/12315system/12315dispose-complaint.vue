<template>
    <section class="work_review" v-loading="loading" element-loading-text="拼命加载中" id="whole">
        <!--<el-card class="box-card">-->
        <!--<div class="text item">-->
        <el-tabs
                v-model="activeName"
                type="border-card"
                @tab-click="handleClick"
                style="width: 96%;margin: 15px auto;"
        >
            <el-tab-pane label="基本信息" name="first">
                <div style="text-align: right; margin-bottom: 20px" v-if="ismain" :class="{'fixed':scrollTop > height}">
                    <el-button type="primary" size="small" icon="document" @click="exportToWord"
                               :loading="exportLoading"
                               style="margin: 2px; border-radius: 13px">
                        <template v-if="exportLoading">
                            正在导出...
                        </template>
                        <template v-else>
                            <i class="fa fa-download"></i>
                            Word导出
                        </template>
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')">受理</el-button>
                    <el-button
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                    >分派下级
                    </el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabNotAcceptance')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                        不受理</el-button>
                    <el-button type="primary" @click="toOperating('back')" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">退回</el-button>
                </div>
                <div style="text-align: right;height: 50px" v-if="ismains">
                    <el-button type="primary" @click="triggerTab('tabOpinion')">意见填写</el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <!-- <em>2.</em>-->
                                    消费投诉表
                                    <!--{{formTypeStr}}-->
                                </el-col>
                                <el-col
                                        :span="10"
                                        style="color: #48576a;text-align: right"
                                ><!--工单号：{{tasklist.tserialnum}}-->
                                </el-col>
                            </el-row>
                        </div>
                        <!-- 12315消费投诉 -->
                        <ComplaintmoduleInfo :complaintform="complaintform" v-if="complaintform" :tasklist="tasklist"/>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <el-tab-pane label="办理跟踪" name="second">
                <el-collapse v-model="collapseValues">
                    <el-collapse-item name="businessInfo" class="accordion">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>1.</em>
                                    办理跟踪
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
                        <!--            <el-col :xs="24" :sm="24" :md="16" :lg="16" v-if="user.regionID != '440681'">-->
                        <!--              <el-form-item label="签收人" prop="leader" class="form_bt">-->
                        <!--                <el-select v-model="backForm.leader" placeholder="请选择签收人">-->
                        <!--                  <el-option-->
                        <!--                    v-for="item in leaderLists"-->
                        <!--                    :key="item.region"-->
                        <!--                    :label="item.name"-->
                        <!--                    :value="item.primaryKey"-->
                        <!--                  ></el-option>-->
                        <!--                </el-select>-->
                        <!--              </el-form-item>-->
                        <!--            </el-col>-->
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
                            <el-button type="primary" @click="submitbackForm">退回</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 受理 -->
            <el-tab-pane label="受理" name="tabAcceptance" v-if="tabAction==='tabAcceptance'">
                <el-form
                        :model="acceptForm"
                        label-width="200px"
                        ref="acceptForm"
                        :rules="acceptFormRules"
                        class="text_bold"
                        label-suffix="："
                >
                    <el-row>
                        <el-col>
                            <el-form-item label="是否受理" prop="isAccep">
                                <div style="line-height: 35px;">受理</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="受理方式" prop="mode">
                                <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                         <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="详细情况说明" prop="details" >
                               <el-input type="textarea" v-model="acceptForm.details"  :rows="5"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAcceptance" :loading="submitLoading">确定</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 不受理 -->
            <el-tab-pane label="不受理" name="tabNotAcceptance" v-if="tabAction==='tabNotAcceptance'">
                <el-form
                        :model="acceptForm"
                        label-width="200px"
                        ref="acceptForm"
                        :rules="acceptFormRules"
                        class="text_bold"
                        label-suffix="："
                >
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
                                <el-input
                                        type="textarea"
                                        v-model="acceptForm.opinion"
                                        placeholder="请输入不受理内容（1000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitNotAcceptance" :loading="submitLoading">确定
                            </el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabAction==='tabAssignment'">
                <el-form
                        :model="assignmentForm"
                        label-width="200px"
                        ref="assignmentForm"
                        label-suffix="："
                        :rules="assignmentFormRules"
                        class="text_bold"
                >
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
                            <el-form-item label="抄送部门" class="form_bt">
                                <el-select
                                        v-model="assignmentForm.ortherDept"
                                        multiple
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        @change="orgPermission()"
                                >
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
                                <el-input
                                        type="textarea"
                                        :rows="5"
                                        placeholder="请输入分派意见（3000字内）"
                                        v-model="assignmentForm.opinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAssignment" :loading="submitLoading">确定</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派部门 -->
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabAction==='tabDepartment'">
                <el-form
                        :model="departmentForm"
                        label-width="200px"
                        ref="departmentForm"
                        label-suffix="："
                        :rules="departmentFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="主导部门" prop="leading">
                                <el-select
                                        v-model="departmentForm.leading"
                                        placeholder="请选择部门"
                                        style="width:100%"
                                        @change="orgPermission('2')"
                                        filterable
                                >
                                    <el-option
                                            v-for="item in departmentList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.orgunitID"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送部门">
                                <el-select
                                        v-model="departmentForm.ccs"
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        multiple
                                        @visible-change="ifMainDepartment"
                                        @change="orgPermission('3')"
                                        ref="dCcsSelect"
                                        filterable
                                >
                                    <el-option
                                            v-for="item in filterDepartmentList()"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.orgunitID"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="分派意见" prop="opinion">
                                <el-input
                                        type="textarea"
                                        :rows="5"
                                        placeholder="请输入分派意见（3000字内）"
                                        v-model="departmentForm.opinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDepartment" :loading="submitLoading">确定</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 意见填写 -->
            <el-tab-pane label="意见填写" name="tabOpinion" v-if="tabAction==='tabOpinion'">
                <el-form
                        :model="opinionForm"
                        label-width="200px"
                        ref="opinionForm"
                        :rules="opinionFormRules"
                        class="text_bold"
                        label-suffix="："
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="意见" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="opinionForm.opinion"
                                        placeholder="请输入意见（3000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="copyOpinion" :loading="submitLoading">确定</el-button>
                            <el-button @click="handleClick">取消</el-button>
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
        nextWork,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,
        findUsersByOrgAndPermission, findUsersByDeptIdAndJurisdiction
    } from "../../api/convenient12315";

    import {selectBusinessBaseInfo} from "../../api/api";

    import ComplaintmoduleForm from "./components/ComplaintmoduleForm";
    import ComplaintmoduleInfo from "./components/ComplaintmoduleInfo";
    import Economyform from "./components/Economyform";
    import EconomyInfo from "./components/EconomyInfo";
    import Tracking from "../../components/Tracking";
    import {myMixin} from "../../assets/mixin/mixin"

    export default {
        mixins: [myMixin],
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
                Providetype: "",
                Objclassify: "",
                Complaintstype: "",
                Businesstype: "",
                Industryname:"",
                exportLoading: false,
                user: JSON.parse(localStorage.getItem("user")),
                formType: "", //表单类型：economyform:经济违法，complaintmodule:消费投诉。
                formTypeStr: "",
                unitList: [],
                deptList: [],
                depts: "",
                leaderLists: [],
                acceptForm: {
                    isAccep: "",
                    opinion: "",
                    department: "",
                    content: "",
                    reason: "",
                    details:"经审查，符合受理条件，决定受理。"
                },
                operating: "",
                loading: false,
                submitLoading: false,
                activeName: "first",
                tabAction: "basicInfo",
                assignmentForm: {
                    ortherDept: [],
                    opinion:"",
                },
                acceptFormRules: {
                    isAccep: [{required: true}],
                    reason: [
                        {required: true, message: "请选择不受理原因", trigger: "change"}
                    ],
                    mode: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入内容", trigger: "blur"},
                        {max: 1000, message: "不能超过1000字符", trigger: "blur"}
                    ],
                    details: [{required: true, message: "请输入内容", trigger: "blur"},
                        {max: 500, message: "不能超过500字符", trigger: "blur"}
                    ]
                },
                departmentForm: {
                    ccs: [],
                    opinion:"",
                },
                departmentFormRules: {
                    leading: [{ required: true, message: "请选择主导部门" }],
                    opinion: [
                        { required: true, message: "请输入分派意见", trigger: "blur" },
                        { max: 3000, message: "不能超出3000字符", trigger: "blur" }
                    ]
                    // ccs: [{required: true, message: "请选择抄送部门"}]
                },
                assignmentFormRules: {
                    unit: [
                        { required: true, message: "请选择成员单位", trigger: "change" }
                    ],
                    opinion: [
                        { required: true, message: "请输入分派意见", trigger: "blur" }
                    ]
                    // ortherDept: [{required: true, message: "请选择抄送部门"}],
                },
                departmentList: [],
                //办理追踪
                opinionList: [],
                backForm: {
                    opinion: "",
                },
                backFormRules: {
                    reason: [
                        {required: true, message: "请选择退回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入退回描述", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ],
                    leader: [
                        {required: true, message: "请选择签收人", trigger: "change"}
                    ],
                },
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
                    objclassifycodeid: null
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
                    questionType: "",
                    producer: "",
                    sell: ""
                },
                questionTypeoOptions: [],
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
                registerDateOptions: {},
                nodeStr: "",
                ismain: true,
                ismains: false,
                opinionForm: {},
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                }
            };
        },
        methods: {
            //导出为Word格式
            exportToWord() {
                //query传参,另一页面进行获取方法
                const querystring = require('querystring');
                let str = querystring.stringify({
                    caseguid: this.$route.query.caseguid,
                    formType: this.formType,
                    tserialnum: this.tasklist.tserialnum,
                    systemsource: this.tasklist.systemsource
                })
                window.open('/crcs/businessSearch/wordexport?' + str)

            },

            handleClick(tab, event) {
                this.tabAction = "";
                this.activeName = "first";
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
            //校验单位或部门是否存在对应权限的人员
            orgPermission(value) {
                if ("1" == value) {
                    let para = {
                        orgid: this.assignmentForm.unit,
                        permission: "subordinate"
                    };
                    if (this.assignmentForm.unit != "") {
                        findUsersByOrgAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该下级机关没有处理该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.assignmentForm.unit = "";
                            }
                        });
                    }
                } else if ("2" == value) {
                    let para = {
                        deptid: this.departmentForm.leading,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.leading != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该部门没有处理该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.departmentForm.leading = "";
                            }
                        });
                    }
                } else if ("5" == value) {
                    let unit = this.assignmentForm.ortherDept[
                    this.assignmentForm.ortherDept.length - 1
                        ];
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
                        findUsersByDeptIdAndJurisdiction(para).then(res => {
                            if (res.data.data == null) {
                                this.$alert(
                                    "该部门没有查看该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.assignmentForm.ortherDept.splice(
                                    this.assignmentForm.ortherDept.indexOf(
                                        this.assignmentForm.ortherDept[
                                        this.assignmentForm.ortherDept.length - 1
                                            ]
                                    )
                                );
                            }
                        });
                    }
                } else {
                    let unit = this.assignmentForm.ortherDept[
                    this.assignmentForm.ortherDept.length - 1
                        ];
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该部门没有查看该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.assignmentForm.ortherDept.splice(
                                    this.assignmentForm.ortherDept.indexOf(
                                        this.assignmentForm.ortherDept[
                                        this.assignmentForm.ortherDept.length - 1
                                            ]
                                    )
                                );
                            }
                        });
                    }
                }
            },
            filterDepartmentList() {
                return this.departmentList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            ifMainDepartment() {
                //判断是否选择部门
                if (!this.departmentForm.leading) {
                    this.$refs.dCcsSelect.visible = false;
                    this.$alert("请先选择主导部门！", "提示", {
                        confirmButtonText: "确定",
                        type: "warning"
                    });
                }
            },
            toOperating(value) {
                this.operating = value;
                this.activeName = "operating";
                this.delayPersonnel();
            },
            //根据当前用户权限查询人员列表
            delayPersonnel() {
                if (this.user.aicPermissions.dealdepartment != undefined) {
                    let para = {
                        permission: "subordinate",
                        deptid: "da00ef72-5b56-4f5b-88c2-4b038a7f41a0"
                    };
                    findUsersByDeptIdAndJurisdiction(para).then(res => {
                        this.leaderLists = res.data.data;
                    });
                } else {
                    let para = {
                        permission: "dealdepartment",
                        orgid: this.user.orgID
                    };
                    findUsersByOrgAndPermission(para).then(res => {
                        this.leaderLists = res.data.data;
                    });
                }
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
                    // instanceof Array
                    if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
                        this.complaintform.providetype = this.Providetype
                    }
                    if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
                        this.complaintform.objclassify = this.Objclassify
                    }
                    if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
                        this.complaintform.complaintstype = this.Complaintstype
                    }
                    if (this.complaintform.businesstype != null && this.complaintform.businesstype != "") {
                        this.complaintform.businesstype = this.Businesstype
                    }
                    if (this.complaintform.industryname != null && this.complaintform.industryname != "") {
                        this.complaintform.industryname = this.Industryname
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "accept",
                        data: this.complaintform,
                        opinion: this.acceptForm.details
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                let params = {
                                    id: this.$route.query.id,
                                    caseguid: this.$route.query.caseguid,
                                    ismain: "1"
                                };
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                this.$router.push({
                                    path: "/listDetail/12315tsgdfk",
                                    query: params
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
            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
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
                    if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
                        this.complaintform.providetype = this.Providetype
                    }
                    if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
                        this.complaintform.objclassify = this.Objclassify
                    }
                    if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
                        this.complaintform.complaintstype = this.Complaintstype
                    }
                    if (this.complaintform.businesstype != null && this.complaintform.businesstype != "") {
                        this.complaintform.businesstype = this.Businesstype
                    }
                    let params;
                    if (this.user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0') {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data: this.complaintform,
                            opinion: this.acceptForm.opinion,
                            noAcceptreason: this.acceptForm.reason,
                            isEnd: "1"
                        };
                    } else {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data: this.complaintform,
                            opinion: this.acceptForm.opinion,
                            noAcceptreason: this.acceptForm.reason
                        };
                    }
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                }, 500);
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
                this.activeName = value;
                if ("tabAcceptance" === value) {
                    this.acceptForm.isAccep = "受理";
                } else if ("tabNotAcceptance" === value) {
                    this.acceptForm.isAccep = "不受理";
                }
                this.assignmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应受理日期：" + this.$moment(this.complaintform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.complaintform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                this.departmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应受理日期：" + this.$moment(this.complaintform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.complaintform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                this.personnelForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应受理日期：" + this.$moment(this.complaintform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.complaintform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
            },
            copyOpinion() {
                //抄送人意见填写
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
                        caseguid: this.complaintform.caseguid,
                        node: this.nodeStr,
                        nextNodeId: "copyToOpinion",
                        opinion: this.opinionForm.opinion,
                        ismain: "0"
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code == 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                this.$router.push("/wddb/12315db");
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
            submitAssignment() {
                //分派下级
                let validation = false;
                this.$refs.assignmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
                        this.complaintform.providetype = this.Providetype
                    }
                    if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
                        this.complaintform.objclassify = this.Objclassify
                    }
                    if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
                        this.complaintform.complaintstype = this.Complaintstype
                    }
                    if (this.complaintform.businesstype != null && this.complaintform.businesstype != "") {
                        this.complaintform.businesstype = this.Businesstype
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: this.complaintform,
                        opinion: this.assignmentForm.opinion,
                        orgid: this.assignmentForm.unit,
                        isnewassign:"1"
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                }, 500);
                                // this.$router.push("/wddb/12315db");
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
            submitbackForm() {
                //退回
                let validation = false;
                this.$refs.backForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
                        this.complaintform.providetype = this.Providetype
                    }
                    if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
                        this.complaintform.objclassify = this.Objclassify
                    }
                    if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
                        this.complaintform.complaintstype = this.Complaintstype
                    }
                    if (this.complaintform.businesstype != null && this.complaintform.businesstype != "") {
                        this.complaintform.businesstype = this.Businesstype
                    }
                    let data = this.complaintform;
                    let params;
                    if (this.user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0') {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "backForward",
                            data: data,
                            opinion: this.backForm.opinion,
                            backreason: this.backForm.reason,
                            isEnd: '1',
                        };
                    } else {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "backForward",
                            data: data,
                            opinion: this.backForm.opinion,
                            backreason: this.backForm.reason,
                            deptid: this.backForm.leader,
                        };
                    }
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                }, 500);
                                // this.$router.push("/wddb/12315db");
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
            reasonChange() {
                if ('缺失要素' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。"
                } else if ('表述不清' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。"
                } else if ('类型有误' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请修改表单类型为：xxx。"
                } else if ('地址不详' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。"
                } else if ('已有知识' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。"
                } else if ('重复受理' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，此工单与另一工单（表单号：xxx）属重复受理。"
                } else if ('市民撤诉' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。"
                } else if ('不属本职' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。"
                } else {
                    this.backForm.opinion = "其它原因"
                }
            },
            submitDepartment() {
                //分派部门
                let validation = false;
                this.$refs.departmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    //深度拷贝，防止更改economyform原先的表单值
                    let data = JSON.parse(JSON.stringify(this.complaintform));
                    //转换数组
                    for (let key in data) {
                        if (data[key] instanceof Array) {
                            if (data[key].length > 0) {
                                data[key] = data[key].pop();
                            } else {
                                data[key] = null;
                            }
                        }
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerDept",
                        data: data,
                        opinion: this.departmentForm.opinion,
                        deptid: this.departmentForm.leading,
                        ortherDept: this.departmentForm.ccs,
                        isnewassign:"1"
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                window.opener.location.reload(); //刷新父窗口
                                setInterval(function() {
                                    window.close();
                                }, 500);
                                // this.$router.push("/wddb/12315db");
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
            findDepartmentList() {
                //获取部门列表
                findOrgDeptByOrgId({ orgId: this.user.orgID }).then(data => {
                    if (data.data) {
                        this.departmentList = data.data;
                    }
                });
            },
        },

        created() {
            this.loading = true;
            let para = {
                id: this.$route.query.id,
                caseguid: this.$route.query.caseguid
            };
            selectBusinessBaseInfo(para).then(res => {
                // this.askdepthandleList = res.data.askdepthandleList
                this.nodeStr = res.data.tasklist.nextnodeid;
                // if (this.nodeStr !== "begin") {
                //     //节点与当前对应不上
                //     this.$router.push("/wddb/12315db");
                //     return;
                // }
                this.tasklist = res.data.tasklist;
                this.handledemand = res.data.handledemand;
                this.customerinfo = res.data.customerinfo;
                this.opinionList = res.data.opinionList;
                this.complaintform = res.data.complaintform;
                if (this.complaintform != null) {
                    this.formType = "complaintmodule";
                    this.formTypeStr = this.complaintform.formtype;

                    if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
                        this.Providetype = this.complaintform.providetype
                        this.complaintform.providetype = this.complaintform.providetype.split("-")//将字符串格式转换成数组
                        this.complaintform.providetype = this.complaintform.providetype[this.complaintform.providetype.length - 1].toString()
                    }
                    if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
                        this.Objclassify = this.complaintform.objclassify
                        this.complaintform.objclassify = this.complaintform.objclassify.split("-")//将字符串格式转换成数组
                        this.complaintform.objclassify = this.complaintform.objclassify[this.complaintform.objclassify.length - 1].toString()
                    }
                    if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
                        this.Complaintstype = this.complaintform.complaintstype
                        this.complaintform.complaintstype = this.complaintform.complaintstype.split("-")//将字符串格式转换成数组
                        this.complaintform.complaintstype = this.complaintform.complaintstype[this.complaintform.complaintstype.length - 1].toString()
                    }
                    if (this.complaintform.businesstype != null && this.complaintform.businesstype != "") {
                        this.Businesstype = this.complaintform.businesstype
                        this.complaintform.businesstype = this.complaintform.businesstype.split("-")//将字符串格式转换成数组
                        this.complaintform.businesstype = this.complaintform.businesstype[this.complaintform.businesstype.length - 1].toString()
                    }
                    if (this.complaintform.industryname != null && this.complaintform.industryname != "") {
                        this.Industryname = this.complaintform.industryname
                        this.complaintform.industryname = this.complaintform.industryname.split("-")//将字符串格式转换成数组
                        this.complaintform.industryname = this.complaintform.industryname[this.complaintform.industryname.length - 1].toString()
                    }

                }
                this.loading = false;
            });
            this.findUnit();
            this.unitChange();
            this.findDepartmentList();
            if ("0" == this.$route.query.ismain) {
                this.ismain = false;
                this.ismains = true;
            }
        }
    };
</script>
<style lang="scss" scope>
    .el-form-item__content {
        text-align: left;
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

        .el-tabs__header {
            border: none;
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

    .bt1 {
        text-align: right;
        padding: 15px 0 5px 0;
        /* border-bottom: 1px solid #e8e8e8; */
    }

</style>
<style lang="scss">
    .item_col {
        /* border: none!important; */
        /* padding: 10px 0; */
    }
</style>
