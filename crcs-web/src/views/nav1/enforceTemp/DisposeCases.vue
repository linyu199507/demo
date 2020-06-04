<template>
    <section class="otherAcceptanceEnforce" id="whole">
        <el-tabs type="border-card" style="width: 96%;margin: 15px auto;" @tab-click="flow" v-model="aictabs">
            <el-form v-if="aictabs == 0">
                <el-row style="text-align: right;" v-if="ismain" :class="{'fixed':scrollTop>height}">
                    <el-col>
                        <el-form-item >
                            <!--word导出-->
                            <el-button
                                    type="primary"
                                    size="small"
                                    icon="document"
                                    @click="exportToWord"
                                    :loading="exportLoading"
                                    style="margin: 2px; border-radius: 13px"
                            >
                                <template v-if="exportLoading">正在导出...</template>
                                <template v-else>
                                    <i class="fa fa-download"></i>
                                    Word导出
                                </template>
                            </el-button>

                            <el-button type="primary" @click="toAccept('立案处理')">
                                立案处理
                            </el-button>
                            <el-button type="primary" @click="toAccept('退回')">
                                退回
                            </el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="ismains" :class="{'fixed':scrollTop>height}">
                    <el-col style="text-align: right;">
                        <el-button type="primary" @click="toAccept('意见填写')">意见填写</el-button>
                    </el-col>
                    <el-col style="height: 15px;"></el-col>
                </el-row>
            </el-form>
            <el-tab-pane label="经济违法行为表单" >
                <el-collapse v-model="processInofActiveNames" class="accordion">
                    <new-my-economy-info
                            :comeconomicmodule="comeconomicmodule"
                            :task="tasklist"
                            :casedata="caseprocesform"
                            v-if="formType==='comeconomicmodule'"
                    />
                    <!--<el-collapse-item name="1">-->
                        <!--<div slot="title" @click.stop="">-->
                            <!--<el-row class="el-accordion">-->
                                <!--<el-col :span="12">-->
                                    <!--1.系统信息-->
                                <!--</el-col>-->
                                <!--<el-col :span="10" style="color: #48576a;text-align: right">-->
                                    <!--&lt;!&ndash;表单号：{{tasklist.serialnum}}&ndash;&gt;-->
                                    <!--工单号：{{tasklist.tserialnum}}-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->
                        <!--<el-form :model="economyform" label-width="150px" ref="economyform">-->
                            <!--<el-row>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">登记编号：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.registrationnumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">记录类型：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.formtype}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                            <!--&lt;!&ndash; 新增 2019年09年26&ndash;&gt;-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">是否紧急：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.worktype}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">标题：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.rqsttitle}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">登记日期：</el-col>-->
                                    <!--&lt;!&ndash;<el-col :span="14" class="st">{{economyform.recorddate?moment(economyform.recorddate).format('YYYY-MM-DD'):''}}</el-col>&ndash;&gt;-->
                                    <!--<el-col :span="14" class="st">{{economyform.recorddate | formatDate}}</el-col>-->
                                <!--</el-col>-->

                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">信息来源：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.appealsource}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">接收方式：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.receivemode}}</el-col>-->
                                <!--</el-col>-->

                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">业务分类：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.businesstype}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">工单分类：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.focus}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">来电号码：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.telephonenumber}}</el-col>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</el-form>-->
                    <!--</el-collapse-item>-->
                    <!--<el-collapse-item name="2">-->
                        <!--<div slot="title" @click.stop="">-->
                            <!--<el-row class="el-accordion">-->
                                <!--<el-col :span="12">-->
                                    <!--2.举报人信息-->
                                <!--</el-col>-->
                                <!--<el-col :span="10" style="color: #48576a;text-align: right">-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->
                        <!--<el-form :model="economyform" label-width="150px" ref="subjectForm">-->
                            <!--<el-row>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">姓名/单位：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.formtype}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">举报人联系电话：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.linknumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">性别：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.gender }}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">举报人地址：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.rqstperson}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">证件类型：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.certificate}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">证件号码：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.idnumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">提供方类型：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.rqstaddress}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">年龄段：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.age}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">电子邮箱：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.informerpostcode}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">国籍：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.nationality}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">民族：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.focus}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">身份：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.status}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">邮政编码：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.appealsource}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">其他联系方式：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.othercontacts}}</el-col>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</el-form>-->
                    <!--</el-collapse-item>-->
                    <!--<el-collapse-item name="3">-->
                        <!--<div slot="title" @click.stop="">-->
                            <!--<el-row class="el-accordion">-->
                                <!--<el-col :span="12">-->
                                    <!--3.被举报对象-->
                                <!--</el-col>-->
                                <!--<el-col :span="10" style="color: #48576a;text-align: right">-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->
                        <!--<el-form :model="economyform" label-width="150px" ref="passengerForm">-->
                            <!--<el-row>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">被举报对象名称：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.subjectname}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">市场主体类型：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.providetype}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">所在区域：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.region}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">被举报对象地址：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.subjectaddress}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">联系人：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.reportedcontact}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">联系电话：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.reportedcontactnumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">社会信用代码：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.registerno}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">行业分类：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.industryname}}</el-col>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</el-form>-->
                    <!--</el-collapse-item>-->
                    <!--<el-collapse-item name="4">-->
                        <!--<div slot="title" @click.stop="">-->
                            <!--<el-row class="el-accordion">-->
                                <!--<el-col :span="12">-->
                                    <!--4.业务信息-->
                                <!--</el-col>-->
                                <!--<el-col :span="10" style="color: #48576a;text-align: right">-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->
                        <!--<el-form :model="economyform" label-width="150px" ref="businessForm">-->
                            <!--<el-row>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">客体类别：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.objectcategory}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">商品/服务名称：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.objname}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">涉及问题类型：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.reportprovidelb}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">涉及涉及违法行为：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.illegalrules}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">品牌：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.brand}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">销售方式：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.sellingformat}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">订单号：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.ordernumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">产品名称：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.productname}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">批准文号：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.approvalnumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">产品批号：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.lotnumber}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">产品规格：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.typespecification}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">生产厂家：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.producecompany}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">销售企业：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.salesenterprise}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">产品有效期：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.expirationdate?moment(economyform.expirationdate).format('YYYY-MM-DD'):''}}</el-col>-->
                                    <!--&lt;!&ndash;<el-col :span="14" class="st">{{economyform.expirationdate | formatDate}}</el-col>&ndash;&gt;-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">关键字：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.keyword}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">举报内容：</el-col>-->
                                    <!--<el-col :span="14" class="st">{{economyform.problemdescribe}}</el-col>-->
                                <!--</el-col>-->
                                <!--<el-col class="br"></el-col>-->
                                <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                    <!--<el-col :span="8" class="bt">相关附件：</el-col>-->
                                    <!--<el-col :span="14" class="st"><el-button size="small" @click="toUploadOpen()" type="primary"> 附件下载</el-button></el-col>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</el-form>-->
                    <!--</el-collapse-item>-->
                </el-collapse>
                <el-collapse class="accordion" v-model="processInofs">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    办理跟踪
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <my-tracking :data="askdepthandleList"/>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <el-tab-pane label="立案处理" v-if="caseup">
                <el-collapse v-model="registrationActiveNames" class="accordion">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    涉及主体信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right;">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="caseprocesform" label-width="180px"
                                 ref="caseprocesformMain"
                                 :rules="caseprocesformRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="涉及主体：" class="bt" prop="subjectname">
                                        <el-input placeholder="请输入涉及主体" v-model="caseprocesform.subjectname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="涉及主体类别和类型：" class="bt" prop="subjecttypecodeid">
                                        <el-cascader filterable
                                                placeholder="请选择涉及主体类别和类型"
                                                :options="subjecttype1"
                                                v-model="caseprocesform.subjecttypecodeid"
                                                @change="handleChangesubjecttype1"
                                                :show-all-levels="false"
                                                class="bt1">
                                        </el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="经营地址：" class="bt" prop="businessaddress">
                                        <el-input placeholder="请输入经营地址" v-model="caseprocesform.businessaddress"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="核查经营地址：" class="bt" prop="semakbusinessaddress">
                                        <el-input placeholder="请输入核查经营地址" v-model="caseprocesform.semakbusinessaddress"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="核查经营名称：" class="bt" prop="semaknama">
                                        <el-input placeholder="请输入核查经营名称" v-model="caseprocesform.semaknama"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>

                    <el-collapse-item name="3">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    案件信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right;">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="caseprocesform" label-width="180px"
                                 ref="economyformInformation"
                                 :rules="caseprocesformRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="是否立案：" class="bt" prop="isregister">
                                        <el-select
                                                placeholder="请选择是否立案"
                                                v-model="caseprocesform.isregister"
                                                @change="isregisterChange(caseprocesform.isregister)"
                                                class="input-text">
                                            <el-option label="是" value="是"></el-option>
                                            <el-option label="否" value="否"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>

                    <el-collapse-item name="2">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    初查情况
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right;">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="caseprocesform" label-width="180px"
                                 ref="caseprocesformInitial"
                                :rules="caseprocesformRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="属实情况：" class="bt mb20" prop="isverified">
                                        <el-select
                                                placeholder="请选择属实情况"
                                                v-model="caseprocesform.isverified"
                                                class="input-text">
                                            <el-option label="属实" value="属实"></el-option>
                                            <el-option label="不属实" value="不属实"></el-option>
                                            <el-option label="待定" value="待定"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="处理期限：" class="bt mb20" prop="limitdate">
                                        <el-input
                                                placeholder="请输入处理期限"
                                                v-model.number="caseprocesform.limitdate"
                                                class="input-text"
                                        >
                                            <template slot="append">工作日</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="调查情况:" class="bt mb20" prop="verifiedopinion">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 4, maxRows: 6}"
                                                placeholder="请输入调查情况(1200字以内)"
                                                v-model="caseprocesform.verifiedopinion">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>

                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;width: 100%;">
                            <el-button type="primary" @click="toDispose()">
                                确定
                            </el-button>
                            <el-button @click="flow('accept')">取消</el-button>
                        </el-col>
                    </el-row>
                </el-collapse>
            </el-tab-pane>
            <el-tab-pane label="退回" v-if="back">
                <el-form :model="nextStepForm" ref="nextStepForm" label-width="180px" style="width: 96%;margin: 0 auto">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                            <el-form-item label="退回描述:" class="bt">
                                <el-input
                                        type="textarea"
                                        :autosize="{ minRows: 4, maxRows: 6}"
                                        placeholder="请输入...(1200字以内)"
                                        v-model="economyform.text">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="toDispose()">确定</el-button>
                            <el-button @click="flow()">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="意见填写" v-if="opinionWrite">
                <el-form
                        :model="opinionForm"
                        label-width="180px"
                        ref="opinionForm"
                        label-suffix="："
                        :rules="opinionFormRules"
                >
                    <el-form-item label="意见" prop="opinion" style="width: 80%" class="bt">
                        <el-input
                                type="textarea"
                                v-model="opinionForm.opinion"
                                placeholder="请输入意见（3000字内）"
                                :rows="4"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="copyOpinion">确认</el-button>
                        <el-button @click="flow()">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
        <el-dialog
                :title="fileUploadText"
                :visible.sync="fileUploadVisible"
                size="tiny">
            <el-upload :auto-upload="false" :file-list="fileList" ref="upload"
                       action="https://jsonplaceholder.typicode.com/posts/"
            >
                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button size="small" type="success" @click="submitUpload">确认上传
                </el-button>
            </el-upload>
        </el-dialog>
    </section>
