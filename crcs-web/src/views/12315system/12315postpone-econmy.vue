<!--申请延期审批-->
<template>
    <section class="delay-approval" id="whole">
        <!--    <el-row style="margin-top: 5px;margin-bottom: 5px">-->
        <!--      <el-col>-->
        <!--        <el-steps :active="4" align-center center space="22%">-->
        <!--          <el-step title="工单确认"></el-step>-->
        <!--          <el-step title="工单审核"></el-step>-->
        <!--          <el-step title="工单处理"></el-step>-->
        <!--          <el-step title="延期审批"></el-step>-->
        <!--          <el-step title="结果反馈"></el-step>-->
        <!--        </el-steps>-->
        <!--      </el-col>-->
        <!--    </el-row>-->
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div style="text-align: right;" :class="{'fixed':scrollTop > height}" >
                    <el-button type="primary" @click="triggerTab('tabAgree')">同意延期</el-button>
                    <el-button type="primary" @click="triggerTab('tabDisagree')">不同意延期</el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    表单内容
                                </el-col>
                                <el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>
                            </el-row>
                        </div>
                        <!--经济违法信息内容 -->
                        <economyInfo v-if="economyform" :economyform="economyform" :tasklist="tasklist" />
                    </el-collapse-item>
                    <!-- 流程跟踪 -->
                    <el-collapse-item name="processTracking">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>4.</em> 流程跟踪
                                </el-col>
                                <el-col :span="10" class="right"></el-col>
                            </el-row>
                        </div>
                        <Tracking :data="opinionList" />
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 同意 -->
            <el-tab-pane label="同意延期" name="tabAgree" v-if="tabActionName==='tabAgree'">
                <el-form
                        :model="delayForm"
                        label-width="200px"
                        class="text_bold"
                        ref="delayForm"
                        label-suffix="："
                        :rules="delayFormRules"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="是否同意" prop="isPass">
                                <div style="line-height: 35px">同意延期</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="审核意见" prop="resolution">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入审核意见（3000字内）"
                                        v-model="delayForm.resolution"
                                ></el-input>
                                <el-button @click="template">意见模板</el-button>
                                <!--透底灰框黑字  color="black" background-color="taansparent" border-color="lightsteelblue"-->
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDelay" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 不同意 -->
            <el-tab-pane label="不同意延期" name="tabDisagree" v-if="tabActionName==='tabDisagree'">
                <el-form
                        :model="delayForm"
                        label-width="200px"
                        class="text_bold"
                        ref="delayForm"
                        label-suffix="："
                        :rules="delayFormRules"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="是否同意" prop="isPass">
                                <div style="line-height: 35px">不同意延期</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="审核意见" prop="resolution">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入审核意见（3000字内）"
                                        v-model="delayForm.resolution"
                                ></el-input>
                                <el-button @click="template">意见模板</el-button>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDelay" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import { searchBaseInfo, nextWork } from "../../api/convenient12315";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "./components/EconomyInfo";
    import {myMixin} from "../../assets/mixin/mixin"
    export default {
        mixins:[myMixin],
        components: {
            Tracking,
            EconomyInfo,
        },
        data() {
            return {
                Providetype:"",
                Industryname:"",
                Objectcategory:"",
                Reportprovidelb:"",
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
                delayForm: {
                    isPass: undefined,
                    resolution: ""
                },
                delayFormRules: {
                    isPass: [{ required: true }],
                    resolution: [
                        { required: true, message: "请输入处理意见", trigger: "blur" },
                        { max: 3000, message: "不能超出3000字符", trigger: "blur" }
                    ]
                },
                submitLoading: false
            };
        },
        methods: {
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            triggerTab(value) {
                this.tabActionName = value;
                if (value === "tabAgree") {
                    this.delayForm.isPass = "同意";
                } else {
                    this.delayForm.isPass = "不同意";
                }
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            template() {
                let isPass = this.delayForm.isPass;
                if (isPass == "同意") {
                    this.delayForm.resolution = "经审核,相关条件符合延期要求,予以延期处理";
                } else {
                    this.delayForm.resolution =
                        "经审核,相关条件不符合延期要求,不予以延期处理";
                }
            },
            submitDelay() {
                //提交延期审批结果
                let validation = false;
                this.$refs.delayForm.validate(valid => (validation = valid));
                if (!validation) return;
                let data
                if ("commommodule" === this.formType) {
                    if(this.commomform != null){
                        data = this.commomform;
                    }else {
                        data = this.consultationform;
                    }
                } else if ("complaintmodule" === this.formType) {
                    data = this.complaintform;
                } else if ("comeconomicmodule" === this.formType) {
                    if(this.economyform.providetype != null && this.economyform.providetype != ""){
                        this.economyform.providetype = this.Providetype
                    }
                    if(this.economyform.industryname != null && this.economyform.industryname != ""){
                        this.economyform.industryname = this.Industryname
                    }
                    if(this.economyform.objectcategory != null && this.economyform.objectcategory != ""){
                        this.economyform.objectcategory = this.Objectcategory
                    }
                    if(this.economyform.reportprovidelb != null && this.economyform.reportprovidelb != ""){
                        this.economyform.reportprovidelb = this.Reportprovidelb
                    }
                    data = this.economyform;
                }
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "superiorProcessing",
                        data: data,
                        opinion: this.delayForm.resolution
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {

                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration:'1000'
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
            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
                });
            },
            getBaseInfo() {
                this.loading = true;
                //获取id，否则跳转
                let { id, caseguid } = this.$route.query;
                if (!id || !caseguid) {
                    this.$router.push("/wddb/12315db");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({ id, caseguid, systemsource: "02" })
                    .then(({ code, data }) => {
                        this.nodeStr = data.tasklist.nextnodeid;
                        if (this.nodeStr !== "applyDelay") {
                            //节点与当前对应不上
                            this.$router.push("/wddb/12315db");
                            return;
                        }
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
                                this.ordernumber = this.economyform.ordernumber;
                                if(this.economyform.providetype != null && this.economyform.providetype != ""){
                                    this.Providetype = this.economyform.providetype
                                    this.economyform.providetype = this.economyform.providetype.split("-")//将字符串格式转换成数组
                                    this.economyform.providetype = this.economyform.providetype[this.economyform.providetype.length-1].toString()
                                }
                                if(this.economyform.industryname != null && this.economyform.industryname != ""){
                                    this.Industryname = this.economyform.industryname
                                    this.economyform.industryname = this.economyform.industryname.split("-")//将字符串格式转换成数组
                                    this.economyform.industryname = this.economyform.industryname[this.economyform.industryname.length-1].toString()
                                }
                                if(this.economyform.objectcategory != null && this.economyform.objectcategory != ""){
                                    this.Objectcategory = this.economyform.objectcategory
                                    this.economyform.objectcategory = this.economyform.objectcategory.split("-")//将字符串格式转换成数组
                                    this.economyform.objectcategory = this.economyform.objectcategory[this.economyform.objectcategory.length-1].toString()
                                }
                                if(this.economyform.reportprovidelb != null && this.economyform.reportprovidelb != ""){
                                    this.Reportprovidelb = this.economyform.reportprovidelb
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb.split("-")//将字符串格式转换成数组
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb[this.economyform.reportprovidelb.length-1].toString()
                                }
                            } else if (data.complaintform != null) {
                                //消费投诉表单
                                this.formType = "complaintmodule";
                                this.formTypeStr = "消费投诉";
                                this.complaintform = data.complaintform;
                                this.ordernumber = this.complaintform.ordernumber;
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
            }
        },
        created() {
            this.getBaseInfo();
        }
    };
</script>
<style lang="scss" scoped>
    .delay-approval {
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
        .el-form .el-row {
            margin: 0;
        }
    }
</style>
