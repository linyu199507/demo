<template>
    <section class="region_audit" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                @tab-click="flow"
                v-model="aictabs"
                v-loading="loading"
        >
            <el-row v-if="ismain && aictabs ==0 ">
                <el-col style="text-align: right;" :class="{'fixed':scrollTop>height}">
                    <el-button type="primary" @click="toReview('true')">处理反馈</el-button>
                    <el-button type="primary" @click="toReview('false')">退回</el-button>
                </el-col>
            </el-row>
            <el-tab-pane :label="formTypeStr" style="margin: 15px auto; ">
                <!--经济违法行为表单-->
                <!--<my-economy-info
                  :data="comeconomicmodule"
                  :task="tasklist"
                  :casedata="caseprocesform"
                  v-if="formType==='comeconomicmodule'"
                />-->
                <!--新的经济违法行为表单-->
                <new-my-economy-info
                        :comeconomicmodule="comeconomicmodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='comeconomicmodule'"
                />
                <!--咨询登记表单-->
                <!--<my-consult-info
                  :data="commommodule"
                  :task="tasklist"
                  :casedata="caseprocesform"
                  v-if="formType==='commommodule'"
                />-->
                <!--新的咨询登记表单-->
                <new-my-consult-info
                        :commommodule="commommodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='commommodule'"
                />
                <!--消费投诉登记-->
                <!--<my-consume-info
                  :data="complaintmodule"
                  :task="tasklist"
                  :casedata="caseprocesform"
                  v-if="formType==='complaintmodule'"
                />-->
                <!--新的消费投诉登记-->
                <new-my-consume-info
                        :complaintmodule="complaintmodule"
                        :task="tasklist"
                        :casedata="caseprocesform"
                        v-if="formType==='complaintmodule'"
                />
                <el-collapse class="accordion" v-model="processingCollapse">
                    <el-collapse-item name="tracking">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="12">办理跟踪</el-col>
                                <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                            </el-row>
                        </div>
                        <my-tracking :data="opinionList"/>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>
            <el-tab-pane v-if="isReviews" name="reviewDoms" :label="reviewForm.isPass">
                <el-form
                        :model="reviewForm"
                        label-width="180px"
                        ref="reviewForm"
                        label-suffix="："
                        :rules="reviewFormRules"
                >
                    <el-form-item label="是否通过" class="form_bt" prop="isPass" style="text-align: left;">
                        <div style="line-height: 35px">{{reviewForm.isPass}}</div>
                    </el-form-item>
                    <el-form-item
                            label="审核意见"
                            class="form_bt"
                            prop="resolution"
                            style="width: 80%"
                            color="black"
                            background-color="taansparent"
                            border-color="lightsteelblue"
                    >
                        <el-input
                                type="textarea"
                                :rows="4"
                                placeholder="请输入审核意见（3000字内）"
                                v-model="reviewForm.resolution"
                                class="mb20"
                        ></el-input>
                        <el-button @click="template()">意见模板</el-button>
                    </el-form-item>
                    <el-form-item label-width="0" style="text-align: center;">
                        <el-button type="primary" @click="toSubmit">确认</el-button>
                        <el-button @click="flow">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane v-if="isReview" name="reviewDom" :label="reviewForm.isPass">
                <el-collapse
                        v-model="registrationActiveNames"
                        class="accordion"
                        v-if="formType==='commommodule'"
                >
                    <el-collapse-item name="4">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="6">处理反馈</el-col>
                                <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="reviewForm"
                                label-width="180px"
                                ref="reviewForm"
                                :rules="reviewFormRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item
                                            label="反馈意见"
                                            class="form_bt"
                                            prop="resolution"
                                            style="width: 80%"
                                            color="black"
                                            background-color="taansparent"
                                            border-color="lightsteelblue"
                                    >
                                        <el-input
                                                type="textarea"
                                                :rows="4"
                                                placeholder="请输入审核意见（3000字内）"
                                                v-model="reviewForm.resolution"
                                        ></el-input>
                                        <el-button @click="template()">意见模板</el-button>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;width: 100%;">
                            <el-button type="primary" @click="toSubmit">确认</el-button>
                            <el-button @click="flow">取消</el-button>
                        </el-col>
                    </el-row>
                </el-collapse>
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
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体：" class="bt" prop="subjectname">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.subjectname"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体类别和类型：" class="bt" prop="subjecttypecodeid">
                                        <el-cascader
                                                filterable
                                                placeholder="请选择行业分类"
                                                :options="subjecttype1"
                                                v-model="caseprocesform.subjecttypecodeid"
                                                @change="handleChangesubjecttype1"
                                                :show-all-levels="false"
                                                class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="经营地址：" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.businessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营地址：" class="bt">
                                        <el-input
                                                placeholder="请输入"
                                                v-model="caseprocesform.semakbusinessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营名称：" class="bt" prop="semaknama">
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
                                    <el-form-item label="属实情况：" class="bt">
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
                                    <el-form-item label="调查情况:" class="bt">
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
                                    <el-form-item label="是否销案：" class="bt">
                                        <el-select
                                                placeholder="请选择"
                                                v-model="caseprocesform.ispursuit"
                                                class="input-text"
                                        >
                                            <el-option label="是" value="是"></el-option>
                                            <el-option abel="否" value="否"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="处理结果：" class="bt">
                                        <el-input
                                                type="textarea"
                                                :rows="4"
                                                placeholder="请输入审核意见（3000字内）"
                                                v-model="caseprocesform.handleopinion"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12" style="text-align:left">
                                    <el-form-item label="经济相关附件:" class="bt" style="width:100%;text-align:left">
                                        <el-upload
                                                :auto-upload="false"
                                                :file-list="fileListsEco"
                                                ref="uploadEconomy"
                                                :data="this.requestMap"
                                                :on-progress="progress"
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
                                                    @click="submitUpload('uploadEconomy')"
                                            >确认上传
                                            </el-button>
                                        </el-upload>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="4">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="6">审核意见</el-col>
                                <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="reviewForm"
                                label-width="180px"
                                ref="reviewForm"
                                :rules="reviewFormRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item
                                            label="是否通过"
                                            class="form_bt"
                                            prop="isPass"
                                            style="text-align: left;"
                                    >
                                        <div style="line-height: 35px">{{reviewForm.isPass}}</div>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item
                                            label="审核意见"
                                            class="form_bt"
                                            prop="resolution"
                                            style="width: 80%"
                                            color="black"
                                            background-color="taansparent"
                                            border-color="lightsteelblue"
                                    >
                                        <el-input
                                                type="textarea"
                                                :rows="4"
                                                placeholder="请输入审核意见（3000字内）"
                                                v-model="reviewForm.resolution"
                                        ></el-input>
                                        <el-button @click="template()">意见模板</el-button>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-row style="margin: 20px 0;">
                        <el-col style="text-align: center;width: 100%;">
                            <el-button type="primary" @click="toSubmit">确认</el-button>
                            <el-button @click="flow">取消</el-button>
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
                                ref="caseprocesformAA"
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体：" class="bt" prop="subjectname">
                                        <el-input
                                                placeholder="请输入涉及主体"
                                                v-model="caseprocesform.subjectname"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="涉及主体类别和类型：" class="bt" prop="subjecttypecodeid">
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
                                    <el-form-item label="经营地址：" class="bt" prop="businessaddress">
                                        <el-input
                                                placeholder="请输入经营地址"
                                                v-model="caseprocesform.businessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营地址：" class="bt" prop="semakbusinessaddress">
                                        <el-input
                                                placeholder="请输入核查经营地址"
                                                v-model="caseprocesform.semakbusinessaddress"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营名称：" class="bt" prop="semaknama">
                                        <el-input
                                                placeholder="请输入核查经营名称"
                                                v-model="caseprocesform.semaknama"
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
                                ref="caseprocesformBB"
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="行政调解文书号：" class="bt">
                                        <el-input
                                                placeholder="请输入行政调解文书号"
                                                v-model="caseprocesform.mediationnumber"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="侵权类型：" class="bt" prop="infringementtype">
                                        <el-cascader
                                                filterable
                                                placeholder="请选择涉侵权类型"
                                                :options="infringementtype1"
                                                v-model="caseprocesform.infringementtypecodeid"
                                                @change="handleChangeInfringementtype1"
                                                :show-all-levels="false"
                                                class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="欺诈标志：" class="bt" prop="infringingmarks">
                                        <el-cascader
                                                filterable
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
                                    <el-form-item label="调解方式：" class="bt" prop="mediationmethods">
                                        <el-cascader
                                                filterable
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
                                    <el-form-item label="未履行义务：" class="bt" prop="noobligation">
                                        <el-cascader
                                                filterable
                                                placeholder="请选择未履行义务"
                                                :options="noobligation1"
                                                v-model="caseprocesform.noobligationcodeid"
                                                @change="handleChangeNoobligation1"
                                                :show-all-levels="false"
                                                class="bt1"
                                        ></el-cascader>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">
                                                    <el-form-item label="调解结果：" class="bt" prop="resultsofmediation">
                                                        <el-select
                                                                placeholder="如选择其他视为调解不成功"
                                                                v-model="caseprocesform.resultsofmediation"
                                                                class="input-text"
                                                        >
                                                            <el-option label="调解成功" value="调解成功"></el-option>
                                                            <el-option label="调解不成功" value="调解不成功"></el-option>
                                                            <el-option label="其他" value="其他"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                </el-col>-->
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="争议金额：" class="bt" prop="disputemoney">
                                        <el-input
                                                placeholder="请输入争议金额"
                                                v-model.number="caseprocesform.disputemoney"
                                                class="input-text"
                                        >
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="精神赔偿金额：" class="bt" prop="mentalmoney">
                                        <el-input
                                                placeholder="请输入精神赔偿金额"
                                                v-model.number="caseprocesform.mentalmoney"
                                                class="input-text"
                                        >
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="加倍赔偿金额：" class="bt" prop="doublemoney">
                                        <el-input
                                                placeholder="请输入加倍赔偿金额"
                                                v-model.number="caseprocesform.doublemoney"
                                                class="input-text"
                                        >
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="挽回经济损失：" class="bt" prop="financialloss">
                                        <el-input
                                                placeholder="请输入挽回经济损失金额"
                                                v-model.number="caseprocesform.financialloss"
                                                class="input-text"
                                        >
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="调解结果：" class="bt" prop="resultsofmediation">
                                        <el-select
                                                placeholder="如选择其他视为调解不成功"
                                                v-model="caseprocesform.resultsofmediation"
                                                class="input-text"
                                        >
                                            <el-option label="调解成功" value="调解成功"></el-option>
                                            <el-option label="调解不成功" value="调解不成功"></el-option>
                                            <el-option label="其他" value="其他"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                                    <el-form-item label="办结日期：" class="bt" prop="transferreddate">
                                        <el-date-picker
                                                type="date"
                                                v-model="caseprocesform.transferreddate"
                                                class="input-text"
                                                style="width: 100%"
                                                value-format="yyyy-MM-dd"
                                                @change="dateChange"
                                                placeholder="选择办结日期"
                                        ></el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="调解情况:" class="bt" prop="mediationis">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 4, maxRows: 6}"
                                                placeholder="请输入调查情况(1200字以内)"
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
                                ref="caseprocesformCC"
                                :rules="caseprocesformRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="处理结果：" class="bt" prop="handleopinion">
                                        <el-input
                                                type="textarea"
                                                :rows="4"
                                                placeholder="请输入处理结果（3000字内）"
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
                                <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                    <el-form-item label="消费相关附件:" class="bt" style="text-align:left">
                                        <el-upload
                                                :auto-upload="false"
                                                :file-list="fileListsCom"
                                                ref="uploadCompalin"
                                                :data="this.requestMapCom"
                                                :on-progress="progress"
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
                                                    @click="submitUploadCom('uploadCompalin')"
                                            >确认上传
                                            </el-button>
                                        </el-upload>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-collapse-item name="4">
                        <div slot="title" @click.stop>
                            <el-row class="el-accordion">
                                <el-col :span="6">审核意见</el-col>
                                <el-col :span="18" style="color: #48576a;text-align: right;"></el-col>
                            </el-row>
                        </div>
                        <el-form
                                :model="reviewForm"
                                label-width="180px"
                                ref="reviewForm"
                                :rules="reviewFormRules"
                        >
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item
                                            label="是否通过"
                                            class="form_bt"
                                            prop="isPass"
                                            style="text-align: left;"
                                    >
                                        <div style="line-height: 35px">{{reviewForm.isPass}}</div>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item
                                            label="审核意见"
                                            class="form_bt"
                                            prop="resolution"
                                            style="width: 80%"
                                            color="black"
                                            background-color="taansparent"
                                            border-color="lightsteelblue"
                                    >
                                        <el-input
                                                type="textarea"
                                                :rows="4"
                                                placeholder="请输入审核意见（3000字内）"
                                                v-model="reviewForm.resolution"
                                        ></el-input>
                                        <el-button @click="template()">意见模板</el-button>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                    <el-form>
                        <el-row>
                            <el-col style="height: 15px;"></el-col>
                            <el-col style="text-align: center">
                                <el-form-item label-width="0" style="text-align: center;">
                                    <el-button type="primary" @click="toSubmit">确认</el-button>
                                    <el-button @click="flow">取消</el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse>
            </el-tab-pane>
        </el-tabs>
    </section>
