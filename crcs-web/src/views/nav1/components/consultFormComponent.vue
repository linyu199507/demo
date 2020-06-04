<template>
  <div>
    <el-form
      :model="commommodule"
      label-width="150px"
      ref="commommodule"
      :rules="commommoduleRules"
    >
      <el-row></el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="12" :lg="20">
          <el-form-item label="表单类型:" class="bt">
            <div style="line-height: 35px">{{commommodule.formtype}}</div>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8" :key="commommodule.businesstypecodeid">
          <el-form-item label="业务分类:" prop="businesstypecodeid" class="bt">
            <!--v-model="commommodule.businesstype"-->
            <el-cascader
              :options="classify"
              v-model="commommodule.businesstypecodeid"
              @change="handleChangebusinesstype1"
              :show-all-levels="true"
              class="bt1"
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="姓名:" prop="name" class="bt">
            <el-input placeholder="请输入姓名" v-model="commommodule.name" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="性别:" prop="gender" class="bt">
            <el-select v-model="commommodule.gender" placeholder="请选择性别" class="input-text">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8" class="bt">
          <el-form-item label="来电号码:" prop="rqstnumber">
            <el-input placeholder="请输入来电号码" v-model="commommodule.rqstnumber" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="联系电话:" prop="linknumber" class="bt">
            <el-input placeholder="请输入联系电话" v-model="commommodule.linknumber" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="是否紧急:" prop="worktype" class="bt">
            <el-select v-model="commommodule.worktype" placeholder="请选择" class="input-text">
              <el-option label="紧急" value="紧急"></el-option>
              <el-option label="普通" value="普通"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="类别:" prop="category" class="bt">
            <el-select v-model="commommodule.category" placeholder="请选择反映类型" class="input-text">
              <el-option label="儿童" value="儿童"></el-option>
              <el-option label="妇女" value="妇女"></el-option>
              <el-option label="老人" value="老人"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="16">
          <el-form-item label="咨询人地址:" class="bt" prop="address">
            <el-select v-model="commommodule.region" placeholder="区域" class="input-texts">
              <el-option label="乐从镇" value="乐从镇"></el-option>
              <el-option label="陈村镇" value="陈村镇"></el-option>
              <el-option label="勒流镇" value="勒流镇"></el-option>
              <el-option label="大良镇" value="大良镇"></el-option>
              <el-option label="杏坛镇" value="杏坛镇"></el-option>
              <el-option label="均安镇" value="均安镇"></el-option>
              <el-option label="容桂镇" value="容桂镇"></el-option>
              <el-option label="龙江镇" value="龙江镇"></el-option>
              <el-option label="伦教镇" value="伦教镇"></el-option>
              <el-option label="北滘镇" value="北滘镇"></el-option>
            </el-select>
            <el-input
              placeholder="请输入地址"
              v-model="commommodule.address"
              class="input-text"
              style="width:74%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="反映类型:" prop="reflectiontype" class="bt">
            <el-select
              v-model="commommodule.reflectiontype"
              placeholder="请选择反映类型"
              class="input-text"
            >
              <el-option label="个人" value="个人"></el-option>
              <el-option label="企业" value="企业"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="工单分类:" prop="Focus" class="bt">
            <el-select v-model="commommodule.focus" placeholder="请选择工单分类" class="input-text">
              <el-option label="普通" value="普通"></el-option>
              <el-option label="特殊" value="特殊"></el-option>
              <el-option label="特派" value="特派"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="诉求来源:" prop="appealsource" class="bt">
            <el-select placeholder="请选择类型" class="input-text" v-model="commommodule.appealsource">
              <el-option label="来函" value="来函"></el-option>
              <el-option label="来人来访" value="来人来访"></el-option>
              <el-option label="电话" value="电话"></el-option>
              <el-option label="传真" value="传真"></el-option>
              <el-option label="省、市移送" value="省、市移送"></el-option>
              <el-option label="烟草移送" value="烟草移送"></el-option>
              <el-option label="其他部门移送" value="其他部门移送"></el-option>
              <el-option label="互联网络" value="互联网络"></el-option>
              <el-option label="抽检" value="抽检"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="24" :span="16">
          <el-form-item label="标题:" prop="rqsttitle" class="bt">
            <el-input v-model="commommodule.rqsttitle" placeholder="请输入标题" class="input-text"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="问题描述:" prop="rqstcontent" class="bt">
            <el-input
              type="textarea"
              :autosize="{ minRows: 6, maxRows: 8}"
              placeholder="请输入问题描述(3000字以内)"
              v-model="commommodule.rqstcontent"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8">
          <el-form-item label="咨询表单相关附件:" class="bt">
            <el-upload
              :auto-upload="false"
              :file-list="fileListscommommodule"
              ref="uploadCommommodule"
              :data="this.requestMap"
              :on-preview="previewFile"
              :on-progress="progress"
              :on-success="uploadSuccess"
              :before-upload="beforeUpload"
              :multiple="true"
              action="/crcs/filesUpload/upload"
            >
              <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
              <el-button
                size="small"
                type="success"
                @click="submitUpload('uploadCommommodule')"
              >确认上传</el-button>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      commommoduleRules: {
        businesstypecodeid: [{ required: true, message: "请选择业务分类" }],
        worktype: [
          { required: true, message: "请选择是否紧急", trigger: "change" }
        ],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        // gender: [{required: true, message: "请选择性别", trigger: "change"}],
        rqstnumber: [
          { required: true, message: "请输入来电号码", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        linknumber: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { max: 30, message: "不能超出30个字符", trigger: "blur" }
        ],
        reflectiontype: [{ required: true, message: "请选择反映类型" }],
        category: [
          { required: true, message: "请选择分类", trigger: "change" }
        ],
        focus: [
          { required: true, message: "请选择工单分类", trigger: "change" }
        ],
        appealsource: [
          { required: true, message: "请选择诉求来源", trigger: "change" }
        ],
        rqsttitle: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { max: 200, message: "不能超出200个字符", trigger: "blur" }
        ],
        rqstcontent: [
          { required: true, message: "请输入问题描述", trigger: "blur" },
          { max: 3000, message: "不能超过3000个字符", trigger: "blur" }
        ],
        address: [
          { required: true, message: "请输入地址", trigger: "blur" },
          { max: 200, message: "不能超过200个字符", trigger: "blur" }
        ]
      },
      commommodule: {
        id: "",
        caseguid: "",
        formtype: "咨询",
        businesstype: "",
        name: "",
        gender: "",
        rqstnumber: "",
        linknumber: "",
        informationOpen: "",
        address: "",
        focus: "普通",
        appealsource: "",
        rqsttitle: "",
        rqstcontent: "",
        attachfilename: "",
        reflectiontype: "",
        worktype: "",
        sdexpirationdate: "",
        usertype: "",
        category: "",
        businesstypecodeid: "",
        region: ""
      },
      businesstype1: []
    };
  },
  props: ["classify"],
  mounted() {
    const uuidV1 = require("uuid/v1");
     this.randomId = uuidV1();
      this.requestMap.caseguid = uuidV1();
  },
  methods: {
     previewFile(file) {
      if (file.response !== undefined) {
      this.download(file.response.data.url)
      } else {
      this.download(file.url)
      }
    },
      download(url) {
      // window.location.href = `${process.env.Download_URL}` + url;
      window.open(`${process.env.Download_URL}` + url,"_blank")
    },
    getMaterial(caseguid) {
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
          let fileInfo;
          for (let i in res.data.records) {
            fileInfo = {
              name: res.data.records[i].attachfilename,
              url: res.data.records[i].filesavepath
            };
            if (this.commommodule.rqsttitle !== "") {
              this.fileListscommommodule.push(fileInfo);
            } else if (this.complaintmodule.rqsttitle !== "") {
              //  this.fileLists.push(fileInfo)
              return;
            } else if (this.comeconomicmodule.rqsttitle !== "") {
              this.fileLists.push(fileInfo);
            }

            // this.fileListsCom.push(fileInfo);
          }
        }
      });
    },
    submitUpload(value) {
      if (this.$route.query.caseguid === undefined) {
        this.requestMap.caseguid = this.randomId;
      } else {
        this.requestMap.caseguid = this.$route.query.caseguid;
      }
      this.$refs[value].submit();
    }
  },
};
</script>