<template>
  <div>
    <!-- 受理 -->
    <div v-if="sign === '受理'">
      <group>
        <popup-picker
          title="受理"
          :data="stepOptions"
          v-model="dealStyle"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChangeDetail"
          placeholder="请选择受理方式(必选)"
        ></popup-picker>
      </group>
      <div class="deal">
        <x-button type="primary" @click.native="comfirmAccept">确定</x-button>
      </div>
    </div>

    <!-- 处理反馈 -->
    <div v-if="sign==='处理反馈'  && formType === 'economyform'" class="feekback">
      <group>
        <x-textarea
          title="处理意见"
          v-model="opionion"
          placeholder="请填写3000字以内的处理意见(必填)"
          :show-counter="false"
          :rows="20"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="dealFeekBack">确定</x-button>
      </div>
    </div>

    <!-- 12345咨询处理反馈 -->
    <div v-if="sign==='处理反馈' && formType === 'commommodule'" class="feekback">
      <group>
        <x-textarea
          title="处理意见"
          v-model="opionion"
          placeholder="请填写3000字以内的处理意见(必填)"
          :show-counter="false"
          :rows="20"
        ></x-textarea>
      </group>

      <x-input title="相关附件" value-align="right" placeholder="请选择附件">
        <input type="file" />
      </x-input>

      <div class="deal">
        <x-button type="primary" @click.native="dealFeekBack">确定</x-button>
      </div>
    </div>
    <!-- 分派下级 -->

    <div v-if="sign === '分派下级'">
      <group>
        <popup-picker
          title="成员单位"
          :data="menberOptions"
          v-model="subordinate"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择成员单位"
        ></popup-picker>
      </group>
      <group>
        <popup-picker
          title="抄送部门"
          :data="deptOptions"
          v-model="copyDept"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择抄送部门"
        ></popup-picker>
      </group>
      <group title="分派意见">
        <x-textarea
          :max="200"
          placeholder="请输入分派意见"
          :show-counter="false"
          :height="200"
          :rows="8"
          :cols="30"
          v-model="distributionContent"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="submitAssignment">确定</x-button>
      </div>
    </div>
    <!-- 分派部门 -->
    <div v-if="sign === '分派部门'">
      <group>
        <popup-picker
          title="主导部门"
          :data="deptOptions"
          v-model="menberDept"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择主导部门"
        ></popup-picker>
      </group>
      <group>
        <popup-picker
          title="抄送部门"
          :data="deptOptions"
          v-model="copyDept"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择抄送部门"
        ></popup-picker>
      </group>
      <group title="分派意见">
        <x-textarea
          :max="200"
          :placeholder="('请输入分派意见')"
          :show-counter="false"
          :height="200"
          :rows="8"
          :cols="30"
          v-model="distributionContent"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="submitDepartment">确定</x-button>
      </div>
    </div>

    <!-- 不受理 -->
    <div v-if="sign === '不受理'">
      <group>
        <popup-picker
          title="不受理"
          :data="notAcceptOptions"
          v-model="notAcceptOpinion"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择不受理原因"
        ></popup-picker>
      </group>
      <div class="copyPeople"></div>
      <group title="不受理内容">
        <x-textarea
          :max="200"
          :placeholder="('请输入不受理内容')"
          :show-counter="false"
          :height="200"
          :rows="8"
          :cols="30"
          v-model="notAcceptContent"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="comfirm">确定</x-button>
      </div>
    </div>
    <!-- 意见填写 -->
    <div v-if="sign === '意见填写'">
      <group title="意见">
        <x-textarea
          :max="200"
          placeholder="请输入意见3000字以内(必填)"
          :show-counter="false"
          :height="200"
          :rows="8"
          :cols="30"
          v-model="opinionCompletion"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="comfirmOpinion">确定</x-button>
      </div>
    </div>

    <!-- 分派人员 -->
    <div v-if="sign === '分派人员'">
      <group>
        <popup-picker
          title="主要人员"
          :data="keyPersonnelOptions"
          v-model="keyPersonnel"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChangePersonnel(keyPersonnel)"
          placeholder="请选择主要人员(必选)"
        ></popup-picker>
      </group>
      <group>
        <popup-picker
          title="抄送人员"
          :data="keyOtherPersonnels"
          v-model="otherPersonnel"
          @on-show="onShow"
          @on-hide="onHide"
          @on-change="onChange"
          placeholder="请选择抄送人员"
        ></popup-picker>
      </group>
      <!--<div class="copyPeople" @click="chooseCopyPeople">-->
        <!--<div class="title">抄送人员</div>-->
        <!--<div class="content">{{copyPeople}}</div>-->
        <!--<div class="arrow">-->
          <!--<img src="../assets/imgs/arrowLeft.png" alt />-->
        <!--</div>-->
      <!--</div>-->
      <group title="分派意见">
        <x-textarea
          :max="200"
          placeholder="请输入不受理内容(必填)"
          :show-counter="false"
          :height="200"
          :rows="8"
          :cols="30"
          v-model="distributionOfOpinions"
        ></x-textarea>
      </group>

      <div class="deal">
        <x-button type="primary" @click.native="submitPersonnel">确定</x-button>
      </div>
    </div>

    <!-- 弹窗 -->
    <popup v-model="deal">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="chooseDealPeople"
        @on-click-left="cancel"
        @on-click-right="comfirmDealPerson"
      ></popup-header>
      <group gutter="0">
        <radio :options="dealOption" v-model="dealPerson"></radio>
      </group>
    </popup>
    <!-- 抄送人员 -->
    <popup v-model="showChoosePeople">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="chooseDealPeople"
        @on-click-left="cancel"
        @on-click-right="comfirmDealPerson"
      ></popup-header>
      <group gutter="0">
        <checklist
          :label-position="labelPosition"
          required
          :options="copyPeopleList"
          v-model="copyPeopleArr"
          @on-change="change"
        ></checklist>
      </group>
    </popup>
  </div>
