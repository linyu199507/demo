<template>
  <el-row class="container">
    <el-col :span="24" class="header">
      <el-col :span="24" class="logo" style="height: 70%;">
        <div>
          <img src="../assets/logo2.png" />
        </div>
      </el-col>
      <el-col :span="24" style="height: 30%;">
        <div class="userinfo">
          <el-col :span="12">
            <span class="userinfo-inner">
              当前用户：{{user.name}}
              （{{user.unitName}}）&nbsp;&nbsp;&nbsp;&nbsp;
              <!-- 部门：{{user.orgName}} -->
              {{dateStr}}
            </span>
          </el-col>
          <el-col :span="12" style="text-align: right;margin-top: -10px;">
            <el-button
              type="text"
              icon="erpshouye"
              style="color: black;"
              @click="homePage()"
            >首页</el-button>
            <el-button type="text" icon="erpwodexiaoxi" style="color: black;" @click="tiShi">我的消息</el-button>
            <el-button type="text" icon="erpbangzhu" style="color: black;" @click="tiShi">帮助</el-button>
            <el-button type="text" icon="erpshezhi" style="color: black;" @click="tiShi">设置</el-button>
            <el-button type="text" icon="erptuichu" style="color: black;" @click="logout">退出</el-button>
          </el-col>
        </div>
      </el-col>
    </el-col>
    <el-col :span="24" class="main">
      <!--左菜单开始-->
      <my-nav-menu />
      <!--左菜单结束-->
      <section class="content-container" ref="getScroll" id="fromExamine">
        <div class="grid-content bg-purple-light">
          <el-col :span="24" class="breadcrumb-container">
            <!--                        <strong class="title">{{$route.name}}</strong>-->
            <el-breadcrumb separator="/" class="breadcrumb-inner">
              <el-breadcrumb-item
                v-for="item in breadcrumb"
                :key="item.path"
                :to="item.path?item.path:item.aliasName"
                v-if="item.isAddress"
              >
                <span>{{ item.name?item.name:item.aliasName}}</span>
              </el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
          <el-col :span="24" class="content-wrapper">
            <transition name="fade" mode="out-in">
              <router-view></router-view>
            </transition>
          </el-col>
          <el-col class="support_text">
            <div style="text-align: center">版权所有：顺德区市场监督管理局 | 技术支持：广东众望通科技股份有限公司</div>
          </el-col>
        </div>
      </section>
    </el-col>
  </el-row>
</template>

<script>
import moment from "moment";
import NavMenu from "../components/NavMenu.vue";
import { findOrgUnitByRegionId } from "../api/convenient12345";
import { removeToken,removeFlag } from "../utils/auth";

moment.locale("zh-cn");
export default {
  components: {
    "my-nav-menu": NavMenu
  },
  data() {
    return {
      user: {},
      isWin: "",
      // 表单滚动高度
      scrollTop: 0,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      },
      dateStr: moment().format("YYYY年MM月DD日 dddd"),
      breadcrumb: []
    };
  },
  methods: {
    //退出登录
    logout: function() {
      var _this = this;
      this.$confirm("确认退出吗?", "提示", {
        type: "info"
      })
        .then(() => {
          removeToken();
          removeFlag();
          localStorage.removeItem("user");
          _this.$router.push("/login");
        })
        .catch(() => {});
    },
    getNextList() {
      var user = JSON.parse(localStorage.getItem("user"));
      if (user) {
        user, (this.sysUserName = user.name || "");
        this.sysUserAvatar = user.avatar || "";
      }
      //获取下级单位列表 todo
      findOrgUnitByRegionId({ regionId: user.regionID }).then(data => {
        if (data.data) {
          this.$store.dispatch("updateUnitList", data.data);
        }
      });
    },
    //提示：
    tiShi() {
      this.$message("功能正在开发中,敬请期待！");
    },
    //首页调整判断
    homePage() {
      var user = JSON.parse(localStorage.getItem("user"));
      if(user.aicPermissions.leadershipPerspective != undefined || user.aicPermissions.leadershipPerspective != null){
        this.$router.push({ path: "/tjfx/tjbb/cybb/Advancedbusiness" });
      }else {
        this.$router.push('/')
      }
    },
  },
  mounted() {
    this.getNextList();
    let user = JSON.parse(localStorage.getItem("user"));
    if (user) {
      this.user = user;
    }
    // 时钟
    // this.timer = setInterval(() => {
    //     this.dateStr = moment().format('YYYY年MM月DD日 dddd HH:mm:ss');
    // }, 1000);

    // this.handleScroll();

    //获取下级单位列表 todo
    // findOrgUnitByRegionId({ regionId: user.regionID }).then(data => {
    //   if (data.data) {
    //     this.$store.dispatch("updateUnitList", data.data);
    //   }
    // });
  },
  beforeDestroy() {
    // if (this.timer) {
    //     clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    // }
  },
  watch: {
    $route: {
      immediate: true,
      handler: function(to, form) {
        this.breadcrumb = [];
        //更新面包削
        let keyStr = to.path;
        if (keyStr.substr(0, 1) === "/") {
          keyStr = keyStr.slice(1, keyStr.length);
        }
        if (keyStr.substr(keyStr.length - 1, keyStr.length) === "/") {
          keyStr = keyStr.slice(0, keyStr.length - 1);
        }
        let innerKey = "";
        for (let value of keyStr.split("/")) {
          innerKey += "._$" + value;
          if (this.breadcrumb.length === 0) {
            this.breadcrumb.push({
              name: "首页",
              path: "/",
              isAddress: true,
              aliasName: "home"
            });
          } else {
            try {
              this.breadcrumb.push({
                name: eval(`this.GLOBAL.myRoute${innerKey}.name`),
                path: eval(`this.GLOBAL.myRoute${innerKey}.path`),
                isAddress: !!eval(`this.GLOBAL.myRoute${innerKey}.component`),
                aliasName: value
              });
            } catch (err) {}
          }
        }
      }
    }
  }
};
</script>


