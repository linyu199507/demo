<!--左菜单栏组件-->
<template>
  <aside class="my-nav-menu">
    <!--收缩按钮-->
    <div>
      <div class="close-tools" @click.prevent="collapse" title="收起" v-if="!isCollapse">
        <i class="el-icon-caret-left"></i>
      </div>
      <div class="open-tools" @click.prevent="collapse" title="展开" v-if="isCollapse">
        <i class="el-icon-caret-right"></i>
      </div>
    </div>
    <div class="row" :class="!isCollapse?'row_open':''">
      <el-menu
        :default-active="$route.path"
        :default-openeds="defaultOpeneds"
        :router="true"
        class="el-menu-vertical"
        @open="handleOpen"
        @close="handleClose"
        @select="handleSelect"
        :collapse="isCollapse"
        :key="menuNumber"
      >
        <!--我的待办-->
        <el-submenu index="/wddb" v-if="business">
          <template slot="title">
            <i class="fa fa-envelope"></i>
            <span slot="title">我的待办</span>
          </template>
          <!--<el-menu-item index="/wddb/dfpyw" v-if="unaccept">未受理待办</el-menu-item>-->
          <el-menu-item index="/wddb/12345db" v-if="system">12345系统待办</el-menu-item>
           <el-menu-item index="/wddb/12315db" v-if="system">12315系统待办</el-menu-item>
          <el-menu-item index="/wddb/bxtdb" v-if="registration">受理登记待办</el-menu-item>
          <!-- <el-menu-item index="/wddb/expected" v-if="expected">预警工单</el-menu-item> -->
          <!--<el-menu-item index="/wddb/xfpgd" v-if="system">新分派工单列表</el-menu-item>-->
          <!--                    <el-menu-item index="/wddb/zxldb">12315平台待办</el-menu-item>-->
          <!--                    <el-menu-item index="/wddb/zfldb">8180平台待办</el-menu-item>-->
        </el-submenu>
        <!--受理登记-->
        <!-- <el-menu-item index="/sldj/zxdj">
                <i class="fa fa-edit"></i>
                <span slot="title">受理登记
                </span>

        </el-menu-item>-->

        <el-submenu index="/sldj" v-if="acceptRegistration">
          <template slot="title" >
            <i class="fa fa-edit"></i>
            <span slot="title" >受理登记</span>
          </template>
          <el-menu-item index="/sldj/zxdj" style="padding-left: 60px" v-if="consultingRegistration">受理登记</el-menu-item>
          <!-- <el-menu-item index="/sldj/xftsdj">消费投诉登记</el-menu-item> -->
          <!-- <el-menu-item index="/sldj/jjwfxdj">经济违法登记</el-menu-item> -->
        </el-submenu>

        <!--系统管理-->
        <el-submenu index="/xtgl" v-if="management">
          <template slot="title">
            <i class="fa fa-id-card-o"></i>
            <span slot="title">系统管理</span>
          </template>
          <!--                    <el-submenu index="/xtgl/zjjg">-->
          <!--                        <template slot="title">-->
          <!--                            <span slot="title">组织架构管理</span>-->
          <!--                        </template>-->
          <!--                        <el-menu-item index="/xtgl/zjjg/yhgl">用户管理</el-menu-item>-->
          <!--                        <el-menu-item index="/xtgl/zjjg/qxgl">权限管理</el-menu-item>-->
          <!--                    </el-submenu>-->
          <el-menu-item index="/xtgl/zjjg" v-if="organizationalStructure && user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" >组织架构管理</el-menu-item>
          <!-- <el-menu-item index="/xtgl/fwpzgl" v-if="serviceConfiguration">服务配置管理</el-menu-item> -->
          <el-menu-item index="/xtgl/jcsj" v-if="basicData">基础数据配置</el-menu-item>
          <!-- <el-menu-item index="/xtgl/czrzcx" v-if="operationLog">操作日志查询</el-menu-item> -->
          <!-- <el-menu-item index="/xtgl/ywsxpz" v-if="businessTime">业务时限配置</el-menu-item> -->
          <!--<el-menu-item index="/xtgl/dxgnpz" v-if="SMS">短信功能配置</el-menu-item>-->
          <!-- <el-menu-item index="/xtgl/spygl" v-if="statementsManagement">审批语管理</el-menu-item> -->
        </el-submenu>
        <!--个人信息维护-->
        <el-submenu index="/grxiwh" v-if="personalInformation">
          <template slot="title">
            <i class="fa fa-address-card"></i>
            <span slot="title">个人信息维护</span>
          </template>
          <el-menu-item index="/grxiwh/cyspy" v-if="examination">常用审批语</el-menu-item>
          <el-menu-item index="/grxiwh/mmxg" v-if="password">密码修改</el-menu-item>
        </el-submenu>
        <!--查询统计-->
        <el-submenu index="/tjfx" v-if="businessQuery">
          <template slot="title">
            <i class="fa fa-bar-chart"></i>
            <span slot="title">查询统计</span>
          </template>
          <el-submenu index="/tjfx/zhcx" v-if="comprehensiveQuery">
            <template slot="title" class="search">
              <span slot="title" style="padding-left:20px">综合查询</span>
            </template>
            <el-menu-item index="/tjfx/zhcx/ywjlcx">业务记录查询</el-menu-item>
            <el-menu-item index="/tjfx/zhcx/cljlcx">材料记录查询</el-menu-item>
           <el-menu-item index="/tjfx/zhcx/wbjlcx">12315已办列表查询</el-menu-item>
            <el-menu-item index="/tjfx/zhcx/cqjlcx">12345已办列表查询</el-menu-item>
            <el-menu-item index="/tjfx/zhcx/sldjcx">受理登记已办列表查询</el-menu-item>
            <el-menu-item index="/tjfx/zhcx/expected">预警工单列表</el-menu-item>
            <el-menu-item index="/tjfx/zhcx/wqfwz">维权服务站工单列表</el-menu-item>
            <el-menu-item index="/wddb/xfpgd" v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">新派工单列表</el-menu-item>
          </el-submenu>
          <el-submenu index="/tjfx/tjbb" v-if="statisticalReports">
            <template slot="title">
              <span slot="title" style="padding-left:20px">统计报表</span>
            </template>
            <el-menu-item index="/tjfx/tjbb/bbcx">报表查询</el-menu-item>
            <el-menu-item index="/tjfx/tjbb/lstjsj">历史统计数据</el-menu-item>
            <!-- <el-submenu index="/tjfx/tjbb/cybb">
              <template slot="title">
                <span slot="title">常用报表</span>
              </template> -->
              <!-- <el-menu-item index="/tjfx/tjbb/cybb/gs" class="menu-text">原工商投诉举报信息季度、年度统计表</el-menu-item> -->
              <!-- <el-menu-item index="/tjfx/tjbb/cybb/sy" class="menu-text">原食药投诉举报信息季度、年度统计表</el-menu-item> -->
              <!-- <el-menu-item index="/tjfx/tjbb/cybb/tx" class="menu-text">投诉举报信息立案情况统计表</el-menu-item> -->
            <!-- </el-submenu> -->
            <!-- <el-menu-item index="/tjfx/tjbb/ywbb">业务报表</el-menu-item> -->
            <!-- <el-submenu index="/tjfx/tjbb/xbbb">
              <template slot="title">
                <span slot="title">消保报表</span>
              </template>
              <el-menu-item index="/tjfx/tjbb/xbbb/slcljb" class="menu-text">受理处理举报情况统计表</el-menu-item>
            </el-submenu> -->
          </el-submenu>
            <!--<el-menu-item index="/tjfx/tjbb/cybb/gs">统计图表</el-menu-item>-->
          <el-submenu index="" >
            <template slot="title">
              <span slot="title" style="padding-left:20px">统计图表</span>
            </template>
            <!--<el-menu-item index="/tjfx/tjbb/cybb/business">投诉举报概况</el-menu-item>-->
            <el-menu-item index="/tjfx/tjbb/cybb/Advancedbusiness">投诉举报概况</el-menu-item>
            <!--<el-menu-item index="/tjfx/tjbb/cybb/type">类型统计</el-menu-item>-->
            <!--<el-menu-item index="/tjfx/tjbb/cybb/high">高频词统计</el-menu-item>-->
            <!--<el-menu-item index="/tjfx/tjbb/cybb/warn">预警</el-menu-item>-->
          </el-submenu>
        </el-submenu>

      </el-menu>
    </div>
  </aside>
