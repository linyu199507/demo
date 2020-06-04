<template>
    <div class="main-container">
        <card :header="{title: '1、表单信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:65%">表单号：{{task.tserialnum}}</span>
                    <span class="detail-item" style="width:30%">业务：咨询</span>
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
                    <span class="detail-item" style="width:65%">表单号：{{task.serialnum}}</span>
                    <span class="detail-item" style="width:30%">咨询方：{{data.name}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">联系电话：{{data.rqstnumber}}</span>
                    <span class="detail-item" style="width:25%">性别：{{data.gender}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">咨询方地址：{{data.consultantaddress}}</span>
                    <span class="detail-item" style="width:25%">是否职投：{{data.isprofessional}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '3、涉及主体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:65%">涉及主体名称：{{data.entname}}</span>
                    <span class="detail-item" style="width:30%">所在区域：{{data.region}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">被举报对象地址：{{data.address}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">联系电话：{{data.linknumber}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '4、业务具体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:45%">分类：{{data.complaintstypecodeid}}</span>
                    <span class="detail-item" style="width:50%">关键字：{{data.keyword}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">案件接收日期：{{data.acceptdate?moment(data.acceptdate).format('YYYY-MM-DD'):''}}</span>
                    <span class="detail-item" style="width:50%">最终反馈日期：{{data.finalfeedbackdate?moment(data.finalfeedbackdate).format('YYYY-MM-DD'):''}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">举报内容：{{data.rqstcontent}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '待审批处理反馈'}" v-if="casedata != null">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">处理结果：{{casedata.handleopinion}}</span>
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
    name: 'consultInfoLocal',
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