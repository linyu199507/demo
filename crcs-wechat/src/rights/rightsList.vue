<template>
    <div class="main-container">
        <header class="title">维权服务
            <a href="javascript:;" class="filter" @click="showSearch = !showSearch">筛选</a>
        </header>
        <div class="main-content">
            <div class="search-bar" v-show="showSearch">
                    <group class="search-form">
                        <x-input title="工单号" v-model="formParam.serialNum" placeholder-align="right" text-align="right" placeholder="请输入工单号"></x-input>
                        <x-input title="标题" v-model="formParam.title" placeholder-align="right" text-align="right" placeholder="请输入标题"></x-input>
                        <popup-picker
                            title="所属辖区"
                            placeholder="请选择所属辖区"
                            :data="langOptions"
                            v-model="formParam.region"
                            :show-name="true"
                            value-text-align="right"
                            ></popup-picker>
                        <popup-picker
                            title="经营主体"
                            placeholder="请选择经营主体"
                            :data="operatorsList"
                            :show-name="true"
                            v-model="formParam.enterprise"
                            value-text-align="right"
                            ></popup-picker>
                        <datetime
                            v-model="formParam.registerDate1"
                            :format="'YYYY-MM-DD'"
                            title="登记时间（始）"
                            placeholder="请选择登记时间（始）"
                            value-align="right"></datetime>
                        <datetime
                            v-model="formParam.registerDate2"
                            :format="'YYYY-MM-DD'"
                            title="登记时间（终）"
                            placeholder="请选择登记时间（始）"
                            value-align="right"></datetime>
                        <div class="submit">
                            <x-button type="primary" mini @click.native="submit">查找</x-button>
                            <x-button type="cancel" mini @click.native="reset">重置</x-button>
                        </div>
                    </group>
            </div>
            <scroller v-if="showSearch" lock-x height="-380" @on-scroll-bottom="onScrollBottom" ref="scrollerBottom" :scroll-bottom-offst="200">
                <div class="data-box">
                    <card v-for="atom in result" :key="atom.id">
                        <div slot="content" class="card-box" @click="showDetail(atom)">
                            <span class="data-item" style="width:100%;">工单号：{{atom.serialnum}}</span>
                            <span class="data-item" style="width:100%;">标题：{{atom.rqsttitle}}</span>
                            <span class="data-item" style="width:100%">所属辖区：{{atom.region}}</span>
                            <span class="data-item" style="width:100%">经营主体：{{atom.enterprise}}</span>
                            <span class="data-item" style="width:45%;">登记时间：{{formatDate(atom.registrationdate)}}</span>
                            <span class="data-item" style="width:50%;text-align:right;">查看详情>></span>
                        </div>
                    </card>
                    <p class="show-more" v-if="loading">加载中</p>
                    <p class="show-more" v-if="!loading">{{(curPage>=totalPage?'已经没有了':'上拉加载更多')}}</p>
                    <!-- <LoadMore v-if="loading" :tip="'正在加载'"></LoadMore>
                    <LoadMore v-if="!loading" :show-loading="false" :tip="curPage>=totalPage?'已经没有了':'上拉加载更多'" background-color="#fbf9fe"></LoadMore> -->
                </div>
            </scroller>
            <scroller v-if="!showSearch" lock-x height="-45" @on-scroll-bottom="onScrollBottom" ref="scrollerBottom" :scroll-bottom-offst="200">
                <div class="data-box">
                    <card v-for="atom in result" :key="atom.id">
                        <div slot="content" class="card-box" @click="showDetail(atom)">
                            <span class="data-item" style="width:100%;">工单号：{{atom.serialnum}}</span>
                            <span class="data-item" style="width:100%;">标题：{{atom.rqsttitle}}</span>
                            <span class="data-item" style="width:100%">所属辖区：{{atom.region}}</span>
                            <span class="data-item" style="width:100%">经营主体：{{atom.enterprise}}</span>
                            <span class="data-item" style="width:60%;">登记时间：{{formatDate(atom.registrationdate)}}</span>
                            <span class="data-item" style="width:35%;text-align:right;">查看详情>></span>
                        </div>
                    </card>
                    <p class="show-more" v-if="loading">加载中</p>
                    <p class="show-more" v-if="!loading">{{(curPage>=totalPage?'已经没有了':'上拉加载更多')}}</p>
                    <!-- <LoadMore v-if="loading" :tip="'正在加载'"></LoadMore>
                    <LoadMore v-if="!loading" :show-loading="false" :tip="curPage>=totalPage?'已经没有了':'上拉加载更多'" background-color="#fbf9fe"></LoadMore> -->
                </div>
            </scroller>
        </div>
    </div>
