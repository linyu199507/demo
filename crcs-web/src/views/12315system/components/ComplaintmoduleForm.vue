<!--消费投诉表单子组件-->
<template>
    <el-tabs type="card" v-model="tabsActiveName">
        <el-tab-pane label="登记信息" name="registration">
            <el-collapse v-model="collapseFormValues" class="accordion">
                <el-collapse-item name="provider">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">提供方信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData"
                             label-width="150px"
                             ref="complaintformOne"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="标题：" class="bt" prop="rqsttitle">
                                    <el-input v-model="formData.rqsttitle" placeholder="请输入标题"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="投诉人姓名/单位：" prop="rqstperson" class="bt">
                                    <el-input v-model="formData.rqstperson" placeholder="请输入投诉人姓名"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="性别：" prop="gender" class="bt">
                                    <el-select v-model="formData.gender" placeholder="请选择性别" class="input-text">
                                        <el-option label="男" value="男"></el-option>
                                        <el-option label="女" value="女"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="投诉人联系电话：" prop="linknumber" class="bt">
                                    <el-input v-model="formData.linknumber" placeholder="请输入联系电话"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件类型：" class="bt">
                                    <el-select v-model="formData.certificate" placeholder="请选择反映类型" class="input-text">
                                        <el-option label="身份证" value="身份证"></el-option>
                                        <el-option label="军官证" value="军官证"></el-option>
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件号码：" class="bt">
                                    <el-input v-model="formData.idnumber" placeholder="请输入证件号码"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="反映类型：" prop="usertype" class="bt" v-if="false">
                                    <el-select v-model="formData.usertype" placeholder="请选择反映类型" class="input-text">
                                        <el-option label="企业" value="企业"></el-option>
                                        <el-option label="个人" value="个人"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="诉求来源" prop="appealsource" class="bt" v-if="false">
                                    <el-select v-model="formData.appealsource" placeholder="请选择诉求来源" class="input-text">
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
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="类别：" class="bt" prop="providelb">
                                    <el-select placeholder="请选择类别"
                                               v-model="formData.providelb"
                                               class="input-text">
                                        <el-option label="儿童" value="儿童"></el-option>
                                        <el-option label="妇女" value="妇女"></el-option>
                                        <el-option label="老人" value="老人"></el-option>
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="年龄段：" class="bt">
                                    <el-select placeholder="请选择年龄段"
                                               v-model="formData.age"
                                               class="input-text">
                                        <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                        <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                        <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="电子邮箱：" class="bt" >
                                    <el-input placeholder="请输入电子邮箱"
                                              v-model="formData.informerpostcode"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="国籍：" class="bt">
                                    <el-input placeholder="请输入国籍"
                                              v-model="formData.nationality"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="民族：" class="bt" >
                                    <el-input placeholder="请输入民族"
                                              v-model="formData.nation"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="身份：" class="bt" >
                                    <el-select placeholder="请选择身份"
                                               v-model="formData.status"
                                               class="input-text">
                                        <el-option label="城镇" value="城镇"></el-option>
                                        <el-option label="乡村" value="乡村"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="邮编：" class="bt">
                                    <el-input placeholder="请输入邮编"
                                              v-model="formData.postcode"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="其他联系方式：" class="bt">
                                    <el-input v-model="formData.othercontacts" placeholder="请输入其他联系方式"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                <el-form-item label="投诉人地址：" class="bt">
                                    <el-input v-model="formData.rqstaddress" placeholder="请输入投诉人地址"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="subjectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">涉及主体信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData"
                             label-width="150px"
                             ref="complaintformTwo"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="被投诉对象名称："  prop="subjectname" class="bt">
                                    <el-input v-model="formData.subjectname" placeholder="请输入名称"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="被投诉对象地址：" prop="subjectaddress" class="bt">
                                    <el-input v-model="formData.subjectaddress" placeholder="请输入被投诉对象地址"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="市场主体类型：" class="bt" prop="providetypecodeid"
                                              :key="formData.providetypecodeid">
                                    <el-cascader filterable
                                                 :placeholder="provideTypeText?provideTypeText:'请选择市场主体类型'"
                                                 :options="providetype1"
                                                 v-model="formData.providetypecodeid"
                                                 @change="handleChangeProvidetype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:provideTypeText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="行业分类：" class="bt" prop="businesstypecodeid"
                                              :key="formData.businesstypecodeid">
                                    <el-cascader filterable
                                                 :placeholder="industrynameText?industrynameText:'请选择行业分类'"
                                                 :options="businesstype1"
                                                 v-model="formData.businesstypecodeid"
                                                 @change="handleChangeBusinesstype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:industrynameText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="社会信用代码：" class="bt" prop="registerno">
                                    <el-input placeholder="请输入社会信用代码"
                                              v-model="formData.registerno"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="联系人：" class="bt" prop="reportedcontact">
                                    <el-input placeholder="请输入联系人"
                                              v-model="formData.reportedcontact"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="联系电话：" prop="subjectphone" class="bt">
                                    <el-input v-model="formData.subjectphone" placeholder="请输入联系电话"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="objectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">业务信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData"
                             label-width="150px"
                             ref="complaintformThree"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="电商平台：" class="bt">
                                    <el-input v-model="formData.unknow" placeholder="请输入电商平台"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="客体类别：" class="bt" prop="objclassifycodeid"
                                              :key="formData.objclassifycodeid">
                                    <el-cascader filterable
                                                 :placeholder="objectcategoryText?objectcategoryText:'请选择客体类别'"
                                                 :options="objclassify1"
                                                 v-model="formData.objclassifycodeid"
                                                 @change="handleChangeObjclassify1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 style="width: 100%"
                                                 :class="{cascader_placeholder:objectcategoryText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="业务分类：" class="bt" prop="businesstypecodeidnew"
                                              :key="formData.businesstypecodeidnew">
                                    <el-cascader filterable
                                                 :placeholder="objectcategoryText?objectcategoryText:'请选择业务分类'"
                                                 :options="industryname1"
                                                 v-model="formData.businesstypecodeidnew"
                                                 @change="handleChangeIndustryname1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 style="width: 100%"
                                                 :class="{cascader_placeholder:objectcategoryText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品/服务名称：" prop="objname" class="bt">
                                    <el-input v-model="formData.objname" placeholder="请输入商品/服务名称"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品品牌：" prop="brand" class="bt">
                                    <el-autocomplete
                                            class="input-text"
                                            v-model="formData.brand"
                                            :fetch-suggestions="querySearch"
                                            placeholder="请输入商品品牌"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="销售方式：" class="bt">
                                    <el-select placeholder="请选择销售方式"
                                               v-model="formData.shoppingmode"
                                               class="input-text">
                                        <el-option label="网购" value="网购"></el-option>
                                        <el-option label="实体店" value="实体店"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否网购：" class="bt" prop="isweb">
                                    <el-select placeholder="请选择是否网购"
                                               v-model="formData.isweb"
                                               class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="消费金额：" class="bt" prop="objmoney">
                                    <el-input placeholder="请输入金额"
                                              v-model="formData.objmoney"
                                              class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                    <el-input placeholder="请输入订单号"
                                              v-model="formData.ordernumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="争议发生时间：" class="bt" >
                                    <el-date-picker
                                            type="date"
                                            v-model="formData.disputedate"
                                            class="input-text"
                                            style="width: 100%"
                                            value-format="yyyy-MM-dd"
                                            @change="dateChangeS"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="诉求内容：" class="bt" >
                                    <el-input placeholder="请输入"
                                              v-model="formData.report"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.complaintstypecodeid">
                                <el-form-item label="投诉问题类别：" class="bt" prop="complaintstypecodeid">
                                    <el-cascader filterable
                                                 :placeholder="complaintsTypeText?complaintsTypeText:'请选择投诉问题类别'"
                                                 :options="complaintstype1"
                                                 v-model="formData.complaintstypecodeid"
                                                 @change="handleChangeComplaintstype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:complaintsTypeText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品名称：" class="bt" >
                                    <el-input placeholder="请输入产品名称"
                                              v-model="formData.productname"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="批准文号：" class="bt" >
                                    <el-input placeholder="请输入批准文号"
                                              v-model="formData.approvalnumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品批号：" class="bt" >
                                    <el-input placeholder="请输入产品批号"
                                              v-model="formData.lotnumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品规格：" class="bt" >
                                    <el-input placeholder="请输入产品规格"
                                              v-model="formData.typespecification"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="生产厂家：" class="bt" >
                                    <el-input placeholder="请输入生产厂家"
                                              v-model="formData.producecompany"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="关键字：" class="bt" prop="keyword">
                                    <el-input placeholder="请输入关键字"
                                              v-model="formData.keyword"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="销售企业：" class="bt">
                                    <el-input placeholder="请输入销售企业"
                                              v-model="formData.salesenterprise "
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品有效期：" class="bt">
                                    <el-date-picker
                                            type="date"
                                            v-model="formData.expirationdate"
                                            class="input-text"
                                            style="width: 100%"
                                            value-format="yyyy-MM-dd"
                                            @change="dateChanges"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="投诉内容:" prop="problemdescribe" class="bt">
                                    <el-input
                                            type="textarea"
                                            :autosize="{ minRows: 6, maxRows: 8}"
                                            placeholder="请输入投诉内容(1200字以内)"
                                            v-model="formData.problemdescribe">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="officeInfo">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion title_accordion title_accordion">
                            <el-col :span="12">
                                <em>3.</em> 处办要求
                            </el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form label-width="220px" ref="handledemand">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应受理时限：</el-col>
                                <el-col :span="16">{{formatDate(complaintform.feedbacktime)}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应反馈时间：</el-col>
                                <el-col :span="16">{{formatDate(complaintform.todealwithtime)}}</el-col>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>

            </el-collapse>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import {selectBaseCodeTree, getMaterialLists} from "../../../api/api";
    import {getArrayByStringId, byIdGetLabels, getIdByName} from "../../../utils/form-util";

    export default {
        name: "ComplaintmoduleForm",
        props: ["complaintform"],
        computed: {
            formData() {
                return this.complaintform;
            }
        },
        data() {
            return {
                industryname1: [],//业务分类
                businesstype1: [],//行业分类
                complaintstype1: [], //涉及问题分类
                objclassify1: [], //涉及客体分类下拉框选项
                objectcategory1: [], //客体类别下拉框选项
                providetype1: [], //市场主体类型下拉框选项
                // 材料列表
                fileLists: [],
                materialList: [],
                fileUploadVisible: false,
                requestMap: {
                    caseguid: ""
                },
                tabsActiveName: "registration",
                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation",
                    "officeInfo"
                ],
                processInofActiveNames: ["1", "2", "3", "4", "5"],
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
                ],
                provideTypeText: null,
                industrynameText: null,
                objectcategoryText: null,
                complaintsTypeText: null,
                formDataRules: {
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"}
                    ],
                    linknumber: [
                        {required: true, message: "请输入电话", trigger: "blur"}
                    ],
                    usertype: [
                        {required: true, message: "请选择反映类型", trigger: "blur"}
                    ],
                    providelb: [
                        {required: true, message: "请选择类别", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, message: "请输入被投诉对象地址", trigger: "blur"}
                    ],
                    objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "blur"}
                    ],
                    objclassifycodeid: [
                        {required: true, message: "请选择客体类别"}
                    ],
                    // industrynamecodeid: [
                    //     {required: true, message: "请选择业务分类"}
                    // ],
                    businesstypecodeidnew: [
                        {required: true, message: "请选择业务分类"}
                    ],
                    businesstypecodeid: [
                        {required: true, message: "请选择行业分类"}
                    ],
                    providetypecodeid: [
                        {required: true, message: "请选择市场主体类型"}
                    ],
                    complaintstypecodeid: [
                        {required: true, message: "请选择投诉问题类别"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"}
                    ],
                    problemdescribe: [
                        {required: true, message: "请输入投诉内容", trigger: "blur"},
                        {max:1200 ,message: "投诉内容字数不得超过1200" }
                    ],
                    rqstperson: [
                        {required: true, message: "请输入投诉人姓名/单位", trigger: "blur"}
                    ],
                    gender: [
                        {required: true, message: "请选择性别", trigger: "blur"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入被投诉对象名称", trigger: "blur"}
                    ],
                   /* subjectphone: [
                        {required: true, message: "请输入联系电话", trigger: "blur"}
                    ],*/
                    brand: [
                        {required: true, message: "请输入商品品牌", trigger: "blur"}
                    ],
                    objmoney: [
                        {required: true, message: "请输入消费金额", trigger: "blur",type: 'number'}
                    ],
                },
            };
        },
        methods: {

            //判断校验
            checkData(){
                console.log("jinru")
                let validation = false;
                this.$refs["complaintformOne"].validate(
                    valid => (validation = valid)
                );
                this.$refs["complaintformTwo"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["complaintformThree"].validate(
                    valid => (validation = valid && validation)
                );
                console.log(validation)
                if (validation) {
                    // this.$emit('checkdata', true);
                    return true ;
                }else{
                    const h = this.$createElement;

                    this.$notify({
                        title: '消息提示',
                        message: h('i', { style: 'color: teal'}, '请检查是否有必填选项未填选!')
                    });
                }

            },

            dateChanges(date) {
                this.formData.expirationdate = date;
            },
            dateChangeS(date) {
                this.formData.disputedate = date;
            },
            toUploadOpenComplain(type) {
                // alert(12);
                this.fileUploadVisible = true;
            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            previewFile(file) {
                this.download(file.url)
            },
            download(url) {
                window.location.href = `${process.env.Download_URL}` + url;
            },
            beforeUpload(file) {
                // console.log(file)
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
                this.requestMap.caseguid = this.complaintform.caseguid;
                this.$refs.upload.submit();
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
            handleChange(value, row) {
            },
            handleChangeIndustryname1(array) {
                let label = this.getLabel(this.industryname1, array[array.length - 1]);
                this.formData.businesstypenew = byIdGetLabels(
                    label,
                    this.industryname1
                ).join("-");
                console.log(this.formData.businesstypecodeidnew)
                console.log(this.formData.businesstypenew)
                this.formData.businesstypecodeidnew = array;
            },
            handleChangeProvidetype1(array) {
                let label = this.getLabel(this.providetype1, array[array.length - 1])
                this.formData.providetype = byIdGetLabels(label, this.providetype1).join("-")//获取所有label值,用"-"进行连接,String格式

                // this.formData.providetype = this.formData.providetype.split("-")//将字符串格式转换成数组
                // this.formData.providetype = this.formData.providetype[this.formData.providetype.length-1].toString()
                // console.log(this.formData.providetype)
                this.formData.providetypecodeid = array
            },
            handleChangeObjclassify1(array) {
                let label = this.getLabel(this.objclassify1, array[array.length - 1])
                this.formData.objclassify = byIdGetLabels(label, this.objclassify1).join("-")
                this.formData.objclassifycodeid = array
            },
            handleChangeComplaintstype1(array) {
                let label = this.getLabel(this.complaintstype1, array[array.length - 1])
                this.formData.complaintstype = byIdGetLabels(label, this.complaintstype1).join("-")
                this.formData.complaintstypecodeid = array
            },
            handleChangeBusinesstype1(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1])
                this.formData.businesstype = byIdGetLabels(label, this.businesstype1).join("-")//获取所有label值,用"-"进行连接,String格式
                this.formData.businesstypecodeid = array
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
            //市场主体类型下拉框级联数据
            getProvidetype1() {
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
                    this.providetype1 = this.data; //类型
                    // if (this.formData.providetypecodeid) {
                    //     this.formData.providetypecodeid = getArrayByStringId(this.formData.providetypecodeid, this.providetype1)
                    // }
                    //赋值默认值
                    // if (this.formData.providetypecodeid.length === 0) {
                    //     //获取原始中文
                    //     this.provideTypeText = this.formData.providetype
                    // }
                });
            },
            //行业分类下拉框级联数据
            getBusinesstype1() {
                let params = {
                    type: "industry12345"
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
                    this.businesstype1 = this.data; //行业分类
                    //赋值默认值
                    // if (!this.formData.businesstypecodeid) {
                    //     //获取原始中文
                    //     this.industrynameText = this.formData.industryname
                    //     //判断是否该中文符合基础数据
                    //     this.formData.businesstypecodeid = getIdByName(this.formData.businesstype, this.businesstype1)
                    // }
                    // if (this.formData.businesstypecodeid != null && this.formData.businesstypecodeid != "") {
                    //     this.formData.businesstypecodeid = getArrayByStringId(this.formData.businesstypecodeid, this.businesstype1)
                    // }
                });
            },
            //涉及客体分类下拉框级联数据
            getObjectcategory1() {
                let params = {
                    type: "object12345"
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
                    this.objclassify1 = this.data; //涉及客体分类
                    //赋值默认值
                    // if (!this.formData.objclassifycodeid) {
                    //     //获取原始中文
                    //     this.objectcategoryText = this.formData.objectcategory
                    //     //判断是否该中文符合基础数据
                    //     this.formData.objclassifycodeid = getIdByName(this.formData.objclassify, this.objectcategory1)
                    // }
                    // if (this.formData.objclassifycodeid) {
                    //     this.formData.objclassifycodeid = getArrayByStringId(this.formData.objclassifycodeid, this.objectcategory1)
                    // }
                });
            },
            //涉及问题分类下拉框级联数据
            getComplaintstype1() {
                let params = {
                    type: "question12345"
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
                    this.complaintstype1 = this.data; //涉及问题分类
                    //赋值默认值
                    // if (!this.formData.complaintstypecodeid) {
                    //     //获取原始中文
                    //     this.complaintsTypeText = this.formData.complaintstype
                    //     //判断是否该中文符合基础数据
                    //     this.formData.complaintstypecodeid = getIdByName(this.formData.complaintstype, this.complaintstype1)
                    // }
                    // if (this.formData.complaintstypecodeid) {
                    //     this.formData.complaintstypecodeid = getArrayByStringId(this.formData.complaintstypecodeid, this.complaintstype1)
                    // }
                });
            },
            //行业分类下拉框级联数据
            getIndustryname1() {
                let params = {
                    type: "business12345"
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
                    console.log(this.industryname1)
                });
            },
            // 根据业务主键查询材料清单
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
                    // console.log(12);
                    if (res.code === 0) {
                        // this.materialList = res.data.records;
                        for (let i in res.data.records) {
                            let fileInfo;
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            }
                            this.fileLists.push(fileInfo)
                        }

                    }
                });
            },
            selectdata() {
                if (this.formData.providetypecodeid != null && this.formData.providetypecodeid != "") {
                    this.formData.providetypecodeid = getArrayByStringId(this.formData.providetypecodeid, this.providetype1)
                }
                if (this.formData.objclassifycodeid != null && this.formData.objclassifycodeid != "") {
                    this.formData.objclassifycodeid = getArrayByStringId(this.formData.objclassifycodeid, this.objclassify1)
                }
                if (this.formData.complaintstypecodeid != null && this.formData.complaintstypecodeid != "") {
                    this.formData.complaintstypecodeid = getArrayByStringId(this.formData.complaintstypecodeid, this.complaintstype1)
                }
            },
            querySearch(queryString, cb) {
                let results = queryString ? this.brandsSearch.filter(item => item.value.indexOf(queryString) !== -1) : this.brandsSearch;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
        },

        created() {
            console.log("1111");
            console.log(this.complaintform);
            this.getComplaintstype1();
            this.getProvidetype1();
            this.getObjectcategory1();
            this.getBusinesstype1();
            this.getIndustryname1();
            // this.getProblemtype1();
            this.getMaterialLists(this.$route.query.caseguid);
        }
    };
</script>

<style lang="scss" scoped>
    .bt {
        font-weight: bold;
        text-align: left;
    }

    .bt1 {
        font-weight: lighter;
        text-align: left;
        width: 100%;
    }

    .accessoriesList {
        font-size: 18px;
        cursor: pointer;
        color: rgb(72, 87, 106);

        ul {
            list-style: none;
            margin: 0;
            padding: 0;

            li {
                span:last-child {
                    &:hover {
                        text-decoration: underline;
                    }
                }

            }
        }
    }
</style>

