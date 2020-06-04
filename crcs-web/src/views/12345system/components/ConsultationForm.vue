<!--咨询登记表单子组件-->
<template>
    <el-tabs type="card" v-model="tabsActiveName">
        <el-tab-pane label="登记信息" name="registration">
            <el-form :model="formData" label-width="150px"
                     ref="commomOne"
                     :rules="formDataRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="表单类型" class="bt">
                            <el-input v-model="formData.formtype" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="表单模板" class="bt">
                            <el-input v-model="formData.formmodule" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="标题" class="bt" prop="rqsttitle">
                            <el-input v-model="formData.rqsttitle" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="姓名" class="bt" prop="name">
                            <el-input v-model="formData.name" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="性别" class="bt" prop="gender">
                            <el-select v-model="formData.gender" placeholder="请选择性别" class="input-text">
                                <el-option label="男" value="1"></el-option>
                                <el-option label="女" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="区域" class="bt">
                            <el-select v-model="formData.region" placeholder="请选择区域" class="input-text">
                                <el-option label="顺德区" value="1"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="是否有效" class="bt">
                            <el-select v-model="formData.isvalid" placeholder="请选择" class="input-text">
                                <el-option label="是" value="1"></el-option>
                                <el-option label="否" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="来电号码" class="bt" prop="rqstphonenumber">
                            <el-input v-model="formData.rqstphonenumber" placeholder="请输入"
                                      class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="联系电话" class="bt">
                            <el-input v-model="formData.linkphonenumber" placeholder="请输入"
                                      class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="反映类型" class="bt">
                            <el-select v-model="formData.usertype" placeholder="请选择反映类型" class="input-text">
                                <el-option label="企业" value="1"></el-option>
                                <el-option label="个人" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="回复方式" class="bt">
                            <el-select v-model="formData.answertype" placeholder="请选择" class="input-text">
                                <el-option label="电话" value="complain"></el-option>
                                <el-option label="网络" value="consult"></el-option>
                                <el-option label="信件" value="enforcement"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="企业类型" class="bt" prop="entitytypecodeid" :key="formData.entitytypecodeid">
                            <el-cascader filterable
                                         :placeholder="entitTypeText?entitTypeText:'请选择企业类型'"
                                         :options="entitytype1"
                                         v-model="formData.entitytypecodeid"
                                         @change="handleChangeentitytype1"
                                         :show-all-levels="false"
                                         class="bt1"
                                         :class="{cascader_placeholder:entitTypeText}"
                            ></el-cascader>
                            <!-- <el-select v-model="formData.entitytype" placeholder="请选择分类" class="input-text">
                               <el-option label="内资企业" value="1"></el-option>
                               <el-option label="外资企业" value="2"></el-option>
                             </el-select>-->
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="企业全称" class="bt" prop="subjectname">
                            <el-input v-model="formData.subjectname" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="企业登记机关" class="bt">
                            <el-input v-model="formData.aicid" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="企业经营地址" class="bt" prop="address">
                            <el-input v-model="formData.address" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.complaintstypecodeid">
                        <el-form-item label="涉及问题分类" prop="complaintstypecodeid">
                                <el-cascader
                                        filterable
                                        :placeholder="problemTypeText?problemTypeText:'请选择涉及问题分类'"
                                        :options="complaintstype1"
                                        v-model="formData.complaintstypecodeid"
                                        @change="handleChangeComplaintstype1"
                                        :show-all-levels="false"
                                        class="input-text"
                                        :class="{cascader_placeholder:problemTypeText}"
                                ></el-cascader>
                            </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="工单分类" class="bt">
                            <el-select v-model="formData.focus" placeholder="请选择分类" class="input-text">
                                <el-option label="普通" value="1"></el-option>
                                <el-option label="特殊" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="问题描述" class="bt" prop="rqstcontent">
                            <el-input
                                    v-model="formData.rqstcontent"
                                    placeholder="请输入"
                                    type="textarea"
                                    :autosize="{ minRows: 3, maxRows: 5}"
                                    class="input-text"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="政务标签" class="bt" prop="govlabel">
                            <el-input v-model="formData.govlabel" placeholder="请输入" class="input-text"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col class="tr"></el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                        <el-form-item label="咨询相关附件" class="bt" style="text-align:left">
                            <el-upload
                                    :auto-upload="false"
                                    :file-list="fileLists"
                                    ref="upload"
                                    :data="this.requestMap"
                                    :on-preview="previewFile"
                                    :on-success="uploadSuccess"
                                    :before-upload="beforeUpload"
                                    :multiple="true"
                                    action="/crcs/filesUpload/upload"
                            >
                                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-tab-pane>
        <!-- <el-tab-pane label="处理信息" name="handle">
             <el-collapse v-model="processInofActiveNames" class="accordion">
                 <el-collapse-item name="1">
                     <div slot="title" @click.stop>
                         <el-row class="el-accordion">
                             <el-col :span="12">处理信息</el-col>
                             <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                         </el-row>
                     </div>
                     <el-form :model="formData" label-width="220px" ref="formData">
                         <el-row>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="登记单编号" class="bt">
                                     <el-input v-model="formData.number" placeholder="请输入" class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="案由" class="bt">
                                     <el-input v-model="formData.cause" placeholder="请输入" class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                         </el-row>
                     </el-form>
                 </el-collapse-item>
                 <el-collapse-item name="2">
                     <div slot="title" @click.stop>
                         <el-row class="el-accordion">
                             <el-col :span="12">涉及主体信息</el-col>
                             <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                         </el-row>
                     </div>
                     <el-form :model="formData" label-width="220px" ref="formData">
                         <el-row>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="是否具有营业执照" class="bt">
                                     <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                         <el-option label="是" value="1"></el-option>
                                         <el-option label="否" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="统一社会信用代码/注册号" class="bt">
                                     <el-input v-model="formData.registration" placeholder="请输入"
                                               class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="所在行业类别" class="bt">
                                     <el-select
                                             v-model="formData.industryCategory"
                                             placeholder="请选择"
                                             class="input-text"
                                     >
                                         <el-option label="IT" value="complain"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="市场名称" class="bt">
                                     <el-input v-model="formData.marketName" placeholder="请输入"
                                               class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="联系方式" class="bt">
                                     <el-input placeholder="请输入" v-model="formData.phone">
                                         <el-select
                                                 slot="prepend"
                                                 placeholder="请选择"
                                                 style="width: 110px;"
                                                 v-model="formData.phoneType"
                                         >
                                             <el-option label="移动电话" value="移动电话"></el-option>
                                             <el-option label="固定电话" value="固定电话"></el-option>
                                         </el-select>
                                     </el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="地址" class="bt">
                                     <el-input placeholder="请输入" v-model="formData.addr">
                                         <el-select
                                                 slot="prepend"
                                                 placeholder="请选择"
                                                 style="width: 110px;"
                                                 v-model="formData.region"
                                         >
                                             <el-option label="顺德区" value="1"></el-option>
                                             <el-option label="禅城区" value="2"></el-option>
                                             <el-option label="南海区" value="3"></el-option>
                                             <el-option label="三水区" value="4"></el-option>
                                             <el-option label="高明区" value="5"></el-option>
                                         </el-select>
                                     </el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="是否转移其他部门" class="bt">
                                     <el-select v-model="formData.department" placeholder="请选择" class="input-text">
                                         <el-option label="消保" value="complain"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="应急处置进展状态" class="bt">
                                     <el-select v-model="formData.progress" placeholder="请选择" class="input-text">
                                         <el-option label value="complain"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="反馈登记人" class="bt">
                                     <el-input v-model="formData.registrar" placeholder="请输入"
                                               class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="协助单位类型" class="bt">
                                     <el-select v-model="formData.unitType" placeholder="请选择" class="input-text">
                                         <el-option label value="complain"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="办案人员" class="bt">
                                     <el-input v-model="formData.registrar" placeholder="请输入"
                                               class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="办案人员号码" class="bt">
                                     <el-input v-model="formData.registrarPhone" placeholder="请输入"
                                               class="input-text"></el-input>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="群体投诉案件" class="bt">
                                     <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                         <el-option label="是" value="1"></el-option>
                                         <el-option label="否" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="涉外投诉案件" class="bt">
                                     <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                         <el-option label="是" value="1"></el-option>
                                         <el-option label="否" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="对投诉中发现违法行为实施行政处理" class="bt">
                                     <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                         <el-option label="是" value="1"></el-option>
                                         <el-option label="否" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="是否转立案" class="bt">
                                     <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                         <el-option label="是" value="1"></el-option>
                                         <el-option label="否" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                         </el-row>
                     </el-form>
                 </el-collapse-item>
                 <el-collapse-item name="5">
                     <div slot="title" @click.stop>
                         <el-row class="el-accordion">
                             <el-col :span="12">分派下级</el-col>
                             <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                         </el-row>
                     </div>
                     <el-form :model="formData" label-width="150px" ref="formData">
                         <el-row>
                             <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                 <el-form-item label="成员单位" class="bt">
                                     <el-select v-model="formData.appear" placeholder="请选择" class="input-text">
                                         <el-option label="消保" value="1"></el-option>
                                         <el-option label="12315" value="2"></el-option>
                                     </el-select>
                                 </el-form-item>
                             </el-col>
                             <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                 <el-form-item label="分派意见" class="bt">
                                     <el-input
                                             v-model="formData.describe"
                                             placeholder="请输入分派意见（500字以内）"
                                             type="textarea"
                                             :autosize="{ minRows: 3, maxRows: 5}"
                                             class="input-text"
                                     ></el-input>
                                 </el-form-item>
                             </el-col>
                         </el-row>
                     </el-form>
                 </el-collapse-item>
             </el-collapse>
         </el-tab-pane>-->
        <!-- <el-dialog :title="fileUploadText" :visible.sync="fileUploadVisible" size="tiny">
          <el-upload
            :auto-upload="false"
            :file-list="fileLists"
            ref="upload"
            :data="this.requestMap"
            :on-progress="progress"
            :on-success="uploadSuccess"
            :before-upload="beforeUpload"
            :multiple="true"
            action="/crcs/filesUpload/upload"
          >
            <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
          </el-upload>
        </el-dialog>-->
    </el-tabs>
