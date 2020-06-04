<template>
  <div class="main-container">
    <card :header="{title: '1、系统信息'}">
      <div slot="content" class="card-box">
        <p>
          <span class="detail-item" style="width:65%">工单号：{{task.tserialnum}}</span>
          <span class="detail-item" style="width:30%">记录类型：{{data.formtype}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">登记编号：{{data.registrationnumber}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">登记日期：{{data.recorddate?(moment(data.recorddate).format("YYYY-MM-DD HH:mm:ss")):''}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">接收方式：{{data.receivemode}}</span>
          <span class="detail-item" style="width:50%">来电号码：{{data.rqstnumber}}</span>
        </p>
      </div>
    </card>
    <card :header="{title: '2、提供方信息'}">
      <div slot="content" class="card-box">
        <p>
          <span class="detail-item" style="width:100%">表单号：{{task.serialnum}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">标题：{{data.rqsttitle}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:70%">投诉人姓名/单位：{{data.rqstperson}}</span>
          <span class="detail-item" style="width:25%">性别：{{data.gender}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">投诉人联系电话：{{data.linknumber}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:35%">证件类型：{{data.certificate}}</span>
          <span class="detail-item" style="width:60%">证件号码：{{data.idnumber}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">反映类型：{{data.usertype}}</span>
          <span class="detail-item" style="width:50%">诉求来源：{{data.appealsource}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:40%">类别：{{data.providelb}}</span>
          <span class="detail-item" style="width:55%">年龄段：{{data.age}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">电子邮箱：{{data.informerpostcode}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">国籍：{{data.nationality}}</span>
          <span class="detail-item" style="width:50%">民族：{{data.nation}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">身份：{{data.status}}</span>
          <span class="detail-item" style="width:50%">邮编：{{data.postcode}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">投诉人地址：{{data.rqstaddress}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">其他联系方式：{{data.othercontacts}}</span>
        </p>
      </div>
    </card>
    <card :header="{title: '3、涉及主体信息'}">
      <div slot="content" class="card-box">
        <p>
          <span class="detail-item" style="width:100%">被投诉对象名称：{{data.subjectname}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">被投诉对象地址：{{data.subjectaddress}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:50%">市场主体类型：{{data.providetype}}</span>
          <span class="detail-item" style="width:45%">行业分类：{{data.businesstype}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">社会信用代码：{{data.registerno}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:40%">联系人：{{data.reportedcontact}}</span>
          <span class="detail-item" style="width:55%">联系电话：{{data.subjectphone}}</span>
        </p>
      </div>
    </card>
    <card :header="{title: '4、业务具体信息'}">
      <div slot="content" class="card-box">
        <p>
          <span class="detail-item" style="width:100%">电商平台：{{data.unknow}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">客体类别：{{data.objclassify}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">业务分类：{{data.businesstypenew}}</span>
          <span class="detail-item" style="width:50%">商品/服务名称：{{data.objname}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">商品品牌：{{data.brand}}</span>
          <span class="detail-item" style="width:50%">销售方式：{{data.shoppingmode}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:45%">是否网购：{{data.isweb}}</span>
          <span class="detail-item" style="width:50%">消费金额：{{data.objmoney}}元</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">订单号：{{data.ordernumber}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:50%">争议发生时间：{{data.acceptdate?moment(data.acceptdate).format('YYYY-MM-DD'):''}}</span>
            <span class="detail-item" style="width:45%">投诉问题类别：{{data.objmoney}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">诉求内容：{{data.isweb}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:45%">产品名称：{{data.productname}}</span>
            <span class="detail-item" style="width:50%">批准文号：{{data.approvalnumber}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:45%">产品批号：{{data.lotnumber}}</span>
            <span class="detail-item" style="width:50%">产品规格：{{data.typespecification}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:45%">生产厂家：{{data.producecompany}}</span>
            <span class="detail-item" style="width:50%">关键字：{{data.keyword}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:100%">销售企业：{{data.salesenterprise}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:100%">产品有效期：{{data.expirationdate?moment(data.expirationdate).format('YYYY-MM-DD'):''}}</span>
        </p>
        <p>
            <span class="detail-item" style="width:100%">投诉内容：{{data.problemdescribe}}</span>
        </p>
        </div>
    </card>
    <card :header="{title: '5、处办要求'}">
      <div slot="content" class="card-box">
        <p>
          <span class="detail-item" style="width:100%">应受理时限：{{data.feedbacktime?(moment(data.feedbacktime).format("YYYY-MM-DD HH:mm:ss")):''}}</span>
        </p>
        <p>
          <span class="detail-item" style="width:100%">应反馈时间：{{data.todealwithtime?(moment(data.todealwithtime).format("YYYY-MM-DD HH:mm:ss")):''}}</span>
        </p>
      </div>
    </card>
    </div>
</template>

<script>
import moment from "moment";
import { getMaterialLists } from "../../api/convenient12345";
export default {
  props: ["data", "task", "casedata"],
  name: "complaintmoduleInfo12315",
  data() {
    return {
        moment:moment,
      collapseModel: [
        "provider",
        "subject",
        "keti",
        "handle",
        "specific",
        "processing"
      ],
      fileList: [],
      requestMap: {
        caseguid: ""
      }
    };
  },
  mounted() {
    let params = {
      tserialnum: "",
      rqsttitle: "",
      begindate: "",
      enddate: "",
      size: 30,
      page: 1,
      caseguid: this.task.caseguid
    };
    console.log(params);
    getMaterialLists(params).then(res => {
      if (res.code === 0) {
        console.log(res);
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
};
</script>

<style lang="less" scoped>
.card-box {
  padding: 14px;
}
// .detail-item{text-overflow: ellipsis;overflow: hidden;white-space:nowrap;display:inline-block;}
.detail-item {
  display: inline-block;
}
</style>