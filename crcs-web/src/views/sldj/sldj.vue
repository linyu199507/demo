<template>
    <div>
        <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card" >
            <el-tab-pane label="咨询登记" name="first">

                <Commommodule
                        @save="submitFrom"
                        @toAccepts="toAccept"
                        @archive="pigeonhole"
                        ref="comform"
                        :commom="commommodule">
                </Commommodule>
                <!--<CommommoduleInfo @nextStep="getNext" @getReject="getRejectNum"></CommommoduleInfo>-->

            </el-tab-pane>
            <el-tab-pane label="诉求登记" name="second">
                <el-tabs v-model="secondActive" @tab-click="handleClick">
                    <el-tab-pane label="消费投诉登记" name="first">
                        <ComplaintmoduleForm
                                @save="submitFrom"
                                @toAccepts="toAccept"
                                @archive="pigeonhole"
                                ref="complain"
                        :complaint="complaintmodule">
                        </ComplaintmoduleForm>
                    </el-tab-pane>
                    <el-tab-pane label="经济违法登记" name="second">
                        <Economyform
                                @save="submitFrom"
                                @toAccepts="toAccept"
                                @archive="pigeonhole"
                                :comeconomic="comeconomicmodule"
                                ref="economy">
                        </Economyform>
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>
            <!-- 受理处理内容 -->
            <el-tab-pane label="初步答复" v-if="accept" name="sixth">
                <el-form
                        :model="nextStepForm"
                        ref="nextStepForm"
                        style="width: 96%;margin: 0 auto"
                        :rules="nextStepFormRules"
                >
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="5">
                            <el-form-item label="初步答复：" prop="department" class="bt">
                                <div style="line-height: 35px">是</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8" v-if="this.formType != 'commommodule'">
                            <el-form-item label-width="90px" label="选择方式：" prop="process" class="stab">
                                <el-select v-model="nextStepForm.process" placeholder="请选择" style="width: 200px;">
                                    <el-option
                                            v-for="item in acceptWay"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="6">
                            <el-button type="primary" @click="acceptA('受理')">确定</el-button>
                            <el-button @click="flows('受理')">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" v-if="nextOrg" name="third" style="min-height : 600px">
                <el-col :xs="24" :sm="24" :md="24" :lg="16">
                <el-form
                        :model="assignmentForm"
                        label-width="180px"
                        ref="assignmentForm"
                        label-suffix="："
                        :rules="assignmentFormRules"
                >
                    <el-form-item label="成员单位" class="bt" prop="unit" style="margin-top: 15px;">
                        <el-select
                                v-model="assignmentForm.unit"
                                placeholder="请选择成员单位"
                                clearable
                                style="width: 745px;"
                        >
                            <el-option
                                    v-for="item in orgList"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.primaryKey"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="抄送部门" class="bt" style="margin-top: 15px;">
                        <el-select
                                v-model="assignmentForm.depts"
                                multiple
                                placeholder="请选择抄送部门"
                                style="width: 745px;"
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
                    <el-form-item label="分派意见" class="bt" prop="opinion" style="margin-top: 15px;">
                        <el-input
                                type="textarea"
                                :rows="4"
                                placeholder="请输入分派意见（3000字内）"
                                v-model="assignmentForm.opinion"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="短信" prop="message" style="margin-top: 15px;">
                        <Cascader
                                :options="messageoptions"
                                v-model="checkList"
                        >
                        </Cascader>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="acceptA('分派下级')">确认</el-button>
                        <el-button @click="flows('分派下级')">取消</el-button>
                    </el-form-item>
                </el-form>
                </el-col>
            </el-tab-pane>

            <!--协同处理-->
            <el-tab-pane label="协同处理" v-if="collaborative" name="serven" style="min-height : 450px">
                <el-form
                        :model="assignmentForm"
                        label-width="180px"
                        ref="assignmentFormOne"
                        label-suffix="："
                        :rules="assignmentFormRulesOne"
                >
                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                    <el-form-item label="主导方" class="bt" prop="unit" style="margin-top: 15px;">
                        <el-select
                                v-model="assignmentForm.unit"
                                placeholder="请选择主导方"
                                clearable
                                style="width: 300px;"
                        >
                            <el-option
                                    v-for="item in userList"
                                    :key="item.regionID"
                                    :label="item.name"
                                    :value="item.primaryKey"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                        <el-form-item label="协助方"  class="bt"  prop="ortherDept">
                            <cascader
                                    style="width : 60%"
                                    :options="unitsoptionsofdept"
                                    v-model="assignmentForm.ortherDept"
                                    placeholder="请选择协助方"
                                    isMultiple="false"
                                    @my-change='casecaderChange'
                            ></cascader>
                        </el-form-item>
                    </el-col>
                    <!--<el-form-item label="抄送部门" class="bt">-->
                        <!--<el-select-->
                                <!--v-model="assignmentForm.ortherDept"-->
                                <!--multiple-->
                                <!--placeholder="请选择抄送部门"-->
                                <!--style="width: 500px;"-->
                                <!--@change="orgPermission()"-->
                        <!--&gt;-->
                            <!--<el-option-->
                                    <!--v-for="item in deptList"-->
                                    <!--:key="item.region"-->
                                    <!--:label="item.name"-->
                                    <!--:value="item.orgunitID"-->
                            <!--&gt;</el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                    <el-form-item label="分派意见" class="bt" prop="opinion" style="width: 80%">
                        <el-input
                                type="textarea"
                                :rows="4"
                                placeholder="请输入分派意见（3000字内）"
                                v-model="assignmentForm.opinion"
                        ></el-input>
                    </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="acceptA('协同处理')">确认</el-button>
                        <el-button @click="flows('协同处理')">取消</el-button>
                    </el-form-item>
                    </el-col>
                </el-form>
            </el-tab-pane>
            <!-- 不受理 -->
            <el-tab-pane label="不受理" v-if="notAccepts" name="fourth">
                <el-form
                        :model="nextStepForm"
                        ref="nextStepForm"
                        style="width: 96%;margin: 0 auto"
                        :rules="nextStepFormRules"
                >
                    <el-row style="margin: 20px 0;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="opinion" label-width="105px" class="bt">
                                <div style="line-height: 35px">{{nextStepForm.noAccept}}</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="department" label-width="130px" class="bt">
                                <el-select
                                        v-model="nextStepForm.cause"
                                        placeholder="请选择"
                                        prop="department"
                                        style="width: 180px;"
                                >
                                    <el-option
                                            v-for="item in reasonBack"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item
                                    label="不受理内容:"
                                    class="bt"
                                    prop="centent"
                                    label-width="105px"
                                    style="width: 80%"
                            >
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入内容"
                                        v-model="nextStepForm.centent"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <!--<el-form-item label="相关附件:" class="bt">-->
                                <!--<el-upload-->
                                        <!--:auto-upload="false"-->
                                        <!--:file-list="fileListsTwo"-->
                                        <!--ref="upload"-->
                                        <!--:data="this.requestMap"-->
                                        <!--:on-error="uploadFile"-->
                                        <!--:on-progress="progress"-->
                                        <!--:on-success="uploadSuccess"-->
                                        <!--:before-upload="beforeUpload"-->
                                        <!--:multiple="true"-->
                                        <!--action="/crcs/filesUpload/upload"-->
                                        <!--:on-remove="handleRemove"-->
                                <!--&gt;-->
                                    <!--<div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>-->
                                    <!--<el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
                                    <!--&lt;!&ndash;<el-button size="small" type="success" @click="submitUpload('uploadTwo')">确认上传&ndash;&gt;-->
                                    <!--&lt;!&ndash;</el-button>&ndash;&gt;-->
                                <!--</el-upload>-->
                            <!--</el-form-item>-->
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0;">
                        <el-col style="text-align: center;width: 90%;">
                            <el-button type="primary" @click="acceptA('不受理')">确定</el-button>
                            <el-button @click="flows('不受理')">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派部门 -->
            <el-tab-pane label="分派部门" v-if="nextUnits" name="five" style="min-height : 600px">
                <el-form
                        :model="assignmentForm"
                        label-width="180px"
                        ref="assignmentForm"
                        label-suffix="："
                        :rules="assignmentFormRules"
                >
                    <el-form-item label="主导部门" class="form_bt" prop="dept">
                        <el-select
                                v-model="assignmentForm.dept"
                                placeholder="请选择主导部门"
                                clearable
                                style="width: 745px;"
                                @visible-change="unitChange('1')"
                                @change="orgPermission('2')"
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
                    <el-form-item label="抄送部门" class="form_bt">
                        <el-select
                                v-model="assignmentForm.ortherDept"
                                multiple
                                placeholder="请选择抄送部门"
                                style="width: 745px;"
                                @visible-change="unitChange($event,'2')"
                                @change="orgPermission('3')"
                                filterable
                        >
                            <el-option
                                    v-for="item in depts"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.orgunitID"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分派意见" class="form_bt" prop="opinion" >
                        <el-input
                                type="textarea"
                                :rows="5"
                                placeholder="请输入分派意见（3000字内）"
                                v-model="assignmentForm.opinion"
                                style="width: 745px;"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="短信"  prop="BMmessage">
                        <Cascader
                                :options="BMmessageoptions"
                                v-model="BMcheckList"
                                style="width: 745px;"
                        >
                        </Cascader>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="acceptA('分派部门')">确认</el-button>
                        <el-button @click="flows()">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <!-- 历史工单查询的弹窗 -->
            <el-dialog title="工单查询" :visible.sync="dialogCommomform" size="large">
                <!--工具条-->
                <el-form :model="search" label-width="100px" class="search_tool">
                    <el-row>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="标题:">
                                <el-input v-model="search.rqsttitle" @change="searchChange" placeholder="标题"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="涉及主体名称:">
                                <el-input v-model="search.beComplainant" @change="searchChange"
                                          placeholder="涉及主体名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :lg="8" :md="8">
                            <el-form-item>
                                <el-button type="primary" @click="findList">查询</el-button>
                                <el-button @click="resetForm">重置</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <br>
                <!--列表-->
                <el-table
                        :data="complaintList"
                        highlight-current-row
                        v-loading="complaintLoading"
                        @selection-change="selsChange"
                        border
                        style="width: 100%"
                        :row-style="{cursor:'pointer'}"
                >
                    <!--                <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>-->
                    <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
                    <el-table-column prop="formtype" label="表单类型" min-width="5%" align="center" :formatter="formtypes" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="rqsttitle" label="工单标题" min-width="15%" align="center" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="subjectname" label="涉及主体名称" min-width="15%" align="center" show-overflow-tooltip v-if="this.search.formType != '01'"></el-table-column>
                    <el-table-column prop="entname" label="涉及主体名称" min-width="15%" align="center" show-overflow-tooltip v-if="this.search.formType == '01'"></el-table-column>
                    <el-table-column prop="updatedate" label="登记时间" min-width="8%" align="center" :formatter="formatDate" sortable="custom"></el-table-column>
                    <el-table-column label="操作" min-width="8%" align="center">
                        <template scope="scope">
                            <el-button size="small" @click="toFind(scope, scope.row)" type="primary">选择</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--分页组件开始-->
                <el-col style="text-align: right;margin: 15px auto">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :page-size="pagination.size"
                            :total="pagination.total"
                            :page-sizes="[10, 20, 30, 40,50]"
                            layout="slot,total, sizes, prev, pager, next, jumper"
                    >
                <span>
                    第 {{pagination.page}} 页 /
                    共 {{(parseInt((pagination.total + pagination.size - 1)/pagination.size))}} 页
                </span>
                    </el-pagination>
                </el-col>
                <!--分页组件结束-->
            </el-dialog>
        </el-tabs>
    </div>
