<!--受理的下一步操作页面->处理反馈、分派、申请延期、退回-->
<template>
    <section class="acceptance-of-next" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div
                        v-if="$route.query.ismain==='1'"
                        style="text-align: right;"
                        :class="{'fixed':scrollTop > height}"
                >
                    <el-button
                            type="primary"
                            size="small"
                            icon="document"
                            @click="exportToWord"
                            :loading="exportLoading"
                            style="margin: 2px; border-radius: 13px"
                    >
                        <template v-if="exportLoading">正在导出...</template>
                        <template v-else>
                            <i class="fa fa-download"></i>
                            Word导出
                        </template>
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabFeedback')">处理反馈</el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                    >分派下级
                    </el-button>
                    <el-button
                            v-if="(user.regionID!='440681' && user.aicPermissions.dealdepartment&&nodeStr!=='moveToLowerDept'&&nodeStr!=='moveToLowerPersonnel'&&departmentList.length>0 && this.tasklist.fromnodeid !=='moveToLowerPersonnel')
                            ||user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button
                            v-if="(user.aicPermissions.dealpersonnel&&nodeStr==='moveToLowerDept'&&nodeStr!=='moveToLowerPersonnel'&&personnelList.length>0)
                            || (nodeStr!=='moveToLowerPersonnel'&& user.regionID=='440681' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0')"
                            type="primary"
                            @click="triggerTab('tabPersonnel')"
                    >分派人员
                    </el-button>
                    <!--                    <el-button type="primary" @click="triggerTab('tabExtension')">申请延期-->
                    <!--                    </el-button>-->
                    <el-button type="primary" @click="triggerTab('tabBack')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">退回
                    </el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">经济违法表</el-col>
                                <!--<el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>-->
                            </el-row>
                        </div>
                        <!-- 12315经济违法 -->
                        <my-economy-info :economyform="economyform" v-if="economyform" :tasklist="tasklist"/>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 流程跟踪 -->
            <el-tab-pane label="流程跟踪" name="processing">
                <el-collapse v-model="collapseValues" class="accordion">
                    <el-collapse-item name="economyProcess">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="6" style="color:rgb(64, 169, 255)">
                                    <em>1.</em>
                                    流程跟踪
                                </el-col>
                                <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="caseprocesform"
                                label-width="180px"
                                ref="caseprocesformA"
                                :rules="caseprocesformRules"
                        >
                            <el-row style="padding:20px 0">
                                <my-tracking-info :data="opinionList"/>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <!-- 处理反馈 -->
            <el-tab-pane label="处理反馈" name="tabFeedback" v-if="tabActionName==='tabFeedback'">
                <el-form
                        :model="caseprocesform"
                        label-width="200px"
                        ref="feedbackForm"
                        :rules="caseprocesformRules"
                        class="text_bold"
                        label-suffix="："
                >
                    <el-collapse
                            v-model="registrationActiveNames"
                            class="accordion"
                            v-if="formType==='comeconomicmodule'"
                    >
                        <el-collapse-item name="1">
                            <div slot="title" @click.stop>
                                <el-row class="el-accordion">
                                    <el-col :span="6">涉及主体信息</el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                                </el-row>
                            </div>
                            <el-form
                                    :model="caseprocesform"
                                    label-width="180px"
                                    ref="caseprocesformA"
                                    :rules="caseprocesformRules"
                            >
                                <el-row style="padding:20px 0">
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体：" prop="subjectname" class="bt mb20">
                                            <el-input
                                                    placeholder="请输入"
                                                    v-model="caseprocesform.subjectname"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体类别和类型：" prop="subjecttypecodeid" class="bt mb20">
                                            <el-cascader
                                                    filterable
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
                                        <el-form-item label="经营地址：" prop="businessaddress" class="bt mb20">
                                            <el-input
                                                    placeholder="请输入经营地址"
                                                    v-model="caseprocesform.businessaddress"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="核查经营地址：" prop="semakbusinessaddress" class="bt mb20">
                                            <el-input
                                                    placeholder="请输入核查经营地址"
                                                    v-model="caseprocesform.semakbusinessaddress"
                                                    class="input-text"
                                            >
                                                <el-select
                                                        v-model="economyform.region"
                                                        slot="prepend"
                                                        placeholder="请选择"
                                                        style="width:100px;color: black;"
                                                >
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
                                        <el-form-item label="核查经营名称：" prop="semaknama" class="bt">
                                            <el-input
                                                    placeholder="请输入核查经营名称"
                                                    v-model="caseprocesform.semaknama"
                                                    class="input-text"
                                            ></el-input>
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
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="2">
                            <div slot="title" @click.stop>
                                <el-row class="el-accordion">
                                    <el-col :span="6">初查情况</el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                                </el-row>
                            </div>
                            <el-form
                                    :model="caseprocesform"
                                    label-width="180px"
                                    ref="caseprocesformB"
                                    :rules="caseprocesformRules"
                            >
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="办结举报类型：" prop="isverified" class="bt">
                                            <el-select
                                                    placeholder="请选择"
                                                    v-model="caseprocesform.isverified"
                                                    class="input-text"
                                            >
                                                <el-option label="属实(立案)" value="属实"></el-option>
                                                <el-option label="属实(不立案)" value="不属实"></el-option>
                                                <!--<el-option label="待定" value="待定"></el-option>-->
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="是否无照经营：" class="bt">
                                            <el-radio-group v-model="caseprocesform.unlicensed"
                                                            style="line-height:36px">
                                                <el-radio label="1">是</el-radio>
                                                <el-radio label="2">否</el-radio>
                                            </el-radio-group>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="没收金额：" class="bt" prop="confiscateAmount">
                                            <el-input v-model.number="caseprocesform.confiscateAmount">
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="罚款金额：" class="bt" prop="penaltyAmount">
                                            <el-input v-model.number="caseprocesform.penaltyAmount">
                                                <template slot="append">元</template>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="销售地行政区划：" class="bt" prop="saleDistrict">
                                            <el-input v-model="caseprocesform.saleDistrict"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="销售地：" class="bt" prop="saleAddr">
                                            <el-input v-model="caseprocesform.saleAddr"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="调查情况:" prop="verifiedopinion" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :autosize="{ minRows: 4, maxRows: 6}"
                                                    placeholder="请输入调查情况(1200字以内)"
                                                    v-model="caseprocesform.verifiedopinion"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="3">
                            <div slot="title" @click.stop>
                                <el-row class="el-accordion">
                                    <el-col :span="12">处理信息</el-col>
                                    <el-col :span="10" style="color: #48576a;text-align: right;"></el-col>
                                </el-row>
                            </div>
                            <el-form
                                    :model="caseprocesform"
                                    label-width="180px"
                                    ref="caseprocesformC"
                                    :rules="caseprocesformRules"
                            >
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="办理部门：" prop="handleDepart" class="bt">
                                            <el-input v-model="caseprocesform.handleDepart"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                                        <!--<el-form-item label="是否销案：" prop="ispursuit" class="bt" v-if="isregisters">-->
                                            <!--<el-select-->
                                                    <!--placeholder="请选择"-->
                                                    <!--v-model="caseprocesform.ispursuit"-->
                                                    <!--class="input-text"-->
                                            <!--&gt;-->
                                                <!--<el-option label="是" value="是"></el-option>-->
                                                <!--<el-option label="否" value="否"></el-option>-->
                                            <!--</el-select>-->
                                        <!--</el-form-item>-->
                                    <!--</el-col>-->
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="经办人：" prop="operator" class="bt" v-if="user.regionId==='440681'">
                                            <el-input v-model="caseprocesform.operator"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="经办人电话：" prop="operatorPhone" class="bt" v-if="user.regionId==='440681'">
                                            <el-input v-model="caseprocesform.operatorPhone"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="经办人意见：" prop="operatorOpinion" class="bt">
                                            <el-input type="textarea" :rows="2"
                                                      v-model="caseprocesform.operatorOpinion"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="处理结果：" prop="handleopinion" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入处理结果（3000字内）"
                                                    v-model="caseprocesform.handleopinion"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                        <el-form-item label="经济相关附件:" class="bt">
                                            <el-upload
                                                    :auto-upload="false"
                                                    :file-list="fileListsEco"
                                                    ref="uploadEconomy"
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
                                                        @click="submitUploadEco('uploadEconomy')"
                                                >确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                    <!-- <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                          <el-form-item label="是否部门领导审核：" class="bt">
                                                              <el-radio-group v-model="caseprocesform.leadershipAudit" style="line-height:36px">
                                                                  <el-radio label="1">是</el-radio>
                                                                  <el-radio label="0">否</el-radio>
                                                              </el-radio-group>
                                                          </el-form-item>
                                    </el-col>-->
                                    <el-col
                                            :xs="24"
                                            :sm="24"
                                            :md="24"
                                            :lg="24"
                                            v-if="caseprocesform.leadershipAudit==='1'"
                                    >
                                        <el-form-item label="送审附言：" prop="postscript" class="bt"
                                                      v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                                            <el-input type="textarea" :rows="2"
                                                      v-model="caseprocesform.postscript"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-row style="margin: 20px 0;">
                            <el-col style="text-align: center;width: 100%;">
                                <el-button type="primary" @click="openRegionAudit" :loading="submitLoading"
                                           v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">确认
                                </el-button>
                                <el-button type="primary" @click="openRegionAudit" :loading="submitLoading"
                                           v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">送区局审批
                                </el-button>
                                <el-button type="primary" @click="openReviewDialog01" :loading="submitLoading"
                                           v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">送领导审核
                                </el-button>
                                <el-button @click="cancelTab">取消</el-button>
                            </el-col>
                        </el-row>
                    </el-collapse>
                </el-form>
                <el-form
                        :model="caseprocesform"
                        label-width="180px"
                        ref="caseprocesformG"
                        label-suffix="："
                        :rules="caseprocesformRules"
                        v-if="formType==='commommodule'"
                >
                    <el-form-item label="处理意见" class="form_bt" prop="handleopinion" style="width: 80%">
                        <el-input
                                type="textarea"
                                :rows="4"
                                placeholder="请输入处理意见（3000字内）"
                                v-model="caseprocesform.handleopinion"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="submitFeedback">送区局处理</el-button>
                        <el-button type="primary" @click="openReviewDialog02">送领导审核</el-button>
                        <el-button @click="cancelTab">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabActionName==='tabAssignment'" style="min-height : 600px">
                <el-form
                        :model="assignmentForm"
                        label-width="200px"
                        ref="assignmentForm"
                        label-suffix="："
                        :rules="assignmentFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="成员单位" prop="unit">
                                <el-select
                                        v-model="assignmentForm.unit"
                                        placeholder="请选择成员单位"
                                        style="width:100%"
                                        @change="orgPermission('1')"
                                        filterable
                                >
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
                            <el-form-item label="抄送部门">
                                <el-select
                                        v-model="assignmentForm.ortherDept"
                                        placeholder="请选择抄送部门"
                                        style="width:100%"
                                        multiple
                                        @change="orgPermission('5')"
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
                                        v-model="assignmentForm.opinion"
                                ></el-input>
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
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabActionName==='tabDepartment'" style="min-height : 600px">
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
                                        @change="orgPermission('2')"
                                        filterable
                                >
                                    <el-option
                                            v-for="item in departmentList"
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
                                        @visible-change="ifMainDepartment"
                                        @change="orgPermission('3')"
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
            <!-- 分派人员 -->
            <el-tab-pane label="分派人员" name="tabPersonnel" v-if="tabActionName==='tabPersonnel'">
                <el-form
                        :model="personnelForm"
                        label-width="200px"
                        ref="personnelForm"
                        label-suffix="："
                        :rules="personnelFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="主要人员" prop="leading">
                                <el-select
                                        v-model="personnelForm.leading"
                                        placeholder="请选择主要人员"
                                        style="width:100%"
                                        @change="filterPCcs"
                                >
                                    <el-option
                                            v-for="item in personnelList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送人员">
                                <el-select
                                        v-model="personnelForm.ccs"
                                        placeholder="请选择抄送人员"
                                        style="width:100%"
                                        multiple
                                        @visible-change="ifMainPersonnel"
                                        ref="pCcsSelect"
                                >
                                    <el-option
                                            v-for="item in filterPersonnelList()"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
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
                                        v-model="personnelForm.opinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitPersonnel" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 申请延期 -->
            <el-tab-pane label="申请延期" name="tabExtension" v-if="tabActionName==='tabExtension'">
                <el-form
                        :model="delayForm"
                        label-width="200px"
                        ref="delayForm"
                        label-suffix="："
                        :rules="delayFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="延时时间单位" prop="unit">
                                <el-select v-model="delayForm.unit" placeholder="请选择延期时间单位" prop="department">
                                    <el-option label="工作时" value="工作时"></el-option>
                                    <el-option label="工作日" value="工作日"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="延期时限" prop="limit">
                                <el-input placeholder="请输入延期时限" v-model.number="delayForm.limit"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="延期原因" prop="reason" style="width: 80%">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入延期原因（1000字内）"
                                        v-model="delayForm.reason"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col>
                            <el-form-item label="延期审批处理人" prop="postpone" class="form_bt">
                                <el-select v-model="delayForm.postpone" placeholder="请选择延期审批处理人">
                                    <el-option
                                            v-for="item in postponeLists"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitDelay" :loading="submitLoading">申请</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <!-- 退回 -->
            <el-tab-pane label="退回" name="tabBack" v-if="tabActionName==='tabBack'">
                <el-form
                        :model="backForm"
                        ref="backForm"
                        label-width="200px"
                        label-suffix="："
                        :rules="backFormRules"
                        class="text_bold"
                >
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="退回原因" prop="reason">
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="退回描述" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="backForm.opinion"
                                        placeholder="请输入退回描述（1000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <!--            <el-col :xs="24" :sm="24" :md="16" :lg="16" v-if="user.regionID != '440681'">-->
                        <!--              <el-form-item label="签收人" prop="leader" class="form_bt">-->
                        <!--                <el-select v-model="backForm.leader" placeholder="请选择签收人">-->
                        <!--                  <el-option-->
                        <!--                    v-for="item in leaderLists"-->
                        <!--                    :key="item.region"-->
                        <!--                    :label="item.name"-->
                        <!--                    :value="item.primaryKey"-->
                        <!--                  ></el-option>-->
                        <!--                </el-select>-->
                        <!--              </el-form-item>-->
                        <!--            </el-col>-->
                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="16">-->
                        <!--<el-form-item label="经济相关附件">-->
                        <!--<el-upload-->
                        <!--:auto-upload="false"-->
                        <!--:file-list="fileListsCom"-->
                        <!--ref="uploadEconmy"-->
                        <!--:data="this.requestMapCom"-->
                        <!--:on-preview="previewFile"-->
                        <!--:on-success="uploadSuccess"-->
                        <!--:before-upload="beforeUpload"-->
                        <!--:multiple="true"-->
                        <!--action="/crcs/filesUpload/upload"-->
                        <!--&gt;-->
                        <!--<div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>-->
                        <!--<el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
                        <!--<el-button-->
                        <!--size="small"-->
                        <!--type="success"-->
                        <!--@click="submitUploadCom('uploadComplain')"-->
                        <!--&gt;确认上传-->
                        <!--</el-button>-->
                        <!--</el-upload>-->
                        <!--</el-form-item>-->
                        <!--</el-col>-->
                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                            <el-form-item label="经济相关附件:" class="bt">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileListsEco"
                                        ref="uploadEconomy"
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
                                            @click="submitUploadEco('uploadEconomy')"
                                    >确认上传
                                    </el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="submitBack" :loading="submitLoading">退回</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-dialog title="送领导审核" :visible.sync="reviewDialogVisible" size="tiny">
                <el-form
                        :model="feedbackForm"
                        label-width="120px"
                        ref="feedbackLeaderForm"
                        label-suffix="："
                        class="text_bold"
                >
                    <el-form-item label="请选择领导" prop="leader" :rules="[{required: true, message: '请选择领导' }]">
                        <el-select v-model="feedbackForm.leader" placeholder="请选择领导">
                            <el-option
                                    :label="`${item.name}（${item.unitName}）`"
                                    :value="item.primaryKey"
                                    v-for="item in leaderList"
                                    :key="item.primaryKey"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="reviewDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitLeader">确定</el-button>
                </div>
            </el-dialog>
            <!-- 送区局处理的弹窗 -->
            <el-dialog title="送区局处理" :visible.sync="dialogRegionAudit" size="tiny">
                <el-form
                        :model="caseform"
                        ref="feedbackLeaderForms"
                        label-suffix="："
                        :rules="caseformRules"
                >
                    <el-form-item label="请选择区局处理人" prop="leaders" class="form_bt">
                        <el-select v-model="caseform.leaders" placeholder="请选择">
                            <el-option
                                    v-for="item in leaderLists"
                                    :key="item.region"
                                    :label="item.name"
                                    :value="item.primaryKey"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogRegionAudit = false">取 消</el-button>
                    <el-button type="primary" @click="submitFeedback2">确 定</el-button>
                </div>
            </el-dialog>
        </el-tabs>
    </section>
</template>

<script>
    import {
        searchBaseInfo,
        nextWork,
        findUsersByPermission,
        findUsersByDeptIdAndPermission,
        findOrgUnitByRegionId,
        findOrgDeptByOrgId,
        findUsersByOrgAndPermission,
        selectBaseCodeTree,
        findUsersByDeptIdAndJurisdiction
    } from "../../api/convenient12345";
    import {getMaterialLists, uploadFile,sendMessage,getSMSPerson} from "../../api/api";
    import EconomyInfo from "./components/EconomyInfo";
    import Tracking from "../../components/Tracking";
    import {byIdGetSuperiors, getArrayByStringId} from "../../utils/form-util";
    import moment from "moment";
    import {myMixin} from "../../assets/mixin/mixin";
    import Cascader from "../../components/cascader"

    export default {
        mixins: [myMixin],
        components: {
            "my-economy-info": EconomyInfo,
            "my-tracking-info": Tracking,
            Cascader
        },
        data() {
            return {
                Providetype: "",
                Industryname: "",
                Objectcategory: "",
                Reportprovidelb: "",
                exportLoading: false,
                isregisters: true, //判断是否销案对应的部分v-if隐藏
                subjecttype1: [], //涉及主体类别和类型下拉框内容
                noobligation1: [], //未履行义务下拉框内容
                mediationmethods1: [], //调解方式下拉框内容
                infringingmarks1: [], //欺诈标志下拉框内容
                infringementtype1: [], //侵权类型下拉框内容
                fileListsAccept: [],
                fileListsEco: [],
                fileListsCom: [],
                materialList: [],
                user: JSON.parse(localStorage.getItem("user")),
                nodeStr: undefined,
                formType: "",
                formTypeStr: "",
                tabActionName: "tabMainContent",
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "economyProcess",
                    "officeRequirements",
                    "processTracking",
                    "fdsfsd"
                ],
                fileUploadText: false,
                fileList: [],
                fileUploadText: "文件上传",
                loading: false,
                fileUploadVisible: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: "",
                consultationform: undefined,
                handledemand: {},
                opinionList: [],
                registercase: false,
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
                    businessaddress: "",
                    semaknama: "",
                    semakbusinessaddress: "",
                    mediationnumber: "",
                    infringementtype: "",
                    infringingmarks: "",
                    mediationmethods: "",
                    noobligation: "",
                    resultsofmediation: "",
                    disputemoney: "",
                    mentalmoney: "",
                    doublemoney: "",
                    financialloss: "",
                    transferreddate: "",
                    mediationis: "",
                    attachfilename: "",
                    cksubjectname: "",
                    subjecttypecodeid: "",
                    infringementtypecodeid: "",
                    infringingmarkscodeid: "",
                    noobligationcodeid: "",
                    mediationmethodscodeid: "",
                    applealtocase: "",
                    caseno: "",
                    casedate: "",
                    casereason: "",
                    unlicensed: "",
                    handleDepart: ""
                },
                caseprocesformRules: {
                    classification: [{required: true, message: "请选择核查分类"}],
                    ispursuit: [{required: true, message: "请选择是否销案"}],
                    operator: [{required: true, message: "请输入经办人"}],
                    operatorPhone: [{ required: true, message: "请输入经办人电话" }],
                    isverified: [{required: true, message: "请选择受理举报类型"}],
                    confiscateAmount: [{type: "number", message: "请输入合法的金额"}],
                    penaltyAmount: [{type: "number", message: "请输入合法的金额"}],
                    postscript: [{required: true, message: "请输入送审附言"}],
                    handleDepart: [{required: true, message: "请输入办理部门"}],
                    subjectname: [{required: true, message: "请输入涉及主体"}],
                    subjecttype: [{required: true, message: "请选择涉及主体类别和类型"}],
                    verifiedopinion: [{required: true, message: "请输入调查情况"}],
                    updatedate: [{required: true, message: "请选择时间"}],
                    businessaddress: [{required: true, message: "请输入经营地址"}],
                    semaknama: [{required: true, message: "请输入核查经营名称"}],
                    semakbusinessaddress: [
                        {required: true, message: "请输入核查经营地址"}
                    ],
                    infringementtype: [{required: true, message: "请选择侵权类型"}],
                    // infringingmarks: [{required: true, message: "请选择欺诈标志"}],
                    mediationmethods: [{required: true, message: "请选择调解方式"}],
                    noobligation: [{required: true, message: "请选择未履行义务"}],
                    resultsofmediation: [{required: true, message: "请选择调解结果"}],
                    cksubjectname: [{required: true, message: "核查经营名称"}],
                    disputemoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    mentalmoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    doublemoney: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    financialloss: [
                        {required: true, type: "integer", message: "请输入金额"}
                    ],
                    transferreddate: [
                        {required: true, type: "date", message: "请选择结办日期"}
                    ],
                    mediationis: [
                        {required: true, message: "请输入调查情况"},
                        {max: 1200, message: "不能超出1200字符", trigger: "blur"}
                    ],
                    handleopinion: [
                        {required: true, message: "请输入处理结果", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    cksubjectaddress: [{required: true, message: "请输入核查经营地址"}],
                    businessaddress: [{required: true, message: "请输入经营地址"}],
                    applealtocase: [
                        {
                            required: true,
                            message: "请选择是否诉转案",
                            trigger: "blur"
                        }
                    ],
                    caseno: [
                        {
                            required: true,
                            message: "请输入立案号",
                            trigger: "blur"
                        },
                        {max: 30, message: "不能超出30字符", trigger: "blur"}
                    ],
                    casedate: [
                        {
                            required: true,
                            type: "date",
                            message: "请输入立案时间",
                            trigger: "blur"
                        }
                    ],
                    casereason: [
                        {
                            required: true,
                            message: "请输入案由",
                            trigger: "blur"
                        },
                        {max: 500, message: "不能超出500字符", trigger: "blur"}
                    ]
                },
                customerinfo: {},
                tasklist: {},
                ordernumber: "",
                delayForm: {},
                delayFormRules: {
                    unit: [{required: true, message: "请选择延期时间单位"}],
                    limit: [
                        {required: true, message: "请输入延期时限"},
                        {type: "integer", message: "延期时限只能是整数"},
                        {
                            type: "integer",
                            max: 100,
                            min: 1,
                            message: "不能大于100与小于1",
                            trigger: "blur"
                        }
                    ],
                    reason: [
                        {required: true, message: "请输入延期原因", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                backForm: {
                    opinion: ""
                },
                backFormRules: {
                    reason: [
                        {required: true, message: "请选择退回原因", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入退回描述", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },
                feedbackForm: {},
                feedbackFormRules: {
                    result: [{required: true, message: "请输入处理结果", trigger: "blur"}]
                },
                caseform: {
                    leader: "",
                    leaders: ""
                },
                caseformRules: {
                    leader: [{required: true, message: "请选择领导"}],
                    leaders: [{required: true, message: "请选择区局处理人"}]
                },
                assignmentForm: {
                    unit: undefined,
                    ortherDept: []
                },
                messageoptions:[],
                BMmessageoptions:[],
                checkList:[],
                BMcheckList:[],
                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"}
                    ]
                    // ortherDept: [{required: true, message: "请选择抄送部门"}],
                },
                departmentForm: {
                    ccs: []
                },
                departmentFormRules: {
                    leading: [{required: true, message: "请选择主导部门"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ]
                    // ccs: [{required: true, message: "请选择抄送部门"}]
                },
                personnelForm: {
                    ccs: []
                },
                personnelFormRules: {
                    leading: [{required: true, message: "请选择主要人员"}],
                    opinion: [
                        {required: true, message: "请输入分派意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ]
                },
                submitLoading: false,
                reviewDialogVisible: false,
                dialogRegionAudit: false,
                leaderList: [],
                unitList: [],
                departmentList: [],
                personnelList: [],
                postponeLists: [],
                registrationActiveNames: ["1", "2", "3", "4", "5"],
                leaderLists: [],
                requestMap: {
                    caseguid: ""
                },
                requestMapEco: {
                    caseguid: ""
                },
                requestMapCom: {
                    caseguid: ""
                }
            };
        },
        watch: {
            economyform: function ({caseguid}) {
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
                            this.fileListsEco.push(fileInfo);
                        }
                    }
                });
            },
            complaintform: function ({caseguid}) {
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
                            this.fileListsCom.push(fileInfo);
                        }
                    }
                });
            }
        },
        methods: {
            //导出为Word格式
            exportToWord() {
                //query传参,另一页面进行获取方法
                const querystring = require("querystring");
                let str = querystring.stringify({
                    caseguid: this.$route.query.caseguid,
                    formType: this.formType,
                    tserialnum: this.tasklist.tserialnum,
                    systemsource: this.tasklist.systemsource
                });
                window.open("/crcs/businessSearch/wordexport?" + str);
            },
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
            // download(url){
            //     // window.location.href = `${process.env.Download_URL}` + url;
            //     window.open(`${process.env.Download_URL}` + url);
            // },
            // 获取材料列表
            // getMaterialList(caseguid) {
            //     let params = {
            //         tserialnum: "",
            //         rqsttitle: "",
            //         begindate: "",
            //         enddate: "",
            //         size: 30,
            //         page: 1,
            //         caseguid: caseguid
            //     };
            //     getMaterialLists(params).then(res => {
            //         if (res.code === 0) {
            //             this.materialList = res.data.records;
            //         }
            //     });
            // },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            dateChanges(date) {
                this.caseprocesform.transferreddate = date;
            },
            triggerTab(value) {
                console.log(this.nodeStr)
                console.log(this.user.regionID)
                console.log(this.user.unitID)
                console.log(this.user)
                console.log(this.tasklist.fromnodeid)
                console.log(this.tasklist.nextnodeid)
                //经济表单处理反馈
                if (this.economyform != null && this.economyform != "") {
                    this.caseprocesform.subjectname = this.economyform.subjectname,
                    this.caseprocesform.businessaddress = this.economyform.subjectaddress;
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = getArrayByStringId(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = getArrayByStringId(
                            this.caseprocesform.noobligationcodeid,
                            this.noobligation1
                        );
                    }
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = getArrayByStringId(
                            this.caseprocesform.mediationmethodscodeid,
                            this.mediationmethods1
                        );
                    }
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = getArrayByStringId(
                            this.caseprocesform.infringingmarkscodeid,
                            this.infringingmarks1
                        );
                    }
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = getArrayByStringId(
                            this.caseprocesform.infringementtypecodeid,
                            this.infringementtype1
                        );
                    }
                }
                this.tabActionName = value;
                if ("tabExtension" == value) {
                    this.delayApproval();
                } else if ("tabBack" == value) {
                    this.delayPersonnel();
                }
                //判断是否销案
                if ("是" == this.caseprocesform.isregister) {
                    this.isregisters = true;
                } else {
                    this.isregisters = false;
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
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            filterDCcs(value) {
                //过滤部门
                this.departmentForm.ccs = this.departmentForm.ccs.filter(
                    item => item !== this.departmentForm.leading
                );
            },
            filterPCcs(value) {
                //过滤人员
                this.personnelForm.ccs = this.personnelForm.ccs.filter(
                    item => item !== this.personnelForm.leading
                );
            },
            ifMainDepartment() {
                //判断是否选择部门
                if (!this.departmentForm.leading) {
                    this.$refs.dCcsSelect.visible = false;
                    this.$alert("请先选择主导部门！", "提示", {
                        confirmButtonText: "确定",
                        type: "warning"
                    });
                }
            },
            ifMainPersonnel() {
                //判断是否主要人员
                if (!this.personnelForm.leading) {
                    this.$refs.pCcsSelect.visible = false;
                    this.$alert("请先选择主要人员！", "提示", {
                        confirmButtonText: "确定",
                        type: "warning"
                    });
                }
            },
            filterPersonnelList() {
                return this.personnelList.filter(
                    item => item.primaryKey !== this.personnelForm.leading
                );
            },
            filterDepartmentList() {
                return this.departmentList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
                );
            },
            openRegionAudit() {
                //打开送区局处理
                let validation;
                this.$refs["caseprocesformB"].validate(valid => (validation = valid));
                this.$refs["caseprocesformC"].validate(
                    valid => (validation = valid && validation)
                );
                // this.$refs["caseprocesformC"].validate(valid => (validation = valid && validation));
                if (!validation) return;
                //判断核查经营地址区域是否勾选了
                // if(!this.complaintform.ckjyareacode||!this.economyform.ckjyareacode){
                //     this.$message({
                //         message: "核查经营地址区域不能为空",
                //         type: "warning"
                //     });
                // }

                if(this.user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'){
                    this.$confirm("请确认无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        this.submitLoading = true;
                        if (
                            this.economyform.providetype != null &&
                            this.economyform.providetype != ""
                        ) {
                            this.economyform.providetype = this.Providetype;
                        }
                        if (
                            this.economyform.industryname != null &&
                            this.economyform.industryname != ""
                        ) {
                            this.economyform.industryname = this.Industryname;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.objectcategory != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory;
                        }
                        if (
                            this.economyform.reportprovidelb != null &&
                            this.economyform.reportprovidelb != ""
                        ) {
                            this.economyform.reportprovidelb = this.Reportprovidelb;
                        }

                        if (
                            this.caseprocesform.transferreddate != null &&
                            this.caseprocesform.transferreddate != ""
                        ) {
                            this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
                        }
                        if (
                            this.caseprocesform.casedate != null &&
                            this.caseprocesform.casedate != ""
                        ) {
                            this.caseprocesform.casedate = this.caseprocesform.casedate.getTime();
                        }
                        if (
                            this.caseprocesform.subjecttypecodeid != null &&
                            this.caseprocesform.subjecttypecodeid != ""
                        ) {
                            this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                            this.caseprocesform.subjecttypecodeid.length - 1
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
                            this.caseprocesform.infringementtypecodeid != null &&
                            this.caseprocesform.infringementtypecodeid != ""
                        ) {
                            this.caseprocesform.infringementtypecodeid = this.caseprocesform.infringementtypecodeid[
                            this.caseprocesform.infringementtypecodeid.length - 1
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
                        let params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "end",
                            data: this.economyform,
                            deptid: this.caseform.leaders,
                            opinion: this.caseprocesform.handleopinion,
                            caseprocesform: this.caseprocesform
                        };
                        nextWork(params)
                            .then(data => {
                                if (data.code === 0) {
                                    this.$message({
                                        message: "提交成功！",
                                        type: "success",
                                        duration: "1000"
                                    });
                                    if(window.opener){
                                        window.opener.location.reload(); //刷新父窗口
                                    }
                                    setInterval(function() {
                                        window.close();
                                    }, 500);
                                    // this.$router.push("/wddb/12315db");
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
                }else{
                    this.$refs.feedbackForm.validate(valid => {
                        if (valid) {
                            this.dialogRegionAudit = true;
                            this.delayPersonnel();
                        }
                    });
                }


            },
            openReviewDialog() {
                //打开送领导审批
                let validation;
                this.$refs["caseprocesformD"].validate(valid => (validation = valid));
                this.$refs["caseprocesformE"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["caseprocesformF"].validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) return;
                this.$refs.feedbackForm.validate(valid => {
                    if (valid) {
                        this.reviewDialogVisible = true;
                    }
                });
            },
            openReviewDialog01() {
                //打开送领导审批
                let validation;
                this.$refs["caseprocesformA"].validate(valid => (validation = valid));
                this.$refs["caseprocesformB"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["caseprocesformC"].validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) return;
                this.$refs.feedbackForm.validate(valid => {
                    if (valid) {
                        this.reviewDialogVisible = true;
                    }
                });
            },
            openReviewDialog02() {
                //打开送领导审批
                let validation;
                this.$refs["caseprocesformG"].validate(valid => (validation = valid));
                if (!validation) return;
                this.$refs.feedbackForm.validate(valid => {
                    if (valid) {
                        this.reviewDialogVisible = true;
                    }
                });
            },
            submitBack() {
                //退回
                let validation = false;
                this.$refs.backForm.validate(valid => (validation = valid));
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
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.providetype != null &&
                            this.economyform.providetype != ""
                        ) {
                            this.economyform.providetype = this.Providetype;
                        }
                        if (
                            this.economyform.industryname != null &&
                            this.economyform.industryname != ""
                        ) {
                            this.economyform.industryname = this.Industryname;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.objectcategory != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory;
                        }
                        if (
                            this.economyform.reportprovidelb != null &&
                            this.economyform.reportprovidelb != ""
                        ) {
                            this.economyform.reportprovidelb = this.Reportprovidelb;
                        }
                        data = this.economyform;
                    }
                    let params;
                    if (this.user.aicPermissions.ywclDispatch.sysMenuFiled == "ywclDispatch") {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "backForward",
                            data: data,
                            opinion: this.backForm.opinion,
                            backreason: this.backForm.reason,
                            isEnd: "1"
                        };
                    } else {
                        params = {
                            user: this.user,
                            formType: this.formType,
                            node: this.nodeStr,
                            nextNodeId: "backForward",
                            data: data,
                            opinion: this.backForm.opinion,
                            backreason: this.backForm.reason,
                            deptid: this.backForm.leader
                        };
                    }
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
                                }, 500);
                                // this.$router.push("/wddb/12315db");\
                                // var obj = this.$route.query;
                                // console.log("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦")
                                // console.log(obj)
                                // obj.flag = "true";
                                // this.$router.push({
                                //     path: '/wddb/12315db',
                                //     query: obj
                                // })
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
            submitUpload(value) {
                this.requestMap.caseguid = this.complaintform.caseguid;
                this.$refs[value].submit();
            },
            submitUploadEco(value) {
                this.requestMapEco.caseguid = this.economyform.caseguid;
                this.$refs[value].submit();
            },
            submitUploadCom() {
            },
            progress(event, file, fileList) {
            },

            submitFeedback() {
                //打开区局处理
                let validation;
                this.$refs["caseprocesformA"].validate(valid => (validation = valid));
                this.$refs["caseprocesformB"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["caseprocesformC"].validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    if (
                        this.caseprocesform.transferreddate != null &&
                        this.caseprocesform.transferreddate != ""
                    ) {
                        this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
                    }
                    if (
                        this.caseprocesform.casedate != null &&
                        this.caseprocesform.casedate != ""
                    ) {
                        this.caseprocesform.casedate = this.caseprocesform.casedate.getTime();
                    }
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
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
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = this.caseprocesform.infringementtypecodeid[
                        this.caseprocesform.infringementtypecodeid.length - 1
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

                    // this.caseprocesform.infringingmarks=this.caseprocesform.infringingmarks[this.caseprocesform.infringingmarks.length-1].toString();
                    // this.caseprocesform.infringementtype=this.caseprocesform.infringementtype[this.caseprocesform.infringementtype.length-1].toString();
                    // this.caseprocesform.mediationmethods=this.caseprocesform.mediationmethods[this.caseprocesform.mediationmethods.length-1].toString();
                    // this.caseprocesform.noobligation=this.caseprocesform.noobligation[this.caseprocesform.noobligation.length-1].toString();
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "regionAudi",
                        data: this.economyform,
                        caseprocesform: this.caseprocesform,
                        opinion: this.feedbackForm.result,
                        deptid: this.caseform.leaders
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
                                }, 500);
                                // this.$router.push("/wddb/12315db");
                                // var obj = this.$route.query;
                                // console.log("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦")
                                // console.log(obj)
                                // obj.flag = "true";
                                // this.$router.push({
                                //     path: '/wddb/12315db',
                                //     query: obj
                                // })
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
            submitFeedback2() {
                //反馈到区局处理
                let validation;
                this.$refs["feedbackLeaderForms"].validate(valid => (validation = valid));
                //判断核查经营地址区域是否勾选了
                // if(!this.complaintform.ckjyareacode||!this.economyform.ckjyareacode){
                //     validation=false
                // }
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;

                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }

                    if (
                        this.caseprocesform.transferreddate != null &&
                        this.caseprocesform.transferreddate != ""
                    ) {
                        this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
                    }
                    if (
                        this.caseprocesform.casedate != null &&
                        this.caseprocesform.casedate != ""
                    ) {
                        this.caseprocesform.casedate = this.caseprocesform.casedate.getTime();
                    }
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
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
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = this.caseprocesform.infringementtypecodeid[
                        this.caseprocesform.infringementtypecodeid.length - 1
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "regionAudit",
                        data: this.economyform,
                        deptid: this.caseform.leaders,
                        opinion: this.caseprocesform.handleopinion,
                        caseprocesform: this.caseprocesform
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                this.$router.push("/wddb/12315db");
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
            submitLeader() {
                //送领导审批
                let validation;
                this.$refs.feedbackForm.validate(valid => (validation = valid));
                this.$refs.feedbackLeaderForm.validate(
                    valid => (validation = valid && validation)
                );
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    //深度拷贝，防止更改economyform原先的表单值
                    let data = JSON.parse(JSON.stringify(this.economyform));
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "leaderApr",
                        data: this.economyform,
                        opinion: this.caseprocesform.handleopinion,
                        deptid: this.feedbackForm.leader,
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
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
                                }, 500);
                                // this.$router.push("/wddb/12315db");
                                // var obj = this.$route.query;
                                // console.log("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦")
                                // console.log(obj)
                                // obj.flag = "true";
                                // this.$router.push({
                                //     path: '/wddb/12315db',
                                //     query: obj
                                // })
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
            submitDelay() {
                //申请延期
                let validation = false;
                this.$refs.delayForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "applyDelay",
                        data: this.economyform,
                        deptid: this.delayForm.postpone,
                        opinion: this.delayForm.reason,
                        delayData: this.delayForm
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
                                    caseguid: this.$route.query.caseguid
                                };
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
                                }, 500);
                                // this.$router.push({path:"/wddb/12315db",params});
                                // var obj = this.$route.query;
                                // console.log("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦")
                                // console.log(obj)
                                // obj.flag = "true";
                                // this.$router.push({
                                //     path: '/wddb/12315db',
                                //     query: obj
                                // })
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
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerDept",
                        data: this.economyform,
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
                                            message: "提交成功！",
                                            type: "success",
                                            duration: "1000"
                                        });
                                        if (window.opener) {
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close();
                                        }, 500);
                                    }else{
                                        this.errorAlert();
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
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: this.economyform,
                        opinion: this.assignmentForm.opinion,
                        orgid: this.assignmentForm.unit,
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
                                            message: "提交成功！",
                                            type: "success",
                                            duration: "1000"
                                        });
                                        if (window.opener) {
                                            window.opener.location.reload(); //刷新父窗口
                                        }
                                        setInterval(function () {
                                            window.close();
                                        }, 500);
                                    }else{
                                        this.errorAlert();
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
            submitPersonnel() {
                //分派人员
                let validation = false;
                this.$refs.personnelForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    if (
                        this.economyform.providetype != null &&
                        this.economyform.providetype != ""
                    ) {
                        this.economyform.providetype = this.Providetype;
                    }
                    if (
                        this.economyform.industryname != null &&
                        this.economyform.industryname != ""
                    ) {
                        this.economyform.industryname = this.Industryname;
                    }
                    if (
                        this.economyform.objectcategory != null &&
                        this.economyform.objectcategory != ""
                    ) {
                        this.economyform.objectcategory = this.Objectcategory;
                    }
                    if (
                        this.economyform.reportprovidelb != null &&
                        this.economyform.reportprovidelb != ""
                    ) {
                        this.economyform.reportprovidelb = this.Reportprovidelb;
                    }
                    this.submitLoading = true;
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerPersonnel",
                        data: this.economyform,
                        opinion: this.personnelForm.opinion,
                        //caseprocesform: this.caseprocesform,
                        deptid: this.personnelForm.leading,
                        ortherDept: this.personnelForm.ccs,
                        isnewassign:"1"
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                this.$router.push("/wddb/12315db");
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
                    this.$router.push("/wddb/12315db");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource: "03"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;
                        if (data.caseprocesform != null) {
                            data.caseprocesform.unlicensed = "2";
                            data.caseprocesform.handleDepart = "业务部门";
                            data.caseprocesform.leadershipAudit = "1";
                            data.caseprocesform.postscript = "请领导审批";
                            this.caseprocesform = data.caseprocesform;
                            data.caseprocesform.operator="佛山市顺德区市场监督管理局";
                            data.caseprocesform.operatorPhone="22838180";

                        }
                        this.customerinfo = data.customerinfo;
                        this.tasklist = data.tasklist;
                        this.opinionList = data.opinionList;
                        if (code === 0) {
                            if (data.economyform != null) {
                                //经济表单
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = "经济违法行为";
                                this.economyform = data.economyform;
                                this.ordernumber = this.economyform.ordernumber;
                                if (
                                    this.economyform.providetype != null &&
                                    this.economyform.providetype != ""
                                ) {
                                    this.Providetype = this.economyform.providetype;
                                    this.economyform.providetype = this.economyform.providetype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.providetype = this.economyform.providetype[
                                    this.economyform.providetype.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.industryname != null &&
                                    this.economyform.industryname != ""
                                ) {
                                    this.Industryname = this.economyform.industryname;
                                    this.economyform.industryname = this.economyform.industryname.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.industryname = this.economyform.industryname[
                                    this.economyform.industryname.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.objectcategory != null &&
                                    this.economyform.objectcategory != ""
                                ) {
                                    this.Objectcategory = this.economyform.objectcategory;
                                    this.economyform.objectcategory = this.economyform.objectcategory.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.objectcategory = this.economyform.objectcategory[
                                    this.economyform.objectcategory.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.reportprovidelb != null &&
                                    this.economyform.reportprovidelb != ""
                                ) {
                                    this.Reportprovidelb = this.economyform.reportprovidelb;
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.reportprovidelb = this.economyform.reportprovidelb[
                                    this.economyform.reportprovidelb.length - 1
                                        ].toString();
                                }
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.$router.push("/wddb/12315db");
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
            getLeaderList() {
                //请求领导的列表
                let params = {
                    deptid: this.user.unitID,
                    permission: "leadershipApproval"
                };
                findUsersByDeptIdAndPermission(params).then(data => {
                    if (data.data) {
                        this.leaderList = data.data;
                    }
                });
            },

            findUnitList() {
                //获取下级单位列表
                findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
                    if (data.data) {
                        this.unitList = data.data;
                    }
                });
            },
            findDepartmentList() {
                //获取部门列表
                findOrgDeptByOrgId({orgId: this.user.orgID}).then(data => {
                    if (data.data) {
                        this.departmentList = data.data;
                    }
                });
            },
            findPersonnelList() {
                //获取人员列表
                findUsersByDeptIdAndPermission({
                    deptid: this.user.unitID,
                    permission: "dealpersonnel"
                }).then(data => {
                    if (data.data) {
                        this.personnelList = data.data;
                    }
                });
            },
            toUploadOpen(type) {
                this.fileUploadVisible = true;
            },
            //校验单位或部门是否存在对应权限的人员
            orgPermission(value) {
                if ("1" == value) {
                    let para = {
                        orgid: this.assignmentForm.unit,
                        permission: "subordinate"
                    };
                    if (this.assignmentForm.unit != "") {
                        findUsersByOrgAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该下级机关没有处理该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.assignmentForm.unit = "";
                            }
                        });
                    }
                } else if ("2" == value) {
                    let para = {
                        deptid: this.departmentForm.leading,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.leading != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该部门没有处理该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.departmentForm.leading = "";
                            }
                        });
                    }
                } else if ("5" == value) {
                    let unit = this.assignmentForm.ortherDept[
                    this.assignmentForm.ortherDept.length - 1
                        ];
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
                        findUsersByDeptIdAndJurisdiction(para).then(res => {
                            if (res.data.data == null) {
                                this.$alert(
                                    "该部门没有查看该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.assignmentForm.ortherDept.splice(
                                    this.assignmentForm.ortherDept.indexOf(
                                        this.assignmentForm.ortherDept[
                                        this.assignmentForm.ortherDept.length - 1
                                            ]
                                    )
                                );
                            }
                        });
                    }
                } else {
                    let unit = this.departmentForm.ccs[this.departmentForm.ccs.length - 1];
                    let para = {
                        deptid: unit,
                        permission: "dealdepartment"
                    };
                    if (this.departmentForm.ccs.length > 0 && unit != "") {
                        findUsersByDeptIdAndPermission(para).then(res => {
                            if (res.data == null) {
                                this.$alert(
                                    "该部门没有查看该业务权限的人员，请先添加权限!",
                                    "提示",
                                    {
                                        confirmButtonText: "确定"
                                    }
                                );
                                this.departmentForm.ccs.splice(
                                    this.departmentForm.ccs.indexOf(
                                        this.departmentForm.ccs.length[
                                        this.departmentForm.ccs.length - 1
                                            ]
                                    )
                                );
                            }
                        });
                    }
                }
            },
            //根据当前用户权限查询人员列表
            delayPersonnel() {
                let para = {
                    permission: "ywclDispatch"
                };
                findUsersByPermission(para).then(res => {
                    this.leaderLists = res.data;
                });
            },
            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1]);
                this.caseprocesform.subjecttype = label;
                this.caseprocesform.subjecttypecodeid = array;
            },
            handleChangeInfringementtype1(array) {
                let label = this.getLabel(
                    this.infringementtype1,
                    array[array.length - 1]
                );
                this.caseprocesform.infringementtype = label;
                this.caseprocesform.infringementtypecodeid = array;
            },
            handleChangeInfringingmarks1(array) {
                let label = this.getLabel(this.infringingmarks1, array[array.length - 1]);
                this.caseprocesform.infringingmarks = label;
                this.caseprocesform.infringingmarkscodeid = array;
            },
            handleChangeMediationmethods1(array) {
                let label = this.getLabel(
                    this.mediationmethods1,
                    array[array.length - 1]
                );
                this.caseprocesform.mediationmethods = label;
                this.caseprocesform.mediationmethodscodeid = array;
            },
            handleChangeNoobligation1(array) {
                let label = this.getLabel(this.noobligation1, array[array.length - 1]);
                this.caseprocesform.noobligation = label;
                this.caseprocesform.noobligationcodeid = array;
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
            //获取对应的级联codeid和lable
            testTemp(children, codeid, label) {
                for (const child of children) {
                    if (codeid === child.value) {
                        return child.label;
                    }
                    if (child.children) {
                        let label = this.testTemp(child.children, codeid, label);
                        if (label) {
                            return label;
                        }
                    }
                }
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
            //侵权类型下拉框级联数据
            getInfringementtype1() {
                let params = {
                    type: "tort12345"
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
                    this.infringementtype1 = this.data; //侵权类型
                });
            },
            //欺诈标志下拉框级联数据
            getInfringingmarks1() {
                let params = {
                    type: "H"
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
                    this.infringingmarks1 = this.data; //欺诈标志
                });
            },
            //调解方式下拉框级联数据
            getMediationmethods1() {
                let params = {
                    type: "J"
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
                    this.mediationmethods1 = this.data; //调解方式
                });
            },
            //未履行义务下拉框级联数据
            getNoobligation1() {
                let params = {
                    type: "K"
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
                    this.noobligation1 = this.data; //未履行义务
                });
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
            //查询延期审批用户列表
            delayApproval() {
                let para = {
                    permission: "delayApproval"
                };
                findUsersByPermission(para).then(res => {
                    this.postponeLists = res.data;
                });
            },
            //codeid数据转换
            dataConversion() {
                //消费表单处理反馈
                if (this.complaintform != null && this.complaintform != "") {
                    (this.caseprocesform.subjectname = this.complaintform.subjectname),
                        // this.caseprocesform.subjecttype = this.complaintform.subjecttype,
                        (this.caseprocesform.cksubjectaddress = this.complaintform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.complaintform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.complaintform.subjectaddress);
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = byIdGetSuperiors(
                            this.caseprocesform.noobligationcodeid,
                            this.noobligation1
                        );
                    }
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = byIdGetSuperiors(
                            this.caseprocesform.mediationmethodscodeid,
                            this.mediationmethods1
                        );
                    }
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = byIdGetSuperiors(
                            this.caseprocesform.infringingmarkscodeid,
                            this.infringingmarks1
                        );
                    }
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = byIdGetSuperiors(
                            this.caseprocesform.infringementtypecodeid,
                            this.infringementtype1
                        );
                    }
                }
                //经济表单处理反馈
                if (this.economyform != null && this.economyform != "") {
                    (this.caseprocesform.subjectname = this.economyform.subjectname),
                        // this.caseprocesform.subjecttype = this.economyform.subjecttype,
                        (this.caseprocesform.cksubjectaddress = this.economyform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.economyform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.economyform.subjectaddress);
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = byIdGetSuperiors(
                            this.caseprocesform.noobligationcodeid,
                            this.noobligation1
                        );
                    }
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = byIdGetSuperiors(
                            this.caseprocesform.mediationmethodscodeid,
                            this.mediationmethods1
                        );
                    }
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = byIdGetSuperiors(
                            this.caseprocesform.infringingmarkscodeid,
                            this.infringingmarks1
                        );
                    }
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = byIdGetSuperiors(
                            this.caseprocesform.infringementtypecodeid,
                            this.infringementtype1
                        );
                    }
                }
                //咨询表单处理反馈
                if (this.consultationform != null && this.consultationform != "") {
                    (this.caseprocesform.subjectname = this.consultationform.subjectname),
                        // this.caseprocesform.subjecttype = this.consultationform.subjecttype,
                        (this.caseprocesform.cksubjectaddress = this.consultationform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.consultationform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.consultationform.subjectaddress);
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjecttype1
                        );
                    }
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = byIdGetSuperiors(
                            this.caseprocesform.noobligationcodeid,
                            this.noobligation1
                        );
                    }
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = byIdGetSuperiors(
                            this.caseprocesform.mediationmethodscodeid,
                            this.mediationmethods1
                        );
                    }
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = byIdGetSuperiors(
                            this.caseprocesform.infringingmarkscodeid,
                            this.infringingmarks1
                        );
                    }
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = byIdGetSuperiors(
                            this.caseprocesform.infringementtypecodeid,
                            this.infringementtype1
                        );
                    }
                }
                //通用表单处理反馈
                if (this.commomform != null && this.commomform != "") {
                    (this.caseprocesform.subjectname = this.commomform.subjectname),
                        // this.caseprocesform.subjecttype = this.commomform.subjecttype,
                        (this.caseprocesform.cksubjectaddress = this.commomform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.commomform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.commomform.subjectaddress);
                }
            },
            caseChange() {
                if (this.caseprocesform.applealtocase == "1") {
                    this.registercase = true;
                } else {
                    this.registercase = false;
                    this.caseprocesform.caseno = "";
                    this.caseprocesform.casedate = "";
                    this.caseprocesform.casereason = "";
                }
            },
            reasonChange() {
                if ("缺失要素" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实补充以下要素：xxx。";
                } else if ("表述不清" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请核实修改以下内容：xxx。";
                } else if ("类型有误" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，请修改表单类型为：xxx。";
                } else if ("地址不详" == this.backForm.reason) {
                    this.backForm.opinion = "此单退回，地址不详细，请核实详细地址。";
                } else if ("已有知识" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，根据知识库信息编码：xxx，请按知识点指引市民。";
                } else if ("重复受理" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，此工单与另一工单（表单号：xxx）属重复受理。";
                } else if ("市民撤诉" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，经我局与市民核实，市民表示无需我局继续跟进此工单。";
                } else if ("不属本职" == this.backForm.reason) {
                    this.backForm.opinion =
                        "此单退回，根据XXX条例，此工单反映事项不属于我局职责范围，建议转派xxx部门办理。";
                } else {
                    this.backForm.opinion = "其它原因";
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
            this.getBaseInfo();
            this.getLeaderList();
            this.findUnitList();
            this.findDepartmentList();
            this.findPersonnelList();
            this.findPersonnelList();
            this.getInfringementtype1();
            this.getInfringingmarks1();
            this.getMediationmethods1();
            this.getNoobligation1();
            this.getSubjecttype1();
            this.SearchMessagePerson();
        }
    };
</script>
<style lang="scss" scoped>
    .bt {
        margin-bottom: 0;
    }

    /* 公共样式 */
    @mixin p20 {
        padding: 20px 0 !important;
    }

    @mixin mb10 {
        margin-bottom: 18px;
    }

    .acceptance-of-next {
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
            padding: 15px 0 5px 0;
            border: none !important;
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }

        .el-form .el-row {
            margin: 0;
        }
    }

    @media (min-width: 1200px) {
        .el-col-lg-12,
        .el-col-lg-24 {
            @include mb10;
        }
    }

    .el-collapse-item__wrap {
        padding: 20px 0 !important;
    }

    .accessoriesList {
        ul {
            list-style: none;
            margin: 0;
            padding: 0;
            color: rgb(72, 87, 106);

            li {
                span {
                    font-size: 16px;

                    &:last-child:hover {
                        cursor: pointer;
                        text-decoration: underline;
                    }
                }
            }
        }
    }
</style>
<style>
    .item_col {
        border: none !important;
    }

    .el-tabs__header {
        border-bottom: 0;
    }
</style>
