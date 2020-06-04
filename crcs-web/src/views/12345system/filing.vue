<!--立案处理-->
<template>
    <section class="filing">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane :label="formTypeStr" name="tabMainContent">
                <div style="text-align: right;">
                    <!--立案处理-->
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
                    <el-button type="primary" @click="triggerTab('tabFiling')">立案处理</el-button>
                    <el-button
                            v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabAssignment')"
                    >分派下级
                    </el-button>
                    <el-button
                            v-if="(user.regionID!='440681' && user.aicPermissions.dealdepartment&&nodeStr!=='moveToLowerDept'&& nodeStr!=='moveToLowerPersonnel'&&departmentList.length>0 && this.tasklist.fromnodeid !=='moveToLowerPersonnel')
                            ||user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'"
                            type="primary"
                            @click="triggerTab('tabDepartment')"
                    >分派部门
                    </el-button>
                    <el-button
                            v-if="user.regionID =='440681' && user.unitID !== 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0' && nodeStr!=='moveToLowerPersonnel'"
                            type="primary"
                            @click="triggerTab('tabPersonnel')"
                    >分派人员
                    </el-button>
                </div>
                <br/>
                <el-collapse v-model="collapseValues" class="accordion">
                    <!-- 定单信息 -->
                    <el-collapse-item name="orderInfo">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>1.</em> 定单信息
                                </el-col>
                                <el-col :span="10" class="right">工单号：{{tasklist.tserialnum}}</el-col>
                            </el-row>
                        </div>
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">接听坐席：</el-col>
                                <el-col :span="16">{{customerinfo.seatsname}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">登记时间：</el-col>
                                <el-col :span="16">{{formatDate(customerinfo.seatsregistrationtime)}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">性别：</el-col>
                                <el-col :span="16">{{customerinfo.seatssex}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">语种：</el-col>
                                <el-col :span="16">{{customerinfo.seatslanguage}}</el-col>
                            </el-col>
                        </el-row>
                    </el-collapse-item>
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>2.</em> 表单内容
                                </el-col>
                                <!--<el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>-->
                            </el-row>
                        </div>
                        <!--经济违法信息内容 -->
                        <economyInfo v-if="economyform" :economyform="economyform" :tasklist="tasklist"/>
                    </el-collapse-item>
                    <!-- 处办要求 -->
                    <el-collapse-item name="officeRequirements">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>3.</em> 处办要求
                                </el-col>
                                <el-col :span="10" class="right"></el-col>
                            </el-row>
                        </div>
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应受理时限：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>
                            </el-col>
                            <!--                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">-->
                            <!--                                <el-col :span="8" class="label">受理时间：</el-col>-->
                            <!--                                <el-col :span="16">{{formatDate(handledemand.accepttime)}}</el-col>-->
                            <!--                            </el-col>-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType == 'comeconomicmodule'">
                                <el-col :span="8" class="label">应立案时限：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.caseputtime)}}</el-col>
                            </el-col>
                            <!--                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="formType == 'comeconomicmodule'">-->
                            <!--                                <el-col :span="8" class="label">立案时间：</el-col>-->
                            <!--                                <el-col :span="16">{{formatDate(handledemand.caseputtimesurplus)}}</el-col>-->
                            <!--                            </el-col>-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col" v-if="handledemand.resulttime != '' || handledemand.resulttime != null">
                                <el-col :span="8" class="label">应反馈时间：</el-col>
                                <el-col :span="16">{{formatDate(handledemand.resulttime)}}</el-col>
                            </el-col>
                        </el-row>

                        <el-row>
                            <!--msx-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col2">
                                <el-upload
                                        :auto-upload="false"
                                        :file-list="fileLists"
                                        ref="upload"
                                        :on-preview="previewFile"
                                        :data="this.requestMap"
                                        :on-success="uploadSuccess"
                                        :before-upload="beforeUpload"
                                        :multiple="true"
                                        action="/crcs/filesUpload/upload"
                                        class="upload-demo"
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
                            </el-col>
                        </el-row>

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
            <!-- 立案处理 -->
            <el-tab-pane label="立案处理" name="tabFiling" v-if="tabActionName==='tabFiling'">
                <el-collapse v-model="collapseFilingValues" class="accordion">
                    <!-- 涉及主体信息 -->
                    <el-collapse-item name="involving">
                        <div slot="title" @click.stop>
                            <el-row>
                                <el-col :span="12" class="text_bold">涉及主体信息</el-col>
                                <!-- <el-col :span="10" class="right">订单号：{{ordernumber}}</el-col> -->
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
                                    <el-form-item label="主体类别和类型" prop="subjectTypeValue">
                                        <el-cascader filterable
                                                     placeholder="请选择"
                                                     :options="subjectTypeList"
                                                     v-model="caseprocesform.subjectTypeValue"
                                                     style="width:100%;font-weight:normal"
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
                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">
                                  <el-form-item label="核查经营地址" prop="cksubjectaddress" class="bt">
                                    <el-input
                                      placeholder="请输入"
                                      v-model="caseprocesform.cksubjectaddress"
                                      class="input-text"
                                    ></el-input>
                                  </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                  <el-form-item label="核查经营名称" class="bt" prop="cksubjectname">
                                    <el-input
                                      placeholder="请输入"
                                      v-model="caseprocesform.cksubjectname"
                                      class="input-text"
                                    ></el-input>
                                  </el-form-item>
                                </el-col>-->

                                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                    <el-form-item label="核查经营地址" prop="semakbusinessaddress" class="bt">
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
                                    <el-form-item label="核查经营名称" prop="semaknama" class="bt">
                                        <el-input
                                                placeholder="请输入核查经营名称"
                                                v-model="caseprocesform.semaknama"
                                                class="input-text"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :xs="24" :sm="24" :md="12" :lg="12">-->
                                    <!--<el-form-item label="核查分类：" prop="classification" class="bt">-->
                                        <!--<el-select-->
                                                <!--placeholder="请选择核查分类"-->
                                                <!--v-model="caseprocesform.classification"-->
                                                <!--class="input-text"-->
                                        <!--&gt;-->
                                            <!--<el-option label="研制" value="研制"></el-option>-->
                                            <!--<el-option label="生产" value="生产"></el-option>-->
                                            <!--<el-option label="流通" value="流通"></el-option>-->
                                            <!--<el-option label="服务" value="服务"></el-option>-->
                                            <!--<el-option label="餐饮服务/使用" value="餐饮服务/使用"></el-option>-->
                                            <!--<el-option label="其他" value="其他"></el-option>-->
                                        <!--</el-select>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
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
                                                @change="changeLimitdate(caseprocesform.isregister)"
                                        >
                                            <el-option label="是" value="是"></el-option>
                                            <el-option label="否" value="否"></el-option>
                                        </el-select>
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
                                <el-col :xs="24" :sm="24" :md="12" :lg="12" :key="caseprocesform.limitdate">
                                    <el-form-item label="处理期限" prop="limitdate">
                                        <el-input
                                                disabled="true"
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
                                </el-col>
                                <el-col :xs="24" :sm="12" :md="8" :lg="24">
                                    <el-form-item label="经济相关附件：" class="bt">
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
                </el-collapse>
                <div class="btn">
                    <el-button type="primary" @click="submitFiling" :loading="submitLoading">确定</el-button>
                    <el-button @click="cancelTab">取消</el-button>
                </div>
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
        </el-tabs>
    </section>
