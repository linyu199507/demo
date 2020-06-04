<template>
    <section class="business-create">
        <el-row style="margin-top: 5px;margin-bottom: 5px">
            <el-col>
                <el-steps :active="1" align-center center space="20%">
                    <el-step title="受理登记"></el-step>
                    <el-step title="工单审核"></el-step>
                    <el-step title="工单处理"></el-step>
                    <el-step title="延时申请审批"></el-step>
                    <el-step title="结果反馈"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-tabs type="border-card" style="width: 96%; margin: 0 auto; min-height: 400px;" @tab-click="flow"
                 v-model="aictabs">
            <el-tab-pane label="咨询登记" name="commommodule">
                <!--第一card-"基本信息"-->
                <el-collapse v-model="registrationActiveNames" class="accordion">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    表单信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="commommodule" label-width="150px" ref="commommodule" :rules="commommoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="20">
                                    <el-form-item label="表单类型:" class="bt">
                                        <div style="line-height: 35px">
                                            {{commommodule.formtype}}
                                        </div>
                                        <!--                                    <el-input  placeholder=""-->
                                        <!--                                               label="咨询"-->
                                        <!--                                               value="咨询"-->
                                        <!--                                               v-model="commommodule.formtype"-->
                                        <!--                                               class="input-text" readonly></el-input>-->
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="业务分类:" prop="businesstype" class="bt">
                                        <el-select v-model="commommodule.businesstype"
                                                   placeholder="请选择业务分类" class="input-text">
                                            <el-option label="投诉类" value="投诉类"></el-option>
                                            <el-option label="咨询类" value="咨询类"></el-option>
                                            <el-option label="执法类" value="执法类"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="姓名:" prop="name" class="bt">
                                        <el-input placeholder="请输入姓名"
                                                  v-model="commommodule.name"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="性别:" prop="gender" class="bt">
                                        <el-select v-model="commommodule.gender" placeholder="请选择性别" class="input-text">
                                            <el-option label="男" value="男"></el-option>
                                            <el-option label="女" value="女"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="bt">
                                    <el-form-item label="来电号码:" prop="rqstnumber">
                                        <el-input placeholder="请输入来电号码"
                                                  v-model="commommodule.rqstnumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="联系电话:" prop="linknumber" class="bt">
                                        <el-input placeholder="请输入联系电话"
                                                  v-model="commommodule.linknumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
