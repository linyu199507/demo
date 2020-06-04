<!--申请延期审批-->
<template>
    <section class="approval" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div style="text-align: right;"  :class="{'fixed':scrollTop > height}">
                    <el-button type="primary" @click="triggerTab('tabBy')">审核通过</el-button>
                    <el-button type="primary" @click="triggerTab('tabFail')">审核不通过</el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <!-- 定单信息 -->
                    <!--<el-collapse-item name="orderInfo">-->
                        <!--<div slot="title" @click.stop>-->
                            <!--<el-row class="accordion_title">-->
                                <!--<el-col :span="12">-->
                                    <!--<em>1.</em> 定单信息-->
                                <!--</el-col>-->
                                <!--<el-col :span="10" class="right">订单号：{{tasklist.tserialnum}}</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->
                    <!--</el-collapse-item>-->
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>1.</em> 表单内容
                                </el-col>
                                <el-col :span="10" class="right">订单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>
                        <!-- 消费投诉信息内容 -->
                        <complaintmoduleInfo v-if="complaintform" :complaintmodule="complaintform"
                                             :casedata="caseprocesform" :tasklist="tasklist"
                        />
                        <!--经济违法信息内容 -->
                        <economyInfo v-if="economyform" :comeconomicmodule="economyform" :casedata="caseprocesform"
                                     :tasklist="tasklist"/>
                        <!--咨询或者通用表单 -->
                        <consultationInfo
                                v-if="commomform"
                                :commommodule="commomform" :casedata="caseprocesform"
                                :tasklist="tasklist"
                        />
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!--办理跟踪-->
            <el-tab-pane name="blgz" label="办理跟踪">
                <el-collapse v-model="collapseValues">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion title_accordion">
                            <el-col :span="12">
                                <em>1.</em>
                                办理跟踪
                            </el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <tracking :data="opinionList" />
                </el-collapse>
            </el-tab-pane>

            <!-- 审核通过 -->
            <el-tab-pane label="审核通过" name="tabBy" v-if="tabActionName==='tabBy'">
                <el-form
                        :model="approvalForm"
                        label-width="200px"
                        ref="approvalForm"
                        :rules="approvalFormRules"
                        class="text_bold"
                        label-suffix="："
                >
                    <el-collapse v-model="registrationActiveNames" class="accordion">
                        <el-collapse-item name="1" v-if="formType === 'comeconomicmodule' || formType === 'complaintmodule' ">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6">
                                        涉及主体信息
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form :model="caseprocesform" label-width="180px" ref="caseprocesformD"
                                     :rules="caseprocesformRules">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体：" prop="subjectname" class="bt">
                                            <el-input placeholder="请输入" v-model="caseprocesform.subjectname"
                                                      class="input-text"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体类别和类型：" prop="subjecttype" class="bt">
                                            <el-cascader filterable
                                                    placeholder="请选择涉及主体类别和类型"
                                                    :options="subjecttype1"
                                                    v-model="caseprocesform.subjecttypecodeid"
                                                    @change="handleChangeSubjecttype1"
                                                    :show-all-levels="false"
                                                    class="bt1">
                                            </el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="经营地址：" prop="businessaddress" class="bt">
                                            <el-input placeholder="请输入" v-model="caseprocesform.businessaddress"
                                                      class="input-text"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="核查经营地址：" prop="cksubjectaddress" class="bt">
                                            <el-input placeholder="请输入" v-model="caseprocesform.semakbusinessaddress"
                                                      class="input-text"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="核查经营名称：" prop="cksubjectname" class="bt">
                                            <el-input placeholder="请输入" v-model="caseprocesform.semaknama"
                                                      class="input-text"></el-input>
                                        </el-form-item>
                                    </el-col>

                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="核查分类：" prop="classification" class="bt">
                                            <el-select
                                                    placeholder="请选择核查分类"
                                                    v-model="caseprocesform.classification"
                                                    class="input-text"
                                            >
                                                <el-option label="研制" value="研制"></el-option>
                                                <el-option label="生产" value="生产"></el-option>
                                                <el-option label="流通" value="流通"></el-option>
                                                <el-option label="服务" value="服务"></el-option>
                                                <el-option label="餐饮服务/使用" value="餐饮服务/使用"></el-option>
                                                <el-option label="其他" value="其他"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="商圈：" prop="businesscircle" class="bt">
                                            <el-input
                                                    placeholder="请输入商圈"
                                                    v-model="caseprocesform.businesscircle"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>

                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="2" v-if="formType === 'complaintmodule'  ">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6">
                                        调解情况
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form :model="caseprocesform" label-width="180px" ref="caseprocesformE"
                                     :rules="caseprocesformRules">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="行政调解文书号：" class="bt">
                                            <el-input placeholder="请输入" v-model="caseprocesform.mediationnumber"
                                                      class="input-text"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="侵权类型：" prop="infringementtype" class="bt">
                                            <el-cascader filterable
                                                    placeholder="请选择侵权类型"
                                                    :options="infringementtype1"
                                                    v-model="caseprocesform.infringementtypecodeid"
                                                    @change="handleChangeInfringementtype1"
                                                    :show-all-levels="false"
                                                    class="bt1">
                                            </el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="欺诈标志：" prop="infringingmarks" class="bt">
                                            <el-cascader filterable
                                                    placeholder="请选择欺诈标志"
                                                    :options="infringingmarks1"
                                                    v-model="caseprocesform.infringingmarkscodeid"
                                                    @change="handleChangeInfringingmarks1"
                                                    :show-all-levels="false"
                                                    class="bt1">
                                            </el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="调解方式：" prop="mediationmethods" class="bt">
                                            <el-cascader filterable
                                                    placeholder="请选择调解方式"
                                                    :options="mediationmethods1"
                                                    v-model="caseprocesform.mediationmethodscodeid"
                                                    @change="handleChangeMediationmethods1"
                                                    :show-all-levels="false"
                                                    class="bt1">
                                            </el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="未履行义务：" prop="noobligation" class="bt">
                                            <el-cascader filterable
                                                    placeholder="请选择未履行义务"
                                                    :options="noobligation1"
                                                    v-model="caseprocesform.noobligationcodeid"
                                                    @change="handleChangeNoobligation1"
                                                    :show-all-levels="false"
                                                    class="bt1">
                                            </el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="调解结果：" prop="resultsofmediation" class="bt">
                                            <el-select
                                                    placeholder="如选择其他视为调解不成功"
                                                    v-model="caseprocesform.resultsofmediation"
                                                    class="input-text">
                                                <el-option label="调解成功" value="调解成功"></el-option>
                                                <el-option label="调解不成功" value="解不成功"></el-option>
                                                <el-option label="其他" value="其他"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="争议金额：" prop="disputemoney" class="bt">
                                            <el-input
                                                    placeholder="请输入金额"
                                                    v-model.number="caseprocesform.disputemoney"
                                                    class="input-text"
                                            >
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="精神赔偿金额：" prop="mentalmoney" class="bt">
                                            <el-input
                                                    placeholder="请输入金额"
                                                    v-model.number="caseprocesform.mentalmoney"
                                                    class="input-text"
                                            >
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="加倍赔偿金额：" prop="doublemoney" class="bt">
                                            <el-input
                                                    placeholder="请输入金额"
                                                    v-model.number="caseprocesform.doublemoney"
                                                    class="input-text"
                                            >
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="挽回经济损失：" prop="financialloss" class="bt">
                                            <el-input
                                                    placeholder="请输入金额"
                                                    v-model.number="caseprocesform.financialloss"
                                                    class="input-text"
                                            >
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="办结日期：" prop="transferreddate" class="bt">
                                            <el-date-picker
                                                    type="date"
                                                    v-model="caseprocesform.transferreddate"
                                                    class="input-text"
                                                    style="width: 100%"
                                                    value-format="yyyy-MM-dd"
                                                    @change="dateChanges"
                                                    placeholder="选择日期"
                                            ></el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="调解情况:" prop="mediations" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :autosize="{ minRows: 4, maxRows: 6}"
                                                    placeholder="请输入调查情况(1200字以内)"
                                                    v-model="caseprocesform.mediationis">
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="3">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6">
                                        处理信息
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form :model="caseprocesform" label-width="180px" ref="caseprocesformF"
                                     :rules="caseprocesformRules">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="处理结果：" prop="handleopinion" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入审核意见（3000字内）"
                                                    v-model="caseprocesform.handleopinion"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="是否诉转案：" class="bt" prop="applealtocase" v-if="formType === 'complaintmodule' ">
                                            <el-select
                                                    placeholder="请选择"
                                                    v-model="caseprocesform.applealtocase"
                                                    class="input-text"
                                                    @change="caseChange"
                                            >
                                                <el-option label="是" value="1"></el-option>
                                                <el-option label="否" value="0"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12" v-if="registercase">
                                        <el-form-item label="立案号：" class="bt" prop="caseno">
                                            <el-input
                                                    placeholder="请输入立案号"
                                                    v-model="caseprocesform.caseno"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12" v-if="registercase">
                                        <el-form-item label="立案时间：" class="bt" prop="casedate">
                                            <el-date-picker
                                                    type="date"
                                                    v-model="caseprocesform.casedate"
                                                    class="input-text"
                                                    style="width: 100%"
                                                    value-format="yyyy-MM-dd"
                                                    @change="dateChangeE"
                                                    placeholder="选择立案日期"
                                            ></el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" v-if="registercase">
                                        <el-form-item label="案由：" class="bt" prop="casereason">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入案由（300字内）"
                                                    v-model="caseprocesform.casereason"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <!--<el-col :xs="24" :sm="24" :md="12" :lg="6">-->
                                        <!--<el-form-item label="相关附件:" class="bt">-->
                                            <!--<el-button-->
                                                    <!--size="small"-->
                                                    <!--@click="toUploadOpen('woferd')"-->
                                                    <!--class="el-button&#45;&#45;primary"-->
                                            <!--&gt;-->
                                                <!--<i class="fa fa-upload"></i>-->
                                                <!--选择文件-->
                                            <!--</el-button>-->
                                        <!--</el-form-item>-->
                                    <!--</el-col>-->
                                    <el-col :xs="24" :sm="12" :md="8" :lg="24">
                                        <el-form-item label="相关附件：" class="bt">
                                            <el-upload
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
                                                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                                <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
                                            </el-upload>
                                        </el-form-item >
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="4">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6">
                                        审核意见
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form :model="approvalForm" label-width="180px">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                                        <el-form-item label="是否通过: " prop="isPass">
                                            <div style="line-height: 35px">{{approvalForm.isPass}}</div>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                                        <el-form-item label="审核意见: " prop="resolution">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入审核意见（3000字内）"
                                                    v-model="approvalForm.resolution"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col>
                                        <el-form-item label="请选择区局处理人: " prop="leader" class="form_bt">
                                            <el-select v-model="approvalForm.leader" placeholder="请选择">
                                                <el-option
                                                        v-for="item in leaderLists"
                                                        :key="item.region"
                                                        :label="item.name"
                                                        :value="item.primaryKey"
                                                        @change="app($event)"
                                                ></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                    </el-collapse>
                    <el-row>

                        <el-col class="centered">
                            <el-button type="primary" @click="submitApproval" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 审核不通过 -->
            <el-tab-pane label="审核不通过" name="tabFail" v-if="tabActionName==='tabFail'">
                <el-form
                        :model="approvalForm"
                        label-width="200px"
                        ref="approvalForm"
                        :rules="approvalFormRules"
                        class="text_bold"
                        label-suffix="："
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="是否通过" prop="isPass">
                                <div style="line-height: 35px">{{approvalForm.isPass}}</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="审核意见" prop="resolution">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入审核意见（3000字内）"
                                        v-model="approvalForm.resolution"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="24">
                            <el-form-item label="相关附件：" class="bt">
                                <el-upload
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
                                    <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                    <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
                                </el-upload>
                            </el-form-item >
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitApproval" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import {searchBaseInfo, nextWork, findUsersByPermission, selectBaseCodeTree,selectDataList,getMaterialLists} from "../../api/convenient12345";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "../sldj/components/EconomyInfo";
    import ConsultationInfo from "../sldj/components/commommoduleInfo";
    import ComplaintmoduleInfo from "../sldj/components/ComplaintmoduleInfo"
    import {getArrayByStringId } from "../../utils/form-util";
    import {myMixin} from '../../assets/mixin/mixin'
    export default {
        mixins:[myMixin],
        components: {
            Tracking,
            ComplaintmoduleInfo,
            EconomyInfo,
            ConsultationInfo
        },
        data() {
            return {
                // 材料列表
                fileLists: [],
                fileListsAccept:[],
                materialList: [],
                requestMap: {
                    caseguid: ""
                },
                leaderLists: [],
                data: {},
                subjecttype1: [],//涉及主体类别和类型下拉框内容
                noobligation1: [],//未履行义务下拉框内容
                mediationmethods1: [],//调解方式下拉框内容
                infringingmarks1: [],//欺诈标志下拉框内容
                infringementtype1: [],//侵权类型下拉框内容
                registrationActiveNames: ['1', '2', '3', '4', '5'],
                registercase:false,
                user: JSON.parse(localStorage.getItem("user")),
                formType: "",
                formTypeStr: "",
                nodeStr: undefined,
                tabActionName: "tabMainContent",
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking"
                ],
                loading: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: undefined,
                consultationform: undefined,
                casedata:undefined,
                handledemand: {},
                opinionList: [],
                // caseprocesform: {
                //     subjectname:"",
                // },
                caseprocesform: {
                    id: "",
                    caseguid: "",
                    nodeid: "",
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
                    applealtocase:"",
                    caseno:"",
                    casedate:"",
                    casereason:"",
                },
                customerinfo: {},
                tasklist: {},
                ordernumber: "",
                approvalForm: {
                    isPass: undefined,
                    resolution: "",
                    leader: "",
                },
                approvalFormRules: {
                    isPass: [{required: true}],
                    resolution: [
                        {required: true, message: "请输入审核意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    leader: [
                        {required: true, message: "请选择区局处理人"},
                    ],
                },

                caseprocesformRules: {
                    ispursuit: [{ required: true, message: "请选择是否销案" }],
                    handleopinion: [
                        { required: true, message: "请输入审核意见", trigger: "blur" },
                        { max: 3000, message: "不能超出3000字符", trigger: "blur" }
                    ],
                    subjectname: [
                        { required: true, message: "请输入涉及主体", trigger: "blur" },
                        { max: 100, message: "不能超出100字符", trigger: "blur" }
                    ],
                    subjecttype: [{ required: true, message: "请选择涉及主体类别和类型" }],
                    businessaddress: [
                        { required: true, message: "请输入经营地址", trigger: "blur" },
                        { max: 100, message: "不能超出100字符", trigger: "blur" }
                    ],
                    semakbusinessaddress: [
                        { required: true, message: "请输入核查经营地址", trigger: "blur" },
                        { max: 100, message: "不能超出100字符", trigger: "blur" }
                    ],
                    semaknama: [
                        { required: true, message: "请输入核查经营名称", trigger: "blur" },
                        { max: 100, message: "不能超出100字符", trigger: "blur" }
                    ],
                    infringementtype: [{ required: true, message: "请选择侵权类型" }],
                    /*infringingmarks: [
                            { required: true, message: "请选择欺诈标志" },
                        ],*/
                    mediationmethods: [{ required: true, message: "请选择调解方式" }],
                    /*noobligation: [
                            { required: true, message: "请选择未履行义务", trigger: "change" },
                        ],*/
                    resultsofmediation: [
                        {
                            required: true,
                            message: "如选择其他视为调解不成功",
                            trigger: "blur"
                        },
                        { max: 100, message: "不能超出100字符", trigger: "blur" }
                    ],
                    disputemoney: [
                        {
                            required: true,
                            type: "number",
                            message: "请输入争议金额",
                            trigger: "blur"
                        }
                    ],
                    mentalmoney: [
                        {
                            required: true,
                            type: "number",
                            message: "请输入精神赔偿金额",
                            trigger: "blur"
                        }
                    ],
                    doublemoney: [
                        {
                            required: true,
                            type: "number",
                            message: "请输入加倍赔偿金额",
                            trigger: "blur"
                        }
                    ],
                    financialloss: [
                        {
                            required: true,
                            type: "number",
                            message: "请输入挽回经济损失金额",
                            trigger: "blur"
                        }
                    ],
                    transferreddate: [
                        {
                            required: true,
                            type: "date",
                            message: "选择办结日期",
                            trigger: "blur"
                        }
                    ],
                    mediationis: [
                        {
                            required: true,
                            message: "请输入调查情况(1200字以内)",
                            trigger: "blur"
                        },
                        { max: 1200, message: "不能超出1200字符", trigger: "blur" }
                    ],
                    handleopinion: [
                        {
                            required: true,
                            message: "请输入处理结果（3000字内）",
                            trigger: "blur"
                        },
                        { max: 3000, message: "不能超出3000字符", trigger: "blur" }
                    ],
                    applealtocase: [
                        {
                            required: true,
                            message: "请选择是否诉转案",
                            trigger: "blur"
                        },
                    ],
                    caseno: [
                        {
                            required: true,
                            message: "请输入立案号",
                            trigger: "blur"
                        },
                        { max: 30, message: "不能超出3000字符", trigger: "blur" }
                    ],
                    casedate: [
                        {
                            required: true,
                            type: "date",
                            message: "请输入立案时间",
                            trigger: "blur"
                        },
                    ],
                    casereason: [
                        {
                            required: true,
                            message: "请输入案由",
                            trigger: "blur"
                        },
                        { max: 500, message: "不能超出3000字符", trigger: "blur" }
                    ],
                },

                submitLoading: false
            };
        },
        methods: {

            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url)
                } else {
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
                    ".xlsx",
                    ".pdf",
                    ".zip",
                    ".wps",
                    ".mp3"
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
                if(this.complaintform != undefined ){
                    this.requestMap.caseguid = this.complaintform.caseguid;
                }
                if(this.economyform != undefined ){
                    this.requestMap.caseguid = this.economyform.caseguid;
                }
                if(this.consultationform != undefined ){
                    this.requestMap.caseguid = this.consultationform.caseguid;
                }
                if(this.commomform != undefined ){
                    this.requestMap.caseguid = this.commomform.caseguid;
                }
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
            triggerTab(value) {
                this.tabActionName = value;
                if (value === "tabBy") {
                    this.approvalForm.isPass = "通过";
                } else {
                    this.approvalForm.isPass = "不通过";
                }
                this.leaderChange();
            },
            dateChanges(date) {
                this.caseprocesform.transferreddate = date;
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            app(value){
            },
            submitApproval() {
                //提交审核结果
                let data;
                if ("commommodule" === this.formType) {
                    if (this.commomform != null) {
                        data = this.commomform;
                    } else {
                        data = this.consultationform;
                    }
                } else if ("complaintmodule" === this.formType) {
                    data = this.complaintform;
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = this.caseprocesform.infringementtypecodeid[
                        this.caseprocesform.infringementtypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = this.caseprocesform.infringingmarkscodeid[
                        this.caseprocesform.infringingmarkscodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = this.caseprocesform.mediationmethodscodeid[
                        this.caseprocesform.mediationmethodscodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = this.caseprocesform.noobligationcodeid[
                        this.caseprocesform.noobligationcodeid.length - 1
                            ].toString();
                    }
                } else if ("comeconomicmodule" === this.formType) {
                    data = this.economyform;
                    if (
                        this.caseprocesform.transferreddate != null &&
                        this.caseprocesform.transferreddate != ""
                    ) {
                        this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
                    }
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
                            ].toString();
                    }
                }

                let validation = false;
                this.$refs.approvalForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let params;
                    if (this.approvalForm.isPass === "通过") {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: 'regionAudit',
                            data: data,
                            deptid: this.approvalForm.leader,
                            opinion: this.approvalForm.resolution,
                            caseprocesform:this.caseprocesform
                        };
                    } else {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: 'superiorProcessing',
                            data: data,
                            opinion: this.approvalForm.resolution
                        };
                    }
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                 this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration:'1000'
                                });
                                if(window.opener){
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                },500);
                            } else {
                                this.errorAlert();
                            }
                            this.submitLoading = false;
                        })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },
            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
                });
            },
            getBaseInfo() {
                this.loading = true;
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    this.$router.push("/wddb/bxtdb");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource: "01"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;
                        if (this.nodeStr !== "leaderApr") {
                            //节点与当前对应不上
                            this.$router.push("/wddb/bxtdb");
                            return;
                        }
                        if (data.caseprocesform != null) {
                            this.caseprocesform = data.caseprocesform;
                            if(this.caseprocesform.applealtocase == "1"){
                                this.registercase = true
                            }
                        }
                        this.customerinfo = data.customerinfo;
                        this.tasklist = data.tasklist;
                        this.opinionList = data.opinionList;
                        this.handledemand = data.handledemand;
                        if (code === 0) {
                            if (data.caseprocesform != null) {
                                this.caseprocesform = data.caseprocesform;


                            }
                            if (data.economyform != null) {
                                //经济表单
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = "经济违法行为";
                                this.economyform = data.economyform;
                                this.ordernumber = this.economyform.ordernumber;
                            } else if (data.complaintform != null) {
                                //消费投诉表单
                                // this.formType = "complaintform";
                                this.formType = "complaintmodule";
                                this.formTypeStr = "消费投诉";
                                this.complaintform = data.complaintform;
                                this.ordernumber = this.complaintform.ordernumber;
                            }
                            // else if (data.consultationform != null) {
                            //     //咨询登记
                            //     // this.formType = "consultationform";
                            //     this.formType = "commommodule";
                            //     this.formTypeStr = "咨询登记";
                            //     this.consultationform = data.consultationform;
                            //     this.ordernumber = this.consultationform.ordernumber;
                            // }
                            else if (data.commomform != null) {
                                //通用表单
                                this.formType = "commommodule";
                                this.formTypeStr = "咨询登记";
                                this.commomform = data.commomform;
                                this.ordernumber = this.commomform.ordernumber;
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.$router.push("/wddb/bxtdb");
                            }
                        }
                        this.loading = false;
                    })
                    .catch(error => {
                        console.error(error);
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                        this.loading = false;
                    });
            },
            handleChange(value, row) {

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
            handleChangeSubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1])
                this.caseprocesform.subjecttype = label
                this.caseprocesform.subjecttypecodeid = array
            },
            handleChangeInfringementtype1(array) {
                let label = this.getLabel(this.infringementtype1, array[array.length - 1])
                this.caseprocesform.infringementtype = label
                this.caseprocesform.infringementtypecodeid=array
            },
            handleChangeInfringingmarks1(array) {
                let label = this.getLabel(this.infringingmarks1, array[array.length - 1])
                this.caseprocesform.infringingmarks = label
                this.caseprocesform.infringingmarkscodeid=array
            },
            handleChangeMediationmethods1(array) {
                let label = this.getLabel(this.mediationmethods1, array[array.length - 1])
                this.caseprocesform.mediationmethods = label
                this.caseprocesform.mediationmethodscodeid=array
            },
            handleChangeNoobligation1(array) {
                let label = this.getLabel(this.noobligation1, array[array.length - 1])
                this.caseprocesform.noobligation = label
                this.caseprocesform.noobligationcodeid=array
            },
            leaderChange() {
                let para = {
                    permission: "delayApproval"
                };
                findUsersByPermission(para).then(res => {
                    this.leaderLists = res.data;
                });
                if(this.caseprocesform.subjecttypecodeid != null && this.caseprocesform.subjecttypecodeid != ""){
                    this.caseprocesform.subjecttypecodeid=getArrayByStringId(this.caseprocesform.subjecttypecodeid, this.subjecttype1 )
                }
                if(this.caseprocesform.noobligationcodeid != null && this.caseprocesform.noobligationcodeid != ""){
                    this.caseprocesform.noobligationcodeid=getArrayByStringId(this.caseprocesform.noobligationcodeid, this.noobligation1 )
                }
                if(this.caseprocesform.mediationmethodscodeid != null && this.caseprocesform.mediationmethodscodeid != ""){
                    this.caseprocesform.mediationmethodscodeid=getArrayByStringId(this.caseprocesform.mediationmethodscodeid, this.mediationmethods1 )
                }
                if(this.caseprocesform.infringingmarkscodeid != null && this.caseprocesform.infringingmarkscodeid != ""){
                    this.caseprocesform.infringingmarkscodeid=getArrayByStringId(this.caseprocesform.infringingmarkscodeid, this.infringingmarks1 )
                }
                if(this.caseprocesform.infringementtypecodeid != null && this.caseprocesform.infringementtypecodeid != ""){
                    this.caseprocesform.infringementtypecodeid=getArrayByStringId(this.caseprocesform.infringementtypecodeid, this.infringementtype1 )
                }
            },


            getLevels(items) {
                let list = []
                for (const i in items) {
                    let data = {
                        value: items[i].codeid,
                        label: items[i].comment,
                    }
                    if (items[i].basecodeforms.length > 0) {
                        data.children = this.getLevels(items[i].basecodeforms)
                    }
                    list.push(data)
                }
                return list
            },
            //侵权类型下拉框级联数据
            getInfringementtype1() {
                let params = {
                    type: "tort12345",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data = list
                    this.infringementtype1 = this.data//侵权类型

                });
            },
            //欺诈标志下拉框级联数据
            getInfringingmarks1() {
                let params = {
                    type: "H",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data = list
                    this.infringingmarks1 = this.data//欺诈标志

                });
            },
            //调解方式下拉框级联数据
            getMediationmethods1() {
                let params = {
                    type: "J",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data = list
                    this.mediationmethods1 = this.data//调解方式

                });
            },
            //未履行义务下拉框级联数据
            getNoobligation1() {
                let params = {
                    type: "K",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(data => {
                    this.data = data.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data = list
                    this.noobligation1 = this.data//未履行义务

                });
            },
            //涉及主体类别和类型下拉框级联数据
            getSubjecttype1() {
                let params = {
                    type: "enterprise12345",
                    // orglevel:"1"
                }
                selectBaseCodeTree(params).then(res => {
                    this.data = res.data.data
                    let list = [];
                    for (const i in this.data) {
                        let item = {
                            value: this.data[i].codeid,
                            label: this.data[i].comment,
                        }
                        if (this.data[i].basecodeforms.length > 0) {
                            item.children = this.getLevels(this.data[i].basecodeforms)
                        }
                        list.push(item)
                    }
                    this.data = list
                    this.subjecttype1 = this.data//涉及主体类别和类型

                });
            },
            dateChangeE(date) {
                this.caseprocesform.casedate = date;
            },
            caseChange() {
                if (this.caseprocesform.applealtocase == "1") {
                    this.registercase = true
                } else {
                    this.registercase = false
                    this.caseprocesform.caseno = ""
                    this.caseprocesform.casedate = ""
                    this.caseprocesform.casereason = ""
                }
            },

        },
        created() {
            this.getInfringementtype1();
            this.getInfringingmarks1();
            this.getMediationmethods1();
            this.getNoobligation1();
            this.getSubjecttype1();
            this.getBaseInfo();

            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);

        }
    };
</script>
<style lang="scss" scoped>
    .approval {
        .btn {
            text-align: center;
            margin-top: 15px;
        }
        .label {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }
        .item_col {
            padding: 5px 0 0 0;
            /* border-bottom: 1px solid #e8e8e8; */
        }
        .el-form .el-row {
            margin: 0;
        }
    }
</style>
