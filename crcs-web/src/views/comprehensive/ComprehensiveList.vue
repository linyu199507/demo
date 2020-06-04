<template>
    <section id="comprehensive_list">

        {{ this.prompt }}
        <div class="operation" >
            <el-button type="primary" size="small"  icon="document" @click="exportToExcel" :loading="exportLoading"
                       style="margin: 2px; border-radius: 13px">
                <template v-if="exportLoading">
                    正在导出...
                </template>
                <template v-else>
                    <i class="fa fa-download"></i>
                    Excel导出
                </template>
            </el-button>
        </div>
        <el-table :data="Tasklist"
                  highlight-current-row v-loading="complaintListLoading"
                  @selection-change="handleSelectionChange" border
                  @cell-click="toFind" :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection" min-width="5%" align="center"></el-table-column>

            <el-table-column type="index" label="序号" min-width="8%" align="center"></el-table-column>

<!--            <el-table-column prop="focus" label="工单分类" min-width="8%" align="center">-->
<!--            </el-table-column>-->

            <el-table-column prop="tserialnum" label="工单号" min-width="12%" align="center">
            </el-table-column>
            <el-table-column prop="rqsttitle" label="标题" min-width="15%" header-align="center"
                             show-overflow-tooltip>
            </el-table-column>
<!--            <el-table-column prop="accordtype" label="业务分类" min-width="8%" align="center"/>-->
            <el-table-column prop="complainant" label="投诉人" min-width="8%" align="center"/>
            <el-table-column prop="complainanted" label="被投诉人/企业" min-width="6%" align="center" show-overflow-tooltip/>
            <el-table-column prop="phone" label="电话" min-width="8%" align="center"/>
            <el-table-column prop="address" label="地址" min-width="8%" align="center" show-overflow-tooltip/>
            <el-table-column prop="content" label="投诉内容" min-width="8%" align="center" show-overflow-tooltip/>
            <el-table-column prop="results" label="处理结果" min-width="8%" align="center" show-overflow-tooltip/>
            <el-table-column prop="classification" label="客体分类" min-width="8%" align="center" show-overflow-tooltip/>
            <el-table-column prop="assigndate" label="诉求时间" min-width="11%" align="center" :formatter="formatDate" sortable>
            </el-table-column>
            <el-table-column prop="bustype" label="业务性质" min-width="6%" align="center"/>
            <el-table-column prop="operate" label="当前环节" min-width="9%" align="center">
            </el-table-column>
            <el-table-column prop="systemsource" label="诉求来源" min-width="8%" align="center" :formatter="formatSystemsource">
            </el-table-column>
        </el-table>
        <!--分页组件开始-->
        <el-col style="text-align: right;margin-top: 10px">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-size="pagination.size"
                    :total="pagination.total"
                    :page-sizes="[10,30,50,100]"
                    layout="slot,total, sizes, prev, pager, next, jumper"
            >
        <span>
          第 {{pagination.page}} 页 /
          共 {{(parseInt((pagination.total + pagination.size - 1)/pagination.size))}} 页
        </span>
            </el-pagination>
        </el-col>
        <!-- 分页组件结束-->
    </section>
</template>

