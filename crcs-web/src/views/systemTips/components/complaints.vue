<template>
  <!-- 投诉举报情况 -->
  <div>
    <!-- 数据对比 -->
    <el-card>
      <div class="dataUpDown">
        <div class="header">据顺德区消费维权智能服务平台受理数据分析 -- 本月情况</div>
        <div class="upDown">
          <el-row :gutter="20">
            <el-col :xs="12" :sm="12" :md="4" class="layout">
              <el-card class="box-card" style="height:150px">
                <div class="title">
                  <span class="iconfont el-icon-erpdianhua font20 pr5"></span> 系统案件总数
                </div>
                <div class="content">
                  <!-- 增加了 -->
                  <div v-if="dataOfOne.regiontotal1.substr(0,1)!='-'">
                    <span class="colorgray">{{dataOfOne.regiontotal}}</span>
                    <span style="color:#000">同比</span>
                    <span class="iconfont el-icon-erpshangsheng colorRed"></span>
                    <span class="fontStyle colorRed">{{dataOfOne.regiontotal1}}</span>
                  </div>
                  <!-- 减少了 -->
                  <div class="fontStyle" v-if="dataOfOne.regiontotal1.substr(0,1)=='-'">
                    <span class="colorgray">{{dataOfOne.regiontotal}}</span>
                    <span style="color:#000">同比</span>
                    <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="fontStyle colorGreen">{{dataOfOne.regiontotal1}}</span>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :xs="12" :sm="12" :md="4" class="layout">
              <el-card class="box-card" style="height:150px">
                <div class="title">
                  <span class="iconfont el-icon-erpaboutuss_fuzhi font20 pr5"></span>系统受理总数
                </div>
                <div class="content">
                  <!-- 增加了 -->
                  <div v-if="dataOfOne.complaint1.substr(0,1)!='-'">
                    <span class="colorgray">{{dataOfOne.complaint}}</span>
                    <span style="color:#000">同比</span>
                    <span class="fontStyle colorRed">{{dataOfOne.complaint1}}</span>
                  </div>
                  <!-- 减少了 -->
                  <div class="fontStyle" v-if="dataOfOne.complaint1.substr(0,1)=='-'">
                    <span class="colorgray">{{dataOfOne.complaint}}</span>
                    <span style="color:#000">同比</span>
                    <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="fontStyle colorGreen">{{dataOfOne.complaint1}}</span>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :xs="12" :sm="12" :md="4" class="layout">
              <el-card class="box-card" style="height:150px">
                <div class="title">
                  <span class="iconfont el-icon-erpaboutuss_fuzhi font20 pr5"></span>系统办结总数
                </div>
                <div class="content">
                  <!-- 增加了 -->
                  <div v-if="dataOfOne.commom1.substr(0,1)!='-'">
                    <span class="colorgray">{{dataOfOne.commom}}</span>
                    <span style="color:#000">同比</span>
                    <span class="fontStyle colorRed">{{dataOfOne.commom1}}</span>
                  </div>
                  <!-- 减少了 -->
                  <div class="fontStyle" v-if="dataOfOne.commom1.substr(0,1)=='-'">
                    <span class="colorgray">{{dataOfOne.commom}}</span>
                    <span style="color:#000">同比</span>
                    <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="fontStyle colorGreen">{{dataOfOne.commom1}}</span>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :xs="12" :sm="12" :md="4" class="layout">
              <el-card class="box-card" style="height:150px">
                <div class="title">
                  <span class="iconfont el-icon-erpjushou font20 pr5"></span>诉转案总数
                </div>
                <div class="content">
                  <!-- 减少了 -->
                  <div v-if="dataOfOne.applealtocase1.substr(0,1)!='-'">
                    <span class="colorgray">{{dataOfOne.applealtocase}}</span>
                    <span style="color:#000">同比</span>
                    <span class="iconfont el-icon-erpxiajiang colorGreen"></span>
                    <span class="fontStyle colorRed">{{dataOfOne.applealtocase1}}</span>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :xs="12" :sm="12" :md="4" class="layout">
              <el-card class="box-card" style="height:150px">
                <div class="title">
                  <span class="iconfont el-icon-erpshoushi-zhua- font20 pr5"></span>举报立案总数
                </div>
                <div class="content">
                  <!-- 减少了 -->
                  <div>
                    <span class="colorgray">{{dataOfOne.economy}}</span>
                    <span style="color:#000">立案</span>
                    <!--<span class="fontStyle colorRed">{{dataOfOne.economy1}}</span>-->
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
      <!-- 各地区各业务量统计 -->
      <div class="businessVolume mt20">
        <el-row :gutter="20">
          <!-- 左边 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="16">
            <div
              class="grid-content bg-purple doublePie"
              v-loading="loadingOfMap"
              element-loading-text="拼命加载中"
            >
              <el-card class="box-card dealCart">
                <!--<div class="zoomDataChart">-->
                <!--<el-date-picker-->
                <!--v-model="zoomData"-->
                <!--type="daterange"-->
                <!--align="right"-->
                <!--placeholder="选择日期范围"-->
                <!--:picker-options="pickerOptions2"-->
                <!--&gt;</el-date-picker>-->
                <!--<el-button type="primary" @click="getDoublepieOption()">查询</el-button>-->
                <!--</div>-->
                <div id="chartOne" class="pie"></div>
              </el-card>
            </div>
          </el-col>

          <!-- 右边 -->
          <el-col :xs="24" :sm="24" :md="8" :lg="8">
            <div class="theThird" v-loading="loading" element-loading-text="拼命加载中">
              <el-card class="box-card histogramCard">
                <div class="settlementRateTitle mapTitle">
                  <p>投诉热点地区</p>
                </div>
                <!--<div class="searchMapDate">-->
                <!--<el-date-picker-->
                <!--v-model="mapDate"-->
                <!--type="daterange"-->
                <!--align="right"-->
                <!--placeholder="选择日期范围"-->
                <!--@change="getMap()"-->
                <!--:picker-options="pickerOptions2"-->
                <!--&gt;</el-date-picker>-->
                <!--<el-button type="primary" @click="getMapDate()">查询</el-button>-->
                <!--</div>-->
                <div class="map" id="map"></div>
                <el-row>
                  <el-col
                    style="text-align:center"
                    :lg="8"
                    :md="12"
                    :xs="12"
                    :sm="12"
                    v-for="(item,index) in statisticsRegional"
                    :key="index"
                  >
                    <span class="titleStyle">{{item.region}}</span>
                    ：{{item.regiontotal}}
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>

      <div class="businessVolume mt20">
        <el-row :gutter="20">
          <!-- 左边 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
            <div class="grid-content bg-purple doublePie">
              <el-card class="box-card dealCart">
                <el-tabs v-model="activeRate" @tab-click="handleClick">
                  <el-tab-pane
                    label="办结率统计"
                    name="settlementRate"
                    v-loading="loadingOfsettlementRate"
                    element-loading-text="拼命加载中"
                  >
                    <div
                      class="settlementRate"
                      id="settlementRate"
                      v-if="activeRate==='settlementRate'"
                    ></div>
                  </el-tab-pane>
                  <el-tab-pane
                    label="调解率统计"
                    name="mediationRate"
                    v-loading="loadingOfmediationRate"
                    element-loading-text="拼命加载中"
                  >
                    <div
                      class="mediationRate"
                      id="mediationRate"
                      v-if="activeRate==='mediationRate'"
                    ></div>
                  </el-tab-pane>
                  <el-tab-pane
                    label="挽回金额统计"
                    name="amountRecovered"
                    v-loading="loadingAmountReacovered"
                    element-loading-text="拼命加载中"
                  >
                    <div
                      class="amountRecovered"
                      id="amountRecovered"
                      v-if="activeRate==='amountRecovered'"
                    ></div>
                  </el-tab-pane>
                </el-tabs>
              </el-card>
            </div>
          </el-col>

          <!-- 右边 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
            <div class="theThird" v-loading="lodingOfHotSpotChange" element-loading-text="拼命加载中">
              <el-card class="box-card histogramCard">
                <div class="settlementRateTitle mapTitle">
                  <p>2019年举报热点变化</p>
                </div>
                <!--<div class="searchMapDate">-->
                <!--<el-date-picker-->
                <!--v-model="mapDate"-->
                <!--type="daterange"-->
                <!--align="right"-->
                <!--placeholder="选择日期范围"-->
                <!--@change="getMap()"-->
                <!--:picker-options="pickerOptions2"-->
                <!--&gt;</el-date-picker>-->
                <!--<el-button type="primary" @click="getMapDate()">查询</el-button>-->
                <!--</div>-->
                <!-- 热点变化Report hotspot changes -->
                <div class="hotspotChanges" id="hotspotChanges"></div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>
