<!--综合查询-->
<template>
  <el-card class="comprehensive_query">
    <div slot="header" class="title">综合条件搜索查询</div>
    <el-form :model="Tasklist" label-suffix="：" ref="Tasklist" label-width="200px" style>
      <el-row>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="表单号" prop="serialnum" class="bt">
            <el-input v-model="Tasklist.serialnum" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="工单号" prop="tserialnum" class="bt">
            <el-input v-model="Tasklist.tserialnum" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="标题" prop="rqsttitle" class="bt">
            <el-input v-model="Tasklist.rqsttitle" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="诉求内容" prop="content" class="bt">
            <el-input v-model="Tasklist.content" placeholder="诉求内容" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="登记时间" class="bt" style="margin-bottom:0">
            <el-col :span="11">
              <el-form-item prop="beginAssigndate" style="margin-left: -20px">
                <el-date-picker
                  type="date"
                  placeholder="起始日期"
                  v-model="Tasklist.createdate"
                  style="width: 100%;"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item prop="endAssigndate" style="margin-left: -20px">
                <el-date-picker
                  type="date"
                  placeholder="结束时间"
                  v-model="Tasklist.duetime"
                  style="width: 100%;"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="诉求来源" prop="systemsource" class="bt">
            <el-select
              clearable
              v-model="Tasklist.systemsource"
              placeholder="请选择诉求来源"
              class="input-texts"
              style="width: 100%"
            >
              <el-option label="受理登记" value="01"></el-option>
              <el-option label="12345系统" value="02"></el-option>
              <el-option label="12315系统" value="03"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="来电号码" prop="serialTel" class="bt">
            <el-input v-model="Tasklist.serialTel" placeholder="请输入来电号码" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="工单状态" prop="nextnodeid" class="bt">
            <el-select
              v-model="Tasklist.nextnodeid"
              clearable
              placeholder="请选择工单状态"
              class="input-texts"
              style="width: 100%"
            >
              <el-option label="处理中" value="01"></el-option>
              <el-option label="处理完成" value="02"></el-option>
              <el-option label="退回" value="03"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="来电人姓名" prop="serialName" class="bt">
            <el-input v-model="Tasklist.serialName" placeholder="请输入来电人姓名" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <!-- <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="业务分类" prop="businesstype" class="bt">
            Providetype
              :options="businesstype1"
              v-model="Tasklist.businesstype"
              @change="handleChange"
              :show-all-levels="false"
              class="bt1"
              style="width: 100%"
            ></el-cascader>
          </el-form-item>
        </el-col>-->
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="涉及主体名称" prop="subjectname" class="bt">
            <el-input v-model="Tasklist.subjectname" placeholder="请输入涉及主体名称" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <!-- 单位 -->
        <el-col :xs="24" :sm="24" :md="12" :lg="12" v-if="Tasklist.companyOptions.length !== 0">
        <!-- <el-col :xs="24" :sm="24" :md="12" :lg="12"> -->
          <el-form-item label="处理单位" class="bt">
            <el-col :span="24">
              <el-select filterable
                         clearable
                         v-model="Tasklist.Company"
                         placeholder="请选择处理单位"
                         >
                <el-option
                  v-for="item in Tasklist.companyOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  @change="change"
                ></el-option>
              </el-select>
            </el-col>
          </el-form-item>
        </el-col>
        <!-- 部门 -->
