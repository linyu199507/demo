<!--立案处理-->
<template>
    <section class="filing" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div style="text-align: right;" :class="{'fixed':scrollTop > height}">
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
                    <el-button type="primary" @click="triggerTab('tabFiling')">立案核查</el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                    >分派下级
                    </el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button
                            v-if="(user.regionID =='440681' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0')"
                            type="primary"
                            @click="triggerTab('tabPersonnel')"
                    >分派人员
                    </el-button>
                </div>
                <br/>
                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>2.</em> 经济违法表单
                                </el-col>
                                <!--<el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>-->
                            </el-row>
                        </div>
                        <!-- 12315经济违法 -->
                        <my-economy-info :economyform="economyform" v-if="economyform" :tasklist="tasklist"/>
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
                                <my-tracking-info :data="opinionList"/>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 立案处理 -->
            <el-tab-pane label="立案核查" name="tabFiling" v-if="tabActionName==='tabFiling'">
                <el-collapse v-model="collapseFilingValues" class="accordion">
                    <!-- 涉及主体信息 -->
                    <el-collapse-item name="involving">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">涉及主体信息</el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformInvolving"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体" prop="subjectname">
                                        <el-input placeholder="请输入" v-model="caseprocesform.subjectname"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="主体类别和类型" prop="subjecttypecodeid">
                                        <el-cascader filterable
                                                     placeholder="请选择涉及主体类别和类型"
                                                     :options="subjecttype1"
                                                     v-model="caseprocesform.subjecttypecodeid"
                                                     @change="handleChangesubjecttype1"
                                                     :show-all-levels="false"
                                                     class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="经营地址" prop="businessaddress" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.businessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营地址" prop="semakbusinessaddress" class="bt">
                                        <el-input placeholder="请输入核查经营地址"
                                                  v-model="caseprocesform.semakbusinessaddress"
                                                  class="input-text">
                                            <el-select v-model="economyform.region" slot="prepend" placeholder="请选择"
                                                       style="width:100px;color: black;">
                                                <el-option label="禅城区" value="禅城区"></el-option>
                                                <el-option label="南海区" value="南海区"></el-option>
                                                <el-option label="顺德区" value="顺德区"></el-option>
                                                <el-option label="高明区" value="高明区"></el-option>
                                                <el-option label="三水区" value="三水区"></el-option>
                                            </el-select>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营名称" prop="semaknama" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.semaknama"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">-->
                                    <!--<el-form-item label="核查分类：" prop="classification" class="bt">-->
                                        <!--<el-select-->
                                                <!--placeholder="请选择核查分类"-->
                                                <!--v-model="caseprocesform.classification"-->
                                                <!--class="input-text"-->
                                        <!--&gt;-->
                                            <!--<el-option label="研制" value="研制"></el-option>-->
                                            <!--<el-option label="生产" value="生产"></el-option>-->
                                            <!--<el-option label="流通" value="流通"></el-option>-->
                                            <!--<el-option label="服务" value="服务"></el-option>-->
                                            <!--<el-option label="餐饮服务/使用" value="餐饮服务/使用"></el-option>-->
                                            <!--<el-option label="其他" value="其他"></el-option>-->
                                        <!--</el-select>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <!-- 案件信息 -->
                    <el-collapse-item name="case">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">案件信息</el-col>
                                <!-- <el-col :span="10" class="right">订单号：{{ordernumber}}</el-col> -->
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformCase"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="是否立案" prop="isregister">
                                        <el-select
                                                placeholder="请选择"
                                                v-model="caseprocesform.isregister"
                                                style="width:100%"
                                                @change="changeLimitdate(caseprocesform.isregister)"
                                        >
                                            <el-option label="是" value="是"></el-option>
                                            <el-option label="否" value="否"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <!-- 初查情况 -->
                    <el-collapse-item name="initial">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">初查情况</el-col>
                                <!-- <el-col :span="10" class="right">订单号：{{ordernumber}}</el-col> -->
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformInitial"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="处理期限" prop="limitdate">
                                        <el-input
                                                placeholder="请输入处理期限"
                                                v-model.number="caseprocesform.limitdate"
                                                class="input-text"
                                        >
                                            <template slot="append">工作日</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>-->
                                <el-col/>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="属实情况" prop="isverified">
                                        <el-select
                                                placeholder="请选择"
                                                v-model="caseprocesform.isverified"
                                                style="width:100%"
                                        >
                                            <!--<el-option label="属实" value="属实"></el-option>-->
                                            <!--<el-option label="不属实" value="不属实"></el-option>-->
                                            <!--<el-option label="待定" value="待定"></el-option>-->
                                            <el-option label="属实(立案)" value="属实"></el-option>
                                            <el-option label="属实(不立案)" value="不属实"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col>
                                    <el-form-item
                                            :label="caseprocesform.isverified==='不属实'?'不属实原因':'调查情况'"
                                            prop="verifiedopinion"
                                    >
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 4, maxRows: 6}"
                                                placeholder="请输入"
                                                v-model="caseprocesform.verifiedopinion"
                                        ></el-input>
                                    </el-form-item>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                                        <el-form-item label="举报相关附件">
                                            <el-upload
                                                    :auto-upload="false"
                                                    :file-list="fileListsEco"
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
                                                        @click="submitUploadEco('uploadEconmy')"
                                                >确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>

                </el-collapse>
                <div class="btn">
                    <el-button type="primary" @click="submitFiling" :loading="submitLoading">确定</el-button>
                    <el-button @click="cancelTab">取消</el-button>
                </div>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabActionName==='tabAssignment'" style="min-height : 600px">
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
                                        filterable
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
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabActionName==='tabDepartment'" style="min-height : 600px">
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
            <!-- 分派人员 -->
            <el-tab-pane label="分派人员" name="tabPersonnel" v-if="tabActionName==='tabPersonnel'">
                <el-form
                        :model="personnelForm"
                        label-width="200px"
                        ref="personnelForm"
                        label-suffix="："
                        :rules="personnelFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="主要人员" prop="leading">
                                <el-select
                                        v-model="personnelForm.leading"
                                        placeholder="请选择主要人员"
                                        style="width:100%"
                                        @change="filterPCcs"
                                >
                                    <el-option
                                            v-for="item in personnelList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送人员">
                                <el-select
                                        v-model="personnelForm.ccs"
                                        placeholder="请选择抄送人员"
                                        style="width:100%"
                                        multiple
                                        @visible-change="ifMainPersonnel"
                                        ref="pCcsSelect"
                                >
                                    <el-option
                                            v-for="item in filterPersonnelList()"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
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
                                        v-model="personnelForm.opinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitPersonnel" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import {myMixin} from '../../assets/mixin/mixin'
    import {searchBaseInfo, nextWork, getMaterialLists,findUsersByOrgAndPermission,findUsersByDeptIdAndJurisdiction,
        findOrgDeptByOrgId,findOrgUnitByRegionId,findUsersByDeptIdAndPermission} from "../../api/convenient12345";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "./components/EconomyInfo";
    import {
        byIdGetSuperiors,
        byIdGetLabel,
        createCascaderOptions,
        getArrayByStringId
    } from "../../utils/form-util";
    import {selectBaseCodeTree,sendMessage,getSMSPerson} from "../../api/api";
    import Cascader from "../../components/cascader"

    export default {
        components: {
            "my-economy-info": EconomyInfo,
            "my-tracking-info": Tracking,
            Cascader,
        },
        mixins: [myMixin],
        data() {
            return {
                exportLoading: false,
                fileList: [],
                fileListsEco: [],
                fileListsAccept: [],
                personnelList: [],
                requestMapEco: {
                    caseguid: ""
                },
                personnelForm: {
                    ccs: []
                },
                Providetype: "",
                Industryname: "",
                Objectcategory: "",
                Reportprovidelb: "",
                subjecttype1: [], //涉及主体类别和类型
                user: JSON.parse(localStorage.getItem("user")),
                formType: "",
                formTypeStr: "",
                nodeStr: undefined,
                tabActionName: "tabMainContent",
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking",
                    "processing",
                    "economyProcess"
                ],
                collapseFilingValues: ["involving", "initial", "case"],
                loading: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: {},
                consultationform: undefined,
                opinionList: [],
                tasklist: {},
                ordernumber: "",
                caseprocesform: {
                    limitdate:"",
                    isregister:"是",
                },
                caseprocesformRules: {
                    classification: [{required: true, message: "请选择核查分类"}],
                    isregister: [{required: true, message: "请选择是否立案"}],
                    verifiedopinion: [
                        {required: true, message: "请输入", trigger: "blur"}
                    ],
                    isverified: [
                        {required: true, message: "请输入属实情况", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入涉及主体", trigger: "blur"}
                    ],
                    semaknama: [
                        {required: true, message: "请输入核查经营名称", trigger: "blur"}
                    ],
                    // cksubjectaddress: [
                    //   { required: true, message: "请输入核查经营地址", trigger: "blur" }
                    // ],
                    semakbusinessaddress: [{required: true, message: "请输入核查经营地址"}],
                    businessaddress: [
                        {required: true, message: "请输入经营地址", trigger: "blur"}
                    ],
                    subjecttypecodeid: [
                        {
                            type: "array",
                            required: true,
                            message: "请选择主体类别和类型"
                        }
                    ],
                    limitdate: [
                        {type: "integer", message: "处理期限只能是整数", trigger: "blur"},
                        {
                            type: "integer",
                            max: 100,
                            min: 1,
                            message: "不能大于100与小于1",
                            trigger: "blur"
                        }
                    ]
                },
                submitLoading: false,
                subjectTypeList: [],
                assignmentForm: {
                    unit: undefined,
                    ortherDept: []
                },
                messageoptions:[],
                BMmessageoptions:[],
                checkList:[],
                BMcheckList:[],
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
                personnelFormRules: {
                    leading: [{required: true, message: "请选择主要人员"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ]
                },
                unitList: [],
                departmentList: [],
            };
        },
        methods: {
            changeLimitdate(value){
                if("否" == value){
                    this.caseprocesform.limitdate = 3
                }else{
                    this.caseprocesform.limitdate = ""
                }
            },

            getMaterialLists(caseguid) {
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
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.fileListsAccept.push(fileInfo);
                            this.fileListsEco.push(fileInfo);
                        }
                    }
                });
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);

                }
            },
            download(url){
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url);
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
            submitUploadEco(value) {
                this.requestMapEco.caseguid = this.economyform.caseguid;
                this.$refs.upload.submit();
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
            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1]);
                this.caseprocesform.subjecttype = label;
                this.caseprocesform.subjecttypecodeid = array;
            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            triggerTab(value) {
                this.tabActionName = value;
                this.caseprocesform.subjectname = this.economyform.subjectname;
                this.caseprocesform.businessaddress = this.economyform.subjectaddress;
                this.caseprocesform.cksubjectaddress = this.economyform.cksubjectaddress;
                this.caseprocesform.cksubjectname = this.economyform.cksubjectname;
                if (
                    this.caseprocesform.subjecttypecodeid != null &&
                    this.caseprocesform.subjecttypecodeid != ""
                ) {
                    this.caseprocesform.subjecttypecodeid = getArrayByStringId(
                        this.caseprocesform.subjecttypecodeid,
                        this.subjecttype1
                    );
                }

                if("tabAssignment" == value && "tabDepartment" == value){
                    this.assignmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                    this.departmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                    this.personnelForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                }

                // this.assignmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                // this.departmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
            },
            async listSubjectType() {
                let params = {
                    type: "F"
                };
                let res = await selectBaseCodeTree(params);
                this.subjectTypeList = createCascaderOptions(res.data.data);
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            submitFiling() {
                //提交立案
                let validation = false;
                this.$refs.caseprocesformInitial.validate(valid => (validation = valid));
                this.$refs.caseprocesformInvolving.validate(
                    valid => (validation = validation && valid)
                );
                if (this.$refs.caseprocesformCase) {
                    this.$refs.caseprocesformCase.validate(
                        valid => (validation = validation && valid)
                    );
                }
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    this.caseprocesform.caseguid = this.economyform.caseguid;
                    //深度拷贝，防止更改economyform原先的表单值
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
                    if (this.economyform.providetype != null && this.economyform.providetype != "") {
                        this.economyform.providetype = this.Providetype
                    }
                    if (this.economyform.industryname != null && this.economyform.industryname != "") {
                        this.economyform.industryname = this.Industryname
                    }
                    if (this.economyform.objectcategory != null && this.economyform.objectcategory != "") {
                        this.economyform.objectcategory = this.Objectcategory
                    }
                    if (this.economyform.reportprovidelb != null && this.economyform.reportprovidelb != "") {
                        this.economyform.reportprovidelb = this.Reportprovidelb
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId:
                            this.caseprocesform.isregister === "是"
                                ? "registerCase"
                                : "noRegisterCase",
                        data: this.economyform,
                        opinion: this.caseprocesform.verifiedopinion,
                        caseprocesform: datas
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                let params = {
                                    id: this.$route.query.id,
                                    caseguid: this.$route.query.caseguid,
                                    ismain: "1"
                                };
                                if (this.caseprocesform.isregister === "是") {
                                    this.$router.push({
                                        path: "/listDetail/12315jbgdfk",
                                        query: params
                                    });
                                } else {
                                    this.$router.push({
                                        path: "/wddb/12315db"
                                    });
                                }
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
                    this.$router.push("/wddb/12315db");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource: "03"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;
                        // if (this.nodeStr !== "accept" || data.tasklist.formtype !== "03") {
                        //     //节点与当前对应不上
                        //     this.$router.push("/wddb/12315db");
                        //     return;
                        // }
                        if (data.caseprocesform != null) {
                            this.caseprocesform = data.caseprocesform;
                        }
                        this.caseprocesform.subjectTypeValue = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjectTypeList
                        );
                        this.tasklist = data.tasklist;
                        console.log("jjjj")
                        console.log(this.tasklist)
                        this.opinionList = data.opinionList;
                        if (code === 0) {
                            if (data.economyform != null) {
                                //经济表单
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = "经济违法行为";
                                this.economyform = data.economyform;
                                this.caseprocesform.subjectname = data.economyform.subjectname;
                                this.ordernumber = this.economyform.ordernumber;
                                this.caseprocesform.subjecttypecodeid = this.economyform.providetypecodeid;
                                if (
                                    this.economyform.providetype != null &&
                                    this.economyform.providetype != ""
                                ) {
                                    this.Providetype = this.economyform.providetype
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
                                    this.Industryname = this.economyform.industryname
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
                                    this.Objectcategory = this.economyform.objectcategory
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
                                    this.Reportprovidelb = this.economyform.reportprovidelb
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
                }
            },
            findUnitList() {
                //获取下级单位列表
                findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
                    if (data.data) {
                        this.unitList = data.data;
                    }
                });
            },
            findDepartmentList() {
                //获取部门列表
                findOrgDeptByOrgId({orgId: this.user.orgID}).then(data => {
                    if (data.data) {
                        this.departmentList = data.data;
                    }
                });
            },
            findPersonnelList() {
                //获取人员列表
                findUsersByDeptIdAndPermission({
                    deptid: this.user.unitID,
                    permission: "dealpersonnel"
                }).then(data => {
                    if (data.data) {
                        this.personnelList = data.data;
                    }
                });
            },
            filterDepartmentList() {
                return this.departmentList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            filterPCcs(value) {
                //过滤人员
                this.personnelForm.ccs = this.personnelForm.ccs.filter(
                    item => item !== this.personnelForm.leading
                );
            },
            filterPersonnelList() {
                return this.personnelList.filter(
                    item => item.primaryKey !== this.personnelForm.leading
                );
            },
            ifMainPersonnel() {
                //判断是否主要人员
                if (!this.personnelForm.leading) {
                    this.$refs.pCcsSelect.visible = false;
                    this.$alert("请先选择主要人员！", "提示", {
                        confirmButtonText: "确定",
                        type: "warning"
                    });
                }
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        orgid: this.assignmentForm.unit,
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
                                            message:"提交成功！",
                                            type:"success",
                                            duration:"1000"
                                        });
                                        if(window.opener){
                                            window.opener.location.reload();//刷新父窗口
                                        }
                                        setInterval(function(){
                                            window.close();
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
            submitPersonnel() {
                //分派人员
                let validation = false;
                this.$refs.personnelForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
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
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerPersonnel",
                        data: this.economyform,
                        opinion: this.personnelForm.opinion,
                        // caseprocesform: this.caseprocesform,
                        deptid: this.personnelForm.leading,
                        ortherDept: this.personnelForm.ccs,
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
            //立案处理页面word导出
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
        async created() {
            // await this.listSubjectType();
            this.getBaseInfo();
            this.getSubjecttype1();
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            this.findUnitList();
            this. findDepartmentList();
            this.findPersonnelList();
            this.SearchMessagePerson();

        }
    };
</script>
<style lang="scss" scoped>
    .filing {
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
