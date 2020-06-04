<!--咨询登记表单子组件-->
<template>
    <el-collapse v-model="activeCollapse">
        <el-collapse-item title="系统信息" name="1">
            <!--<el-form label-width="120px" style="margin-top: 20px">-->
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="commommodule"
                    ref="commommoduleSystem"
                    :rules="commommoduleRules">
                <el-row class="con">
                    <el-col :span="8">
                        <el-form-item label="业务性质：">
                            <el-input placeholder="咨询" :disabled="true" style="width: 100%"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="标题：" prop="rqsttitle">
                            <el-input v-model="commommodule.rqsttitle" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="途径：" prop="appealsource">
                            <el-select
                                    clearable
                                    v-model="commommodule.appealsource"
                                    @change="changeRegionInfo(commommodule.appealsource)"
                                    placeholder="请选择"
                                    style="width: 100%"
                            >
                                <el-option label="市消委会平台" value="市消委会平台"></el-option>
                                <el-option label="8180平台" value="8180平台"></el-option>
                                <el-option label="顺德消委会网" value="顺德消委会网"></el-option>
                                <el-option label="网络发言人" value="网络发言人"></el-option>
                                <el-option label="广东省消费者协会投诉与咨询信息系统" value="广东省消费者协会投诉与咨询信息系统"></el-option>
                                <el-option label="企业直通车" value="企业直通车"></el-option>
                                <el-option label="食药监系统（12331）" value="食药监系统（12331）"></el-option>
                                <el-option label="红盾云桥平台" value="红盾云桥平台"></el-option>
                                <el-option label="上级交办案件" value="上级交办案件"></el-option>
                                <el-option label="省外移送案件" value="省外移送案件"></el-option>
                                <el-option label="抽查抽检" value="抽查抽检"></el-option>
                                <el-option label="来信投诉举报、职业投诉人来函" value="来信投诉举报、职业投诉人来函"></el-option>
                                <el-option label="来人来访投诉举报" value="来人来访投诉举报"></el-option>
                                <el-option label="纪检监察行政投诉平台" value="纪检监察行政投诉平台"></el-option>
                                <el-option label="接收烟草案件" value="接收烟草案件"></el-option>
                                <el-option label="纪委信访交办件" value="纪委信访交办件"></el-option>
                                <el-option label="信访系统" value="信访系统"></el-option>
                                <el-option label="政府论坛" value="政府论坛"></el-option>
                                <el-option label="维权服务站" value="维权服务站"></el-option>
                                <el-option label="其他" value="其他"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="移送编号：">
                            <el-input v-model="commommodule.transfernumber" placeholder="请输入移送编号" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row   v-if="commommodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="登记机构：">
                            <el-input v-model="commommodule.registration" placeholder="请输入登记机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="承办机构：">
                            <el-input v-model="commommodule.undertake" placeholder="请输入承办机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="满意度：">
                            <el-select v-model="commommodule.satisfied" clearable placeholder="请选择满意度" style="width: 100%" >
                                <el-option label="满意" value="满意"></el-option>
                                <el-option label="不满意" value="不满意"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-form-item label="来函单位：">
                            <el-input v-model="commommodule.incomingcommpany" placeholder="请输入来函单位" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="2" title="提供方信息">
            <el-form label-width="120px"
                     style="margin-top: 20px"
                     :model="commommodule"
                     ref="commommoduleProvider"
                     :rules="commommoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="咨询方：" prop="name">
                            <el-input v-model="commommodule.name" placeholder="请输入咨询方" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="咨询方电话：" prop="rqstphonenumber">
                            <el-input v-model="commommodule.rqstphonenumber" placeholder="请输入咨询方电话" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="性别：">
                            <el-select v-model="commommodule.gender" clearable placeholder="请选择" style="width: 100%" >
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="是否职投：">
                            <el-select v-model="commommodule.isprofessional" clearable placeholder="请选择" style="width: 100%" >
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="咨询方地址：" prop="consultantaddress">
                            <el-input
                                    style="width: 100%"
                                    placeholder="请输入咨询方地址"
                                    v-model="commommodule.consultantaddress"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="3" title="涉及主体信息">
            <el-form
                    style="margin-top: 20px"
                    label-width="120px"
                    :model="commommodule"
                    ref="commommoduleSubject"
                    :rules="commommoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="涉及主体名称：" prop="entname">
                            <el-input v-model="commommodule.entname" placeholder="请入涉及主体名称" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="联系电话：" prop="linkphonenumber">
                            <el-input v-model="commommodule.linkphonenumber" placeholder="请输入联系电话" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="地址：" prop="address">
                            <el-select v-model="commommodule.region" clearable style="width:150px" placeholder="请选择">
                                <el-option label="乐从" value="乐从"></el-option>
                                <el-option label="陈村" value="陈村"></el-option>
                                <el-option label="勒流" value="勒流"></el-option>
                                <el-option label="大良" value="大良"></el-option>
                                <el-option label="杏坛" value="杏坛"></el-option>
                                <el-option label="均安" value="均安"></el-option>
                                <el-option label="容桂" value="容桂"></el-option>
                                <el-option label="龙江" value="龙江"></el-option>
                                <el-option label="伦教" value="伦教"></el-option>
                                <el-option label="北滘" value="北滘"></el-option>
                                <el-option label="其他" value="其他"></el-option>
                            </el-select>
                            <el-input v-model="commommodule.address" style="width:300px"
                                      placeholder="请输入详细地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="4" title="具体信息">
            <el-form
                    style="margin-top: 20px"
                    label-width="120px"
                    :model="commommodule"
                    ref="commommoduleBusiness"
                    :rules="commommoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" :key="commommodule.complaintstypecodeid">
                        <el-form-item label="分类：" prop="complaintstypecodeid">
                            <!--<el-select v-model="commommodule.complaintstypecodeid" clearable placeholder="请选择分类" style="width: 100%" >-->
                                <!--<el-option label="基础数据1" value="1"></el-option>-->
                                <!--<el-option label="基础数据2" value="2"></el-option>-->
                            <!--</el-select>-->
                            <el-cascader
                                    clearable

                                    :options="complaintstype1"
                                    v-model="commommodule.complaintstypecodeid"
                                    @change="handleChangeComplaintstype1"
                                    :show-all-levels="true"
                                    class="input-text"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="关键字：" prop="keyword">
                            <el-input v-model="commommodule.keyword" placeholder="请输入关键字" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="案件接收日期：" prop="acceptdate">
                            <el-date-picker v-model="commommodule.acceptdate"
                                            type="date"
                                            class="input-text"
                                            value-format="yyyy-MM-dd"
                                            style="width: 100%"
                                            placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="最终反馈日期：" prop="finalfeedbackdate">
                            <el-date-picker v-model="commommodule.finalfeedbackdate" type="date"
                                            style="width: 100%"
                                            class="input-text" placeholder="选择日期"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="问题描述：" prop="rqstcontent">
                            <el-input
                                    type="textarea"
                                    style="width:90%"
                                    :rows="rows"
                                    v-model="commommodule.rqstcontent"
                                    placeholder="请输入关问题描述"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                            <el-form-item label="咨询相关附件:" class="bt">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="commommodulefile"
                                        ref="upload"
                                        :on-preview="previewFile"
                                        :data="this.requestMap"
                                        :on-progress="progress"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                        class="upload-demo"
                                        :on-change="onchange"
                                        :on-remove="handleRemove"
                                >
                                    <!--<div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>-->
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <!--<el-button-->
                                            <!--size="small"-->
                                            <!--type="success"-->
                                            <!--@click="submitUploadCommon('upload')"-->
                                            <!--disabled="true"-->
                                    <!--&gt;确认上传-->
                                    <!--</el-button>-->
                                </el-upload>
                            </el-form-item>
                        </el-col>
                    </el-row>
            </el-form>
        </el-collapse-item>
        <el-row style="text-align:center;margin: 20px 0">
            <el-button v-if="saveChange === false" type="primary" @click="submitFrom('commommodule')">暂存</el-button>
            <el-button v-if="saveChange === true" type="info" disabled @click="haveChange" >已暂存</el-button>
            <el-button type="primary" @click="accepts('accepts','commommodule')">初步答复</el-button>
            <el-button type="primary" @click="accepts('nextOrg','commommodule')">分派下级</el-button>
            <el-button type="primary" @click="accepts('nextUnits','commommodule')">分派部门</el-button>
            <el-button type="primary" @click="accepts('collaborative','commommodule')">协同处理</el-button>
            <el-button type="primary" @click="accepts('notAccepts','commommodule')">不受理</el-button>
            <el-button type="primary" @click="archive('end','commommodule')">直接归档</el-button>
            <el-button type="primary" @click="accepts('dialog','commommodule')">工单模板</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
        </el-row>
    </el-collapse>
