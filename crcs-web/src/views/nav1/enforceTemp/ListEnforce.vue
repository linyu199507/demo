<template>
    <section>
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

        <!--列表-->
        <el-table :data="complaintList" highlight-current-row v-loading="complaintListLoading"
                  @selection-change="selsChange" border
                  @cell-click="toFind" :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection" width="50" align="center"></el-table-column>
            <el-table-column type="index" label="序号" width="65"></el-table-column>
            <el-table-column prop="on" label="登记编号" width="180" sortable></el-table-column>
            <el-table-column prop="subject" label="涉及主体名称" min-width="200" sortable
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="content" label="登记内容" min-width="200" sortable
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="birth" label="登记日期" width="150" sortable></el-table-column>
            <el-table-column prop="provider" label="提供方" min-width="100" sortable></el-table-column>
            <el-table-column prop="type" label="类型" width="100" :formatter="formatType" sortable>
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
                sels: [],//列表选中列
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
                this.complaintListLoading = true;
                listComplaint(params).then((res) => {
                    this.pagination = res.data.pagination;
                    this.complaintList = res.data.list;
                    this.complaintListLoading = false;
                });
            },
            toFind(row, column) {
                //阻止勾选复选框跳转
                if (column.property !== 'select') {
                    this.$router.push('/wddb/tsldb_temp/zfsl')
                }
            },
            formatType(row, column) {
                return `执法类`
                // if (row.type === 1) {
                //     return `咨询类`
                // } else if (row.type === 2) {
                //     return `投诉类`
                // } else {
                //     return `执法类`
                // }
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
                        this.$alert('删除成功!', '提示', {
                            confirmButtonText: '确定',
                            type: 'success',
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
                    this.complaintListLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    batchRemoveUser(para).then((res) => {
                        this.complaintListLoading = false;
                        //NProgress.done();
                        this.$alert('删除成功!', '提示', {
                            confirmButtonText: '确定',
                            type: 'success',
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
