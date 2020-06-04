<template>
    <div>
        <el-row type="flex" class="row-bg" justify="space-between">
            <el-form label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="登记编号：">
                            <el-input
                                    @change="getSearchList"
                                    placeholder="请输入登记编号"
                                    v-model="searchForm.number"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="工单类型：">
                            <!--<el-input
                                    placeholder="请选择工单类型"
                                    @change="getSearchList"
                                    v-model="searchForm.focus"
                                    style="width:200px"
                            ></el-input>-->
                            <el-select v-model="searchForm.bustype"
                                       placeholder="请选择工单类型"
                                       style="width: 200px"
                                       @change="getSearchList">
                                <el-option label="举报" value="举报"></el-option>
                                <el-option label="投诉" value="投诉"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="当前环节：">
                            <el-select
                                    v-model="searchForm.currentLink"
                                    clearable
                                    placeholder="请选择"
                                    style="width: 200px"
                                    @change="getSearchList"
                            >
                                <el-option
                                        v-for="item in currentLinkOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                ></el-option>
                            </el-select>
                            <!-- <el-input placeholder="请输入当前环节" @change="getSearchList" v-model="searchForm.currentLink" style="width:200px"></el-input> -->
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item label="登记时间：">
                            <el-date-picker
                                    v-model="searchForm.registerDate"
                                    type="datetimerange"
                                    :picker-options="pickerOptions"
                                    placeholder="选择时间范围"
                                    align="right"
                                    style="width:200px"
                                    @change="getSearchList"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="12" :lg="8">
                        <el-form-item>
                            <el-button type="primary" @click="getSearchList">查询</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-row>

        <el-table
                :data="listData"
                highlight-current-row
                :row-class-name="tableRowClassName"
                v-loading="complaintListLoading"
                element-loading-text="拼命加载中"
                @selection-change="selsChange"
                border
                style="width: 100%"
                :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="index" label="序号" min-width="3%" align="center"
                             :formatter="formatIndex"></el-table-column>

            <el-table-column prop="serialnum" label="登记编号" min-width="15%" align="center"></el-table-column>
            <el-table-column prop="bustype" label="类型" min-width="5%" align="center"></el-table-column>
            <el-table-column prop="operate" label="当前环节" min-width="7%" align="center"></el-table-column>
            <el-table-column
                    prop="assigndate"
                    :formatter="formatDate"
                    label="登记时间"
                    min-width="10%"
                    align="center"
                    sortable
            ></el-table-column>
            <el-table-column
                    prop="complainant"
                    label="提供方"
                    min-width="6%"
                    align="center"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="complainanted"
                    label="涉及主体"
                    min-width="6%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="registrant"
                    label="登记人"
                    min-width="5%"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="content"
                    label="登记内容"
                    min-width="12%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="handledeptname"
                    label="处理部门"
                    min-width="8%"
                    align="center"
                    show-overflow-tooltip></el-table-column>
            <el-table-column
                    prop="sdexpirationdate"
                    label="到期时间"
                    min-width="10%"
                    :formatter="formatDates"
                    align="center"
                    sortable
            ></el-table-column>
            <el-table-column label="操作" min-width="4%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="gotoDeal(scope,scope.row)" type="primary">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 30, 50, 100]"
                :page-size="size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
        ></el-pagination>
    </div>
</template>
<script>
    import {getFinishListData} from "../../api/api";
    import moment from "moment";

    export default {
        data() {
            return {
                listData: [],
                complaintListLoading: false,
                systemsource: "03",
                currentPage: 1,
                size: 10,
                total: 0,
                currentLinkOptions: [
                    {
                        value: "begin",
                        label: "开始"
                    },
                    {
                        value: "accept",
                        label: "受理"
                    },
                    {
                        value: "todispatch",
                        label: "待分流"
                    },
                    {
                        value: "noAccept",
                        label: "不受理"
                    },
                    {
                        value: "registerCase",
                        label: "立案"
                    },
                    {
                        value: "noRegisterCase",
                        label: "不予立案"
                    },
                    {
                        value: "leaderApr",
                        label: "领导审核"
                    },
                    {
                        value: "moveToLowerOrg",
                        label: "分派下级机关"
                    },
                    {
                        value: "moveToLowerDept",
                        label: "分派其他部门"
                    },
                    {
                        value: "moveToLowerPersonnel",
                        label: "分派人员"
                    },
                    {
                        value: "regionAudit",
                        label: "区局处理"
                    },
                    {
                        value: "handleFeedback",
                        label: "处理反馈"
                    },
                    {
                        value: "end",
                        label: "结束"
                    },
                    {
                        value: "backForward",
                        label: "退回"
                    }
                ],
                searchForm: {
                    number: "",
                    registerDate: "",
                    currentLink: ""
                },
                pickerOptions: {
                    shortcuts: [
                        {
                            text: "最近一周",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                                picker.$emit("pick", [start, end]);
                            }
                        },
                        {
                            text: "最近一个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                                picker.$emit("pick", [start, end]);
                            }
                        },
                        {
                            text: "最近三个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                                picker.$emit("pick", [start, end]);
                            }
                        }
                    ]
                }
            };
        },
        mounted() {
            this.getsldjList();
        },

        methods: {
            handleEdit(index, row) {
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            gotoDeal(scope, row) {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid,
                    systemsource: row.systemsource
                }
                let url = this.$router.resolve({
                    path: "/tjfx/zhcx/wbjlcx/12315lists/ywjlxq",
                    query: params
                });
                window.open(url.href,"_blank")
                // this.$router.push({path: '/tjfx/zhcx/wbjlcx/12315lists/ywjlxq', query: params})
            },
            getSearchList() {
                this.getsldjList();
            },
            handleSizeChange(val) {
                this.size = val;
                this.getsldjList();
            },
            formatIndex(row, column, cellValue) {
                return this.size * (this.currentPage - 1) + 1 + row.index;
            },
            tableRowClassName(row, index) {
                //把每一行的索引放进row
                row.index = index
            },
            formatDate(row, column) {
                return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getsldjList();
            },
            getlistData() {
            },
            getsldjList() {
                let user = JSON.parse(localStorage.getItem("user"));
                if (
                    this.searchForm.registerDate[0] !== undefined &&
                    this.searchForm.registerDate[1] !== undefined
                ) {
                    var begindate = moment(this.searchForm.registerDate[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    var enddate = moment(this.searchForm.registerDate[1]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                }
                let params = {
                    systemsource: this.systemsource,
                    page: this.currentPage,
                    total: this.total,
                    size: this.size,
                    user: user,
                    serialnum: this.searchForm.number,
                    begindate: begindate,
                    enddate: enddate,
                    currentLink: this.searchForm.currentLink,
                    bustype: this.searchForm.bustype
                };
                getFinishListData(params).then(res => {
                    if (res.code === 0) {
                        console.log(res);
                        this.listData = res.data.records;
                        this.currentPage = res.data.current;
                        this.current = res.data.current;
                        this.size = res.data.size;
                        this.total = res.data.total;
                    }
                });
            },
            formatDates(row, column) {
                if (row.sdexpirationdate != null) {
                    return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            }
        }
    };
</script>
<style lang="scss" scoped>
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
        margin-bottom: 20px;
    }

    .el-input__inner {
        width: 200px !important;
    }

    .el-pagination {
        white-space: nowrap;
        padding: 2px 5px;
        color: #48576a;
        text-align: right;
        margin-top: 20px;
    }
</style>
