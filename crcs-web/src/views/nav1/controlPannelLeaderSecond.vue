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
            <div class="cartBottom" style="padding: 30px 14px 30px 0;">
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
            <div class="cartBottom" style="padding: 30px 14px 30px 0;">
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
            <div class="cartBottom" style="padding: 30px 14px 30px 0;">
              <div class="total" @click="goToSearch()" style="font-size:16px;font-weight:500">工单查询</div>
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
              <div class="listTitle">系统管理</div>
            </div>
            <div class="cartBottom" style="padding: 30px 14px 30px 0;">
              <div class="total" style="font-size:16px;font-weight:500;">系统管理</div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="4" :lg="8">
        <div class="grid-content bg-purple-light" @click="wariningList()">
          <el-card :body-style="{ padding: '0px' }">
            <div class="listName listNameWaring">
              <!-- <div class="listIcon">
                <span class="iconfont el-icon-erpxiaoxi-jinggao"></span>
              </div>-->
              <div class="listTitle listFile">
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <!-- <el-card class="box-card box-cardTwo"> -->
                      <div class="box-cardTwo">
                        <div class="warning">
                          <div class="waringIcon iconfont el-icon-erpdiandeng"></div>
                          <!-- <div class="waringTitle">工单红牌：</div> -->
                          <div class="waringContent">24</div>
                          <div class="waringTitle">工单红牌</div>
                        </div>
                      </div>
                      <!-- </el-card> -->
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple-light">
                      <!-- <el-card class="box-card box-cardTwo"> -->
                      <div class="warning">
                        <div class="box-cardTwo">
                          <div class="warning">
                            <div
                              class="waringIcon iconfont el-icon-erpdiandeng"
                              style="color:rgb(255, 255, 168)"
                            ></div>
                            <!-- <div class="waringTitle">工单黄牌：</div> -->
                            <div class="waringContent">24</div>
                            <div class="waringTitle">工单黄牌</div>
                          </div>
                        </div>
                      </div>
                      <!-- </el-card> -->
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-purple">
                      <!-- <el-card class="box-card box-cardTwo"> -->
                      <div class="warning">
                        <div class="box-cardTwo">
                          <div
                            class="waringIcon iconfont el-icon-erpdiandeng"
                            style="color:rgb(255, 179, 102)"
                          ></div>
                          <!-- <div class="waringTitle">工单预警：</div> -->
                          <div class="waringContent">24</div>
                          <div class="waringTitle">工单预警</div>
                        </div>
                      </div>
                      <!-- </el-card> -->
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
          </el-card>
          <!-- <el-card class="box-card">
            <div v-for="o in 4" :key="o" class="text item">{{'列表内容 ' + o }}</div>
          </el-card>-->
        </div>
      </el-col>
    </el-row>
    <!-- 第二行卡片 -->
    <el-row :gutter="20">
      <!-- 左边 -->
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card dealCart">
            <div class="dealListTitle">
              <p>在办统计</p>
            </div>
            <div class="dealList">
              <ul>
                <li>
                  <div class="dealType">
                    <div class="dealTitle">咨询登记</div>
                    <div class="dealNumber">{{dealregisterNumber}}</div>
                  </div>
                </li>
                <li>
                  <div class="dealType">
                    <div class="dealTitle">消费投诉</div>
                    <div class="dealNumber">{{dealComplainNumber}}</div>
                  </div>
                </li>
                <li>
                  <div class="dealType">
                    <div class="dealTitle">经济违法</div>
                    <div class="dealNumber">{{dealEconomyNumber}}</div>
                  </div>
                </li>
              </ul>
            </div>
          </el-card>
        </div>
      </el-col>
      <!-- 右边 -->
      <el-col :xs="24" :sm="24" :md="12" :lg="16">
        <div class="grid-content bg-purple doublePie">
          <el-card class="box-card dealCart">
            <div id="chartOne" class="pie"></div>
          </el-card>
          <div class="tips">
            本月新增(减少)业务120单,其中咨询登记业务增加(减少)
            <span>10</span>单,消费投诉增加(减少)
            <span>90</span>单,经济违法增加
            <span>20</span>单;
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- 第三行卡片 -->
    <el-row :gutter="20">
      <!-- 左边 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="theThird">
          <el-card class="box-card settlementRateCard">
            <div class="settlementRateTitle">
              <p>办结率统计</p>
            </div>
            <div class="settlementRate">
              <ul>
                <li>
                  <!-- <div class="settlementRateName">咨询登记</div>
                  <div class="settlementRateNumber">34%</div>-->
                  <div class="settleRate" id="settleRateRegister"></div>
                  <div class="registerName">咨询登记</div>
                </li>
                <li>
                  <div class="settleRate" id="settleRateComplain"></div>
                  <!-- <div class="settlementRateName">消费投诉</div>
                  <div class="settlementRateNumber">54%</div>-->
                  <div class="registerName">消费投诉</div>
                </li>
                <li>
                  <div class="settleRate" id="settleRateEconomy"></div>
                  <div class="registerName">经济违法</div>
                  <!-- <div class="settlementRateName">咨询登记</div>
                  <div class="settlementRateNumber">69%</div>-->
                </li>
              </ul>
            </div>
          </el-card>
        </div>
      </el-col>
      <!-- 右边 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="16">
        <div class="theThird">
          <el-card class="box-card histogramCard">
            <div class="histogram" id="histogram"></div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <!-- 第四行 -->
    <el-row :gutter="20">
      <!-- 左边 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="theThird">
          <el-card class="box-card settlementRateCard">
            <div class="settlementRateTitle mapTitle">
              <p>投诉热点地区</p>
            </div>
            <div class="map" id="map"></div>
          </el-card>
        </div>
      </el-col>
      <!-- 中间 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="theThird">
          <el-card class="box-card histogramCard">
            <div class="settlementRateTitle hotTradeTitle">
              <p>投诉热点行业Top5</p>
            </div>
            <div class="hotTrade" id="hotTrade"></div>
          </el-card>
        </div>
      </el-col>
      <!-- 右边 -->
      <el-col :xs="24" :sm="24" :md="24" :lg="8">
        <div class="theThird">
          <el-card class="box-card histogramCard">
            <div class="settlementRateTitle hotTradeTitle">
              <p>本周投诉热点商家Top10</p>
            </div>
            <div class="searchTopTenData">
              <el-date-picker
                v-model="searchTopTenData"
                type="daterange"
                align="right"
                placeholder="选择日期范围"
                :picker-options="pickerOptions2"
              ></el-date-picker>
              <el-button type="primary">筛选</el-button>
            </div>
            <div class="hotTrade" id="hotTrade">
              <ul>
                <li>
                  <span class="topOne">1</span> xxxx饭店
                </li>
                <li>
                  <span class="topTwo">2</span>xxxxktv
                </li>
                <li>
                  <span class="topThree">3</span>xxxx酒吧
                </li>
                <li>
                  <span class="top10">4</span>xxxx服装店
                </li>
                <li>
                  <span class="top10">5</span>xxxxx影视城
                </li>
                <li>
                  <span class="top10">6</span>xxxxx咖啡馆
                </li>
                <li>
                  <span class="top10">7</span>xxxxx电影院
                </li>
                <li>
                  <span class="top10">8</span>xxxxxxxx按摩店
                </li>
                <li>
                  <span class="top10">9</span>xxxxxxxxxx医院
                </li>
                <li>
                  <span class="top10">10</span>xxxxxxxxxxxx投行
                </li>
              </ul>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import shundeMapJson from "../../assets/json/shunde.json";
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
      total: 0,
      // 在办统计数据
      dealregisterNumber: 0,
      dealComplainNumber: 0,
      dealEconomyNumber: 0,
      currentDate: new Date(),
      // 柱状条形图的数据
      histogramData: [],
      //  第二行卡片
      // 双层嵌套的饼形
      doublepieOption: {
        title: {
          left: "left",
          text: "各地区的业务量比较",
          textStyle: {
            // color: "#333"
            color: "#1f8fff",
            fontsize: 20
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          y: "10%",
          data: ["咨询登记", "消费投诉", "经济违法"]
        },
        series: [
          {
            name: "地域",
            type: "pie",
            selectedMode: "single",
            color: ["#9FE6B8", "#FFDB5C", "#ff9f7f"],
            radius: [0, "30%"],

            label: {
              normal: {
                position: "inner"
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              { value: 335, name: "咨询登记", selected: true },
              { value: 679, name: "消费投诉" },
              { value: 1548, name: "经济违法" }
            ]
          },
          {
            name: "地区",
            type: "pie",
            radius: ["40%", "55%"],
            color: [
              "#0070E0",
              "#5CADFF",
              "#99FFFF",
              "#99FFCC",
              "#CCFF99",
              "#FFFF99",
              "#ffffcc",
              "#FFCC99",
              "#FFAD5C",
              "#FF8F1F"
            ],
            label: {
              normal: {
                formatter: "{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",
                backgroundColor: "#fff",
                borderColor: "#1F8FFF",
                color: "#1F8FFF",
                borderWidth: 1,
                borderRadius: 4,
                // shadowBlur:3,
                // shadowOffsetX: 2,
                // shadowOffsetY: 2,
                // shadowColor: '#999',
                // padding: [0, 7],
                rich: {
                  a: {
                    //   color: "#999",
                    lineHeight: 22,
                    align: "center",
                    color: "#1F8FFF"
                  },

                  hr: {
                    borderColor: "#1F8FFF",
                    width: "100%",
                    color: "#1F8FFF",
                    borderWidth: 0.5,
                    height: 0
                  },
                  b: {
                    fontSize: 12,
                    lineHeight: 33,
                    color: "#1F8FFF"
                  },
                  per: {
                    color: "#eee",
                    backgroundColor: "#1F8FFF",
                    padding: [2, 4],
                    borderRadius: 2
                  }
                }
              }
            },
            labelLine: {
              lineStyle: {
                color: "#1F8FFF"
              }
            },
            data: [
              { value: 335, name: "乐从镇" },
              { value: 310, name: "陈村" },
              { value: 234, name: "勒流镇" },
              { value: 135, name: "大良镇" },
              { value: 1048, name: "杏坛镇" },
              { value: 251, name: "均安镇" },
              { value: 147, name: "容桂镇" },
              { value: 102, name: "龙江镇" },
              { value: 102, name: "伦教镇" },
              { value: 102, name: "北滘镇" }
            ]
          }
        ]
      },
      // 柱状折线图
      histogramOption: {
        title: {
          text: "2019年的业务量统计",
          textStyle: {
            // color: "#333"
            color: "#1f8fff"
          }
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            crossStyle: {
              color: "#999"
            }
          }
        },
        toolbox: {
          feature: {
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ["处理量", "趋势"]
        },
        xAxis: [
          {
            type: "category",
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月"
            ],
            axisPointer: {
              type: "shadow"
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "处理量",
            min: 0,
            max: 5000,
            interval: 500,
            axisLabel: {
              formatter: "{value} 单"
            }
          },
          {
            type: "value",
            name: "趋势",
            min: 0,
            max: 5000,
            interval: 500,
            axisLabel: {
              formatter: "{value} 单"
            }
          }
        ],
        series: [
          {
            name: "处理量",
            type: "bar",
            color: "#1F8FFF",
            data: []
          },
          {
            name: "趋势",
            type: "line",
            yAxisIndex: 1,
            color: "rgb(255, 179, 102)",
            data: [
              2.0,
              2.2,
              3.3,
              4.5,
              6.3,
              10.2,
              20.3,
              23.4,
              23.0,
              16.5,
              12.0,
              6.2
            ]
          }
        ]
      },
      // 条形图
      hotTradeOption: {
        title: {},
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        legend: {
          data: ["2018年", "2019年"]
        },
        grid: {
          top: "10%",
          left: "3%",
          right: "8%",
          bottom: "3%",
          containLabel: true
        },
        xAxis: {
          type: "value",
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          type: "category",
          data: ["餐饮", "服装", "建筑", "房地产业", "卫生"]
        },
        series: [
          {
            name: "2018年",
            type: "bar",
            data: [18203, 23489, 29034, 104970, 131744],
            color: "rgb(255, 179, 102)"
          },
          {
            name: "2019年",
            type: "bar",
            data: [19325, 23438, 31000, 121594, 134141],
            color: "#1F8FFF"
          }
        ]
      },
      //顺德地图数据
      shundeMapOption: {
        tooltip: {
          trigger: "item",
          formatter: "{b}<br/>{c} (单)"
        },
        visualMap: {
          min: 500,
          max: 50000,
          text: ["High", "Low"],
          left: "right",
          realtime: false,
          calculable: true,
          inRange: {
            color: ["#e2f2f8", "#5CADFF", "#ffac38", "rgb(255, 179, 102)"]
          }
        },
        series: [
          {
            name: "shunde",
            type: "map",
            mapType: "shunde",
            aspectScale: 0.85, //地图长度比
            label: {
              normal: {
                show: true,
                textStyle: {
                  color: "#fff"
                }
              },
              emphasis: {
                show: true,
                textStyle: {
                  color: "#333"
                }
              }
            },
            data: [
              { name: "陈村镇", value: 17000 },
              { name: "北滘镇", value: 1000 },
              { name: "乐从镇", value: 5000 },
              { name: "伦教镇", value: 20000 },
              { name: "勒流镇", value: 25000 },
              { name: "龙江镇", value: 30000 },
              { name: "大良镇", value: 18000 },
              { name: "杏坛镇", value: 2300 },
              { name: "容桂镇", value: 20000 },
              { name: "均安镇", value: 16000 }
            ]
          }
        ]
      },
      // 办结率的数据
      economyNumber: 0,
      complainNumber: 0,
      registerNumber: 0,
      // 经济违法办结率数据
      economyOption: {
        title: {
          show: true,
          text: "",
          x: "center",
          y: "center",
          textStyle: {
            fontSize: "15",
            color: "#fff",
            fontWeight: "normal"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{d}%",
          show: false
        },
        legend: {
          orient: "vertical",
          x: "left",
          show: false
        },
        series: []
      },

      // 咨询登记办结率数据
      registerOption: {
        title: {
          show: true,
          text: "",
          x: "center",
          y: "center",
          textStyle: {
            fontSize: "15",
            color: "#fff",
            fontWeight: "normal"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{d}%",
          show: false
        },
        legend: {
          orient: "vertical",
          x: "left",
          show: false
        },
        series: []
      },

      // 消费投诉办结率数据
      complainOption: {
        title: {
          show: true,
          text: "",
          x: "center",
          y: "center",
          textStyle: {
            fontSize: "15",
            color: "#fff",
            fontWeight: "normal"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{d}%",
          show: false
        },
        legend: {
          orient: "vertical",
          x: "left",
          show: false
        },
        series: []
      },
      // 获取代办书来给你
      dealData: 0,
      // 时间选择
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
      searchTopTenData: "",
      value7: ""
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    drawDoublePie() {
      let drawChartOne = this.$echarts.init(
        document.getElementById("chartOne")
      );
      drawChartOne.setOption(this.doublepieOption);
    },
    // 柱状折线图
    drawHistogram() {
      let histogramChart = this.$echarts.init(
        document.getElementById("histogram")
      );
      histogramChart.setOption(this.histogramOption);
    },
    // 条形图
    drawhotTrade() {
      let hotTrade = this.$echarts.init(document.getElementById("hotTrade"));
      hotTrade.setOption(this.hotTradeOption);
    },
    // 地图
    drawMap() {
      let shundeMap = this.$echarts.init(document.getElementById("map"));

      this.$echarts.registerMap("shunde", shundeMapJson, {});

      shundeMap.setOption(this.shundeMapOption);
    },
    goToDeal() {
      this.$router.push({ path: "/wddb/dfpyw" });
    },
    // 12345
    goOutSize() {
      this.$router.push({ path: "/wddb/12345db" });
    },
    goToRegister() {
      this.$router.push({ path: "/wddb/bxtdb" });
    },
    //工单已办
    goToDealList() {
      this.$router.push({ path: "/controlpannelLeader/gdyb" });
    },
    goToSearch() {
      this.$router.push({ path: "/tjfx/zhcx/ywjlcx" });
    },
    goToSetUp() {
      this.$router.push({ path: "/xtgl/zjjg" });
    },
    // 经济违法办结率
    economyRate() {
      let economyRate = this.$echarts.init(
        document.getElementById("settleRateEconomy")
      );
      economyRate.setOption(this.economyOption);
    },
    // 咨询登记办结率
    registerRate() {
      let registerRate = this.$echarts.init(
        document.getElementById("settleRateRegister")
      );
      registerRate.setOption(this.registerOption);
    },
    // 消费投诉办结率
    complainRate() {
      let complainRate = this.$echarts.init(
        document.getElementById("settleRateComplain")
      );
      complainRate.setOption(this.complainOption);
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
    // 获取业务量统计
    getTrafficCount() {
      let paramsOne = {};
      getTrafficCount(paramsOne).then(res => {
        if (res.data.code === 0) {
          console.log(res);
          this.histogramData = res.data.data;
          this.histogramOption.series[0].data = this.histogramData;
          console.log(this.histogramData);
          this.drawHistogram();
        }
      });
    },
    // 获取办结率
    getPercentage() {
      let params = {};
      getPercentage(params).then(res => {
        if (res.data.code == 0) {
          let dataList = res.data.data;
          this.economyNumber = Math.round((dataList[1] / dataList[0]) * 100);
          this.complainNumber = Math.round((dataList[3] / dataList[2]) * 100);
          this.registerNumber = Math.round((dataList[5] / dataList[4]) * 100);
          // console.log(this.economyNumber);
          // console.log(this.complainNumber);
          // console.log(this.registerNumber);
          this.economyOption.title.text = this.economyNumber + "%";
          this.registerOption.title.text = this.registerNumber + "%";
          this.complainOption.title.text = this.complainNumber + "%";
          this.economyOption.series = [
            {
              name: "",
              type: "pie",
              radius: ["65%", "85%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: this.economyNumber,
                  name: "",
                  itemStyle: {
                    color: "#1f8fff",
                    borderWidth: 10
                  }
                },
                {
                  value: 100 - this.economyNumber,
                  name: "",
                  itemStyle: {
                    color: "#99ccff"
                  }
                }
              ]
            },
            {
              name: "",
              type: "pie",
              radius: ["0", "50%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: 100,
                  name: "",
                  itemStyle: {
                    color: "#007bf6",
                    borderWidth: 10
                  }
                }
              ]
            }
          ];
          this.registerOption.series = [
            {
              name: "",
              type: "pie",
              radius: ["65%", "85%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: this.registerNumber,
                  name: "",
                  itemStyle: {
                    color: "#1f8fff",
                    borderWidth: 10
                  }
                },
                {
                  value: 100 - this.registerNumber,
                  name: "",
                  itemStyle: {
                    color: "#99ccff"
                  }
                }
              ]
            },
            {
              name: "",
              type: "pie",
              radius: ["0", "50%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: 100,
                  name: "",
                  itemStyle: {
                    color: "#007bf6",
                    borderWidth: 10
                  }
                }
              ]
            }
          ];
          this.complainOption.series = [
            {
              name: "",
              type: "pie",
              radius: ["65%", "85%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: this.complainNumber,
                  name: "",
                  itemStyle: {
                    color: "#1f8fff",
                    borderWidth: 10
                  }
                },
                {
                  value: 100 - this.complainNumber,
                  name: "",
                  itemStyle: {
                    color: "#99ccff"
                  }
                }
              ]
            },
            {
              name: "",
              type: "pie",
              radius: ["0", "50%"],
              avoidLabelOverlap: true,
              hoverAnimation: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: false
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: [
                {
                  value: 70,
                  name: "",
                  itemStyle: {
                    color: "#007bf6",
                    borderWidth: 10
                  }
                }
              ]
            }
          ];
          this.economyRate();
          this.registerRate();
          this.complainRate();
        }
      });
    },
    // 在办统计
    getdealListTotal() {
      let params = {};
      dealListTotal(params).then(res => {
        if (res.data.code === 0) {
          this.dealregisterNumber = res.data.data[0];
          this.dealComplainNumber = res.data.data[1];
          this.dealEconomyNumber = res.data.data[2];
        } else {
          console.log(res.data.msg);
        }
      });
    }
  },
  mounted() {
    this.economyOption.series = [
      {
        name: "",
        type: "pie",
        radius: ["65%", "85%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: this.economyNumber,
            name: "",
            itemStyle: {
              color: "#1f8fff",
              borderWidth: 10
            }
          },
          {
            value: 100 - this.economyNumber,
            name: "",
            itemStyle: {
              color: "#99ccff"
            }
          }
        ]
      },
      {
        name: "",
        type: "pie",
        radius: ["0", "50%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: 100,
            name: "",
            itemStyle: {
              color: "#007bf6",
              borderWidth: 10
            }
          }
        ]
      }
    ];
    this.registerOption.series = [
      {
        name: "",
        type: "pie",
        radius: ["65%", "85%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: this.registerNumber,
            name: "",
            itemStyle: {
              color: "#1f8fff",
              borderWidth: 10
            }
          },
          {
            value: 100 - this.registerNumber,
            name: "",
            itemStyle: {
              color: "#99ccff"
            }
          }
        ]
      },
      {
        name: "",
        type: "pie",
        radius: ["0", "50%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: 100,
            name: "",
            itemStyle: {
              color: "#007bf6",
              borderWidth: 10
            }
          }
        ]
      }
    ];
    this.complainOption.series = [
      {
        name: "",
        type: "pie",
        radius: ["65%", "85%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: this.complainNumber,
            name: "",
            itemStyle: {
              color: "#1f8fff",
              borderWidth: 10
            }
          },
          {
            value: 100 - this.complainNumber,
            name: "",
            itemStyle: {
              color: "#99ccff"
            }
          }
        ]
      },
      {
        name: "",
        type: "pie",
        radius: ["0", "50%"],
        avoidLabelOverlap: true,
        hoverAnimation: false,
        label: {
          normal: {
            show: false,
            position: "center"
          },
          emphasis: {
            show: false
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: 70,
            name: "",
            itemStyle: {
              color: "#007bf6",
              borderWidth: 10
            }
          }
        ]
      }
    ];
    this.endListSize();
    this.getTrafficCount();
    // 获取办结率
    this.getPercentage();
    // this.histogramOption.series[0].data = this.histogramData;

    console.log("=====");
    console.log(this.histogramData);
    console.log(this.histogramOption.series[0].data);

    this.drawDoublePie();
    this.drawhotTrade();
    this.drawMap();
    // this.drawHistogram();

    // 获取代办数据
    this.getDealData();
    this.drawHistogram();
    this.economyRate();
    this.registerRate();
    this.complainRate();
    // 获取在办统计
    this.getdealListTotal();
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
  background: #99a9bf;
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
.el-button--primary{
  background: rgb(31, 143, 255);
  border-color: rgb(31, 143, 255);
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
.searchTopTenData {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 10px;
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
  border-top: 3px solid #1f8fff;
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
    color: #1f8fff;
    & > ul {
      list-style: none;
      & > li {
        margin-bottom: 5px;
        font-size: 16px;
        cursor: pointer;
      }
    }
    .box-cardTwo {
      text-align: center;
      margin: 0 auto;
      height: 150px;
      /* width: 100%; */
      /* width: 200px !important; */
      /* height: 30px; */
      /* margin-right: 5px!important; */
      margin-bottom: 10px;
      /* margin-top: 10px; */
      .el-card__body {
        padding: none !important;
        .warning {
          /* display: flex; */
          align-items: center;
          margin-top: -13px;
          .waringIcon {
            margin: 20px auto 20px;
            color: #ff4949;
          }
        }
      }
    }
  }
  .waringIcon {
    margin: 20px auto 20px;
    color: #ff4949;
  }
  .waringTitle {
    text-align: center;
    color: #1f8fff;
    /* font-weight: 600; */
    margin-top: 10px;
  }
  .waringContent {
    font-size: 20px;
    text-align: center;
    font-weight: 600;
    color: #1f8fff;
  }
}
.listName1 {
  position: relative;
  align-items: center;
  width: 100%;
  height: 150px;
  border-top: 3px solid #1f8fff;
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
    /* transform: translateY(-50%); */
    z-index: 2;
    text-align: right;
    margin-right: 14px;
    font-size: 20px;
    color: #1f8fff;
    & > ul {
      list-style: none;
      & > li {
        margin-bottom: 5px;
        font-size: 16px;
        cursor: pointer;
      }
    }
    .box-cardTwo {
      width: 200px !important;
      height: 30px;
      margin-right: 0;
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
            color: #1f8fff;
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
.listNameWaring {
  height: 236px;
  border: none !important;
}
.cartBottom {
  background: #1f8fff;
  /* background: #99ccff; */
  .total {
    font-size: 20px;
    font-weight: 600;
    color: #fff;
    text-align: right;
  }
}
.el-col {
  margin-top: 10px;
}
/* 第二行卡片样式 */
.dealCart {
  height: 350px;
  .el-tabs__item {
    font-size: 16px;
  }
  .el-card__body {
    height: 100%;
  }
}
/* 在办类型 */
.dealListTitle {
  /* color: #333; */
  color: #1f8fff;
  font-weight: 600;
  & > p {
    text-align: left;
    font-size: 20px;
    margin-top: -3px;
    padding: 0;
  }
}
.dealList {
  width: 100%;
  height: 100%;
  margin-top: 65px;
  & > ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    /* justify-content: space-evenly; */
    & > li {
      flex: 1;
      height: 150px;
      background: #1f8fff;
      /* background: #99ccff; */
      margin-right: 20px;
      /* border-radius: 5px; */
      box-shadow: 5px 3px 5px 0px #ccc;
      .dealType {
        height: 100%;
        box-sizing: border-box;
        align-items: center;
        position: relative;
        .dealTitle {
          height: 70px;
          line-height: 70px;
          font-size: 20px;
          color: #fff;
          text-align: center;
          vertical-align: text-bottom;
          bottom: 0;
        }
        .dealNumber {
          width: 95%;
          margin: 0 auto;
          height: 80px;
          line-height: 80px;
          font-weight: 600;
          text-align: center;
          font-size: 20px;
          color: #fff;
        }
      }
    }
    & > li:last-child {
      margin-right: 0;
    }
  }
}

.pie,
.theThird {
  width: 100%;
  height: 350px;
}
.doublePie {
  position: relative;
  .tips {
    width: 100px;
    height: auto;
    position: absolute;
    top: 0;
    right: 0;
    margin: 10px;
    letter-spacing: 3px;
    color: #334455;
    text-indent: 2em;
    font-size: 14px;
    span {
      color: #ff4949;
      font-weight: 600;
    }
  }
}
/* 第三行卡片 */
.theThird {
  .histogram {
    width: 100%;
    height: 350px;
  }
  .settlementRateTitle {
    /* color: #333; */
    color: #1f8fff;
    font-weight: 600;
    & > p {
      text-align: left;
      font-size: 20px;
      margin-top: -3px;
      padding: 0;
    }
  }
  .settlementRate {
    width: 100%;
    & > ul {
      list-style: none;
      padding: 0;
      margin: 0;
      width: 100%;
      height: 150px;
      display: flex;
      align-items: center;
      justify-content: space-evenly;
      margin-top: 65px;
      & > li {
        width: 100px;
        height: 100px;
        color: rgb(31, 143, 255);

        /* border-radius: 50%; */
        /* background: #99ccff; */
        /* margin-right: 20px; */
        /* box-shadow: 5px 3px 5px 0px #ccc; */
        text-align: center;
        .settleRate {
          width: 100%;
          height: 100%;
          font-size: 16px;
        }
        .registerName {
          text-align: center;
        }
        .settlementRateNumber {
          height: 50px;
          font-size: 16px;
          font-weight: 600;
          line-height: 30px;
          color: #fff;
        }
      }
      & > li:last-child {
        margin-right: 0;
      }
    }
  }
}
.settlementRateCard,
.histogramCard {
  height: 350px;
}
.hotTrade {
  height: 300px;
  ul {
    list-style: none;
    padding: 0;
    margin: 0;
    padding-top: 0;
    margin-top: 10px;
    & > li {
      width: 100%;
      font-size: 16px;
      padding-left: 10px;
      margin-bottom: 2px;
      color: #334455;
      span {
        color: #fff;
        font-size: 14px;
        line-height: 15px;
        width: 15px;
        height: 15px;
        display: inline-block;
        text-align: center;
        margin-right: 5px;
        background: #8eb9f5;
        &.topOne {
          background: #f54545;
        }
        &.topTwo {
          background: #ff8547;
        }
        &.topThree {
          background: #ffac38;
        }
      }
    }
  }
}
.mapTitle p,
.hotTradeTitle p {
  margin-bottom: 0 !important;
}
/* 地图 */
.map {
  width: 100%;
  height: 300px;
}
/* 修改红黄牌出的样式 */
.bg-purple,
.bg-purple-light {
  background: none;
}
.listFile {
  margin-right: 5px !important;
  margin-left: 5px !important;
}
</style>