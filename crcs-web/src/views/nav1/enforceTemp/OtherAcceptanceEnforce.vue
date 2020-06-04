<template>
  <section class="application_delay" id="whole">
    <el-tabs
      type="border-card"
      style="width: 96%;margin: 15px auto;"
      @tab-click="flow"
      v-model="aictabs"
      v-loading="loading"
    >
      <el-tab-pane :label="formTypeStr">
        <el-row v-if="ismain">
          <el-col style="text-align: right;" :class="{'fixed':scrollTop > height}">
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
            <el-button type="primary" @click="toOperation('受理')" v-if="Accept">受理</el-button>
            <el-button type="primary" @click="toOperation('处理反馈')" v-if="dispose">处理反馈</el-button>
            <el-button type="primary" @click="toOperation('分派下级')" v-if="nextOrg">分派下级</el-button>
            <el-button type="primary" @click="toOperation('分派部门')" v-if="nextUnit">分派部门</el-button>
            <el-button type="primary" @click="toOperation('分派人员')" v-if="personnel">分派人员</el-button>
            <el-button type="primary" @click="toOperation('不受理')" v-if="notAccept">不受理</el-button>
            <el-button
              type="primary"
              @click="toOperation('申请延期')"
              v-if="formType!=='commommodule'"
            >申请延期</el-button>
            <el-button type="primary" @click="toOperation('退回')">退回</el-button>
          </el-col>
          <el-col style="height: 15px;"></el-col>
        </el-row>
        <el-row v-if="ismains" >
          <el-col style="text-align: right;" :class="{'fixed':scrollTop > height}">
            <el-button type="primary" @click="toOperation('意见填写')">意见填写</el-button>
          </el-col>
          <el-col style="height: 15px;"></el-col>
        </el-row>
        <!--经济违法行为表单-->
        <!--<my-economy-info-->
          <!--:data="comeconomicmodule"-->
          <!--:task="tasklist"-->
          <!--:casedata="caseprocesform"-->
          <!--v-if="formType==='comeconomicmodule'"-->
        <!--/>-->
        <!--新的经济违法行为表单-->
        <new-my-economy-info
                :comeconomicmodule="comeconomicmodule"
                :task="tasklist"
                :casedata="caseprocesform"
                v-if="formType==='comeconomicmodule'"
        />
        <!--咨询登记表单-->
        <!--<my-consult-info-->
          <!--:data="commommodule"-->
          <!--:task="tasklist"-->
          <!--:casedata="caseprocesform"-->
          <!--v-if="formType==='commommodule'"-->
        <!--/>-->
        <!--新的咨询登记表单-->
        <new-my-consult-info
                :commommodule="commommodule"
                :task="tasklist"
                :casedata="caseprocesform"
                v-if="formType==='commommodule'"
        />
        <!--消费投诉登记-->
        <!--<my-consume-info-->
          <!--:data="complaintmodule"-->
          <!--:task="tasklist"-->
          <!--:casedata="caseprocesform"-->
          <!--v-if="formType==='complaintmodule'"-->
        <!--/>-->
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
            <my-tracking :data="opinionList" />
          </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-tab-pane v-if="isOperation" name="operationDom" :label="operationType">
        <!-- 经济违法处理反馈 -->
        <el-collapse
          v-model="registrationActiveNames"
          class="accordion"
          v-if="formType==='comeconomicmodule' && operationType=='处理反馈'"
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
                    <el-cascader filterable
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
                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                  <el-form-item label="是否立案：" class="bt" prop="isregister">
                    <el-select
                            placeholder="请选择是否立案"
                            v-model="caseprocesform.isregister"
                            class="input-text">
                      <el-option label="是" value="是"></el-option>
                      <el-option label="否" value="否"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
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
                      <el-button size="small" type="success" @click="submitUpload('uploadEconomy')">确认上传</el-button>
                    </el-upload>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-collapse-item>
          <el-row style="margin: 20px 0;">
            <el-col style="text-align: center;width: 100%;">
              <el-button type="primary" @click="openLeadership('0')">确定</el-button>
              <el-button type="primary" @click="openLeadership('1')">送分局领导审核</el-button>
              <el-button @click="cancelTab">取消</el-button>
            </el-col>
          </el-row>
        </el-collapse>
        <!-- 消费投诉处理 -->
        <el-collapse
          v-model="registrationActiveNames"
          class="accordion"
          v-if="formType==='complaintmodule' && operationType=='处理反馈'"
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
                    <el-cascader filterable
                      placeholder="请选择涉及主体类别和类型"
                      :options="entitytype1"
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
                  <el-form-item label="侵权类型：" class="bt" prop="infringementtypecodeid">
                    <el-cascader filterable
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
                    <el-cascader filterable
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
                    <el-cascader filterable
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
                    <el-cascader filterable
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
                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                  <el-form-item label="办结日期：" class="bt" prop="transferreddate">
                    <el-date-picker
                      type="date"
                      v-model="caseprocesform.transferreddate"
                      class="input-text"
                      style="width: 100%"
                      value-format="yyyy-MM-dd"
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
                      <el-button size="small" type="success" @click="submitUploadCom('uploadCompalin')">确认上传</el-button>
                    </el-upload>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-collapse-item>
          <el-row style="margin: 20px 0;">
            <el-col style="text-align: center;width: 100%;">
              <el-button type="primary" @click="openLeadership('0')">确定</el-button>
              <el-button type="primary" @click="openLeadership('1')">送分局领导审核</el-button>
              <el-button @click="cancelTab">取消</el-button>
            </el-col>
          </el-row>
        </el-collapse>
        <!-- 咨询处理反馈 -->
        <el-form
          :model="caseprocesform"
          label-width="180px"
          ref="caseprocesformD"
          label-suffix="："
          :rules="caseprocesformRules"
          v-if="formType==='commommodule' && operationType=='处理反馈'"
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
            <el-button type="primary" @click="openLeadership('0')">确定</el-button>
            <el-button type="primary" @click="openLeadership('1')">送分局领导审核</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 退回 -->
        <el-form
          :model="goBackForm"
          label-width="180px"
          ref="goBackForm"
          label-suffix="："
          :rules="goBackFormRules"
          v-if="operationType==='退回'"
        >
          <el-form-item label="退回理由" class="form_bt" prop="reason" style="width: 80%">
              <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入退回理由（3000字内）"
                v-model="goBackForm.reason"
              ></el-input>
            </el-form-item>
          <el-form-item label="签收人" prop="leader" class="form_bt" v-if="user.regionID != '440681'">
            <el-select v-model="goBackForm.leader" placeholder="请选择签收人">
              <el-option
                v-for="item in leaderLists"
                :key="item.region"
                :label="item.name"
                :value="item.primaryKey"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label-width="0" style="text-align: center;">
            <el-button type="primary" @click="toSubmit">确认</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 意见填写 -->
        <el-form
                :model="opinionForm"
                label-width="180px"
                ref="opinionForm"
                label-suffix="："
                :rules="opinionFormRules"
                v-if="operationType==='意见填写'"
        >
          <el-form-item label="意见" prop="opinion" style="width: 80%" class="bt">
            <el-input
                    type="textarea"
                    v-model="opinionForm.opinion"
                    placeholder="请输入意见（3000字内）"
                    :rows="4"
            ></el-input>
          </el-form-item>
          <el-form-item label-width="0" style="text-align: center;">
            <el-button type="primary" @click="copyOpinion">确认</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 分派下级 -->
        <el-form
          :model="assignmentForm"
          label-width="180px"
          ref="assignmentForm"
          label-suffix="："
          :rules="assignmentFormRules"
          v-if="operationType==='分派下级'"
        >
          <el-form-item label="成员单位" class="form_bt" prop="unit" style="margin-top: 15px;">
            <el-select
              v-model="assignmentForm.unit"
              placeholder="请选择成员单位"
              clearable
              @change="orgPermission('1')"
              style="width: 300px;"
              filterable
            >
              <el-option
                v-for="item in reasonBack"
                :key="item.region"
                :label="item.name"
                :value="item.primaryKey"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="抄送部门" class="form_bt" prop="ortherDept">
            <el-select
              v-model="assignmentForm.ortherDept"
              multiple
              placeholder="请选择抄送部门"
              style="width: 500px;"
              @visible-change="unitChange()"
              @change="orgPermission()"
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
          <el-form-item label="分派意见" class="form_bt" prop="opinion" style="width: 80%">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入分派意见（3000字内）"
              v-model="assignmentForm.opinion"
            ></el-input>
          </el-form-item>
          <el-form-item label-width="0" style="text-align: center;">
            <el-button type="primary" @click="toSubmit">确认</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 分派部门 -->
        <el-form
          :model="assignmentForm"
          label-width="180px"
          ref="assignmentForm"
          label-suffix="："
          :rules="assignmentFormRules"
          v-if="operationType==='分派部门'"
        >
          <el-form-item label="主导部门" class="form_bt" prop="dept">
            <el-select
              v-model="assignmentForm.dept"
              placeholder="请选择主导部门"
              clearable
              style="width: 300px;"
              @visible-change="unitChange('1')"
              @change="orgPermission('2')"
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
          <el-form-item label="抄送部门" class="form_bt" prop="ortherDept">
            <el-select
              v-model="assignmentForm.ortherDept"
              multiple
              placeholder="请选择抄送部门"
              style="width: 500px;"
              @visible-change="unitChange($event,'2')"
              @change="orgPermission('3')"
              filterable
            >
              <el-option
                v-for="item in deptLists"
                :key="item.region"
                :label="item.name"
                :value="item.orgunitID"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="分派意见" class="form_bt" prop="opinion" style="width: 80%">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入分派意见（3000字内）"
              v-model="assignmentForm.opinion"
            ></el-input>
          </el-form-item>
          <el-form-item label-width="0" style="text-align: center;">
            <el-button type="primary" @click="toSubmit">确认</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 分派人员 -->
        <el-form
          :model="assignmentForm"
          label-width="180px"
          ref="assignmentForm"
          label-suffix="："
          :rules="assignmentFormRules"
          v-if="operationType==='分派人员'"
        >
          <el-form-item label="主要人员" class="form_bt" prop="person">
            <el-select
              v-model="assignmentForm.person"
              placeholder="请选择主要人员"
              clearable
              style="width: 300px;"
              @visible-change="unitPersonnel('1')"
            >
              <el-option
                v-for="item in personList"
                :key="item.region"
                :label="item.name"
                :value="item.primaryKey"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="抄送人员" class="form_bt" prop="ortherPerson">
            <el-select
              v-model="assignmentForm.ortherPerson"
              multiple
              placeholder="请选择抄送人员"
              style="width: 500px;"
              @visible-change="unitPersonnel($event,'2')"
            >
              <el-option
                v-for="item in personLists"
                :key="item.region"
                :label="item.name"
                :value="item.primaryKey"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="分派意见" class="form_bt" prop="opinion" style="width: 80%">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入分派意见（3000字内）"
              v-model="assignmentForm.opinion"
            ></el-input>
          </el-form-item>
          <el-form-item label-width="0" style="text-align: center;">
            <el-button type="primary" @click="toSubmit">确认</el-button>
            <el-button @click="cancelTab">取消</el-button>
          </el-form-item>
        </el-form>
        <!-- 申请延期 -->
        <el-form
          :model="delayForm"
          label-width="180px"
          ref="delayForm"
          label-suffix="："
          :rules="delayFormRules"
          v-if="operationType==='申请延期'"
        >
          <el-row>
            <el-col :xs="24" :sm="24" :md="12" :lg="8">
              <el-form-item label="延时时间单位" prop="unit" class="form_bt">
                <el-select v-model="delayForm.unit" placeholder="请选择延期时间单位" prop="department">
                  <el-option label="小时" value="小时"></el-option>
                  <el-option label="工作日" value="工作日"></el-option>
                  <el-option label="周" value="周"></el-option>
                  <el-option label="月份" value="月份"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8">
              <el-form-item label="延期时限" class="form_bt" prop="limit">
                <el-input placeholder="请输入延期时限" v-model.number="delayForm.limit"></el-input>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="延期原因" class="form_bt" prop="reason" style="width: 80%">
                <el-input
                  type="textarea"
                  :rows="4"
                  placeholder="请输入延期原因（3000字内）"
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
            <el-col>
              <el-form-item label-width="0" style="text-align: center;">
                <el-button type="primary" @click="toSubmit()">确认</el-button>
                <el-button @click="cancelTab">取消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <!-- 受理 -->
        <el-form
                :model="nextStepForm"
                ref="nextStepForm"
                style="width: 96%;margin: 0 auto"
                :rules="nextStepFormRules"
                v-if="operationType==='受理'"
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
              <el-button @click="cancelTab">取消</el-button>
            </el-col>
          </el-row>
        </el-form>
        <!-- 不受理 -->
        <el-form
                :model="nextStepForm"
                ref="nextStepForm"
                style="width: 96%;margin: 0 auto"
                :rules="nextStepFormRules"
                v-if="operationType==='不受理'"
        >
          <el-row style="margin: 20px 0;">
            <el-col :xs="24" :sm="24" :md="24" :lg="8">
              <el-form-item label="是否受理：" label-width="105px" class="bt">
                <div style="line-height: 35px;font-weight: bold;text-align: left;">{{nextStepForm.noAccept}}</div>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="8">
              <el-form-item label="不受理原因：" prop="cause" label-width="130px" class="bt" style="position: absolute">
                <el-select
                        v-model="nextStepForm.cause"
                        placeholder="请选择"
                        prop="department"
                        style="width: 180px;"
                >
                  <el-option
                          v-for="item in reasonWay"
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
          </el-row>
          <el-row style="margin: 10px 0;">
            <el-col style="text-align: center;width: 90%;">
              <el-button type="primary" @click="accepts('不受理')">确定</el-button>
              <el-button @click="cancelTab">取消</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="送分局领导审核" :visible.sync="dialogFormVisible" size="tiny">
      <el-form
        :model="caseform"
        label-width="120px"
        ref="feedbackLeaderForm"
        label-suffix="："
        :rules="caseformRules"
      >
        <el-form-item label="请选择领导" prop="leader" class="form_bt">
          <el-select v-model="caseform.leader" placeholder="请选择">
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
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="toLeadership">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="送区局处理" :visible.sync="dialogRegionAudit" size="tiny">
      <el-form :model="caseform" ref="feedbackLeaderForm" label-suffix="：" :rules="caseformRules">
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
        <el-button type="primary" @click="toSubmit">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 上传文件的弹窗 -->
    <!-- <el-dialog :title="fileUploadText" :visible.sync="fileUploadVisible" size="tiny">
      <el-upload
        :auto-upload="false"
        :file-list="fileList"
        ref="upload"
        :multiple="true"
        action="https://jsonplaceholder.typicode.com/posts/"
      >
        <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
      </el-upload>
    </el-dialog> -->
  </section>
