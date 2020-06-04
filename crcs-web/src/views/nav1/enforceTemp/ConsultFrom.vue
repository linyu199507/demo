<template>
    <section class="fromExamine">
        <!--    <el-row style="margin-top: 5px;margin-bottom: 5px">-->
        <!--      <el-col>-->
        <!--        <el-steps :active="stepNumber" align-center center space="22%">-->
        <!--          <el-step icon="erpdengji" title="受理登记"></el-step>-->
        <!--          <el-step icon="erpshenhe" title="工单审核"></el-step>-->
        <!--          <el-step icon="erpdaichulidingdan" title="工单处理"></el-step>-->
        <!--          <el-step icon="erpshenqingshenpi" title="延时申请审批"></el-step>-->
        <!--          <el-step icon="erpyijianfankui" title="结果反馈"></el-step>-->
        <!--        </el-steps>-->
        <!--      </el-col>-->
        <!--    </el-row>-->
        <!-- <el-tabs type="border-card" style="width: 96%; margin: 0 auto; min-height: 400px;" v-model="aictabs"
    @tab-click="flows">-->
        <el-tabs
                type="border-card"
                style="width: 96%; margin: 15px auto; min-height: 400px;"
                v-model="aictabs"
                @tab-click="flows"
        >
            <!-- 咨询登记 -->
            <el-tab-pane label="咨询登记" name="commommodule">
                <el-collapse v-model="registrationActiveNames" class="accordion">
                    <el-collapse-item name="1">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="12">表单信息</el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right">
                                </el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="commommodule"
                                label-width="150px"
                                ref="commommodule"
                                :rules="commommoduleRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="20">
                                    <el-form-item label="表单类型:" class="bt">
                                        <div style="line-height: 35px">{{commommodule.formtype}}</div>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="commommodule.businesstypecodeid">
                                    <el-form-item label="业务分类:" prop="businesstypecodeid" class="bt">
                                        <!--v-model="commommodule.businesstype"-->
                                        <el-cascader
                                                filterable
                                                :options="businesstype1"
                                                v-model="commommodule.businesstypecodeid"
                                                @change="handleChangebusinesstype1"
                                                :show-all-levels="false"
                                                class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="姓名:" prop="name" class="bt">
                                        <el-input placeholder="请输入姓名" v-model="commommodule.name"
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
                                        <el-input
                                                placeholder="请输入来电号码"
                                                v-model="commommodule.rqstnumber"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="联系电话:" prop="linknumber" class="bt">
                                        <el-input
                                                placeholder="请输入联系电话"
                                                v-model="commommodule.linknumber"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="是否紧急:" prop="worktype" class="bt">
                                        <el-select v-model="commommodule.worktype" placeholder="请选择" class="input-text">
                                            <el-option label="紧急" value="紧急"></el-option>
                                            <el-option label="普通" value="普通"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="类别:" prop="category" class="bt">
                                        <el-select
                                                v-model="commommodule.category"
                                                placeholder="请选择反映类型"
                                                class="input-text"
                                        >
                                            <el-option label="儿童" value="儿童"></el-option>
                                            <el-option label="妇女" value="妇女"></el-option>
                                            <el-option label="老人" value="老人"></el-option>
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                    <el-form-item label="咨询人地址:" class="bt" prop="address">
                                        <el-select v-model="commommodule.region" placeholder="区域" class="input-texts">
                                            <el-option label="乐从镇" value="乐从镇"></el-option>
                                            <el-option label="陈村镇" value="陈村镇"></el-option>
                                            <el-option label="勒流镇" value="勒流镇"></el-option>
                                            <el-option label="大良镇" value="大良镇"></el-option>
                                            <el-option label="杏坛镇" value="杏坛镇"></el-option>
                                            <el-option label="均安镇" value="均安镇"></el-option>
                                            <el-option label="容桂镇" value="容桂镇"></el-option>
                                            <el-option label="龙江镇" value="龙江镇"></el-option>
                                            <el-option label="伦教镇" value="伦教镇"></el-option>
                                            <el-option label="北滘镇" value="北滘镇"></el-option>
                                        </el-select>
                                        <el-input
                                                placeholder="请输入地址"
                                                v-model="commommodule.address"
                                                class="input-text"
                                                style="width:74%"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="反映类型:" prop="reflectiontype" class="bt">
                                        <el-select
                                                v-model="commommodule.reflectiontype"
                                                placeholder="请选择反映类型"
                                                class="input-text"
                                        >
                                            <el-option label="个人" value="个人"></el-option>
                                            <el-option label="企业" value="企业"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="工单分类:" prop="Focus" class="bt">
                                        <el-select
                                                v-model="commommodule.focus"
                                                placeholder="请选择工单分类"
                                                class="input-text"
                                        >
                                            <el-option label="普通" value="普通"></el-option>
                                            <el-option label="特殊" value="特殊"></el-option>
                                            <el-option label="特派" value="特派"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="诉求来源:" prop="appealsource" class="bt">
                                        <el-select
                                                placeholder="请选择类型"
                                                class="input-text"
                                                v-model="commommodule.appealsource"
                                        >
                                            <el-option label="上级交办" value="上级交办"></el-option>
                                            <el-option label="外地移送、其他部门" value="外地移送、其他部门"></el-option>
                                            <el-option label="抽查抽检" value="抽查抽检"></el-option>
                                            <el-option label="来信" value="来信"></el-option>
                                            <el-option label="来人来访" value="来人来访"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="24" :span="16">
                                    <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                        <el-input
                                                v-model="commommodule.rqsttitle"
                                                placeholder="请输入标题"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="问题描述:" prop="rqstcontent" class="bt">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 6, maxRows: 8}"
                                                placeholder="请输入问题描述(3000字以内)"
                                                v-model="commommodule.rqstcontent"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="咨询表单相关附件:" class="bt">
                                        <el-upload
                                                :auto-upload="false"
                                                :file-list="fileListscommommodule"
                                                ref="uploadCommommodule"
                                                :data="this.requestMap"
                                                :on-preview="previewFile"
                                                :on-progress="progress"
                                                :on-success="uploadSuccess"
                                                :before-upload="beforeUpload"
                                                :multiple="true"
                                                action="/crcs/filesUpload/upload"
                                        >
                                            <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                            <el-button
                                                    size="small"
                                                    type="success"
                                                    @click="submitUpload('uploadCommommodule')"
                                            >确认上传
                                            </el-button>
                                        </el-upload>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="2">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="12">表单处办</el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="commommoduleOfficeForm"
                                label-width="150px"
                                ref="commommoduleOfficeForm"
                                :rules="commommoduleOfficeFormRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="归档单位:" prop="unit" class="bt">
                                        <el-select
                                                placeholder="请选择归档部门"
                                                class="input-text"
                                                v-model="commommoduleOfficeForm.unit"
                                        >
                                            <el-option label="部门1" value="部门1"></el-option>
                                            <el-option label="部门2" value="部门2"></el-option>
                                            <el-option label="部门3" value="部门3"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="处办方式:" prop="way" class="bt">
                                        <el-select
                                                placeholder="请选择处办方式"
                                                class="input-text"
                                                v-model="commommoduleOfficeForm.way"
                                        >
                                            <el-option label="直接归档" value="直接归档"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="处理意见:" prop="idea" class="bt">
                                        <el-input
                                                v-model="commommoduleOfficeForm.idea"
                                                type="textarea"
                                                :autosize="{ minRows: 6, maxRows: 8}"
                                                placeholder="请输入处理意见(3000字以内)"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <!--是否受理部分  -->
                <el-row>
                    <el-col style="height: 15px;"></el-col>
                    <el-col style="text-align: center">
                        <el-button type="primary" @click="submitFrom('commommodule')">暂存</el-button>
                        <el-button type="primary" @click="toAccept('accepts','commommodule')">受理</el-button>
                        <el-button
                                type="primary"
                                v-if="orgList.length > 0"
                                @click="toAccept('nextOrg','commommodule')"
                        >分派下级
                        </el-button>
                        <el-button type="primary" @click="toAccept('notAccepts','commommodule')">不受理</el-button>
                        <el-button type="primary" @click="pigeonhole('commommodule')">直接归档</el-button>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <!-- 诉求登记 -->
            <el-tab-pane label="诉求登记" name="appeal" v-model="EconomicViolation">
                <el-tabs v-model="activeName">
                    <!-- 消费投诉登记 -->
                    <el-tab-pane label="消费投诉登记" name="complaintmodule">
                        <el-collapse v-model="registrationActiveNames" class="accordion">
                            <el-collapse-item name="1">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">系统信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="complaintmodule"
                                        label-width="150px"
                                        ref="complaintmoduleSystem"
                                        :rules="complaintmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="记录类型:" class="bt">
                                                <div style="line-height: 35px">{{complaintmodule.formtype}}</div>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                                <el-input
                                                        v-model="complaintmodule.rqsttitle"
                                                        placeholder="请输入标题"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <!--消费投诉 dateChange -->
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="登记日期：" class="bt" prop="recorddate">
                                                <el-date-picker
                                                        type="date"
                                                        v-model="complaintmodule.recorddate"
                                                        class="input-text"
                                                        style="width: 100%"
                                                        value-format="yyyy-MM-dd"
                                                        placeholder="选择日期"
                                                ></el-date-picker>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="信息来源：" class="bt" prop="appealsource">
                                                <el-select
                                                        placeholder="请选择类型"
                                                        class="input-text"
                                                        v-model="complaintmodule.appealsource"
                                                >
                                                    <el-option label="来函" value="来函"></el-option>
                                                    <el-option label="来人来访" value="来人来访"></el-option>
                                                    <el-option label="电话" value="电话"></el-option>
                                                    <el-option label="传真" value="传真"></el-option>
                                                    <el-option label="省、市移送" value="省、市移送"></el-option>
                                                    <el-option label="烟草移送" value="烟草移送"></el-option>
                                                    <el-option label="其他部门移送" value="其他部门移送"></el-option>
                                                    <el-option label="互联网络" value="互联网络"></el-option>
                                                    <el-option label="抽检" value="抽检"></el-option>
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="接收方式：" class="bt" prop="receivemode">
                                                <el-select
                                                        placeholder="请选择"
                                                        v-model="complaintmodule.receivemode"
                                                        class="input-text"
                                                >
                                                    <el-option label="举报" value="举报"></el-option>
                                                    <el-option label="投诉" value="投诉"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="24"
                                                :lg="8"
                                                :key="complaintmodule.businesstypecodeid"
                                        >
                                            <el-form-item label="业务分类:" prop="businesstypecodeid" class="bt">
                                                <el-cascader
                                                        filterable
                                                        :options="businesstype1"
                                                        v-model="complaintmodule.businesstypecodeid"
                                                        @change="handleChangebusinesstype3"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="工单分类:" prop="focus" class="bt">
                                                <el-select
                                                        v-model="complaintmodule.focus"
                                                        placeholder="请输入工单分类"
                                                        class="input-text"
                                                >
                                                    <el-option label="普通" value="普通"></el-option>
                                                    <el-option label="特殊" value="特殊"></el-option>
                                                    <el-option label="特派" value="特派"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="是否紧急:" prop="worktype" class="bt">
                                                <el-select
                                                        v-model="complaintmodule.worktype"
                                                        placeholder="请选择"
                                                        class="input-text"
                                                >
                                                    <el-option label="紧急" value="紧急"></el-option>
                                                    <el-option label="普通" value="普通"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="2">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">提供方信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="complaintmodule"
                                        label-width="150px"
                                        ref="complaintmoduleProvider"
                                        :rules="complaintmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="姓名：" class="bt" prop="rqstperson">
                                                <el-input
                                                        placeholder="请输入姓名"
                                                        v-model="complaintmodule.rqstperson"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="性别：" class="bt" prop="gender">
                                                <el-select
                                                        v-model="complaintmodule.gender"
                                                        placeholder="请选择性别"
                                                        class="input-text"
                                                >
                                                    <el-option label="男" value="男"></el-option>
                                                    <el-option label="女" value="女"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="联系电话：" class="bt" prop="linknumber">
                                                <el-input
                                                        v-model="complaintmodule.linknumber"
                                                        placeholder="请输入联系电话"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="年龄段：" class="bt" prop="age">
                                                <el-select
                                                        placeholder="请选择"
                                                        v-model="complaintmodule.age"
                                                        class="input-text"
                                                >
                                                    <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                                    <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                                    <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="证件类型：" class="bt" prop="certificate">
                                                <el-select
                                                        placeholder="请选择"
                                                        v-model="complaintmodule.certificate"
                                                        class="input-text"
                                                >
                                                    <el-option label="身份证" value="身份证"></el-option>
                                                    <el-option label="军官证" value="军官"></el-option>
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="证件号码：" class="bt" prop="idnumber">
                                                <el-input
                                                        v-model="complaintmodule.idnumber"
                                                        placeholder="请输入证件号码"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="联系地址：" class="bt" prop="linkaddress">
                                                <el-input
                                                        v-model="complaintmodule.linkaddress"
                                                        placeholder="请输入"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="电子邮箱：" class="bt" prop="emaiaddress">
                                                <el-input
                                                        v-model="complaintmodule.emaiaddress"
                                                        placeholder="请输入邮箱"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="3">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">涉及主体信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="complaintmodule"
                                        label-width="150px"
                                        ref="complaintmoduleSubject"
                                        :rules="complaintmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="涉及主体名称：" class="bt" prop="subjectname">
                                                <el-input
                                                        placeholder="请输入名称"
                                                        v-model="complaintmodule.subjectname"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="24"
                                                :lg="8"
                                                :key="complaintmodule.entitytypecodeid"
                                        >
                                            <el-form-item label="市场主体类型：" class="bt" prop="entitytypecodeid">
                                                <el-cascader
                                                        filterable
                                                        placeholder="请选择市场主体类型"
                                                        :options="entitytype1"
                                                        v-model="complaintmodule.entitytypecodeid"
                                                        @change="handleChangeentitytype1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="社会信用代码：" class="bt" prop="registerno">
                                                <el-input
                                                        placeholder="请输入名称"
                                                        v-model="complaintmodule.registerno"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="联系电话：" class="bt" prop="subjectphone">
                                                <el-input
                                                        placeholder="请输入联系电话"
                                                        v-model="complaintmodule.subjectphone"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                            <el-form-item label="地址：" class="bt" prop="subjectaddress">
                                                <el-select
                                                        v-model="complaintmodule.region"
                                                        placeholder="区域"
                                                        class="input-texts"
                                                >
                                                    <el-option label="乐从镇" value="乐从镇"></el-option>
                                                    <el-option label="陈村镇" value="陈村镇"></el-option>
                                                    <el-option label="勒流镇" value="勒流镇"></el-option>
                                                    <el-option label="大良镇" value="大良镇"></el-option>
                                                    <el-option label="杏坛镇" value="杏坛镇"></el-option>
                                                    <el-option label="均安镇" value="均安镇"></el-option>
                                                    <el-option label="容桂镇" value="容桂镇"></el-option>
                                                    <el-option label="龙江镇" value="龙江镇"></el-option>
                                                    <el-option label="伦教镇" value="伦教镇"></el-option>
                                                    <el-option label="北滘镇" value="北滘镇"></el-option>
                                                </el-select>
                                                <el-input
                                                        placeholder="请输入地址"
                                                        v-model="complaintmodule.subjectaddress"
                                                        class="input-text"
                                                        style="width:74%"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="4">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">业务具体信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="complaintmodule"
                                        label-width="150px"
                                        ref="complaintmoduleBusiness"
                                        :rules="complaintmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="销售方式：" class="bt" prop="shoppingmode">
                                                <el-select
                                                        v-model="complaintmodule.shoppingmode"
                                                        placeholder="请选择"
                                                        class="input-text"
                                                >
                                                    <el-option label="网购" value="网购"></el-option>
                                                    <el-option label="电视购物" value="电视购物"></el-option>
                                                    <el-option label="实体店" value="实体店"></el-option>
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="complaintmodule.servicetypecodeid"
                                        >
                                            <el-form-item label="商品/服务类型：" class="bt" prop="servicetypecodeid">
                                                <el-cascader
                                                        filterable
                                                        :options="servicetype1"
                                                        v-model="complaintmodule.servicetypecodeid"
                                                        @change="handleChangeservicetype1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="品牌：" class="bt" prop="brand">
                                                <el-autocomplete
                                                        class="input-text"
                                                        v-model="complaintmodule.brand"
                                                        :fetch-suggestions="querySearch"
                                                        placeholder="请输入"
                                                />
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                                <el-input
                                                        placeholder="请输入"
                                                        v-model="complaintmodule.objname"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="型号规格：" class="bt" prop="typespecification">
                                                <el-input
                                                        placeholder="请输入"
                                                        v-model="complaintmodule.typespecification"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="数量：" class="bt" prop="quantity">
                                                <el-input
                                                        placeholder="请输入数量"
                                                        v-model="complaintmodule.quantity"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="争议发生时间：" class="bt" prop="disputedate">
                                                <el-date-picker
                                                        type="date"
                                                        v-model="complaintmodule.disputedate"
                                                        class="input-text"
                                                        style="width: 100%"
                                                        value-format="yyyy-MM-dd"
                                                        @change="dateChanges"
                                                        placeholder="选择日期"
                                                ></el-date-picker>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="消费金额：" class="bt" prop="objmoney">
                                                <el-input
                                                        placeholder="请输入金额"
                                                        v-model.number="complaintmodule.objmoney"
                                                        class="input-text"
                                                >
                                                    <template slot="append">元</template>
                                                </el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="24"
                                                :lg="8"
                                                :key="complaintmodule.complaintstypecodeid"
                                        >
                                            <el-form-item label="投诉问题类型：" class="bt" prop="complaintstypecodeid">
                                                <el-cascader
                                                        filterable
                                                        :options="complaintstype1"
                                                        v-model="complaintmodule.complaintstypecodeid"
                                                        @change="handleChangecomplaintstype1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                                <el-input
                                                        placeholder="请输入"
                                                        v-model="complaintmodule.ordernumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="关键字：" class="bt" prop="keyword">
                                                <el-input
                                                        placeholder="请输入关键字"
                                                        v-model.number="complaintmodule.keyword"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="诉求内容：" class="bt" prop="report">
                                                <el-input
                                                        placeholder="请输入诉求内容"
                                                        v-model="complaintmodule.report"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="问题描述：" class="bt" prop="problemdescribe">
                                                <el-input
                                                        placeholder="请输入"
                                                        v-model="complaintmodule.problemdescribe"
                                                        type="textarea"
                                                        :autosize="{ minRows: 3, maxRows: 5}"
                                                        class="input-text"
                                                ></el-input>
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
                                    <el-button type="primary" @click="submitFrom('complaintmodule')">暂存</el-button>
                                    <el-button type="primary" @click="toAcceptT('accepts','complaintmodule')">受理
                                    </el-button>
                                    <el-button
                                            type="primary"
                                            v-if="orgList.length > 0"
                                            @click="toAcceptT('nextOrg','complaintmodule')"
                                    >分派下级
                                    </el-button>
                                    <el-button type="primary" @click="toAcceptT('notAccepts','complaintmodule')">不受理
                                    </el-button>
                                    <el-button type="primary" @click="pigeonhole('complaintmodule')">直接归档</el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-tab-pane>

                    <!--经济违法登记  -->
                    <el-tab-pane label="经济违法登记" name="comeconomicmodule">
                        <el-collapse v-model="processInofActiveNames" class="accordion">
                            <el-collapse-item name="1">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">系统信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right">
                                        </el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="comeconomicmodule"
                                        label-width="150px"
                                        ref="comeconomicmoduleSubject"
                                        :rules="comeconomicmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="记录类型:" class="bt">
                                                <div style="line-height: 35px">{{comeconomicmodule.formtype}}</div>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="标题:" prop="rqsttitle" class="bt">
                                                <el-input
                                                        v-model="comeconomicmodule.rqsttitle"
                                                        placeholder="请输入标题"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="登记日期：" class="bt" prop="recorddate">
                                                <el-date-picker
                                                        type="date"
                                                        v-model="comeconomicmodule.recorddate"
                                                        class="input-text"
                                                        style="width: 100%"
                                                        value-format="yyyy-MM-dd"
                                                        placeholder="选择日期"
                                                ></el-date-picker>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="信息来源：" class="bt" prop="appealsource">
                                                <el-select
                                                        placeholder="请选择类型"
                                                        class="input-text"
                                                        v-model="comeconomicmodule.appealsource"
                                                >
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
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="接收方式：" class="bt" prop="receivemode">
                                                <el-select
                                                        placeholder="请选择"
                                                        v-model="comeconomicmodule.receivemode"
                                                        class="input-text"
                                                >
                                                    <el-option label="举报" value="举报"></el-option>
                                                    <el-option label="投诉" value="投诉"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="24"
                                                :lg="8"
                                                :key="comeconomicmodule.businesstypecodeid"
                                        >
                                            <el-form-item label="业务分类:" prop="businesstypecodeid" class="bt">
                                                <el-cascader
                                                        filterable
                                                        :options="businesstype1"
                                                        v-model="comeconomicmodule.businesstypecodeid"
                                                        @change="handleChangebusinesstype2"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="工单分类:" prop="focus" class="bt">
                                                <el-select
                                                        v-model="comeconomicmodule.focus"
                                                        placeholder="请选择工单分类"
                                                        class="input-text"
                                                >
                                                    <el-option label="普通" value="普通"></el-option>
                                                    <el-option label="特殊" value="特殊"></el-option>
                                                    <el-option label="特派" value="特派"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="来电号码:" prop="telephonenumber" class="bt">
                                                <el-input
                                                        v-model="comeconomicmodule.telephonenumber"
                                                        placeholder="请输入来电号码"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="是否紧急:" prop="worktype" class="bt">
                                                <el-select
                                                        v-model="comeconomicmodule.worktype"
                                                        placeholder="请选择"
                                                        class="input-text"
                                                >
                                                    <el-option label="紧急" value="紧急"></el-option>
                                                    <el-option label="普通" value="普通"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="2">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">举报人信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="comeconomicmodule"
                                        label-width="150px"
                                        ref="comeconomicmoduleProvider"
                                        :rules="comeconomicmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="姓名/单位：" class="bt" prop="rqstperson">
                                                <el-input
                                                        placeholder="请输入姓名/单位"
                                                        v-model="comeconomicmodule.rqstperson"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="举报人联系电话：" class="bt" prop="linknumber">
                                                <el-input
                                                        placeholder="请输入举报人联系电话"
                                                        v-model="comeconomicmodule.linknumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="性别:" class="bt" prop="gender">
                                                <el-select
                                                        v-model="comeconomicmodule.gender"
                                                        placeholder="请选择性别"
                                                        class="input-text"
                                                >
                                                    <el-option label="男" value="男"></el-option>
                                                    <el-option label="女" value="女"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="年龄段：" class="bt" prop="age">
                                                <el-select
                                                        placeholder="请选择年龄段"
                                                        v-model="comeconomicmodule.age"
                                                        class="input-text"
                                                >
                                                    <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                                    <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                                    <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="举报人地址：" class="bt">
                                                <el-input
                                                        placeholder="请输入举报人地址"
                                                        v-model="comeconomicmodule.rqstaddress"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="提供方类型：" class="bt" prop="usertype">
                                                <!--<el-cascader filterable
                          placeholder="请选择提供方类型"
                          :options="usertype1"
                          v-model="comeconomicmodule.usertype"
                          @change="handleChange"
                          :show-all-levels="false"
                          class="bt1"
                        ></el-cascader>-->
                                                <el-select
                                                        v-model="comeconomicmodule.usertype"
                                                        placeholder="请选择反映类型"
                                                        class="input-text"
                                                >
                                                    <el-option label="个人" value="个人"></el-option>
                                                    <el-option label="企业" value="企业"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="证件类型：" class="bt" prop="certificate">
                                                <el-select
                                                        placeholder="请选择证件类型"
                                                        v-model="comeconomicmodule.certificate"
                                                        class="input-text"
                                                >
                                                    <el-option label="身份证" value="身份证"></el-option>
                                                    <el-option label="军官证" value="军官"></el-option>
                                                    <el-option label="其他" value="其他"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="证件号码：" class="bt" prop="idnumber">
                                                <el-input
                                                        placeholder="请输入证件号码"
                                                        v-model="comeconomicmodule.idnumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="电子邮箱：" class="bt" prop="informerpostcode">
                                                <el-input
                                                        placeholder="请输入电子邮箱"
                                                        v-model="comeconomicmodule.informerpostcode"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="国籍：" class="bt" prop="nationality">
                                                <el-input
                                                        placeholder="请输入国籍"
                                                        v-model="comeconomicmodule.nationality"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="民族：" class="bt" prop="nation">
                                                <el-input
                                                        placeholder="请输入民族"
                                                        v-model="comeconomicmodule.nation"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="邮政编码：" class="bt">
                                                <el-input
                                                        placeholder="请输入邮政编码"
                                                        v-model="comeconomicmodule.postcode"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="其他联系方式：" class="bt" prop="othercontacts">
                                                <el-input
                                                        placeholder="请输入其他联系方式"
                                                        v-model="comeconomicmodule.othercontacts"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="3">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">被举报对象</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="comeconomicmodule"
                                        label-width="150px"
                                        ref="comeconomicmoduleObject"
                                        :rules="comeconomicmoduleRules"
                                >
                                    <el-row>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="被举报对象名称：" class="bt" prop="subjectname">
                                                <el-input
                                                        placeholder="请输入被举报对象名称"
                                                        v-model="comeconomicmodule.subjectname"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                                            <el-form-item label="被举报对象地址：" class="bt" prop="subjectaddress">
                                                <el-select
                                                        v-model="comeconomicmodule.region"
                                                        placeholder="区域"
                                                        class="input-texts"
                                                >
                                                    <el-option label="乐从镇" value="乐从镇"></el-option>
                                                    <el-option label="陈村镇" value="陈村镇"></el-option>
                                                    <el-option label="勒流镇" value="勒流镇"></el-option>
                                                    <el-option label="大良镇" value="大良镇"></el-option>
                                                    <el-option label="杏坛镇" value="杏坛镇"></el-option>
                                                    <el-option label="均安镇" value="均安镇"></el-option>
                                                    <el-option label="容桂镇" value="容桂镇"></el-option>
                                                    <el-option label="龙江镇" value="龙江镇"></el-option>
                                                    <el-option label="伦教镇" value="伦教镇"></el-option>
                                                    <el-option label="北滘镇" value="北滘镇"></el-option>
                                                </el-select>
                                                <el-input
                                                        placeholder="请输入被举报对象地址"
                                                        v-model="comeconomicmodule.subjectaddress"
                                                        class="input-text"
                                                        style="width:74%"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="comeconomicmodule.providetypecodeid"
                                        >
                                            <el-form-item label="市场主体类型：" class="bt" prop="providetypecodeid">
                                                <el-cascader
                                                        filterable
                                                        :options="providetype1"
                                                        v-model="comeconomicmodule.providetypecodeid"
                                                        @change="handleChangeprovidetype1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="comeconomicmodule.industrynamecodeid"
                                        >
                                            <el-form-item label="行业分类：" class="bt" prop="industrynamecodeid">
                                                <el-cascader
                                                        filterable
                                                        placeholder="请选择行业分类"
                                                        :options="industryname1"
                                                        v-model="comeconomicmodule.industrynamecodeid"
                                                        @change="handleChangeindustryname1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="联系人：" class="bt" prop="reportedcontact">
                                                <el-input
                                                        placeholder="请输入联系人"
                                                        v-model="comeconomicmodule.reportedcontact"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="联系电话：" class="bt" prop="reportedcontactnumber">
                                                <el-input
                                                        placeholder="请输入联系电话"
                                                        v-model="comeconomicmodule.reportedcontactnumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                            <el-form-item label="社会信用代码：" class="bt" prop="registerno">
                                                <el-input
                                                        placeholder="请输入社会信用代码"
                                                        v-model="comeconomicmodule.registerno"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                    </el-row>
                                </el-form>
                            </el-collapse-item>
                            <el-collapse-item name="4">
                                <div slot="title" @click.stop>
                                    <el-row class="el-accordion">
                                        <el-col :span="12">业务信息</el-col>
                                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                                    </el-row>
                                </div>
                                <el-form
                                        :model="comeconomicmodule"
                                        label-width="150px"
                                        ref="comeconomicmoduleBusiness"
                                        :rules="comeconomicmoduleRules"
                                >
                                    <el-row>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="comeconomicmodule.objectcategorycodeid"
                                        >
                                            <el-form-item label="客体类别：" class="bt" prop="objectcategorycodeid">
                                                <el-cascader
                                                        filterable
                                                        placeholder="请选择客体类别"
                                                        :options="objectcategory1"
                                                        v-model="comeconomicmodule.objectcategorycodeid"
                                                        @change="handleChangeobjectcategory1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="comeconomicmodule.reportprovidelbcodeid"
                                        >
                                            <el-form-item label="举报问题类别：" class="bt" prop="reportprovidelbcodeid">
                                                <el-cascader
                                                        filterable
                                                        placeholder="请选择举报问题类别"
                                                        :options="reportprovidelb1"
                                                        v-model="comeconomicmodule.reportprovidelbcodeid"
                                                        @change="handleChangereportprovidelb1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col
                                                :xs="24"
                                                :sm="24"
                                                :md="12"
                                                :lg="8"
                                                :key="comeconomicmodule.illegalrulescodeid"
                                        >
                                            <el-form-item label="涉及违法行为：" class="bt" prop="illegalrulescodeid">
                                                <el-cascader
                                                        filterable
                                                        placeholder="请选择涉及违法行为"
                                                        :options="illegalrules1"
                                                        v-model="comeconomicmodule.illegalrulescodeid"
                                                        @change="handleChangeillegalrules1"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="关键字：" class="bt" prop="keyword">
                                                <el-input
                                                        placeholder="请输入关键字"
                                                        v-model.number="comeconomicmodule.keyword"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <!--后台实体类未添加相应字段-->
                                        <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">
                                            <el-form-item label="商品/服务类型：" class="bt" prop="servicetype">
                                                <el-cascader filterable
                                                        :options="servicetype1"
                                                        v-model="comeconomicmodule.servicetype"
                                                        @change="handleChange"
                                                        :show-all-levels="false"
                                                        class="bt1"
                                                ></el-cascader>
                                            </el-form-item>
                    </el-col>-->
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                                <el-input
                                                        placeholder="请输入商品/服务名称"
                                                        v-model="comeconomicmodule.objname"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="品牌：" class="bt" prop="brand">
                                                <el-autocomplete
                                                        class="input-text"
                                                        v-model="comeconomicmodule.brand"
                                                        :fetch-suggestions="querySearch"
                                                        placeholder="请输入"
                                                />
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="销售方式：" class="bt" prop="sellingformat">
                                                <el-select
                                                        placeholder="请选择销售方式"
                                                        v-model="comeconomicmodule.sellingformat"
                                                        class="input-text"
                                                >
                                                    <el-option label="网购" value="网购"></el-option>
                                                    <el-option label="实体店" value="实体店"></el-option>
                                                </el-select>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                                <el-input
                                                        placeholder="请输入订单号"
                                                        v-model="comeconomicmodule.ordernumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>

                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="产品名称：" class="bt" prop="productname">
                                                <el-input
                                                        placeholder="请输入产品名称"
                                                        v-model="comeconomicmodule.productname"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="批准文号：" class="bt" prop="approvalnumber">
                                                <el-input
                                                        placeholder="请输入批准文号"
                                                        v-model="comeconomicmodule.approvalnumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="产品批号：" class="bt" prop="lotnumber">
                                                <el-input
                                                        placeholder="请输入产品批号"
                                                        v-model="comeconomicmodule.lotnumber"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="产品规格：" class="bt" prop="typespecification">
                                                <el-input
                                                        placeholder="请输入产品规格"
                                                        v-model="comeconomicmodule.typespecification"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="生产厂家：" class="bt" prop="producecompany">
                                                <el-input
                                                        placeholder="请输入生产厂家"
                                                        v-model="comeconomicmodule.producecompany"
                                                        class="input-text"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                            <el-form-item label="销售企业：" class="bt">
                                                <el-input
                                                        placeholder="请输入销售企业"
                                                        v-model="comeconomicmodule.salesenterprise "
                                                        class="input-text"
                                                ></el-input>
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
                                                        placeholder="选择日期"
                                                ></el-date-picker>
                                                <!--@change="dateChangesF"-->
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="问题描述:" prop="problemdescribe" class="bt">
                                                <el-input
                                                        type="textarea"
                                                        :autosize="{ minRows: 6, maxRows: 8}"
                                                        placeholder="请输入问题描述(3000字以内)"
                                                        v-model="comeconomicmodule.problemdescribe"
                                                ></el-input>
                                            </el-form-item>
                                        </el-col>
                                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="经济表单相关附件:" class="bt" prop="attachfilename">
                                                <el-upload
                                                        :auto-upload="false"
                                                        :file-list="fileLists"
                                                        ref="upload"
                                                        :data="this.requestMap"
                                                        :on-preview="previewFile"
                                                        :on-progress="progress"
                                                        :on-success="uploadSuccess"
                                                        :before-upload="beforeUpload"
                                                        :multiple="true"
                                                        action="/crcs/filesUpload/upload"
                                                >
                                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                                    <el-button slot="trigger" size="small" type="primary">选取文件
                                                    </el-button>
                                                    <el-button
                                                            size="small"
                                                            type="success"
                                                            @click="submitUpload('upload')"
                                                    >确认上传
                                                    </el-button>
                                                </el-upload>
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
                                    <el-button type="primary" @click="submitFrom('comeconomicmodule')">暂存</el-button>
                                    <el-button type="primary" @click="toAcceptE('acceptE','comeconomicmodule')">受理
                                    </el-button>
                                    <el-button
                                            type="primary"
                                            v-if="orgList.length > 0"
                                            @click="toAcceptE('nextOrg','comeconomicmodule')"
                                    >分派下级
                                    </el-button>
                                    <el-button type="primary" @click="toAcceptE('noAccept','comeconomicmodule')">不受理
                                    </el-button>
                                    <el-button type="primary" @click="pigeonhole('comeconomicmodule')">直接归档</el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>

            <!-- 受理处理内容 -->
            <el-tab-pane label="受理" v-if="accept">
                <el-form
                        :model="nextStepForm"
                        ref="nextStepForm"
                        style="width: 96%;margin: 0 auto"
                        :rules="nextStepFormRules"
                >
                    <el-row style="margin: 20px auto;width: 96%;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="5">
                            <el-form-item label="是否受理：" prop="department" class="bt">
                                <div style="line-height: 35px">受理</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8" v-if="this.formType != 'commommodule'">
                            <el-form-item label-width="90px" label="受理方式：" prop="process" class="stab">
                                <el-select v-model="nextStepForm.process" placeholder="请选择" style="width: 200px;">
                                    <el-option
                                            v-for="item in acceptWay"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="6">
                            <el-button type="primary" @click="accepts('受理')">确定</el-button>
                            <el-button @click="flows()">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 经济违法跳到这里立案 -->
            <el-tab-pane
                    :label="acceptForm.isAccep==='acceptE'?'受理':'不受理'"
                    v-if="acceptForm.isAccep !==''"
            >
                <!-- 经济违法是否受理 -->
                <el-form
                        :model="acceptForm"
                        ref="acceptForm"
                        style="width: 96%;margin: 0 auto"
                        label-width="105px"
                        :rules="acceptFormRules"
                >
                    <el-row v-if="acceptForm.isAccep==='acceptE'" style="margin: 20px">
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="是否受理：" prop="isAccep" class="bt">
                                <div style="line-height: 35px">{{acceptForm.isAccep==='acceptE'?'受理':'不受理'}}</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="受理方式：" prop="mode" class="bt">
                                <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px;">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="12" :sm="12" :md="12" :lg="8">
                            <el-button type="primary" @click="acceptsE('受理')">确定</el-button>
                            <el-button @click="flows()">取消</el-button>
                        </el-col>
                    </el-row>

                    <el-row v-else style="margin: 20px">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="isAccep" class="bt">
                                <div style="line-height: 35px">{{acceptForm.isAccep==='acceptE'?'受理':'不受理'}}</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="reason" class="bt">
                                <el-select
                                        v-model="acceptForm.reason"
                                        placeholder="请选择"
                                        prop="department"
                                        style="width: 180px;"
                                >
                                    <el-option
                                            v-for="item in reasonBack"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="不受理内容：" class="bt" prop="content" style="width: 80%">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入内容"
                                        v-model="acceptForm.content"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="附件上传2：" class="form_bt" style="width: 80%">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileListsOne"
                                        ref="uploadOne"
                                        :data="this.requestMap"
                                        :on-error="uploadFile"
                                        :on-progress="progress"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                >
                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <el-button size="small" type="success" @click="submitUpload('uploadOne')">确认上传
                                    </el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                        <el-col style="text-align: center">
                            <el-button type="primary" @click="accepts('不受理')">确定</el-button>
                            <el-button @click="flows()">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="分派下级" v-if="nextOrg">
                <el-form
                        :model="assignmentForm"
                        label-width="180px"
                        ref="assignmentForm"
                        label-suffix="："
                        :rules="assignmentFormRules"
                >
                    <el-form-item label="成员单位" class="bt" prop="unit" style="margin-top: 15px;">
                        <el-select
                                v-model="assignmentForm.unit"
                                placeholder="请选择成员单位"
                                clearable
                                style="width: 300px;"
                        >
                            <el-option
                                    v-for="item in orgList"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.primaryKey"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="抄送部门" class="bt" prop="depts">
                        <el-select
                                v-model="assignmentForm.depts"
                                multiple
                                placeholder="请选择抄送部门"
                                style="width: 500px;"
                                @change="orgPermission()"
                        >
                            <el-option
                                    v-for="item in deptList"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.orgunitID"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分派意见" class="bt" prop="opinion" style="width: 80%">
                        <el-input
                                type="textarea"
                                :rows="4"
                                placeholder="请输入分派意见（3000字内）"
                                v-model="assignmentForm.opinion"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="accepts('分派下级')">确认</el-button>
                        <el-button @click="flows()">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="不受理" v-if="notAccepts">
                <el-form
                        :model="nextStepForm"
                        ref="nextStepForm"
                        style="width: 96%;margin: 0 auto"
                        :rules="nextStepFormRules"
                >
                    <el-row style="margin: 20px 0;">
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="是否受理：" prop="opinion" label-width="105px" class="bt">
                                <div style="line-height: 35px">{{nextStepForm.noAccept}}</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                            <el-form-item label="不受理原因：" prop="department" label-width="130px" class="bt">
                                <el-select
                                        v-model="nextStepForm.cause"
                                        placeholder="请选择"
                                        prop="department"
                                        style="width: 180px;"
                                >
                                    <el-option
                                            v-for="item in reasonBack"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item
                                    label="不受理内容:"
                                    class="bt"
                                    prop="centent"
                                    label-width="105px"
                                    style="width: 80%"
                            >
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入内容"
                                        v-model="nextStepForm.centent"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-form-item label="相关附件:" class="bt">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileListsTwo"
                                        ref="uploadTwo"
                                        :data="this.requestMap"
                                        :on-error="uploadFile"
                                        :on-progress="progress"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                >
                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <el-button size="small" type="success" @click="submitUpload('uploadTwo')">确认上传
                                    </el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0;">
                        <el-col style="text-align: center;width: 90%;">
                            <el-button type="primary" @click="accepts('不受理')">确定</el-button>
                            <el-button @click="flows()">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>

        <!-- </el-tabs> -->
        <!-- 上传文件的弹窗 -->
        <!-- <el-dialog :title="fileUploadText" :visible.sync="fileUploadVisible" size="tiny"> -->
        <!-- <el-upload
                    :auto-upload="false"
                    :file-list="fileLists"
                    ref="upload"
                    :data="this.requestMap"
                    :on-error="uploadFile"
                    :on-progress="progress"
                    :on-success="uploadSuccess"
                    :before-upload="beforeUpload"
                    :multiple="true"
                    action="/crcs/filesUpload/upload"
            >
                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
    </el-upload>-->
        <!-- </el-dialog> -->
    </section>
