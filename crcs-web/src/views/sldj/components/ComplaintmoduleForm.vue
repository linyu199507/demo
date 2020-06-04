<template>
    <el-collapse v-model="activeCollapse">
                <div>
                    <el-button style="text-align:center; float: right ; height: 43px" type="primary" @click="changeToEconomy">投诉转举报</el-button>
                </div>
        <el-collapse-item title="系统信息" name="1">

            <!--<template slot="title">-->
            <!--<div style="display: flex;align-items: center;justify-content: space-between;width:95%;margin-left: 84px;margin-top:-43px">-->
            <!--<div style="text-align: left">系统信息</div>-->
            <!--<el-button type="primary" style=" ">投诉转举报</el-button>-->
            <!--</div>-->
            <!--</template>-->
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="complaintmodule"
                    ref="complaintmoduleSystem"
                    :rules="complaintmoduleRules">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="业务性质：">
                            <el-input placeholder="消费投诉" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="标题：" prop="rqsttitle">
                            <el-input v-model="complaintmodule.rqsttitle" placeholder="请输入标题"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="途径：" prop="appealsource" >
                            <el-select v-model="complaintmodule.appealsource"
                                       clearable
                                       @change="changeRegionInfo(complaintmodule.appealsource)"
                                       class="input-text"
                                       style="width: 100%" placeholder="请选择">
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
                    <el-col :xs="24" :sm="24" :md="8" :lg="8" v-if="showVisitors == true">
                        <el-form-item label="移送编号：">
                            <el-input placeholder="请输入移送编号" v-model="complaintmodule.transfernumber"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