<!--        <el-col :xs="24" :sm="24" :md="12" :lg="12" v-if="Tasklist.departmentOptions.length !== 0">-->
<!--          <el-form-item label="处理部门" class="bt">-->
<!--            <el-col :span="24">-->
<!--              <el-select v-model="Tasklist.department" placeholder="请选择">-->
<!--                <el-option-->
<!--                  v-for="item in Tasklist.departmentOptions"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-col>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="关键字" prop="keyword" class="bt">
            <el-input v-model="Tasklist.keyword" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="处理结果" prop="handleopinion" class="bt">
            <el-input v-model="Tasklist.handleopinion" placeholder="请输入处理结果" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="当前环节" prop="currentLink" class="bt">
            <el-select
                    v-model="Tasklist.currentLink"
                    clearable
                    placeholder="请选择当前环节"
                    class="input-texts"
                    style="width: 100%"
            >
              <el-option
                      v-for="item in currentLinkOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="商品品牌" prop="brand" class="bt">
            <el-input v-model="Tasklist.brand" placeholder="请输入" class="input-text"></el-input>
          </el-form-item>
        </el-col>

        <!--        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
        <!--          <el-form-item label="挽回经济损失" prop="financialloss" class="bt">-->
        <!--            <el-input-->
        <!--                    placeholder="请输入金额"-->
        <!--                    v-model.number="Tasklist.financialloss"-->
        <!--                    class="input-text"-->
        <!--            >-->
        <!--              <template slot="append">元</template>-->
        <!--            </el-input>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
        <!--          <el-form-item label="加倍赔偿金额" prop="doublemoney" class="bt">-->
        <!--            <el-input-->
        <!--                    placeholder="请输入金额"-->
        <!--                    v-model.number="Tasklist.doublemoney"-->
        <!--                    class="input-text"-->
        <!--            >-->
        <!--              <template slot="append">元</template>-->
        <!--            </el-input>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!-- <el-col :xs="24" :sm="24" :md="12" :lg="12" style="height:40px"> -->
        <el-col :xs="24" :sm="24" :md="12" :lg="12" >
          <el-form-item label="挽回经济损失" class="bt" style="margin-bottom:0">
            <el-col :span="11" style="height:56px">
              <el-form-item prop="beginFinancialloss" style="margin-left: -20px;">
                <el-input
                  placeholder="请输入金额"
                  v-model.number="Tasklist.beginFinancialloss"
                  class="input-text"
                >
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11" style="height:56px">
              <el-form-item prop="endFinancialloss" style="margin-left: -20px">
                <!-- <el-form-item prop="beginFinancialloss" style="margin-left: -20px"> -->
                <el-form-item prop="endFinancialloss" style="margin-left: -20px">
                  <el-input
                    placeholder="请输入金额"
                    v-model.number="Tasklist.endFinancialloss"
                    class="input-text"
                  >
                    <template slot="append">元</template>
                  </el-input>
                </el-form-item>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="加倍赔偿金额" class="bt" style="margin-bottom:0">
            <el-col :span="11" style="height:56px">
              <el-form-item prop="beginDoublemoney" style="margin-left: -20px">
                <el-input
                  placeholder="请输入金额"
                  v-model.number="Tasklist.beginDoublemoney"
                  class="input-text"
                >
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11" style="height:56px">
              <el-form-item prop="endDoublemoney" style="margin-left: -20px">
                <el-form-item prop="endDoublemoney" style="margin-left: -20px">
                  <el-input
                    placeholder="请输入金额"
                    v-model.number="Tasklist.endDoublemoney"
                    class="input-text"
                  >
                    <template slot="append">元</template>
                  </el-input>
                </el-form-item>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="经营地址区域" prop="jyareacode" class="bt">
            <el-select
              v-model="Tasklist.jyareacode"
              clearable
              placeholder="请选择区域"
              class="input-texts"
              style="width: 100%"
            >
