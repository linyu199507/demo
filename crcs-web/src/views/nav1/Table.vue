<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="search">
                <el-form-item>
                    <el-col>标题:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.rqsttitle" @change="change(search.rqsttitle)" placeholder="标题"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-col>表单号:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.serialnum" @change="change(search.serialnum)" placeholder="表单号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-col>工单号:</el-col>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="search.tserialnum" @change="change(search.tserialnum)" placeholder="工单号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="findList">查询</el-button>
                </el-form-item>
                <el-button style="float: right;" type="danger" @click="handleDel">删除</el-button>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="complaintList" highlight-current-row v-loading="complaintListLoading"
                  @selection-change="selsChange" border  style="width: 100%"
                  @cell-click="toFind" :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection"  min-width="5%" align="center"></el-table-column>
            <el-table-column type="index" label="序号"  min-width="5%" align="center"></el-table-column>
            <el-table-column prop="focus" label="工单分类"  min-width="7%" :formatter="formatType" sortable align="center"></el-table-column>
            <el-table-column prop="serialnum" label="表单号"  min-width="12%" sortable align="center" ></el-table-column>
            <el-table-column prop="tserialnum" label="工单号"  min-width="12%" sortable align="center"></el-table-column>
<!--            <el-table-column prop="subject" label="涉及主体名称" min-width="180" sortable-->
<!--                             show-overflow-tooltip></el-table-column>-->
            <el-table-column prop="rqsttitle" label="工单标题"  min-width="19%" sortable
                             header-align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="accordtype" label="业务分类"  min-width="10%" :formatter="formatBusi" sortable align="center"></el-table-column>
            <el-table-column prop="labelname" label="政务标签"  min-width="10%" :formatter="formatLabel" sortable align="center"></el-table-column>
            <el-table-column prop="assigndate" label="交办时间"  min-width="10%" sortable align="center" :formatter="formatDate"></el-table-column>
            <el-table-column prop="resulttimemin" label="到期时间"  min-width="10%" sortable align="center"></el-table-column>
<!--            <el-table-column prop="provider" label="提供方" width="100" sortable></el-table-column>-->
<!--            <el-table-column prop="type" label="类型" width="100" :formatter="formatType" sortable></el-table-column>-->
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
    import {listTasklist, removeUser, batchRemoveUser,deleteTasklist} from '../../api/api';
    import moment from 'moment'

    export default {
        data() {
            return {
                search: {
                    focus: '',
                    serialnum: '',
                    tserialnum: '',
                    rqsttitle: '',
                    accordtype: '',
                    labelname: '',
                    assigndate: '',
                    esulttimemin: '',
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
            change(value){
                this.findList();
                console.log(value);
            },
            findList() {
                // let params = {
                //     pagination: this.pagination
                // };
                var user = localStorage.getItem('user');
                user = JSON.parse(user);
                let params = {
                    page: this.pagination.page,
                    total: this.pagination.total,
                    size: this.pagination.size,
                    user:user,
                    systemsource:'1',
                    tserialnum:this.search.tserialnum,
                    rqsttitle: this.search.rqsttitle,
                    serialnum: this.search.serialnum
                };
                this.complaintListLoading = true;
                listTasklist(params).then((res) => {
                    // this.pagination = res.data.pagination;
                    // this.complaintList = res.data.list;
                    // this.complaintListLoading = false;
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.complaintList = res.data.records;
                    this.complaintListLoading = false;

                });
            },
            toFind(row, column) {
                //阻止勾选复选框跳转
                // console.log(column.property)
                // if (column.property !== 'select') {
                //     this.$router.push('/wddb/tsldb/gdsh')
                // }
                //阻止勾选复选框跳转
                if (column.property !== 'select') {
                    let para = {
                        id: row.id,
                        caseguid: row.caseguid
                    };
                    this.$router.push({path:'/wddb/12345db/gdsh', query: para})
                }
            },
            formatDate(row, column) {
                // console.log(row)
                // row.createdate
                return moment(row.assigndate).format('YYYY-MM-DD')
            },
            formatType(row, column) {
                return `普通`
                // if (row.type === 1) {
                //     return `咨询类`
                // } else if (row.type === 2) {
                //     return `投诉类`
                // } else {
                //     return `执法类`
                // }
            },
            formatBusi(row, column){
                return '消费投诉业务'
            },
            formatLabel(row, column){
                return '乱收费'
            },
            //删除
            handleDel: function () {
                let user = localStorage.getItem('user');
                let ids = this.sels.map(item=>item.id).toString();
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.complaintListLoading = true;
                    //NProgress.start();
                    let para = {
                        ids: ids,
                        user: JSON.parse(user)
                    };
                    deleteTasklist(para).then((res) => {
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