</template>

<script>
import { rightsList} from '../api/api';
import moment from "moment";
export default {
    data(){
        return {
            showSearch:false,
            langOptions: [[
                {name:'顺德区市监局大良分局', value:'顺德区市监局大良分局'},
                {name:'顺德区市监局容桂分局', value:'顺德区市监局容桂分局'},
                {name:'顺德区市监局伦教分局', value:'顺德区市监局伦教分局'},
                {name:'顺德区市监局北滘分局', value:'顺德区市监局北滘分局'},
                {name:'顺德区市监局陈村分局', value:'顺德区市监局陈村分局'},
                {name:'顺德区市监局乐从分局', value:'顺德区市监局乐从分局'},
                {name:'顺德区市监局龙江分局', value:'顺德区市监局龙江分局'},
                {name:'顺德区市监局勒流分局', value:'顺德区市监局勒流分局'},
                {name:'顺德区市监局杏坛分局', value:'顺德区市监局杏坛分局'},
                {name:'顺德区市监局均安分局', value:'顺德区市监局均安分局'}
            ]],
            operatorsList: [[
                {name:'', value:''}
            ]],
            formParam:{
                serialNum:'',
                title:'',
                region:[''],
                enterprise:[''],
                registerDate1:'',
                registerDate2:'',
            },
            markParam:{
                serialNum:'',
                title:'',
                region:[''],
                enterprise:[''],
                registerDate1:'',
                registerDate2:'',
            },
            result:[],
            curPage:1,
            pageSize:20,
            loading:false,
            totalPage:2,
        }
    },
    mounted(){
        this.loadPage()
    },
    methods:{
        formatDate(timestamp){
            if(!timestamp) return '';
            return moment(new Date(timestamp)).format("YYYY-MM-DD HH:mm:ss");
        },
        loadPage(curPage){
            if(curPage){
                this.curPage = curPage;
                this.totalPage = 100;
                this.result = [];
            }
            if(this.curPage > this.totalPage) return;
            this.loading = true;
            const requestParam = {
                pageSize:this.pageSize,
                curPage:this.curPage,
                orderField:'registrationdate',
                paramMap:{},
                likeParamMap:{},
                customParamMap:{
                    isCurUser:"1",
                },
            }
            if(this.markParam.serialNum != ''){
                requestParam.paramMap.serialNum = this.markParam.serialNum;
            }
            if(this.markParam.title != ''){
                requestParam.likeParamMap.rqsttitle = this.markParam.title;
            }
            if(this.markParam.region.length > 0 && this.markParam.region[0] != ''){
                requestParam.paramMap.region = this.markParam.region[0];
            }
            if(this.markParam.enterprise.length > 0 && this.markParam.enterprise[0] != ''){
                requestParam.paramMap.enterprise = this.markParam.enterprise[0];
            }
            if(this.markParam.registerDate1 != ''){
                requestParam.customParamMap.registerDateBegin = this.markParam.registerDate1;
            }
            if(this.markParam.registerDate2 != ''){
                requestParam.customParamMap.registerDateEnd = this.markParam.registerDate2;
            }
            const cur = this;
            rightsList(requestParam).then(res => {
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
            this.$router.push({name:'rightsDetail', query:{id:data.id}});
        },
        reset(){
            this.formParam = {
                serialNum:'',
                title:'',
                region:[''],
                enterprise:[''],
                registerDate1:'',
                registerDate2:'',
            };
        },
        submit(){
            if(this.loading) return;
            this.markParam = {};
            for(let key in this.formParam){
                this.markParam[key] = this.formParam[key];
            }
            this.loadPage(1);
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
.search-form{touch-action: none;}
.filter{position: absolute;top: 0;right:5PX;font-size: 15PX; display: inline-block;margin: 0 10PX;}
.submit{text-align: center;}
.weui-btn_cancel{background-color:red}
#vux-scroller-sv1wk{margin-top:5px}
</style>