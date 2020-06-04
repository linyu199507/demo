<template>
<div>
  <div>
    <tab :line-width=2 active-color='#fc378c' v-model="index" @change="choose">
    <tab-item class="vux-center"  :selected="demo2 === item" v-for="(item, index) in list2" @click="demo2 = item" :key="index">{{item}}</tab-item>
    </tab>
    <swiper v-model="index" height="40px" :show-dots="false">
    <swiper-item v-for="(item, index) in list2" :key="index">
    <div class="tab-swiper vux-center">
      <popup-picker
        style="font-size: "
        v-if="item == '系统'"
        placeholder="请选择系统"
        :data="system"
        v-model="systemValue"
        value-text-align="left"
      ></popup-picker>
      <popup-picker
        v-if="item == '类型'"
        placeholder="请选择类型"
        :data="fours"
        v-model="foursValue"
        value-text-align="left"
      ></popup-picker>
      <popup-picker
        v-if="item == '环节'"
        placeholder="请选择环节"
        :data="link"
        v-model="linkValue"
        value-text-align="left"
      ></popup-picker>
      <!--{{item}} Container-->
    </div>
    </swiper-item>
    </swiper>
  </div>
<!--工单列表-->
  <div class="list">


    <!--<group title="single column" label-width="5em">-->
    <!--<popup-picker-->
    <!--title="查询条件1"-->
    <!--placeholder="请选择业务分类"-->
    <!--:data="jobClassificationOptions"-->
    <!--v-model="economyForm.EconomyBaseInfo.business"-->
    <!--value-text-align="left"-->
    <!--&gt;</popup-picker>-->
    <!--<popup-picker-->
    <!--title="查询条件2"-->
    <!--placeholder="请选择业务分类"-->
    <!--:data="jobClassificationOptions"-->
    <!--v-model="economyForm.EconomyBaseInfo.business"-->
    <!--value-text-align="left"-->
    <!--&gt;</popup-picker>-->
    <!--<popup-picker-->
    <!--title="查询条件3"-->
    <!--placeholder="请选择业务分类"-->
    <!--:data="jobClassificationOptions"-->
    <!--v-model="economyForm.EconomyBaseInfo.business"-->
    <!--value-text-align="left"-->
    <!--&gt;</popup-picker>-->
    <!--</group>-->

    <ul>
      <li v-for="(item,index) in lists" :key="index" v-if="lists.length > 0">
        <div class="header">
          <div class="name">标题：{{item.rqsttitle}}</div>
          <div class="detail" item @click="gotoDetailList(item)">查看详情></div>
        </div>
        <div class="content">{{item.content}}</div>
        <div class="deal">
          <div class="current">
            当前环节:
            <span>{{item.operate}}</span>
          </div>
          <div class="type">
            业务类型:
            <span>{{item.bustype}}</span>
          </div>
        </div>
        <div class="begin">
          交办时间:
          <span>{{$moment(item.assigndate).format("YYYY-MM-DD HH:mm:ss")}}</span>
        </div>
        <div class="end">
          截止时间:
          <span>{{$moment(item.sdexpirationdate).format("YYYY-MM-DD HH:mm:ss")}}</span>
        </div>
      </li>
    </ul>
    <tabbar style="position:fixed;bottom:0;left:0">
      <tabbar-item @on-item-click="cs" link="/main/mainIndex" v-if="userInfo===''">
        <img slot="icon-active" src="../assets/imgs/home.png" />
        <img slot="icon" src="../assets/imgs/homenot.png" />
        <span slot="label" >首页</span>
      </tabbar-item>
      <tabbar-item @on-item-click="cs" link="/main/MainLeader" v-else> 
        <img slot="icon-active" src="../assets/imgs/home.png" />
        <img slot="icon" src="../assets/imgs/homenot.png" />
        <span slot="label" >首页</span>
      </tabbar-item>
      <tabbar-item link="/main/Pending" selected>
        <img slot="icon-active" src="../assets/imgs/listPerson.png" />
        <img slot="icon" src="../assets/imgs/list.png" />
        <span slot="label" >待办列表</span>
      </tabbar-item>
      <tabbar-item show-dot  link="/main/user">
        <img slot="icon-active" src="../assets/imgs/personActive.png" />
        <img slot="icon" src="../assets/imgs/person.png" />
        <span slot="label" >个人中心</span>
      </tabbar-item>
    </tabbar>
  </div>
