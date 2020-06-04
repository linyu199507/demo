<template>
    <section class="listConsult">
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="search">
                <el-form-item>
                    <el-col>登记编号:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.on" placeholder="登记编号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-col>涉及主体:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.name" placeholder="主体名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="findList">查询</el-button>
                </el-form-item>
                <el-button style="float: right;" type="danger" @click="batchRemove">批量删除</el-button>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="complaintList" highlight-current-row v-loading="complaintListLoading"
                  @selection-change="selsChange" border
                  @cell-click="toFind" :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection" width="50" align="center"></el-table-column>
            <el-table-column type="index" label="序号" width="70" align="center"></el-table-column>
            <el-table-column prop="on" label="登记编号" width="130" sortable align="center"></el-table-column>
            <el-table-column prop="type" label="类型" width="100" :formatter="formatType" sortable align="center"></el-table-column>
            <el-table-column prop="birth" label="登记日期" width="130" sortable align="center"></el-table-column>
            <el-table-column prop="provider" label="提供方" width="100" sortable align="center"></el-table-column>
            <el-table-column prop="subject" label="涉及主体名称" min-width="180" sortable  header-align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="providerDept" label="登记部门" width="120" :formatter="formatDept" sortable align="center"></el-table-column>
            <el-table-column prop="provider" label="登记人" width="100" sortable align="center"></el-table-column>
            <el-table-column prop="content" label="登记内容" min-width="180" sortable header-align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="operate" label="操作" width="100" align="center">
                <template slot-scope="item">
                    <el-button type="primary" size="small" @click="toFindDetails()">
                        分流
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <br/>
        <!--分页组件开始-->
        <el-col style="text-align: right;">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-size="pagination.size"
                    :total="pagination.total"
                    :page-sizes="[10, 20, 30, 40,50]"
                    layout="slot,total, sizes, prev, pager, next, jumper"
            >
        <span>
          第 {{pagination.page}} 页 /
          共 {{(parseInt((pagination.total + pagination.size - 1)/pagination.size))}} 页
        </span>
            </el-pagination>
        </el-col>
        <!--分页组件结束-->

        <!--分流操作开始-->
        <el-dialog
                title="分流"
                size="large"
                top="5%"
                :visible.sync="findDetailsVisible">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    业务基本信息
                </div>
                <el-form :model="subjectForm" label-width="150px" ref="basicForm">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="登记日期:">
                                <el-date-picker
                                        v-model="subjectForm.registrarDate"
                                        type="date"
                                        class="input-text"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="业务类别:">
                                <el-select v-model="subjectForm.businessType" placeholder="请选择分类" class="input-text">
                                    <el-option label="投诉类" value="complain"></el-option>
                                    <el-option label="资询类" value="consult"></el-option>
                                    <el-option label="执法类" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="提供方姓名单位:">
                                <el-input v-model="subjectForm.person" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="提供方联系电话:">
                                <el-input v-model="subjectForm.phone" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="涉案主体名称:">
                                <el-input v-model="subjectForm.name" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="涉案主体联系电话:">
                                <el-input v-model="subjectForm.phones" placeholder="请输入" class="input-text"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                            <el-form-item label="投诉内容:">
                                <el-input
                                        type="textarea"
                                        style="width: 91.2%;"
                                        :autosize="{ minRows: 6, maxRows: 8}"
                                        placeholder="请输入内容"
                                        v-model="subjectForm.text">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                   投诉分流处理
                </div>
                <el-form :model="subjectForm" label-width="150px" ref="basicForm">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="事件级别:">
                                <el-select v-model="subjectForm.rank" placeholder="请选择分类" class="input-text">
                                    <el-option label="一般事件" value="complain"></el-option>
                                    <el-option label="紧急事件" value="consult"></el-option>
                                    <el-option label="特别紧急事件" value="enforcement"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="是否食药、质监投诉举报:">
                                <el-select v-model="subjectForm.complaints" placeholder="请选择" class="input-text">
                                    <el-option label="是" value="complain"></el-option>
                                    <el-option label="否" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="初查反馈期限:">
                                <el-date-picker
                                        v-model="subjectForm.feedbackDate"
                                        type="date"
                                        class="input-text"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="办理期限:">
                                <el-date-picker
                                        v-model="subjectForm.transactDate"
                                        type="date"
                                        class="input-text"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="分流情况:">
                                <el-select v-model="subjectForm.tauthority" placeholder="请选择" style="width: 39%">
                                    <el-option label="本地市场监督管理机关" value="complain"></el-option>
                                    <el-option label="上级市场监督管理机关" value="consult"></el-option>
                                </el-select>
                                <el-select v-model="subjectForm.direction" placeholder="请选择" style="width: 40%">
                                    <el-option label="向上分流" value="complain"></el-option>
                                    <el-option label="向下分流" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="接收部门:">
                                <el-select v-model="subjectForm.receptionDept" placeholder="请选择分类" class="input-text">
                                    <el-option label="是" value="complain"></el-option>
                                    <el-option label="否" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item label="被投诉地址:">
                                <el-input v-model="subjectForm.person" placeholder="请输入" style="width: 91.2%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24">
                            <el-form-item label="附言:">
                                <el-input
                                        type="textarea"
                                        style="width: 91.2%;"
                                        :autosize="{ minRows: 6, maxRows: 8}"
                                        placeholder="请输入内容"
                                        v-model="subjectForm.text">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="是否重大事件:">
                                <el-select v-model="subjectForm.incident" placeholder="请选择" class="input-text">
                                    <el-option label="是" value="complain"></el-option>
                                    <el-option label="否" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="是否出现场:">
                                <el-select v-model="subjectForm.site" placeholder="请选择" class="input-text">
                                    <el-option label="是" value="complain"></el-option>
                                    <el-option label="否" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="15">
                            <el-form-item label="结案审批部门类型:">
                                <el-select v-model="subjectForm.deptType" placeholder="请选择" class="input-text" style="width: 60.2%">
                                    <el-option label="是" value="complain"></el-option>
                                    <el-option label="否" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="12" :lg="12">
                            <el-form-item label="操作:">
                                <el-select v-model="subjectForm.operate" placeholder="请选择" class="input-text">
                                    <el-option label="分流" value="complain"></el-option>
                                    <el-option label="送审" value="consult"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-card>
            <el-col style="height: 15px"></el-col>
            <el-row>
                <el-col style="text-align: center">
                    <el-button type="primary" @click="toDistribution">提交</el-button>
                    <el-button type="primary" @click="toCloseDetails()">关闭</el-button>
                </el-col>
            </el-row>
        </el-dialog>
        <!--分流操作结束-->
    </section>
