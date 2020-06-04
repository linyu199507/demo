<template>
    <el-collapse v-model="activeCollapse">
        <!--<div>{{comeconomicmodule}}</div>-->
        <el-collapse-item title="系统信息" name="1">
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="comeconomicmodule"
                     ref="comeconomicmoduleSubject"
                     :rules="comeconomicmoduleRules">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="业务性质:">
                            <el-input placeholder="经济违法举报" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="标题：" prop="rqsttitle">
                            <el-input placeholder="请输入标题" v-model="comeconomicmodule.rqsttitle"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="途径：" prop="appealsource">
                            <el-select v-model="comeconomicmodule.appealsource"
                                       clearable
                                       @change="changeRegionInfo(comeconomicmodule.appealsource)"
                                       class="input-text" style="width: 100%" >
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
                            <el-input placeholder="请输入移送编号" v-model="comeconomicmodule.transfernumber"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
<!--                    <el-col :xs="24" :sm="24" :md="16" :lg="8">-->
<!--                        <el-form-item label="来件类型：">-->
<!--                            <el-input v-model="comeconomicmodule.incommingtype" placeholder="请输入来件类型"></el-input>-->
<!--                        </el-form-item>-->
<!--                    </el-col>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="8">
                        <el-form-item label="地区来源：">
                            <el-input placeholder="请输入地点" v-model="comeconomicmodule.detailaddress"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="所属板块：">
                            <el-select v-model="comeconomicmodule.subordinateplate" class="input-text" style="width: 100%"
                                       clearable
                                       placeholder="请输入所属板块">
                                <el-option label="工商" value="工商"></el-option>
                                <el-option label="质监" value="质监"></el-option>
                                <el-option label="食药监" value="食药监"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row   v-if="comeconomicmodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="登记机构：">
                            <el-input v-model="comeconomicmodule.registration" placeholder="请输入登记机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="承办机构：">
                            <el-input v-model="comeconomicmodule.undertake" placeholder="请输入承办机构" style="width: 100%" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="满意度：">
                            <el-select v-model="comeconomicmodule.satisfied"
                                       clearable
                                       placeholder="请选择满意度" style="width: 100%" >
                                <el-option label="满意" value="满意"></el-option>
                                <el-option label="不满意" value="不满意"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row v-if="showSpotCheck">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="移送部门：">
                            <el-cascader
                                    clearable
                                    :options="transferDeptOptions" v-model="comeconomicmodule.transdepartment"
                                    class="input-text" style="width: 100%" @change="handleChange"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                        <el-form-item label="抽查单位：">
                            <el-select  clearable v-model="comeconomicmodule.checkunit">
                                <el-option label="国抽" value="国抽"></el-option>
                                <el-option label="省抽" value="省抽"></el-option>
                                <el-option label="市抽" value="市抽"></el-option>
                            </el-select>
                            <el-input v-model="comeconomicmodule.othersunit" placeholder="请输入其他抽检单位"
                                      style="width:335px"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="showSpotCheck">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="程度：">
                            <el-select v-model="comeconomicmodule.severity"
                                       clearable
                                       class="input-text" style="width: 100%" >
                                <el-option value="严重不合格" label="严重不合格"></el-option>
                                <el-option value="较严重" label="较严重"></el-option>
                                <el-option value="不合格" label="不合格"></el-option>
                                <el-option value="轻微不合格" label="轻微不合格"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="产品类别：">
                            <el-select v-model="comeconomicmodule.productcategory" clearable>
                                <el-option value="食药" label="食药"></el-option>
                                <el-option value="质监" label="质监"></el-option>
                            </el-select>
                            <el-input
                                    placeholder="请输入产品类别"
                                    v-model="comeconomicmodule.otherproductcategory"
                                    style="width:335px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>

                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-form-item label="来函单位：">
                            <el-input v-model="comeconomicmodule.incomingcommpany" placeholder="请输入来函单位"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="提供方信息" name="2" v-if="showSpotCheck === false">
            <el-form label-width="120px"
                     style="margin-top: 20px"
                     :model="comeconomicmodule"
                     ref="comeconomicmoduleProvider"
                     :rules="comeconomicmoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="举报人姓名：" prop="rqstperson">
                            <el-input placeholder="请输入举报人姓名" v-model="comeconomicmodule.rqstperson"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="举报人电话：" prop="rqstnumber">
                            <el-input placeholder="请输入举报人电话" v-model="comeconomicmodule.rqstnumber"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="性别：">
                            <el-select v-model="comeconomicmodule.gender" clearable style="width: 100%">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="是否职投：">
                            <el-select placeholder="请选择" clearable v-model="comeconomicmodule.isprofessional" style="width: 100%">
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="举报人地址：" prop="">
                            <el-input placeholder="请输入举报人地址" v-model="comeconomicmodule.rqstaddress"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="涉及主体信息" name="3">
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="comeconomicmodule"
                    ref="comeconomicmoduleObject"
                    :rules="comeconomicmoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="涉及主体名称：" prop="subjectname">
                            <el-input placeholder="请输入举报人姓名" v-model="comeconomicmodule.subjectname"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="联系电话：" prop="linknumber">
                            <el-input placeholder="请输入举报人电话" v-model="comeconomicmodule.linknumber"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="地址：" prop="subjectaddress">
                            <el-select v-model="comeconomicmodule.region" style="width:150px" clearable>
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
                            <el-input placeholder="请输入地址" style="width:300px"
                                      v-model="comeconomicmodule.subjectaddress"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="具体信息" name="4">
            <el-form label-width="120px"
                     style="margin-top:20px"
                     :model="comeconomicmodule"
                     ref="comeconomicmoduleBusiness"
                     :rules="comeconomicmoduleRules">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" :key="comeconomicmodule.reportprovidelbcodeid">
                        <el-form-item label="涉及问题类型：" prop="reportprovidelbcodeid">
                            <el-cascader
                                    clearable
                                    :options="reportprovidelb1"
                                    :show-all-levels="true"
                                    v-model="comeconomicmodule.reportprovidelbcodeid"
                                    class="input-text" style="width: 100%"
                                    @change="handleChangereportprovidelb1"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="16" :lg="8" :key="comeconomicmodule.objectcategorycodeid">
                        <el-form-item label="商品/服务类型：" prop="objectcategorycodeid">
                            <el-cascader
                                    clearable
                                    :options="objectcategory1"
                                    :show-all-levels="true"
                                    v-model="comeconomicmodule.objectcategorycodeid"
                                    class="input-text" style="width: 100%"
                                    @change="handleChangeobjectcategory1"
                            ></el-cascader>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="商品/服务名称：">
                            <el-input placeholder="请输入商品/服务名称" v-model="comeconomicmodule.objname"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>




                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="品牌：">
                            <el-input placeholder="请输入品牌" v-model="comeconomicmodule.brand"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="是否网购：">
                            <el-select v-model="comeconomicmodule.isweb" clearable placeholder="请选择" style="width: 100%">
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="网购平台：">
                            <el-input placeholder="请输入网购平台" v-model="comeconomicmodule.shoppingplatform"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="领域：">
                            <el-select v-model="comeconomicmodule.filed" clearable class="input-text" style="width: 100%"  placeholder="请选择">
                                <el-option label="生产" value="生产"></el-option>
                                <el-option label="销售" value="销售"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="受理通知书号：">
                            <el-input v-model="comeconomicmodule.acceptancenumber" placeholder="请输入受理通知书号"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" >
                        <el-form-item label="案件转办书号：">
                            <el-input v-model="comeconomicmodule.transferbooknumber" placeholder="请输入案件转办书号"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="案例接收日期：" prop="acceptdate">
                            <el-date-picker v-model="comeconomicmodule.acceptdate" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="初步反馈日期：" prop="feedbackdate">
                            <el-date-picker v-model="comeconomicmodule.feedbackdate" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="案件分派日期：">
                            <el-date-picker v-model="comeconomicmodule.assignmentdate" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="应立案日期：" prop="filingcasesdate">
                            <el-date-picker v-model="comeconomicmodule.filingcasesdate" type="date"
                                            class="input-text" style="width: 100%"  placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-form-item label="最终反馈日期：" prop="finalfeedbackdate">
                            <el-date-picker
                                    v-model="comeconomicmodule.finalfeedbackdate" type="date"
                                    class="input-text" style="width: 100%"  placeholder="选择日期"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                <el-col :xs="24" :sm="24" :md="16" :lg="8">
                    <el-form-item label="关键字：" prop="keyword">
                        <el-input v-model="comeconomicmodule.keyword" placeholder="请输入关键字"></el-input>
                    </el-form-item>
                </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-form-item label="问题描述：" prop="problemdescribe">
                            <el-input
                                    type="textarea"
                                    :rows="rows"
                                    v-model="comeconomicmodule.problemdescribe"
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
            </el-form>
        </el-collapse-item>
        <el-row style="text-align:center;margin: 20px 0">
            <el-button v-if="saveChange === false" type="primary" @click="submitFrom('comeconomicmodule')">暂存</el-button>
            <el-button v-if="saveChange === true" type="primary" disabled="">已暂存</el-button>
            <el-button type="primary" @click="accepts('accepts','comeconomicmodule')">初步答复</el-button>
            <el-button type="primary" @click="accepts('nextOrg','comeconomicmodule')">分派下级</el-button>
            <el-button type="primary" @click="accepts('nextUnits','comeconomicmodule')">分派部门</el-button>
            <el-button type="primary" @click="accepts('collaborative','comeconomicmodule')">协同处理</el-button>
            <el-button type="primary" @click="accepts('notAccepts','comeconomicmodule')">不受理</el-button>
            <el-button type="primary" @click="archive('end','comeconomicmodule')">直接归档</el-button>
            <el-button type="primary" @click="accepts('dialog','comeconomicmodule')">工单模板</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
        </el-row>
    </el-collapse>
