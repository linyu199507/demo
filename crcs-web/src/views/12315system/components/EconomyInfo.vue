<!--经济违法详细子组件-->
<template>
  <el-tabs v-model="tabFormActionName" style="margin-top:30px;">
    <el-collapse v-model="collapseFormValues" class="accordion">

      <el-collapse-item name="processing">
        <div slot="title"  @click.stop>
          <el-row class="min_title">
            <el-col :span="12">
              系统信息
            </el-col>
            <el-col :span="10" class="right">
              表单号：
              {{tasklist.serialnum}}
            </el-col>
             <el-col :span="10" class="right"></el-col>
          </el-row>
        </div>
        <el-row>

          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">登记编号：</el-col>
            <el-col :span="16">{{economyform.registrationnumber}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">记录类型：</el-col>
            <el-col :span="16">{{economyform.formtype}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">登记日期：</el-col>
            <el-col :span="16">{{$moment(this.economyform.recorddate).format("YYYY-MM-DD HH:mm:ss")}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">接收方式：</el-col>
            <el-col :span="16">{{economyform.receivemode}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">来电号码：</el-col>
            <el-col :span="16">{{economyform.rqstnumber}}</el-col>
          </el-col>

        </el-row>
      </el-collapse-item>

      <el-collapse-item name="provider">
        <div slot="title" @click.stop>
          <el-row class="min_title">
            <el-col :span="12">提供方信息</el-col>
            <el-col :span="10" class="right">
            </el-col>
          </el-row>
        </div>
        <el-row>
          <el-col class="item_col" :xs="24" :sm="24" :md="12" :lg="8">
            <el-col :span="8" class="label">标题：</el-col>
            <el-col :span="16">{{economyform.rqsttitle}}</el-col>
          </el-col>
          <el-col class="item_col" :xs="24" :sm="24" :md="12" :lg="8">
            <el-col :span="8" class="label">投诉人姓名/单位：</el-col>
            <el-col :span="16">{{economyform.rqstperson}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">性别：</el-col>
            <el-col :span="16">{{economyform.gender}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">举报人联系电话：</el-col>
            <el-col :span="16">{{economyform.linknumber}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">证件类型：</el-col>
            <el-col :span="16">{{economyform.certificate}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">证件号码：</el-col>
            <el-col :span="16">{{economyform.idnumber}}</el-col>
          </el-col>
          <el-col class="item_col" :xs="24" :sm="24" :md="12" :lg="8">
            <el-col :span="8" class="label">提供方类型：</el-col>
            <el-col :span="16">{{economyform.usertype}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">诉求来源：</el-col>
            <el-col :span="16">{{economyform.appealsource}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">年龄段：</el-col>
            <el-col :span="16">{{economyform.age}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">电子邮箱：</el-col>
            <el-col :span="16">{{economyform.informerpostcode}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">国籍：</el-col>
            <el-col :span="16">{{economyform.nationality}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">民族：</el-col>
            <el-col :span="16">{{economyform.nation}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">身份：</el-col>
            <el-col :span="16">{{economyform.status}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">邮编：</el-col>
            <el-col :span="16">{{economyform.postcode}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">其他联系方式：</el-col>
            <el-col :span="16">{{economyform.othercontacts}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="16" class="item_col">
            <el-col :span="4" class="label">举报人地址：</el-col>
            <el-col :span="16">{{economyform.rqstaddress}}</el-col>
          </el-col>
        </el-row>
      </el-collapse-item>
      <!-- 涉及主体信息 -->
      <el-collapse-item name="subjectInformation">
        <div slot="title" @click.stop>
          <el-row class="min_title">
            <el-col :span="12">涉及主体信息</el-col>
            <el-col :span="10" class="right"></el-col>
          </el-row>
        </div>
        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">被举报对象名称：</el-col>
            <el-col :span="16">{{economyform.subjectname}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">联系人：</el-col>
            <el-col :span="16">{{economyform.reportedcontact}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">联系电话：</el-col>
            <el-col :span="16">{{economyform.subjectphone}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">行业分类：</el-col>
            <el-col :span="16">{{economyform.industryname}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">社会信用代码：</el-col>
            <el-col :span="16">{{economyform.registerno}}</el-col>
          </el-col>
         <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">市场主体类型：</el-col>
            <el-col :span="16">{{economyform.providetype}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="16" class="item_col">
            <el-col :span="4" class="label">被举报对象地址：</el-col>
            <el-col :span="16">{{economyform.subjectaddress}}</el-col>
          </el-col>
        </el-row>
      </el-collapse-item>
      <!-- 业务信息信息 -->
      <el-collapse-item name="objectInformation">
        <div slot="title" @click.stop>
          <el-row class="min_title">
            <el-col :span="12">业务信息</el-col>
            <el-col :span="10" class="right"></el-col>
          </el-row>
        </div>
        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">客体类别：</el-col>
            <el-col :span="16">{{economyform.objectcategory}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">业务分类：</el-col>
            <el-col :span="16">{{economyform.businesstypenew}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">商品/服务名称：</el-col>
            <el-col :span="16">{{economyform.objname}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">商品品牌：</el-col>
            <el-col :span="16">{{economyform.brand}}</el-col>
          </el-col>
          <!--<el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">-->
            <!--<el-col :span="8" class="label">销售方式：</el-col>-->
            <!--<el-col :span="16">{{economyform.sellingformat}}</el-col>-->
          <!--</el-col>-->
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">是否网购：</el-col>
            <el-col :span="16">{{economyform.isweb}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">电商平台：</el-col>
            <el-col :span="16">{{economyform.shoppingplatform}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">消费金额：</el-col>
            <el-col :span="16">{{economyform.objmoney}} 元</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">订单号：</el-col>
            <el-col :span="16">{{economyform.ordernumber}}</el-col>
          </el-col>
          <!--<el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
            <el-col :span="8" class="label">争议发生时间：</el-col>
            <el-col :span="16">{{economyform.disputedate}}</el-col>
          </el-col>-->
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">举报问题类别：</el-col>
            <el-col :span="16">{{economyform.reportprovidelb}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">产品名称：</el-col>
            <el-col :span="16">{{economyform.productname}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">批准文号：</el-col>
            <el-col :span="16">{{economyform.approvalnumber}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">产品批号：</el-col>
            <el-col :span="16">{{economyform.lotnumber}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">产品规格：</el-col>
            <el-col :span="16">{{economyform.typespecification}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">生产厂家：</el-col>
            <el-col :span="16">{{economyform.producecompany}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">关键字：</el-col>
            <el-col :span="16">{{economyform.keyword}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">销售企业：</el-col>
            <el-col :span="16">{{economyform.salesenterprise}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">产品有效期：</el-col>
            <!--<el-col :span="16">{{economyform.expirationdate}}</el-col>-->
            <el-col :span="16">{{this.economyform.expirationdate?$moment(this.economyform.expirationdate).format("YYYY-MM-DD"):''}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col">
            <el-col :span="3" class="label w110">举报内容：</el-col>
            <el-col :span="21">{{economyform.problemdescribe}}</el-col>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="8" class="item_col">
            <el-col :span="8" class="label">相关附件：</el-col>
            <el-upload
                    :auto-upload="false"
                    :file-list="fileLists"
                    ref="upload"
                    :data="this.requestMap"
                    :on-preview="previewFile"
                    :on-success="uploadSuccess"
                    :before-upload="beforeUpload"
                    :multiple="true"
                    action="/crcs/filesUpload/upload"
            >
            </el-upload>
          </el-col>
        </el-row>
      </el-collapse-item>
      <!--mosx-->
      <el-collapse-item name="officeInfo">
        <div slot="title" @click.stop>
          <el-row class="el-accordion title_accordion title_accordion">
            <el-col :span="12">
              <em>3.</em> 处办要求
            </el-col>
            <el-col :span="10"></el-col>
          </el-row>
        </div>
        <el-form label-width="220px" ref="handledemand">
          <el-row>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">应立案时限：</el-col>
              <el-col :span="16">{{formatDate(economyform.feedbacktime)}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">应反馈时间：</el-col>
              <el-col :span="16">{{formatDate(economyform.todealwithtime)}}</el-col>
            </el-col>
          </el-row>
        </el-form>
      </el-collapse-item>

    </el-collapse>
  </el-tabs>
</template>

<script>

    import { getMaterialLists } from "../../../api/convenient12315";

export default {
  name: "EconomyInfo",
  props: ["tasklist", "economyform",'casedata',],
  data() {
    return {
// 材料列表
        fileLists: [],
        fileListsAccept:[],
        materialList: [],
        requestMap: {
            caseguid: ""
        },
      collapseFormValues: [
        "provider",
        "subjectInformation",
        "objectInformation",
        "specificInformation",
        "correlationInformation",
        ,'processing',
              "officeInfo"
      ],
      tabFormActionName: "tabRegister"
    };
  },
  methods: {

      previewFile(file) {
          if (file.response !== undefined) {
              this.download(file.response.data.url)
          } else {
              this.download(file.url)
          }
      },

      download(url){
          // window.location.href = `${process.env.Download_URL}` + url;
          window.open(`${process.env.Download_URL}` + url);
      },


      uploadSuccess(response, file, fileList) {
          if (file.response.code === 0) {
              this.$message({
                  message: "上传成功",
                  type: "success"
              });
          } else {
              this.$message.error("上传失败" + file.response.msg);
          }
      },

      beforeUpload(file) {
          // console.log(file)
          let fileName = file.name;
          let pos = fileName.lastIndexOf(".");
          const isLt = file.size / 1024 / 1024 / 20 <= 1;
          // console.log(file.size / 1024 / 1024 / 20);
          let lastName = fileName.substring(pos, fileName.length);
          let arrays = [
              ".docx",
              ".doc",
              ".txt",
              ".xls",
              ".jpg",
              ".png",
              ".pfg",
              ".flv",
              ".rmvb",
              ".mvb",
              ".mp4",
              ".xlsx"
          ];
          if (arrays.indexOf(lastName) === -1 || isLt === false) {
              this.$alert(
                  "不支持上传该格式的文件,请上传docx，doc，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                  "提示",
                  {
                      confirmButtonText: "确定"
                  }
              );
              return false;
          }
      },

      submitUpload() {
          console.log(11)
          this.requestMap.caseguid = this.complaintform.caseguid;
          this.$refs.upload.submit();
      },

      getMaterialLists(caseguid) {
          let params = {
              tserialnum: "",
              rqsttitle: "",
              begindate: "",
              enddate: "",
              size: 30,
              page: 1,
              caseguid: caseguid
          };
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
                      this.fileListsAccept.push(fileInfo);
                      this.fileLists.push(fileInfo);
                  }
              }
          });
      },

    formatDate(value) {
      return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
    },

  },
    created() {
        // await this.listSubjectType();
        // this.getBaseInfo();
        // this.getSubjecttype1();

        let {id, caseguid} = this.$route.query;
        this.caseguid = this.$route.query.caseguid;
        console.log(this.caseguid)
        this.getMaterialLists(this.caseguid);

    }
};
</script>

<style lang="scss" scoped>
.label {
  font-size: 13px;
  color: #48576a;
  font-weight: bold;
  text-align: right;
}
.item_col {
  padding: 5px 0 0 0;
  /* border-bottom: 1px solid #e8e8e8; */
}
.w110 {
  width: 110px;
}
.bt {
  margin: 5px auto;
  font-size: 13px;
  color: #48576a;
  font-weight: bold;
  text-align: right;
}

.bts {
  margin: 10px auto;
  font-size: 13px;
  color: #48576a;
  font-weight: bold;
  text-align: right;
}

.st {
  margin: 5px auto;
  font-size: 13px;
  /*text-align: center;*/
}

.br {
  padding-bottom: 0;
  margin-bottom: 10px;
  /* border-bottom: 1px solid #e8e8e8; */
}
.el-form .el-row {
  margin: 0!important;
}
</style>
