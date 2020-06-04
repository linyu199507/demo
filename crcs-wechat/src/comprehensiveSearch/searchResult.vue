<template>
    <div class="main-container">
        <header class="title">搜索结果</header>
        <div class="main-content">
            <!-- <div class="table-header">
                    <span style="width:32%;">工单号</span>
                    <span style="width:20%;">标题</span>
                    <span style="width:30%;">登记时间</span>
                    <span style="width:10%;">类型</span>
            </div> -->
            <scroller lock-x height="-40" @on-scroll-bottom="onScrollBottom" ref="scrollerBottom" :scroll-bottom-offst="200">
            <div class="data-box">
                <card v-for="atom in result" :key="atom.id">
                    <div slot="content" class="card-box" @click="showDetail(atom)">
                        <span class="data-item" style="width:100%;">工单号：{{atom.tserialnum}}</span>
                        <span class="data-item" style="width:100%;">标题：{{atom.rqsttitle}}</span>
                        <span class="data-item" style="width:30%">投诉人：{{atom.complainant}}</span>
                        <span class="data-item" style="width:65%">被投诉人/企业：{{atom.complainant}}</span>
                        <span class="data-item" style="width:45%;">诉求日期：{{formatDate(atom.assigndate)}}</span>
                        <span class="data-item" style="width:50%">业务性质：{{atom.bustype}}</span>
                        <span class="data-item" style="width:45%">当前环节：{{atom.operate}}</span>
                        <span class="data-item" style="width:50%;text-align:right;">查看详情>></span>
                    </div>
                </card>
                <p class="show-more" v-if="loading">加载中</p>
                <p class="show-more" v-if="!loading">{{(curPage>=totalPage?'已经没有了':'上拉加载更多')}}</p>
            </div>
            </scroller>
        </div>
    </div>
</template>

<script>
import { searchBusinessInfo} from '../api/api';
import moment from "moment";
export default {
    data(){
        return {
            formParam:{
                serialNum:'',
                tserialNum:'',
                title:'',
                registerDate1:'',
                registerDate2:'',
                source:'',
                nature:'',
                status:'',
            },
            result:[],
            curPage:1,
            pageSize:20,
            loading:false,
            totalPage:2,
        }
    },
    mounted(){
        this.formParam = JSON.parse(sessionStorage.getItem('searchFormParam'));
        this.loadPage()
    },
    methods:{
        formatDate(timestamp){
            return moment(new Date(timestamp)).format("YYYY-MM-DD HH:mm:ss");
        },
        loadPage(){
            if(this.curPage > this.totalPage) return;
            this.loading = true;
            const requestParam = {
                size:this.pageSize,
                page:this.curPage,
                createdate:this.formParam.registerDate1,
                duetime:this.formParam.registerDate2,
            }
            if(this.formParam.source != ''){
                requestParam.systemsource = this.formParam.source;
            }
            if(this.formParam.nature != ''){
                requestParam.bustype = this.formParam.nature;
            }
            if(this.formParam.status != ''){
                requestParam.nextnodeid = this.formParam.status;
            }
            if(this.formParam.serialNum != ''){
                requestParam.serialnum = this.formParam.serialNum;
            }
            if(this.formParam.tserialNum != ''){
                requestParam.tserialnum = this.formParam.tserialNum;
            }
            if(this.formParam.title != ''){
                requestParam.rqsttitle = this.formParam.title;
            }
            const cur = this;
            searchBusinessInfo(requestParam).then(res => {
                if(res.code == 0){
                    cur.totalPage = res.data.pages;
                    const data = res.data.records;
                    data.forEach(each => {
                        cur.result.push(each);
                    })
                    cur.loading = false;
                }else{
                    this.curPage--;
                }
            })
        },
        onScrollBottom(){
            if(this.loading) return;
            this.curPage++;
            if(this.curPage > this.totalPage) return;
            this.loadPage();
        },
        showDetail(data){
            this.$router.push({name:'comprehensiveSearchResultDetail', query:{id:data.id, caseguid:data.caseguid, systemsource:data.systemsource}});
        }
    }
}
</script>

<style lang="less" scoped>
ul,li{list-style: none;}
header.title{height:40PX;line-height:40PX;text-align: center;font-size: 18PX;border-bottom: 1PX solid rgb(233, 233, 233);box-shadow: 0 2PX 5PX #ccc;}
div.table-header{height:30PX;font-size:15PX;text-align: center;border-bottom: 1PX solid rgb(233, 233, 233);}
div.table-header span{line-height:30PX;display:inline-block;}
div.table-header span:not(:last-child){border-right:1PX solid rgb(233, 233, 233)}
// .data-atom{height:30PX;font-size:15PX;}
.data-atom{padding:2%;border-bottom: 1PX solid rgb(233, 233, 233);}
.data-item{text-overflow: ellipsis;overflow: hidden;white-space:nowrap;display:inline-block;line-height: 20PX;}
.show-more{text-align: center;font-size: 18PX;}
.weui-panel.weui-panel_access{margin:10PX 2% 0 2%;padding:2%;box-shadow: 0 2PX 5PX rgb(233, 233, 233);}
</style>