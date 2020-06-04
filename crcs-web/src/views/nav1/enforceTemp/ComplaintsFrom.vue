<template>
    <section class="fromExamine">
        <el-row style="margin-top: 5px;margin-bottom: 5px">
            <el-col>
                <el-steps :active="1" align-center center space="22%">
                    <el-step title="受理登记"></el-step>
                    <el-step title="工单审核"></el-step>
                    <el-step title="工单处理"></el-step>
                    <el-step title="延时申请审批"></el-step>
                    <el-step title="结果反馈"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-tabs type="border-card" style="width: 96%; margin: 0 auto; min-height: 400px;" v-model="aictabs" @tab-click="flows">
            <el-tab-pane label="消费投诉表单" name="complaintmodule">
                <el-collapse v-model="registrationActiveNames" class="accordion">
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
                        <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleSystem"
                                 :rules="complaintmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="记录类型:" class="bt">
                                        <div style="line-height: 35px">
                                            {{complaintmodule.formtype}}
                                        </div>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                        <el-input v-model="complaintmodule.rqsttitle" placeholder="请输入标题"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="登记日期：" class="bt" prop="recorddate">
                                        <el-date-picker
                                                type="date"
                                                v-model="complaintmodule.recorddate"
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
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="接收方式：" class="bt" prop="receivemode">
                                        <el-select placeholder="请选择"
                                                   v-model="complaintmodule.receivemode"
                                                   class="input-text">
                                            <el-option label="举报" value="举报"></el-option>
                                            <el-option label="投诉" value="投诉"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="业务分类:" prop="businesstype" class="bt">
                                        <el-select v-model="complaintmodule.businesstype"
                                                   placeholder="请选择业务分类" class="input-text">
                                            <el-option label="投诉类" value="投诉类"></el-option>
                                            <el-option label="咨询类" value="咨询类"></el-option>
                                            <el-option label="执法类" value="执法类"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="工单分类:" prop="focus" class="bt">
                                        <el-select v-model="complaintmodule.focus" placeholder="请输入工单分类"
                                                   class="input-text">
                                            <el-option label="普通" value="普通"></el-option>
                                            <el-option label="特殊" value="特殊"></el-option>
                                            <el-option label="特派" value="特派"></el-option>
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
                                    提供方信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleProvider"
                                 :rules="complaintmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="姓名：" class="bt" prop="rqstperson">
                                        <el-input placeholder="请输入姓名"
                                                  v-model="complaintmodule.rqstperson"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="性别：" class="bt" prop="gender">
                                        <el-select v-model="complaintmodule.gender" placeholder="请选择性别"
                                                   class="input-text">
                                            <el-option label="男" value="男"></el-option>
                                            <el-option label="女" value="女"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="联系电话：" class="bt" prop="linknumber">
                                        <el-input v-model="complaintmodule.linknumber"
                                                  placeholder="请输入联系电话"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="年龄段：" class="bt" prop="age">
                                        <el-select placeholder="请选择"
                                                   v-model="complaintmodule.age"
                                                   class="input-text">
                                            <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                            <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                            <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="证件类型：" class="bt" prop="certificate">
                                        <el-select placeholder="请选择"
                                                   v-model="complaintmodule.certificate"
                                                   class="input-text">
                                            <el-option label="身份证" value="身份证"></el-option>
                                            <el-option label="军官证" value="军官"></el-option>
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="证件号码：" class="bt" prop="idnumber">
                                        <el-input v-model="complaintmodule.idnumber"
                                                  placeholder="请输入证件号码"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="联系地址：" class="bt" prop="linkaddress">
                                        <el-input v-model="complaintmodule.linkaddress"
                                                  placeholder="请输入"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="电子邮箱：" class="bt" prop="emaiaddress">
                                        <el-input v-model="complaintmodule.emaiaddress"
                                                  placeholder="请输入邮箱"
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
                                    <el-form-item label="涉及主体名称：" class="bt" prop="subjectname">
                                        <el-input placeholder="请输入名称"
                                                  v-model="complaintmodule.subjectname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="统一社会信用代码：" class="bt" prop="registerno">
                                        <el-input placeholder="请输入名称"
                                                  v-model="complaintmodule.registerno"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="市场主体类型：" class="bt" prop="entitytype">
                                        <el-select placeholder="请选择"
                                                   v-model="complaintmodule.entitytype"
                                                   class="input-text">
                                            <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                            <el-option label="国有企业" value="国有企业"></el-option>
                                            <el-option label="外资企业" value="外资企业"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="联系电话：" class="bt" prop="subjectphone">
                                        <el-input placeholder="请输入联系电话"
                                                  v-model="complaintmodule.subjectphone"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="地址：" class="bt" prop="subjectaddress">
                                        <el-input placeholder="请输入地址"
                                                  v-model="complaintmodule.subjectaddress"
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
                                    业务具体信息
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="complaintmodule" label-width="150px" ref="complaintmoduleBusiness"
                                 :rules="complaintmoduleRules">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="购物方式：" class="bt" prop="shoppingmode">
                                        <el-select v-model="complaintmodule.shoppingmode"
                                                   placeholder="请选择"
                                                   class="input-text">
                                            <el-option label="B2C" value="B2C"></el-option>
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="商品/服务类型：" class="bt" prop="servicetype">
                                        <el-select placeholder="请选择"
                                                   v-model="complaintmodule.servicetype"
                                                   class="input-text">
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="品牌：" class="bt" prop="brand">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.brand"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.objname"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="型号规格：" class="bt" prop="typespecification">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.typespecification"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="数量：" class="bt" prop="quantity">
                                        <el-input placeholder="请输入数量"
                                                  v-model="complaintmodule.quantity"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="争议发生时间：" class="bt" prop="disputedate">
                                        <el-date-picker
                                                type="date"
                                                v-model="complaintmodule.disputedate"
                                                class="input-text"
                                                style="width: 100%"
                                                value-format="yyyy-MM-dd"
                                                @change="dateChanges"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="消费金额：" class="bt" prop="objmoney">
                                        <el-input placeholder="请输入金额"
                                                  v-model="complaintmodule.objmoney"
                                                  class="input-text">
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="诉求内容：" class="bt" prop="report">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.report"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="涉及问题类型：" class="bt" prop="complaintstype">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.complaintstype"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.ordernumber"
                                                  class="input-text">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="投诉内容：" class="bt" prop="problemdescribe">
                                        <el-input placeholder="请输入"
                                                  v-model="complaintmodule.problemdescribe"
                                                  type="textarea" :autosize="{ minRows: 3, maxRows: 5}"
                                                  class="input-text"></el-input>
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
                            <el-button type="primary" @click="toAccept('accepts')">
                                受理
                            </el-button>
                            <el-button type="primary" @click="toAccept('nextOrg')">
                                分派下级
                            </el-button>
                            <el-button type="primary" @click="toAccept('notAccepts')">
                                不受理
                            </el-button>
                            <el-button type="primary">
                                直接归档
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="受理" v-if="accept">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto" :rules="nextStepFormRules">
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="5">
                            <el-form-item label="是否受理：" prop="department">
                                <el-select v-model="nextStepForm.department" placeholder="请选择"
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="6">
                            <el-form-item label-width="0" prop="process">
                                <el-select v-model="nextStepForm.process" placeholder="请选择"
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="6">
                            <el-button type="primary" @click="accepts('受理')">
                                <!--                            <el-button type="primary" @click="">-->
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
                        <el-select v-model="assignmentForm.unit" placeholder="请选择成员单位" clearable @visible-change="orgChange()"
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
            <el-tab-pane label="不受理" v-if="notAccepts">
                <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto" :rules="nextStepFormRules">
                    <el-row style="margin: 20px 0;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="opinion" label-width="105px" class="bt">
                                <div style="line-height: 35px">
                                    {{nextStepForm.noAccept}}
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="department" label-width="130px" class="bt">
                                <el-select v-model="nextStepForm.department" placeholder="请选择" prop="department"
                                           style="width: 180px;" clearable>
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
                            <el-form-item label="不受理内容:" class="form_bt" prop="centent" label-width="105px"
                                          style="width: 80%">
                                <el-input type="textarea" :rows="4" placeholder="请输入内容"
                                          v-model="nextStepForm.centent"></el-input>
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
                            <el-button type="primary" @click="accepts('不受理')">
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

