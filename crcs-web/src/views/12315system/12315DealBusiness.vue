<template>
    <section class="work_review mt20" v-loading="loading" id="whole" element-loading-text="拼命加载中">
        <el-card class="box-card">
            <div class="text item">
                <el-tabs v-model="activeName" type="card">
                    <el-row>
                        <el-col :span="24" class="" style="text-align: right;" :class="{'fixed':scrollTop > height}">
                            <el-button type="primary" size="medium" @click="submitForm" :loading="loading">提交
                            </el-button>
                            <!-- <el-button size="medium" class="mt20">关闭</el-button> -->
                        </el-col>
                    </el-row>
                    <el-tab-pane label="分流" name="first" style="margin: 15px auto;">
                        <el-collapse v-model="collapseValues" class="accordion">
                            <el-collapse-item name="orderInfo">
                                <div slot="title" @click.stop>
                                    <el-row class="accordion_title">
                                        <el-col :span="12">
                                            <em>1.</em> 业务基本信息
                                        </el-col>
                                        <el-col :span="10" class="right">工单号：{{tasklist.tserialnum}}</el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">业务类别：</el-col>
                                        <el-col :span="16" class="text">{{formType}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">登记时间：</el-col>
                                        <el-col
                                                :span="16"
                                                class="text"
                                        >{{$moment(formData.recorddate).format("YYYY-MM-DD HH:mm:ss")}}
                                        </el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">客体类别：</el-col>
                                        <el-col :span="16" class="text">{{formData.objectcategory}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">姓名/单位：</el-col>
                                        <el-col :span="16" class="text">{{formData.rqstperson}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">投诉人联系电话：</el-col>
                                        <el-col :span="16" class="text">{{formData.linknumber}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">投诉问题类别：</el-col>
                                        <el-col :span="16" class="text">{{formData.reportprovidelb}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">被投诉对象名称：</el-col>
                                        <el-col :span="16" class="text">{{formData.subjectname}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="24" :lg="16" class="item_col mb10">
                                        <el-col :span="4" class="label">投诉对象地址：</el-col>
                                        <el-col :span="20" class="text">{{formData.subjectaddress}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="16" class="item_col mb10">
                                        <el-col :span="4" class="label">投诉地址：</el-col>
                                        <el-col :span="20" class="text">{{formData.rqstaddress}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                        <el-col
                                                :span="3"
                                                class="label"
                                                style="text-align:center;padding-left:36px;"
                                        >附件下载：
                                        </el-col>
                                        <el-col :span="21">
                                            <ul class="ulStyle">
                                                <li
                                                        v-for="(item,index) in fileList"
                                                        :key="index"
                                                        @click="download(item.filesavepath)"
                                                >
                                                    <span>{{index + 1}}、</span>
                                                    {{item.attachfilename}}
                                                </li>
                                            </ul>
                                        </el-col>
                                    </el-col>
                                </el-row>
                            </el-collapse-item>
                        </el-collapse>

                        <el-collapse v-model="collapseValues" class="accordion" style="border-top:0">
                            <el-collapse-item v-model="businessForm" name="formContent">
                                <div slot="title" @click.stop>
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>2.</em> 投诉人分流处理
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form :model="dealBusinessForm" :rules="businessRules" ref="dealBusinessForm">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-col :xs="24" :sm="24" :md="24" :lg="10" style>
                                                <el-form-item
                                                        :label="formType==='举报'?'排查反馈期限':'初查反馈期限'"
                                                        label-width="150px"
                                                        prop="feedbackPeriod"
                                                        class="labelFontStyle"
                                                >
                                                    <el-date-picker
                                                            v-model="dealBusinessForm.feedbackPeriod"
                                                            type="date"
                                                            placeholder="选择日期"
                                                            style="width:224px"
                                                            :clearable="false"
                                                    ></el-date-picker>
                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="24" :lg="14">
                                                 <span class="fontStyle" v-if='formType==="举报"'>还剩 {{getWorkDay(dealBusinessForm.feedbackPeriod)}} 个工作日（有管辖权的市场监管部门收到举报之日起十五个工作日内予以核查，并决定是否受理）</span>
                                                <span class="fontStyle" v-if='formType==="投诉"'>还剩 {{getWorkDay(dealBusinessForm.feedbackPeriod)}} 个工作日（有管辖权的市场监管部门自收到消费者投诉之日起七个工作日内，告知投诉人是否受理）</span>
                                            </el-col>
                                        </el-col>

                                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">-->
                                            <!--<el-col :xs="24" :sm="24" :md="24" :lg="10" style>-->
                                                <!--<el-form-item-->
                                                        <!--label="办理期限："-->
                                                        <!--prop="deadlineForProcessing"-->
                                                        <!--class="labelFontStyle"-->
                                                        <!--label-width="150px"-->
                                                <!--&gt;-->
                                                    <!--<el-date-picker-->
                                                            <!--v-model="dealBusinessForm.deadlineForProcessing"-->
                                                            <!--type="date"-->
                                                            <!--placeholder="选择日期"-->
                                                            <!--style="width:224px"-->
                                                            <!--:clearable="false"-->
                                                    <!--&gt;</el-date-picker>-->
                                                <!--</el-form-item>-->
                                            <!--</el-col>-->

                                            <!--<el-col :xs="24" :sm="24" :md="24" :lg="14">-->
                                                <!--<span class="fontStyle" v-if='formType==="举报"'>市场监管部门适用一般程序处理的案件应当自立案之日起九十日内作出处理决定</span>-->
                                                <!--<span class="fontStyle" v-if='formType==="投诉"'>有管辖权的市场监管部门应当在受理消费者投诉之日起四十五日内终结调解</span>-->
                                            <!--</el-col>-->
                                        <!--</el-col>-->

                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-col :xs="24" :sm="24" :md="24" :lg="10" style>
                                                <el-form-item
                                                        label="分流情况："
                                                        prop="diversionSituation"
                                                        class="labelFontStyle"
                                                        label-width="150px"
                                                >
                                                    <el-select
                                                            v-model="dealBusinessForm.diversionSituation"
                                                            placeholder="请选择"
                                                            @change="shuntSecondOptions"
                                                    >
                                                        <el-option
                                                                v-for="item in diversionSituationOptions"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                            <el-col :xs="24" :sm="24" :md="24" :lg="14">
                                                <el-select v-model="dealBusinessForm.subDiversion" placeholder="请选择">
                                                    <el-option
                                                            v-for="item in subDiversionOptions"
                                                            :key="item.value"
                                                            :label="item.label"
                                                            :value="item.value"
                                                    ></el-option>
                                                </el-select>
                                            </el-col>
                                        </el-col>

                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-col :xs="24" :sm="24" :md="24" :lg="10">
                                                <el-form-item
                                                        v-if="dealBusinessForm.subDiversion!=='103'"
                                                        label="接收部门："
                                                        label-width="150px"
                                                        class="labelFontStyle"
                                                        prop="acceptDept"
                                                >
                                                    <el-select v-model="dealBusinessForm.acceptDept" placeholder="请选择">
                                                        <el-option
                                                                v-for="item in acceptDeptOptions"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                                <el-form-item
                                                        v-else
                                                        label="反馈人"
                                                        label-width="150px"
                                                        class="labelFontStyle"
                                                        prop="douser"
                                                >
                                                    <el-select v-model="dealBusinessForm.douser" placeholder="请选择">
                                                        <el-option label="默认" value=" "/>
                                                        <el-option label="顺德区业务处理员"
                                                                   value="be933549534742b9b0e43dffb1db2bc2"/>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-form-item label="事发地：" class="labelFontStyle" label-width="150px">
                                                <el-col :span="20" class="text">{{formData.accsce}}</el-col>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-form-item label="被投诉地址：" class="labelFontStyle" label-width="150px">
                                                <el-col :span="20" class="text">{{formData.rqstaddress}}</el-col>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-col :xs="24" :sm="24" :md="24" :lg="20">
                                                <el-form-item label="附言：" class="labelFontStyle" label-width="150px">
                                                    <el-input
                                                            type="textarea"
                                                            cols="30"
                                                            v-model="dealBusinessForm.subDesc"
                                                            class="textAreaStyle"
                                                    ></el-input>
                                                </el-form-item>
                                            </el-col>
                                        </el-col>

                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-col :xs="24" :sm="24" :md="24" :lg="20">
                                                <el-form-item label="办结审批部门类型：" label-width="150px"
                                                              class="labelFontStyle">
                                                    <el-select v-model="dealBusinessForm.deptType" placeholder="请选择">
                                                        <el-option
                                                                v-for="item in deptTypeOptions"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                        </el-col>

                                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                            <el-form-item label="操作: " class="labelFontStyle" label-width="150px">
                                                <el-radio-group
                                                        v-model="dealBusinessForm.chooseOperations"
                                                        :disabled="dealBusinessForm.subDiversion==='103'"
                                                >
                                                    <el-radio label="1">分流</el-radio>
                                                    <el-radio label="2">送审</el-radio>
                                                </el-radio-group>
                                            </el-form-item>
                                        </el-col>

                                        <!-- 如果操作是送审显示 -->
                                        <template
                                                v-if="dealBusinessForm.chooseOperations==='2'&&dealBusinessForm.subDiversion!=='103'"
                                        >
                                            <el-col :xs="24" :sm="24" :md="12" :lg="10" class="item_col mb10">
                                                <el-form-item
                                                        label="审批人："
                                                        label-width="150px"
                                                        class="labelFontStyle"
                                                        prop="approver"
                                                >
                                                    <el-select v-model="dealBusinessForm.approver" placeholder="请输入审批人">
                                                        <el-option label="顺德区业务处理员"
                                                                   value="be933549534742b9b0e43dffb1db2bc2"/>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                            <!-- 如果操作是送审显示 -->
                                            <el-col :xs="24" :sm="24" :md="12" :lg="10" class="item_col mb10">
                                                <el-form-item label="审批部门: " label-width="150px">
                                                    <el-select v-model="dealBusinessForm.approverDept"
                                                               placeholder="请输入业务部门">
                                                        <el-option label="业务部门" value="1"/>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>
                                            <!-- 如果操作是送审显示 -->
                                            <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                                <el-form-item
                                                        label-width="150px"
                                                        label="送审附言："
                                                        prop="desc"
                                                        class="labelFontStyle"
                                                >
                                                    <el-input
                                                            type="textarea"
                                                            cols="30"
                                                            v-model="dealBusinessForm.desc"
                                                            class="textAreaStyle"
                                                    ></el-input>
                                                </el-form-item>
                                            </el-col>
                                        </template>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                        </el-collapse>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </el-card>
    </section>
</template>
<script>
    import {searchBaseInfo, shuntDispatch} from "../../api/convenient12315";
    import {getMaterialLists} from "../../api/api";
    import {myMixin} from '../../assets/mixin/mixin'

    export default {
        mixins: [myMixin],
        data() {
            return {
                formData: {},
                formType: "",
                tasklist: {},
                fileList: [],
                loading: false,
                activeName: "first",
                active: "",
                caseguid: "",
                dealListDetail: {
                    registerNum: 0
                },
                businessRules: {
                    feedbackPeriod: [
                        {
                            type: "date",
                            required: true,
                            message: "请选择初查反馈期限",
                            trigger: "change"
                        }
                    ],
                    deadlineForProcessing: [
                        {
                            type: "date",
                            required: true,
                            message: "请选择办理期限",
                            trigger: "change"
                        }
                    ],
                    acceptDept: [
                        {required: true, message: "请选择接收部门", trigger: "change"}
                    ],
                    desc: [{required: true, message: "请填写送审附言", trigger: "blur"}],
                    approver: [
                        {required: true, message: "请输入审批人名称", trigger: "blur"},
                    ],
                    douser: [
                        {
                            type: "string",
                            required: true,
                            message: "请选择反馈人",
                            trigger: "change"
                        }
                    ]
                },
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "businessInfo",
                    "processTracking"
                ],
                acceptDeptOptions: [
                    {
                        value: "5689",
                        label: "市场监管组-技术部门"
                    }
                ],
                diversionSituationOptions: [
                    {
                        value: "100",
                        label: "本地市场监督管理机关"
                    },
                    {
                        value: "700",
                        label: "监督ODR企业开展和解"
                    }
                ],
                subDiversionOptions: [],
                deptTypeOptions: [
                    {
                        value: "1",
                        label: "登记部门审批"
                    },
                    {
                        value: "2",
                        label: "处理部门审批"
                    }
                ],
                businessForm: {},
                dealBusinessForm: {
                    douser: "be933549534742b9b0e43dffb1db2bc2",
                    feedbackPeriod: "",
                    deadlineForProcessing: "",
                    diversionSituation: "100",
                    acceptDept: "5689",
                    subDesc: "",
                    deptType: "2",
                    chooseOperations: "1",
                    approver: "be933549534742b9b0e43dffb1db2bc2",
                    approverDept: "1",
                    desc: "请领导审批",
                    subDiversion: "103",
                    jsUnitCode: '144060600'
                }
            };
        },
        methods: {
            getMaterialLists(caseguid) {
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
                        this.fileList = res.data.records;
                    }
                });
            },
            download(url) {
                window.open(process.env.Download_URL + url);
            },
            submitForm() {
                this.$refs.dealBusinessForm.validate(valid => {
                    if (valid) {
                        this.dispatch();
                    }
                });
            },
            findInfo(id, caseguid) {
                searchBaseInfo({id, caseguid, systemsource: "03"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;

                        if (this.nodeStr !== "todispatch") {
                            //节点与当前对应不上
                            this.$router.push("/wddb/12315db");
                            return;
                        }
                        if (data.economyform) {
                            this.formData = data.economyform;
                            this.formType = "举报";
                        } else if (data.complaintform) {
                            this.formData = data.complaintform;
                            this.formType = "投诉";
                        }
                        this.tasklist = data.tasklist;
                        this.dealBusinessForm.deadlineForProcessing = this.tasklist.firstblqx;
                        this.dealBusinessForm.feedbackPeriod = this.tasklist.expiredate;
                        this.dealBusinessForm.deadlineForProcessing = this.formData.todealwithtime;
                        this.dealBusinessForm.feedbackPeriod = this.formData.feedbacktime;
                        
                        this.loading = false;
                      
                    })
                    .catch(error => {
                        console.error(error)
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                        this.loading = false;
                    });
            },
            getWorkDay(date){
                //工作日
                let difference=this.$moment(date).diff(this.$moment(),'days')+1
                return Math.round((difference/7)*5)
            },
            dispatch() {
                let data = {
                    caseguid: this.formData.caseguid,
                    user: JSON.parse(localStorage.getItem("user")),
                    ...this.dealBusinessForm
                };
                data.feedbackPeriod = this.$moment(data.feedbackPeriod).format(
                    "YYYY-MM-DD HH:mm:ss"
                );
                data.deadlineForProcessing = this.$moment(
                    data.deadlineForProcessing
                ).format("YYYY-MM-DD HH:mm:ss");
                data.douser = data.douser.trim();
                this.loading = true;
                shuntDispatch(data)
                    .then(res => {
                        this.loading = false;
                        let params = {
                            id: this.$route.query.id,
                            caseguid: this.$route.query.caseguid,
                            ismain: this.$route.query.ismain
                        };
                        this.$message({
                            message: "提交成功！",
                            type: "success",
                            duration: "1000"
                        });
                        //工单审核
                        if ("02" == this.tasklist.formtype) {
                            this.$router.push({
                                path: "/listDetail/12315tsgdsh",
                                query: params
                            });
                        } else {
                            this.$router.push({
                                path: "/listDetail/12315jbgdsh",
                                query: params
                            });
                        }
                    })
                    .catch(error => {
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                        this.loading = false;
                    });
            },
            shuntSecondOptions(firstLevel, init) {
                if ("100" === firstLevel) {
                    this.subDiversionOptions = [
                        {
                            value: "102",
                            label: "向下分流"
                        },
                        {
                            value: "103",
                            label: "自办"
                        },
                        {
                            value: "104",
                            label: "横向分流"
                        }
                    ];
                    this.dealBusinessForm.subDiversion = "103";
                } else if ("700" === firstLevel) {
                    this.subDiversionOptions = [
                        {
                            value: "700",
                            label: "监督ODR企业开展和解"
                        }
                    ];
                    this.dealBusinessForm.subDiversion = "700";
                }
            }
        },
        mounted() {
            this.shuntSecondOptions("100", true);
        },
        created() {
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            if (!caseguid || !id) {
                this.$router.push("/wddb/12315db");
            } else {
                this.findInfo(id, caseguid);
            }
        }
    };
</script>
<style lang="scss" scoped>
    .text {
        color: #48576a;
    }

    .el-form-item {
        .el-form-item__label {
            width: 120px !important;
        }
    }

    .ulStyle {
        list-style: none;
        margin-bottom: 10px;
        color: rgb(72, 87, 106);
        margin: 0;
        padding: 0;
        cursor: pointer;

        li:hover {
            text-decoration: underline;
        }
    }

    .el-radio-group {
        padding-top: 10px;
        font-weight: normal;
    }

    .el-form-item__label {
        width: 120px !important;
    }

    .labelFontStyle {
        font-size: 13px !important;
        /* margin-left: 20px !important; */
        font-weight: 600 !important;
        line-height: 36px !important;
    }

    /* .el-form {
      padding: 0 0 0 40px;
    } */
    .ml25 {
        margin-left: 25px;
    }

    .label {
        font-size: 13px;
        color: #48576a;
        font-weight: 600;
        text-align: right;
    }

    .el-col {
        border-radius: 4px;
        line-height: 36px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .fontStyle {
        color: red;
        font-weight: bold;
        font-size: 12px;
    }

    .textAreaStyle {
        width: 80%;
        /* border: 1px solid rgb(191, 203, 217); */
        /* border-radius: 5px; */
        /* padding: 10px; */
    }
</style>
