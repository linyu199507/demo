<template>
    <el-collapse v-model="activeCollapse">
        <el-collapse-item title="系统信息" name="1" style="font-weight: bold">
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="complaintmodule"
                    ref="complaintmodule">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">业务性质：</el-col>
                        <el-col :span="16" class="st">消费投诉</el-col>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">标题：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.rqsttitle}}</el-col>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">途径：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.appealsource}}</el-col>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">移送编号：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.transfernumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
<!--                    <el-col :xs="24" :sm="24" :md="8" :lg="8">-->
<!--                        <el-col :span="8" class="buff">来件类型：</el-col>-->
<!--                        <el-col :span="16" class="st">{{complaintmodule.incommingtype}}</el-col>-->
<!--                    </el-col>-->
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">地区来源：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.detailaddress}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">所属板块：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.subordinateplate}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-col :span="8" class="buff">来函单位：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.incomingcommpany}}</el-col>
                    </el-col>
                </el-row>
                <el-row  v-if="complaintmodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">登记机构：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.registration}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">承办机构：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.undertake}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">满意度：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.satisfied}}</el-col>
                    </el-col>
                </el-row>
<!--                <el-row>-->
<!--                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">-->
<!--                        <el-col :span="8" class="buff">来函单位：</el-col>-->
<!--                        <el-col :span="16" class="st">{{complaintmodule.incomingcommpany}}</el-col>-->
<!--                    </el-col>-->
<!--                </el-row>-->
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="提供方信息" name="2" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">投诉人姓名：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.rqstperson}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">投诉人电话：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.rqstnumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">性别：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.gender}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">是否职投：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.isprofessional}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">投诉人地址：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.rqstaddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="涉及主体信息" name="3" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">涉及主体名称：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.subjectname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">联系电话：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.linknumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">地址：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.region}} {{complaintmodule.subjectaddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="具体信息" name="4" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">涉及问题类型：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.complaintstype}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">商品/服务类型：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.servicetype}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">商品/服务名称：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.objname}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">品牌：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.brand}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">领域：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.filed}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">是否网购：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.isweb}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">网购平台：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.shoppingplatform}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">案例接收日期：</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.acceptdate != null">{{$moment(this.complaintmodule.acceptdate).format("YYYY-MM-DD HH:mm:ss")}}</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.acceptdate == null">{{complaintmodule.acceptdate}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">初步反馈日期：</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.feedbackdate != null">{{$moment(this.complaintmodule.feedbackdate).format("YYYY-MM-DD HH:mm:ss")}}</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.feedbackdate == null">{{complaintmodule.feedbackdate}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">案件分派日期：</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.assignmentdate != null">{{$moment(this.complaintmodule.assignmentdate).format("YYYY-MM-DD HH:mm:ss")}}</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.assignmentdate == null">{{complaintmodule.assignmentdate}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">最终反馈日期：</el-col>
                        <!--<el-col :span="16">{{complaintmodule.feedbacktime}}</el-col>-->
                        <el-col :span="16" class="st" v-if="complaintmodule.feedbacktime != null">{{$moment(this.complaintmodule.feedbacktime).format("YYYY-MM-DD HH:mm:ss")}}</el-col>
                        <el-col :span="16" class="st" v-if="complaintmodule.feedbacktime == null">{{complaintmodule.feedbacktime}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">受理通知书号：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.acceptancenumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">案件转办书号：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.transferbooknumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-col :span="8" class="buff">关键字：</el-col>
                        <el-col :span="16" class="st">{{complaintmodule.keyword}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="18">
                        <el-col :span="3" class="buff" style="width: 15%">问题描述：</el-col>
                        <el-col :span="20" class="st">{{complaintmodule.problemdescribe}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                    <el-form-item label="附件："  label-width="130px" >
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                action="/crcs/filesUpload/upload"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :file-list="fileList"
                                :auto-upload="false"
                                :data="this.requestMap"
                                :on-success="uploadSuccess"
                        >
                        </el-upload>
                    </el-form-item>
                    </el-col>
                </el-row>
                <el-row></el-row>
            </el-form>
        </el-collapse-item>
    </el-collapse>
</template>
<script>
    import {myMixin} from "../../../assets/mixin/mixin";
    import {getMaterialLists,deleteUploadFile}from"../../../api/api"
    import  { ToDate } from "../../../utils/timeToDate";

    export default {
        props:["complaintmodule"],
        mixins: [myMixin],
        data() {
            return {

                requestMap: {
                    caseguid: ""
                },
                complaintmodule: {},
                rows: 5,
                systemInfo: {
                    title: "",
                    region: "",
                    transferNumber: "",
                    subordinatePlate: "",
                    address: "",
                    detailAddress: "",
                    incommingType: "",
                    incomingCommpany: ""
                },
                providerInfo: {
                    name: "",
                    tel: "",
                    gender: "",
                    jobPlacement: ""
                },
                subject: {
                    name: "",
                    tel: "",
                    address: "",
                    detailAddress: "",
                    dealCompany: ""
                },
                specificInfo: {
                    type: [],
                    serviceType: [],
                    specificInfo: "",
                    filed: "",
                    onlineShopping: "",
                    shoppingPlatform: "",
                    acceptDate: "",
                    feedbackDate: "",
                    assignmentDate: "",
                    finalFeedbackDate: "",
                    notificationNumber: "",
                    transferBookNumber: "",
                    keyword: "",
                    desc: ""
                },
                fileList: [],
                showSpotCheck: false,
                showVisitors:true,
                complaintForm: {},
                activeCollapse: ["1", "2", "3", "4"]
            };
        },
        mounted(){
          this.getMaterialLists()
        },
        methods: {
            handleChangeBusiness(value) {
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                let para={
                    filename:this.filePara.filename,
                    fileurl:this.filePara.fileurl
                }
                console.log(para)
                deleteUploadFile(para).then(res=>{
                    console.log(res)
                })
            },
            handlePreview(file) {
                if(file.status=="success"){
                    this.download(file.url)
                }
            },
            download(url){
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
            changeRegionInfo(value) {
                if (value === "其他") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                    if(value === "来人来访"){
                        this.showVisitors = false;
                    }
                }
            },
            getMaterialLists() {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.complaintmodule.caseguid
                };
                console.log(params)
                getMaterialLists(params).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        console.log(res)
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            //this.fileListsAccept.push(fileInfo);
                            this.fileList.push(fileInfo);
                        }
                    }
                });
            },
        },
    };
</script>
<style lang="scss" scoped>
    .el-date-editor.el-input {
        width: 223px;
    }

    .buff {
        margin: 5px auto;
        font-size: 13px;
        color: #48576a;
        text-align: right;
    }

    .st {
        margin: 5px auto;
        font-size: 13px;
        color: #48576a;
        font-weight: normal;
        text-align: left;
    }
</style>
