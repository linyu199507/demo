<template>
    <section class="leaderAudit">
        <el-row style="margin-top: 0;margin-bottom: 5px">
            <el-col>
                <el-steps :active="4" align-center center space="22%">
                    <el-step title="工单审核"></el-step>
                    <el-step title="工单受理"></el-step>
                    <el-step title="工单处理"></el-step>
                    <el-step title="延时申请审批"></el-step>
                    <el-step title="结果反馈"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-tabs type="border-card" style="width: 96%;margin: 0 auto;" @tab-click="flow" v-model="aictabs">
            <el-tab-pane label="基本信息">
                <el-collapse v-model="activeNames" class="accordion">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>01</em> 定单信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    定单号：19083459210
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="customerinfo" label-width="150px" ref="customerinfo">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-col :span="8" class="bt">接听坐席：</el-col>
                                    <el-col :span="12" class="st">{{customerinfo.seatsname}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-col :span="8" class="bt">接登记时间：</el-col>
                                    <el-col :span="14" class="st">{{customerinfo.seatsregistrationtime}}</el-col>
                                </el-col>
                                <el-col class="br"></el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-col :span="8" class="bt">性别：</el-col>
                                    <el-col :span="14" class="st">{{customerinfo.seatssex}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-col :span="8" class="bt">语种：</el-col>
                                    <el-col :span="14" class="st">{{customerinfo.seatslanguage}}</el-col>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="2" v-if="economy">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>02</em> 经济违法行为举报表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：19083459210-01
                                </el-col>
                            </el-row>
                        </div>
                        <el-tabs type="card" v-model="economicsActiveName">
                            <el-tab-pane label="登记信息" name="registration">
                                <el-collapse v-model="registrationActiveNames" class="accordion">
                                    <el-collapse-item name="1">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    提供方信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">表单类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.formType}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">标题：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.rqsttitle}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">举报人姓名：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.rqstperson}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">年龄：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.age}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">性别：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.gender}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">民族：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.nation}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">来电号码：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.rqstnumber}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联系电话：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.linknumber}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">身份：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.status}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">证件类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.certificate}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">证件号码：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.idnumber}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">职业：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.career}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">工作单位：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.company}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">反映类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.usertype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">回复方式：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.answertype}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">工单分类：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.focus}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">类别：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.providetype}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联投诉票据：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.omplaintpaper}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">残疾人标识：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.disabilityidentification}}</el-col>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">提供方类别：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.objmoney}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">举报人地址：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.rqstaddress}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联系地址：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.contactaddress}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="2">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及主体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">名称：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.subjectname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联系方式：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.linktype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">核查名称：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.cksubjectname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">经营地址：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.subjectaddress}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">身份：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.status}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">核查经营地址：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.cksubjectaddress}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="3">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及客体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品/服务名称：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.objname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品品牌：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.brand}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及客体分类：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.objclassify}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及金额：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.amobjmoneyount}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">生产企业：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.producecompany}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否网购：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.isweb}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品进口标志：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.importmark}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">型号规格：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.typespecification}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品数量：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.quantity}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">计量单位：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.measureunit}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">计商品/服务价格：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.price}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">购买日期：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.purchasingdate}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="4">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    业务具体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及问题分类：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.problemtype}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉嫌违法行为：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.illegalrules}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">事件级别：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.eventlevel}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">关键字：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.keyword}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">政务标签：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.govlabel}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">业务分类：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.differserv}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">侵权分类：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.tortclassify}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">案值：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.anestimate}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">经济损失值：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.economicloss}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">人生伤害类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.personalinjury}}</el-col>>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">人生伤害确认程度：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.injurydegree}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">受伤人数：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.injuries}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">消防安全确认程度：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.consumptionsafety}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">消防安全事件类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.consumptionsafetytype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                               <!-- <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">绝产亩地：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.even}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">减少亩地：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.reduction}}</el-col>
                                                </el-col>-->
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">简要情况：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.briefing}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-col :span="8" class="bt" style="margin-left: -180px" >问题描述：</el-col>
                                                    <el-col :span="14" class="st" style="width: 75%">{{economyform.problemdescribe}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="5">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    处理相关信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否出现场：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.isoutmall}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否反馈：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.isfeedback}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否需要奖励：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.isaward}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">相关附件：</el-col>
                                                    <el-col :span="14" class="st"></el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                </el-collapse>
                            </el-tab-pane>
                            <el-tab-pane label="处理信息" name="handle">
                                <el-collapse v-model="processInofActiveNames" class="accordion">
                                    <el-collapse-item name="1">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    处理信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="220px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">登记单编号：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.number}}</el-col>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">案由：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.cause}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="2">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及主体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="economyform" label-width="220px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">是否具有营业执照：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.registration}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">统一社会信用代码/注册号：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.registration}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">所在行业类别：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.industryCategory}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">市场名称：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.marketName}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">联系方式：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.contactMode}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">地址：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.manageAddr}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">是否转移其他部门：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.department}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">应急处置进展状态：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.progress}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">反馈登记人：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.registrar}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">协助单位类型：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.unitType}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">办案人员：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.registrar}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">办案人员号码：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.registrarPhone}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">书式案卷存放位置：</el-col>
                                                    <el-col :span="14" class="st">{{economyform.seat}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="3">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    奖励信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="awardForm" label-width="150px" ref="awardForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="检测报告号：" class="bt">
                                                        <el-input v-model="awardForm.id"  readonly
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="领奖人身份证号：" class="bt">
                                                        <el-input v-model="awardForm.IDCard" placeholder="请输入" readonly
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="奖励金额：" class="bt">
                                                        <el-input v-model="awardForm.amount" placeholder="请输入" readonly
                                                                  class="input-text">
                                                            <template slot="append">万元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="奖励价值：" class="bt">
                                                        <el-input v-model="awardForm.cost" placeholder="请输入" readonly
                                                                  class="input-text">
                                                            <template slot="append">万元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="4">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    初步意见
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="opinionForm" label-width="150px" ref="opinionForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="处理意见：" class="bt">
                                                        <el-input v-model="opinionForm.text" placeholder="请输入" readonly
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                                                    <el-form-item label="附件清单：" class="bt">
                                                        <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                                                            选择文件
                                                        </el-button>
                                                    </el-form-item>
                                                </el-col>

                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="5">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    分派下级
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="assignForm" label-width="150px" ref="assignForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="成员单位：" class="bt">
                                                        <el-select v-model="assignForm.appear"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="消保" value="1"></el-option>
                                                            <el-option label="12315" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="分派意见：" class="bt">
                                                        <el-input v-model="assignForm.describe"
                                                                  placeholder="请输入分派意见（500字以内）"
                                                                  type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                </el-collapse>
                            </el-tab-pane>
                        </el-tabs>
                    </el-collapse-item>

                    <el-collapse-item name="2" v-if="complaint">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>02</em>  消费投诉表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：19083459210-01
                                </el-col>
                            </el-row>
                        </div>
                        <el-tabs type="card" v-model="economicsActiveName">
                            <el-tab-pane label="登记信息" name="registration">
                                <el-collapse v-model="registrationActiveNames" class="accordion">
                                    <el-collapse-item name="1">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    提供方信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">表单类型：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.formType}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">标题：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.rqsttitle}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">表单模板：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.formtype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">投诉人姓名：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.rqstperson}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">性别：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.gender}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">来电号码：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.rqstnumber}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联系电话：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.linknumber}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">反映类型：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.usertype}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">回复方式：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.answertype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">类型：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.providetype}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">类别：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.providelb}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">工单分类：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.focus}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">区域：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.region}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">投诉人地址：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.subjectzcadd}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="2">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及主体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">名称：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.subjectname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">联系电话：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.subjectphone}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">经营地址：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.subjectaddress}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">实际注册地址：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.subjectzcadd}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">核查名称：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.cksubjectname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">核查经营地址：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.cksubjectaddress}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="3">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及客体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品/服务名称：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.objname}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">商品品牌：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.brand}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及客体分类：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.objclassify}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及金额：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.objmoney}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">生产企业：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.prodproducecompanyuce}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否网购：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.isweb}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="4">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    业务具体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">涉及问题分类：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.problemtype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-col :span="8" class="bt" style="margin-left: -188px">问题陈述：</el-col>
                                                    <el-col :span="14" class="st" style="width: 75%">{{complaintform.problemdescribe}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">关键字：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.keyword}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">事件级别：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.problemtype}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">政务标签：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.govlabeloutput}}</el-col>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="5">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    处理相关信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="195px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-col :span="8" class="bt">是否出现场：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.isoutmall}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="8" :lg="24">
                                                    <el-form-item label="相关附件：" class="bt">
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                </el-collapse>
                            </el-tab-pane>
                            <el-tab-pane label="处理信息" name="handle">
                                <el-collapse v-model="processInofActiveNames" class="accordion">
                                    <el-collapse-item name="1">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    处理信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="220px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">登记单编号：</el-col>
                                                    <el-col :span="14" class="st">{{comeconomicmodule.number}}</el-col>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">案由：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.cause}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="2">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    涉及主体信息
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="220px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">是否具有营业执照：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.license}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">统一社会信用代码/注册号：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.registration}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">所在行业类别：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.industryCategory}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">市场名称：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.marketName}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">联系方式：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.phone}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">地址：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.addr}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">是否转移其他部门：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.department}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">应急处置进展状态：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.progress}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">反馈登记人：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.registrar}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">协助单位类型：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.unitType}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">办案人员：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.registrar}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">办案人员号码：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.registrarPhone}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">群体投诉案件：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.license}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">涉外投诉案件：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.license}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">对投诉中发现违法行为实施行政处理：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.license}}</el-col>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                                    <el-col :span="8" class="bt">是否转立案：</el-col>
                                                    <el-col :span="14" class="st">{{complaintform.license}}</el-col>
                                                </el-col>
                                                <el-col class="br"></el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="4">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    初步意见
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="处理意见：" class="bt">
                                                        <el-input v-model="complaintform.text" placeholder="请输入" readonly
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                                                    <el-form-item label="附件清单：" class="bt">
                                                        <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                                                            选择文件
                                                        </el-button>
                                                    </el-form-item>
                                                </el-col>

                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="5">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    分派下级
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="成员单位：" class="bt">
                                                        <el-select v-model="complaintform.appear"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="消保" value="1"></el-option>
                                                            <el-option label="12315" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="分派意见：" class="bt">
                                                        <el-input v-model="assignForm.describe"
                                                                  placeholder="请输入分派意见（500字以内）"
                                                                  type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                </el-collapse>
                            </el-tab-pane>
                        </el-tabs>
                    </el-collapse-item>

                    <el-collapse-item name="2" v-if="commom">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>02</em> 通用表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：19083459210-01
                                </el-col>
                            </el-row>
                        </div>
                        <el-tabs type="card" v-model="economicsActiveName">
                            <el-tab-pane label="登记信息" name="registration">

                                <el-form :model="commomform" label-width="150px" ref="commomform">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">表单类型：</el-col>
                                            <el-col :span="14" class="st">{{commomform.formtype}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">表单模板：</el-col>
                                            <el-col :span="14" class="st">{{commomform.formmodule}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">标题：</el-col>
                                            <el-col :span="14" class="st">{{commomform.rqsttitle}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">姓名：</el-col>
                                            <el-col :span="14" class="st">{{commomform.name}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">性别：</el-col>
                                            <el-col :span="14" class="st">{{commomform.gender}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">是否有效：</el-col>
                                            <el-col :span="14" class="st">{{commomform.isvalid}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">来电号码：</el-col>
                                            <el-col :span="14" class="st">{{commomform.rqstnumber}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">联系电话：</el-col>
                                            <el-col :span="14" class="st">{{commomform.linknumber}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">反映类型：</el-col>
                                            <el-col :span="14" class="st">{{commomform.usertype}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">回复方式：</el-col>
                                            <el-col :span="14" class="st">{{commomform.answertype}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">企业类型：</el-col>
                                            <el-col :span="14" class="st">{{commomform.entitytype}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">企业全称：</el-col>
                                            <el-col :span="14" class="st">{{commomform.entname}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">企业登记机关：</el-col>
                                            <el-col :span="14" class="st">{{commomform.aicid}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-col :span="8" class="bt">企业经营地址：</el-col>
                                            <el-col :span="14" class="st">{{commomform.address}}</el-col>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">工单分类：</el-col>
                                            <el-col :span="14" class="st">{{commomform.focus}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                            <el-col :span="8" class="bt" style="margin-left: -188px">问题描述：</el-col>
                                            <el-col :span="14" class="st" style="width: 75%">{{commomform.rqstcontent}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">政务标签：</el-col>
                                            <el-col :span="14" class="st">{{commomform.govlabel}}</el-col>
                                        </el-col>
                                        <el-col class="br"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-col :span="8" class="bt">相关附件：</el-col>
                                            <el-col :span="14" class="st">{{commomform.govlabel}}</el-col>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-tab-pane>
                            <el-tab-pane label="处理信息" name="handle">
                                <el-collapse v-model="processInofActiveNames" class="accordion">
                                    <el-collapse-item name="4">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    初步意见
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="opinionForm" label-width="150px" ref="opinionForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="处理意见：" class="bt">
                                                        <el-input v-model="opinionForm.text" placeholder="请输入"
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                                                    <el-form-item label="附件清单：" class="bt">
                                                        <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                                                            选择文件
                                                        </el-button>
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                    <el-collapse-item name="5">
                                        <div slot="title" @click.stop="">
                                            <el-row class="el-accordion">
                                                <el-col :span="12">
                                                    分派下级
                                                </el-col>
                                                <el-col :span="10" style="color: #48576a;text-align: right">
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <el-form :model="assignForm" label-width="150px" ref="assignForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="成员单位：" class="bt">
                                                        <el-select v-model="assignForm.appear"
                                                                   placeholder="请选择成员单位..."
                                                                   class="input-text">
                                                            <el-option label="消保" value="1"></el-option>
                                                            <el-option label="12315" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="分派意见：" class="bt">
                                                        <el-input v-model="assignForm.describe"
                                                                  placeholder="请输入分派意见（500字以内）"
                                                                  type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                            </el-row>
                                        </el-form>
                                    </el-collapse-item>
                                </el-collapse>
                            </el-tab-pane>
                        </el-tabs>
                    </el-collapse-item>

                    <el-collapse-item name="3">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion title_accordion">
                                <el-col :span="12">
                                    <em>03</em> 处办要求
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="handledemand" label-width="220px" ref="handledemand">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">初步意见应反馈时限：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.opiniontimeshould}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">初步意见反馈时间：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.opiniontime}}</el-col>
                                </el-col>
                                <el-col class="br"></el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">应受理时限：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.accepttimeshould}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">应受理到期时间：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.accepttime}}</el-col>
                                </el-col>
                                <el-col class="br"></el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">应立案时限：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.caseputtimeshould}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-col :span="8" class="bt">应立案到期时间：</el-col>
                                    <el-col :span="14" class="st">{{handledemand.caseputtime}}</el-col>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="4">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>04</em> 办理跟踪
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-table
                                :data="opinionList" border
                                style="width: 100%">
                            <el-table-column type="expand">
                                <template slot-scope="props">
                                    <el-form inline class="demo-table-expand" label-width="100px">
                                        <el-form-item label="步骤">
                                            <span>{{ props.row.nodename }}</span>
                                        </el-form-item>
                                        <el-form-item label="处理部门">
                                            <span>{{ props.row.handledept }}</span>
                                        </el-form-item>
                                        <el-form-item label="处理人员">
                                            <span>{{ props.row.handler }}</span>
                                        </el-form-item>
                                        <el-form-item label="操作">
                                            <span>{{ props.row.operate}}</span>
                                        </el-form-item>
                                        <el-form-item label="处理时间">
                                            <span>{{ props.row.handletime }}</span>
                                        </el-form-item>
                                        <el-form-item label="处理意见">
                                            <span>{{ props.row.handleopinion }}</span>
                                        </el-form-item>
                                    </el-form>
                                </template>
                            </el-table-column>
                            <el-table-column type="index" label="序号" width="70"></el-table-column>
                            <el-table-column
                                    label="步骤"
                                    prop="nodename">
                            </el-table-column>
                            <el-table-column
                                    label="处理部门"
                                    prop="handledept">
                            </el-table-column>
                            <el-table-column
                                    label="处理人员"
                                    prop="handler">
                            </el-table-column>
                            <el-table-column
                                    label="操作"
                                    prop="operate">
                            </el-table-column>
                            <el-table-column
                                    label="处理时间"
                                    prop="handletime" :formatter="formData">
                            </el-table-column>
                            <el-table-column
                                    label="处理意见"
                                    prop="handleopinion"
                                    show-overflow-tooltip>
                            </el-table-column>
                        </el-table>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>

            <!--材料上传开始-->
            <el-tab-pane label="材料上传">
                <el-tabs v-model="activeName" class="file-tabs">
                    <el-tab-pane label="文档" name="word">
                        <!--                        <el-button size="small" @click="toUploadOpen('word')"><i class="fa fa-upload"></i> 上传-->
                        <!--                        </el-button>-->
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="wordList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="图片" name="image">
                        <!--                        <el-button size="small" @click="toUploadOpen('image')"><i class="fa fa-upload"></i> 上传-->
                        <!--                        </el-button>-->
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="imageList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="视频" name="video">
                        <!--<el-button size="small" @click="toUploadOpen('video')"><i class="fa fa-upload"></i> 上传
                        </el-button>-->
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="videoList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="其他" name="other">
                        <!--                        <el-button size="small" @click="toUploadOpen('other')"><i class="fa fa-upload"></i> 上传-->
                        <!--                        </el-button>-->
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="otherList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>

            <el-tab-pane label="审核" v-if="approved">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <table style="width: 96%;border-color: #fbfdff;margin: 20px auto;margin-top: 15px" cellspacing="0" cellpadding="0" border>
                        <tr style="width: 100%;height: 65px;">
                            <td  style="width: 20%;height:120px;text-align: center;font-weight: bold;"><span style="color: #e64242">*</span>审核意见：</td>
                            <td  style="width: 80%">
                                <input style="width: 99.5%;height: 120px;" placeholder="请输入内容">
                            </td>
                        </tr>
                    </table>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="$router.push('/wddb/12345db')">
                                同意
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="退回原因" v-if="fallBack">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <table style="width: 96%;border-color: #fbfdff;margin: 20px auto;margin-top: 15px" cellspacing="0" cellpadding="0" border>
                        <tr style="width: 100%;height: 65px;">
                            <td  style="width: 20%;height:120px;text-align: center;font-weight: bold;"><span style="color: #e64242">*</span>退回理由：</td>
                            <td  style="width: 80%">
                                <input style="width: 99.5%;height: 120px;" placeholder="请输入内容">
                            </td>
                        </tr>
                    </table>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="$router.push('/wddb/tsldb/gdcl')">
                                退回
                            </el-button>
                        </el-col>
                    </el-row>
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
                <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
            </el-upload>
        </el-dialog>
        <el-col style="height: 15px;"></el-col>
        <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto" v-show="flows">
            <el-col style="text-align: center;">
                <el-button type="primary" @click="toAccept(2)">
                    审核通过
                </el-button>
                <el-button type="primary" @click="toAccept(0)">
                    审核不通过
                </el-button>
            </el-col>
        </el-form>
    </section>
</template>

<script>

    import {selectBusinessBaseInfo,confirmBaseInfo,selectDatagriddataId,listTasklist } from '../../../api/api';
    import moment from 'moment'

    export default {
        name: "particulars",
        data() {
            return {

                    moment,
                    commom:false,
                    complaint:false,
                    economy:false,
                    flows: true,
                    aictabs:'0',

                    //待办表单
                    tasklist: {
                        id:'',
                        caseguid:'',
                        focus:'',
                        serialnum:'',
                        tserialnum:'',
                        rqsttitle: '',
                        accordtype: '',
                        labelname: '',
                        assigndate:'',
                        expiredate:'',
                        resulttimemin:'',
                        systemsource:'',
                        issignin:'',
                        signinname:'',
                        signinuserid:'',
                        signindeptid:'',
                        signdate:'',
                        updatedate:'',
                    },

                    //定单信息表
                    customerinfo: {
                        id: '',
                        caseguid: '',
                        seatsname:'',
                        seatsregistrationtime:'',
                        seatssex:'',
                        seatslanguage:'',
                        updatedate: '',
                        focus: '',
                        teti: '',
                        serialnum: '',
                        contractnum: '',
                        tserialnum: '',
                        rqsttitle: '',
                        accordtype: '',
                        labelname: '',
                        createdate: '',
                        resulttimemin: '',
                        status: '',

                    },



                    econmyform: {
                        id: "",
                        caseguid: "",
                        formtype: "",
                        rqsttitle: "",
                        rqstperson: "",
                        gender: "",
                        businesstype: "",
                        rqstaddress: "",
                        rqstnumber: "",
                        linknumber: "",
                        usertype: "",
                        rqsttype: "",
                        focus: "",
                        providelb: "",
                        appealsource: "",
                        informationOpen: "",
                        subjectname: "",
                        subjectphone: "",
                        aicstationid: "",
                        postcode: "",
                        subjectaddress: "",
                        subjectzcadd: "",
                        objname: "",
                        brand: "",
                        objclassify: "",
                        // objmoney: "",
                        producecompany: "",
                        isweb: "",
                        importmark: "",
                        typespecification: "",
                        quantity: "",
                        measureunit: "",
                        price: "",
                        purchasingdate: "",
                        problemtype: "",
                        illegalrules: "",
                        eventlevel: "",
                        problemdescribe: "",
                        keyword: "",
                        isoutmall: "",
                        isfeedback: "",
                        isaward: "",
                        attachfilename: "",
                        updatedate: "",
                        recorddate: "",
                        worktype: "",
                        sdexpirationdate: "",
                        expirationdate: "",
                        providetypecodeid: "",
                        businesstypecodeid: "",
                        objectcategorycodeid: "",
                        reportprovidelbcodeid: "",
                        region: ""
                    },
                    complaintform:{
                        id: "",
                        caseguid: "",
                        formtype: "",
                        rqsttitle: "",
                        rqstperson: "",
                        gender: "",
                        rqstnumber: "",
                        linknumber: "",
                        usertype: "",
                        answertype: "",
                        providetype: "",
                        providelb: "",
                        focus: "",
                        region: "",
                        age: "",
                        nation: "",
                        status: "",
                        complaintpaper: "",
                        disabilityidentification: "",
                        certificate: "",
                        codepostal: "",
                        idnumber: "",
                        career: "",
                        company: "",
                        subjectname: "",
                        linktype: "",
                        subjectphone: "",
                        aicstationid: "",
                        postcode: "",
                        jyareacode: "",
                        subjectaddress: "",
                        zcareacode: "",
                        subjectzcadd: "",
                        cksubjectname: "",
                        ckjyareacode: "",
                        cksubjectaddress: "",
                        objname: "",
                        brand: "",
                        objclassify: "",
                        objmoney: 0,
                        producecompany: "",
                        isweb: "",
                        importmark: "",
                        typespecification: "",
                        quantity: "",
                        measureunit: "",
                        price: "",
                        purchasingdate: "",
                        problemtype: "",
                        problemdescribe: "",
                        keyword: "",
                        govlabeloutput: "",
                        differserv: "",
                        briefing: "",
                        anestimate: "",
                        economicloss: "",
                        tortclassify: "",
                        personalinjury: "",
                        injurydegree: "",
                        injuries: "",
                        consumptionsafety: "",
                        consumptionsafetytype: "",
                        even: "",
                        reduction: "",
                        report: "",
                        isoutmall: "",
                        attachfilename: "",
                        updatedate: "",
                        appealsource: "",
                        informationopen: "",
                        businesstype: "",
                        eventlevel: "",
                        rqstaddress: "",
                        registrationnumber: "",
                        recorddate: "",
                        // recorddate: "",
                        receivemode: "",
                        linkaddress: "",
                        emaiaddress: "",
                        registerno: "",
                        entitytype: "",
                        shoppingmode: "",
                        servicetype: "",
                        complaintstype: "",
                        contentrs: "",
                        disputedate: "",
                        ordernumber: "",
                        businesstypecodeid: "",
                        entitytypecodeid: "",
                        servicetypecodeid: "",
                        complaintstypecodeid: "",
                        worktype: ""
                    },
                    commomform:{
                        id:'',
                        caseguid:'',
                        formtype:'',
                        formmodule:'',
                        rqsttitle:'',
                        name:'',
                        gender:'',
                        region:'',
                        isvalid:'',
                        rqstnumber:'',
                        linknumber:'',
                        usertype:'',
                        answertype:'',
                        entitytype:'',
                        entname:'',
                        aicid:'',
                        address:'',
                        focus:'',
                        focusxjfl:'',
                        rqstcontent:'',
                        govlabel:'',
                        businesstypecodeid: "",
                        updatedate:'',
                    },
                    seats: {
                        seatsname: '',
                        seatsregistrationtime: '',
                        seatssex: '',
                        seatslanguage: '',
                    },
                    handledemand:{
                        id:'',
                        caseguid:'',
                        opiniontimeshould:'',
                        opiniontimesurplus:'',
                        opiniontime:'',
                        accepttimeshould:'',
                        accepttimesurplus:'',
                        accepttime:'',
                        caseputtimeshould:'',
                        caseputtimesurplus:'',
                        caseputtime:'',
                        resulttimeshould:'',
                        resulttimesurplus:'',
                        resulttime:'',
                        opiniontimeminstr:'',
                        accepttimeminstr:'',
                        caseputtimeminstr:'',
                        resulttimeminstr:'',
                        updatedate:'',
                    },
                    //办理追踪
                    opinionList:{
                        id:'',
                        caseguid:'',
                        serialnum:'',
                        activitytype:'',
                        operatorouname:'',
                        operateusername:'',
                        opeartionstatus:'',
                        nodename:'',
                        handleopinion:'',
                        handler:'',
                        handledept:'',
                        operate:'',
                        handletime:'',
                        updatedate:'',
                    },

                cities: [{
                    value: '同意受理',
                    label: '同意受理'
                }, {
                    value: '退回',
                    label: '退回'
                }, {
                    value: '转移',
                    label: '转移'
                }, {
                    value: '不受理',
                    label: '不受理'
                }],
                dept: [{
                    value: 'consent',
                    label: '企管科'
                }, {
                    value: 'reject',
                    label: '消保科'
                }, {
                    value: 'transfer',
                    label: '广告科'
                }, {
                    value: 'endFlow',
                    label: '结束流程'
                }],
                acceptWay: [{
                    value: 'mediate',
                    label: '调解'
                },{
                    value: 'Investigation',
                    label: '查处'
                },{
                    value: 'investigate',
                    label: '调查'
                }],
                reason1: [{
                    value: 'consent',
                    label: '支付单位不正确'
                }, {
                    value: 'reject',
                    label: '可查阅知识库解决'
                }],
                reasonBack: [{
                    value: 'consent',
                    label: '缺失要素'
                }, {
                    value: 'reject',
                    label: '表述不清'
                },{
                    value: 'consent',
                    label: '缺失要素'
                }, {
                    value: 'reject',
                    label: '类型错误'
                },{
                    value: 'consent',
                    label: '地址不祥'
                }, {
                    value: 'reject',
                    label: '已有知识'
                }, {
                    value: 'reject',
                    label: '重复受理'
                },{
                    value: 'consent',
                    label: '不属本职'
                }, {
                    value: 'reject',
                    label: '市民撤诉'
                }],
                activeName: 'word',
                wordList: [{
                    filename: '文档1.docx',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '文档1。'
                }, {
                    filename: '文档2.doc',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '文档2。'
                }, {
                    filename: '文档3.docx',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '文档3。'
                }, {
                    filename: '文档4.docx',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '文档4。'
                }],
                imageList: [{
                    filename: '图片1.png',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '图片1。'
                }, {
                    filename: '图片2.jpg',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '图片2。'
                }, {
                    filename: '图片3.png',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '图片3。'
                }, {
                    filename: '图片4.svg',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '图片4。'
                }],
                videoList: [{
                    filename: '视频1.mp4',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '视频1。'
                }, {
                    filename: '视频2.mp4',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '视频2。'
                }, {
                    filename: '视频3.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '视频3。'
                }, {
                    filename: '视频4.mp4',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '视频4。'
                }],
                otherList: [{
                    filename: '文件1.png',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '文件1。'
                }, {
                    filename: '音频2.jpg',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '文件2。'
                }, {
                    filename: '文件3.png',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '文件3。'
                }, {
                    filename: '其他4.svg',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '文件4。'
                }],
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: '',

                tableData5: [{
                    id: '受理登记',
                    dept: '企管科',
                    person: '柳逐浪',
                    operation: '送部门',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理',
                    dept: '企管科',
                    person: '林修崖',
                    operation: '送审核',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理审查',
                    dept: '企管科',
                    person: '林破天',
                    operation: '送审批',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理审批',
                    dept: '局领导',
                    person: '陆云',
                    operation: '下级处理',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理结果',
                    dept: '企管科',
                    person: '柳逐浪',
                    operation: '意见反馈',
                    time: '2019-07-30',
                    opinion: '经协调，双方达成和解'
                }],

                offerForm: {
                    name: '莫风',
                    sex: '女',
                    phone: '13655060778',
                    email: '',
                    papersType: '身份证',
                    papersID: '440833199506120456',
                    addr: '顺德乐从镇新马路23号'
                },
                basicForm: {
                    type: '投诉类',
                    person: '李晓敏',
                    registrarDate: '2019-07-26',
                    formType: '消费投诉',
                    title: '股东变更',
                    word: '普通',
                    addr: '顺德乐从镇',
                    reflectType: '企业',
                    reply: '网络',
                    text: '已经核名的企业还未到工商办理新设立，想要变更股东要怎么处理',
                },
                sourceForm: {
                    number: '65000019',
                    content: '12345平台',
                    way: '网络',
                    type: '系统获取',
                    receiveDate: '2019-07-26',
                },
                comeconomicmodule: {
                    type: '维稳',
                    workType: '特殊',
                    reply: '网上',
                    reflection: '企业',
                    phone: '13832329598',
                    callNumber: '13693494839',
                    sex: '女',
                    reportName: '袁静',
                    title: '乱收费',
                    reportAddr: '佛山顺德区',
                    formType: '经济违法行为举报表单',
                    formType1: '消费投诉表单',
                    type1:'消费投诉业务-价格投诉',
                    type2:'自然人',
                    sex1:'妇女',
                    reportAddr1:'顺德区',
                    title1: '护肤品高价格问题',
                },

                commommodule:{
                    type:'通用表单',
                    template:'企业工商登记业务-其他',
                    title:'[广东财务服务网]',
                    name:'周女士',
                    sex:'女',
                    area:'顺德区',
                    judge:'是',
                    tele:'(暂未接入)',
                    phone:'13333328449',
                    reflect:'企业',
                    enterprise:'内资企业',
                    fullName:'佛山市万佳德科技有限公司',
                    registration:'顺德市场监督管理局',
                    address:'无',
                    classification:'特殊',
                    describe:'市民来电反映情况:................................',
                    label:'企业注销',

                },

                entForm: {
                    entName: '佛山市万佳德科技有限公司',
                    social: '968456255326652616',
                    entType: '股份有限公司',
                    phone: '0757-36650456',
                    representative: '刘楠',
                    examine: '佛山市万佳德科技有限公司',
                    commodity: '键盘',
                    money: '2000元',
                    brand: '联想',
                    type: '配件',
                    manufacture: '佛山南粤精品配件生产',
                    quantity: '10',
                    loginAddr: '佛山市顺德区德胜中路54号',
                    manageAddr: '佛山市顺德区德胜中路54号',
                    examineAddr: '佛山市顺德区德胜中路54号'
                },
                nextStepForm: {},
                orderInfoForm: {
                    seat: '张燕娴',
                    receiveDate: '2019-08-05 09:25:58',
                    sex: '2',
                    languages: '普通话',
                },
                inrequest: {
                    estimate: '2019-08-05 09:25:58',
                    feedback: '2019-08-06 09:25:58',
                    acceptability: '2019-08-08 10:25:58',
                    filing: '2019-08-14 19:25:58',
                    acceptabilityExpire: '2019-08-10 09:25:58',
                    filingExpire: '2019-09-01 09:25:58',
                },
                comeconomicmodule: {
                    name: '西藏保利爱家房地产经济有限公司佛山分公司',
                    phone: '13834593938',
                    manageAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    actualAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    checkManageAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    checkName: '',
                },
                comeconomicmodule: {
                    onlineStatus: '1',
                    produce: '1',
                    amount: '0',
                    passengerType: '1',
                    brand: '无',
                    goodsName: '公寓',
                },
                comeconomicmodule: {
                    describe: '市民3月22日在上述商家处购买了保利中悦',
                    label: '乱收费',
                    keyWord: '乱收费',
                    level: '',
                    action: '1',
                    type: '1',
                },
                comeconomicmodule: {
                    award: '1',
                    feedback: '1',
                    appear: '2',
                },
                comeconomicmodule: {
                    cause: '',
                    number: '1908050085901-01',
                },
                mainForm: {},
                awardForm: {},
                opinionForm: {
                    text: '收熟，已转相关科室跟进，请留意进一步回复。'
                },
                assignForm: {},
                special:false,
                checkList: [],
                fallBack:false,
                coupleBack:false,
                approved:false,
                activeNames: ['1', '2', '3', '4'],
                registrationActiveNames: ['1', '2', '3', '4', '5'],
                processInofActiveNames: ['1', '2', '3', '4', '5'],
                economicsActiveName: 'registration'
            }
        },
        methods: {
            arraySpanMethod({row, column, rowIndex, columnIndex}) {
                if (rowIndex % 2 === 0) {
                    if (columnIndex === 0) {
                        return [1, 2];
                    } else if (columnIndex === 1) {
                        return [0, 0];
                    }
                }
            },

            objectSpanMethod({row, column, rowIndex, columnIndex}) {
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
                }else {
                    this.fileUploadText = '附件上传'
                }
                this.fileUploadVisible = true
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
                formData(row, column) {
                    if(row.handletime ==null){
                        return ''
                    }else {
                        return moment(row.handletime).format('YYYY-MM-DD')
                    }
                },
            //显示新增界面
            nextStep: function () {
                this.addFormVisible = true;
                this.addForm = {};
            },
            flow(visible) {
                if (visible.index == "0") {
                    this.flows = true
                } else {
                    this.flows = false
                }
                this.approved = false
                this.fallBack = false
                if(visible.index == "2"){
                    this.aictabs = '0'
                    this.flows = true
                }
            },
            toAccept(value) {
                this.aictabs = '2'
                if(value == "0"){
                    this.fallBack  = true
                    this.flows = false
                    this.coupleBack = false
                    this.approved = false  //审核通过分页
                }else if(value == "1"){
                    this.fallBack = false
                    this.coupleBack = true
                    this.flows = false
                    this.approved = false
                }else if (value =="2"){
                    this.approved = true
                    this.fallBack  = false
                    this.flows = false
                    this.coupleBack = false
                }

            },

            onChange() {
                if (this.basicForm.word == "special") {
                    this.special = true
                } else {
                    this.special = false
                }
            },
            pass() {
                this.$alert('确定通过审核吗？', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        if(action == "confirm"){
                            this.$router.push('/wddb/tsldb/gdcl');
                        }
                    }

                });
            }
        },
            created() {
                let para = {
                    id: this.$route.query.id,
                    caseguid:this.$route.query.caseguid
                };
                selectBusinessBaseInfo(para).then((res) =>  {

                    this.tasklist = res.data.tasklist
                    this.economyform = res.data.economyform
                    this.handledemand = res.data.handledemand
                    this.customerinfo = res.data.customerinfo
                    this.opinionList = res.data.opinionList
                    this.complaintform = res.data.complaintform
                    this.commomform = res.data.commomform
                    // this.seats = res.data.seats
                    console.log(this.handledemand)
                    // this.customerinfo = res.data.customerinfo
                    if(this.complaintform != null){
                        this.commom = false,
                        this.complaint = true,
                        this.economy = false
                    }else if(this.economyform != null){
                        this.commom = false,
                            this.complaint = false,
                            this.economy = true
                    }else {
                        this.commom = true,
                        this.complaint = false,
                        this.economy = false
                    }
                });
            },
    }
</script>

<style>
    .accordion .el-collapse-item__header .el-collapse-item__header__arrow {
        position: absolute;
        top: 15px;
        right: 10px;
        z-index: 10;
        transform: rotate(90deg);
    }

    .el-collapse-item.is-active > .el-collapse-item__header .el-collapse-item__header__arrow {
        transform: rotate(-90deg);
    }

    .accordion .el-collapse-item__header {
        position: relative;
    }
</style>
<style lang="scss" scoped>
    .leaderAudit {
        margin-top: 10px;
        overflow-x: hidden;

        .el-table__body-wrapper {
            overflow: hidden;
        }

        .demo-table-expand {
            font-size: 0;
        }

        .demo-table-expand label {
            width: 90px;
            color: #99a9bf;
        }

        .demo-table-expand .el-form-item {
            margin-right: 0;
            margin-bottom: 0;
            width: 50%;
        }

        .el-input input {
            height: 30px;
        }

        .el-form-item {
            margin-bottom: 15px;
        }

        .bt {
            margin: 5px auto;
            font-size: 14px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }
        .st{
            margin: 5px auto;
            font-size: 14px;
            /*text-align: center;*/
            margin-left: 15px;
        }
        .br {
            padding-bottom: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }

        .clearfix {
            font-weight: bold;
        }

        .el-accordion {
            font-size: 14px;
            font-weight: bold;
            color: #1e1f20;
            cursor: auto;
        }

        .title_accordion {
            color: #40a9ff;
        }

        .element.style {
            margin-left: 150px;
            float: left;
        }

    }

    .input-text {
        width: 100%;
    }
</style>