</template>

<script>
import {
  next,
  searchBaseInfo,
  findOrgUnitByRegionId,
  findOrgDeptByOrgId,
  findUsersByDeptIdAndPermission,
  findUsersByOrgAndPermission,
  findUsersByPermission,
  selectBaseCodeTree,
  uploadFile,
  getMaterialLists
} from "../../../api/api";
import {myMixin} from '../../../assets/mixin/mixin'
import Tracking from "../../../components/Tracking";
import EconomyInfoLocal from "../../acceptanceRegistration/EconomyInfoLocal";
import consultInfoLocal from "../../acceptanceRegistration/consultInfoLocal";
import ConsumeInfoLocal from "../../acceptanceRegistration/ConsumeInfoLocal";
//引入新的组件
import NewEconomyInfoLocal from "../../sldj/components/EconomyInfo";
import NewconsultInfo from "../../sldj/components/commommoduleInfo";
import NewComplaintmoduleInfo from "../../sldj/components/ComplaintmoduleInfo"
import {byIdGetSuperiors,byIdGetLabels,getArrayByStringId} from "../../../utils/form-util";
import moment from "moment";

export default {
  mixins:[myMixin],
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
        exportLoading:false,
        complaintstype1:"",
        servicetype1:"",
        entitytype2:"",
        industryname1:"",
        illegalrules1:"",
        reportprovidelb1:"",
        objectcategory1:"",
        providetype1:"",

      entitytype1: [],//市场主体类型
      infringementtype1: [], //侵权类型下拉框选项
      subjecttype1: [], //涉及主体类别和类型下拉框选项
      infringingmarks1: [], //欺诈标志下拉框选项
      mediationmethods1: [], //调解方式下拉框选项
      noobligation1: [], //未履行义务下拉框选项
      requestMap: {
        caseguid: ""
      },
      requestMapCom: {
        caseguid: ""
      },
      fileLists:[],
      fileListsCom:[],
      fileListsEco:[],
      processingCollapse: ["processing", "tracking"],
      fileUploadText: false,
      fileList: [],
      fileUploadText: "文件上传",
      loading: true,
      formType: "",
      formTypeStr: "",
      opinionList: [],
      aictabs: "0",
      isOperation: false,
      dialogFormVisible: false,
      fileUploadVisible: false,
      dialogRegionAudit: false,
      operationType: "",
      commommodule: {},
      comeconomicmodule: {},
      complaintmodule: {},
      tasklist: {},
      registercase:false,
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
        subjecttypecodeid: [{ required: true, message: "请选择涉及主体类别和类型" }],
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
        infringementtypecodeid: [{ required: true, message: "请选择侵权类型" }],
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
          { max: 30, message: "不能超出30字符", trigger: "blur" }
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
          { max: 500, message: "不能超出500字符", trigger: "blur" }
        ],
      },
      caseform: {
        leader: "",
        leaders: ""
      },
      caseformRules: {
        leader: [{ required: true, message: "请选择领导" }],
        leaders: [{ required: true, message: "请选择区局处理人" }]
      },
      reasonBack: [],
      deptList: [],
      deptLists: [],
      personList: [],
      personLists: [],
      leaderLists: [],
      postponeLists: [],
      goBackForm: {
        reason: undefined,
        leader: ""
      },
      goBackFormRules: {
        reason: [
          { required: true, message: "请输入退回理由", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ],
        leader: [{ required: true, message: "请选择签收人" }]
      },
      assignmentForm: {
        unit: undefined,
        opinion: undefined,
        dept: "",
        ortherDept: [],
        person: "",
        ortherPerson: []
      },
      assignmentFormRules: {
        unit: [{ required: true, message: "请选择成员单位" }],
        opinion: [
          { required: true, message: "请输入分派意见", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ],
        dept: [{ required: true, message: "请选择主导部门" }],
        // ortherDept: [{ required: true, message: "请选择其他部门" }],
        person: [{ required: true, message: "请选择主要人员" }],
        // ortherPerson: [{ required: true, message: "请选择查看人员" }]
      },
      delayForm: {
        unit: undefined,
        limit: undefined,
        reason: undefined,
        postpone: ""
      },
      delayFormRules: {
        unit: [{ required: true, message: "请输入延期时限" }],
        limit: [{ required: true, message: "请选择延期时间单位" }],
        reason: [
          { required: true, message: "请输入延期原因", trigger: "blur" },
          { max: 3000, message: "不能超出3000字符", trigger: "blur" }
        ],
        postpone: [{ required: true, message: "请选择延期审批处理人" }]
      },
      opinionForm: {},
      opinionFormRules: {
        opinion: [{ required: true, message: "请输入内容", trigger: "blur" }]
      },
      nextUnit: true,
      nextOrg: true,
      personnel: false,
      dispose: true,
      ismain: true,
      ismains:false,
      Accept:false,
      //notAccept:false,
      depts: "",
      persons: "",
      registrationActiveNames: ["1", "2", "3", "4", "5"],
      user: JSON.parse(localStorage.getItem("user")),
      nextStepForm: {
        noAccept: "不受理",
        centent: "",
        department: "受理",
        process: "",
        cause: "",
        options: []
      },
      nextStepFormRules: {
        department: [{ required: true, message: "请选择", trigger: "change" }],
        process: [
          { required: true, message: "请选择受理方式", trigger: "change" }
        ],
        centent: [{ required: true, message: "请输入", trigger: "blur" }],
        cause: [
          { required: true, message: "请选择不受理原因", trigger: "change" }
        ],
      },
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
      reasonWay: [
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
      // window.location.href = `${process.env.Download_URL}` + url;
      window.open(`${process.env.Download_URL}` + url,"_blank")
    },

    // 获取用户信息
    getUserInfo() {
      let user = localStorage.getItem("user");
    },
    dateChanges(date) {
      this.caseprocesform.isverified = date;
    },
    dateChangeE(date) {
      this.caseprocesform.casedate = date;
    },
    flow({name}) {
      if ("operationDom" !== name) {
        this.isOperation = false;
      }
    },
    toOperation(value) {
      //消费表单处理反馈
      if (this.formType === "complaintmodule") {
        this.caseprocesform.subjectname = this.complaintmodule.subjectname,
                // this.caseprocesform.subjecttype = this.complaintform.subjecttype,
        this.caseprocesform.semaknama = this.complaintmodule.cksubjectaddress,
        this.caseprocesform.semakbusinessaddress = this.complaintmodule.cksubjectname,
        this.caseprocesform.businessaddress = this.complaintmodule.subjectaddress,
        this.caseprocesform.subjecttypecodeid = this.complaintmodule.entitytypecodeid
        this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(this.complaintmodule.entitytypecodeid, this.entitytype1)
      }
      //经济表单处理反馈
      if (this.formType === "comeconomicmodule") {

        this.caseprocesform.subjectname = this.comeconomicmodule.subjectname,
                this.caseprocesform.subjecttypecodeid = this.comeconomicmodule.industrynamecodeid,
                this.caseprocesform.subjecttypecodeid = byIdGetSuperiors(this.caseprocesform.subjecttypecodeid, this.subjecttype1)
        this.caseprocesform.semaknama = this.comeconomicmodule.cksubjectaddress,
                this.caseprocesform.semakbusinessaddress = this.comeconomicmodule.cksubjectname,
                this.caseprocesform.businessaddress = this.comeconomicmodule.subjectaddress
      }
      //咨询表单处理反馈
      if (this.formType === "commommodule") {
      }
      this.operationType = value;
      this.isOperation = true;
      this.aictabs = "operationDom";
      if ("退回" == value) {
        this.delayPersonnel();
      } else if ("分派下级" == value) {
        this.orgChange();
      } else if ("申请延期" == value) {
        this.delayApproval();
      }
    },
    openLeadership(value) {
      //验证
      let validation;
      if ("commommodule" === this.formType) {
        this.$refs.caseprocesformD.validate(valid => (validation = valid));
      } else if ("complaintmodule" === this.formType) {
        this.$refs["caseprocesformAA"].validate(valid => (validation = valid));
        this.$refs["caseprocesformBB"].validate(
                valid => (validation = valid && validation)
        );
        this.$refs["caseprocesformCC"].validate(
                valid => (validation = valid && validation)
        );
      } else {
        this.$refs["caseprocesformA"].validate(valid => (validation = valid));
        this.$refs["caseprocesformB"].validate(
                valid => (validation = valid && validation)
        );
        this.$refs["caseprocesformC"].validate(
                valid => (validation = valid && validation)
        );
      }
      if (validation) {
        if ("1" == value) {
          this.dialogFormVisible = true;
          this.leaderChange();
        } else {
          this.dialogRegionAudit = true;
          this.regionChange();
        }
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
    progress(event, file, fileList) {
    },
    submitUpload(value) {
      // this.requestMap.caseguid = this.complaintform.caseguid;
      this.requestMap.caseguid = this.$route.query.caseguid;
      this.$refs[value].submit();
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


    submitUploadCom(value) {
      this.requestMapCom.caseguid = this.$route.query.caseguid;
      this.$refs[value].submit();
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
    toLeadership() {
      let node = this.$route.query.node;
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
          data = this.complaintmodule;
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
          data = this.comeconomicmodule;
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
        // let newCaseprocesform=this.caseprocesform;

        // this.caseprocesform.subjecttype=this.caseprocesform.subjecttype[this.caseprocesform.subjecttype.length-1].toString();
        // this.caseprocesform.infringementtype=this.caseprocesform.infringementtype[this.caseprocesform.infringementtype.length-1].toString();
        // this.caseprocesform.infringingmarks=this.caseprocesform.infringingmarks[this.caseprocesform.infringingmarks.length-1].toString();
        // this.caseprocesform.mediationmethods=this.caseprocesform.mediationmethods[this.caseprocesform.mediationmethods.length-1].toString();
        // this.caseprocesform.noobligation=this.caseprocesform.noobligation[this.caseprocesform.noobligation.length-1].toString();
        let para = {
          user: JSON.parse(user),
          node: node,
          nextNodeId: "leaderApr",
          // nextStepForm: this.nextStepForm,
          formType: this.formType,
          data,
          deptid: this.caseform.leader,
          opinion: this.caseprocesform.handleopinion,
          caseprocesform: this.caseprocesform
        };
        next(para).then(data => {
          if (data.code === 0) {
            this.$message({
                message: '提交成功！',
                type: 'success',
                duration:'1000'
            });
            this.$router.push("/wddb/bxtdb");
          } else {
            this.$alert("发送失败!", "提示", {
              confirmButtonText: "确定",
              type: "error"
            });
          }
        });
      });
    },
    orgPermission(value) {
      if ("1" == value) {
        let para = {
          orgid: this.assignmentForm.unit,
          permission: "subordinate"
        };
        if (this.assignmentForm.unit != "") {
          findUsersByOrgAndPermission(para).then(res => {
            if (res.data.data == null) {
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
          deptid: this.assignmentForm.dept,
          permission: "dealdepartment"
        };
        if (this.assignmentForm.dept != "") {
          findUsersByDeptIdAndPermission(para).then(res => {
            if (res.data.data == null) {
              this.$alert(
                      "该部门没有处理该业务权限的人员，请先添加权限!",
                      "提示",
                      {
                        confirmButtonText: "确定"
                      }
              );
              this.assignmentForm.dept = "";
            }
          });
        }
      } else {
        let unit = this.assignmentForm.ortherDept[
        this.assignmentForm.ortherDept.length - 1
                ];
        let para = {
          deptid: unit,
          permission: "dealdepartment"
        };
        if (this.assignmentForm.ortherDept.length > 0 && unit != "") {
          findUsersByDeptIdAndPermission(para).then(res => {
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

    orgChange: function () {
      let user = localStorage.getItem("user");
      let users = JSON.parse(user);
      let para = {
        regionId: users.regionID
      };
      findOrgUnitByRegionId(para).then(res => {
        if (res.data.data.length == 0) {
          this.nextOrg = false;
        } else {
          this.reasonBack = res.data.data.filter(function (org) {
            if (users.regionID == org.region) {
              return false;
            } else {
              return true;
            }
          });
        }
      });
    },
    unitChange(callback, value) {
      if (callback == true && "2" == value && this.assignmentForm.dept == "") {
        this.deptLists = "";
        this.$alert("请先选择主导部门!", "提示", {
          confirmButtonText: "确定"
        });
      } else {
        if (
                "2" == value &&
                this.depts != "" &&
                this.assignmentForm.dept != ""
        ) {
          this.deptLists = this.depts;
        } else {
          let user = localStorage.getItem("user");
          let users = JSON.parse(user);
          let para = {
            orgId: users.orgID
          };
          findOrgDeptByOrgId(para).then(res => {
            this.deptList = res.data.data;
            this.depts = res.data.data.filter(dept => {
              if (this.assignmentForm.dept == dept.orgunitID) {
                return false;
              } else {
                return true;
              }
            });
          });
        }
      }
    },
    unitPersonnel(callback, value) {
      if (
              callback == true &&
              "2" == value &&
              this.assignmentForm.person == ""
      ) {
        this.personLists = "";
        this.$alert("请先选择主导人员!", "提示", {
          confirmButtonText: "确定"
        });
      } else {
        if (
                "2" == value &&
                this.persons != "" &&
                this.assignmentForm.person != ""
        ) {
          this.personLists = this.persons;
        } else {
          let user = localStorage.getItem("user");
          let users = JSON.parse(user);
          let para = {
            deptid: users.unitID,
            permission: "dealpersonnel"
          };
          findUsersByDeptIdAndPermission(para).then(res => {
            if (res.data.data.length > 0) {
              this.personList = res.data.data;
            } else {
              this.$alert("没有处理该业务权限的人员，请先添加权限!", "提示", {
                confirmButtonText: "确定"
              });
            }
            this.persons = res.data.data.filter(person => {
              if (this.assignmentForm.person == person.primaryKey) {
                return false;
              } else {
                return true;
              }
            });
          });
        }
      }
    },
    leaderChange() {
      let user = localStorage.getItem("user");
      let users = JSON.parse(user);
      let para = {
        deptid: users.unitID,
        permission: "leadershipApproval"
      };
      findUsersByDeptIdAndPermission(para).then(res => {
        this.leaderLists = res.data.data;
      });
    },
    regionChange() {
      // let user = localStorage.getItem("user");
      // let users = JSON.parse(user);
      let para = {
        permission: "delayApproval"
      };
      findUsersByPermission(para).then(res => {
        this.leaderLists = res.data;
      });
    },
    //查询延期审批用户列表
    delayApproval() {
      let para = {
        permission: "delayApproval"
      };
      findUsersByPermission(para).then(res => {
        this.postponeLists = res.data;
        console.log(this.postponeLists)
      });
    },
    cancelTab() {
      this.aictabs = "0";
      this.isOperation = false;
    },
    toSubmit() {
      let validation;
      let nextNodeId;
      let opinion;
      let orgid;
      let deptid;
      let ortherDept;
      let node = this.$route.query.node;
      let isnode;
      if (this.operationType === "处理反馈") {
        if ("commommodule" === this.formType) {
          this.$refs.caseprocesformD.validate(valid => (validation = valid));
        } else if ("complaintmodule" === this.formType) {
          this.$refs["caseprocesformAA"].validate(
                  valid => (validation = valid)
          );
          this.$refs["caseprocesformBB"].validate(
                  valid => (validation = valid && validation)
          );
          this.$refs["caseprocesformCC"].validate(
                  valid => (validation = valid && validation)
          );
        } else {
          this.$refs["caseprocesformA"].validate(valid => (validation = valid));
          this.$refs["caseprocesformB"].validate(
                  valid => (validation = valid && validation)
          );
          this.$refs["caseprocesformC"].validate(
                  valid => (validation = valid && validation)
          );
        }
        if (!validation) {
          return;
        }
        nextNodeId = "regionAudit";
        opinion = this.caseprocesform.handleopinion;
        deptid = this.caseform.leaders;
        isnode = "0";
      } else if (this.operationType === "分派下级") {
        this.$refs.assignmentForm.validate(valid => (validation = valid));
        nextNodeId = "moveToLowerOrg";
        opinion = this.assignmentForm.opinion;
        orgid = this.assignmentForm.unit;
        ortherDept = this.assignmentForm.ortherDept;
      } else if (this.operationType === "分派部门") {
        this.$refs.assignmentForm.validate(valid => (validation = valid));
        nextNodeId = "moveToLowerDept";
        opinion = this.assignmentForm.opinion;
        deptid = this.assignmentForm.dept;
        ortherDept = this.assignmentForm.ortherDept;
      } else if (this.operationType === "分派人员") {
        this.$refs.assignmentForm.validate(valid => (validation = valid));
        nextNodeId = "moveToLowerPersonnel";
        opinion = this.assignmentForm.opinion;
        deptid = this.assignmentForm.person;
        ortherDept = this.assignmentForm.ortherPerson;
      } else if (this.operationType === "申请延期") {
        this.$refs.delayForm.validate(valid => (validation = valid));
        nextNodeId = "applyDelay";
        deptid = this.delayForm.postpone;
        opinion = this.delayForm.reason;
      } else if (this.operationType === "退回") {
        this.$refs.goBackForm.validate(valid => (validation = valid));
        opinion = this.goBackForm.reason;
        deptid = this.goBackForm.leader;
        nextNodeId = "backForward";
        // if("accept" == node){
        //   nextNodeId = "end";
        // }else {
        //   nextNodeId = "superiorProcessing";
        // }
      }
      if (!validation) return;
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
            if(this.complaintmodule.entitytype != null && this.complaintmodule.entitytype != ""){
                this.complaintmodule.entitytype = this.entitytype2
            }
            if(this.complaintmodule.servicetype != null && this.complaintmodule.servicetype != ""){
                this.complaintmodule.servicetype = this.servicetype1
            }
            if(this.complaintmodule.complaintstype != null && this.complaintmodule.complaintstype != ""){
                this.complaintmodule.complaintstype = this.complaintstype1
            }
            data = this.complaintmodule;
        } else if ("comeconomicmodule" === this.formType) {

            if(this.comeconomicmodule.providetype != null && this.comeconomicmodule.providetype != ""){
                this.providetype1 = this.comeconomicmodule.providetype
            }
            if(this.comeconomicmodule.objectcategory != null && this.comeconomicmodule.objectcategory != ""){
                this.objectcategory1 = this.comeconomicmodule.objectcategory
            }
            if(this.comeconomicmodule.reportprovidelb != null && this.comeconomicmodule.reportprovidelb != ""){
                this.reportprovidelb1 = this.comeconomicmodule.reportprovidelb
            }
            if(this.comeconomicmodule.illegalrules != null && this.comeconomicmodule.illegalrules != ""){
                this.illegalrules1 = this.comeconomicmodule.illegalrules
            }
            if(this.comeconomicmodule.industryname != null && this.comeconomicmodule.industryname != ""){
                this.industryname1 = this.comeconomicmodule.industryname
            }

          data = this.comeconomicmodule;
        }
        /*//深度拷贝，防止更改economyform原先的表单值
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
        }*/
        let para = {
          user: JSON.parse(user),
          node: node,
          nextNodeId,
          // nextStepForm: this.nextStepForm,
          formType: this.formType,
          data,
          opinion: opinion,
          //caseprocesform: datas,
          orgid: orgid,
          deptid: deptid,
          ortherDept: ortherDept
        };
        next(para).then(data => {
          if (data.code === 0) {
            this.$message({
                message: '提交成功！',
                type: 'success',
                duration:'1000'
            });
            if (this.operationType === "申请延期") {
              let {id, caseguid} = this.$route.query;
              this.$router.push({
                path: "/wddb/bxtdb/sqyq",
                query: {id, caseguid}
              });
            } else {
              this.$router.push("/wddb/bxtdb");
            }
          } else {
            this.$alert("提交失败!", "提示", {
              confirmButtonText: "确定",
              type: "defeated"
            });
          }
        });
      });
    },

    copyOpinion() {
      //抄送人意见填写
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
          caseguid = this.commommodule.caseguid;
        } else if ("complaintmodule" === this.formType) {
          caseguid = this.complaintmodule.caseguid;
        } else {
          caseguid = this.comeconomicmodule.caseguid;
        }
        let params = {
          user: this.user,
          caseguid,
          node: this.$route.query.node,
          nextNodeId: "copyToOpinion",
          opinion: this.opinionForm.opinion,
          ismain: "0"
        };
        next(params).then(data => {
          if (data.code === 0) {
            this.$message({
                message: '提交成功！',
                type: 'success',
                duration:'1000'
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

    accepts(value) {
      let data;
      let worktype;
      if (this.formType == "commommodule") {
        data = this.commommodule;
        worktype = this.commommodule.worktype;
      } else if ("complaintmodule" == this.formType) {
          if(this.complaintmodule.entitytype != null && this.complaintmodule.entitytype != ""){
              this.complaintmodule.entitytype = this.entitytype2
          }
          if(this.complaintmodule.servicetype != null && this.complaintmodule.servicetype != ""){
              this.complaintmodule.servicetype = this.servicetype1
          }
          if(this.complaintmodule.complaintstype != null && this.complaintmodule.complaintstype != ""){
              this.complaintmodule.complaintstype = this.complaintstype1
          }
        data = this.complaintmodule;
        worktype = this.complaintmodule.worktype;
      } else if ("comeconomicmodule" == this.formType) {
        worktype = this.comeconomicmodule.worktype;
          if(this.comeconomicmodule.providetype != null && this.comeconomicmodule.providetype != ""){
              this.providetype1 = this.comeconomicmodule.providetype
          }
          if(this.comeconomicmodule.objectcategory != null && this.comeconomicmodule.objectcategory != ""){
              this.objectcategory1 = this.comeconomicmodule.objectcategory
          }
          if(this.comeconomicmodule.reportprovidelb != null && this.comeconomicmodule.reportprovidelb != ""){
              this.reportprovidelb1 = this.comeconomicmodule.reportprovidelb
          }
          if(this.comeconomicmodule.illegalrules != null && this.comeconomicmodule.illegalrules != ""){
              this.illegalrules1 = this.comeconomicmodule.illegalrules
          }
          if(this.comeconomicmodule.industryname != null && this.comeconomicmodule.industryname != ""){
              this.industryname1 = this.comeconomicmodule.industryname
          }
        data = this.comeconomicmodule;
      }
      let user = localStorage.getItem("user");
      if ("受理" == value) {
        let para = {
          user: JSON.parse(user),
          node: this.$route.query.node,
          formType: this.formType,
          data,
          nextNodeId: "accept",
          opinion: this.nextStepForm.process,
          worktype,
        };
        next(para).then(res => {
          if (res.code == 0) {
            this.$alert("受理成功!", "提示", {
              confirmButtonText: "确定",
              type: "success"
            });
            let para = {
              id: res.data.id,
              caseguid: res.data.caseguid,
              node: "accept"
            };
            if("comeconomicmodule" == this.formType){
              this.$router.push({path: "/wddb/bxtdb/sldjla", query: para});
            }else {
              this.$router.push({path: "/wddb/bxtdb/qtsl", query: para});
            }
          } else {
            this.$alert("提交失败!", "提示", {
              confirmButtonText: "确定",
              type: "defeated"
            });
          }
        });
      } else {
        let para
        if(this.user.regionID == "440681"){
          para = {
            user: JSON.parse(user),
            node: this.$route.query.node,
            formType: this.formType,
            data,
            nextNodeId: "noAccept",
            opinion: this.nextStepForm.centent,
            noAcceptreason: this.nextStepForm.cause,
            isEnd:"1"
          };
        }else {
          para = {
            user: JSON.parse(user),
            node: this.$route.query.node,
            formType: this.formType,
            data,
            nextNodeId: "noAccept",
            opinion: this.nextStepForm.centent,
            noAcceptreason: this.nextStepForm.cause,
          };
        }
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
      }
    },

    getBaseInfo() {
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
                  }
                  if (
                          res.data.economyform != null &&
                          res.data.complaintform == null &&
                          res.data.consultationform == null
                  ) {
                    //经济表单
                    this.formType = "comeconomicmodule";
                    this.formTypeStr = "经济违法行为表单";
                    this.comeconomicmodule = res.data.economyform;
                      if(this.comeconomicmodule.providetype != null && this.comeconomicmodule.providetype != ""){
                          this.providetype1 = this.comeconomicmodule.providetype
                          this.comeconomicmodule.providetype = this.comeconomicmodule.providetype.split("-")//将字符串格式转换成数组
                          this.comeconomicmodule.providetype = this.comeconomicmodule.providetype[this.comeconomicmodule.providetype.length-1].toString()
                      }
                      if(this.comeconomicmodule.objectcategory != null && this.comeconomicmodule.objectcategory != ""){
                          this.objectcategory1 = this.comeconomicmodule.objectcategory
                          this.comeconomicmodule.objectcategory = this.comeconomicmodule.objectcategory.split("-")//将字符串格式转换成数组
                          this.comeconomicmodule.objectcategory = this.comeconomicmodule.objectcategory[this.comeconomicmodule.objectcategory.length-1].toString()
                      }
                      if(this.comeconomicmodule.reportprovidelb != null && this.comeconomicmodule.reportprovidelb != ""){
                          this.reportprovidelb1 = this.comeconomicmodule.reportprovidelb
                          this.comeconomicmodule.reportprovidelb = this.comeconomicmodule.reportprovidelb.split("-")//将字符串格式转换成数组
                          this.comeconomicmodule.reportprovidelb = this.comeconomicmodule.reportprovidelb[this.comeconomicmodule.reportprovidelb.length-1].toString()
                      }
                      if(this.comeconomicmodule.illegalrules != null && this.comeconomicmodule.illegalrules != ""){
                          this.illegalrules1 = this.comeconomicmodule.illegalrules
                          this.comeconomicmodule.illegalrules = this.comeconomicmodule.illegalrules.split("-")//将字符串格式转换成数组
                          this.comeconomicmodule.illegalrules = this.comeconomicmodule.illegalrules[this.comeconomicmodule.illegalrules.length-1].toString()
                      }
                      if(this.comeconomicmodule.industryname != null && this.comeconomicmodule.industryname != ""){
                          this.industryname1 = this.comeconomicmodule.industryname
                          this.comeconomicmodule.industryname = this.comeconomicmodule.industryname.split("-")//将字符串格式转换成数组
                          this.comeconomicmodule.industryname = this.comeconomicmodule.industryname[this.comeconomicmodule.industryname.length-1].toString()
                      }
                  } else if (
                          res.data.complaintform != null &&
                          res.data.economyform == null &&
                          res.data.consultationform == null
                  ) {
                    //消费投诉表单
                    this.formType = "complaintmodule";
                    this.formTypeStr = "消费投诉表单";
                    this.complaintmodule = res.data.complaintform;
                      if(this.complaintmodule.entitytype != null && this.complaintmodule.entitytype != ""){
                          this.entitytype2 = this.complaintmodule.entitytype
                          this.complaintmodule.entitytype = this.complaintmodule.entitytype.split("-")//将字符串格式转换成数组
                          this.complaintmodule.entitytype = this.complaintmodule.entitytype[this.complaintmodule.entitytype.length-1].toString()
                      }
                      if(this.complaintmodule.servicetype != null && this.complaintmodule.servicetype != ""){
                          this.servicetype1 = this.complaintmodule.servicetype
                          this.complaintmodule.servicetype = this.complaintmodule.servicetype.split("-")//将字符串格式转换成数组
                          this.complaintmodule.servicetype = this.complaintmodule.servicetype[this.complaintmodule.servicetype.length-1].toString()
                      }
                      if(this.complaintmodule.complaintstype != null && this.complaintmodule.complaintstype != ""){
                          this.complaintstype1 = this.complaintmodule.complaintstype
                          this.complaintmodule.complaintstype = this.complaintmodule.complaintstype.split("-")//将字符串格式转换成数组
                          this.complaintmodule.complaintstype = this.complaintmodule.complaintstype[this.complaintmodule.complaintstype.length-1].toString()
                      }

                  } else if (
                          res.data.consultationform != null &&
                          res.data.complaintform == null &&
                          res.data.economyform == null
                  ) {
                    //咨询登记
                    this.formType = "commommodule";
                    this.formTypeStr = "咨询登记";
                    this.commommodule = res.data.consultationform;
                    // this.consultationform = res.data.consultationform
                      if(this.commommodule.entitytype != null && this.commommodule.entitytype != ""){
                          this.Entitytype1 = this.commommodule.entitytype
                          this.commommodule.entitytype = this.commommodule.entitytype.split("-")//将字符串格式转换成数组
                          this.commommodule.entitytype = this.commommodule.entitytype[this.commommodule.entitytype.length-1].toString()
                      }
                  } else {
                    //找不到对的工单信息
                    this.$alert("找不到对应的工单信息!", "提示", {
                      confirmButtonText: "确定",
                      type: "warning"
                    });
                    this.$router.push("/wddb/bxtdb");
                  }
                  this.tasklist = res.data.tasklist;
                  if (this.tasklist.isaccept == "1" || "commommodule" == this.formType) {
                    this.dispose = true
                  } else {
                    this.dispose = false
                    this.nextUnit = false
                    this.notAccept = true
                    this.Accept = true
                  }
                  this.opinionList = res.data.opinionList;
                  this.loading = false;
                }
              })
              .catch(error => {
                console.error(error);
                this.$message({
                  message: "请求失败",
                  type: "error"
                });
              });
        this.getSubjecttype();
        this.getInfringingmarks();
        this.getInfringementtype();
        this.getMediationmethods();
        this.getNoobligation();
        this.getEntitytype1();
    },
    // dateChangeF(date) {
    //   this.caseprocesform.transferreddate = date;
    // },
    toUploadOpen(type) {
      this.fileUploadVisible = true;
    },
    handleChangesubjecttype1(array) {
      let label = this.getLabel(this.subjecttype1, array[array.length - 1])
      this.caseprocesform.subjecttype = label
      this.caseprocesform.subjecttypecodeid = array
    },
    handleChangeInfringementtype1(array) {
      let label = this.getLabel(this.infringementtype1, array[array.length - 1])
      this.caseprocesform.infringementtype = label
      this.caseprocesform.infringementtypecodeid = array
    },
    handleChangeInfringingmarks1(array) {
      let label = this.getLabel(this.infringingmarks1, array[array.length - 1])
      this.caseprocesform.infringingmarks = label
      this.caseprocesform.infringingmarkscodeid = array
    },
    handleChangeMediationmethods1(array) {
      let label = this.getLabel(this.mediationmethods1, array[array.length - 1])
      this.caseprocesform.mediationmethods = label
      this.caseprocesform.mediationmethodscodeid = array
    },
    handleChangeNoobligation1(array) {
      let label = this.getLabel(this.noobligation1, array[array.length - 1])
      this.caseprocesform.noobligation = label
      this.caseprocesform.noobligationcodeid = array
    },
    getLabel(children, codeid) {
      for (const child of children) {
        if (codeid === child.value) {
          return child.label
        }
        if (child.children) {
          let label = this.getLabel(child.children, codeid)
          if (label) {
            return label
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
        this.infringingmarks1 = this.data; //欺诈标志  getArrayByStringId
          if(this.caseprocesform.infringingmarkscodeid != null && this.caseprocesform.infringingmarkscodeid != ""){
              this.caseprocesform.infringingmarkscodeid = getArrayByStringId(this.caseprocesform.infringingmarkscodeid,infringingmarks1)
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
      });
    },
    //根据当前用户权限查询人员列表
    delayPersonnel() {
      if (this.user.aicPermissions.dealdepartment != undefined) {
        let para = {
          permission: "subordinate",
          orgid: "6ba12fbe-0100-1000-e000-03fec0a85893"
        };
        findUsersByOrgAndPermission(para).then(res => {
          this.leaderLists = res.data.data;
        });
      } else {
        let para = {
          permission: "dealdepartment",
          orgid: this.user.orgID
        };
        findUsersByOrgAndPermission(para).then(res => {
          this.leaderLists = res.data.data;
        });
      }
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
            this.fileListsCom.push(fileInfo);
            this.fileListsEco.push(fileInfo)
          }
        }
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
  },
  mounted() {

    this.getMaterial(this.$route.query.caseguid)
  },
  created() {
    this.getBaseInfo();
    this.orgChange();
    if ("0" == this.$route.query.ismain) {
      this.ismain = false;
      this.ismains = true;
    } else {
      if ("moveToLowerDept" == this.$route.query.node) {
        this.personnel = true;
        this.nextOrg = false;
        this.nextUnit = false;
      } else if ("moveToLowerPersonnel" == this.$route.query.node) {
        this.personnel = false;
        this.nextOrg = false;
        this.nextUnit = false;
        // this.dispose = true;
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.application_delay {
  overflow-x: hidden;

  .el-table__body-wrapper {
    overflow: hidden;
  }

  .bt {
    /*margin: 5px auto;*/
    font-size: 13px;
    color: #48576a;
    font-weight: bold;
    // text-align: right;
  }

  .bts {
    margin: 10px auto;
    font-size: 13px;
    color: #48576a;
    font-weight: bold;
    text-align: right;
  }

  .st {
    margin: 5px auto;
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
}
.step i {
  font-size: 20px !important;
}
</style>
