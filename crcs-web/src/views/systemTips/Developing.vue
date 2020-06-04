<!--功能正在开发提示页面-->
<!--<template>
  
   <div class="row">
     <i class="el-icon-information"></i>
    功能正在开发中，敬请期待！
   </div>
    
  
</template>

<script>
export default {
  name: "BeingDevelop",
 
  
  methods: {
  
 
  },

};
</script>

<style scoped>
.row {
  width: 100%;
  text-align: center;
  font-size: 30px;
  height: 350px;
  color: #262626;
}

.el-icon-information {
  color: #20a0ff;
  font-size: 30px;
  margin-right: 10px;
}

</style>-->
<!--12345待办列表-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="search">
                <el-row :gutter="10" style="margin-bottom:10px">
                    <el-col :span="6">
                        <div class="grid-content bg-purple">
                            <el-form-item>
                                <el-col>标题:</el-col>
                            </el-form-item>
                            <el-form-item>
                                <el-input
                                        v-model="search.rqsttitle"
                                        @change="change(search.rqsttitle)"
                                        placeholder="标题"
                                ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div class="grid-content bg-purple">
                            <el-form-item>
                                <el-col>表单号:</el-col>
                            </el-form-item>
                            <el-form-item>
                                <el-input
                                        v-model="search.serialnum"
                                        @change="change(search.serialnum)"
                                        placeholder="表单号"
                                ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="grid-content bg-purple">
                            <el-form-item>
                                <el-col>时间:</el-col>
                            </el-form-item>
                            <el-form-item>
                                <el-date-picker v-model="search.wasDeal" type="datetimerange" placeholder="选择时间范围"></el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="6">
                        <div class="grid-content bg-purple">
                            <el-form-item>
                                <el-col>电话:</el-col>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="search.tel" @change="change(search.tel)" placeholder="联系电话"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content bg-purple">
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
                        </div>
                    </el-col>

                    <el-col :span="6">
                        <div class="grid-content bg-purple">
                            <el-form-item>
                                <el-col>关键词:</el-col>
                            </el-form-item>
                            <el-form-item>
                                <el-input
                                        v-model="search.keyword"
                                        @change="change(search.keyword)"
                                        placeholder="关键词"
                                ></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="6" class="text-align:right">
                        <el-button type="primary" v-on:click="findList">查询</el-button>
                        <el-button type="danger" @click="handleDel" v-if="unitList.length>0">删除</el-button>
                    </el-col>
                </el-row>
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
                :row-style="{cursor:'pointer'}"
        >
            <!-- @cell-click="toFind" -->
            <el-table-column prop="select" type="selection" min-width="3%" align="center"></el-table-column>
            <el-table-column type="index" label="序号" min-width="5%" align="center"></el-table-column>
            <el-table-column
                    prop="formtype"
                    label="登记编号"
                    min-width="17%"
                    align="center"
                    :formatter="formtypes"
            >
                <template scope="scope">
                    <div>
                        <span @click="toFind()">{{scope.row.formtype}}</span>
                    </div>
                </template>
            </el-table-column>
            <!--      <el-table-column prop="focus" label="工单分类" min-width="7%" align="center"></el-table-column>-->
            <el-table-column prop="serialnum" label="类型" min-width="7%" align="center"></el-table-column>
            <el-table-column prop="tserialnum" label="登记时间" min-width="13%" align="center"></el-table-column>
            <el-table-column
                    prop="rqsttitle"
                    label="提供方"
                    min-width="12%"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column prop="operate" label="涉及主体" min-width="10%" align="center"></el-table-column>
            <el-table-column
                    prop="accordtype"
                    label="登记部门"
                    min-width="10%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="labelname"
                    label="登记人"
                    min-width="10%"
                    align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column
                    prop="assigndate"
                    label="登记内容"
                    min-width="13%"
                    align="center"
                    :formatter="formatDate"
            ></el-table-column>
            <el-table-column
                    prop="sdexpirationdate"
                    label="处理部门"
                    min-width="13%"
                    :formatter="formatDates"
                    align="center"
            ></el-table-column>
            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="gotoDeal(scope,scope.row)" type="primary">处理</el-button>
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
    import {} from "../../api/api";
    import moment from "moment";

    export default {
        data() {
            return {
                search: {
                    wasDeal: "",
                    tel: "",
                    keyword: "",
                    focus: "",
                    serialnum: "",
                    tserialnum: "",
                    rqsttitle: "",
                    accordtype: "",
                    labelname: "",
                    assigndate: "",
                    esulttimemin: ""
                },
                complaintList: [
                    {
                        select: "select",
                        formtype: "分派",
                        serialnum: "serialnum",
                        tserialnum: "tserialnum",
                        rqsttitle: "rqsttitle",
                        operate: "operate",
                        accordtype: "accordtype",
                        labelname: "labelname",
                        sdexpirationdate: "sdexpirationdate",
                        assigndate: "assigndate"
                    },
                    {
                        select: "select",
                        formtype: "formtype",
                        serialnum: "举报",
                        tserialnum: "tserialnum",
                        rqsttitle: "rqsttitle",
                        operate: "operate",
                        accordtype: "accordtype",
                        labelname: "labelname",
                        sdexpirationdate: "sdexpirationdate",
                        assigndate: "assigndate"
                    },
                    {
                        select: "select",
                        formtype: "formtype",
                        serialnum: "投诉",
                        tserialnum: "tserialnum",
                        rqsttitle: "rqsttitle",
                        operate: "operate",
                        accordtype: "accordtype",
                        labelname: "labelname",
                        sdexpirationdate: "sdexpirationdate",
                        assigndate: "assigndate"
                    }
                ],
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1
                },
                complaintListLoading: false,
                sels: [], //列表选中列
                unitList: []
            };
        },
        methods: {
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
            handleSizeChange() {},
            handleCurrentChange() {},
            handleDel() {},
            findList() {},
            selsChange() {},
            formtypes(row, column) {
                if ("02" == row.formtype) {
                    return "消费投诉";
                } else if ("03" == row.formtype) {
                    return "经济违法举报";
                } else {
                    return "咨询";
                }
            },
            change() {},
            toFind() {
                let params = {};
                let url = this.$router.resolve({
                    path: "/listDetail/12315detail",
                    query: params
                });
                window.open(url.href, "_blank");
            },
            gotoDeal(value, row) {
                console.log(row);
                console.log(value);
                if (row.serialnum === "举报") {
                    let params = {};
                    let url = this.$router.resolve({
                        path: "/listDetail/12315DealBusiness/processingForm",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else if (row.serialnum === "投诉") {
                    let params = {};
                    let url = this.$router.resolve({
                        path: "/listDetail/12315DealBusiness/processingForm",
                        query: params
                    });
                    window.open(url.href, "_blank");
                } else {
                    let params = {};
                    let url = this.$router.resolve({
                        path: "/listDetail/12315DealBusiness",
                        query: params
                    });
                    window.open(url.href, "_blank");
                }
            }
        },

        mounted() {}
    };
</script>

<style lang="scss" scoped>
    .el-col-6 {
        width: auto !important;
    }
</style>

