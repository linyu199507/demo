<template>
  <div>
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">涉及主体信息</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input
          title="涉及主体"
          v-model="caseprocesform.subjectname"
          value-align="left"
          placeholder="必填"
        ></x-input>
        <x-textarea
          title="经营地址"
          v-model="caseprocesform.businessaddress"
          placeholder="请填写经营地址(必填)"
          :show-counter="false"
          :rows="2"
        ></x-textarea>
        <x-textarea
          title="核查经营名称"
          semaknamajing
          v-model="caseprocesform.semaknama"
          placeholder="请填写核查经营名称"
          :show-counter="false"
          :rows="2"
        ></x-textarea>
        <!-- 待定 -->
        <!-- <popup-picker
          title="核查经营地区"
          placeholder="请选择"
          :data="operationAreaOptions"
          v-model="operationArea"
          value-text-align="left"
        ></popup-picker>-->
        <popup-picker
          title="所在地区"
          placeholder="请选择所在地区"
          :data="addressData"
          v-model="caseprocesform.region"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="核查经营地址"
          v-model="caseprocesform.semakbusinessaddress"
          placeholder="请填写地址"
          :show-counter="false"
          :rows="2"
        ></x-textarea>
        <!-- 基础数据 -->
        <popup-picker
          title="主体类别和类型"
          placeholder="必选"
          :data="subjectCategoryOptions"
          v-model="subjectCategory"
          value-text-align="left"
        ></popup-picker>
      </group>
    </div>

    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">案件信息</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <popup-picker
          title="是否立案"
          placeholder="请选择(必选)"
          :data="fileACaseOptions"
          :key="fileACase[0]"
          v-model="fileACase"
          value-text-align="left"
        ></popup-picker>
      </group>
    </div>

    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">初查情况</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <x-input
          title="处理期限(工作日)"
          disabled="true"
          v-model="caseprocesform.limitdate"
          value-align="left"
          placeholder="请输入处理期限"
        ></x-input>
        <popup-picker
          title="属实情况"
          placeholder="请选择属实情况(必选)"
          :key="trueCondition[0]"
          :data="trueConditionOptions"
          v-model="trueCondition"
          value-text-align="left"
        ></popup-picker>

        <x-textarea
          title="调查情况"
          v-model="caseprocesform.verifiedopinion"
          placeholder="请填写调查情况"
          :show-counter="false"
          :rows="10"
        ></x-textarea>
        <!-- 文件上传 -->
        <div class="upload">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :auto-upload="false"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button
              style="margin-left: 10px;"
              size="small"
              type="success"
              @click="submitUpload"
            >上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </div>
      </group>
    </div>



    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">处理</x-button>
      <x-button type="default" mini @click.native="showChoose1">取消</x-button>
    </div>
  </div>
</template>
<script>
import { searchBaseInfo, nextWork } from '../api/convenient12345';
import { parse } from 'path';

