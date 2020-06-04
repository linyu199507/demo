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
                    <el-form
                            :model="economyform"
                            label-width="150px"
                            ref="economyformOne"
                            class="label"
                            label-suffix="："
                            :rules="economyformRules"
                    >
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="表单类型">
                                    <el-input v-model="economyform.formtype" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="标题" prop="rqsttitle">
                                    <el-input v-model="economyform.rqsttitle" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="举报人姓名" prop="rqstperson">
                                    <el-input v-model="economyform.rqstperson" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="年龄">
                                    <el-input v-model="economyform.age" placeholder="请输入" class="input-text">
                                        <template slot="append">岁</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="性别" prop="gender">
                                    <el-select v-model="economyform.gender" placeholder="请选择性别" class="input-text">
                                        <el-option label="男" value="男"></el-option>
                                        <el-option label="女" value="女"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="民族">
                                    <el-input v-model="economyform.nation" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="来电号码">
                                    <el-input v-model="economyform.rqstnumber" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="联系电话" prop="linknumber">
                                    <el-input v-model="economyform.linknumber" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="身份">
                                    <el-input v-model="economyform.status" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件类型">
                                    <el-select
                                            v-model="economyform.certificate"
                                            placeholder="请选择分类"
                                            class="input-text"
                                    >
                                        <el-option label value="身份证"></el-option>
                                        <el-option label value="军官证"></el-option>
                                        <el-option label value="其他证件"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="证件号码">
                                    <el-input v-model="economyform.idnumber" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="职业">
                                    <el-select v-model="economyform.career" placeholder="请选择职业" class="input-text">
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="工作单位">
                                    <el-input v-model="economyform.company" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="反映类型" prop="usertype">
                                    <el-select
                                            v-model="economyform.usertype"
                                            placeholder="请选择反映类型"
                                            class="input-text"
                                    >
                                        <el-option label="企业" value="企业"></el-option>
                                        <el-option label="个人" value="个人"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="回复方式">
                                    <el-select v-model="economyform.answertype" placeholder="请选择" class="input-text">
                                        <el-option label="电话" value="complain"></el-option>
                                        <el-option label="网络" value="consult"></el-option>
                                        <el-option label="信件" value="enforcement"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="工单分类">
                                    <el-select v-model="economyform.focus" placeholder="请选择分类" class="input-text">
                                        <el-option label="普通" value="普通"></el-option>
                                        <el-option label="特殊" value="特殊"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="类别">
                                    <el-select v-model="economyform.providelb" placeholder="请选择类别" class="input-text">
                                        <el-option label="儿童" value="儿童"></el-option>
                                        <el-option label="妇女" value="妇女"></el-option>
                                        <el-option label="老人" value="老人"></el-option>
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="投诉票据">
                                    <el-input
                                            v-model="economyform.complaintpaper"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="残疾人标识">
                                    <el-input
                                            v-model="economyform.disabilityidentification"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                <el-form-item label="举报人地址">
                                    <el-input v-model="economyform.rqstaddress" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="联系地址">
                                    <el-input
                                            v-model="economyform.contactaddress"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
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
                    <el-form
                            :model="economyform"
                            label-width="150px"
                            ref="economyformTwo"
                            label-suffix="："
                            class="label"
                            :rules="economyformRules"
                    >
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="名称" prop="subjectname">
                                    <el-input v-model="economyform.subjectname" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="联系方式">
                                    <el-input v-model="economyform.linktype" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="核查名称">-->
                            <!--                  <el-input-->
                            <!--                    v-model="economyform.cksubjectname"-->
                            <!--                    placeholder="请输入"-->
                            <!--                    class="input-text"-->
                            <!--                  ></el-input>-->
                            <!--                </el-form-item>-->
                            <!--              </el-col>-->
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="经营地址" prop="subjectaddress">
                                    <el-input
                                            v-model="economyform.subjectaddress"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="实际注册地址">-->
                            <!--                  <el-input v-model="economyform.subjectzcadd" placeholder="请输入" class="input-text"></el-input>-->
                            <!--                </el-form-item>-->
                            <!--              </el-col>-->
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="核查经营地址">-->
                            <!--                  <el-input-->
                            <!--                    v-model="economyform.cksubjectaddress"-->
                            <!--                    placeholder="请输入"-->
                            <!--                    class="input-text"-->
                            <!--                  ></el-input>-->
                            <!--                </el-form-item>-->
                            <!--              </el-col>-->
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
                    <el-form :model="economyform" label-width="150px" ref="economyformThree" class="label" :rules="economyformRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品/服务名称" prop="objname">
                                    <el-input v-model="economyform.objname" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品品牌" prop="brand">
                                    <el-autocomplete
                                            class="input-text"
                                            v-model="economyform.brand"
                                            :fetch-suggestions="querySearch"
                                            placeholder="请输入"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="economyform.objectcategorycodeid">
                                <el-form-item label="涉及客体分类" prop="objectcategorycodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="objectCategoryText?objectCategoryText:'请选择涉及客体分类'"
                                            :options="objectcategory1"
                                            v-model="economyform.objectcategorycodeid"
                                            @change="handleChangeObjclassify1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:objectCategoryText}"
                                    ></el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="涉及金额">
                                    <el-input v-model="economyform.objmoney" placeholder="请输入" class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="生产企业">
                                    <el-input
                                            v-model="economyform.producecompany"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否网购" prop="isweb">
                                    <el-select v-model="economyform.isweb" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品进口标志">
                                    <el-input v-model="economyform.importmark" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="型号规格">
                                    <el-input
                                            v-model="economyform.typespecification"
                                            placeholder="请输入"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品数量">
                                    <el-input v-model="economyform.quantity" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="计量单位">
                                    <el-input v-model="economyform.measureunit" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品/服务价格">
                                    <el-input v-model="economyform.price" placeholder="请输入" class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="购买日期">
                                    <el-date-picker
                                            v-model="economyform.purchasingdate"
                                            type="date"
                                            class="input-text"
                                            style="width: 100%;"
                                            placeholder="选择日期"
                                            @change="dateChanges"
                                    ></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="specificInformation">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">业务具体信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form
                            :model="economyform"
                            label-width="150px"
                            ref="economyformFour"
                            :rules="economyformRules"
                            label-suffix="："
                            class="label"
                    >
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="economyform.problemtypecodeid">
                                <el-form-item label="涉及问题分类" prop="problemtypecodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="problemTypeText?problemTypeText:'请选择涉及问题分类'"
                                            :options="problemtype1"
                                            v-model="economyform.problemtypecodeid"
                                            @change="handleChangeProblemtype1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:problemTypeText}"
                                    ></el-cascader>
                                    <!--<el-select v-model="economyform.problemtype" placeholder="请选择" class="input-text">
                                                        <el-option label="其他" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="涉嫌违法行为" :key="economyform.illegalrulescodeid" prop="illegalrulescodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="illegalrulesText?illegalrulesText:'请选择涉嫌违法行为'"
                                            :options="illegalrules1"
                                            v-model="economyform.illegalrulescodeid"
                                            @change="handleChangeIllegalrules1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:illegalrulesText}"
                                    ></el-cascader>
                                    <!--<el-select
                                                        v-model="economyform.illegalrules"
                                                        placeholder="请选择"
                                                        class="input-text"
                                                      >
                                                        <el-option label="其他" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="事件级别">
                                    <el-select v-model="economyform.eventlevel" placeholder="请选择" class="input-text">
                                        <el-option label="特别紧急事件" value="特别紧急事件"></el-option>
                                        <el-option label="紧急事件" value="紧急事件"></el-option>
                                        <el-option label="一般事件" value="一般事件"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="关键字" prop="keyword">
                                    <el-input v-model="economyform.keyword" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="政务标签">
                                    <el-input v-model="economyform.govlabel" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="economyform.businesstypecodeid">
                                <el-form-item label="业务分类" prop="businesstypecodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="businesstTypeText?businesstTypeText:'请选择业务分类'"
                                            :options="businesstype1"
                                            v-model="economyform.businesstypecodeid"
                                            @change="handleChangeBusinesstype1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:businesstTypeText}"
                                    ></el-cascader>
                                    <!-- <el-select v-model="economyform.businesstype" placeholder="请选择" class="input-text">
                                                         <el-option label="其他" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="economyform.tortclassifycodeid">
                                <el-form-item label="侵权分类">
                                    <el-cascader
                                            filterable
                                            :placeholder="tortclassifyText?tortclassifyText:'请选择侵权分类'"
                                            :options="tortclassify1"
                                            v-model="economyform.tortclassifycodeid"
                                            @change="handleChangeTortclassify1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:tortclassifyText}"
                                    ></el-cascader>
                                    <!--<el-select
                                                        v-model="economyform.tortclassify"
                                                        placeholder="请选择"
                                                        class="input-text"
                                                      >
                                                        <el-option label="其他" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="案值">
                                    <el-input v-model="economyform.anestimate" placeholder="请输入" class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="经济损失值" prop="economicloss">
                                    <el-input v-model.number="economyform.economicloss" placeholder="请输入" class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="问题描述" prop="problemdescribe">
                                    <el-input
                                            v-model="economyform.problemdescribe"
                                            placeholder="请输入"
                                            type="textarea"
                                            :autosize="{ minRows: 3, maxRows: 5}"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="correlationInformation">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">处理相关信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="economyform" label-width="150px" ref="economyform" class="label">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否出现场: ">
                                    <el-select v-model="economyform.isoutmall" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否反馈: ">
                                    <el-select v-model="economyform.isfeedback" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否需要奖励: ">
                                    <el-select v-model="economyform.isaward" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="12" :md="8" :lg="24">
                                <el-form-item label="相关附件: " class="bt">
                                    <el-upload
                                            :auto-upload="false"
                                            :file-list="fileListsEco"
                                            ref="uploadAccept"
                                            :on-preview="previewFile"
                                            :data="this.requestMap"
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
                                                @click="submitUpload('uploadAccept')"
                                        >确认上传
                                        </el-button>
                                    </el-upload>
                                </el-form-item>
                                <el-form-item label="附件清单：" class="bt mt20 accessoriesList">
                                    <ul>
                                        <li v-for="(item,index) in this.EcoMaterialList" :key="index" class="mb10">
                                            <span class="filesname">{{index+1}}、</span>
                                            <span @click="download(item.filesavepath)">{{item.attachfilename}}</span>
                                        </li>
                                    </ul>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import {getMaterialLists, selectBaseCodeTree} from "../../../api/api";
    import {
        getArrayByStringId,
        byIdGetLabels,
        getIdByName
    } from "../../../utils/form-util";

    export default {
        name: "Economyform",
        props: ["tasklist", "economyform"],
        data() {
            return {
                tortclassify1: [], //侵权分类
                businesstype1: [], //业务分类
                illegalrules1: [], //涉嫌违法行为
                problemtype1: [], //涉及问题分类
                objectcategory1: [], //涉及客体分类

                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation"
                ],
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
                objectCategoryText: null,
                problemTypeText: null,
                illegalrulesText: null,
                businesstTypeText: null,
                tortclassifyText: null,
                //msx
                economyformRules:{
                    // linknumber: [
                    //     {required: true, message: "请输入联系电话", trigger: "blur"},
                    //     {max: 11, message: "不能超出11个号码", trigger: "blur"}
                    // ],
                    // rqsttitle: [
                    //     {required: true, message: "请输入标题", trigger: "blur"},
                    //     {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    // ],
                    usertype: [
                        {required: true, message: "请选择反映类型", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "blur"}
                    ],
                    // subjectaddress: [
                    //     {required: true, message: "请输入经营地址", trigger: "blur"},
                    //     {max: 100, message: "不能超出100个字符", trigger: "blur"}
                    // ],
                    objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"},
                        {max: 50, message: "不能超出50个字符", trigger: "blur"}
                    ],
                    problemtypecodeid: [
                        {required: true, message: "请选择涉及问题分类"}
                    ],
                    illegalrulescodeid: [
                        {required: true, message: "请选择涉嫌违法行为"}
                    ],
                    usertype: [
                        {required: true, message: "请选择提供方类型"}
                    ],
                    businesstypecodeid: [
                        {required: true, message: "请选择业务分类"}
                    ],
                    objectcategorycodeid: [
                        {required: true, message: "请选择涉及客体类别"}
                    ],
                    // rqstperson: [
                    //     {required: true, message: "请输入举报人姓名", trigger: "blur"}
                    // ],
                    // gender: [
                    //     {required: true, message: "请选择性别", trigger: "blur"}
                    // ],
                    /*appealsource: [
                        {required: true, message: "请选择诉求来源", trigger: "blur"}
                    ],*/
                    // subjectname: [
                    //     {required: true, message: "请输入名称", trigger: "blur"}
                    // ],
                    brand: [
                        {required: true, message: "请选择商品品牌", trigger: "blur"}
                    ],
                    // economicloss: [
                    //     {required: true, type: "number", message: "请输入经济损失值", trigger: "blur" },
                    // ],
                    problemdescribe: [
                        {required: true, message: "请输入问题描述", trigger: "blur"},
                        {max:1200 ,message: "问题描述字数不得超过1200" }
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
            }
        },
        methods: {
            //判断校验 msx
            checkData(){
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
                this.$refs["economyformFour"].validate(
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
                this.economyform.purchasingdate = date;
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
                        this.objectCategoryText = this.economyform.objectcategory;
                        //判断是否该中文符合基础数据
                        this.economyform.objectcategorycodeid = getIdByName(
                            this.economyform.objectcategory,
                            this.objectcategory1
                        );
                    }
                    if (this.economyform.objectcategorycodeid) {
                        this.economyform.objectcategorycodeid = getArrayByStringId(
                            this.economyform.objectcategorycodeid,
                            this.objectcategory1
                        );
                    }
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
                    //赋值默认值
                    if (!this.economyform.problemtypecodeid) {
                        //获取原始中文
                        this.problemTypeText = this.economyform.problemtype;
                        //判断是否该中文符合基础数据
                        this.economyform.problemtypecodeid = getIdByName(
                            this.economyform.problemtype,
                            this.problemtype1
                        );
                    }
                    if (this.economyform.problemtypecodeid) {
                        this.economyform.problemtypecodeid = getArrayByStringId(
                            this.economyform.problemtypecodeid,
                            this.problemtype1
                        );
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
                    //赋值默认值
                    if (!this.economyform.illegalrulescodeid) {
                        //获取原始中文
                        this.illegalrulesText = this.economyform.illegalrules;
                        //判断是否该中文符合基础数据
                        this.economyform.illegalrulescodeid = getIdByName(
                            this.economyform.illegalrules,
                            this.illegalrules1
                        );
                    }
                    if (this.economyform.illegalrulescodeid) {
                        this.economyform.illegalrulescodeid = getArrayByStringId(
                            this.economyform.illegalrulescodeid,
                            this.illegalrules1
                        );
                    }
                });
            },
            //业务分类下拉框级联数据
            getBusinesstype1() {
                let params = {
                    type: "industry12345",
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
                    //赋值默认值
                    if (!this.economyform.businesstypecodeid) {
                        //获取原始中文
                        this.businesstTypeText = this.economyform.businesstype;
                        //判断是否该中文符合基础数据
                        this.economyform.businesstypecodeid = getIdByName(
                            this.economyform.businesstype,
                            this.businesstype1
                        );
                    }
                    if (this.economyform.businesstypecodeid) {
                        this.economyform.businesstypecodeid = getArrayByStringId(
                            this.economyform.businesstypecodeid,
                            this.businesstype1
                        );
                    }
                });
            },
            //侵权分类下拉框级联数据
            getTortclassify1() {
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
                    this.tortclassify1 = this.data; //侵权分类
                    //赋值默认值
                    if (!this.economyform.tortclassifycodeid) {
                        //获取原始中文
                        this.tortclassifyText = this.economyform.tortclassify;
                        //判断是否该中文符合基础数据
                        this.economyform.tortclassifycodeid = getIdByName(
                            this.economyform.tortclassify,
                            this.tortclassify1
                        );
                    }
                    if (this.economyform.tortclassifycodeid) {
                        this.economyform.tortclassifycodeid = getArrayByStringId(
                            this.economyform.tortclassifycodeid,
                            this.tortclassify1
                        );
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
            handleChangeObjclassify1(array) {
                let label = this.getLabel(this.objectcategory1, array[array.length - 1]);
                this.economyform.objectcategory = byIdGetLabels(
                    label,
                    this.objectcategory1
                ).join("-");
                this.economyform.objectcategorycodeid = array;
            },
            handleChangeProblemtype1(array) {
                let label = this.getLabel(this.problemtype1, array[array.length - 1]);
                this.economyform.problemtype = byIdGetLabels(
                    label,
                    this.problemtype1
                ).join("-");
                this.economyform.problemtypecodeid = array;
            },
            handleChangeIllegalrules1(array) {
                let label = this.getLabel(this.illegalrules1, array[array.length - 1]);
                this.economyform.illegalrules = byIdGetLabels(
                    label,
                    this.illegalrules1
                ).join("-");
                this.economyform.illegalrulescodeid = array;
            },
            handleChangeBusinesstype1(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1]);
                this.economyform.businesstype = byIdGetLabels(
                    label,
                    this.businesstype1
                ).join("-");
                this.economyform.businesstypecodeid = array;
            },
            handleChangeTortclassify1(array) {
                let label = this.getLabel(this.tortclassify1, array[array.length - 1]);
                this.economyform.tortclassify = byIdGetLabels(
                    label,
                    this.tortclassify1
                ).join("-");
                this.economyform.tortclassifycodeid = array;
            },
            querySearch(queryString, cb) {
                let results = queryString
                    ? this.brandsSearch.filter(
                        item => item.value.indexOf(queryString) !== -1
                    )
                    : this.brandsSearch;
                // 调用 callback 返回建议列表的数据
                cb(results);
            }
        },
        mounted() {
            this.getObjectcategory1();
            this.getProblemtype1();
            this.getIllegalrules1();
            this.getBusinesstype1();
            this.getTortclassify1();
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