<!--              <el-option label="顺德区" value="顺德区"></el-option>-->
<!--              <el-option label="禅城区" value="禅城区"></el-option>-->
<!--              <el-option label="三水区" value="三水区"></el-option>-->
<!--              <el-option label="南海区" value="南海区"></el-option>-->
<!--              <el-option label="高明区" value="高明区"></el-option>-->
                <el-option label="乐从" value="乐从"></el-option>
                <el-option label="陈村" value="陈村"></el-option>
                <el-option label="勒流" value="勒流"></el-option>
                <el-option label="大良" value="大良"></el-option>
                <el-option label="杏坛" value="杏坛"></el-option>
                <el-option label="均安" value="均安"></el-option>
                <el-option label="容桂" value="容桂"></el-option>
                <el-option label="龙江" value="龙江"></el-option>
                <el-option label="伦教" value="伦教"></el-option>
                <el-option label="北滘" value="北滘"></el-option>
          </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="经营地址详细地址" prop="serialAddress" class="bt">
            <el-input v-model="Tasklist.serialAddress" placeholder="请输入经营地址详细地址" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="涉及客体分类" class="bt" prop="objectcategory" style="margin-top:0">
            <el-cascader filterable
                         clearable
              placeholder="请选择涉及客体分类"
              :options="objectcategory1"
              v-model="Tasklist.objectcategory"
              @change="handleChangeObjectcategory"
              change-on-select
              :show-all-levels="false"
              class="bt1"
              style="width: 100%"
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="涉及问题分类" prop="problemtype" class="bt">
            <el-cascader filterable
                         clearable
              placeholder="请选择涉及问题分类"
              :options="problemtype1"
              v-model="Tasklist.problemtype"
              @change="handleChangeproblemtype"
              change-on-select
              :show-all-levels="false"
              class="bt1"
              style="width: 100%"
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="是否诉转案" prop="applealtocase" class="bt">
            <el-select v-model="Tasklist.applealtocase" clearable placeholder="请选择">
              <el-option label="是" value="1"></el-option>
              <el-option label="否" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="业务性质" prop="bustype" class="bt">
            <el-select
              v-model="Tasklist.bustype"
              clearable
              placeholder="请选择业务性质"
              class="input-texts"
              style="width: 100%"
            >
              <el-option label="咨询意见建议类" value="咨询"></el-option>
              <el-option label="投诉类" value="投诉"></el-option>
              <el-option label="举报类" value="举报"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="途径" prop="appealsource" class="bt">
            <el-select
                    v-model="Tasklist.appealsource"
                    clearable
                    placeholder="请选择途径"
                    class="input-texts"
                    style="width: 100%"
            >
              <el-option label="市消委会平台" value="市消委会平台"></el-option>
              <el-option label="8180平台" value="8180平台"></el-option>
              <el-option label="顺德消委会网" value="顺德消委会网"></el-option>
              <el-option label="网络发言人" value="网络发言人"></el-option>
              <el-option label="广东省消费者协会投诉与咨询信息系统" value="广东省消费者协会投诉与咨询信息系统"></el-option>
              <el-option label="企业直通车" value="企业直通车"></el-option>
              <el-option label="食药监系统（12331）" value="食药监系统（12331）"></el-option>
              <el-option label="红盾云桥平台" value="红盾云桥平台"></el-option>
              <el-option label="上级交办案件" value="上级交办案件"></el-option>
              <el-option label="省外移送案件" value="省外移送案件"></el-option>
              <el-option label="抽查抽检" value="抽查抽检"></el-option>
              <el-option label="来信投诉举报、职业投诉人来函" value="来信投诉举报、职业投诉人来函"></el-option>
              <el-option label="来人来访投诉举报" value="来人来访投诉举报"></el-option>
              <el-option label="纪检监察行政投诉平台" value="纪检监察行政投诉平台"></el-option>
              <el-option label="接收烟草案件" value="接收烟草案件"></el-option>
              <el-option label="纪委信访交办件" value="纪委信访交办件"></el-option>
              <el-option label="信访系统" value="信访系统"></el-option>
              <el-option label="政府论坛" value="政府论坛"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-form-item label="来函编号" prop="incomingcommpany" class="bt">
            <el-input v-model="Tasklist.incomingcommpany" placeholder="请输入来函编号" class="input-text"></el-input>
          </el-form-item>
        </el-col>



        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="工单分类" prop="focus" class="bt">-->
        <!--            <el-select v-model="Tasklist.focus" placeholder="请选择" style="width: 100%">-->
        <!--              <el-option label="普通" value="普通"></el-option>-->
        <!--              <el-option label="特殊" value="特殊"></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="是否网购" prop="focus" class="bt">-->
        <!--            <el-select v-model="Tasklist.buyInInternet" placeholder="请选择" style="width: 100%">-->
        <!--              <el-option label="是" value="1"></el-option>-->
        <!--              <el-option label="否" value="0"></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="信息来源" class="bt" prop="appealsource">-->
        <!--            <el-select placeholder="请选择信息来源" class="input-text" v-model="Tasklist.appealsource">-->
        <!--              <el-option label="来函" value="来函"></el-option>-->
        <!--              <el-option label="来人" value="来人"></el-option>-->
        <!--              <el-option label="电话" value="电话"></el-option>-->
        <!--              <el-option label="传真" value="传真"></el-option>-->
        <!--              <el-option label="上级交办" value="上级交办"></el-option>-->
        <!--              <el-option label="互联网络" value="互联网络"></el-option>-->
        <!--              <el-option label="其它" value="其它"></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="商品/服务类型" class="bt" prop="servicetype">-->
        <!--            <el-cascader filterable-->
        <!--              :options="servicetype1"-->
        <!--              v-model="Tasklist.servicetype"-->
        <!--              @change="handleChange"-->
        <!--              :show-all-levels="false"-->
        <!--              class="bt1"-->
        <!--              style="width: 100%"-->
        <!--            ></el-cascader>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="调解结果" prop="resultsofmediation" class="bt">-->
        <!--            <el-select-->
        <!--              placeholder="如选择其他视为调解不成功"-->
        <!--              v-model="Tasklist.resultsofmediation"-->
        <!--              class="input-text"-->
        <!--            >-->
        <!--              <el-option label="调解成功" value="调解成功"></el-option>-->
        <!--              <el-option label="调解不成功" value="解不成功"></el-option>-->
        <!--              <el-option label="其他" value="其他"></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :xs="24" :sm="24" :md="12" :lg="12">-->
        <!--          <el-form-item label="区域" prop="region" class="bt">-->
        <!--            <el-select-->
        <!--              v-model="Tasklist.region"-->
        <!--              placeholder="请选择区域"-->
        <!--              class="input-texts"-->
        <!--              style="width: 100%"-->
        <!--            >-->
        <!--              <el-option label="乐从镇" value="乐从镇"></el-option>-->
        <!--              <el-option label="陈村镇" value="陈村镇"></el-option>-->
        <!--              <el-option label="勒流镇" value="勒流镇"></el-option>-->
        <!--              <el-option label="大良镇" value="大良镇"></el-option>-->
        <!--              <el-option label="杏坛镇" value="杏坛镇"></el-option>-->
        <!--              <el-option label="均安镇" value="均安镇"></el-option>-->
        <!--              <el-option label="容桂镇" value="容桂镇"></el-option>-->
        <!--              <el-option label="龙江镇" value="龙江镇"></el-option>-->
        <!--              <el-option label="伦教镇" value="伦教镇"></el-option>-->
        <!--              <el-option label="北滘镇" value="北滘镇"></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->

        <el-col style="text-align: center">
          <el-button type="primary" @click="submitForm">查询</el-button>
          <el-button @click="resetForm('Tasklist')">重置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