</template>
<script>
    import {
    getMaterialLists,deleteUploadFile,selectBaseCodeTree
    } from "../../../api/api";
    import {byIdGetLabels, getArrayByStringId, getIdByName} from "../../../utils/form-util";

    export default {
        // mixins: [myMixin],
        name:"Commommodule",
        props: ["commom"],
        computed:{

        },
        watch: {
            // 如果 `question` 发生改变，这个函数就会运行
            "commommodule.acceptdate": function (newQuestion, oldQuestion) {
                this.change()
            },
            "commommodule.finalfeedbackdate": function (newQuestion, oldQuestion) {
                this.change()
            },
            // commomwatch: function ({caseguid}, _) {
            //     let params = {
            //         tserialnum: "",
            //         rqsttitle: "",
            //         begindate: "",
            //         enddate: "",
            //         size: 30,
            //         page: 1,
            //         caseguid: caseguid
            //     };
            //     getMaterialLists(params).then(res => {
            //         console.log(res)
            //         if (res.code === 0) {
            //             let fileInfo;
            //             for (let i in res.data.records) {
            //                 fileInfo = {
            //                     name: res.data.records[i].attachfilename,
            //                     url: res.data.records[i].filesavepath
            //                 };
            //                 this.commommodulefile.push(fileInfo);
            //                 //this.fileList.push(fileInfo)
            //             }
            //         }
            //     });
            // }
        },

        data() {
            return {
                saveChange:false,
                imageUrl:"",
                problemTypeText:"",
                options:[],
                complaintstype1:[],
                commommodule: {
                    id:"",
                    caseguid:"",
                },
                commommoduleRules:{
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "change"}
                    ],
                    name: [
                        {required: true, message: "请输入名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    rqstphonenumber: [
                        {required: true, message: "请输入咨询方电话", trigger: "blur"},
                        {max: 15, message: "不能超出15个字符", trigger: "blur"}
                    ],
                    linkphonenumber: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 15, message: "不能超出15个字符", trigger: "blur"}
                    ],
                    consultantaddress: [
                        {required: true, message: "请输入咨询方地址", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    entname: [
                        {required: true, message: "请入涉及主体名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 15, message: "不能超出15个字符", trigger: "blur"}
                    ],
                    address: [
                        {required: true, validator:(rule, value, callback)=>{
                                if(!value){
                                    return callback(new Error('请输入详细地址'));
                                }
                                    if(!this.commommodule.region){
                                        return callback(new Error('请选择区域'));
                                }
                                callback();
                            }}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    rqstcontent: [
                        {required: true, message: "请输入问题描述", trigger: "blur"},
                        {max: 500, message: "不能超出500个字符", trigger: "blur"}
                    ],
                    acceptdate:[
                        {required: true, message: "请选择案件接收日期"},
                    ],
                    finalfeedbackdate:[
                        {required: true, message: "请选择最终反馈日期"},
                    ],
                },
                aictabs: "commommodule",
                accept:false,
                notAccepts:false,
                nextOrg:false,
                formType:"",
                save: false,
                rows: 5,
                fileList: [],
                showSpotCheck: false,
                activeCollapse: ["1", "2", "3", "4"],
                dialogCommomform:false,
                commommodulefile:[],
                fileListsAccept:[],
                requestMap: {
                     caseguid: ""
                },
            };
        },
        watch: {

            commommodule: function ({complaintstypecodeid},_) {
                // console.log("asdasdsa")
                this.commommodule.complaintstype = this.commommodule.complaintstype.split("-")
                this.commommodule.complaintstype = this.commommodule.complaintstype[this.commommodule.complaintstype.length-1].toString()
                //判断是否该中文符合基础数据
                this.commommodule.complaintstypecodeid = getArrayByStringId(
                    this.commommodule.complaintstypecodeid,
                    this.complaintstype1
                );
                // console.log(this.commommodule.complaintstypecodeid)
            },

            complaintstype1: function (val, oldVal) {


            },
        },
        methods: {

            timeChange(value){
                this.commommodule = value
                if(this.commommodule.acceptdate != null){
                    this.commommodule.acceptdate = new Date(this.commommodule.acceptdate)
                }
                if(this.commommodule.finalfeedbackdate != null){
                    this.commommodule.finalfeedbackdate = new Date(this.commommodule.finalfeedbackdate)
                }
            },

            change(newQuestion,oldQuestion){},

            archive(value,formType){
                this.formType = formType;
                let validation;
                this.$refs["commommoduleSystem"].validate(
                    valid => (validation = valid)
                );
                this.$refs["commommoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["commommoduleSubject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["commommoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) {
                    document.getElementById("fromExamine").scrollTop = 0;
                    return;
                }
                this.aictabs = "2";
                let data ={
                    parameter:value,
                    aictabs:this.aictabs,
                    formType:this.formType,
                    commommodule:this.commommodule
                }
                this.$emit('archive', data);
            },

            accepts(value,formType){
                this.formType = formType;
                if('dialog' != value){
                    let validation;
                    this.$refs["commommoduleSystem"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["commommoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["commommoduleSubject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["commommoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                    if (!validation) {
                        document.getElementById("fromExamine").scrollTop = 0;
                        return;
                    }
                    this.aictabs = "2";
                }
                let data ={
                    parameter:value,
                    aictabs:this.aictabs,
                    formType:this.formType,
                    commommodule:this.commommodule,
                }
                this.$emit('toAccepts', data);
            },

            haveChange(){
                console.log("123213")
                window.open(this.$router.push({ path: "/wddb/bxtdb", query: params }))
            },

            //暂存
            submitFrom(formType) {

                this.formType = formType;
                let validation = false;
                this.$refs["commommoduleSystem"].validate(
                    valid => (validation = valid)
                );
                this.$refs["commommoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["commommoduleSubject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["commommoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );

                if (validation) {
                    this.save = true;
                    let data ={
                        formType:this.formType,
                        commommodule:this.commommodule
                    }
                    this.$emit('save', data);
                    this.saveChange=true
                }
            },
            changeRegionInfo(value) {
                if (value === "信访系统") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                }
            },
            //重置
            resetForm() {
                this.commommodule = {
                    ...this.commommodule,
                    ...{
                        id:"",
                        caseguid:"",
                        rqsttitle:"",
                        appealsource:"",
                        transfernumber:"",
                        incomingcommpany:"",
                        name:"",
                        rqstnumber:"",
                        gender:"",
                        isprofessional:"",
                        consultantaddress:"",
                        entname:"",
                        linknumber:"",
                        processingunit:"",
                        region:"",
                        address:"",
                        keyword:"",
                        acceptdate:"",
                        finalfeedbackdate:"",
                        rqstcontent:"",
                    }
                };
            },
            submitUploadCommon(value){
                 // this.requestMap.caseguid = this.commommodule.caseguid;
                //this.requestMap.caseguid = this.getUuid().toString()
                this.requestMap.caseguid=this.commom.caseguid;
                // console.log(this.commom.caseguid);
                this.$refs.upload.submit();
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            download(url){
                window.open(`${process.env.Download_URL}` + url);
            },
            uploadSuccess(response, file, fileList) {
                this.imageUrl = URL.createObjectURL(file.raw);
                if (file.response.code === 0) {
                    // this.$message({
                    //     message: "附件上传成功",
                    //     type: "success"
                    // });
                    //alert("附件上传成功")
                    const h = this.$createElement;
                    this.$notify({
                        title: '消息提示',
                        message: h('i', { style: 'color: teal'}, '附件上传成功')
                    });
                } else {
                    this.$message.error("附件上传失败" + file.response.msg);
                }
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
                    ".wps",
                    ".mp3"
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
            onchange(file,fileList){

            },
            handleRemove(file, fileList) {
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            progress(event, file, fileList) {
            },

            handleChangeComplaintstype1(array) {
                let label = this.getLabel(this.complaintstype1, array[array.length - 1]);
                this.commommodule.complaintstype = byIdGetLabels(
                    label,
                    this.complaintstype1
                ).join("-");
                this.commommodule.complaintstypecodeid = array;
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
            //涉及问题分类下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "commom12345"
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
                    this.complaintstype1 = this.data; //涉及问题分类
                    console.log(this.complaintstype1)
                    console.log(this.commommodule)
                    if (this.commommodule.complaintstypecodeid) {
                        this.commommodule.complaintstypecodeid = getArrayByStringId(
                            this.commommodule.complaintstypecodeid,
                            this.complaintstype1
                        );
                    }
                });
            },


            getfiledata(){
                // console.log("进来了")
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    //caseguid: this.commommodule.caseguid
                    caseguid:this.commom.caseguid
                };
                getMaterialLists(params).then(res => {
                    // console.log(res)
                    if (res.code === 0) {
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.commommodulefile.push(fileInfo);
                        }
                    }
                });
            }

        },
        mounted(){
            this.requestMap.caseguid=this.$route.query.caseguid;
            // console.log(this.$route.query.caseguid)
            this.getfiledata();
            this.getComplaintstype1();
            // console.log(this.commom)
            console.log(this.commom)

            // console.log(this.requestMap)
        }

    };
</script>
<style lang="scss" scoped>
    .el-input {
        width: 224px;
    }

    .el-tabs .el-form .el-row {
        margin: 0 35px;
    }
</style>
<style lang="scss">
    .search_tool {
        padding: 10px 15px 0 0;
        background-color: #f2f2f2;
        border-radius: 5px;
        .el-form-item {
            margin-bottom: 10px;
        }
        .el-form-item__content {
            line-height: normal;
        }
    }
    .el-form-item__label {
        font-size: 13px;
        font-weight: 600;
    }
</style>


