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
                    <el-form :model="formData" label-width="150px"
                             ref="complaintformOne"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="表单类型：" class="bt">
                                    <el-input v-model="formData.formtype" placeholder="请输入表单类型"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="标题：" class="bt" prop="rqsttitle">
                                    <el-input v-model="formData.rqsttitle" placeholder="请输入标题"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <!--<el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                                <!--<el-form-item label="表单模板：" class="bt">-->
                                    <!--<el-select-->
                                            <!--v-model="formData.businesstype"-->
                                            <!--placeholder="请选择表单模板"-->
                                            <!--class="input-text"-->
                                    <!--&gt;-->
                                        <!--<el-option label="消费投诉业务-价格投诉" value="消费投诉业务-价格投诉"></el-option>-->
                                        <!--<el-option label="消费投诉业务-质量投诉" value="消费投诉业务-质量投诉"></el-option>-->
                                    <!--</el-select>-->
                                <!--</el-form-item>-->
                            <!--</el-col>-->
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="投诉人姓名：" class="bt" prop="rqstperson">
                                    <el-input v-model="formData.rqstperson" placeholder="请输入投诉人姓名"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="性别：" class="bt" prop="gender">
                                    <el-select v-model="formData.gender" placeholder="请选择性别" class="input-text">
                                        <el-option label="男" value="男"></el-option>
                                        <el-option label="女" value="女"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                                    <el-input
                                            v-model="formData.rqstnumber"
                                            placeholder="请输入来电号码"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="联系电话：" class="bt">
                                    <el-input
                                            v-model="formData.linkphonenumber"
                                            placeholder="请输入联系电话"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="反映类型：" class="bt" prop="usertype">
                                    <el-select v-model="formData.usertype" placeholder="请选择反映类型" class="input-text">
                                        <el-option label="企业" value="企业"></el-option>
                                        <el-option label="个人" value="个人"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="回复方式：" class="bt">
                                    <el-select v-model="formData.answertype" placeholder="请选择回复方式" class="input-text">
                                        <el-option label="电话" value="complain"></el-option>
                                        <el-option label="网络" value="consult"></el-option>
                                        <el-option label="信件" value="enforcement"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.providetypecodeid">
                                <el-form-item label="市场主体类型" class="bt" prop="providetypecodeid">
                                    <el-cascader
                                            filterable
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
                                <el-form-item label="类别：" class="bt" prop="providelb">
                                    <el-select v-model="formData.providelb" placeholder="请选择类别" class="input-text">
                                        <el-option label="儿童" value="儿童"></el-option>
                                        <el-option label="妇女" value="妇女"></el-option>
                                        <el-option label="老人" value="老人"></el-option>
                                        <el-option label="其他" value="其他"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="工单分类：" class="bt">
                                    <el-select v-model="formData.focus" placeholder="请选择工单分类" class="input-text">
                                        <el-option label="普通" value="1"></el-option>
                                        <el-option label="特殊" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="区域：" class="bt">
                                    <el-select v-model="formData.region" placeholder="请选择区域" class="input-text">
                                        <el-option label="顺德区" value="顺德区"></el-option>
                                        <!--<el-option label="乐从镇" value="乐从镇"></el-option>-->
                                        <!--<el-option label="陈村镇" value="陈村镇"></el-option>-->
                                        <!--<el-option label="勒流镇" value="勒流镇"></el-option>-->
                                        <!--<el-option label="大良镇" value="大良镇"></el-option>-->
                                        <!--<el-option label="杏坛镇" value="杏坛镇"></el-option>-->
                                        <!--<el-option label="均安镇" value="均安镇"></el-option>-->
                                        <!--<el-option label="容桂镇" value="容桂镇"></el-option>-->
                                        <!--<el-option label="龙江镇" value="龙江镇"></el-option>-->
                                        <!--<el-option label="伦教镇" value="伦教镇"></el-option>-->
                                        <!--<el-option label="北滘镇" value="北滘镇"></el-option>-->
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="24">
                                <el-form-item label="投诉人地址：" class="bt">
                                    <el-input
                                            v-model="formData.rqstaddress"
                                            placeholder="请输入投诉人地址"
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
                    <el-form :model="formData" label-width="150px"
                             ref="complaintformTwo"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="名称：" class="bt" prop="subjectname">
                                    <el-input v-model="formData.subjectname" placeholder="请输入名称"
                                              class="input-text"></el-input>
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
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="联系电话：" class="bt" prop="subjectphone">
                                    <el-input
                                            v-model="formData.subjectphone"
                                            placeholder="请输入联系电话"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="经营地址：" class="bt" prop="subjectaddress">
                                    <el-input
                                            v-model="formData.subjectaddress"
                                            placeholder="请输入经营地址"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="实际注册地址：" class="bt">-->
                            <!--                  <el-input v-model="formData.subjectzcadd" placeholder="请输入实际注册地址" class="input-text"></el-input>-->
                            <!--                </el-form-item>-->
                            <!--              </el-col>-->
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="核查名称：" class="bt">-->
                            <!--                  <el-input v-model="formData.cksubjectname" placeholder="请输入核查名称" class="input-text"></el-input>-->
                            <!--                </el-form-item>-->
                            <!--              </el-col>-->
                            <!--              <el-col :xs="24" :sm="24" :md="24" :lg="24">-->
                            <!--                <el-form-item label="核查经营地址：" class="bt">-->
                            <!--                  <el-input-->
                            <!--                    v-model="formData.cksubjectaddress"-->
                            <!--                    placeholder="请输入核查经营地址"-->
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
                    <el-form :model="formData" label-width="150px"
                             ref="complaintformThree"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                                    <el-input v-model="formData.objname" placeholder="请输入商品/服务名称"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="商品品牌：" class="bt" prop="brand">
                                    <el-autocomplete
                                            class="input-text"
                                            v-model="formData.brand"
                                            :fetch-suggestions="querySearch"
                                            placeholder="请输入商品品牌"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="涉及金额：" class="bt" prop="objmoney">
                                    <el-input v-model.number="formData.objmoney" placeholder="请输入涉及金额" class="input-text">
                                        <template slot="append">元</template>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.objclassifycodeid">
                                <el-form-item label="涉及客体分类：" class="bt" prop="objclassifycodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="objectCategoryText?objectCategoryText:'请选择涉及客体分类'"
                                            :options="objclassify1"
                                            v-model="formData.objclassifycodeid"
                                            @change="handleChangeObjclassify1"
                                            :show-all-levels="false"
                                            class="bt1"
                                            :class="{cascader_placeholder:objectCategoryText}"
                                    ></el-cascader>
                                    <!--<el-select v-model="formData.objclassify" placeholder="请选择" class="input-text">
                                                        <el-option label="公寓" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <!-- <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.complaintstypecodeid">
                                             <el-form-item label="涉及问题分类：" class="bt">
                                               <el-cascader filterable
                                                 :placeholder="complaintsTypeText?complaintsTypeText:'请选择涉及问题分类'"
                                                 :options="complaintstype1"
                                                 v-model="formData.complaintstypecodeid"
                                                 @change="handleChangeComplaintstype1"
                                                 :show-all-levels="false"
                                                 class="bt1"
                                                 :class="{cascader_placeholder:complaintsTypeText}"
                                               ></el-cascader>
                                               &lt;!&ndash;<el-select v-model="formData.problemtype" placeholder="请选择" class="input-text">
                                                 <el-option label="其他" value="其他"></el-option>
                                               </el-select>&ndash;&gt;
                                             </el-form-item>
                            </el-col>-->
                            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                                <el-form-item label="关键字：" class="bt" prop="keyword">
                                    <el-input v-model="formData.keyword" placeholder="请输入关键字"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="生产企业：" class="bt">
                                    <el-input
                                            v-model="formData.producecompany"
                                            placeholder="请输入生产企业"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否网购：" class="bt" prop="isweb">
                                    <el-select v-model="formData.isweb" placeholder="请选择是否网购" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="商品进口标志: " class="bt">-->