<!--                    <el-col :xs="24" :sm="24" :md="8" :lg="8" v-if="showVisitors == true">-->
<!--                        <el-form-item label="来件类型：">-->
<!--                            <el-input v-model="complaintmodule.incommingtype" placeholder="请输入来件类型"></el-input>-->
<!--                        </el-form-item>-->
<!--                    </el-col>-->
                    <el-col :xs="24" :sm="24" :md="8" :lg="8" v-if="showVisitors == true">
                        <el-form-item label="地区来源：">
                            <el-input v-model="complaintmodule.detailaddress" placeholder="请输入地区来源"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="所属板块：">
                            <el-select v-model="complaintmodule.subordinateplate" class="input-text"
                                       clearable
                                       style="width: 100%"  placeholder="请输入所属板块">
                                <el-option label="工商" value="工商"></el-option>
                                <el-option label="质监" value="质监"></el-option>
                                <el-option label="食药监" value="食药监"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-form-item label="来函单位：">
                            <el-input v-model="complaintmodule.incomingcommpany" placeholder="请输入来函单位"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row   v-if="complaintmodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="登记机构：">
                            <el-input v-model="complaintmodule.registration" placeholder="请输入登记机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="承办机构：">
                            <el-input v-model="complaintmodule.undertake" placeholder="请输入承办机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="满意度：">
                            <el-select v-model="complaintmodule.satisfied" clearable placeholder="请选择满意度" style="width: 100%" >
                                <el-option label="满意" value="满意"></el-option>
                                <el-option label="不满意" value="不满意"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="提供方信息" name="2">
            <el-form label-width="120px"
                     style="margin-top:20px"
                     :model="complaintmodule"
                     :rules="complaintmoduleRules"
                     ref="complaintmoduleProvider">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="投诉人姓名：" prop="rqstperson">
                            <el-input v-model="complaintmodule.rqstperson" placeholder="请输入投诉人姓名"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="投诉人电话：" prop="rqstnumber">
                            <el-input v-model="complaintmodule.rqstnumber" placeholder="请输入投诉人电话"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="性别：">
                            <el-select v-model="complaintmodule.gender" class="input-text"
                                       clearable
                                       style="width: 100%"  placeholder="请选择">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="是否职投：">
                            <el-select v-model="complaintmodule.isprofessional" class="input-text"
                                       clearable
                                       style="width: 100%"  placeholder="请选择">
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="投诉人地址：" prop="">
                            <el-input placeholder="请输入投诉人地址" v-model="complaintmodule.rqstaddress"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="涉及主体信息" name="3">
            <el-form
                    :model="complaintmodule"
                    :rules="complaintmoduleRules"
                    ref="complaintmoduleSubject"
                    label-width="120px"
                    style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="涉及主体名称：" prop="subjectname">
                            <el-input v-model="complaintmodule.subjectname" placeholder="请输入涉及主体名称"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="联系电话：" prop="linknumber">
                            <el-input v-model="complaintmodule.linknumber" placeholder="请输入联系电话"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="地址：" prop="subjectaddress">
                            <el-select v-model="complaintmodule.region" style="width:150px" clearable placeholder="请选择">
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
                            <el-input v-model="complaintmodule.subjectaddress" style="width:300px"
                                      placeholder="请输入地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="具体信息" name="4">
            <el-form
                    :model="complaintmodule"
                    :rules="complaintmoduleRules"
                    ref="complaintmoduleBusiness"
                    label-width="120px"
                     style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8" :key="complaintmodule.complaintstypecodeid">
                        <el-form-item label="涉及问题类型：" prop="complaintstypecodeid">
                            <el-cascader
                                    clearable
                                    :options="complaintstype1"
                                    :show-all-levels="true"
                                    v-model="complaintmodule.complaintstypecodeid"
                                    @change="handleChangeComplaintstype1"
                                    class="input-text"
                                    style="width: 100%"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8" :key="complaintmodule.servicetypecodeid">
                        <el-form-item label="商品/服务类型：" prop="servicetypecodeid">
                            <el-cascader
                                    clearable
                                    placeholder="请选择商品/服务类型"
                                    :options="servicetype1"
                                    :show-all-levels="true"
                                    v-model="complaintmodule.servicetypecodeid"
                                    @change="handleChangeservicetype1"
                                    class="input-text"
                                    style="width: 100%"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="商品/服务名称：">
                            <el-input placeholder="请输入商品/服务名称" v-model="complaintmodule.objname"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="品牌：">
                            <el-input placeholder="请输入品牌" v-model="complaintmodule.brand"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="领域：">
                            <el-select v-model="complaintmodule.filed" class="input-text"
                                       clearable
                                       style="width: 100%"  placeholder="请选择">
                                <el-option label="生产" value="生产"></el-option>
                                <el-option label="销售" value="销售"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="是否网购：">
                            <el-select v-model="complaintmodule.isweb" class="input-text"
                                       clearable
                                       style="width: 100%"  placeholder="请选择">
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="网购平台：">
                            <el-input placeholder="请输入网购平台" v-model="complaintmodule.shoppingplatform"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="案例接收日期：" prop="acceptdate" :key="complaintmodule.acceptdate">
                            <el-date-picker v-model="complaintmodule.acceptdate" type="date" class="input-text"
                                            style="width: 100%" placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="初步反馈日期：" :key="complaintmodule.feedbackdate" prop="feedbackdate">
                            <el-date-picker v-model="complaintmodule.feedbackdate" type="date"
                                            class="input-text" style="width: 100%" placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>

                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="案件分派日期：" :key="complaintmodule.assignmentdate">
                            <el-date-picker v-model="complaintmodule.assignmentdate" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8" >
                        <el-form-item label="最终反馈日期：" prop="feedbacktime" :key="complaintmodule.feedbacktime">
                            <el-date-picker v-model="complaintmodule.feedbacktime" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="受理通知书号：">
                            <el-input v-model="complaintmodule.acceptancenumber" placeholder="请输入受理通知书号"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="案件转办书号：">
                            <el-input v-model="complaintmodule.transferbooknumber" placeholder="请输入案件转办书号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="关键字：" prop="keyword">
                            <el-input v-model="complaintmodule.keyword" placeholder="请输入关键字"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="问题描述：" prop="problemdescribe">
                            <el-input
                                    type="textarea"
                                    :rows="rows"
                                    v-model="complaintmodule.problemdescribe"
                                    placeholder="请输入问题描述"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-form-item label="附件上传：">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                action="/crcs/filesUpload/upload"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :file-list="fileList"
                                :auto-upload="false"
                                :on-success="uploadSuccess"
                                :data="this.requestMap"
                                :multiple="true"
                                :before-upload="beforeUpload"
                        >
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            <!--<el-button-->
                                    <!--style="margin-left: 10px;"-->
                                    <!--size="small"-->
                                    <!--type="success"-->
                                    <!--@click="submitUpload"-->
                            <!--&gt;确认上传-->
                            <!--</el-button>-->
