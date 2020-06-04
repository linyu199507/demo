<template>
    <section class="endFlow">
        <el-row style="margin-top: 30px;margin-bottom: 15px">
            <el-col>
                <el-steps :active="5" align-center center space="22%">
                    <el-step title="受理登记"></el-step>
                    <el-step title="受理"></el-step>
                    <el-step title="受理审查"></el-step>
                    <el-step title="受理审批"></el-step>
                    <el-step title="受理结果"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-tabs type="border-card" style="width: 96%;margin: 0 auto;" @tab-click="flow">
            <el-tab-pane label="信息">
                <el-collapse v-model="contentName0" accordion>
                    <el-collapse-item name="1">
                        <template slot="title">
                            <span class="el-accordion">系统来源信息</span>
                        </template>
                        <el-form :model="sourceForm" label-width="150px" ref="sourceForm">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="登记编号：" class="bt">
                                        <el-input v-model="sourceForm.number" placeholder="请输入"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="信息来源：" class="bt">
                                        <el-input v-model="sourceForm.content" placeholder="请输入"
                                                  class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="接收方式：" class="bt">
                                        <el-select v-model="sourceForm.way" placeholder="请选择分类" class="input-text">
                                            <el-option label="电话" value="complain"></el-option>
                                            <el-option label="信件" value="consult"></el-option>
                                            <el-option label="网络" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="接收类型：" class="bt">
                                        <el-select v-model="sourceForm.type" placeholder="请选择分类" class="input-text">
                                            <el-option label="人工录入" value="complain"></el-option>
                                            <el-option label="系统获取" value="consult"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="接收时间：" class="bt">
                                        <el-date-picker
                                                v-model="sourceForm.receiveDate"
                                                type="date"
                                                class="input-text"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <el-collapse v-model="contentName1" accordion>
                    <el-collapse-item name="1">
                        <template slot="title">
                            <span class="el-accordion">基本信息</span>
                        </template>
                        <el-form :model="basicForm" label-width="150px" ref="basicForm">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="问题分类:" class="bt">
                                        <el-select v-model="basicForm.type" placeholder="请选择分类" class="input-text">
                                            <el-option label="投诉类" value="complain"></el-option>
                                            <el-option label="资询类" value="consult"></el-option>
                                            <el-option label="执法类" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="登记人:" class="bt">
                                        <el-input v-model="basicForm.person" placeholder="请输入" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="登记日期:" class="bt">
                                        <el-date-picker
                                                v-model="basicForm.registrarDate"
                                                type="date"
                                                class="input-text"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="表单类型:" class="bt">
                                        <el-select v-model="basicForm.formType" placeholder="请输入" class="input-text">
                                            <el-option label="消费投诉" value="complain"></el-option>
                                            <el-option label="售后投诉" value="consult"></el-option>
                                            <el-option label="其他投诉" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="工单分类:" class="bt">
                                        <el-select v-model="basicForm.word" placeholder="请输入" class="input-text">
                                            <el-option label="普通" value="complain"></el-option>
                                            <el-option label="特殊" value="consult"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="发生地:" class="bt">
                                        <el-input v-model="basicForm.addr" placeholder="请输入"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="反映类型:" class="bt">
                                        <el-select v-model="basicForm.reflectType" placeholder="请选择类型" class="input-text">
                                            <el-option label="企业" value="complain"></el-option>
                                            <el-option label="个人" value="consult"></el-option>
                                            <el-option label="机构" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="回复方式:" class="bt">
                                        <el-select v-model="basicForm.reply" placeholder="请选择类型" class="input-text">
                                            <el-option label="电话" value="complain"></el-option>
                                            <el-option label="网络" value="consult"></el-option>
                                            <el-option label="信件" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="标题:" class="bt">
                                        <el-input v-model="basicForm.title" placeholder="请输入"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24">
                                    <el-form-item label="投诉内容:" class="bt">
                                        <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 6, maxRows: 8}"
                                                placeholder="请输入内容"
                                                v-model="basicForm.text">
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <el-collapse v-model="contentName2" accordion>
                    <el-collapse-item name="1">
                        <template slot="title">
                            <span class="el-accordion">提供方</span>
                        </template>
                        <el-form :model="offerForm" label-width="150px" ref="offerForm">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="姓名:" class="bt">
                                        <el-input v-model="offerForm.name" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="性别:" class="bt">
                                        <el-select v-model="offerForm.sex" placeholder="请选择分类" class="input-text">
                                            <el-option label="男" value="complain"></el-option>
                                            <el-option label="女" value="consult"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="联系电话:" class="bt">
                                        <el-input v-model="offerForm.phone" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="证件类型:" class="bt">
                                        <el-select v-model="offerForm.papersType" placeholder="请选择分类" class="input-text">
                                            <el-option label="身份证" value="complain"></el-option>
                                            <el-option label="军官证" value="consult"></el-option>
                                            <el-option label="其他" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="证件号码:" class="bt">
                                        <el-input v-model="offerForm.papersID" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="电子邮箱:" class="bt">
                                        <el-input v-model="offerForm.email" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="联系地址:" class="bt">
                                        <el-input v-model="offerForm.addr" placeholder="请输入" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
                <el-collapse v-model="contentName3" accordion>
                    <el-collapse-item name="1">
                        <template slot="title">
                            <span class="el-accordion">主体信息</span>
                        </template>
                        <el-form :model="entForm" label-width="150px" ref="entForm">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="主体名称:" class="bt">
                                        <el-input v-model="entForm.entName" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8" style="height: 45px">
                                    <el-form-item label="统一社会信用代码/注册号" class="bt">
                                        <el-input v-model="entForm.social" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="主体类型:" class="bt">
                                        <el-select v-model="entForm.entType" placeholder="请选择分类" class="input-text">
                                            <el-option label="私营企业" value="complain"></el-option>
                                            <el-option label="股份有限公司" value="consult"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="联系电话:" class="bt">
                                        <el-input v-model="entForm.phone" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="法定代表人:" class="bt">
                                        <el-input v-model="entForm.representative" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="核查名称:" class="bt">
                                        <el-input v-model="entForm.examine" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="客体名称:" class="bt">
                                        <el-input v-model="entForm.commodity" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="涉及金额:" class="bt">
                                        <el-input v-model="entForm.money" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="品牌:" class="bt">
                                        <el-input v-model="entForm.brand" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="类别:" class="bt">
                                        <el-select v-model="entForm.type" placeholder="请选择分类" class="input-text">
                                            <el-option label="食品" value="complain"></el-option>
                                            <el-option label="家电" value="consult"></el-option>
                                            <el-option label="家具" value="enforcement"></el-option>
                                            <el-option label="数码产品" value="enforcement"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="生产企业:" class="bt">
                                        <el-input v-model="entForm.manufacture" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                                    <el-form-item label="数量:" class="bt">
                                        <el-input v-model="entForm.quantity" placeholder="请输入" class="input-text"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="注册地址:" class="bt">
                                        <el-input v-model="entForm.loginAddr" placeholder="请输入"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="经营地址:" class="bt">
                                        <el-input v-model="entForm.manageAddr" placeholder="请输入"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="核查经营地址:" class="bt">
                                        <el-input v-model="entForm.examineAddr" placeholder="请输入"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>

            <!--材料上传开始-->
            <el-tab-pane label="材料上传">
                <el-tabs v-model="activeName" class="file-tabs">
                    <el-tab-pane label="文档" name="word">
                        <el-button size="small" @click="toUploadOpen('word')"><i class="fa fa-upload"></i> 上传
                        </el-button>
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="wordList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="图片" name="image">
                        <el-button size="small" @click="toUploadOpen('image')"><i class="fa fa-upload"></i> 上传
                        </el-button>
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="imageList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="视频" name="video">
                        <el-button size="small" @click="toUploadOpen('video')"><i class="fa fa-upload"></i> 上传
                        </el-button>
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="videoList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="其他" name="other">
                        <el-button size="small" @click="toUploadOpen('other')"><i class="fa fa-upload"></i> 上传
                        </el-button>
                        <el-button size="small"><i class="fa fa-download"></i> 下载</el-button>
                        <el-button size="small"><i class="fa fa-remove"></i> 删除</el-button>
                        <br/>
                        <br/>
                        <el-table :data="otherList" border style="width: 100%">
                            <el-table-column prop="selection" width="50" type="selection"></el-table-column>
                            <el-table-column prop="index" width="65" type="index" label="序号"></el-table-column>
                            <el-table-column prop="filename" label="文件名" min-width="200"></el-table-column>
                            <el-table-column prop="author" label="上传人" width="180"></el-table-column>
                            <el-table-column prop="fileDate" label="上传时间" min-width="150"></el-table-column>
                            <el-table-column prop="describe" label="描述" min-width="250"></el-table-column>
                        </el-table>
                        <div class="pagination" style="float: right;">
                            <el-pagination
                                    :page-size="fileWordPagination.size"
                                    :total="fileWordPagination.total"
                                    :page-sizes="[10, 20, 30, 40,50]"
                                    layout="slot,total, sizes, prev, pager, next, jumper"
                            >
                                <span>
                                  第 {{fileWordPagination.page}} 页 /
                                  共 {{(parseInt((fileWordPagination.total + fileWordPagination.size - 1)/fileWordPagination.size))}} 页
                                </span>
                            </el-pagination>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </el-tab-pane>
            <!--材料上传结束-->
            <el-tab-pane label="流程跟踪">

                <el-table
                        :data="tableData5" border
                        style="width: 100%">
                    <el-table-column type="expand">
                        <template scope="props">
                            <el-form inline class="demo-table-expand" label-width="100px">
                                <el-form-item label="步骤">
                                    <span>{{ props.row.id }}</span>
                                </el-form-item>
                                <el-form-item label="处理部门">
                                    <span>{{ props.row.dept }}</span>
                                </el-form-item>
                                <el-form-item label="处理人员">
                                    <span>{{ props.row.person }}</span>
                                </el-form-item>
                                <el-form-item label="操作">
                                    <span>{{ props.row.operation}}</span>
                                </el-form-item>
                                <el-form-item label="处理时间">
                                    <span>{{ props.row.time }}</span>
                                </el-form-item>
                                <el-form-item label="处理意见">
                                    <span>{{ props.row.opinion }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column type="index" label="序号" width="70"></el-table-column>
                    <el-table-column
                            label="步骤"
                            prop="id">
                    </el-table-column>
                    <el-table-column
                            label="处理部门"
                            prop="dept">
                    </el-table-column>
                    <el-table-column
                            label="处理人员"
                            prop="person">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            prop="operation">
                    </el-table-column>
                    <el-table-column
                            label="处理时间"
                            prop="time">
                    </el-table-column>
                    <el-table-column
                            label="处理意见"
                            prop="opinion"
                            show-overflow-tooltip>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
        </el-tabs>
        <el-dialog
                :title="fileUploadText"
                :visible.sync="fileUploadVisible"
                size="tiny">
            <el-upload :auto-upload="false" :file-list="fileList" ref="upload"
                       action="https://jsonplaceholder.typicode.com/posts/"
            >
                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button size="small" type="success" @click="submitUpload">确认上传
                </el-button>
            </el-upload>
        </el-dialog>
        <el-col style="height: 15px;"></el-col>
        <table v-show="flows" style="width: 96%;border-color: #fbfdff;margin: 0 auto;margin-top: 15px" cellspacing="0" cellpadding="0" border>
            <tr style="width: 100%;background-color: #ececec;"><th colspan="4">历次意见</th></tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: #f6f6f6">受理登记意见：</td>
                <!--                <td  style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;同意受理</td>-->
                <td style="background-color: #f6f6f6">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：柳逐浪&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: rgb(243, 247, 251);">受理意见：</td>
                <td style="background-color: rgb(243, 247, 251);">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：林修崖&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color:#f6f6f6">审查意见：</td>
                <td style="background-color: #f6f6f6">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：林破天&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: rgb(243, 247, 251);">审批意见：</td>
                <!--                <td  style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;同意受理</td>-->
                <td style="background-color: rgb(243, 247, 251);">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：陆云&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
        </table>
        <el-form :model="nextStepForm" ref="nextStepForm" style="width: 96%;margin: 0 auto" v-show="flows">
            <el-row style="margin: 20px 0 0 0;">
                <el-col :xs="12" :sm="12" :md="12" :lg="6">
                    <el-form-item label="结果" prop="opinion" label-width="65px" required>
                        <el-select v-model="nextStepForm.opinion" placeholder="默认意见">
                            <el-option
                                    v-for="item in cities"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="6">
                    <el-form-item label="日期：" prop="date" label-width="65px" required error="请选择日期">
                        <el-date-picker type="date" placeholder="选择日期"
                                        v-model="nextStepForm.date"></el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="24" :md="12" :lg="8">
                    <el-form-item label="选择下一步处理部门：" prop="department" label-width="170px" required>
                        <el-select v-model="nextStepForm.department" placeholder="请选择" prop="department">
                            <el-option
                                    v-for="item in dept"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col style="text-align: right;width: 100%;">
                    <el-button type="primary" @click="open">
                        发送
                    </el-button>
                </el-col>
            </el-row>
        </el-form>

    </section>
</template>

<script>
    export default {
        data() {
            return {
                nextStepForm:{},
                cities: [{
                    value: '转案件处理',
                    label: '转案件处理'
                }, {
                    value: '结果反馈',
                    label: '结果反馈'
                }],
                value6: '',

                dept: [{
                    value: 'consent',
                    label: '企管科'
                }, {
                    value: 'reject',
                    label: '消保科'
                }, {
                    value: 'transfer',
                    label: '广告科'
                }, {
                    value: 'endFlow',
                    label: '结束流程'
                }],
                value7: '',

                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                textarea: '',
                activeName: 'word',
                wordList: [{
                    filename: '文档1.docx',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '文档1。'
                }, {
                    filename: '文档2.doc',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '文档2。'
                }, {
                    filename: '文档3.docx',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '文档3。'
                }, {
                    filename: '文档4.docx',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '文档4。'
                }],
                imageList: [{
                    filename: '图片1.png',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '图片1。'
                }, {
                    filename: '图片2.jpg',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '图片2。'
                }, {
                    filename: '图片3.png',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '图片3。'
                }, {
                    filename: '图片4.svg',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '图片4。'
                }],
                videoList: [{
                    filename: '视频1.mp4',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '视频1。'
                }, {
                    filename: '视频2.mp4',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '视频2。'
                }, {
                    filename: '视频3.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '视频3。'
                }, {
                    filename: '视频4.mp4',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '视频4。'
                }],
                otherList: [{
                    filename: '文件1.png',
                    author: '李晓明',
                    fileDate: '2019-07-18 12:00',
                    describe: '文件1。'
                }, {
                    filename: '音频2.jpg',
                    author: '李先明',
                    fileDate: '2019-07-17 19:18',
                    describe: '文件2。'
                }, {
                    filename: '文件3.png',
                    author: '王小虎',
                    fileDate: '2019-07-16 16:11',
                    describe: '文件3。'
                }, {
                    filename: '其他4.svg',
                    author: '王小明',
                    fileDate: '2019-07-15 12:33',
                    describe: '文件4。'
                }],
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: '',
                flows: true,
                tableData5: [{
                    id: '受理登记',
                    dept: '企管科',
                    person: '柳逐浪',
                    operation: '送部门',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理',
                    dept: '企管科',
                    person: '林修崖',
                    operation: '送审核',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理审查',
                    dept: '企管科',
                    person: '林破天',
                    operation: '送审批',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理审批',
                    dept: '局领导',
                    person: '陆云',
                    operation: '下级处理',
                    time: '2019-07-30',
                    opinion: '同意受理'
                }, {
                    id: '受理结果',
                    dept: '企管科',
                    person: '柳逐浪',
                    operation: '意见反馈',
                    time: '2019-07-30',
                    opinion: '经协调，双方达成和解'
                }],
                offerForm: {
                    name:'莫风',
                    sex: '女',
                    phone: '13655060778',
                    email: '',
                    papersType: '身份证',
                    papersID: '440833199506120456',
                    addr: '顺德乐从镇新马路23号'
                },
                basicForm: {
                    type:'投诉类',
                    person:'李晓敏',
                    registrarDate:'2019-07-26',
                    formType:'消费投诉',
                    title:'股东变更',
                    word:'普通',
                    addr:'顺德乐从镇',
                    reflectType:'企业',
                    reply:'网络',
                    text:'已经核名的企业还未到工商办理新设立，想要变更股东要怎么处理',
                },
                sourceForm: {
                    number:'65000019',
                    content:'12345平台',
                    way:'网络',
                    type:'系统获取',
                    receiveDate:'2019-07-26',
                },
                providerForm: {},
                entForm: {
                    entName:'佛山市万佳德科技有限公司',
                    social:'968456255326652616',
                    entType:'股份有限公司',
                    phone:'0757-36650456',
                    representative:'刘楠',
                    examine:'佛山市万佳德科技有限公司',
                    commodity:'键盘',
                    money:'2000',
                    brand:'联想',
                    type:'配件',
                    manufacture:'佛山南粤精品配件生产',
                    quantity:'10',
                    loginAddr:'佛山市顺德区德胜中路54号',
                    manageAddr:'佛山市顺德区德胜中路54号',
                    examineAddr:'佛山市顺德区德胜中路54号'
                },
                content:'',
                contentName0:'1',
                contentName1:'1',
                contentName2:'1',
                contentName3:'1',
                // providerFormRules: {
                //     name: [
                //         {required: true, message: '请输入名称', trigger: 'blur'},
                //     ]
                // },
            };
        },
        methods: {
            arraySpanMethod({ row, column, rowIndex, columnIndex }) {
                if (rowIndex % 2 === 0) {
                    if (columnIndex === 0) {
                        return [1, 2];
                    } else if (columnIndex === 1) {
                        return [0, 0];
                    }
                }
            },

            objectSpanMethod({ row, column, rowIndex, columnIndex }) {
                if (columnIndex === 0) {
                    if (rowIndex % 2 === 0) {
                        return {
                            rowspan: 2,
                            colspan: 1
                        };
                    } else {
                        return {
                            rowspan: 0,
                            colspan: 0
                        };
                    }
                }
            },

            toUploadOpen(type) {
                if (type === 'word') {
                    this.fileUploadText = '文档上传'
                } else if (type === 'image') {
                    this.fileUploadText = '图片上传'
                } else if (type === 'video') {
                    this.fileUploadText = '视频上传'
                } else if (type === 'other') {
                    this.fileUploadText = '其他文件上传'
                }
                this.fileUploadVisible = true
            },
            submitUpload() {
                this.$refs.upload.submit();
            },

            //显示新增界面
            nextStep: function () {
                this.addFormVisible = true;
                this.addForm = {
                };
            },
            flow(visible) {
                if(visible.index == "1" || visible.index == "2"){
                    this.flows=false;
                }else {
                    this.flows=true;
                }
            },

            open() {
                if(this.nextStepForm.opinion == "结果反馈"){
                    this.content = '结果反馈成功';
                    this.$alert(this.content, '提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$router.push('/wddb/12345db');

                        }

                    });
                }else if(this.nextStepForm.opinion == "转案件处理"){
                    this.content = '已转案件处理';
                    this.$alert(this.content, '提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            // this.$message({
                            //     type: 'info',
                            //     message: `action: ${ action }`
                            //
                            // });
                            this.$router.push('/wddb/12345db');

                        }

                    });
                }else {
                    this.content = '请选择结果！';
                    this.$alert(this.content, '提示', {
                        confirmButtonText: '确定',
                        callback: action => {

                        }

                    });
                }
            }
        }

    };
</script>
<style lang="scss" scoped>
    .input-text {
        width: 100%;
    }
    .endFlow {
        overflow-x: hidden;

        .el-table__body-wrapper{
            overflow: hidden;
        }
        .el-input input{
            height: 30px;
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
        .el-form-item{
            margin-bottom: 15px;
        }
        .bt {
            font-weight: bold;
            text-align: right;
        }
        .clearfix {
            font-weight: bold;
        }
        .el-accordion{
            font-size: 14px;
            font-weight: bold;
            color: #1e1f20;
        }
    }

</style>