<!--                                    <el-input v-model="formData.importmark" placeholder="请输入商品进口标志"-->
<!--                                              class="input-text"></el-input>-->
<!--                                </el-form-item>-->
<!--                            </el-col>-->
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="型号规格: " class="bt">-->
<!--                                    <el-input-->
<!--                                            v-model="formData.typespecification"-->
<!--                                            placeholder="请输入型号规格"-->
<!--                                            class="input-text"-->
<!--                                    ></el-input>-->
<!--                                </el-form-item>-->
<!--                            </el-col>-->
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="商品数量: " class="bt">-->
<!--                                    <el-input v-model="formData.quantity" placeholder="请输入商品数量"-->
<!--                                              class="input-text"></el-input>-->
<!--                                </el-form-item>-->
<!--                            </el-col>-->
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="计量单位: " class="bt">-->
<!--                                    <el-input v-model="formData.measureunit" placeholder="请输入计量单位"-->
<!--                                              class="input-text"></el-input>-->
<!--                                </el-form-item>-->
<!--                            </el-col>-->
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="商品/服务价格: " class="bt">-->
<!--                                    <el-input v-model="formData.price" placeholder="请输入商品/服务价格" class="input-text">-->
<!--                                        <template slot="append">元</template>-->
<!--                                    </el-input>-->