<!--                                <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                    <el-form-item label="信息公开:" class="bt">-->
<!--                                        <el-checkbox-group v-model="commommodule.informationOpen" style="line-height: 35px">-->
<!--                                            <el-checkbox label="匿姓名" name="informationOpen" class="bt1"></el-checkbox>-->
<!--                                            <el-checkbox label="匿电话" name="informationOpen" class="bt1"></el-checkbox>-->
<!--                                        </el-checkbox-group>-->
<!--                                    </el-form-item>-->
<!--                                </el-col>-->
                                <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                    <el-form-item label="举报人地址:" prop="address" class="bt">
                                        <el-input placeholder="请输入举报人地址"
                                                  v-model="commommodule.address"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="反映类型:" prop="usertype" class="bt">
                                        <el-select v-model="commommodule.usertype" placeholder="请选择反映类型" class="input-text">
                                            <el-option label="个人" value="个人"></el-option>
                                            <el-option label="企业" value="企业"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="工单分类:" prop="focus" class="bt">
                                        <el-select v-model="commommodule.focus" placeholder="请输入工单分类" class="input-text">
                                            <el-option label="普通" value="普通"></el-option>
                                            <el-option label="特殊" value="特殊"></el-option>
                                            <el-option label="特派" value="特派"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="诉求来源:" prop="appealsource" class="bt">
                                        <el-select placeholder="请选择类型" class="input-text"
                                                   v-model="commommodule.appealsource">
                                            <el-option label="来函" value="来函"></el-option>
                                            <el-option label="来人" value="来人"></el-option>
                                            <el-option label="电话" value="电话"></el-option>
                                            <el-option label="传真" value="传真"></el-option>
                                            <el-option label="上级交办" value="上级交办"></el-option>
                                            <el-option label="互联网络" value="互联网络"></el-option>
                                            <el-option label="其它" value="其它"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="24" :span="16">
                                    <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                        <el-input v-model="commommodule.rqsttitle" placeholder="请输入标题"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="问题描述:" prop="rqstcontent" class="bt">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 6, maxRows: 8}"
                                                placeholder="请输入问题描述(3000字以内)"
                                                v-model="commommodule.rqstcontent">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="相关附件:" class="bt">
                                        <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary"><i
                                                class="fa fa-upload"></i>
                                            选择文件
                                        </el-button>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="2">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    表单处办
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="commommoduleOfficeForm" label-width="150px" ref="commommoduleOfficeForm"
                                 :rules="commommoduleOfficeFormRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="归档单位:" prop="unit" class="bt">
                                        <el-select placeholder="请选择归档部门" class="input-text"
                                                   v-model="commommoduleOfficeForm.unit">
                                            <el-option label="部门1" value="部门1"></el-option>
                                            <el-option label="部门2" value="部门2"></el-option>
                                            <el-option label="部门3" value="部门3"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="处办方式:" prop="way" class="bt">
                                        <el-select placeholder="请选择处办方式" class="input-text"
                                                   v-model="commommoduleOfficeForm.way">
                                            <el-option label="直接归档" value="complain"></el-option>
                                            <el-option label="直接归档" value="consult"></el-option>
                                            <el-option label="直接归档" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="处理意见:" prop="idea" class="bt">
                                        <el-input v-model="commommoduleOfficeForm.idea"
                                                  type="textarea"
                                                  :autosize="{ minRows: 6, maxRows: 8}"
                                                  placeholder="请输入处理意见(3000字以内)">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <el-row>
                    <el-col style="height: 15px;"></el-col>
                    <el-col style="text-align: center">
                        <el-button type="primary" @click="submitFrom('commommodule')">
                            暂存
                        </el-button>
                        <el-button type="primary" @click="toAccept('commommodule')">
                            受理
                        </el-button>
                        <el-button type="primary" @click="toNotAccept('commommodule')">
                            不受理
                        </el-button>
                        <el-button type="primary">
                            直接归档
                        </el-button>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="诉求登记">
                <el-tabs v-model="activeNameas" class="file-tabs" @tab-click="flow">
                    <el-tab-pane label="消费投诉表单" name="complaintmodule">
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
                                <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleProvider"
                                         :rules="complaintmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="表单类型:" class="bt">
                                                <div style="line-height: 35px">
                                                    {{complaintmodule.formtype}}
                                                </div>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="标题：" class="bt" prop="rqsttitle">
                                                <el-input placeholder="请输入标题"
                                                          v-model="complaintmodule.rqsttitle"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="举报人姓名：" class="bt" prop="rqstperson">
                                                <el-input placeholder="请输入举报人姓名"
                                                          v-model="complaintmodule.rqstperson"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="性别:" class="bt" prop="gender">
                                                <el-select v-model="complaintmodule.gender" placeholder="请选择性别"
                                                           class="input-text">
                                                    <el-option label="男" value="男"></el-option>
                                                    <el-option label="女" value="女"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="业务分类：" class="bt" prop="businesstype">
                                                <el-select placeholder="请选择业务分类"
                                                           v-model="complaintmodule.businesstype"
                                                           class="input-text">
                                                    <el-option label="举报" value="举报"></el-option>
                                                    <el-option label="投诉" value="投诉"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="举报人地址：" class="bt" prop="address">
                                                <el-input placeholder="请输入举报人地址"
                                                          v-model="complaintmodule.address"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                                                <el-input placeholder="请输入来电号码"
                                                          v-model="complaintmodule.rqstnumber"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="联系电话：" class="bt" prop="linknumber">
                                                <el-input v-model="complaintmodule.linknumber"
                                                          placeholder="请输入联系电话"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="反映类型：" class="bt" prop="usertype">
                                                <el-select placeholder="请选择反映类型"
                                                           v-model="complaintmodule.usertype"
                                                           class="input-text">
                                                    <el-option label="企业" value="企业"></el-option>
                                                    <el-option label="个人" value="个人"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="类型：" class="bt" prop="providetype">
                                                <el-select placeholder="请选择类型"
                                                           v-model="complaintmodule.providetype"
                                                           class="input-text">
                                                    <el-option label="自然人" value="自然人"></el-option>
                                                    <el-option label="企业" value="企业"></el-option>
                                                    <el-option label="机构" value="机构"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="工单分类：" class="bt" prop="focus">
                                                <el-select placeholder="请选择工单分类"
                                                           v-model="complaintmodule.focus"
                                                           class="input-text">
                                                    <el-option label="普通" value="普通"></el-option>
                                                    <el-option label="特殊" value="特殊"></el-option>
                                                    <el-option label="特派" value="特派"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="类别：" class="bt" prop="providelb">
                                                <el-select placeholder="请选择类别"
                                                           v-model="complaintmodule.providelb"
                                                           class="input-text">
                                                    <el-option label="维稳" value="维稳"></el-option>
                                                    <el-option label="督办" value="督办"></el-option>
                                                    <el-option label="催办" value="催办"></el-option>
                                                    <el-option label="疑问" value="疑问"></el-option>
                                                    <el-option label="重大" value="重大"></el-option>
                                                    <el-option label="紧急" value="紧急"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="诉求来源：" class="bt" prop="appealsource">
                                                <el-select placeholder="请选择类型" class="input-text"
                                                           v-model="complaintmodule.appealsource">
                                                    <el-option label="来函" value="来函"></el-option>
                                                    <el-option label="来人" value="来人"></el-option>
                                                    <el-option label="电话" value="电话"></el-option>
                                                    <el-option label="传真" value="传真"></el-option>
                                                    <el-option label="上级交办" value="上级交办"></el-option>
                                                    <el-option label="互联网络" value="互联网络"></el-option>
                                                    <el-option label="其它" value="其它"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