<script>
import {
  searchOverallBusiness,
  searchRegionData,
  searchComplaintsArea,
  searchregionComplete,
  searchHotQuestionChange
} from "../../../../src/api/api";

import moment from "moment";
import shundeMapJson from "../../../assets/json/shunde.json";
export default {
  data() {
    return {
      //linyu
      statisticsRegional: null,
      loading: true,
      loadingOfMap: true,
      lodingOfHotSpotChange: true,
      loadingOfsettlementRate: true,
      loadingOfmediationRate: true,
      loadingAmountReacovered: true,
      listOfDate: {
        listOne: [],
        listTwo: [],
        listThree: [],
        listFour: [],
        listFive: [],
        listSix: []
      },
      //小图标+数据
      dataOfOne: {
        regiontotal: "", //案件总数
        complaint: "", //受理总数
        economy: "", //立案总数
        commom: "", //办结总数
        applealtocase: "", //诉转案同比
        regiontotal1: "", //案件总数同比
        complaint1: "", //受理总数同比
        economy1: "", //立案总数同比
        commom1: "", //办结总数同比
        applealtocase1: "" //诉转案总数同比
      },

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
      // 双层嵌套的饼形
      option: {
        title: {
          left: "left",
          text: "各地区的业务量比较",
          textStyle: {
            // color: "#333"
            color: "rgb(4, 7, 12)",
            fontSize: "20"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          y: "20%",
          data: ["咨询登记", "消费投诉", "经济违法"]
        },
        series: [
          {
            name: "地域",
            type: "pie",
            selectedMode: "single",
            color: ["#9FE6B8", "#FFDB5C", "#ff9f7f"],
            radius: [0, "30%"],
            center: ["50%", "50%"],
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
            center: ["50%", "50%"],
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
              { value: 335, name: "大良" },
              { value: 310, name: "容桂" },
              { value: 234, name: "伦教" },
              { value: 135, name: "勒流" },
              { value: 1048, name: "陈村" },
              { value: 251, name: "均安" },
              { value: 147, name: "杏坛" },
              { value: 102, name: "龙江" },
              { value: 102, name: "乐从" },
              { value: 102, name: "北滘" }
            ]
          }
        ]
      },
      activeRate: "settlementRate",
      zoomData: "",
      mapDate: "",
      doublepieOption: {
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: 10,
          data: [
            "直达",
            "营销广告",
            "搜索引擎",
            "邮件营销",
            "联盟广告",
            "视频广告",
            "百度",
            "谷歌",
            "必应",
            "其他"
          ]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            selectedMode: "single",
            radius: [0, "30%"],

            label: {
              position: "inner"
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 335, name: "直达", selected: true },
              { value: 679, name: "营销广告" },
              { value: 1548, name: "搜索引擎" }
            ]
          },
          {
            name: "访问来源",
            type: "pie",
            radius: ["40%", "55%"],
            label: {
              formatter: "{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",
              backgroundColor: "#eee",
              borderColor: "#aaa",
              borderWidth: 1,
              borderRadius: 4,
              // shadowBlur:3,
              // shadowOffsetX: 2,
              // shadowOffsetY: 2,
              // shadowColor: '#999',
              // padding: [0, 7],
              rich: {
                a: {
                  color: "#999",
                  lineHeight: 22,
                  align: "center"
                },
                // abg: {
                //     backgroundColor: '#333',
                //     width: '100%',
                //     align: 'right',
                //     height: 22,
                //     borderRadius: [4, 4, 0, 0]
                // },
                hr: {
                  borderColor: "#aaa",
                  width: "100%",
                  borderWidth: 0.5,
                  height: 0
                },
                b: {
                  fontSize: 16,
                  lineHeight: 33
                },
                per: {
                  color: "#eee",
                  backgroundColor: "#334455",
                  padding: [2, 4],
                  borderRadius: 2
                }
              }
            },
            data: [
              { value: 335, name: "直达" },
              { value: 310, name: "邮件营销" },
              { value: 234, name: "联盟广告" },
              { value: 135, name: "视频广告" },
              { value: 1048, name: "百度" },
              { value: 251, name: "谷歌" },
              { value: 147, name: "必应" },
              { value: 102, name: "其他" }
            ]
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
          min: 10,
          max: 2000,

          top: "10%",
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
            top: "15%",
            aspectScale: 0.9, //地图长度比
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
            data: [] //本周投诉热点地区--数据绑定位置
          }
        ]
      },
      // 办结率
      settlementRateOption: {
        color: "#ff8f1f",
        legend: {
          show: true
        },
        tooltip: {},
        dataset: {
          source: [
            // { product: "伦教", 办结率: 0.39 },
            // { product: "乐从", 办结率: 40 },
            // { product: "龙江", 办结率: 10 },
            // { product: "陈村", 办结率: 69 },
            // { product: "杏坛", 办结率: 99 },
            // { product: "容桂", 办结率: 48 },
            // { product: "大良", 办结率: 28 },
            // { product: "勒流", 办结率: 60 },
            // { product: "均按", 办结率: 28 },
            // { product: "北滘", 办结率: 44 }
          ]
        },
        xAxis: { type: "category" },
        yAxis: {
          name: "办结率",
          type: "value",
          nameLocation: "end",
          min: 0,
          max: 100,
          splitNumber: "10",
          axisLabel: {
            formatter: "{value}(%)",
            show: true
          }
        },
        series: [
          {
            type: "bar",
            label: {
              show: true,
              position: "top"
            }
          }
        ]
      },
      // 调解率统计
      mediationRateOption: {
        color: "#ff8f1f",
        legend: {
          show: true
        },
        tooltip: {},
        dataset: {
          source: [
            // { product: "伦教", 调解率: 39 },
            // { product: "乐从", 调解率: 40 },
            // { product: "龙江", 调解率: 10 },
            // { product: "陈村", 调解率: 69 },
            // { product: "杏坛", 调解率: 99 },
            // { product: "容桂", 调解率: 48 },
            // { product: "大良", 调解率: 28 },
            // { product: "勒流", 调解率: 60 },
            // { product: "均按", 调解率: 28 },
            // { product: "北滘", 调解率: 44 }
          ]
        },
        xAxis: { type: "category" },
        yAxis: {
          name: "调解率",
          type: "value",
          nameLocation: "end",
          min: 0,
          max: 100,
          splitNumber: "10",
          axisLabel: {
            formatter: "{value}(%)",
            show: true
          }
        },
        series: [{ type: "bar", label: { show: true, position: "top" } }]
      },
      // amountRecovered
      // 挽回金额统计
      amountReacoveredOption: {
        color: "#ff8f1f",
        legend: {
          show: true
        },
        tooltip: {},
        dataset: {
          source: []
        },
        grid: {
          left: "20%"
        },

        xAxis: { type: "category" },
        yAxis: {
          name: "挽回金额",
          type: "value",
          min: 0,
          axisLabel: {
            formatter: "{value}(万元)",
            show: true
          },
          max: function(value) {
            return value.max + 20;
          }
        },
        series: [
          {
            type: "bar",
            label: {
              show: true,
              position: "top"
            }
          }
        ]
      },
      // 投诉热点折线图
      hotSpotChangeOption: {
        title: {
          text: ""
        },
        tooltip: {
          trigger: "axis"
        },
        legend: {
          data: []
        },
        grid: {
          left: "10%",
          right: "4%",
          bottom: "0%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: "value"
        },
        series: []
      }
    };
  },
  mounted() {
    // 热点投诉
      console.log(123)
    this.getRegionComplete();
    this.drawHotSpotChanges(); //热点变化图
    this.getComplaintsArea();
    this.getRegionData();
    this.getOverallBusiness();
    this.getHotQuestionChange();

    // // clearTimeout(timer)
    // // var timer = setTimeout(() => {
    this.drawsettlement();
    // this.drawAmountReacovered();
    this.drawMediationRate();

    // }, 2000);

    //------------------分割线----------------------

    // this.drawDoublePie();
    // this.drawMap();
  },
  methods: {
    //linyu
    //热点变化
    getHotQuestionChange() {
      let params = {};
      searchHotQuestionChange(params).then(res => {
        console.log(res);
        for (var key in res.data) {
          if (key >= 0 && key <= 5) {
            this.listOfDate.listOne.push(res.data[key]);
          }
          if (key >= 6 && key <= 11) {
            this.listOfDate.listTwo.push(res.data[key]);
          }
          if (key >= 12 && key <= 17) {
            this.listOfDate.listThree.push(res.data[key]);
          }
          if (key >= 18 && key <= 23) {
            this.listOfDate.listFour.push(res.data[key]);
          }
          if (key >= 24 && key <= 29) {
            this.listOfDate.listFive.push(res.data[key]);
          }
          if (key >= 30 && key <= 35) {
            this.listOfDate.listSix.push(res.data[key]);
          }
        }

        this.hotSpotChangeOption.legend.data.push(
          this.listOfDate.listOne[0].region,
          this.listOfDate.listTwo[0].region,
          this.listOfDate.listThree[0].region,
          this.listOfDate.listFour[0].region,
          this.listOfDate.listFive[0].region,
          this.listOfDate.listSix[0].region
        );

        this.hotSpotChangeOption.xAxis.data.push(
          this.listOfDate.listOne[0].monthstart,
          this.listOfDate.listOne[1].monthstart,
          this.listOfDate.listOne[2].monthstart,
          this.listOfDate.listOne[3].monthstart,
          this.listOfDate.listOne[4].monthstart,
          this.listOfDate.listOne[5].monthstart
        );

        this.putDataList(this.listOfDate);
        this.drawHotSpotChanges();
        this.lodingOfHotSpotChange = false;
      });
    },

    //地区办结、调解、金额
    getRegionComplete() {
      let params = {};
      searchregionComplete(params).then(res => {
        console.log(res);
        for (var key in res.data) {
          //金额
          let data = {
            product: res.data[key].region,
            挽回金额:
              res.data[key].commom > 0
                ? res.data[key].commom / 10000
                : res.data[key].commom
          };
          this.amountReacoveredOption.dataset.source.push(data);
          //办结率
          let dataOne = {
            product: res.data[key].region,
            办结率: Number(res.data[key].transferred)
          };
          this.settlementRateOption.dataset.source.push(dataOne);
          //调解率
          let dataTwo = {
            product: res.data[key].region,
            调解率: Number(res.data[key].mediation)
          };
          this.mediationRateOption.dataset.source.push(dataTwo);
        }
        console.log(this.settlementRateOption.dataset.source);
        console.log(this.mediationRateOption.dataset.source);
        this.drawsettlement();
        this.loadingOfsettlementRate = false;
        this.drawMediationRate();
        this.drawAmountReacovered();
      });
    },

    //投诉热点地区(地图)
    getComplaintsArea() {
      let params = {};
      searchComplaintsArea(params).then(res => {
        this.statisticsRegional = res.data.all;
        for (var key in res.data.all) {
          let data = {
            name: res.data.all[key].region,
            value: res.data.all[key].regiontotal
          };
          this.shundeMapOption.series[0].data.push(data);
        }
        this.drawMap();
        this.loading = false;
      });
    },

    //双层饼图
    getRegionData() {
      let params = {};
      searchRegionData(params).then(res => {
        this.regionTotalOne = [];
        this.regionTotalTwo = [];
        let data = {};
        // console.log(this.regionTotalOne)
        for (var key in res.data) {
          if (key == "咨询") {
            data = { value: res.data[key], name: key, selected: true };
            this.regionTotalTwo.push(data);
          } else if (key == "投诉" || key == "举报") {
            data = { value: res.data[key], name: key };
            this.regionTotalTwo.push(data);
          } else if (key == "区局") {
          } else {
            data = { value: res.data[key], name: key };
            this.regionTotalOne.push(data);
          }
        }
        this.option.series[1].data = this.regionTotalOne;
        this.option.series[0].data = this.regionTotalTwo;
        this.drawDoublePie();
      });
    },

    getOverallBusiness() {
      let params = {};
      searchOverallBusiness(params).then(res => {
        // this.dataOfOne = res.data.dataList
        this.dataOfOne.regiontotal = res.data.dataList[0].regiontotal; //案件总数
        this.dataOfOne.complaint = res.data.dataList[0].complaint; //受理总数
        this.dataOfOne.economy = res.data.dataList[0].economy; //立案总数
        this.dataOfOne.commom = res.data.dataList[0].commom; //办结总数
        this.dataOfOne.applealtocase = res.data.dataList[0].applealtocase;
        this.dataOfOne.regiontotal1 = res.data.regiontotal; //案件总数同比
        this.dataOfOne.complaint1 = res.data.complaint; //受理总数同比
        this.dataOfOne.economy1 = res.data.economy; //立案总数同比
        this.dataOfOne.commom1 = res.data.commom; //办结总数同比
        this.dataOfOne.applealtocase1 = res.data.applealtocase;
      });
    },

    putDataList(value) {
      for (var key in value) {
        let dateOfLists1 = {
          name: value[key][0].region,
          type: "line",
          stack: "总量",
          data: []
        };
        for (var number in value[key]) {
          dateOfLists1.data.push(value[key][number].regiontotal);
        }
        this.hotSpotChangeOption.series.push(dateOfLists1);
      }
      console.log(this.hotSpotChangeOption.series);
    },

    //---------------------------分割线----------------------------------

    // 点击获取饼形图数据的事件
    getDoublepieOption() {
      let firstDate = this.crtTimeFtt(this.searchTopTenData[0]);
      let secondDate = this.crtTimeFtt(this.searchTopTenData[1]);

      let params = {
        begindate: firstDate,
        enddate: secondDate
      };
    },
    // 格式化日期
    getMap() {
      this.getHotZoom();
    },
    getHotZoom() {
      if (this.mapDate[0] == null && this.mapDate[1]) {
        var myDate = new Date();
        var year = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
        var month = myDate.getMonth() + 1; //获取当前月份(0-11,0代表1月)
        var day = myDate.getDate(); //获取当前日(1-31)

        var currentDate = year + "-" + month + "-" + day;
        // console.log(this.getWeekDay(currentDate));
        var begin = this.getWeekDay(currentDate)[0];
        var end = this.getWeekDay(currentDate)[
          this.getWeekDay(currentDate).length - 1
        ];
        this.getHotZoom1(begin, end);
      } else {
        let firstDate = moment(this.mapDate[0]).format("YYYY-MM-DD");
        let endtDate = moment(this.mapDate[1]).format("YYYY-MM-DD");
        this.getHotZoom1(firstDate, endtDate);
      }
    },
    drawMap() {
      let shundeMap = this.$echarts.init(document.getElementById("map"));

      this.$echarts.registerMap("shunde", shundeMapJson, {});

      shundeMap.setOption(this.shundeMapOption);
      this.loading = false;
    },
    getHotZoom1(begin, end) {
      // console.log(begin, end);
      let params = {
        begindate: begin,
        enddate: end
      };
    },
    crtTimeFtt(val) {
      if (val != null) {
        var date = new Date(val);
        var day = date.getDate() > 9 ? +date.getDate : "0" + date.getDate;
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + day;
      }
    },
    drawDoublePie() {
      let drawChartOne = this.$echarts.init(
        document.getElementById("chartOne")
      );
      drawChartOne.setOption(this.option);
      this.loadingOfMap = false;
    },
    // 办结率条形图
    drawsettlement() {
      let drawsettlement = this.$echarts.init(
        document.getElementById("settlementRate")
      );
      drawsettlement.setOption(this.settlementRateOption, true);
      // this.loadingOfsettlementRate = false;
    },
    // 调解率统计
    drawMediationRate() {
      let mediationRate = this.$echarts.init(
        document.getElementById("mediationRate")
      );
      mediationRate.setOption(this.mediationRateOption, true);
      this.loadingOfmediationRate = false;
    },
    // 挽回金额统计
    drawAmountReacovered() {
      let amountRecovered = this.$echarts.init(
        document.getElementById("amountRecovered")
      );
      amountRecovered.setOption(this.amountReacoveredOption, true);
      this.loadingAmountReacovered = false;
    },

    // 热点变化
    drawHotSpotChanges() {
      let hotSpotChanges = this.$echarts.init(
        document.getElementById("hotspotChanges")
      );
      hotSpotChanges.setOption(this.hotSpotChangeOption);
      // this.lodingOfHotSpotChange = false;
      // console.log(this.lodingOfHotSpotChange);
    },
    handleClick(tab, event) {
      if (tab.label === "办结率统计") {
        const interval = setInterval(() => {
          let settlementDom = document.getElementById("settlementRate");
          if (settlementDom) {
            this.drawsettlement();
            this.loadingOfsettlementRate = false;
            clearInterval(interval);
          }
        }, 200);
      } else if (tab.label === "调解率统计") {
        const interval1 = setInterval(() => {
          let mediationDom = document.getElementById("mediationRate");
          if (mediationDom) {
            this.drawMediationRate();
            clearInterval(interval1);
          }
        }, 200);
      } else if (tab.label === "挽回金额统计") {
        const interval2 = setInterval(() => {
          let amountRecoveredDom = document.getElementById("amountRecovered");
          if (amountRecoveredDom) {
            this.drawAmountReacovered();
            clearInterval(interval2);
          }
        }, 200);
      }
    }
  }
};
</script>
<style lang="scss" scoped>
@mixin color {
  color: rgb(4, 7, 12);
}
.layout {
  width: 20%;
}
.colorRed {
  color: #ff4949;
}
.colorGreen {
  color: #1aad19;
}
.colorgray {
  color: #c7c7c7;
  font-size: 20px;
}
.font20 {
  font-size: 20px;
}
.pr5 {
  padding-right: 5px;
}
.el-card {
  padding: 0 !important;
  border: none;
}