</template>
<script>
export default {
  data() {
    return {
        complain:false,
        unaccept:false,
        acceptRegistration:false,
        consultingRegistration:false,
        management:false,
        organizationalStructure:false,
        serviceConfiguration:false,
        basicData:false,
        operationLog:false,
        businessTime:false,
        SMS:false,
        statementsManagement:false,
        personalInformation:false,
        examination:false,
        password:false,
        comprehensiveQuery:false,
        statisticalReports:false,
        businessQuery:false,
        business:false,
        registration:false,
        system:false,
        expected: false,


        user: JSON.parse(localStorage.getItem("user")),
        isCollapse: true,
        menuNumber: 0, //记录强制渲染，用来恢复展开的结果样式
        defaultOpeneds: [],
        currentOpeneds: new Map()
    };
  },

  methods: {

    //折叠导航栏
    collapse: function() {
      //注意赋值顺序
      let opends = [];
      this.currentOpeneds.forEach((value, key) => {
        opends.push(...value);
      });
      //去重，重复不展示
      this.defaultOpeneds = [...new Set(opends)];
      if (this.isCollapse) {
        this.menuNumber++;
      }
      this.isCollapse = !this.isCollapse;
    },
    handleSelect(key, keyPath) {},
    handleOpen(key, keyPath) {
      //保存当前打开的菜单
      this.currentOpeneds.set(key, keyPath);
    },
    handleClose(key, keyPath) {
      //移除关闭的菜单
      this.currentOpeneds.delete(key);
    }
  },
    //权限控制
    created() {
        this.user;
        let data = this.user.aicPermissions
        //投诉举报——————系统管理员
        if  (data.complain != undefined && "complain" == data.complain.sysMenuFiled){
                this.complain=true,
                this.business=true,
                this.unaccept=true,
                this.system=true,
                this.registration=true,
                this.acceptRegistration=true,
                this.consultingRegistration=true,
                this.management=true,
                this.organizationalStructure=true,
                this.serviceConfiguration=true,
                this.basicData=true,
                this.operationLog=true,
                this.businessTime=true,
                this.SMS=true,
                this.statementsManagement=true,
                this.personalInformation=true,
                this.examination=true,
                this.password=true,
                this.businessQuery=true,
                this.comprehensiveQuery=true,
                this.statisticalReports=true,
                this.expected = true

        }
        //我的待办菜单栏
        if(data.system != undefined && "system" == data.system.sysMenuFiled){
            this.business=true
            this.system=true
        }
        if(data.registration != undefined && "registration" == data.registration.sysMenuFiled){
            this.business=true
            this.registration=true
        }
        if(data.unaccept != undefined && "unaccept" == data.unaccept.sysMenuFiled){
            this.business=true
            this.unaccept=true
        }

        //受理登记菜单栏
        if(data.consultingRegistration != undefined && "consultingRegistration" == data.consultingRegistration.sysMenuFiled){
            this.consultingRegistration=true
            this.acceptRegistration=true
        }
        //查询统计菜单栏
        if(data.comprehensiveQuery != undefined && "comprehensiveQuery" == data.comprehensiveQuery.sysMenuFiled){
            this.comprehensiveQuery=true
            this.businessQuery=true
        }
        if(data.statisticalReports != undefined && "statisticalReports" == data.statisticalReports.sysMenuFiled){
            this.statisticalReports=true
            this.businessQuery=true
        }
        //系统管理菜单栏
        if(data.organizationalStructure != undefined && "organizationalStructure" == data.organizationalStructure.sysMenuFiled){
            this.organizationalStructure=true
            this.management=true

        }
        if(data.serviceConfiguration != undefined && "serviceConfiguration" == data.serviceConfiguration.sysMenuFiled){
            this.serviceConfiguration=true
            this.management=true
        }
        if(data.basicData != undefined && "basicData" == data.basicData.sysMenuFiled){
            this.basicData=true
            this.management=true
        }
        if(data.operationLog != undefined && "operationLog" == data.operationLog.sysMenuFiled){
            this.operationLog=true
            this.management=true
        }
        if(data.SMS != undefined && "SMS" == data.SMS.sysMenuFiled){
            this.SMS=true
            this.management=true
        }
        if(data.statementsManagement != undefined && "statementsManagement" == data.statementsManagement.sysMenuFiled){
            this.statementsManagement=true
            this.management=true
        }
        if(data.businessTime != undefined && "businessTime" == data.businessTime.sysMenuFiled){
            this.businessTime=true
            this.management=true
        }

        // //个人信息维护菜单栏
        if(data.examination != undefined && "examination" == data.examination.sysMenuFiled){
            this.examination=true
            this.personalInformation=true
        }
        if(data.password != undefined && "password" == data.password.sysMenuFiled){
            this.password=true
            this.personalInformation=true
        }

    }
};
</script>