<!--                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                            <el-form-item label="信息公开:" class="bt" prop="informationOpen">-->
<!--                                                <el-checkbox-group v-model="complaintmodule.informationOpen"-->
<!--                                                                   style="line-height: 35px">-->
<!--                                                    <el-checkbox label="匿姓名" name="informationOpen"-->
<!--                                                                 class="bt1"></el-checkbox>-->
<!--                                                    <el-checkbox label="匿电话" name="informationOpen"-->
<!--                                                                 class="bt1"></el-checkbox>-->
<!--                                                </el-checkbox-group>-->
<!--                                            </el-form-item>-->
<!--                                        </el-col>-->
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
                                <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleSubject"
                                         :rules="complaintmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="名称：" class="bt" prop="subjectname">
                                                <el-input placeholder="请输入名称"
                                                          v-model="complaintmodule.subjectname"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="联系电话：" class="bt" prop="subjectphone">
                                                <el-input placeholder="请输入联系电话"
                                                          v-model="complaintmodule.subjectphone"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="管辖单位：" class="bt">
                                                <el-input placeholder="请输入管辖单位"
                                                          v-model="complaintmodule.aicstationid"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="邮编：" class="bt">
                                                <el-input placeholder="请输入邮编"
                                                          v-model="complaintmodule.postcode"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                            <el-form-item label="经营地址：" class="bt" prop="subjectaddress">
                                                <el-input placeholder="请输入经营地址"
                                                          v-model="complaintmodule.subjectaddress"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                            <el-form-item label="实际注册地址：" class="bt" prop="subjectzcadd">
                                                <el-input placeholder="请输入实际注册地址"
                                                          v-model="complaintmodule.subjectzcadd"
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
                                            涉及客体信息
                                        </el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleKeTi"
                                         :rules="complaintmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="涉及客体分类：" class="bt" prop="objclassify">
                                                <el-select placeholder="请选择涉及客体分类"
                                                           v-model="complaintmodule.objclassify"
                                                           class="input-text">
                                                    <el-option label="公寓" value="公寓"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                                <el-input placeholder="请输入商品/服务名称"
                                                          v-model="complaintmodule.objname"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品品牌：" class="bt" prop="brand">
                                                <el-input placeholder="请输入商品品牌"
                                                          v-model="complaintmodule.brand"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="涉及金额：" class="bt" prop="objmoney">
                                                <el-input placeholder="请输入涉及金额"
                                                          v-model.number="complaintmodule.objmoney"
                                                          class="input-text">
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="生产企业：" class="bt">
                                                <el-input placeholder="请输入生产企业"
                                                          v-model="complaintmodule.producecompany"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="是否网购：" class="bt" prop="isweb">
                                                <el-select
                                                        v-model="complaintmodule.isweb"
                                                        placeholder="请选择是否网购"
                                                        class="input-text">
                                                    <el-option label="是" value="是"></el-option>
                                                    <el-option label="否" value="否"></el-option>
                                                </el-select>
                                            </el-form-item>
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
                                <el-form :model="complaintmodule" label-width="180px" ref="complaintmoduleBusiness"
                                         :rules="complaintmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="涉及问题分类：" class="bt" prop="problemtype">
                                                <el-select v-model="complaintmodule.problemtype"
                                                           placeholder="请选择涉及问题分类"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="具体业务分类：" class="bt" prop="differserv">
                                                <el-select placeholder="请选择具体业务分类"
                                                           v-model="complaintmodule.differserv"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="关键字：" class="bt" prop="keyword">
                                                <el-input placeholder="请输入关键字"
                                                          v-model="complaintmodule.keyword"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="事件级别：" class="bt" prop="eventlevel">
                                                <el-select
                                                        placeholder="请选择事件级别"
                                                        v-model="complaintmodule.eventlevel"
                                                        class="input-text">
                                                    <el-option label="特别紧急事件" value="特别紧急事件"></el-option>
                                                    <el-option label="紧急事件" value="紧急事件"></el-option>
                                                    <el-option label="一般事件" value="一般事件"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="问题陈述：" class="bt" prop="problemdescribe">
                                                <el-input placeholder="请输入问题陈述"
                                                          v-model="complaintmodule.problemdescribe"
                                                          type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="简要情况：" class="bt" prop="briefing">
                                                <el-input placeholder="请输入简要情况"
                                                          v-model="complaintmodule.briefing"
                                                          type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="案值：" class="bt" prop="anestimate">
                                                <el-input placeholder="请输入案值"
                                                          v-model="complaintmodule.anestimate"
                                                          class="input-text">
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="经济损失值：" class="bt" prop="economicloss">
                                                <el-input placeholder="请输入经济损失值"
                                                          v-model="complaintmodule.economicloss"
                                                          class="input-text">
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="侵权分类：" class="bt" prop="tortclassify">
                                                <el-select placeholder="请选择侵权分类"
                                                           v-model="complaintmodule.tortclassify"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="人身伤害类型：" class="bt" prop="personalinjury">
                                                <el-select placeholder="请选择人身伤害类型"
                                                           v-model="complaintmodule.personalinjury"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="人身伤害确认程度：" class="bt" prop="injurydegree">
                                                <el-select placeholder="请选择人身伤害确认程度"
                                                           v-model="complaintmodule.injurydegree"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="受伤人数：" class="bt" prop="injuries">
                                                <el-input placeholder="请输入受伤人数"
                                                          v-model="complaintmodule.injuries"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="消费安全确认程度：" class="bt" prop="consumptionsafety">
                                                <el-select placeholder="请选择消费安全确认程度"
                                                           v-model="complaintmodule.consumptionsafety"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="消费安全事件类型：" class="bt" prop="consumptionsafetytype">
                                                <el-select placeholder="请选择消费安全事件类型"
                                                           v-model="complaintmodule.consumptionsafetytype"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="绝产亩数：" class="bt" prop="even">
                                                <el-input placeholder="请输入绝产亩数"
                                                          v-model="complaintmodule.even"
                                                          class="input-text">
                                                    <template slot="append">亩</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="减产亩数：" class="bt" prop="reduction">
                                                <el-input placeholder="请输入减产亩数"
                                                          v-model="complaintmodule.reduction"
                                                          class="input-text">
                                                    <template slot="append">亩</template>
                                                </el-input>
                                            </el-form-item>
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
                                <el-form :model="complaintmodule" label-width="180px" ref="complaintmoduleHandle"
                                         :rules="complaintmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="是否出现场：" class="bt" prop="">
                                                <el-select placeholder="请选择"
                                                           v-model="complaintmodule.isoutmall"
                                                           class="input-text">
                                                    <el-option label="是" value="是"></el-option>
                                                    <el-option label="否" value="否"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="相关附件：" class="bt" prop="">
                                                <el-button size="small" @click="toUploadOpen('word')"
                                                           class="el-button--primary"><i
                                                        v-model="complaintmodule.attachfilename"
                                                        class="fa fa-upload" style="margin: 5px auto;"></i> 选择文件
                                                </el-button>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>

                            <el-collapse-item name="6">
                                <div slot="title" @click.stop="">
                                    <el-row class="el-accordion">
                                        <el-col :span="12">
                                            表单处办
                                        </el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form :model="complaintmodule" label-width="150px" ref="orderInfoForm">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="处办方式:">
                                                <el-select placeholder="请选择处办方式" class="input-text">
                                                    <el-option label="下级科室处办" value="complain"></el-option>
                                                    <el-option label="送热线办" value="consult"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="下级科室:">
                                                <el-select
                                                        placeholder="请选择下级科室" class="input-text">
                                                    <el-option label="部门1" value="部门1"></el-option>
                                                    <el-option label="部门2" value="部门2"></el-option>

                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="处理意见:">
                                                <el-input
                                                        type="textarea"
                                                        :autosize="{ minRows: 6, maxRows: 8}"
                                                        placeholder="请输入处理意见(3000字以内)"
                                                >
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                        </el-collapse>
                        <el-form>
                            <el-row>
                                <el-col style="height: 15px;"></el-col>
                                <el-col style="text-align: center">
                                    <el-button type="primary" @click="submitFrom('complaintmodule')">
                                        暂存
                                    </el-button>
                                    <el-button type="primary" @click="toAccept('complaintmodule')">
                                        受理
                                    </el-button>
                                    <el-button type="primary" @click="toNotAccept('complaintmodule')">
                                        不受理
                                    </el-button>
                                    <el-button type="primary">
                                        直接归档
                                    </el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-tab-pane>


                    <el-tab-pane label="经济违法行为表单" name="comeconomicmodule" >
                        <el-collapse v-model="processInofActiveNames" class="accordion">
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
                                <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleProvider"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="表单类型：" class="bt">
                                                <div style="line-height: 35px">
                                                    {{comeconomicmodule.formtype}}
                                                </div>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="标题：" class="bt" prop="rqsttitle">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.rqsttitle"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="举报人姓名：" class="bt" prop="rqstperson">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.rqstperson"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="性别:" class="bt" prop="gender">
                                                <el-select v-model="comeconomicmodule.gender" placeholder="请选择性别"
                                                           class="input-text">
                                                    <el-option label="男" value="男"></el-option>
                                                    <el-option label="女" value="女"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="业务分类：" class="bt" prop="businesstype">
                                                <el-select
                                                        placeholder="请选择业务分类"
                                                        v-model="comeconomicmodule.businesstype"
                                                        class="input-text">
                                                    <el-option label="举报" value="举报"></el-option>
                                                    <el-option label="投诉" value="投诉"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="举报人地址：" class="bt"  prop="rqstaddress">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.rqstaddress"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.rqstnumber"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="联系电话：" class="bt" prop="linknumber">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.linknumber"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="反映类型：" class="bt" prop="usertype">
                                                <el-select placeholder="请选择反映类型"
                                                           v-model="comeconomicmodule.usertype"
                                                           class="input-text">
                                                    <el-option label="企业" value="企业"></el-option>
                                                    <el-option label="个人" value="个人"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="类型：" class="bt" prop="rqsttype">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.rqsttype"
                                                           class="input-text">
                                                    <el-option label="自然人" value="complain"></el-option>
                                                    <el-option label="企业" value="consult"></el-option>
                                                    <el-option label="机构" value="enforcement"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="工单分类：" class="bt" prop="focus">
                                                <el-select placeholder="请选择分类"
                                                           v-model="comeconomicmodule.focus"
                                                           class="input-text">
                                                    <el-option label="普通" value="普通"></el-option>
                                                    <el-option label="特殊" value="特殊"></el-option>
                                                    <el-option label="特派" value="特派"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="类别：" class="bt" prop="providelb">
                                                <el-select placeholder="请选择类别"
                                                           v-model="comeconomicmodule.providelb"
                                                           class="input-text">
                                                    <el-option label="维稳" value="1"></el-option>
                                                    <el-option label="督办" value="2"></el-option>
                                                    <el-option label="催办" value="3"></el-option>
                                                    <el-option label="疑问" value="4"></el-option>
                                                    <el-option label="重大" value="5"></el-option>
                                                    <el-option label="紧急" value="6"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="诉求来源：" class="bt" prop="appealsource">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.appealsource"
                                                           class="input-text">
                                                    <el-option label="来函" value="来函"></el-option>
                                                    <el-option label="来人" value="来人"></el-option>
                                                    <el-option label="电话" value="电话"></el-option>
                                                    <el-option label="传真" value="传真"></el-option>
                                                    <el-option label="上级交办" value="上级交办"></el-option>
                                                    <el-option label="互联网络" value="互联网络"></el-option>
                                                    <el-option label="其它" value="其它"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