</template>

<script>
    import {searchBaseInfo, nextWork,findUsersByOrgAndPermission,findUsersByDeptIdAndJurisdiction,
        findOrgDeptByOrgId,findOrgUnitByRegionId,getMaterialLists,findUsersByDeptIdAndPermission} from "../../api/convenient12345";
    import Tracking from "../../components/Tracking";
    import EconomyInfo from "./components/EconomyInfo";
    import Cascader from "../../components/cascader"
    import {
        byIdGetSuperiors,
        byIdGetLabel,
        createCascaderOptions
    } from "../../utils/form-util";
    import {selectBaseCodeTree,sendMessage,getSMSPerson,deleteUploadFile} from "../../api/api";

    export default {
        components: {
            Tracking,
            EconomyInfo,
            Cascader
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
                exportLoading: false,
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
                collapseFilingValues: ["involving", "initial", "case"],
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
                loading: false,
                commomform: undefined,
                complaintform: undefined,
                economyform: {},
                consultationform: undefined,
                handledemand: {},
                opinionList: [],
                customerinfo: {},
                tasklist: {},
                ordernumber: "",
                caseprocesform: {
                    limitdate:60,
                    isregister:"是",
                },
                caseprocesformRules: {
                    classification: [{required: true, message: "请选择核查分类"}],
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
                    subjectTypeValue: [
                        {
                            type: "array",
                            required: true,
                            message: "请选择主体类别和类型",
                            trigger: "change"
                        }
                    ],
                    semaknama: [
                        {required: true, message: "请输入核查经营名称", trigger: "blur"}
                    ],
                    cksubjectaddress: [
                        {required: true, message: "请输入核查经营地址", trigger: "blur"}
                    ],
                    businessaddress: [
                        {required: true, message: "请输入经营地址", trigger: "blur"}
                    ],
                    limitdate: [
                        {type: "integer",required: true, message: "处理期限只能是整数", trigger: "blur"},
                        {
                            type: "integer",
                            max: 100,
                            min: 1,
                            message: "不能大于100与小于1",
                            trigger: "blur"
                        }
                    ],
                    semakbusinessaddress:[{required: true,  trigger: "blur",
                    validator:(rule, value, callback)=>{
                        if(!value){
                            return callback(new Error('请输入核查经营地址'));
                        }
                        if(!this.economyform.region){
                            return callback(new Error('请选择区域'));
                        }
                        callback();
                    }}]
                },
                submitLoading: false,
                subjectTypeList: [],
                unitList: [],
                departmentList: [],
            };
        },
        methods: {
            changeLimitdate(value){
                if(value != "" && value != null){
                    this.caseprocesform.limitdate = 60
                }
            },
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
                // console.log(file)
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

            submitUpload() {
                this.requestMap.caseguid = this.economyform.caseguid;
                this.$refs.upload.submit();
            },

            handleRemove(file, fileList) {
                console.log("ghjkbn")
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
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
                if("tabFiling" == value){
                    this.caseprocesform.subjectname = this.economyform.subjectname
                    this.caseprocesform.businessaddress = this.economyform.subjectaddress
                    this.caseprocesform.cksubjectaddress = this.economyform.cksubjectaddress
                    this.caseprocesform.cksubjectname = this.economyform.cksubjectname
                }
                // this.assignmentForm.opinion = "处理期限：" + this.$moment(this.tasklist.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                if("tabAssignment" == value && "tabDepartment" == value){
                    this.assignmentForm.opinion = "受理时间：" + this.$moment(this.tasklist.taskstartdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 立案时间：" + this.$moment(this.economyform.filingcasesdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 反馈时间：" + this.$moment(this.economyform.feedbackdate).format("YYYY-MM-DD HH:mm:ss");
                    this.departmentForm.opinion = "受理时间：" + this.$moment(this.economyform.acceptdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 立案时间：" + this.$moment(this.economyform.filingcasesdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 反馈时间：" + this.$moment(this.economyform.feedbackdate).format("YYYY-MM-DD HH:mm:ss");
                    this.personnelForm.opinion  = "受理时间：" + this.$moment(this.economyform.acceptdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 立案时间：" + this.$moment(this.economyform.filingcasesdate).format("YYYY-MM-DD HH:mm:ss")
                    // + " , 反馈时间：" + this.$moment(this.economyform.feedbackdate).format("YYYY-MM-DD HH:mm:ss");
                }
            },
            async listSubjectType() {
                let params = {
                    type: "enterprise12345"
                };
                let res = await selectBaseCodeTree(params);
                this.subjectTypeList = createCascaderOptions(res.data.data);
            },
            cancelTab() {
                this.tabActionName = "tabMainContent";
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
                    this.caseprocesform.subjecttypecodeid = this.caseprocesform.subjectTypeValue[
                    this.caseprocesform.subjectTypeValue.length - 1
                        ];
                    this.caseprocesform.subjectTypeValue = ""
                    this.caseprocesform.subjecttype = byIdGetLabel(
                        this.caseprocesform.subjecttypecodeid,
                        this.subjectTypeList
                    );
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
                        caseprocesform: this.caseprocesform
                    };
                    nextWork(params)
                        .then(data => {
                            if (data.code === 0) {

                                this.$message({
                                    message: '提交成功！',
                                    type: 'success',
                                    duration: '1000'
                                });
                                let params = {
                                    id: this.$route.query.id,
                                    caseguid: this.$route.query.caseguid,
                                    ismain: "1"
                                };
                                if (this.caseprocesform.isregister === "是") {
                                    this.$router.push({
                                        path: "/listDetail/clfpysth",
                                        query: params
                                    });
                                } else {
                                    this.$router.push({
                                        path: "/wddb/12345db"
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
            getBaseInfo() {
                this.loading = true;
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    this.$router.push("/wddb/12345db");
                    return;
                }
                // //获取工单信息
                searchBaseInfo({id, caseguid, systemsource: "02"})
                    .then(({code, data}) => {
                        this.nodeStr = data.tasklist.nextnodeid;
                        // if (this.nodeStr !== "accept" || data.tasklist.formtype !== "03") {
                        //     //节点与当前对应不上
                        //     this.$router.push("/wddb/12345db");
                        //     return;
                        // }
                        if (data.caseprocesform != null) {
                            this.caseprocesform = data.caseprocesform;
                        }
                        this.caseprocesform.subjectTypeValue = byIdGetSuperiors(
                            this.caseprocesform.subjecttypecodeid,
                            this.subjectTypeList
                        );
                        this.customerinfo = data.customerinfo;
                        this.tasklist = data.tasklist;
                        this.opinionList = data.opinionList;
                        this.handledemand = data.handledemand;
                        if (code === 0) {
                            if (data.economyform != null) {
                                //经济表单
                                this.formType = "comeconomicmodule";
                                this.formTypeStr = "经济违法行为";
                                this.economyform = data.economyform;
                                this.caseprocesform.subjectname = data.economyform.subjectname;
                                this.ordernumber = this.economyform.ordernumber;
                            } else {
                                //找不到对的工单信息
                                this.$alert("找不到对应的工单信息!", "提示", {
                                    confirmButtonText: "确定",
                                    type: "warning"
                                });
                                this.$router.push("/wddb/12345db");
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
            filterPCcs(value) {
                //过滤人员
                this.personnelForm.ccs = this.personnelForm.ccs.filter(
                    item => item !== this.personnelForm.leading
                );
            },
            filterPersonnelList() {
                return this.personnelList.filter(
                    item => item.primaryKey !== this.personnelForm.leading
                );
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
                }
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
            filterDepartmentList() {
                return this.departmentList.filter(
                    item => item.orgunitID !== this.departmentForm.leading
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
                    let params = {
                        user: this.user,
                        formType: this.formType,
                        node: this.nodeStr,
                        nextNodeId: "moveToLowerOrg",
                        data: data,
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
                                        if(window.opener){
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
                                            message: "提交成功！",
                                            type: "success",
                                            duration: "1000"
                                        });
                                        if(window.opener){
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
                        // caseprocesform: this.caseprocesform,
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
        async created() {
            await this.listSubjectType();
            this.getBaseInfo();
            this.findUnitList();
            this. findDepartmentList();
            this.findPersonnelList();
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);
            this.SearchMessagePerson()
        }
    };
</script>
<style lang="scss" scoped>
    .filing {
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
            /* padding: 15px 0 5px 0; */
            /* border-bottom: 1px solid #e8e8e8; */
        }
    }
</style>