</template>

<script>
import {
  selectBaseCodeTree,
  findOrgDeptByOrgId,
  findOrgUnitByRegionId
} from "../../api/api";
import moment from "moment";

export default  {
  name: "ReportQuery",
  data() {
    return {
      orgID: 0,
      newCompanyLists: [],
      Tasklist: {
        currentLink:"",
        companyOptions: [],
        departmentOptions: [],
        focus: "",
        serialnum: "",
        tserialnum: "",
        rqsttitle: "",
        Company: "",
        department: "",
        // labelname:'',
        createdate: "",
        duetime: "",
        systemsource: "",
        // 是否网购
        buyInInternet: "",
        // 投诉人
        serialName: "",
        // 投诉人电话
        serialTel: "",
        // 投诉地址
        serialAddress: "",
        appealsource: "",
        businesstype: "",
        subjectname: "",
        objectcategory: "",
        brand: "",
        servicetype: "",
        resultsofmediation: "",
        region: "",
        keyword: "",
        bustype: "",
        content: "", //投诉内容
        handleopinion: "", //处理意见内容
        applealtocase: "", //是否诉转案
        jyareacode: "", //经营地址区
        beginFinancialloss: "", //挽回经济损失
        endFinancialloss: "", //挽回经济损失
        beginDoublemoney: "", //加倍赔偿金额
        endDoublemoney: "" ,//加倍赔偿金额
        appealsource:"", //途径
        incomingcommpany:"" //来函编号
      },
      dataForm: {
        // labelname: undefined,
        rqsttitle: undefined,
        beginAssigndate: undefined,
        endAssigndate: undefined,
        focus: undefined,
        serialnum: undefined,
        tserialnum: undefined
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      businesstype1: [], //业务分类下拉框
      servicetype1: [], //商品/服务类型下拉框
      objectcategory1: [], //客体类别下拉框
        objectcategoryOne:"",
      problemtype1: [], //涉及问题分类下拉框
        problemtypeOne:"",
      //loading:false
        currentLinkOptions: [
            {
                value: "begin",
                label: "待分派"
            },
            {
                value: "accept",
                label: "受理"
            },
            {
                value: "noAccept",
                label: "不受理"
            },
            {
                value: "registerCase",
                label: "立案"
            },
            {
                value: "noRegisterCase",
                label: "不予立案"
            },
            {
                value: "leaderApr",
                label: "领导审核"
            },
            {
                value: "moveToLowerOrg",
                label: "分派下级机关"
            },
            {
                value: "moveToLowerDept",
                label: "分派其他部门"
            },
            {
                value: "moveToLowerPersonnel",
                label: "分派人员"
            },
            {
                value: "regionAudit",
                label: "区局处理"
            },
            {
                value: "handleFeedback",
                label: "处理反馈"
            },
            {
                value: "end",
                label: "结束"
            },
            {
                value: "backForward",
                label: "退回"
            }
        ],
    };
  },
  methods: {
    //   changeCompany(value){
    //       let value;
    //   },
    change() {
      alert(1);
    },
    //业务分类下拉框级联数据
    getBusinesstype1() {
      let params = {
        type: "B",
        orgLevel:"2"
      };
      // console.log(params)
      selectBaseCodeTree(params).then(data => {
        this.data = data.data.data;
        // console.log(this.data)
        let list = [];
        for (const i in this.data) {
          let item = {
            value: this.data[i].comment,
            label: this.data[i].comment
          };
          if (this.data[i].basecodeforms.length > 0) {
            item.children = this.getLevels(this.data[i].basecodeforms);
          }
          list.push(item);
        }
        this.data = list;
        // console.log(this.data)
        this.businesstype1 = this.data; //业务分类
      });
    },

    //商品/服务类型下拉框级联数据
    getServicetype1() {
      let params = {
        type: "object12345"
      };
      // console.log(params)
      selectBaseCodeTree(params).then(data => {
        this.data = data.data.data;
        // console.log(this.data)
        let list = [];
        for (const i in this.data) {
          let item = {
            // value: this.data[i].comment,
            value: this.data[i].codeid,
            label: this.data[i].comment
          };
          if (this.data[i].basecodeforms.length > 0) {
            item.children = this.getLevels(this.data[i].basecodeforms);
          }
          list.push(item);
        }
        this.data = list;
        // console.log(this.data)
        this.servicetype1 = this.data;
        this.objectcategory1 = this.data; //客体类别
      });
    },

    //涉及问题分类下拉框级联数据
    getProblemtype1() {
      let params = {
        type: "question12345"
      };
      // console.log(params)
      selectBaseCodeTree(params).then(data => {
        this.data = data.data.data;
        // console.log(this.data)
        let list = [];
        for (const i in this.data) {
          let item = {
            // value: this.data[i].comment,
            value: this.data[i].codeid,
            label: this.data[i].comment
          };
          if (this.data[i].basecodeforms.length > 0) {
            item.children = this.getLevels(this.data[i].basecodeforms);
          }
          list.push(item);
        }
        this.data = list;
        // console.log(this.data)
        this.problemtype1 = this.data; //业务分类
      });
    },

    getLevels(items) {
      let list = [];
      for (const i in items) {
        let data = {
          // value: items[i].comment,
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

    handleChange(array) {
      console.log(array)
    },

      handleChangeObjectcategory(array) {
        let label = this.getValue(this.objectcategory1, array[array.length - 1]);
        this.objectcategoryOne = label
        console.log(label)
    },
      handleChangeproblemtype(array) {
        let label = this.getValue(this.problemtype1, array[array.length - 1]);
        this.problemtypeOne = label
        console.log(label)
    },
      getValue(children, codeid) {
        console.log(children)
        console.log(codeid)
          for (const child of children) {
              if (codeid === child.value) {
                  return child.label;
              }
              if (child.children) {
                  let value = this.getValue(child.children, codeid);
                  if (value) {
                      return value;
                  }
              }
          }
      },

    submitForm() {
      //默认不未输入
      let valid = false;
      // console.log(this.Tasklist)

      for (const key in this.Tasklist) {
        let dataForm = this.Tasklist[key];
        if (dataForm) {
          if (dataForm instanceof Array) {
            valid = dataForm.length > 0;
          } else {
            valid = true;
          }
          if (valid) {
            break;
          }
        }
      }
      if (valid) {
        let params = {
          serialnum: this.Tasklist.serialnum,//表单号
          tserialnum: this.Tasklist.tserialnum,//工单号
          rqsttitle: this.Tasklist.rqsttitle,//标题
          content: this.Tasklist.content,//诉求内容
          createdate: "",//起始时间
          duetime: "",//结束时间
          systemsource: this.Tasklist.systemsource,//诉求来源
          serialTel: this.Tasklist.serialTel,//来电号码
          nextnodeid: this.Tasklist.nextnodeid,//工单状态
          serialName: this.Tasklist.serialName,//来点人姓名
          subjectname: this.Tasklist.subjectname,//涉及主体名称
          Company: this.Tasklist.Company,//处理单位
          keyword: this.Tasklist.keyword,//关键字
          handleopinion: this.Tasklist.handleopinion,//处理结果
          currentLink: this.Tasklist.currentLink,//当前环节
          brand: this.Tasklist.brand,//商品品牌
          beginFinancialloss: this.Tasklist.beginFinancialloss,//挽回经济损失下限
          endFinancialloss: this.Tasklist.endFinancialloss,//挽回经济损失上限
          beginDoublemoney: this.Tasklist.beginDoublemoney,//加倍赔偿金额下限
          endDoublemoney: this.Tasklist.endDoublemoney,//加倍赔偿金额上限
          jyareacode: this.Tasklist.jyareacode,//经营地址区域
          serialAddress: this.Tasklist.serialAddress,//经营地址详细地址
          objectcategory: "",//涉及客体分类
          problemtype: "",//涉及问题分类
          applealtocase: this.Tasklist.applealtocase,//是否诉转案
          bustype: this.Tasklist.bustype,//业务性质
          appealsource:this.Tasklist.appealsource,  //途径
          incomingcommpany:this.Tasklist.incomingcommpany,  //来函编号
          objectcategoryOne:this.objectcategoryOne,
          problemtypeOne:this.problemtypeOne

        };
        // if ( this.Tasklist.businesstype != null && this.Tasklist.businesstype != "" ) {
        //   data.businesstype = this.Tasklist.businesstype[this.Tasklist.businesstype.length - 1].toString();
        // }
        if ( this.Tasklist.problemtype != null && this.Tasklist.problemtype != "" ) {
          params.problemtype = this.Tasklist.problemtype[this.Tasklist.problemtype.length - 1].toString();
        }
        // if ( this.Tasklist.servicetype != null && this.Tasklist.servicetype != "" ) {
        //   data.servicetype = this.Tasklist.servicetype[this.Tasklist.servicetype.length - 1].toString();
        // }
        if ( this.Tasklist.objectcategory != null && this.Tasklist.objectcategory != "" ) {
          params.objectcategory = this.Tasklist.objectcategory[this.Tasklist.objectcategory.length - 1].toString();
        }
        if (this.Tasklist.createdate) {
          params.createdate = moment(this.Tasklist.createdate).format("YYYY-MM-DD HH:mm:ss");
        }
        if (this.Tasklist.duetime) {
          params.duetime = moment(this.Tasklist.duetime).format("YYYY-MM-DD HH:mm:ss");
        }

        console.log(params)
          sessionStorage.setItem('serchdata',JSON.stringify(params));
          //抄送部门工单处理
        let url = this.$router.resolve({
          path: "/tjfx/zhcx/ywjlcx/cxjg",
          // query: params
        });

        window.open(url.href, "_blank");
        return;

        // this.$router.push({
        //   path: "/tjfx/zhcx/ywjlcx/cxjg",
        //   query: this.Tasklist
        // });
      } else {
        this.$message.closeAll();
        this.$message({
          message: "至少填写一项！",
          type: "info"
        });
      }
    },
    resetForm(formName) {
      //    console.log(this.Tasklist)
      this.Tasklist.createdate = "";
      this.Tasklist.duetime = "";
      this.Tasklist.buyInInternet = "";
      this.Tasklist.Company = "";
      this.Tasklist.nextnodeid = "";
      this.$refs[formName].resetFields();
    },
    getCompanyList() {
      let user = localStorage.getItem("user");
      let users = JSON.parse(user);
      let para = {
        regionId: users.regionID
      };
      findOrgUnitByRegionId(para).then(res => {
        if (res.data.code === 0) {
          for (let i in res.data.data) {
            this.Tasklist.companyOptions[i] = {
              value: "",
              label: ""
            };
            this.Tasklist.companyOptions[i].value = res.data.data[i].region;
            this.Tasklist.companyOptions[i].label = res.data.data[i].name;
          }
        }
      });
    },
    compare() {
      let regionID = JSON.parse(localStorage.getItem("user")).regionID;
      if (regionID == "440681") {
        this.getCompanyList();
        this.getDepartmentList();
        let arr = this.Tasklist.departmentOptions;
        let arr1 = this.Tasklist.companyOptions;
        setTimeout(function() {
          for (let i = 0; i < arr.length; i++) {
            arr1.push(arr[i]);
          }
          this.newCompanyLists = arr1;
          // this.Tasklist.companyOptions = this.newCompanyLists;
        }, 1000);
        // console.log(this.newCompanyLists);
        console.log(this.Tasklist.companyOptions);
      } else {
        this.getDepartmentList();
      }
    },
    getDepartmentList() {
      let params = {
        orgId: JSON.parse(localStorage.getItem("user")).orgID
      };
      findOrgDeptByOrgId(params).then(res => {
        if (res.data.code === 0) {
          this.departmentOptions;
          for (let i in res.data.data) {
            this.Tasklist.departmentOptions[i] = {
              value: "",
              label: ""
            };
            this.Tasklist.departmentOptions[i].value =
              res.data.data[i].orgunitID;
            this.Tasklist.departmentOptions[i].label = res.data.data[i].name;
          }
        }
      });
    }
  },
  mounted() {
    this.orgID = JSON.parse(localStorage.getItem("user")).orgID;
    this.getBusinesstype1();
    this.getProblemtype1();
    this.getServicetype1();

    this.compare();

    // this.getComplaintstype1();
    // this.getUsertype1();
    // this.getIndustryname1();
  }
};
</script>

<style lang="scss">
.comprehensive_query {
  margin: 20px 20px 0 20px;
  box-shadow: none;
  min-height: 70vh;

  .el-form {
    margin-left: -40px;
    margin-right: 40px;
  }

  .el-form-item {
    margin-bottom: 20px;
  }
  .el-form-item1 {
    margin-bottom: 0px !important;
  }
  .el-form-item__content {
    line-height: normal;
    /*margin-left: 0px;*/
  }

  .title {
    /*font-weight: bold;*/
    /*text-align: center;*/
  }

  .el-card__header {
    /*padding: 0;*/
  }
  .el-select {
    width: 100% !important;
  }
  .line {
    text-align: center;
    line-height: 36px;
    font-weight: bold;
  }
}
</style>
