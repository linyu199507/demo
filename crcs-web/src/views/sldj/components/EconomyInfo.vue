<template>
    <el-collapse v-model="activeCollapse">
        <el-collapse-item title="系统信息" name="1" style="font-weight: bold">
            <el-form label-width="120px">
                <el-row>
                        <el-col :xs="24" :sm="24" :md="16" :lg="8">
                            <el-col :span="8" class="buff">业务性质：</el-col>
                            <el-col :span="16" class="st">经济违法举报</el-col>
                        </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">标题：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.rqsttitle}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">途径：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.appealsource}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">移送编号：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.transfernumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
<!--                    <el-col :xs="24" :sm="24" :md="16" :lg="8">-->
<!--                        <el-col :span="8" class="buff">来件类型：</el-col>-->
<!--                        <el-col :span="16" class="st">{{comeconomicmodule.incommingtype}}</el-col>-->
<!--                    </el-col>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="8">
                        <el-col :span="8" class="buff">地区来源：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.detailaddress}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">所属板块：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.subordinateplate}}</el-col>
                    </el-col>
                </el-row>

                <el-row  v-if="comeconomicmodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">登记机构：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.registration}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">承办机构：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.undertake}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">满意度：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.satisfied}}</el-col>
                    </el-col>
                </el-row>

                <el-row v-if="showSpotCheck">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">移送部门：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.transdepartment}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">抽查单位：</el-col>
                        <el-col :span="4" class="st">{{comeconomicmodule.checkunit}}</el-col>
                        <el-col :span="12" class="st">{{comeconomicmodule.othersunit}}</el-col>
                    </el-col>
                </el-row>
                <el-row v-if="showSpotCheck">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">程度：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.severity}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">产品类别：</el-col>
                        <el-col :span="6" class="st">{{comeconomicmodule.productcategory}}</el-col>
                        <el-col :span="10" class="st">{{comeconomicmodule.otherproductcategory}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-col :span="8" class="buff">领域：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.filed}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-col :span="8" class="buff">来函单位：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.incomingcommpany}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="提供方信息" name="2" style="font-weight: bold" v-if="showSpotCheck === false">
            <el-form label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">举报人姓名：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.rqstperson}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">举报人电话：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.rqstnumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">性别：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.gender}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">是否职投：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.isprofessional}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">举报人地址：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.rqstaddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="涉及主体信息" name="3" style="font-weight: bold">
            <el-form label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">涉及主体名称：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.subjectname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">联系电话：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.linknumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">地址：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.region}} {{comeconomicmodule.subjectaddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item title="具体信息" name="4" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top:20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">涉及问题类型：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.reportprovidelb}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">商品/服务类型：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.objectcategory}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">商品/服务名称：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.objname}}</el-col>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck === false">
                        <el-col :span="8" class="buff">品牌：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.brand}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck === false">
                        <el-col :span="8" class="buff">是否网购：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.isweb}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck === false">
                        <el-col :span="8" class="buff">网购平台：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.shoppingplatform}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showVisitors == true">
                        <el-col :span="8" class="buff">领域：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.filed}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showVisitors == true">
                        <el-col :span="8" class="buff">受理通知书号：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.acceptancenumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showVisitors == true">
                        <el-col :span="8" class="buff">案件转办书号：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.transferbooknumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">案例接收日期：</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.acceptdate == null">{{comeconomicmodule.acceptdate}}</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.acceptdate != null">{{$moment(this.comeconomicmodule.acceptdate).format("YYYY-MM-DD")}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">初步反馈日期：</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.feedbackdate == null">{{comeconomicmodule.feedbackdate}}</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.feedbackdate != null">{{$moment(this.comeconomicmodule.feedbackdate).format("YYYY-MM-DD")}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">案件分派日期：</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.assignmentdate == null">{{comeconomicmodule.assignmentdate}}</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.assignmentdate != null">{{$moment(this.comeconomicmodule.assignmentdate).format("YYYY-MM-DD")}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">应立案日期：</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.filingcasesdate == null">{{comeconomicmodule.filingcasesdate}}</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.filingcasesdate != null">{{$moment(this.comeconomicmodule.filingcasesdate).format("YYYY-MM-DD")}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">最终反馈日期：</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.finalfeedbackdate == null">{{comeconomicmodule.finalfeedbackdate}}</el-col>
                        <el-col :span="16" class="st" v-if="comeconomicmodule.finalfeedbackdate != null">{{$moment(this.comeconomicmodule.finalfeedbackdate).format("YYYY-MM-DD")}}</el-col>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">关键字：</el-col>
                        <el-col :span="16" class="st">{{comeconomicmodule.keyword}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                        <el-col :span="4" class="buff" style="width: 15%">问题描述：</el-col>
                        <el-col :span="20" class="st">{{comeconomicmodule.problemdescribe}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                    <el-form-item label="附件：" label-width="130px">
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
            </el-form>
        </el-collapse-item>
    </el-collapse>
</template>
<script>
    import {myMixin} from "../../../assets/mixin/mixin";
    import{getMaterialLists,deleteUploadFile}from "../../../api/api"
    import  { ToDate } from "../../../utils/timeToDate";

    export default {
        props:["comeconomicmodule"],
        mixins: [myMixin],
        data() {
            return {
                requestMap:{
                    caseguid:""
                },
                comeconomicmodule: {},
                activeCollapse: ["1", "2", "3", "4"],
                rows: 5,
                num: 9,
                showSpotCheck: false,
                showVisitors:true,
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
                specificInfo: {
                    type: [],
                    serviceType: [],
                    brand: "",
                    onlineShopping: "",
                    shoppingPlatform: "",
                    acceptDate: "",
                    feedbackDate: "",
                    assignmentDate: "",
                    filingCasesDate: "",
                    finalFeedbackDate: "",
                    notificationNumber: "",
                    transferBookNumber: "",
                    keyword: "",
                    desc: ""
                },
                systemInfo: {
                    title: "",
                    channel: "",
                    transferNumber: "",
                    detailAddress: "",
                    selectedOptions: [],
                    checkUnit: "",
                    othersUnit: "",
                    degree: "",
                    subordinatePlate: "",
                    filed: "",
                    productCategory: "",
                    otherProductCategory: "",
                    incommingType: "",
                    incomingCommpany: ""
                },
                provider: {
                    name: "",
                    tel: "",
                    gender: "",
                    jobPlacement: ""
                },
                mainInfo: {
                    name: "",
                    tel: "",
                    address: "",
                    detailAddress: "",
                    dept: ""
                },
                fileList: []
            };
        },
        methods: {
            handleChangeBusiness(value) {
            },
            handleChange(value) {
            },
            getChannelInfo() {
                if (this.comeconomicmodule.appealsource === "11") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                    if(this.comeconomicmodule.appealsource === "13"){
                        this.showVisitors = false;
                    }else {
                        this.showVisitors = true;
                    }
                }
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
                if(file.status=="success"){
                    this.download(file.url)
                }
            },
            download(url){
                window.open(`${process.env.Download_URL}` + url);
            },
            getMaterialLists() {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    // caseguid:this.comeconomicmodule.caseguid
                    caseguid:this.requestMap.caseguid
                };
                console.log(params)
                getMaterialLists(params).then(res => {
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

        mounted() {
            this.requestMap.caseguid=this.$route.query.caseguid
            this.getChannelInfo();
            this.getMaterialLists();
        }
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
