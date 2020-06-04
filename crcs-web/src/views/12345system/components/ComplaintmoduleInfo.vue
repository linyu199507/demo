<!--消费投诉详细子组件-->
<template>
    <el-tabs v-model="tabFormActionName" type="card">
        <el-tab-pane label="登记信息" name="tabRegister">
            <el-collapse v-model="collapseFormValues" class="accordion">
                <el-collapse-item name="provider">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">提供方信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                            <el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">标题：</el-col>
                            <el-col :span="16">{{complaintform.rqsttitle}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">表单类型：</el-col>
                            <el-col :span="16">{{complaintform.formtype}}</el-col>
                        </el-col>
                        <!--<el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">-->
                            <!--<el-col :span="8" class="label">表单模板：</el-col>-->
                            <!--<el-col :span="16">{{complaintform.businesstype}}</el-col>-->
                        <!--</el-col>-->
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">区域：</el-col>
                            <el-col :span="16">{{complaintform.region}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">投诉人姓名：</el-col>
                            <el-col :span="16">{{complaintform.rqstperson}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">性别：</el-col>
                            <el-col :span="16">{{complaintform.gender}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">投诉人地址：</el-col>
                            <el-col :span="16">{{complaintform.rqstaddress}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">来电号码：</el-col>
                            <el-col :span="16">{{complaintform.rqstnumber}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">联系电话：</el-col>
                            <el-col :span="16">{{complaintform.linknumber}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">反映类型：</el-col>
                            <el-col :span="16">{{complaintform.usertype}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">回复方式：</el-col>
                            <el-col :span="16">{{complaintform.answertype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">类别：</el-col>
                            <el-col :span="16">{{complaintform.providelb}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">工单分类：</el-col>
                            <el-col :span="16">{{complaintform.focus}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <!--<el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">-->
                            <!--<el-col :span="8" class="label">区域：</el-col>-->
                            <!--<el-col :span="16">{{complaintform.region}}</el-col>-->
                        <!--</el-col>-->
                       <!-- <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">投诉票据：</el-col>
                            <el-col :span="16">{{complaintform.omplaintpaper}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">民族：</el-col>
                            <el-col :span="16">{{complaintform.nation}}</el-col>
                        </el-col>-->
                    </el-row>
                    <!--<el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">身份：</el-col>
                            <el-col :span="16">{{complaintform.status}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">残疾人标识：</el-col>
                            <el-col :span="16">{{complaintform.disabilityidentification}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">邮政编码：</el-col>
                            <el-col :span="16">{{complaintform.codepostal}}</el-col>
                        </el-col>
                    </el-row>-->
                    <!--<el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">证件类型：</el-col>
                            <el-col :span="16">{{complaintform.certificate}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">证件号码：</el-col>
                            <el-col :span="16">{{complaintform.idnumber}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">职业：</el-col>
                            <el-col :span="16">{{complaintform.career}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">工作单位：</el-col>
                            <el-col :span="16">{{complaintform.company}}</el-col>
                        </el-col>
                    </el-row>-->
                </el-collapse-item>
                <!-- 涉及主体信息 -->
                <el-collapse-item name="subjectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">涉及主体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">名称：</el-col>
                            <el-col :span="16">{{complaintform.subjectname}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">行业分类：</el-col>
                            <el-col :span="16">{{complaintform.businesstype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">联系方式：</el-col>
                            <el-col :span="16">{{complaintform.subjectphone}}</el-col>
                        </el-col>
                        <!--<el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                          <el-col :span="8" class="label">邮编：</el-col>
                          <el-col :span="16">{{complaintform.postcode}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                          <el-col :span="4" class="label">管辖单位：</el-col>
                          <el-col :span="20">{{complaintform.aicstationid}}</el-col>
                        </el-col>-->
                        <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
                            <el-col :span="8" class="label">经营地址：</el-col>
                            <el-col :span="16">{{complaintform.subjectaddress}}</el-col>
                        </el-col>
                        <!--            <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">-->
                        <!--              <el-col :span="8" class="label">实际注册地址：</el-col>-->
                        <!--              <el-col :span="16">{{complaintform.subjectzcadd}}</el-col>-->
                        <!--            </el-col>-->
                        <!--            <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">-->
                        <!--              <el-col :span="8" class="label">核查名称：</el-col>-->
                        <!--              <el-col :span="16">{{complaintform.cksubjectname}}</el-col>-->
                        <!--            </el-col>-->
                        <!--            <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">-->
                        <!--              <el-col :span="8" class="label">核查经营地址：</el-col>-->
                        <!--              <el-col :span="16">{{complaintform.cksubjectaddress}}</el-col>-->
                        <!--            </el-col>-->
                    </el-row>
                </el-collapse-item>
                <!-- 涉及客体信息 -->
                <el-collapse-item name="objectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">涉及客体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品/服务名称：</el-col>
                            <el-col :span="16">{{complaintform.objname}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品品牌：</el-col>
                            <el-col :span="16">{{complaintform.brand}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及客体分类：</el-col>
                            <el-col :span="16">{{complaintform.objclassify}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及问题分类：</el-col>
                            <el-col :span="16">{{complaintform.complaintstype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">关键字：</el-col>
                            <el-col :span="16">{{complaintform.keyword}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及金额：</el-col>
                            <el-col :span="16">{{complaintform.objmoney}} 元</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">生产企业：</el-col>
                            <el-col :span="16">{{complaintform.producecompany}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">是否网购：</el-col>
                            <el-col :span="16">{{complaintform.isweb}}</el-col>
                        </el-col>
                        <!--<el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品进口标识：</el-col>
                            <el-col :span="16">{{complaintform.todo}}</el-col>
                        </el-col>-->
                      <!--  <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">型号规格：</el-col>
                            <el-col :span="16">{{complaintform.typespecification}}</el-col>
                        </el-col>-->
                       <!-- <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品数量：</el-col>
                            <el-col :span="16">{{complaintform.quantity}}</el-col>
                        </el-col>-->
                        <!--<el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">计量单位：</el-col>
                            <el-col :span="16">{{complaintform.measureunit}}</el-col>
                        </el-col>-->
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品/服务价格：</el-col>
                            <el-col :span="16">{{complaintform.price}} 元</el-col>
                        </el-col>
                        <!--<el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">购买日期：</el-col>
                            <el-col :span="16">{{formatDate(complaintform.purchasingdate)}}</el-col>
                        </el-col>-->
                    </el-row>
                </el-collapse-item>
                <!-- 业务具体信息 -->
                <el-collapse-item name="specificInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">业务具体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">涉及问题分类：</el-col>
                            <el-col :span="20">{{complaintform.complaintstype}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">问题描述：</el-col>
                            <el-col :span="20">{{complaintform.problemdescribe}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">事件级别：</el-col>
                            <el-col :span="20">{{complaintform.eventlevel}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">政务标签：</el-col>
                            <el-col :span="20">{{complaintform.govlabeloutput}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <!-- 处理相关信息 -->
                <el-collapse-item name="correlationInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">处理相关信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">投诉要求：</el-col>
                            <el-col :span="20">{{complaintform.report}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">是否出现场：</el-col>
                            <el-col :span="20">{{complaintform.isoutmall}}</el-col>
                        </el-col>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">相关附件：</el-col>
                            <!--<el-col :span="20">{{complaintform.attachfilename}}</el-col>-->
                            <el-upload
                                    style="float: left;"
                                    :auto-upload="false"
                                    :file-list="fileLists"
                                    ref="upload"
                                    :data="this.requestMap"
                                    :on-preview="previewFile"
                                    :on-success="uploadSuccess"
                                    :before-upload="beforeUpload"
                                    :multiple="true"
                                    action="/crcs/filesUpload/upload"
                            >
                            </el-upload>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <!--        <el-collapse-item name="processing" v-if="casedata != null">-->
                <!--          <div slot="title" @click.stop="">-->
                <!--            <el-row class="el-accordion">-->
                <!--              <el-col :span="12">-->
                <!--                待审批处理反馈-->
                <!--              </el-col>-->
                <!--              <el-col :span="10" style="color: #48576a;text-align: right">-->
                <!--              </el-col>-->
                <!--            </el-row>-->
                <!--          </div>-->
                <!--          <template>-->
                <!--            <el-form label-width="150px">-->
                <!--              <el-row>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">涉及主体：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.subjectname}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">涉及主体类别和类型：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.subjecttype}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">经营地址：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.businessaddress}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">核查经营地址：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.semakbusinessaddress}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">核查经营名称：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.semaknama}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">行政调解文书号：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.mediationnumber}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">侵权类型：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.infringingmarks}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">欺诈标志：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.infringingmarks}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">调解方式：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.mediationmethods}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">未履行义务：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.noobligation}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">调解结果：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.resultsofmediation}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">争议金额：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.disputemoney}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">精神赔偿金额：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.mentalmoney}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">加倍赔偿金额：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.doublemoney}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">挽回经济损失：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.financialloss}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">办结日期：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.transferreddate}}</el-col>-->
                <!--                </el-col>-->
                <!--                <el-col class="br"></el-col>-->
                <!--                <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                  <el-col :span="8" class="bt">调查情况：</el-col>-->
                <!--                  <el-col :span="14" class="st">{{casedata.mediationis}}</el-col>-->
                <!--                </el-col>-->
                <!--                &lt;!&ndash;                                    <el-col class="br"></el-col>&ndash;&gt;-->
                <!--                &lt;!&ndash;                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">&ndash;&gt;-->
                <!--                &lt;!&ndash;                                        <el-col :span="8" class="bt">附件清单：</el-col>&ndash;&gt;-->
                <!--                &lt;!&ndash;                                        <el-col :span="14" class="st"></el-col>&ndash;&gt;-->
                <!--                &lt;!&ndash;                                    </el-col>&ndash;&gt;-->
                <!--              </el-row>-->
                <!--            </el-form>-->
                <!--          </template>-->
                <!--        </el-collapse-item>-->
            </el-collapse>
        </el-tab-pane>
      <!--  <el-tab-pane label="处理信息" name="tabHandle">
            <el-collapse v-model="collapseFormValues" class="accordion">
                <el-collapse-item name="processingInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">处理信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">登记单编号：</el-col>
                            <el-col :span="16">{{complaintform.number}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">案由：</el-col>
                            <el-col :span="16">{{complaintform.cause}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <el-collapse-item name="processingInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">涉及主体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">是否具有营业执照：</el-col>
                            <el-col :span="16">{{complaintform.license}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">统一社会信用代码/注册号：</el-col>
                            <el-col :span="16">{{complaintform.registration}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">所在行业类别：</el-col>
                            <el-col :span="16">{{complaintform.industryCategory}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">市场名称：</el-col>
                            <el-col :span="16">{{complaintform.marketName}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">联系方式：</el-col>
                            <el-col :span="16">{{complaintform.phone}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">地址：</el-col>
                            <el-col :span="16">{{complaintform.addr}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">是否转送其他部门：</el-col>
                            <el-col :span="16">{{complaintform.department}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">应急处置进展状态：</el-col>
                            <el-col :span="16">{{complaintform.progress}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">反馈登记人：</el-col>
                            <el-col :span="16">{{complaintform.registrar}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">协助单位类型：</el-col>
                            <el-col :span="16">{{complaintform.unitType}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">办案人员：</el-col>
                            <el-col :span="16">{{complaintform.registrar}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">办案人员电话：</el-col>
                            <el-col :span="16">{{complaintform.registrarPhone}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">群体投诉案件：</el-col>
                            <el-col :span="16">{{complaintform.license}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="8" class="label">涉外投诉案件：</el-col>
                            <el-col :span="16">{{complaintform.license}}</el-col>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="11" class="item_col">
                            <el-col :span="9" class="label" style="width: 36.5%">是否转立案：</el-col>
                            <el-col :span="14">{{complaintform.license}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                            <el-col :span="10" class="label">对投诉中发现违法行为实施行政处罚：</el-col>
                            <el-col :span="14">{{complaintform.license}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>-->
    </el-tabs>
</template>

<script>

    import {getArrayByStringId} from "../../../utils/form-util";
    import { getMaterialLists } from "../../../api/convenient12345";


    export default {
        name: "ComplaintmoduleInfo",
        // props: {
        //   tasklist: Object,
        //   complaintform: Object
        // },
        props: ["tasklist", "complaintform", "casedata"],
        data() {
            return {
                // 材料列表
                fileLists: [],
                fileListsAccept:[],
                materialList: [],
                requestMap: {
                    caseguid: ""
                },
                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation",
                    "processingInformation",
                    'processing',
                    "officeInfo"
                ],
                tabFormActionName: "tabRegister"
            };
        },
        methods: {

            previewFile(file) {
                if (file.response !== undefined) {
                    console.log(file)
                    this.download(file.response.data.url)
                } else {
                    console.log(file)
                    this.download(file.url)
                }
            },

            download(url){
                // window.location.href = `${process.env.Download_URL}` + url;
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
                // console.log(file)
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 20 <= 1;
                // console.log(file.size / 1024 / 1024 / 20);
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
                    ".xlsx"
                ];
                if (arrays.indexOf(lastName) === -1 || isLt === false) {
                    this.$alert(
                        "不支持上传该格式的文件,请上传docx，doc，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                        "提示",
                        {
                            confirmButtonText: "确定"
                        }
                    );
                    return false;
                }
            },

            submitUpload() {
                console.log(11)
                this.requestMap.caseguid = this.complaintform.caseguid;
                this.$refs.upload.submit();
            },

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
                        console.log(res)
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.fileListsAccept.push(fileInfo);
                            this.fileLists.push(fileInfo);
                        }
                    }
                });
            },

            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
        },
        created() {
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            console.log(this.caseguid)
            this.getMaterialLists(this.caseguid);
            console.log(this.complaintmodule)
            console.log(this.tasklist)
        },
        mounted() {

        },
    };
</script>

<style lang="scss" scoped>
    .label {
        font-size: 13px;
        color: #48576a;
        font-weight: bold;
        text-align: right;
    }

    .item_col {
        padding: 5px 0 0 0;
    }

    .w110 {
        width: 110px;
    }

    .bt {
        margin: 10px auto;
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
        margin: 10px auto;
        font-size: 13px;
    }

    .br {
        padding-bottom: 0;
        margin-bottom: 10px;
        border-bottom: 1px solid #e8e8e8;
    }

    .el-form .el-row {
        margin: 0 !important;
    }
</style>
