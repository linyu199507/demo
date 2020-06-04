<!--受理的下一步操作页面->处理反馈、分派、申请延期、退回-->
<template>
    <section class="acceptance-of-next" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div
                        v-if="$route.query.ismain==='1'"
                        style="text-align: right;"
                        :class="{'fixed':scrollTop > height}"
                >
                    <el-button
                            type="primary"
                            size="small"
                            icon="document"
                            @click="exportToWord"
                            :loading="exportLoading"
                            style="margin: 2px; border-radius: 13px"
                    >
                        <template v-if="exportLoading">正在导出...</template>
                        <template v-else>
                            <i class="fa fa-download"></i>
                            Word导出
                        </template>
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')">立案</el-button>
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
                    >不立案</el-button>
                    <el-button type="primary" @click="triggerTab('tabBack')" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">退回</el-button>
                </div>
                <div
                        style="text-align: right;height: 50px"
                        v-if="ismains"
                        :class="{'fixed':scrollTop > height}"
                >
                    <el-button type="primary" @click="triggerTab('tabOpinion')">意见填写</el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">举报表单</el-col>
                                <!--<el-col :span="10" class="right">
                                    工单号：
                                    {{tasklist.tserialnum}}
                                </el-col>-->
                            </el-row>
                        </div>

                        <!--经济违法信息内容 -->
                        <economyInfo
                                v-if="economyform"
                                :economyform="economyform"
                                :casedata="caseprocesform"
                                :tasklist="tasklist"
                        />
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>

            <el-tab-pane label="流程跟踪" name="processing">
                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="economyProcess">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="6" style="color:rgb(64, 169, 255)">
                                    <em>1.</em>
                                    流程跟踪
                                </el-col>
                                <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                ref="caseprocesformA"
                                :rules="caseprocesformRules"
                        >
                            <el-row style="padding:20px 0">
                                <Tracking :data="opinionList"/>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabActionName==='tabAssignment'">
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
                                <el-select
                                        v-model="assignmentForm.unit"
                                        placeholder="请选择成员单位"
                                        style="width:100%"
                                        @change="orgPermission('1')"
                                >
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
                            <el-form-item label="抄送部门">
                                <el-select
                                        v-model="assignmentForm.ortherDept"
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        multiple
                                        @change="orgPermission('5')"
                                        ref="dCcsSelect"
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
                                        v-model="assignmentForm.opinion"
                                ></el-input>
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
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabActionName==='tabDepartment'">
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
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 意见填写 -->
            <el-tab-pane label="意见填写" name="tabOpinion" v-if="tabActionName==='tabOpinion'">
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
            <!-- 申请延期 -->
            <el-tab-pane label="申请延期" name="tabExtension" v-if="tabActionName==='tabExtension'">
                <el-form
                        :model="delayForm"
                        label-width="200px"
                        ref="delayForm"
                        label-suffix="："
                        :rules="delayFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="延时时间单位" prop="unit">
                                <el-select v-model="delayForm.unit" placeholder="请选择延期时间单位" prop="department">
                                    <el-option label="工作时" value="工作时"></el-option>
                                    <el-option label="工作日" value="工作日"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="延期时限" prop="limit">
                                <el-input placeholder="请输入延期时限" v-model.number="delayForm.limit"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="延期原因" prop="reason" style="width: 80%">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入延期原因（1000字内）"
                                        v-model="delayForm.reason"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="延期审批处理人" prop="postpone" class="form_bt">
                                <el-select v-model="delayForm.postpone" placeholder="请选择延期审批处理人">
                                    <el-option
                                            v-for="item in postponeLists"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDelay" :loading="submitLoading">申请</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 退回 -->
            <el-tab-pane label="退回" name="tabBack" v-if="tabActionName==='tabBack'">
                <el-form
                        :model="backForm"
                        ref="backForm"
                        label-width="200px"
                        label-suffix="："
                        :rules="backFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="退回原因" prop="reason">
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="退回描述" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="backForm.opinion"
                                        placeholder="请输入退回描述（1000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="举报相关附件">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileListsCom"
                                        ref="upload"
                                        :data="this.requestMapEco"
                                        :on-preview="previewFile"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                >
                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <el-button
                                            size="small"
                                            type="success"
                                            @click="submitUploadEco('uploadEco')"
                                    >确认上传
                                    </el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitBack" :loading="submitLoading">退回</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 受理 -->
            <el-tab-pane label="立案" name="tabAcceptance" v-if="tabActionName==='tabAcceptance'">
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
                            <el-form-item label="是否立案">
                                <div style="line-height: 35px">立案</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="立案方式" prop="mode">
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
            <el-tab-pane label="不立案" name="tabNotAcceptance" v-if="tabActionName==='tabNotAcceptance'">
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
                            <el-form-item label="是否立案">
                                <div style="line-height: 35px">不立案</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="不立案原因" prop="reason">
                                <el-select v-model="acceptForm.reason" placeholder="请选择不立案原因">
                                    <el-option label="交付单位不正确" value="交付单位不正确"></el-option>
                                    <el-option label="可通过查阅知识库解决" value="可通过查阅知识库解决"></el-option>
                                    <el-option label="其他" value="其他"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="不立案内容" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="acceptForm.opinion"
                                        placeholder="请输入不立案内容（1000字内）"
                                        :rows="5"
                                ></el-input>
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
        </el-tabs>
    </section>
