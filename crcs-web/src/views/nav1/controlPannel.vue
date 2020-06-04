<template>
  <div class="controlPannel">
    <!-- 第一行卡片 -->
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="5" :lg="4">
        <div class="grid-content bg-purple">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listName1 needDeal">
              <div class="listIcon1">
                <span class="iconfont el-icon-erpdengji"></span>
              </div>
              <div class="listTitle1">
                <ul>
                  <li @click="goToDeal()">未受理登记待办</li>
                  <li @click="goOutSize()">12345系统待办</li>
                  <li @click="goToRegister()">受理登记待办</li>
                </ul>
              </div>
            </div>
            <div class="cartBottom" style="padding: 30px;">
              <div class="total">{{dealData}}</div>
            </div>
          </el-card>
        </div>
      </el-col>

      <el-col :xs="24" :sm="12" :md="5" :lg="4">
        <div class="grid-content bg-purple-light">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listName">
              <div class="listIcon">
                <span class="iconfont el-icon-erpshenqingshenpi"></span>
              </div>
              <div class="listTitle" @click="goToDealList()">工单已办</div>
            </div>
            <div class="cartBottom" style="padding: 30px;">
              <div class="total">{{total}}</div>
            </div>
          </el-card>
        </div>
      </el-col>

      <el-col :xs="24" :sm="12" :md="5" :lg="4">
        <div class="grid-content bg-purple">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listName">
              <div class="listIcon">
                <span class="iconfont el-icon-erpchazhaobiaodanliebiao"></span>
              </div>
              <div class="listTitle" @click="goToSearch()">工单查询</div>
            </div>
            <div class="cartBottom" style="padding: 30px;">
              <div class="total">132</div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="5" :lg="4">
        <div class="grid-content bg-purple">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listName">
              <div class="listIcon">
                <span class="iconfont el-icon-erpshezhi"></span>
              </div>
              <div class="listTitle" @click="goToSetUp()">系统管理</div>
            </div>
            <div class="cartBottom" style="padding: 30px;">
              <div class="total">423</div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="4" :lg="8">
        <div class="grid-content bg-purple-light" @click="wariningList()">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listNameWaring">
              <div class="box-cardTwo">
                <div class="warning">
                  <div
                    class="waringIcon iconfont el-icon-erpdiandeng"
                    style="color:rgb(255, 73, 73)"
                  ></div>
                  <div class="waringContent">24</div>
                  <div class="warinName">工单红牌</div>
                </div>
              </div>
              <div class="box-cardTwo">
                <div class="warning">
                  <div
                    class="waringIcon iconfont el-icon-erpdiandeng"
                    style="color:rgb(255, 255, 168)"
                  ></div>
                  <div class="waringContent">24</div>
                  <div class="warinName">工单黄牌</div>
                </div>
              </div>
              <div class="box-cardTwo">
                <div class="warning">
                  <div
                    class="waringIcon iconfont el-icon-erpdiandeng"
                    style="color:rgb(255, 179, 102)"
                  ></div>
                  <div class="waringContent">24</div>
                  <div class="warinName">工单预警</div>
                </div>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <!-- 第二行卡片 -->
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content bg-purple">
          <el-card class="box-card dealCart">
            <div id="pie" class="pie"></div>
          </el-card>
        </div>
      </el-col>

      <el-col :xs="24" :sm="24" :md="24" :lg="12">
        <div class="grid-content bg-purple">
          <el-card class="box-card dealCart">
            <div class="dealPieTitle">
              <p>被投诉Top5的企业</p>
            </div>
            <div class="search">
              <el-col :span="24">
                <el-form v-model="search" :inline="true">
                  <el-form-item>
                    <div class="block">
                      <el-date-picker
                        v-model="value7"
                        type="daterange"
                        align="right"
                        placeholder="选择日期范围"
                        :picker-options="pickerOptions2"
                      ></el-date-picker>
                    </div>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" native-type="submit" @click="getPieData">查询</el-button>
                  </el-form-item>
                </el-form>
              </el-col>
            </div>
            <div id="pieThire" class="pie" style="height:250px;"></div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {
  totalFirstData,
  endListTasklist,
  getPercentage,
  getTrafficCount,
  dealListTotal
} from "../../api/api";
export default {
  data() {
    return {
      currentDate: new Date(),
      //第一行第一张卡片
      dealData: 0,
      // 处理工单总数
      total: 0,
      // 日期选择数据
      pickerOptions2: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      search: "",
      value7: "",
      //  第二行卡片
      pieOption: {
        baseOption: {
          color:['rgb(102, 179, 255)','rgb(255, 179, 102)','rgb(255, 209, 163)'],
          title: {
            text: "顺德监管局各类业务占比",
            x: "0",
            textStyle:{
              color:"rgb(32, 144, 255)"
            }
          },
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          toolbox: {
            show: true,
            feature: {
              saveAsImage: { show: true }
            }
          },
          legend: {
            orient: "horizontal",
            left: "0",
            top: "10%",
            data: ["咨询登记", "消费投诉", "经济违法"]
          },
          series: [
            {
              name: "访问来源",
              type: "pie",
              radius: "55%",
              center: ["50%", "60%"],
              data: [
                { value: 335, name: "咨询登记" },
                { value: 310, name: "消费投诉" },
                { value: 234, name: "经济违法" }
              ],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)"
                }
              }
            }
          ]
        },
        media: [
          {
            option: {
              legend: {
                left: "0",
                top: "5%",
                orient: "horizontal"
              },
              series: [
                {
                  radius: [20, "50%"],
                  center: "center"
                }
              ]
            }
          },
          {
            query: {
              minAspectRatio: 1
            },
            option: {
              legend: {
                left: "0",
                orient: "horizontal",
                textStyle:{
                 color:['rgb(102, 179, 255)','rgb(255, 179, 102)','rgb(255, 209, 163)'],

                }
              },
              series: [
                {
                  radius: [20, "50%"],
                  center: "center"
                }
              ]
            }
          },
          {
            query: {
              maxAspectRatio: 1
            },
            option: {
              legend: {
                left: "0",
                orient: "horizontal"
              },
              series: [
                {
                  radius: [20, "50%"],
                  center: "center"
                  // center: ['50%', '30%']
                }
              ]
            }
          },
          {
            query: {
              maxWidth: 500
            },
            option: {
              legend: {
                left: "0",
                top: "5%",
                orient: "vertical"
              },
              series: [
                {
                  radius: [20, "50%"],
                  center: "center"
                  // center: ['50%', '30%']
                }
              ]
            }
          }
        ]
      },

      pieOptionThire: {
        color:['rgb(102, 179, 255)','#2994FF','#0075EB','#FF9429','rgb(255, 209, 163)'],
        title:{
          text:"投诉次数",
          left:"center",
          top:"center",
          textStyle:{
            color:"rgb(32, 144, 255)",
            fontSize: '16px'
          }
        },
        tooltip: {
          trigger: "item"
        },
        legend: {
          left:"center",
          orient: "horizontal",
          x: "left",
          data: ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            roseType: "radius",
            radius: ["30%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center",
                formatter: "{b}: {c} ({d}%)"
              },
              emphasis: {
                show: false,
                textStyle: {
                  fontSize: "20",
                  fontWeight: "bold"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              { value: 335, name: "直接访问" },
              { value: 310, name: "邮件营销" },
              { value: 234, name: "联盟广告" },
              { value: 135, name: "视频广告" },
              { value: 1548, name: "搜索引擎" }
            ]
          }
        ]
      }
    };
  },
  methods: {
    // 查询
    getPieData() {
      console.log(1);
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    // 12345
    goOutSize() {
      this.$router.push({ path: "/wddb/12345db" });
    },
    goToRegister() {
      this.$router.push({ path: "/wddb/bxtdb" });
    },
    drawPie() {
      let drawPie = this.$echarts.init(document.getElementById("pie"));
      drawPie.setOption(this.pieOption);
    },
    drawPieThire() {
      let drawPie = this.$echarts.init(document.getElementById("pieThire"));
      drawPie.setOption(this.pieOptionThire);
    },
    goToDeal() {
      this.$router.push({ path: "/wddb/dfpyw" });
    },
    goToDealList() {
      this.$router.push({ path: "/wddb/bxtdb" });
    },
    goToSearch() {
      this.$router.push({ path: "/tjfx/zhcx/ywjlcx" });
    },
    goToSetUp() {
      this.$router.push({ path: "/xtgl/zjjg" });
    },
    // 获取领导桌面第一张卡片的总数
    getDealData() {
      let params = {};
      totalFirstData(params).then(res => {
        if (res.data.code === 0) {
          this.dealData = res.data.data;
        }
      });
    },
    //工单已办记录数
    endListSize() {
      var user = localStorage.getItem("user");
      user = JSON.parse(user);
      let params = { page: "1", total: "1", size: "1", nextnodeid: "end" };
      endListTasklist(params).then(res => {
        if (res.code == 0) {
          this.total = res.data.total; //总数量(已办结工单)
          //  console.log("工单已办总数="+this.total)
        }
      });
    }
  },
  mounted() {
    this.drawPie();
    // this.drawPieTwo();
    this.drawPieThire();
  }
};
</script>
<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: rgb(32, 144, 255);
  /* background: #99a9bf; */
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

