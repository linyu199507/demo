<template>
    <section class="application_delay">
<!--        <el-row style="margin-top: 5px;margin-bottom: 5px">-->
<!--            <el-col>-->
<!--                <el-steps :active="4" align-center center space="22%">-->
<!--                    <el-step title="受理登记"></el-step>-->
<!--                    <el-step title="工单审核"></el-step>-->
<!--                    <el-step title="工单处理"></el-step>-->
<!--                    <el-step title="延时审批"></el-step>-->
<!--                    <el-step title="结果反馈"></el-step>-->
<!--                </el-steps>-->
<!--            </el-col>-->
<!--        </el-row>-->
        <el-tabs type="border-card" style="width: 96%;margin: 15px auto;" @tab-click="flow"
                 v-model="aictabs" v-loading="loading">
            <el-tab-pane :label="formTypeStr">
                <!--经济违法行为表单-->
                <my-economy-info :data="comeconomicmodule" :task="tasklist" v-if="formType==='comeconomicmodule'"/>
                <!--新的经济违法行为表单-->
                <!--<new-my-economy-info
                        :data="comeconomicmodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='comeconomicmodule'"
                />-->
                <!--咨询登记表单-->
                <my-consult-info :data="commommodule" :task="tasklist" v-if="formType==='commommodule'"/>
                <!--新的咨询登记表单-->
                <!--<new-my-consult-info
                        :data="commommodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='commommodule'"
                />-->
                <!--消费投诉登记-->
                <my-consume-info :data="complaintmodule" :task="tasklist" v-if="formType==='complaintmodule'"/>
                <!--新的消费投诉登记-->
                <!--<new-my-consume-info
                        :data="complaintmodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='complaintmodule'"
                />-->
                <el-collapse class="accordion" v-model="processingCollapse">
                    <el-collapse-item name="processing">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    待审批处理反馈
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <template v-if="formType==='comeconomicmodule'">
                            <el-form label-width="150px">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">涉及主体：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">涉及主体类别和类型：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col class="br"></el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">属实情况：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">调查情况：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col class="br"></el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">处理结果：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col class="br"></el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">附件清单：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </template>
                        <template v-else>
                            <el-form label-width="150px">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">处理结果：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                    <el-col class="br"></el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-col :span="8" class="bt">附件清单：</el-col>
                                        <el-col :span="14" class="st"></el-col>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </template>
                    </el-collapse-item>
                </el-collapse>
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
                <el-row>
                    <el-col style="height: 15px;"></el-col>
                    <el-col style="text-align: center;">
                        <el-button type="primary" @click="toReview('同意延期')">
                            同意延期
                        </el-button>
                        <el-button type="primary" @click="toReview('不同意延期')">
                            不同意延期
                        </el-button>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane v-if="isReview" name="reviewDom" :label="reviewForm.isPass==='同意延期'?'同意延期':'不同意延期'">
                <el-form :model="reviewForm" label-width="180px" ref="reviewForm" label-suffix="："
                         :rules="reviewFormRules">
                    <el-form-item label="是否通过" class="form_bt" prop="isPass" style="text-align: left;">
                        <div style="line-height: 35px">
                            {{reviewForm.isPass}}
                        </div>
                    </el-form-item>
                    <el-form-item label="审核意见" class="form_bt" prop="resolution" style="width: 80%"
                                  color="black" background-color="taansparent" border-color="lightsteelblue">
                        <el-input type="textarea" :rows="4" placeholder="请输入审核意见（3000字内）"
                                  v-model="reviewForm.resolution"></el-input>
                        <el-button @click="template">意见模板</el-button>
                        <!--透底灰框黑字  color="black" background-color="taansparent" border-color="lightsteelblue"-->
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="toSubmit">确认</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import {next, searchBaseInfo} from '../../api/api';

    import Tracking from '../../components/Tracking'
    import EconomyInfoLocal from './EconomyInfoLocal'
    import consultInfoLocal from './consultInfoLocal'
    import ConsumeInfoLocal from './ConsumeInfoLocal'
    //引入新的组件
    import NewEconomyInfoLocal from "../sldj/components/EconomyInfo";
    import NewconsultInfo from "../sldj/components/commommoduleInfo";
    import NewComplaintmoduleInfo from "../sldj/components/ComplaintmoduleInfo"

    export default {
        components: {
            'my-tracking': Tracking,
            'my-economy-info': EconomyInfoLocal,
            'my-consult-info': consultInfoLocal,
            'my-consume-info': ConsumeInfoLocal,
            "new-my-economy-info": NewEconomyInfoLocal,
            "new-my-consult-info": NewconsultInfo,
            "new-my-consume-info": NewComplaintmoduleInfo,
        },
        data() {
            return {

                loading: true,
                formType: '',
                formTypeStr: '',
                processingCollapse: ['processing','tracking'],
                opinionList: [],
                aictabs: '0',
                commommodule: {
                    id: '',
                    caseguid: '',
                    formtype: '',
                    businesstype: '',
                    name: '',
                    gender: '',
                    rqstnumber: '',
                    linknumber: '',
                    informationOpen: '',
                    address: '',
                    usertype: '',
                    focus: '',
                    appealsource: '',
                    rqsttitle: '',
                    rqstcontent: '',
                    attachfilename: '',
                },
                comeconomicmodule: {
                    id: '',
                    caseguid: '',
                    formtype: '',
                    rqsttitle: '',
                    rqstperson: '',
                    gender: '',
                    businesstype: '',
                    rqstaddress: '',
                    rqstnumber: '',
                    linknumber: '',
                    usertype: '',
                    rqsttype: '',
                    focus: '',
                    providelb: '',
                    appealsource: '',
                    informationOpen: '',
                    subjectname: '',
                    subjectphone: '',
                    aicstationid: '',
                    postcode: '',
                    subjectaddress: '',
                    subjectzcadd: '',
                    objname: '',
                    brand: '',
                    objclassify: '',
                    objmoney: '',
                    producecompany: '',
                    isweb: '',
                    importmark: '',
                    typespecification: '',
                    quantity: '',
                    measureunit: '',
                    price: '',
                    purchasingdate: '',
                    problemtype: '',
                    illegalrules: '',
                    eventlevel: '',
                    problemdescribe: '',
                    keyword: '',
                    isoutmall: '',
                    isfeedback: '',
                    isaward: '',
                    attachfilename: '',
                    updatedate: '',
                },
                complaintmodule: {
                    id: '',
                    caseguid: '',
                    formtype: '',
                    rqsttitle: '',
                    rqstperson: '',
                    gender: '',
                    rqstnumber: '',
                    linknumber: '',
                    rqstaddress: '',
                    businesstype: '',
                    usertype: '',
                    answertype: '',
                    providetype: '',
                    providelb: '',
                    focus: '',
                    appealsource: '',
                    informationOpen: '',
                    subjectname: '',
                    subjectphone: '',
                    aicstationid: '',
                    postcode: '',
                    subjectaddress: '',
                    subjectzcadd: '',
                    objclassify: '',
                    objname: '',
                    brand: '',
                    objmoney: '',
                    producecompany: '',
                    isweb: '',
                    problemtype: '',
                    problemdescribe: '',
                    keyword: '',
                    eventlevel: '',
                    differserv: '',
                    briefing: '',
                    anestimate: '',
                    economicloss: '',
                    tortclassify: '',
                    personalinjury: '',
                    injurydegree: '',
                    injuries: '',
                    consumptionsafety: '',
                    consumptionsafetytype: '',
                    even: '',
                    reduction: '',
                    isoutmall: '',
                    attachfilename: '',
                    updatedate: '',
                },
                reviewForm: {
                    isPass: undefined,
                    resolution: '',
                },
                reviewFormRules: {
                    isPass: [
                        {required: true, message: '请选择'}
                    ],
                    resolution: [
                        {required: true, message: '请输入处理意见', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000字符', trigger: 'blur'},
                    ]
                },
                isReview: false,
                tasklist: {},
            };
        },
        methods: {

            template() {
                let isPass = this.reviewForm.isPass
                console.log(isPass)
                if (isPass == '同意延期') {
                    this.reviewForm.resolution = '经审核,相关条件符合延期要求,予以延期处理'
                } else {
                    this.reviewForm.resolution = '经审核,相关条件不符合延期要求,不予以延期处理'
                }
            },

            flow({name}) {
                if ('reviewDom' !== name) {
                    this.isReview = false;
                }
            },
            toReview(value) {
                this.isReview = true
                this.aictabs = 'reviewDom'
                this.reviewForm.isPass = value
            },
            toSubmit() {
                this.$refs.reviewForm.validate((valid) => {
                    if (!valid) return
                    this.$confirm('请确定无误提交?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let data;
                        let user = localStorage.getItem('user');
                        if ("commommodule" === this.formType) {
                            data = this.commommodule
                        } else if ("complaintmodule" === this.formType) {
                            data = this.complaintmodule
                        } else if ("comeconomicmodule" === this.formType) {
                            data = this.comeconomicmodule
                        }
                        let para = {
                            user: JSON.parse(user),
                            node: 'leaderApr',
                            nextStepForm: this.nextStepForm,
                            formType: this.formType,
                            data,
                            nextNodeId: 'handleFeedback',
                        }
                        next(para).then((data) => {
                            if (data.code === 0) {
                                 this.$message({
                                    message: '操作成功！',
                                    type: 'success',
                                    duration:'1000'
                                });
                                 this.$router.push('/wddb/bxtdb')
                            }
                        });
                    })
                })
            }
        },
        created() {

            //获取id，否则跳转
            let {id, caseguid} = this.$route.query
            if (!id || !caseguid) {
                this.$router.push('/wddb/bxtdb')
                return
            }
            let systemsource = '01'
            // //获取工单信息
            searchBaseInfo({id, caseguid, systemsource}).then((res) => {
                if (res.code === 0) {
                    if (res.data.economyform != null) {//经济表单
                        this.formType = 'comeconomicmodule'
                        this.formTypeStr = '经济违法行为表单'
                        this.comeconomicmodule = res.data.economyform
                    } else if (res.data.complaintform != null) {//消费投诉表单
                        this.formType = 'complaintmodule'
                        this.formTypeStr = '消费投诉表单'
                        this.complaintmodule = res.data.complaintform
                    } else if (res.data.consultationform != null) {//咨询登记
                        this.formType = 'commommodule'
                        this.formTypeStr = '咨询登记'
                        this.commommodule = res.data.consultationform
                    } else {//找不到对的工单信息
                        this.$alert('找不到对应的工单信息！', '提示', {
                            confirmButtonText: '确定',
                            type: 'warning',
                        });
                        this.$router.push('/wddb/bxtdb')
                    }
                    this.opinionList = res.data.opinionList
                    this.tasklist = res.data.tasklist
                    this.loading = false
                }
            }).catch((error) => {
                this.$alert('请求失败，请重试！', '提示', {
                    confirmButtonText: '确定',
                    type: 'error',
                });
            })

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