.title {
  font-size: 16px;
  margin-bottom: 10px;
}
.fontStyle {
  font-size: 26px;
}
.settlementRate,
.amountRecovered {
  width: 100%;
  height: 500px;
}
.hotspotChanges {
  width: 100%;
  height: 450px;
}
.mediationRate {
  width: 100%;
  height: 500px;
}
.dataUpDown {
  width: 100%;
  .header {
    border-bottom: 1px solid #c7c7c7;
    padding-bottom: 10px;
    font-size: 16px;
    margin-bottom: 10px;
  }
}

.searchMapDate,
.searchHotDate {
  text-align: center;
  margin-top: 20px;
}
/* 地图 */
.map {
  width: 100%;
  height: 300px;
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
.dealCart {
  position: relative;
  height: 600px;

  .el-tabs__item {
    font-size: 16px;
  }

  .el-card__body {
    height: 100%;
  }
}
.zoomDataChart {
  position: absolute;
  top: 60px;
  left: 0;
  height: 40px;
  width: 100%;
  z-index: 2;
  text-align: center;
}
.mb20 {
  margin-bottom: 20px;
}
.mt20 {
  margin-top: 20px;
}
.histogram,
.histogramCard {
  width: 100%;
  height: 600px;
}
.histogramCardOne {
  position: relative;
  height: 600px;
}
.zoomDataChart {
  position: absolute;
  top: 60px;
  left: 0;
  height: 40px;
  width: 100%;
  z-index: 2;
  text-align: center;
}

.settlementRateTitle {
  @include color();
  font-weight: 600;

  & > p {
    text-align: left;
    font-size: 20px;
    margin-top: -3px;
    padding: 0;
  }
}
.rate {
  height: 240px;
}
.titleStyle {
  font-size: 16px;
}
</style>