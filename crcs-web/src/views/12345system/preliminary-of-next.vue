<!--初步答复的下一步操作页面->受理，不受理，分派，退回-->
<template>
    <section class="preliminary_of_next" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent" style="min-width: 800px">
                <div style="text-align: right;" v-if="ismain"  :class="{'fixed':scrollTop > height}">
                    <el-button type="primary" size="small" icon="document" @click="exportToWord" :loading="exportLoading"
                               style="margin: 2px; border-radius: 13px" >
                        <template v-if="exportLoading">
                            正在导出...
                        </template>
                        <template v-else >
                            <i class="fa fa-download"></i>
                            Word导出
                        </template>
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')">受理</el-button>
                    <el-button type="primary" @click="triggerTab('tabNotAcceptance')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">不受理</el-button>
                    <el-button type="primary" @click="triggerTab('tabAssignment')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">分派下级
                    </el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabBack')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">退回</el-button>
                </div>
                <div style="text-align: right;" v-if="ismains">
                    <el-button type="primary" @click="triggerTab('tabOpinion')">意见填写</el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <!-- 定单信息 -->
                    <el-collapse-item name="orderInfo">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>1.</em> 定单信息
                                </el-col>
                                <el-col :span="10" class="right">定单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">接听坐席：</el-col>
                                <el-col :span="16">{{customerinfo.seatsname}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">登记时间：</el-col>
                                <el-col :span="16">{{formatDate(customerinfo.seatsregistrationtime)}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">性别：</el-col>
                                <el-col :span="16">{{customerinfo.seatssex}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">语种：</el-col>
                                <el-col :span="16">{{customerinfo.seatslanguage}}</el-col>
                            </el-col>
                        </el-row>
                    </el-collapse-item>
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>2.</em> {{formTypeStr}}
                                </el-col>
                                <!--<el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>-->
                            </el-row>
                        </div>
                        <!-- 消费投诉信息内容 -->
                        <complaintmoduleInfo v-if="complaintform" :complaintform="complaintform" :tasklist="tasklist"/>
                        <!--经济违法信息内容 -->
                        <economyInfo v-if="economyform" :economyform="economyform" :tasklist="tasklist"/>
                        <!--咨询或者通用表单 -->
                        <consultationInfo v-if="consultationform||commomform"
                                          :formData="consultationform?consultationform:commomform"
                                          :tasklist="tasklist"/>
                    </el-collapse-item>
                    <!-- 处办要求 -->
                    <el-collapse-item name="officeRequirements" v-if="formType != 'commommodule'">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>3.</em> 处办要求
                                </el-col>
                                <el-col :span="10" class="right"></el-col>
                            </el-row>
                        </div>
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应受理时限：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>
                            </el-col>
                            <!--                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">-->
                            <!--                                <el-col :span="8" class="label">受理时间：</el-col>-->
                            <!--                                <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>-->
                            <!--                            </el-col>-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType == 'comeconomicmodule'">
                                <el-col :span="8" class="label">应立案时限：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.caseputtime)}}</el-col>
                            </el-col>
                            <!--                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType == 'comeconomicmodule'">-->
                            <!--                                <el-col :span="8" class="label">立案时间：</el-col>-->
                            <!--                                <el-col :span="16">{{formatDate(handledemand.caseputtimesurplus)}}</el-col>-->
                            <!--                            </el-col>-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="handledemand.resulttime != '' || handledemand.resulttime != null">
                                <el-col :span="8" class="label">应反馈时间：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.resulttime)}}</el-col>
                            </el-col>
                        </el-row>
                        <el-row>
                            <!--msx-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col2">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileList"
                                        ref="upload"
                                        :on-preview="previewFile"
                                        :data="this.requestMap"
                                        :on-progress="progress"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                        class="upload-demo"
                                        :on-remove="handleRemove"
                                >
                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <el-button
                                            size="small"
                                            type="success"
                                            @click="submitUpload('upload')"
                                    >确认上传
                                    </el-button>
                                </el-upload>
                            </el-col>
                        </el-row>

                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!--办理跟踪-->
            <el-tab-pane name="blgz" label="办理跟踪">
                <el-collapse v-model="collapseValues">
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
                </el-collapse>
            </el-tab-pane>

            <!-- 受理 -->
            <el-tab-pane label="受理" name="tabAcceptance" v-if="tabActionName==='tabAcceptance'">
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
            <el-tab-pane label="不受理" name="tabNotAcceptance" v-if="tabActionName==='tabNotAcceptance'">
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
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabActionName==='tabAssignment'">
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
                            <el-form-item label="抄送部门" class="form_bt" >
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
            <!-- 退回 -->
            <el-tab-pane label="退回" name="tabBack" v-if="tabActionName==='tabBack'">
                <el-form :model="backForm" ref="backForm" label-width="200px" label-suffix="：" :rules="backFormRules"
                         class="text_bold">
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
                            <el-form-item label="附件清单">
                                <el-upload class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/"
                                           multiple>
                                    <el-button size="small" type="primary">选择附件</el-button>
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

            <!-- 意见填写 -->
            <el-tab-pane label="意见填写" name="tabOpinion" v-if="tabActionName==='tabOpinion'">
                <el-form :model="opinionForm" label-width="200px" ref="opinionForm" :rules="opinionFormRules"
                         class="text_bold" label-suffix="：">
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
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,
        findUsersByOrgAndPermission,
        findUsersByDeptIdAndJurisdiction
    } from "../../api/convenient12345";
    import {myMixin} from "../../assets/mixin/mixin"
    import Tracking from "../../components/Tracking";
    import ComplaintmoduleInfo from "./components/ComplaintmoduleInfo";
    import EconomyInfo from "./components/EconomyInfo";
    import ConsultationInfo from "./components/ConsultationInfo";
    import {deleteUploadFile} from "../../api/api"

    export default {
        mixins: [myMixin],
        components: {
            Tracking,
            ComplaintmoduleInfo,
            EconomyInfo,
            ConsultationInfo
        },
        data() {
            return {
                fileList:[],
                requestMap:{
                    caseguid: ""
                },
                exportLoading:false,
                user: JSON.parse(localStorage.getItem("user")),
                formType: "",
                formTypeStr: "",
                nodeStr: undefined,
                tabActionName: "tabMainContent",
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking"
                ],
                loading: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: undefined,
                consultationform: undefined,
                handledemand: {},
                opinionList: [],
                caseprocesform: {},
                customerinfo: {},
                tasklist: {},
                ordernumber: "",
                acceptForm: {},
                opinionForm: {},
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
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                },
                backForm: {
                    opinion:"",
                },
                backFormRules: {
                    reason: [
                        {required: true, message: "请选择退回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入内容", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                assignmentForm: {
                    ortherDept: [],
                },
                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"}
                    ],
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
                unitList: [],
                deptList: [],
                depts: '',
                submitLoading: false,
                ismain: true,
                ismains: false,
                newCompanyLists: [],
            };
        },
        methods: {
            //导出为Word格式
            exportToWord(){
                //query传参,另一页面进行获取方法
                const querystring = require('querystring');
                let str=   querystring.stringify({caseguid:this.$route.query.caseguid , formType:this.formType,tserialnum:this.tasklist.tserialnum,systemsource:this.tasklist.systemsource})
                window.open('/crcs/businessSearch/wordexport?'+str)

            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            triggerTab(value) {
                this.tabActionName = value;
                if ("tabAcceptance" === value) {
                    this.acceptForm.isAccep = "受理";
                } else if ("tabNotAcceptance" === value) {
                    this.acceptForm.isAccep = "不受理";
                }
                this.assignmentForm.opinion =
                    "处理期限：" +
                    this.$moment(this.tasklist.sdexpirationdate).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                this.departmentForm.opinion =
                    "处理期限：" +
                    this.$moment(this.tasklist.sdexpirationdate).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                this.personnelForm.opinion =
                    "处理期限：" +
                    this.$moment(this.tasklist.sdexpirationdate).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
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
                    // let params = {
                    //   user: this.user,
                    //   formType: this.formType,
                    //   node: this.nodeStr,
                    //   nextNodeId: "backForward",
                    //   data: this[this.formType],
                    //   opinion: this.backForm.opinion
                    // };
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = this.economyform;
                    }
                    let params;
                    // if (this.user.regionID == "440681") {
                    if (this.user.aicPermissions.dealdepartment == "bmfwDispatch"){
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
                                // this.$router.push("/wddb/12345db");
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
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = this.economyform;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        caseprocesform: this.caseprocesform,
                        orgid: this.assignmentForm.unit,
                        isnewassign:'1'
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                this.$router.push("/wddb/12345db");
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
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = this.economyform;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerDept",
                        data: data,
                        opinion: this.departmentForm.opinion,
                        caseprocesform: this.caseprocesform,
                        deptid: this.departmentForm.leading,
                        ortherDept: this.departmentForm.ccs,
                        isnewassign:'1'
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {

                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                this.$router.push("/wddb/12345db");
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
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
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
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = this.economyform;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "accept",
                        data: data,
                        opinion: this.acceptForm.mode,
                        handledemand:this.handledemand
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
                                if ("comeconomicmodule" === this.formType) {
                                    this.$router.push({
                                        path: "/wddb/12345db/lacl",
                                        query: params
                                    });
                                } else {
                                    this.$router.push({
                                        path: "/wddb/12345db/clfpysth",
                                        query: params
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
                    let caseguid;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            caseguid = this.commomform.caseguid;
                        } else {
                            caseguid = this.consultationform.caseguid;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        caseguid = this.complaintform.caseguid;
                    } else if ("comeconomicmodule" === this.formType) {
                        caseguid = this.economyform.caseguid;
                    }
                    let params = {
                        user: this.user,
                        caseguid,
                        node: this.nodeStr,
                        nextNodeId: "copyToOpinion",
                        opinion: this.opinionForm.opinion,
                        ismain: "0"
                    };
                    nextWork(params).then(data => {
                        if (data.code == 0) {
                            this.$message({
                                message: '提交成功！',
                                type: 'success',
                                duration: '1000'
                            });
                            this.$router.push("/wddb/12345db");
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
                searchBaseInfo({id, caseguid, systemsource: "02"}).then(({code, data}) => {
                    this.nodeStr = data.tasklist.nextnodeid;
                    // if (
                    //   this.nodeStr !== "interimReply" &&
                    //   data.tasklist.isaccept === "1"
                    // ) {
                    //   //节点与当前对应不上
                    //   this.$router.push("/wddb/12345db");
                    //   return;
                    // }
                    this.caseprocesform = data.caseprocesform;
                    this.customerinfo = data.customerinfo;
                    this.tasklist = data.tasklist;
                    this.opinionList = data.opinionList;
                    this.handledemand = data.handledemand;
                    if (code === 0) {
                        if (data.economyform != null) {
                            //经济表单
                            this.formType = "comeconomicmodule";
                            this.formTypeStr = "经济违法行为表单";
                            this.economyform = data.economyform;
                            this.ordernumber = this.economyform.ordernumber;
                            if(this.economyform.providetype != null && this.economyform.providetype != ""){
                                this.providetype1 = this.economyform.providetype
                                this.economyform.providetype = this.economyform.providetype.split("-")//将字符串格式转换成数组
                                this.economyform.providetype = this.economyform.providetype[this.economyform.providetype.length-1].toString()
                            }
                            if(this.economyform.objectcategory != null && this.economyform.objectcategory != ""){
                                this.objectcategory1 = this.economyform.objectcategory
                                this.economyform.objectcategory = this.economyform.objectcategory.split("-")//将字符串格式转换成数组
                                this.economyform.objectcategory = this.economyform.objectcategory[this.economyform.objectcategory.length-1].toString()
                            }
                            if(this.economyform.reportprovidelb != null && this.economyform.reportprovidelb != ""){
                                this.reportprovidelb1 = this.economyform.reportprovidelb
                                this.economyform.reportprovidelb = this.economyform.reportprovidelb.split("-")//将字符串格式转换成数组
                                this.economyform.reportprovidelb = this.economyform.reportprovidelb[this.economyform.reportprovidelb.length-1].toString()
                            }
                            if(this.economyform.illegalrules != null && this.economyform.illegalrules != ""){
                                this.illegalrules1 = this.economyform.illegalrules
                                this.economyform.illegalrules = this.economyform.illegalrules.split("-")//将字符串格式转换成数组
                                this.economyform.illegalrules = this.economyform.illegalrules[this.economyform.illegalrules.length-1].toString()
                            }
                            if(this.economyform.industryname != null && this.economyform.industryname != ""){
                                this.industryname1 = this.economyform.industryname
                                this.economyform.industryname = this.economyform.industryname.split("-")//将字符串格式转换成数组
                                this.economyform.industryname = this.economyform.industryname[this.economyform.industryname.length-1].toString()
                            }
                        } else if (data.complaintform != null) {
                            //消费投诉表单
                            this.formType = "complaintmodule";
                            this.formTypeStr = "消费投诉表单";
                            this.complaintform = data.complaintform;
                            this.ordernumber = this.complaintform.ordernumber;
                            if(this.complaintform.entitytype != null && this.complaintform.entitytype != ""){
                                this.entitytype2 = this.complaintform.entitytype
                                this.complaintform.entitytype = this.complaintform.entitytype.split("-")//将字符串格式转换成数组
                                this.complaintform.entitytype = this.complaintform.entitytype[this.complaintform.entitytype.length-1].toString()
                            }
                            if(this.complaintform.servicetype != null && this.complaintform.servicetype != ""){
                                this.servicetype1 = this.complaintform.servicetype
                                this.complaintform.servicetype = this.complaintform.servicetype.split("-")//将字符串格式转换成数组
                                this.complaintform.servicetype = this.complaintform.servicetype[this.complaintform.servicetype.length-1].toString()
                            }
                            if(this.complaintform.complaintstype != null && this.complaintform.complaintstype != ""){
                                this.complaintstype1 = this.complaintform.complaintstype
                                this.complaintform.complaintstype = this.complaintform.complaintstype.split("-")//将字符串格式转换成数组
                                this.complaintform.complaintstype = this.complaintform.complaintstype[this.complaintform.complaintstype.length-1].toString()
                            }
                            if (
                                this.complaintform.businesstypecodeid != null &&
                                this.complaintform.businesstypecodeid != ""
                            ) {
                                this.Businesstype1 = this.complaintform.businesstype;
                                this.complaintform.businesstype = this.complaintform.businesstype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.complaintform.businesstype = this.complaintform.businesstype[
                                this.complaintform.businesstype.length - 1
                                    ].toString();
                            }
                        } else if (data.consultationform != null) {
                            //咨询登记
                            this.formType = "commommodule";
                            this.formTypeStr = "咨询";
                            this.consultationform = data.consultationform;
                            // this.ordernumber = this.consultationform.ordernumber;
                            if(this.consultationform.entitytype != null && this.consultationform.entitytype != ""){
                                this.Entitytype1 = this.consultationform.entitytype
                                this.consultationform.entitytype = this.consultationform.entitytype.split("-")//将字符串格式转换成数组
                                this.consultationform.entitytype = this.consultationform.entitytype[this.consultationform.entitytype.length-1].toString()
                            }
                        } else if (data.commomform != null) {
                            //通用表单
                            this.formType = "commommodule";
                            this.formTypeStr = "通用表单";
                            this.commomform = data.commomform;
                            // this.ordernumber = this.commomform.ordernumber;
                            if(this.commomform.entitytype != null && this.commomform.entitytype != ""){
                                this.Entitytype1 = this.commomform.entitytype
                                this.commomform.entitytype = this.commomform.entitytype.split("-")//将字符串格式转换成数组
                                this.commomform.entitytype = this.commomform.entitytype[this.commomform.entitytype.length-1].toString()
                            }
                        } else {
                            //找不到对的工单信息
                            this.$alert("找不到对应的工单信息!", "提示", {
                                confirmButtonText: "确定",
                                type: "warning"
                            });
                            this.$router.push("/wddb/12345db");
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
            findUnit() {
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
            //根据当前用户权限查询人员列表
            delayPersonnel() {
                if (this.user.aicPermissions.dealdepartment != undefined) {
                    let para = {
                        permission: "subordinate",
                        orgid: "6ba12fbe-0100-1000-e000-03fec0a85893"
                    };
                    findUsersByOrgAndPermission(para).then(res => {
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
                // else {
                //   let para = {
                //     permission: "dealpersonnel",
                //     deptid:this.user.unitID
                //   };
                //   findUsersByDeptIdAndPermission(para).then(res => {
                //     this.leaderLists = res.data.data;
                //   });
                // }
            },
            getCompanyList() {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    regionId: users.regionID
                };
                findOrgUnitByRegionId(para).then(res => {
                    if (res.code === 0) {
                        for (let i in res.data) {
                            this.units.unitList[i] = {
                                value: "",
                                label: ""
                            };
                            this.units.unitList[i].value = res.data[i].primaryKey;
                            this.units.unitList[i].label = res.data[i].name;
                        }
                    }
                });
            },
            // compare() {
            //     let regionID = JSON.parse(localStorage.getItem("user")).regionID;
            //     if (regionID == "440681") {
            //         this.getCompanyList();
            //         this.getDepartmentList();
            //         let arr = this.units.deptList;
            //         let arr1 = this.units.unitList;
            //         setTimeout(function() {
            //             for (let i = 0; i < arr.length; i++) {
            //                 arr1.push(arr[i]);
            //             }
            //             this.newCompanyLists = arr1;
            //             // this.Tasklist.companyOptions = this.newCompanyLists;
            //         }, 1000);
            //     } else {
            //         this.getDepartmentList();
            //     }
            // },
            // getDepartmentList() {
            //     let params = {
            //         orgId: JSON.parse(localStorage.getItem("user")).orgID
            //     };
            //     findOrgDeptByOrgId(params).then(res => {
            //         if (res.code === 0) {
            //             for (let i in res.data) {
            //                 this.units.deptList[i] = {
            //                     value: "",
            //                     label: ""
            //                 };
            //                 this.units.deptList[i].value = res.data[i].orgunitID;
            //                 this.units.deptList[i].label = res.data[i].name;
            //             }
            //         }
            //     });
            // },
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

            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            // 点击下载材料
            download(url) {
                window.open(`${process.env.Download_URL}` + url, "_blank")
            },
            progress(event, file, fileList) {
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
            onchange(file,fileList){},
            handleRemove(file, fileList) {
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 200 <= 1;
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
                    ".xlsx",
                    ".pdf",
                    ".zip",
                    ".wps"
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
                // this.requestMap.caseguid = this.commomform.caseguid;
                this.requestMap.caseguid = this.$route.query.caseguid;
                this.$refs.upload.submit();
            },
        },
        created() {
            this.getBaseInfo();
            this.findUnit();
            // this.unitChange();
            // this.compare();
            this.findDepartmentList();
            if ("0" == this.$route.query.ismain) {
                this.ismain = false;
                this.ismains = true;
            }
            console.log(this.$route.query)
        }
    };
</script>
<style lang="scss" scoped>
    .preliminary_of_next {
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
        .item_col2 {
            padding: 10px 0 0 0;
            /* border-bottom: 1px solid #e8e8e8; */
        }

        .el-form .el-row {
            margin: 0;
        }
    }
</style>