</template>

<script>
    import {listComplaint, removeUser, batchRemoveUser} from '../../../api/api';

    export default {
        data() {
            return {
                search: {
                    on: '',
                    name: ''
                },
                complaintList: [],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1,
                },
                complaintListLoading: false,
                findDetailsVisible:false,
                toDistributionStatus:false,
                sels: [],//列表选中列
                subjectForm: {
                    name: '西藏保利爱家房地产经济有限公司佛山分公司',
                    phone: '13834593938',
                    manageAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    actualAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    checkManageAddr: '顺德区大良新城区拥翠路保利国际金融中心1号办公楼',
                    checkName: '',
                },
                detailsData: {
                    source: {
                        number: '65000019',
                        content: '12345平台',
                        way: '网络',
                        type: '系统获取',
                        receiveDate: '2019-07-26',
                    },
                    basic: {
                        type: '咨询类',
                        person: '李晓敏',
                        registrarDate: '2019-07-26',
                        formType: '消费投诉',
                        title: '股东变更',
                        word: '普通',
                        addr: '顺德乐从镇',
                        reflectType: '企业',
                        reply: '网络',
                        text: '已经核名的企业还未到工商办理新设立，想要变更股东要怎么处理',
                    },
                    offer: {
                        name: '莫风',
                        sex: '女',
                        phone: '13655060778',
                        email: '1696204958@qq.com',
                        papersType: '身份证',
                        papersID: '440833199506120456',
                        addr: '顺德乐从镇新马路23号'
                    },
                    ent: {
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
                },
            }
        },
        methods: {
            handleCurrentChange(currentPage) {
                this.pagination.page = currentPage;
                this.findList();
            },
            handleSizeChange(size) {
                this.pagination.size = size;
                this.findList();
            },
            findList() {
                let params = {
                    pagination: this.pagination
                };
                // this.complaintListLoading = true;
                listComplaint(params).then((res) => {
                    this.pagination = res.data.pagination;
                    this.complaintList = res.data.list;
                    this.complaintListLoading = false;
                }).catch(()=>this.complaintListLoading = false);
            },
            toFind(row, column) {
                //阻止勾选复选框跳转 column.property !== 'select'
                if (column.property !== 'operate') {
                    this.$router.push('/wddb/zxldb/zfsl')
                }
            },
            formatType(row, column) {
                return `咨询类`
                // if (row.type === 1) {
                //     return `咨询类`
                // } else if (row.type === 2) {
                //     return `投诉类`
                // } else {
                //     return `执法类`
                // }
            },
            formatDept(row, column) {
                return `业务部门`
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.complaintListLoading = true;
                    //NProgress.start();
                    let para = {id: row.id};
                    removeUser(para).then((res) => {
                        this.complaintListLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.findList();
                    });
                }).catch(() => {

                });
            },
            selsChange: function (sels) {
                console.log(sels)
                this.sels = sels;
            },
            toDistribution() {
                this.toDistributionStatus = true
            },
            toFindDetails() {
                this.findDetailsVisible = true;
                // this.$router.push('logParticulars')
            },
            toCloseDetails() {
                console.log("123")
                this.findDetailsVisible = false;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.complaintListLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveUser(para).then((res) => {
                        this.complaintListLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.findList();
                    });
                }).catch(() => {

                });
            },
        },

        mounted() {
            this.findList();
        }
    }

</script>

<style lang="scss" scoped>
    .listConsult{
        .input-text{
               width: 80%;
           }
        .box-card .el-form .el-row {
            margin-left: 0;
            margin-right: 0;
        }
        .el-form-item {
            margin-bottom: 15px;
        }
    }
</style>