<script>
    import {saveBaseInfo,next,searchBaseInfo,findOrgUnitByRegionId} from '../../../api/api';

    export default {
        data() {
            return {
                consults:false,
                aictabs: 'complaintmodule',
                type: '',
                textarea: '',
                nextStepForm: {
                    noAccept:'不受理',
                    department:'',
                    process:'',
                },
                nextStepFormRules: {
                    department: [
                        {required: true, message: '请选择', trigger: 'change'},
                    ],
                    process: [
                        {required: true, message: '请选择受理方式', trigger: 'change'},
                    ],
                    centent: [
                        {required: true, message: '请输入', trigger: 'blur'},
                    ],
                },
                assignmentForm: {
                    unit: undefined,
                    opinion: undefined,
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
                dept: [],
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
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                accept: false,
                notAccepts: false,
                nextOrg:false,
                fileList: [],
                fileUploadText: '',
                sourceForm: {},
                complaintmodule: {
                    id:'',
                    caseguid:'',
                    formtype:'消费投诉',
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
                    age:'',
                    nation:'',
                    status:'',
                    complaintpaper:'',
                    disabilityidentification:'',
                    certificate:'',
                    codepostal:'',
                    idnumber:'',
                    career:'',
                    company:'',
                    subjectname:'',
                    linktype:'',
                    subjectphone:'',
                    aicstationid:'',
                    postcode:'',
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
                    importmark:'',
                    typespecification:'',
                    quantity:'',
                    measureunit:'',
                    price:'',
                    purchasingdate:'',
                    problemtype:'',
                    problemdescribe:'',
                    keyword:'',
                    govlabeloutput:'',
                    differserv :'',
                    briefing:'',
                    anestimate:'',
                    economicloss:'',
                    tortclassify:'',
                    personalinjury:'',
                    injurydegree:'',
                    injuries:'',
                    consumptionsafety:'',
                    consumptionsafetytype:'',
                    even:'',
                    reduction:'',
                    report:'',
                    isoutmall:'',
                    attachfilename:'',
                    updatedate:'',
                    appealsource:'',
                    informationopen:'',
                    businesstype:'',
                    eventlevel:'',
                    rqstaddress:'',
                    registrationnumber:'',
                    recorddate:moment().toDate(),
                    receivemode:'',
                    linkaddress:'',
                    emaiaddress:'',
                    registerno:'',
                    entitytype:'',
                    shoppingmode:'',
                    servicetype:'',
                    complaintstype:'',
                    contentrs:'',
                    disputedate:'',
                    ordernumber:'',
                    businesstypecodeid: "",
                    entitytypecodeid: "",
                    servicetypecodeid: "",
                    complaintstypecodeid: "",
        },
                complaintmoduleRules: {
                    // rqstperson: [
                    //     {required: true, message: '请输入姓名', trigger: 'blur'},
                    //     {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    // ],
                    // gender: [
                    //     {required: true, message: '请选择性别', trigger: 'change'},
                    // ],
                    // rqstaddress: [
                    //     {required: true, message: '请输入举报人地址', trigger: 'blur'},
                    //     {max: 500, message: '不能超出500个字符', trigger: 'blur'},
                    // ],
                    // rqstnumber: [
                    //     {required: true, message: '请输入来电号码', trigger: 'blur'},
                    //     {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    // ],
                    // linknumber: [
                    //     {required: true, message: '请输入联系电话', trigger: 'blur'},
                    //     {max: 200, message: '不能超出200个字符', trigger: 'blur'},
                    // ],
                    // usertype: [
                    //     {required: true, message: '请选择反映类型', trigger: 'change'},
                    // ],
                    // providetype: [
                    //     {required: true, message: '请选择类型', trigger: 'change'},
                    // ],
                    // focus: [
                    //     {required: true, message: '请选择工单分类', trigger: 'change'},
                    // ],
                    // providelb: [
                    //     {required: true, message: '请选择类别', trigger: 'change'},
                    // ],
                    // appealsource: [
                    //     {required: true, message: '请选择诉求来源', trigger: 'change'},
                    // ],
                    // subjectname: [
                    //     {required: true, message: '请输入名称', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // subjectphone: [
                    //     {required: true, message: '请输入联系电话', trigger: 'blur'},
                    //     {max: 30, message: '不能超出30个字符', trigger: 'blur'},
                    // ],
                    // aicstationid: [
                    //     {required: true, message: '请输入管辖单位', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // postcode: [
                    //     {required: true, message: '请输入邮编', trigger: 'blur'},
                    //     {max: 20, message: '不能超出20个字符', trigger: 'blur'},
                    // ],
                    // subjectaddress: [
                    //     {required: true, message: '请输入经营地址', trigger: 'blur'},
                    //     {max: 500, message: '不能超出500个字符', trigger: 'blur'},
                    // ],
                    // objclassify: [
                    //     {required: true, message: '请选择涉及客体分类', trigger: 'change'},
                    // ],
                    // objname: [
                    //     {required: true, message: '请输入商品/服务名称', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // brand: [
                    //     {required: true, message: '请输入商品品牌', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // objmoney: [
                    //     {type: 'number', required: true, message: '请输入涉及金额', trigger: 'blur'},
                    // ],
                    // producecompany: [
                    //     {required: true, message: '请输入生产企业', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // isweb: [
                    //     {required: true, message: '请选择是否网购', trigger: 'change'},
                    // ],
                    // problemtype: [
                    //     {required: true, message: '请选择涉及问题分类', trigger: 'change'},
                    // ],
                    // problemdescribe: [
                    //     {required: true, message: '请输入问题陈述', trigger: 'blur'},
                    //     {max: 3000, message: '不能超出3000个字符', trigger: 'blur'},],
                    // keyword: [
                    //     {required: true, message: '请输入关键字', trigger: 'blur'},
                    //     {max: 100, message: '不能超出100个字符', trigger: 'blur'},
                    // ],
                    // briefing: [
                    //     {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    // ],
                    // injuries: [
                    //     {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    // ],
                    // even: [
                    //     {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    // ],
                    // reduction: [
                    //     {max: 50, message: '不能超出50个字符', trigger: 'blur'},
                    // ],
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
                formType: 'complaintmodule',
                postpone: false,
                coupleBack: false,

                businessForm: {
                    classification: '',
                    level: '',
                    illegal: '',
                },
                subjectForm: {},

                mainForm: {},
                registrationActiveNames: ['1', '2', '3', '4'],
                processInofActiveNames: ['1', '2', '3', '4', '5', '6'],
                // activeNameas: 'complaintmodule',
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
            // 查询单位
             orgChange(value){
                let user = localStorage.getItem('user');
                let users = JSON.parse(user);
                let para = {
                    regionId:users.regionID,
                }
                findOrgUnitByRegionId(para).then((res) => {
                    this.dept = res.data.data

                });
            },
            accepts(value){
                let validation = false;
                // this.$refs.nextStepForm.validate((valid) => validation = valid);
                // if (!validation) {
                //     return
                // }
                let data = this.complaintmodule
                let user = localStorage.getItem('user');
                if("受理" == value){
                    let para ={
                        user: JSON.parse(user),
                        node:'begin',
                        nextStepForm:this.nextStepForm,
                        formType:this.formType,
                        data,
                        nextNodeId:'accept',
                        opinion:this.nextStepForm.process,
                    }
                    next(para).then((res) => {
                        let para = {
                            id:res.data.id,
                            caseguid: res.data.caseguid,
                            node:'accept',
                        };
                        this.$router.push({path:'/wddb/bxtdb/qtsl', query: para})
                    });
                }else if("不受理" == value){
                    let para ={
                        user: JSON.parse(user),
                        node:'begin',
                        // nextStepForm:this.nextStepForm,
                        formType:"complaintmodule",
                        data:this.complaintmodule,
                        nextNodeId:'noAccept',
                        opinion:this.nextStepForm.process,
                        isEnd:'1'
                    }
                    next(para).then((res) => {
                        this.$router.push('/wddb/bxtdb')
                    });
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
                    next(para).then((res) => {
                        if (res.code === 0) {
                           
                            this.$message({
                                message: '分派下级成功！',
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
                }

                // this.$router.push({path:'/wddb/bxtdb/sldjla', query: para})
            },

            flows(visible) {
                this.aictabs = 'complaintmodule'
                this.accept = false
                this.notAccepts = false
                this.nextOrg = false
            },

            toAccept(value) {
                let validation;
                this.$refs['complaintmoduleSystem'].validate((valid) => validation = valid);
                this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                this.$refs['complaintmoduleSubject'].validate((valid) => validation = valid && validation);
                this.$refs['complaintmoduleBusiness'].validate((valid) => validation = valid && validation);
                if (!validation) {
                    return
                }
                this.aictabs = '1'
                if("accepts" == value){
                    this.accept = true
                } else if("notAccepts" == value){
                    this.notAccepts = true
                }else {
                    this.nextOrg = true
                }
            },

            dateChange(date) {
                this.complaintmodule.recorddate = date
            },

            dateChanges(date) {
                this.complaintmodule.disputedate = date
            },

            submitFrom(formType) {
                this.formType = formType;
                let validation = true;
                // this.$refs['complaintmoduleSubject'].validate((valid) => validation = valid);
                // this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                // this.$refs['complaintmoduleKeTi'].validate((valid) => validation = valid && validation);
                // this.$refs['complaintmoduleBusiness'].validate((valid) => validation = valid && validation);
                console.log(this.complaintmodule)
                //通过提交
                if (validation) {
                    let user = localStorage.getItem('user');
                    let para = {
                        user: JSON.parse(user),
                        formType: this.formType,
                        complaintmodule: this.complaintmodule,
                        node:'staging',
                        nextNodeId:'accept',
                        method: "submit",
                    };
                    //把信息公开数组转字符
                    saveBaseInfo(para).then((res) => {
                        console.log(res)
                        if("commommodule" == res.data.formType){
                            this.commommodule = res.data.customerinfo
                        }else if("complaintmodule" == res.data.formType){
                            this.complaintmodule = res.data.complaintform
                        }else if("comeconomicmodule" == res.data.formType){
                            this.comeconomicmodule = res.data.comeconomicmodule
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
            submitUpload() {
                this.$refs.upload.submit();
            },
            nextStep() {
            },
            removeFile() {
                this.$confirm('确认删除选中的文件?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                }).catch(() => {

                });
            },
            getBaseInfo() {
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query
                if (!id || !caseguid) {
                    return
                }
                let systemsource = '02'
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource}).then((res) => {
                    console.log(res)
                    this.tasklist = res.data.tasklist
                    if(res.data.caseprocesform != null){
                        this.caseprocesform = res.data.caseprocesform
                    }
                    if (res.code === 0) {
                        if (res.data.complaintform != null) {//消费投诉表单
                            this.formType = 'complaintmodule'
                            this.formTypeStr = '消费投诉表单'
                            this.complaintmodule = res.data.complaintform
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
