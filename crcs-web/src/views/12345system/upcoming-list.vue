<!--12345待办列表-->
<template>
    <section class="db_12345">
        <!--工具条-->
        <el-form :model="search" label-width="90px" class="search_tool">
            <el-row>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="表单号:">
                        <el-input v-model="search.serialnum" @change="searchChange" placeholder="表单号"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="标题:">
                        <el-input v-model="search.rqsttitle" @change="searchChange" placeholder="标题"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="工单类型:">
                        <el-select
                                v-model="search.formType"
                                clearable
                                @change="searchChange"
                                placeholder="选择工单类型"
                                style="width: 100%"
                        >
                            <el-option label="消费投诉" value="02"></el-option>
                            <el-option label="经济违法举报" value="03"></el-option>
                            <el-option label="咨询" value="01"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="当前环节:">
                        <el-select
                                v-model="search.currentLink"
                                clearable
                                @change="searchChange"
                                placeholder="选择当前环节"
                                style="width: 100%"
                        >
                            <el-option
                                    v-for="item in currentLinkOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="关键词:">
                        <el-input v-model="search.keyword" @change="searchChange" placeholder="关键词"></el-input>
                    </el-form-item>
                </el-col>
                <el-collapse-transition>
                    <div v-if="isRetract">
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="电话:">
                                <el-input v-model="search.tel" @change="searchChange" placeholder="联系电话"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="投诉人:">
                                <el-input v-model="search.complainant" @change="searchChange"
                                          placeholder="投诉人姓名"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="被投诉对象:">
                                <el-input
                                        v-model="search.beComplainant"
                                        @change="searchChange"
                                        placeholder="被投诉对象"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">
                            <el-form-item label="工单号:">
                                <el-input v-model="search.tserialnum" @change="searchChange"
                                          placeholder="工单号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :lg="8" :md="8">
                            <el-form-item label="时间:">
                                <el-date-picker
                                        v-model="search.wasDeal"
                                        type="datetimerange"
                                        placeholder="选择时间范围"
                                        style="width:100%"
                                ></el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :md="8" :lg="8">&nbsp;</el-col>
                    </div>
                </el-collapse-transition>
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
        <div class="handle_btn">
            <el-button type="primary" size="small" @click="exportToExcel">
                <i class="fa fa-download"></i>
                Excel 导出
            </el-button>
            <el-button type="danger" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" @click="handleDel" size="small" icon="delete">删除</el-button>
        </div>
        <!--列表-->
        <el-table
                :data="complaintList"
                highlight-current-row
                :row-class-name="tableRowClassName"
                v-loading="complaintListLoading"
                element-loading-text="拼命加载中"
                @selection-change="selsChange"
                border
                style="width: 100%"
                :row-style="{cursor:'pointer'}"
                @sort-change="sortChange"
        >
            <!-- @cell-click="toFind" -->
            <el-table-column prop="select" type="selection" min-width="3%" align="center"></el-table-column>
            <el-table-column
                    prop="index"
                    label="序号"
                    min-width="4%"
                    align="center"
                    :formatter="formatIndex"
            ></el-table-column>
            <el-table-column
                    prop="formtype"
                    label="业务类型"
                    min-width="7%"
                    align="center"
                    :formatter="formtypes"
            ></el-table-column>
            <el-table-column prop="serialnum" label="表单号" width="130px" align="center">
                <template slot-scope="scope">
                    <span v-html="setSerialnumRed(scope.row)"></span>
                </template>
            </el-table-column>
            <el-table-column prop="tserialnum" label="工单号" width="150px" align="center">
                <template slot-scope="scope">
                    <span v-html="setTserialnumRed(scope.row)"></span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="rqsttitle"
                    label="工单标题"
                    min-width="17%"
                    header-align="center"
                    show-overflow-tooltip
            >
                <template slot-scope="scope">
                    <span v-html="setRqsttitleRed(scope.row)"></span>
                </template>
            </el-table-column>
            <!--登记内容，问题陈述-->
            <el-table-column
                    prop="content"
                    label="登记内容"
                    min-width="18%"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="operate"
                    label="当前环节"
                    min-width="8%"
                    align="center">
            </el-table-column>