<!--                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                        </el-upload>
                    </el-form-item>
                </el-row>
                <el-row></el-row>
            </el-form>
        </el-collapse-item>
        <el-row style="text-align:center;margin: 20px 0">
            <el-button v-if="saveChange === false"  type="primary" @click="submitFrom('complaintmodule')">暂存</el-button>
            <el-button v-if="saveChange === true"  type="info" disabled >已暂存</el-button>
            <el-button type="primary" @click="accepts('accepts','complaintmodule')">初步答复</el-button>
            <el-button type="primary" @click="accepts('nextOrg','complaintmodule')">分派下级</el-button>
            <el-button type="primary" @click="accepts('nextUnits','complaintmodule')">分派部门</el-button>
            <el-button type="primary" @click="accepts('collaborative','complaintmodule')">协同处理</el-button>
            <el-button type="primary" @click="accepts('notAccepts','complaintmodule')">不受理</el-button>
            <el-button type="primary" @click="archive('end','complaintmodule')">直接归档</el-button>
            <el-button type="primary" @click="accepts('dialog','complaintmodule')">工单模板</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
            <el-button type="primary" @click="copyOfComplaint">工单复制</el-button>
        </el-row>
    </el-collapse>
</template>
<script>
    import {myMixin} from "../../../assets/mixin/mixin";

    import moment from 'moment';


    import {
        saveBaseInfo,
        next,
        uploadFile,
        getMaterialLists,
        searchBaseInfo,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        selectBaseCodeByType,
        selectBaseCodeTree,
        findUsersByDeptIdAndPermission,
        deleteUploadFile
    } from "../../../api/api";

    import {
        getArrayByStringId,
        byIdGetLabels,
        getIdByName,
        byValueGetLabels
    } from "../../../utils/form-util";

    export default {
        name:"ComplaintmoduleForm",
        props: ["complaint"],
        mixins: [myMixin],
        data() {
            return {
                saveChange:false,
                Acceptdate:null,
                Feedbackdate:null,
                Assignmentdate:null,
                Feedbacktime:null,
                servicetype1:[],
                complaintstype1:[],
                complaintmodule: {
                    id:"",
                    caseguid:"",
                },
                requestMap: {
                    caseguid: ""
                },
                complaintmoduleRules:{
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "change"}
                    ],
                    rqstperson: [
                        {required: true, message: "请输入投诉人姓名", trigger: "change"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    rqstnumber: [
                        {required: true, message: "请输入投诉人电话", trigger: "change"},
                        {max: 15, message: "不能超出15个字符", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入涉及主体名称", trigger: "change"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入联系电话", trigger: "change"},
                        {max: 15, message: "不能超出15个字符", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, validator:(rule, value, callback)=>{
                                if(!value){
                                    return callback(new Error('请输入详细地址'));
                                }
                                if(!this.complaintmodule.region){
                                    return callback(new Error('请选择区域'));
                                }
                                callback();
                            }}
                    ],
                    servicetypecodeid: [
                        {required: true, message: "请选择商品/服务类型"},
                    ],
                    complaintstypecodeid:[
                        {required: true, message: "请选择涉及问题分类"},
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    problemdescribe: [
                        {required: true, message: "请输入问题描述", trigger: "blur"},
                        {max: 500, message: "不能超出500个字符", trigger: "blur"}
                    ],
                    acceptdate:[
                        {required: true, message: "请选择案件接收日期"},
                    ],
                    feedbacktime:[
                        {required: true, message: "请选择最终反馈日期"},
                    ],
                    feedbackdate:[
                        {required: true, message: "请选择初步反馈日期"},
                    ],
                },
                options:[],
                rows: 5,
                fileList: [],
                showSpotCheck: false,
                showVisitors:true,
                complaintForm: {},
                activeCollapse: ["1", "2", "3", "4"]
            };
        },
        methods: {

            copyOfComplaint(){
                console.log(this.complaintmodule)
                localStorage.setItem("copyComplaint", JSON.stringify(this.complaintmodule));
                let params = {
                    type: "copyOfComplaint"
                }
                // this.$router.resolve({
                //     path:'/sldj/zxdj',
                //     query:{
                //         type:"toChangeEconomy"
                //     }
                // })
                let url = this.$router.resolve({
                    path: "/sldj/zxdj",
                    query: params
                });
                window.open(url.href, "_blank");
                return;
            },

            changeToEconomy(){

                localStorage.setItem("dataChange", JSON.stringify(this.complaintmodule));
                let params = {
                    type: "toChangeEconomy"
                }
                // this.$router.resolve({
                //     path:'/sldj/zxdj',
                //     query:{
                //         type:"toChangeEconomy"
                //     }
                // })
                let url = this.$router.resolve({
                    path: "/sldj/zxdj",
                    query: params
                });
                window.open(url.href, "_blank");
                return;
            },


            timeChange(value){
                this.complaintmodule = value
                if(this.complaintmodule.acceptdate != null){
                    console.log(this.complaintmodule.acceptdate)
                    this.complaintmodule.acceptdate = new Date(this.complaintmodule.acceptdate)
                    console.log(this.complaintmodule.acceptdate)

                }
                if(this.complaintmodule.feedbackdate != null){
                    this.complaintmodule.feedbackdate = new Date(this.complaintmodule.feedbackdate)
                }
                if(this.complaintmodule.assignmentdate != null){
                    this.complaintmodule.assignmentdate = new Date(this.complaintmodule.assignmentdate)
                }
                if(this.complaintmodule.feedbacktime != null){
                    this.complaintmodule.feedbacktime = new Date(this.complaintmodule.feedbacktime)
                }
            },

            archive(value,formType){
                this.formType = formType;
                let validation;
                this.$refs["complaintmoduleSystem"].validate(
                    valid => (validation = valid)
                );
                this.$refs["complaintmoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleSubject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleBusiness"].validate(
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
                    complaintmodule:this.complaintmodule
                }
                this.$emit('archive', data);
            },
            //受理按钮
            accepts(value,formType){
                this.formType = formType;
                if('dialog' != value){
                    let validation;
                    this.$refs["complaintmoduleSystem"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["complaintmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleSubject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleBusiness"].validate(
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
                    complaintmodule:this.complaintmodule
                }
                this.$emit('toAccepts', data);

            },

            submitFrom(formType) {
                this.formType = formType;
                let validation = false;
                this.$refs["complaintmoduleSystem"].validate(
                    valid => (validation = valid)
                );
                this.$refs["complaintmoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleSubject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );
                if (validation) {
                    this.save = true;
                    let data ={
                        formType:this.formType,
                        complaintmodule:this.complaintmodule
                    }
                    this.$emit('save', data);
                    this.saveChange=true
                }
            },

            //商品/服务类型下拉框级联数据
            getServicetype1() {
                let params = {
                    type: "object12345"
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
                    this.servicetype1 = this.data;
                    if (
                        this.complaintmodule.servicetypecodeid != null &&
                        this.complaintmodule.servicetypecodeid != ""
                    ) {
                        this.complaintmodule.servicetypecodeid = getArrayByStringId(
                            this.complaintmodule.servicetypecodeid,
                            this.servicetype1
                        );
                    }
                });
            },
            //涉及问题分类下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "question12345"
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
                    //赋值默认值
                    if (!this.complaintmodule.complaintstypecodeid) {
                        //获取原始中文
                        this.complaintsTypeText = this.complaintmodule.complaintstype;
                        //判断是否该中文符合基础数据
                        this.complaintmodule.complaintstypecodeid = getIdByName(
                            this.complaintmodule.complaintstype,
                            this.complaintstype1
                        );
                    }
                    if (this.complaintmodule.complaintstypecodeid) {
                        this.complaintmodule.complaintstypecodeid = getArrayByStringId(
                            this.complaintmodule.complaintstypecodeid,
                            this.complaintstype1
                        );
                    }
                });
            },
            handleChangeservicetype1(array) {
                let label = this.getValue(this.servicetype1, array[array.length - 1]);
                this.complaintmodule.servicetype = byValueGetLabels(
                    label,
                    this.servicetype1
                ).join("-");
                this.complaintmodule.servicetypecodeid = array;
            },
            handleChangeComplaintstype1(array) {
                console.log(this.complaintmodule.complaintstypecodeid)
                let label = this.getValue(this.complaintstype1, array[array.length - 1]);
                console.log(label)
                this.complaintmodule.complaintstype =byValueGetLabels(
                    label,
                    this.complaintstype1
                ).join("-");
                this.complaintmodule.complaintstypecodeid = array;
                console.log(this.complaintmodule.complaintstype1)
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
            getValue(children, codeid) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.value;
                    }
                    if (child.children) {
                        let value = this.getValue(child.children, codeid);
                        if (value) {
                            return value;
                        }
                    }
                }
            },


            handleChangeBusiness(value) {
            },
            submitUpload(value) {
                this.requestMap.caseguid=this.complaint.caseguid;
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
                if (file.response.code === 0) {
                    // this.$message({
                    //     message: "附件上传成功",
                    //     type: "success"
                    // });
                    alert("附件上传成功")
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
            changeRegionInfo(value) {
                if (value === "信访系统") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                    if(value === "来人来访投诉举报"){
                        this.showVisitors = false;
                    }
                }
            },
            //重置
            resetForm() {
                this.complaintmodule = {
                    ...this.complaintmodule,
                    ...{
                        id:"",
                        caseguid:"",
                        rqsttitle:"",
                        appealsource:"",
                        transfernumber:"",
                        incommingtype:"",
                        detailaddress:"",
                        subordinateplate:"",
                        incomingcommpany:"",
                        rqstperson:"",
                        rqstnumber:"",
                        gender:"",
                        isprofessional:"",
                        subjectname:"",
                        linknumber:"",
                        dealcompany:"",
                        region:"",
                        subjectaddress:"",
                        complaintstypecodeid:"",
                        servicetypecodeid:"",
                        objname:"",
                        filed:"",
                        isweb:"",
                        shoppingplatform:"",
                        acceptdate:"",
                        feedbackdate:"",
                        assignmentdate:"",
                        feedbacktime:"",
                        acceptancenumber:"",
                        transferbooknumber:"",
                        keyword:"",
                        problemdescribe:"",
                    }
                };
            },
        },
        mounted() {
            if(this.$route.query.type === "copyOfComplaint"){
                //时间转换
                this.Acceptdate = this.$moment(JSON.parse(localStorage.getItem("copyComplaint")).acceptdate).format('YYYY-MM-DD HH:mm:ss');
                this.Feedbackdate = this.$moment(JSON.parse(localStorage.getItem("copyComplaint")).feedbackdate).format('YYYY-MM-DD HH:mm:ss');
                this.Assignmentdate = this.$moment(JSON.parse(localStorage.getItem("copyComplaint")).assignmentdate).format('YYYY-MM-DD HH:mm:ss');
                this.Feedbacktime = this.$moment(JSON.parse(localStorage.getItem("copyComplaint")).feedbacktime).format('YYYY-MM-DD HH:mm:ss');

                this.complaintmodule.acceptdate = new Date(this.Acceptdate);
                this.complaintmodule.feedbackdate = new Date(this.Feedbackdate);
                this.complaintmodule.assignmentdate = new Date(this.Assignmentdate);
                this.complaintmodule.feedbacktime = new Date(this.Feedbacktime);

                this.complaintmodule = JSON.parse(localStorage.getItem("copyComplaint"))

                // localStorage.clear("data");linyu
                // localStorage.setItem("copyComplaint", JSON.stringify());
            }


            this.getComplaintstype1();
            this.getServicetype1();
    }
    };
</script>
<style lang="scss" scoped>
    .el-date-editor.el-input {
        width: 223px;
    }
    .bt {
        font-weight: bold;
        text-align: left;
    }
</style>