</template>

<script>
    import {
        searchBaseInfo,
        nextWork,
        findUsersByPermission,
        findUsersByDeptIdAndPermission,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByOrgAndPermission,
        selectBaseCodeTree,
        findUsersByDeptIdAndJurisdiction
    } from "../../api/convenient12345";
    import {getMaterialLists, uploadFile} from "../../api/api";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "./components/EconomyInfo";
    import {byIdGetSuperiors, getArrayByStringId} from "../../utils/form-util";
    import moment from "moment";
    import {myMixin} from "../../assets/mixin/mixin";

    export default {
        mixins: [myMixin],
        components: {
            Tracking,
            EconomyInfo
        },
        data() {
            return {
                Providetype: "",
                Industryname: "",
                Objectcategory: "",
                Reportprovidelb: "",
                subjecttype1: [], //涉及主体类别和类型下拉框内容
                noobligation1: [], //未履行义务下拉框内容
                mediationmethods1: [], //调解方式下拉框内容
                infringingmarks1: [], //欺诈标志下拉框内容
                infringementtype1: [], //侵权类型下拉框内容
                fileListsAccept: [],
                fileListsEco: [],
                fileListsCom: [],
                materialList: [],
                exportLoading: false,
                user: JSON.parse(localStorage.getItem("user")),
                nodeStr: undefined,
                formType: "",
                formTypeStr: "",
                tabActionName: "tabMainContent",
                tabAction: "basicInfo",
                ismain: true,
                ismains: false,
                opinionForm: {},
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                },
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking",
                    "economyProcess"
                ],
                fileUploadText: false,
                fileList: [],
                fileUploadText: "文件上传",
                loading: false,
                fileUploadVisible: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: "",
                consultationform: undefined,
                handledemand: {},
                opinionList: [],
                registercase: false,
                caseprocesform: {
                    id: "",
                    caseguid: "",
                    nodeid: "",
                    subjectname: "",
                    subjecttype: "",
                    isverified: "",
                    verifiedopinion: "",
                    isregister: "",
                    ispursuit: "",
                    handleopinion: "",
                    updatedate: "",
                    businessaddress: "",
                    semaknama: "",
                    semakbusinessaddress: "",
                    mediationnumber: "",
                    infringementtype: "",
                    infringingmarks: "",
                    mediationmethods: "",
                    noobligation: "",
                    resultsofmediation: "",
                    disputemoney: "",
                    mentalmoney: "",
                    doublemoney: "",
                    financialloss: "",
                    transferreddate: "",
                    mediationis: "",
                    attachfilename: "",
                    cksubjectname: "",
                    subjecttypecodeid: "",
                    infringementtypecodeid: "",
                    infringingmarkscodeid: "",
                    noobligationcodeid: "",
                    mediationmethodscodeid: "",
                    applealtocase: "",
                    caseno: "",
                    casedate: "",
                    casereason: ""
                },
                caseprocesformRules: {
                    ispursuit: [{required: true, message: "请选择是否销案"}],
                    subjectname: [{required: true, message: "请输入涉及主体"}],
                    subjecttype: [
                        {required: true, message: "请选择请选择涉及主体类别和类型"}
                    ],
                    isverified: [{required: true, message: "请输入属实情况"}],
                    verifiedopinion: [{required: true, message: "请输入调查情况"}],
                    updatedate: [{required: true, message: "请选择时间"}],
                    businessaddress: [{required: true, message: "请输入经营地址"}],
                    semaknama: [{required: true, message: "请输入核查经营名称"}],
                    semakbusinessaddress: [
                        {required: true, message: "请输入核查经营地址"}
                    ],
                    infringementtype: [{required: true, message: "请选择侵权类型"}],
                    // infringingmarks: [{required: true, message: "请选择欺诈标志"}],
                    mediationmethods: [{required: true, message: "请选择调解方式"}],
                    noobligation: [{required: true, message: "请选择未履行义务"}],
                    resultsofmediation: [{required: true, message: "请选择调解结果"}],
                    cksubjectname: [{required: true, message: "核查经营名称"}],
                    disputemoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    mentalmoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    doublemoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    financialloss: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    transferreddate: [
                        {required: true, type: "date", message: "请选择结办日期"}
                    ],
                    mediationis: [
                        {required: true, message: "请输入调查情况"},
                        {max: 1200, message: "不能超出1200字符", trigger: "blur"}
                    ],
                    handleopinion: [
                        {required: true, message: "请输入处理结果", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    cksubjectaddress: [{required: true, message: "请输入核查经营地址"}],
                    businessaddress: [{required: true, message: "请输入经营地址"}],
                    applealtocase: [
                        {
                            required: true,
                            message: "请选择是否诉转案",
                            trigger: "blur"
                        }
                    ],
                    caseno: [
                        {
                            required: true,
                            message: "请输入立案号",
                            trigger: "blur"
                        },
                        {max: 30, message: "不能超出30字符", trigger: "blur"}
                    ],
                    casedate: [
                        {
                            required: true,
                            type: "date",
                            message: "请输入立案时间",
                            trigger: "blur"
                        }
                    ],
                    casereason: [
                        {
                            required: true,
                            message: "请输入案由",
                            trigger: "blur"
                        },
                        {max: 500, message: "不能超出500字符", trigger: "blur"}
                    ]
                },
                customerinfo: {},
                tasklist: {},
                ordernumber: "",
                delayForm: {},
                delayFormRules: {
                    unit: [{required: true, message: "请选择延期时间单位"}],
                    limit: [
                        {required: true, message: "请输入延期时限"},
                        {type: "integer", message: "延期时限只能是整数"},
                        {
                            type: "integer",
                            max: 100,
                            min: 1,
                            message: "不能大于100与小于1",
                            trigger: "blur"
                        }
                    ],
                    reason: [
                        {required: true, message: "请输入延期原因", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                backForm: {
                    opinion: ""
                },
                backFormRules: {
                    reason: [
                        {required: true, message: "请选择退回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入退回描述", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                acceptForm: {
                    isAccep: "",
                    opinion: "",
                    department: "",
                    content: "",
                    reason: "",
                    details:"经审查，符合立案条件，决定立案。"
                },
                acceptFormRules: {
                    // isAccep: [{required: true}],
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
                feedbackForm: {},
                feedbackFormRules: {
                    result: [{required: true, message: "请输入处理结果", trigger: "blur"}]
                },
                caseform: {
                    leader: "",
                    leaders: ""
                },
                caseformRules: {
                    leader: [{required: true, message: "请选择领导"}],
                    leaders: [{required: true, message: "请选择区局处理人"}]
                },
                assignmentForm: {
                    unit: undefined,
                    ortherDept: []
                },
                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"}
                    ]
                    // ortherDept: [{required: true, message: "请选择抄送部门"}],
                },
                departmentForm: {
                    ccs: []
                },
                departmentFormRules: {
                    leading: [{required: true, message: "请选择主导部门"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ]
                    // ccs: [{required: true, message: "请选择抄送部门"}]
                },
                personnelForm: {
                    ccs: []
                },
                personnelFormRules: {
                    leading: [{required: true, message: "请选择主要人员"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ]
                },
                submitLoading: false,
                reviewDialogVisible: false,
                dialogRegionAudit: false,
                leaderList: [],
                unitList: [],
                departmentList: [],
                personnelList: [],
                postponeLists: [],
                registrationActiveNames: ["1", "2", "3", "4", "5"],
                leaderLists: [],
                requestMap: {
                    caseguid: ""
                },
                requestMapEco: {
                    caseguid: ""
                },
                requestMapCom: {
                    caseguid: ""
                }
            };
        },
        watch: {
            economyform: function ({caseguid}) {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid: caseguid
                };
                getMaterialLists(params).then(res => {
                    if (res.code === 0) {
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.fileListsEco.push(fileInfo);
                        }
                    }
                });
            },
            complaintform: function ({caseguid}) {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid: caseguid
                };
                getMaterialLists(params).then(res => {
                    if (res.code === 0) {
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.fileListsAccept.push(fileInfo);
                            this.fileListsCom.push(fileInfo);
                        }
                    }
                });
            }
        },
        methods: {
            //导出为Word格式
            exportToWord() {
                //query传参,另一页面进行获取方法
                const querystring = require("querystring");
                let str = querystring.stringify({
                    caseguid: this.$route.query.caseguid,
                    formType: this.formType,
                    tserialnum: this.tasklist.tserialnum,
                    systemsource: this.tasklist.systemsource
                });
                window.open("/crcs/businessSearch/wordexport?" + str);
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            // 点击下载材料
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url, "_blank")
            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            dateChanges(date) {
                this.caseprocesform.transferreddate = date;
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
                        caseguid: this.economyform.caseguid,
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
            triggerTab(value) {
                //经济表单处理反馈
                if (this.economyform != null && this.economyform != "") {
                    (this.caseprocesform.subjectname = this.economyform.subjectname),
                        // this.caseprocesform.subjecttype = this.economyform.subjecttype,
                        (this.caseprocesform.semakbusinessaddress = this.economyform.cksubjectaddress),
                        (this.caseprocesform.semaknama = this.economyform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.economyform.subjectaddress);
                    this.caseprocesform.subjecttypecodeid = this.economyform.providetypecodeid;
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = getArrayByStringId(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                }

                this.tabActionName = value;
                if ("tabExtension" == value) {
                    this.delayApproval();
                } else if ("tabBack" == value) {
                    this.delayPersonnel();
                }
                // this.caseprocesform.transferreddate = moment().format("YYYY-MM-DD").toDate()
                this.assignmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                this.departmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                this.personnelForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                    + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            filterPersonnelList() {
                return this.personnelList.filter(
                    item => item.primaryKey !== this.personnelForm.leading
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
            filterDepartmentList() {
                return this.departmentList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            findDepartmentList() {
                //获取部门列表
                findOrgDeptByOrgId({orgId: this.user.orgID}).then(data => {
                    if (data.data) {
                        this.departmentList = data.data;
                    }
                });
            },
            submitBack() {
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
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    let data = this.economyform;
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
                            isEnd: "1"
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
                            deptid: this.backForm.leader
                        };
                    }
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
            uploadSuccess(response, file, fileList) {
                if (file.response.code === 0) {
                    this.$message({
                        message: "上传成功",
                        type: "success"
                    });
                } else {
                    this.$message.error("上传失败" + file.response.msg);
                }
            },
            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 20 <= 1;
                let lastName = fileName.substring(pos, fileName.length);
                let arrays = [
                    ".docx",
                    ".doc",
                    ".txt",
                    ".xls",
                    ".jpg",
                    ".png",
                    ".pfg",
                    ".flv",
                    ".rmvb",
                    ".mvb",
                    ".mp4",
                    ".xlsx"
                ];
                if (arrays.indexOf(lastName) === -1 || isLt === false) {
                    this.$alert(
                        "不支持上传该格式的文件,请上传docx，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                        "提示",
                        {
                            confirmButtonText: "确定"
                        }
                    );
                    return false;
                }
            },
            submitUpload(value) {
                this.requestMap.caseguid = this.complaintform.caseguid;
                this.$refs[value].submit();
            },
            submitUploadEco(value) {
                // alert('经济');
                this.requestMapEco.caseguid = this.economyform.caseguid;
                this.$refs.upload.submit();
            },
            submitUploadCom() {
            },
            progress(event, file, fileList) {
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
                    //深度拷贝，防止更改economyform原先的表单值
                    let data = JSON.parse(JSON.stringify(this.economyform));
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
                    this.submitLoading = true;
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
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
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
                    //深度拷贝，防止更改economyform原先的表单值
                    let data = JSON.parse(JSON.stringify(this.economyform));
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
                    let datas = JSON.parse(JSON.stringify(this.caseprocesform));
                    //转换数组
                    for (let key in datas) {
                        if (datas[key] instanceof Array) {
                            if (datas[key].length > 0) {
                                datas[key] = datas[key].pop();
                            } else {
                                datas[key] = null;
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
                        caseprocesform: datas,
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
            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
                });
            },
            getBaseInfo() {
                this.loading = true;
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    this.$router.push("/wddb/12345db");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource: "02"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;
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
                                // this.requestMapEco.caseguid = data.economyform.caseguid
                                this.ordernumber = this.economyform.ordernumber;
                                if (
                                    this.economyform.providetype != null &&
                                    this.economyform.providetype != ""
                                ) {
                                    this.Providetype = this.economyform.providetype;
                                    this.economyform.providetype = this.economyform.providetype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.providetype = this.economyform.providetype[
                                    this.economyform.providetype.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.industryname != null &&
                                    this.economyform.industryname != ""
                                ) {
                                    this.Industryname = this.economyform.industryname;
                                    this.economyform.industryname = this.economyform.industryname.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.industryname = this.economyform.industryname[
                                    this.economyform.industryname.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.objectcategory != null &&
                                    this.economyform.objectcategory != ""
                                ) {
                                    this.Objectcategory = this.economyform.objectcategory;
                                    this.economyform.objectcategory = this.economyform.objectcategory.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.objectcategory = this.economyform.objectcategory[
                                    this.economyform.objectcategory.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.reportprovidelb != null &&
                                    this.economyform.reportprovidelb != ""
                                ) {
                                    this.Reportprovidelb = this.economyform.reportprovidelb;
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb[
                                    this.economyform.reportprovidelb.length - 1
                                        ].toString();
                                }
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.$router.push("/wddb/12315db");
                            }
                        }
                        if ("0" == this.$route.query.ismain) {
                            this.ismain = false;
                            this.ismains = true;
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
            },
            getLeaderList() {
                //请求领导的列表
                let params = {
                    deptid: this.user.unitID,
                    permission: "leadershipApproval"
                };
                findUsersByPermission(params).then(data => {
                    if (data.data) {
                        this.leaderList = data.data;
                    }
                });
            },
            findUnitList() {
                //获取下级单位列表
                findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
                    if (data.data) {
                        this.unitList = data.data;
                    }
                });
            },
            toUploadOpen(type) {
                this.fileUploadVisible = true;
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
                    let unit = this.departmentForm.ccs[this.departmentForm.ccs.length - 1];
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.ccs.length > 0 && unit != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该部门没有查看该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.departmentForm.ccs.splice(
                                    this.departmentForm.ccs.indexOf(
                                        this.departmentForm.ccs.length[
                                        this.departmentForm.ccs.length - 1
                                            ]
                                    )
                                );
                            }
                        });
                    }
                }
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
            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1]);
                this.caseprocesform.subjecttype = label;
                this.caseprocesform.subjecttypecodeid = array;
            },
            handleChangeInfringementtype1(array) {
                let label = this.getLabel(
                    this.infringementtype1,
                    array[array.length - 1]
                );
                this.caseprocesform.infringementtype = label;
                this.caseprocesform.infringementtypecodeid = array;
            },
            handleChangeInfringingmarks1(array) {
                let label = this.getLabel(this.infringingmarks1, array[array.length - 1]);
                this.caseprocesform.infringingmarks = label;
                this.caseprocesform.infringingmarkscodeid = array;
            },
            handleChangeMediationmethods1(array) {
                let label = this.getLabel(
                    this.mediationmethods1,
                    array[array.length - 1]
                );
                this.caseprocesform.mediationmethods = label;
                this.caseprocesform.mediationmethodscodeid = array;
            },
            handleChangeNoobligation1(array) {
                let label = this.getLabel(this.noobligation1, array[array.length - 1]);
                this.caseprocesform.noobligation = label;
                this.caseprocesform.noobligationcodeid = array;
            },
            handleChange(array) {
            },
            getLabel(children, codeid) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.label;
                    }
                    if (child.children) {
                        let label = this.getLabel(child.children, codeid);
                        if (label) {
                            return label;
                        }
                    }
                }
            },
            //获取对应的级联codeid和lable
            testTemp(children, codeid, label) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.label;
                    }
                    if (child.children) {
                        let label = this.testTemp(child.children, codeid, label);
                        if (label) {
                            return label;
                        }
                    }
                }
            },
            getLevels(items) {
                let list = [];
                for (const i in items) {
                    let data = {
                        value: items[i].codeid,
                        label: items[i].comment
                    };
                    if (items[i].basecodeforms.length > 0) {
                        data.children = this.getLevels(items[i].basecodeforms);
                    }
                    list.push(data);
                }
                return list;
            },
            //侵权类型下拉框级联数据
            getInfringementtype1() {
                let params = {
                    type: "tort12345"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.infringementtype1 = this.data; //侵权类型
                });
            },
            //欺诈标志下拉框级联数据
            getInfringingmarks1() {
                let params = {
                    type: "H"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.infringingmarks1 = this.data; //欺诈标志
                });
            },
            //调解方式下拉框级联数据
            getMediationmethods1() {
                let params = {
                    type: "J"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.mediationmethods1 = this.data; //调解方式
                });
            },
            //未履行义务下拉框级联数据
            getNoobligation1() {
                let params = {
                    type: "K"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.noobligation1 = this.data; //未履行义务
                });
            },
            //涉及主体类别和类型下拉框级联数据
            getSubjecttype1() {
                let params = {
                    type: "enterprise12345"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.subjecttype1 = this.data; //涉及主体类别和类型
                });
            },
            //查询延期审批用户列表
            delayApproval() {
                let para = {
                    permission: "delayApproval"
                };
                findUsersByPermission(para).then(res => {
                    this.postponeLists = res.data;
                });
            },
            //codeid数据转换
            dataConversion() {
                //经济表单处理反馈
                if (this.economyform != null && this.economyform != "") {
                    (this.caseprocesform.subjectname = this.economyform.subjectname),
                        // this.caseprocesform.subjecttype = this.economyform.subjecttype,
                        (this.caseprocesform.cksubjectaddress = this.economyform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.economyform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.economyform.subjectaddress);
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                }
            },
            caseChange() {
                if (this.caseprocesform.applealtocase == "1") {
                    this.registercase = true;
                } else {
                    this.registercase = false;
                    this.caseprocesform.caseno = "";
                    this.caseprocesform.casedate = "";
                    this.caseprocesform.casereason = "";
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
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "accept",
                        data: this.economyform,
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
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                this.$router.push({
                                    path: "/listDetail/12315jblacl",
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
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    let params;
                    if (this.user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0') {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data: this.economyform,
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
                            data: this.economyform,
                            opinion: this.acceptForm.opinion,
                            noAcceptreason: this.acceptForm.reason
                        };
                    }
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
                                    window.close();
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
            reasonChange() {
                if ("缺失要素" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。";
                } else if ("表述不清" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。";
                } else if ("类型有误" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请修改表单类型为：xxx。";
                } else if ("地址不详" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。";
                } else if ("已有知识" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。";
                } else if ("重复受理" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，此工单与另一工单（表单号：xxx）属重复受理。";
                } else if ("市民撤诉" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。";
                } else if ("不属本职" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。";
                } else {
                    this.backForm.opinion = "其它原因";
                }
            }
        },
        created() {
            this.getBaseInfo();
            this.getLeaderList();
            this.findUnitList();
            this.findDepartmentList();
            this.findPersonnelList();
            this.findPersonnelList();
            this.getInfringementtype1();
            this.getInfringingmarks1();
            this.getMediationmethods1();
            this.getNoobligation1();
            this.getSubjecttype1();
            this.findDepartmentList();
        }
    };
</script>
<style lang="scss" scoped>
    /* 公共样式 */
    @mixin p20 {
        padding: 20px 0 !important;
    }

    @mixin mb10 {
        margin-bottom: 10px;
    }

    .acceptance-of-next {
        .btn {
            text-align: center;
            margin-top: 15px;
        }

        .label {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .item_col {
            padding: 5px 0 0 0;
            /* border-bottom: 1px solid #e8e8e8; */
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }

        .el-form .el-row {
            margin: 0;
        }
    }

    @media (min-width: 1200px) {
        .el-col-lg-12 {
            @include mb10;
        }
    }

    .el-collapse-item__wrap {
        padding: 20px 0 !important;
    }

    .accessoriesList {
        ul {
            list-style: none;
            margin: 0;
            padding: 0;
            color: rgb(72, 87, 106);

            li {
                span {
                    font-size: 16px;

                    &:last-child:hover {
                        cursor: pointer;
                        text-decoration: underline;
                    }
                }
            }
        }
    }
</style>
<style lang="scss">
    .el-tabs__header {
        border-bottom: none;
        padding: 0;
        position: relative;
        margin: 0;
    }
</style>