<!--                                </el-form-item>-->
<!--                            </el-col>-->
<!--                            <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--                                <el-form-item label="购买日期: " class="bt">-->
<!--                                    <el-date-picker-->
<!--                                            v-model="formData.purchasingdate"-->
<!--                                            type="date"-->
<!--                                            class="input-text"-->
<!--                                            style="width: 100%;"-->
<!--                                            placeholder="选择购买日期"-->
<!--                                            @change="dateChanges"-->
<!--                                    ></el-date-picker>-->
<!--                                </el-form-item>-->
<!--                            </el-col>-->
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
                    <el-form :model="formData" label-width="150px"
                             ref="complaintformFour"
                             :rules="formDataRules">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="formData.complaintstypecodeid">
                                <el-form-item label="涉及问题分类" prop="complaintstypecodeid">
                                    <el-cascader
                                            filterable
                                            :placeholder="problemTypeText?problemTypeText:'请选择涉及问题分类'"
                                            :options="complaintstype1"
                                            v-model="formData.complaintstypecodeid"
                                            @change="handleChangeComplaintstype1"
                                            :show-all-levels="false"
                                            class="input-text"
                                            :class="{cascader_placeholder:problemTypeText}"
                                    ></el-cascader>
                                    <!--<el-select v-model="economyform.problemtype" placeholder="请选择" class="input-text">
                                                        <el-option label="其他" value="1"></el-option>
                                    </el-select>-->
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="问题陈述：" class="bt" prop="problemdescribe">
                                    <el-input
                                            v-model="formData.problemdescribe"
                                            placeholder="请输入"
                                            type="textarea"
                                            :autosize="{ minRows: 3, maxRows: 5}"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="事件级别：" class="bt" prop="eventlevel">
                                    <el-select v-model="formData.eventlevel" placeholder="请选择" class="input-text">
                                        <el-option label="特别紧急事件" value="特别紧急事件"></el-option>
                                        <el-option label="紧急事件" value="紧急事件"></el-option>
                                        <el-option label="一般事件" value="一般事件"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="政务标签：" class="bt" prop="govlabeloutput">
                                    <el-input v-model="formData.govlabeloutput" placeholder="请输入"
                                              class="input-text"></el-input>
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
                    <el-form :model="formData" label-width="150px" ref="formData">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                <el-form-item label="是否出现场：" class="bt">
                                    <el-select v-model="formData.isoutmall" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="是"></el-option>
                                        <el-option label="否" value="否"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col class="tr"></el-col>
                            <el-col :xs="24" :sm="12" :md="8" :lg="24">
                                <el-form-item label="上传文件：" class="bt">
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
                                </el-form-item>

                                <!-- <el-form-item label="附件清单：" class="bt mt20 accessoriesList">
                                                  <ul>
                                                    <li v-for="(item,index) in this.materialList" :key="index" class="mb10">
                                                      <span class="filesname">{{index+1}}、</span>
                                                      <span @click="download(item.filesavepath)">{{item.attachfilename}}</span>
                                                      </li>
                                                  </ul>
                                </el-form-item>-->
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>
       <!-- <el-tab-pane label="处理信息" name="handle">
            <el-collapse v-model="processInofActiveNames" class="accordion">
                <el-collapse-item name="1">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">处理信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData" label-width="220px" ref="formData">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="登记单编号：" class="bt">
                                    <el-input v-model="formData.number" placeholder="请输入" class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="案由：" class="bt">
                                    <el-input v-model="formData.cause" placeholder="请输入" class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="2">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">涉及主体信息</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData" label-width="220px" ref="formData">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="是否具有营业执照：" class="bt">
                                    <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="1"></el-option>
                                        <el-option label="否" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="统一社会信用代码/注册号：" class="bt">
                                    <el-input v-model="formData.registration" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="所在行业类别：" class="bt">
                                    <el-select
                                            v-model="formData.industryCategory"
                                            placeholder="请选择"
                                            class="input-text"
                                    >
                                        <el-option label="IT" value="complain"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="市场名称：" class="bt">
                                    <el-input v-model="formData.marketName" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="联系方式：" class="bt">
                                    <el-input placeholder="请输入" v-model="formData.phone">
                                        <el-select
                                                slot="prepend"
                                                placeholder="请选择"
                                                style="width: 110px;"
                                                v-model="formData.phoneType"
                                        >
                                            <el-option label="移动电话" value="移动电话"></el-option>
                                            <el-option label="固定电话" value="固定电话"></el-option>
                                        </el-select>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="地址：" class="bt">
                                    <el-input placeholder="请输入" v-model="formData.addr">
                                        <el-select
                                                slot="prepend"
                                                placeholder="请选择"
                                                style="width: 110px;"
                                                v-model="formData.region"
                                        >
                                            <el-option label="顺德区" value="1"></el-option>
                                            <el-option label="禅城区" value="2"></el-option>
                                            <el-option label="南海区" value="3"></el-option>
                                            <el-option label="三水区" value="4"></el-option>
                                            <el-option label="高明区" value="5"></el-option>
                                        </el-select>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="是否转移其他部门：" class="bt">
                                    <el-select v-model="formData.department" placeholder="请选择" class="input-text">
                                        <el-option label="消保" value="complain"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="应急处置进展状态：" class="bt">
                                    <el-select v-model="formData.progress" placeholder="请选择" class="input-text">
                                        <el-option label value="complain"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="反馈登记人：" class="bt">
                                    <el-input v-model="formData.registrar" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="协助单位类型：" class="bt">
                                    <el-select v-model="formData.unitType" placeholder="请选择" class="input-text">
                                        <el-option label value="complain"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="办案人员：" class="bt">
                                    <el-input v-model="formData.registrar" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="办案人员电话：" class="bt">
                                    <el-input v-model="formData.registrarPhone" placeholder="请输入"
                                              class="input-text"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="群体投诉案件：" class="bt">
                                    <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="1"></el-option>
                                        <el-option label="否" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="涉外投诉案件：" class="bt">
                                    <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="1"></el-option>
                                        <el-option label="否" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="对投诉中发现违法行为实施行政处罚：" class="bt">
                                    <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="1"></el-option>
                                        <el-option label="否" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="是否转立案：" class="bt">
                                    <el-select v-model="formData.license" placeholder="请选择" class="input-text">
                                        <el-option label="是" value="1"></el-option>
                                        <el-option label="否" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
                <el-collapse-item name="5">
                    <div slot="title" @click.stop>
                        <el-row class="el-accordion">
                            <el-col :span="12">分派下级</el-col>
                            <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                        </el-row>
                    </div>
                    <el-form :model="formData" label-width="150px" ref="formData">
                        <el-row>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12">
                                <el-form-item label="成员单位：" class="bt">
                                    <el-select v-model="formData.appear" placeholder="请选择" class="input-text">
                                        <el-option label="消保" value="1"></el-option>
                                        <el-option label="12315" value="2"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                <el-form-item label="分派意见：" class="bt">
                                    <el-input
                                            v-model="formData.describe"
                                            placeholder="请输入分派意见（500字以内）"
                                            type="textarea"
                                            :autosize="{ minRows: 3, maxRows: 5}"
                                            class="input-text"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>-->
    </el-tabs>
