<!-- 今日到期待办列表 -->
<template>
    <section class="expected">
        <div class="handle_btn">
            <el-button type="primary" size="small" @click="exportToExcel">
                <i class="fa fa-download"></i>
                Excel 导出
            </el-button>
        </div>
        <!-- 数据表格 -->
        <el-table
            highlight-current-row
            :row-class-name="tableRowClassName"
            v-loading="complaintListLoading"
            element-loading-text="拼命加载中"
            border
            style="width: 100%"
            :row-style="{cursor:'pointer'}"
            @sort-change="sortChange"
            :data="taskList"
        >
            <el-table-column prop="index" label="序号" min-width="5%" align="center" :formatter="formatIndex"></el-table-column>
            <el-table-column prop="formtype" label="类型" width="100px" align="center" :formatter="formatType"></el-table-column>
            <el-table-column prop="serialnum" label="表单号" width="150px" align="center">
                <template slot-scope="scope">
                    <span v-html="setSerialnumRed(scope.row)"></span>
                </template>
            </el-table-column>
            <el-table-column prop="rqsttitle" label="工单标题" min-width="17%" header-align="center" show-overflow-tooltip>
                <template slot-scope="scope">
                    <span v-html="setRqsttitleRed(scope.row)"></span>
                </template>
            </el-table-column>
            <el-table-column prop="content" label="登记内容" min-width="20%" header-align="center" show-overflow-tooltip></el-table-column>
            <el-table-column prop="operate" label="当前环节" min-width="10%" align="center"></el-table-column>
            <el-table-column prop="handledeptname" label="处理部门" show-overflow-tooltip min-width="10%" align="center"></el-table-column>
            <el-table-column prop="assigndate" label="交办时间" width="150px" align="center" :formatter="formatDateBegin" sortable="custom">
            </el-table-column>
            <el-table-column prop="sdexpirationdate" label="到期时间" width="150px" :formatter="formatDateEnd" align="center" sortable="custom">
            </el-table-column>
            <el-table-column prop="systemsource" label="系统来源" width="100px" align="center" :formatter="formatSystem"></el-table-column>
            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="toDeal(scope, scope.row)" type="primary">处理</el-button>
                </template>
            </el-table-column>
        </el-table>
        <br />
        <!--分页组件开始-->
        <el-col style="text-align: right;">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-size="pagination.size"
                :total="pagination.total"
                :page-sizes="[10, 20, 30, 40,50,100,200]"
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
    import moment from "moment";
    import { listTasklist } from "../../api/api";

    export default {
        data(){
            return {
                list: [],
                taskList: [], //工单数据
                search: {
                    sortField: "sdexpirationdate",
                    sortOrder: "asc"
                },
                pagination: { //分页组件
                    total: 0,
                    size: 10,
                    page: 1
                },
                complaintListLoading: false //加载中loading
            };
        },
        methods: {
            //把每一行的索引放进row
            tableRowClassName(row, index) {
                row.index = index;
            },
            //排序条件发生变化
            sortChange({column, prop, order}) {
                this.search.sortField = prop;
                this.search.sortOrder = order ? order.replace("ending", "") : null;
                this.findList();
            },
            //序号
            formatIndex(row, column, cellValue) {
                return this.pagination.size * (this.pagination.page - 1) + 1 + row.index;
            },
            //工单类型转换
            formatType(row, column) {
                if ("02" == row.systemsource) { //12345系统
                    if ("02" == row.formtype) {
                        return "消费投诉";
                    } else if ("03" == row.formtype) {
                        return "经济违法举报";
                    } else {
                        return "咨询";
                    }
                } else if ("03" == row.systemsource) { //12315系统
                    return row.bustype;
                }
            },
            //2小时内到期工单表单号变红
            setSerialnumRed(row) {
                let nowDate = new Date().getTime();
                if (nowDate <= row.sdexpirationdate && (row.sdexpirationdate - nowDate <= 2 * 60 * 60 * 1000)) {
                    return '<span style="color:red;">' + row.serialnum + '</span>';
                } else {
                    return row.serialnum;
                }
            },
            //2小时内到期工单标题变红
            setRqsttitleRed(row) {
                let nowDate = new Date().getTime();
                if (nowDate <= row.sdexpirationdate && (row.sdexpirationdate - nowDate <= 2 * 60 * 60 * 1000)) {
                    return '<span style="color:red;">' + row.rqsttitle + '</span>';
                } else {
                    return row.rqsttitle;
                }
            },
            //交办时间格式转换
            formatDateBegin(row, column) {
                if (row.assigndate != null) {
                    return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            },
            //到期时间格式转换
            formatDateEnd(row, column) {
                if (row.sdexpirationdate != null) {
                    return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            },
            //系统来源
            formatSystem(row, column) {
                if ("02" == row.systemsource) {
                    return "12345系统";
                } else if ("03" == row.systemsource) {
                    return "12315系统";
                }
            },
            //每页条数发生变化
            handleSizeChange(size) {
                this.pagination.size = size;
                this.findList();
            },
            //页码发生变化
            handleCurrentChange(currentPage) {
                this.pagination.page = currentPage;
                this.findList();
            },
            findList() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                let nowTime = new Date();
                let expectedBegin = this.$moment(nowTime).format("YYYY-MM-DD HH:mm:ss");
                let expectedEnd = this.$moment(nowTime.getTime() + 12 * 60 * 60 * 1000).format("YYYY-MM-DD HH:mm:ss");
                let params = {
                    page: this.pagination.page,
                    total: this.pagination.total,
                    size: this.pagination.size,
                    user: user,
                    systemsource: "0203",
                    sortOrder: this.search.sortOrder,
                    sortField: this.search.sortField,
                    expectedBegin: expectedBegin,
                    expectedEnd: expectedEnd
                };
                this.complaintListLoading = true;
                listTasklist(params).then(res => {
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.taskList = res.data.records;
                    this.complaintListLoading = false;
                });
            },
            toDeal(column, row) {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid,
                    ismain: row.ismain
                };
                //12345系统
                if (row.systemsource == '02') {
                    if ("0" === row.ismain) {
                        //抄送部门工单处理
                        let url = this.$router.resolve({
                            path: "/listDetail/slbslfpth",
                            query: params
                        });
                        window.open(url.href, "_blank");
                        return;
                    }
                    if ("leaderApr" === row.nextnodeid) {
                        //领导审批
                        let url = this.$router.resolve({
                            path: "/listDetail/ldsp",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("applyDelay" === row.nextnodeid) {
                        //延期申请
                        let url = this.$router.resolve({
                            path: "/listDetail/yqsp",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    }else if ("regionAudit" === row.nextnodeid) {
                        //区局处理
                        let url = this.$router.resolve({
                            path: "/listDetail/qjcl",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("begin" === row.nextnodeid || "withdraw" === row.nextnodeid) {
                        //工单审核
                        let url = this.$router.resolve({
                            path: "/listDetail/gdsh",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase == "") {
                        //经济违法立案处理
                        let url = this.$router.resolve({
                            path: "/wddb/12345db/lacl",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase != "") {
                        //经济违法处理处理反馈
                        let url = this.$router.resolve({
                            path: "/listDetail",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("handleFeedback" === row.nextnodeid || "1" === row.isaccept || "01" === row.formtype) {
                        //处理反馈
                        let resolve = this.$router.resolve({
                            path: "/listDetail",
                            query: {...params}
                        });
                        window.open(resolve.href, "_blank");
                    } else if ("0" === row.isaccept) {
                        //未受理
                        let url = this.$router.resolve({
                            path: "/listDetail/slbslfpth",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    }
                } else if (row.systemsource == '03') { //12315系统
                    if ("0" === row.ismain) {
                        //抄送部门工单处理
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsgdcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                            return;
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbgdcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                            return;
                        }
                    }
                    if ("leaderApr" === row.nextnodeid) {
                        //领导审批
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsldsp",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbldsp",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    } else if ("todispatch" === row.nextnodeid) {
                        //待分流
                        let {href} = this.$router.resolve({
                            path: "/listDetail/12315DealBusiness",
                            query: params
                        });
                        window.open(href, "_blank");
                    } else if ("applyDelay" === row.nextnodeid) {
                        //延期申请
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsyqsp",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbyqsp",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    } else if ("accept" === row.nextnodeid && "02" == row.formtype) {
                        let url = this.$router.resolve({
                            path: "/listDetail/12315tsgdfk",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("regionAudit" === row.nextnodeid) {
                        //区局处理
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsqjcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbqjcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    } else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase == "") {
                        //经济违法立案处理
                        let url = this.$router.resolve({
                            path: "/listDetail/12315jblacl",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase != "") {
                        //经济违法处理反馈
                        let url = this.$router.resolve({
                            path: "/listDetail/12315jbgdfk",
                            query: params
                        });
                        window.open(url.href, "_blank");
                    } else if ("handleFeedback" === row.nextnodeid || "1" === row.isaccept) {
                        //处理反馈
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsgdfk",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbgdfk",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    } else if ("begin" === row.nextnodeid || "withdraw" === row.nextnodeid) {
                        //工单审核
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsgdsh",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbgdsh",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    } else {
                        //未受理
                        if ("02" == row.formtype) {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315tsgdcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        } else {
                            let url = this.$router.resolve({
                                path: "/listDetail/12315jbgdcl",
                                query: params
                            });
                            window.open(url.href, "_blank");
                        }
                    }
                }
            },
            exportToExcel() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                let nowTime = new Date();
                let expectedBegin = this.$moment(nowTime).format("YYYY-MM-DD HH:mm:ss");
                let expectedEnd = this.$moment(nowTime.getTime() + 12 * 60 * 60 * 1000).format("YYYY-MM-DD HH:mm:ss");
                const querystring = require("querystring");
                let params = querystring.stringify({
                    caseguid: null,
                    system: '0203',
                    ename: user.ename,
                    expectedBegin: expectedBegin,
                    expectedEnd: expectedEnd
                });
                if (this.taskList.length == 0) {
                    this.$alert('无数据进行导出', '提示', {confirmButtonText: '确定'});
                } else {
                    window.open('/crcs/businessSearch/excelexport?' + params);
                    this.$alert('已为您导出所有预警工单', '提示', {confirmButtonText: '确定'});
                }
            }
        },
        mounted(){
            //表格内容渲染
            this.findList();
        }
    };
</script>

<style lang="scss">
    .expected {
        .search_tool {
            padding: 10px 15px 0 0;
            background-color: #f2f2f2;
            border-radius: 5px;

            .el-form-item {
                margin-bottom: 10px;
            }

            .el-form-item__content {
                line-height: normal;
            }
        }

        .handle_btn {
            margin: 10px 0;
        }

        .retract {
            font-size: 14px;
            color: #20a0ff;
            cursor: pointer;
        }
    }
</style>