</div>

</template>
<script>
import { todoList } from '../api/api';

export default {

  watch : {
    systemValue : function (newValue) {
      console.log(newValue.toString())
      let value = newValue.toString()
      if(value === '12345'){ this.systemsource = "02" }
      if(value === '12315'){ this.systemsource = "03" }
      this.getList()
    },

    foursValue : function (newValue) {
      console.log(newValue.toString())
      let value = newValue.toString()
      if(value === '投诉'){ this.formType = "02" }
      if(value === '举报'){ this.formType = "03" }
      if(value === '咨询'){ this.formType = "01" }
      this.getList()
    },

    linkValue : function (newValue) {
      let value = newValue.toString()
      console.log(value)
      if(value === '待分派'){   this.currentLink = 'begin'  }
      else if (value === '受理'){   this.currentLink = 'accept' }
      else if (value === '不受理'){  this.currentLink = 'noAccept'  }
      else if (value === '立案'){   this.currentLink = 'registerCase' }
      else if (value === '不予立案'){   this.currentLink = 'noRegisterCase' }
      else if (value === '领导审核'){   this.currentLink = 'leaderApr' }
      else if (value === '分派下级机关'){  this.currentLink = 'moveToLowerOrg'  }
      else if (value === '分派其他部门'){   this.currentLink = 'moveToLowerDept' }
      else if (value === '分派人员'){  this.currentLink = 'moveToLowerPersonnel'  }
      else if (value === '区局处理'){  this.currentLink = 'regionAudit'  }
      else if (value === '处理反馈'){   this.currentLink = 'handleFeedback' }
      else if (value === '结束'){  this.currentLink = 'end'  }
      this.getList()
    },
  },

  data() {
    return {
      userInfo:'',
      systemsource:"",
      formType:"",
      currentLink:"",
      system:[['12345','12315']],
      fours:[['投诉','举报','咨询']],
      link:[['待分派','受理','不受理','立案','不予立案','领导审核','分派下级机关','分派其他部门','分派人员','区局处理','处理反馈','结束']],
      linkValue:[],
      foursValue:[],
      systemValue:[],
      index: 0,
      list2:['系统','类型','环节'],
      demo2: '2',
      economyForm: {
        // 订单信息
        orderInfo: {
          seatsname: '',
          seatsregistrationtime: '',
        },
        EconomyBaseInfo: {
          formtype: '',
          rqsttitle: '',
          rqstperson: '',
          age: '',
          nation: '',
          rqstnumber: '',
          linknumber: '',
          status: '',
          idnumber: '',
          company: '',
          complaintpaper: '',
          disabilityidentification: '',
          rqstaddress: '',
          contactaddress: '',
          subjectname: '',
          linktype: '',
          subjectaddress: '',
          objname: '',
          brand: '',
          objmoney: '',
          producecompany: '',
          importmark: '',
          typespecification: '',
          quantity: '',
          measureunit: '',
          price: '',
          keyword: '',
          govlabel: '',
          anestimate: '',
          economicloss: '',
          problemdescribe: '',
          accepttimesurplus: '',
          accepttime: '',
          caseputtime: '',
          accepttime: '',
        },
      },
      jobClassificationOptions: [['普通', '特殊']],
      user: JSON.parse(localStorage.getItem('user')),
      lists: [],
    };
  },
  methods: {
    choose(){
      console.log(123)
    },

    getAllList() {

      // const params = {
      //   user: this.user,
      //   systemsource: '02',
      //   size: 10000,
      //   formType: '01',
      //   page: 1,
      //   total: 0,
      // };
      const params = {
        user: this.user,
        systemsource: "03",
        size: 10000,
        formType: this.formType,
        page: 1,
        total: 0,
      };
      todoList(params).then((res) => {
        this.lists = res.data.records;
        console.log(this.lists);
      });
    },
    getList(value) {


      const params = {
        user: this.user,
        systemsource: this.systemsource,
        size: 10000,
        formType: this.formType,
        page: 1,
        total: 0,
        currentLink:this.currentLink,
      };
      console.log(params)
      todoList(params).then((res) => {
        this.lists = res.data.records;
        console.log(this.lists);
      });
    },

    gotoDetailList(item) {
      const params = {
        caseguid: item.caseguid,
        id: item.id,
        ismain: item.ismain,
        stepValue: '',
      };
      console.log(item.systemsource);
      console.log(item.formtype);
      if (item.systemsource === '02') {
        //  12345系统
        if (item.formtype === '01') {
          // 咨询
          // 判断是哪个环节
          if (item.nextnodeid === 'begin') {
            this.$router.push({
              path: '/main/ConsultDetailOf12345',
              query: params,
            });
          } else {
            this.$router.push({
              path: '/main/assignSubordinateOfConsult',
              query: params,
            });
          }

          // this.chooseCurrent(params, item, '/main/ConsultDetailOf12345');
        } else if (item.formtype === '02') {
          // 投诉
          if (item.nextnodeid === 'begin') {
            this.$router.push({
              path: '/main/complainFrom',
              query: params,
            });
          } else {
            this.$router.push({
              path: '/main/DetailOf12345',
              query: params,
            });
          }
        } else if (item.formtype === '03') {
          // 举报
          if (item.nextnodeid === 'begin') {
            this.$router.push({
              path: '/main/Economy',
              query: params,
            });
          } else {
            this.$router.push({
              path: '/main/Economy/acceptedOfEconomyDetail',
              query: params,
            });
          }
        }
      } else if (item.systemsource === '03') {
        // 12315系统
        if (item.nextnodeid === 'todispatch') {
          this.$router.push({ path: '/main/toBeDiverted', query: params });
        } else if (item.formtype === '02') {
          // 投诉
          if (item.nextnodeid === 'begin') {
            this.$router.push({
              path: '/main/beginToDealComplain',
              query: params,
            });
          } else {
            this.$router.push({
              path: '/main/acceptedof12315complain',
              query: params,
            });
          }

        } else if (item.formtype === '03') {
          // 举报
          if (item.nextnodeid === 'begin') {
            this.$router.push({
              path: '/main/beginToDealComplain',
              query: params,
            });
          } else {
            this.$router.push({
              path: '/main/acceptedOf12315Report',
              query: params,
            });
          }
        } else {
        }
      } else {
      }
    },
  },
  mounted() {
    this.getAllList();
    if(sessionStorage.getItem('LeaderInfo') !== null){
      this.userInfo = sessionStorage.getItem('LeaderInfo')
      console.log(this.userInfo)
    }
  },
};
</script>
<style lang="less">
.weui-cells:first-child {
  margin-top: 0 !important;
}

