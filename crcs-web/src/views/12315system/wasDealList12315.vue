<!--12315待办列表-->
<template>
    <section class="db_12315">
        <!--工具条-->
        <el-form :model="search" label-width="90px" class="search_tool">
            <el-row>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                    <el-form-item label="登记编号:">
                        <el-input v-model="search.serialnum" @change="searchChange" placeholder="登记编号"></el-input>
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
                            <el-option label="投诉" value="02"></el-option>
                            <el-option label="举报" value="03"></el-option>
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
                                <el-input v-model="search.beComplainant" @change="searchChange"
                                          placeholder="被投诉对象"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="12" :lg="8" :md="8">
                            <el-form-item label="时间:">
                                <el-date-picker
                                        v-model="search.assigndate"
                                        type="datetimerange"
                                        placeholder="选择时间范围"
                                        style="width:100%"
                                ></el-date-picker>
                            </el-form-item>
                        </el-col>
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
                    min-width="5%"
                    align="center"
                    :formatter="formatIndex"
            ></el-table-column>
            <el-table-column
                    prop="serialnum"
                    label="登记编号"
                    min-width="17%"
                    align="center"
                    header-align="center"
                    show-overflow-tooltip
            >
                <template scope="scope">
                    <div>
                        <span @click="toFind(scope, scope.row)" v-html="setSerialnumRed(scope.row)"></span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="bustype" label="类型" min-width="7%" align="center" :formatter="formtypes"></el-table-column>
            <el-table-column prop="operate" label="当前环节" min-width="8%" align="center"></el-table-column>
            <el-table-column
                    prop="assigndate"
                    label="登记时间"
                    width="140px"
                    align="center"
                    :formatter="formatDate"
                    sortable
            ></el-table-column>
            <el-table-column prop="complainant" label="提供方" min-width="6%" align="center"></el-table-column>
            <el-table-column
                    prop="complainanted"
                    label="涉及主体"
                    min-width="12%"
                    align="center"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <!-- <el-table-column prop="regdeptname" label="登记部门" min-width="8%" align="center"
            show-overflow-tooltip></el-table-column>-->
            <el-table-column prop="registrant" label="登记人" min-width="6%" align="center"></el-table-column>
            <el-table-column
                    prop="content"
                    label="登记内容"
                    min-width="18%"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
