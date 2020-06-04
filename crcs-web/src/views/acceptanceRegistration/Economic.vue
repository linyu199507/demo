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
            <el-tab-pane label="经济违法登记" name="comeconomicmodule">
                <el-collapse v-model="processInofActiveNames" class="accordion">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    系统信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleSubject"
                                 :rules="comeconomicmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="记录类型:" class="bt">
                                        <div style="line-height: 35px">
                                            {{comeconomicmodule.formtype}}
                                        </div>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                        <el-input v-model="comeconomicmodule.rqsttitle" placeholder="请输入标题"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="登记日期：" class="bt" prop="recorddate">
                                        <el-date-picker
                                                type="date"
                                                v-model="comeconomicmodule.recorddate"
                                                class="input-text"
                                                style="width: 100%"
                                                value-format="yyyy-MM-dd"
                                                @change="dateChange"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="信息来源：" class="bt" prop="appealsource">
                                        <el-select placeholder="请选择类型" class="input-text"
                                                   v-model="comeconomicmodule.appealsource">
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
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="接收方式：" class="bt" prop="receivemode">
                                        <el-select placeholder="请选择"
                                                   v-model="comeconomicmodule.receivemode"
                                                   class="input-text">
                                            <el-option label="举报" value="举报"></el-option>
                                            <el-option label="投诉" value="投诉"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="业务分类:" prop="businesstype" class="bt">
                                        <el-select v-model="comeconomicmodule.businesstype"
                                                   placeholder="请选择业务分类" class="input-text">
                                            <el-option label="投诉类" value="投诉类"></el-option>
                                            <el-option label="咨询类" value="咨询类"></el-option>
                                            <el-option label="执法类" value="执法类"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="工单分类:" prop="focus" class="bt">
                                        <el-select v-model="comeconomicmodule.focus" placeholder="请输入工单分类"
                                                   class="input-text">
                                            <el-option label="普通" value="普通"></el-option>
                                            <el-option label="特殊" value="特殊"></el-option>
                                            <el-option label="特派" value="特派"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="来电号码:" prop="telephonenumber" class="bt">
                                        <el-input v-model="comeconomicmodule.telephonenumber" placeholder="请输入来电号码"
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
                                    举报人信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleProvider"
                                 :rules="comeconomicmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                    <el-form-item label="姓名/单位：" class="bt" prop="rqstperson">
                                        <el-input placeholder="请输入姓名/单位"
                                                  v-model="comeconomicmodule.rqstperson"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                    <el-form-item label="举报人联系电话：" class="bt" prop="linknumber">
                                        <el-input placeholder="请输入举报人联系电话"
                                                  v-model="comeconomicmodule.linknumber"
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
                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                    <el-form-item label="举报人地址：" class="bt" prop="rqstaddress">
                                        <el-input placeholder="请输入举报人地址"
                                                  v-model="comeconomicmodule.rqstaddress"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="证件类型：" class="bt" prop="certificate ">
                                        <el-select
                                                placeholder="请选择证件类型"
                                                v-model="comeconomicmodule.certificate"
                                                class="input-text">
                                            <el-option label="身份证" value="身份证"></el-option>
                                            <el-option label="军官证" value="军官"></el-option>
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="证件号码：" class="bt" prop="idnumber">
                                        <el-input placeholder="请输入证件号码"
                                                  v-model="comeconomicmodule.idnumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="提供方类型：" class="bt" prop="usertype">
                                        <el-select placeholder="请选择提供方类型"
                                                   v-model="comeconomicmodule.usertype"
                                                   class="input-text">
                                            <el-option label="企业" value="企业"></el-option>
                                            <el-option label="个人" value="个人"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="年龄段：" class="bt" prop="age">
                                        <el-select placeholder="请选择年龄段"
                                                   v-model="comeconomicmodule.age"
                                                   class="input-text">
                                            <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                            <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                            <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col><el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="电子邮箱：" class="bt" prop="informerpostcode">
                                    <el-input placeholder="请输入电子邮箱"
                                              v-model="comeconomicmodule.informerpostcode"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>

                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="国籍：" class="bt" prop="nationality">
                                        <el-input placeholder="请输入国籍"
                                                  v-model="comeconomicmodule.nationality"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="民族：" class="bt" prop="nation">
                                        <el-input placeholder="请输入民族"
                                                  v-model="comeconomicmodule.nation"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="身份：" class="bt" prop="status">
                                        <el-select placeholder="请选择身份"
                                                   v-model="comeconomicmodule.status"
                                                   class="input-text">
                                            <el-option label="城镇" value="城镇"></el-option>
                                            <el-option label="乡村" value="乡村"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="邮政编码：" class="bt" prop="postcode">
                                        <el-input placeholder="请输入邮政编码"
                                                  v-model="comeconomicmodule.postcode"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="其他联系方式：" class="bt" prop="othercontacts">
                                        <el-input placeholder="请输入其他联系方式"
                                                  v-model="comeconomicmodule.othercontacts"
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
                                    被举报对象
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleObject"
                                 :rules="comeconomicmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="被举报对象名称：" class="bt" prop="subjectname">
                                        <el-input placeholder="请输入被举报对象名称"
                                                  v-model="comeconomicmodule.subjectname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="被举报对象地址：" class="bt" prop="subjectaddress">
                                        <el-input placeholder="请输入被举报对象地址"
                                                  v-model="comeconomicmodule.subjectaddress"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="市场主体类型：" class="bt" prop="providetype">
                                        <el-select placeholder="请选择市场主体类型"
                                                   v-model="comeconomicmodule.providetype"
                                                   class="input-text">
                                            <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                            <el-option label="国有企业" value="国有企业"></el-option>
                                            <el-option label="外资企业" value="外资企业"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="行业分类：" class="bt" prop="industryname">
                                        <el-select placeholder="请选择行业分类"
                                                   v-model="comeconomicmodule.industryname"
                                                   class="input-text">
                                            <el-option label="餐饮" value="餐饮"></el-option>
                                            <el-option label="电子" value="电子"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                    <el-form-item label="联系人：" class="bt" prop="reportedcontact">
                                        <el-input placeholder="请输入联系人"
                                                  v-model="comeconomicmodule.reportedcontact"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="联系电话：" class="bt" prop="reportedcontactnumber">
                                        <el-input placeholder="请输入联系电话"
                                                  v-model="comeconomicmodule.reportedcontactnumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                    <el-form-item label="社会信用代码：" class="bt" prop="registerno">
                                        <el-input placeholder="请输入社会信用代码"
                                                  v-model="comeconomicmodule.registerno"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>

                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="4">
                        <div slot="title" @click.stop="">
                            <el-row class="el-accordion">
                                <el-col :span="12">
                                    业务信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="comeconomicmodule" label-width="150px" ref="comeconomicmoduleBusiness"
                                 :rules="comeconomicmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="客体类别：" class="bt" prop="objectcategory">
                                        <el-select placeholder="请选择害体类别"
                                                   v-model="comeconomicmodule.objectcategory"
                                                   class="input-text">
                                            <el-option label="服务器" value="服务器"></el-option>
                                            <el-option label="其他客体类别" value="其他客体类别"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                        <el-input placeholder="请输入商品/服务名称"
                                                  v-model="comeconomicmodule.objname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="品牌：" class="bt" prop="brand">
                                        <el-input placeholder="请输入"
                                                  v-model="comeconomicmodule.brand"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="销售方式：" class="bt" prop="sellingformat">
                                        <el-select placeholder="请选择销售方式"
                                                   v-model="comeconomicmodule.sellingformat"
                                                   class="input-text">
                                            <el-option label="网购" value="网购"></el-option>
                                            <el-option label="实体店" value="实体店"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                        <el-input placeholder="请输入订单号"
                                                  v-model="comeconomicmodule.ordernumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="涉及问题类型：" class="bt" prop="reportprovidelb">
                                        <el-select
                                                placeholder="涉及问题类型"
                                                v-model="comeconomicmodule.reportprovidelb"
                                                class="input-text">
                                            <el-option label="虚假宣传" value="虚假宣传"></el-option>
                                            <el-option label="产品劣质" value="产品劣质"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="产品名称：" class="bt" prop="productname">
                                        <el-input placeholder="请输入产品名称"
                                                  v-model="comeconomicmodule.productname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="批准文号：" class="bt" prop="approvalnumber">
                                        <el-input placeholder="请输入批准文号"
                                                  v-model="comeconomicmodule.approvalnumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="产品批号：" class="bt" prop="lotnumber">
                                        <el-input placeholder="请输入产品批号"
                                                  v-model="comeconomicmodule.lotnumber"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="产品规格：" class="bt" prop="typespecification">
                                        <el-input placeholder="请输入产品规格"
                                                  v-model="comeconomicmodule.typespecification"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="生产厂家：" class="bt" prop="producecompany">
                                        <el-input placeholder="请输入生产厂家"
                                                  v-model="comeconomicmodule.producecompany"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="销售企业：" class="bt">
                                        <el-input placeholder="请输入销售企业"
                                                  v-model.number="comeconomicmodule.salesenterprise "
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="产品有效期：" class="bt" prop="expirationdate">
                                        <el-date-picker
                                                type="date"
                                                v-model="comeconomicmodule.expirationdate"
                                                class="input-text"
                                                style="width: 100%"
                                                value-format="yyyy-MM-dd"
                                                @change="dateChanges"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="关键字：" class="bt" prop="keyword">
                                        <el-input placeholder="请输入关键字"
                                                  v-model.number="comeconomicmodule.keyword"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="举报内容:" prop="problemdescribe" class="bt">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 6, maxRows: 8}"
                                                placeholder="请输入举报内容(3000字以内)"
                                                v-model="comeconomicmodule.problemdescribe">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="相关附件:" class="bt" prop="attachfilename">
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
                </el-collapse>
                <el-form>
                    <el-row>
                        <el-col style="height: 15px;"></el-col>
                        <el-col style="text-align: center">
                            <el-button type="primary" @click="submitFrom('comeconomicmodule')">
                                暂存
                            </el-button>
                            <el-button type="primary" @click="toAccept('accept')">
                                受理
                            </el-button>
                            <el-button type="primary" @click="toAccept('nextOrg')">
                                分派下级
                            </el-button>
                            <el-button type="primary" @click="toAccept('noAccept')">
                                不受理
                            </el-button>
                            <el-button type="primary">
                                直接归档
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane :label="acceptForm.isAccep==='accept'?'受理':'不受理'" v-if="acceptForm.isAccep!==''">
                <el-form :model="acceptForm" ref="acceptForm" style="width: 96%;margin: 0 auto" label-width="105px"
                         :rules="acceptFormRules">
                    <el-row v-if="acceptForm.isAccep==='accept'" style="margin: 20px">
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="是否受理：" prop="isAccep" class="form_bt">
                                <div style="line-height: 35px">
                                    {{acceptForm.isAccep==='accept'?'受理':'不受理'}}
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="受理方式：" prop="mode" class="form_bt">
                                <el-select v-model="acceptForm.mode" placeholder="请选择"
                                           style="width: 200px;">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="8">
                            <el-button type="primary" @click="accepts('受理')">
                                确定
                            </el-button>
                        </el-col>
                    </el-row>
                    <el-row v-else style="margin: 20px">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="isAccep" class="bt">
                                <div style="line-height: 35px">
                                    {{acceptForm.isAccep==='accept'?'受理':'不受理'}}
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="reason" class="bt">
                                <el-select v-model="acceptForm.reason" placeholder="请选择" prop="department"
                                           style="width: 180px;">
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
                            <el-form-item label="不受理内容：" class="form_bt" prop="content"
                                          style="width: 80%">
                                <el-input type="textarea" :rows="4" placeholder="请输入内容"
                                          v-model="acceptForm.content"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="附件上传：" class="form_bt"
                                          style="width: 80%">
                                <el-upload :auto-upload="false" :file-list="fileList" ref="upload"
                                           action="https://jsonplaceholder.typicode.com/posts/">
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                        <el-col style="text-align: center">
                            <el-button type="primary" @click="accepts('不受理')">
                                确定
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="分派下级" v-if="nextOrg">
                <el-form :model="assignmentForm" label-width="180px" ref="assignmentForm" label-suffix="："
                         :rules="assignmentFormRules" >
                    <el-form-item label="成员单位" class="form_bt" prop="unit" style="margin-top: 15px;">
                        <el-select v-model="assignmentForm.unit" placeholder="请选择成员单位" clearable @visible-change="con()"
                                   style="width: 300px;">
                            <el-option
                                    v-for="item in dept"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.primaryKey">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分派意见" class="form_bt" prop="opinion" style="width: 80%">
                        <el-input type="textarea" :rows="4" placeholder="请输入分派意见（3000字内）"
                                  v-model="assignmentForm.opinion"></el-input>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="accepts('分派下级')">确认</el-button>
                    </el-form-item>
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
    import {saveBaseInfo, next, searchBaseInfo,findOrgUnitByRegionId} from '../../api/api';

    export default {
        data() {
            return {
                nextOrg:false,
                aictabs: 'comeconomicmodule',
                type: '',
                textarea: '',
                acceptForm: {
                    isAccep: '',
                    opinion: '',
                    department: '',
                    content: '',
                },
                acceptFormRules: {
                    isAccep: [
                        {required: true},
                    ],
                    reason: [
                        {required: true, message: '请选择不受理原因', trigger: 'change'},
                    ],
                    mode: [
                        {required: true, message: '请选择受理方式', trigger: 'change'},
                    ],
                    content: [
                        {required: true, message: '请输入', trigger: 'blur'},
                    ],
                },
                dept: [],
                assignmentForm: {
                    unit: undefined,
                    opinion: "拟转XX跟进处理。",
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
                reasonBack: [{
                    value: '缺失要素',
                    label: '缺失要素'
                }, {
                    value: '表述不清',
                    label: '表述不清'
                }, {
                    value: '缺失要素',
                    label: '缺失要素'
                }, {
                    value: '类型错误',
                    label: '类型错误'
                }, {
                    value: '地址不祥',
                    label: '地址不祥'
                }, {
                    value: '已有知识',
                    label: '已有知识'
                }, {
                    value: '重复受理',
                    label: '重复受理'
                }, {
                    value: '不属本职',
                    label: '不属本职'
                }, {
                    value: '市民撤诉',
                    label: '市民撤诉'
                }],
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: '',
                comeconomicmodule: {
                    id: '',
                    caseguid: '',
                    formtype: '经济违法',
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
                    // 处办方式
                    ishandle:'',
                    depart: ''

                },
               comeconomicmoduleRules: {
                   /*     rqsttitle: [
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
                       providetype: [
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
                           {max: 200, message: '不能超出200个字符', trigger: 'blur'},],*/
                   },
                formType: 'comeconomicmodule',
                processInofActiveNames: ['1', '2', '3', '4', '5', '6'],
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
             // 获取单位列表
            con(value){
                let user = localStorage.getItem('user');
                let users = JSON.parse(user);
                let para = {
                    regionId:users.regionID,
                }
                findOrgUnitByRegionId(para).then((res) => {
                    this.dept = res.data.data

                });
            },
            flow(visible) {
                if (visible.name === 'comeconomicmodule') {
                    this.aictabs = 'comeconomicmodule'
                    this.acceptForm.isAccep = ''
                    this.nextOrg = false
                }
            },
            accepts(value) {
                let validation = false;
                // this.$refs.acceptForm.validate((valid) => validation = valid);
                // if (!validation) {
                //     return
                // }
                // let nextNodeId;
                // let opinion;

                let data = this.comeconomicmodule
                let user = localStorage.getItem('user');

                if ("受理" === value) {
                    // nextNodeId = 'registerCase'
                    // opinion = '受理'
                    let para = {
                        user: JSON.parse(user),
                        node:'begin',
                        nextStepForm:this.acceptForm,
                        formType:this.formType,
                        data,
                        nextNodeId:'accept',
                        opinion:this.acceptForm.isAccep,
                    }
                    next(para).then((res) => {
                        let para = {
                            id:res.data.id,
                            caseguid: res.data.caseguid,
                            // node:res.data.node
                            node:'accept'
                        };
                        console.log(para.node)
                        this.$router.push({path:'/wddb/bxtdb/sldjla', query: para})
                    });

                } else if("不受理" === value) {
                    let para ={
                        user: JSON.parse(user),
                        node:'begin',
                        // nextStepForm:this.nextStepForm,
                        formType:"comeconomicmodule",
                        data:this.comeconomicmodule,
                        nextNodeId:'noAccept',
                        opinion:this.acceptForm.process,
                        isEnd:'1'
                    }
                    next(para).then((res) => {
                        this.$router.push('/wddb/bxtdb')
                    });

                    // opinion = this.acceptForm.content
                    // nextNodeId = 'end'
                }else {
                    let para = {
                        user: JSON.parse(user),
                        node:'begin',
                        nextNodeId:'moveToLowerOrg',
                        formType: this.formType,
                        data,
                        opinion:this.assignmentForm.opinion,
                        orgid:this.assignmentForm.unit,
                    }
                    console.log(para)
                    next(para).then((res) => {
                        if (res.code === 0) {
                             this.$message({
                                message: '分派下级成功!',
                                type: 'success',
                                duration:'1000'
                             });
                            let para = {
                                id: res.data.id,
                                caseguid: res.data.caseguid,
                                node:'moveToLowerOrg',
                            };
                            this.$router.push({path: '/wddb/bxtdb/qtsl', query: para})
                        }
                    });


                    // node:'begin',
                    // nextNodeId = 'moveToLowerOrg'
                    // opinion = this.assignmentForm.opinion
                }
                // let user = localStorage.getItem('user');
               /* let para = {
                    user: JSON.parse(user),
                    node: 'begin',
                    acceptForm: this.acceptForm,
                    opinion,
                    formType: this.formType,
                    data: this.comeconomicmodule,
                    nextNodeId
                }
                next(para).then((res) => {
                    if (nextNodeId === 'end') {
                        this.$router.push({path: '/wddb/bxtdb'})
                    } else {
                        let para = {
                            id: res.data.id,
                            caseguid: res.data.caseguid,
                        };
                        this.$router.push({path: '/wddb/bxtdb/sldjla', query: para})
                    }
                });*/
            },

            toAccept(isAccep) {
                let validation = false;
                this.$refs['comeconomicmoduleSubject'].validate((valid) => validation = valid);
                this.$refs['comeconomicmoduleProvider'].validate((valid) => validation = valid && validation);
                this.$refs['comeconomicmoduleObject'].validate((valid) => validation = valid && validation);
                this.$refs['comeconomicmoduleBusiness'].validate((valid) => validation = valid && validation);
                if (validation) {
                    this.aictabs = '1'
                    if("nextOrg" == isAccep){
                        this.nextOrg = true
                    }else {
                        this.acceptForm.isAccep = isAccep
                    }
                }
            },
            dateChange(date) {
                this.comeconomicmodule.recorddate = date
            },
            dateChanges(date) {
                this.comeconomicmodule.expirationdate = date
            },

            submitFrom(formType) {
                this.formType = formType;
                let validation = true;
                this.$refs['comeconomicmoduleSubject'].validate((valid) => validation = valid);
                this.$refs['comeconomicmoduleProvider'].validate((valid) => validation = valid && validation);
                this.$refs['comeconomicmoduleObject'].validate((valid) => validation = valid && validation);
                this.$refs['comeconomicmoduleBusiness'].validate((valid) => validation = valid && validation);
                //通过提交
                if (validation) {
                    let user = localStorage.getItem('user');
                    let para = {
                        user: JSON.parse(user),
                        formType: this.formType,
                        comeconomicmodule: this.comeconomicmodule,
                        node: 'staging',
                        nextNodeId: 'accept',
                        method: "submit",
                    };
                    console.log(para)
                    //把信息公开数组转字符
                    // para.commommodule.informationOpen = para.commommodule.informationOpen.toString();
                    // para.complaintmodule.informationOpen = para.complaintmodule.informationOpen.toString();
                    saveBaseInfo(para).then((res) => {
                        if ("comeconomicmodule" === res.data.formType) {
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
                } else {
                    this.$message({message: '请检查表单是否有为空或超出长度！', type: 'info'})
                }
            },
            toGuiDan(formType) {
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
            getBaseInfo() {
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query
                if (!id || !caseguid) {
                    return
                }
                let systemsource = '01'
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource}).then((res) => {
                    this.tasklist = res.data.tasklist
                    if (res.data.caseprocesform != null) {
                        this.caseprocesform = res.data.caseprocesform
                    }
                    if (res.code === 0) {
                        if (res.data.economyform != null) {//经济消费
                            this.formType = 'comeconomicmodule'
                            this.comeconomicmodule = res.data.economyform
                        } else {//找不到对的工单信息
                            this.$alert('找不到对应的工单信息!', '提示', {
                                confirmButtonText: '确定',
                                type: 'warning',
                            });
                            this.$router.push('/wddb/bxtdb')
                        }
                        this.opinionList = res.data.opinionList
                        this.loading = false
                    }
                }).catch((error) => {
                    console.error(error);
                    this.$message({
                        message: '请求失败',
                        type: 'error'
                    });
                })
            }
        },
        created() {
            this.getBaseInfo()
        },
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
