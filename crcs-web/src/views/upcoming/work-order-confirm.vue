<template>
  <section class="workOrderConfirm" v-loading="loading">
    <el-tabs
      type="border-card"
      style="width: 96%;margin: 0 auto;"
      @tab-click="flow"
      v-model="aictabs"
    >
      <el-tab-pane label="基本信息">
        <el-collapse v-model="activeNames" class="accordion">
          <el-collapse-item name="1">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion" :model="datagriddata">
                <el-col :span="12">
                  <em>1.</em> 定单信息
                </el-col>
                <el-col
                  :span="10"
                  style="color: #48576a;text-align: right"
                >定单号：{{datagriddata.tserialnum}}</el-col>
              </el-row>
            </div>
            <el-form :model="seats" label-width="150px" ref="seats" :rules="seatsRules">
              <el-row>
                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                  <el-form-item label="接听坐席：" class="bt" prop="seatsname">
                    <el-input v-model="seats.seatsname" placeholder="请输入" class="input-text"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                  <el-form-item label="登记时间：" class="bt" prop="seatsregistrationtime">
                    <el-date-picker
                      v-model="seats.seatsregistrationtime"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                  <el-form-item label="性别：" class="bt" prop="seatssex">
                    <el-select v-model="seats.seatssex" placeholder="请选择性别" class="input-text">
                      <el-option label="男" value="男"></el-option>
                      <el-option label="女" value="女"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                  <el-form-item label="语种：" class="bt" prop="seatslanguage">
                    <el-select v-model="seats.seatslanguage" placeholder="请选择语种" class="input-text">
                      <el-option label="普通话" value="普通话"></el-option>
                      <el-option label="粤语" value="粤语"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-collapse-item>
          <el-collapse-item name="2" v-if="economy">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion" :model="datagriddata">
                <el-col :span="12">
                  <em>2.</em> 经济违法行为举报表单
                </el-col>
                <el-col
                  :span="10"
                  style="color: #48576a;text-align: right"
                >表单号：{{datagriddata.serialnum}}</el-col>
              </el-row>
            </div>
            <el-tabs type="card" v-model="economicsActiveName">
              <el-tab-pane label="登记信息" name="registration">
                <el-collapse v-model="registrationActiveNames" class="accordion">
                  <el-collapse-item name="1">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">提供方信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="comeconomicmodule"
                      label-width="150px"
                      ref="comeconomicmoduleProvide"
                      :rules="comeconomicmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="表单类型：" class="bt" prop="formtype">
                            <div
                              style="line-height: 35px;float: left"
                            >{{comeconomicmodule.formtype}}</div>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="标题：" class="bt" prop="rqsttitle">
                            <el-input
                              v-model="comeconomicmodule.rqsttitle"
                              placeholder="请输入标题"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="举报人姓名：" class="bt" prop="rqstperson">
                            <el-input
                              v-model="comeconomicmodule.rqstperson"
                              placeholder="请输入举报人姓名"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="年龄：" class="bt" prop="age">
                            <el-input
                              v-model.number="comeconomicmodule.age"
                              placeholder="请输入年龄"
                              class="input-text"
                            >
                              <template slot="append">岁</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="性别：" class="bt" prop="gender">
                            <el-select
                              v-model="comeconomicmodule.gender"
                              placeholder="请选择性别"
                              class="input-text"
                            >
                              <el-option label="男" value="男"></el-option>
                              <el-option label="女" value="女"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="民族：" class="bt" prop="nation">
                            <el-input
                              v-model="comeconomicmodule.nation"
                              placeholder="请输入民族"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                            <el-input
                              v-model="comeconomicmodule.rqstnumber"
                              placeholder="请输入来电号码"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="联系电话：" class="bt" prop="linknumber">
                            <el-input
                              v-model="comeconomicmodule.linknumber"
                              placeholder="请输入联系电话"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="身份：" class="bt" prop="status">
                            <el-input
                              v-model="comeconomicmodule.status"
                              placeholder="请输入身份"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="证件类型：" class="bt" prop="certificate">
                            <el-input
                              v-model="comeconomicmodule.certificate"
                              placeholder="请输入证件类型"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="证件号码：" class="bt" prop="idnumber">
                            <el-input
                              v-model="comeconomicmodule.idnumber"
                              placeholder="请输入证件号码"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="职业：" class="bt" prop="career">
                            <el-select
                              v-model="comeconomicmodule.career"
                              placeholder="请选择职业"
                              class="input-text"
                            >
                              <el-option label="工程师" value="工程师"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="工作单位：" class="bt" prop="company">
                            <el-input
                              v-model="comeconomicmodule.company"
                              placeholder="请输入工作单位"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="反映类型：" class="bt" prop="usertype">
                            <el-select
                              v-model="comeconomicmodule.usertype"
                              placeholder="请选择反映类型"
                              class="input-text"
                            >
                              <el-option label="个人" value="个人"></el-option>
                              <el-option label="企业" value="企业"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="回复方式：" class="bt" prop="answertype">
                            <el-select
                              v-model="comeconomicmodule.answertype"
                              placeholder="请选择回复方式"
                              class="input-text"
                            >
                              <el-option label="电话" value="电话"></el-option>
                              <el-option label="网上" value="网上"></el-option>
                              <el-option label="信件" value="信件"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="工单分类：" class="bt" prop="focus">
                            <el-select
                              v-model="comeconomicmodule.focus"
                              placeholder="请选择工单分类"
                              class="input-text"
                            >
                              <el-option label="普通" value="普通"></el-option>
                              <el-option label="特殊" value="特殊"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="类别：" class="bt" prop="providelb">
                            <el-select
                              v-model="comeconomicmodule.providelb"
                              placeholder="请选择类别"
                              class="input-text"
                            >
                              <el-option label="维稳" value="维稳"></el-option>
                              <el-option label="督办" value="督办"></el-option>
                              <el-option label="催办" value="催办"></el-option>
                              <el-option label="疑问" value="疑问"></el-option>
                              <el-option label="重大" value="重大"></el-option>
                              <el-option label="紧急" value="紧急"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="投诉票据：" class="bt" prop="omplaintpaper">
                            <el-input
                              v-model="comeconomicmodule.omplaintpaper"
                              placeholder="请输入投诉票据"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="残疾人标识：" class="bt" prop="disabilityidentification">
                            <el-input
                              v-model="comeconomicmodule.disabilityidentification"
                              placeholder="请输入残疾人标识"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="提供方类别：" class="bt" prop="xxx">
                            <el-select
                              v-model="comeconomicmodule.xxx"
                              placeholder="请选择分类"
                              class="input-text"
                            >
                              <el-option label value></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                          <el-form-item label="举报人地址：" class="bt" prop="rqstaddress">
                            <el-input
                              v-model="comeconomicmodule.rqstaddress"
                              placeholder="请输入举报人地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="联系地址：" class="bt" prop="contactaddress">
                            <el-input
                              v-model="comeconomicmodule.contactaddress"
                              placeholder="请输入联系地址"
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
                        <el-col :span="12">涉及主体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="comeconomicmodule"
                      label-width="150px"
                      ref="comeconomicmoduleSubject"
                      :rules="comeconomicmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="名称：" class="bt" prop="subjectname">
                            <el-input
                              v-model="comeconomicmodule.subjectname"
                              placeholder="请输入名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="联系方式：" class="bt" prop="linktype">
                            <el-input
                              v-model="comeconomicmodule.linktype"
                              placeholder="请输入联系方式"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="核查名称：" class="bt" prop="cksubjectname">
                            <el-input
                              v-model="comeconomicmodule.cksubjectname"
                              placeholder="请输入核查名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="经营地址：" class="bt" prop="subjectaddress">
                            <el-input
                              v-model="comeconomicmodule.subjectaddress"
                              placeholder="请输入经营地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="实际注册地址：" class="bt" prop="subjectzcadd">
                            <el-input
                              v-model="comeconomicmodule.subjectzcadd"
                              placeholder="请输入实际注册地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="核查经营地址：" class="bt" prop="cksubjectaddress">
                            <el-input
                              v-model="comeconomicmodule.cksubjectaddress"
                              placeholder="请输入核查经营地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="3">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">涉及客体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="comeconomicmodule"
                      label-width="150px"
                      ref="comeconomicmoduleObject"
                      :rules="comeconomicmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                            <el-input
                              v-model="comeconomicmodule.objname"
                              placeholder="请输入商品/服务名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品品牌：" class="bt" prop="brand">
                            <el-input
                              v-model="comeconomicmodule.brand"
                              placeholder="请输入商品品牌"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及客体分类：" class="bt" prop="objclassify">
                            <el-select
                              v-model="comeconomicmodule.objclassify"
                              placeholder="请选择涉及客体分类"
                              class="input-text"
                            >
                              <el-option label="存、停车管理服务" value="存、停车管理服务"></el-option>
                              <el-option label="宽带接入" value="宽带接入"></el-option>
                              <el-option label="公寓" value="公寓"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及金额：" class="bt" prop="objmoney">
                            <el-input
                              v-model.number="comeconomicmodule.objmoney"
                              placeholder="请输入涉及金额"
                              class="input-text"
                            >
                              <template slot="append">元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="生产企业：" class="bt" prop="producecompany">
                            <el-input
                              v-model="comeconomicmodule.producecompany"
                              placeholder="请输入生产企业"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否网购：" class="bt" prop="isweb">
                            <el-select
                              v-model="comeconomicmodule.isweb"
                              placeholder="请选择是否网购"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8"></el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="型号规格：" class="bt" prop="typespecification">
                            <el-input
                              v-model="comeconomicmodule.typespecification"
                              placeholder="请输入型号规格"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品数量：" class="bt" prop="quantity">
                            <el-input
                              v-model="comeconomicmodule.quantity"
                              placeholder="请输入商品数量"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="计量单位：" class="bt" prop="measureunit">
                            <el-input
                              v-model="comeconomicmodule.measureunit"
                              placeholder="请输入计量单位"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品/服务价格：" class="bt" prop="price">
                            <el-input
                              v-model="comeconomicmodule.price"
                              placeholder="请输入商品/服务价格"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="购买日期：" class="bt" prop="purchasingdate">
                            <el-date-picker
                              v-model="comeconomicmodule.purchasingdate"
                              type="date"
                              class="input-text"
                              placeholder="选择购买日期"
                            ></el-date-picker>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="4">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">业务具体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="comeconomicmodule"
                      label-width="150px"
                      ref="comeconomicmoduleSpecific"
                      :rules="comeconomicmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及问题分类：" class="bt" prop="problemtype">
                            <el-select
                              v-model="comeconomicmodule.problemtype"
                              placeholder="请选择涉及问题分类"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                              <el-option label="合同" value="合同"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉嫌违法行为：" class="bt" prop="illegalrules">
                            <el-select
                              v-model="comeconomicmodule.illegalrules"
                              placeholder="请选择涉嫌违法行为"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="事件级别：" class="bt" prop="eventlevel">
                            <el-select
                              v-model="comeconomicmodule.eventlevel"
                              placeholder="请选择事件级别"
                              class="input-text"
                            >
                              <el-option label="特别紧急事件" value="特别紧急事件"></el-option>
                              <el-option label="紧急事件" value="紧急事件"></el-option>
                              <el-option label="一般事件" value="一般事件"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="关键字：" class="bt" prop="keyword">
                            <el-input
                              v-model="comeconomicmodule.keyword"
                              placeholder="请输入关键字"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="政务标签：" class="bt" prop="govlabel">
                            <el-input
                              v-model="comeconomicmodule.govlabel"
                              placeholder="请输入政务标签"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="业务分类：" class="bt" prop="differserv">
                            <el-select
                              v-model="comeconomicmodule.differserv"
                              placeholder="请选择业务分类"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="侵权分类：" class="bt" prop="tortclassify">
                            <el-select
                              v-model="comeconomicmodule.tortclassify"
                              placeholder="请选择侵权分类"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="案值：" class="bt" prop="anestimate">
                            <el-input
                              v-model.number="comeconomicmodule.anestimate"
                              placeholder="请输入案值"
                              class="input-text"
                            >
                              <template slot="append">元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="经济损失值：" class="bt" prop="economicloss">
                            <el-input
                              v-model.number="comeconomicmodule.economicloss"
                              placeholder="请输入经济损失值"
                              class="input-text"
                            >
                              <template slot="append">元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="人事伤害类型：" class="bt" prop="personalinjury">
                            <el-select
                              v-model="comeconomicmodule.personalinjury"
                              placeholder="请选择人事伤害类型"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="人事伤害确认程度：" class="bt" prop="injurydegree">
                            <el-input
                              v-model="comeconomicmodule.injurydegree"
                              placeholder="请输入人事伤害确认程度"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="受伤人数：" class="bt" prop="injuries">
                            <el-input
                              v-model="comeconomicmodule.injuries"
                              placeholder="请输入受伤人数"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="消防安全确认程度：" class="bt" prop="consumptionsafety">
                            <el-input
                              v-model="comeconomicmodule.consumptionsafety"
                              placeholder="请输入消防安全确认程度"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="消防安全事件类型：" class="bt" prop="consumptionsafetytype">
                            <el-select
                              v-model="comeconomicmodule.consumptionsafetytype"
                              placeholder="请选择消防安全事件类型"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <!--<el-form-item label="绝产亩地：" class="bt" prop="even">
                            <el-input
                              v-model="comeconomicmodule.even"
                              placeholder="请输入绝产亩地"
                              class="input-text"
                            >
                              <template slot="append">亩</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="减少亩地：" class="bt" prop="reduction">
                            <el-input
                              v-model="comeconomicmodule.reduction"
                              placeholder="请输入减少亩地"
                              class="input-text"
                            >
                              <template slot="append">亩</template>
                            </el-input>
                          </el-form-item>-->
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="简要情况：" class="bt" prop="briefing">
                            <el-input
                              v-model="comeconomicmodule.briefing"
                              placeholder="请输入简要情况"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="问题描述：" class="bt" prop="problemdescribe">
                            <el-input
                              v-model="comeconomicmodule.problemdescribe"
                              placeholder="请输入问题描述"
                              type="textarea"
                              :autosize="{ minRows: 3, maxRows: 5}"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="5">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">处理相关信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="comeconomicmodule"
                      label-width="150px"
                      ref="comeconomicmoduleHandle"
                      :rules="comeconomicmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否出现场：" class="bt">
                            <el-select
                              v-model="comeconomicmodule.isoutmall"
                              placeholder="请选择是否出现场"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否反馈：" class="bt">
                            <el-select
                              v-model="comeconomicmodule.isfeedback"
                              placeholder="请选择是否反馈"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否需要奖励：" class="bt">
                            <el-select
                              v-model="comeconomicmodule.isaward"
                              placeholder="请选择是否需要奖励"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                          <el-form-item label="相关附件：" class="bt" prop>
                            <el-button
                              size="small"
                              @click="toUploadOpen('word')"
                              class="el-button--primary"
                              style="float: left"
                            >
                              <i class="fa fa-upload"></i>
                              选择文件
                            </el-button>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                </el-collapse>
              </el-tab-pane>
              <el-tab-pane label="处理信息" name="handle">
                <el-collapse v-model="processInofActiveNames" class="accordion">
                  <el-collapse-item name="1">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">处理信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="processInfoForm" label-width="220px" ref="processInfoForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="登记单编号：" class="bt" prop>
                            <el-input
                              v-model="processInfoForm.number"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="案由：" class="bt" prop>
                            <el-input
                              v-model="processInfoForm.cause"
                              placeholder="请输入"
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
                        <el-col :span="12">涉及主体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="mainForm" label-width="220px" ref="mainForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="是否具有营业执照：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="统一社会信用代码/注册号：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registration"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="所在行业类别：" class="bt" prop>
                            <el-select
                              v-model="mainForm.industryCategory"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="IT" value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="市场名称：" class="bt" prop>
                            <el-input
                              v-model="mainForm.marketName"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="联系方式：" class="bt" prop>
                            <el-input placeholder="请输入" v-model="mainForm.phone">
                              <el-select
                                v-model="mainForm.contactMode"
                                slot="prepend"
                                placeholder="请选择"
                                style="width: 110px;"
                              >
                                <el-option label="移动电话" value="1"></el-option>
                                <el-option label="固定电话" value="2"></el-option>
                              </el-select>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="地址：" class="bt" prop>
                            <el-input placeholder="请输入" v-model="mainForm.addr">
                              <el-select
                                v-model="mainForm.addrMode"
                                slot="prepend"
                                placeholder="请选择"
                                style="width: 110px;"
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
                          <el-form-item label="是否转移其他部门：" class="bt" prop>
                            <el-select
                              v-model="mainForm.department"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="消保" value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="应急处置进展状态：" class="bt" prop>
                            <el-select
                              v-model="mainForm.progress"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="反馈登记人：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrar"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="协助单位类型：" class="bt" prop>
                            <el-select
                              v-model="mainForm.unitType"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="办案人员：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrar"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="办案人员号码：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrarPhone"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="17" :lg="17">
                          <el-form-item label="书式案卷存放位置：" class="bt" prop>
                            <el-input v-model="mainForm.seat" placeholder="请输入" class="input-text"></el-input>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="3">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">奖励信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="awardForm" label-width="150px" ref="awardForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="检测报告号：" class="bt" prop>
                            <el-input v-model="awardForm.id" placeholder="请输入" class="input-text"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="领奖人身份证号：" class="bt" prop>
                            <el-input
                              v-model="awardForm.IDCard"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="奖励金额：" class="bt" prop>
                            <el-input
                              v-model="awardForm.amount"
                              placeholder="请输入"
                              class="input-text"
                            >
                              <template slot="append">万元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="奖励价值：" class="bt" prop>
                            <el-input v-model="awardForm.cost" placeholder="请输入" class="input-text">
                              <template slot="append">万元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="领奖时间：" class="bt" prop>
                            <el-date-picker
                              v-model="awardForm.receiveDate"
                              type="date"
                              class="input-text"
                              placeholder="选择日期"
                            ></el-date-picker>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="4">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">初步意见</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="opinionForm" label-width="150px" ref="opinionForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="处理意见：" class="bt" prop>
                            <el-input
                              v-model="opinionForm.text"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                          <el-form-item label="附件清单：" class="bt" prop>
                            <el-button
                              size="small"
                              @click="toUploadOpen('word')"
                              class="el-button--primary"
                              style="float: left"
                            >
                              <i class="fa fa-upload"></i>
                              选择文件
                            </el-button>
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
                    <el-form :model="assignForm" label-width="150px" ref="assignForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="成员单位：" class="bt" prop>
                            <el-select
                              v-model="assignForm.appear"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="消保" value="1"></el-option>
                              <el-option label="12315" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="分派意见：" class="bt" prop>
                            <el-input
                              v-model="assignForm.describe"
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
              </el-tab-pane>
            </el-tabs>
          </el-collapse-item>

          <el-collapse-item name="2" v-if="complaint">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion" :model="datagriddata">
                <el-col :span="12">
                  <em>2.</em> 消费投诉表单
                </el-col>
                <el-col
                  :span="10"
                  style="color: #48576a;text-align: right"
                >表单号：{{datagriddata.serialnum}}</el-col>
              </el-row>
            </div>
            <el-tabs type="card" v-model="economicsActiveName">
              <el-tab-pane label="登记信息" name="registration">
                <el-collapse
                  v-model="registrationActiveNames"
                  class="accordion"
                  v-if="'registration'===economicsActiveName"
                >
                  <el-collapse-item name="1">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">提供方信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="complaintmodule"
                      label-width="150px"
                      ref="complaintmoduleProvide"
                      :rules="complaintmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="表单类型：" class="bt" prop>
                            <div style="line-height: 35px;float: left">{{complaintmodule.formtype}}</div>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="标题：" class="bt" prop="rqsttitle">
                            <el-input
                              v-model="complaintmodule.rqsttitle"
                              placeholder="请输入标题"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <!-- <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                                    <el-form-item label="表单模板：" class="bt" prop="">
                                                        <el-select placeholder="请选择表单模板"
                                                                   class="input-text"
                                                                   v-model="complaintmodule.formmodule">
                                                            <el-option label="消费投诉业务-价格投诉" value="1"></el-option>
                                                            <el-option label="消费投诉业务-质量投诉" value="2"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                        </el-col>-->
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="投诉人姓名：" class="bt" prop="rqstperson">
                            <el-input
                              v-model="complaintmodule.rqstperson"
                              placeholder="请输入投诉人姓名"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="性别：" class="bt" prop="gender">
                            <el-select
                              v-model="complaintmodule.gender"
                              placeholder="请选择性别"
                              class="input-text"
                            >
                              <el-option label="男" value="男"></el-option>
                              <el-option label="女" value="女"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                            <el-input
                              v-model="complaintmodule.rqstnumber"
                              placeholder="请输入来电号码"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="联系电话：" class="bt" prop="linknumber">
                            <el-input
                              v-model="complaintmodule.linknumber"
                              placeholder="请输入联系电话"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="反映类型：" class="bt" prop="usertype">
                            <el-select
                              v-model="complaintmodule.usertype"
                              placeholder="请选择反映类型"
                              class="input-text"
                            >
                              <el-option label="个人" value="个人"></el-option>
                              <el-option label="企业" value="企业"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="回复方式：" class="bt" prop="answertype">
                            <el-select
                              v-model="complaintmodule.answertype"
                              placeholder="请选择回复方式"
                              class="input-text"
                            >
                              <el-option label="网上" value="网上"></el-option>
                              <el-option label="电话" value="电话"></el-option>
                              <el-option label="信件" value="信件"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>

                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="类型：" class="bt" prop="providetype">
                            <el-select
                              v-model="complaintmodule.providetype"
                              placeholder="请选择类型"
                              class="input-text"
                            >
                              <el-option label="自然人" value="自然人"></el-option>
                              <el-option label="监督人" value="监督人"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="类别：" class="bt" prop="providelb">
                            <el-select
                              v-model="complaintmodule.providelb"
                              placeholder="请选择类别"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                              <el-option label="青男" value="青男"></el-option>
                              <el-option label="妇女" value="妇女"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="工单分类：" class="bt" prop="focus">
                            <el-select
                              v-model="complaintmodule.focus"
                              placeholder="请选择工单分类"
                              class="input-text"
                            >
                              <el-option label="普通" value="普通"></el-option>
                              <el-option label="特殊" value="特殊"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="8">
                          <el-form-item label="区域：" class="bt" prop="region">
                            <el-select
                              v-model="complaintmodule.region"
                              placeholder="请选择区域"
                              class="input-text"
                            >
                              <el-option label="顺德区" value="顺德区"></el-option>
                              <el-option label="市直" value="市直"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="24">
                          <el-form-item label="投诉人地址：" class="bt" prop="subjectzcadd">
                            <el-input
                              v-model="complaintmodule.complainantaddress"
                              placeholder="请输入投诉人地址"
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
                        <el-col :span="12">涉及主体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="complaintmodule"
                      label-width="150px"
                      ref="complaintmoduleSubject"
                      :rules="complaintmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="名称：" class="bt" prop="subjectname">
                            <el-input
                              v-model="complaintmodule.subjectname"
                              placeholder="请输入名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="联系电话：" class="bt" prop="subjectphone">
                            <el-input
                              v-model="complaintmodule.subjectphone"
                              placeholder="请输入联系电话"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="经营地址：" class="bt" prop="subjectaddress">
                            <el-input
                              v-model="complaintmodule.subjectaddress"
                              placeholder="请输入经营地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="实际注册地址：" class="bt" prop="subjectzcadd">
                            <el-input
                              v-model="complaintmodule.subjectzcadd"
                              placeholder="请输入实际注册地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="核查名称：" class="bt" prop="cksubjectname">
                            <el-input
                              v-model="complaintmodule.cksubjectname"
                              placeholder="请输入核查名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="核查经营地址：" class="bt" prop="cksubjectaddress">
                            <el-input
                              v-model="complaintmodule.cksubjectaddress"
                              placeholder="请输入核查经营地址"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="3">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">涉及客体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="complaintmodule"
                      label-width="150px"
                      ref="complaintmoduleObject"
                      :rules="complaintmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品/服务名称：" class="bt" prop="objname">
                            <el-input
                              v-model="complaintmodule.objname"
                              placeholder="请输入商品/服务名称"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="商品品牌：" class="bt" prop="brand">
                            <el-input
                              v-model="complaintmodule.brand"
                              placeholder="请输入商品品牌"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及客体分类：" class="bt" prop="objclassify">
                            <el-select
                              v-model="complaintmodule.objclassify"
                              placeholder="请选择涉及客体分类"
                              class="input-text"
                            >
                              <el-option label="存、停车管理服务" value="存、停车管理服务"></el-option>
                              <el-option label="宽带接入" value="宽带接入"></el-option>
                              <el-option label="公寓" value="公寓"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及金额：" class="bt" prop="objmoney">
                            <el-input
                              v-model.number="complaintmodule.objmoney"
                              placeholder="请输入涉及金额"
                              class="input-text"
                            >
                              <template slot="append">元</template>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="生产企业：" class="bt" prop="producecompany">
                            <el-input
                              v-model="complaintmodule.producecompany"
                              placeholder="请输入生产企业"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否网购：" class="bt" prop="isweb">
                            <el-select
                              v-model="complaintmodule.isweb"
                              placeholder="请选择是否网购"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="4">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">业务具体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="complaintmodule"
                      label-width="150px"
                      ref="complaintmoduleSpecific"
                      :rules="complaintmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="涉及问题分类：" class="bt" prop="problemtype">
                            <el-select
                              v-model="complaintmodule.problemtype"
                              placeholder="请选择涉及问题分类"
                              class="input-text"
                            >
                              <el-option label="其他" value="其他"></el-option>
                              <el-option label="合同" value="合同"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="问题陈述：" class="bt" prop="problemdescribe">
                            <el-input
                              v-model="complaintmodule.problemdescribe"
                              placeholder="请输入问题陈述"
                              type="textarea"
                              :autosize="{ minRows: 3, maxRows: 5}"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="关键字：" class="bt" prop="keyword">
                            <el-input
                              v-model="complaintmodule.keyword"
                              placeholder="请输入关键字"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="事件级别：" class="bt" prop>
                            <el-select
                              v-model="complaintmodule.eventlevel"
                              placeholder="请选择事件级别"
                              class="input-text"
                            >
                              <el-option label="特别紧急事件" value="特别紧急事件"></el-option>
                              <el-option label="紧急事件" value="紧急事件"></el-option>
                              <el-option label="一般事件" value="一般事件"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="政务标签：" class="bt" prop="govlabeloutput">
                            <el-input
                              v-model="complaintmodule.govlabeloutput"
                              placeholder="请输入政务标签"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="5">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">处理相关信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form
                      :model="complaintmodule"
                      label-width="150px"
                      ref="complaintmoduleHandle"
                      :rules="complaintmoduleRules"
                    >
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                          <el-form-item label="是否出现场：" class="bt">
                            <el-select
                              v-model="complaintmodule.isoutmall"
                              placeholder="请选择是否出现场"
                              class="input-text"
                            >
                              <el-option label="是" value="是"></el-option>
                              <el-option label="否" value="否"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col class="tr"></el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                          <el-form-item label="附件清单：" class="bt" prop>
                            <el-button
                              size="small"
                              @click="toUploadOpen('word')"
                              class="el-button--primary"
                              style="float: left"
                            >
                              <i class="fa fa-upload"></i>
                              选择文件
                            </el-button>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                </el-collapse>
              </el-tab-pane>
              <el-tab-pane label="处理信息" name="handle">
                <el-collapse
                  v-model="processInofActiveNames"
                  class="accordion"
                  v-if="'handle'===economicsActiveName"
                >
                  <el-collapse-item name="1">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">处理信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="processInfoForm" label-width="220px" ref="processInfoForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="登记单编号：" class="bt" prop>
                            <el-input
                              v-model="processInfoForm.number"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="案由：" class="bt" prop>
                            <el-input
                              v-model="processInfoForm.cause"
                              placeholder="请输入"
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
                        <el-col :span="12">涉及主体信息</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="mainForm" label-width="220px" ref="mainForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="是否具有营业执照：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="统一社会信用代码/注册号：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registration"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="所在行业类别：" class="bt" prop>
                            <el-select
                              v-model="mainForm.industryCategory"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="IT" value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="市场名称：" class="bt" prop>
                            <el-input
                              v-model="mainForm.marketName"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="联系方式：" class="bt" prop>
                            <el-input placeholder="请输入" v-model="mainForm.phone">
                              <el-select
                                v-model="mainForm.contactMode"
                                slot="prepend"
                                placeholder="请选择"
                                style="width: 110px;"
                              >
                                <el-option label="移动电话" value="1"></el-option>
                                <el-option label="固定电话" value="2"></el-option>
                              </el-select>
                            </el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="地址：" class="bt" prop>
                            <el-input placeholder="请输入" v-model="mainForm.addr">
                              <el-select
                                v-model="mainForm.addrMode"
                                slot="prepend"
                                placeholder="请选择"
                                style="width: 110px;"
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
                          <el-form-item label="是否转移其他部门：" class="bt" prop>
                            <el-select
                              v-model="mainForm.department"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="消保" value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="应急处置进展状态：" class="bt" prop>
                            <el-select
                              v-model="mainForm.progress"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="反馈登记人：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrar"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="协助单位类型：" class="bt" prop>
                            <el-select
                              v-model="mainForm.unitType"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label value="complain"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="办案人员：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrar"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="办案人员号码：" class="bt" prop>
                            <el-input
                              v-model="mainForm.registrarPhone"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="群体投诉案件：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="涉外投诉案件：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="对投诉中发现违法行为实施行政处理：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="是否转立案：" class="bt" prop>
                            <el-select
                              v-model="mainForm.license"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="是" value="1"></el-option>
                              <el-option label="否" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>
                  </el-collapse-item>
                  <el-collapse-item name="4">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">初步意见</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="opinionForm" label-width="150px" ref="opinionForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="处理意见：" class="bt" prop>
                            <el-input
                              v-model="opinionForm.text"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                          <el-form-item label="附件清单：" class="bt" prop>
                            <el-button
                              size="small"
                              @click="toUploadOpen('word')"
                              class="el-button--primary"
                              style="float: left"
                            >
                              <i class="fa fa-upload"></i>
                              选择文件
                            </el-button>
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
                    <el-form :model="assignForm" label-width="150px" ref="assignForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="成员单位：" class="bt" prop>
                            <el-select
                              v-model="assignForm.appear"
                              placeholder="请选择"
                              class="input-text"
                            >
                              <el-option label="消保" value="1"></el-option>
                              <el-option label="12315" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="分派意见：" class="bt" prop>
                            <el-input
                              v-model="assignForm.describe"
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
              </el-tab-pane>
            </el-tabs>
          </el-collapse-item>

          <el-collapse-item name="2" v-if="commom">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion" :model="datagriddata">
                <el-col :span="12">
                  <em>2.</em>
                  {{commommodule.formtype}}
                </el-col>
                <el-col
                  :span="10"
                  style="color: #48576a;text-align: right"
                >表单号：{{datagriddata.serialnum}}</el-col>
              </el-row>
            </div>
            <el-tabs type="card" v-model="economicsActiveName">
              <el-tab-pane label="登记信息" name="registration">
                <el-form
                  :model="commommodule"
                  label-width="150px"
                  ref="commommodule"
                  :rules="commommoduleRules"
                >
                  <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                      <el-form-item label="表单类型：" class="bt" prop="formtype">
                        <div style="line-height: 35px;float: left">{{commommodule.formtype}}</div>
                      </el-form-item>
                    </el-col>
                    <!-- <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                            <el-form-item label="表单模板：" class="bt" prop="formmodule">
                                                <el-input v-model="commommodule.formmodule" placeholder="请输入表单模板"
                                                          class="input-text"></el-input>
                                            </el-form-item>
                    </el-col>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                      <el-form-item label="标题：" class="bt" prop="rqsttitle">
                        <el-input
                          v-model="commommodule.rqsttitle"
                          placeholder="请输入标题"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="姓名：" class="bt" prop="name">
                        <el-input
                          v-model="commommodule.name"
                          placeholder="请输入姓名"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="性别：" class="bt" prop="gender">
                        <el-select
                          v-model="commommodule.gender"
                          placeholder="请选择性别"
                          class="input-text"
                        >
                          <el-option label="男" value="男"></el-option>
                          <el-option label="女" value="女"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="16">
                      <el-form-item label="区域：" class="bt" prop="region">
                        <el-select
                          v-model="commommodule.region"
                          placeholder="请选择区域"
                          class="input-text"
                        >
                          <el-option label="顺德区" value="顺德区"></el-option>
                          <el-option label="市直" value="市直"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="16">
                      <el-form-item label="是否有效：" class="bt" prop="isvalid">
                        <el-select
                          v-model="commommodule.isvalid"
                          placeholder="请选择是否有效"
                          class="input-text"
                        >
                          <el-option label="是" value="是"></el-option>
                          <el-option label="否" value="否"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="来电号码：" class="bt" prop="rqstnumber">
                        <el-input
                          v-model="commommodule.rqstnumber"
                          placeholder="请输入来电号码"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="联系电话：" class="bt" prop="linknumber">
                        <el-input
                          v-model="commommodule.linknumber"
                          placeholder="请输入联系电话"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="反映类型：" class="bt" prop="usertype">
                        <el-select
                          v-model="commommodule.usertype"
                          placeholder="请选择反映类型"
                          class="input-text"
                        >
                          <el-option label="个人" value="个人"></el-option>
                          <el-option label="企业" value="企业"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="回复方式：" class="bt" prop="answertype">
                        <el-select
                          v-model="commommodule.answertype"
                          placeholder="请选择回复方式"
                          class="input-text"
                        >
                          <el-option label="电话" value="电话"></el-option>
                          <el-option label="网上" value="网上"></el-option>
                          <el-option label="信件" value="信件"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="企业类型：" class="bt" prop="entitytype">
                        <el-select
                          v-model="commommodule.entitytype"
                          placeholder="请选择企业类型"
                          class="input-text"
                        >
                          <el-option label="内资企业" value="内资企业"></el-option>
                          <el-option label="外资企业" value="外资企业"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="企业全称：" class="bt" prop="entname">
                        <el-input
                          v-model="commommodule.entname"
                          placeholder="请输入企业全称"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="企业登记机关：" class="bt" prop="aicid">
                        <el-input
                          v-model="commommodule.aicid"
                          placeholder="请输入企业登记机关"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="企业经营地址：" class="bt" prop="address">
                        <el-input
                          v-model="commommodule.address"
                          placeholder="请输入企业经营地址"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                      <el-form-item label="工单分类：" class="bt" prop="focus">
                        <el-select
                          v-model="commommodule.focus"
                          placeholder="请选择工单分类"
                          class="input-text"
                        >
                          <el-option label="普通" value="普通"></el-option>
                          <el-option label="特殊" value="特殊"></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                      <el-form-item label="问题描述：" class="bt" prop="rqstcontent">
                        <el-input
                          v-model="commommodule.rqstcontent"
                          placeholder="请输入问题描述"
                          type="textarea"
                          :autosize="{ minRows: 3, maxRows: 5}"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="16">
                      <el-form-item label="政务标签：" class="bt" prop="govlabel">
                        <el-input
                          v-model="commommodule.govlabel"
                          placeholder="请输入政务标签"
                          class="input-text"
                        ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col class="tr"></el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                      <el-form-item label="相关附件：" class="bt" prop>
                        <el-button
                          size="small"
                          @click="toUploadOpen('word')"
                          class="el-button--primary"
                          style="float: left"
                        >
                          <i class="fa fa-upload"></i>
                          选择文件
                        </el-button>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="处理信息" name="handle">
                <el-collapse v-model="processInofActiveNames" class="accordion">
                  <el-collapse-item name="4">
                    <div slot="title" @click.stop>
                      <el-row class="el-accordion">
                        <el-col :span="12">初步意见</el-col>
                        <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
                      </el-row>
                    </div>
                    <el-form :model="opinionForm" label-width="150px" ref="opinionForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="处理意见：" class="bt" prop>
                            <el-input
                              v-model="opinionForm.text"
                              placeholder="请输入"
                              class="input-text"
                            ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                          <el-form-item label="附件清单：" class="bt" prop>
                            <el-button
                              size="small"
                              @click="toUploadOpen('word')"
                              class="el-button--primary"
                              style="float: left"
                            >
                              <i class="fa fa-upload"></i>
                              选择文件
                            </el-button>
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
                    <el-form :model="assignForm" label-width="150px" ref="assignForm">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                          <el-form-item label="成员单位：" class="bt" prop>
                            <el-select
                              v-model="assignForm.appear"
                              placeholder="请选择成员单位..."
                              class="input-text"
                            >
                              <el-option label="消保" value="1"></el-option>
                              <el-option label="12315" value="2"></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                          <el-form-item label="分派意见：" class="bt" prop>
                            <el-input
                              v-model="assignForm.describe"
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
              </el-tab-pane>
            </el-tabs>
          </el-collapse-item>

          <el-collapse-item name="3">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion title_accordion">
                <el-col :span="12">
                  <em>3.</em> 处办要求
                </el-col>
                <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
              </el-row>
            </div>
            <el-form :model="inrequest" label-width="220px" ref="inrequest">
              <el-row>
                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                  <el-form-item label="初步意见应反馈时限：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.opiniontimeshould"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                  <el-form-item label="初步意见反馈时间：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.opiniontime"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="12" v-if="formType != 'commommodule'">
                  <el-form-item label="应受理时限：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.accepttimeshould"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="12" v-if="formType != 'commommodule'">
                  <el-form-item label="应受理到期时间：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.accepttime"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                  <el-form-item label="应立案时限：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.caseputtimeshould"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="12">
                  <el-form-item label="应立案到期时间：" class="bt" prop>
                    <el-date-picker
                      v-model="inrequest.caseputtime"
                      type="datetime"
                      class="input-text"
                      placeholder="选择时间"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-collapse-item>
          <el-collapse-item name="4">
            <div slot="title" @click.stop>
              <el-row class="el-accordion title_accordion">
                <el-col :span="12">
                  <em>4.</em> 办理跟踪
                </el-col>
                <el-col :span="10" style="color: #48576a;text-align: right"></el-col>
              </el-row>
            </div>
            <tracking :data="opinionList" />
          </el-collapse-item>
        </el-collapse>
      </el-tab-pane>
      <el-col style="height: 15px;"></el-col>
      <el-form
        :model="nextStepForm"
        ref="nextStepForm"
        style="width: 96%;margin: 0 auto"
        v-show="flows"
      >
        <el-col style="text-align: center;">
          <el-button type="primary" @click="submitFrom" :loading="submitLoading">确认</el-button>
        </el-col>
      </el-form>
    </el-tabs>
    <el-dialog :title="fileUploadText" :visible.sync="fileUploadVisible" size="tiny">
      <el-upload
        :auto-upload="false"
        :file-list="fileList"
        ref="upload"
        action="https://jsonplaceholder.typicode.com/posts/"
      >
        <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button size="small" type="success" @click="submitUpload">确认上传</el-button>
      </el-upload>
    </el-dialog>
  </section>
