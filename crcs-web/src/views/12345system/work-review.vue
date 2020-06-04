<!--工单审核-->
<template>
    <section class="work_review" v-loading="loading" id="whole" element-loading-text="拼命加载中">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                @tab-click="tabClick"
                v-model="tabAction"
                v-show="!loading"
        >
            <el-tab-pane label="基本信息" name="basicInfo">
                <div style="text-align: right;" :class="{'fixed':scrollTop > height}">
                    <el-button type="primary" @click="save" :loading="submitLoading">保存</el-button>
                    <el-button type="primary" @click="trigger('tabBy')" v-if="formType == 'commommodule'">处理反馈
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')" v-if="formType != 'commommodule' ">
                        受理
                    </el-button>
                    <!--<el-button type="primary" @click="triggerTab('tabRegister')" v-if="formType != 'commommodule' && formType === 'comeconomicmodule'">-->
                        <!--立案-->
                    <!--</el-button>-->
                    <el-button type="primary" @click="triggerTab('tabAssignment')"
                               v-if="user.aicPermissions.subordinate&&unitList.length>0">分派下级
                    </el-button>
                    <el-button
                            v-if="user.aicPermissions.dealdepartment&&deptList.length>0"
                            type="primary" @click="triggerTab('tabDepartment')">分派部门
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabNotAcceptance')"
                               v-if="formType != 'commommodule'  && user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                        不受理
                    </el-button>
                    <!--<el-button type="primary" @click="triggerTab('tabNotRegister')"-->
                               <!--v-if="formType != 'commommodule' && formType === 'comeconomicmodule' && user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">-->
                        <!--不立案-->
                    <!--</el-button>-->
                    <el-button type="primary" @click="toOperating('back')">退回</el-button>
                </div>
                <el-collapse v-model="activeNames" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="orderInfo">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion" v-model="customerinfo">
                                <el-col :span="12">
                                    <em>1.</em> 定单信息
                                </el-col>
                                <el-col
                                        :span="10"
                                        style="color: #48576a;text-align: right"
                                >
                                    <!--定单号：{{tasklist.tserialnum}}-->
                                    表单号：{{tasklist.serialnum}}
                                </el-col>
                            </el-row>
                        </div>
                        <el-form :model="seats" label-width="200px" ref="seats">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="接听坐席：" class="bt">
                                        <el-input v-model="customerinfo.seatsname" placeholder="请输入"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="登记时间：" class="bt">
                                        <el-date-picker
                                                v-model="customerinfo.seatsregistrationtime"
                                                type="datetime"
                                                class="input-text"
                                                placeholder="选择时间"
                                        ></el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="性别：" class="bt">
                                        <el-select
                                                v-model="customerinfo.seatssex"
                                                placeholder="请选择性别"
                                                class="input-text"
                                        >
                                            <el-option label="男" value="1"></el-option>
                                            <el-option label="女" value="2"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="语种：" class="bt">
                                        <el-select
                                                v-model="customerinfo.seatslanguage"
                                                placeholder="请选择语种"
                                                class="input-text"
                                        >
                                            <el-option label="普通话" value="普通话"></el-option>
                                            <el-option label="粤语" value="粤语"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    <em>2.</em>
                                    {{formTypeStr}}
                                </el-col>
                                <el-col
                                        :span="10"
                                        style="color: #48576a;text-align: right"
                                ><!--表单号：{{tasklist.serialnum}}-->
                                </el-col>
                            </el-row>
                        </div>
                        <!-- 消费投诉 -->
                        <complaintmoduleForm @checkdata="save" ref="complaintOne" :complaintform="complaintform" v-if="complaintform"/>
                        <!-- 咨询 -->
                        <consultationForm @checkdata="save" ref="commomOne" :consultationform="commomform" v-if="commomform"/>
                        <!-- 经济违法 -->
                        <economyform @checkdata="save" ref="economyOne" :economyform="economyform" v-if="economyform"/>
                    </el-collapse-item>
                    <el-collapse-item name="officeInfo" v-if="formType != 'commommodule'">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion title_accordion">
                                <el-col :span="12">
                                    <em>3.</em> 处办要求
                                </el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                            </el-row>
                        </div>
                        <el-form :model="handledemand" label-width="220px" ref="handledemand">
                            <el-row>
                                <!--                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">-->
                                <!--                                    <el-col :span="8" class="label">初步意见应反馈时限：</el-col>-->
                                <!--                                    <el-col :span="16">{{formatDate(handledemand.opiniontimesurplus)}}</el-col>-->
                                <!--                                </el-col>-->
                                <!--                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">-->
                                <!--                                    <el-col :span="8" class="label">初步意见反馈时间：</el-col>-->
                                <!--                                    <el-col :span="16">{{formatDate(handledemand.opiniontime)}}</el-col>-->
                                <!--                                </el-col>-->
                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col"
                                        v-if="formType != 'commommodule'">
                                    <el-col :span="8" class="label">应受理时限：</el-col>
                                    <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>
                                </el-col>
                                <!--                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType != 'commommodule'">-->
                                <!--                                    <el-col :span="8" class="label">受理时间：</el-col>-->
                                <!--                                    <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>-->
                                <!--                                </el-col>-->
                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col"
                                        v-if="formType == 'comeconomicmodule'">
                                    <el-col :span="8" class="label">应立案时限：</el-col>
                                    <el-col :span="16">{{formatDate(handledemand.caseputtime)}}</el-col>
                                </el-col>
                                <!--                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType == 'comeconomicmodule'">-->
                                <!--                                    <el-col :span="8" class="label">立案时间：</el-col>-->
                                <!--                                    <el-col :span="16">{{formatDate(handledemand.caseputtimesurplus)}}</el-col>-->
                                <!--                                </el-col>-->
                                <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col"
                                        v-if="handledemand.resulttime != '' || handledemand.resulttime != null">
                                    <el-col :span="8" class="label">应反馈时间：</el-col>
                                    <el-col :span="16">{{formatDate(handledemand.resulttime)}}</el-col>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 处理反馈 --><!--tabActionName-->
            <el-tab-pane label="处理反馈" name="tabBy" v-if="tabAction==='tabBy'">
                <el-form
                        :model="approvalForm"
                        label-width="200px"
                        ref="approvalForm"
                        :rules="approvalFormRules"
                        class="text_bold"
                        label-suffix="："
                >

                    <el-collapse v-model="registrationActiveNames" class="accordion">
                        <el-collapse-item name="4">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6">
                                        处理意见
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form :model="approvalForm" :rules="approvalFormRules" ref="commommoduleSystem"
                                     label-width="180px">
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="处理意见: " prop="resolution">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入处理意见（3000字内）"
                                                    v-model="approvalForm.resolution"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="12" :md="8" :lg="24">
                                        <el-form-item label="咨询相关附件：" class="bt" style="float: left">
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
                                                <el-button size="small" type="success" @click="submitUpload">确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                    <el-col class="centered">
                                        <el-button type="primary" @click="submitApproval" :loading="submitLoading">确定
                                        </el-button>
                                        <el-button @click="cancelTab">取消</el-button>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                    </el-collapse>
                </el-form>
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
                    <tracking :data="opinionList"/>
                </el-collapse>
            </el-tab-pane>
            <el-tab-pane name="operating" label="退回" v-if="operating==='back'" class="operating">
                <el-form
                        :model="backForm"
                        ref="backForm"
                        label-width="200px"
                        label-suffix="："
                        :rules="backFormRules"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="16" :lg="16">
                            <el-form-item label="退回原因" class="form_bt" prop="reason">
                                <el-select v-model="backForm.reason" placeholder="请选择退回原因" @change="reasonChange()">
                                    <el-option label="缺失要素" value="缺失要素"></el-option>
                                    <el-option label="表述不清" value="表述不清"></el-option>
                                    <el-option label="类型有误" value="类型有误"></el-option>
                                    <el-option label="地址不详" value="地址不详"></el-option>
                                    <el-option label="已有知识" value="已有知识"></el-option>
                                    <el-option label="重复受理" value="重复受理"></el-option>
                                    <el-option label="市民撤诉" value="市民撤诉"></el-option>
                                    <el-option label="不属本职" value="不属本职"></el-option>
                                    <el-option label="其他原因" value="其他原因"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="16">
                            <el-form-item label="退回描述" class="form_bt" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="backForm.opinion"
                                        placeholder="请输入退回描述（1000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="24">
                            <el-form-item label="咨询相关附件：" class="bt" style="float: left">
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
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitbackForm" :loading="operatingLoading">退回</el-button>
                            <el-button @click="resetOperating">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 受理 -->
            <el-tab-pane label="受理" name="tabAcceptance" v-if="tabAction==='tabAcceptance'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否受理" prop="isAccep">
                                <div style="line-height: 35px">受理</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="受理方式" prop="mode">
                                <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px;">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAcceptance" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 不受理 -->
            <el-tab-pane label="不受理" name="tabNotAcceptance" v-if="tabAction==='tabNotAcceptance'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否受理" prop="isAccep">
                                <div style="line-height: 35px">不受理</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="不受理原因" prop="reason">
                                <el-select v-model="acceptForm.reason" placeholder="请选择不受理原因" @change="reasonChange2">
                                    <el-option label="交付单位不正确" value="交付单位不正确"></el-option>
                                    <el-option label="可通过查阅知识库解决" value="可通过查阅知识库解决"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="不受理内容" prop="opinion">
                                <el-input type="textarea" v-model="acceptForm.opinion" placeholder="请输入不受理内容（1000字内）"
                                          :rows="5"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitNotAcceptance" :loading="submitLoading">确定
                            </el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 立案 -->
            <el-tab-pane label="立案" name="tabRegister" v-if="tabAction==='tabRegister'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否立案" prop="isAccep">
                                <div style="line-height: 35px">是</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="立案方式" prop="mode">
                                <el-select v-model="acceptForm.mode" placeholder="请选择" style="width: 200px;">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAcceptance" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 不立案 -->
            <el-tab-pane label="不立案" name="tabNotRegister" v-if="tabAction==='tabNotRegister'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否立案" prop="isAccep">
                                <div style="line-height: 35px">不立案</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="不立案原因" prop="reason">
                                <el-select v-model="acceptForm.reason" placeholder="请选择不受理原因" @change="reasonChange2">
                                    <el-option label="交付单位不正确" value="交付单位不正确"></el-option>
                                    <el-option label="可通过查阅知识库解决" value="可通过查阅知识库解决"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="不立案内容" prop="opinion">
                                <el-input type="textarea" v-model="acceptForm.opinion" placeholder="请输入不立案内容（1000字内）"
                                          :rows="5"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitNotAcceptance" :loading="submitLoading">确定
                            </el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabAction==='tabAssignment'" style="min-height : 600px">
                <el-form :model="assignmentForm" label-width="200px" ref="assignmentForm" label-suffix="："
                         :rules="assignmentFormRules" class="text_bold">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="成员单位" prop="unit">
                                <el-select v-model="assignmentForm.unit" filterable placeholder="请选择成员单位"
                                           style="width:100%">
                                    <el-option
                                            v-for="item in unitList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送部门" class="form_bt">
                                <el-select v-model="assignmentForm.ortherDept" multiple placeholder="请选择抄送部门"
                                           style="width:100%" @change="orgPermission()" filterable>
                                    <el-option
                                            v-for="item in deptList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.orgunitID"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="分派意见" prop="opinion">
                                <el-input type="textarea" :rows="5" placeholder="请输入分派意见（3000字内）"
                                          v-model="assignmentForm.opinion"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="短信"  prop="message" v-if="user.primaryKey=='991804e6-2595-474d-b345-e93aab3729c2'">
                                <Cascader
                                        :options="messageoptions"
                                        v-model="checkList"
                                >
                                </Cascader>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAssignment" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 分派部门 -->
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabAction==='tabDepartment'" style="min-height : 600px">
                <el-form
                        :model="departmentForm"
                        label-width="200px"
                        ref="departmentForm"
                        label-suffix="："
                        :rules="departmentFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="主导部门" prop="leading">
                                <el-select
                                        v-model="departmentForm.leading"
                                        placeholder="请选择部门"
                                        style="width:100%"
                                        @change="orgPermissions('2')"
                                        filterable
                                >
                                    <el-option
                                            v-for="item in deptList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.orgunitID"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送部门" prop="ccs">
                                <el-select
                                        v-model="departmentForm.ccs"
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        multiple
                                        @change="orgPermissions('3')"
                                        ref="dCcsSelect"
                                        filterable
                                >
                                    <el-option
                                            v-for="item in filterDepartmentList()"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.orgunitID"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="分派意见" prop="opinion">
                                <el-input
                                        type="textarea"
                                        :rows="5"
                                        placeholder="请输入分派意见（3000字内）"
                                        v-model="departmentForm.opinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="短信"  prop="BMmessage" v-if="user.primaryKey=='991804e6-2595-474d-b345-e93aab3729c2'">
                                <Cascader
                                        :options="BMmessageoptions"
                                        v-model="BMcheckList"
                                >
                                </Cascader>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDepartment" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
        </el-tabs>

    </section>
