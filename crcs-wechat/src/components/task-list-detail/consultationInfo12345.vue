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
                    <span class="detail-item" style="width:30%">表单模板：{{data.formmodule}}</span>
                    <span class="detail-item" style="width:65%">姓名：{{data.rqstperson}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:25%">性别：{{data.gender}}</span>
                    <span class="detail-item" style="width:70%">来电号码：{{data.rqstphonenumber}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:70%">联系电话：{{data.linkphonenumber}}</span>
                    <span class="detail-item" style="width:25%">是否有效：{{data.isvalid}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:50%">反映类型：{{data.usertype}}</span>
                    <span class="detail-item" style="width:45%">回复方式：{{data.answertype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:30%">企业类型：{{data.entitytype}}</span>
                    <span class="detail-item" style="width:65%">企业全称：{{data.subjectname}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">企业登记机关：{{data.aicid}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">企业经营地址：{{data.subjectname}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">工单分类：{{data.focus}}</span>
                    <span class="detail-item" style="width:50%">涉及问题分类：{{data.complaintstype}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:100%">问题描述：{{data.rqstcontent}}</span>
                </p>
                <p>
                    <span class="detail-item" style="width:45%">政务标签：{{data.govlabel}}</span>
                </p>
            </div>
        </card>
        <card :header="{title: '待审批处理反馈'}">
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
    name: 'consultationInfo12345',
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