</template>
<script>

    import {
        saveBaseInfo,
        next,
        searchBaseInfo,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,
        searchConsultationformList,
        searchComplaintformList,
        searchEconomyformList,
        getOptionsOfDept,
        getOptions,
        getAllData,
        getMaterialLists,deleteUploadFile,
        getMessagePerson,sendMessage,getSMSPerson
    } from "../../api/api";
    import moment from "moment/moment";

    import Commommodule from "./components/commommoduleForm";
    import Economyform from "./components/Economyform";
    import ComplaintmoduleForm from "./components/ComplaintmoduleForm";
    import Cascader from "../../components/cascader";

    export default {

        components: {
            Commommodule,
            Economyform,
            ComplaintmoduleForm,
            Cascader
        },

        data() {
            return {
                timesChange:true,
                checkList:[],
                BMcheckList:[],
                saveChange:false,
                Acceptdate:null,
                Feedbackdate:null,
                Assignmentdate:null,
                Feedbacktime:null,
                Complaintstype:null,

                activeName: "first",
                secondActive: "first",
                collaborative: false,
                nextOrg: false,
                reject: false,
                accept: false,
                notAccepts:false,
                nextUnits:false,
                formData: {},
                fileList: [],
                options:[],
                formType:"",
                formTypeStr:"",
                nodes: "",
                aictabs: "commommodule",
                acceptdateA:null,
                commommodule: {
                    id:"",
                    caseguid:"",
                    save:"",  //标识是否暂存
                    complaintstypecodeid:"",
                },
                complaintmodule:  {
                    id:"",
                    caseguid:"",
                    acceptdate:"",
                    feedbackdate:"",
                    assignmentdate:"",
                    feedbacktime:""
                },
                comeconomicmodule:  {
                    id:"",
                    caseguid:"",
                },
                nextStepForm: {
                    noAccept: "不受理",
                    centent: "",
                    department: "受理",
                    process: "",
                    cause: "",
                    options: []
                },
                requestMap: {
                    caseguid: ""
                },
                deptList: [],
                depts:[],
                orgList: [],
                userList:[  JSON.parse(localStorage.getItem("user")) ],
                unitsoptionsofdept:[],
                messageoptions:[],
                BMmessageoptions:[],
                assignmentForm: {
                    unit: "",
                    opinion: "",
                    dept: "",
                    ortherDept: [],
                    person: "",
                    ortherPerson: []
                },
                assignmentFormRules: {
                    unit: [{required: true, message: "请选择成员单位"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    dept: [{ required: true, message: "请选择主导部门" }],
                    // depts: [{required: true, message: "请选择抄送部门"}]
                },
                assignmentFormRulesOne: {
                    unit: [{required: true, message: "请选择成员单位"}],
                    // ortherDept: [{required: true, message: "请选择成员单位",type: "list"},],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                },
                nextStepFormRules: {
                    department: [{required: true, message: "请选择", trigger: "change"}],
                    process: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    centent: [{required: true, message: "请输入", trigger: "blur"}]
                },
                reasonBack: [
                    {
                        value: "缺失要素",
                        label: "缺失要素"
                    },
                    {
                        value: "表述不清",
                        label: "表述不清"
                    },
                    {
                        value: "类型错误",
                        label: "类型错误"
                    },
                    {
                        value: "地址不祥",
                        label: "地址不祥"
                    },
                    {
                        value: "已有知识",
                        label: "已有知识"
                    },
                    {
                        value: "重复受理",
                        label: "重复受理"
                    },
                    {
                        value: "不属本职",
                        label: "不属本职"
                    },
                    {
                        value: "市民撤诉",
                        label: "市民撤诉"
                    }
                ],

                acceptWay: [
                    {
                        value: "调解",
                        label: "调解"
                    },
                    {
                        value: "查处",
                        label: "查处"
                    },
                    {
                        value: "调查",
                        label: "调查"
                    }
                ],
                dialogCommomform:false,
                search: {
                    rqsttitle: "",
                    entname:"",
                    formType:"",
                },
                complaintList:[],
                complaintLoading:false,
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1
                },
                list: [], //列表选中列，业务caseguid
                idList: [], //列表选中列 id
            };
        },
        // components: {
        //     Commommodule,
        //     Economyform,
        //     ComplaintmoduleForm
        // },
        methods: {

            casecaderChange(obj){
                console.log(obj)
                let data = ""
                for( var key in obj){

                    data =  data + obj[key].label + " 、"
                }
                this.assignmentForm.opinion ="协同人员：" + data

            },

            beforeUpload() {
            },
            uploadSuccess(response, file, fileList) {
                this.imageUrl = URL.createObjectURL(file.raw);
                if (file.response.code === 0) {
                    alert("附件上传成功")
                } else {
                    this.$message.error("附件上传失败" + file.response.msg);
                }
            },
            //受理分页跳转
            toAccept(value) {
                console.log(value)
                this.formType = value.formType;
                this.aictabs = value.aictabs;
                if("commommodule" == this.formType){
                    console.log("sldj的commommodule")
                    this.commommodule = value.commommodule;
                    this.search.formType = '01';
                }
                if("complaintmodule" == this.formType){
                    this.complaintmodule = value.complaintmodule;
                    this.search.formType = '02';
                }
                if("comeconomicmodule" == this.formType){
                    this.comeconomicmodule = value.comeconomicmodule;
                    this.search.formType = '03';
                }
                if ("accepts" == value.parameter) {
                    this.accept = true;
                    this.notAccepts = false;
                    this.nextOrg = false;
                    this.nextUnits = false;
                    this.collaborative = false;
                    this.activeName = 'sixth'
                } else if ("notAccepts" == value.parameter) {
                    this.notAccepts = true;
                    this.nextUnits = false;
                    this.accept = false;
                    this.nextOrg = false;
                    this.collaborative = false;
                    this.activeName = 'fourth'
                } else if("nextUnits" == value.parameter){
                    this.nextUnits = true;
                    this.notAccepts = false;
                    this.accept = false;
                    this.nextOrg = false;
                    this.collaborative = false;
                    this.activeName = 'five'
                } else if("dialog" == value.parameter){
                    this.nextUnits = false;
                    this.notAccepts = false;
                    this.accept = false;
                    this.nextOrg = false;
                    this.dialogCommomform = true;
                    this.collaborative = false;
                    this.findList();
                }else if("collaborative" == value.parameter){
                    this.nextUnits = false;
                    this.notAccepts = false;
                    this.accept = false;
                    this.nextOrg = false;
                    this.collaborative = true;
                    this.activeName = 'serven'
                }else {
                    this.collaborative = false;
                    this.nextOrg = true;
                    this.nextUnits = false;
                    this.accept = false;
                    this.notAccepts = false;
                    this.activeName = 'third'
                }
            },
            //受理&&分派--确认
            acceptA(value) {
                let data;
                let worktype;
                if (this.nodes == "") {
                    this.nodes = "begin";
                } else if (this.$route.query.node != undefined) {
                    this.nodes = this.$route.query.node;
                }

                if (this.formType == "commommodule") {
                    if(this.timesChange){
                        this.DateToTime()
                    }
                    if (this.commommodule.id == null || this.commommodule.id == "") {
                        data = this.commommodule;
                        worktype = this.commommodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    } else {
                        data = this.commommodule;
                        worktype = this.commommodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    }
                }
                if (this.formType == "complaintmodule") {
                    if(this.timesChange){
                        this.DateToTime()
                    }
                    if (this.complaintmodule.id == null || this.complaintmodule.id == "") {
                        data = this.complaintmodule;
                        worktype = this.complaintmodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    } else {
                        data = this.complaintmodule;
                        worktype = this.complaintmodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    }
                }
                if (this.formType == "comeconomicmodule") {
                    if(this.timesChange){
                        this.DateToTime()
                    }
                    data = this.comeconomicmodule;
                    worktype = this.comeconomicmodule.worktype;
                    for (let key in data) {
                        if (data[key] instanceof Array) {
                            if (data[key].length > 0) {
                                data[key] = data[key].pop();
                            } else {
                                data[key] = null;
                            }
                        }
                    }
                }
                let user = localStorage.getItem("user");
                if ("受理" == value) {
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        nextStepForm: this.nextStepForm,
                        formType: this.formType,
                        data,
                        nextNodeId: "accept",
                        caseguid: this.requestMap.caseguid,
                        opinion: this.nextStepForm.process,
                        worktype,
                        type: "manualEntry"
                    };
                    console.log(para)
                    next(para).then(res => {
                        console.log(res)
                        if (res.code == 0) {
                            if(this.formType==="commommodule") {
                                this.commommodule.caseguid = res.data.caseguid;
                                this.$refs.comform.submitUploadCommon('upload');
                            }
                            if(this.formType==="complaintmodule") {
                                this.complaintmodule.caseguid = res.data.caseguid;
                                this.$refs.complain.submitUpload('upload');
                            }
                            if(this.formType==="comeconomicmodule") {
                                this.comeconomicmodule.caseguid = res.data.caseguid;
                                this.$refs.economy.submitUpload('upload');
                            }
                            if ("true" != res.data.str) {
                                this.$alert(res.data.str, "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.aictabs = "appeal";
                                this.activeName = "complaintmodule";
                                this.accept = false;
                                this.acceptE = false;
                                this.notAccepts = false;
                                this.nextOrg = false;
                            } else {
                                this.$message({
                                    message: '受理成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                let para = {
                                    id: res.data.id,
                                    caseguid: res.data.caseguid,
                                    node: "accept"
                                };
                                this.$router.push({path: "/wddb/bxtdb/gdfk", query: para});
                            }
                        } else {
                            this.$alert("提交失败!", "提示", {
                                confirmButtonText: "确定",
                                type: "defeated"
                            });
                        }
                    });
                } else if ("不受理" == value) {
                    let noAcceptreason;
                    noAcceptreason = this.nextStepForm.cause;
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        // nextStepForm:this.nextStepForm,
                        formType: this.formType,
                        data,
                        nextNodeId: "noAccept",
                        opinion: this.nextStepForm.centent,
                        noAcceptreason: noAcceptreason,
                        isEnd: "1"
                    };
                    next(para).then(res => {
                        if (res.code === 0) {
                            if(this.formType==="commommodule") {
                                console.log("99099")
                                this.commommodule.caseguid = res.data.caseguid;
                                this.$refs.comform.submitUploadCommon('upload');
                            }
                            if(this.formType==="complaintmodule") {
                                this.complaintmodule.caseguid = res.data.caseguid;
                                this.$refs.complain.submitUpload('upload');
                            }
                            if(this.formType==="comeconomicmodule") {
                                this.comeconomicmodule.caseguid = res.data.caseguid;
                                this.$refs.ComplaintmoduleForm.submitUpload('upload');
                            }
                            this.$message({
                                message: '提交成功！',
                                type: 'success',
                                duration: '1000'
                            });
                            this.$router.push("/wddb/bxtdb");
                        } else {
                            this.$alert("提交失败!", "提示", {
                                confirmButtonText: "确定",
                                type: "defeated"
                            });
                        }
                    });
                } else if ("协同处理" == value){
                    this.timesChange = false
                    let validation;
                    console.log(validation)
                    this.$refs["assignmentFormOne"].validate(
                        valid => (validation = valid)
                    );
                    console.log(validation)
                    if(validation){
                        if(this.assignmentForm.ortherDept.length > 0 ){
                            let para = {
                                user: JSON.parse(user),
                                node: this.nodes,
                                nextNodeId: "collaborative",
                                formType: this.formType,
                                deptid: this.assignmentForm.dept,
                                data,
                                ortherDept: this.assignmentForm.ortherDept,
                                opinion: this.assignmentForm.opinion
                            };
                            console.log("进入")
                            next(para).then(res => {
                                if (res.code === 0) {
                                    if(this.formType==="commommodule") {
                                        this.commommodule.caseguid = res.data.caseguid;
                                        this.$refs.comform.submitUploadCommon('upload');
                                    }
                                    if(this.formType==="complaintmodule") {
                                        this.complaintmodule.caseguid = res.data.caseguid;
                                        this.$refs.complain.submitUpload('upload');
                                    }
                                    if(this.formType==="comeconomicmodule") {
                                        this.comeconomicmodule.caseguid = res.data.caseguid;
                                        // this.$refs.ComplaintmoduleForm.submitUpload('upload');
                                        this.$refs["economy"].submitUpload('upload');
                                    }
                                    this.$message({
                                        message: '分派部门成功！',
                                        type: 'success',
                                        duration: '1000'
                                    });
                                    this.$router.push("/wddb/bxtdb");
                                } else {
                                    this.$alert("提交失败!", "提示", {
                                        confirmButtonText: "确定",
                                        type: "defeated"
                                    });
                                }
                            });
                        }else {
                            this.$notify({
                                title: '提示',
                                message: this.$createElement('i', { style: 'color: teal'}, '协同方未选择，请先选择后再进行操作')
                            });
                        }

                    }
                } else if ("分派部门" == value){
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        nextNodeId: "moveToLowerDept",
                        formType: this.formType,
                        deptid: this.assignmentForm.dept,
                        data,
                        ortherDept: this.assignmentForm.ortherDept,
                        opinion: this.assignmentForm.opinion,
                        phonelist:this.BMcheckList
                    };
                    next(para).then(res => {
                        if (res.code === 0) {
                            if(this.formType==="commommodule") {
                                this.commommodule.caseguid = res.data.caseguid;
                                this.$refs.comform.submitUploadCommon('upload');
                            }
                            if(this.formType==="complaintmodule") {
                                this.complaintmodule.caseguid = res.data.caseguid;
                                this.$refs.complain.submitUpload('upload');
                            }
                            if(this.formType==="comeconomicmodule") {
                                this.comeconomicmodule.caseguid = res.data.caseguid;
                                // this.$refs.ComplaintmoduleForm.submitUpload('upload');
                                this.$refs["economy"].submitUpload('upload');
                            }

                            //分派部门短信发送
                            let paras={
                                phonelist:this.BMcheckList,
                                tserialnum:res.data.tserialnum,
                                caseguid:res.data.caseguid,
                            }
                            sendMessage(paras).then(res=>{
                                console.log(res)
                                if(res.code === 0){
                                    this.$message({
                                        message: '分派部门成功！',
                                        type: 'success',
                                        duration: '1000'
                                    });
                                    this.$router.push("/wddb/bxtdb");
                                }else {
                                    this.$alert("工单分派成功但短信发送超时！", "提示", {
                                        confirmButtonText: "确定",
                                        type: "error"
                                    });
                                }
                            })

                            // this.$message({
                            //     message: '分派部门成功！',
                            //     type: 'success',
                            //     duration: '1000'
                            // });
                            // this.$router.push("/wddb/bxtdb");
                        }
                        // else {
                        //     this.$alert("提交失败!", "提示", {
                        //         confirmButtonText: "确定",
                        //         type: "defeated"
                        //     });
                        // }
                    });
                } else {//分派下级
                    console.log(this.checkList)
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        nextNodeId: "moveToLowerOrg",
                        formType: this.formType,
                        orgid: this.assignmentForm.unit,
                        data,
                        ortherDept: this.assignmentForm.depts,
                        opinion: this.assignmentForm.opinion,
                        phonelist:this.checkList
                    };
                    console.log(para)
                    next(para).then(res => {
                        console.log(res)
                        if (res.code === 0) {
                            if(this.formType==="commommodule") {
                                this.commommodule.caseguid = res.data.caseguid;
                                this.$refs.comform.submitUploadCommon('upload');
                            }
                            if(this.formType==="complaintmodule") {
                                this.complaintmodule.caseguid = res.data.caseguid;
                                this.$refs.complain.submitUpload('upload');
                            }
                            if(this.formType==="comeconomicmodule") {
                                this.comeconomicmodule.caseguid = res.data.caseguid;
                                // this.$refs.ComplaintmoduleForm.submitUpload('upload');
                                this.$refs["economy"].submitUpload('upload');
                            }
                            //分派下级短信发送
                            let paras={
                                phonelist:this.checkList,
                                tserialnum:res.data.tserialnum,
                                caseguid:res.data.caseguid,
                            }
                            console.log(paras)
                            sendMessage(paras).then(res=>{
                                console.log(res)
                                if(res.code === 0){

                                    this.$message({
                                        message: '分派下级成功！',
                                        type: 'success',
                                        duration: '1000'
                                    });
                                    this.$router.push("/wddb/bxtdb");

                                }else{
                                    this.$alert("工单分派成功但短信发送超时！", "提示", {
                                        confirmButtonText: "确定",
                                        type: "error"
                                    });
                                }
                            })




                            // this.commommodule.caseguid=res.data.caseguid;
                            // this.$refs.comform.submitUploadCommon('upload');
                            // this.$message({
                            //     message: '分派下级成功！',
                            //     type: 'success',
                            //     duration: '1000'
                            // });
                            // this.$router.push("/wddb/bxtdb");
                        }
                // else {
                //             this.$alert("提交失败!", "提示", {
                //                 confirmButtonText: "确定",
                //                 type: "defeated"
                //             });
                //         }
                    });
                }
            },

            //暂存
            submitFrom(value) {

                this.formType = value.formType;
                if(value.commommodule != null ){
                    this.commommodule = value.commommodule;
                }if(value.complaintmodule != null ){
                    this.complaintmodule = value.complaintmodule;
                }if(value.comeconomicmodule != null ){
                    this.comeconomicmodule = value.comeconomicmodule;
                }
                let validation = true;
                //通过提交
                if (validation) {
                    let user = localStorage.getItem("user");
                    let worktype;
                    //将获取的类型数组转化为字符串格式
                    if (this.formType == "commommodule") {
                        this.DateToTime()
                        this.Complaintstype = this.commommodule.complaintstypecodeid
                        for (let key in this.commommodule) {
                            if (this.commommodule[key] instanceof Array) {
                                if (this.commommodule[key].length > 0) {
                                    this.commommodule[key] = this.commommodule[key].pop();
                                } else {
                                    this.commommodule[key] = null;
                                }
                            }
                        }
                    }
                    if (this.formType == "complaintmodule") {
                        this.DateToTime()
                        for (let key in this.complaintmodule) {
                            if (this.complaintmodule[key] instanceof Array) {
                                if (this.complaintmodule[key].length > 0) {
                                    this.complaintmodule[key] = this.complaintmodule[key].pop();
                                } else {
                                    this.complaintmodule[key] = null;
                                }
                            }
                        }

                    }
                    if (this.formType == "comeconomicmodule") {
                        this.DateToTime()
                        for (let key in this.comeconomicmodule) {
                            if (this.comeconomicmodule[key] instanceof Array) {
                                if (this.comeconomicmodule[key].length > 0) {
                                    this.comeconomicmodule[key] = this.comeconomicmodule[key].pop();
                                } else {
                                    this.comeconomicmodule[key] = null;
                                }
                            }
                        }
                    }
                    let para = {
                        user: JSON.parse(user),
                        formType: this.formType,
                        commommodule: this.commommodule,
                        complaintmodule: this.complaintmodule,
                        comeconomicmodule: this.comeconomicmodule,
                        node: "begin",
                        caseguid: this.requestMap.caseguid,
                        nextNodeId: "staging",
                        method: "submit",
                        worktype
                    };
                    console.log(para)
                    saveBaseInfo(para).then(res => {
                        console.log(res)
                        if (res.code === 0) {
                            console.log(res)
                            if(this.formType==="commommodule") {
                                this.commommodule.caseguid = res.data.tasklist.caseguid;
                                this.$refs.comform.submitUploadCommon('upload');
                                this.commommodule.complaintstypecodeid = this.Complaintstype
                            }
                            if(this.formType==="complaintmodule") {
                                this.complaintmodule.caseguid = res.data.tasklist.caseguid;
                                this.$refs.complain.submitUpload('upload');
                            }
                            if(this.formType==="comeconomicmodule") {
                                this.comeconomicmodule.caseguid = res.data.tasklist.caseguid;
                                // this.$refs.ComplaintmoduleForm.submitUpload('upload');
                                this.$refs["economy"].submitUpload('upload');
                            }
                            this.$message({
                                message: "保存成功！",
                                type: "success",
                                duration: "2000"
                            });
                            this.nodes = res.data.nextNodeId;
                            if ("commommodule" == res.data.formType) {
                                this.commommodule = res.data.customerinfo;
                                this.commommodule.save=="issave",//标识暂存
                                this.$refs["comform"].timeChange(this.commommodule)
                            } else if ("complaintmodule" == res.data.formType) {
                                this.complaintmodule = res.data.complaintform;
                                this.$refs["complain"].timeChange(this.complaintmodule)
                            } else if ("comeconomicmodule" == res.data.formType) {
                                this.comeconomicmodule = res.data.economyform;
                                this.$refs["economy"].timeChange(this.comeconomicmodule)
                            }

                        } else {
                            this.$alert("保存失败！", "提示", {
                                type: "error",
                                confirmButtonText: "确定"
                            });
                        }
                    });
                }
            },

            //归档
            pigeonhole(value) {
                let data;
                this.formType = value.formType;
                if ("commommodule" == value.formType) {
                    this.commommodule = value.commommodule
                    this.DateToTime();
                    for (let key in this.commommodule) {
                        if (this.commommodule[key] instanceof Array) {
                            if (this.commommodule[key].length > 0) {
                                this.commommodule[key] = this.commommodule[key].pop();
                            } else {
                                this.commommodule[key] = null;
                            }
                        }
                    }
                    data = this.commommodule;
                } else if ("complaintmodule" == value.formType) {
                    this.complaintmodule = value.complaintmodule
                    this.DateToTime();
                    for (let key in this.complaintmodule) {
                        if (this.complaintmodule[key] instanceof Array) {
                            if (this.complaintmodule[key].length > 0) {
                                this.complaintmodule[key] = this.complaintmodule[key].pop();
                            } else {
                                this.complaintmodule[key] = null;
                            }
                        }
                    }
                    data = this.complaintmodule;
                } else {
                    this.comeconomicmodule = value.comeconomicmodule
                    this.DateToTime();
                    for (let key in this.comeconomicmodule) {
                        if (this.comeconomicmodule[key] instanceof Array) {
                            if (this.comeconomicmodule[key].length > 0) {
                                this.comeconomicmodule[key] = this.comeconomicmodule[key].pop();
                            } else {
                                this.comeconomicmodule[key] = null;
                            }
                        }
                    }
                    data = this.comeconomicmodule;
                }
                let user = localStorage.getItem("user");
                let para = {
                    user: JSON.parse(user),
                    node: "begin",
                    formType: value.formType,
                    data,
                    nextNodeId: "end"
                };
                next(para).then(res => {
                    if (res.code === 0) {
                        if(this.formType==="commommodule") {
                            this.commommodule.caseguid = res.data.caseguid;
                            this.$refs.comform.submitUploadCommon('upload');
                        }
                        if(this.formType==="complaintmodule") {
                            this.complaintmodule.caseguid = res.data.caseguid;
                            this.$refs.complain.submitUpload('upload');
                        }
                        if(this.formType==="comeconomicmodule") {
                            this.comeconomicmodule.caseguid = res.data.caseguid;
                            this.$refs.ComplaintmoduleForm.submitUpload('upload');
                        }
                        this.$message({
                            message: '归档成功！',
                            type: 'success',
                            duration: '1000'
                        });
                        this.$router.push("/wddb/bxtdb");
                    } else {
                        this.$alert("归档失败!", "提示", {
                            confirmButtonText: "确定",
                            type: "defeated"
                        });
                    }
                });
            },

            //取消按钮
            flows(value){
                this.accept = false
                this.nextOrg = false
                this.notAccepts = false
                this.collaborative = false
                if("commommodule" == this.formType){
                    this.activeName = "first"
                }
                if("complaintmodule" == this.formType){
                    this.activeName = "second"
                    this.secondActive = "first"
                }
                if("comeconomicmodule" == this.formType){
                    this.activeName = "second"
                    this.secondActive = "second"
                }
            },

            orgPermission() {
                let unit = this.assignmentForm.depts[
                this.assignmentForm.depts.length - 1
                    ];
                let para = {
                    deptid: unit,
                    permission: "dealdepartment"
                };
                if (this.assignmentForm.depts.length > 0 && unit != "") {
                    findUsersByDeptIdAndPermission(para).then(res => {
                        if (res.data.data == null) {
                            this.$alert(
                                "该部门没有查看该业务权限的人员，请先添加权限!",
                                "提示",
                                {
                                    confirmButtonText: "确定"
                                }
                            );
                            this.assignmentForm.depts.splice(
                                this.assignmentForm.depts.indexOf(
                                    this.assignmentForm.depts[this.assignmentForm.depts.length - 1]
                                )
                            );
                        }
                    });
                }
            },

            DateToTime(){
                if (this.formType == "commommodule") {
                    if (this.commommodule.acceptdate != null){
                        this.commommodule.acceptdate = this.commommodule.acceptdate.getTime()
                    }
                    if (this.commommodule.finalfeedbackdate != null){
                        this.commommodule.finalfeedbackdate = this.commommodule.finalfeedbackdate.getTime()
                    }
                }
                if (this.formType == "complaintmodule") {
                    console.log(".......")
                    console.log(this.complaintmodule)

                    if (this.complaintmodule.acceptdate != null && typeof this.complaintmodule.acceptdate === "object"){
                        this.complaintmodule.acceptdate = this.complaintmodule.acceptdate.getTime()
                    }else if(this.complaintmodule.acceptdate != null && typeof this.complaintmodule.acceptdate === "string"){
                        this.complaintmodule.acceptdate = new Date(this.complaintmodule.acceptdate).getTime()
                    }
                    if (this.complaintmodule.feedbackdate != null && typeof this.complaintmodule.feedbackdate === "object"){
                        this.complaintmodule.feedbackdate = this.complaintmodule.feedbackdate.getTime()
                    }else if (this.complaintmodule.feedbackdate != null && typeof this.complaintmodule.feedbackdate === "string"){
                        this.complaintmodule.feedbackdate = new Date(this.complaintmodule.feedbackdate).getTime()
                    }

                    if (this.complaintmodule.assignmentdate != null && typeof this.complaintmodule.assignmentdate === "object"){
                        this.complaintmodule.assignmentdate = this.complaintmodule.assignmentdate.getTime()
                    }else if (this.complaintmodule.assignmentdate != null && typeof this.complaintmodule.assignmentdate === "string"){
                        this.complaintmodule.assignmentdate = new Date(this.complaintmodule.assignmentdate).getTime()
                    }


                    if (this.complaintmodule.feedbacktime != null && typeof this.complaintmodule.feedbacktime === "object"){
                        this.complaintmodule.feedbacktime = this.complaintmodule.feedbacktime.getTime()
                    }else if (this.complaintmodule.feedbacktime != null && typeof this.complaintmodule.feedbacktime === "string"){
                        this.complaintmodule.feedbacktime = new Date(this.complaintmodule.feedbacktime).getTime()
                    }
                }
                if (this.formType == "comeconomicmodule") {

                    if(this.comeconomicmodule.acceptdate != null){
                        console.log(this.comeconomicmodule.acceptdate)
                        this.comeconomicmodule.acceptdate = this.comeconomicmodule.acceptdate.getTime()
                        console.log(this.comeconomicmodule.acceptdate)

                    }
                    if(this.comeconomicmodule.feedbackdate != null){
                        this.comeconomicmodule.feedbackdate = this.comeconomicmodule.feedbackdate.getTime()
                    }
                    if(this.comeconomicmodule.assignmentdate != null){
                        this.comeconomicmodule.assignmentdate = this.comeconomicmodule.assignmentdate.getTime()
                    }
                    if(this.comeconomicmodule.filingcasesdate != null){
                        this.comeconomicmodule.filingcasesdate = this.comeconomicmodule.filingcasesdate.getTime()
                    }
                    if(this.comeconomicmodule.finalfeedbackdate != null){
                        this.comeconomicmodule.finalfeedbackdate = this.comeconomicmodule.finalfeedbackdate.getTime()
                    }
                }

            },

            getBaseInfo() {
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    return;
                }
                this.getBaseInfos(id,caseguid,'');
            },
            getBaseInfos(id,caseguid,value){
                // //获取工单信息
                let systemsource = "01";
                searchBaseInfo({id, caseguid, systemsource})
                    .then(res => {
                        this.tasklist = res.data.tasklist;
                        if (res.data.caseprocesform != null) {
                            this.caseprocesform = res.data.caseprocesform;
                        }

                        if (res.code === 0) {
                            console.log(res.data)
                            if (res.data.commomform != null) {
                                console.log("jinru")
                                //咨询登记
                                this.formType = "commommodule";
                                this.formTypeStr = "咨询登记";
                                this.activeName = 'first'
                                this.commommodule = res.data.commomform;
                                if('100' == value){
                                    this.commommodule.id = null;
                                    this.commommodule.caseguid = null;
                                    this.commommodule.entname = null;
                                    this.commommodule.processingunit = null;
                                    this.commommodule.region = null;
                                    this.commommodule.address = null;
                                }
                                this.$refs["comform"].timeChange(this.commommodule)
                            } else if (res.data.complaintform != null) {
                                this.formType = "complaintmodule";
                                this.formTypeStr = '消费投诉表单'
                                this.aictabs = "appeal";
                                this.activeName = 'second'
                                this.secondActive = "first";
                                this.complaintmodule = res.data.complaintform;
                                if('100' == value){
                                    this.complaintmodule.id = null;
                                    this.complaintmodule.caseguid = null;
                                    this.complaintmodule.subjectname = null;
                                    this.complaintmodule.dealcompany = null;
                                    this.complaintmodule.region = null;
                                    this.complaintmodule.subjectaddress = null;
                                }
                                this.$refs["complain"].timeChange(this.complaintmodule)

                            } else if (res.data.economyform != null) {
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = '经济违法行为表单'
                                this.activeName = 'second'
                                this.secondActive = "second";
                                this.comeconomicmodule = res.data.economyform;
                                if('100' == value){
                                    this.comeconomicmodule.id = null;
                                    this.comeconomicmodule.caseguid = null;
                                    this.comeconomicmodule.subjectname = null;
                                    this.comeconomicmodule.dealcompany = null;
                                    this.comeconomicmodule.region = null;
                                    this.comeconomicmodule.subjectaddress = null;

                                }
                                this.$refs["economy"].timeChange(this.comeconomicmodule)
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                // this.$router.push("/wddb/bxtdb");
                            }
                            this.opinionList = res.data.opinionList;
                            this.loading = false;
                            this.getMaterial(caseguid);
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                    });
            },
            getMaterial(caseguid) {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid: caseguid
                };
            },
            unitChange(value) {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    orgId: users.orgID
                };
                if (
                    "2" == value &&
                    this.depts != "" &&
                    this.assignmentForm.dept != ""
                ) {
                    this.deptLists = this.depts;
                }else {
                    findOrgDeptByOrgId(para).then(res => {
                        this.deptList = res.data.data;
                        this.depts = res.data.data.filter(dept => {
                            if (this.assignmentForm.dept == dept.orgunitID) {
                                return false;
                            } else {
                                return true;
                            }
                        });
                    });
                }
            },

            orgChange(value) {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    regionId: users.regionID
                };
                findOrgUnitByRegionId(para).then(res => {
                    this.orgList = res.data.data;
                    console.log(this.orgList)
                });
            },

            handleClick(tab, event) {
                this.reject = false;
                this.assignSub = false;
                this.accept = false;
                this.nextOrg = false;
                this.notAccepts = false;
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            handlePreview(file) {
            },
            getNext(value) {
                this.assignSub = value;
                this.activeName = 'third';
            },
            getRejectNum(value) {
                this.reject = value;
                this.activeName = 'fourth'
            },
            handleCurrentChange(currentPage) {
                this.pagination.page = currentPage;
                this.findList();
            },
            handleSizeChange(size) {
                this.pagination.size = size;
                this.findList();
            },
            searchChange() {
                this.findList();
            },
            selsChange: function (sels) {
                this.list = [];
                sels.map(item => {
                    this.list.push(item.caseguid);
                    this.idList.push(item.id)
                });
            },
            //工单类型转换
            formtypes(row, column) {
                if ('02' == this.search.formType) {
                    return "投诉";
                } else if ("03" == this.search.formType) {
                    return "举报";
                } else {
                    return "咨询";
                }
            },
            //时间转换
            formatDate(row, column) {
                if (row.updatedate != null) {
                    return this.$moment(row.updatedate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            },
            findList() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                let params ;
                this.complaintLoading = true;
                if('01' == this.search.formType){
                    params = {
                        page: this.pagination.page,
                        total: this.pagination.total,
                        size: this.pagination.size,
                        rqsttitle: this.search.rqsttitle,
                        entname: this.search.entname,
                    };
                    searchConsultationformList(params).then(res => {
                        this.pagination.page = res.data.current;
                        this.pagination.total = res.data.total;
                        this.pagination.size = res.data.size;
                        this.complaintList = res.data.records;
                        this.complaintLoading = false;
                    });
                }else if('02' == this.search.formType){
                    params = {
                        page: this.pagination.page,
                        total: this.pagination.total,
                        size: this.pagination.size,
                        rqsttitle: this.search.rqsttitle,
                        subjectname: this.search.entname,
                    };
                    searchComplaintformList(params).then(res => {
                        this.pagination.page = res.data.current;
                        this.pagination.total = res.data.total;
                        this.pagination.size = res.data.size;
                        this.complaintList = res.data.records;
                        this.complaintLoading = false;
                    });
                }else {
                    params = {
                        page: this.pagination.page,
                        total: this.pagination.total,
                        size: this.pagination.size,
                        rqsttitle: this.search.rqsttitle,
                        subjectname: this.search.entname,
                    };
                    searchEconomyformList(params).then(res => {
                        this.pagination.page = res.data.current;
                        this.pagination.total = res.data.total;
                        this.pagination.size = res.data.size;
                        this.complaintList = res.data.records;
                        this.complaintLoading = false;
                    });
                }
            },
            resetForm() {
                this.search = {
                    ...this.search,
                    ...{
                        rqsttitle: "",
                        entname:"",
                    }
                };
                this.findList();
            },
            toFind(column, row) {
                let id = row.id;
                let caseguid = row.caseguid;
                this.getBaseInfos(id,caseguid,'100');
                this.dialogCommomform = false;
            },
            getUuid(){
                var s = [];
                var hexDigits = "0123456789abcdef";
                for (var i = 0; i < 36; i++) {
                    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                }
                s[14] = "4";
                s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
                s[8] = s[13] = s[18] = s[23] = "-";
                var uuid = s.join("");
                return uuid;
            },

            //抄送部门级联数据
            getOptionsOfDeptData(){
                let params={}
                getOptionsOfDept(params).then( res =>{

                    // this.unitsoptionsofdept = res;
                    console.log(this.unitsoptionsofdept)
                })
            },

            getAllData(){
                let params={}
                getAllData(params).then( res =>{

                    console.log(res)
                    this.unitsoptionsofdept = res;
                })
            },
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
            this.getAllData();
            this.getBaseInfo();
            this.orgChange();
            this.unitChange();
            this.getOptionsOfDeptData()
            this.commommodule.id = this.$route.query.id;
            this.commommodule.caseguid = this.$route.query.caseguid;
            this.complaintmodule.id = this.$route.query.id;
            this.complaintmodule.caseguid = this.$route.query.caseguid;
            this.comeconomicmodule.id = this.$route.query.id;
            this.comeconomicmodule.caseguid = this.$route.query.caseguid;
            // this.requestMap.caseguid=this.getUuid().toString();
            // this.commommodule.caseguid=this.requestMap.caseguid;

            console.log(this.userList)
            console.log(JSON.parse(localStorage.getItem("user")))

            if(this.$route.query.type === "toChangeEconomy" || this.$route.query.type === "rightFormChangeEconomy"){
                this.activeName = "second"
                this.secondActive = "second"
            }else if(this.$route.query.type === "copyOfComplaint"){
                this.activeName = "second"
                this.secondActive = "first"
            }
            this.SearchMessagePerson();
        }
    };
</script>
<style>

</style>