/* 没有图片的卡片样式 */
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
}
/* 控制面板第一行的样式 */
.listName {
  display: flex;
  align-items: center;
  width: 100%;
  height: 150px;
  border-top: 3px solid rgb(32, 144, 255);
  .listIcon {
    flex: 1;
    color: #ddefff;
    text-align: center;
    .el-icon-erpdaichulidingdan:before {
      font-size: 70px;
    }
    .el-icon-erpshenqingshenpi:before {
      font-size: 55px;
      margin-left: 10px;
    }
    .el-icon-erpchazhaobiaodanliebiao:before {
      font-size: 60px;
      margin-left: 10px;
    }
    .el-icon-erpxiaoxi-jinggao:before {
      font-size: 60px;
      color: #ff4949;
    }
    .el-icon-erpdengji:before {
      font-size: 60px;
    }
    .el-icon-erpshezhi:before {
      font-size: 60px;
      margin-left: 10px;
    }
  }
  .listTitle {
    flex: 2;
    cursor: pointer;
    text-align: right;
    margin-right: 14px;
    font-size: 16px;
    color: rgb(32, 144, 255);
    .box-cardTwo {
      /* width: 200px !important; */
      /* height: 30px;
       */
      margin-bottom: 10px;
      margin-top: 10px;
      .el-card__body {
        padding: none !important;
        .warning {
          display: flex;
          align-items: center;
          margin-top: -13px;
          .waringTitle {
            flex: 1;
            text-align: center;
            color: rgb(32, 144, 255);
            font-weight: 600;
          }
          .waringContent {
            flex: 1;
            text-align: center;
            font-weight: 600;
            color: #ff4949;
          }
        }
      }
    }
  }
}
.warinName {
  width: 100%;
  color: rgb(32, 144, 255);
  text-align: center;
  margin-top: 10px;
}
/* 控制面板第一行第一个 */
.listName1 {
  position: relative;
  align-items: center;
  width: 100%;
  height: 150px;
  border-top: 3px solid rgb(32, 144, 255);
  .listIcon1 {
    position: absolute;
    left: 5%;
    top: 50%;
    margin-top: -35px;
    color: #ddefff;
    text-align: center;

    .el-icon-erpdengji:before {
      font-size: 70px;
    }
  }
  .listTitle1 {
    position: relative;
    margin-top: 40px;
    /* margin-top:45%; */
    /* transform: translateY(-50%); */
    z-index: 2;
    text-align: right;
    margin-right: 14px;
    font-size: 20px;
    color: rgb(32, 144, 255);
    & > ul {
      list-style: none;
      & > li {
        margin-bottom: 5px;
        font-size: 16px;
        cursor: pointer;
      }
    }
    .box-cardTwo {
      /* width: 200px !important; */
      /* height: 30px; */

      margin-right: 0;
      margin-bottom: 10px;
      margin-top: 10px;
      .el-card__body {
        padding: none !important;
        .warning {
          display: flex;
          align-items: center;
          .waringTitle {
            flex: 1;
            text-align: center;
            color: #97a8be;
            font-weight: 600;
          }
          .waringContent {
            flex: 1;
            text-align: center;
            font-weight: 600;
            color: #ff4949;
          }
        }
      }
    }
  }
}
.waringIcon {
  margin-bottom: 20px;
}
.waringContent {
  text-align: center;
}
.listNameWaring {
  display: flex;
  align-items: center;
  height: 236px;
  border: none !important;
  .warning {
    text-align: center;
    margin: 0 auto;
  }
  .waringContent {
    font-size: 20px;
    color: rgb(32, 144, 255);
  }
}
.cartBottom {
  background: rgb(32, 144, 255);
  /* background: #99ccff; */

  /* background: linear-gradient(to bottom,  0%, #ffffff 100%); */
  .total {
    font-size: 20px;
    font-weight: 600;
    color: #fff;
    text-align: right;
  }
}
.box-cardTwo {
  flex: 1;
}

.el-col {
  margin-top: 10px;
}
/* 第二行卡片样式 */
.dealCart {
  height: 360px;
  .el-tabs__item {
    font-size: 16px;
  }
  .dealPieTitle {
    p {
      font-size: 18px;
      color: rgb(32, 144, 255);
      font-weight: 600;
      text-align: left;
      padding-left:5px;
      margin: 0;
    }
  }
}
/* 饼形图样式 */
.pie {
  width: 100%;
  height: 300px;
}
/* 搜索 */
.search {
  width: 100%;
  height: 50px;
}
.el-form--inline {
  text-align: center;
}
</style>