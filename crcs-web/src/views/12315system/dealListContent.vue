<template>
    <section class="work_review mt20" v-loading="loading" element-loading-text="拼命加载中" id="whole">
        <el-card class="box-card">
            <div class="text item">
                <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                    <el-tab-pane label="基本信息" name="first">
                        <el-collapse v-model="collapseValues" class="accordion">
                            <el-collapse-item name="orderInfo">
                                <div slot="title">
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>1.</em> 定单信息
                                        </el-col>
                                        <el-col :span="10" class="right">定单号：</el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">登记编号：</el-col>
                                        <el-col :span="16">{{data.registrationnumber}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">记录类型：</el-col>
                                        <el-col :span="16">{{data.formtype}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">登记日期：</el-col>
                                        <el-col :span="16">{{data.recorddate}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">接收方式：</el-col>
                                        <el-col :span="16">{{data.receivemode}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">来电方式：</el-col>
                                        <el-col :span="16">{{data.rqstnumber}}</el-col>
                                    </el-col>
                                </el-row>
                            </el-collapse-item>
                        </el-collapse>

                        <el-collapse v-model="collapseValues" class="accordion" style="border-top:0">
                            <el-collapse-item name="formContent">
                                <div slot="title">
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>2.</em> 投诉人信息
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">姓名/单位：</el-col>
                                        <el-col :span="16">{{data.rqstperson}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">投诉人联系电话：</el-col>
                                        <el-col :span="16">{{data.linknumber}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">性别：</el-col>
                                        <el-col :span="16">{{data.gender}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">投诉人地址：</el-col>
                                        <el-col :span="16">{{data.rqstaddress}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">证件类型：</el-col>
                                        <el-col :span="16">{{data.certificate}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">证件号码：</el-col>
                                        <el-col :span="16">{{data.idnumber}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">提供方类型：</el-col>
                                        <el-col :span="16">{{data.usertype}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">年龄段：</el-col>
                                        <el-col :span="16">{{data.age}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">电子邮箱：</el-col>
                                        <el-col :span="16">{{data.informerpostcode}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">国籍：</el-col>
                                        <el-col :span="16">{{data.nationality}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">民族：</el-col>
                                        <el-col :span="16">{{data.nation}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">身份：</el-col>
                                        <el-col :span="16">{{data.status}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">邮政编码：</el-col>
                                        <el-col :span="16">{{data.postcode}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">其他联系方式：</el-col>
                                        <el-col :span="16">{{data.othercontacts}}</el-col>
                                    </el-col>
                                </el-row>
                            </el-collapse-item>
                        </el-collapse>

                        <el-collapse v-model="collapseValues" class="accordion" style="border-top:0">
                            <el-collapse-item name="officeRequirements">
                                <div slot="title">
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>3.</em> 被投诉对象
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">被投诉对象名称：</el-col>
                                        <el-col :span="16">{{data.subjectname}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">市场主体类型：</el-col>
                                        <el-col :span="16">{{data.providetype}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">社会信用代码：</el-col>
                                        <el-col :span="16">{{data.registerno}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">行业分类：</el-col>
                                        <el-col :span="16">{{data.industryname}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">联系人：</el-col>
                                        <el-col :span="16">{{data.reportedcontact}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">联系电话：</el-col>
                                        <el-col :span="16">{{data.subjectphone}}</el-col>
                                    </el-col>
<!--                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">-->
<!--                                        <el-col :span="8" class="label">证件号码：</el-col>-->
<!--                                        <el-col :span="16">{{data.}}</el-col>-->
<!--                                    </el-col>-->
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">被投诉对象地址：</el-col>
                                        <el-col :span="16">{{data.subjectaddress}}</el-col>
                                    </el-col>

<!--                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">-->
<!--                                        <el-col :span="8" class="label">电子邮箱：</el-col>-->
<!--                                        <el-col :span="16">{{data.}}</el-col>-->
<!--                                    </el-col>-->
                                </el-row>
                            </el-collapse-item>
                        </el-collapse>

                        <!-- 业务类型为投诉 -->
                        <el-collapse v-model="collapseValues" class="accordion" style="border-top:0" v-if="fromtype == 'complaintmodule'">
                            <el-collapse-item name="businessInfo">
                                <div slot="title">
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>4.</em> 业务信息
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">客体类别：</el-col>
                                        <el-col :span="16">{{data.objectcategory}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">商品/服务名称：</el-col>
                                        <el-col :span="16">{{data.objname}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">品牌：</el-col>
                                        <el-col :span="16">{{data.brand}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">销售方式：</el-col>
                                        <el-col :span="16">{{data.sellingformat}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">消费金额(元)：</el-col>
                                        <el-col :span="16">{{data.objmoney}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">订单号：</el-col>
                                        <el-col :span="16">{{data.ordernumber}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">争议发生时间：</el-col>
                                        <el-col :span="16">{{data.disputedate}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">诉求内容：</el-col>
                                        <el-col :span="16">{{data.report}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">诉求问题类别：</el-col>
                                        <el-col :span="16">{{data.complaintstype}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品名称：</el-col>
                                        <el-col :span="16">{{data.productname}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">批准文号：</el-col>
                                        <el-col :span="16">{{data.approvalnumber}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品批号：</el-col>
                                        <el-col :span="16">{{data.lotnumber}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品规格：</el-col>
                                        <el-col :span="16">{{data.typespecification}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">销售企业：</el-col>
                                        <el-col :span="16">{{data.salesenterprise}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品有效期：</el-col>
                                        <el-col :span="16">{{data.expirationdate}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">关键字：</el-col>
                                        <el-col :span="16">{{data.keyword}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                        <el-col :span="3" class="label" style="text-align:center;padding-left:36px">
                                            附件下载：
                                        </el-col>
                                        <el-col :span="21" style="text-align:left">
                                            <el-button type="primary" size="small">点击下载</el-button>
                                        </el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                        <el-col :span="3" class="label" style="text-align:center;padding-left:36px">
                                            投诉内容：
                                        </el-col>
                                        <el-col :span="21">{{data.problemdescribe}}</el-col>
                                    </el-col>

                                </el-row>
                            </el-collapse-item>
                        </el-collapse>
                        <!-- 业务类型为举报 -->
                        <el-collapse v-model="collapseValues" class="accordion" style="border-top:0" v-if="fromtype == 'comeconomicmodule'">
                            <el-collapse-item name="businessInfo">
                                <div slot="title">
                                    <el-row class="accordion_title mb10">
                                        <el-col :span="12">
                                            <em>4.</em> 业务信息
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">客体类别：</el-col>
                                        <el-col :span="16">{{data.objectcategory}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">商品/服务名称：</el-col>
                                        <el-col :span="16">{{data.objname}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">品牌：</el-col>
                                        <el-col :span="16">{{data.brand}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">销售方式：</el-col>
                                        <el-col :span="16">{{data.sellingformat}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">消费金额(元)：</el-col>
                                        <el-col :span="16">{{data.objmoney}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">订单号：</el-col>
                                        <el-col :span="16">{{data.ordernumber}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">举报问题类型：</el-col>
                                        <el-col :span="16">{{data.reportprovidelb}}</el-col>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品名称：</el-col>
                                        <el-col :span="16">{{data.productname}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">批准文号：</el-col>
                                        <el-col :span="16">{{data.approvalnumber}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品批号：</el-col>
                                        <el-col :span="16">{{data.lotnumber}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品规格：</el-col>
                                        <el-col :span="16">{{data.typespecification}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">生产厂家：</el-col>
                                        <el-col :span="16">{{data.producecompany}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">销售企业：</el-col>
                                        <el-col :span="16">{{data.salesenterprise}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">产品有效期：</el-col>
                                        <el-col :span="16">{{data.expirationdate}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col mb10">
                                        <el-col :span="8" class="label">关键字：</el-col>
                                        <el-col :span="16">{{data.keyword}}</el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                        <el-col :span="3" class="label" style="text-align:center;padding-left:36px">
                                            附件下载：
                                        </el-col>
                                        <el-col :span="21" style="text-align:left">
                                            <el-button type="primary" size="small">点击下载</el-button>
                                        </el-col>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col mb10">
                                        <el-col :span="3" class="label" style="text-align:center;padding-left:36px">
                                            举报内容：
                                        </el-col>
                                        <el-col :span="21">{{data.problemdescribe}}</el-col>
                                    </el-col>

                                </el-row>
                            </el-collapse-item>
                        </el-collapse>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </el-card>
    </section>
</template>
<script>
    import {
        searchBaseInfo,
    } from "../../api/convenient12345";
    export default {
        data() {
            return {
                loading: false,
                activeName: "first",
                dealListDetail: {
                    registerNum: 0
                },
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "businessInfo",
                    "processTracking"
                ],
                fromtype: "",
                data:{},
                tasklist:{}
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        },
        created() {
            let para = {
                id: this.$route.query.id,
                caseguid: this.$route.query.caseguid,
                systemsource: "03"
            };
            searchBaseInfo(para).then(res => {
                console.log(res)
                // this.opinionList = res.data.opinionList;
                if (res.data.tasklist != null) {
                    this.tasklist = res.data.tasklist;
                }
                if (res.data.complaintform != null) {
                    this.data = res.data.complaintform;
                    this.fromtype = "complaintmodule"
                } else if (res.data.economyform != null) {
                    this.data = res.data.economyform;
                    this.fromtype = "comeconomicmodule"
                }
                if (res.data.caseprocesform != null) {
                    this.caseprocesform = res.data.caseprocesform;
                }
                this.loading = false;
            });
        }
    };
</script>
<style lang="scss" scoped>
    .label {
        font-size: 13px;
        color: #48576a;
        font-weight: bold;
        text-align: right;
    }

    .el-col {
        border-radius: 4px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
</style>
