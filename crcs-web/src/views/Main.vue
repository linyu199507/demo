<template>
    <section class="distributed">
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="search">
                <el-form-item>
                    <el-col>登记编号:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.number" placeholder="登记编号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-col>主体名称:</el-col>
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

        <!--列表开始-->
        <el-table :data="distributionList" highlight-current-row v-loading="distributionListLoading"
                  @selection-change="selsChange"
                  border style="width: 100%;">
            <el-table-column type="selection" width="50" align="center">
            </el-table-column>
            <el-table-column type="index" label="序号" width="70">
            </el-table-column>
            <el-table-column prop="on" label="登记编号" width="160" sortable>
            </el-table-column>
            <el-table-column prop="subject" label="涉及主体名称" min-width="200" sortable
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="content" label="登记内容" min-width="200" sortable
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="birth" label="登记日期" width="150" sortable></el-table-column>
            <el-table-column prop="provider" label="提供方" min-width="100" sortable></el-table-column>
            <el-table-column prop="type" label="类型" width="100" :formatter="formatType" sortable>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="item">
                    <el-button type="primary" size="small" @click="toDistribution(item)">
                        分配
                    </el-button>
                    <el-button type="primary" size="small" @click="toFindDetails(item.row)">
                        查看
                    </el-button>
                    <el-button type="danger" size="small" @click="handleDel(item.$index, item.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--列表结束-->
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
        <!--分配操作开始-->
        <el-dialog title="新增分配" :visible.sync="toDistributionStatus" @close="cancelDistribution">
            <el-row>
                <el-col :span="17" :offset="4" :xs="{span: 20, offset: 4}">
                    <el-form :model="distributionForm" label-position="right"
                             label-width="60px" :rules="distributionFormRules" ref="distributionForm">
                        <!--<el-form-item label="类型" prop="type">
                            <el-select v-model="distributionForm.type" placeholder="请选择类型">
                                <el-option label="投诉类" value="complain"></el-option>
                                <el-option label="资询类" value="consult"></el-option>
                                <el-option label="执法类" value="enforcement"></el-option>
                                <el-option label="其他" value="other"></el-option>
                            </el-select>
                        </el-form-item>-->
                        <el-form-item label="部门" prop="department">
                            <el-select v-model="distributionForm.department" placeholder="请选择部门" style="width: 100%">
                                <el-option label="12315投诉举报中心" value="12315"></el-option>
                                <el-option label="消保科" value="xbk"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="意见" prop="opinion">
                            <el-input
                                    type="textarea"
                                    :rows="6"
                                    placeholder="请输入意见"
                                    v-model="distributionForm.opinion">
                            </el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelDistribution">取 消</el-button>
                <el-button type="primary" @click="submitDistribution" :loading="distributionLoading">确 定</el-button>
            </div>
        </el-dialog>
        <!--分配操作结束-->
        <!--查看详情开始-->
        <el-dialog
                title="查看详情"
                size="large"
                top="5%"
                :visible.sync="findDetailsVisible">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    系统来源信息
                </div>
                <el-row class="hang">
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            登记编号：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.source.number}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            信息来源：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.source.content}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            接收方式：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.source.type}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            接收类型：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.source.way}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            接收时间：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.source.receiveDate}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                </el-row>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    基本信息
                </div>
                <el-row class="hang">
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            问题分类：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.type}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            登记人：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.person}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            登记日期：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.registrarDate}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            表单类型：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.formType}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            工单分类：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.word}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            反映类型：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.reflectType}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            回复方式：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.reply}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="16">
                        <el-col :span="4" class="bt">
                            发生地：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.basic.addr}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            标题：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.basic.title}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :span="16">
                        <el-col :span="4" class="bt">
                            投诉内容：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.basic.text}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                </el-row>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    提供方
                </div>
                <el-row class="hang">
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            姓名：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.name}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            性别：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.sex}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            联系电话：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.phone}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            电子邮箱：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.email}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            证件类型：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.papersType}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            证件号码：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.offer.papersID}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :span="16">
                        <el-col :span="4" class="bt">
                            联系地址：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.offer.addr}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                </el-row>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    主体信息
                </div>
                <el-row class="hang">
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            主体名称：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.entName}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="14" class="bt">
                            统一社会信用代码/注册号：
                        </el-col>
                        <el-col :span="10">
                            {{detailsData.ent.social}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            主体类型：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.entType}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            联系电话：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.phone}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            法定代表人：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.representative}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            核查名称：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.examine}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            客体名称：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.commodity}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            涉及金额：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.money}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            品牌：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.brand}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            类别：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.type}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            生产企业：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.manufacture}}
                        </el-col>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="12" :lg="8">
                        <el-col :span="8" class="bt">
                            数量：
                        </el-col>
                        <el-col :span="16">
                            {{detailsData.ent.quantity}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :span="16">
                        <el-col :span="4" class="bt">
                            注册地址：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.ent.loginAddr}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :span="16">
                        <el-col :span="4" class="bt">
                            经营地址：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.ent.manageAddr}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :span="16">
                        <el-col :span="4" class="bt">
                            核查经营地址：
                        </el-col>
                        <el-col :span="20">
                            {{detailsData.ent.examineAddr}}
                        </el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                </el-row>
            </el-card>
            <el-row>
                <el-col style="text-align: center">
                    <el-button type="primary" @click="toDistribution">分配</el-button>
                    <el-button @click="findDetailsVisible=false">关闭</el-button>
                </el-col>
            </el-row>
        </el-dialog>
        <!--查看详情结束-->
    </section>
