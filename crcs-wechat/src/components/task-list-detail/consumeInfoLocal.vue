<template>
    <div class="main-container">
        <card :header="{title: '1、系统信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:65%">工单号：{{task.tserialnum}}</span>
                    <span class="detail-item" style="width:30%">业务：消费投诉</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">标题：{{data.rqsttitle}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:25%">途径：{{data.appealsource}}</span>
                    <span class="detail-item" style="width:70%">移送编号：{{data.transfernumber}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">地区来源：{{data.detailaddress}}</span>
                    <span class="detail-item" style="width:50%">所属板块：{{data.subordinateplate}}</span>
                </p>
                <div v-if="data.appealsource === '信访系统'">
                    <span class="detail-item" style="width:45%">登记机构：{{data.registration}}</span>
                    <span class="detail-item" style="width:50%">承办机构{{data.undertake}}</span>
                    <span class="detail-item" style="width:100%">满意度：{{data.satisfied}}</span>
                </div>
                <div>
                    <span class="detail-item" style="width:100%">来函单位：{{data.incomingcommpany}}</span>
                    
                </div>
            </div>
        </card>
        <card :header="{title: '2、提供方信息'}">
            <div slot="content" class="card-box">
                <p>
                    <!-- <span class="detail-item" style="width:65%">表单号：{{task.serialnum}}</span> -->
                    <span class="detail-item" style="width:30%">投诉人姓名：{{data.rqstperson}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">联系电话：{{data.rqstnumber}}</span>
                    <span class="detail-item" style="width:25%">性别：{{data.gender}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">投诉人地址：{{data.rqstaddress}}</span>
                    <span class="detail-item" style="width:25%">是否职投：{{data.isprofessional}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '3、涉及主体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:65%">涉及主体名称：{{data.subjectname}}</span>
                    <span class="detail-item" style="width:30%">所在区域：{{data.region}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">被举报对象地址：{{data.subjectaddress}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">联系电话：{{data.linknumber}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '4、业务具体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:45%">涉及问题类型：{{data.complaintstype}}</span>
                    <span class="detail-item" style="width:50%">商品/服务类型：{{data.servicetype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">商品/服务名称：{{data.objname}}</span>
                    <span class="detail-item" style="width:50%">品牌：{{data.brand}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">领域：{{data.filed}}</span>
                    <span class="detail-item" style="width:50%">是否网购：{{data.isweb}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">网购平台：{{data.shoppingplatform}}</span>
                    <span class="detail-item" style="width:45%">案件接收日期：{{data.acceptdate?moment(data.acceptdate).format('YYYY-MM-DD'):''}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:50%">初步反馈日期：{{data.feedbackdate?moment(data.feedbackdate).format('YYYY-MM-DD'):''}}</span>
                    <span class="detail-item" style="width:50%">案件分派日期：{{data.assignmentdate?moment(data.assignmentdate).format('YYYY-MM-DD'):''}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:50%">最终反馈日期：{{data.finalfeedbackdate?moment(data.finalfeedbackdate).format('YYYY-MM-DD'):''}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">受理通知书号：{{data.acceptancenumber}}</span>
                    <span class="detail-item" style="width:50%">案件转办书号：{{data.transferbooknumber}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:50%">关键字：{{data.keyword}}</span>
                    <span class="detail-item" style="width:50%">投诉内容：{{data.problemdescribe}}</span>
                </p>
            </div>
        </card>
    </div>
</template>

<script>
import moment from 'moment'
import {getMaterialLists}from"../../api/convenient12345"
export default {
    props: ['data','task','casedata',],
    name: 'consumeInfoLocal',
    data() {
        return {
            moment:moment,
            collapseModel: ['provider', 'subject', 'keti', 'handle', 'specific','processing'],
            fileList:[],
            requestMap:{
                caseguid:""
            }
        }
    },
    mounted(){
        let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.task.caseguid
                };
                console.log(params)
                getMaterialLists(params).then(res => {
                    if (res.code === 0) {
                        console.log(res)
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            //this.fileListsAccept.push(fileInfo);
                            this.fileList.push(fileInfo);
                        }
                    }
                });
    }
}
</script>

<style lang="less" scoped>
.card-box{padding: 14PX;}
// .detail-item{text-overflow: ellipsis;overflow: hidden;white-space:nowrap;display:inline-block;}
.detail-item{display:inline-block;}
</style>