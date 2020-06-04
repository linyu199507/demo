<!--咨询或者通用表单子组件-->
<template>
  <el-tabs v-model="tabFormActionName">
    <el-tab-pane label="登记信息" name="tabRegister">
      <el-collapse v-model="collapseFormValues" class="accordion">
        <el-collapse-item name="provider">
          <div slot="title" @click.stop>
            <el-row class="min_title">
              <el-col :span="12">提供方信息</el-col>
              <el-col :span="10" class="right"></el-col>
              <el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>
            </el-row>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">表单类型：</el-col>
              <el-col :span="16">{{formData.formtype}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">表单模板：</el-col>
              <el-col :span="16">{{formData.formmodule}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">标题：</el-col>
              <el-col :span="16">{{formData.rqsttitle}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">姓名：</el-col>
              <el-col :span="16">{{formData.name}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">性别：</el-col>
              <el-col :span="16">{{formData.gender}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">是否有效：</el-col>
              <el-col :span="16">{{formData.isvalid}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">来电号码：</el-col>
              <el-col :span="16">{{formData.rqstphonenumber}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">联系电话：</el-col>
              <el-col :span="16">{{formData.linkphonenumber}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">反映类型：</el-col>
              <el-col :span="16">{{formData.usertype}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">回复方式：</el-col>
              <el-col :span="16">{{formData.answertype}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">企业类型：</el-col>
              <el-col :span="16">{{formData.entitytype}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">企业全称：</el-col>
              <el-col :span="16">{{formData.subjectname}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">企业登记机关：</el-col>
              <el-col :span="16">{{formData.aicid}}</el-col>
            </el-col>

            <el-col :xs="24" :sm="24" :md="24" :lg="12" class="item_col">
              <el-col :span="8" class="label">企业经营地址：</el-col>
              <el-col :span="16">{{formData.address}}</el-col>
            </el-col>
            <el-col class="item_col">
              <el-col :span="4" class="label">工单分类：</el-col>
              <el-col :span="20">{{formData.focus}}</el-col>
            </el-col>
            <el-col class="item_col">
              <el-col :span="4" class="label">涉及问题分类：</el-col>
              <el-col :span="20">{{formData.complaintstype}}</el-col>
            </el-col>
            <el-col class="item_col">
              <el-col :span="4" class="label">问题描述：</el-col>
              <el-col :span="20">{{formData.rqstcontent}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
              <el-col :span="8" class="label">政务标签：</el-col>
              <el-col :span="16">{{formData.govlabel}}</el-col>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" class="item_col">
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
        <el-collapse-item name="processing" v-if="casedata != null">
          <div slot="title" @click.stop="">
            <el-row class="el-accordion">
              <el-col :span="12">
                待审批处理反馈
              </el-col>
              <el-col :span="10" style="color: #48576a;text-align: right">
              </el-col>
            </el-row>
          </div>
          <template>
            <el-form label-width="150px">
              <el-row>
                <el-col :xs="24" :sm="24" :md="24" :lg="12">
                  <el-col :span="8" class="bt">处理结果：</el-col>
                  <el-col :span="14" class="st">{{casedata.handleopinion}}</el-col>
                </el-col>
                <!--                            <el-col class="br"></el-col>-->
                <!--                            <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                <!--                                <el-col :span="8" class="bt">附件清单：</el-col>-->
                <!--                                <el-col :span="14" class="st"></el-col>-->
                <!--                            </el-col>-->
              </el-row>
            </el-form>
          </template>
        </el-collapse-item>
      </el-collapse>
    </el-tab-pane>
<!--    <el-tab-pane label="处理信息" name="tabHandle"></el-tab-pane>-->
  </el-tabs>
</template>

<script>
export default {
  name: "ConsultationInfo",
  props: ["tasklist", "formData","casedata"], //formData可能是通用表单或者咨询表单
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
        'processing',
      ],
      tabFormActionName: "tabRegister"
    };
  },
  methods: {

      previewFile(file) {
          if (file.response !== undefined) {
              console.log(file)
              this.download(file.response.data.url)
          } else {
              console.log(file)
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
    }
  },
    created() {
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
</style>