export default {
  data() {
    return {
      subjectCategoryOptions: [['其他']],
      addressData: [['禅城区', '南海区', '顺德区', '高明区', '三水区']],
      operationAreaOptions: [
        ['禅城区', '南海区', '顺德区', '高明区', '三水区'],
      ],
      trueConditionOptions: [['属实', '不属实', '待定']],
      fileACaseOptions: [['是', '否']],
      businessAddress: '',
      businessName: '',
      subjectInvolved: '',
      upload: '',
      subjectCategory: [],
      fileList: [],
      trueCondition: [],
      fileACase: [],
      operationArea: [],
      id: '',
      caseguid: '',
      systemsource: '',
      // 立案处理内容
      caseprocesform: {
        subjectname: '',
        businessaddress: '',
        semaknama: '',
        semakbusinessaddress: '',
        limitdate: 60,
        verifiedopinion: '',
      },
      user: JSON.parse(localStorage.getItem('user')),

      // 经济表单内容
      economyForm: {},
      // 当前环节
      currentLink: '',
    };
  },
  methods: {
    handlePreview() {},
    handleRemove() {},
    submitUpload() {},
    arrayTransferString(data) {
      for (const key in data) {
        if (data[key] instanceof Array) {
          if (data[key].length > 0) {
            data[key] = data[key].pop();
          } else {
            data[key] = null;
          }
        }
      }
    },
    showChoose() {
      console.log('jjj')
      if (this.caseprocesform.subjectname === ''
        || this.subjectCategory === []
        || this.caseprocesform.businessaddress === ''
        || this.caseprocesform.semaknama === ''
        || this.fileACase === []
        || this.caseprocesform.limitdate === ''
        || this.trueCondition === []
        || this.caseprocesform.verifiedopinion === '') {
        console.log('123456');
        this.$vux.alert.show({
          title: '提示',
          content: '还有信息没填完',
        });
      } else {
        this.caseprocesform.subjectTypeValue = this.subjectCategory[0];
        this.caseprocesform.isverified = this.trueCondition[0];
        this.caseprocesform.isregister = this.fileACase[0];
        console.log(this.caseprocesform.subjectTypeValue)
        console.log(this.caseprocesform.isverified)
        console.log(this.caseprocesform.isregister)
        const caseprocesform = JSON.parse(JSON.stringify(this.caseprocesform));
        this.arrayTransferString(caseprocesform);
        const params = {
          user: this.user,
          formType: 'comeconomicmodule',
          node: this.currentLink,
          nextNodeId: 'registerCase',
          data: this.economyForm,
          caseprocesform: caseprocesform,
          opinion: caseprocesform.verifiedopinion,
        };
        console.log(params)
        // return " ";
        nextWork(params).then((res) => {
          console.log(res)
          if (res.code === 0) {
            this.$vux.alert.show({
              title: '提示',
              content: '立案成功',
            });
            // return "";
            const para = {
              caseguid: res.data.caseguid,
              id: res.data.id,
              systemsource: this.systemsource,
              nextnodeId: 'registerCase',
            };
            this.$router.push({
              path: '/main/Economy/acceptedOfEconomyDetail',
              query: para,
            });
          } else {
            this.$vux.alert.show({
              title: '提示',
              content: res.msg,
            });
          }
        });
      }
    },
    getCaseFilingDatail() {
      const params = {
        id: this.$route.query.id,
        caseguid: this.$route.query.caseguid,
        systemsource: this.$route.query.systemsource,
      };
      searchBaseInfo(params).then((res) => {
        // 涉及主体 经营地址  核查经营地址 核查经营名称
        if (res.code === 0) {
          this.economyForm = res.data.economyform;
          this.id = this.economyForm.id;
          this.caseguid = this.economyForm.caseguid;
          this.currentLink = res.data.tasklist.nextnodeid;
          this.systemsource = res.data.tasklist.systemsource;
          this.nextnodeid = res.data.tasklist.nextnodeid;
          // 核查经营名称
          if (this.economyForm.semaknama !== '') {
            this.caseprocesform.semaknama = this.economyForm.semaknama;
          }

          if (this.economyForm.subjectname !== '') {
            this.caseprocesform.subjectname = this.economyForm.subjectname;
          }
          // 核查经营地址
          if (this.economyForm.subjectaddress !== '') {
            this.caseprocesform.businessaddress = this.economyForm.subjectaddress;
          }
          console.log(res.data.caseprocesform);
          // if (res.data.caseprocesform !== null) {
          if (res.data.caseprocesform.isverified !== '') {
            this.trueCondition[0] = res.data.caseprocesform.isverified;
          }
          if (res.data.caseprocesform.isregister !== '') {
            this.fileACase[0] = res.data.caseprocesform.isregister;
          }
          // }
        }
      });
    },
  },
  mounted() {
    this.getCaseFilingDatail();
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);
.dispatchedWhole {
  width: 90%;
  margin: 0 auto;
  border-radius: 20px;
  padding-bottom: 20px;
  margin-top: 20px;
  background: #fff;
  box-shadow: #eee 2px 2px 5px 2px;
  font-size: @fontStyleOfTitle;
  padding-top: 20px;
  .header {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    border-bottom: 2px solid #d9d9d9;
    span {
      display: inline-block;
      width: 5px;
      height: 40px;
      margin-right: 20px;
      margin-left: 20px;
      background: @color;
      vertical-align: middle;
    }
    .title {
      vertical-align: middle;
      font-size: 40px;
    }
  }
}
.detail {
  text-align: center;
  margin-top: 100px;
}
.upload {
  padding: 20px 30px;
}
</style>
<style lang="less">
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

.weui-wepay-flow,
.weui-wepay-flow-auto {
  padding: 150px 40px 20px 40px !important;
}
.weui-wepay-flow_vertical .weui-wepay-flow__bd {
  width: 10px;
}
.weui-cell__ft {
  text-align: left !important;
}
.weui-cell:before {
  left: 0 !important;
}
.weui-cells:before {
  border-top: none !important;
}
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.vux-cell-box:not(:first-child):before {
  left: 0 !important;
}
.weui-wepay-flow__li_done .weui-wepay-flow__state {
  background: @backgroundColor!important;
}
.weui-wepay-flow__process {
  background: @backgroundColor!important;
}
.vux-popup-header-right {
  color: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: @backgroundColor!important;
}
.vux-popup-header-right,
.weui-search-bar__cancel-btn {
  color: @backgroundColor!important;
}
.weui-btn_primary {
  background: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: @backgroundColor!important;
}
</style>