<style scoped lang="scss">
@import "~scss_vars";

.container {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;

  .header {
    height: 85px;
    background: $color-primary;
    background-image: url("../assets/temp.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: relative;
    color: #fff;

    .userinfo {
      /*text-align: right;*/
      /*padding-right: 35px;*/
      padding: 0 20px;
      height: 100%;
      /*line-height: 50px;*/

      .userinfo-inner {
        cursor: pointer;
        color: black;

        img {
          width: 40px;
          height: 40px;
          border-radius: 20px;
          margin: 5px 0 0 10px;
          float: right;
        }
      }
    }

    .logo {
      //width:230px;
      display: flex;
      align-items: center;
      height: 100%;
      font-size: 22px;
      border-color: rgba(238, 241, 146, 0.3);
      border-right-width: 0px;
      border-right-style: solid;

      img {
        width: 600px;
        /*height: 40px;*/
      }

      .txt {
        color: #fff;
      }
    }

    .tools {
      padding: 60px 23px;
      width: 14px;
      height: 60px;
      line-height: 60px;
      cursor: pointer;
    }
  }

  .main {
    display: flex;
    // background: #324057;
    position: absolute;
    top: 85px;
    bottom: 0;
    overflow: hidden;

    .content-container {
      // background: #f1f2f7;
      flex: 1;
      // position: absolute;
      // right: 0px;
      // top: 0px;
      // bottom: 0px;
      // left: 230px;
      overflow-y: scroll;
      padding: 20px;

      .breadcrumb-container {
        height: 25px;
        //margin-bottom: 15px;
        .title {
          width: 200px;
          float: left;
          color: #475669;
        }

        .breadcrumb-inner {
          /*float: left;*/
          color: #475669;
          line-height: 20px;
          font-size: 15px;
        }
      }

      .content-wrapper {
        background-color: #fff;
        box-sizing: border-box;
        min-height: 75vh;
      }
    }
  }
}

.head_btn {
  color: black;
  /*color: #2517ec;*/
  float: right;
  margin-right: 25px;

  .el-button {
    color: inherit;
  }

  .date {
    float: left;
    line-height: 36px;
    margin-right: 15px;
  }
}

.support_text {
  margin-top: 20px;
  font-size: 12px;
  color: #262626;
}
</style>
<style>
/*列表表格滚动隐藏*/
.el-table__body-wrapper {
  overflow-y: hidden;
  overflow-x: hidden;
}

/*隐藏tabs里面的下拉框表单行高*/
.el-tabs .el-row .el-form-item__content,
.box-card .el-form .el-row .el-form-item__content {
  line-height: normal;
}

/*把tabs里面的整体表单居中*/
.el-tabs .el-form .el-row {
  margin-left: -40px;
  margin-right: 40px;
}

.box-card .el-form .el-row {
  margin-left: -40px;
  margin-right: 40px;
}

/*覆盖流程跟踪行展开样式*/
.demo-table-expand .el-form-item .el-form-item__label {
  font-weight: bold;
}

/*设置文本框内的字体样式*/
.el-textarea__inner {
  color: #1f2d3d;
  font-family: inherit;
}

.el-table .cell,
.el-table th > div {
  padding-left: 8px;
  padding-right: 8px;
}

* {
  font-size: 13px;
}

.el-form-item__label {
  font-size: 13px;
}

.el-message {
  margin-top: 80px;
}

.el-loading-mask {
  z-index: 1000;
}

.el-message-box__message > p {
  font-size: 15px;
}
</style>