</template>
<script>
import { nextWork,findOrgUnitByRegionId,findOrgDeptByOrgId,findUsersByDeptIdAndPermission,searchBaseInfo } from '../api/convenient12345';
import { parse } from 'path';

export default {

  watch: {
    subordinate : function (newvalue) {
      console.log(newvalue)
      console.log(newvalue.toString())
      console.log(this.unitList)
      for (let key in this.unitList) {
        if (this.unitList[key].name == newvalue.toString()) {
          console.log(this.unitList[key].name)
          this.Subordinate = this.unitList[key]
          console.log(this.Subordinate)
        }
      }

    },
    copyDept : function (newvalue) {
      console.log(123)
      console.log(newvalue)
      console.log(this.deptList)
      for (let key in this.deptList) {
        if (this.deptList[key].name == newvalue.toString()) {
          console.log(this.deptList[key].name)
          this.CopyDept.push(this.deptList[key].orgunitID)
          console.log(this.CopyDept)
        }
      }
    },
    menberDept : function (newvalue) {
      console.log(123)
      console.log(newvalue)
      console.log(this.deptList)
      for (let key in this.deptList) {
        if (this.deptList[key].name == newvalue.toString()) {
          console.log(this.deptList[key].name)
          this.MenberDept = this.deptList[key]
          console.log(this.MenberDept)
        }
      }
    },
    keyPersonnel : function (newvalue) {
      console.log(newvalue)
      console.log(this.personnelList)
      for (let key in this.personnelList) {
        if (this.personnelList[key].name == newvalue.toString()) {
          console.log(this.personnelList[key].name)
          this.KeyPersonnel = this.personnelList[key]
          console.log(this.KeyPersonnel)
        }
      }
    },
    otherPersonnel : function (newvalue) {
      console.log(newvalue)
      console.log(this.personnelList)
      for (let key in this.personnelList) {
        if (this.personnelList[key].name == newvalue.toString()) {
          console.log(this.personnelList[key])
          // this.OtherPersonnel = this.personnelList[key]
          this.OtherPersonnel.push(this.personnelList[key].primaryKey)
          console.log(this.OtherPersonnel)
          // this.CopyDept.push(this.deptList[key].orgunitID)
        }
      }
    },
  },

  data() {
    return {
      Providetype1: '',
      Objclassify1: '',
      Complaintstype1: '',
      Entitytype1: '',
      Objectcategory1: '',
      Problemtype1: '',
      Illegalrules1: '',
      Businesstype1: '',
      Tortclassify1: '',
      nextNodeId: 'end',
      formType: '',
      formTypeStr: '',
      economyform: {},
      complaintform: {},
      consultationform: {},
      commomform: {},
      caseprocesform: {},
      customerinfo: {},
      tasklist: {},
      opinionList: [],
      handledemand: {},
      ordernumber: '',
      // stepOptions: [['调解', '和解', '调查']],
      Subordinate:null,//分派下级--成员单位对象属性
      subordinate: [],//分派下级--成员单位选框数据
      deptList:[],//分派下级--部门列表
      DeptList:[],//分派下级--抄送部门对象属性
      MenberDept:[],//主导部门
      unitList:[],//下级单位列表
      copyDept: [],
      CopyDept:[],
      personnelList:[],//分派人员--主要人员
      stepOptions: [['调解', '和解', '调查']],
      menberOptions: [[],],
      notAcceptOptions: [['交付单位不正确', '可通过查阅知识库解决']],
      keyPersonnelOptions: [[]],//主要人员
      keyOtherPersonnels:[[]],//抄送人员
      otherPersonnel:[],
      deptOptions: [[]],
      dealOption: ['处理反馈', '分派人员'],
      copyPeopleList: ['哈韩的', 'hahd', 'hdhhh'],
      keyPersonnel: [],
      KeyPersonnel:[],
      OtherPersonnel:[],
      copyPeopleArr: [],
      dealStyle: [],
      menberDept: [],
      sign: '',
      formtype: '',
      dealPerson: '',
      disposal: '',
      opinionCompletion: '',
      deptValue: '',
      notAcceptOpinion: [],
      notAcceptContent: '',
      copyPeople: '',
      distributionContent: '',
      opionion: '',
      distributionOfOpinions: '',
      labelPosition: '',
      deal: false,
      showChoosePeople: false,
      // 页面跳转传过来的值
      user: JSON.parse(localStorage.getItem('user')),
      caseguid: '',
      nodeStr: '',
      // 受理方式
      acceptedMode: '',
    };
  },

  methods: {


    submitAssignment() {
      console.log(this.$route.query)
      // return " ";
      //分派下级
      // let validation = false;
      // this.$refs.assignmentForm.validate(valid => (validation = valid));
      // if (!validation) return;
      if(this.subordinate === [] || this.distributionContent === ''){
        this.$vux.alert.show({
          title: '提示',
          content: '还有信息没填完',
        });
      } else {
        this.$confirm("请确认无误提交?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.submitLoading = true;
          let data;
          if ("commommodule" === this.formType) {
            if (this.commomform != null) {
              data = this.changeArray(this.commomform);
            } else {
              data = this.changeArray(this.consultationform);
            }
          } else if ("complaintmodule" === this.formType) {

            data = this.changeArray(this.complaintform);
          } else if ("comeconomicmodule" === this.formType) {

            data = this.changeArray(this.economyform);
          }
          let params = {
            user: this.user,
            formType: this.formType,
            node: this.nodeStr,
            nextNodeId: "moveToLowerOrg",
            data: data,
            opinion: this.distributionContent,
            caseprocesform: this.caseprocesform,
            orgid: this.Subordinate.primaryKey,
            ortherDept: this.CopyDept,
          };
          // return " ";
          nextWork(params)
            .then(data => {
              if (data.code === 0) {
                this.$message({
                  message: "提交成功！",
                  type: "success",
                  duration: "1000"
                });
                this.$router.push("/main/Pending");
                // this.$alert("提交成功！", "提示", {
                //     confirmButtonText: "确定",
                //     type: "success",
                //     callback: action => {
                //         window.opener.location.reload();//刷新父窗口
                //         setInterval(function () {
                //             window.close()
                //         },500);
                //         // this.$router.push("/wddb/12345db");
                //     }
                // });
              } else {
                this.errorAlert();
              }
            })
            .catch(data => {
              this.errorAlert();
            });
        });
      }
    },
    submitDepartment() {
      //分派部门
      // let validation = false;
      // this.$refs.departmentForm.validate(valid => (validation = valid));
      // if (!validation) return;
      if(this.menberDept === [] || this.distributionContent === ''){
        this.$vux.alert.show({
          title: '提示',
          content: '还有信息没填完',
        });
      } else {
        this.$confirm("请确认无误提交?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          //深度拷贝，防止更改complaintform原先的表单值
          let data;
          if ("commommodule" === this.formType) {
            if (this.commomform != null) {
              data = this.changeArray(this.commomform);
            } else {
              data = this.changeArray(this.consultationform);
            }
          } else if ("complaintmodule" === this.formType) {

            data = this.changeArray(this.complaintform);
          } else if ("comeconomicmodule" === this.formType) {

            data = this.changeArray(this.economyform);
          }
          let params = {
            user: this.user,
            formType: this.formType,
            node: this.nodeStr,
            nextNodeId: "moveToLowerDept",
            data: data,
            opinion: this.distributionContent,
            deptid: this.MenberDept.orgunitID,//.primaryKey
            ortherDept: this.CopyDept,
          };
          console.log(params)
          // return " ";
          nextWork(params)
            .then(data => {
              if (data.code === 0) {
                this.$message({
                  message: '提交成功！',
                  type: 'success',
                  duration: '1000'
                });

                this.$router.push("/main/Pending");
              } else {
                this.errorAlert();
              }
            })
            .catch(data => {
              this.errorAlert();
            });
        });
      }
    },
    submitPersonnel() {
      //分派人员
      // let validation = false;
      // this.$refs.personnelForm.validate(valid => (validation = valid));
      // if (!validation) return;
      this.$confirm("请确认无误提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        let data;
        if ("commommodule" === this.formType) {
          if (this.commomform != null) {
            data = this.changeArray(this.commomform);
          } else {
            data = this.changeArray(this.consultationform);
          }
        } else if ("complaintmodule" === this.formType) {

          data = this.changeArray(this.complaintform);
        } else if ("comeconomicmodule" === this.formType) {

          data = this.changeArray(this.economyform);
        }
        let params = {
          user: this.user,
          formType: this.formType,
          node: this.nodeStr,
          nextNodeId: "moveToLowerPersonnel",
          data: data,
          opinion: this.distributionOfOpinions,
          caseprocesform: this.caseprocesform,
          deptid: this.KeyPersonnel.primaryKey,
          ortherDept: this.OtherPersonnel,
        };
        console.log(params)
        // return " ";
        nextWork(params)
          .then(data => {
            if (data.code === 0) {
              this.$message({
                message: "提交成功！",
                type: "success",
                duration: "1000"
              });
              this.$router.push("/main/Pending");
            } else {
              this.errorAlert();
            }
          })
          .catch(data => {
            this.errorAlert();
          });
      });
    },
    onChangeDetail() {
      this.acceptedMode = this.dealStyle[0];
      // console.log(this.acceptedMode);
    },
    chooseDealPeople() {},
    onShow() {
      // console.log('on show');
    },
    change(val, label) {},
    onHide(type) {},
    onChange(val) {
      if (this.dealStyle.length !== 0) {
        this.deal = true;
      }
    },
    cancel() {
      this.deal = false;
      this.showChoosePeople = false;
      this.dealPerson = '';
      this.copyPeopleArr = [];
    },

    comfirmDealPerson() {
      this.deal = false;
      this.showChoosePeople = false;
      this.copyPeople = this.copyPeopleArr.join(',');

      // console.log(this.copyPeopleArr);
      if (this.dealPerson == '处理反馈') {
        this.$router.push({ path: '/main/DetailOf12345/processFeedback' });
      } else if (this.dealPerson == '分派人员') {
        this.$router.push({ path: '/main/DetailOf12345/dispatchedPersonnel' });
      } else {
      }
    },
    chooseCopyPeople() {
      this.showChoosePeople = true;
    },
    comfirm() {
      this.$router.push({ path: '/main/Pending' });
    },

    changeArray(data) {
      //遍历去除数组格式
      for (const key in data) {
        if (data[key] instanceof Array) {
          if (data[key].length > 0) {
            data[key] = data[key].pop();
          } else {
            data[key] = null;
          }
        }
      }
      console.log(data)
      return data ;
    },
    comfirmAccept() {
      // 受理
      if (this.dealStyle.length !== 0) {
        if (this.$route.query.fromType === 'economyform') {
          // const ecoorderInfo = JSON.parse(
          //   sessionStorage.getItem('EconomyOrderInfo'),
          // );
          // const economyFormData = JSON.parse(
          //   sessionStorage.getItem('economyFormData'),
          // );
          console.log(this.formType)
          let data;
          if ("commommodule" === this.formType) {
            if (this.commomform != null) {
              data = this.changeArray(this.commomform);
            } else {
              data = this.changeArray(this.consultationform);
            }
          } else if ("complaintmodule" === this.formType) {

            data = this.changeArray(this.complaintform);
          } else if ("comeconomicmodule" === this.formType) {
            console.log(this.economyform)
            data = this.changeArray(this.economyform);
          }
          console.log(data)
          console.log(this.$route.query)
          let params
          if(this.$route.query.currentLink != null){
             params = {
              user: this.user,
              fromType: this.$route.query.fromType,
              node: this.$route.query.currentLink,
              nextNodeId: 'accept',
              data: data,//ecoorderInfo
              opinion: this.acceptedMode,
            };
          }else{
             params = {
              user: this.user,
              fromType: this.fromType,
              node: 'begin',
              nextNodeId: 'accept',
              data: data,//ecoorderInfo
              opinion: this.acceptedMode,
            };
          }
          console.log(params)

          nextWork(params).then(res => {
            console.log(res)
            if (res.code === 0) {
              const params = {
                caseguid: res.data.caseguid,
                id: res.data.id,
              };
              console.log(params)
              // return "";
              this.$router.push({
                path: '/main/Economy/acceptedOfEconomyDetail',
                query: params,
              });
            }
          });
        }
      } else {
        this.$vux.alert.show({
          title: '提示',
          content: '请选择受理方式',
        });
      }
    },
    // 分派人员
    dispatchedPersonnel() {
      if (this.keyPersonnel !== '' && this.distributionOfOpinions !== '') {
        if (this.$route.query.fromType === 'economyform') {
          const economyform = JSON.parse(sessionStorage.getItem('economyFormData'));
          const params = {
            user: this.user,
            fromType: 'comeconomicmodule',
            node: 'moveToLowerPersonnel',
            nextNodeId: 'registerCase',
            data: economyform,
            ortherDept: this.copyDept,
            deptid: '',
            opinion: this.distributionOfOpinions,
          };
          nextWork(params).then((res) => {
            console.log(res);
            if (res.code === 0) {
              this.$vux.alert.show({
                title: '提示',
                content: '分派成功',
              });
              this.$router.push({ path: '/main/Pending' });
            } else {
              this.$vux.alert.show({
                title: '提示',
                content: res.msg,
              });
            }
          });
        }
      } else {
        this.$vux.alert.show({
          title: '提示',
          content: '人员未选择或分派意见未填写',
        });
      }
    },
    comfirmOpinion() {
      if (this.opinionCompletion === '') {
        this.$vux.alert.show({
          title: '提示',
          content: '请填写意见内容',
        });
      } else {
        const params = {
          user: this.user,
          caseguid: this.caseguid,
          node: this.nodeStr,
          nextNodeId: 'copyToOpinion',
          opinion: this.opinionCompletion,
          ismain: '0',
        };
        nextWork(params).then((res) => {
          if (res.code === 0) {
            this.$vux.alert.show({
              title: '提示',
              content: res.msg,
            });
            this.$router.push({ path: '/main/Pending' });
          } else {
            this.$vux.alert.show({
              title: '提示',
              content: res.msg,
            });
          }
        });
      }
    },
    dealFeekBack() {
      // 经济表单的处理反馈
      console.log(this.$route.query);
      // msx
      this.$confirm('请确认无误提交?', '提示', {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
      let data;
      if (this.formType === 'commommodule') {
        if (this.commomform != null) {
          data = this.commomform;
        } else {
          if (this.consultationform.entitytype != null && this.consultationform.entitytype != "") {
            this.consultationform.entitytype = this.Entitytype1;
          }
          data = this.consultationform;
        }
      } else if (this.formType === 'complaintmodule') {
        if (this.complaintform.objclassify != null && this.complaintform.objclassify != "") {
          this.complaintform.objclassify = this.Objclassify1;
        }
        if (this.complaintform.complaintstype != null && this.complaintform.complaintstype != "") {
          this.complaintform.complaintstype = this.Complaintstype1;
        }
        if (this.complaintform.providetype != null && this.complaintform.providetype != "") {
          this.complaintform.providetype = this.Providetype1;
        }
        data = this.complaintform;
      } else if (this.formType === 'comeconomicmodule') {
        if (this.economyform.tortclassify != null && this.economyform.tortclassify != "") {
          this.economyform.tortclassify = this.Tortclassify1;
        }
        if (this.economyform.businesstype != null && this.economyform.businesstype != "") {
          this.economyform.businesstype = this.Businesstype1;
        }
        if (this.economyform.illegalrules != null && this.economyform.illegalrules != "") {
          this.economyform.illegalrules = this.Illegalrules1;
        }
        if (this.economyform.problemtype != null && this.economyform.problemtype != "") {
          this.economyform.problemtype = this.Problemtype1;
        }
        if (this.economyform.objectcategory != null && this.economyform.tortclassify != "") {
          this.economyform.objectcategory = this.Objectcategory1;
        }
        data = this.economyform;
      }
        const params = {
        user: this.user,
        formType: this.formType,
        node: this.nodeStr,
        nextNodeId: this.nextNodeId,
        data: data,
        opinion: this.opinion,
        caseprocesform: this.caseprocesform,
      };
        nextWork(params)
          .then(data => {
            if (data.code === 0) {
              this.$message({
                message: '提交成功！',
                type: 'success',
                duration: '1000',
              });
              this.$router.push('/main/Pending');
            } else {
              this.errorAlert();
            }
          })
          .catch(data => {
          this.errorAlert();
        });
          });
    },

    onChangePersonnel(value){
      this.keyOtherPersonnels = [[]]
      for (let key in this.keyPersonnelOptions[0]) {
        if (this.keyPersonnelOptions[0][key].length > 0) {
          console.log(this.keyPersonnelOptions[0][key])
          if(this.keyPersonnelOptions[0][key] == value.toString()){

          }else {
            this.keyOtherPersonnels[0].push(this.keyPersonnelOptions[0][key])
          }
        }
      }console.log(this.keyOtherPersonnels)
        if (this.keyOtherPersonnels[0].length == 0){
          return keyOtherPersonnels=[["无相关人员数据"]] ;
        }
        console.log(this.keyOtherPersonnels)
    },
    findUnit() {
      //获取下级单位列表
      findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
        if (data.data) {
          this.unitList = data.data;

          for (let key in this.unitList) {
              if (this.unitList.length > 0) {
                this.menberOptions[0].push(this.unitList[key].name);
              }
          }
          console.log(this.menberOptions)

        }
      });
    },
    unitChange() {
      //获取部门列表
      findOrgDeptByOrgId({orgId: this.user.orgID}).then(res => {
        this.deptList = res.data;

        for (let key in this.deptList) {
          if (this.deptList.length > 0) {
            this.deptOptions[0].push(this.deptList[key].name);
          }
        }
        console.log(this.deptOptions)
      });
    },
    findPersonnelList() {
      console.log(123)
      //获取人员列表
      findUsersByDeptIdAndPermission({
        deptid: this.user.unitID,
        permission: "dealpersonnel"
      }).then(data => {
        if (data.data) {
          this.personnelList = data.data;
          for (let key in this.personnelList) {
            if (this.personnelList.length > 0) {
              this.keyPersonnelOptions[0].push(this.personnelList[key].name);
            }
          }
        }
      });
    },
    getBaseInfo() {
      this.loading = true;
      //获取id，否则跳转
      console.log(111)
      console.log(this.$route.query)
      let {id, caseguid} = this.$route.query;
      if (!id || !caseguid) {
        this.$router.push("/main/Pending");
        return;
      }
      // //获取工单信息
      searchBaseInfo({id, caseguid, systemsource: "02"})
        .then(({code, data}) => {
          console.log(data)
          // this.nodeStr = data.tasklist.nextnodeid;
          // if (
          //   this.nodeStr !== "handleFeedback" &&
          //   data.tasklist.isaccept === "1"
          // ) {
          //   //节点与当前对应不上并不是属于没有受理
          //   this.$router.push("/wddb/12345db");
          //   return;
          // }
          if (data.caseprocesform != null) {
            this.caseprocesform = data.caseprocesform;
          }
          this.customerinfo = data.customerinfo;
          this.tasklist = data.tasklist;
          this.opinionList = data.opinionList;
          this.handledemand = data.handledemand;
          if (code === 0) {
            if (data.economyform != null) {
              //经济表单
              this.formType = "comeconomicmodule";
              this.formTypeStr = "经济违法行为";
              this.economyform = data.economyform;
              this.ordernumber = this.economyform.ordernumber;
              console.log(this.economyform )
            } else if (data.complaintform != null) {
              //消费投诉表单
              this.formType = "complaintmodule";
              this.formTypeStr = "消费投诉表单";
              this.complaintform = data.complaintform;
              this.ordernumber = this.complaintform.ordernumber;

            } else if (data.consultationform != null) {
              //咨询
              this.formType = "commommodule";
              this.formTypeStr = data.consultationform.formtype;
              this.consultationform = data.consultationform;
              this.ordernumber = this.consultationform.ordernumber;


            } else if (data.commomform != null) {
              //通用表单
              this.formType = "commommodule";
              this.formTypeStr = data.commomform.formtype;
              this.commomform = data.commomform;
              this.ordernumber = this.commomform.ordernumber;

            } else {
              //找不到对的工单信息
              this.$alert("找不到对应的工单信息!", "提示", {
                confirmButtonText: "确定",
                type: "warning"
              });
              this.$router.push("/wddb/12345db");
            }
          }
          this.loading = false;
        })
        .catch(error => {
          console.error(error);
          this.$message({
            message: "请求失败",
            type: "error"
          });
          this.loading = false;
        });
      console.log(this.formType)
    },
  },

  mounted() {
    this.findUnit();
    this.unitChange();
    this.findPersonnelList();
    this.sign = this.$route.query.stepValue;
    this.formtype = this.$route.query.formType;
    this.caseguid = this.$route.query.caseguid;
    this.nodeStr = this.$route.query.currentLink;
    this.getBaseInfo();
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 30px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);