<script>

    import { searchBusinessInfo , searchBusinessExcel , excelexport, searchBusinessList} from '../../api/api';



    export default {
        name: "ComprehensiveList",
        data() {
            return {
                allIn:false,
                allChoose:false,
                prompt:"",
                serchdata:{},
                multipleSelection: [],
                ExcelUrl: `${process.env.REPORT_URL}/ureport/preview?_u=file:`,
                map:{},
                list:[],
                caseguidsIsNull:false,
                caseguidsIsNotNull:false,
                listComplainant:[],
                sels: [],//列表选中列
                /* Tasklist:[{
                     size:'',
                     page:'',
                     caseguid:'',
                     id:'',
                     index:'',
                     focus:'',
                     serialnum:'',
                     tserialnum:'',
                     rqsttitle:'',
                     accordtype:'',
                     labelname:'',
                     createdate:'',
                     duetime:'',
                     sourcesystem:'',
                 }],*/
                Tasklist:[],

                complaintListLoading:false,
                pagination: {
                    total: 0,
                    size: 10,
                    page: 1,
                },
                exportLoading: false
            }
        },


        methods: {
            formatDate(row, column) {
                return this.$moment(row.assigndate).format("YYYY-MM-DD HH:mm");
            },

            handleSelectionChange(val) {
                this.list=[]
                //遍历list集合中每条数据中对应的id
                val.map(item=>{
                    this.list.push(item.caseguid)
                })
                console.log(this.list)

            },

            formatJson(filterVal, jsonData) {
                console.log(jsonData)
                // return jsonData.map(v => filterVal.map(j => v[j]))
                // return v[j];
            },

            exportToExcel(row) {
                this.exportLoading = true
                this.list = Array.from(new Set(this.list))
                const querystring = require('querystring')
                let str = querystring.stringify({caseguid: this.list,system:"ComprehensiveList"})
                if (this.list.length == 0) {
                    this.list = null;
                    this.size = 100,
                        this.page = 1,
                        this.total = 1,
                        this.caseguidsIsNull = true
                    this.allIn = true
                    this.findList()
                    // window.open('/crcs/businessSearch/excelexport?'+str)
                    // this.$alert('请勾选对应数据进行导出', '提示', {confirmButtonText: '确定'});
                }else{

                    let param = { list : this.list }
                    searchBusinessList(param);
                    this.exportLoading = false

                    // window.open('/crcs/businessSearch/excelexport?'+str)
                    // this.$alert('已为您导出所选待办业务', '提示', {confirmButtonText: '确定'});
                }


                // if (this.list.length == 0) {
                //     this.list = null;
                // }
                // if(this.Tasklist.length == 0){
                //     this.$alert('无数据进行导出', '提示', {confirmButtonText: '确定'});
                // }else{
                //     if(this.list == "" || this.list == null){
                //         window.open('/crcs/businessSearch/excelexport?'+str)
                //         this.$alert('已为您导出所有待办业务', '提示', {confirmButtonText: '确定'});
                //     }else{
                //         window.open('/crcs/businessSearch/excelexport?'+str)
                //         this.$alert('已为您导出所选待办业务', '提示', {confirmButtonText: '确定'});
                //     }
                // }

            },



            handleCurrentChange(Page) {
                this.pagination.page = Page;//页数
                console.log(this.pagination.current)
                console.log(Page)
                this.findList();
            },
            handleSizeChange(size) {
                console.log(size)
                this.pagination.size = size;
                this.findList();
            },
            findList() {
                let serchdata
                if(this.allIn){
                    serchdata = JSON.parse(sessionStorage.getItem("serchdata"))
                    serchdata.page=this.page;
                    serchdata.size=this.size;
                    this.allIn = false
                }else{
                    serchdata = JSON.parse(sessionStorage.getItem("serchdata"))
                    serchdata.page=this.pagination.page;
                    serchdata.size=this.pagination.size;
                }



                // this.Tasklist = this.$route.params
                let user = JSON.parse(localStorage.getItem("user"))

                // let params = {
                //     page:this.pagination.current,
                //     size:this.pagination.size,
                //     focus:this.$route.query.focus,
                //     serialnum:this.$route.query.serialnum,
                //     tserialnum:this.$route.query.tserialnum,
                //     rqsttitle:this.$route.query.rqsttitle,
                //     accordtype:this.$route.query.accordtype,
                //     labelname:this.$route.query.labelname,
                //     createdate:this.$route.query.createdate,
                //     duetime:this.$route.query.duetime,
                //     buyInInternet:this.$route.query.buyInInternet,// 是否网购
                //     serialName:this.$route.query.serialName,// 投诉人
                //     serialTel:this.$route.query.serialTel,// 投诉人电话
                //     serialAddress:this.$route.query.serialAddress,// 投诉地址
                //     appealsource:this.$route.query.appealsource,
                //     businesstype:this.$route.query.businesstype,
                //     subjectname:this.$route.query.subjectname,
                //     objectcategory:this.$route.query.objectcategory,
                //     brand:this.$route.query.brand,
                //     servicetype:this.$route.query.servicetype,
                //     resultsofmediation:this.$route.query.resultsofmediation,
                //     region:this.$route.query.region,
                //     keyword:this.$route.query.keyword,
                //     bustype:this.$route.query.bustype,
                //     content:this.$route.query.content,
                //     handleopinion:this.$route.query.handleopinion,
                //     systemsource:this.$route.query.systemsource, //系统来源
                //     handleregion:this.$route.query.Company,
                //     handledeptid: this.$route.query.department,
                //     applealtocase:this.$route.query.applealtocase, //是否诉转案
                //     jyareacode:this.$route.query.jyareacode, //经营地址区
                //     beginFinancialloss:this.$route.query.beginFinancialloss, //挽回经济损失
                //     endFinancialloss:this.$route.query.endFinancialloss, //挽回经济损失
                //     beginDoublemoney:this.$route.query.beginDoublemoney, //加倍赔偿金额
                //     endDoublemoney:this.$route.query.endDoublemoney, //加倍赔偿金额
                //     problemtype:this.$route.query.problemtype, //涉及问题分类
                //     nextnodeid:this.$route.query.nextnodeid, //工单状态
                //     currentLink:this.$route.query.currentLink, //当前环节
                //     userRegion: user.regionID  //当前用户机构id
                // };
                // this.complaintListLoading = true;
                console.log(serchdata)
                searchBusinessInfo(serchdata).then((res) =>  {
                    console.log(res)
                    this.Tasklist = res.data.records
                    this.size = res.data.size
                    this.current = res.data.current
                    this.total = res.data.total
                    this.complaintListLoading = false;

                    if(this.caseguidsIsNull == true){
                        if(this.list == null){
                            this.list = []
                            for (var caseguid in this.Tasklist){
                                // console.log(caseguid+":::"+this.Tasklist[caseguid].caseguid)
                                this.list.push(this.Tasklist[caseguid].caseguid);
                            }
                            // console.log(this.list)
                            // return this.list;
                            // const querystring = require('querystring')
                            // let str = querystring.stringify({caseguid: this.list,system:"ComprehensiveList"})
                            // window.open('/crcs/businessSearch/excelexport?'+str)
                            let param = { list : this.list }
                            searchBusinessList(param);

                        }
                        this.caseguidsIsNull = false
                        this.list=[]
                        // this.size = 10
                        // this.current = 1
                        // this.total = this.total
                        this.handleSizeChange(10)
                        this.exportLoading = false

                    }

                });
                /* listComplaints(params).then((res) => {
                     // this.pagination = res.data.pagination;
                     // this.complaintList = res.data.list;
                     this.pagination.page = res.data.current;
                     this.pagination.total = res.data.total;
                     this.pagination.size = res.data.size;
                     this.complaintList = res.data.records;
                     this.complaintListLoading = false;
                 });*/
            },
            selsChange: function (sels) {
                this.sels = sels;
            },

            //批量导出
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
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.findList();
                    });
                }).catch(() => {

                });
            },

            toFind(row, column) {
                //阻止勾选复选框跳转
                // console.log(row)
                this.id=row.id
                this.caseguid=row.caseguid
                this.tserialnum=row.tserialnum
                this.focus = row.focus        //工单分类
                this.tserialnum = row.tserialnum //工单号
                this.rqsttitle = row.rqsttitle  //工单标题
                this.accordtype =row.accordtype  //业务分类
                this.assigndate = row.assigndate //交办时间
                this.systemsource =row.systemsource //系统来源
                if (column.property !== 'select') {
                    let para = {
                        id: this.id,
                        caseguid: this.caseguid,
                        systemsource: this.systemsource //系统来源
                    };

                    let url = this.$router.resolve({
                        path:'/tjfx/zhcx/ywjlcx/cxjg/ywjlxq',
                        query: para
                    });
                    window.open(url.href,"_blank")
                    // this.$router.push({path:'/tjfx/zhcx/ywjlcx/cxjg/ywjlxq', query: para})
                    //  this.$router.push({path:'/wddb/bxtdb/qtsl', query: para})
                }
            },
            //导出
            exportList() {
                let para = {
                    id:this.id,
                    caseguid:this.caseguid,
                    focus:this.focus,   //工单分类
                    tserialnum:this.tserialnum, //工单号
                    rqsttitle:this.rqsttitle, //工单标题
                    accordtype:this.accordtype, //业务分类
                    assigndate:this.assigndate,//交办时间
                    systemsource:this.systemsource,//系统来源
                    // tserialnum: this.tserialnum
                };
                //综合查询-->业务记录查询结果导出---------------------------------------
                searchBusinessExcel(para).then((res) =>  {



                });
            },
            formatSystemsource(row, column) {
                if (row.systemsource === '01') {
                    return `受理登记`
                } else if (row.systemsource === '02') {
                    return `12345系统`
                } else if (row.systemsource === '03') {
                    return `12315系统`
                } else {
                    return `其他系统`
                }
            },
            toPrompt(){
                console.log(this.serchdata)
                this.prompt = '查询条件 --  '
                if(this.serchdata.serialnum){
                    this.prompt = this.prompt + '表单号：'+this.serchdata.serialnum
                }if(this.serchdata.tserialnum){
                    this.prompt = this.prompt + '，工单号：'+this.serchdata.tserialnum
                }if(this.serchdata.rqsttitle){
                    this.prompt = this.prompt + '，标题：'+this.serchdata.rqsttitle
                }if(this.serchdata.content){
                    this.prompt = this.prompt + '，诉求内容：'+this.serchdata.content
                }if(this.serchdata.createdate){
                    this.prompt = this.prompt + '，登记时间-起始日期：'+this.serchdata.createdate
                }if(this.serchdata.duetime){
                    this.prompt = this.prompt + '，登记时间-结束时间：'+this.serchdata.duetime
                }if(this.serchdata.systemsource){
                    if(this.serchdata.systemsource === '01'){
                        this.prompt = this.prompt + '，诉求来源：手工录入'
                    }else if(this.serchdata.systemsource === '02'){
                        this.prompt = this.prompt + '，诉求来源：12345系统'
                    }else if(this.serchdata.systemsource === '03'){
                        this.prompt = this.prompt + '，诉求来源：12315系统'
                    }
                }if(this.serchdata.serialTel){
                    this.prompt = this.prompt + '，来电号码：'+this.serchdata.serialTel
                }if(this.serchdata.serialName){
                    this.prompt = this.prompt + '，来电人姓名：'+this.serchdata.serialName
                }if(this.serchdata.subjectname){
                    this.prompt = this.prompt + '，涉及主体名称：'+this.serchdata.subjectname
                }if(this.serchdata.Company){

                    if(this.serchdata.Company === '440681001'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局大良分局'
                    } else if(this.serchdata.Company === '440681002'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局容桂分局'
                    } else if(this.serchdata.Company === '440681003'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局伦教分局'
                    } else if(this.serchdata.Company === '440681004'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局北滘分局'
                    } else if(this.serchdata.Company === '440681005'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局陈村分局'
                    } else if(this.serchdata.Company === '440681006'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局乐从分局'
                    } else if(this.serchdata.Company === '440681007'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局勒流分局'
                    } else if(this.serchdata.Company === '440681008'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局龙江分局'
                    } else if(this.serchdata.Company === '440681009'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局杏坛分局'
                    } else if(this.serchdata.Company === '440681010'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局均安分局'
                    } else if(this.serchdata.Company === '440681'){
                        this.prompt = this.prompt + '，处理单位：佛山市顺德区市场监督管理局'
                    }

                }if(this.serchdata.keyword){
                    this.prompt = this.prompt + '，关键字：'+this.serchdata.keyword
                }if(this.serchdata.handleopinion){
                    this.prompt = this.prompt + '处理意见：'+this.serchdata.handleopinion
                }if(this.serchdata.currentLink){
                    this.prompt = this.prompt + '，当前环节：'+this.serchdata.currentLink
                }if(this.serchdata.brand){
                    this.prompt = this.prompt + '，商品品牌：'+this.serchdata.brand
                }if(this.serchdata.beginFinancialloss){
                    this.prompt = this.prompt + '，挽回经济损失-起：'+this.serchdata.beginFinancialloss
                }if(this.serchdata.endFinancialloss){
                    this.prompt = this.prompt + '，挽回经济损失-内：'+this.serchdata.endFinancialloss
                }if(this.serchdata.beginDoublemoney){
                    this.prompt = this.prompt + '，加倍赔偿金额-起：'+this.serchdata.beginDoublemoney
                }if(this.serchdata.endDoublemoney){
                    this.prompt = this.prompt + '，加倍赔偿金额-内：'+this.serchdata.endDoublemoney
                }if(this.serchdata.jyareacode){
                    this.prompt = this.prompt + '，经营地址区域：'+this.serchdata.jyareacode
                }if(this.serchdata.serialAddress){
                    this.prompt = this.prompt + '，经营地址详细地址：'+this.serchdata.serialAddress
                }if(this.serchdata.objectcategory){
                    this.prompt = this.prompt + '，涉及客体分类：'+this.serchdata.objectcategoryOne
                }if(this.serchdata.problemtype){
                    this.prompt = this.prompt + '，涉及问题分类：'+this.serchdata.problemtypeOne
                }if(this.serchdata.applealtocase){
                    if(this.serchdata.applealtocase === '0'){
                        this.prompt = this.prompt + '，是否诉转案：否'
                    }if(this.serchdata.applealtocase === '1'){
                        this.prompt = this.prompt + '，是否诉转案：是'
                    }
                }if(this.serchdata.bustype){
                    this.prompt = this.prompt + '，业务性质：'+this.serchdata.bustype
                }if(this.serchdata.appealsource){
                    this.prompt = this.prompt + '，途径：'+this.serchdata.appealsource
                }if(this.serchdata.incomingcommpany){
                    this.prompt = this.prompt + '，来函编号：'+this.serchdata.incomingcommpany
                }





            },
        },
        created() {
            console.log(serchdata)
            let serchdata = JSON.parse(sessionStorage.getItem("serchdata"))
            this.serchdata = serchdata

            this.toPrompt()
            let user = JSON.parse(localStorage.getItem("user"))
            console.log(user)
            // let params = {
            //     page:1,
            //     size:10,
            //     focus:this.$route.query.focus,
            //     serialnum:this.$route.query.serialnum,
            //     tserialnum:this.$route.query.tserialnum,
            //     rqsttitle:this.$route.query.rqsttitle,
            //     accordtype:this.$route.query.accordtype,
            //     labelname:this.$route.query.labelname,
            //     createdate:this.$route.query.createdate,
            //     duetime:this.$route.query.duetime,
            //     buyInInternet:this.$route.query.buyInInternet,// 是否网购
            //     serialName:this.$route.query.serialName,// 投诉人
            //     serialTel:this.$route.query.serialTel,// 投诉人电话
            //     serialAddress:this.$route.query.serialAddress,// 投诉地址
            //     appealsource:this.$route.query.appealsource,
            //     businesstype:this.$route.query.businesstype,
            //     subjectname:this.$route.query.subjectname,
            //     objectcategory:this.$route.query.objectcategory,
            //     brand:this.$route.query.brand,
            //     servicetype:this.$route.query.servicetype,
            //     resultsofmediation:this.$route.query.resultsofmediation,
            //     region:this.$route.query.region,
            //     keyword:this.$route.query.keyword,
            //     bustype:this.$route.query.bustype,
            //     content:this.$route.query.content,
            //     handleopinion:this.$route.query.handleopinion,
            //     systemsource:this.$route.query.systemsource, //系统来源
            //     handleregion:this.$route.query.Company,
            //     handledeptid: this.$route.query.department,
            //     applealtocase:this.$route.query.applealtocase, //是否诉转案
            //     jyareacode:this.$route.query.jyareacode, //经营地址区
            //     beginFinancialloss:this.$route.query.beginFinancialloss, //挽回经济损失
            //     endFinancialloss:this.$route.query.endFinancialloss, //挽回经济损失
            //     beginDoublemoney:this.$route.query.beginDoublemoney, //加倍赔偿金额
            //     endDoublemoney:this.$route.query.endDoublemoney, //加倍赔偿金额
            //     problemtype:this.$route.query.problemtype, //涉及问题分类
            //     nextnodeid:this.$route.query.nextnodeid, //工单状态
            //     currentLink:this.$route.query.currentLink, //当前环节
            //     userRegion: user.regionID  //当前用户机构id
            // };
            //综合查询-->业务记录查询---------------------------------------
            // searchBusinessInfo(params).then((res) =>  {
            searchBusinessInfo(serchdata).then((res) =>  {
                console.log(res)
                this.Tasklist = res.data.records
                this.pagination.page = res.data.current;
                this.pagination.total = res.data.total;
                this.pagination.size = res.data.size;
                this.complaintListLoading = false;
            });
            this.$router.resolve({path:'/tjfx/zhcx/ywjlcx/cxjg/ywjlxq', query: params})
        },


    }
</script>

<style lang="scss" scoped>
    #comprehensive_list {
    }

    .operation {
        /*background: #f2f2f2;*/
        margin: 10px 0 10px 0;
    }
</style>
<style>
    #comprehensive_list .el-table__body-wrapper {
        overflow-x: auto;
    }
</style>