</template>
7
<script>
    import {searchBaseInfo,next,selectBaseCodeTree,selectBaseCodeByCodeid} from '../../../api/api';
    import Tracking from '../../../components/Tracking'
    import EconomyInfoLocal from '../../acceptanceRegistration/EconomyInfoLocal'
    import consultInfoLocal from '../../acceptanceRegistration/consultInfoLocal'
    import ConsumeInfoLocal from '../../acceptanceRegistration/ConsumeInfoLocal'
    import moment from 'moment'
    //引入新的组件
    import NewEconomyInfo from "../../sldj/components/EconomyInfo";
    import NewconsultInfo from "../../sldj/components/commommoduleInfo";
    import NewComplaintmoduleInfo from "../../sldj/components/ComplaintmoduleInfo"
    import {myMixin} from "../../../assets/mixin/mixin"

    export default {
        mixins:[myMixin],
        components: {
            'my-tracking': Tracking,
            'my-economy-info': EconomyInfoLocal,
            'my-consult-info': consultInfoLocal,
            'my-consume-info': ConsumeInfoLocal,
            "new-my-economy-info": NewEconomyInfo,
            "new-my-consult-info": NewconsultInfo,
            "new-my-consume-info": NewComplaintmoduleInfo,

        },
        data() {
            return {
                formType:"",
                tasklist:{},
                exportLoading:false,
                subjecttype1:[],//涉及主体类别和类型下拉框选项

                moment,
                cities: [{
                    value: '同意受理',
                    label: '同意受理'
                }, {
                    value: '驳回',
                    label: '驳回'
                }, {
                    value: '转移',
                    label: '转移'
                }],
                value6: '',

                dept: [{
                    value: 'consent',
                    label: '企管科'
                }, {
                    value: 'reject',
                    label: '消保科'
                }, {
                    value: 'transfer',
                    label: '广告科'
                }],
                reasonBack: [
                    {
                        value: "缺失要素",
                        label: "缺失要素"
                    },
                    {
                        value: "表述不清",
                        label: "表述不清"
                    },
                    {
                        value: "类型错误",
                        label: "类型错误"
                    },
                    {
                        value: "地址不祥",
                        label: "地址不祥"
                    },
                    {
                        value: "已有知识",
                        label: "已有知识"
                    },
                    {
                        value: "重复受理",
                        label: "重复受理"
                    },
                    {
                        value: "不属本职",
                        label: "不属本职"
                    },
                    {
                        value: "市民撤诉",
                        label: "市民撤诉"
                    }
                ],
                value7: '',
                postpone:false,
                coupleBack:false,
                assign:false,
                back:false,
                caseup:false,
                aictabs:'0',
                opinionWrite:false,

                registrationActiveNames: ['1', '2', '3', '4', '5'],
                processInofActiveNames: ['1', '2', '3', '4', '5','6'],
                processInofs:['1'],
                activeNameas: 'consumption1',
                nextStepForm: {},
                opinionForm: {},
                opinionFormRules: {
                    opinion: [{ required: true, message: "请输入内容", trigger: "blur" }]
                },
                ismain: true,
                ismains:false,
                //立案处理页
                caseprocesform:{
                    id: "",
                    caseguid: "",
                    nodeid: "registerCase",
                    subjectname: "",
                    subjecttype: "",
                    isverified: "",
                    verifiedopinion: "",
                    isregister: "",
                    ispursuit: "",
                    handleopinion: "",
                    updatedate: "",
                    businessaddress:"",
                    semaknama:"",
                    semakbusinessaddress:"",
                    mediationnumber:"",
                    infringementtype:"",
                    infringingmarks:"",
                    mediationmethods:"",
                    noobligation:"",
                    resultsofmediation:"",
                    disputemoney:"",
                    mentalmoney:"",
                    doublemoney:"",
                    financialloss:"",
                    transferreddate:"",
                    mediationis:"",
                    attachfilename:"",
                    subjecttypecodeid: "",
                    infringementtypecodeid: "",
                    infringingmarkscodeid: "",
                    noobligationcodeid: "",
                    mediationmethodscodeid: "",
                    limitdate:"",

                },
                //校验
                caseprocesformRules:{
                    subjectname: [
                        { required: true, message: "请输入涉及主体", trigger: "blur" },
                        { max: 50, message: "不能超出50个字符", trigger: "blur" }
                    ],
                    subjecttypecodeid: [
                        { required: true, message: "请选择涉及主体类别和类型"},
                    ],
                    businessaddress: [
                        { required: true, message: "请输入经营地址", trigger: "blur" },
                        { max: 100, message: "不能超出100个字符", trigger: "blur" }
                    ],
                    semakbusinessaddress: [
                        { required: true, message: "请输入核查经营地址", trigger: "blur" },
                        { max: 100, message: "不能超出100个字符", trigger: "blur" }
                    ],
                    semaknama: [
                        { required: true, message: "请输入核查经营名称", trigger: "blur" },
                        { max: 100, message: "不能超出100个字符", trigger: "blur" }
                    ],
                    isverified: [
                        { required: true, message: "请选择属实情况", trigger: "change" },
                    ],
                    limitdate: [
                        { type: "number",required: true, message: "请输入处理期限", trigger: "blur"},
                        // {  message: "不能超出100天", trigger: "blur" }
                    ],
                    verifiedopinion: [
                        { required: true, message: "请输入调查情况(1200字以内)", trigger: "blur" },
                        { max: 1200, message: "不能超出1200个字符", trigger: "blur" }
                    ],
                    isregister: [
                        { required: true, message: "请选择是否立案", trigger: "change" },
                    ],
                },
                //是否紧急

                economyform: {
                    id: '',
                    caseguid: '',
                    formtype: '举报',
                    reportedcontactnumber:'',
                    certificate:'',
                    rqsttitle: '',
                    rqstperson: '',
                    gender: '',
                    reportedcontact:'',
                    registerno:'',
                    nationality:'',
                    informerpostcode:'',
                    productname:'',
                    reportprovidelb:'',
                    objectcategory:'',
                    sellingformat:'',
                    ordernumber:'',
                    approvalnumber:'',
                    lotnumber:'',
                    salesenterprise:'',
                    expirationdate:'',
                    industryname:'',
                    othercontacts:'',
                    telephonenumber:'',
                    receivemode:'',
                    recorddate:'',
                    registrationnumber:'',
                    businesstype: '',
                    rqstaddress: '',
                    rqstnumber: '',
                    linknumber: '',
                    usertype: '',
                    providetype: '',
                    focus: '',
                    providelb: '',
                    appealsource: '',
                    informationOpen: [],
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
                    worktype:''
                },
                askdepthandleList:{},
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                textarea: '',

                activeName: 'word',
                wordList: [{
                    filename: '文档1.docx',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '重要的文档，包含某某'
                }, {
                    filename: '文档2.docx',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '重要的文档，包含某某'
                }],
                pictureList: [{
                    filename: '图片1.jpg',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '图片'
                }, {
                    filename: '图片2.jpg',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '图片'
                }],
                videoList: [{
                    filename: '视频1.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '视频'
                }, {
                    filename: '视频2.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '视频'
                }],
                otherList: [{
                    filename: '录音1.mp3',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '音频'
                }, {
                    filename: '录音2.mp3',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '音频'
                }],
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: '',
                flows:true,
                offerForm: {},
                basicForm: {},
                sourceForm: {},
                providerForm: {},
                mainForm: {},
            };
        },
        methods: {
            arraySpanMethod({ row, column, rowIndex, columnIndex }) {
                if (rowIndex % 2 === 0) {
                    if (columnIndex === 0) {
                        return [1, 2];
                    } else if (columnIndex === 1) {
                        return [0, 0];
                    }
                }
            },

            objectSpanMethod({ row, column, rowIndex, columnIndex }) {
                if (columnIndex === 0) {
                    if (rowIndex % 2 === 0) {
                        return {
                            rowspan: 2,
                            colspan: 1
                        };
                    } else {
                        return {
                            rowspan: 0,
                            colspan: 0
                        };
                    }
                }
            },

            toUploadOpen(type) {
                if (type === 'word') {
                    this.fileUploadText = '文档上传'
                } else if (type === 'image') {
                    this.fileUploadText = '图片上传'
                } else if (type === 'video') {
                    this.fileUploadText = '视频上传'
                } else if (type === 'other') {
                    this.fileUploadText = '其他文件上传'
                }
                this.fileUploadVisible = true
            },
            submitUpload() {
                this.$refs.upload.submit();
            },

            //立案处理页的确定按钮
            toDispose(){
                let validation;
                this.$refs["caseprocesformMain"].validate(valid => (validation = valid));
                this.$refs["caseprocesformInitial"].validate(valid => (validation = valid && validation));
                this.$refs["economyformInformation"].validate(valid => (validation = valid && validation));
                if (!validation) {
                    return;
                }
                let user = localStorage.getItem('user');
                let nextNodeId
                if("是" == this.caseprocesform.isregister){
                    nextNodeId = 'registerCase'
                }else {
                    nextNodeId = 'noRegisterCase'
                }
                this.caseprocesform.subjecttypecodeid=this.caseprocesform.subjecttypecodeid[this.caseprocesform.subjecttypecodeid.length-1].toString();
                let para ={
                    user: JSON.parse(user),
                    node: this.$route.query.node,
                    nextStepForm:this.nextStepForm,
                    formType:this.formType,
                    nextNodeId:nextNodeId,
                    data:this.comeconomicmodule,
                    opinion:this.caseprocesform.verifiedopinion,
                    caseprocesform:this.caseprocesform
                }
                next(para).then((res) => {
                    let para = {
                        id: this.$route.query.id,
                        caseguid: this.$route.query.caseguid,
                        //下一节点为下个环节的当前节点
                        node:nextNodeId,
                    };
                    this.$router.push({path:'/notDealListDetail/qtsl', query: para})
                });
            },

            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1])
                this.caseprocesform.subjecttype = label
                this.caseprocesform.subjecttypecodeid=array
            },
            getLabel(children,codeid){
                for (const child of children) {
                    if(codeid===child.value){
                        return child.label
                    }
                    if(child.children){
                        let label= this.getLabel(child.children,codeid)
                        if(label){
                            return label
                        }
                    }
                }
            },

            //显示新增界面
            nextStep: function () {
                this.addFormVisible = true;
                this.addForm = {
                };
            },
            flow(visible) {
                this.aictabs = '0'
                this.caseup = false
                this.back = false
            },

            toAccept(value) {
                this.aictabs = '1'
                if("立案处理" == value){
                    this.caseup = true
                    if (this.comeconomicmodule != null && this.comeconomicmodule != "") {
                        //linyu
                        this.caseprocesform.subjectname = this.comeconomicmodule.subjectname,//涉及主体
                        this.caseprocesform.semaknama = this.comeconomicmodule.cksubjectaddress,//核查经营名称
                        this.caseprocesform.semakbusinessaddress = this.comeconomicmodule.cksubjectname,//核查经营地址
                        this.caseprocesform.businessaddress = this.comeconomicmodule.subjectaddress//经营地址
                    }

                }else if("意见填写" == value){
                    this.opinionWrite = true
                }else {
                    this.back = true
                }
            },
            //获取级联选择器下级菜单
            getLevels(items){
                let list=[]
                for (const i in items) {
                    let data={
                        value:items[i].codeid,
                        label: items[i].comment,
                    }
                    if(items[i].basecodeforms.length>0){
                        data.children=this.getLevels(items[i].basecodeforms)
                    }
                    list.push(data)
                }
                return list
            },

            //涉及主体类别和类型级联数据
            getSubjecttype(){
                let params={
                    type:"enterprise12345",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(data => {
                    this.data=data.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item={
                            value:this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if(this.data[i].basecodeforms.length>0){
                            item.children=this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data=list
                    this.subjecttype1=this.data//业务分类
                });
            },

            selectDate() {
                let params = {
                    providetype: this.economyform.providetype
                }
                selectBaseCodeByCodeid(params).then(data => {
                });
            },
            copyOpinion() {
                //抄送人意见填写
                let validation = false;
                this.$refs.opinionForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let user = localStorage.getItem('user');
                    let params = {
                        user: JSON.parse(user),
                        caseguid:this.economyform.caseguid,
                        node: this.$route.query.node,
                        nextNodeId: "copyToOpinion",
                        opinion: this.opinionForm.opinion,
                        ismain:"0"
                    };
                    next(params).then(data => {
                        if (data.code === 0) {
                            this.$router.push('/wddb/bxtdb')
                        } else {
                            this.errorAlert();
                        }
                        this.submitLoading = false;
                    });
                });
            },
            //word导出
            exportToWord() {
                //query传参,另一页面进行获取方法
                const querystring = require("querystring");
                let str = querystring.stringify({
                    caseguid: this.$route.query.caseguid,
                    formType: this.formType,
                    tserialnum: this.tasklist.tserialnum,
                    systemsource: this.tasklist.systemsource
                });
                window.open("/crcs/businessSearch/wordexport?" + str);
            },
            isregisterChange(value){
                if("否" == value){
                    this.caseprocesform.limitdate = 3
                }else{
                    this.caseprocesform.limitdate = ""
                }
            },
        },



        //时间戳转时间
        filters: {
            formatDate: function (value) {
                let date = new Date(value);
                let y = date.getFullYear();
                let MM = date.getMonth() + 1;
                MM = MM < 10 ? ('0' + MM) : MM;
                let d = date.getDate();
                d = d < 10 ? ('0' + d) : d;
                let h = date.getHours();
                h = h < 10 ? ('0' + h) : h;
                let m = date.getMinutes();
                m = m < 10 ? ('0' + m) : m;
                let s = date.getSeconds();
                s = s < 10 ? ('0' + s) : s;
                return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
            }
        },

        mounted() {
            this.getSubjecttype();

        },

        created() {
            if ("0" == this.$route.query.ismain) {
                this.ismain = false;
                this.ismains = true;
            }
            let para = {
                id:this.$route.query.id,
                caseguid:this.$route.query.caseguid,
                systemsource:'01',
            };
            this.caseprocesform.caseguid = this.$route.query.caseguid,
            searchBaseInfo(para).then((res) =>  {
                if(res.code == 0){
                        this.comeconomicmodule = res.data.economyform
                        this.askdepthandleList = res.data.opinionList
                        this.comeconomicmodule.worktype = res.data.tasklist.worktype;
                        this.tasklist=res.data.tasklist
                        this.formType="comeconomicmodule"
                }
                let params = {
                    providetype:this.comeconomicmodule.providetype
                }

                selectBaseCodeByCodeid(params).then(res => {
                    this.comeconomicmodule.providetype=res.data.data[0].comment
                });
            });
        },

    };
</script>
<style lang="scss" scoped>
    .otherAcceptanceEnforce {
        overflow-x: hidden;

        .el-table__body-wrapper{
            overflow: hidden;
        }
        .bt {
            /*margin: 5px auto;*/
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
        .st{
            margin: 0 auto;
            font-size: 13px;
            /*text-align: center;*/
            margin-left: 15px;
        }

        .br {
            padding-bottom: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }
    }
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .mb20 {
        margin-bottom: 20px!important;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .input-text {
        width: 100%;
    }
    .el-input input{
        height: 30px;
    }
    .el-form-item{
        margin-bottom: 9px;
    }
        .el-col-lg-12 {
            margin: 10px 0;
        }
        .el-collapse-item__content {
            padding:10px 0;
            background: #ccc;

        }


</style>