<!--            <el-table-column-->
<!--                    prop="handledeptname"-->
<!--                    label="处理部门"-->
<!--                    show-overflow-tooltip-->
<!--                    min-width="10%"-->
<!--                    align="center"-->
<!--            ></el-table-column>-->
            <el-table-column
                    prop="assigndate"
                    label="交办时间"
                    width="140px"
                    align="center"
                    :formatter="formatDate"
                    sortable="custom"
            ></el-table-column>
            <el-table-column
                    prop="sdexpirationdate"
                    label="到期时间"
                    width="140px"
                    :formatter="formatDates"
                    align="center"
                    sortable="custom"
            ></el-table-column>

            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="toFind(scope, scope.row)" type="primary">处理</el-button>
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
        listTasklist,
        removeUser,
        batchRemoveUser,
        deleteTasklist,
        signTasklist,
        findOrgUnitByRegionId
    } from "../../api/api";
    import moment from "moment";
    import {getParam12345,setParam12345,removeParam12345} from "../../utils/auth";

    export default {
        data() {
            return {
                list: [], //列表选中列 caseguid
                idList: [], //列表选中列 id
                isRetract: false,
                search: {
                    wasDeal: "",
                    tel: "",
                    keyword: "",
                    serialnum: "",
                    tserialnum: "",
                    rqsttitle: "",
                    formType: "",
                    complainant: "",
                    beComplainant: "",
                    accordtype: "",
                    labelname: "",
                    esulttimemin: "",
                    sortField: "sdexpirationdate",
                    sortOrder: "asc",
                    currentLink: "",
                    phone:""    //商家电话
                },
                currentLinkOptions: [
                    {
                        value: "begin",
                        label: "待分派"
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
                    },
                    {
                        value: "withdraw",
                        label: "待办收回"
                    }
                ],
                complaintList: [],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1
                },
                complaintListLoading: false,
                unitList: [],
                user: JSON.parse(localStorage.getItem("user")),
            };
        },
        methods: {
            //2小时内到期工单表单号变红
            setSerialnumRed(row) {
                let nowDate = new Date().getTime();
                if (row.sdexpirationdate - nowDate <= 2 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate) {
                    return '<span style="color:red;">' + row.serialnum + '</span>';
                } else {
                    return row.serialnum;
                }
            },
            //2小时内到期工单工单号变红
            setTserialnumRed(row) {
                let nowDate = new Date().getTime();
                if (row.sdexpirationdate - nowDate <= 2 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate) {
                    return '<span style="color:red;">' + row.tserialnum + '</span>';
                } else {
                    return row.tserialnum;
                }
            },
            //2小时内到期工单标题变红
            setRqsttitleRed(row) {
                let nowDate = new Date().getTime();
                if (row.sdexpirationdate - nowDate <= 2 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate) {
                    return '<span style="color:red;">' + row.rqsttitle + '</span>';
                } else {
                    return row.rqsttitle;
                }
            },
            exportToExcel(row) {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                if (
                    this.search.wasDeal[0] !== undefined &&
                    this.search.wasDeal[1] !== undefined
                ) {
                    var begindate = moment(this.search.wasDeal[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    var enddate = moment(this.search.wasDeal[1]).format(
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
                    system: "12345",
                    rqsttitle: this.search.rqsttitle,
                    serialnum: this.search.serialnum,
                    keyword: this.search.keyword,
                    tel: this.search.tel,
                    ename: user.ename,
                    tserialnum: this.search.tserialnum,
                    currentLink: this.search.currentLink,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    formType: this.search.formType,
                    begindate: begindate,
                    enddate: enddate,
                    phone:this.search.phone
                });
                if (this.complaintList.length == 0) {
                    this.$alert('无数据进行导出', '提示', {confirmButtonText: '确定'});
                } else {
                    if (this.list == "" || this.list == null) {
                        window.open('/crcs/businessSearch/excelexport?' + str)
                        //this.$alert('已为您导出所有待办业务', '提示', {confirmButtonText: '确定'});
                    } else {
                        window.open('/crcs/businessSearch/excelexport?' + str)
                        this.$alert('已为您导出所选待办业务', '提示', {confirmButtonText: '确定'});
                    }
                }
            },

            selsChange: function (sels) {
                this.idList = [];
                this.list = [];
                sels.map(item => {
                    this.idList.push(item.id);
                    this.list.push(item.caseguid)
                });
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
                        tserialnum: "",
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
                var begindate = "";
                var enddate = "";
                if (this.search.wasDeal !== "") {
                    if (
                        this.search.wasDeal[0] !== null &&
                        this.search.wasDeal[1] !== null
                    ) {
                        begindate = moment(this.search.wasDeal[0]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                        enddate = moment(this.search.wasDeal[1]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                    }
                }

                let params = {
                    page: this.pagination.page,
                    total: this.pagination.total,
                    size: this.pagination.size,
                    user: user,
                    systemsource: "02",
                    tserialnum: this.search.tserialnum,
                    rqsttitle: this.search.rqsttitle,
                    serialnum: this.search.serialnum,
                    tel: this.search.tel,
                    keyword: this.search.keyword,
                    formType: this.search.formType,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    begindate: begindate,
                    enddate: enddate,
                    currentLink: this.search.currentLink,
                    sortOrder: this.search.sortOrder,
                    sortField: this.search.sortField,
                };
                this.complaintListLoading = true;
                listTasklist(params).then(res => {
                    // console.log(res)
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.complaintList = res.data.records;
                    this.complaintListLoading = false;
                });
            },
            toFind(column, row) {
                var begindate = "";
                var enddate = "";
                if (this.search.wasDeal !== "") {
                    if (
                        this.search.wasDeal[0] !== null &&
                        this.search.wasDeal[1] !== null
                    ) {
                        begindate = moment(this.search.wasDeal[0]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                        enddate = moment(this.search.wasDeal[1]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                    }
                }
                let paramsSearch = {
                    rqsttitle: this.search.rqsttitle,
                    serialnum: this.search.serialnum,
                    formType: this.search.formType,
                    currentLink: this.search.currentLink,
                    keyword: this.search.keyword,
                    tel: this.search.tel,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    tserialnum: this.search.tserialnum,
                    begindate: begindate,
                    enddate: enddate
                }
                setParam12345(paramsSearch);
                //阻止勾选复选框跳转
                //   if (column.property !== "select") {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid,
                    ismain: row.ismain
                };
                // if ("0" === row.issignin && "1" == row.ismain) {
                //     let paras = {
                //         user: JSON.parse(localStorage.getItem("user")),
                //         caseguid: row.caseguid,
                //         nextnodeid: row.nextnodeid,
                //         ismain: row.ismain
                //     };
                //     signTasklist(paras);
                // }
                //判断跳转的操作步骤页面
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
                    //   this.$router.push({ path: "/wddb/12345db/ldsp", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail/ldsp",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else if ("applyDelay" === row.nextnodeid) {
                    //延期申请
                    //   this.$router.push({ path: "/wddb/12345db/yqsp", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail/yqsp",
                        query: params
                    });
                    window.open(url.href, "_blank");
                // } else if ("accept" === row.nextnodeid && "03" === row.formtype) {
                }else if ("regionAudit" === row.nextnodeid) {
                    //区局处理
                    //   this.$router.push({ path: "/wddb/12345db/qjcl", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail/qjcl",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else if ("begin" === row.nextnodeid || "withdraw" === row.nextnodeid) {
                    //工单审核
                    //   this.$router.push({ path: "/wddb/12345db/gdsh", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail/gdsh",
                        query: params
                    });
                    window.open(url.href, "_blank");
                }else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase == "") {
                    //经济违法立案处理
                    //   this.$router.push({ path: "/wddb/12345db/lacl", query: params });
                    let url = this.$router.resolve({
                        path: "/wddb/12345db/lacl",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else if ("1" === row.isaccept && "03" === row.formtype && row.isregistercase !="") {
                    //经济违法处理处理反馈
                    //   this.$router.push({ path: "/wddb/12345db/lacl", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else if (
                    "handleFeedback" === row.nextnodeid ||
                    "1" === row.isaccept ||
                    "01" === row.formtype //01表示通用表单
                ) {
                    //处理反馈
                    //   window.open(this.$router.push({ path: "/wddb/12345db/clfpysth", query: params }))
                    let resolve = this.$router.resolve({
                        path: "/listDetail",
                        query: {...params}
                    });
                    window.open(resolve.href, "_blank");
                } else if ("0" === row.isaccept) {
                    //未受理
                    //   this.$router.push({ path: "/wddb/12345db/slbslfpth", query: params });
                    let url = this.$router.resolve({
                        path: "/listDetail/slbslfpth",
                        query: params
                    });
                    window.open(url.href, "_blank");
                }
                //   }
            },

            //工单类型转换
            formtypes(row, column) {
                if ("02" == row.formtype) {
                    if("0" ==row.ismain){
                        return "投诉(抄送)";
                    }else {
                        return "投诉";
                    }
                } else if ("03" == row.formtype) {
                    if("0" ==row.ismain){
                        return "举报(抄送)";
                    }else {
                        return "举报";
                    }
                } else {
                    if("0" ==row.ismain){
                        return "咨询(抄送)";
                    }else {
                        return "咨询";
                    }
                }
            },
            tableRowClassName(row, index) {
                //把每一行的索引放进row
                row.index = index;
            },
            formatIndex(row, column, cellValue) {
                return this.pagination.size * (this.pagination.page - 1) + 1 + row.index;
            },
            formatDate(row, column) {
                return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
            },
            formatDates(row, column) {
                if (row.sdexpirationdate != null) {
                    return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
                } else {
                    return "";
                }
            },
            //删除
            handleDel() {
                if (!this.idList || this.idList.length === 0) {
                    this.$message({
                        message: "至少勾选一条待办业务！",
                        type: "warning",
                        duration: "1000"
                    });
                    return;
                }
                let user = localStorage.getItem("user");
                let ids = this.idList.toString();
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
                    .catch(() => {
                    });
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
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm("确认删除选中记录吗？", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.complaintListLoading = true;
                        //NProgress.start();
                        let para = {ids: ids};
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
            var paramsSearchJson = getParam12345();
            if (paramsSearchJson != null && paramsSearchJson != "" && paramsSearchJson != "undefined"){
                let paramsSearch = JSON.parse(paramsSearchJson);
                this.search.rqsttitle = paramsSearch.rqsttitle;
                this.search.serialnum = paramsSearch.serialnum;
                this.search.formType = paramsSearch.formType;
                this.search.currentLink = paramsSearch.currentLink;
                this.search.keyword = paramsSearch.keyword;
                this.search.tel = paramsSearch.tel;
                this.search.complainant = paramsSearch.complainant;
                this.search.beComplainant = paramsSearch.beComplainant;
                this.search.tserialnum = paramsSearch.tserialnum;
                var begindate =  paramsSearch.begindate;
                var enddate =  paramsSearch.enddate;
                if (begindate != null && begindate != "" && begindate != "undefined" && enddate != null && enddate != "" && enddate != "undefined"){
                    this.search.wasDeal = [begindate,enddate];
                }
                removeParam12345();
            }
            this.findList();
            this.findUnitList();
        },
    };
</script>

<style lang="scss">
    .db_12345 {
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