.copyPeople {
  display: flex;
  align-items: center;
  background: #fff;
  padding: 0.233333rem 0.43rem;
  font-size: 0.4rem;
  border-bottom: 1px solid #d9d9d9;
  .title {
    flex: 1;
  }
  .content {
    flex: 3;
    text-align: right;
    color: #999999;
  }
  .arrow {
    width: 40px;
    img {
      width: 100%;
    }
  }
}

.feekback {
  background: #fff;
  width: 90%;
  padding: 20px;
  margin: 20px auto;
  border-radius: 20px;
}
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.distributionOfOpinions {
  .title {
    font-size: @fontStyleOfTitle;
    font-weight: 600;
    padding-left: 40px;
  }
  .weui-textarea {
    // width: 90%;
    // margin: 20px auto;
    margin: 0 auto;
    font-size: @fontStyleOfTitle;
  }
  .weui-cell {
    width: 90%;
    margin: 0 auto;
  }
}
.deal {
  width: 80%;
  margin: 100px auto;
}
.weui-btn_primary {
  background: @backgroundColor !important;
}
</style>
<style lang="less">
@import url("../assets/font/commonOfCommon.less");
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.vux-popup-header-right {
  color: @backgroundColor!important;
}
.weui-cells_checkbox .weui-check:checked + .vux-checklist-icon-checked:before {
  color: @backgroundColor!important;
}
</style>
