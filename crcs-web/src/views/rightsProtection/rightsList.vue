<template>
    <section class="sldej">
        <!--工具条-->
        <el-form :model="search" label-width="90px" class="search_tool">
            <el-row>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="标题:">
                        <el-input v-model="search.rqsttitle" @change="searchChange" placeholder="标题"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                  <el-form-item label="工单号:">
                    <el-input v-model="search.serialnum" @change="searchChange" placeholder="工单号"></el-input>
                  </el-form-item>
                </el-col>
                <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">-->
                    <!--<el-form-item label="工单类型:">-->
                        <!--<el-select-->
                                <!--v-model="search.formType"-->
                                <!--clearable-->
                                <!--@change="searchChange"-->
                                <!--placeholder="选择工单类型"-->
                                <!--style="width: 100%"-->
                        <!--&gt;-->
                            <!--<el-option label="消费投诉" value="02"></el-option>-->
                            <!--<el-option label="经济违法举报" value="03"></el-option>-->
                            <!--<el-option label="咨询" value="01"></el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">-->
                    <!--<el-form-item label="当前环节:">-->
                        <!--<el-select-->
                                <!--v-model="search.currentLink"-->
                                <!--clearable-->
                                <!--@change="searchChange"-->
                                <!--placeholder="选择当前环节"-->
                                <!--style="width: 100%"-->
                        <!--&gt;-->
                            <!--<el-option-->
                                    <!--v-for="item in currentLinkOptions"-->
                                    <!--:key="item.value"-->
                                    <!--:label="item.label"-->
                                    <!--:value="item.value"-->
                            <!--&gt;</el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">-->
                    <!--<el-form-item label="关键词:">-->
                        <!--<el-input v-model="search.keyword" @change="searchChange" placeholder="关键词"></el-input>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-collapse-transition>-->
                    <!--<div v-if="isRetract">-->
                        <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">-->
                            <!--<el-form-item label="投诉人:">-->
                                <!--<el-input v-model="search.complainant" @change="searchChange"-->
                                          <!--placeholder="投诉人姓名"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col :xs="24" :sm="12" :md="8" :lg="8">-->
                            <!--<el-form-item label="被投诉对象:">-->
                                <!--<el-input v-model="search.beComplainant" @change="searchChange"-->
                                          <!--placeholder="被投诉对象"></el-input>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                        <!--<el-col :xs="24" :sm="12" :lg="8" :md="8">-->
                            <!--<el-form-item label="时间:">-->
                                <!--<el-date-picker-->
                                        <!--v-model="search.wasDeal"-->
                                        <!--type="datetimerange"-->
                                        <!--placeholder="选择时间范围"-->
                                        <!--style="width:100%"-->
                                <!--&gt;</el-date-picker>-->
                            <!--</el-form-item>-->
                        <!--</el-col>-->
                    <!--</div>-->
                <!--</el-collapse-transition>-->
                <el-col :xs="24" :sm="12" :lg="8" :md="8">
                    <el-form-item>
                        <el-button type="primary" @click="findList">查询</el-button>
                        <el-button @click="resetForm">重置</el-button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="retract" @click="isRetract=!isRetract">
              <template v-if="isRetract">
                收起&nbsp;
                <i class="el-icon-arrow-up"></i>
              </template>
              <template v-else>
                展开&nbsp;
                <i class="el-icon-arrow-down"></i>
              </template>
            </span>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <!--<div class="handle_btn">-->
            <!--<el-button type="primary" size="small" @click="exportToExcel">-->
                <!--<i class="fa fa-download"></i>-->
                <!--Excel 导出-->
            <!--</el-button>-->
            <!--<el-button type="danger" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" @click="batchRemove" size="small" icon="delete">删除</el-button>-->
        <!--</div>-->
        <!--列表-->
        <el-table
                :data="complaintList"
                highlight-current-row
                v-loading="complaintListLoading"
                @selection-change="selsChange"
                border
                style="width: 100%"
                :row-style="{cursor:'pointer'}"
                @sort-change="sortChange"
                :row-class-name="tableRowClassName"
        >
            <!-- @cell-click="toFind" -->

            <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>
            <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
            <el-table-column
                    prop="serialnum"
                    label="表单号"
                    min-width="7%"
                    align="center"
            ></el-table-column>
            <el-table-column prop="rqsttitle" label="标题" min-width="17%" align="center">
                <!--<template slot-scope="scope">-->
                    <!--<span v-html="setSerialnumRed(scope.row)"></span>-->
                <!--</template>-->
            </el-table-column>
            <el-table-column prop="region" label="所属辖区" min-width="15%" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column prop="enterprise" label="经营主体名称" min-width="12%" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column
                    prop="rqstcontent"
                    label="问题描述"
                    min-width="18%"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column prop="processing" label="当前环节" min-width="8%" align="center">
                <template slot-scope="scope">
                <span v-html="setSerialnumRed(scope.row)"></span>
                </template>
            </el-table-column>
            <el-table-column prop="registrationdate" label="登记时间" width="140px" align="center" :formatter="formatDate" sortable="custom"></el-table-column>
            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="toFind(scope, scope.row)" type="primary">查看</el-button>
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
    import {
        listRights,
        removeUser,
        batchRemoveUser,
        deleteTasklist,
        signTasklist,
        findOrgUnitByRegionId
    } from "../../api/api";
    import moment from "moment/moment";
    import {getParamDb,setParamDb,removeParamDb} from "../../utils/auth";

    export default {
        data() {
            return {
                isRetract: false,
                begindate: null,
                enddate: null,
                ename: "",
                search: {
                    currentLink: "",
                    focus: "",
                    tel: "",
                    wasDeal: "",
                    keyword: "",
                    serialnum: "",
                    rqsttitle: "",
                    accordtype: "",
                    labelname: "",
                    assigndate: "",
                    esulttimemin: "",
                    sortField: "assigndate",
                    sortOrder: "desc",
                    formType:"",
                    complainant:"",
                    beComplainant:""
                },
                complaintList: [],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1
                },
                complaintListLoading: false,
                list: [], //列表选中列，业务caseguid
                idList: [], //列表选中列 id
                unitList: [],
                currentLinkOptions: [
                    {
                        value: "begin",
                        label: "待分派"
                    },
                    {
                        value: "staging",
                        label: "暂存"
                    },
                    {
                        value: "accept",
                        label: "受理"
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
                user: JSON.parse(localStorage.getItem("user")),
            };
        },
        methods: {



                tableRowClassName(item) {
                    // console.log(item)
                    // if(item.processing === null ){
                        // item.tserialnum = item.tserialnum + "(抄送)"
                        // return '处理中';
                        // item.processing === '处理中'
                    // }else{
                        // return '已处理';
                        // item.processing === '已处理'
                    // }
                },

            setSerialnumRed(row) {
                if (row.processing === null ) {
                    return '处理中' ;
                } else {
                    return '已处理';
                }
            },

            exportToExcel(row) {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                if (
                    this.search.wasDeal[0] !== undefined &&
                    this.search.wasDeal[1] !== undefined
                ) {
                    this.begindate = moment(this.search.wasDeal[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    this.enddate = moment(this.search.wasDeal[1]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                }
                this.list = Array.from(new Set(this.list));
                const querystring = require("querystring");
                if (this.list.length == 0) {
                    this.list = null;
                }
                let str = querystring.stringify({
                    caseguid: this.list,
                    system: "local",
                    rqsttitle: this.search.rqsttitle,
                    keyword: this.search.keyword,
                    tel: this.search.tel,
                    formType: this.search.formType,
                    currentLink: this.search.currentLink,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    ename: user.ename,
                    begindate: this.begindate,
                    enddate: this.enddate
                });
                if (this.complaintList.length == 0) {
                    this.$message({
                        message: "至少勾选一条待办业务！",
                        type: "warning",
                        duration: "1000"
                    });
                    return;
                } else {
                    if (this.list == "" || this.list == null) {
                        window.open("/crcs/businessSearch/excelexport?" + str);
                        /*this.$alert("已为您导出所有待办业务", "提示", {
                            confirmButtonText: "确定"
                        });*/
                    } else {
                        window.open("/crcs/businessSearch/excelexport?" + str);
                        this.$alert("已为您导出所选待办业务", "提示", {
                            confirmButtonText: "确定"
                        });
                    }
                }
            },

            handleCurrentChange(currentPage) {
                this.pagination.page = currentPage;
                this.findList();
            },
            handleSizeChange(size) {
                this.pagination.size = size;
                this.findList();
            },
            searchChange() {
                this.findList();
            },
            resetForm() {
                this.search = {
                    ...this.search,
                    ...{
                        wasDeal: "",
                        tel: "",
                        keyword: "",
                        serialnum: "",
                        rqsttitle: "",
                        formType: "",
                        complainant: "",
                        beComplainant: "",
                        accordtype: "",
                        labelname: "",
                        esulttimemin: "",
                        currentLink: ""
                    }
                };
                this.findList();
            },
            findList() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);

                // if (
                //     this.search.wasDeal[0] !== undefined &&
                //     this.search.wasDeal[1] !== undefined
                // ) {
                //     this.begindate = moment(this.search.wasDeal[0]).format(
                //         "YYYY-MM-DD HH:mm:ss"
                //     );
                //     this.enddate = moment(this.search.wasDeal[1]).format(
                //         "YYYY-MM-DD HH:mm:ss"
                //     );
                // }

                // debugger
                console.log(this.search.serialnum)
                console.log(this.search.rqsttitle)

                let params = {
                    curPage: this.pagination.page,
                    pageSize: this.pagination.size,
                    orderField: 'registrationdate',
                    likeParamMap:{
                        serialnum: this.search.serialnum,
                        rqsttitle: this.search.rqsttitle,
                    }
                };
                console.log(params)
                this.complaintListLoading = true;
                listRights(params).then(res => {
                    // console.log(res)
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.complaintList = res.data.records;
                    // console.log(res.data.records)
                    this.complaintListLoading = false;
                });
            },
            toFind(column, row) {
                if (
                    this.search.wasDeal[0] !== undefined &&
                    this.search.wasDeal[1] !== undefined
                ) {
                    this.begindate = moment(this.search.wasDeal[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    this.enddate = moment(this.search.wasDeal[1]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                }
                let paramsSearch = {
                    rqsttitle: this.search.rqsttitle,
                    formType: this.search.formType,
                    currentLink: this.search.currentLink,
                    keyword: this.search.keyword,
                    tel: this.search.tel,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    begindate: this.begindate,
                    enddate: this.enddate
                }
                setParamDb(paramsSearch);
                //阻止勾选复选框跳转
                // if (column.property !== "select") {
                let para = {
                    id: row.id,
                    caseguid: row.caseguid,
                    type: 'right',

                };

                    //跳转详情页
                    let url = this.$router.resolve({
                        path: "/tjfx/zhcx/wqfwz/gdxq",
                        query: para
                    });
                    window.open(url.href, "_blank");
                // this.$router.push({name:'rightsDetail', query:{id:row.id}});
            },
            //工单类型转换
            formtypes(row, column) {
                // if ("02" == row.formtype) {
                //     return "投诉";
                // } else if ("03" == row.formtype) {
                //     return "举报";
                // } else {
                //     return "咨询";
                // }
            },
            //时间转换
            formatDate(row, column) {
                return moment(row.registrationdate).format("YYYY-MM-DD HH:mm:ss");
            },
            //时间转换
            formatDates(row, column) {
                if (row.sdexpirationdate != null) {
                    return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            },

            selsChange: function (sels) {
                this.list = [];
                sels.map(item => {
                    this.list.push(item.caseguid);
                    this.idList.push(item.id)
                });
            },

            //批量删除
            batchRemove: function () {
                if (!this.idList || this.idList.length === 0) {
                    this.$message({
                        message: "至少勾选一条待办业务！",
                        type: "warning",
                        duration: "1000"
                    });
                    return;
                }
                let ids = this.idList.toString();
                let user = localStorage.getItem("user");
                this.$confirm("确认删除选中记录吗？", "提示", {
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
                    .catch(() => {
                    });
            },
            findUnitList() {
                //获取下级单位列表，若没有下级单位，则隐藏删除按钮
                let user = JSON.parse(localStorage.getItem("user"));
                findOrgUnitByRegionId({regionId: user.regionID}).then(res => {
                    if (res.data.data) {
                        this.unitList = res.data.data;
                    }
                });
            },
            //排序条件发生变化
            sortChange({column, prop, order}) {
                this.search.sortField = prop;
                this.search.sortOrder = order ? order.replace("ending", "") : null;
                this.findList();
            }
        },

        mounted() {
            var paramsSearchJson = getParamDb();
            if (paramsSearchJson != null && paramsSearchJson != "" && paramsSearchJson != "undefined") {
                let paramsSearch = JSON.parse(paramsSearchJson);
                this.search.rqsttitle = paramsSearch.rqsttitle;
                this.search.formType = paramsSearch.formType;
                this.search.currentLink = paramsSearch.currentLink;
                this.search.keyword = paramsSearch.keyword;
                this.search.tel = paramsSearch.tel;
                this.search.complainant = paramsSearch.complainant;
                this.search.beComplainant = paramsSearch.beComplainant;
                var begindate = paramsSearch.begindate;
                var enddate = paramsSearch.enddate;
                if (begindate != null && begindate != "" && begindate != "undefined" && enddate != null && enddate != "" && enddate != "undefined") {
                    this.begindate = begindate;
                    this.enddate = enddate;
                    this.search.wasDeal = [begindate,enddate];
                }
                removeParamDb();
            }
            this.findList();
            this.findUnitList();
        }
    };
</script>

<style lang="scss">

    .el-table .coloer_change {
        background: oldlace;
    }

    .sldej {
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
