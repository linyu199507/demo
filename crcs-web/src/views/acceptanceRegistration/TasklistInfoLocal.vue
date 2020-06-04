<template>
    <section class="application_delay">
        <el-tabs type="border-card" style="width: 96%;margin: 0 auto;" @tab-click="flow"
                 v-model="aictabs" v-loading="loading">
            <div class="operation" style="text-align: right;"  v-if="aictabs === 'economyFrom'">
               <!-- <template>表单号：{{tasklist.serialnum}}</template>-->
                <!--<template>当前节点：{{tasklist.nextnodeid}}</template>-->

                <el-button type="primary" size="small" icon="document" @click="exportToWord" :loading="exportLoading"
                           style="margin: 2px; border-radius: 13px"

                            >
                    <template v-if="exportLoading">
                        正在导出...
                    </template>
                    <template v-else >
                        <i class="fa fa-download"></i>
                        Word导出
                    </template>
                </el-button>
                <el-button type="primary" @click="triggerBus('tabBus')" v-if="user.primaryKey=='80b309e6-bb1d-49ba-81e8-f87c542e4f2c'">在办意见</el-button>
                <el-button
                        v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0' && nodeStr != 'end'&& user.aicPermissions.dealdepartment"
                        type="primary"
                        @click="submitWithdraw"
                >待办收回</el-button>
            </div>
            <el-tab-pane :label="formTypeStr" name="economyFrom">
                <!--受理登记待办--经济违法行为表单-->
                <my-economy-info :data="comeconomicmodule" :task="tasklist" v-if="formType==='comeconomicmodule' && systemsource==='01'"/>
                <!--新的经济违法行为表单-->
                <!--<new-my-economy-info-->
                        <!--:data="comeconomicmodule"-->
                        <!--:task="tasklist"-->
                        <!--:casedata="caseprocesform"-->
                        <!--v-if="formType==='comeconomicmodule'"-->
                <!--/>-->
                <!--受理登记待办--咨询登记表单-->
                <my-consult-info :data="commommodule" :task="tasklist" v-if="formType==='commommodule' && systemsource==='01'"/>
                <!--新的咨询登记表单-->
                <!--<new-my-consult-info-->
                        <!--:data="commommodule"-->
                        <!--:task="tasklist"-->
                        <!--:casedata="caseprocesform"-->
                        <!--v-if="formType==='commommodule'"-->
                <!--/>-->
                <!--受理登记待办--消费投诉登记-->
                <my-consume-info :data="complaintmodule" :task="tasklist"  v-if="formType==='complaintmodule' && systemsource==='01'"/>
                <!--新的消费投诉登记-->
                <!--<new-my-consume-info-->
                        <!--:data="complaintmodule"-->
                        <!--:task="tasklist"-->
                        <!--:casedata="caseprocesform"-->
                        <!--v-if="formType==='complaintmodule'"-->
                <!--/>-->
                <!--12345系统--经济违法行为表单-->
                <EconomyInfo12345 :economyform="comeconomicmodule" :tasklist="tasklist" v-if="formType==='comeconomicmodule' && systemsource==='02'"/>
                <!--12345系统--消费表单-->
                <ComplaintmoduleInfo12345 :complaintform="complaintmodule" :tasklist="tasklist" v-if="formType==='complaintmodule' && systemsource==='02'"/>
                <!--12345系统--咨询表单-->
                <ConsultationInfo12345 :formData="commommodule" :tasklist="tasklist" v-if="formType==='commommodule' && systemsource==='02'"/>

                <!--12315系统--投诉表单-->
                <ComplaintmoduleInfo12315 :complaintform="complaintmodule" :tasklist="tasklist" v-if="formType==='complaintmodule' && systemsource==='03'"/>
                <!--12315系统--举报表单-->
                <EconomyInfo12315 :economyform="comeconomicmodule" :tasklist="tasklist" v-if="formType==='comeconomicmodule' && systemsource==='03'"/>


                <el-collapse class="accordion" v-model="processingCollapse">
                    <el-collapse-item name="tracking">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    办理跟踪
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <my-tracking :data="opinionList"/>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>

            <!-- 待办收回 -->
            <el-tab-pane name="takeBack" label="收回" v-if="operating==='takeBack'">
                <el-form 
                :model="takeBack" 
                ref="takeBack" 
                :rules="takeBackRules" 
                label-width="150px" 
                label-suffix="："
                style="margin-top:50px;">
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="收回原因" class="form_bt" prop="reason">
                            <el-select placeholder="请选择收回原因" v-model="takeBack.reason"  @change="reasonChange()" v-if="systemsource!=='03'">
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
                            <el-select placeholder="请选择收回原因" v-model="takeBack.reason" v-if="systemsource==='03'">
                                <el-option label="不属实" value="不属实"></el-option>
                                <el-option label="其他" value="其他"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                        <el-form-item label="收回描述" class="form_bt" prop="opinion">
                            <el-input type="textarea" v-model="takeBack.opinion" placeholder="请输入收回描述（1000字内）" :rows="5" >
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col class="centered">
                        <el-button type="primary" @click="submitApproval" :loading="submitLoading">收回</el-button>
                        <el-button @click="cancelTab">取消</el-button>
                    </el-col>
                </el-form>
            </el-tab-pane>
            <!-- 在办反馈 -->
            <el-tab-pane label="在办意见" name="tabBus" v-if="operating==='tabBus'">
                <el-form :model="opinionForm" label-width="200px" ref="opinionForm" :rules="opinionFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="反馈内容" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="opinionForm.opinion"
                                        placeholder="请输入反馈内容（3000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col class="centered">
                            <el-button type="primary" @click="copyOpinion('tabBus')" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import {next, searchBaseInfo ,wordexport} from '../../api/api';
    import {toProcessing} from '../../api/convenient12345'
    //受理待办组件
    import Tracking from '../../components/Tracking'
    import EconomyInfoLocal from './EconomyInfoLocal'
    import consultInfoLocal from './consultInfoLocal'
    import ConsumeInfoLocal from './ConsumeInfoLocal'
    //引入新的组件
    import NewEconomyInfoLocal from "../sldj/components/EconomyInfo";
    import NewconsultInfo from "../sldj/components/commommoduleInfo";
    import NewComplaintmoduleInfo from "../sldj/components/ComplaintmoduleInfo"
    // 12345系统组件
    import EconomyInfo12345 from '../12345system/components/EconomyInfo'
    import ComplaintmoduleInfo12345 from '../12345system/components/ComplaintmoduleInfo'
    import ConsultationInfo12345 from '../12345system/components/ConsultationInfo'
    //12315系统组件
    import ComplaintmoduleInfo12315 from '../12315system/components/ComplaintmoduleInfo'
    import EconomyInfo12315 from '../12315system/components/EconomyInfo'

    export default {
        components: {
            'my-tracking': Tracking,
            'my-economy-info': EconomyInfoLocal,
            'my-consult-info': consultInfoLocal,
            'my-consume-info': ConsumeInfoLocal,
            EconomyInfo12345,
            ComplaintmoduleInfo12345,
            ConsultationInfo12345,
            ComplaintmoduleInfo12315,
            EconomyInfo12315,
            "new-my-economy-info": NewEconomyInfoLocal,
            "new-my-consult-info": NewconsultInfo,
            "new-my-consume-info": NewComplaintmoduleInfo,

        },
        data() {
            return {
                commomform: null,
                complaintform: null,
                economyform: null,
                processingCollapse: ['processing','tracking'],
                tasklist: {},
                casedata:{},
                loading: true,
                formType: '',
                exportLoading:false,
                formTypeStr: '',
                systemsource:this.$route.query.systemsource,
                opinionList: [],
                aictabs: 'economyFrom',
                isOperation: false,
                dialogFormVisible: false,
                operationType: '',
                commommodule: {},
                comeconomicmodule: {},
                complaintmodule: {},
                feedbackForm: {
                    isCase: undefined,
                    resolution: '',
                },
                opinionForm: {},
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                },
                feedbackFormRules: {
                    isCase: [
                        {required: true, message: '请选择是否销案'},
                    ],
                    leader: [
                        {required: true, message: '请选择领导'},
                    ],
                    resolution: [
                        {required: true, message: '请输入审核意见', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000字符', trigger: 'blur'},
                    ]
                },
                goBackForm: {
                    reason: undefined,
                },
                goBackFormRules: {
                    reason: [
                        {required: true, message: '请输入退回理由', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000字符', trigger: 'blur'},
                    ]
                },
                assignmentForm: {
                    unit: undefined,
                    opinion: undefined,
                },
                assignmentFormRules: {
                    unit: [
                        {required: true, message: '请选择成员单位'},
                    ],
                    opinion: [
                        {required: true, message: '请输入分派意见', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000字符', trigger: 'blur'},
                    ]
                },
                delayForm: {
                    unit: undefined,
                    limit: undefined,
                    reason: undefined,
                },
                delayFormRules: {
                    unit: [
                        {required: true, message: '请输入延期时限'},
                    ],
                    limit: [
                        {required: true, message: '请选择延期时间单位'},
                    ],
                    reason: [
                        {required: true, message: '请输入延期原因', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000字符', trigger: 'blur'},
                    ]
                },
                user: JSON.parse(localStorage.getItem("user")),
                nodeStr: undefined,
                takeBack: {
                    reason: "",
                    opinion: ""
                },
                operating: "",
                takeBackRules: {
                    reason: [
                        {required: true, message: "请选择收回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入收回描述", trigger: "blur"},
                        {max: 1000, message: "不能超过1000字符", trigger: "blur"}
                    ]
                },
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

            flow({name}) {
                // if ('operationDom' !== name) {
                //     this.isOperation = false;
                // }
                if (name !== 'takeBack'){
                    this.operating = '';
                    this.aictabs = 'economyFrom';
                }
            },


            getBaseInfo() {
                //获取id，否则跳转
                let {id, caseguid, systemsource} = this.$route.query
                if (!id || !caseguid) {
                    // this.$router.push('/wddb/bxtdb')
                    return
                }
                // let systemsource = '01'
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource}).then((res) => {

                    if (res.code === 0) {
                        if (res.data.economyform != null) {//经济表单
                            this.formType = 'comeconomicmodule'
                            this.formTypeStr = '经济违法行为表单'
                            this.comeconomicmodule = res.data.economyform
                            console.log(this.comeconomicmodule)
                        } else if (res.data.complaintform != null) {//消费投诉表单
                            this.formType = 'complaintmodule'
                            this.formTypeStr = '消费投诉表单'
                            this.complaintmodule = res.data.complaintform
                            this.$router.resolve({
                                path: "tjfx/zhcx/ywjlcx/cxjg/ywjlxq",
                                query: this.serialnum
                            });
                        } else if (res.data.commomform) {//咨询登记
                            this.formType = 'commommodule'
                            this.formTypeStr = '咨询登记'
                            this.commommodule = res.data.commomform
                        } else {//找不到对的工单信息
                            this.$message({
                                message: '找不到对应的工单',
                                type: 'error'
                            });
                            // this.$router.push('/wddb/bxtdb')

                        }
                        this.opinionList = res.data.opinionList
                        this.tasklist = res.data.tasklist
                        console.log("=============")
                        console.log(this.opinionList)
                        console.log(this.systemsource)
                        console.log(this.formType)
                        this.nodeStr = res.data.tasklist.nextnodeid;
                        this.loading = false
                    }
                }).catch((error) => {
                    console.error(error);
                    this.$message({
                        message: '请求失败',
                        type: 'error'
                    });
                })
            },
            reasonChange(){
                if('缺失要素' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，请核实补充以下要素：xxx。"
                }else if ('表述不清' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，请核实修改以下内容：xxx。"
                }else if ('类型有误' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，请修改表单类型为：xxx。"
                }else if ('地址不详' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，地址不详细，请核实详细地址。"
                }else if ('已有知识' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，根据知识库信息编码：xxx，请按知识点指引市民。"
                }else if ('重复受理' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，此工单与另一工单（表单号：xxx）属重复受理。"
                }else if ('市民撤诉' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，经我局与市民核实，市民表示无需我局继续跟进此工单。"
                }else if ('不属本职' == this.takeBack.reason){
                    this.takeBack.opinion = "此单收回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。"
                }else{
                    this.takeBack.opinion = "其它原因"
                }
            },
            // 待办收回
            submitWithdraw() {
                this.operating = 'takeBack';
                this.aictabs = 'takeBack';
            },
            //在办反馈 msx
            triggerBus(value){
                this.operating ='tabBus';
                this.aictabs = 'tabBus';
            },
            //确认收回
            submitApproval(){
                //工单收回
                this.$refs.takeBack.validate(valid => {
                    if (valid){
                        this.$confirm("是否将该工单收回到您的待办中?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            this.submitLoading = true;
                            let data;
                            if ("commommodule" === this.formType) {
                                data = this.commommodule;
                            } else if ("complaintmodule" === this.formType) {
                                data = this.complaintmodule;
                            } else if ("comeconomicmodule" === this.formType) {
                                data = this.comeconomicmodule;
                            }
                            let params = {
                                user: this.user,
                                formType: this.formType,
                                node: this.nodeStr,
                                nextNodeId: "withdraw",
                                data: data,
                                opinion: this.takeBack.opinion,
                                backreason: this.takeBack.reason
                            };
                            console.log(params)
                            next(params)
                                .then(data => {
                                    if (data.code === 0) {
                                        this.$message({
                                            message: "提交成功！",
                                            type: "success",
                                            duration: "1000"
                                        });
                                        if(this.systemsource==='02'){
                                            this.$router.push("/tjfx/zhcx/cqjlcx");
                                        }else if(this.systemsource==='03'){
                                            this.$router.push("/tjfx/zhcx/wbjlcx");
                                        }else {
                                            this.$router.push("/tjfx/zhcx/sldjcx");
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
                    }
                })
            },

            copyOpinion(value) {
                //反馈意见填写
                // return
                let validation = false;
                this.$refs.opinionForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    // let caseguid;
                    // if ("commommodule" === this.formType) {
                    //     if (this.commomform != null) {
                    //         caseguid = this.commommodule.caseguid;
                    //     } else {
                    //         caseguid = this.consultationform.caseguid;
                    //     }
                    // } else if ("complaintmodule" === this.formType) {
                    //     caseguid = this.complaintmodule.caseguid;
                    // } else if ("comeconomicmodule" === this.formType) {
                    //     caseguid = this.comeconomicmodule.caseguid;
                    // }
                    let params = {
                        user: this.user,
                        caseguid:this.$route.query.caseguid,
                        // node: this.nodeStr,
                        // nextNodeId: "tabBus",
                        opinion: this.opinionForm.opinion,
                        type: value,
                        formtype:this.formType
                    };
                    console.log(params)
                    toProcessing(params).then(data => {
                        if (data.code == 0) {
                            this.$message({
                                message: '提交成功！',
                                type: 'success',
                                duration: '1000'
                            });
                            // this.cancelTab()
                            location.reload();
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

            //取消收回
            cancelTab(){
                this.operating = '';
                this.aictabs = 'economyFrom';
            }
        },
        created() {
            this.getBaseInfo()
        },
    };
</script>
<style lang="scss" scoped>
    .application_delay {
        overflow-x: hidden;

        .el-table__body-wrapper {
            overflow: hidden;
        }

        .bt {
            margin: 5px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
            margin-left: -100px;
        }

        .bts {
            margin: 10px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .st {
            margin: 5px auto;
            font-size: 13px;
            /*text-align: center;*/
            margin-left: 15px;
        }

        .br {
            padding-bottom: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }
    }

</style>