<!--            <el-table-column-->
<!--                    prop="handledeptname"-->
<!--                    label="处理部门"-->
<!--                    show-overflow-tooltip-->
<!--                    min-width="8%"-->
<!--                    align="center"-->
<!--            ></el-table-column>-->
            <el-table-column
                    prop="sdexpirationdate"
                    label="到期时间"
                    width="140px"
                    :formatter="formatDates"
                    align="center"
                    sortable
            ></el-table-column>
            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="gotoDeal(scope,scope.row)" type="primary">处理</el-button>
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
    import {listTasklist, signTasklist,deleteTasklist} from "../../api/api";
    import moment from "moment";
    import {getParam12315,setParam12315,removeParam12315} from "../../utils/auth";

    export default {
        data() {
            return {
                isRetract: false,
                search: {
                    tel: "",
                    keyword: "",
                    focus: "",
                    serialnum: "",
                    tserialnum: "",
                    complainanted: "",
                    accordtype: "",
                    labelname: "",
                    assigndate: "",
                    esulttimemin: "",
                    phone:"",
                    formType:"",
                    complainant:"",
                    beComplainant:"",
                    rqsttitle:"",
                    currentLink:""
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
                exportLoading: false,
                sels: [], //列表选中列
                list: [], //存储选中的数据对应的caseguid
                idList: [], //存储选中的数据对应的id
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
            exportToExcel(row) {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                if (
                    this.search.assigndate[0] !== undefined &&
                    this.search.assigndate[1] !== undefined
                ) {
                    var begindate = moment(this.search.assigndate[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    var enddate = moment(this.search.assigndate[1]).format(
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
                    system: "12315",
                    ename: user.ename,
                    serialnum: this.search.serialnum,
                    currentLink: this.search.currentLink,
                    formType: this.search.formType,
                    keyword: this.search.keyword,
                    tel: this.search.tel,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    begindate: begindate,
                    enddate: enddate,
                    phone:this.search.phone
                });
                if (this.complaintList.length == 0) {
                    this.$alert("无数据进行导出", "提示", {confirmButtonText: "确定"});
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
                // }else{
                //     this.$alert('请勾选待办业务进行导出', '提示', {
                //         confirmButtonText: '确定',
                //     });
                // }
            },

            tableRowClassName(row, index) {
                //把每一行的索引放进row
                row.index = index;
            },
            formatIndex(row, column, cellValue) {
                return this.pagination.size * (this.pagination.page - 1) + 1 + row.index;
            },
            //增加抄送单标识
            formtypes(row, column) {
                if ("02" == row.formtype) {
                    if("0" ==row.ismain){
                        return "投诉(抄送)";
                    }else {
                        return "投诉";
                    }
                } else {
                    if("0" ==row.ismain){
                        return "违法(抄送)";
                    }else {
                        return "举报";
                    }
                }
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
            toFind(column, row) {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid
                };
                let url = this.$router.resolve({
                    path: "/listDetail/12315detail",
                    query: params
                });
                window.open(url.href, "_blank");
            },
            gotoDeal(value, row) {
                var begindate = "";
                var enddate = "";
                if (this.search.assigndate !== "") {
                    if (
                        this.search.assigndate[0] !== null &&
                        this.search.assigndate[1] !== null
                    ) {
                        begindate = moment(this.search.assigndate[0]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                        enddate = moment(this.search.assigndate[1]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                    }
                }
                let paramsSearch = {
                    serialnum : this.search.serialnum,
                    rqsttitle : this.search.rqsttitle,
                    tel : this.search.tel,
                    keyword : this.search.keyword,
                    formType : this.search.formType,
                    complainant : this.search.complainant,
                    beComplainant : this.search.beComplainant,
                    currentLink : this.search.currentLink,
                    begindate : begindate,
                    enddate : enddate
                }
                setParam12315(paramsSearch);
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
                    // if ("02" == row.formtype) {
                    //     let url = this.$router.resolve({
                    //         path: "/listDetail/12315tsgdfk",
                    //         query: params
                    //     });
                    //     window.open(url.href, "_blank");
                    // } else {
                    //     //经济违法立案处理
                    //     let url = this.$router.resolve({
                    //         path: "/listDetail/12315jblacl",
                    //         query: params
                    //     });
                    //     window.open(url.href, "_blank");
                    // }
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
            },
            selsChange: function (sels) {
                this.idList = [];
                this.list=[];
                sels.map(item => {
                    this.idList.push(item.id);
                    this.list.push(item.caseguid)
                });
            },
            //排序条件发生变化
            sortChange({column, prop, order}) {
                this.search.sortField = prop;
                this.search.sortOrder = order ? order.replace("ending", "") : null;
                this.findList();
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
                        tel: "",
                        keyword: "",
                        serialnum: "",
                        tserialnum: "",
                        formType: "",
                        complainant: "",
                        beComplainant: "",
                        accordtype: "",
                        labelname: "",
                        assigndate: "",
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
                if (this.search.assigndate !== "") {
                    if (
                        this.search.assigndate[0] !== null &&
                        this.search.assigndate[1] !== null
                    ) {
                        begindate = moment(this.search.assigndate[0]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                        enddate = moment(this.search.assigndate[1]).format(
                            "YYYY-MM-DD HH:mm:ss"
                        );
                    }
                }
                let params = {
                    page: this.pagination.page,
                    total: this.pagination.total,
                    size: this.pagination.size,
                    user: user,
                    systemsource: "03",
                    tserialnum: this.search.tserialnum,
                    serialnum: this.search.serialnum,
                    tel: this.search.tel,
                    keyword: this.search.keyword,
                    formType: this.search.formType,
                    complainant: this.search.complainant,
                    beComplainant: this.search.beComplainant,
                    begindate: begindate,
                    enddate: enddate,
                    sortOrder: this.search.sortOrder,
                    sortField: this.search.sortField,
                    currentLink: this.search.currentLink,
                    rqsttitle:this.search.rqsttitle,
                };
                this.complaintListLoading = true;
                listTasklist(params).then(res => {
                    this.pagination.page = res.data.current;
                    this.pagination.total = res.data.total;
                    this.pagination.size = res.data.size;
                    this.complaintList = res.data.records;
                    this.complaintListLoading = false;
                });
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
        },

        mounted() {
            var paramsSearchJson = getParam12315();
            if (paramsSearchJson != null && paramsSearchJson != "" && paramsSearchJson != "undefined"){
                let paramsSearch = JSON.parse(paramsSearchJson);
                this.search.serialnum = paramsSearch.serialnum;
                this.search.rqsttitle = paramsSearch.rqsttitle;
                this.search.tel = paramsSearch.tel;
                this.search.keyword = paramsSearch.keyword;
                this.search.formType = paramsSearch.formType;
                this.search.complainant = paramsSearch.complainant;
                this.search.beComplainant = paramsSearch.beComplainant;
                this.search.currentLink = paramsSearch.currentLink;
                var begindate = paramsSearch.begindate;
                var enddate = paramsSearch.enddate;
                if (begindate != null && begindate != "" && begindate != "undefined" && enddate != null && enddate != "" && enddate != "undefined"){
                    this.search.assigndate = [begindate,enddate];
                }
                removeParam12315();
            }
            this.findList();
        }
    };
</script>

<style lang="scss">
    .db_12315 {
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
