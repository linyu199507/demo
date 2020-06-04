<template>
    <div>
        <el-row type="flex" class="row-bg" justify="space-between">
            <el-form label-width="80px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="标题：">
                            <el-input
                                    placeholder="请输入标题"
                                    @change="getsldjList"
                                    v-model="sldjForm.title"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="工单号：">
                            <el-input
                                    placeholder="请输入工单号"
                                    @change="getsldjList"
                                    v-model="sldjForm.number"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="时间：">
                            <el-date-picker
                                    style="width:300px"
                                    v-model="sldjForm.currentDate"
                                    type="datetimerange"
                                    placeholder="选择时间范围"
                                    @change="getsldjList"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="工单类型：">
                            <el-select v-model="sldjForm.bustype"
                                       placeholder="请选择工单类型"
                                       style="width: 200px"
                                       @change="getsldjList"
                                       clearable>
                                <el-option label="咨询" value="咨询"></el-option>
                                <el-option label="举报" value="举报"></el-option>
                                <el-option label="投诉" value="投诉"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="电话：">
                            <el-input
                                    placeholder="请输入电话"
                                    @change="getsldjList"
                                    v-model="sldjForm.tel"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="6">
                        <el-form-item label="关键词：">
                            <el-input
                                    placeholder="请输入关键词"
                                    @change="getsldjList"
                                    v-model="sldjForm.keyword"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="投诉/举报:">
                            <el-input
                                    placeholder="请输入投诉举报人"
                                    @change="getsldjList"
                                    v-model="sldjForm.rqstperson"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="办理单位：">
                            <el-input
                                    placeholder="请输入办理单位"
                                    @change="getsldjList"
                                    v-model="sldjForm.handledeptname"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="问题分类：">
                            <el-input
                                    placeholder="请输入问题分类"
                                    @change="getsldjList"
                                    v-model="sldjForm.complaintstype"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="商品/服务:">
                            <el-input
                                    placeholder="请输入商品服务类型"
                                    @change="getsldjList"
                                    v-model="sldjForm.servicetype"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="12" :md="8" :lg="8">
                        <el-form-item label="是否立案:">
                            <el-select
                                    v-model="sldjForm.isregistercase"
                                    clearable
                                    @change="getsldjList"
                                    placeholder="请选择"
                                    style="width: 200px"
                            >
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="是否网购：">
                            <el-select v-model="sldjForm.isweb"
                                       placeholder="请选择"
                                       style="width: 200px"
                                       @change="getsldjList"
                                       clearable>
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="是否职投：">
                            <el-select v-model="sldjForm.isprofessional"
                                       placeholder="请选择"
                                       style="width: 200px"
                                       @change="getsldjList"
                                       clearable>
                                <el-option label="是" value="是"></el-option>
                                <el-option label="否" value="否"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="来函单位：">
                            <el-input
                                    placeholder="请输入"
                                    @change="getsldjList"
                                    v-model="sldjForm.incomingcommpany"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="途径：">
                            <el-input
                                    placeholder="请输入"
                                    @change="getsldjList"
                                    v-model="sldjForm.appealsource"
                                    style="width:200px"
                            ></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="8">
                        <el-form-item label="当前环节：">
                            <el-select
                                    v-model="sldjForm.currentLink"
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
                        </el-form-item>
                    </el-col>

                    <el-col :xs="24" :sm="24" :md="8" :lg="2">
                        <el-form-item>
                            <el-button type="primary" @click="getsldjList">查询</el-button>
                            <!-- <el-button type="danger">删除</el-button> -->
                        </el-form-item>
                    </el-col>

                </el-row>
            </el-form>
        </el-row>
        <el-table
                :data="listData"
                highlight-current-row
                v-loading="complaintListLoading"
                element-loading-text="拼命加载中"
                @selection-change="selsChange"
                border
                style="width: 100%"
                :row-style="{cursor:'pointer'}"
        >
            <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
            <el-table-column prop="bustype" label="业务类型" min-width="5%" align="center"></el-table-column>
            <el-table-column prop="tserialnum" label="工单号" min-width="13%" align="center">
                <template scope="scope">
                    <div>
                        <span @click="toFind(scope, scope.row)" v-html="setSerialnumColor(scope.row)"></span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="rqsttitle" label="工单标题" min-width="10%" align="center" show-overflow-tooltip></el-table-column>