</template>

<script>
    import {
        next,
        searchBaseInfo,
        findUsersByPermission,
        selectBaseCodeTree
    } from "../../../api/api";
    import {
        byIdGetSuperiors,
        byIdGetLabels,
        getArrayByStringId
    } from "../../../utils/form-util";

    import Tracking from "../../../components/Tracking";
    import EconomyInfoLocal from "../../acceptanceRegistration/EconomyInfoLocal";
    import consultInfoLocal from "../../acceptanceRegistration/consultInfoLocal";
    import ConsumeInfoLocal from "../../acceptanceRegistration/ConsumeInfoLocal";
    //引入新的组件
    import NewEconomyInfoLocal from "../../sldj/components/EconomyInfo";
    import NewconsultInfo from "../../sldj/components/commommoduleInfo";
    import NewComplaintmoduleInfo from "../../sldj/components/ComplaintmoduleInfo"
    import {myMixin} from "../../../assets/mixin/mixin";

    export default {
        mixins: [myMixin],
        components: {
            "my-tracking": Tracking,
            "my-economy-info": EconomyInfoLocal,
            "my-consult-info": consultInfoLocal,
            "my-consume-info": ConsumeInfoLocal,
            "new-my-economy-info": NewEconomyInfoLocal,
            "new-my-consult-info": NewconsultInfo,
            "new-my-consume-info": NewComplaintmoduleInfo,
        },
        data() {
            return {
                loading: true,
                formType: "",
                formTypeStr: "",
                processingCollapse: ["processing", "tracking"],
                opinionList: [],
                aictabs: "0",
                commommodule: {},
                tasklist: {},
                comeconomicmodule: {},
                complaintmodule: {},
                reviewForm: {
                    isPass: undefined,
                    resolution: "",
                    leader: ""
                },
                reviewFormRules: {
                    isPass: [{required: true, message: "请选择"}],
                    resolution: [
                        {required: true, message: "请输入处理意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    leader: [{required: true, message: "请选择区局处理人"}]
                },
                leaderLists: [],
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
                    applealtocase: "",
                    caseno: "",
                    casedate: "",
                    casereason: "",
                    infringingmarkscodeid: []
                },
                caseprocesformRules: {
                    ispursuit: [{required: true, message: "请选择是否销案"}],
                    handleopinion: [
                        {required: true, message: "请输入审核意见", trigger: "blur"},
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入涉及主体", trigger: "blur"},
                        {max: 100, message: "不能超出100字符", trigger: "blur"}
                    ],
                    subjecttype: [{required: true, message: "请选择涉及主体类别和类型"}],
                    businessaddress: [
                        {required: true, message: "请输入经营地址", trigger: "blur"},
                        {max: 100, message: "不能超出100字符", trigger: "blur"}
                    ],
                    semakbusinessaddress: [
                        {required: true, message: "请输入核查经营地址", trigger: "blur"},
                        {max: 100, message: "不能超出100字符", trigger: "blur"}
                    ],
                    semaknama: [
                        {required: true, message: "请输入核查经营名称", trigger: "blur"},
                        {max: 100, message: "不能超出100字符", trigger: "blur"}
                    ],
                    infringementtype: [{required: true, message: "请选择侵权类型"}],
                    /*infringingmarks: [
                                        { required: true, message: "请选择欺诈标志" },
                                    ],*/
                    mediationmethods: [{required: true, message: "请选择调解方式"}],
                    /*noobligation: [
                                        { required: true, message: "请选择未履行义务", trigger: "change" },
                                    ],*/
                    resultsofmediation: [
                        {
                            required: true,
                            message: "如选择其他视为调解不成功",
                            trigger: "blur"
                        },
                        {max: 100, message: "不能超出100字符", trigger: "blur"}
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
                        {max: 1200, message: "不能超出1200字符", trigger: "blur"}
                    ],
                    handleopinion: [
                        {
                            required: true,
                            message: "请输入处理结果（3000字内）",
                            trigger: "blur"
                        },
                        {max: 3000, message: "不能超出3000字符", trigger: "blur"}
                    ],
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
                        {max: 30, message: "不能超出3000字符", trigger: "blur"}
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
                        {max: 500, message: "不能超出3000字符", trigger: "blur"}
                    ]
                },
                isReview: false,
                isReviews: false,
                ismain: true,
                registrationActiveNames: ["1", "2", "3", "4", "5"],
                registercase: false,
                requestMapCom: {
                    caseguid: ""
                },
                entitytype1: [], //市场主体类型
                infringementtype1: [], //侵权类型下拉框选项
                subjecttype1: [], //涉及主体类别和类型下拉框选项
                infringingmarks1: [], //欺诈标志下拉框选项
                mediationmethods1: [], //调解方式下拉框选项
                noobligation1: [], //未履行义务下拉框选项
                fileLists: [],
                fileListsCom: [],
                fileListsEco: [],
                requestMap: {
                    caseguid: ""
                }
            };
        },

        methods: {
            template() {
                let isPass = this.reviewForm.isPass;
                if (isPass == "审核通过") {
                    this.reviewForm.resolution =
                        "经审核,材料齐全且相关信息正确无误,予以通过处理";
                } else {
                    this.reviewForm.resolution =
                        "经审核,不予以通过处理,原因如下:1.材料缺乏,无法提供证明 2.提供信息有误,请重新核查";
                }
            },

            leaderChange() {
                // let user = localStorage.getItem("user");
                // let users = JSON.parse(user);
                let para = {
                    permission: "delayApproval"
                };
                findUsersByPermission(para).then(res => {
                    this.leaderLists = res.data;
                });
            },

            flow() {
                this.isReview = false;
                this.isReviews = false;
                this.aictabs = "0";
            },
            toReview(b) {
                if ("true" == b) {
                    this.isReview = true;
                    this.isReviews = false;
                    this.reviewForm.isPass = "审核通过";
                    this.aictabs = "reviewDom";
                } else {
                    this.isReviews = true;
                    this.isReview = false;
                    this.reviewForm.isPass = "审核不通过";
                    this.aictabs = "reviewDoms";
                }
                this.leaderChange();
            },
            toSubmit() {
                let node = this.$route.query.node;
                this.$refs.reviewForm.validate(valid => {
                    if (!valid) return;
                    this.$confirm("请确定无误提交?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                        let data;
                        let user = localStorage.getItem("user");
                        if ("commommodule" === this.formType) {
                            data = this.commommodule;
                        } else if ("complaintmodule" === this.formType) {
                            if (
                                this.complaintmodule.entitytype != null &&
                                this.complaintmodule.entitytype != ""
                            ) {
                                this.complaintmodule.entitytype = this.entitytype2;
                            }
                            if (
                                this.complaintmodule.servicetype != null &&
                                this.complaintmodule.servicetype != ""
                            ) {
                                this.complaintmodule.servicetype = this.servicetype1;
                            }
                            if (
                                this.complaintmodule.complaintstype != null &&
                                this.complaintmodule.complaintstype != ""
                            ) {
                                this.complaintmodule.complaintstype = this.complaintstype1;
                            }
                            data = this.complaintmodule;
                        } else if ("comeconomicmodule" === this.formType) {
                            if (
                                this.comeconomicmodule.providetype != null &&
                                this.comeconomicmodule.providetype != ""
                            ) {
                                this.providetype1 = this.comeconomicmodule.providetype;
                            }
                            if (
                                this.comeconomicmodule.objectcategory != null &&
                                this.comeconomicmodule.objectcategory != ""
                            ) {
                                this.objectcategory1 = this.comeconomicmodule.objectcategory;
                            }
                            if (
                                this.comeconomicmodule.reportprovidelb != null &&
                                this.comeconomicmodule.reportprovidelb != ""
                            ) {
                                this.reportprovidelb1 = this.comeconomicmodule.reportprovidelb;
                            }
                            if (
                                this.comeconomicmodule.illegalrules != null &&
                                this.comeconomicmodule.illegalrules != ""
                            ) {
                                this.illegalrules1 = this.comeconomicmodule.illegalrules;
                            }
                            if (
                                this.comeconomicmodule.industryname != null &&
                                this.comeconomicmodule.industryname != ""
                            ) {
                                this.industryname1 = this.comeconomicmodule.industryname;
                            }
                            data = this.comeconomicmodule;
                        }
                        for (let key in this.caseprocesform) {
                            if (this.caseprocesform[key] instanceof Array) {
                                if (this.caseprocesform[key].length > 0) {
                                    this.caseprocesform[key] = this.caseprocesform[key].pop();
                                } else {
                                    this.caseprocesform[key] = null;
                                }
                            }
                        }

                        let para;
                        if ("审核通过" == this.reviewForm.isPass) {
                            para = {
                                user: JSON.parse(user),
                                node: node,
                                formType: this.formType,
                                caseprocesform: this.caseprocesform,
                                data,
                                nextNodeId: "end",
                                opinion: this.reviewForm.resolution,
                                isEnd: "1"
                            };
                        } else {
                            para = {
                                user: JSON.parse(user),
                                node: node,
                                formType: this.formType,
                                data,
                                nextNodeId: "superiorProcessing",
                                opinion: this.reviewForm.resolution
                            };
                        }
                        next(para).then(data => {
                            if (data.code === 0) {
                                this.$message({
                                    message: "提交成功！",
                                    type: "success",
                                    duration: "1000"
                                });
                                this.$router.push("/wddb/bxtdb");
                            }
                        });
                    });
                });
            },
            //选择诉转案，则显示立案号等选项
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
            dateChange(date) {
                this.caseprocesform.transferreddate = date
            },
            dateChangeE(date) {
                this.caseprocesform.casedate = date;
            },
            progress(event, file, fileList) {
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url, "_blank")
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
                // console.log(file.size / 1024 / 1024 / 20);
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
            submitUploadCom(value) {
                this.requestMapCom.caseguid = this.$route.query.caseguid;
                this.$refs[value].submit();
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
            //获取级联选择器下级菜单
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

            //涉及主体类别和类型级联数据
            getSubjecttype() {
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
                    this.subjecttype1 = this.data; //业务分类
                    if (
                        this.complaintmodule.entitytypecodeid != null &&
                        this.complaintmodule.entitytypecodeid != ""
                    ) {
                        this.caseprocesform.subjecttypecodeid = getArrayByStringId(
                            this.complaintmodule.entitytypecodeid,
                            this.subjecttype1
                        );
                    }
                });
            },

            //欺诈标志级联数据
            getInfringingmarks() {
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
                    if (
                        this.caseprocesform.infringingmarkscodeid != null &&
                        this.caseprocesform.infringingmarkscodeid != ""
                    ) {
                        this.caseprocesform.infringingmarkscodeid = getArrayByStringId(
                            this.caseprocesform.infringingmarkscodeid,
                            this.infringingmarks1
                        );
                    } else {
                        this.caseprocesform.infringingmarkscodeid = [];
                    }
                });
            },
            //侵权类型级联数据
            getInfringementtype() {
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
                    if (
                        this.caseprocesform.infringementtypecodeid != null &&
                        this.caseprocesform.infringementtypecodeid != ""
                    ) {
                        this.caseprocesform.infringementtypecodeid = getArrayByStringId(
                            this.caseprocesform.infringementtypecodeid,
                            this.infringementtype1
                        );
                    }
                });
            },
            //调解方式级联数据
            getMediationmethods() {
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
                    if (
                        this.caseprocesform.mediationmethodscodeid != null &&
                        this.caseprocesform.mediationmethodscodeid != ""
                    ) {
                        this.caseprocesform.mediationmethodscodeid = getArrayByStringId(
                            this.caseprocesform.mediationmethodscodeid,
                            this.mediationmethods1
                        );
                    }
                });
            },
            //市场主体类型下拉框级联数据
            getEntitytype1() {
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
                    this.entitytype1 = this.data;
                });
            },
            //未履行义务级联数据
            getNoobligation() {
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
                    if (
                        this.caseprocesform.noobligationcodeid != null &&
                        this.caseprocesform.noobligationcodeid != ""
                    ) {
                        this.caseprocesform.noobligationcodeid = getArrayByStringId(
                            this.caseprocesform.noobligationcodeid,
                            this.noobligation1
                        );
                    }
                });
            }
        },
        mounted() {
            // this.getSubjecttype();
            // this.getInfringingmarks();
            // this.getInfringementtype();
            // this.getMediationmethods();
            // this.getNoobligation();
        },
        created() {
            if ("0" == this.$route.query.ismain) {
                this.ismain = false;
            }
            //获取id，否则跳转
            let {id, caseguid} = this.$route.query;
            if (!id || !caseguid) {
                this.$router.push("/wddb/bxtdb");
                return;
            }
            let systemsource = "01";
            // //获取工单信息
            searchBaseInfo({id, caseguid, systemsource})
                .then(res => {
                    if (res.code === 0) {
                        if (res.data.caseprocesform != null) {
                            this.caseprocesform = res.data.caseprocesform;
                            if (this.caseprocesform.applealtocase == "1") {
                                this.registercase = true;
                            }
                        }
                        if (res.data.economyform != null) {
                            //经济表单
                            this.formType = "comeconomicmodule";
                            this.formTypeStr = "经济违法行为表单";
                            this.comeconomicmodule = res.data.economyform;
                            if (
                                this.comeconomicmodule.providetype != null &&
                                this.comeconomicmodule.providetype != ""
                            ) {
                                this.providetype1 = this.comeconomicmodule.providetype;
                                this.comeconomicmodule.providetype = this.comeconomicmodule.providetype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.comeconomicmodule.providetype = this.comeconomicmodule.providetype[
                                this.comeconomicmodule.providetype.length - 1
                                    ].toString();
                            }
                            if (
                                this.comeconomicmodule.objectcategory != null &&
                                this.comeconomicmodule.objectcategory != ""
                            ) {
                                this.objectcategory1 = this.comeconomicmodule.objectcategory;
                                this.comeconomicmodule.objectcategory = this.comeconomicmodule.objectcategory.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.comeconomicmodule.objectcategory = this.comeconomicmodule.objectcategory[
                                this.comeconomicmodule.objectcategory.length - 1
                                    ].toString();
                            }
                            if (
                                this.comeconomicmodule.reportprovidelb != null &&
                                this.comeconomicmodule.reportprovidelb != ""
                            ) {
                                this.reportprovidelb1 = this.comeconomicmodule.reportprovidelb;
                                this.comeconomicmodule.reportprovidelb = this.comeconomicmodule.reportprovidelb.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.comeconomicmodule.reportprovidelb = this.comeconomicmodule.reportprovidelb[
                                this.comeconomicmodule.reportprovidelb.length - 1
                                    ].toString();
                            }
                            if (
                                this.comeconomicmodule.illegalrules != null &&
                                this.comeconomicmodule.illegalrules != ""
                            ) {
                                this.illegalrules1 = this.comeconomicmodule.illegalrules;
                                this.comeconomicmodule.illegalrules = this.comeconomicmodule.illegalrules.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.comeconomicmodule.illegalrules = this.comeconomicmodule.illegalrules[
                                this.comeconomicmodule.illegalrules.length - 1
                                    ].toString();
                            }
                            if (
                                this.comeconomicmodule.industryname != null &&
                                this.comeconomicmodule.industryname != ""
                            ) {
                                this.industryname1 = this.comeconomicmodule.industryname;
                                this.comeconomicmodule.industryname = this.comeconomicmodule.industryname.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.comeconomicmodule.industryname = this.comeconomicmodule.industryname[
                                this.comeconomicmodule.industryname.length - 1
                                    ].toString();
                            }
                        } else if (res.data.complaintform != null) {
                            //消费投诉表单
                            this.formType = "complaintmodule";
                            this.formTypeStr = "消费投诉表单";
                            this.complaintmodule = res.data.complaintform;
                            if (
                                this.complaintmodule.entitytype != null &&
                                this.complaintmodule.entitytype != ""
                            ) {
                                this.entitytype2 = this.complaintmodule.entitytype;
                                this.complaintmodule.entitytype = this.complaintmodule.entitytype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.complaintmodule.entitytype = this.complaintmodule.entitytype[
                                this.complaintmodule.entitytype.length - 1
                                    ].toString();
                            }
                            if (
                                this.complaintmodule.servicetype != null &&
                                this.complaintmodule.servicetype != ""
                            ) {
                                this.servicetype1 = this.complaintmodule.servicetype;
                                this.complaintmodule.servicetype = this.complaintmodule.servicetype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.complaintmodule.servicetype = this.complaintmodule.servicetype[
                                this.complaintmodule.servicetype.length - 1
                                    ].toString();
                            }
                            if (
                                this.complaintmodule.complaintstype != null &&
                                this.complaintmodule.complaintstype != ""
                            ) {
                                this.complaintstype1 = this.complaintmodule.complaintstype;
                                this.complaintmodule.complaintstype = this.complaintmodule.complaintstype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.complaintmodule.complaintstype = this.complaintmodule.complaintstype[
                                this.complaintmodule.complaintstype.length - 1
                                    ].toString();
                            }
                        } else if (res.data.consultationform != null) {
                            //咨询登记
                            this.formType = "commommodule";
                            this.formTypeStr = "咨询登记";
                            this.commommodule = res.data.consultationform;
                            if (
                                this.commommodule.entitytype != null &&
                                this.commommodule.entitytype != ""
                            ) {
                                this.Entitytype1 = this.commommodule.entitytype;
                                this.commommodule.entitytype = this.commommodule.entitytype.split(
                                    "-"
                                ); //将字符串格式转换成数组
                                this.commommodule.entitytype = this.commommodule.entitytype[
                                this.commommodule.entitytype.length - 1
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
                        this.opinionList = res.data.opinionList;
                        this.tasklist = res.data.tasklist;
                        this.loading = false;
                    }
                    this.getSubjecttype();
                    this.getInfringingmarks();
                    this.getInfringementtype();
                    this.getMediationmethods();
                    this.getNoobligation();
                })
                .catch(error => {
                    console.error(error);
                    this.$message({
                        message: "请求失败",
                        type: "error"
                    });
                });
        }
    };
</script>
<style lang="scss" scoped>
    .region_audit {
        overflow-x: hidden;

        .el-table__body-wrapper {
            overflow: hidden;
        }

        .bt {
            margin: 10px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .bts {
            margin: 10px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .st {
            margin: 10px auto;
            font-size: 13px;
            /*text-align: center;*/
            margin-left: 15px;
        }

        .br {
            padding-bottom: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }

        .el-tabs .el-form .el-row {
            margin-left: 0;
            margin-right: 0;
        }
    }
</style>