.list {
  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    li {
      width: 90%;
      background: #fff;
      margin: 0 auto 20px;
      border-radius: 10px;
      padding: 0.2rem;
      box-shadow: #ccc 2px 2px 2px;
      .header {
        display: flex;
        align-items: center;
        height: 100px;
        padding-top: 20px;
        margin-bottom: 20px;
        .name {
          flex: 2;
          color: #000;
          font-size: 30px;
        }
        .detail {
          flex: 1;
          color: #ccc;
          font-size: 20px;
          margin-bottom: 20px;
          cursor: pointer;
          text-align: right;
          &:hover {
            text-decoration: underline;
          }
        }
      }
      .content {
        margin-bottom: 10px;
        color: #ccc;
        overflow: hidden;
        font-size: 0.4rem;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
      }
      .deal {
        display: flex;
        justify-content: flex-start;
        align-items: center;
        margin-bottom: 10px;
        .current {
          flex: 1;
          span {
            color: rgb(229, 51, 51);
          }
        }
        .type {
          flex: 1;
          span {
            color: #ccc;
          }
        }
      }
      .begin {
        margin-bottom: 10px;
        span {
          color: #ccc;
        }
      }
      .end {
        margin-bottom: 10px;
        span {
          color: #ccc;
        }
      }
    }
  }
}
.weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon, .weui-tabbar__item.weui-bar__item_on .weui-tabbar__icon > i, .weui-tabbar__item.weui-bar__item_on .weui-tabbar__label{
  color:#1296db!important;
}
</style>