<!--                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                            <el-form-item label="信息公开:" class="bt" prop="informationOpen">-->
<!--                                                <el-checkbox label="匿姓名" name="type" class="bt1"-->
<!--                                                             v-model="comeconomicmodule.informationOpen"-->
<!--                                                             style="margin: 8px 45px"></el-checkbox>-->
<!--                                                <el-checkbox label="匿电话" name="type" class="bt1"-->
<!--                                                             style="margin: 8px 0"></el-checkbox>-->
<!--                                            </el-form-item>-->
<!--                                        </el-col>-->
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
                                <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleSubject"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="名称：" class="bt" prop="subjectname">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.subjectname"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="联系方式：" class="bt" prop="subjectphone">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.subjectphone"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="管辖单位：" class="bt" prop="aicstationid">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.aicstationid"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="邮编：" class="bt" prop="postcode">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.postcode"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="经营地址：" class="bt" prop="subjectaddress">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.subjectaddress"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="实际注册地址：" class="bt" prop="subjectzcadd">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.subjectzcadd"
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
                                            涉及客体信息
                                        </el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleObject"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="涉及客体分类：" class="bt" prop="objclassify">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.objclassify"
                                                           class="input-text">
                                                    <el-option label="公寓" value="1"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.objname"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品品牌：" class="bt" prop="brand">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.brand"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="涉及金额：" class="bt" prop="objmoney">
                                                <el-input placeholder="请输入"
                                                          v-model.number="comeconomicmodule.objmoney"
                                                          class="input-text">
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="生产企业：" class="bt" prop="producecompany">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.producecompany"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="是否网购：" class="bt" prop="isweb">
                                                <el-select
                                                        placeholder="请选择"
                                                        v-model="comeconomicmodule.isweb"
                                                        class="input-text">
                                                    <el-option label="是" value="是"></el-option>
                                                    <el-option label="否" value="否"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品进口标志：" class="bt" prop="importmark">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.importmark"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="型号规格：" class="bt" prop="typespecification">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.typespecification"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品数量：" class="bt" prop="quantity">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.quantity"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="计量单位：" class="bt" prop="measureunit">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.measureunit"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品/服务价格：" class="bt">
                                                <el-input placeholder="请输入"
                                                          v-model.number="comeconomicmodule.price"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="购买日期：" class="bt" prop="purchasingdate">
                                                <el-date-picker
                                                        type="date"
                                                        v-model="comeconomicmodule.purchasingdate"
                                                        class="input-text"
                                                        style="width: 100%"
                                                        value-format="yyyy-MM-dd"
                                                        @change="dateChange"
                                                        placeholder="选择日期">
                                                </el-date-picker>
                                            </el-form-item>
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
                                <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleBusiness"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="涉及问题分类：" class="bt" prop="problemtype">
                                                <el-select v-model="comeconomicmodule.problemtype"
                                                           placeholder="请选择"
                                                           class="input-text">
                                                    <el-option label="分类1" value="分类1"></el-option>
                                                    <el-option label="分类2" value="分类2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="涉嫌违法行为：" class="bt" prop="illegalrules">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.illegalrules"
                                                           class="input-text">
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="事件级别：" class="bt" prop="eventlevel">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.eventlevel"
                                                           class="input-text">
                                                    <el-option label="特别紧急事件" value="1"></el-option>
                                                    <el-option label="紧急事件" value="2"></el-option>
                                                    <el-option label="一般事件" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                            <el-form-item label="关键字：" class="bt" prop="keyword">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.keyword"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="问题陈述：" class="bt" prop="problemdescribe">
                                                <el-input placeholder="请输入"
                                                          v-model="comeconomicmodule.problemdescribe"
                                                          type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                          class="input-text"></el-input>
                                            </el-form-item>
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
                                <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleHandle"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="是否出现场：" class="bt" prop="isoutmall">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.isoutmall"
                                                           class="input-text">
                                                    <el-option label="是" value="1"></el-option>
                                                    <el-option label="否" value="0"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="是否反馈：" class="bt" prop="isfeedback">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.isfeedback"
                                                           class="input-text">
                                                    <el-option label="是" value="1"></el-option>
                                                    <el-option label="否" value="0"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="是否需要奖励：" class="bt" prop="isaward">
                                                <el-select placeholder="请选择"
                                                           v-model="comeconomicmodule.isaward"
                                                           class="input-text">
                                                    <el-option label="是" value="1"></el-option>
                                                    <el-option label="否" value="0"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="相关附件:" prop="attachfilename">
                                                <el-button size="small" v-model="comeconomicmodule.attachfilename"
                                                           @click="toUploadOpen('word')" class="el-button--primary"><i
                                                        class="fa fa-upload"></i>
                                                    选择文件
                                                </el-button>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="6">
                                <div slot="title" @click.stop="">
                                    <el-row class="el-accordion">
                                        <el-col :span="12">
                                            表单处办
                                        </el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form :model="comeconomicmodule" label-width="150px" ref="orderInfoForm"
                                         :rules="comeconomicmoduleRules">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="处办方式:">
                                                <el-select placeholder="请选择处办方式" class="input-text">
                                                    <el-option label="下级科室处办" value="complain"></el-option>
                                                    <el-option label="送热线办" value="consult"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="下级科室:">
                                                <el-select placeholder="请选择下级科室" class="input-text">
                                                    <el-option label="部门1" value="部门1"></el-option>
                                                    <el-option label="部门2" value="部门2"></el-option>

                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="处理意见:">
                                                <el-input
                                                        type="textarea"
                                                        :autosize="{ minRows: 6, maxRows: 8}"
                                                        placeholder="请输入处理意见(3000字以内)"
                                                >
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                        </el-collapse>
                        <el-form>
                            <el-row>
                                <el-col style="height: 15px;"></el-col>
                                <el-col style="text-align: center">
                                    <el-button type="primary" @click="submitFrom('comeconomicmodule')">
                                        暂存
                                    </el-button>
                                    <el-button type="primary" @click="toAccept('comeconomicmodule')">
                                        受理
                                    </el-button>
                                    <el-button type="primary" @click="toNotAccept('comeconomicmodule')">
                                        不受理
                                    </el-button>
                                    <el-button type="primary">
                                        直接归档
                                    </el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="受理" v-if="accept">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="是否受理：" prop="department">
                                <el-select v-model="nextStepForm.opinion" placeholder="请选择"
                                           style="width: 120px;">
                                    <el-option label="受理" value="受理"></el-option>
                                    <el-option label="不受理" value="不受理"></el-option>
                                </el-select>
                                <el-select v-model="nextStepForm.department" placeholder="请选择"
                                           style="width: 200px;">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="6">
                            <el-button type="primary" @click="accepts">
