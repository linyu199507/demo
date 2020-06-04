<template>
    <section class="work_review" v-loading="loading" id="whole" element-loading-text="拼命加载中">
        <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick"
                 style="width: 96%;margin: 15px auto;">
            <el-tab-pane label="基本信息" name="first">

                <div style="text-align: right;margin-bottom:20px" :class="{'fixed':scrollTop > height}">
                    <el-button type="primary" @click="save" :loading="submitLoading">保存</el-button>
                    <el-button type="primary" @click="triggerTab('tabAcceptance')">
                        立案
                    </el-button>
<!--                    <el-button type="primary" @click="triggerTab('tabFiling')">-->
<!--                        立案处理-->
<!--                    </el-button>-->
                    <el-button type="primary" @click="triggerTab('tabAssignment')"
                               v-if="user.aicPermissions.subordinate&&unitList.length>0">分派下级
                    </el-button>
                    <el-button
                            v-if="user.aicPermissions.dealdepartment&&deptList.length>0"
                            type="primary" @click="triggerTab('tabDepartment')">分派部门
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabNotAcceptance')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                        不立案
                    </el-button>
                    <el-button type="primary" @click="toOperating('back')">退回</el-button>
                </div>

                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="orderInfo">
                        <div slot="title">
                            <!--<el-row class="accordion_title mb10">-->
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    系统信息
                                </el-col>
                                 <el-col :span="10" class="right">工单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>
                        <el-row>
                            <el-form :model="economyform">
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label" >登记编号：</el-col>
                                    <el-col :span="16" class="txtleft">{{economyform.registrationnumber}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">记录类型：</el-col>
                                    <el-col :span="16">{{economyform.formtype}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">登记日期：</el-col>
                                    <el-col :span="16"><!--{{economyform.recorddate}}-->
                                        {{$moment(this.economyform.recorddate).format("YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">接收方式：</el-col>
                                    <el-col :span="16">{{economyform.receivemode}}</el-col>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col" style="margin: 8px auto">
                                    <el-col :span="8" class="label">来电号码：</el-col>
                                    <el-col :span="16">{{economyform.rqstnumber}}</el-col>
                                </el-col>
                            </el-form>
                        </el-row>
                    </el-collapse-item>
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion title_accordion">
                                <el-col :span="12">
                                    举报表单
                                    <!--{{formTypeStr}}-->
                                </el-col>
                                <el-col
                                        :span="10"
                                        style="color: #48576a;text-align: right"
                                >表单号：{{tasklist.serialnum}}
                                </el-col>
                            </el-row>
                        </div>
                        <!-- 12315经济违法 -->
                        <economyform @checkdata="save" ref="economyformOnce" :economyform="economyform" v-if="economyform" :tasklist="tasklist"/>

                    </el-collapse-item>

                </el-collapse>
            </el-tab-pane>
            <el-tab-pane name="back" label="退回" v-if="operating==='back'" class="operating">
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
                        <el-col :xs="24" :sm="24" :md="16" :lg="16">
                            <el-form-item label="附件清单" class="form_bt">
                                <el-upload
                                        class="upload-demo"
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple
                                >
                                    <el-button size="small" type="primary">选择附件</el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitbackForm" >退回</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 受理 -->
            <el-tab-pane label="立案" name="tabAcceptance" v-if="tabAction==='tabAcceptance'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否立案" prop="isAccep">
                                <div style="line-height: 35px;text-align:left">是</div>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="立案方式" prop="mode" >
                                <el-select v-model="acceptForm.mode" placeholder="请选择">
                                    <el-option label="调解" value="调解"></el-option>
                                    <el-option label="查处" value="查处"></el-option>
                                    <el-option label="调查" value="调查"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="详细情况说明" prop="details" >
                               <el-input type="textarea" v-model="acceptForm.details"  :rows="5"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitAcceptance" :loading="submitLoading">确定</el-button>
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 不受理 -->
            <el-tab-pane label="不立案" name="tabNotAcceptance" v-if="tabAction==='tabNotAcceptance'">
                <el-form :model="acceptForm" label-width="200px" ref="acceptForm" :rules="acceptFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col>
                            <el-form-item label="是否立案" prop="isAccep">
                                <div style="line-height: 35px">不立案</div>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="不立案原因" prop="reason">
                                <el-select v-model="acceptForm.reason" placeholder="请选择不立案原因" style="width:100%" @change="reasonChange1()">
                                    <el-option label="不属实" value="11"></el-option>
                                    <el-option label="其他" value="12"></el-option>
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
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 立案处理 -->
            <el-tab-pane label="立案处理" name="tabFiling" v-if="tabAction==='tabFiling'">
                <el-collapse v-model="collapseFilingValues" class="accordion">
                    <!-- 涉及主体信息 -->
                    <el-collapse-item name="involving">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">涉及主体信息</el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformInvolving"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体" prop="subjectname">
                                        <el-input placeholder="请输入" v-model="caseprocesform.subjectname"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="主体类别和类型" prop="subjecttypecodeid">
                                        <el-cascader filterable
                                                     placeholder="请选择涉及主体类别和类型"
                                                     :options="subjecttype1"
                                                     v-model="caseprocesform.subjecttypecodeid"
                                                     @change="handleChangesubjecttype1"
                                                     :show-all-levels="false"
                                                     class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="经营地址" prop="businessaddress" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.businessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营地址" prop="semakbusinessaddress" class="bt">
                                        <el-input placeholder="请输入核查经营地址"
                                                  v-model="caseprocesform.semakbusinessaddress"
                                                  class="input-text">
                                            <el-select v-model="economyform.region" slot="prepend" placeholder="请选择"
                                                       style="width:100px;color: black;">
                                                <el-option label="禅城区" value="禅城区"></el-option>
                                                <el-option label="南海区" value="南海区"></el-option>
                                                <el-option label="顺德区" value="顺德区"></el-option>
                                                <el-option label="高明区" value="高明区"></el-option>
                                                <el-option label="三水区" value="三水区"></el-option>
                                            </el-select>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营名称" prop="semaknama" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.semaknama"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <!-- 初查情况 -->
                    <el-collapse-item name="initial">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">初查情况</el-col>
                                <!-- <el-col :span="10" class="right">订单号：{{ordernumber}}</el-col> -->
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformInitial"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="处理期限" prop="limitdate">
                                        <el-input
                                                placeholder="请输入处理期限"
                                                v-model.number="caseprocesform.limitdate"
                                                class="input-text"
                                        >
                                            <template slot="append">工作日</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col/>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="属实情况" prop="isverified">
                                        <el-select
                                                placeholder="请选择"
                                                v-model="caseprocesform.isverified"
                                                style="width:100%"
                                        >
                                            <el-option label="属实" value="属实"></el-option>
                                            <el-option label="不属实" value="不属实"></el-option>
                                            <el-option label="待定" value="待定"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col>
                                    <el-form-item
                                            :label="caseprocesform.isverified==='不属实'?'不属实原因':'调查情况'"
                                            prop="verifiedopinion"
                                    >
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 4, maxRows: 6}"
                                                placeholder="请输入"
                                                v-model="caseprocesform.verifiedopinion"
                                        ></el-input>
                                    </el-form-item>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="16">
                                        <el-form-item label="举报相关附件">
                                            <el-upload
                                                    :auto-upload="false"
                                                    :file-list="fileListsEco"
                                                    ref="upload"
                                                    :data="this.requestMapEco"
                                                    :on-preview="previewFile"
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
                                                        @click="submitUploadEco('uploadEconmy')"
                                                >确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <!-- 案件信息 -->
                    <el-collapse-item name="case">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">案件信息</el-col>
                                <!-- <el-col :span="10" class="right">订单号：{{ordernumber}}</el-col> -->
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                class="text_bold"
                                ref="caseprocesformCase"
                                label-suffix="："
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="是否立案" prop="isregister">
                                        <el-select
                                                placeholder="请选择"
                                                v-model="caseprocesform.isregister"
                                                style="width:100%"
                                        >
                                            <el-option label="是" value="是"></el-option>
                                            <el-option label="否" value="否"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <div class="centered">
                    <el-button type="primary" @click="submitFiling" :loading="submitLoading">确定</el-button>
                    <el-button @click="handleClick">取消</el-button>
                </div>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabAction==='tabAssignment'" style="min-height : 600px">
                <el-form :model="assignmentForm" label-width="200px" ref="assignmentForm" label-suffix="："
                         :rules="assignmentFormRules" class="text_bold">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="成员单位" prop="unit">
                                <el-select v-model="assignmentForm.unit" filterable placeholder="请选择成员单位" style="width:100%">
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
                            <el-button @click="handleClick">取消</el-button>
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
                            <el-form-item label="抄送部门">
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
                            <el-button @click="handleClick">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 流程跟踪 -->
             <el-tab-pane label="流程跟踪" name="processing">
                    <el-collapse v-model="collapseValues" class="accordion" >
                        <el-collapse-item name="economyProcess">
                            <div slot="title" @click.stop="">
                                <el-row class="el-accordion">
                                    <el-col :span="6" style="color:rgb(64, 169, 255)">
                                        <em>1.</em>
                                        流程跟踪
                                    </el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;">
                                    </el-col>
                                </el-row>
                            </div>
                            <el-form
                                    label-width="180px"
                            >
                                <el-row style="padding:20px 0">
                                    <Tracking :data="opinionList" />
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                    </el-collapse>
            </el-tab-pane>
        </el-tabs>
        <!--</div>-->
        <!--</el-card>-->
    </section>
</template>
<script>
    import {myMixin} from '../../assets/mixin/mixin'
    import {
        nextWork, findOrgUnitByRegionId, findOrgDeptByOrgId,
        findUsersByDeptIdAndPermission,searchBaseInfo,saveBmfwBaseInfo,
        findUsersByOrgAndPermission,getMaterialLists,selectBaseCodeTree
    } from "../../api/convenient12315";

    import Economyform from "./components/Economyform";
    import EconomyInfo from "./components/EconomyInfo";
    import Tracking from "../../components/Tracking";
    import {sendMessage, getSMSPerson} from "../../api/api"
    import Cascader from "../../components/cascader";

    export default {
        mixins:[myMixin],
        name: "WorkReview",
        components: {
            Economyform,
            EconomyInfo,
            Tracking,
            Cascader
        },
        data() {
            return {
                subjecttype1:[],
                fileList: [],
                fileListsEco: [],
                fileListsAccept: [],
                requestMapEco: {
                    caseguid: ""
                },
                providetypecodeid1:[],//市场主体类型
                industrynamecodeid1:[],//行业分类
                objectcategorycodeid1:[],//客体类别
                reportprovidelbcodeid1:[],//举报问题类别
                businesstypecodeid1:[],//业务分类
                illegalrulescodeid1:[],//涉嫌违法
                user: JSON.parse(localStorage.getItem("user")),
                caseprocesform: {},
                collapseFilingValues: ["involving", "initial", "case"],
                formType: "", //表单类型：economyform:经济违法，complaintmodule:消费投诉。
                formTypeStr: "",
                unitList: [],
                deptList: [],
                depts: '',
                leaderLists: [],
                acceptForm: {
                    isAccep: "",
                    opinion: "",
                    department: "",
                    content: "",
                    reason: "",
                    details:"经审查，符合受理条件，决定立案。"
                },
                operating: "",
                loading: false,
                submitLoading: false,
                activeName: "first",
                tabAction: "basicInfo",
                assignmentForm: {
                    unit: undefined,
                    ortherDept: [],
                },
                messageoptions:[],
                BMmessageoptions:[],
                checkList:[],
                BMcheckList:[],
                caseprocesformRules: {
                    isregister: [{required: true, message: "请选择是否立案"}],
                    verifiedopinion: [
                        {required: true, message: "请输入", trigger: "blur"}
                    ],
                    isverified: [
                        {required: true, message: "请输入属实情况", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入涉及主体", trigger: "blur"}
                    ],
                    semaknama: [
                        {required: true, message: "请输入核查经营名称", trigger: "blur"}
                    ],
                    // cksubjectaddress: [
                    //   { required: true, message: "请输入核查经营地址", trigger: "blur" }
                    // ],
                    semakbusinessaddress: [{required: true, message: "请输入核查经营地址"}],
                    businessaddress: [
                        {required: true, message: "请输入经营地址", trigger: "blur"}
                    ],
                    subjecttypecodeid: [
                        {
                            type: "array",
                            required: true,
                            message: "请选择主体类别和类型"
                        }
                    ],
                    limitdate: [
                        {type: "integer", message: "处理期限只能是整数", trigger: "blur"},
                        {
                            type: "integer",
                            max: 100,
                            min: 1,
                            message: "不能大于100与小于1",
                            trigger: "blur"
                        }
                    ]
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
                },
                acceptFormRules: {
                    isAccep: [{required: true}],
                    reason: [
                        {required: true, message: "请选择不受理原因", trigger: "change"}
                    ],
                    mode: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"},
                        {max: 500, message: "不能超过500字符", trigger: "blur"}
                    ],
                    details: [{required: true, message: "请输入内容", trigger: "blur"},
                        {max: 500, message: "不能超过500字符", trigger: "blur"}
                    ]
                },
                //办理追踪
                opinionList: [],
                //待办表单
                tasklist: {
                    id: "",
                    caseguid: "",
                    focus: "",
                    seriaopinionlnum: "",
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
                //消費
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
                dealListDetail: {
                    registerNum: 0
                },
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "businessInfo",
                    "economyProcess",
                    "processTracking"
                ],
                orderInfo: {
                    registerNum: "",
                    recordType: "",
                    registerDate: "",
                    acceptType: ""
                },
                complainantInfo: {
                    name: "",
                    tel: 0,
                    gender: "",
                    address: "",
                    documentType: "",
                    documentNum: "",
                    providerType: "",
                    inageGroupOptions: "",
                    email: "",
                    nationality: "",
                    Nation: "",
                    identity: "",
                    otherContactInfo: ""
                },
                objectOfComplaint: {
                    name: "",
                    address: "",
                    marketSubjectType: "",
                    industryClassification: "",
                    tel: "",
                    identificationNumber: "",
                    Contacts: "",
                    email: ""
                },
                businessInfo: {
                    objectClass: "",
                    goodsName: "",
                    brand: "",
                    salesWay: "",
                    payMoney: "",
                    orderNum: "",
                    date: "",
                    contentOfAppeal: "",
                    questionCategory: "",
                    productName: "",
                    approvalNumber: "",
                    productBatchNumber: "",
                    productSpecification: "",
                    salesEnterprise: "",
                    productValidity: "",
                    keyWord: "",
                    questionType: "",
                    producer: "",
                    sell: ""
                },
                questionTypeoOptions: [],
                questionCategoryOptions: [],
                acceptTypeOptions: [],
                recordTypeOptions: [],
                questionCategoryOptions: [],
                salesWayOptions: [],
                objectClassOptions: [],
                industryClassificationOptions: [],
                nationalityOptions: [],
                marketSubjectTypeOptions: [],
                inageGroupOptions: [],
                documentTypeOptions: [],
                identityOptions: [],
                providerTypeOptions: [],
                nationality: [],
                NationOptions: [],
                genderOptions: [
                    {
                        label: "女",
                        value: "weman"
                    },
                    {
                        label: "男",
                        value: "man"
                    }
                ],
                registerDateOptions: {},
                nodeStr:"",
                backForm: {
                    opinion:"",
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
            };
        },
        methods: {
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
                            this.fileListsEco.push(fileInfo);
                        }
                    }
                });
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);

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
                    ".xlsx"
                ];
                if (arrays.indexOf(lastName) === -1 || isLt === false) {
                    this.$alert(
                        "不支持上传该格式的文件,请上传docx，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                        "提示",
                        {
                            confirmButtonText: "确定"
                        }
                    );
                    return false;
                }
            },
            submitUploadEco(value) {
                this.requestMapEco.caseguid = this.economyform.caseguid;
                this.$refs.upload.submit();
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
            //涉及主体类别和类型下拉框级联数据
            getSubjecttype1() {
                let params = {
                    type: "enterprise12345"
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
                    this.subjecttype1 = this.data; //涉及主体类别和类型
                });
            },
            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1]);
                this.caseprocesform.subjecttype = label;
                this.caseprocesform.subjecttypecodeid = array;
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
            handleClick(tab, event) {
                this.tabAction = "";
                this.activeName = "first"
            },
            unitChange() {
                //获取部门列表
                findOrgDeptByOrgId({orgId: this.user.orgID}).then(res => {
                    this.deptList = res.data;
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
                }else {
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
            toOperating(value) {
                this.activeName = value;
                this.operating = value;
                this.delayPersonnel();
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
                    //深度拷贝，防止更改economyform原先的表单值
                    let datas=JSON.parse(JSON.stringify(this.economyform))
                    //转换数组
                    for (let key in datas) {
                        if (datas[key] instanceof Array) {
                            if (datas[key].length > 0) {
                                datas[key] = datas[key].pop();
                            } else {
                                datas[key] = null;
                            }
                        }
                    }
                    // datas.expirationdate=Date.parse(datas.expirationdate)

                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "accept",
                        data:datas,
                        opinion: this.acceptForm.details
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
                                    duration:'1000'
                                });
                                this.$router.push({
                                    path: "/listDetail/12315jblacl",
                                    query: params
                                });
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
            submitFiling() {
                //提交立案
                let validation = false;
                this.$refs.caseprocesformInitial.validate(valid => (validation = valid));
                this.$refs.caseprocesformInvolving.validate(
                    valid => (validation = validation && valid)
                );
                if (this.$refs.caseprocesformCase) {
                    this.$refs.caseprocesformCase.validate(
                        valid => (validation = validation && valid)
                    );
                }
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    this.caseprocesform.caseguid = this.economyform.caseguid;
                    //深度拷贝，防止更改economyform原先的表单值
                    let datas = JSON.parse(JSON.stringify(this.caseprocesform));
                    //转换数组
                    for (let key in datas) {
                        if (datas[key] instanceof Array) {
                            if (datas[key].length > 0) {
                                datas[key] = datas[key].pop();
                            } else {
                                datas[key] = null;
                            }
                        }
                    }
                    //转换数组
                    for (let key in this.economyform) {
                        if (this.economyform[key] instanceof Array) {
                            if (this.economyform[key].length > 0) {
                                this.economyform[key] = this.economyform[key].pop();
                            } else {
                                this.economyform[key] = null;
                            }
                        }
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId:
                            this.caseprocesform.isregister === "是"
                                ? "registerCase"
                                : "noRegisterCase",
                        data: this.economyform,
                        opinion: this.caseprocesform.verifiedopinion,
                        caseprocesform: datas
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                let params = {
                                    id: this.$route.query.id,
                                    caseguid: this.$route.query.caseguid,
                                    ismain: "1"
                                };
                                if (this.caseprocesform.isregister === "是") {
                                    this.$router.push({
                                        path: "/listDetail/12315jbgdfk",
                                        query: params
                                    });
                                } else {
                                    this.$router.push({
                                        path: "/wddb/12315db"
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
                    //深度拷贝，防止更改economyform原先的表单值
                    let data=JSON.parse(JSON.stringify(this.economyform))
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
                            data:this.economyform,
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
            triggerTab(value) {

                if(this.$refs["economyformOnce"].checkData()){
                    this.activeName = value;
                    this.tabAction = value;
                    if ("tabAcceptance" === value) {
                        this.acceptForm.isAccep = "立案";
                    } else if ("tabNotAcceptance" === value) {
                        this.acceptForm.isAccep = "不立案";
                    }
                    this.assignmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                    this.departmentForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                    this.personnelForm.opinion = "区局接收案件日期：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 应立案日期：" + this.$moment(this.economyform.feedbacktime).format("YYYY-MM-DD HH:mm:ss")
                        + " , 最终反馈期限：" + this.$moment(this.economyform.todealwithtime).format("YYYY-MM-DD HH:mm:ss");
                }

                // this.assignmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                    // this.departmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                    // this.personnelForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
            },
            // checkData(){
            //
            // },
            save() {

                if(this.$refs["economyformOnce"].checkData()){
                    this.$confirm("请确认无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        this.submitLoading = true;
                        let handledemand = this.handledemand;
                        // for (let key in handledemand) {
                        //     if (handledemand[key] instanceof Date) {
                        //         handledemand[key] = handledemand[key].getTime();
                        //     }
                        // }
                        let params = {
                            handledemand,
                            customerinfo: this.customerinfo
                        };
                        params.handledemand = params.handledemand;

                        // providetypecodeid:[],//市场主体类型
                        //     industrynamecodeid:[],//行业分类
                        //     objectcategorycodeid:[],//客体类别
                        //     reportprovidelbcodeid:[],//举报问题类别
                        if (this.economyform) {
                            if (this.economyform.providetypecodeid != null && this.economyform.providetypecodeid != "") {
                                this.providetypecodeid1 = this.economyform.providetypecodeid
                                this.economyform.providetypecodeid = this.economyform.providetypecodeid[
                                this.economyform.providetypecodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.providetypecodeid = ""}
                            if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                                this.objectcategorycodeid1 = this.economyform.objectcategorycodeid
                                this.economyform.objectcategorycodeid = this.economyform.objectcategorycodeid[
                                this.economyform.objectcategorycodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.objectcategorycodeid = ""}
                            if (this.economyform.industrynamecodeid != null && this.economyform.industrynamecodeid != "") {
                                this.industrynamecodeid1 = this.economyform.industrynamecodeid
                                this.economyform.industrynamecodeid = this.economyform.industrynamecodeid[
                                this.economyform.industrynamecodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.industrynamecodeid = ""}
                            if (this.economyform.reportprovidelbcodeid != null && this.economyform.reportprovidelbcodeid != "") {
                                this.reportprovidelbcodeid1 = this.economyform.reportprovidelbcodeid
                                this.economyform.reportprovidelbcodeid = this.economyform.reportprovidelbcodeid[
                                this.economyform.reportprovidelbcodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.reportprovidelbcodeid = ""}
                            if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                                this.businesstypecodeid1 = this.economyform.businesstypecodeid
                                this.economyform.businesstypecodeid = this.economyform.businesstypecodeid[
                                this.economyform.businesstypecodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.businesstypecodeid = ""}
                            if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                                this.illegalrulescodeid1 = this.economyform.illegalrulescodeid
                                this.economyform.illegalrulescodeid = this.economyform.illegalrulescodeid[
                                this.economyform.illegalrulescodeid.length - 1
                                    ].toString();
                            }else{ this.economyform.illegalrulescodeid = ""}
                            if (this.economyform.businesstypecodeidnew != null && this.economyform.businesstypecodeidnew != "") {
                                this.businesstypecodeid1 = this.economyform.businesstypecodeidnew
                                this.economyform.businesstypecodeidnew = this.economyform.businesstypecodeidnew[
                                this.economyform.businesstypecodeidnew.length - 1
                                    ].toString();
                            }else{ this.economyform.businesstypecodeidnew = ""}
                            params.economyform = this.economyform;
                        }

                        saveBmfwBaseInfo(params).then(data => {
                            this.submitLoading = false;
                            if (this.economyform.providetypecodeid != null && this.economyform.providetypecodeid != "") {
                                this.economyform.providetypecodeid = this.providetypecodeid1
                            }
                            if (this.economyform.objectcategorycodeid != null && this.economyform.objectcategorycodeid != "") {
                                this.economyform.objectcategorycodeid = this.objectcategorycodeid1
                            }
                            if (this.economyform.industrynamecodeid != null && this.economyform.industrynamecodeid != "") {
                                this.economyform.industrynamecodeid = this.industrynamecodeid1
                            }
                            if (this.economyform.reportprovidelbcodeid != null && this.economyform.reportprovidelbcodeid != "") {
                                this.economyform.reportprovidelbcodeid = this.reportprovidelbcodeid1
                            }
                            if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                                this.economyform.businesstypecodeid = this.businesstypecodeid1
                            }
                            if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                                this.economyform.illegalrulescodeid = this.illegalrulescodeid1
                            }
                            this.$message({
                                message: '保存成功！',
                                type: 'success',
                                duration:'1000'
                            });
                        });
                    });
                }

            },
            findUnit() {
                //获取下级单位列表
                findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
                    if (data.data) {
                        this.unitList = data.data;
                    }
                });
            },
            //分派下级
            submitAssignment() {
                let validation = false;
                this.$refs.assignmentForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    //深度拷贝，防止更改economyform原先的表单值
                    let data=JSON.parse(JSON.stringify(this.economyform))
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        orgid: this.assignmentForm.unit,
                        ortherDept: this.assignmentForm.ortherDept,
                        isnewassign:"1"
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
                    //深度拷贝，防止更改economyform原先的表单值
                    let data=JSON.parse(JSON.stringify(this.economyform))
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerDept",
                        data: data,
                        opinion: this.departmentForm.opinion,
                        deptid: this.departmentForm.leading,
                        ortherDept: this.departmentForm.ccs,
                        isnewassign:"1"
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
                                        if(window.opener){
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close()
                                        },500);
                                    }else{
                                        this.$alert("工单分派成功但短信发送超时！", "提示", {
                                            confirmButtonText: "确定",
                                            type: "error"
                                        });
                                    }
                                })

                                // this.$router.push("/wddb/12315db");
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
            submitbackForm() {
                //退回提交
                this.$refs.backForm.validate(valid => {
                    if (valid) {
                        this.$confirm("请确认无误提交?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            //深度拷贝，防止更改economyform原先的表单值
                            let data=JSON.parse(JSON.stringify(this.economyform))
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
                            let params;
                            // if (this.user.regionID == "440681") {
                            if (this.user.aicPermissions.ywclDispatch.sysMenuFiled == "ywclDispatch"){
                                params = {
                                    user: this.user,
                                    node: this.nodeStr,
                                    formType: this.formType,
                                    data: data,
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
                            nextWork(params)
                                .then(data => {
                                    if (data.code === 0) {
                                        this.$message({
                                            message: '提交成功！',
                                            type: 'success',
                                            duration: '1000'
                                        });
                                        if(window.opener){
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close()
                                        },500);
                                        // this.$router.push("/wddb/12315db");
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
            //抄送部门不能出现与选择的主导部门一样
            filterDepartmentList() {
                return this.deptList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
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
            reasonChange(){
                if('缺失要素' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。"
                }else if ('表述不清' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。"
                }else if ('类型有误' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，请修改表单类型为：xxx。"
                }else if ('地址不详' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。"
                }else if ('已有知识' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。"
                }else if ('重复受理' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，此工单与另一工单（表单号：xxx）属重复受理。"
                }else if ('市民撤诉' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。"
                }else if ('不属本职' == this.backForm.reason){
                    this.backForm.opinion = "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。"
                }else{
                    this.backForm.opinion = "其它原因"
                }
            },
            reasonChange1() {
                if ('11' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "不属实"
                } else if ('12' == this.acceptForm.reason) {
                    this.acceptForm.opinion = "其它"
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
                caseguid: this.$route.query.caseguid,
                systemsource: "03"
            };
            searchBaseInfo(para).then(res => {
                this.nodeStr = res.data.tasklist.nextnodeid;
                // if (this.nodeStr !== "begin") {
                //     //节点与当前对应不上
                //     this.$router.push("/wddb/12315db");
                //     return;
                // }
                this.tasklist = res.data.tasklist;
                this.economyform = res.data.economyform;
                this.handledemand = res.data.handledemand;
                this.customerinfo = res.data.customerinfo;
                this.opinionList = res.data.opinionList;
                this.complaintform = res.data.complaintform;
                this.commomform = res.data.commomform;
                this.economyform.appealsource = '互联网络';
                this.economyform.usertype='个人';
                this.economyform.providetypecodeid = null;
                this.economyform.illegalrulescodeid = null;
                this.economyform.rportprovidelbcodeid = null;
                this.economyform.industryname = null;
                this.economyform.objectcategory = null;
                this.economyform.reportprovidelb = null;
                this.economyform.providetype = null;
                this.economyform.reportprovidelb = null;
                if (this.complaintform != null) {
                    this.formType = "complaintmodule";
                    this.formTypeStr = this.complaintform.formtype;
                } else if (this.economyform != null) {
                    this.formType = "comeconomicmodule";
                    this.formTypeStr = this.economyform.formtype;
                    this.economyform.rqsttitle = '总局单:'+this.economyform.subjectname+this.economyform.registrationnumber
                }
                this.loading = false;
            });
            this.findUnit();
            this.unitChange();
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            this.getSubjecttype1();
            this.SearchMessagePerson()
        }
    };

</script>
<style lang="scss" scope>
    .el-form-item__content {
        text-align: center;
    }

    .el-date-editor.el-input {
        width: 200px;
    }
    .txtleft {
        text-align: left;
    }
    .el-form-item__content {
        text-align: left;
    }
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
    }

    .label {
        font-size: 13px;
        color: #48576a;
        font-weight: bold;
        text-align: right;
    }

    .el-col {
        border-radius: 4px;
    }

    .inputWidth {
        width: 200px;
        height: 40px;
    }

    .work_review .el-input input {
        width: 100%;
        height: 40px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
</style>
