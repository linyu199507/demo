<template>
    <div class="main-container">
        <!-- <header class="title">维权服务</header> -->

        <card :header="{title: '站点'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">工单号：{{data.serialnum}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">标题：{{data.rqsttitle}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">所属辖区：{{data.region}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">经营主体：{{data.enterprise}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">登记时间：{{formatDateTime(data.registrationdate)}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">登记人：{{data.staffname}}</span>
                </p>

            </div>
        </card>

        <card :header="{title: '投诉人信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">姓名/名称：{{data.name}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">联系电话：{{data.phone}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">具体地址：{{data.address}}</span>
                </p>
            </div>
        </card>

        <card :header="{title: '被投诉人信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">姓名/名称：{{data.vendorname}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">所属区：{{data.vendorregion}}</span>

                </p>
                <p>
                    <span class="detail-item" style="width:100%">所属镇：{{data.vendortown}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">联系电话：{{data.vendorphone}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">具体地址：{{data.vendoraddress}}</span>
                </p>
            </div>
        </card>

        <card :header="{title: '投诉货品或服务'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">名称/类别：{{data.goodsname}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">型号：{{data.goodsmodel}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">投诉类型：{{data.goodstype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">投诉性质：{{data.nature}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:35%">数量：{{data.number}}</span>
                    <span class="detail-item" style="width:60%">设计金额：{{data.money}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">购买时间：{{formatDate(data.shoppingdate)}}</span>
                    <span class="detail-item" style="width:50%">发生时间：{{formatDate(data.happendate)}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '个案详情'}">
            <div slot="content" class="card-box">
                <p>{{data.rqstcontent}}</p>
            </div>
        </card>
        <card :header="{title: '诉求内容'}">
            <div slot="content" class="card-box">
                <p>{{data.appeal}}</p>
            </div>
        </card>
        <card :header="{title: '处理结果'}" v-if="data.feedbackmark == '1' || data.processingdate">
            <div slot="content" class="card-box">
                <p>处理结果：{{data.processing}}</p>
                <p>处理时间：{{formatDateTime(data.processingdate)}}</p>
            </div>
        </card>
        <card :header="{title: '申请工单转换记录'}" v-if="data.requestmark && data.requestmark == '1'">
            <div slot="content" class="card-box">
                <p>申请原因：{{lastRequest.reason}}</p>
                <p>发往分局：{{lastRequest.branchOffice}}</p>
                <p>申请时间：{{formatDateTime(lastRequest.createTime)}}</p>
            </div>
        </card>

        <card :header="{title: '处理结果'}" v-if="processingFormShow">
            <div slot="content" class="card-box">
                <group>
                    <x-textarea placeholder="请输入处理结果" v-model="processing" :max="300"></x-textarea>
                    <div class="submit">
                        <x-button type="primary" mini @click.native="submitProcessing">提交</x-button>
                    </div>
                </group>
            </div>
        </card>
        <card :header="{title: '工单转换申请'}" v-if="transformFormShow">
            <div slot="content" class="card-box">
                <group>
                    <x-textarea placeholder="请输入申请原因" :title="'申请原因'" v-model="reason" :max="300"></x-textarea>
                    <popup-picker v-model="branchOfficeValue" :title="'发往分局'" :show-name="true" placeholder="请选择发往分局" :data="branchOfficeOptions"></popup-picker>
                    <div class="submit">
                        <x-button type="primary" mini @click.native="submitTransform">提交</x-button>
                    </div>
                </group>
            </div>
        </card>
        <div class="submit bottom-submit">
            <x-button type="primary" mini @click.native="processingForm" v-if="(!data.requestmark || data.requestmark == '0' || data.requestmark=='') && (!data.feedbackmark || data.feedbackmark == '0' || data.feedbackmark=='')">处理工单</x-button>
            <x-button type="primary" mini @click.native="transform" v-if="(!data.requestmark || data.requestmark == '0' || data.requestmark=='') && (!data.feedbackmark || data.feedbackmark == '0' || data.feedbackmark=='')">申请转投诉举报</x-button>
        </div>
    </div>

</template>


<script>
import { rightsDetail } from '../api/api';
import moment from "moment";
import axios from 'axios';
export default {
    data(){
        return {
            id:'',
            processingFormShow:false,
            processing:'',
            reason:'',
            transformFormShow:false,
            branchOfficeOptions:[[
                {value:'测试分局', name:'测试分局'},
            ]],
            branchOfficeValue:[],
            data:{

            },
            lastRequest:{},
            uploading:false,
        }
    },
    mounted(){
        this.id = this.$route.query.id;
        rightsDetail(this.id).then(res => {
            if (res.code === 0) {
                this.data = res.data;
                if(this.data.requestmark == '1'){
                    this.loadLastRequest();
                }
            }
        });
    },
    methods:{
        submitProcessing(){
            if(this.processing.trim() == '') return this.$vux.alert.show('请输入处理结果');
            this.uploading = true;
            const form = new FormData();
            form.append('id', this.id);
            form.append('processing', this.processing);
            axios.post(`/crcs/rightsdetails/processing`, form)
                .then(res => {
                    let result = res.data;
                    if(result.code == 0){
                        this.$vux.alert.show('提交成功');
                        this.data.feedbackmark = '1';
                        this.data.processing = this.processing;
                        this.data.processingdate = result.data;
                        this.processingFormShow = false;
                        this.transformFormShow = false;
                    }else{
                        this.$vux.alert.show('提交失败！'+result.msg);
                    }
                    this.uploading = false;
                });
        },
        submitTransform(){
            if(this.reason.trim() == '') return this.$vux.alert.show('请输入申请原因');
            if(this.branchOfficeValue.length < 1) return this.$vux.alert.show('请选择分局');
            this.uploading = true;
            const form = new FormData();
            form.append('id', this.id);
            form.append('reason', this.reason);
            form.append('branchOffice', this.branchOfficeValue[0]);
            axios.post(`/crcs/rightsdetails/transform`, form)
                .then(res => {
                    let result = res.data;
                    if(result.code == 0){
                        this.$vux.alert.show('提交成功');
                        this.data.requestmark = '1';
                        this.processingFormShow = false;
                        this.transformFormShow = false;
                        this.loadLastRequest();
                    }else{
                        this.$vux.alert.show('提交失败！'+result.msg);
                    }
                    this.uploading = false;
                });
        },
        processingForm(){
            this.processingFormShow = !this.processingFormShow;
            this.transformFormShow = false;
        },
        transform(){
            this.transformFormShow = !this.transformFormShow;
            this.processingFormShow = false;
        },
        formatDateTime(timestamp){
            return moment(new Date(timestamp)).format("YYYY-MM-DD HH:mm:ss");
        },
        formatDate(timestamp){
            return moment(new Date(timestamp)).format("YYYY-MM-DD");
        },
        loadLastRequest(){
            axios.get('/crcs/rightsdetails/last_transform_request/'+this.id).then(res => {
                this.lastRequest = res.data.data;
            });
        },
    }
}
</script>

<style lang="less" scoped>
header.title{height:40PX;line-height:40PX;text-align: center;font-size: 18PX;border-bottom: 1PX solid rgb(233, 233, 233);box-shadow: 0 2PX 5PX #ccc;}
.card-box {
  padding: 14px;
}
// .detail-item{text-overflow: ellipsis;overflow: hidden;white-space:nowrap;display:inline-block;}
.detail-item {
  display: inline-block;
}
.submit{text-align: center;}
.bottom-submit{padding-bottom:15%}
</style>
