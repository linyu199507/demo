<!--经济违法表单组件-->
<template>
    <el-tabs v-model="tabFormActionName" type="card">
        <el-tab-pane label="登记信息" name="tabRegister">
            <el-collapse v-model="collapseFormValues" class="accordion">
                <el-collapse-item name="provider">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">提供方信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="economyform"
                             label-width="150px"
                             ref="economyformOne"
                             :rules="economyformRules"
                             class="label"
                             label-suffix="：">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="标题" prop="rqsttitle" class="bt">
                                    <el-input v-model="economyform.rqsttitle" placeholder="请输入标题"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="举报人姓名/单位" prop="rqstperson" class="bt">
                                    <el-input v-model="economyform.rqstperson" placeholder="请输入投诉人姓名"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="性别" prop="gender" class="bt">
                                    <el-select v-model="economyform.gender" placeholder="请选择性别" class="input-text">
                                        <el-option label="男" value="男"></el-option>
                                        <el-option label="女" value="女"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="投诉人联系电话" class="bt" prop="linknumber">
                                    <el-input v-model="economyform.linknumber" placeholder="请输入联系电话"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件类型" class="bt">
                                    <el-select v-model="economyform.certificate" placeholder="请选择反映类型"
                                               class="input-text">
                                        <el-option label="身份证" value="身份证"></el-option>
                                        <el-option label="军官证" value="军官证"></el-option>
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件号码" class="bt">
                                    <el-input v-model="economyform.idnumber" placeholder="请输入证件号码"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="提供方类型" class="bt" prop="usertype" v-if="false">
                                    <el-select v-model="economyform.usertype"  placeholder="请选择提供方类型" class="input-text">
                                        <el-option label="企业" value="企业"></el-option>
                                        <el-option label="个人" value="个人"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="诉求来源" prop="appealsource" class="bt" v-if="false">
                                    <el-select v-model="economyform.appealsource" placeholder="请选择诉求来源" class="input-text">
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
                                <el-form-item label="年龄段" class="bt" prop="age">
                                    <el-select placeholder="请选择年龄段"
                                               v-model="economyform.age"
                                               class="input-text">
                                        <el-option label="少年（19岁以下）" value="少年（19岁以下）"></el-option>
                                        <el-option label="中青年（19-65岁）" value="中青年（19-65岁）"></el-option>
                                        <el-option label="老年人（65岁以上）" value="老年人（65岁以上）"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="电子邮箱" class="bt" prop="informerpostcode">
                                    <el-input placeholder="请输入电子邮箱"
                                              v-model="economyform.informerpostcode"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="国籍" class="bt" prop="nationality">
                                    <el-input placeholder="请输入国籍"
                                              v-model="economyform.nationality"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="民族" class="bt" prop="nation">
                                    <el-input placeholder="请输入民族"
                                              v-model="economyform.nation"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="身份" class="bt" prop="status">
                                    <el-select placeholder="请选择身份"
                                               v-model="economyform.status"
                                               class="input-text">
                                        <el-option label="城镇" value="城镇"></el-option>
                                        <el-option label="乡村" value="乡村"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="邮编" class="bt">
                                    <el-input placeholder="请输入邮编"
                                              v-model="economyform.postcode"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="16">
                                <el-form-item label="举报人地址" class="bt">
                                    <el-input v-model="economyform.rqstaddress" placeholder="请输入投诉人地址"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="其他联系方式" class="bt">
                                    <el-input v-model="economyform.othercontacts" placeholder="请输入其他联系方式"
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
                    <el-form :model="economyform"
                             label-width="150px"
                             ref="economyformTwo"
                             :rules="economyformRules"
                             label-suffix="："
                             class="label">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="被举报对象名称" prop="subjectname" class="bt">
                                    <el-input v-model="economyform.subjectname" placeholder="请输入名称"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="被举报对象地址" class="bt" prop="subjectaddress">
                                    <el-input v-model="economyform.subjectaddress" placeholder="请输入被投诉对象地址"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="市场主体类型" class="bt" prop="providetypecodeid"
                                              :key="economyform.providetypecodeid">
                                    <el-cascader filterable
                                                 :options="providetype1"
                                                 :placeholder="provideTypeText?provideTypeText:'请选择市场主体类型'"
                                                 v-model="economyform.providetypecodeid"
                                                 @change="handleChangeprovidetype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:provideTypeText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="行业分类" class="bt" prop="industrynamecodeid"
                                              :key="economyform.industrynamecodeid">
                                    <el-cascader filterable
                                                 :placeholder="industrynameText?industrynameText:'请选择行业分类'"
                                                 :options="industryname1"
                                                 v-model="economyform.industrynamecodeid"
                                                 @change="handleChangeindustryname1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:industrynameText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="社会信用代码" class="bt" prop="registerno">
                                    <el-input placeholder="请输入社会信用代码"
                                              v-model="economyform.registerno"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="联系人" class="bt" prop="reportedcontact">
                                    <el-input placeholder="请输入联系人"
                                              v-model="economyform.reportedcontact"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12"  :lg="8">
                                <el-form-item label="联系电话" prop="subjectphone" class="bt">
                                    <el-input v-model="economyform.subjectphone" placeholder="请输入联系电话"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="objectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">涉及客体信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="economyform"
                             label-width="150px"
                             ref="economyformThree"
                             :rules="economyformRules"
                             class="label">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="客体类别：" :key="economyform.objectcategorycodeid" prop="objectcategorycodeid">
                                    <el-cascader filterable
                                                 :placeholder="objectcategoryText?objectcategoryText:'请选择客体类别'"
                                                 :options="objectcategory1"
                                                 v-model="economyform.objectcategorycodeid"
                                                 @change="handleChangeObjclassify1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:objectcategoryText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="业务分类：" :key="economyform.businesstypecodeidnew" prop="businesstypecodeidnew">
                                    <el-cascader filterable
                                                 :placeholder="objectcategoryText?objectcategoryText:'请选择业务分类'"
                                                 :options="businesstype1"
                                                 v-model="economyform.businesstypecodeidnew"
                                                 @change="handleChangeBusinesstype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:objectcategoryText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="涉嫌违法行为：" prop="illegalrulescodeid" :key="economyform.illegalrulescodeid">
                                    <el-cascader filterable
                                                 :placeholder="illegalrulesText?illegalrulesText:'请选择涉嫌违法行为'"
                                                 :options="illegalrules1"
                                                 v-model="economyform.illegalrulescodeid"
                                                 @change="handleChangeIllegalrules"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:illegalrulesText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品/服务名称：" prop="objname">
                                    <el-input v-model="economyform.objname" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品品牌：" prop="brand">
                                    <el-autocomplete
                                            class="input-text"
                                            v-model="economyform.brand"
                                            :fetch-suggestions="querySearch"
                                            placeholder="请输入"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否网购：" class="bt" prop="isweb">
                                    <el-select placeholder="请选择是否网购"
                                               v-model="economyform.isweb"
                                               class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="电商平台：" >
                                    <el-input v-model="economyform.shoppingplatform" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="举报问题类别：" class="bt" prop="reportprovidelbcodeid"
                                              :key="economyform.reportprovidelbcodeid">
                                    <el-cascader filterable
                                                 :placeholder="reportprovidelbText?reportprovidelbText:'请选择举报问题类别'"
                                                 :options="reportprovidelb1"
                                                 v-model="economyform.reportprovidelbcodeid"
                                                 @change="handleChangereportprovidelb1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:reportprovidelbText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="订单号：" class="bt" prop="ordernumber">
                                    <el-input placeholder="请输入订单号"
                                              v-model="economyform.ordernumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="消费金额：" class="bt" prop="objmoney">
                                    <el-input placeholder="请输入金额"
                                              v-model="economyform.objmoney"
                                              class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品名称：" class="bt" prop="productname">
                                    <el-input placeholder="请输入产品名称"
                                              v-model="economyform.productname"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="批准文号：" class="bt" prop="approvalnumber">
                                    <el-input placeholder="请输入批准文号"
                                              v-model="economyform.approvalnumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品批号：" class="bt" prop="lotnumber">
                                    <el-input placeholder="请输入产品批号"
                                              v-model="economyform.lotnumber"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品规格：" class="bt" prop="typespecification">
                                    <el-input placeholder="请输入产品规格"
                                              v-model="economyform.typespecification"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="生产厂家：" class="bt" prop="producecompany">
                                    <el-input placeholder="请输入生产厂家"
                                              v-model="economyform.producecompany"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="关键字：" class="bt" prop="keyword">
                                    <el-input placeholder="请输入关键字"
                                              v-model="economyform.keyword"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="销售企业：" class="bt">
                                    <el-input placeholder="请输入销售企业"
                                              v-model="economyform.salesenterprise "
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="产品有效期：" class="bt" prop="expirationdate">
                                    <el-date-picker
                                            type="date"
                                            v-model="economyform.expirationdate"
                                            class="input-text"
                                            style="width: 100%"
                                            value-format="yyyy-MM-dd"
                                            @change="dateChanges"
                                            placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="举报内容:" prop="problemdescribe" class="bt">
                                    <el-input
                                            type="textarea"
                                            :autosize="{ minRows: 6, maxRows: 8}"
                                            placeholder="请输入举报内容(1200字以内)"
                                            v-model="economyform.problemdescribe">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <!--mosx-->
                <el-collapse-item name="officeInfo">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion title_accordion title_accordion">
                            <el-col :span="12">
                                <em>3.</em> 处办要求
                            </el-col>
                            <el-col :span="10"></el-col>
                        </el-row>
                    </div>
                    <el-form label-width="220px" ref="handledemand">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应立案时限：</el-col>
                                <el-col :span="16">{{formatDate(economyform.feedbacktime)}}</el-col>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
                                <el-col :span="8" class="label">应反馈时间：</el-col>
                                <el-col :span="16">{{formatDate(economyform.todealwithtime)}}</el-col>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import {getMaterialLists, selectBaseCodeTree} from '../../../api/convenient12315'
    import {getArrayByStringId, byIdGetLabels, getIdByName} from "../../../utils/form-util";

    export default {
        name: "Economyform",
        props: ["tasklist", "economyform"],
        data() {
            return {
                providetype1: [],//市场土体类型
                industryname1: [],//行业分类
                // tortclassify1:[],//侵权分类
                businesstype1:[],//业务分类
                illegalrules1:[],//涉嫌违法行为
                reportprovidelb1: [],//举报问题类别
                problemtype1: [],//涉及问题分类
                objectcategory1: [],//涉及客体分类
                processInofActiveNames: ["1", "2", "3", "4", "5"],
                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation",
                    "officeInfo"
                ],
                illegalrulesText: null,
                fileListsEco: [],
                EcoMaterialList: [],
                requestMap: {
                    caseguid: 0
                },
                tabFormActionName: "tabRegister",
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
                reportprovidelbText: null,
                economyformRules:{
                    linknumber: [
                        {required: true, message: "请输入投诉人联系电话", trigger: "blur"},
                        {max: 11, message: "不能超出11个号码", trigger: "blur"}
                    ],
                    rqsttitle: [
                        {required: true, message: "请输入标题", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    usertype: [
                        {required: true, message: "请选择反映类型", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "blur"}
                    ],
                    subjectaddress: [
                        {required: true, message: "请输入举报对象地址", trigger: "blur"},
                        {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    ],
                    objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    reportprovidelbcodeid: [
                        {required: true, message: "请选择举报问题类别"}
                    ],
                    illegalrulescodeid: [
                        {required: true, message: "请选择涉嫌违法行为"}
                    ],
                    usertype: [
                        {required: true, message: "请选择提供方类型"}
                    ],
                    providetypecodeid: [
                        {required: true, message: "请选择市场主体类型"}
                    ],
                    industrynamecodeid: [
                        {required: true, message: "请选择行业分类"}
                    ],
                    // businesstypecodeid: [
                    //     {required: true, message: "请选择业务分类"}
                    // ],
                    businesstypecodeidnew: [
                        {required: true, message: "请选择业务分类"}
                    ],
                    objectcategorycodeid: [
                        {required: true, message: "请选择客体类别"}
                    ],
                    rqstperson: [
                        {required: true, message: "请输入举报人姓名/单位", trigger: "blur"}
                    ],
                    gender: [
                        {required: true, message: "请选择性别", trigger: "blur"}
                    ],
                    appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "blur"}
                    ],
                    subjectname: [
                        {required: true, message: "请输入被举报对象名称", trigger: "blur"}
                    ],
                    /*subjectphone: [
                        {required: true, message: "请输入联系电话", trigger: "blur"}
                    ],*/
                    brand: [
                        {required: true, message: "请选择商品品牌", trigger: "blur"}
                    ],
                    objmoney: [
                        {required: true, message: "请输入消费金额", trigger: "blur",type:"number"}
                    ],
                    problemdescribe: [
                        {required: true, message: "请输入举报内容", trigger: "blur"},
                        {max:1200 ,message: "举报内容字数不得超过1200" }
                    ],
                }
            };
        },
        watch: {
            economyform: function ({caseguid}, _) {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid: caseguid
                };
                // getMaterialLists(params).then(res => {
                //     if (res.code === 0) {
                //         let fileInfo;
                //         for( let i in res.data.records){
                //             fileInfo={
                //                 name:res.data.records[i].attachfilename,
                //                 url:res.data.records[i].filesavepath,
                //             }
                //             this.fileListsEco.push(fileInfo)
                //         }
                //     }
                // });
            }
        },
        methods: {
            //判断校验
            checkData(){
                console.log("jinru")
                let validation = false;
                this.$refs["economyformOne"].validate(
                    valid => (validation = valid)
                );
                this.$refs["economyformTwo"].validate(
                    valid => (validation = valid && validation)
                );
                this.$refs["economyformThree"].validate(
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
                this.economyform.expirationdate = date;
            },

            previewFile(file) {
                this.download(file.url)
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
                console.log(file.size / 1024 / 1024 / 20);
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
                this.requestMap.caseguid = this.economyform.caseguid;
                this.$refs[value].submit();
            },
            progress(event, file, fileList) {
            },
            // download(url){
            //   window.location.href = `${process.env.Download_URL}` + url;
            // },

            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },
            //客体类别下拉框级联数据
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
                    this.objectcategory1 = this.data; //客体类别
                    //赋值默认值
                    if (!this.economyform.objectcategorycodeid) {
                        //获取原始中文
                        this.objectcategoryText = this.economyform.objectcategory
                        //判断是否该中文符合基础数据
                        this.economyform.objectcategorycodeid = getIdByName(this.economyform.objectcategory, this.objectcategory1)
                    }
                    if (this.economyform.objectcategorycodeid) {
                        this.economyform.objectcategorycodeid = getArrayByStringId(this.economyform.objectcategorycodeid, this.objectcategory1)
                    }
                    console.log(this.objectcategoryText)
                });
            },
            //涉及问题分类下拉框级联数据
            getProblemtype1() {
                let params = {
                    type: "question12345"
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
                    this.problemtype1 = this.data; //涉及问题分类
                    if (this.economyform.problemtypecodeid != null && this.economyform.problemtypecodeid != "") {
                        this.economyform.problemtypecodeid = getArrayByStringId(this.economyform.problemtypecodeid, this.problemtype1)
                    }
                });
            },
            //涉嫌违法行为下拉框级联数据
            getIllegalrules1() {
                let params = {
                    type: "illegal12345"
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
                    this.illegalrules1 = this.data; //涉嫌违法行为
                    if (this.economyform.illegalrulescodeid != null && this.economyform.illegalrulescodeid != "") {
                        this.economyform.illegalrulescodeid = getArrayByStringId(this.economyform.illegalrulescodeid, this.illegalrules1)
                        console.log(this.economyform.illegalrulescodeid )
                    }
                });
            },
            //举报问题类型下拉框级联数据
            getReportprovidelb1() {
                let params = {
                    type: "question12345"
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
                    this.reportprovidelb1 = this.data; //举报问题类别
                    //赋值默认值
                    if (!this.economyform.reportprovidelbcodeid) {
                        //获取原始中文
                        this.reportprovidelbText = this.economyform.reportprovidelb
                        //判断是否该中文符合基础数据
                        this.economyform.reportprovidelbcodeid = getIdByName(this.economyform.reportprovidelb, this.reportprovidelb1)
                    }
                    if (this.economyform.reportprovidelbcodeid) {
                        this.economyform.reportprovidelbcodeid = getArrayByStringId(this.economyform.reportprovidelbcodeid, this.reportprovidelb1)
                    }
                });
            },
            //业务分类下拉框级联数据
            getBusinesstype1() {
                let params = {
                    type: "business12345",
                    // orgLevel: "2"

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
                    this.businesstype1 = this.data; //业务分类
                    if (this.economyform.businesstypecodeid != null && this.economyform.businesstypecodeid != "") {
                        this.economyform.businesstypecodeid = getArrayByStringId(this.economyform.businesstypecodeid, this.businesstype1)
                    }
                });
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
                    this.providetype1 = this.data; //市场主体类型
                    console.log(this.economyform.providetype)
                    if (this.economyform.providetype) {
                        this.economyform.providetype = this.economyform.providetype.split("-")
                        this.economyform.providetype = this.economyform.providetype[this.economyform.providetype.length-1].toString()
                        this.economyform.providetypecodeid = getIdByName(this.economyform.providetype,this.providetype1)
                        this.economyform.providetypecodeid = getArrayByStringId(this.economyform.providetypecodeid, this.providetype1)
                        console.log(this.economyform.providetypecodeid)
                    }
                    //赋值默认值
                    if (this.economyform.providetypecodeid.length === 0) {
                        //获取原始中文
                        this.provideTypeText = this.economyform.providetype
                    }
                });
            },
            //行业分类下拉框级联数据
            getIndustryname1() {
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
                    this.industryname1 = this.data;
                    //赋值默认值
                    if (!this.economyform.industrynamecodeid) {
                        //获取原始中文
                        this.industrynameText = this.economyform.industryname
                        //判断是否该中文符合基础数据
                        this.economyform.industrynamecodeid = getIdByName(this.economyform.industryname, this.industryname1)
                    }
                    if (this.economyform.industrynamecodeid) {
                        this.economyform.industrynamecodeid = getArrayByStringId(this.economyform.industrynamecodeid, this.industryname1)
                    }

                });
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
            handleChangeindustryname1(array) {
                let label = this.getLabel(this.industryname1, array[array.length - 1])
                this.economyform.industryname = byIdGetLabels(label, this.industryname1).join("-")
                this.economyform.industrynamecodeid = array
            },
            handleChangeprovidetype1(array) {
                let label = this.getLabel(this.providetype1, array[array.length - 1])
                this.economyform.providetype = byIdGetLabels(label, this.providetype1).join("-")
                this.economyform.providetypecodeid = array
                //console.log(this.economyform.providetype)
                //console.log(this.economyform.providetypecodeid)
            },
            handleChangereportprovidelb1(array) {
                let label = this.getLabel(this.reportprovidelb1, array[array.length - 1])
                this.economyform.reportprovidelb = byIdGetLabels(label, this.reportprovidelb1).join("-")
                this.economyform.reportprovidelbcodeid = array
            },
            handleChangeObjclassify1(array) {
                let label = this.getLabel(this.objectcategory1, array[array.length - 1])
                this.economyform.objectcategory = byIdGetLabels(label, this.objectcategory1).join("-")
                this.economyform.objectcategorycodeid = array
            },
            handleChangeBusinesstype1(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1]);
                this.economyform.businesstypenew = byIdGetLabels(
                    label,
                    this.businesstype1
                ).join("-");
                console.log(this.economyform.businesstypenew)
                this.economyform.businesstypecodeidnew = array;
            },
            handleChangeIllegalrules(array) {
                let label = this.getLabel(this.illegalrules1, array[array.length - 1]);
                this.economyform.illegalrules = byIdGetLabels(
                    label,
                    this.illegalrules1
                ).join("-");
                console.log(this.economyform.illegalrules)
                //console.log(this.economyform.illegalrulescodeid)
                this.economyform.illegalrulescodeid = array;
            },
            querySearch(queryString, cb) {
                let results = queryString ? this.brandsSearch.filter(item => item.value.indexOf(queryString) !== -1) : this.brandsSearch;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },

        },
        mounted() {
            this.getObjectcategory1()
            this.getProblemtype1()
            this.getBusinesstype1()
            this.getIllegalrules1()
            // this.getTortclassify1()
            this.getProvidetype1()
            this.getIndustryname1()
            this.getReportprovidelb1()

        }
    };
</script>

<style lang="scss" scoped>
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
</style>