</template>

<script>
    import {selectBaseCodeTree, getMaterialLists} from "../../../api/api";
    import {
        getArrayByStringId,
        byIdGetLabels,
        getIdByName
    } from "../../../utils/form-util";

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
                industrynameText: null,
                problemTypeText:"",
                businesstype1: [], //业务分类
                complaintstype1: [], //涉及问题分类
                objclassify1: [], //涉及客体分类下拉框选项
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
                    "correlationInformation"
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
                objectCategoryText: null,
                complaintsTypeText: null,
                formDataRules: {
                    // rqsttitle: [
                    //     {required: true, message: "请输入标题", trigger: "blur"}
                    // ],
                    // rqstperson: [
                    //     {required: true, message: "请输入投诉人姓名", trigger: "blur"}
                    // ],
                    // gender: [
                    //     {required: true, message: "请选择性别", trigger: "blur"}
                    // ],
                    // rqstnumber: [
                    //     {required: true, message: "请输入来电号码", trigger: "blur"}
                    // ],
                    // providetypecodeid: [
                    //     {required: true, message: "请选择市场主体类型"}
                    // ],
                    // providelb: [
                    //     {required: true, message: "请选择类别", trigger: "blur"}
                    // ],
                    // subjectname: [
                    //     {required: true, message: "请输入被投诉对象名称", trigger: "blur"}
                    // ],
                    // subjectphone: [
                    //      {required: true, message: "请输入联系电话", trigger: "blur"}
                    //  ],
                    // subjectaddress: [
                    //     {required: true, message: "请输入被投诉对象地址", trigger: "blur"}
                    // ],
                    objname: [
                        {required: true, message: "请输入商品/服务名称", trigger: "blur"}
                    ],
                    // objmoney: [
                    //     {required: true, message: "请输入涉及金额", trigger: "blur",type: 'number'}
                    // ],
                    objclassifycodeid: [
                        {required: true, message: "请选择客体类别"}
                    ],
                    keyword: [
                        {required: true, message: "请输入关键字", trigger: "blur"}
                    ],
                    isweb: [
                        {required: true, message: "请选择是否网购", trigger: "blur"}
                    ],
                    complaintstypecodeid: [
                        {required: true, message: "请选择投诉问题类别"}
                    ],
                    problemdescribe: [
                        {required: true, message: "请输入问题陈述", trigger: "blur"},
                        {max:1200 ,message: "问题陈述字数不得超过1200" }
                    ],
                    brand: [
                        {required: true, message: "请输入商品品牌", trigger: "blur"}
                    ],
                    // eventlevel: [
                    //     {required: true, message: "请选择事件级别", trigger: "blur"}
                    // ],
                    // govlabeloutput: [
                    //     {required: true, message: "请输入政务标签", trigger: "blur"}
                    // ],
                    usertype: [
                        {required: true, message: "请选择反映类型", trigger: "blur"}
                    ],

                    //
                    industrynamecodeid: [
                        {required: true, message: "请选择业务分类"}
                    ],
                    businesstypecodeid: [
                        {required: true, message: "请选择行业分类"}
                    ],
                    // appealsource: [
                    //     {required: true, message: "请选择诉求来源", trigger: "blur"}
                    // ],
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
                this.$refs["complaintformFour"].validate(
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
                this.formData.purchasingdate = date;
            },
            toUploadOpenComplain(type) {
                // alert(12);
                this.fileUploadVisible = true;
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url)
                } else {
                    this.download(file.url)
                }
            },
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url, "_blank")
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

            handleChangeBusinesstype1(array) {
                let label = this.getLabel(this.businesstype1, array[array.length - 1])
                this.formData.businesstype = byIdGetLabels(label, this.businesstype1).join("-")//获取所有label值,用"-"进行连接,String格式
                this.formData.businesstypecodeid = array
            },
            handleChangeProvidetype1(array) {
                let label = this.getLabel(this.providetype1, array[array.length - 1]);
                this.formData.providetype = byIdGetLabels(label, this.providetype1).join(
                    "-"
                );
                this.formData.providetypecodeid = array;
            },
            handleChangeObjclassify1(array) {
                let label = this.getLabel(this.objclassify1, array[array.length - 1]);
                this.formData.objclassify = byIdGetLabels(label, this.objclassify1).join(
                    "-"
                );
                this.formData.objclassifycodeid = array;
            },
            handleChangeComplaintstype1(array) {
                let label = this.getLabel(this.complaintstype1, array[array.length - 1]);
                this.formData.complaintstype = byIdGetLabels(
                    label,
                    this.complaintstype1
                ).join("-");
                this.formData.complaintstypecodeid = array;
            },
            handleChangeProblemtype1(array) {
                let label = this.getLabel(this.problemtype1, array[array.length - 1]);
                this.economyform.problemtype = byIdGetLabels(
                    label,
                    this.problemtype1
                ).join("-");
                this.economyform.problemtypecodeid = array;
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
                    if (!this.formData.businesstypecodeid) {
                        //获取原始中文
                        this.industrynameText = this.formData.industryname
                        //判断是否该中文符合基础数据
                        this.formData.businesstypecodeid = getIdByName(this.formData.businesstype, this.businesstype1)
                    }
                    if (this.formData.businesstypecodeid != null && this.formData.businesstypecodeid != "") {
                        this.formData.businesstypecodeid = getArrayByStringId(this.formData.businesstypecodeid, this.businesstype1)
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
                    this.providetype1 = this.data; //类型
                    //赋值默认值
                    if (!this.formData.providetypecodeid) {
                        //获取原始中文
                        this.provideTypeText = this.formData.providetype;
                        //判断是否该中文符合基础数据
                        this.formData.providetypecodeid = getIdByName(
                            this.formData.providetype,
                            this.providetype1
                        );
                    }
                    if (
                        this.formData.providetypecodeid != null &&
                        this.formData.providetypecodeid != ""
                    ) {
                        this.formData.providetypecodeid = getArrayByStringId(
                            this.formData.providetypecodeid,
                            this.providetype1
                        );
                    }
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
                    if (!this.formData.objclassifycodeid) {
                        //获取原始中文
                        this.objectCategoryText = this.formData.objclassify;
                        //判断是否该中文符合基础数据
                        this.formData.objclassifycodeid = getIdByName(
                            this.formData.objclassify,
                            this.objclassify1
                        );
                    }
                    if (
                        this.formData.objclassifycodeid != null &&
                        this.formData.objclassifycodeid != ""
                    ) {
                        this.formData.objclassifycodeid = getArrayByStringId(
                            this.formData.objclassifycodeid,
                            this.objclassify1
                        );
                    }
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
                    if (!this.formData.complaintstypecodeid) {
                        //获取原始中文
                        this.complaintsTypeText = this.formData.complaintstype;
                        //判断是否该中文符合基础数据
                        this.formData.complaintstypecodeid = getIdByName(
                            this.formData.complaintstype,
                            this.complaintstype1
                        );
                    }
                    if (this.formData.complaintstypecodeid) {
                        this.formData.complaintstypecodeid = getArrayByStringId(
                            this.formData.complaintstypecodeid,
                            this.complaintstype1
                        );
                    }
                });
            },
            //涉及问题分类下拉框级联数据
            /* getProblemtype1(){
                         let params={
                             type:"D",
                         }
                         selectBaseCodeTree(params).then(data => {
                             this.data=data.data.data
                             let list = [];
                             for (const i in this.data) {
                                 let item={
                                     value:this.data[i].codeid,
                                     label: this.data[i].comment,
                                 }
                                 if(this.data[i].basecodeforms.length>0){
                                     item.children=this.getLevels(this.data[i].basecodeforms)
                                 }
                                 list.push(item)
                             }
                             this.data=list
                             // this.problemtype1=this.data//业务分类
                             this.complaintstype1=this.data//业务分类
                         });
                     },*/
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
                            };
                            this.fileLists.push(fileInfo);
                        }
                        // console.log(res);
                        // console.log(this.materialList);
                    }
                });
            },
            selectdata() {
                if (
                    this.formData.providetypecodeid != null &&
                    this.formData.providetypecodeid != ""
                ) {
                    this.formData.providetypecodeid = getArrayByStringId(
                        this.formData.providetypecodeid,
                        this.providetype1
                    );
                }
                if (
                    this.formData.objclassifycodeid != null &&
                    this.formData.objclassifycodeid != ""
                ) {
                    this.formData.objclassifycodeid = getArrayByStringId(
                        this.formData.objclassifycodeid,
                        this.objclassify1
                    );
                }
                if (
                    this.formData.complaintstypecodeid != null &&
                    this.formData.complaintstypecodeid != ""
                ) {
                    this.formData.complaintstypecodeid = getArrayByStringId(
                        this.formData.complaintstypecodeid,
                        this.complaintstype1
                    );
                }
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

        created() {
            this.getBusinesstype1();
            this.getComplaintstype1();
            this.getProvidetype1();
            this.getObjectcategory1();
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
