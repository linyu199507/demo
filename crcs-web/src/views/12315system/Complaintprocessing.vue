<template>
    <section class="work_review" v-loading="loading" element-loading-text="拼命加载中" id="whole">
        <!--<el-card class="box-card">-->
        <!--<div class="text item">-->
        <el-tabs
                v-model="activeName"
                type="border-card"
                @tab-click="handleClick"
                style="width: 100%;margin: 15px auto;"
        >
            <el-tab-pane label="基本信息" name="first">
                <div style="text-align: right; margin-bottom:20px" :class="{'fixed':scrollTop > height}" >
                    <el-button type="primary" @click="save" :loading="submitLoading">保存</el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')">受理</el-button>
                    <el-button
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                            v-if="user.aicPermissions.subordinate&&unitList.length>0"
                    >分派下级</el-button>
                    <el-button
                            v-if="user.aicPermissions.dealdepartment&&deptList.length>0"
                            type="primary" @click="triggerTab('tabDepartment')">分派部门
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabNotAcceptance')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                        不受理</el-button>
                    <el-button type="primary" @click="toOperating('back')">退回</el-button>
                </div>

                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="orderInfo">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    系统信息
                                </el-col>
                                 <el-col :span="10" class="right">工单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>

                        <el-row>
                            <el-form :model="complaintform">
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label" >登记编号：</el-col>
                                    <el-col :span="16" class="txtleft">{{complaintform.registrationnumber}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">记录类型：</el-col>
                                    <el-col :span="16">{{complaintform.formtype}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">登记日期：</el-col>
                                    <el-col :span="16">
                                        {{$moment(this.complaintform.recorddate).format("YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">接收方式：</el-col>
                                    <el-col :span="16">{{complaintform.receivemode}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">来电号码：</el-col>
                                    <el-col :span="16">{{complaintform.rqstnumber}}</el-col>
                                </el-col>
                            </el-form>
                        </el-row>

                    </el-collapse-item>
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>2.</em>
                                    投诉类
                                    <!--{{formTypeStr}}-->
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：{{tasklist.serialnum}}
                                </el-col>
                            </el-row>
                        </div>
                        <!-- 12315投诉 -->
                        <complaintmoduleForm @checkdata="save" ref="complaintOne" :complaintform="complaintform" v-if="complaintform" />
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
                        <tracking :data="opinionList" />
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
                            <el-button @click="cancelTab">取消</el-button>
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

                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">-->
                            <!--<el-col :xs="24" :sm="24" :md="24" :lg="10" style>-->
                                <!--<el-form-item-->
                                        <!--label="办理期限："-->
                                        <!--prop="deadlineForProcessing"-->
                                        <!--class="labelFontStyle"-->
                                        <!--label-width="150px"-->
                                <!--&gt;-->
                                    <!--<el-date-picker-->
                                            <!--v-model="dealBusinessForm."-->
                                            <!--type="date"-->
                                            <!--placeholder="选择日期"-->
                                            <!--style="width:224px"-->
                                            <!--:clearable="false"-->
                                    <!--&gt;</el-date-picker>-->
                                <!--</el-form-item>-->
                            <!--</el-col>-->

                            <!--<el-col :xs="24" :sm="24" :md="24" :lg="14">-->
                                <!--<span class="fontStyle" v-if='formType==="举报"'>市场监管部门适用一般程序处理的案件应当自立案之日起九十日内作出处理决定</span>-->
                                <!--<span class="fontStyle" v-if='formType==="投诉"'>有管辖权的市场监管部门应当在受理消费者投诉之日起四十五日内终结调解</span>-->
                            <!--</el-col>-->
                        <!--</el-col>-->

                        <el-col>
                            <el-form-item label="受理方式" prop="mode">
                                <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px;">
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
                            <el-button @click="cancelTab">取消</el-button>
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="不受理原因" prop="reason">
                                <el-select v-model="acceptForm.reason" placeholder="请选择不受理原因" style="width:100%" @change="reasonChange3()">
                                    <el-option label="投诉事项不属于市场监督管理部门职责" value="1"></el-option>
                                    <el-option label="本行政机关不具有处理权限" value="2"></el-option>
                                    <el-option label="法院、仲裁机构、市场监督管理部门或者其它行政机关、消费者协会或者依法成立的其他调解组已经受理或者处理过同一消费者权益争议" value="3"></el-option>
                                    <el-option label="不是为生活消费需要购买、使用商品或者接受服务，或者不能证明与被投诉人之间存在消费者权益争议" value="4"></el-option>
                                    <el-option label="除法律另有规定外，投诉人知道或者应当知道自己的权益受到被投诉人侵害之日起超过三年" value="5"></el-option>
                                    <el-option label="未提供《市场监督管理投诉举报处理暂行办法》第九条第一款和第十条规定的材料" value="6"></el-option>
                                    <el-option label="法律、法规、规章规定不予受理的其他情形" value="7"></el-option>
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
                            <el-button type="primary" @click="submitNotAcceptance" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabAction==='tabAssignment'" style="min-height : 600px">
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
                                <el-select v-model="assignmentForm.unit" filterable placeholder="请选择成员单位" style="width:100%">
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
                                        filterable
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="短信"  prop="message" v-if="user.primaryKey=='991804e6-2595-474d-b345-e93aab3729c2'">
                                <Cascader
                                        :options="messageoptions"
                                        v-model="checkList"

                                >
                                </Cascader>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAssignment" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派部门 -->
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabAction==='tabDepartment'" style="min-height : 600px">
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
                                        @change="orgPermissions('2')"
                                        filterable
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
                            <el-form-item label="抄送部门">
                                <el-select
                                        v-model="departmentForm.ccs"
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        multiple
                                        @change="orgPermissions('3')"
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="短信"  prop="BMmessage" v-if="user.primaryKey=='991804e6-2595-474d-b345-e93aab3729c2'">
                                <Cascader
                                        :options="BMmessageoptions"
                                        v-model="BMcheckList"

                                >
                                </Cascader>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDepartment" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>
<script>
    import {
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,
        selectBusinessBaseInfo,
        saveBmfwBaseInfo,
        sendMessage,
        getSMSPerson
    } from "../../api/api";
    import { nextWork } from "../../api/convenient12315";
    import ComplaintmoduleForm from "./components/ComplaintmoduleForm";
    import ComplaintmoduleInfo from "./components/ComplaintmoduleInfo";
    import Tracking from "../../components/Tracking";
    import {myMixin} from "../../assets/mixin/mixin";
    import Cascader from "../../components/cascader";
    export default {
        mixins:[myMixin],
        name: "WorkReview",
        components: {
            ComplaintmoduleInfo,
            ComplaintmoduleForm,
            Tracking,
            Cascader
        },
        data() {
            return {
                user: JSON.parse(localStorage.getItem("user")),
                nodeStr:"",
                formType: "", //表单类型：economyform:经济违法，complaintmodule:投诉。
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
                    ortherDept: []
                },
                messageoptions:[],
                checkList:[],
                BMmessageoptions:[],
                BMcheckList:[],

                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"}
                    ],
                    // ortherDept: [{required: true, message: "请选择其他部门"}],
                },
                acceptFormRules: {
                    isAccep: [{ required: true }],
                    reason: [
                        { required: true, message: "请选择不受理原因", trigger: "change" }
                    ],
                    mode: [
                        { required: true, message: "请选择受理方式", trigger: "change" }
                    ],
                    opinion: [
                        { required: true, message: "请输入内容", trigger: "blur" },
                        { max: 1000, message: "不能超过1000字符", trigger: "blur" }
                    ],
                    details: [{required: true, message: "请输入内容", trigger: "blur"},
                        {max: 500, message: "不能超过500字符", trigger: "blur"}
                    ]
                },
                departmentForm: {
                    ccs: []
                },
                departmentFormRules: {
                    leading: [{required: true, message: "请选择主导部门"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
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
                    updatedate: "",
                    blqx:"",
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
                    appealsource: "互联网络",
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
                    objclassifycodeid: null,
                    industrynamecodeid: null,
                    industryname: "",
                    feedbacktime:"",
                    todealwithtime:"",
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
                    illegalrulescodeid: null,
                    region: "",
                    objectcategory: "",
                    objectcategorycodeid: null,
                    problemtypecodeid: null,
                    tortclassifycodeid: null
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
                    "processTracking",
                    "processing"
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
            handleClick(visible) {
                if (visible.name !== "operating") {
                    this.resetOperating();
                }
            },
            tabClick(visible) {
                if (visible.name !== "operating") {
                    this.resetOperating();
                }
            },
            resetOperating() {
                //取消
                this.operating = "";
                this.tabAction = "first";
            },
            findUnit() {
                //获取下级单位列表
                findOrgUnitByRegionId({ regionId: this.user.regionID }).then(data => {
                    if (data.data) {
                        this.unitList = data.data.data;
                    }
                });
            },
            unitChange() {
                //获取部门列表
                findOrgDeptByOrgId({ orgId: this.user.orgID }).then(res => {
                    this.deptList = res.data.data;
                });
            },
            orgPermission() {
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
            },
            cancelTab() {
                this.tabAction = "";
                this.activeName = "first";
            },
            toOperating(value) {
                this.operating = value;
                this.activeName = "operating";
                this.delayPersonnel();
            },
            submitAcceptance() {
                //受理
                 this.$confirm("请确认无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        this.submitLoading = true;
                        //深度拷贝，防止更改complaintform原先的表单值
                        let data=JSON.parse(JSON.stringify(this.complaintform))
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
                            nextNodeId: "accept",
                            data: data,
                            opinion: this.acceptForm.details,
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
                                        duration:'1000'
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

                // let validation = false;
                // this.$refs.acceptForm.validate(valid => (validation = valid));
                // if (!validation) return;

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
                    //深度拷贝，防止更改complaintform原先的表单值
                    let data=JSON.parse(JSON.stringify(this.complaintform))
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
                    if (this.user.regionID == "440681") {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data,
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
                            data,
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
                                    duration:'1000'
                                });
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                },500);
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
                //退回提交
                this.$refs.backForm.validate(valid => {
                    if (valid) {
                        this.$confirm("请确认无误提交?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            //深度拷贝，防止更改economyform原先的表单值
                            let data=JSON.parse(JSON.stringify(this.economyform))
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
                            let params;
                            // if (this.user.regionID == "440681") {
                            if (this.user.aicPermissions.ywclDispatch.sysMenuFiled == "ywclDispatch"){
                                params = {
                                    user: this.user,
                                    node: this.nodeStr,
                                    formType: this.formType,
                                    data: data,
                                    nextNodeId: "backForward",
                                    opinion: this.backForm.opinion,
                                    backreason: this.backForm.reason,
                                    isEnd: '1',
                                };
                            } else {
                                params = {
                                    user: this.user,
                                    node: this.nodeStr,
                                    formType: this.formType,
                                    data,
                                    nextNodeId: "backForward",
                                    opinion: this.backForm.opinion,
                                    backreason: this.backForm.reason,
                                    deptid: this.backForm.leader
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
                                        },500);
                                        // this.$router.push("/wddb/12315db");
                                    }
                                })
                                .catch(data => {
                                    this.$alert("操作失败！", "提示", {
                                        confirmButtonText: "确定",
                                        type: "error"
                                    });
                                });
                        });
                    }
                });
            },
            triggerTab(value) {

                if(this.$refs.complaintOne.checkData()){
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

                    // this.assignmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                    // this.departmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                    // this.personnelForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                    this.unitChange();
                }
            },
            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
                });
            },
            save() {

                if(this.$refs.complaintOne.checkData()){
                    this.$confirm("请确认无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        this.submitLoading = true;
                        let handledemand = this.handledemand;
                        //深度拷贝，防止更改complaintform原先的表单值
                        let data=JSON.parse(JSON.stringify(this.complaintform))
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
                        for (let key in handledemand) {
                            if (handledemand[key] instanceof Date) {
                                handledemand[key] = handledemand[key].getTime();
                            }
                        }
                        let params = {
                            handledemand,
                            customerinfo: this.customerinfo,
                            complaintform: data
                        };
                        saveBmfwBaseInfo(params).then(data => {
                            this.submitLoading = false;
                            this.$message({
                                message: '保存成功！',
                                type: 'success',
                                duration:'1000'
                            });
                        });
                    });
                }

            },
            //校验单位或部门是否存在对应权限的人员
            orgPermissions(value) {
                if ('2' == value) {
                    let para = {
                        deptid: this.departmentForm.leading,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.leading != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert("该部门没有处理该业务权限的人员，请先添加权限!", "提示", {
                                    confirmButtonText: "确定"
                                });
                                this.departmentForm.leading = ""
                            }
                        });
                    }
                }else {
                    let unit = this.departmentForm.ccs[this.departmentForm.ccs.length - 1]
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.ccs.length > 0 && unit != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert("该部门没有查看该业务权限的人员，请先添加权限!", "提示", {
                                    confirmButtonText: "确定"
                                });
                                this.departmentForm.ccs.splice(this.departmentForm.ccs.indexOf(this.departmentForm.ccs.length[this.departmentForm.ccs.length - 1]))
                            }
                        });
                    }
                }
            },
            //分派下级
            submitAssignment() {
                let validation = false;
                this.$refs.assignmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    //深度拷贝，防止更改complaintform原先的表单值
                    let data=JSON.parse(JSON.stringify(this.complaintform))
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
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        orgid: this.assignmentForm.unit,
                        ortherDept: this.assignmentForm.ortherDept,
                        isnewassign:"1"
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                //调用发短信接口
                                let paras={
                                    phonelist:this.checkList,
                                    tserialnum:this.tasklist.serialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                console.log(paras)
                                sendMessage(paras).then(res=>{
                                    if(res.code===0){
                                        this.$message({
                                            message: '提交成功！',
                                            type: 'success',
                                            duration:'1000'
                                        });
                                        if(window.opener){
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close()
                                        },500);
                                    }else{
                                        this.$alert("工单分派成功但短信发送超时！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "error"
                                        });
                                    }
                                })
                            }
                            // else {
                            //     this.errorAlert();
                            // }
                            this.submitLoading = false;
                        })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
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
                    let data=JSON.parse(JSON.stringify(this.complaintform))
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
                                //调用发短信接口
                                let paras={
                                    phonelist:this.BMcheckList,
                                    tserialnum:this.tasklist.serialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                console.log(paras)
                                sendMessage(paras).then(res=>{
                                    if(res.code===0){
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
                                        },500);
                                    }else{
                                        this.$alert("工单分派成功但短信发送超时！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "error"
                                        });
                                    }
                                })


                                // this.$router.push("/wddb/12315db");
                            }
                            // else {
                            //     this.errorAlert();
                            // }
                            this.submitLoading = false;
                        })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },
            //抄送部门不能出现与选择的主导部门一样
            filterDepartmentList() {
                return this.deptList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            reasonChange(){
                if('缺失要素' == this.backForm.reason){
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
            reasonChange3(){
                if ('1' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "投诉事项不属于市场监督管理部门职责"
                } else if ('2' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "本行政机关不具有处理权限"
                }else if ('3' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "法院、仲裁机构、市场监督管理部门或者其它行政机关、消费者协会或者依法成立的其他调解组已经受理或者处理过同一消费者权益争议"
                }else if ('4' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "不是为生活消费需要购买、使用商品或者接受服务，或者不能证明与被投诉人之间存在消费者权益争议"
                }else if ('5' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "除法律另有规定外，投诉人知道或者应当知道自己的权益受到被投诉人侵害之日起超过三年"
                }else if ('6' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "未提供《市场监督管理投诉举报处理暂行办法》第九条第一款和第十条规定的材料"
                }else if ('7' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "法律、法规、规章规定不予受理的其他情形"
                }
            },
            //获取短信列表人
            SearchMessagePerson(){
                console.log("000")
                let params={}
                getSMSPerson(params).then(res=>{
                    console.log("123123")
                    console.log(res)
                    this.messageoptions=res
                    this.BMmessageoptions=res
                })
            }
        },

        created() {
            this.loading = true;
            let para = {
                id: this.$route.query.id,
                caseguid: this.$route.query.caseguid,
                systemsource: "03"
            };
            selectBusinessBaseInfo(para).then(res => {
                // this.askdepthandleList = res.data.askdepthandleList
                this.nodeStr = res.data.tasklist.nextnodeid;
                // if (this.nodeStr !== "begin") {
                //   //节点与当前对应不上
                //   this.$router.push("/wddb/12315db");
                //   return;
                // }
                this.tasklist = res.data.tasklist;
                this.economyform = res.data.economyform;
                this.handledemand = res.data.handledemand;
                this.customerinfo = res.data.customerinfo;
                this.opinionList = res.data.opinionList;
                this.complaintform = res.data.complaintform;
                this.complaintform.appealsource = '互联网络';
                this.complaintform.usertype='个人';
                this.complaintform.providetypecodeid = null;
                this.complaintform.businesstypecodeid = null;
                this.complaintform.objclassifycodeid = null;
                this.complaintform.complaintstype = null;
                this.complaintform.industryname = null;
                if (this.complaintform != null) {
                    this.formType = "complaintmodule";
                    this.formTypeStr = this.complaintform.formtype;
                    this.complaintform.rqsttitle = '总局单:'+this.complaintform.subjectname+this.complaintform.registrationnumber
                } else if (this.economyform != null) {
                    this.formType = "comeconomicmodule";
                    this.formTypeStr = this.economyform.formtype;
                }
                this.loading = false;
            });
            this.findUnit();
            this.unitChange();
            let user = localStorage.getItem("user");
            let users = JSON.parse(user);
            this.SearchMessagePerson()
        }
    };
</script>
<style lang="scss" scope>
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
        text-align: right;
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