</template>

<script>
    import {
        saveBaseInfo,
        next,
        uploadFile,
        getMaterialLists,
        searchBaseInfo,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        selectBaseCodeByType,
        selectBaseCodeTree,
        findUsersByDeptIdAndPermission
    } from "../../../api/api";

    import moment from "moment";
    import {getArrayByStringId, byIdGetLabels} from "../../../utils/form-util";

    export default {
        data() {
            return {
                businesstypecodeid1: [], //业务分类--咨询
                businesstypecodeid2: [], //业务分类--消费
                businesstypecodeid3: [], //业务分类--经济

                entitytypecodeid1: [], //市场主体类型--消费
                servicetypecodeid1: [], //商品/服务类型--消费
                complaintstypecodeid1: [], //投诉问题类型--消费

                providetypecodeid1: [], //市场主体类型--经济
                industrynamecodeid1: [], //行业分类--经济
                objectcategorycodeid1: [], //客体类别--经济
                reportprovidelbcodeid1: [], //举报问题类别--经济
                illegalrulescodeid1: [], //涉及违法行为--经济

                randomId: "",
                illegalrules1: [], //违法行为下拉框
                businesstype1: [], //业务分类下拉框
                entitytype1: [], //市场主体类型下拉框
                servicetype1: [], //商品/服务类型下拉框
                complaintstype1: [], //投诉问题类型下拉框
                usertype1: [], //提供方类型下拉框
                industryname1: [], //行业分类下拉框
                objectcategory1: [], //客体类别下拉框
                reportprovidelb1: [], //举报问题类别下拉框
                providetype1: [], //市场主体类型下拉框(经济违法)
                requestMap: {
                    caseguid: ""
                },

                // 消费投诉部分
                type: "",
                activeName: "complaintmodule",
                assignmentFormRules: {
                    unit: [{required: true, message: "请选择成员单位"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    depts: [{required: true, message: "请选择抄送部门"}]
                },
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

                acceptWay: [
                    {
                        value: "调解",
                        label: "调解"
                    },
                    {
                        value: "查处",
                        label: "查处"
                    },
                    {
                        value: "调查",
                        label: "调查"
                    }
                ],
                // 咨询登记的进度条
                fileUploadVisible: false,
                accept: false,
                acceptE: false,
                notAccepts: false,
                nextOrg: false,
                // 直接归档
                DirectFiling: false,
                fileLists: [],
                fileListscommommodule: [],
                fileListsTwo: [],
                fileListsOne: [],
                deptList: [],
                depts: "",
                fileUploadText: "上传文件",
                sourceForm: {},
                complaintmodule: {
                    id: "",
                    caseguid: "",
                    formtype: "消费投诉",
                    rqsttitle: "",
                    rqstperson: "",
                    gender: "",
                    rqstnumber: "",
                    linknumber: "",
                    usertype: "",
                    answertype: "",
                    providetype: "",
                    providelb: "",
                    focus: "普通",
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
                    recorddate: moment().toDate(),
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
                    businesstypecodeid: [],
                    entitytypecodeid: [],
                    servicetypecodeid: [],
                    complaintstypecodeid: [],
                    ordernumber: "",
                    worktype: ""
                },
                // 诉求登记
                EconomicViolation: "complaintmodule",
                commommoduleOfficeFormRules: {
                    unit: [
                        {required: true, message: "请选择归档单位", trigger: "change"}
                    ],
                    way: [{required: true, message: "请选择处办方式", trigger: "change"}],
                    idea: [
                        {required: true, message: "请输入处理意见", trigger: "blur"},
                        {max: 3000, message: "不能超过3000字", trigger: "blur"}
                    ]
                },
                // 消费投诉部分结束
                // 咨询登记部分开始
                aictabs: "commommodule",
                type: "",
                textarea: "",
                nextStepForm: {
                    noAccept: "不受理",
                    centent: "",
                    department: "受理",
                    process: "",
                    cause: "",
                    options: []
                },
                nextStepFormRules: {
                    department: [{required: true, message: "请选择", trigger: "change"}],
                    process: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    centent: [{required: true, message: "请输入", trigger: "blur"}]
                },
                dept: [
                    {
                        value: "企管科",
                        label: "企管科"
                    },
                    {
                        value: "消保科",
                        label: "消保科"
                    },
                    {
                        value: "广告科",
                        label: "广告科"
                    }
                ],
                orgList: [],
                fileUploadVisible: false,
                accept: false,
                acceptE: false,
                notAccepts: false,
                nextOrg: false,
                comeconomicmodule: {
                    id: "",
                    caseguid: "",
                    formtype: "经济违法行为",
                    rqsttitle: "",
                    rqstperson: "",
                    gender: "",
                    businesstype: "",
                    rqstaddress: "",
                    rqstnumber: "",
                    linknumber: "",
                    usertype: "",
                    rqsttype: "",
                    focus: "普通",
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
                    recorddate: moment().toDate(),
                    // recorddate: "",
                    worktype: "",
                    sdexpirationdate: "",
                    expirationdate: "",
                    providetypecodeid: [],
                    businesstypecodeid: [],
                    objectcategorycodeid: [],
                    reportprovidelbcodeid: [],
                    illegalrulescodeid: [],
                    industrynamecodeid: [],
                    region: ""

                    // servicetype:"",//后台实体类未添加相应字段
                },
                // 经济违法
                acceptForm: {
                    isAccep: "",
                    opinion: "",
                    department: "",
                    content: "",
                    reason: ""
                },
                acceptFormRules: {
                    isAccep: [{required: true}],
                    reason: [
                        {required: true, message: "请选择不受理原因", trigger: "change"}
                    ],
                    mode: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    content: [{required: true, message: "请输入", trigger: "blur"}]
                },
                comeconomicmoduleRules: {
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    worktype: [
                        {required: true, message: "请选择是否紧急", trigger: "change"}
                    ],
                    rqstperson: [
                        {required: true, message: "请输入举报人姓名", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    // gender: [{required: true, message: "请选择性别", trigger: "change"}],
                    businesstypecodeid: [{required: true, message: "请选择业务分类"}],
                    rqstnumber: [
                        {required: true, message: "请输入来电号码", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    usertype: [{required: true, message: "请选择提供方类型"}],
                    rqsttype: [
                        {required: true, message: "请选择类型", trigger: "change"}
                    ],
                    focus: [
                        {required: true, message: "请选择工单分类", trigger: "change"}
                    ],
                    providelbcode: [
                        {required: true, message: "请选择类别", trigger: "change"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "change"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    subjectphone: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    aicstationid: [
                        {required: true, message: "请输入管辖单位", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, message: "请输入经营地址", trigger: "blur"},
                        {max: 500, message: "不能超出500个字符", trigger: "blur"}
                    ],
                    subjectzcadd: [
                        {max: 500, message: "不能超出500个字符", trigger: "blur"}
                    ],
                    /* objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],*/
                    brand: [
                        {required: true, message: "请输入商品品牌", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    objclassify: [
                        {required: true, message: "请选择涉及客体分类", trigger: "change"}
                    ],
                    producecompany: [
                        {required: true, message: "请输入生产企业", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "change"}
                    ],
                    typespecification: [
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    quantity: [{max: 50, message: "不能超出50个字符", trigger: "blur"}],
                    measureunit: [
                        {max: 10, message: "不能超出10个字符", trigger: "blur"}
                    ],
                    price: [{type: "number", message: "请输入涉及金额", trigger: "blur"}],
                    problemtype: [
                        {required: true, message: "请选择涉及问题分类", trigger: "change"}
                    ],
                    illegalrules: [{required: true, message: "请选择涉嫌违法行为"}],
                    problemdescribe: [
                        {required: true, message: "请输入问题陈述", trigger: "blur"},
                        {max: 3000, message: "不能超出3000个字符", trigger: "blur"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    attachfilename: [
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, message: "请输入地址", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ]
                },

                complaintmoduleRules: {
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    worktype: [
                        {required: true, message: "请选择是否紧急", trigger: "change"}
                    ],
                    rqstperson: [
                        {required: true, message: "请输入举报人姓名", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    // gender: [{required: true, message: "请选择性别", trigger: "change"}],
                    businesstypecodeid: [{required: true, message: "请选择业务分类"}],
                    rqstnumber: [
                        {required: true, message: "请输入来电号码", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    usertype: [
                        {required: true, message: "请选择反映类型", trigger: "change"}
                    ],
                    providetype: [{required: true, message: "请选择市场主体类型"}],
                    focus: [
                        {required: true, message: "请选择工单分类", trigger: "change"}
                    ],
                    providelb: [
                        {required: true, message: "请选择类别", trigger: "change"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "change"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    subjectphone: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    aicstationid: [
                        {required: true, message: "请输入管辖单位", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    postcode: [
                        {required: true, message: "请输入邮编", trigger: "blur"},
                        {max: 20, message: "不能超出20个字符", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, message: "请输入地址", trigger: "blur"},
                        {max: 500, message: "不能超出500个字符", trigger: "blur"}
                    ],
                    objclassify: [
                        {required: true, message: "请选择涉及客体分类", trigger: "change"}
                    ],
                    /*objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],*/
                    servicetypecodeid: [{required: true, message: "请选择商品/服务类型"}],
                    brand: [
                        {required: true, message: "请输入商品品牌", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    objmoney: [
                        {
                            required: true,
                            type: "number",
                            message: "请输入涉及金额",
                            trigger: "blur"
                        }
                    ],
                    producecompany: [
                        {required: true, message: "请输入生产企业", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "change"}
                    ],
                    problemtype: [
                        {required: true, message: "请选择涉及问题分类", trigger: "change"}
                    ],
                    problemdescribe: [
                        {required: true, message: "请输入问题陈述", trigger: "blur"},
                        {max: 3000, message: "不能超出3000个字符", trigger: "blur"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    briefing: [{max: 50, message: "不能超出50个字符", trigger: "blur"}],
                    injuries: [{max: 50, message: "不能超出50个字符", trigger: "blur"}],
                    even: [{max: 50, message: "不能超出50个字符", trigger: "blur"}],
                    reduction: [{max: 50, message: "不能超出50个字符", trigger: "blur"}],
                    /* certificate: [
                         {required: true, message: "请选择证件类型", trigger: "change"}
                     ],*/
                    idnumber: [
                        {
                            validator: (rule, value, callback) => {
                                if ("身份证" === this.complaintmodule.certificate) {
                                    if (value.length === 15 || value.length === 18) {
                                        callback();
                                    } else {
                                        callback(new Error("身份证号码长度应该为15位或18位"));
                                    }
                                }
                                callback();
                            },
                            trigger: "blur"
                        }
                    ]
                },
                commommodule: {
                    id: "",
                    caseguid: "",
                    formtype: "咨询",
                    businesstype: "",
                    name: "",
                    gender: "",
                    rqstnumber: "",
                    linknumber: "",
                    informationOpen: "",
                    address: "",
                    focus: "普通",
                    appealsource: "",
                    rqsttitle: "",
                    rqstcontent: "",
                    attachfilename: "",
                    reflectiontype: "",
                    worktype: "",
                    sdexpirationdate: "",
                    usertype: "",
                    category: "",
                    businesstypecodeid: [],
                    region: ""
                },
                commommoduleRules: {
                    businesstypecodeid: [{required: true, message: "请选择业务分类"}],
                    worktype: [
                        {required: true, message: "请选择是否紧急", trigger: "change"}
                    ],
                    name: [
                        {required: true, message: "请输入姓名", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    // gender: [{required: true, message: "请选择性别", trigger: "change"}],
                    rqstnumber: [
                        {required: true, message: "请输入来电号码", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入联系电话", trigger: "blur"},
                        {max: 30, message: "不能超出30个字符", trigger: "blur"}
                    ],
                    reflectiontype: [{required: true, message: "请选择反映类型"}],
                    category: [
                        {required: true, message: "请选择分类", trigger: "change"}
                    ],
                    focus: [
                        {required: true, message: "请选择工单分类", trigger: "change"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "change"}
                    ],
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 200, message: "不能超出200个字符", trigger: "blur"}
                    ],
                    rqstcontent: [
                        {required: true, message: "请输入问题描述", trigger: "blur"},
                        {max: 3000, message: "不能超过3000个字符", trigger: "blur"}
                    ],
                    address: [
                        {required: true, message: "请输入地址", trigger: "blur"},
                        {max: 200, message: "不能超过200个字符", trigger: "blur"}
                    ]
                },
                commommoduleOfficeForm: {
                    unit: "",
                    way: "",
                    idea: ""
                },
                assignmentForm: {
                    unit: "",
                    opinion: "",
                    depts: ""
                },
                formType: "commommodule",
                nodes: "",
                registrationActiveNames: ["1", "2", "3", "4", "5"],
                processInofActiveNames: ["1", "2", "3", "4", "5", "6"],
                brandsSearch: [
                    {value: "美的(Midea)"},
                    {value: "万家乐"},
                    {value: "万和"},
                    {value: "格兰仕"},
                    {value: "康宝"},
                    {value: "云米"},
                    {value: "中国移动"},
                    {value: "中国联通"},
                    {value: "中国电信"},
                    {value: "圣品轩"},
                    {value: "国栓家私"},
                    {value: "滴滴出行"},
                    {value: "港华"},
                    {value: "喜威"},
                    {value: "广电"},
                    {value: "大自然"},
                    {value: "乐创"},
                    {value: "乐从乐华"},
                    {value: "碧桂园"},
                    {value: "德尔玛"},
                    {value: "艾诗凯奇"},
                    {value: "欧派橱柜"},
                    {value: "互通宽带"},
                    {value: "美博空调"},
                    {value: "海信（容声）"},
                    {value: "森太"},
                    {value: "海迅"},
                    {value: "优盟"},
                    {value: "德玛仕"},
                    {value: "德邦"},
                    {value: "黄疸记"},
                    {value: " 天虹"},
                ]
            };
        },
        methods: {
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            // 点击下载材料
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url, "_blank")
            },
            getMaterial(caseguid) {
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
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            if (this.commommodule.rqsttitle !== "") {
                                this.fileListscommommodule.push(fileInfo);
                            } else if (this.complaintmodule.rqsttitle !== "") {
                                //  this.fileLists.push(fileInfo)
                                return;
                            } else if (this.comeconomicmodule.rqsttitle !== "") {
                                this.fileLists.push(fileInfo);
                            }

                            // this.fileListsCom.push(fileInfo);
                        }
                    }
                });
            },
            //下拉框获取数据
            selectBase() {
                let para = {
                    type: "F"
                    // orglevel:"1"
                };
                selectBaseCodeList(para).then(res => {
                });
            },

            toUploadOpen(type) {
                this.fileUploadVisible = true;
            },

            // dateChange(date) {
            //   this.complaintmodule.recorddate = date;
            // },
            dateChanges(date) {
                this.complaintmodule.disputedate = date;
            },
            // dateChangeE(date) {
            //   this.comeconomicmodule.recorddate = date;
            // },
            dateChangesF(date) {
                this.comeconomicmodule.expirationdate = date;
            },
            flows(visible) {
                if ("commommodule" == this.formType) {
                    this.aictabs = "commommodule";
                } else {
                    this.aictabs = "appeal";
                    if ("complaintmodule" == this.formType) {
                        this.EconomicViolation = "complaintmodule";
                    } else {
                        this.EconomicViolation = "comeconomicmodule";
                    }
                }
                // this.aictabs = "commommodule";
                this.accept = false;
                this.acceptE = false;
                this.notAccepts = false;
                this.nextOrg = false;
                this.acceptForm.isAccep = "";
                this.complaintmodule.report = "退货退款、赔偿损失";
            },

            pigeonhole(value) {
                let validation;
                let data;
                if ("commommodule" == value) {
                    this.$refs.commommodule.validate(valid => (validation = valid));
                    if (
                        this.commommodule.businesstypecodeid != null &&
                        this.commommodule.businesstypecodeid != ""
                    ) {
                        this.businesstypecodeid1 = this.commommodule.businesstypecodeid;
                        this.commommodule.businesstypecodeid = this.commommodule.businesstypecodeid[
                        this.commommodule.businesstypecodeid.length - 1
                            ].toString();
                    }
                    data = this.commommodule;
                } else if ("complaintmodule" == value) {
                    this.$refs["complaintmoduleSystem"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["complaintmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleSubject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                    if (this.complaintmodule.id == null || this.complaintmodule.id == "") {
                        this.complaintmodule.recorddate = this.complaintmodule.recorddate.getTime();
                    }
                    if (
                        this.complaintmodule.businesstypecodeid != null &&
                        this.complaintmodule.businesstypecodeid != ""
                    ) {
                        this.businesstypecodeid2 = this.complaintmodule.businesstypecodeid;
                        this.complaintmodule.businesstypecodeid = this.complaintmodule.businesstypecodeid[
                        this.complaintmodule.businesstypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.complaintmodule.entitytypecodeid != null &&
                        this.complaintmodule.entitytypecodeid != ""
                    ) {
                        this.entitytypecodeid1 = this.complaintmodule.entitytypecodeid;
                        this.complaintmodule.entitytypecodeid = this.complaintmodule.entitytypecodeid[
                        this.complaintmodule.entitytypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.complaintmodule.servicetypecodeid != null &&
                        this.complaintmodule.servicetypecodeid != ""
                    ) {
                        this.servicetypecodeid1 = this.complaintmodule.servicetypecodeid;
                        this.complaintmodule.servicetypecodeid = this.complaintmodule.servicetypecodeid[
                        this.complaintmodule.servicetypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.complaintmodule.complaintstypecodeid != null &&
                        this.complaintmodule.complaintstypecodeid != ""
                    ) {
                        this.complaintstypecodeid1 = this.complaintmodule.complaintstypecodeid;
                        this.complaintmodule.complaintstypecodeid = this.complaintmodule.complaintstypecodeid[
                        this.complaintmodule.complaintstypecodeid.length - 1
                            ].toString();
                    }
                    data = this.complaintmodule;
                } else {
                    this.$refs["comeconomicmoduleSubject"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["comeconomicmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["comeconomicmoduleObject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["comeconomicmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                    if (
                        this.comeconomicmodule.id == null ||
                        this.comeconomicmodule.id == ""
                    ) {
                        this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime();
                    }
                    if (
                        this.comeconomicmodule.businesstypecodeid != null &&
                        this.comeconomicmodule.businesstypecodeid != ""
                    ) {
                        this.businesstypecodeid3 = this.comeconomicmodule.businesstypecodeid;
                        this.comeconomicmodule.businesstypecodeid = this.comeconomicmodule.businesstypecodeid[
                        this.comeconomicmodule.businesstypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.comeconomicmodule.providetypecodeid != null &&
                        this.comeconomicmodule.providetypecodeid != ""
                    ) {
                        this.providetypecodeid1 = this.comeconomicmodule.providetypecodeid;
                        this.comeconomicmodule.providetypecodeid = this.comeconomicmodule.providetypecodeid[
                        this.comeconomicmodule.providetypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.comeconomicmodule.industrynamecodeid != null &&
                        this.comeconomicmodule.industrynamecodeid != ""
                    ) {
                        this.industrynamecodeid1 = this.comeconomicmodule.industrynamecodeid;
                        this.comeconomicmodule.industrynamecodeid = this.comeconomicmodule.industrynamecodeid[
                        this.comeconomicmodule.industrynamecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.comeconomicmodule.objectcategorycodeid != null &&
                        this.comeconomicmodule.objectcategorycodeid != ""
                    ) {
                        this.objectcategorycodeid1 = this.comeconomicmodule.objectcategorycodeid;
                        this.comeconomicmodule.objectcategorycodeid = this.comeconomicmodule.objectcategorycodeid[
                        this.comeconomicmodule.objectcategorycodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.comeconomicmodule.reportprovidelbcodeid != null &&
                        this.comeconomicmodule.reportprovidelbcodeid != ""
                    ) {
                        this.reportprovidelbcodeid1 = this.comeconomicmodule.reportprovidelbcodeid;
                        this.comeconomicmodule.reportprovidelbcodeid = this.comeconomicmodule.reportprovidelbcodeid[
                        this.comeconomicmodule.reportprovidelbcodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.comeconomicmodule.illegalrulescodeid != null &&
                        this.comeconomicmodule.illegalrulescodeid != ""
                    ) {
                        this.illegalrulescodeid1 = this.comeconomicmodule.illegalrulescodeid;
                        this.comeconomicmodule.illegalrulescodeid = this.comeconomicmodule.illegalrulescodeid[
                        this.comeconomicmodule.illegalrulescodeid.length - 1
                            ].toString();
                    }
                    data = this.comeconomicmodule;
                }
                if (!validation) {
                    document.getElementById("fromExamine").scrollTop = 0;
                    return;
                }
                let user = localStorage.getItem("user");
                let para = {
                    user: JSON.parse(user),
                    node: "begin",
                    // nextStepForm:this.nextStepForm,
                    formType: this.formType,
                    data,
                    nextNodeId: "end"
                };
                next(para).then(res => {
                    if (res.code === 0) {
                        this.$alert("归档成功!", "提示", {
                            confirmButtonText: "确定",
                            type: "success"
                        });
                        this.$router.push("/wddb/bxtdb");
                    } else {
                        this.$alert("归档失败!", "提示", {
                            confirmButtonText: "确定",
                            type: "defeated"
                        });
                    }
                });
            },

            accepts(value) {
                let data;
                let worktype;
                if (this.nodes == "") {
                    this.nodes = "begin";
                } else if (this.$route.query.node != undefined) {
                    this.nodes = this.$route.query.node;
                }
                let validation = false;

                /*if (this.formType == "commommodule") {
                    if (this.commommodule.id == null || this.commommodule.id == "") {
                        console.log(this.commommodule.businesstypecodeid)
                        if (
                            this.commommodule.businesstypecodeid != null &&
                            this.commommodule.businesstypecodeid != ""
                        ) {
                            this.commommodule.businesstypecodeid = this.commommodule.businesstypecodeid[
                            this.commommodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                    }
                    if(this.commommodule.businesstypecodeid instanceof Array){
                        this.commommodule.businesstypecodeid = this.commommodule.businesstypecodeid[
                        this.commommodule.businesstypecodeid.length - 1
                            ].toString()
                    }
                    console.log(this.commommodule)
                    data = this.commommodule;
                    worktype = this.commommodule.worktype;
                } else if ("complaintmodule" == this.formType) {
                    if (this.complaintmodule.id == null || this.complaintmodule.id == "") {
                        this.complaintmodule.recorddate = this.complaintmodule.recorddate.getTime()
                        if (
                            this.complaintmodule.businesstypecodeid != null &&
                            this.complaintmodule.businesstypecodeid != ""
                        ) {
                            this.complaintmodule.businesstypecodeid = this.complaintmodule.businesstypecodeid[
                            this.complaintmodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.entitytypecodeid != null &&
                            this.complaintmodule.entitytypecodeid != ""
                        ) {
                            this.complaintmodule.entitytypecodeid = this.complaintmodule.entitytypecodeid[
                            this.complaintmodule.entitytypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.servicetypecodeid != null &&
                            this.complaintmodule.servicetypecodeid != ""
                        ) {
                            this.complaintmodule.servicetypecodeid = this.complaintmodule.servicetypecodeid[
                            this.complaintmodule.servicetypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.complaintstypecodeid != null &&
                            this.complaintmodule.complaintstypecodeid != ""
                        ) {
                            this.complaintmodule.complaintstypecodeid = this.complaintmodule.complaintstypecodeid[
                            this.complaintmodule.complaintstypecodeid.length - 1
                                ].toString();
                        }

                    }
                    if(this.complaintmodule.businesstypecodeid instanceof Array){
                        this.complaintmodule.businesstypecodeid = this.complaintmodule.businesstypecodeid[
                        this.complaintmodule.businesstypecodeid.length - 1
                            ].toString()
                    }
                    if(this.complaintmodule.entitytypecodeid instanceof Array){
                        this.complaintmodule.entitytypecodeid = this.complaintmodule.entitytypecodeid[
                        this.complaintmodule.entitytypecodeid.length - 1
                            ].toString()
                    }
                    if(this.complaintmodule.servicetypecodeid instanceof Array){
                        this.complaintmodule.servicetypecodeid = this.complaintmodule.servicetypecodeid[
                        this.complaintmodule.servicetypecodeid.length - 1
                            ].toString()
                    }
                    if(this.complaintmodule.complaintstypecodeid instanceof Array){
                        this.complaintmodule.complaintstypecodeid = this.complaintstypecodeid.servicetypecodeid[
                        this.complaintmodule.complaintstypecodeid.length - 1
                            ].toString()
                    }
                    console.log(this.complaintmodule)
                    data = this.complaintmodule;
                    worktype = this.complaintmodule.worktype;
                } else if ("comeconomicmodule" == this.formType) {
                    console.log(this.comeconomicmodule)
                    if (
                        this.comeconomicmodule.id == null ||
                        this.comeconomicmodule.id == ""
                    ) {
                        this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime()
                        if (
                            this.comeconomicmodule.businesstypecodeid != null &&
                            this.comeconomicmodule.businesstypecodeid != ""
                        ) {
                            this.comeconomicmodule.businesstypecodeid = this.comeconomicmodule.businesstypecodeid[
                            this.comeconomicmodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.providetypecodeid != null &&
                            this.comeconomicmodule.providetypecodeid != ""
                        ) {
                            this.comeconomicmodule.providetypecodeid = this.comeconomicmodule.providetypecodeid[
                            this.comeconomicmodule.providetypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.industrynamecodeid != null &&
                            this.comeconomicmodule.industrynamecodeid != ""
                        ) {
                            this.comeconomicmodule.industrynamecodeid = this.comeconomicmodule.industrynamecodeid[
                            this.comeconomicmodule.industrynamecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.objectcategorycodeid != null &&
                            this.comeconomicmodule.objectcategorycodeid != ""
                        ) {
                            this.comeconomicmodule.objectcategorycodeid = this.comeconomicmodule.objectcategorycodeid[
                            this.comeconomicmodule.objectcategorycodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.reportprovidelbcodeid != null &&
                            this.comeconomicmodule.reportprovidelbcodeid != ""
                        ) {
                            this.comeconomicmodule.reportprovidelbcodeid = this.comeconomicmodule.reportprovidelbcodeid[
                            this.comeconomicmodule.reportprovidelbcodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.illegalrulescodeid != null &&
                            this.comeconomicmodule.illegalrulescodeid != ""
                        ) {
                            this.comeconomicmodule.illegalrulescodeid = this.comeconomicmodule.illegalrulescodeid[
                            this.comeconomicmodule.illegalrulescodeid.length - 1
                                ].toString();
                        }
                    }
                    worktype = this.comeconomicmodule.worktype;
                    data = this.comeconomicmodule;
                    console.log(data)
                }*/
                if (this.formType == "commommodule") {
                    if (this.commommodule.id == null || this.commommodule.id == "") {
                        data = this.commommodule;
                        worktype = this.commommodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    } else {
                        data = this.commommodule;
                        worktype = this.commommodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    }
                }
                if (this.formType == "complaintmodule") {
                    if (this.complaintmodule.id == null || this.complaintmodule.id == "") {
                        this.complaintmodule.recorddate = this.complaintmodule.recorddate.getTime();
                        data = this.complaintmodule;
                        worktype = this.complaintmodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    } else {
                        data = this.complaintmodule;
                        worktype = this.complaintmodule.worktype;
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    }
                }
                if (this.formType == "comeconomicmodule") {
                    if (
                        this.comeconomicmodule.id == null ||
                        this.comeconomicmodule.id == undefined
                    ) {
                        this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime();
                    }
                    this.comeconomicmodule.expirationdate = this.comeconomicmodule.expirationdate.getTime();
                    data = this.comeconomicmodule;
                    worktype = this.comeconomicmodule.worktype;
                    for (let key in data) {
                        if (data[key] instanceof Array) {
                            if (data[key].length > 0) {
                                data[key] = data[key].pop();
                            } else {
                                data[key] = null;
                            }
                        }
                    }
                    /*else{

                        // if(this.comeconomicmodule.recorddate != null || this.comeconomicmodule.recorddate != ""){
                        //     this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime()
                        // }

                        data = this.comeconomicmodule;
                        worktype = this.comeconomicmodule.worktype;
                        console.log(this.comeconomicmodule)
                        console.log(data)
                        for (let key in data) {
                            if (data[key] instanceof Array) {
                                if (data[key].length > 0) {
                                    data[key] = data[key].pop();
                                } else {
                                    data[key] = null;
                                }
                            }
                        }
                    }*/
                }
                //深度拷贝，防止更改complaintform原先的表单值
                // let datas =JSON.parse(JSON.stringify(data))
                //转换数组
                /* for (let key in data) {
                    if (data[key] instanceof Array) {
                        if (data[key].length > 0) {
                            data[key] = data[key].pop();
                        } else {
                            data[key] = null;
                        }
                    }
                }*/

                let user = localStorage.getItem("user");
                if ("受理" == value) {
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        nextStepForm: this.nextStepForm,
                        formType: this.formType,
                        data,
                        nextNodeId: "accept",
                        caseguid: this.requestMap.caseguid,
                        opinion: this.nextStepForm.process,
                        worktype
                    };
                    next(para).then(res => {
                        if (res.code == 0) {
                            if ("true" != res.data.str) {
                                this.$alert(res.data.str, "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.aictabs = "appeal";
                                this.activeName = "complaintmodule";
                                this.accept = false;
                                this.acceptE = false;
                                this.notAccepts = false;
                                this.nextOrg = false;
                            } else {
                                this.$alert("受理成功!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "success"
                                });
                                let para = {
                                    id: res.data.id,
                                    caseguid: res.data.caseguid,
                                    node: "accept"
                                };
                                this.$router.push({path: "/wddb/bxtdb/qtsl", query: para});
                            }
                        } else {
                            this.$alert("提交失败!", "提示", {
                                confirmButtonText: "确定",
                                type: "defeated"
                            });
                        }
                    });
                } else if ("不受理" == value) {
                    let noAcceptreason;
                    if ("comeconomicmodule" == this.formType) {
                        noAcceptreason = this.nextStepForm.cause;
                    } else {
                        noAcceptreason = this.acceptForm.reason;
                    }
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        // nextStepForm:this.nextStepForm,
                        formType: this.formType,
                        data,
                        nextNodeId: "noAccept",
                        opinion: this.nextStepForm.centent,
                        noAcceptreason: noAcceptreason,
                        isEnd: "1"
                    };
                    next(para).then(res => {
                        if (res.code === 0) {
                            this.$alert("提交成功!", "提示", {
                                confirmButtonText: "确定",
                                type: "success"
                            });
                            this.$router.push("/wddb/bxtdb");
                        } else {
                            this.$alert("提交失败!", "提示", {
                                confirmButtonText: "确定",
                                type: "defeated"
                            });
                        }
                    });
                } else {
                    // this.$refs.assignmentForm.validate((valid) => validation = valid)
                    let para = {
                        user: JSON.parse(user),
                        node: this.nodes,
                        nextNodeId: "moveToLowerOrg",
                        formType: this.formType,
                        orgid: this.assignmentForm.unit,
                        data,
                        ortherDept: this.assignmentForm.depts,
                        opinion: this.assignmentForm.opinion
                    };
                    next(para).then(res => {
                        if (res.code === 0) {
                            this.$alert("分派下级成功!", "提示", {
                                confirmButtonText: "确定",
                                type: "success"
                            });
                            this.$router.push("/wddb/bxtdb");
                        } else {
                            this.$alert("提交失败!", "提示", {
                                confirmButtonText: "确定",
                                type: "defeated"
                            });
                        }
                    });
                }
            },

            acceptsE(value) {
                let nextNodeId;
                let opinion;
                if (this.nodes == "") {
                    this.nodes = "begin";
                } else if (this.$route.query.node != undefined) {
                    this.nodes = this.$route.query.node;
                }
                this.formType = "comeconomicmodule";
                if ("受理" === value) {
                    nextNodeId = "accept";
                    opinion = "受理";
                } else if ("不受理" === value) {
                    opinion = this.acceptForm.content;
                    nextNodeId = "end";
                } else {
                    node: this.nodes, (nextNodeId = "moveToLowerOrg");
                    opinion = this.assignmentForm.opinion;
                }
                let user = localStorage.getItem("user");

                if (
                    this.comeconomicmodule.id == undefined ||
                    this.comeconomicmodule.id == null
                ) {
                    this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime();
                }
                for (let key in this.comeconomicmodule) {
                    if (this.comeconomicmodule[key] instanceof Array) {
                        if (this.comeconomicmodule[key].length > 0) {
                            this.comeconomicmodule[key] = this.comeconomicmodule[key].pop();
                        } else {
                            this.comeconomicmodule[key] = null;
                        }
                    }
                }
                let para = {
                    user: JSON.parse(user),
                    node: this.nodes,
                    acceptForm: this.acceptForm,
                    opinion,
                    formType: this.formType,
                    data: this.comeconomicmodule,
                    nextNodeId,
                    worktype: this.comeconomicmodule.worktype
                };
                next(para).then(res => {
                    if (res.code == 0) {
                        if ("true" != res.data.str) {
                            this.$alert(res.data.str, "提示", {
                                confirmButtonText: "确定",
                                type: "success"
                            });
                            this.aictabs = "appeal";
                            this.activeName = "comeconomicmodule";
                            this.accept = false;
                            this.acceptE = false;
                            this.notAccepts = false;
                            this.nextOrg = false;
                        } else {
                            if (nextNodeId === "end") {
                                this.$alert("提交成功!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "success"
                                });
                                this.$router.push({path: "/wddb/bxtdb"});
                            } else {
                                this.$alert("受理成功!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "success"
                                });
                                let para = {
                                    id: res.data.id,
                                    caseguid: res.data.caseguid,
                                    node: nextNodeId
                                };
                                this.$router.push({path: "/wddb/bxtdb/sldjla", query: para});
                            }
                        }
                    } else {
                        this.$alert("提交失败!", "提示", {
                            confirmButtonText: "确定",
                            type: "defeated"
                        });
                    }
                });
            },
            toAccept(value, formType) {
                // this.stepNumber = 2;
                this.formType = formType;
                let validation;
                this.$refs.commommodule.validate(valid => (validation = valid));
                if (!validation) {
                    document.getElementById("fromExamine").scrollTop = 0;
                    return;
                }
                this.aictabs = "2";
                if ("accepts" == value) {
                    this.accept = true;
                } else if ("notAccepts" == value) {
                    this.notAccepts = true;
                } else {
                    this.nextOrg = true;
                }
            },
            // 投诉消费的受理
            toAcceptT(value, formType) {
                // 设置进度条的进度
                this.stepNumber = 2;

                for (let key in this.complaintmodule) {
                    if (this.complaintmodule[key] instanceof Array) {
                        if (this.complaintmodule[key].length > 0) {
                            this.complaintmodule[key] = this.complaintmodule[key].pop();
                        } else {
                            this.complaintmodule[key] = null;
                        }
                    }
                }

                /*if(this.complaintmodule.businesstypecodeid instanceof Array){
                    this.complaintmodule.businesstypecodeid = this.complaintmodule.businesstypecodeid[
                        this.complaintmodule.businesstypecodeid.length - 1
                        ].toString()
                }
                if(this.complaintmodule.entitytypecodeid instanceof Array){
                    this.complaintmodule.entitytypecodeid = this.complaintmodule.entitytypecodeid[
                    this.complaintmodule.entitytypecodeid.length - 1
                        ].toString()
                }
                if(this.complaintmodule.servicetypecodeid instanceof Array){
                    this.complaintmodule.servicetypecodeid = this.complaintmodule.servicetypecodeid[
                    this.complaintmodule.servicetypecodeid.length - 1
                        ].toString()
                }
                if(this.complaintmodule.complaintstypecodeid instanceof Array){
                    this.complaintmodule.complaintstypecodeid = this.complaintmodule.complaintstypecodeid[
                    this.complaintmodule.complaintstypecodeid.length - 1
                        ].toString()
                }*/
                let validation;
                this.formType = formType;
                this.$refs["complaintmoduleSystem"].validate(
                    valid => (validation = valid)
                );
                this.$refs["complaintmoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleSubject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintmoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) {
                    document.getElementById("fromExamine").scrollTop = 0;
                    return;
                }
                this.aictabs = "2";
                if ("accepts" == value) {
                    this.accept = true;
                } else if ("notAccepts" == value) {
                    this.notAccepts = true;
                } else {
                    this.nextOrg = true;
                }
            },

            //经济违法的受理、分派下级、不受理
            toAcceptE(isAccep, value) {
                // 设置当前受理的精度条为2
                // this.stepNumber = 2;
                this.formType = value;
                let validation = false;
                this.$refs["comeconomicmoduleSubject"].validate(
                    valid => (validation = valid)
                );
                this.$refs["comeconomicmoduleProvider"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["comeconomicmoduleObject"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["comeconomicmoduleBusiness"].validate(
                    valid => (validation = valid && validation)
                );
                if (validation) {
                    this.aictabs = "2";
                    // if ("nextOrg" == isAccep) {
                    //     this.nextOrg = true;
                    // } else {
                    //     this.acceptForm.isAccep = isAccep;
                    // }
                    if ("acceptE" == isAccep) {
                        this.accept = true;
                    } else if ("notAccepts" == isAccep) {
                        //nextOrg
                        this.notAccepts = true;
                    } else {
                        this.nextOrg = true;
                    }
                }
                document.getElementById("fromExamine").scrollTop = 0;
            },

            submitFrom(formType) {
                this.formType = formType;
                let validation = false;
                if ("commommodule" === this.formType) {
                    this.$refs.commommodule.validate(valid => (validation = valid));
                } else if ("complaintmodule" === this.formType) {
                    this.$refs["complaintmoduleSystem"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["complaintmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleSubject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                } else if ("comeconomicmodule" == this.formType) {
                    this.$refs["comeconomicmoduleSubject"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["comeconomicmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["comeconomicmoduleObject"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["comeconomicmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                }
                //通过提交
                if (validation) {
                    let user = localStorage.getItem("user");
                    let worktype;
                    if ("commommodule" == this.formType) {
                        worktype = this.commommodule.worktype;
                    } else if ("complaintmodule" == this.formType) {
                        worktype = this.complaintmodule.worktype;
                    } else {
                        worktype = this.comeconomicmodule.worktype;
                    }

                    //将获取的类型数组转化为字符串格式
                    if (this.formType == "commommodule") {
                        if (
                            this.commommodule.businesstypecodeid != null &&
                            this.commommodule.businesstypecodeid != ""
                        ) {
                            this.businesstypecodeid1 = this.commommodule.businesstypecodeid;
                            this.commommodule.businesstypecodeid = this.commommodule.businesstypecodeid[
                            this.commommodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                    }
                    if (this.formType == "complaintmodule") {
                        if (
                            this.complaintmodule.id == null ||
                            this.complaintmodule.id == ""
                        ) {
                            this.complaintmodule.recorddate = this.complaintmodule.recorddate.getTime();
                        }

                        for (let key in this.complaintmodule) {
                            if (this.complaintmodule[key] instanceof Array) {
                                if (this.complaintmodule[key].length > 0) {
                                    this.complaintmodule[key] = this.complaintmodule[key].pop();
                                } else {
                                    this.complaintmodule[key] = null;
                                }
                            }
                        }

                        /*if (
                            this.complaintmodule.businesstypecodeid != null &&
                            this.complaintmodule.businesstypecodeid != ""
                        ) {
                            this.businesstypecodeid2 = this.complaintmodule.businesstypecodeid
                            this.complaintmodule.businesstypecodeid = this.complaintmodule.businesstypecodeid[
                            this.complaintmodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.entitytypecodeid != null &&
                            this.complaintmodule.entitytypecodeid != ""
                        ) {
                            this.entitytypecodeid1 = this.complaintmodule.entitytypecodeid
                            this.complaintmodule.entitytypecodeid = this.complaintmodule.entitytypecodeid[
                            this.complaintmodule.entitytypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.servicetypecodeid != null &&
                            this.complaintmodule.servicetypecodeid != ""
                        ) {
                            this.servicetypecodeid1 = this.complaintmodule.servicetypecodeid
                            this.complaintmodule.servicetypecodeid = this.complaintmodule.servicetypecodeid[
                            this.complaintmodule.servicetypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.complaintmodule.complaintstypecodeid != null &&
                            this.complaintmodule.complaintstypecodeid != ""
                        ) {
                            this.complaintstypecodeid1 = this.complaintmodule.complaintstypecodeid
                            this.complaintmodule.complaintstypecodeid = this.complaintmodule.complaintstypecodeid[
                            this.complaintmodule.complaintstypecodeid.length - 1
                                ].toString();
                        }*/
                    }
                    if (this.formType == "comeconomicmodule") {
                        // if (this.comeconomicmodule.id == null || this.comeconomicmodule.id == "") {
                        //     this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime()
                        // }
                        this.comeconomicmodule.recorddate = this.comeconomicmodule.recorddate.getTime();
                        for (let key in this.comeconomicmodule) {
                            if (this.comeconomicmodule[key] instanceof Array) {
                                if (this.comeconomicmodule[key].length > 0) {
                                    this.comeconomicmodule[key] = this.comeconomicmodule[key].pop();
                                } else {
                                    this.comeconomicmodule[key] = null;
                                }
                            }
                        }

                        /*if (
                            this.comeconomicmodule.businesstypecodeid != null &&
                            this.comeconomicmodule.businesstypecodeid != ""
                        ) {
                            this.businesstypecodeid3 = this.comeconomicmodule.businesstypecodeid
                            this.comeconomicmodule.businesstypecodeid = this.comeconomicmodule.businesstypecodeid[
                            this.comeconomicmodule.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.providetypecodeid != null &&
                            this.comeconomicmodule.providetypecodeid != ""
                        ) {
                            this.providetypecodeid1 = this.comeconomicmodule.providetypecodeid
                            this.comeconomicmodule.providetypecodeid = this.comeconomicmodule.providetypecodeid[
                            this.comeconomicmodule.providetypecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.industrynamecodeid != null &&
                            this.comeconomicmodule.industrynamecodeid != ""
                        ) {
                            this.industrynamecodeid1 = this.comeconomicmodule.industrynamecodeid
                            this.comeconomicmodule.industrynamecodeid = this.comeconomicmodule.industrynamecodeid[
                            this.comeconomicmodule.industrynamecodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.objectcategorycodeid != null &&
                            this.comeconomicmodule.objectcategorycodeid != ""
                        ) {
                            this.objectcategorycodeid1 = this.comeconomicmodule.objectcategorycodeid
                            this.comeconomicmodule.objectcategorycodeid = this.comeconomicmodule.objectcategorycodeid[
                            this.comeconomicmodule.objectcategorycodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.reportprovidelbcodeid != null &&
                            this.comeconomicmodule.reportprovidelbcodeid != ""
                        ) {
                            this.reportprovidelbcodeid1 = this.comeconomicmodule.reportprovidelbcodeid
                            this.comeconomicmodule.reportprovidelbcodeid = this.comeconomicmodule.reportprovidelbcodeid[
                            this.comeconomicmodule.reportprovidelbcodeid.length - 1
                                ].toString();
                        }
                        if (
                            this.comeconomicmodule.illegalrulescodeid != null &&
                            this.comeconomicmodule.illegalrulescodeid != ""
                        ) {
                            this.illegalrulescodeid1 = this.comeconomicmodule.illegalrulescodeid
                            this.comeconomicmodule.illegalrulescodeid = this.comeconomicmodule.illegalrulescodeid[
                            this.comeconomicmodule.illegalrulescodeid.length - 1
                                ].toString();
                        }*/
                    }
                    let para = {
                        user: JSON.parse(user),
                        formType: this.formType,
                        commommodule: this.commommodule,
                        complaintmodule: this.complaintmodule,
                        comeconomicmodule: this.comeconomicmodule,
                        node: "begin",
                        caseguid: this.requestMap.caseguid,
                        nextNodeId: "staging",
                        method: "submit",
                        worktype
                    };
                    para.complaintmodule.recorddate = Date.parse(
                        para.complaintmodule.recorddate
                    );
                    para.comeconomicmodule.recorddate = Date.parse(
                        para.comeconomicmodule.recorddate
                    );

                    saveBaseInfo(para).then(res => {
                        if (res.code === 0) {
                            this.$message({
                                message: "保存成功！",
                                type: "success",
                                duration: "1000"
                            });
                            this.nodes = res.data.nextNodeId;
                            if ("commommodule" == res.data.formType) {
                                this.commommodule = res.data.customerinfo;
                                this.commommodule.worktype = res.data.tasklist.worktype;

                                if (
                                    this.commommodule.businesstypecodeid != null &&
                                    this.commommodule.businesstypecodeid != ""
                                ) {
                                    this.commommodule.businesstypecodeid = this.businesstypecodeid1;
                                }
                            } else if ("complaintmodule" == res.data.formType) {
                                this.complaintmodule = res.data.complaintform;
                                this.complaintmodule.worktype = res.data.tasklist.worktype;

                                if (
                                    this.complaintmodule.businesstypecodeid != null &&
                                    this.complaintmodule.businesstypecodeid != ""
                                ) {
                                    this.complaintmodule.businesstypecodeid = this.businesstypecodeid2;
                                }
                                if (
                                    this.complaintmodule.entitytypecodeid != null &&
                                    this.complaintmodule.entitytypecodeid != ""
                                ) {
                                    this.complaintmodule.entitytypecodeid = this.entitytypecodeid1;
                                }
                                if (
                                    this.complaintmodule.servicetypecodeid != null &&
                                    this.complaintmodule.servicetypecodeid != ""
                                ) {
                                    this.complaintmodule.servicetypecodeid = this.servicetypecodeid1;
                                }
                                if (
                                    this.complaintmodule.complaintstypecodeid != null &&
                                    this.complaintmodule.complaintstypecodeid != ""
                                ) {
                                    this.complaintmodule.complaintstypecodeid = this.complaintstypecodeid1;
                                }
                            } else if ("comeconomicmodule" == res.data.formType) {
                                this.comeconomicmodule = res.data.economyform;
                                this.comeconomicmodule.worktype = res.data.tasklist.worktype;

                                if (
                                    this.comeconomicmodule.businesstypecodeid != null &&
                                    this.comeconomicmodule.businesstypecodeid != ""
                                ) {
                                    this.comeconomicmodule.businesstypecodeid = this.businesstypecodeid3;
                                }
                                if (
                                    this.comeconomicmodule.providetypecodeid != null &&
                                    this.comeconomicmodule.providetypecodeid != ""
                                ) {
                                    this.comeconomicmodule.providetypecodeid = this.providetypecodeid1;
                                }
                                if (
                                    this.comeconomicmodule.industrynamecodeid != null &&
                                    this.comeconomicmodule.industrynamecodeid != ""
                                ) {
                                    this.comeconomicmodule.industrynamecodeid = this.industrynamecodeid1;
                                }
                                if (
                                    this.comeconomicmodule.objectcategorycodeid != null &&
                                    this.comeconomicmodule.objectcategorycodeid != ""
                                ) {
                                    this.comeconomicmodule.objectcategorycodeid = this.objectcategorycodeid1;
                                }
                                if (
                                    this.comeconomicmodule.reportprovidelbcodeid != null &&
                                    this.comeconomicmodule.reportprovidelbcodeid != ""
                                ) {
                                    this.comeconomicmodule.reportprovidelbcodeid = this.reportprovidelbcodeid1;
                                }
                                if (
                                    this.comeconomicmodule.illegalrulescodeid != null &&
                                    this.comeconomicmodule.illegalrulescodeid != ""
                                ) {
                                    this.comeconomicmodule.illegalrulescodeid = this.illegalrulescodeid1;
                                }
                            }
                        } else {
                            this.$alert("保存失败！", "提示", {
                                type: "error",
                                confirmButtonText: "确定"
                            });
                        }
                    });
                }
            },
            toGuiDan(formType) {
                this.formType = formType;
                let validation = false;
                if ("commommodule" === formType) {
                    this.$refs.commommodule.validate(valid => (validation = valid));
                } else if ("complaintmodule" === formType) {
                    this.$refs["complaintmoduleSubject"].validate(
                        valid => (validation = valid)
                    );
                    this.$refs["complaintmoduleProvider"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleKeTi"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["complaintmoduleBusiness"].validate(
                        valid => (validation = valid && validation)
                    );
                    // this.$refs['complaintmoduleProvider'].validate((valid) => validation = valid && validation);
                }
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

            uploadFile(err, file, fileList) {
                // alert(err);
            },
            submitUpload(value) {
                if (this.$route.query.caseguid === undefined) {
                    this.requestMap.caseguid = this.randomId;
                } else {
                    this.requestMap.caseguid = this.$route.query.caseguid;
                }
                this.$refs[value].submit();
            },
            progress(event, file, fileList) {
                // console.log(event);
            },

            nextStep() {
            },
            removeFile() {
                this.$confirm("确认删除选中的文件?", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.$message({
                            message: "删除成功",
                            type: "success"
                        });
                    })
                    .catch(() => {
                    });
            },
            orgChange(value) {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    regionId: users.regionID
                };
                findOrgUnitByRegionId(para).then(res => {
                    this.orgList = res.data.data;
                });
            },

            unitChange() {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    orgId: users.orgID
                };
                findOrgDeptByOrgId(para).then(res => {
                    this.deptList = res.data.data;
                    this.depts = res.data.data.filter(dept => {
                        if (this.assignmentForm.depts == dept.orgunitID) {
                            return false;
                        } else {
                            return true;
                        }
                    });
                });
            },
            orgPermission() {
                let unit = this.assignmentForm.depts[
                this.assignmentForm.depts.length - 1
                    ];
                let para = {
                    deptid: unit,
                    permission: "dealdepartment"
                };
                if (this.assignmentForm.depts.length > 0 && unit != "") {
                    findUsersByDeptIdAndPermission(para).then(res => {
                        if (res.data.data == null) {
                            this.$alert(
                                "该部门没有查看该业务权限的人员，请先添加权限!",
                                "提示",
                                {
                                    confirmButtonText: "确定"
                                }
                            );
                            this.assignmentForm.depts.splice(
                                this.assignmentForm.depts.indexOf(
                                    this.assignmentForm.depts[this.assignmentForm.depts.length - 1]
                                )
                            );
                        }
                    });
                }
            },

            getBaseInfo() {
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    return;
                }
                let systemsource = "01";
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource})
                    .then(res => {
                        this.tasklist = res.data.tasklist;
                        if (res.data.caseprocesform != null) {
                            this.caseprocesform = res.data.caseprocesform;
                        }
                        if (res.code === 0) {
                            if (res.data.consultationform != null) {
                                //咨询登记
                                this.formType = "commommodule";
                                this.formTypeStr = "咨询登记";
                                this.commommodule = res.data.consultationform;
                                // this.com = this.commommodule;
                                // console.log('咨询登记');
                                // console.log(this.commommodule);
                                this.commommodule.worktype = res.data.tasklist.worktype;
                            } else if (res.data.complaintform != null) {
                                this.formType = "complaintmodule";
                                this.aictabs = "appeal";
                                this.activeName = "complaintmodule";
                                this.complaintmodule = res.data.complaintform;
                                this.complaintmodule.worktype = res.data.tasklist.worktype;
                            } else if (res.data.economyform != null) {
                                this.formType = "comeconomicmodule";
                                this.aictabs = "appeal";
                                this.activeName = "comeconomicmodule";
                                this.comeconomicmodule = res.data.economyform;

                                // console.log('经济');
                                // console.log(this.comeconomicmodule );
                                this.comeconomicmodule.worktype = res.data.tasklist.worktype;
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.$router.push("/wddb/bxtdb");
                            }
                            this.opinionList = res.data.opinionList;
                            this.loading = false;
                            this.getMaterial(caseguid);
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                    });
            },
            // 获取用户信息
            getUserInfo() {
                let user = localStorage.getItem("user");
            },

            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 20 <= 1;
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
            //业务分类下拉框级联数据
            getBusinesstype1() {
                let params = {
                    type: "B",
                    orgLevel: "2"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.businesstype1 = this.data; //业务分类
                    if (
                        this.commommodule.businesstypecodeid != null &&
                        this.commommodule.businesstypecodeid != ""
                    ) {
                        this.commommodule.businesstypecodeid = getArrayByStringId(
                            this.commommodule.businesstypecodeid,
                            this.businesstype1
                        );
                    }
                    if (
                        this.complaintmodule.businesstypecodeid != null &&
                        this.complaintmodule.businesstypecodeid != ""
                    ) {
                        this.complaintmodule.businesstypecodeid = getArrayByStringId(
                            this.complaintmodule.businesstypecodeid,
                            this.businesstype1
                        );
                    }
                    if (
                        this.comeconomicmodule.businesstypecodeid != null &&
                        this.comeconomicmodule.businesstypecodeid != ""
                    ) {
                        this.comeconomicmodule.businesstypecodeid = getArrayByStringId(
                            this.comeconomicmodule.businesstypecodeid,
                            this.businesstype1
                        );
                    }
                });
            },
            //商品/服务类型下拉框级联数据
            getServicetype1() {
                let params = {
                    type: "A"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.servicetype1 = this.data;
                    this.objectcategory1 = this.data; //客体类别
                    if (
                        this.complaintmodule.servicetypecodeid != null &&
                        this.complaintmodule.servicetypecodeid != ""
                    ) {
                        this.complaintmodule.servicetypecodeid = getArrayByStringId(
                            this.complaintmodule.servicetypecodeid,
                            this.servicetype1
                        );
                    }
                    if (
                        this.comeconomicmodule.objectcategorycodeid != null &&
                        this.comeconomicmodule.objectcategorycodeid != ""
                    ) {
                        this.comeconomicmodule.objectcategorycodeid = getArrayByStringId(
                            this.comeconomicmodule.objectcategorycodeid,
                            this.objectcategory1
                        );
                    }
                });
            },
            //投诉问题类型下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "D"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.complaintstype1 = this.data; //投诉问题类型
                    this.reportprovidelb1 = this.data; //举报问题类别
                    if (
                        this.complaintmodule.complaintstypecodeid != null &&
                        this.complaintmodule.complaintstypecodeid != ""
                    ) {
                        this.complaintmodule.complaintstypecodeid = getArrayByStringId(
                            this.complaintmodule.complaintstypecodeid,
                            this.complaintstype1
                        );
                    }
                    if (
                        this.comeconomicmodule.reportprovidelbcodeid != null &&
                        this.comeconomicmodule.reportprovidelbcodeid != ""
                    ) {
                        this.comeconomicmodule.reportprovidelbcodeid = getArrayByStringId(
                            this.comeconomicmodule.reportprovidelbcodeid,
                            this.reportprovidelb1
                        );
                    }
                });
            },
            //提供方类型下拉框级联数据
            getUsertype1() {
                let params = {
                    type: "F"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    // this.usertype1 = this.data;
                    this.entitytype1 = this.data;
                    this.providetype1 = this.data;
                    if (
                        this.comeconomicmodule.providetypecodeid != null &&
                        this.comeconomicmodule.providetypecodeid != ""
                    ) {
                        this.comeconomicmodule.providetypecodeid = getArrayByStringId(
                            this.comeconomicmodule.providetypecodeid,
                            this.providetype1
                        );
                    }
                    if (
                        this.complaintmodule.entitytypecodeid != null &&
                        this.complaintmodule.entitytypecodeid != ""
                    ) {
                        this.complaintmodule.entitytypecodeid = getArrayByStringId(
                            this.complaintmodule.entitytypecodeid,
                            this.entitytype1
                        );
                    }
                });
            },
            //行业分类下拉框级联数据
            getIndustryname1() {
                let params = {
                    type: "E"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.industryname1 = this.data;
                    if (
                        this.comeconomicmodule.industrynamecodeid != null &&
                        this.comeconomicmodule.industrynamecodeid != ""
                    ) {
                        this.comeconomicmodule.industrynamecodeid = getArrayByStringId(
                            this.comeconomicmodule.industrynamecodeid,
                            this.industryname1
                        );
                    }
                });
            },
            //涉及违法行为下拉框级联数据
            getIllegalrules1() {
                let params = {
                    type: "C"
                    // orglevel:"1"
                };
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data;
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment
                        };
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms);
                        }
                        list.push(item);
                    }
                    this.data = list;
                    this.illegalrules1 = this.data; //涉及违法行为
                    if (
                        this.comeconomicmodule.illegalrulescodeid != null &&
                        this.comeconomicmodule.illegalrulescodeid != ""
                    ) {
                        this.comeconomicmodule.illegalrulescodeid = getArrayByStringId(
                            this.comeconomicmodule.illegalrulescodeid,
                            this.illegalrules1
                        );
                    }
                });
            },

            handleChangebusinesstype1(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1]);
                this.commommodule.businesstype = byIdGetLabels(
                    label,
                    this.businesstype1
                ).join("-");
                this.commommodule.businesstypecodeid = array;
            },
            handleChangebusinesstype2(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1]);
                this.comeconomicmodule.businesstype = byIdGetLabels(
                    label,
                    this.businesstype1
                ).join("-");
                this.comeconomicmodule.businesstypecodeid = array;
            },
            handleChangebusinesstype3(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1]);
                this.complaintmodule.businesstype = byIdGetLabels(
                    label,
                    this.businesstype1
                ).join("-");
                this.complaintmodule.businesstypecodeid = array;
            },
            handleChangeentitytype1(array) {
                let label = this.getLabel(this.entitytype1, array[array.length - 1]);
                this.complaintmodule.entitytype = byIdGetLabels(
                    label,
                    this.entitytype1
                ).join("-");
                this.complaintmodule.entitytypecodeid = array;
            },
            handleChangeservicetype1(array) {
                let label = this.getLabel(this.servicetype1, array[array.length - 1]);
                this.complaintmodule.servicetype = byIdGetLabels(
                    label,
                    this.servicetype1
                ).join("-");
                this.complaintmodule.servicetypecodeid = array;
            },
            handleChangecomplaintstype1(array) {
                let label = this.getLabel(this.complaintstype1, array[array.length - 1]);
                this.complaintmodule.complaintstype = byIdGetLabels(
                    label,
                    this.complaintstype1
                ).join("-");
                this.complaintmodule.complaintstypecodeid = array;
            },
            handleChangeprovidetype1(array) {
                let label = this.getLabel(this.providetype1, array[array.length - 1]);
                this.comeconomicmodule.providetype = byIdGetLabels(
                    label,
                    this.providetype1
                ).join("-");
                this.comeconomicmodule.providetypecodeid = array;
            },
            handleChangeobjectcategory1(array) {
                let label = this.getLabel(this.objectcategory1, array[array.length - 1]);
                this.comeconomicmodule.objectcategory = byIdGetLabels(
                    label,
                    this.objectcategory1
                ).join("-");
                this.comeconomicmodule.objectcategorycodeid = array;
            },
            handleChangereportprovidelb1(array) {
                let label = this.getLabel(this.reportprovidelb1, array[array.length - 1]);
                this.comeconomicmodule.reportprovidelb = byIdGetLabels(
                    label,
                    this.reportprovidelb1
                ).join("-");
                this.comeconomicmodule.reportprovidelbcodeid = array;
            },
            handleChangeillegalrules1(array) {
                let label = this.getLabel(this.illegalrules1, array[array.length - 1]);
                this.comeconomicmodule.illegalrules = byIdGetLabels(
                    label,
                    this.illegalrules1
                ).join("-");
                this.comeconomicmodule.illegalrulescodeid = array;
            },
            handleChangeindustryname1(array) {
                let label = this.getLabel(this.industryname1, array[array.length - 1]);
                this.comeconomicmodule.industryname = byIdGetLabels(
                    label,
                    this.industryname1
                ).join("-");
                this.comeconomicmodule.industrynamecodeid = array;
            },

            handleChange(array) {
            },
            getLabel(children, codeid) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.label;
                    }
                    if (child.children) {
                        let label = this.getLabel(child.children, codeid);
                        if (label) {
                            return label;
                        }
                    }
                }
            },
            toFindnext() {
            },
            dataToString() {
            },
            getLevels(items) {
                let list = [];
                for (const i in items) {
                    let data = {
                        value: items[i].codeid,
                        label: items[i].comment
                    };
                    if (items[i].basecodeforms.length > 0) {
                        data.children = this.getLevels(items[i].basecodeforms);
                    }
                    list.push(data);
                }
                return list;
            },
            querySearch(queryString, cb) {
                let results = queryString
                    ? this.brandsSearch.filter(
                        item => item.value.indexOf(queryString) !== -1
                    )
                    : this.brandsSearch;
                // 调用 callback 返回建议列表的数据
                cb(results);
            }
        },

        mounted() {
            const uuidV1 = require("uuid/v1");
            this.randomId = uuidV1();
            this.requestMap.caseguid = uuidV1();
            this.getUserInfo();
            this.getBusinesstype1();
            this.getServicetype1();
            this.getComplaintstype1();
            this.getUsertype1();
            this.getIndustryname1();
            this.getIllegalrules1();
        },
        created() {
            this.getBaseInfo();
            this.orgChange();
            this.unitChange();
            // this.getbusinesstypes();
            this.commommodule.id = this.$route.query.id;
            this.commommodule.caseguid = this.$route.query.caseguid;
            this.complaintmodule.id = this.$route.query.id;
            this.complaintmodule.caseguid = this.$route.query.caseguid;
            this.comeconomicmodule.id = this.$route.query.id;
            this.comeconomicmodule.caseguid = this.$route.query.caseguid;
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
        text-align: left;
        width: 100%;
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

    .stab {
        font-size: 13px;
        font-weight: bold;
    }

    .el-accordion {
        font-size: 13px;
        font-weight: bold;
        color: #1e1f20;
        // cursor: auto;
    }

    .input-text {
        width: 100%;
    }

    .input-texts {
        width: 25%;
    }
</style>
