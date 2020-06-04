<!--12345待办列表-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="search">
                <el-form-item>
                    <el-col>系统选择:</el-col>
                </el-form-item>
                <el-form-item style="width: 15%">
                    <el-select
                            placeholder="请选择"
                            class="input-text"
                            v-model="search.systemsource"
                            @change="change(search.systemsource)"
                            clearable>
                        <el-option label="本地系统" value="0"></el-option>
                        <el-option label="12345系统" value="1"></el-option>
                        <el-option label="全部系统" value=""></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-col>工单分类:</el-col>
                </el-form-item>
                <el-form-item style="width: 15%">
                    <el-select
                            placeholder="请选择"
                            class="input-text"
                            v-model="search.focus"
                            @change="change(search.focus)"
                            clearable
                    >
                        <el-option label="普通" value="普通"></el-option>
                        <el-option label="特殊" value="特殊"></el-option>
                        <el-option label="特派" value="特派"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-col>标题:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.rqsttitle" @change="change(search.rqsttitle)" placeholder="标题"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-col>工单号:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input
                            v-model="search.tserialnum"
                            @change="change(search.tserialnum)"
                            placeholder="工单号"
                    ></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" v-on:click="findList(search.systemsource)">查询</el-button>
                </el-form-item>
                <el-button style="float: right;" type="danger" @click="handleDel">删除</el-button>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table
                :data="complaintList"
                highlight-current-row
                v-loading="complaintListLoading"
                element-loading-text="拼命加载中"
                @selection-change="selsChange"
                border
                style="width: 100%"
                @cell-click="toFind"
                :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>
            <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
            <el-table-column prop="focus" label="工单分类" min-width="7%" align="center"></el-table-column>
            <el-table-column prop="serialnum" label="表单号" min-width="12%" align="center"></el-table-column>
            <el-table-column prop="tserialnum" label="工单号" min-width="12%" align="center"></el-table-column>
            <!--            <el-table-column prop="subject" label="涉及主体名称" min-width="180" -->
            <!--                             show-overflow-tooltip></el-table-column>-->
            <el-table-column
                    prop="rqsttitle"
                    label="工单标题"
                    min-width="19%"
                    header-align="center"
                    show-overflow-tooltip
            >
                <template slot-scope="scope">
                    <span v-html="brightenKeyword(scope.row.rqsttitle,search.rqsttitle)"></span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="accordtype"
                    label="业务分类"
                    min-width="10%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="labelname"
                    label="政务标签"
                    min-width="10%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="assigndate"
                    label="交办时间"
                    min-width="10%"
                    align="center"
                    :formatter="formatDate"
            ></el-table-column>
            <el-table-column prop="resulttimemin" label="到期时间" min-width="10%" align="center"></el-table-column>
            <!--            <el-table-column prop="provider" label="提供方" width="100" ></el-table-column>-->
            <!--            <el-table-column prop="type" label="类型" width="100" :formatter="formatType" ></el-table-column>-->
        </el-table>
        <br />
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
    </section>
</template>