</template>

<script>
    import {listDistribution, removeUser, batchRemoveUser} from '../api/api';

    export default {
        data() {
            return {
                distributionList: [],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1,
                },
                distributionListLoading: false,
                toDistributionStatus: false,
                distributionForm: {
                    type: undefined,
                    department: undefined,
                },
                distributionFormRules: {
                    department: [
                        {required: true, message: '请选择部门', trigger: 'blur'}
                    ],
                    opinion: [
                        {required: true, message: '请输入意见', trigger: 'blur'},
                    ],
                },
                distributionLoading: false,
                sels: [],//列表选中列
                search: {
                    number: '',
                    name: ''
                },
                findDetailsVisible: false,
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
                }
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
                this.distributionListLoading = true;
                listDistribution(params).then((res) => {
                    this.pagination = res.data.pagination;
                    this.distributionList = res.data.list;
                    this.distributionListLoading = false;
                });
            },
            //提交新增分配
            submitDistribution() {
                this.$refs.distributionForm.validate((valid) => {
                    if (valid) {
                        this.distributionLoading = true;
                        setTimeout(() => {
                            this.toDistributionStatus = false;
                            this.distributionLoading = false;
                            this.findDetailsVisible = false;
                            this.$refs.distributionForm.resetFields();
                            this.$message({
                                message: '分配成功',
                                type: 'success',
                                duration: 1000
                            });
                        }, 500)
                    } else {
                        return false;
                    }
                });
            },
            cancelDistribution() {
                this.toDistributionStatus = false;
                this.$refs.distributionForm.resetFields();
            },
            toDistribution(item) {
                this.toDistributionStatus = true
            },
            toFindDetails(item) {
                this.findDetailsVisible = true;
                // this.$router.push('logParticulars')
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.distributionListLoading = true;
                    //NProgress.start();
                    let para = {id: row.id};
                    removeUser(para).then((res) => {
                        this.distributionListLoading = false;
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
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.distributionListLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveUser(para).then((res) => {
                        this.distributionListLoading = false;
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
            formatType(row, column) {
                if (row.type === 1) {
                    return `咨询类`
                } else if (row.type === 2) {
                    return `投诉类`
                } else {
                    return `执法类`
                }
            },
        },
        mounted() {
            this.findList();
            this.getUserInfo()
        }
    }

</script>

<style lang="scss">
    .distributed {
        .subject {
            white-space: nowrap;
            overflow-x: hidden;
            text-overflow: ellipsis;
        }

        .hang .el-col {
            font-family: initial;
        }

        .br {
            padding-bottom: 10px;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }

        .bt {
            font-weight: bold;
            text-align: right;
        }

        .clearfix {
            font-weight: bold;
        }

        .box-card {
            margin-bottom: 15px;
        }

        .el-card__header {
            background-color: #fafafa;
        }

        .el-dialog--large {
            width: 70%;
        }

        /*.el-message {*/
        /*    top: 200px;*/
        /*}*/
    }
</style>