<style lang="scss">
.my-nav-menu {
  font-size: 13px;
  position: relative;

  .el-menu-item {
    font-size: 13px;
  }

  .hide_overflow {
    overflow: hidden;
  }

  .row {
    height: 100%;
    /*隐藏滚动条，当IE下溢出，仍然可以滚动*/
    -ms-overflow-style: none;
  }

  .row_open {
    overflow-y: auto;
    overflow-x: hidden;
  }

  .row::-webkit-scrollbar {
    width: 0;
  }

  .menu-text {
    display: flex;
    white-space: normal;
    line-height: normal;
    align-items: center;
    word-break: break-all;
    padding-right: 0;
  }

  .el-menu-vertical:not(.el-menu--collapse) {
    width: 230px;
  }

  .el-menu--collapse {
    width: 60px;
  }

  .el-menu-vertical {
    min-height: 100%;
  }

  .el-menu-item {
    display: flex;
    align-items: center;
    min-height: 50px;
    height: auto;
  }

  .close-tools {
    cursor: pointer;
    position: absolute;
    top: 0;
    right: -15px;
    color: #dbdfe6;
    font-size: 20px;
    line-height: 56px;
    z-index: 10;
  }

  .open-tools {
    cursor: pointer;
    position: absolute;
    top: 0;
    right: -15px;
    color: #dbdfe6;
    font-size: 20px;
    line-height: 56px;
    z-index: 10;
  }
  .el-menu{
    /* background: #d5ecff; */
     background: linear-gradient(to bottom, rgb(31, 143, 255) 0%,#ffffff 100%);
  }

}
.fa-edit {
  font-size: 18px !important;
}
 .el-submenu .el-menu-item.is-active, .el-menu-item.is-active {
    color: #4c5866;
    /* background: #b5d8f5!important; */
   /* color:#04070c; */
    background: #e5f1fc !important;
    font-weight: 600!important;
}
.my-nav-menu .el-menu-item {
  background: #fff!important;
   padding-left: 60px!important;
}
.el-submenu .el-menu-item:hover, .el-submenu__title:hover{
   background: #e3f2ff !important;
   color:#04070c;
}

.el-menu-item, .el-submenu__title {
  color: #04070c;
}
.el-submenu li.el-menu-item:hover{
  background: #fff
}
.el-menu li{
  /* background: #d5ecff; */
  /* background: #d5ecff; */
     background: linear-gradient(to bottom, rgb(31, 143, 255) 0%,#ffffff 100%);
     /* background: linear-gradient(to bottom,  0%,#ffffff 100%); */
}
.my-nav-menu{
  /* box-shadow: 2px 3px 8px 0 rgba(61, 80, 99, 0.05); */
  box-shadow: 2px -3px 5px gray;
}
</style>

