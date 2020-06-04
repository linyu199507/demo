<template>
    <div class="main-container">
        <card :header="{title: '1、提供方信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:65%">表单号：{{task.serialnum}}</span>
                    <span class="detail-item" style="width:30%">表单类型：{{data.formtype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:30%">标题：{{data.rqsttitle}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:30%">表单模板：{{data.accordtype}}</span>
                    <span class="detail-item" style="width:65%">投诉人姓名：{{data.rqstperson}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:25%">性别：{{data.gender}}</span>
                    <span class="detail-item" style="width:70%">联系电话：{{data.rqstnumber}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">投诉人地址：{{data.rqstaddress}}</span>
                    <span class="detail-item" style="width:25%">反映类型：{{data.usertype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">回复方式：{{data.answertype}}</span>
                    <span class="detail-item" style="width:50%">类别：{{data.providelb}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">工单分类：{{data.focus}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '2、涉及主体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:100%">涉及主体名称：{{data.subjectname}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">被举报对象地址：{{data.subjectaddress}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">联系电话：{{data.subjectphone}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '3、涉及客体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:60%">商品/服务名称：{{data.objname}}</span>
                    <span class="detail-item" style="width:35%">商品品牌：{{data.brand}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">涉及客体分类：{{data.objectcategory}}</span>
                    <span class="detail-item" style="width:50%">涉及问题分类：{{data.problemtype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">关键字：{{data.keyword}}</span>
                    <span class="detail-item" style="width:50%">涉及金额：{{data.objmoney}}元</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">生产企业：{{data.producecompany}}</span>
                    <span class="detail-item" style="width:50%">是否网购：{{data.isweb}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '4、业务具体信息'}">
            <div slot="content" class="card-box">
                <p>
                    <span class="detail-item" style="width:45%">涉嫌违法行为：{{data.illegalrules}}</span>
                    <span class="detail-item" style="width:50%">事件级别：{{data.eventlevel}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">政务标签：{{data.govlabel}}</span>
                    <span class="detail-item" style="width:50%">业务分类：{{data.businesstype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">侵权分类：{{data.tortclassify}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">问题描述：{{data.problemdescribe}}</span>
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
    name: 'econnomyInfo12345',
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