</template>

<script>
import { selectDatagriddataId, confirmBaseInfo } from "../../api/api";
import moment from "moment";
import Tracking from "../../components/Tracking";

export default {
  name: "workOrderConfirm",
  components: {
    Tracking
  },
  data() {
    return {
      submitLoading: false,
      moment,
      loading: true,
      flows: true,
      commom: false,
      complaint: false,
      economy: false,
      aictabs: "0",
      cities: [
        {
          value: "同意受理",
          label: "同意受理"
        },
        {
          value: "退回",
          label: "退回"
        },
        {
          value: "转移",
          label: "转移"
        },
        {
          value: "不受理",
          label: "不受理"
        }
      ],
      dept: [
        {
          value: "consent",
          label: "企管科"
        },
        {
          value: "reject",
          label: "消保科"
        },
        {
          value: "transfer",
          label: "广告科"
        },
        {
          value: "endFlow",
          label: "结束流程"
        }
      ],
      reason1: [
        {
          value: "consent",
          label: "支付单位不正确"
        },
        {
          value: "reject",
          label: "可查阅知识库解决"
        }
      ],
      activeName: "word",
      wordList: [
        {
          filename: "文档1.docx",
          author: "李晓明",
          fileDate: "2019-07-18 12:00",
          describe: "文档1。"
        },
        {
          filename: "文档2.doc",
          author: "李先明",
          fileDate: "2019-07-17 19:18",
          describe: "文档2。"
        },
        {
          filename: "文档3.docx",
          author: "王小虎",
          fileDate: "2019-07-16 16:11",
          describe: "文档3。"
        },
        {
          filename: "文档4.docx",
          author: "王小明",
          fileDate: "2019-07-15 12:33",
          describe: "文档4。"
        }
      ],
      imageList: [
        {
          filename: "图片1.png",
          author: "李晓明",
          fileDate: "2019-07-18 12:00",
          describe: "图片1。"
        },
        {
          filename: "图片2.jpg",
          author: "李先明",
          fileDate: "2019-07-17 19:18",
          describe: "图片2。"
        },
        {
          filename: "图片3.png",
          author: "王小虎",
          fileDate: "2019-07-16 16:11",
          describe: "图片3。"
        },
        {
          filename: "图片4.svg",
          author: "王小明",
          fileDate: "2019-07-15 12:33",
          describe: "图片4。"
        }
      ],
      videoList: [
        {
          filename: "视频1.mp4",
          author: "李晓明",
          fileDate: "2019-07-18 12:00",
          describe: "视频1。"
        },
        {
          filename: "视频2.mp4",
          author: "李先明",
          fileDate: "2019-07-17 19:18",
          describe: "视频2。"
        },
        {
          filename: "视频3.mp4",
          author: "王小虎",
          fileDate: "2019-07-16 16:11",
          describe: "视频3。"
        },
        {
          filename: "视频4.mp4",
          author: "王小明",
          fileDate: "2019-07-15 12:33",
          describe: "视频4。"
        }
      ],
      otherList: [
        {
          filename: "文件1.png",
          author: "李晓明",
          fileDate: "2019-07-18 12:00",
          describe: "文件1。"
        },
        {
          filename: "音频2.jpg",
          author: "李先明",
          fileDate: "2019-07-17 19:18",
          describe: "文件2。"
        },
        {
          filename: "文件3.png",
          author: "王小虎",
          fileDate: "2019-07-16 16:11",
          describe: "文件3。"
        },
        {
          filename: "其他4.svg",
          author: "王小明",
          fileDate: "2019-07-15 12:33",
          describe: "文件4。"
        }
      ],
      fileWordPagination: {
        page: 1,
        size: 10,
        total: 4
      },
      fileUploadVisible: false,
      fileList: [],
      fileUploadText: "",
      opinionList: [],
      offerForm: {
        name: "",
        sex: "",
        phone: "",
        email: "",
        papersType: "",
        papersID: "",
        addr: ""
      },
      comeconomicmodule: {
        id: "",
        caseguid: "",
        formtype: "",
        rqsttitle: "",
        accordtype: "",
        rqstperson: "",
        gender: "",
        rqstnumber: "",
        linknumber: "",
        rqstaddress: "",
        usertype: "",
        answertype: "",
        focus: "",
        providelb: "",
        age: "",
        nation: "",
        contactaddress: "",
        status: "",
        complaintpaper: "",
        disabilityidentification: "",
        certificate: "",
        idnumber: "",
        caree: "",
        company: "",
        subjectname: "",
        linktype: "",
        subjectphone: "",
        aicstationid: "",
        postcode: "",
        jyareacode: "",
        subjectaddress: "",
        zcareacode: "",
        subjectzcadd: "",
        cksubjectname: "",
        ckjyareacode: "",
        cksubjectaddress: "",
        objname: "",
        brand: "",
        objclassify: "",
        objmoney: "",
        producecompany: "",
        isweb: "",
        importmark: "",
        typespecification: "",
        quantity: "",
        measureunit: "",
        price: "",
        purchasingdate: "",
        problemtype: "",
        illegalrules: "",
        problemdescribe: "",
        keyword: "",
        eventlevel: "",
        govlabel: "",
        differserv: "",
        briefing: "",
        anestimate: "",
        economicloss: "",
        tortclassify: "",
        personalinjury: "",
        injurydegree: "",
        injuries: "",
        consumptionsafety: "",
        consumptionsafetytype: "",
        even: "",
        reduction: "",
        report: "",
        isoutmall: "",
        isfeedback: "",
        isaward: "",
        attachfilename: "",
        updatedate: ""
      },
      comeconomicmoduleRules: {
        rqsttitle: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        rqstperson: [
          { required: true, message: "请输入投诉人姓名", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        age: [{ type: "number", message: "年龄只能是数字", trigger: "blur" }],
        nation: [
          { required: true, message: "请输入民族", trigger: "blur" },
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        rqstnumber: [
          { required: true, message: "请输入来电号码", trigger: "blur" },
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        linknumber: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        status: [{ max: 50, message: "不能超出50个字符", trigger: "blur" }],
        certificate: [
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        idnumber: [{ max: 50, message: "不能超出50个字符", trigger: "blur" }],
        career: [{ max: 50, message: "不能超出50个字符", trigger: "blur" }],
        company: [{ max: 100, message: "不能超出100个字符", trigger: "blur" }],
        usertype: [
          { required: true, message: "请选择反映类型", trigger: "change" }
        ],
        answertype: [
          { required: true, message: "请选择回复方式", trigger: "change" }
        ],
        focus: [
          { required: true, message: "请选择工单分类", trigger: "change" }
        ],
        providelb: [
          { required: true, message: "请选择类别", trigger: "change" }
        ],
        omplaintpaper: [
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        disabilityidentification: [
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        rqstaddress: [
          { required: true, message: "请输入举报人地址", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        contactaddress: [
          { required: true, message: "请输入联系人地址", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        cksubjectname: [
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        subjectaddress: [
          { required: true, message: "请输入经营地址", trigger: "blur" },
          { max: 500, message: "不能超出500个字符", trigger: "blur" }
        ],
        subjectzcadd: [
          { max: 500, message: "不能超出500个字符", trigger: "blur" }
        ],
        cksubjectaddress: [
          { max: 500, message: "不能超出500个字符", trigger: "blur" }
        ],
        subjectname: [
          { required: true, message: "请输入涉及主体名称", trigger: "blur" },
          { max: 100, message: "不能超出100个字符", trigger: "blur" }
        ],
        objname: [
          { required: true, message: "请输入商品/服务名称", trigger: "blur" },
          { max: 100, message: "不能超出100个字符", trigger: "blur" }
        ],
        objclassify: [
          { required: true, message: "请选择涉及客体分类", trigger: "change" }
        ],
        brand: [
          { required: true, message: "请输入商品品牌", trigger: "blur" },
          { max: 100, message: "不能超出100个字符", trigger: "blur" }
        ],
        /* objmoney: [
          { required: true, message: "请输入涉及金额", trigger: "blur" },
          { type: "number", message: "金额只能是数字", trigger: "blur" }
        ],*/
        producecompany: [
          { max: 100, message: "不能超出100个字符", trigger: "blur" }
        ],
        isweb: [
          { required: true, message: "请输入是否网购", trigger: "change" }
        ],
        typespecification: [
          { max: 50, message: "不能超出50个字符", trigger: "blur" }
        ],
        quantity: [{ max: 50, message: "不能超出50个字符", trigger: "blur" }],
        measureunit: [
          { max: 10, message: "不能超出50个字符", trigger: "blur" }
        ],
        price: [
          {
            type: "number",
            message: "商品/服务价格只能是数字",
            trigger: "blur"
          }
        ],
        problemtype: [
          { required: true, message: "请选择涉及问题分类", trigger: "change" }
        ],
        illegalrules: [
          { required: true, message: "请选择涉嫌违法行为", trigger: "change" }
        ],
        eventlevel: [
          { required: true, message: "请选择事件级别", trigger: "change" }
        ],
        keyword: [
          { required: true, message: "请输入关键字", trigger: "blur" },
          { max: 100, message: "不能超出50个字符", trigger: "blur" }
        ],
        govlabel: [
          { required: true, message: "请输入政务标签", trigger: "blur" },
          { max: 100, message: "不能超出100个字符", trigger: "blur" }
        ],
        differserv: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        tortclassify: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        anestimate: [
          { type: "number", message: "案值只能是数字", trigger: "blur" }
        ],
        economicloss: [
          { type: "number", message: "经济损失值只能是数字", trigger: "blur" }
        ],
        personalinjury: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        injurydegree: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        injuries: [{ max: 50, message: "不能超出100个字符", trigger: "blur" }],
        consumptionsafety: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        consumptionsafetytype: [
          { max: 50, message: "不能超出100个字符", trigger: "blur" }
        ],
        even: [{ max: 50, message: "不能超出100个字符", trigger: "blur" }],
        reduction: [{ max: 50, message: "不能超出100个字符", trigger: "blur" }],
        briefing: [{ max: 50, message: "不能超出100个字符", trigger: "blur" }],
        problemdescribe: [
          { max: 3000, message: "不能超出3000个字符", trigger: "blur" }
        ],
        isaward: [
          { required: true, message: "请选择是否需要奖励", trigger: "change" }
        ],
        isfeedback: [
          { required: true, message: "请选择是否反馈", trigger: "change" }
        ],
        isoutmall: [
          { required: true, message: "请选择涉嫌违法行为", trigger: "change" }
        ]
      },
      complaintmodule: {
        id: "",
        caseguid: "",
        formtype: "",
        rqsttitle: "",
        rqstperson: "",
        gender: "",
        rqstnumber: "",
        linknumber: "",
        usertype: "",
        answertype: "",
        providetype: "",
        providelb: "",
        focus: "",
        region: "",
        subjectname: "",
        linktype: "",
        subjectphone: "",
        jyareacode: "",
        subjectaddress: "",
        zcareacode: "",
        subjectzcadd: "",
        cksubjectname: "",
        ckjyareacode: "",
        cksubjectaddress: "",
        objname: "",
        brand: "",
        objclassify: "",
        objmoney: "",
        producecompany: "",
        isoutmall: "",
        problemtype: "",
        problemdescribe: "",
        keyword: "",
        govlabeloutput: "",
        attachfilename: "",
        updatedate: ""
      },
      complaintmoduleRules: {
        rqsttitle: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        rqstperson: [
          { required: true, message: "请输入投诉人姓名", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        rqstnumber: [{ max: 30, message: "不能超出30个字符", trigger: "blur" }],
        linknumber: [{ max: 30, message: "不能超出30个字符", trigger: "blur" }],
        usertype: [
          { required: true, message: "请选择反映类型", trigger: "blur" }
        ],
        answertype: [
          { required: true, message: "请选择回复方式", trigger: "change" }
        ],
        providetype: [
          { required: true, message: "请选择类型", trigger: "change" }
        ],
        providelb: [
          { required: true, message: "请选择类别", trigger: "change" }
        ],
        focus: [
          { required: true, message: "请选择工单分类", trigger: "change" }
        ],
        region: [{ required: true, message: "请选择区域", trigger: "change" }],
        subjectname: [
          { required: true, message: "请输入名称", trigger: "blur" },
          { max: 100, message: "不能超100个字符", trigger: "blur" }
        ],
        subjectphone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { max: 30, message: "不能超30个字符", trigger: "blur" }
        ],
        subjectaddress: [
          { max: 500, message: "不能超500个字符", trigger: "blur" }
        ],
        subjectzcadd: [
          { max: 500, message: "不能超500个字符", trigger: "blur" }
        ],
        cksubjectname: [
          { max: 200, message: "不能超200个字符", trigger: "blur" }
        ],
        cksubjectaddress: [
          { max: 500, message: "不能超500个字符", trigger: "blur" }
        ],
        objname: [
          { required: true, message: "请输入商品/服务名称", trigger: "blur" },
          { max: 100, message: "不能超100个字符", trigger: "blur" }
        ],
        brand: [{ max: 100, message: "不能超100个字符", trigger: "blur" }],
        objclassify: [
          { max: 100, message: "不能超100个字符", trigger: "blur" }
        ],
        // objmoney: [
        //   { required: true, message: "请输入涉及金额", trigger: "blur" },
        //   { type: "number", message: "金额只能是数字", trigger: "blur" }
        // ],
        problemdescribe: [
          { required: true, message: "请选择涉及问题分类", trigger: "blur" },
          { max: 3000, message: "请输入问题陈述", trigger: "blur" }
        ],
        keyword: [
          { required: true, message: "请输入关键字", trigger: "blur" },
          { max: 100, message: "不能超100个字符", trigger: "blur" }
        ],
        govlabeloutput: [
          { max: 100, message: "不能超100个字符", trigger: "blur" }
        ],
        problemtype: [
          { required: true, message: "请选择涉及问题分类", trigger: "change" }
        ]
      },
      commommodule: {
        id: "",
        caseguid: "",
        formtype: "",
        formmodule: "",
        rqsttitle: "",
        name: "",
        gender: "",
        region: "",
        isvalid: "",
        rqstnumber: "",
        linknumber: "",
        usertype: "",
        answertype: "",
        entitytype: "",
        entname: "",
        aicid: "",
        address: "",
        focus: "",
        focusxjfl: "",
        rqstcontent: "",
        govlabel: "",
        updatedate: ""
      },
      commommoduleRules: {
        rqsttitle: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "change" }],
        region: [{ required: true, message: "请选择区域", trigger: "change" }],
        isvalid: [
          { required: true, message: "请选择是否有效", trigger: "change" }
        ],
        rqstnumber: [
          { required: true, message: "请输入来电号码", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        linknumber: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        usertype: [
          { required: true, message: "请选择反映类型", trigger: "change" }
        ],
        answertype: [
          { required: true, message: "请选择回复方式", trigger: "change" }
        ],
        entname: [{ max: 200, message: "不能超出200个字符", trigger: "blur" }],
        aicid: [{ max: 80, message: "不能超出80个字符", trigger: "blur" }],
        address: [{ max: 500, message: "不能超出500个字符", trigger: "blur" }],
        focus: [
          { required: true, message: "请选择工单分类", trigger: "change" }
        ],
        rqstcontent: [
          { required: true, message: "请输入问题描述", trigger: "blur" },
          { max: 3000, message: "不能超出3000个字符", trigger: "blur" }
        ],
        govlabel: [{ max: 100, message: "不能超出100个字符", trigger: "blur" }]
      },
      nextStepForm: {},
      datagriddata: {
        id: "",
        caseguid: "",
        focus: "",
        teti: "",
        serialnum: "",
        contractnum: "",
        tserialnum: "",
        rqsttitle: "",
        accordtype: "",
        labelname: "",
        createdate: "",
        resulttimemin: "",
        status: "",
        updatedate: ""
      },
      seats: {
        seatsname: "",
        seatsregistrationtime: "",
        seatssex: "",
        seatslanguage: ""
      },
      seatsRules: {
        seatsname: [
          { required: true, message: "请输入接听坐席", trigger: "blur" },
          { max: 20, message: "不能超出20个字符", trigger: "blur" }
        ],
        seatsregistrationtime: [
          {
            type: "date",
            required: true,
            message: "请选择登记时间",
            trigger: "blur"
          }
        ],
        seatssex: [{ required: true, message: "请选择性别", trigger: "blur" }]
      },
      inrequest: {
        id: "",
        caseguid: "",
        opiniontimeshould: "",
        opiniontimesurplus: "",
        opiniontime: "",
        accepttimeshould: "",
        accepttimesurplus: "",
        accepttime: "",
        caseputtimeshould: "",
        caseputtimesurplus: "",
        caseputtime: "",
        resulttimeshould: "",
        resulttimesurplus: "",
        resulttime: "",
        opiniontimeminstr: "",
        accepttimeminstr: "",
        caseputtimeminstr: "",
        resulttimeminstr: "",
        updatedate: ""
      },
      processInfoForm: {
        cause: "",
        number: ""
      },
      mainForm: {},
      awardForm: {},
      opinionForm: {
        text: ""
      },
      assignForm: {
        describe:"拟转XX跟进处理。",
      },
      comeconomicm: {},
      activeNames: ["1", "2", "3", "4"],
      registrationActiveNames: ["1", "2", "3", "4", "5"],
      processInofActiveNames: ["1", "2", "3", "4", "5"],
      economicsActiveName: "registration"
    };
  },
  methods: {
    toUploadOpen(type) {
      if (type === "word") {
        this.fileUploadText = "文档上传";
      } else if (type === "image") {
        this.fileUploadText = "图片上传";
      } else if (type === "video") {
        this.fileUploadText = "视频上传";
      } else if (type === "other") {
        this.fileUploadText = "其他文件上传";
      } else {
        this.fileUploadText = "附件上传";
      }
      this.fileUploadVisible = true;
    },
    submitUpload() {
      this.$refs.upload.submit();
    },

    submitFrom() {
      //效验证表单
      // let validation = false;
      // this.$refs.seats.validate(valid => (validation = valid));
      //消费表单验证 todo
      // if (this.complaint) {
      //   this.$refs.complaintmoduleProvide.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.complaintmoduleSubject.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.complaintmoduleObject.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.complaintmoduleSpecific.validate(
      //     valid => (validation = valid && validation)
      //   );
      // } else if (this.economy) {
      //   //经济消费
      //   this.$refs.comeconomicmoduleProvide.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.comeconomicmoduleSubject.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.comeconomicmoduleObject.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.comeconomicmoduleSpecific.validate(
      //     valid => (validation = valid && validation)
      //   );
      //   this.$refs.comeconomicmoduleHandle.validate(
      //     valid => (validation = valid && validation)
      //   );
      // } else if (this.commom) {
      //   //通用表单
      //   this.$refs.commommodule.validate(
      //     valid => (validation = valid && validation)
      //   );
      // }
      // if (!validation) {
      //   this.$message({
      //     message: "请检查表单是否有为空或超出！",
      //     type: "info"
      //   });
      //   return;
      // }
      this.$confirm("请确认无误提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.submitLoading = true;
        var user = localStorage.getItem("user");
        let inrequest = this.inrequest;
        for (let key in inrequest) {
          if (inrequest[key] instanceof Date) {
            inrequest[key] = inrequest[key].getTime();
          }
        }
        let seats = this.seats;
        for (let key in seats) {
          if (seats[key] instanceof Date) {
            seats[key] = seats[key].getTime();
          }
        }

        let para = {
          user: JSON.parse(user),
          comeconomicmodule: this.comeconomicmodule,
          commommodule: this.commommodule,
          complaintmodule: this.complaintmodule,
          seats,
          inrequest,
          node: "bmfwToDetermine",
          nextNodeId: this.commom ? "handleFeedback" : "begin",
          datagriddata: this.datagriddata
        };
        confirmBaseInfo(para).then(data => {
          this.submitLoading = false;
          if (data.code === 0) {
            this.$message({
                  message: '提交成功！',
                  type: 'success',
                  duration:'1000'
            });
           let params = {
              id: this.$route.query.id,
              caseguid: this.$route.query.caseguid,
              ismain: "1"
            };
            if (this.commom) {
              //通用表单和咨询
              this.$router.push({
                path: "/wddb/12345db/clfpysth",
                query: params
              });
            } else {
              this.$router.push({
                path: "/wddb/12345db/gdsh",
                query: params
              });
            }
          } else {
            this.$alert("提交失败！", "提示", {
              type: "error",
              confirmButtonText: "确定"
            });
          }
        });
      });
    },
    flow(visible) {
      if (visible.index == "0") {
        this.flows = true;
      } else {
        this.flows = false;
      }
      this.accept = false;
      this.notAccepts = false;
      this.back = false;
      this.assign = false;
      if (visible.index == "2") {
        this.aictabs = "0";
        this.flows = true;
      }
    }
  },
  created() {
    let para = {
      id: this.$route.query.id,
      caseguid: this.$route.query.caseguid
    };
    selectDatagriddataId(para).then(({ status, data: { data } }) => {
      //判断请求状态
      if (status === 200) {
        this.datagriddata = data.datagriddata;
        this.seats = data.seats;
        this.comeconomicmodule = data.comeconomicmodule;
        this.commommodule = data.commommodule;
        this.complaintmodule = data.complaintmodule;
        this.inrequest = data.inrequest;
        for (let item of data.askdepthandleList) {
          this.opinionList.push({
            id: item.id,
            handler: item.operateusername,
            nodename: item.opeartionstatus,
            handletime: item.handletime,
            handledept: item.operatorouname,
            handleopinion: item.handleopinion,
            activitytype: item.activitytype
          });
        }
        if (this.comeconomicmodule != null) {
          this.commom = false;
          this.complaint = false;
          this.economy = true;
        } else if (this.commommodule != null) {
          this.commom = true;
          this.complaint = false;
          this.economy = false;
        } else if (this.complaintmodule != null) {
          this.commom = false;
          this.complaint = true;
          this.economy = false;
        }
      } else {
        this.$alert("查询失败，请返回重试！", "提示", {
          type: "error",
          confirmButtonText: "确定"
        });
      }
      this.loading = false;
    });
  }
};
</script>

<style>
.accordion .el-collapse-item__header .el-collapse-item__header__arrow {
  position: absolute;
  top: 15px;
  right: 10px;
  z-index: 10;
  transform: rotate(90deg);
}

.el-collapse-item.is-active
  > .el-collapse-item__header
  .el-collapse-item__header__arrow {
  transform: rotate(-90deg);
}

.accordion .el-collapse-item__header {
  position: relative;
}
</style>
<style lang="scss" scoped>
.workOrderConfirm {
  margin-top: 10px;
  overflow-x: hidden;

  .el-table__body-wrapper {
    overflow: hidden;
  }

  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .el-input input {
    height: 30px;
  }

  .el-form-item {
    margin-bottom: 20px;
  }

  .bt {
    font-weight: bold;
    text-align: right;
  }

  .clearfix {
    font-weight: bold;
  }

  .el-accordion {
    font-size: 13px;
    font-weight: bold;
    color: #1e1f20;
    cursor: auto;
  }

  .title_accordion {
    color: #40a9ff;
  }

  .element.style {
    margin-left: 150px;
    float: left;
  }
}

.input-text {
  width: 100%;
}
</style>