<!--                            <el-button type="primary" @click="">-->
                                确定
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="不受理" v-if="notAccepts">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px 0;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="opinion" label-width="105px" class="bt">
                                <div style="line-height: 35px">
                                    {{nextStepForm.noAccept}}
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="department" label-width="130px" required class="bt">
                                <el-select v-model="nextStepForm.department" placeholder="请选择" prop="department" style="width: 180px;">
                                    <el-option
                                            v-for="item in reasonBack"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="不受理内容:" class="form_bt" prop="reason" label-width="105px" style="width: 80%">
                                <el-input type="textarea" :rows="4" placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-upload :auto-upload="false" :file-list="fileList" ref="upload" style="margin: 0 26px;"
                                       action="https://jsonplaceholder.typicode.com/posts/">
                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            </el-upload>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0;">
                        <el-col style="text-align: center;width: 90%;">
                            <el-button type="primary" @click="notAccept">
                                确定
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
            <el-upload :auto-upload="false" :file-list="fileList" ref="upload" :multiple="true"
                       action="https://jsonplaceholder.typicode.com/posts/">
                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button size="small" type="success" @click="submitUpload">确认上传
                </el-button>
            </el-upload>
        </el-dialog>
    </section>
</template>

<script>
    import {saveBaseInfo,next} from '../../api/api';

    export default {
        data() {
            return {
                flows: true,
                aictabs: 'commommodule',
                type: '',
                textarea: '',
                nextStepForm: {
                    opinion:'',
                    department:'',
                },
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

                reasonBack: [{
                    value: 'consent',
                    label: '缺失要素'
                }, {
                    value: 'reject',
                    label: '表述不清'
                }, {
                    value: 'consent',
                    label: '缺失要素'
                }, {
                    value: 'reject',
                    label: '类型错误'
                }, {
                    value: 'consent',
                    label: '地址不祥'
                }, {
                    value: 'reject',
                    label: '已有知识'
                }, {
                    value: 'reject',
                    label: '重复受理'
                }, {
                    value: 'consent',
                    label: '不属本职'
                }, {
                    value: 'reject',
                    label: '市民撤诉'
                }],

                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                accept: false,
                fileList: [],
                fileUploadText: '',
                sourceForm: {},
                comeconomicmodule: {
                    id: '',
                    caseguid: '',
                    formtype: '经济违法行为',
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
                },
                comeconomicmoduleRules: {
                    rqsttitle: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},],
                    rqstperson: [
                        {required: true, message: '请输入举报人姓名', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},],
                    gender: [
                        {required: true, message: '请选择性别', trigger: 'change'},],
                    businesstype: [
                        {required: true, message: '请选择业务分类', trigger: 'change'},],
                    rqstaddress: [
                        {required: true, message: '请输入举报人地址', trigger: 'blur'},
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},],
                    rqstnumber: [
                        {required: true, message: '请输入来电号码', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},],
                    linknumber: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},],
                    usertype: [
                        {required: true, message: '请选择反映类型', trigger: 'change'},],
                    rqsttype: [
                        {required: true, message: '请选择类型', trigger: 'change'},],
                    focus: [
                        {required: true, message: '请选择工单分类', trigger: 'change'},],
                    providelb: [
                        {required: true, message: '请选择类别', trigger: 'change'},],
                    appealsource: [
                        {required: true, message: '请选择诉求来源', trigger: 'change'},],
                    subjectname: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    subjectphone: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},],
                    aicstationid: [
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    postcode: [
                        {max: 20, message: '不能超出20个字符', trigger: 'blur'},],
                    subjectaddress: [
                        {required: true, message: '请输入经营地址', trigger: 'blur'},
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},],
                    subjectzcadd: [
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},],
                    objname: [
                        {required: true, message: '请输入商品/服务名称', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    brand: [
                        {required: true, message: '请输入商品品牌', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    objclassify: [
                        {required: true, message: '请选择涉及客体分类', trigger: 'change'},],
                    objmoney: [
                        {type: 'number', required: true, message: '请输入涉及金额', trigger: 'blur'},],
                    producecompany: [
                        {required: true, message: '请输入生产企业', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    isweb: [
                        {required: true, message: '请选择是否网购', trigger: 'change'},],
                    typespecification: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},],
                    quantity: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},],
                    measureunit: [
                        {max: 10, message: '不能超出10个字符', trigger: 'blur'},],
                    price: [
                        {type: 'number', message: '请输入涉及金额', trigger: 'blur'},],
                    problemtype: [
                        {required: true, message: '请选择涉及问题分类', trigger: 'change'},],
                    illegalrules: [
                        {required: true, message: '请选择涉嫌违法行为为', trigger: 'change'},],
                    problemdescribe: [
                        {required: true, message: '请输入问题陈述', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000个字符', trigger: 'blur'},],
                    keyword: [
                        {required: true, message: '请输入关键字', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},],
                    attachfilename: [
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},],
                },
                complaintmodule: {
                    id: '',
                    caseguid: '',
                    formtype: '消费投诉',
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
                    informationOpen: [],
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
                complaintmoduleRules: {
                    rqsttitle: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},
                    ],
                    rqstperson: [
                        {required: true, message: '请输入举报人姓名', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    gender: [
                        {required: true, message: '请选择性别', trigger: 'change'},
                    ],
                    businesstype: [
                        {required: true, message: '请选择业务分类', trigger: 'change'},
                    ],
                    address: [
                        {required: true, message: '请输入举报人地址', trigger: 'blur'},
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},
                    ],
                    rqstnumber: [
                        {required: true, message: '请输入来电号码', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    linknumber: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},
                    ],
                    usertype: [
                        {required: true, message: '请选择反映类型', trigger: 'change'},
                    ],
                    providetype: [
                        {required: true, message: '请选择类型', trigger: 'change'},
                    ],
                    focus: [
                        {required: true, message: '请选择工单分类', trigger: 'change'},
                    ],
                    providelb: [
                        {required: true, message: '请选择类别', trigger: 'change'},
                    ],
                    appealsource: [
                        {required: true, message: '请选择诉求来源', trigger: 'change'},
                    ],
                    subjectname: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    subjectphone: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    aicstationid: [
                        {required: true, message: '请输入管辖单位', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    postcode: [
                        {required: true, message: '请输入邮编', trigger: 'blur'},
                        {max: 20, message: '不能超出20个字符', trigger: 'blur'},
                    ],
                    subjectaddress: [
                        {required: true, message: '请输入经营地址', trigger: 'blur'},
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},
                    ],
                    objclassify: [
                        {required: true, message: '请选择涉及客体分类', trigger: 'change'},
                    ],
                    objname: [
                        {required: true, message: '请输入商品/服务名称', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    brand: [
                        {required: true, message: '请输入商品品牌', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    objmoney: [
                        {type: 'number', required: true, message: '请输入涉及金额', trigger: 'blur'},
                    ],
                    producecompany: [
                        {required: true, message: '请输入生产企业', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    isweb: [
                        {required: true, message: '请选择是否网购', trigger: 'change'},
                    ],
                    problemtype: [
                        {required: true, message: '请选择涉及问题分类', trigger: 'change'},
                    ],
                    problemdescribe: [
                        {required: true, message: '请输入问题陈述', trigger: 'blur'},
                        {max: 3000, message: '不能超出3000个字符', trigger: 'blur'},],
                    keyword: [
                        {required: true, message: '请输入关键字', trigger: 'blur'},
                        {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    ],
                    briefing: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    ],
                    injuries: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    ],
                    even: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    ],
                    reduction: [
                        {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    ],
                },
                commommodule: {
                    id: '',
                    caseguid: '',
                    formtype: '咨询',
                    businesstype: '',
                    name: '',
                    gender: '',
                    rqstnumber: '',
                    linknumber: '',
                    informationOpen: [],
                    address: '',
                    focus: '',
                    appealsource: '',
                    rqsttitle: '',
                    rqstcontent: '',
                    attachfilename: '',
                },
                commommoduleRules: {
                    businesstype: [
                        {required: true, message: '请选择业务分类', trigger: 'change'},
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    gender: [
                        {required: true, message: '请选择性别', trigger: 'change'},
                    ],
                    rqstnumber: [
                        {required: true, message: '请输入来电号码', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    linknumber: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'},
                        {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '请输入举报人地址', trigger: 'blur'},
                        {max: 500, message: '不能超出500个字符', trigger: 'blur'},
                    ],
                    usertype: [
                        {required: true, message: '请选择反映类型', trigger: 'change'},
                    ],
                    focus: [
                        {required: true, message: '请选择工单分类', trigger: 'change'},
                    ],
                    appealsource: [
                        {required: true, message: '请选择诉求来源', trigger: 'change'},
                    ],
                    rqsttitle: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {max: 200, message: '不能超出200个字符', trigger: 'blur'},
                    ],
                    rqstcontent: [
                        {required: true, message: '请输入问题描述', trigger: 'blur'},
                        {max: 3000, message: '不能超过3000个字符', trigger: 'blur'},
                    ],
                },
                commommoduleOfficeForm: {
                    unit: undefined,
                    way: undefined,
                    idea: undefined,
                },
                commommoduleOfficeFormRules: {
                    unit: [
                        {required: true, message: '请选择归档单位', trigger: 'change'},
                    ],
                    way: [
                        {required: true, message: '请选择处办方式', trigger: 'change'},
                    ],
                    idea: [
                        {required: true, message: '请输入处理意见', trigger: 'blur'},
                        {max: 3000, message: '不能超过3000字', trigger: 'blur'},
                    ],
                },
                formType: 'commommodule',
                postpone: false,
                coupleBack: false,

                businessForm: {
                    classification: '',
                    level: '',
                    illegal: '',
                },
                subjectForm: {},

                mainForm: {},
                registrationActiveNames: ['1', '2', '3', '4', '5'],
                processInofActiveNames: ['1', '2', '3', '4', '5', '6'],
                activeNameas: 'complaintmodule',
            };
        },
        methods: {
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

            flow(visible) {
                if (visible.name == "commommodule") {
                    this.flows = true
                } else {
                    this.flows = false
                }
                this.postpone = false
                this.coupleBack = false
                this.back = false
                this.accept = false
                if (visible.index == "2") {
                    this.aictabs = 'commommodule'
                    this.flows = true
                }
            },

            accepts(){
                let data
                let nextNodeId
                if("commommodule" == this.formType){
                   data = this.commommodule
                    nextNodeId ='handleFeedback';
                }else if("complaintmodule" == this.formType){
                   data = this.complaintmodule
                    nextNodeId = 'handleFeedback';
               }else {
                   data = this.comeconomicmodule
                    nextNodeId = 'registerCase'
               }
                let user = localStorage.getItem('user');
               let para ={
                   user: JSON.parse(user),
                   node:'accept',
                   nextStepForm:this.nextStepForm,
                   formType:this.formType,
                   data,
                   nextNodeId:nextNodeId,
                   opinion:this.nextStepForm.opinion,
               }
                next(para).then((res) => {
                    let para = {
                        id:res.data.id,
                        caseguid: res.data.caseguid,
                    };
                    if("comeconomicmodule" == this.formType){
                        this.$router.push({path:'/wddb/bxtdb/sldjla', query: para})
                    }else {
                        this.$router.push({path:'/wddb/bxtdb/qtsl', query: para})
                    }
                });
                // this.$router.push({path:'/wddb/bxtdb/sldjla', query: para})
            },

            toAccept(formType) {
                this.formType=formType
                this.aictabs = '2'
                this.accept = true
            },
            toNotAccept(formType) {
                this.formType=formType
                this.aictabs = '2'
                this.notAccepts = true
            },
            dateChange(date) {
                this.comeconomicmodule.purchasingdate = date
            },
            submitFrom(formType) {
                this.formType = formType;
                let validation = false;
                if ('commommodule' === formType) {
                    this.$refs.commommodule.validate((valid) => validation = valid);
                } else if ('complaintmodule' === formType) {
                    this.$refs['complaintmoduleSubject'].validate((valid) => validation = valid);
                    this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                    this.$refs['complaintmoduleKeTi'].validate((valid) => validation = valid && validation);
                    this.$refs['complaintmoduleBusiness'].validate((valid) => validation = valid && validation);
                }else if('comeconomicmodule' == formType){
                    this.$refs['comeconomicmoduleSubject'].validate((valid) => validation = valid);
                    this.$refs['comeconomicmoduleProvider'].validate((valid) => validation = valid && validation);
                    this.$refs['comeconomicmoduleObject'].validate((valid) => validation = valid && validation);
                    this.$refs['comeconomicmoduleBusiness'].validate((valid) => validation = valid && validation);
                }
                //通过提交
                if (validation) {
                    let user = localStorage.getItem('user');
                    let para = {
                        user: JSON.parse(user),
                        formType: this.formType,
                        commommodule: this.commommodule,
                        complaintmodule: this.complaintmodule,
                        comeconomicmodule: this.comeconomicmodule,
                        node:'staging',
                        nextNodeId:'accept',
                        method: "submit",
                    };
                    //把信息公开数组转字符
                    // para.commommodule.informationOpen = para.commommodule.informationOpen.toString();
                    // para.complaintmodule.informationOpen = para.complaintmodule.informationOpen.toString();
                    saveBaseInfo(para).then((res) => {
                        if("commommodule" == res.data.formType){
                            this.commommodule = res.data.customerinfo
                        }else if("complaintmodule" == res.data.formType){
                            this.complaintmodule = res.data.complaintform
                        }else if("comeconomicmodule" == res.data.formType){
                            this.comeconomicmodule = res.data.economyform
                        }
                        if (res.code === 0) {
                            this.$message({
                                message: '保存成功！',
                                type: 'success',
                                duration:'1000'
                            });
                        } else {
                            this.$alert('保存失败！', '提示', {
                                type: 'error',
                                confirmButtonText: '确定',
                            });
                        }
                    });
                }
            },
            toGuiDan(formType){
                this.formType = formType;
                let validation = false;
                if ('commommodule' === formType) {
                    this.$refs.commommodule.validate((valid) => validation = valid);
                } else if ('complaintmodule' === formType) {
                    this.$refs['complaintmoduleSubject'].validate((valid) => validation = valid);
                    this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                    this.$refs['complaintmoduleKeTi'].validate((valid) => validation = valid && validation);
                    this.$refs['complaintmoduleBusiness'].validate((valid) => validation = valid && validation);
                    this.$refs['complaintmoduleBusiness'].validate((valid) => validation = valid && validation);
                    // this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                }
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            nextStep() {
            },
            removeFile() {
                this.$confirm('确认删除选中的文件?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.$alert('删除成功!', '提示', {
                        confirmButtonText: '确定',
                        type: 'success',
                    });
                }).catch(() => {

                });
            }
        }

    };
</script>
<style lang="scss">

    .el-button--primary {
        color: #fff;
        background-color: #20a0ff;
        border-color: #20a0ff;
    }

    .clearfix {
        font-weight: bold;
    }

    .el-card__header {
        background-color: #fafafa;
    }

    .bt1 {
        font-weight: lighter;
    }

    .bt {
        font-weight: bold;
        text-align: left;
    }

    .business-create {
        .file-tabs {
            margin-top: -15px;
        }

        .pagination {
            float: right;
            margin-top: 10px;
        }
    }

    .el-accordion {
        font-size: 13px;
        font-weight: bold;
        color: #1e1f20;
        cursor: auto;
    }

    .input-text {
        width: 100%;

    }
</style>
