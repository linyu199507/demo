<!--本系统经济违法子组件-->
<template>
    <el-collapse v-model="collapseModel" class="economy_info_local">
        <el-collapse-item name="provider">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="6">
                        1.系统信息
                    </el-col>
                    <el-col :span="16" style="color: #48576a;text-align: right">
                        工单号：{{task.tserialnum}}
                    </el-col>
                    <el-col :span="2" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="data">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">业务性质：</el-col>
                        <el-col :span="14" class="st">经济违法举报</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-col :span="4" class="bt">标题：</el-col>
                        <el-col :span="20" class="st">{{data.rqsttitle}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">途径：</el-col>
                        <el-col :span="14" class="st">{{data.appealsource}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">移送编号：</el-col>
                        <el-col :span="14" class="st">{{data.transfernumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">地区来源：</el-col>
                        <el-col :span="14" class="st">{{data.detailaddress}}</el-col>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">所属板块：</el-col>
                        <el-col :span="14" class="st">{{data.subordinateplate}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-row  v-if="data.appealsource === '信访系统'">
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bt">登记机构：</el-col>
                            <el-col :span="14" class="st">{{data.registration}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bt">承办机构：</el-col>
                            <el-col :span="14" class="st">{{data.undertake}}</el-col>
                        </el-col>
                        <el-col class="br"></el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bt">满意度：</el-col>
                            <el-col :span="14" class="st">{{data.satisfied}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row v-if="data.appealsource === '抽查抽检'">
                        <el-col :xs="24" :sm="24" :md="16" :lg="12">
                            <el-col :span="8" class="bt">移送部门：</el-col>
                            <el-col :span="16" class="st">{{data.transdepartment}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12">
                            <el-col :span="8" class="bt">抽查单位：</el-col>
                            <el-col :span="4" class="st">{{data.checkunit}}</el-col>
                            <el-col :span="12" class="st">{{data.othersunit}}</el-col>
                        </el-col>
                        <el-col class="br"></el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12">
                            <el-col :span="8" class="bt">程度：</el-col>
                            <el-col :span="16" class="st">{{data.severity}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12">
                            <el-col :span="8" class="bt">产品类别：</el-col>
                            <el-col :span="4" class="st">{{data.productcategory}}</el-col>
                            <el-col :span="12" class="st">{{data.otherproductcategory}}</el-col>
                        </el-col>
                        <el-col class="br"></el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12">
                            <el-col :span="8" class="bt">来函单位：</el-col>
                            <el-col :span="16" class="st">{{data.incomingcommpany}}</el-col>
                        </el-col>
                    </el-row>
<!--                    <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                        <el-col :span="8" class="bt">接收方式：</el-col>-->
<!--                        <el-col :span="14" class="st">{{data.receivemode}}</el-col>-->
<!--                    </el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">业务分类：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.businesstype}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">工单分类：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.focus}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col class="br"></el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">来电号码：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.telephonenumber}}</el-col>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="subject">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        2.举报人信息
                    </el-col>
                    <el-col :span="12" style="color: #48576a;text-align: right">
                        <el-col :span="16" style="color: #48576a;text-align: right">表单号：{{task.serialnum}}</el-col>
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="subjectForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">姓名/单位：</el-col>
                        <el-col :span="14" class="st">{{data.rqstperson}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">举报人联系电话：</el-col>
                        <el-col :span="14" class="st">{{data.rqstnumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">性别：</el-col>
                        <el-col :span="14" class="st">{{data.gender}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">举报人地址：</el-col>
                        <el-col :span="14" class="st">{{data.rqstaddress}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">是否职投：</el-col>
                        <el-col :span="14" class="st">{{data.isprofessional}}</el-col>
                    </el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">证件号码：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.idnumber}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col class="br"></el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">提供方类型：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.providetype}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">年龄段：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.age}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col class="br"></el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">电子邮箱：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.informerpostcode}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">国籍：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.nationality}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col class="br"></el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">民族：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.nation}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">身份：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.status}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col class="br"></el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">邮政编码：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.postcode}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">其他联系方式：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.othercontacts}}</el-col>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="keti">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        3.被举报对象
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="passengerForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">被举报对象名称：</el-col>
                        <el-col :span="14" class="st">{{data.subjectname}}</el-col>
                    </el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">市场主体类型：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.providetype}}</el-col>-->
                    <!--</el-col>-->
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">所在区域：</el-col>
                        <el-col :span="14" class="st">{{data.region}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">被举报对象地址：</el-col>
                        <el-col :span="14" class="st">{{data.subjectaddress}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">联系人：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.reportedcontact}}</el-col>-->
                    <!--</el-col>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">联系电话：</el-col>
                        <el-col :span="14" class="st">{{data.linknumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">社会信用代码：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.registerno}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--<el-col :span="8" class="bt">行业分类：</el-col>-->
                        <!--<el-col :span="14" class="st">{{data.industryname}}</el-col>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="specific">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        4.业务信息
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="businessForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">涉及问题类型：</el-col>
                        <el-col :span="14" class="st">{{data.reportprovidelb}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">商品/服务类型：</el-col>
                        <el-col :span="14" class="st">{{data.objectcategory}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">商品/服务名称：</el-col>
                        <el-col :span="14" class="st">{{data.objname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">品牌：</el-col>
                        <el-col :span="14" class="st">{{data.brand}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">是否网购：</el-col>
                        <el-col :span="14" class="st">{{data.isweb}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">网购平台：</el-col>
                        <el-col :span="14" class="st">{{data.shoppingplatform}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">领域：</el-col>
                        <el-col :span="14" class="st">{{data.filed}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">受理通知书号：</el-col>
                        <el-col :span="14" class="st">{{data.acceptancenumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">案件转办书号：</el-col>
                        <el-col :span="14" class="st">{{data.transferbooknumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">案件接收日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.acceptdate == null">{{data.acceptdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.acceptdate != null">{{data.acceptdate?moment(data.acceptdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">初步反馈日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.feedbackdate == null">{{data.feedbackdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.feedbackdate != null">{{data.feedbackdate?moment(data.feedbackdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">案件分派日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.assignmentdate == null">{{data.assignmentdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.assignmentdate != null">{{data.assignmentdate?moment(data.assignmentdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">应立案日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.filingcasesdate == null">{{data.filingcasesdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.filingcasesdate != null">{{data.filingcasesdate?moment(data.filingcasesdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">最终反馈日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.finalfeedbackdate == null">{{data.finalfeedbackdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.finalfeedbackdate != null">{{data.finalfeedbackdate?moment(data.finalfeedbackdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">关键字：</el-col>
                        <el-col :span="14" class="st">{{data.keyword}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-col :span="4" class="bt">举报内容：</el-col>
                        <el-col :span="20" class="st">{{data.problemdescribe}}</el-col>
                    </el-col>

                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">相关附件：</el-col>
                        <el-col :span="16">
                            <el-upload
                                    :auto-upload="false"
                                    :file-list="fileList"
                                    ref="upload"
                                    :on-preview="previewFile"
                                    :data="this.requestMap"
                                    :on-success="uploadSuccess"
                                    :before-upload="beforeUpload"
                                    :multiple="true"
                                    action="/crcs/filesUpload/upload"
                                    class="upload-demo"
                                    :on-remove="handleRemove"
                            >
                                <el-button slot="trigger" size="mini" type="primary">选择附件</el-button>
                                <el-button size="mini" type="success"
                                           @click="submitUpload()"
                                >确认上传</el-button>
                            </el-upload>
                        </el-col>
                        <!--<el-col :span="14" class="st"><el-button size="small" @click="toUploadOpen()" type="primary"> 附件下载</el-button></el-col>-->
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
<!--        <el-collapse-item name="processing" v-if="casedata != null">-->
<!--            <div slot="title" @click.stop="">-->
<!--                <el-row class="el-accordion">-->
<!--                    <el-col :span="12">-->
<!--                        5.待审批处理反馈-->
<!--                    </el-col>-->
<!--                    <el-col :span="10" style="color: #48576a;text-align: right">-->
<!--                    </el-col>-->
<!--                </el-row>-->
<!--            </div>-->
<!--            <template>-->
<!--                <el-form label-width="150px">-->
<!--                    <el-row>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">涉及主体：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.subjectname}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">涉及主体类别和类型：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.subjecttype}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">经营地址：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.businessaddress}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">核查经营地址：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.semakbusinessaddress}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">核查经营名称：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.semaknama}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">处理期限：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.limitdate}}工作日</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">属实情况：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.isverified}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">调查情况：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.verifiedopinion}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">是否立案：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.isregister}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12" v-if="casedata.ispursuit != null || casedata.ispursuit != ''">-->
<!--                            <el-col :span="8" class="bt">是否销案：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.ispursuit}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
<!--                            <el-col :span="8" class="bt">处理结果：</el-col>-->
<!--                            <el-col :span="14" class="st">{{casedata.handleopinion}}</el-col>-->
<!--                        </el-col>-->
<!--                        <el-col class="br"></el-col>-->
<!--                        &lt;!&ndash;                        <el-col :xs="24" :sm="24" :md="24" :lg="12">&ndash;&gt;-->
<!--                        &lt;!&ndash;                            <el-col :span="8" class="bt">附件清单：</el-col>&ndash;&gt;-->
<!--                        &lt;!&ndash;                            <el-col :span="14" class="st"></el-col>&ndash;&gt;-->
<!--                        &lt;!&ndash;                        </el-col>&ndash;&gt;-->
<!--                    </el-row>-->
<!--                </el-form>-->
<!--            </template>-->
<!--        </el-collapse-item>-->
    </el-collapse>

</template>
<script>
    import moment from 'moment'
    import {getMaterialLists,deleteUploadFile}from"../../api/api"
    export default {
        props: ['data','task','casedata',],
        name: 'economyInfoLocal',
        data() {
            return {
                moment,
                collapseModel: ['provider', 'subject', 'keti', 'handle', 'specific','processing'],
                fileList:[],
                requestMap:{
                    caseguid:""
                }
            }
        },
        mounted(){
            this.getMaterialLists()
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
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
                if (file.response.code === 0) {
                    this.$message({
                        message: "上传成功",
                        type: "success"
                    });
                } else {
                    this.$message.error("上传失败" + file.response.msg);
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
                    ".wps"
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
            submitUpload(){
                this.requestMap.caseguid=this.task.caseguid;
                this.$refs.upload.submit();
            },
            getMaterialLists() {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.task.caseguid
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
    }
</script>

<style lang="scss">
    .economy_info_local {
        .el-collapse-item__header .el-collapse-item__header__arrow {
            position: absolute;
            top: 15px;
            right: 10px;
            z-index: 10;
            transform: rotate(90deg);
        }

        .el-collapse-item.is-active > .el-collapse-item__header .el-collapse-item__header__arrow {
            transform: rotate(-90deg);
        }

        .el-collapse-item__header {
            position: relative;
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
        .el-form .el-row {
            margin: 0!important;
        }
    }
</style>