</template>
<script>
    import {myMixin} from "../../../assets/mixin/mixin";

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

        name:"Economyform",
        props: ["comeconomic"],

        mixins: [myMixin],
        data() {
            return {
                saveChange:false,
                objectcategory1: [],
                reportprovidelb1:[],
                options:[],
                rightForm:{},
                comeconomicmodule: {
                    id:"",
                    caseguid:"",
                },
                activeCollapse: ["1", "2", "3", "4"],
                rows: 5,
                num: 9,
                showSpotCheck: false,
                showVisitors:true,
                requestMap: {
                    caseguid: ""
                },

                transferDeptOptions: [
                    {
                        value: "上级交办",
                        label: "上级交办",
                        children: [
                            {value: "省局", label: "省局"},
                            {value: "市局", label: "市局"}
                        ]
                    },
                    {
                        value: "其他部门",
                        label: "其他部门"
                    }
                ],
                fileList: [],
                comeconomicmoduleRules:{
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
                                if(!this.comeconomicmodule.region){
                                    return callback(new Error('请选择区域'));
                                }
                                callback();
                            }}
                    ],
                    objectcategorycodeid: [
                        {required: true, message: "请选择商品/服务类型"},
                    ],
                    reportprovidelbcodeid:[
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
                    acceptdate: [
                        {required: true, message: "请案件接收日期"},
                    ],
                    filingcasesdate: [
                        {required: true, message: "请应立案日期"},
                    ],
                    finalfeedbackdate: [
                        {required: true, message: "请选择最终反馈日期"},
                    ],
                    feedbackdate: [
                        {required: true, message: "请选择初步反馈日期"},
                    ],
                },
            };
        },
        methods: {

            timeChange(value){
                this.comeconomicmodule = value
                if(this.comeconomicmodule.acceptdate != null){
                    this.comeconomicmodule.acceptdate = new Date(this.comeconomicmodule.acceptdate)
                }
                if(this.comeconomicmodule.feedbackdate != null){
                    this.comeconomicmodule.feedbackdate = new Date(this.comeconomicmodule.feedbackdate)
                }
                if(this.comeconomicmodule.assignmentdate != null){
                    this.comeconomicmodule.assignmentdate = new Date(this.comeconomicmodule.assignmentdate)
                }
                if(this.comeconomicmodule.filingcasesdate != null){
                    this.comeconomicmodule.filingcasesdate = new Date(this.comeconomicmodule.filingcasesdate)
                }
                if(this.comeconomicmodule.finalfeedbackdate != null){
                    this.comeconomicmodule.finalfeedbackdate = new Date(this.comeconomicmodule.finalfeedbackdate)
                }
            },

            archive(value,formType){
                this.formType = formType;
                let validation;
                this.$refs["comeconomicmoduleSubject"].validate(
                    valid => (validation = valid)
                );
                if(this.comeconomicmodule.appealsource != '11'){
                    this.$refs["comeconomicmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                }
                this.$refs["comeconomicmoduleObject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["comeconomicmoduleBusiness"].validate(
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
                    comeconomicmodule:this.comeconomicmodule
                }
                this.$emit('archive', data);
            },
            //受理按钮
            accepts(value,formType){
                console.log("uuiuii")
                this.formType = formType;
                if('dialog' != value){
                    let validation;
                    this.$refs["comeconomicmoduleSubject"].validate(
                        valid => (validation = valid)
                    );
                    if(this.comeconomicmodule.appealsource != '11'){
                        this.$refs["comeconomicmoduleProvider"].validate(
                            valid => (validation = valid && validation)
                        );
                    }
                    this.$refs["comeconomicmoduleObject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["comeconomicmoduleBusiness"].validate(
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
                    comeconomicmodule:this.comeconomicmodule
                }
                this.$emit('toAccepts', data);

            },

            submitFrom(formType) {
                this.formType = formType;
                let validation = false;
                this.$refs["comeconomicmoduleSubject"].validate(
                    valid => (validation = valid)
                );
                if(this.comeconomicmodule.appealsource != '11'){//抽查抽检
                    this.$refs["comeconomicmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                }
                this.$refs["comeconomicmoduleObject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["comeconomicmoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );
                if (validation) {
                    this.save = true;
                    let data ={
                        formType:this.formType,
                        comeconomicmodule:this.comeconomicmodule
                    }
                    this.$emit('save', data);
                    this.saveChange=true
                }


            },
            //商品/服务类型下拉框级联数据
            getObjectcategory1() {
                let params = {
                    type: "object12345"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].comment,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.objectcategory1 = this.data; //客体类别
                    if (
                        this.comeconomicmodule.objectcategorycodeid != null &&
                        this.comeconomicmodule.objectcategorycodeid != ""
                    ) {
                        this.comeconomicmodule.objectcategorycodeid = getArrayByStringId(
                            this.comeconomicmodule.objectcategorycodeid,
                            this.objectcategory1
                        );
                    }
                });
            },
            //投诉问题类型下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "question12345"
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
                    this.reportprovidelb1 = this.data; //举报问题类别
                    if (
                        this.comeconomicmodule.reportprovidelbcodeid != null &&
                        this.comeconomicmodule.reportprovidelbcodeid != ""
                    ) {
                        this.comeconomicmodule.reportprovidelbcodeid = getArrayByStringId(
                            this.comeconomicmodule.reportprovidelbcodeid,
                            this.reportprovidelb1
                        );
                    }
                });
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
            handleChangeobjectcategory1(array) {
                let label = this.getValue(this.objectcategory1, array[array.length - 1]);
                this.comeconomicmodule.objectcategory = byValueGetLabels(
                    label,
                    this.objectcategory1
                ).join("-");
                this.comeconomicmodule.objectcategorycodeid = array;
            },
            handleChangereportprovidelb1(array) {
                let label = this.getValue(this.reportprovidelb1, array[array.length - 1]);
                this.comeconomicmodule.reportprovidelb = byValueGetLabels(
                    label,
                    this.reportprovidelb1
                ).join("-");
                this.comeconomicmodule.reportprovidelbcodeid = array;
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
            handleChange(value) {
            },
            getChannelInfo(value) {
                if (value === "11") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                }
            },
            submitUpload() {
                this.requestMap.caseguid=this.comeconomic.caseguid;
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
                if (value === "抽查抽检") {//抽检
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                    if(value === "来人来访投诉举报"){//来人来访投诉举报
                        this.showVisitors = false;
                    }else {
                        this.showVisitors = true;
                    }
                }
            },
            //重置
            resetForm() {
                this.comeconomicmodule = {
                    ...this.comeconomicmodule,
                    ...{
                        id:"",
                        caseguid:"",
                        rqsttitle:"",
                        appealsource:"",
                        transfernumber:"",
                        incommingtype:"",
                        detailaddress:"",
                        subordinateplate:"",
                        transdepartment:"",
                        checkunit:"",
                        othersunit:"",
                        severity:"",
                        productcategory:"",
                        otherproductcategory:"",
                        filed:"",
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
                        reportprovidelbcodeid:"",
                        objectcategorycodeid:"",
                        objname:"",
                        acceptdate:"",
                        feedbackdate:"",
                        assignmentdate:"",
                        filingcasesdate:"",
                        finalfeedbackdate:"",
                        acceptancenumber:"",
                        transferbooknumber:"",
                        keyword:"",
                        isweb:"",
                        shoppingplatform:"",
                        problemdescribe:"",
                    }
                };
            },
        },
        mounted() {
            console.log(this.$route.query.type)
            if(this.$route.query.type === "toChangeEconomy"){
                this.comeconomicmodule = JSON.parse(localStorage.getItem("dataChange"))
                this.comeconomicmodule.reportprovidelbcodeid = this.comeconomicmodule.complaintstypecodeid
                this.comeconomicmodule.objectcategorycodeid = this.comeconomicmodule.servicetypecodeid
                this.comeconomicmodule.reportprovidelb = this.comeconomicmodule.complaintstype
                this.comeconomicmodule.objectcategory = this.comeconomicmodule.servicetype

                this.comeconomicmodule.acceptdate = null
                this.comeconomicmodule.feedbackdate = null
                this.comeconomicmodule.assignmentdate = null
                this.comeconomicmodule.filingcasesdate = null
                this.comeconomicmodule.finalfeedbackdate = null
                this.comeconomicmodule.feedbacktime = null

                console.log(this.comeconomicmodule)
                // localStorage.clear("data");linyu
                localStorage.setItem("dataChange", JSON.stringify());
            }else if(this.$route.query.type === "rightFormChangeEconomy"){
                this.rightForm = JSON.parse(localStorage.getItem("rightForm"))
                console.log(this.rightForm)
                this.comeconomicmodule.appealsource = '维权服务站'//渠道
                this.comeconomicmodule.detailaddress = this.rightForm.region//地区来源
                this.comeconomicmodule.transfernumber = this.rightForm.serialnum//维权工单号
                this.comeconomicmodule.rqsttitle = this.rightForm.rqsttitle //标题
                this.comeconomicmodule.rqstperson = this.rightForm.name  //投诉人
                this.comeconomicmodule.gender = this.rightForm.gender  // 性别
                this.comeconomicmodule.rqstnumber = this.rightForm.phone   //投诉人电话
                this.comeconomicmodule.rqstaddress = this.rightForm.address  //投诉人地址
                this.comeconomicmodule.subjectname = this.rightForm.vendorname //被投诉商家名
                this.comeconomicmodule.linknumber = this.rightForm.vendorphone //商家电话
                this.comeconomicmodule.subjectaddress = this.rightForm.vendoraddress  //商家地址
                this.comeconomicmodule.region = this.rightForm.vendortown  //商家地址前面镇街
                this.comeconomicmodule.objname = this.rightForm.goodsname //商品名称
                this.comeconomicmodule.problemdescribe = this.rightForm.rqstcontent //问题描述

            }




            this.getComplaintstype1();
            this.getObjectcategory1();
        }
    };
</script>
<style lang="scss" scoped>
    .el-date-editor.el-input {
        width: 223px;
    }
</style>
