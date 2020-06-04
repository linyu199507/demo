<template>
    <section class="leaderExamine">
        <el-row style="margin-top: 30px;margin-bottom: 15px">
            <el-col>
                <el-steps :active="4" align-center center space="22%">
                    <el-step title="受理登记"></el-step>
                    <el-step title="受理"></el-step>
                    <el-step title="受理审查"></el-step>
                    <el-step title="受理审批"></el-step>
                    <el-step title="受理结果"></el-step>
                </el-steps>
            </el-col>
        </el-row>
        <el-tabs type="border-card" style="width: 96%;margin: 0 auto;" @tab-click="flow">
            <el-tab-pane label="系统信息来源">
                <el-form :model="sourceForm" label-width="150px" ref="sourceForm">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="登记编号：">
                                <el-input v-model="sourceForm.number" placeholder="请输入"
                                          class="input-text" value="23000020"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="信息来源：">
                                <el-input v-model="sourceForm.content" placeholder="请输入"
                                          class="input-text" value="12345平台"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="接收方式：">
                                <el-select v-model="sourceForm.way" placeholder="请选择分类" class="input-text">
                                    <el-option label="电话" value="complain"></el-option>
                                    <el-option label="信件" value="consult"></el-option>
                                    <el-option label="网络" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="接收类型：">
                                <el-select v-model="sourceForm.type" placeholder="请选择分类" class="input-text">
                                    <el-option label="人工录入" value="complain"></el-option>
                                    <el-option label="系统获取" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="接收时间：">
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
            </el-tab-pane>

            <el-tab-pane label="基本信息">
                <el-form :model="basicForm" label-width="150px" ref="basicForm" :rules="basicFormRules">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="问题分类:">
                                <el-select v-model="basicForm.type" placeholder="请选择分类" class="input-text">
                                    <el-option label="投诉类" value="complain"></el-option>
                                    <el-option label="资询类" value="consult"></el-option>
                                    <el-option label="执法类" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="登记人:">
                                <el-input v-model="basicForm.person" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="登记日期:">
                                <el-date-picker
                                        v-model="basicForm.registrarDate"
                                        type="date"
                                        class="input-text"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="表单类型:">
                                <el-select v-model="basicForm.formType" placeholder="请输入" class="input-text">
                                    <el-option label="消费投诉" value="complain"></el-option>
                                    <el-option label="售后投诉" value="consult"></el-option>
                                    <el-option label="其他投诉" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="工单分类:">
                                <el-select v-model="basicForm.word" placeholder="请输入" class="input-text">
                                    <el-option label="普通" value="complain"></el-option>
                                    <el-option label="特殊" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="发生地:">
                                <el-input v-model="basicForm.addr" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="反映类型:">
                                <el-select v-model="basicForm.reflectType" placeholder="请选择类型" class="input-text">
                                    <el-option label="企业" value="complain"></el-option>
                                    <el-option label="个人" value="consult"></el-option>
                                    <el-option label="机构" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="回复方式:">
                                <el-select v-model="basicForm.reply" placeholder="请选择类型" class="input-text">
                                    <el-option label="电话" value="complain"></el-option>
                                    <el-option label="网络" value="consult"></el-option>
                                    <el-option label="信件" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="标题:">
                                <el-input v-model="basicForm.title" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                            <el-form-item label="投诉内容:">
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
            </el-tab-pane>
            <el-tab-pane label="提供方">
                <el-form :model="offerForm" label-width="150px" ref="offerForm">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="姓名:">
                                <el-input v-model="offerForm.name" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="性别:">
                                <el-select v-model="offerForm.sex" placeholder="请选择分类" class="input-text">
                                    <el-option label="男" value="complain"></el-option>
                                    <el-option label="女" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="联系电话:">
                                <el-input v-model="offerForm.phone" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="证件类型:">
                                <el-select v-model="offerForm.papersType" placeholder="请选择分类" class="input-text">
                                    <el-option label="身份证" value="complain"></el-option>
                                    <el-option label="军官证" value="consult"></el-option>
                                    <el-option label="其他" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="证件号码:">
                                <el-input v-model="offerForm.papersID" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="电子邮箱:">
                                <el-input v-model="offerForm.email" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="联系地址:">
                                <el-input v-model="offerForm.addr" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="主体信息">
                <el-form :model="entForm" label-width="150px" ref="entForm">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="主体名称:">
                                <el-input v-model="entForm.entName" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8" style="height: 45px">
                            <el-form-item label="统一社会信用代码/注册号">
                                <el-input v-model="entForm.social" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="主体类型:">
                                <el-select v-model="entForm.entType" placeholder="请选择分类" class="input-text">
                                    <el-option label="私营企业" value="complain"></el-option>
                                    <el-option label="股份有限公司" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="联系电话:">
                                <el-input v-model="entForm.phone" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="法定代表人:">
                                <el-input v-model="entForm.representative" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="核查名称:">
                                <el-input v-model="entForm.examine" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="客体名称:">
                                <el-input v-model="entForm.commodity" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="涉及金额:">
                                <el-input v-model="entForm.money" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="品牌:">
                                <el-input v-model="entForm.brand" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="类别:">
                                <el-select v-model="entForm.type" placeholder="请选择分类" class="input-text">
                                    <el-option label="食品" value="complain"></el-option>
                                    <el-option label="家电" value="consult"></el-option>
                                    <el-option label="家具" value="enforcement"></el-option>
                                    <el-option label="数码产品" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="生产企业:">
                                <el-input v-model="entForm.manufacture" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="8">
                            <el-form-item label="数量:">
                                <el-input v-model="entForm.quantity" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="注册地址:">
                                <el-input v-model="entForm.loginAddr" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="经营地址:">
                                <el-input v-model="entForm.manageAddr" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="核查经营地址:">
                                <el-input v-model="entForm.examineAddr" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
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
                        <el-table :data="pictureList" border style="width: 100%">
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
        <table v-show="flows" style="width: 96%;margin: 0 auto;border-color: #fbfdff;" cellspacing="0" cellpadding="0" border>
            <tr style="width: 100%;background-color: #ececec;"><th colspan="4">历次意见</th></tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: aliceblue;">受理登记意见：</td>
                <!--                <td  style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;同意受理</td>-->
                <td style="background-color: aliceblue;">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：柳逐浪&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: rgb(243, 247, 251);">受理意见：</td>
                <!--                <td  style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;同意受理</td>-->
                <td style="background-color: rgb(243, 247, 251);">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：林修崖&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;text-align: center;background-color: aliceblue;">审查意见：</td>
                <!--                <td  style="width: 100%;">&nbsp;&nbsp;&nbsp;&nbsp;同意受理</td>-->
                <td style="background-color: aliceblue;">
                    <ul>
                        <div>同意受理</div>
                        <br>
                        <div style="float: right;margin-right: 20px;">处理人：林破天&nbsp;&nbsp;&nbsp;&nbsp;日期：2019-07-26</div>
                    </ul>
                </td>
            </tr>
            <tr style="width: 100%;height: 65px;">
                <td  style="width: 20%;height:120px;text-align: center;"><span style="color: #e64242">*</span>意见：</td>
                <td  style="width: 80%"><input style="width: 100%;height: 120px;" placeholder="请输入内容"></td>
            </tr>
        </table>
        <!--        <el-col style="height: 15px;"></el-col>-->
        <!--        <el-col>-->
        <!--            <el-col span="2" style="padding: 3px 28px;"><span style="color: #e64242">*</span>意见：</el-col>-->
        <!--            <el-col span="22"><el-input type="textarea" :rows="4" placeholder="请输入内容" v-model="value6"></el-input></el-col>-->
        <!--        </el-col>-->
        <el-col style="height: 15px;"></el-col>
        <el-col :span="19" v-show="flows" style="margin: 0 26px">
            <el-col :span="4">
                <el-select v-model="value6" clearable placeholder="默认意见">
                    <el-option
                            v-for="item in cities"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="3" style="padding: 3px 38px;"><span style="color: #e64242">*</span>日期：</el-col>
            <el-col :span="4">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="5" style="padding: 3px 30px;"><span style="color: #e64242">*</span>选择下一步处理部门：</el-col>
            <el-col :span="4">
                <el-select v-model="value7" clearable placeholder="请选择">
                    <el-option
                            v-for="item in dept"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
        </el-col>
        <el-col v-show="flows" style="text-align: right;width: 98%;">
            <el-button type="primary" @click="nextStep">
                <router-link to="/wddb/zxldb/cljg" style="color: #fff;text-decoration: none;">发送</router-link>
            </el-button>
        </el-col>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                flows:true,
                cities: [{
                    value: '同意受理',
                    label: '同意受理'
                }, {
                    value: '驳回',
                    label: '驳回'
                }, {
                    value: '转移',
                    label: '转移'
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
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '重要的文档，包含某某'
                }, {
                    filename: '文档2.docx',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '重要的文档，包含某某'
                }],
                pictureList: [{
                    filename: '图片1.jpg',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '图片'
                }, {
                    filename: '图片2.jpg',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '图片'
                }],
                videoList: [{
                    filename: '视频1.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '视频'
                }, {
                    filename: '视频2.mp4',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '视频'
                }],
                otherList: [{
                    filename: '录音1.mp3',
                    author: '王小虎',
                    fileDate: '2019-07-12 20:00',
                    describe: '音频'
                }, {
                    filename: '录音2.mp3',
                    author: '王小虎',
                    fileDate: '2019-07-12 19:00',
                    describe: '音频'
                }],
                fileWordPagination: {
                    page: 1,
                    size: 10,
                    total: 4,
                },
                fileUploadVisible: false,
                fileList: [],
                fileUploadText: '',
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
        }

    };
</script>
<style lang="scss" scoped>
    .leaderExamine {
        /*.el-table__row td div{*/
        /*    float: right;*/
        /*    margin-right: 20px;*/
        /*}*/
        overflow-x: hidden;

        .el-table__body-wrapper{
            overflow: hidden;
        }
        /*.el-table__header thead tr{*/
        /*    display: none;*/
        /*}*/
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
            margin-bottom: 9px;
        }
    }

    .input-text {
        width: 100%;
    }

</style>
