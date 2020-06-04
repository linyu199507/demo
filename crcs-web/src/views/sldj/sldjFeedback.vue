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
                        :class="{'fixed':scrollTop > height}"
                        style="text-align: right;"
                >
                    <!--word导出-->
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
                    <el-button type="primary" v-if="tasklist.feedback != '1' && tasklist.formtype != '01'"
                               @click="triggerBus('first')">初步反馈</el-button>
                    <el-button type="primary" @click="triggerBus('tabBus')">在办反馈</el-button>
                    <el-button type="primary" @click="triggerTab('tabFeedback')">最终反馈</el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                    >分派下级
                    </el-button>
                    <el-button
                            v-if="(user.regionID!='440681' && user.aicPermissions.dealdepartment&&nodeStr!=='moveToLowerDept'&&nodeStr!=='moveToLowerPersonnel'&&departmentList.length>0)
                            ||user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button
                            v-if="(user.aicPermissions.dealpersonnel&&nodeStr==='moveToLowerDept'&&nodeStr!=='moveToLowerPersonnel'&&personnelList.length>0)
                            || (nodeStr!=='moveToLowerPersonnel' && user.regionID=='440681' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0')"
                            type="primary"
                            @click="triggerTab('tabPersonnel')"
                    >分派人员
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabBack')"
                               v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">退回
                    </el-button>
                    <el-button type="primary" @click="triggerTab('tabExtension')">申请延期
                    </el-button>
                </div>
                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>1.</em> {{formTypeStr}}
                                </el-col>
                                <el-col :span="10" class="right">定单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>
                        <!-- 消费投诉信息内容 -->
                        <complaintmoduleInfo v-if="complaintform" :complaintmodule="complaintform"
                                             :tasklist="tasklist"/>
                        <!--经济违法信息内容 -->
                        <economyInfo v-if="economyform" :comeconomicmodule="economyform" :tasklist="tasklist"/>
                        <!--咨询或者通用表单 -->
                        <consultationInfo v-if="commomform"
                                          :commommodule="commomform"
                                          :tasklist="tasklist"/>
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
                    <tracking :data="opinionList"/>
                </el-collapse>
            </el-tab-pane>

            <!-- 初步反馈 -->
            <el-tab-pane label="初步反馈" name="tabOpinionOne" v-if="tabActionName==='tabOpinionOne'">
                <el-form :model="opinionForm" label-width="200px" ref="opinionForm" :rules="opinionFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="初步反馈内容" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="opinionForm.opinion"
                                        placeholder="请输入初步反馈内容（2000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col class="centered">
                            <el-button type="primary" @click="copyOpinion('preliminary')" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <!-- 在办反馈 -->
            <el-tab-pane label="在办反馈" name="tabOpinion" v-if="tabActionName==='tabOpinion'">
                <el-form :model="opinionForm" label-width="200px" ref="opinionForm" :rules="opinionFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="反馈内容" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="opinionForm.opinion"
                                        placeholder="请输入反馈内容（3000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>

                        <el-col class="centered">
                            <el-button type="primary" @click="copyOpinion('tabBus')" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <!-- 处理反馈 -->
            <el-tab-pane label="最终反馈" name="tabFeedback" v-if="tabActionName==='tabFeedback'">
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
                                        <el-form-item label="涉及主体类别和类型：" prop="subjecttype" class="bt mb20">
                                            <el-cascader
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
                                        <el-form-item label="核查经营地址：" prop="semakbusinessaddress" class="bt">
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
                                        <el-form-item label="属实情况：" prop="isverified" class="bt">
                                            <el-select
                                                    placeholder="请选择"
                                                    v-model="caseprocesform.isverified"
                                                    class="input-text"
                                            >
                                                <el-option label="属实" value="属实"></el-option>
                                                <el-option label="不属实" value="不属实"></el-option>
                                                <el-option label="待定" value="待定"></el-option>
                                            </el-select>
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
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12" v-if="isregisters">
                                        <el-form-item label="是否销案：" prop="ispursuit" class="bt">
                                            <el-select
                                                    placeholder="请选择"
                                                    v-model="caseprocesform.ispursuit"
                                                    class="input-text"
                                            >
                                                <el-option label="是" value="是"></el-option>
                                                <el-option label="否" value="否"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="处理结果：" prop="handleopinion" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入处理意见（1000字内）"
                                                    v-model="caseprocesform.handleopinion"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                        <el-form-item label="经济相关附件:" class="bt">
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
                                                        @click="submitUploadEco('uploadEconomy')"
                                                >确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-row style="margin: 20px 0;">
                            <el-col style="text-align: center;width: 100%;">
                                <el-button type="primary" @click="openRegionAudit" :loading="submitLoading"
                                           v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">确定
                                </el-button>
                                <!--隐藏送区局-->
                                <!--<el-button type="primary" @click="openRegionAudit" :loading="submitLoading"-->
                                           <!--v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">送区局处理-->
                                <!--</el-button>-->
                                <el-button type="primary" @click="openReviewDialog01" :loading="submitLoading"
                                           v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">送领导审批
                                </el-button>
                                <el-button @click="cancelTab">取消</el-button>
                            </el-col>
                        </el-row>
                    </el-collapse>
                    <el-collapse
                            v-model="registrationActiveNames"
                            class="accordion"
                            v-if="formType==='complaintmodule'"
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
                                    ref="caseprocesformD"
                                    :rules="caseprocesformRules"
                            >
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体：" prop="subjectname" class="bt">
                                            <el-input
                                                    placeholder="请输入"
                                                    v-model="caseprocesform.subjectname"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="涉及主体类别和类型：" prop="subjecttype" class="bt">
                                            <el-cascader
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
                                        <el-form-item label="经营地址：" prop="businessaddress" class="bt">
                                            <el-input
                                                    placeholder="请输入"
                                                    v-model="caseprocesform.businessaddress"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="核查经营地址：" prop="semakbusinessaddress" class="bt">
                                            <el-input
                                                    placeholder="请输入"
                                                    v-model="caseprocesform.semakbusinessaddress"
                                                    class="input-text"
                                            >
                                                <el-select
                                                        v-model="complaintform.region"
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
                                                    placeholder="请输入"
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
                        <el-collapse-item name="2">
                            <div slot="title" @click.stop>
                                <el-row class="el-accordion">
                                    <el-col :span="6">调解情况</el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                                </el-row>
                            </div>
                            <el-form
                                    :model="caseprocesform"
                                    label-width="180px"
                                    ref="caseprocesformE"
                                    :rules="caseprocesformRules"
                            >
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="行政调解文书号：" class="bt">
                                            <el-input
                                                    placeholder="请输入"
                                                    v-model="caseprocesform.mediationnumber"
                                                    class="input-text"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="侵权类型：" prop="infringementtype" class="bt">
                                            <el-cascader
                                                    placeholder="请选择侵权类型"
                                                    :options="infringementtype1"
                                                    v-model="caseprocesform.infringementtypecodeid"
                                                    @change="handleChangeInfringementtype1"
                                                    :show-all-levels="false"
                                                    class="bt1"
                                            ></el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="欺诈标志：" prop="infringingmarks" class="bt">
                                            <el-cascader
                                                    placeholder="请选择欺诈标志"
                                                    :options="infringingmarks1"
                                                    v-model="caseprocesform.infringingmarkscodeid"
                                                    @change="handleChangeInfringingmarks1"
                                                    :show-all-levels="false"
                                                    class="bt1"
                                            ></el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="调解方式：" prop="mediationmethods" class="bt">
                                            <el-cascader
                                                    placeholder="请选择调解方式"
                                                    :options="mediationmethods1"
                                                    v-model="caseprocesform.mediationmethodscodeid"
                                                    @change="handleChangeMediationmethods1"
                                                    :show-all-levels="false"
                                                    class="bt1"
                                            ></el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="未履行义务：" prop="noobligation" class="bt">
                                            <el-cascader
                                                    placeholder="请选择未履行义务"
                                                    :options="noobligation1"
                                                    v-model="caseprocesform.noobligationcodeid"
                                                    @change="handleChangeNoobligation1"
                                                    :show-all-levels="false"
                                                    class="bt1"
                                            ></el-cascader>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="调解结果：" prop="resultsofmediation" class="bt">
                                            <el-select
                                                    placeholder="如选择其他视为调解不成功"
                                                    v-model="caseprocesform.resultsofmediation"
                                                    class="input-text"
                                            >
                                                <el-option label="达成调解协议" value="达成调解协议"></el-option>
                                                <el-option label="未达成调解协议" value="未达成调解协议"></el-option>
                                                <el-option label="撤回" value="撤回"></el-option>
                                                <el-option label="联系不到当事人" value="联系不到当事人"></el-option>
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
                                    <!--时间&#45;&#45;&#45;&#45;&#45;&#45;@change="dateChanges"-->
                                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                        <el-form-item label="办结日期：" prop="transferreddate" class="bt">
                                            <el-date-picker
                                                    type="date"
                                                    v-model="caseprocesform.transferreddate"
                                                    class="input-text"
                                                    style="width: 100%"
                                                    value-format="yyyy-MM-dd"
                                                    placeholder="选择日期"
                                            ></el-date-picker>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="调解情况:" prop="mediationis" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :autosize="{ minRows: 4, maxRows: 6}"
                                                    placeholder="请输入调查情况(500字以内)"
                                                    v-model="caseprocesform.mediationis"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-collapse-item name="3">
                            <div slot="title" @click.stop>
                                <el-row class="el-accordion">
                                    <el-col :span="6">处理信息</el-col>
                                    <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                                </el-row>
                            </div>
                            <el-form
                                    :model="caseprocesform"
                                    label-width="180px"
                                    ref="caseprocesformF"
                                    :rules="caseprocesformRules"
                            >
                                <el-row>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                        <el-form-item label="处理结果：" prop="handleopinion" class="bt">
                                            <el-input
                                                    type="textarea"
                                                    :rows="4"
                                                    placeholder="请输入内容（1000字内）"
                                                    v-model="caseprocesform.handleopinion"
                                            ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                        <el-form-item label="是否诉转案：" class="bt" prop="applealtocase">
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
                                    <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                        <el-form-item label="消费相关附件:" class="bt">
                                            <el-upload
                                                    :auto-upload="false"
                                                    :file-list="fileListsAccept"
                                                    ref="upload"
                                                    :on-preview="previewFile"
                                                    :data="this.requestMapCom"
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
                                                        @click="submitUploadCom('uploadAccept')"
                                                >确认上传
                                                </el-button>
                                            </el-upload>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-collapse-item>
                        <el-row
                                style="margin: 20px 0;"
                                v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                        >
                            <el-col style="text-align: center;width: 100%;">
                                <!--隐藏区局处理-->
                                <el-button type="primary" @click="openRegionAudit" :loading="submitLoading" v-if="false">送区局消保科处理
                                </el-button>
                                <el-button type="primary" @click="openReviewDialog" :loading="submitLoading">送领导审核
                                </el-button>
                                <el-button @click="cancelTab">取消</el-button>
                            </el-col>
                        </el-row>
                        <el-row
                                style="margin: 20px 0;"
                                v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                        >
                            <el-col style="text-align: center;width: 100%;">
                                <el-button type="primary" @click="openRegionAudit" :loading="submitLoading">确定
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
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                            <el-form-item label="处理意见" class="form_bt" prop="handleopinion" style="width: 80%">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入处理意见（1000字内）"
                                        v-model="caseprocesform.handleopinion"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="24">
                            <el-form-item label="咨询相关附件" class="bt" style="float: left">
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
                                    <el-button size="small" type="success" @click="submitUpload('upload')">确认上传
                                    </el-button>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item
                            label-width="0"
                            style="text-align: center;"
                            v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                    >
                        <!--隐藏区局处理-->
                        <el-button type="primary" @click="openRegionAudit" v-if="false">送区局处理</el-button>
                        <el-button type="primary" @click="openReviewDialog02">送领导审核</el-button>
                        <el-button @click="cancelTab">取消</el-button>
                    </el-form-item>
                    <el-form-item
                            label-width="0"
                            style="text-align: center;"
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                    >
                        <el-button type="primary" @click="openRegionAudit">确定</el-button>
                        <el-button @click="cancelTab">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <!-- 分派下级 -->
            <el-tab-pane label="分派下级" name="tabAssignment" v-if="tabActionName==='tabAssignment'" style="min-height:550px">
                <!--<el-form-->
                        <!--:model="assignmentForm"-->
                        <!--label-width="200px"-->
                        <!--ref="assignmentForm"-->
                        <!--label-suffix="："-->
                        <!--:rules="assignmentFormRules"-->
                        <!--class="text_bold"-->
                <!--&gt;-->
                    <!--<el-row>-->
                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="16">-->
                            <!--<el-form-item label="成员单位" prop="unit">-->
                                <!--<cascader-->
                                <!--clearable-->
                                <!--:options="options"-->
                                <!--v-model="assignmentForm.unitsoptionsmodel"-->
                                <!--placeholder="请选择"-->
                                <!--expand-trigger="hover"-->
                                <!--isMultiple="true"-->
                                <!--&gt;-->
                                <!--</cascader>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="16">-->
                            <!--<el-form-item label="抄送部门">-->
                                <!--<cascader-->
                                <!--:options="unitsoptionsofdept"-->
                                <!--v-model="unitsoptionsmodelofdept"-->
                                <!--placeholder="请选择抄送部门"-->
                                <!--isMultiple="false"-->
                                <!--&gt;</cascader>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col :xs="24" :sm="24" :md="24" :lg="16">-->
                            <!--<el-form-item label="分派意见" prop="opinion">-->
                                <!--<el-input-->
                                        <!--type="textarea"-->
                                        <!--:rows="5"-->
                                        <!--placeholder="请输入分派意见（3000字内）"-->
                                        <!--v-model="assignmentForm.opinion"-->
                                <!--&gt;</el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col class="centered">-->
                            <!--<el-button type="primary" @click="submitAssignment" :loading="submitLoading">确定</el-button>-->
                            <!--<el-button @click="cancelTab">取消</el-button>-->
                        <!--</el-col>-->
                    <!--</el-row>-->
                <!--</el-form>-->

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
                                        filterable
                                        placeholder="请选择成员单位"
                                        style="width: 745px;"
                                        @change="orgPermission('1')"
                                >
                                    <el-option
                                            v-for="item in unitList"
                                            :key="item.region"
                                            :label="item.name"
                                            :value="item.primaryKey"
                                            ref="ulist"
                                    ></el-option>
                                </el-select>

                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="抄送部门">
                                <el-select
                                v-model="assignmentForm.ortherDept"
                                placeholder="请选择抄送部门"
                                style="width: 745px;"
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
                                        style="width: 745px;"
                                ></el-input>
                            </el-form-item>
                            <el-form-item label="短信" prop="message">
                                <Cascader
                                        :options="messageoptions"
                                        v-model="checkList"
                                        style="width: 745px;"
                                        @change="changmessage"
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
            <el-tab-pane label="分派部门" name="tabDepartment" v-if="tabActionName==='tabDepartment'" style="min-height:550px">
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
                                        style="width: 745px;"
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
                                        style="width: 745px;"
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
                                        style="width: 745px;"
                                ></el-input>
                            </el-form-item>
                            <el-form-item label="短信"  prop="BMmessage"
                                          v-if="user.ename=='luwy@fsgs' || user.ename=='chenzx1@fsgs'">
                                <Cascader
                                        :options="BMmessageoptions"
                                        v-model="BMcheckList"
                                        style="width: 745px;"
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
                            <el-form-item label="抄送人员" prop="ccs">
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
                            <!--<el-form-item label="延时时间单位" prop="unit">-->
                                <!--<el-select v-model="delayForm.unit" placeholder="请选择延期时间单位" prop="department">-->
                                    <!--<el-option label="工作时" value="工作时"></el-option>-->
                                    <!--<el-option label="工作日" value="工作日"></el-option>-->
                                <!--</el-select>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col :xs="24" :sm="24" :md="12" :lg="8">-->
                            <!--<el-form-item label="延期时限" prop="limit">-->
                                <!--<el-input placeholder="请输入延期时限" v-model.number="delayForm.limit"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <el-form-item label="最终反馈延期天数：" prop="delaydate">
                            <!--<el-date-picker v-model="delayForm.delaydate" type="date"-->
                                                              <!--style="width: 100%"-->
                                                              <!--class="input-text" placeholder="选择延期日期"-->
                                                              <!--@change="changdatetime()"-->
                        <!--&gt;</el-date-picker>-->
                            <el-input label="最终反馈延期后的时间:"
                                      prop="limitdate"
                                      disabled="true"
                                      v-model="finaldelayday"
                                      class="input-text"
                            >
                                <template slot="append">自然日</template>
                            </el-input>
                        </el-form-item>
                            <!--<el-form-item label="立案延期后的时间" prop="delaydate" v-if="tasklist.isregistercase==null && tasklist.formtype=='03'">-->
                            <!--<el-input label="立案延期后的时间:"-->
                                      <!--prop="limitdate"-->
                                      <!--disabled="true"-->
                                      <!--v-model="tasklist.nextnodeid"-->
                                      <!--class="input-text"-->

                            <!--&gt;</el-input>-->
                        <!--</el-form-item>-->
                        </el-col>
                        <el-col>
                            <el-form-item label="延期原因" prop="reason" style="width: 80%">
                                <el-input
                                        type="textarea"
                                        :rows="4"
                                        placeholder="请输入延期原因（最少15字）"
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
                            <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                <el-form-item label="最终反馈延期相关附件" class="bt">
                                    <el-upload
                                            :auto-upload="false"
                                            :file-list="delayfile"
                                            ref="upload"
                                            :on-preview="previewFile"
                                            :data="this.requestMap"
                                            :on-progress="progress"
                                            :on-success="uploadSuccess"
                                            :before-upload="beforeUpload"
                                            :multiple="true"
                                            action="/crcs/filesUpload/upload"
                                            class="upload-demo"
                                            :on-change="onchange"
                                            :on-remove="handleRemove"
                                    >
                                        <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                        <el-button
                                        size="small"
                                        type="success"
                                        @click="submitUpload('upload')"
                                        >确认上传
                                        </el-button>
                                    </el-upload>
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
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="消费相关附件">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileLists"
                                        ref="upload"
                                        :data="this.requestMapCom"
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
                                            @click="submitUploadCom('uploadComplain')"
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
                        :rules="caseformRulesA"
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
                        ref="feedbackLeaderForm"
                        label-suffix="："
                        :rules="caseformRulesB"
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
        findUsersByDeptIdAndJurisdiction,
        getOptions,
        getOptionsOfDept,
        toProcessing
    } from "../../api/convenient12345";
    import {getMaterialLists, uploadFile,getMessagePerson,sendMessage,getSMSPerson,deleteUploadFile} from "../../api/api";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "../sldj/components/EconomyInfo";
    import ConsultationInfo from "../sldj/components/commommoduleInfo";
    import ComplaintmoduleInfo from "../sldj/components/ComplaintmoduleInfo"
    import Cascader from "../../components/cascader"
    import {
        byIdGetSuperiors,
        getArrayByStringId,
        byIdGetLabels
    } from "../../utils/form-util";
    import moment from "moment";
    import {myMixin} from "../../assets/mixin/mixin";

    export default {
        mixins: [myMixin],
        components: {
            Tracking,
            ComplaintmoduleInfo,
            EconomyInfo,
            ConsultationInfo,
            Cascader
        },
        data() {
            return {
                show:"true",
                delayfile:[],
                finaldelayday:30,
                messageoptions:[],
                BMmessageoptions:[],
                checkList:[],
                BMcheckList:[],
                options:[],
                unitsoptionsofdept:[],
                unitsoptionsmodelofdept:[],
                // 材料列表
                fileLists: [],
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
                opinionForm: {},
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking"
                ],
                fileUploadText: false,
                fileList: [],
                fileUploadText: "文件上传",
                loading: false,
                fileUploadVisible: false,
                commomform: null,
                complaintform: null,
                economyform: null,
                consultationform: null,
                handledemand: {},
                opinionList: [],
                registercase: false,
                Providetype1: "",
                Objclassify1: "",
                Complaintstype1: "",
                Entitytype1: "",
                Objectcategory1: "",
                Problemtype1: "",
                Illegalrules1: "",
                Businesstype1: "",
                Tortclassify1: "",
                isregisters: true,
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
                    disputemoney: 0,
                    mentalmoney: 0,
                    doublemoney: 0,
                    financialloss: 0,
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
                    casereason: ""
                },
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                },
                caseprocesformRules: {
                    ispursuit: [{required: true, message: "请选择是否销案"}],
                    subjectname: [{required: true, message: "请输入涉及主体"}],
                    subjecttype: [
                        {required: true, message: "请选择请选择涉及主体类别和类型"}
                    ],
                    isverified: [{required: true, message: "请输入属实情况"}],
                    verifiedopinion: [{required: true, message: "请输入调查情况"}],
                    updatedate: [{required: true, message: "请选择时间"}],
                    businessaddress: [{required: true, message: "请输入经营地址"}],
                    semaknama: [{required: true, message: "请输入核查经营名称"}],
                    semakbusinessaddress: [
                        {
                            required: true, validator: (rule, value, callback) => {
                                if (!value) {
                                    return callback(new Error('请输入核查经营地址'));
                                }
                                if (this.formType === 'complaintmodule') {
                                    if (!this.complaintform.region) {
                                        return callback(new Error('请选择区域'));
                                    }
                                } else if (this.formType === 'comeconomicmodule') {
                                    if (!this.economyform.region) {
                                        return callback(new Error('请选择区域'));
                                    }
                                }
                                callback();
                            }
                        }
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
                        {max: 500, message: "不能超出500字符", trigger: "blur"}
                    ],
                    handleopinion: [
                        {required: true, message: "请输入处理结果", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
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
                        {min: 15, message: "不能少于15个字", trigger: "blur"}
                    ],
                    // delaydate: [
                    //     {required: true, message: "请选择延期时间"},
                    // ],
                    postpone: [
                        {required: true, message: "请选延期处理人", trigger: "blur"},
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
                caseformRulesA: {
                    leader: [{required: true, message: "请选择领导"}]
                },
                caseformRulesB: {
                    leaders: [{required: true, message: "请选择区局处理人"}]
                },
                assignmentForm: {
                    unit: undefined,
                    ortherDept: [],
                    unitsoptionsmodel:[]
                },
                assignmentFormRules: {
                    unit: [
                        {required: true, message: "请选择成员单位", trigger: "blur"}
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
                exportLoading: false,
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
                },
                units: {
                    unitList: [],
                    deptList: []
                },
                newCompanyLists: [],
                scrollTop: 0,
                height: 100
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
        },
        methods: {
            getScroll() {
                this.scrollTop = this.scrollTop =
                    window.pageYOffset ||
                    document.documentElement.scrollTop ||
                    document.body.scrollTop ||
                    document.scrollingElement.scrollTop;
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
                window.open(`${process.env.Download_URL}` + url, "_blank")
            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            dateChanges(date) {
                this.caseprocesform.transferreddate = date;
            },


            triggerTab(value) {
                //消费表单处理反馈
                if (this.complaintform != null && this.complaintform != "") {
                    this.caseprocesform.subjectname = this.complaintform.subjectname,
                        this.caseprocesform.businessaddress = this.complaintform.subjectaddress;
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
                //经济表单处理反馈
                if (this.economyform != null && this.economyform != "") {
                    (this.caseprocesform.subjectname = this.economyform.subjectname),
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
                //咨询表单处理反馈
                if (this.consultationform != null && this.consultationform != "") {
                    (this.caseprocesform.subjectname = this.consultationform.subjectname),
                        (this.caseprocesform.cksubjectaddress = this.consultationform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.consultationform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.consultationform.subjectaddress);
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
                //通用表单处理反馈
                if (this.commomform != null && this.commomform != "") {
                    (this.caseprocesform.subjectname = this.commomform.subjectname),
                        (this.caseprocesform.cksubjectaddress = this.commomform.cksubjectaddress),
                        (this.caseprocesform.cksubjectname = this.commomform.cksubjectname),
                        (this.caseprocesform.businessaddress = this.commomform.subjectaddress);
                }
                this.tabActionName = value;
                if ("tabExtension" == value) {
                    this.delayApproval();
                } else if ("tabBack" == value) {
                    this.delayPersonnel();
                }
                if ("是" == this.caseprocesform.isregister) {
                    this.isregisters = true;
                } else {
                    this.isregisters = false;
                }
                if (this.tasklist.filingdate != null) {
                    this.assignmentForm.opinion = "受理时间：" + this.$moment(this.tasklist.taskstartdate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 立案时间：" + this.$moment(this.tasklist.filingdate).format("YYYY-MM-DD HH:mm:ss")
                    this.departmentForm.opinion = "受理时间：" + this.$moment(this.tasklist.taskstartdate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 立案时间：" + this.$moment(this.tasklist.filingdate).format("YYYY-MM-DD HH:mm:ss")
                    this.personnelForm.opinion = "受理时间：" + this.$moment(this.tasklist.taskstartdate).format("YYYY-MM-DD HH:mm:ss")
                        + " , 立案时间：" + this.$moment(this.tasklist.filingdate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    this.assignmentForm.opinion = "受理时间：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    this.departmentForm.opinion = "受理时间：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                    this.personnelForm.opinion = "受理时间：" + this.$moment(this.tasklist.assigndate).format("YYYY-MM-DD HH:mm:ss")
                }
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
            },
            changmessage(value){
              console.log(value)
            },

            triggerBus(value){
                if("first" === value){
                    this.tabActionName = "tabOpinionOne";
                }else if("tabBus" === value){
                    this.tabActionName = "tabOpinion";
                }
            },



            copyOpinion(value) {
                //反馈意见填写
                // return
                let validation = false;
                this.$refs.opinionForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let caseguid;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            caseguid = this.commomform.caseguid;
                        } else {
                            caseguid = this.consultationform.caseguid;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        caseguid = this.complaintform.caseguid;
                    } else if ("comeconomicmodule" === this.formType) {
                        caseguid = this.economyform.caseguid;
                    }
                    let params = {
                        user: this.user,
                        caseguid:caseguid,
                        // node: this.nodeStr,
                        // nextNodeId: "tabBus",
                        opinion: this.opinionForm.opinion,
                        type: value,
                        formtype:this.formType
                    };
                    toProcessing(params).then(data => {
                        if (data.code == 0) {
                            this.$message({
                                message: '提交成功！',
                                type: 'success',
                                duration: '1000'
                            });
                            this.$router.push("/wddb/bxtdb");
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
                if ("complaintmodule" === this.formType) {
                    this.$refs["caseprocesformD"].validate(valid => (validation = valid));
                    this.$refs["caseprocesformE"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["caseprocesformF"].validate(
                        valid => (validation = valid && validation)
                    );
                } else if ("comeconomicmodule" === this.formType) {
                    this.$refs["caseprocesformA"].validate(valid => (validation = valid));
                    this.$refs["caseprocesformB"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["caseprocesformC"].validate(
                        valid => (validation = valid && validation)
                    );
                } else {
                    this.$refs["caseprocesformG"].validate(valid => (validation = valid));
                }

                if (!validation) return;
                //账号为消保科,直接处理
                if (this.user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0') {
                    this.submitLoading = true;
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
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

                    //转换数组
                    for (let key in this.caseprocesform) {
                        if (this.caseprocesform[key] instanceof Array) {
                            if (this.caseprocesform[key].length > 0) {
                                this.caseprocesform[key] = this.caseprocesform[key].pop();
                            } else {
                                this.caseprocesform[key] = null;
                            }
                        }
                    }

                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "end",
                        data: data,
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
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
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
                } else {
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
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            data = this.consultationform;
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
                    }
                    let params;
                    if (this.user.aicPermissions.bmfwDispatch.sysMenuFiled == "bmfwDispatch") {
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
            onchange(file,fileList){},
            handleRemove(file, fileList) {
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 200 <= 1;
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
                 // this.requestMap.caseguid = this.commomform.caseguid;
                 this.requestMap.caseguid = this.$route.query.caseguid;
                this.$refs.upload.submit();
            },
            submitUploadEco(value) {
                this.requestMapEco.caseguid = this.economyform.caseguid;
                this.$refs.upload.submit();
            },
            submitUploadCom(value) {
                this.requestMapCom.caseguid = this.complaintform.caseguid;
                this.$refs.upload.submit();
            },
            progress(event, file, fileList) {
            },

            submitFeedback() {
                //处理反馈
                let validation;
                if ("commommodule" === this.formType) {
                    this.$refs["caseprocesformG"].validate(valid => (validation = valid));
                } else {
                    this.$refs["caseprocesformA"].validate(valid => (validation = valid));
                    this.$refs["caseprocesformB"].validate(
                        valid => (validation = valid && validation)
                    );
                    this.$refs["caseprocesformC"].validate(
                        valid => (validation = valid && validation)
                    );
                }
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
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
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
                        nextNodeId: "regionAudi",
                        data: data,
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
                this.$refs["feedbackLeaderForm"].validate(valid => (validation = valid));
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
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
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
                    //转换数组
                    for (let key in this.caseprocesform) {
                        if (this.caseprocesform[key] instanceof Array) {
                            if (this.caseprocesform[key].length > 0) {
                                this.caseprocesform[key] = this.caseprocesform[key].pop();
                            } else {
                                this.caseprocesform[key] = null;
                            }
                        }
                    }

                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "regionAudit",
                        data: data,
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
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
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
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
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
                    //转换数组
                    for (let key in this.caseprocesform) {
                        if (this.caseprocesform[key] instanceof Array) {
                            if (this.caseprocesform[key].length > 0) {
                                this.caseprocesform[key] = this.caseprocesform[key].pop();
                            } else {
                                this.caseprocesform[key] = null;
                            }
                        }
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "leaderApr",
                        data: data,
                        opinion: this.caseprocesform.handleopinion,
                        deptid: this.feedbackForm.leader,
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
                                if (window.opener) {
                                    window.opener.location.reload(); //刷新父窗口
                                }
                                setInterval(function () {
                                    window.close();
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
                    this.submitLoading = true;
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "applyDelay",
                        data: data,
                        deptid: this.delayForm.postpone,
                        opinion: this.delayForm.reason,
                        delayData: this.delayForm,
                        // delaytime:this.$moment(this.delayForm.delaydate).format("YYYY-MM-DD HH:mm:ss"),
                        finaldelayday:30
                    };
                    console.log(params)
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

            submitUpload(value){
                this.requestMap.caseguid=this.$route.query.caseguid;
                console.log(this.$route.query.caseguid)
                this.$refs.upload.submit();
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            download(url){
                window.open(`${process.env.Download_URL}` + url);
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
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetypelassify != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
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
                        phonelist:this.BMcheckList
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                //分派部门短信发送
                                let paras={
                                    phonelist:this.BMcheckList,
                                    tserialnum:this.tasklist.tserialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                sendMessage(paras).then(res=>{
                                    if(res.code === 0){
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
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
                    }
                    if (
                        this.caseprocesform.transferreddate != null &&
                        this.caseprocesform.transferreddate != ""
                    ) {
                        this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
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
                    if (
                        this.caseprocesform.providetypecodeid != null &&
                        this.caseprocesform.providetypecodeid != ""
                    ) {
                        this.caseprocesform.providetypecodeid = this.caseprocesform.providetypecodeid[
                        this.caseprocesform.providetypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
                            ].toString();
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
                        opinion: this.assignmentForm.opinion,
                        caseprocesform: this.caseprocesform,
                        orgid: this.assignmentForm.unit,
                        phonelist:this.checkList,
                        ortherDept: this.assignmentForm.ortherDept,//msx 抄送部门
                    };
                    console.log(params.ortherDept)
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {
                                //分派下级短信发送
                                let paras={
                                    phonelist:this.checkList,
                                    tserialnum:this.tasklist.tserialnum,
                                    caseguid:this.tasklist.caseguid,
                                }
                                sendMessage(paras).then(res=>{
                                    if(res.code === 0){
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
                    this.submitLoading = true;
                    let data;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            if (
                                this.commomform.entitytype != null &&
                                this.commomform.entitytype != ""
                            ) {
                                this.commomform.entitytype = this.Entitytype1;
                            }
                            data = this.commomform;
                        } else {
                            if (
                                this.consultationform.entitytype != null &&
                                this.consultationform.entitytype != ""
                            ) {
                                this.consultationform.entitytype = this.Entitytype1;
                            }
                            data = this.consultationform;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        if (
                            this.complaintform.objclassify != null &&
                            this.complaintform.objclassify != ""
                        ) {
                            this.complaintform.objclassify = this.Objclassify1;
                        }
                        if (
                            this.complaintform.complaintstype != null &&
                            this.complaintform.complaintstype != ""
                        ) {
                            this.complaintform.complaintstype = this.Complaintstype1;
                        }
                        if (
                            this.complaintform.providetype != null &&
                            this.complaintform.providetype != ""
                        ) {
                            this.complaintform.providetype = this.Providetype1;
                        }
                        data = this.complaintform;
                    } else if ("comeconomicmodule" === this.formType) {
                        if (
                            this.economyform.tortclassify != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.tortclassify = this.Tortclassify1;
                        }
                        if (
                            this.economyform.businesstype != null &&
                            this.economyform.businesstype != ""
                        ) {
                            this.economyform.businesstype = this.Businesstype1;
                        }
                        if (
                            this.economyform.illegalrules != null &&
                            this.economyform.illegalrules != ""
                        ) {
                            this.economyform.illegalrules = this.Illegalrules1;
                        }
                        if (
                            this.economyform.problemtype != null &&
                            this.economyform.problemtype != ""
                        ) {
                            this.economyform.problemtype = this.Problemtype1;
                        }
                        if (
                            this.economyform.objectcategory != null &&
                            this.economyform.tortclassify != ""
                        ) {
                            this.economyform.objectcategory = this.Objectcategory1;
                        }
                        data = this.economyform;
                    }
                    if (
                        this.caseprocesform.transferreddate != null &&
                        this.caseprocesform.transferreddate != ""
                    ) {
                        this.caseprocesform.transferreddate = this.caseprocesform.transferreddate.getTime();
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
                    if (
                        this.caseprocesform.providetypecodeid != null &&
                        this.caseprocesform.providetypecodeid != ""
                    ) {
                        this.caseprocesform.providetypecodeid = this.caseprocesform.providetypecodeid[
                        this.caseprocesform.providetypecodeid.length - 1
                            ].toString();
                    }
                    if (
                        this.caseprocesform.subjecttypecodeid != null &&
                        this.caseprocesform.subjecttypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjecttypecodeid[
                        this.caseprocesform.subjecttypecodeid.length - 1
                            ].toString();
                    }
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerPersonnel",
                        data: data,
                        opinion: this.personnelForm.opinion,
                        caseprocesform: this.caseprocesform,
                        deptid: this.personnelForm.leading,
                        ortherDept: this.personnelForm.ccs
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
                        console.log(data)
                        this.nodeStr = data.tasklist.nextnodeid;
                        if (data.caseprocesform != null) {
                            this.caseprocesform = data.caseprocesform;
                        }
                        this.customerinfo = data.customerinfo;
                        this.tasklist = data.tasklist;
                        this.opinionList = data.opinionList;
                        this.handledemand = data.handledemand;
                        console.log(this.tasklist.isregistercase)
                        if (code === 0) {
                            if (data.economyform != null) {
                                //经济表单
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = "经济违法行为";
                                this.economyform = data.economyform;
                                this.ordernumber = this.economyform.ordernumber;
                                if (
                                    this.economyform.objectcategory != null &&
                                    this.economyform.objectcategory != ""
                                ) {
                                    this.Objectcategory1 = this.economyform.objectcategory;
                                    this.economyform.objectcategory = this.economyform.objectcategory.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.objectcategory = this.economyform.objectcategory[
                                    this.economyform.objectcategory.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.problemtype != null &&
                                    this.economyform.problemtype != ""
                                ) {
                                    this.Problemtype1 = this.economyform.problemtype;
                                    this.economyform.problemtype = this.economyform.problemtype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.problemtype = this.economyform.problemtype[
                                    this.economyform.problemtype.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.illegalrules != null &&
                                    this.economyform.illegalrules != ""
                                ) {
                                    this.Illegalrules1 = this.economyform.illegalrules;
                                    this.economyform.illegalrules = this.economyform.illegalrules.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.illegalrules = this.economyform.illegalrules[
                                    this.economyform.illegalrules.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.businesstype != null &&
                                    this.economyform.businesstype != ""
                                ) {
                                    this.Businesstype1 = this.economyform.businesstype;
                                    this.economyform.businesstype = this.economyform.businesstype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.businesstype = this.economyform.businesstype[
                                    this.economyform.businesstype.length - 1
                                        ].toString();
                                }
                                if (
                                    this.economyform.tortclassify != null &&
                                    this.economyform.tortclassify != ""
                                ) {
                                    this.Tortclassify1 = this.economyform.tortclassify;
                                    this.economyform.tortclassify = this.economyform.tortclassify.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.economyform.tortclassify = this.economyform.tortclassify[
                                    this.economyform.tortclassify.length - 1
                                        ].toString();
                                }
                            } else if (data.complaintform != null) {
                                //消费投诉表单
                                this.formType = "complaintmodule";
                                this.formTypeStr = "消费投诉表单";
                                this.complaintform = data.complaintform;
                                this.ordernumber = this.complaintform.ordernumber;
                                if (
                                    this.complaintform.providetype != null &&
                                    this.complaintform.providetype != ""
                                ) {
                                    this.Providetype1 = this.complaintform.providetype;
                                    this.complaintform.providetype = this.complaintform.providetype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.complaintform.providetype = this.complaintform.providetype[
                                    this.complaintform.providetype.length - 1
                                        ].toString();
                                }
                                if (
                                    this.complaintform.objclassify != null &&
                                    this.complaintform.objclassify != ""
                                ) {
                                    this.Objclassify1 = this.complaintform.objclassify;
                                    this.complaintform.objclassify = this.complaintform.objclassify.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.complaintform.objclassify = this.complaintform.objclassify[
                                    this.complaintform.objclassify.length - 1
                                        ].toString();
                                }
                                if (
                                    this.complaintform.complaintstype != null &&
                                    this.complaintform.complaintstype != ""
                                ) {
                                    this.Complaintstype1 = this.complaintform.complaintstype;
                                    this.complaintform.complaintstype = this.complaintform.complaintstype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.complaintform.complaintstype = this.complaintform.complaintstype[
                                    this.complaintform.complaintstype.length - 1
                                        ].toString();
                                }
                            }
                            // else if (data.consultationform != null) {
                            //     //咨询
                            //     this.formType = "commommodule";
                            //     this.formTypeStr = "咨询表单";
                            //     this.consultationform = data.consultationform;
                            //     this.ordernumber = this.consultationform.ordernumber;
                            //
                            //     if (
                            //         this.consultationform.entitytype != null &&
                            //         this.consultationform.entitytype != ""
                            //     ) {
                            //         this.Entitytype1 = this.consultationform.entitytype;
                            //         this.consultationform.entitytype = this.consultationform.entitytype.split(
                            //             "-"
                            //         ); //将字符串格式转换成数组
                            //         this.consultationform.entitytype = this.consultationform.entitytype[
                            //         this.consultationform.entitytype.length - 1
                            //             ].toString();
                            //     }
                            // }
                            else if (data.commomform != null) {
                                //通用表单
                                this.formType = "commommodule";
                                this.formTypeStr = "咨询表单";
                                // this.formTypeStr = data.commomform.formtype;
                                this.commomform = data.commomform;
                                this.ordernumber = this.commomform.ordernumber;
                                if (
                                    this.commomform.entitytype != null &&
                                    this.commomform.entitytype != ""
                                ) {
                                    this.Entitytype1 = this.commomform.entitytype;
                                    this.commomform.entitytype = this.commomform.entitytype.split(
                                        "-"
                                    ); //将字符串格式转换成数组
                                    this.commomform.entitytype = this.commomform.entitytype[
                                    this.commomform.entitytype.length - 1
                                        ].toString();
                                }
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
            getLeaderList() {
                //请求领导列表
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
            //根据当前用户权限查询区局处理人员列表
            delayPersonnel() {
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
            //根据当前用户权限查询区局处理人员列表
            delayPersonnel() {
                let para = {
                    permission: "bmfwDispatch"
                };
                findUsersByPermission(para).then(res => {
                    this.leaderLists = res.data;
                });
            },
            handleChangesubjecttype1(array) {
                let label = this.getLabel(this.subjecttype1, array[array.length - 1]);
                this.caseprocesform.subjecttype = byIdGetLabels(
                    label,
                    this.subjecttype1
                ).join("-");
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
            //word导出
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
            //下级成员单位级联数据
            getOptionsData(){
                let params={}
                            getOptions(params).then(res=>{
                                console.log(res)
                                this.options=res;
                                for(var key in res){

                                }

                            })
                        },
            //抄送部门级联数据
            getOptionsOfDeptData(){
                let params={}
                        getOptionsOfDept(params).then(res=>{
                            console.log(res)
                            this.unitsoptionsofdept=res;
                        })
            },
            SearchMessagePerson(){
                let params={}
            console.log("5656")
                getSMSPerson(params).then(res=>{
                    console.log("33333")
                    console.log(res)
                    this.messageoptions=res
                    this.BMmessageoptions=res
                })
            },
            getfiledata(){
                // console.log("进来了")
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.$route.query.caseguid
                };
                console.log(params)
                getMaterialLists(params).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.delayfile.push(fileInfo);
                        }
                    }
                });
            }

        },
        created() {
            this.getBaseInfo();
            this.getLeaderList();
            this.findUnitList();
            this.findDepartmentList();
            this.findPersonnelList();
            this.getInfringementtype1();
            this.getInfringingmarks1();
            this.getMediationmethods1();
            this.getNoobligation1();
            this.getSubjecttype1();
            this.getOptionsData();
            this.getOptionsOfDeptData()
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            this.SearchMessagePerson()
            this.getfiledata()

        },
        mounted() {
            window.addEventListener("scroll", this.getScroll);
            //this.getMaterialLists();
        }
    };
</script>
<style lang="scss" scoped>
    /* 公共样式 */
    @mixin p20 {
        padding: 20px 0 !important;
    }

    @mixin mb10 {
        margin-bottom: 10px;
    }

    .w110 {
        width: 110px;
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
            padding: 5px 0 0px 0;
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
