<template>
    <section class="work_review">
        <el-row style="margin-top: 0;margin-bottom: 5px">
            <el-col>
                <el-steps :active="1" align-center center space="22%">
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
                            <el-row class="el-accordion title_accordion" v-model="customerinfo">
                                <el-col :span="12">
                                    <em>01</em> 定单信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    定单号：{{tasklist.tserialnum}}
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="seats" label-width="150px" ref="seats">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="接听坐席：" class="bt">
                                        <el-input v-model="customerinfo.seatsname" placeholder="请输入"
                                                  class="input-text">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="登记时间：" class="bt">
                                        <el-date-picker
                                                v-model="customerinfo.seatsregistrationtime"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="性别：" class="bt">
                                        <el-select v-model="customerinfo.seatssex" placeholder="请选择性别" class="input-text">
                                            <el-option label="男" value="1"></el-option>
                                            <el-option label="女" value="2"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="语种：" class="bt">
                                        <el-select v-model="customerinfo.seatslanguage" placeholder="请选择语种"
                                                   class="input-text">
                                            <el-option label="普通话" value="普通话"></el-option>
                                            <el-option label="粤语" value="粤语"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="2" v-if="economy">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion title_accordion" >
                                <el-col :span="12">
                                    <em>02</em> 经济违法行为举报表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：{{tasklist.serialnum}}
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
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="表单类型：" class="bt">
                                                        <el-input v-model="economyform.formType" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="标题：" class="bt">
                                                        <el-input v-model="economyform.rqsttitle" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="举报人姓名：" class="bt">
                                                        <el-input v-model="economyform.rqstperson" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="年龄：" class="bt">
                                                        <el-input v-model="economyform.age" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="性别：" class="bt">
                                                        <el-select v-model="economyform.gender" placeholder="请选择性别"
                                                                   class="input-text">
                                                            <el-option label="男" value="1"></el-option>
                                                            <el-option label="女" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="民族：" class="bt">
                                                        <el-input v-model="economyform.nation" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="来电号码：" class="bt">
                                                        <el-input v-model="economyform.rqstnumber" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="联系电话：" class="bt">
                                                        <el-input v-model="economyform.linknumber" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="身份：" class="bt">
                                                        <el-input v-model="economyform.status" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="证件类型：" class="bt">
                                                        <el-input v-model="economyform.certificate"
                                                                  placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="证件号码：" class="bt">
                                                        <el-input v-model="economyform.idnumber"
                                                                  placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="职业：" class="bt">
                                                        <el-select v-model="economyform.career"
                                                                   placeholder="请选择分类"
                                                                   class="input-text">
                                                            <el-option label="" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="工作单位：" class="bt">
                                                        <el-input v-model="economyform.company"
                                                                  placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="反映类型：" class="bt">
                                                        <el-select v-model="economyform.usertype"
                                                                   placeholder="请选择反映类型"
                                                                   class="input-text">
                                                            <el-option label="企业" value="1"></el-option>
                                                            <el-option label="个人" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="回复方式：" class="bt">
                                                        <el-select v-model="economyform.answertype" placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="电话" value="complain"></el-option>
                                                            <el-option label="网络" value="consult"></el-option>
                                                            <el-option label="信件" value="enforcement"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="工单分类：" class="bt">
                                                        <el-select v-model="economyform.focus" placeholder="请选择分类"
                                                                   class="input-text">
                                                            <el-option label="普通" value="1"></el-option>
                                                            <el-option label="特殊" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="类别：" class="bt">
                                                        <el-select v-model="economyform.providetype" placeholder="请选择类别"
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
                                                    <el-form-item label="投诉票据：" class="bt">
                                                        <el-input v-model="economyform.omplaintpaper" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="残疾人标识：" class="bt">
                                                        <el-input v-model="economyform.disabilityidentification"
                                                                  placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="提供方类别：" class="bt">
                                                        <el-select v-model="comeconomicmodule.objmoney"
                                                                   placeholder="请选择分类"
                                                                   class="input-text">
                                                            <el-option label="" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                                    <el-form-item label="举报人地址：" class="bt">
                                                        <el-input v-model="economyform.rqstaddress" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="联系地址：" class="bt">
                                                        <el-input v-model="economyform.contactaddress" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>


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
                                                    <el-form-item label="名称：" class="bt">
                                                        <el-input v-model="economyform.subjectname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="联系方式：" class="bt">
                                                        <el-input v-model="economyform.linktype" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="核查名称：" class="bt">
                                                        <el-input v-model="economyform.cksubjectname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="经营地址：" class="bt">
                                                        <el-input v-model="economyform.subjectaddress" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="实际注册地址：" class="bt">
                                                        <el-input v-model="economyform.subjectzcadd" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="核查经营地址：" class="bt">
                                                        <el-input v-model="economyform.cksubjectaddress"
                                                                  placeholder="请输入"
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
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品/服务名称：" class="bt">
                                                        <el-input v-model="economyform.objname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品品牌：" class="bt">
                                                        <el-input v-model="economyform.brand" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及客体分类：" class="bt">
                                                        <el-select v-model="economyform.objclassify"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="公寓" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及金额：" class="bt">
                                                        <el-input v-model="economyform.objmoney" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="生产企业：" class="bt">
                                                        <el-input v-model="economyform.producecompany" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否网购：" class="bt">
                                                        <el-select v-model="economyform.isweb"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="complain"></el-option>
                                                            <el-option label="否" value="consult"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品进口标志：" class="bt">
                                                        <el-input v-model="economyform.importmark" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="型号规格：" class="bt">
                                                        <el-input v-model="economyform.typespecification" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品数量：" class="bt">
                                                        <el-input v-model="economyform.quantity" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="计量单位：" class="bt">
                                                        <el-input v-model="economyform.measureunit" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品/服务价格：" class="bt">
                                                        <el-input v-model="economyform.price" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="购买日期：" class="bt">
                                                        <el-date-picker
                                                                v-model="economyform.purchasingdate"
                                                                type="date"
                                                                class="input-text"
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
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及问题分类：" class="bt">
                                                        <el-select v-model="economyform.problemtype"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉嫌违法行为：" class="bt">
                                                        <el-select v-model="economyform.illegalrules"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="事件级别：" class="bt">
                                                        <el-select v-model="economyform.eventlevel"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="特别紧急事件" value="1"></el-option>
                                                            <el-option label="紧急事件" value="2"></el-option>
                                                            <el-option label="一般事件" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="关键字：" class="bt">
                                                        <el-input v-model="economyform.keyword" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="政务标签：" class="bt">
                                                        <el-input v-model="economyform.govlabel" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="业务分类：" class="bt">
                                                        <el-select v-model="economyform.differserv"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="侵权分类：" class="bt">
                                                        <el-select v-model="economyform.tortclassify"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="案值：" class="bt">
                                                        <el-input v-model="economyform.anestimate" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="经济损失值：" class="bt">
                                                        <el-input v-model="economyform.economicloss" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="人生伤害类型：" class="bt">
                                                        <el-select v-model="economyform.personalinjury"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="人生伤害确认程度：" class="bt">
                                                        <el-input v-model="economyform.injurydegree" placeholder="请输入"
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="受伤人数：" class="bt">
                                                        <el-input v-model="economyform.injuries" placeholder="请输入"
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="消防安全确认程度：" class="bt">
                                                        <el-input v-model="economyform.consumptionsafety" placeholder="请输入"
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="消防安全事件类型：" class="bt">
                                                        <el-select v-model="economyform.consumptionsafetytype"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <!--<el-form-item label="绝产亩地：" class="bt">
                                                        <el-input v-model="economyform.even" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">亩</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="减少亩地：" class="bt">
                                                        <el-input v-model="economyform.reduction" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">亩</template>
                                                        </el-input>
                                                    </el-form-item>-->
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="简要情况：" class="bt">
                                                        <el-input v-model="economyform.briefing" placeholder="请输入"
                                                                  class="input-text">
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="问题描述：" class="bt">
                                                        <el-input v-model="economyform.problemdescribe" placeholder="请输入"
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
                                        <el-form :model="economyform" label-width="150px" ref="economyform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否出现场：" class="bt">
                                                        <el-select v-model="economyform.isoutmall"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否反馈：" class="bt">
                                                        <el-select v-model="economyform.isfeedback"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否需要奖励：" class="bt">
                                                        <el-select v-model="economyform.isaward"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                                                    <el-form-item label="相关附件：" class="bt">
                                                        <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                                                            选择文件
                                                        </el-button>
                                                    </el-form-item>
                                                </el-col>
                                                <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">
                                                    <el-form-item label="附件清单：" class="bt">
                                                        <el-upload
                                                                style="float: left"
                                                                class="upload-demo"
                                                                action="https://jsonplaceholder.typicode.com/posts/"
                                                                :on-preview="handlePreview"
                                                                :on-remove="handleRemove"
                                                                :file-list="fileList">
                                                            <el-button size="small" type="primary">选择文件</el-button>
                                                        </el-upload>
                                                    </el-form-item>
                                                </el-col>-->
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
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="登记单编号：" class="bt">
                                                        <el-input v-model="economyform.number" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="案由：" class="bt">
                                                        <el-input v-model="economyform.cause" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
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
                                        <el-form :model="mainForm" label-width="220px" ref="mainForm">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="是否具有营业执照：" class="bt">
                                                        <el-select v-model="mainForm.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="统一社会信用代码/注册号：" class="bt">
                                                        <el-input v-model="mainForm.registration" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="所在行业类别：" class="bt">
                                                        <el-select v-model="mainForm.industryCategory"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="IT" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="市场名称：" class="bt">
                                                        <el-input v-model="mainForm.marketName" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="联系方式：" class="bt">
                                                        <el-input placeholder="请输入" v-model="mainForm.phone">
                                                            <el-select v-model="mainForm.contactMode" slot="prepend"
                                                                       placeholder="请选择" style="width: 110px;">
                                                                <el-option label="移动电话" value="1"></el-option>
                                                                <el-option label="固定电话" value="2"></el-option>
                                                            </el-select>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="地址：" class="bt">
                                                        <el-input placeholder="请输入" v-model="mainForm.addr">
                                                            <el-select v-model="mainForm.addrMode" slot="prepend"
                                                                       placeholder="请选择" style="width: 110px;">
                                                                <el-option label="顺德区" value="1"></el-option>
                                                                <el-option label="禅城区" value="2"></el-option>
                                                                <el-option label="南海区" value="3"></el-option>
                                                                <el-option label="三水区" value="4"></el-option>
                                                                <el-option label="高明区" value="5"></el-option>
                                                            </el-select>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="是否转移其他部门：" class="bt">
                                                        <el-select v-model="mainForm.department"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="消保" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="应急处置进展状态：" class="bt">
                                                        <el-select v-model="mainForm.progress"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="反馈登记人：" class="bt">
                                                        <el-input v-model="mainForm.registrar" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="协助单位类型：" class="bt">
                                                        <el-select v-model="mainForm.unitType"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="办案人员：" class="bt">
                                                        <el-input v-model="mainForm.registrar" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="办案人员号码：" class="bt">
                                                        <el-input v-model="mainForm.registrarPhone" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="17" :lg="17">
                                                    <el-form-item label="书式案卷存放位置：" class="bt">
                                                        <el-input v-model="mainForm.seat" placeholder="请输入"
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
                                                        <el-input v-model="awardForm.id" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="领奖人身份证号：" class="bt">
                                                        <el-input v-model="awardForm.IDCard" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="奖励金额：" class="bt">
                                                        <el-input v-model="awardForm.amount" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">万元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="奖励价值：" class="bt">
                                                        <el-input v-model="awardForm.cost" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">万元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="领奖时间：" class="bt">
                                                        <el-date-picker
                                                                v-model="awardForm.receiveDate"
                                                                type="date"
                                                                class="input-text"
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
                            <el-row class="el-accordion title_accordion" >
                                <el-col :span="12">
                                    <em>02</em> 消费投诉表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：{{tasklist.serialnum}}
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
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="表单类型：" class="bt">
                                                        <el-input v-model="complaintform.formtype" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="标题：" class="bt">
                                                        <el-input v-model="complaintform.rqsttitle" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="表单模板：" class="bt">
                                                        <el-select v-model="complaintform.formtype" placeholder="请选择表单模板"
                                                                   class="input-text">
                                                            <el-option label="消费投诉业务-价格投诉" value="1"></el-option>
                                                            <el-option label="消费投诉业务-质量投诉" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="投诉人姓名：" class="bt">
                                                        <el-input v-model="complaintform.rqstperson" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="性别：" class="bt">
                                                        <el-select v-model="complaintform.gender" placeholder="请选择性别"
                                                                   class="input-text">
                                                            <el-option label="男" value="1"></el-option>
                                                            <el-option label="女" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="来电号码：" class="bt">
                                                        <el-input v-model="complaintform.rqstnumber" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="联系电话：" class="bt">
                                                        <el-input v-model="complaintform.linknumber" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="反映类型：" class="bt">
                                                        <el-select v-model="complaintform.usertype"
                                                                   placeholder="请选择反映类型"
                                                                   class="input-text">
                                                            <el-option label="企业" value="1"></el-option>
                                                            <el-option label="个人" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="回复方式：" class="bt">
                                                        <el-select v-model="complaintform.answertype" placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="电话" value="complain"></el-option>
                                                            <el-option label="网络" value="consult"></el-option>
                                                            <el-option label="信件" value="enforcement"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>

                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="类型：" class="bt">
                                                        <el-select v-model="complaintform.providetype" placeholder="请选择类别"
                                                                   class="input-text">
                                                            <el-option label="自然人" value="1"></el-option>
                                                            <el-option label="监督人" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="类别：" class="bt">
                                                        <el-select v-model="complaintform.providelb" placeholder="请选择类别"
                                                                   class="input-text">
                                                            <el-option label="青男" value="1"></el-option>
                                                            <el-option label="妇女" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="工单分类：" class="bt">
                                                        <el-select v-model="complaintform.focus" placeholder="请选择类别"
                                                                   class="input-text">
                                                            <el-option label="普通" value="1"></el-option>
                                                            <el-option label="特殊" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                                    <el-form-item label="区域：" class="bt">
                                                        <el-select v-model="complaintform.region" placeholder="请选择类别"
                                                                   class="input-text">
                                                            <el-option label="顺德区" value="1"></el-option>
                                                            <el-option label="XX区" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                                    <el-form-item label="投诉人地址：" class="bt">
                                                        <el-input v-model="complaintform.subjectzcadd" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
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
                                                    <el-form-item label="名称：" class="bt">
                                                        <el-input v-model="complaintform.subjectname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="联系电话：" class="bt">
                                                        <el-input v-model="complaintform.subjectphone" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="经营地址：" class="bt">
                                                        <el-input v-model="complaintform.subjectaddress" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="实际注册地址：" class="bt">
                                                        <el-input v-model="complaintform.subjectzcadd" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="核查名称：" class="bt">
                                                        <el-input v-model="complaintform.cksubjectname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="核查经营地址：" class="bt">
                                                        <el-input v-model="complaintform.cksubjectaddress"
                                                                  placeholder="请输入"
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
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品/服务名称：" class="bt">
                                                        <el-input v-model="complaintform.objname" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品品牌：" class="bt">
                                                        <el-input v-model="complaintform.brand" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及客体分类：" class="bt">
                                                        <el-select v-model="complaintform.objclassify"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="公寓" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及金额：" class="bt">
                                                        <el-input v-model="complaintform.objmoney" placeholder="请输入"
                                                                  class="input-text">
                                                            <template slot="append">元</template>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="生产企业：" class="bt">
                                                        <el-input v-model="complaintform.producecompany" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否网购：" class="bt">
                                                        <el-select v-model="complaintform.isweb"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="complain"></el-option>
                                                            <el-option label="否" value="consult"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品进口标志：" class="bt">
                                                        <el-input v-model="comeconomicmodule.importMark" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="型号规格：" class="bt">
                                                        <el-input v-model="comeconomicmodule.model" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品数量：" class="bt">
                                                        <el-input v-model="comeconomicmodule.number" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="计量单位：" class="bt">
                                                        <el-input v-model="comeconomicmodule.unit" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="商品/服务价格：" class="bt">
                                                        <el-input v-model="comeconomicmodule.price" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="购买日期：" class="bt">
                                                        <el-date-picker
                                                                v-model="comeconomicmodule.purchaseDate"
                                                                type="date"
                                                                class="input-text"
                                                                placeholder="选择日期">
                                                        </el-date-picker>
                                                    </el-form-item>
                                                </el-col>-->
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
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="涉及问题分类：" class="bt">
                                                        <el-select v-model="complaintform.problemtype"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="其他" value="1"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="问题陈述：" class="bt">
                                                        <el-input v-model="complaintform.problemdescribe" placeholder="请输入"
                                                                  type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="关键字：" class="bt">
                                                        <el-input v-model="complaintform.keyword" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="事件级别：" class="bt">
                                                        <el-select v-model="complaintform.problemtype"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="特别紧急事件" value="1"></el-option>
                                                            <el-option label="紧急事件" value="2"></el-option>
                                                            <el-option label="一般事件" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="政务标签：" class="bt">
                                                        <el-input v-model="complaintform.govlabeloutput" placeholder="请输入"
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
                                        <el-form :model="complaintform" label-width="150px" ref="complaintform">
                                            <el-row>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                                    <el-form-item label="是否出现场：" class="bt">
                                                        <el-select v-model="complaintform.isoutmall"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col class="tr"></el-col>
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
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="登记单编号：" class="bt">
                                                        <el-input v-model="complaintform.number" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="案由：" class="bt">
                                                        <el-input v-model="complaintform.cause" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
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
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="是否具有营业执照：" class="bt">
                                                        <el-select v-model="complaintform.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="统一社会信用代码/注册号：" class="bt">
                                                        <el-input v-model="complaintform.registration" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="所在行业类别：" class="bt">
                                                        <el-select v-model="complaintform.industryCategory"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="IT" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="市场名称：" class="bt">
                                                        <el-input v-model="complaintform.marketName" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="联系方式：" class="bt">
                                                        <el-input placeholder="请输入" v-model="complaintform.phone">
                                                            <el-select  slot="prepend"
                                                                       placeholder="请选择" style="width: 110px;">
                                                                <el-option label="移动电话" value="1"></el-option>
                                                                <el-option label="固定电话" value="2"></el-option>
                                                            </el-select>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="地址：" class="bt">
                                                        <el-input placeholder="请输入" v-model="complaintform.addr">
                                                            <el-select  slot="prepend"
                                                                       placeholder="请选择" style="width: 110px;">
                                                                <el-option label="顺德区" value="1"></el-option>
                                                                <el-option label="禅城区" value="2"></el-option>
                                                                <el-option label="南海区" value="3"></el-option>
                                                                <el-option label="三水区" value="4"></el-option>
                                                                <el-option label="高明区" value="5"></el-option>
                                                            </el-select>
                                                        </el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="是否转移其他部门：" class="bt">
                                                        <el-select v-model="complaintform.department"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="消保" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="应急处置进展状态：" class="bt">
                                                        <el-select v-model="complaintform.progress"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="反馈登记人：" class="bt">
                                                        <el-input v-model="complaintform.registrar" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="协助单位类型：" class="bt">
                                                        <el-select v-model="complaintform.unitType"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="" value="complain"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="办案人员：" class="bt">
                                                        <el-input v-model="complaintform.registrar" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="办案人员号码：" class="bt">
                                                        <el-input v-model="complaintform.registrarPhone" placeholder="请输入"
                                                                  class="input-text"></el-input>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="群体投诉案件：" class="bt">
                                                        <el-select v-model="complaintform.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="涉外投诉案件：" class="bt">
                                                        <el-select v-model="complaintform.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="对投诉中发现违法行为实施行政处理：" class="bt">
                                                        <el-select v-model="complaintform.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="是否转立案：" class="bt">
                                                        <el-select v-model="complaintform.license"
                                                                   placeholder="请选择"
                                                                   class="input-text">
                                                            <el-option label="是" value="1"></el-option>
                                                            <el-option label="否" value="2"></el-option>
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
                                                        <el-input v-model="complaintform.text" placeholder="请输入"
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
                                                        <el-input v-model="complaintform.describe"
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
                            <el-row class="el-accordion title_accordion" >
                                <el-col :span="12">
                                    <em>02</em> 通用表单
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                    表单号：{{tasklist.serialnum}}
                                </el-col>
                            </el-row>
                        </div>
                        <el-tabs type="card" v-model="economicsActiveName">
                            <el-tab-pane label="登记信息" name="registration">
                                <el-form :model="commomform" label-width="150px" ref="commomform">
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="表单类型：" class="bt">
                                                <el-input v-model="commomform.formtype" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="表单模板：" class="bt">
                                                <el-input v-model="commomform.formmodule" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="标题：" class="bt">
                                                <el-input v-model="commomform.rqsttitle" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="姓名：" class="bt">
                                                <el-input v-model="commomform.name" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="性别：" class="bt">
                                                <el-select v-model="commomform.gender" placeholder="请选择性别"
                                                           class="input-text">
                                                    <el-option label="男" value="1"></el-option>
                                                    <el-option label="女" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                            <el-form-item label="区域：" class="bt">
                                                <el-select v-model="commomform.region" placeholder="请选择区域"
                                                           class="input-text">
                                                    <el-option label="顺德区" value="1"></el-option>
                                                    <el-option label="XX区" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                            <el-form-item label="是否有效：" class="bt">
                                                <el-select  v-model="commomform.isvalid"
                                                            placeholder="请选择"
                                                            class="input-text">
                                                    <el-option label="是" value="1"></el-option>
                                                    <el-option label="否" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="来电号码：" class="bt">
                                                <el-input v-model="commomform.rqstnumber" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="联系电话：" class="bt">
                                                <el-input v-model="commomform.linknumber" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="反映类型：" class="bt">
                                                <el-select v-model="commomform.usertype"
                                                           placeholder="请选择反映类型"
                                                           class="input-text">
                                                    <el-option label="企业" value="1"></el-option>
                                                    <el-option label="个人" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="回复方式：" class="bt">
                                                <el-select v-model="commomform.answertype" placeholder="请选择"
                                                           class="input-text">
                                                    <el-option label="电话" value="complain"></el-option>
                                                    <el-option label="网络" value="consult"></el-option>
                                                    <el-option label="信件" value="enforcement"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="企业类型：" class="bt">
                                                <el-select v-model="commomform.entitytype" placeholder="请选择分类"
                                                           class="input-text">
                                                    <el-option label="内资企业" value="1"></el-option>
                                                    <el-option label="外资企业" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="企业全称：" class="bt">
                                                <el-input v-model="commomform.entname" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="企业登记机关：" class="bt">
                                                <el-input v-model="commomform.aicid" placeholder="请输入"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="企业经营地址：" class="bt">
                                                <el-input  v-model="commomform.address"
                                                           placeholder="请输入"
                                                           class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="工单分类：" class="bt">
                                                <el-select v-model="commomform.focus" placeholder="请选择分类"
                                                           class="input-text">
                                                    <el-option label="普通" value="1"></el-option>
                                                    <el-option label="特殊" value="2"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="问题描述：" class="bt">
                                                <el-input v-model="commomform.rqstcontent" placeholder="请输入"
                                                          type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="政务标签：" class="bt">
                                                <el-input  v-model="commomform.govlabel"
                                                           placeholder="请输入"
                                                           class="input-text"></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col class="tr"></el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="相关附件：" class="bt">
                                                <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                                                    选择文件
                                                </el-button>
                                            </el-form-item>
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
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="初步意见应反馈时限：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.opiniontimeshould"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="初步意见反馈时间：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.opiniontime"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="应受理时限：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.accepttimeshould"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="应受理到期时间：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.accepttime"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="应立案时限：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.caseputtimeshould"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="应立案到期时间：" class="bt">
                                        <el-date-picker
                                                v-model="handledemand.caseputtime"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间">
                                        </el-date-picker>
                                    </el-form-item>
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
                                    <el-form inline class="demo-table-expand"

                                             label-width="100px">
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
                                            <span>{{moment(props.row.handletime).format('YYYY-MM-DD')}}</span>
                                        </el-form-item>
                                        <el-form-item label="处理意见">
                                            <span>{{ props.row.handleopinion }}</span>
                                        </el-form-item>
                                    </el-form>
                                </template>
                            </el-table-column>
                            <el-table-column type="index" label="序号" width="70" align="center"></el-table-column>
                            <el-table-column label="步骤" prop="nodename" align="center">
                            </el-table-column>
                            <el-table-column label="处理部门" prop="handledept" align="center">
                            </el-table-column>
                            <el-table-column label="处理人员" prop="handler" align="center">
                            </el-table-column>
                            <el-table-column label="操作" prop="operate" align="center">
                            </el-table-column>
                            <el-table-column label="处理时间" prop="handletime" :formatter="formData" align="center">
                            </el-table-column>
                            <el-table-column label="处理意见" prop="handleopinion" align="center" show-overflow-tooltip>
                            </el-table-column>
                        </el-table>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>

            <!--材料上传开始-->
            <el-tab-pane label="材料上传">
                <el-tabs v-model="activeName" class="file-tabs">
                    <el-tab-pane label="文档" name="word">
                        <el-button size="small" @click="toUploadOpen('word')"><i class="fa fa-upload"></i> 上传
                        </el-button>
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
                        <el-button size="small" @click="toUploadOpen('image')"><i class="fa fa-upload"></i> 上传
                        </el-button>
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
                        <el-button size="small" @click="toUploadOpen('video')"><i class="fa fa-upload"></i> 上传
                        </el-button>
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
                        <el-button size="small" @click="toUploadOpen('other')"><i class="fa fa-upload"></i> 上传
                        </el-button>
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

            <el-tab-pane label="初步答复" v-if="assign">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="成员单位：" prop="department" required>
                                <el-select v-model="nextStepForm.dept" placeholder="请选择" prop="department"
                                           style="width: 180px;">
                                    <el-option
                                            v-for="item in dept"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <table style="width: 96%;border-color: #fbfdff;margin: 0 auto;" cellspacing="0"
                           cellpadding="0" border>
                        <tr style="width: 100%;height: 65px;">
                            <td style="width: 20%;height:120px;text-align: center;font-weight: bold;"><span
                                    style="color: #e64242">*</span>分派意见：
                            </td>
                            <td style="width: 80%">
                                <input style="width: 99.5%;height: 120px;" placeholder="请输入内容">
                            </td>
                        </tr>
                    </table>
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-button size="small" @click="toUploadOpen()" type="primary"> 选取文件</el-button>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="$router.push({path:'/wddb/tsldb/gdsl',query:{id:tasklist.id,caseguid:tasklist.caseguid}})">
                                确定
                            </el-button><!--@click="$router.push({path:'/wddb/tsldb/gdsl',query:{id:tasklist.id}})"-->
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="受理" v-if="accept">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="是否受理：" prop="department" required>
                                <el-select v-model="nextStepForm.department" placeholder="请选择" prop="department"
                                           style="width: 120px;">
                                    <el-option
                                            v-for="item in acceptOrNot"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                                <el-select v-model="nextStepForm.department1" placeholder="请选择" prop="department"
                                           style="width: 200px;">
                                    <el-option
                                            v-for="item in acceptWay"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="6">
                            <el-button type="primary" @click="$router.push({path:'/wddb/tsldb/lacl',query:{id:tasklist.id,caseguid:tasklist.caseguid}})">
                                受理
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="不受理" v-if="notAccepts">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="department" required>
                                <el-select v-model="nextStepForm.department2" placeholder="请选择" prop="department"
                                           style="width: 120px;">
                                    <el-option
                                            v-for="item in acceptOrNot"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="department" required>
                                <el-select v-model="nextStepForm.departments" placeholder="请选择" prop="department"
                                           style="width: 180px;">
                                    <el-option
                                            v-for="item in reason1"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <table style="width: 96%;border-color: #fbfdff;margin: 0 auto;" cellspacing="0"
                           cellpadding="0" border>
                        <tr style="width: 100%;height: 65px;">
                            <td style="width: 20%;height:120px;text-align: center;font-weight: bold;"><span
                                    style="color: #e64242">*</span>不受理内容：
                            </td>
                            <td style="width: 80%">
                                <input style="width: 99.5%;height: 120px;" placeholder="请输入内容">
                            </td>
                        </tr>
                    </table>
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="12" :sm="12" :md="12" :lg="6">
                            <el-button size="small" @click="toUploadOpen()" type="primary"> 选取文件</el-button>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="$router.push('/wddb/12345db')">
                                不受理
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="退回" v-if="back">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="退回原因：" prop="opinion">
                                <el-select v-model="nextStepForm.opinion1" placeholder="请选择" style="width: 180px;">
                                    <el-option
                                            v-for="item in reasonBack"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <table style="width: 96%;border-color: #fbfdff;margin: 0 auto;" cellspacing="0"
                           cellpadding="0" border>
                        <tr style="width: 100%;height: 65px;">
                            <td style="width: 20%;height:120px;text-align: center;font-weight: bold;"><span
                                    style="color: #e64242">*</span>退回描述：
                            </td>
                            <td style="width: 80%">
                                <input style="width: 99.5%;height: 120px;" placeholder="请输入内容">
                            </td>
                        </tr>
                    </table>
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-button size="small" @click="toUploadOpen()" type="primary">选取文件</el-button>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;">
                            <el-button type="primary" @click="$router.push('/wddb/12345db')">
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
                <el-button type="primary">
                    保存
                </el-button>
                <el-button type="primary" @click="toAccept(0)">
                    分派
                </el-button>
                <el-button type="primary" @click="toAccept(1)">
                    受理
                </el-button>
                <el-button type="primary" @click="toAccept(2)">
                    不受理
                </el-button>
                <el-button type="primary" @click="toAccept(3)">
                    退回
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
                aictabs: '0',

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
                acceptOrNot: [{
                    value: '受理',
                    label: '受理'
                }, {
                    value: '不受理',
                    label: '不受理'
                }],
                acceptWay: [{
                    value: '调解',
                    label: '调解'
                }, {
                    value: '查处',
                    label: '查处'
                }, {
                    value: '调查',
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
                rqstphonenumber:'',
                linkphonenumber:'',
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
                updatedate  :'',
                },

                comeconomicmodule: {
                    number:'',
                    cause:'',
                    caseguid:'',
                    formtype:'',
                    rqsttitle:'',
                    accordtype:'',
                    rqstperson:'',
                    gender:'',
                    rqstnumber:'',
                    linknumber:'',
                    rqstaddress:'',
                    usertype:'',
                    answertype:'',
                    focus:'',
                    providelb:'',
                    subjectname:'',
                    linktype:'',
                    subjectphone:'',
                    jyareacode:'',
                    subjectaddress:'',
                    zcareacode:'',
                    subjectzcadd:'',
                    cksubjectname:'',
                    ckjyareacode:'',
                    cksubjectaddress:'',
                    objname:'',
                    brand:'',
                    objclassify:'',
                    objmoney:'',
                    producecompany:'',
                    isweb:'',
                    problemtype:'',
                    illegalrules:'',
                    problemdescribe:'',
                    keyword:'',
                    eventlevel:'',
                    govlabel:'',
                    isoutmall:'',
                    isfeedback:'',
                    isaward:'',
                    attachfilename:'',
                    updatedate:'',
                },
                complaintform:{
                    id:'',
                    caseguid:'',
                    formtype:'',
                    rqsttitle:'',
                    rqstperson:'',
                    gender:'',
                    rqstnumber:'',
                    linknumber:'',
                    usertype:'',
                    answertype:'',
                    providetype:'',
                    providelb:'',
                    focus:'',
                    region:'',
                    subjectname:'',
                    linktype:'',
                    subjectphone:'',
                    jyareacode:'',
                    subjectaddress:'',
                    zcareacode:'',
                    subjectzcadd:'',
                    cksubjectname:'',
                    ckjyareacode:'',
                    cksubjectaddress:'',
                    objname:'',
                    brand:'',
                    objclassify:'',
                    objmoney:'',
                    producecompany:'',
                    isweb:'',
                    problemtype:'',
                    problemdescribe:'',
                    keyword:'',
                    govlabeloutput:'',
                    attachfilename:'',
                    updatedate:'',
                },
                economyform:{
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

                mainForm: {},
                awardForm: {},
                opinionForm: {
                    text: '收熟，已转相关科室跟进，请留意进一步回复。'
                },
                assignForm: {},
                special: false,
                checkList: [],
                accept: false,
                notAccepts: false,
                back: false,
                assign: false,
                choose: false,
                activeNames: ['1', '2', '3', '4'],
                registrationActiveNames: ['1', '2', '3', '4', '5'],
                processInofActiveNames: ['1', '2', '3', '4', '5'],
                economicsActiveName: 'registration'
            }
        },
        methods: {


            //跳转:分派--确认
            jump() {
               /* this.$refs.dataForm.validate((valid) => {
                    if (valid) {*/

                this.$router.push({
                    path:'//wddb/tsldb/gdsl',
                    query:{
                        id:tasklist.id
                    }
                })

                        // this.$router.push({path:'/wddb/tsldb/gdsl'+ this.$route.path});
                        // this.$router.push({path: '/tjfx/tjbb/bbcx/cxjg', query: this.dataForm});
                  /*  } else {
                        return false;
                    }
                });*/
            },
            formData(row, column) {
                if (row.handletime == null) {
                    return ''
                } else {
                    return moment(row.handletime).format('YYYY-MM-DD')
                }
            },

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

            submitFrom() {
                var user = localStorage.getItem('user');
                let para = {
                    user: JSON.parse(user),
                    comeconomicmodule: this.comeconomicmodule,
                    economyform: this.economyform,
                    complaintmodule: this.complaintmodule,
                    seats: this.seats,
                    handledemand: this.handledemand,
                    complaintform:this.complaintform,
                    commomform:this.commomform,
                    tasklist:this.tasklist,
                    // datagriddata: this.datagriddata,
                };
                confirmBaseInfo(para).then(() => {
                     this.$message({
                        message: '提交成功！',
                        type: 'success',
                        duration:'1000'
                    });
                });
            },

            formatDate(row, column) {
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
                this.accept = false
                this.notAccepts = false
                this.back = false
                this.assign = false
                if (visible.index == "2") {
                    this.aictabs = '0'
                    this.flows = true
                }
            },
            toAccept(value) {
                this.aictabs = '2'
                if (value == "0") {
                    this.assign = true
                    this.accept = false
                    this.notAccepts = false
                    this.back = false
                    this.flows = false
                } else if (value == "1") {
                    this.accept = true
                    this.notAccepts = false
                    this.back = false
                    this.flows = false
                } else if (value == "2") {
                    this.notAccepts = true
                    this.accept = false
                    this.back = false
                    this.flows = false
                } else {
                    this.back = true
                    this.accept = false
                    this.notAccepts = false
                    this.flows = false
                }

            },

        },
        created() {
            let para = {
                id: this.$route.query.id,
                caseguid:this.$route.query.caseguid
            };
            selectBusinessBaseInfo(para).then((res) =>  {
                // this.askdepthandleList = res.data.askdepthandleList
                this.tasklist = res.data.tasklist
                this.economyform = res.data.economyform
                this.handledemand = res.data.handledemand
                this.customerinfo = res.data.customerinfo
                this.opinionList = res.data.opinionList
                this.complaintform = res.data.complaintform
                this.commomform = res.data.commomform
                // this.comeconomicmodule = res.data.comeconomicmodule
                // this.commommodule = res.data.commommodule
                // this.complaintmodule = res.data.complaintmodule
                // this.datagriddata = res.data.datagriddata
                // this.seats = res.data.seats
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
                // this.askdepthandleList = res.data.askdepthandleList
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
    .work_review {
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
            font-weight: bold;
            text-align: right;
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