</template>

<script>
    import {myMixin} from "../../assets/mixin/mixin"
    import {
        selectBusinessBaseInfo, saveBmfwBaseInfo, next,
        findUsersByOrgAndPermission,sendMessage,getSMSPerson
    } from "../../api/api";
    import {
        nextWork, findOrgUnitByRegionId, findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission, getMaterialLists
    } from "../../api/convenient12345";

    import ComplaintmoduleForm from "./components/ComplaintmoduleForm";
    import Economyform from "./components/Economyform";
    import ConsultationForm from "./components/ConsultationForm";
    import Tracking from "../../components/Tracking";
    import {byIdGetSuperiors, getArrayByStringId} from "../../utils/form-util";
    import Cascader from "../../components/cascader"

    export default {
        mixins: [myMixin],
        name: "WorkReview",
        components: {
            ComplaintmoduleForm,
            Economyform,
            ConsultationForm,
            Tracking,
            Cascader
        },
        data() {
            return {
                // 材料列表
                fileLists: [],
                fileListsAccept: [],
                materialList: [],
                requestMap: {
                    caseguid: ""
                },
                tabActionName: "tabMainContent",
                registrationActiveNames: ['1', '2', '3', '4', '5'],
                approvalForm: {
                    isPass: undefined,
                    resolution: "",
                    leader: "",
                },
                /*办理跟踪*/
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "businessInfo",
                    "processTracking",
                    "processing"
                ],
                tortclassifycodeid1: [],//经济--侵权分类
                businesstypecodeid1: [],//经济--业务分类
                illegalrulescodeid1: [],//经济--涉嫌违法行为
                problemtypecodeid1: [],//经济--涉及问题分类
                objectcategorycodeid1: [],//经济--客体类别
                providetypecodeid1: [],//消费--类型
                complaintstypecodeid1: [],//消费--涉及问题分类
                objclassifycodeid1: [],//消费--涉及客体分类
                entitytypecodeid1: [],//咨询--企业类型
                // complaintstypecodeid1: [],//咨询--涉及问题分类
                user: JSON.parse(localStorage.getItem("user")),
                operating: "",
                operatingLoading: false,
                formType: "", //表单类型：economyform:经济违法，complaintmodule:消费投诉。
                formTypeStr: "",
                submitLoading: false,
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
                preliminaryForm: {
                    opinion: "收悉，请留意进一步回复。",
                },
                preliminaryFormRules: {
                    opinion: [
                        {required: true, message: "请输入处理意见", trigger: "blur"},
                        {max: 3000, message: "不能超过3000字符", trigger: "blur"}
                    ]
                },
                backForm: {
                    opinion: "",
                },
                backFormRules: {
                    reason: [
                        {required: true, message: "请选择退回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入退回描述", trigger: "blur"},
                        {max: 1000, message: "不能超过1000字符", trigger: "blur"}
                    ]
                },
                tabAction: "basicInfo",
                loading: false,
                dept: [
                    {
                        value: "consent",
                        label: "企管科"
                    },
                    {
                        value: "reject",
                        label: "消保科"
                    },
                    {
                        value: "transfer",
                        label: "广告科"
                    },
                    {
                        value: "endFlow",
                        label: "结束流程"
                    }
                ],
                activeName: "word",
                wordList: [],
                imageList: [],
                videoList: [],
                otherList: [],
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4
                },
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: "",
                nextStepForm: {},
                //待办表单
                tasklist: {
                    id: "",
                    caseguid: "",
                    focus: "",
                    serialnum: "",
                    tserialnum: "",
                    rqsttitle: "",
                    accordtype: "",
                    labelname: "",
                    assigndate: "",
                    expiredate: "",
                    resulttimemin: "",
                    systemsource: "",
                    issignin: "",
                    signinname: "",
                    signinuserid: "",
                    signindeptid: "",
                    signdate: "",
                    updatedate: ""
                },

                //定单信息表
                customerinfo: {
                    id: "",
                    caseguid: "",
                    seatsname: "",
                    seatsregistrationtime: "",
                    seatssex: "",
                    seatslanguage: "",
                    updatedate: "",
                    focus: "",
                    teti: "",
                    serialnum: "",
                    contractnum: "",
                    tserialnum: "",
                    rqsttitle: "",
                    accordtype: "",
                    labelname: "",
                    createdate: "",
                    resulttimemin: "",
                    status: ""
                },

                commomform: {
                    id: "",
                    caseguid: "",
                    formtype: "",
                    businesstype: "",
                    name: "",
                    gender: "",
                    rqstnumber: "",
                    linknumber: "",
                    informationOpen: "",
                    address: "",
                    focus: "",
                    appealsource: "",
                    rqsttitle: "",
                    rqstcontent: "",
                    attachfilename: "",
                    reflectiontype: "",
                    worktype: "",
                    sdexpirationdate: "",
                    usertype: "",
                    category: "",
                    businesstypecodeid: null,
                    entitytypecodeid: "",
                    entitytype: "",
                    region: "",
                    complaintstype:"",
                    complaintstypecodeid:null,
                },
                complaintform: {
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
                    businesstypecodeid: null,
                    entitytypecodeid: null,
                    servicetypecodeid: null,
                    complaintstypecodeid: null,
                    worktype: "",
                    providetypecodeid: null,
                    objclassifycodeid: null,
                },
                economyform: {
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
                    complaintpaper: "",
                    isaward: "",
                    attachfilename: "",
                    updatedate: "",
                    recorddate: "",
                    worktype: "",
                    sdexpirationdate: "",
                    expirationdate: "",
                    tortclassify: "",
                    providetypecodeid: null,
                    businesstypecodeid: null,
                    reportprovidelbcodeid: null,
                    illegalrulescodeid: null,
                    region: "",
                    objectcategory: "",
                    objectcategorycodeid: null,
                    problemtypecodeid: null,
                    tortclassifycodeid: null,
                },
                seats: {
                    seatsname: "",
                    seatsregistrationtime: "",
                    seatssex: "",
                    seatslanguage: ""
                },
                handledemand: {
                    id: "",
                    caseguid: "",
                    opiniontimeshould: "",
                    opiniontimesurplus: "",
                    opiniontime: "",
                    accepttimeshould: "",
                    accepttimesurplus: "",
                    accepttime: "",
                    caseputtimeshould: "",
                    caseputtimesurplus: "",
                    caseputtime: "",
                    resulttimeshould: "",
                    resulttimesurplus: "",
                    resulttime: "",
                    opiniontimeminstr: "",
                    accepttimeminstr: "",
                    caseputtimeminstr: "",
                    resulttimeminstr: "",
                    updatedate: ""
                },
                //办理追踪
                opinionList: [],

                mainForm: {},
                awardForm: {},
                opinionForm: {
                    text: "收悉，已转相关科室跟进，请留意进一步回复。"
                },
                assignForm: {},
                special: false,
                checkList: [],
                accept: false,
                notAccepts: false,
                back: false,
                assign: false,
                choose: false,
                activeNames: ["orderInfo", "formContent", "officeInfo", "track"],
                acceptForm: {opinion: "", reason: ""},
                messageoptions:[],
                BMmessageoptions:[],
                checkList:[],
                BMcheckList:[],
                acceptFormRules: {
                    isAccep: [{required: true}],
                    reason: [
                        {required: true, message: "请选择不受理原因", trigger: "change"}
                    ],
                    mode: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"},
                        {max: 1000, message: "不能超过1000字符", trigger: "blur"}
                    ]
                },
                personnelForm: {
                    ccs: []
                },
                departmentForm: {
                    ccs: []
                },
                departmentFormRules: {
                    leading: [{required: true, message: "请选择主导部门"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    // ccs: [{required: true, message: "请选择抄送部门"}]
                },
                assignmentForm: {
                    ortherDept: [],
                },
                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"}
                    ],
                    // ortherDept: [{required: true, message: "请选择其他部门"}],
                },
                unitList: [],
                deptList: [],
                depts: '',
                leaderLists: [],
                units: {
                    unitList: [],
                    deptList: [],
                },
                newCompanyLists: [],
                nodeStr: undefined,
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

            download(url) {
                window.location.href = `${process.env.Download_URL}` + url;
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
                this.requestMap.caseguid = this.commomform.caseguid;
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

            submitPreliminaryForm() {
                // 初步答复提交
                this.$refs.preliminaryForm.validate(valid => {
                    if (valid) {
                        this.$confirm("请确认无误提交?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            this.operatingLoading = true;
                            let data;
                            if ("complaintmodule" === this.formType) {
                                if (this.complaintform.providetypecodeid != null && this.complaintform.providetypecodeid != "") {
                                    this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[
                                    this.complaintform.providetypecodeid.length - 1
                                        ].toString();
                                }
                                if (this.complaintform.objclassifycodeid != null && this.complaintform.objclassifycodeid != "") {
                                    this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[
                                    this.complaintform.objclassifycodeid.length - 1
                                        ].toString();
                                }
                                if (this.complaintform.complaintstypecodeid != null && this.complaintform.complaintstypecodeid != "") {
                                    this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[
                                    this.complaintform.complaintstypecodeid.length - 1
                                        ].toString();
                                }
                                data = this.complaintform;
                            } else if ("commommodule" === this.formType) {
                                data = this.commomform;
                            } else if ("comeconomicmodule" === this.formType) {
                                if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                                    this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                                    this.economyform.objectcategorycodeid.length - 1
                                        ].toString();
                                }
                                if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                                    this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                                    this.economyform.problemtypecodeid.length - 1
                                        ].toString();
                                }
                                if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                                    this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                                    this.economyform.illegalrulescodeid.length - 1
                                        ].toString();
                                }
                                if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                                    this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                                    this.economyform.businesstypecodeid.length - 1
                                        ].toString();
                                }
                                if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                                    this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                                    this.economyform.tortclassifycodeid.length - 1
                                        ].toString();
                                }
                                data = this.economyform;
                            }
                            let params = {
                                user: JSON.parse(localStorage.getItem("user")),
                                node: this.nodeStr,
                                formType: this.formType,
                                data,
                                nextNodeId: "interimReply",
                                opinion: this.preliminaryForm.opinion
                            };
                            next(params)
                                .then(data => {
                                    if (data.code === 0) {
                                        this.$message({
                                            message: "提交成功！",
                                            type: "success",
                                            duration: "1000"
                                        });
                                        callback: action => {
                                            let params = {
                                                id: this.$route.query.id,
                                                caseguid: this.$route.query.caseguid
                                            };
                                            this.$router.push({
                                                path: "/wddb/12345db/slbslfpth",
                                                query: params
                                            });
                                        }
                                    } else {
                                        this.$alert("操作失败！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "error"
                                        });
                                    }
                                    this.operatingLoading = false;
                                })
                                .catch(data => {
                                    this.$alert("操作失败！", "提示", {
                                        confirmButtonText: "确定",
                                        type: "error"
                                    });
                                    this.operatingLoading = false;
                                });
                        });
                    }
                });
            },
            submitbackForm() {
                //退回提交
                this.$refs.backForm.validate(valid => {
                    let data;
                    if ("complaintmodule" === this.formType) {
                        data = this.complaintform;
                    } else if ("commommodule" === this.formType) {
                        data = this.commomform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = this.economyform;
                    }
                    if (valid) {
                        this.$confirm("请确认无误提交?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            let params;
                            //转换数组
                            for (let key in data) {
                                if (data[key] instanceof Array) {
                                    if (data[key].length > 0) {
                                        data[key] = data[key].pop();
                                    } else {
                                        data[key] = null;
                                    }
                                }
                            }
                            // if (this.user.regionID == "440681") {
                            if (this.user.aicPermissions.bmfwDispatch.sysMenuFiled == "bmfwDispatch") {
                                params = {
                                    user: this.user,
                                    node: this.nodeStr,
                                    formType: this.formType,
                                    data,
                                    nextNodeId: "backForward",
                                    opinion: this.backForm.opinion,
                                    backreason: this.backForm.reason,
                                    isEnd: '1',
                                };
                            } else {
                                params = {
                                    user: this.user,
                                    node: this.nodeStr,
                                    formType: this.formType,
                                    data,
                                    nextNodeId: "backForward",
                                    opinion: this.backForm.opinion,
                                    backreason: this.backForm.reason,
                                    deptid: this.backForm.leader
                                };
                            }
                            next(params)
                                .then(data => {
                                    if (data.code === 0) {
                                        this.$alert("退回成功！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "success",
                                            callback: action => {
                                                if (window.opener) {
                                                    window.opener.location.reload(); //刷新父窗口
                                                }
                                                setInterval(function () {
                                                    window.close()
                                                }, 500);
                                                // this.$router.push("/wddb/12345db");
                                            }
                                        });
                                    }
                                })
                                .catch(data => {
                                    this.$alert("操作失败！", "提示", {
                                        confirmButtonText: "确定",
                                        type: "error"
                                    });
                                });
                        });
                    }
                });
            },
            toOperating(value) {
                if(value === 'back') {
                    this.operating = value;
                    this.tabAction = "operating";
                    this.delayPersonnel();
                }
            },
            resetOperating() {
                //取消
                this.operating = "";
                this.tabAction = "basicInfo";
            },
            submitApproval() {
                //提交处理反馈结果   if(id instanceof Array){
                let data;
                if ("commommodule" === this.formType) {
                    if (this.commomform != null) {
                        if (this.commomform.entitytypecodeid instanceof Array) {
                            this.commomform.entitytypecodeid =
                                this.commomform.entitytypecodeid[this.commomform.entitytypecodeid.length - 1].toString()
                        }
                        if (this.commomform.complaintstypecodeid instanceof Array) {
                            this.commomform.complaintstypecodeid =
                                this.commomform.complaintstypecodeid[this.commomform.complaintstypecodeid.length - 1].toString()
                        }
                        data = this.commomform;
                    } else {
                        if (this.consultationform.entitytype != null && this.consultationform.entitytype != "") {
                            this.consultationform.entitytype = this.Entitytype1
                        }
                        if (this.consultationform.entitytypecodeid instanceof Array) {
                            this.consultationform.entitytypecodeid =
                                this.consultationform.entitytypecodeid[this.consultationform.entitytypecodeid.length - 1].toString()
                        }
                        data = this.consultationform;
                    }
                }
                let validation = false;
                this.$refs["commommoduleSystem"].validate(valid => (validation = valid));
                if (!validation) return;

            

                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: 'end',
                        data: data,
                        opinion: this.approvalForm.resolution,
                        caseprocesform: this.caseprocesform,
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                }, 500);
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
            //咨询类处理反馈按钮
            trigger(value) {

                let validation ;
                if(this.formType === "commommodule"){
                    if(this.$refs.commomOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }else if(this.formType === "complaintmodule"){
                    if(this.$refs.complaintOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }else if(this.formType === "comeconomicmodule"){
                    if(this.$refs.economyOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }
                console.log(validation)
                if(validation){
                    this.tabAction = value;
                }

            },
            triggerTab(value) {
                let validation ;
                if(this.formType === "commommodule"){
                    if(this.$refs.commomOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }else if(this.formType === "complaintmodule"){
                    if(this.$refs.complaintOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }else if(this.formType === "comeconomicmodule"){
                    if(this.$refs.economyOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }

                if(validation){
                this.tabAction = value;
                if ("tabAcceptance" === value) {
                    this.acceptForm.isAccep = "受理";
                } else if ("tabNotAcceptance" === value) {
                    this.acceptForm.isAccep = "不受理";
                }else if ("tabRegister" === value) {
                    this.acceptForm.isAccep = "立案";
                }else if ("tabNotRegister" === value) {
                    this.acceptForm.isAccep = "不立案";
                }
                // if ("commommodule" != this.formType){
                this.assignmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                this.departmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                // this.personnelForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                // }
                }
            },
            cancelTab() {
                this.tabAction = "basicInfo";
            },
            //保存
            save() {
                let validation ;
                if(this.formType === "commommodule"){
                    console.log(123)

                    if(this.$refs.commomOne.checkData()){
                         validation = true;
                    }else{
                         validation = false;
                    }
                }else if(this.formType === "complaintmodule"){
                    if(this.$refs.complaintOne.checkData()){
                         validation = true;
                    }else{
                         validation = false;
                    }
                }else if(this.formType === "comeconomicmodule"){
                    if(this.$refs.economyOne.checkData()){
                        validation = true;
                    }else{
                        validation = false;
                    }
                }

                if (validation){

                    this.$confirm("请确认无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        this.submitLoading = true;
                        let handledemand = this.handledemand;
                        for (let key in handledemand) {
                            if (handledemand[key] instanceof Date) {
                                handledemand[key] = handledemand[key].getTime();
                            }
                        }
                        let params = {
                            handledemand,
                            customerinfo: this.customerinfo
                        };
                        params.handledemand = params.handledemand;
                        if (this.complaintform) {
                            if (this.complaintform.providetypecodeid != null && this.complaintform.providetypecodeid != "") {
                                this.providetypecodeid1 = this.complaintform.providetypecodeid
                                this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[
                                this.complaintform.providetypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.complaintform.objclassifycodeid != null && this.complaintform.objclassifycodeid != "") {
                                this.objclassifycodeid1 = this.complaintform.objclassifycodeid
                                this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[
                                this.complaintform.objclassifycodeid.length - 1
                                    ].toString();
                            }
                            if (this.complaintform.complaintstypecodeid != null && this.complaintform.complaintstypecodeid != "") {
                                this.complaintstypecodeid1 = this.complaintform.complaintstypecodeid
                                this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[
                                this.complaintform.complaintstypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.complaintform.businesstypecodeid != null && this.complaintform.businesstypecodeid != "") {
                                this.businesstypecodeid1 = this.complaintform.businesstypecodeid
                                this.complaintform.businesstypecodeid = this.complaintform.businesstypecodeid[
                                this.complaintform.businesstypecodeid.length - 1
                                    ].toString();
                            }
                            params.complaintform = this.complaintform;
                        } else if (this.economyform) {
                            if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                                this.objclassifycodeid1 = this.economyform.objectcategorycodeid
                                this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                                this.economyform.objectcategorycodeid.length - 1
                                    ].toString();
                            }
                            if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                                this.problemtypecodeid1 = this.economyform.problemtypecodeid
                                this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                                this.economyform.problemtypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                                this.illegalrulescodeid1 = this.economyform.illegalrulescodeid
                                this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                                this.economyform.illegalrulescodeid.length - 1
                                    ].toString();
                            }
                            if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                                this.businesstypecodeid1 = this.economyform.businesstypecodeid
                                this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                                this.economyform.businesstypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                                this.tortclassifycodeid1 = this.economyform.tortclassifycodeid
                                this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                                this.economyform.tortclassifycodeid.length - 1
                                    ].toString();
                            }
                            params.economyform = this.economyform;
                        } else if (this.commomform) {
                            if (this.commomform.entitytypecodeid != null && this.commomform.entitytypecodeid != "") {
                                this.entitytypecodeid1 = this.commomform.entitytypecodeid
                                this.commomform.entitytypecodeid = this.commomform.entitytypecodeid[
                                this.commomform.entitytypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.commomform.complaintstypecodeid != null && this.commomform.complaintstypecodeid != "") {
                                this.complaintstypecodeid1 = this.commomform.complaintstypecodeid
                                this.commomform.complaintstypecodeid = this.commomform.complaintstypecodeid[
                                this.commomform.complaintstypecodeid.length - 1
                                    ].toString();
                            }
                            params.commomform = this.commomform;
                        }
                        saveBmfwBaseInfo(params).then(data => {
                            this.submitLoading = false;
                            this.$message({
                                message: "保存成功！",
                                type: "success",
                                duration: "1000"
                            });
                            if (data.data.type === "complaintform") {
                                this.complaintform = data.data.complaintform
                                if (this.complaintform.providetypecodeid != null && this.complaintform.providetypecodeid != "") {
                                    this.complaintform.providetypecodeid = this.providetypecodeid1
                                }
                                if (this.complaintform.complaintstypecodeid != null && this.complaintform.complaintstypecodeid != "") {
                                    this.complaintform.complaintstypecodeid = this.complaintstypecodeid1
                                }
                                if (this.complaintform.objclassifycodeid != null && this.complaintform.objclassifycodeid != "") {
                                    this.complaintform.objclassifycodeid = this.objclassifycodeid1
                                }
                            }

                            if (data.data.type === "commomform") {
                                if (this.commomform.entitytypecodeid != null && this.commomform.entitytypecodeid != "") {
                                    this.commomform.entitytypecodeid = this.entitytypecodeid1
                                }
                                if (this.commomform.complaintstypecodeid != null && this.commomform.complaintstypecodeid != "") {
                                    this.commomform.complaintstypecodeid = this.complaintstypecodeid1
                                }
                            }

                            if (data.data.type === "economyform") {
                                if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                                    this.economyform.objectcategorycodeid = this.objclassifycodeid1

                                }
                                if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                                    this.economyform.tortclassifycodeid = this.tortclassifycodeid1
                                }
                                if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                                    this.economyform.businesstypecodeid = this.businesstypecodeid1
                                }
                                if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                                    this.economyform.illegalrulescodeid = this.illegalrulescodeid1
                                }
                                if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                                    this.economyform.problemtypecodeid = this.problemtypecodeid1
                                }
                            }
                        });


                    });
            }
            },
            //跳转:分派--确认
            jump() {
                this.$router.push({
                    path: "//wddb/tsldb/gdsl",
                    query: {
                        id: tasklist.id
                    }
                });
            },

            tabClick(visible) {
                if (visible.name !== "operating") {
                    this.resetOperating();
                }
            },
            toAccept(value) {
                this.tabAction = "2";
                if (value == "0") {
                    this.assign = true;
                    this.accept = false;
                    this.notAccepts = false;
                    this.back = false;
                    this.flows = false;
                } else if (value == "1") {
                    this.accept = true;
                    this.notAccepts = false;
                    this.back = false;
                    this.flows = false;
                } else if (value == "2") {
                    this.notAccepts = true;
                    this.accept = false;
                    this.back = false;
                    this.flows = false;
                } else {
                    this.back = true;
                    this.accept = false;
                    this.notAccepts = false;
                    this.flows = false;
                }
            },
            //====================================================================================
            findUnit() {
                //获取下级单位列表
                findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
                    if (data.data) {
                        this.unitList = data.data;
                    }
                });
            },
            orgPermission() {
                let unit = this.assignmentForm.ortherDept[this.assignmentForm.ortherDept.length - 1]
                let para = {
                    deptid: unit,
                    permission: "dealdepartment"
                };
                if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
                    findUsersByDeptIdAndPermission(para).then(res => {
                        if (res.data == null) {
                            this.$alert("该部门没有查看该业务权限的人员，请先添加权限!", "提示", {
                                confirmButtonText: "确定"
                            });
                            this.assignmentForm.ortherDept.splice(this.assignmentForm.ortherDept.indexOf(this.assignmentForm.ortherDept[this.assignmentForm.ortherDept.length - 1]))
                        }
                    });
                }
            },
            unitChange() {
                //获取部门列表
                findOrgDeptByOrgId({orgId: this.user.orgID}).then(res => {
                    this.deptList = res.data;
                    console.log(this.deptList)
                });
            },
            submitNotAcceptance() {
                //不受理
                let validation = false;
                this.$refs.acceptForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let params;
                    let data;
                    if ("complaintmodule" === this.formType) {
                        data = this.complaintform;

                    } else {
                        data = this.economyform;
                    }
                    //深度拷贝，防止更改economyform原先的表单值
                    data = JSON.parse(JSON.stringify(data));
                    //转换数组
                    for (let key in data) {
                        if (data[key] instanceof Array) {
                            if (data[key].length > 0) {
                                data[key] = data[key].pop();
                            } else {
                                data[key] = null;
                            }
                        }
                    }
                    if (this.user.regionID == "440681") {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data,
                            opinion: this.acceptForm.opinion,
                            noAcceptreason: this.acceptForm.reason,
                            isEnd: '1',
                        };
                    } else {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "noAccept",
                            data,
                            opinion: this.acceptForm.opinion,
                            noAcceptreason: this.acceptForm.reason,
                        };
                    }
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close()
                                }, 500);
                                this.$router.push("/wddb/12345db");
                            } else {
                                this.errorAlert();
                            }
                            this.submitLoading = false;
                        })
                        .catch(error => {
                            console.log(error)
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },
            submitAcceptance() {
                //受理
                let validation = false;
                this.$refs.acceptForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (this.complaintform.providetypecodeid != null) (
                            this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[this.complaintform.providetypecodeid.length - 1].toString()
                        )
                        if (this.complaintform.objclassifycodeid != null) (
                            this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[this.complaintform.objclassifycodeid.length - 1].toString()
                        )
                        if (this.complaintform.complaintstypecodeid != null) (
                            this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[this.complaintform.complaintstypecodeid.length - 1].toString()
                        )
                        if (this.complaintform.businesstypecodeid != null) (
                            this.complaintform.businesstypecodeid = this.complaintform.businesstypecodeid[this.complaintform.businesstypecodeid.length - 1].toString()
                        )

                        data = this.complaintform;

                    } else if ("comeconomicmodule" === this.formType) {
                        if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                            this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                            this.economyform.objectcategorycodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                            this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                            this.economyform.problemtypecodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                            this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                            this.economyform.illegalrulescodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                            this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                            this.economyform.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                            this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                            this.economyform.tortclassifycodeid.length - 1
                                ].toString();
                        }
                        data = this.economyform;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "accept",
                        data: data,
                        opinion: this.acceptForm.mode,
                        handledemand: this.handledemand,
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                let params = {
                                    id: this.$route.query.id,
                                    caseguid: this.$route.query.caseguid,
                                    ismain: "1",
                                };
                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                if ("comeconomicmodule" === this.formType) {
                                    this.$router.push({
                                        path: "/listDetail/lacl",
                                        query: params
                                    });
                                } else {
                                    this.$router.push({
                                        path: "/listDetail/clfpysth",
                                        query: params
                                    });
                                }
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
            //分派下级
            submitAssignment() {
                //分派下级
                let validation = false;
                this.$refs.assignmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (this.commomform.entitytypecodeid != null && this.commomform.entitytypecodeid != "") {
                                this.entitytypecodeid1 = this.commomform.entitytypecodeid
                                this.commomform.entitytypecodeid = this.commomform.entitytypecodeid[
                                this.commomform.entitytypecodeid.length - 1
                                    ].toString();
                            }
                            if (this.commomform.complaintstypecodeid != null && this.commomform.complaintstypecodeid != "") {
                                this.complaintstypecodeid1 = this.commomform.complaintstypecodeid
                                this.commomform.complaintstypecodeid = this.commomform.complaintstypecodeid[
                                this.commomform.complaintstypecodeid.length - 1
                                    ].toString();
                            }
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (this.complaintform.providetypecodeid != null) (
                            this.complaintform.providetypecodeid = this.complaintform.providetypecodeid[this.complaintform.providetypecodeid.length - 1].toString()
                        )
                        if (this.complaintform.objclassifycodeid != null) (
                            this.complaintform.objclassifycodeid = this.complaintform.objclassifycodeid[this.complaintform.objclassifycodeid.length - 1].toString()
                        )
                        if (this.complaintform.complaintstypecodeid != null) (
                            this.complaintform.complaintstypecodeid = this.complaintform.complaintstypecodeid[this.complaintform.complaintstypecodeid.length - 1].toString()
                        )
                        if (this.complaintform.businesstypecodeid != null) (
                            this.complaintform.businesstypecodeid = this.complaintform.businesstypecodeid[this.complaintform.businesstypecodeid.length - 1].toString()
                        )
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                            this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                            this.economyform.objectcategorycodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                            this.economyform.problemtypecodeid = this.economyform.problemtypecodeid[
                            this.economyform.problemtypecodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                            this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                            this.economyform.illegalrulescodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                            this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                            this.economyform.businesstypecodeid.length - 1
                                ].toString();
                        }
                        if (this.economyform.tortclassifycodeid != null && this.economyform.tortclassifycodeid != "") {
                            this.economyform.tortclassifycodeid = this.economyform.tortclassifycodeid[
                            this.economyform.tortclassifycodeid.length - 1
                                ].toString();
                        }
                        data = this.economyform;
                    }
                    console.log(data)
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        caseprocesform: this.caseprocesform,
                        orgid: this.assignmentForm.unit,
                        ortherDept: this.assignmentForm.ortherDept,
                        isnewassign: '1'
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                //调用发短信接口
                                let paras={
                                    phonelist:this.checkList,
                                    tserialnum:this.tasklist.serialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                console.log(paras)
                                sendMessage(paras).then(res=>{
                                    if(res.code===0){
                                        this.$message({
                                            message:"提交成功！",
                                            type:"success",
                                            duration:"1000"
                                        });
                                        if(window.opener){
                                            window.opener.location.reload();//刷新父窗口
                                        }
                                        setInterval(function(){
                                            window.close();
                                        },500);
                                        this.$router.push("/wddb/12345db")
                                    }else{
                                        this.$alert("工单分派成功但短信发送超时！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "error"
                                        });
                                    }
                                })

                            }
                            // else {
                            //     this.errorAlert();
                            // }
                            this.submitLoading = false;
                        })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },
            submitDepartment() {
                //分派部门
                let validation = false;
                this.$refs.departmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    //深度拷贝，防止更改complaintform原先的表单值
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            data = JSON.parse(JSON.stringify(this.commomform))
                            // data = this.commomform;
                        } else {
                            data = JSON.parse(JSON.stringify(this.consultationform))
                            // data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        data = JSON.parse(JSON.stringify(this.complaintform))
                        // data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        data = JSON.parse(JSON.stringify(this.economyform))
                        // data = this.economyform;
                    }
                    //转换数组
                    for (let key in data) {
                        if (data[key] instanceof Array) {
                            if (data[key].length > 0) {
                                data[key] = data[key].pop();
                            } else {
                                data[key] = null;
                            }
                        }
                    }
                    console.log(data)
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerDept",
                        data: data,
                        opinion: this.departmentForm.opinion,
                        deptid: this.departmentForm.leading,
                        ortherDept: this.departmentForm.ccs,
                        isnewassign: '1'
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                //调用发短信接口
                                let paras={
                                    phonelist:this.BMcheckList,
                                    tserialnum:this.tasklist.serialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                console.log(paras)
                                sendMessage(paras).then(res=>{
                                    if(res.code===0){
                                        this.$message({
                                            message: '提交成功！',
                                            type: 'success',
                                            duration: '1000'
                                        });
                                        if (window.opener) {
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close()
                                        }, 500);
                                    }else{
                                        this.errorAlert();
                                    }
                                })


                                // this.$router.push("/wddb/12345db");
                            }
                            // else {
                            //     this.errorAlert();
                            // }
                            this.submitLoading = false;
                        })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },
            //根据当前用户权限查询人员列表
            delayPersonnel() {
                if (this.user.aicPermissions.subordinate != undefined) {
                    let para = {
                        permission: "subordinate",
                        orgid: "6ba12fbe-0100-1000-e000-03fec0a85893"
                    };
                    findUsersByOrgAndPermission(para).then(res => {
                        this.leaderLists = res.data.data;
                    });
                } else if (this.user.aicPermissions.dealdepartment != undefined) {
                    let para = {
                        permission: "dealdepartment",
                        orgid: this.user.orgID
                    };
                    findUsersByOrgAndPermission(para).then(res => {
                        this.leaderLists = res.data.data;
                    });
                } else {
                    let para = {
                        permission: "dealpersonnel",
                        deptid: this.user.unitID
                    };
                    findUsersByDeptIdAndPermission(para).then(res => {
                        this.leaderLists = res.data.data;
                    });
                }
            },
            //=============================================================================
            getCompanyList() {
                let user = localStorage.getItem("user");
                let users = JSON.parse(user);
                let para = {
                    regionId: users.regionID
                };
                findOrgUnitByRegionId(para).then(res => {
                    if (res.code === 0) {
                        for (let i in res.data) {
                            this.units.unitList[i] = {
                                value: "",
                                label: ""
                            };
                            this.units.unitList[i].value = res.data[i].primaryKey;
                            this.units.unitList[i].label = res.data[i].name;
                        }
                    }
                });
            },
            filterDepartmentList() {
                return this.deptList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            //校验单位或部门是否存在对应权限的人员
            orgPermissions(value) {
                if ('2' == value) {
                    let para = {
                        deptid: this.departmentForm.leading,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.leading != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert("该部门没有处理该业务权限的人员，请先添加权限!", "提示", {
                                    confirmButtonText: "确定"
                                });
                                this.departmentForm.leading = ""
                            }
                        });
                    }
                } else {
                    let unit = this.departmentForm.ccs[this.departmentForm.ccs.length - 1]
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.ccs.length > 0 && unit != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert("该部门没有查看该业务权限的人员，请先添加权限!", "提示", {
                                    confirmButtonText: "确定"
                                });
                                this.departmentForm.ccs.splice(this.departmentForm.ccs.indexOf(this.departmentForm.ccs.length[this.departmentForm.ccs.length - 1]))
                            }
                        });
                    }
                }
            },
            getDepartmentList() {
                let params = {
                    orgId: JSON.parse(localStorage.getItem("user")).orgID
                };
                findOrgDeptByOrgId(params).then(res => {
                    if (res.code === 0) {
                        for (let i in res.data) {
                            this.units.deptList[i] = {
                                value: "",
                                label: ""
                            };
                            this.units.deptList[i].value = res.data[i].orgunitID;
                            this.units.deptList[i].label = res.data[i].name;
                        }
                    }
                });
            },
            reasonChange() {
                if ('缺失要素' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。"
                } else if ('表述不清' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。"
                } else if ('类型有误' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请修改表单类型为：xxx。"
                } else if ('地址不详' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。"
                } else if ('已有知识' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。"
                } else if ('重复受理' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，此工单与另一工单（表单号：xxx）属重复受理。"
                } else if ('市民撤诉' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。"
                } else if ('不属本职' == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。"
                } else {
                    this.backForm.opinion = "其它原因"
                }
            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            reasonChange2() {
                if ('交付单位不正确' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "交付单位不正确"
                } else if ('可通过查阅知识库解决' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "可通过查阅知识库解决"
                }
            },
            //获取短信列表人
            SearchMessagePerson(){
                console.log("000")
                let params={}
                getSMSPerson(params).then(res=>{
                    console.log("123123")
                    console.log(res)
                    this.messageoptions=res
                    this.BMmessageoptions=res
                })
            }
        },
        created() {
            this.loading = true;
            let para = {
                id: this.$route.query.id,
                caseguid: this.$route.query.caseguid
            };
            selectBusinessBaseInfo(para).then(res => {
                // this.askdepthandleList = res.data.askdepthandleList
                this.nodeStr = res.data.tasklist.nextnodeid;
                // if (this.nodeStr !== "begin") {
                //     //节点与当前对应不上
                //     this.$router.push("/wddb/12345db");
                //     return;
                // }
                this.tasklist = res.data.tasklist;
                this.economyform = res.data.economyform;
                this.handledemand = res.data.handledemand;
                this.customerinfo = res.data.customerinfo;
                this.opinionList = res.data.opinionList;
                this.complaintform = res.data.complaintform;
                this.commomform = res.data.commomform;
                if (this.complaintform != null) {
                    this.formType = "complaintmodule";
                    this.formTypeStr = this.complaintform.formtype;
                } else if (this.economyform != null) {
                    this.formType = "comeconomicmodule";
                    this.formTypeStr = this.economyform.formtype;
                } else if (this.commomform != null) {
                    this.formType = "commommodule";
                    this.formTypeStr = this.commomform.formtype;
                }
                
                this.loading = false;
            });
            this.findUnit();
            this.unitChange();
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            this.SearchMessagePerson()
        }
    };
</script>

<style>
    .accordion .el-collapse-item__header .el-collapse-item__header__arrow {
        position: absolute;
        top: 15px;
        right: 10px;
        z-index: 10;
        transform: rotate(90deg);
    }

    .el-collapse-item.is-active
    > .el-collapse-item__header
    .el-collapse-item__header__arrow {
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

        // .el-form-item {
        // margin-bottom: 15px;
        // }

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

        .operating .el-form .el-row {
            margin: 0;
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }

        .label {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }
    }

    .input-text {
        width: 100%;
    }
</style>