<script>
    import {
        listTasklist,
        removeUser,
        endListTasklist,
        batchRemoveUser,
        deleteTasklist,
        signTasklist
    } from "../../api/api";

    export default {
        data() {
            return {
                search: {
                    focus: "",
                    serialnum: "",
                    tserialnum: "",
                    rqsttitle: "",
                    accordtype: "",
                    labelname: "",
                    assigndate: "",
                    esulttimemin: "",
                    systemsource:""
                },
                complaintList: [],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1
                },
                complaintListLoading: false,
                sels: [] //列表选中列
            };
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
            change(value) {
                console.log(value)
                this.findList(value);
            },
            findList() {
                // let params = {
                //     pagination: this.pagination
                // };
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                console.log(this.search.systemsource)
                let params = {
                    page: this.pagination.page,
                    total: this.pagination.total,
                    size: this.pagination.size,
                    // user: user,
                    // systemsource: "",
                    // tserialnum: this.search.tserialnum,
                    // rqsttitle: this.search.rqsttitle,
                    // serialnum: this.search.serialnum,
                    // focus: this.search.focus,
                    // tserialnum: "",
                    // rqsttitle: "",
                    // serialnum: "",
                    // focus: "",
                    nextnodeid:"end",
                    systemsource:this.search.systemsource,
                    focus:this.search.focus,
                };
                this.complaintListLoading = true;
                endListTasklist(params).then(res => {
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.complaintList = res.data.records;
                    this.complaintListLoading = false;
                });
            },
            toFind(row, column) {
                //阻止勾选复选框跳转
                if (column.property !== "select") {
                    let params = {
                        id: row.id,
                        caseguid: row.caseguid,
                        ismain: row.ismain
                    };
                    console.log(row);
                    if ("0" === row.issignin) {
                        let paras = {
                            user: JSON.parse(localStorage.getItem("user")),
                            caseguid: row.caseguid,
                            nextnodeid: row.nextnodeid,
                            ismain: row.ismain
                        };
                        signTasklist(paras);
                    }
                    //判断跳转的操作步骤页面
                    if ("leaderApr" === row.nextnodeid) {
                        //领导审批
                        this.$router.push({ path: "/wddb/12345db/ldsp", query: params });
                    } else if ("applyDelay" === row.nextnodeid) {
                        //延期申请
                        this.$router.push({ path: "/wddb/12345db/yqsp", query: params });
                    } else if ("accept" === row.nextnodeid && "03" === row.formtype) {
                        //经济违法立案处理
                        this.$router.push({ path: "/wddb/12345db/lacl", query: params });
                    } else if ("begin" === row.nextnodeid) {
                        //初步答复
                        this.$router.push({ path: "/wddb/12345db/gdsh", query: params });
                    } else if (
                        "handleFeedback" === row.nextnodeid ||
                        "1" === row.isaccept ||
                        "01" === row.formtype //01表示通用表单
                    ) {
                        //处理反馈
                        this.$router.push({ path: "/wddb/12345db/clfpysth", query: params });
                    } else if ("0" === row.isaccept) {
                        //未受理
                        this.$router.push({ path: "/wddb/12345db/slbslfpth", query: params });
                    }
                }
            },
            formatDate(row, column) {
                return this.$moment(row.assigndate).format("YYYY-MM-DD");
            },
            //删除
            handleDel: function() {
                let user = localStorage.getItem('user');
                let ids = this.sels.map(item => item.id).toString();
                this.$confirm("确认删除该记录吗?", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.complaintListLoading = true;
                        //NProgress.start();
                        let para = {
                            ids: ids,
                            user: JSON.parse(user)
                        };
                        deleteTasklist(para).then(res => {
                            this.complaintListLoading = false;
                            //NProgress.done();
                            this.$alert("删除成功!", "提示", {
                                confirmButtonText: "确定",
                                type: "success"
                            });
                            this.findList();
                        });
                    })
                    .catch(() => {});
            },
            selsChange: function(sels) {
                this.sels = sels;
            },
            brightenKeyword(val, keyword) {
                val = val + "";
                if (val.indexOf(keyword) !== -1 && keyword !== "") {
                    return val.replace(
                        keyword,
                        '<font color="#20a0ff">' + keyword + "</font>"
                    );
                } else {
                    return val;
                }
            },
            //批量删除
            batchRemove: function() {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm("确认删除选中记录吗？", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.complaintListLoading = true;
                        //NProgress.start();
                        let para = { ids: ids };
                        batchRemoveUser(para).then(res => {
                            this.complaintListLoading = false;
                            //NProgress.done();
                            this.$alert("删除成功!", "提示", {
                                confirmButtonText: "确定",
                                type: "success"
                            });
                            this.findList();
                        });
                    })
                    .catch(() => {});
            }
        },

        mounted() {
            this.findList();
        }
    };
</script>

<style>
    /* .el-table__header thead th{*/
    /*	 text-align: center;*/
    /* }*/
    /* .el-table__body tbody tr td div{*/
    /*	 float: left;*/
    /*	 margin-left: 10px;*/
    /* }*/
    /*.el-form-item__content {*/
    /*	margin-right: 10px;*/
    /*}*/
</style>