<!--            <el-table-column prop="subjectname" label="涉及主体名称" min-width="15%" align="center" show-overflow-tooltip v-if="this.search.formType != '01'"></el-table-column>-->
<!--            <el-table-column prop="entname" label="涉及主体名称" min-width="15%" align="center" show-overflow-tooltip v-if="this.search.formType == '01'"></el-table-column>-->
            <!-- 当前环节 -->
            <el-table-column
                    prop="operate"
                    label="当前环节"
                    min-width="6%"
                    align="center"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <!-- 业务分类 -->
            <!--<el-table-column-->
                    <!--prop="content"-->
                    <!--label="问题描述"-->
                    <!--min-width="12%"-->
                    <!--align="center"-->
                    <!--show-overflow-tooltip-->
            <!--&gt;</el-table-column>-->
            <el-table-column
                    prop="handledeptname"
                    label="办理单位"
                    min-width="6%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="complaintstype"
                    label="问题分类"
                    min-width="6%"
                    align="center"
                    :formatter="formatcomplaintstype"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="objname"
                    label="商品服务类型"
                    min-width="6%"
                    align="center"
                    :formatter="formatobjname"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="appealsource"
                    label="途径"
                    min-width="6%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="isregistercase"
                    label="立案情况"
                    min-width="6%"
                    align="center"
                    :formatter="formatisregistercase"
            ></el-table-column>
            <!-- 办理时间 -->
            <el-table-column
                    prop="assigndate"
                    label="办理时间"
                    min-width="6%"
                    header-align="center"
                    show-overflow-tooltip
                    :formatter="formatDate"
                    sortable
            ></el-table-column>
            <!-- 到期时间 -->
            <el-table-column
                prop="sdexpirationdate"
                label="到期时间"
                min-width="6%"
                align="center"
                :formatter="formatDates"
                sortable
                show-overflow-tooltip
        ></el-table-column>

            <el-table-column label="操作" min-width="8%" align="center">
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
                sldjForm: {
                    title: "",
                    number: "",
                    currentDate: "",
                    tel: "",
                    keyword: "",
                    rqstperson:"",
                    handledeptname:"",
                    complaintstype:"",
                    isregistercase:"",
                    bustype: "",
                    isweb:"",
                    isprofessional:"",
                    incomingcommpany:"",
                    servicetype:"",
                    appealsource:"",
                    currentLink:"",
                },
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
                listData: [],
                complaintListLoading: false,
                systemsource: "01",
                currentPage: 1,
                size: 10,
                total: 0,
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

            getSearchList() {
                this.getsldjList();
            },

            handleEdit(index, row) {
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            handleSizeChange(val) {
                // let changeSize = val;
                this.size = val;
                this.getsldjList();
            },
            getLists() {
            },
            gotoDeal(scope, row) {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid,
                    systemsource: row.systemsource
                }

                let url =  this.$router.resolve({
                    path: '/tjfx/zhcx/sldjcx/sldj/ywjlxq',
                    query: params
                })
                window.open(url.href, "_blank")
                // this.$router.push({path: '/tjfx/zhcx/sldjcx/sldj/ywjlxq', query: params})


            },
            formatDate(row, column) {
                return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm:ss");
            },
            formatDates(row, column) {
                console.log("时间")
                return this.$moment(row.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss");
            },
            formatobjname(row,column){
                if(row.formtype==='01'){ //咨询
                    return "/"
                } else {
                    return row.servicetype;
                }
            },
            formatcomplaintstype(row,column){
                if(row.complaintstype==null){
                    return "/"
                }else{
                    return row.complaintstype;
                }
            },
            formatisregistercase(row,column){
                if(row.formtype==='03'){
                    return row.isregistercase;
                }else{
                    return "/"
                }
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getsldjList();
            },
            getlistData() {
            },
            getsldjList() {
                // this.systemsource = "01"
                let user = JSON.parse(localStorage.getItem("user"));
                if (
                    this.sldjForm.currentDate[0] !== undefined &&
                    this.sldjForm.currentDate[1] !== undefined
                ) {
                    var begindate = moment(this.sldjForm.currentDate[0]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                    var enddate = moment(this.sldjForm.currentDate[1]).format(
                        "YYYY-MM-DD HH:mm:ss"
                    );
                }
                let params = {
                    systemsource: this.systemsource,
                    page: this.currentPage,
                    total: this.total,
                    size: this.size,
                    user: user,
                    rqsttitle: this.sldjForm.title,
                    tserialnum: this.sldjForm.number,
                    begindate: begindate,
                    enddate: enddate,
                    tel: this.sldjForm.tel,
                    keyword: this.sldjForm.keyword,
                    bustype: this.sldjForm.bustype,
                    rqstperson: this.sldjForm.rqstperson,
                    handledeptname:this.sldjForm.handledeptname,
                    complaintstype:this.sldjForm.complaintstype,
                    isweb:this.sldjForm.isweb,
                    isprofessional:this.sldjForm.isprofessional,
                    incomingcommpany:this.sldjForm.incomingcommpany,
                    isregistercase:this.sldjForm.isregistercase,
                    servicetype:this.sldjForm.servicetype,
                    appealsource:this.sldjForm.appealsource,
                    currentLink: this.sldjForm.currentLink,
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
            },
            toFind(column, row) {
                let params = {
                    id: row.id,
                    caseguid: row.caseguid
                };
                let url = this.$router.resolve({
                    path: "/tjfx/zhcx/sldjcx",
                    query: params
                });
                window.open(url.href, "_blank");
            },
            //初步反馈，立案，最终反馈即将到期工单表单号变色
            setSerialnumColor(row) {
                let nowDate = new Date().getTime();
                if(row.formtype=="01"){
                    if(row.sdexpirationdate - nowDate <= 48 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate) {
                        return '<span style="color:red;">' + row.tserialnum + '</span>';
                    }else{
                        return row.tserialnum;
                    }
                }else if(row.formtype=="02"){
                    console.log(row)
                    if(row.feedback==null && (row.sdexpirationdate - nowDate <= 24 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate)) {
                        console.log(row.feedback+row.tserialnum)
                        return '<span style="color:blue;">' + row.tserialnum + '</span>';
                    }else if(row.feedback=="1" && row.sdexpirationdate - nowDate <= 48 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate){
                        console.log(row.tserialnum)
                        return '<span style="color:red;">' + row.tserialnum + '</span>';
                    }else{
                        return row.tserialnum;
                    }
                }else if(row.formtype=="03"){
                    if(row.feedback==null && row.sdexpirationdate - nowDate <= 24 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate){
                        return '<span style="color:blue;">' + row.tserialnum + '</span>';
                    }else if(row.feedback=='1' && row.isregistercase=='' && row.sdexpirationdate - nowDate <= 48 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate){
                        return '<span style="color:#1bff23;">' + row.tserialnum + '</span>';
                    }else if(row.feedback=='1' && row.isregistercase!='' && row.nextnodeid !='end' && row.sdexpirationdate - nowDate <= 48 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate){
                        return '<span style="color:red;">' + row.tserialnum + '</span>';
                    }else{
                        return row.tserialnum;
                    }
                }else{
                    return row.tserialnum;
                }

                // let nowDate = new Date().getTime();
                // if (row.sdexpirationdate - nowDate <= 48 * 60 * 60 * 1000 && row.sdexpirationdate>nowDate) {
                //     return '<span style="color:red;">' + row.tserialnum + '</span>';
                // } else {
                //     return row.tserialnum;
                // }
            },
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