</template>

<script>

    import {getMaterialLists, selectBaseCodeTree} from "../../../api/api";
    import {byIdGetLabels, getArrayByStringId, getIdByName} from "../../../utils/form-util";

    export default {
        name: "ConsultationForm",
        props: ["consultationform"],
        computed: {
            formData() {
                return this.consultationform;
            }
        },
        data() {
            return {
                problemTypeText:"",
                complaintstype1:[],
                entitytypecodeid1: [],
                entitytype1: [],//企业类型
                tabsActiveName: "registration",
                fileUploadVisible: false,
                requestMap: {
                    caseguid: ""
                },
                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation"
                ],
                fileLists: [],
                processInofActiveNames: ["1", "2", "3", "4", "5"],
                entitTypeText: null,
                formDataRules: {
                    // rqsttitle: [
                    //     {required: true, message: "请输入标题", trigger: "blur"}
                    // ],
                    // name: [
                    //     {required: true, message: "请输入姓名", trigger: "blur"}
                    // ],
                    // gender: [
                    //     {required: true, message: "请选择性别", trigger: "blur"}
                    // ],
                    // rqstphonenumber: [
                    //     {required: true, message: "请输入来电号码", trigger: "blur"}
                    // ],
                    // entitytypecodeid: [
                    //     {required: true, message: "请选择市场主体类型"}
                    // ],
                    // subjectname: [
                    //     {required: true, message: "请输入企业全称", trigger: "blur"}
                    // ],
                    // address: [
                    //     {required: true, message: "请输入企业经营地址", trigger: "blur"}
                    // ],
                    rqstcontent: [
                        {required: true, message: "请输入问题描述", trigger: "blur"},
                        {max:1200 ,message: "问题描述字数不得超过1200" }
                    ],
                    complaintstypecodeid: [
                        {required: true, message: "请选择涉及问题分类"}
                    ],

                },
            };
        },
        watch: {
            consultationform: function ({caseguid}, _) {
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
                            this.fileLists.push(fileInfo);
                        }
                    }
                });
            }
        },
        methods: {

            handleChangeComplaintstype1(array) {
                let label = this.getLabel(this.complaintstype1, array[array.length - 1]);
                this.formData.complaintstype = byIdGetLabels(
                    label,
                    this.complaintstype1
                ).join("-");
                this.formData.complaintstypecodeid = array;
            },
            //涉及问题分类下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "commom12345"
                };
                selectBaseCodeTree(params).then(data => {
                    console.log(data)
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
                    this.complaintstype1 = this.data; //涉及问题分类
                    console.log(this.complaintstype1)
                    //赋值默认值
                    // if (!this.formData.complaintstypecodeid) {
                    //     //获取原始中文
                    //     this.problemTypeText = this.formData.complaintstype
                    //     //判断是否该中文符合基础数据
                    //     this.formData.complaintstypecodeid = getIdByName(this.formData.complaintstype, this.complaintstype1)
                    // }
                    if (this.formData.complaintstypecodeid) {
                        this.formData.complaintstypecodeid = getArrayByStringId(
                            this.formData.complaintstypecodeid,
                            this.complaintstype1
                        );
                    }else {
                        this.formData.complaintstypecodeid = []
                    }
                    console.log(this.formData.complaintstypecodeid)
                });
            },

            //判断校验
            checkData() {
                console.log("jinru")
                let validation = false;
                this.$refs["commomOne"].validate(
                    valid => (validation = valid)
                );
                console.log(this.formData.complaintstypecodeid)
                console.log(validation)
                if (validation) {
                    // this.$emit('checkdata', true);
                    return true;
                } else {
                    const h = this.$createElement;

                    this.$notify({
                        title: '消息提示',
                        message: h('i', {style: 'color: teal'}, '请检查是否有必填选项未填选!')
                    });
                }

            },

            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url)
                } else {
                    this.download(file.url)
                }
            },
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url, "_blank")
            },
            beforeUpload(file) {
                // console.log(file)
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
            toUploadOpen(type) {
                this.fileUploadVisible = true;
            },
            submitUpload() {
                this.requestMap.caseguid = this.consultationform.caseguid;
                this.$refs.upload.submit();
            },

            handleChangeentitytype1(array) {
                let label = this.getLabel(this.entitytype1, array[array.length - 1])
                this.formData.entitytype = byIdGetLabels(label, this.entitytype1).join("-")
                this.formData.entitytypecodeid = array
            },
            getLabel(children, codeid) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.label
                    }
                    if (child.children) {
                        let label = this.getLabel(child.children, codeid)
                        if (label) {
                            return label
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
            //企业类型下拉框级联数据
            getEntitytype1() {
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
                    this.entitytype1 = this.data; //企业类型
                    //赋值默认值
                    if (!this.formData.entitytypecodeid) {
                        //获取原始中文
                        this.entitTypeText = this.formData.entitytype
                        //判断是否该中文符合基础数据
                        this.formData.entitytypecodeid = getIdByName(this.formData.entitytype, this.entitytype1)
                    }
                    this.selectdata()
                });
            },
            selectdata() {

                if (this.formData.entitytypecodeid != null && this.formData.entitytypecodeid != "") {
                    this.formData.entitytypecodeid = getArrayByStringId(this.formData.entitytypecodeid, this.entitytype1)
                }
            },

        },

        created() {
            this.getEntitytype1()
            this.getComplaintstype1()

        },
        mounted() {
            // console.log("sd")
            const uuidV1 = require("uuid/v1");
            // this.requestMap.uid = uuidV1();
            // console.log(this.consultationform);
        }
    };
</script>

<style lang="scss" scoped>
    .bt {
        font-weight: bold;
        text-align: right;
    }
</style>
