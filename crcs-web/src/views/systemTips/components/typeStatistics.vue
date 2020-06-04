<template>
  <!-- 数据对比 -->
  <el-card>
    <div class="businessVolume mt20">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="16" :lg="14">
          <div
            class="grid-content bg-purple doublePie"
            v-loading="loading"
            element-loading-text="拼命加载中"
          >
            <el-card class="box-card dealCart">
              <div class="settlementRateTitle mapTitle">
                <p>投诉、举报性质统计分析</p>
              </div>
              <div class="zoomDataChart">
                <div id="roda" style="width: 100%;height:600px;"></div>
              </div>
            </el-card>
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :md="8" :lg="10">
          <div class="grid-content bg-purple doublePie">
            <el-card class="box-card dealCart">
              <div class="settlementRateTitle mapTitle">
                <p>投诉热点商家top10</p>
              </div>
              <el-tabs v-model="activeStore" @tab-click="handleClick">
                <el-tab-pane
                  label="投诉热点商家总量"
                  name="allOfStore"
                  v-loading="loadingOfStore"
                  element-loading-text="拼命加载中"
                >
                  <div
                    v-if="activeStore === 'allOfStore'"
                    id="hotBus"
                    style="width: 100%;height:500px;"
                  ></div>
                </el-tab-pane>
                <el-tab-pane label="本月投诉热点商家" name="averageOfStore">
                  <div
                    v-if="activeStore === 'averageOfStore'"
                    id="hotBusOne"
                    style="width: 100%;height:500px;"
                  ></div>
                </el-tab-pane>
              </el-tabs>
              <!-- <div class="zoomDataChart">
                <div id="hotBus" style="width: 100%;height:530px;"></div>
              </div>-->
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="businessVolume mt20">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="24" :md="16" :lg="14">
          <el-card class="box-card dealCart">
            <div class="settlementRateTitle mapTitle">
              <p>投诉举报热点事件Top6</p>
            </div>
            <el-tabs v-model="activeRate" @tab-click="handleClick">
              <el-tab-pane
                label="热点事件总量"
                name="allData"
                v-loading="loadingOfHotIssues"
                element-loading-text="拼命加载中"
              >
                <div
                  v-if="activeRate === 'allData'"
                  id="hotIssues"
                  style="width: 100%;height:600px;"
                ></div>
              </el-tab-pane>
              <el-tab-pane
                label="本月热点事件"
                name="mounthData"
                v-loading="loadingOfHotIssuesOne"
                element-loading-text="拼命加载中"
              >
                <div
                  v-if="activeRate === 'mounthData'"
                  id="hotIssuesOne"
                  style="width: 100%;height:600px;"
                ></div>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>

        <el-col :xs="24" :sm="24" :md="10" :lg="10">
          <div class="grid-content bg-purple doublePie">
            <el-card class="box-card dealCart">
              <div class="settlementRateTitle mapTitle">
                <p>投诉热点行业Top6</p>
              </div>
              <el-tabs v-model="activeHotIndustries" @tab-click="handleClick">
                <el-tab-pane
                  label="投诉热点行业总量"
                  name="allIndustries"
                  v-loading="loadingOfAllIndustries"
                  element-loading-text="拼命加载中"
                >
                  <div
                    v-if="activeHotIndustries === 'allIndustries'"
                    id="hotIndustry"
                    style="width: 100%;height:500px;"
                  ></div>
                </el-tab-pane>
                <el-tab-pane
                  label="本月投诉热点行业"
                  name="mounthIndustries"
                  v-loading="loadingOfAllIndustriesOne"
                  element-loading-text="拼命加载中"
                >
                  <div
                    v-if="activeHotIndustries === 'mounthIndustries'"
                    id="hotIndustryOne"
                    style="width: 100%;height:500px;"
                  ></div>
                </el-tab-pane>
              </el-tabs>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>
<script>
import {
  searchTenBusiness,
  searchComplaintRanking,
  searchHotQuestion,
  searchIllegalBehavior
} from "../../../../src/api/api";

// import moment from "moment";
// import shundeMapJson from "../../../assets/json/shunde.json";
export default {
  data() {
    return {
      loading: true,
      loadingOfStore: true,
      loadingOfAvg: true,
      loadingOfHotIssues: true,
      loadingOfHotIssuesOne: true,
      loadingOfAllIndustries: true,
      loadingOfAllIndustriesOne: true,
      //liny
      dateOfTenBusiness: null,
      activeRate: "allData",
      activeStore: "allOfStore",
      activeHotIndustries: "allIndustries",
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
      //雷达图
      option: {
        // title: {
        //   text: "投诉、举报性质统计分析",
        //   top:'0'
        // },
        tooltip: {},
        legend: {
          left: "5%",
          data: ["总量", "本月"]
        },
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
        radar: {
          name: {
            textStyle: {
              color: "#fff",
              backgroundColor: "#0070e0",
              borderRadius: 3,
              padding: [3, 5]
            }
          },
          center: ["50%", "45%"],
          indicator: []
        },
        grid: {
          left: "10%"
        },
        series: [
          {
            name: "预算 vs 开销",
            type: "radar",
            data: [
              { value: [], name: "总量" },
              {
                value: [],
                name: "本月"
              }
            ]
          }
        ]
      },
      //热点商家
      optionOfHotBus: {
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
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        legend: {
          data: ["投诉总量"]
          // data: ["投诉总量", "本月投诉量"]
        },
        grid: {
          left: "10%",
          right: "10%",
          containLabel: true
        },
        xAxis: {
          type: "value",
          boundaryGap: [0, 0.01],
          name: "商家"
        },
        yAxis: {
          type: "category",
          data: [],
          name: "投诉量",
          axisLabel: {
            width: "200px"
          }
        },
        series: [
          {
            name: "案件总数",
            type: "bar",
            data: [],
            label: {
              show: true,
              position: "right"
            }
          }
          // {
          //   name: "本月热点事件",
          //   type: "bar",
          //   data: []
          // }
        ]
      },
      optionOfHotBusOne: {
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
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        legend: {
          left: "10%",
          data: ["投诉总量"]
          // data: ["投诉总量", "本月投诉量"]
        },
        grid: {
          left: "10%",
          right: "10%",
          containLabel: true
        },
        xAxis: {
          type: "value",
          boundaryGap: [0, 0.01],
          name: "商家"
        },
        yAxis: {
          type: "category",
          data: [],
          name: "投诉量",
          axisLabel: {
            width: "200px"
          }
        },
        series: [
          {
            name: "案件总数",
            type: "bar",
            data: [],
            label: {
              show: true,
              position: "right"
            }
          }
          // {
          //   name: "本月热点事件",
          //   type: "bar",
          //   data: []
          // }
        ]
      },
      //热点行业
      optionOfHotInd: {
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
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "10%",
          right: "10%",
          containLabel: true
        },
        xAxis: [
          {
            type: "category",
            name: "行业",
            nameLocation: "end",
            data: [],
            axisLabel: {
              axiosLabel: 0,
              rotate: "34"
            },
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "投诉量",
            nameLocation: "end"
          }
        ],
        series: [
          {
            name: "总投诉量",
            type: "bar",
            barWidth: "60%",
            data: [],
            label: {
              show: true,
              position: "top"
            }
          }
        ]
      },
      optionOfHotIndOne: {
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
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "10%",
          right: "10%",
          containLabel: true
        },
        xAxis: [
          {
            type: "category",
            name: "行业",
            nameLocation: "end",
            data: [],
            axisLabel: {
              axiosLabel: 0,
              rotate: "34"
            },
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "投诉量",
            nameLocation: "end"
          }
        ],
        series: [
          {
            name: "总投诉量",
            type: "bar",
            barWidth: "60%",
            data: [],
            label: {
              show: true,
              position: "top"
            }
          }
        ]
      },
      //热点事件
      optionOfHotIssues: {
        legend: {
          show: true
        },
        tooltip: {},
        color: [
          "#0070E0",
          "#5CADFF",
          "#99FFFF",
          "#99FFCC",
          "#FFCC99",
          "#FFAD5C",
          "#FF8F1F",
          "#CCFF99",
          "#FFFF99",
          "#ffffcc"
        ],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: "center",
          top: "bottom",
          data: [
            "rose1",
            "rose2",
            "rose3",
            "rose4",
            "rose5",
            "rose6",
            "rose7",
            "rose8"
          ]
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ["pie", "funnel"]
            },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: "面积模式",
            type: "pie",
            radius: [35, 130],
            center: ["50%", "50%"],
            roseType: "area",
            data: [
              // { value: 10, name: "rose1" },
            ],
            labelLine: {
              lineStyle: {
                color: "#0070E0"
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            },
            label: {
              color: "#fff",
              backgroundColor: "#0070E0",
              padding: 5,
              borderRadius: 5
            }
          }
        ]
      },
      optionOfHotIssuesAgain: {
        legend: {
          show: true
        },
        tooltip: {},
        color: [
          "#0070E0",
          "#5CADFF",
          "#99FFFF",
          "#99FFCC",
          "#FFCC99",
          "#FFAD5C",
          "#FF8F1F",
          "#CCFF99",
          "#FFFF99",
          "#ffffcc"
        ],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: "center",
          top: "bottom",
          data: [
            "rose1",
            "rose2",
            "rose3",
            "rose4",
            "rose5",
            "rose6",
            "rose7",
            "rose8"
          ]
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ["pie", "funnel"]
            },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: "面积模式",
            type: "pie",
            radius: [35, 130],
            center: ["50%", "50%"],
            roseType: "area",
            data: [
              // { value: 10, name: "rose1" },
            ],
            labelLine: {
              lineStyle: {
                color: "#0070E0"
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            },
            label: {
              color: "#fff",
              backgroundColor: "#0070E0",
              padding: 5,
              borderRadius: 5
            }
          }
        ]
      }
    };
  },
  mounted() {
    this.getIllegalBehavior();
    this.getHotQuestion();
    this.getComplaintRanking();
    this.getComplaintsArea();

    //------------------分割线----------------------
  },
  methods: {
    //linyu

    //热点事件
    getIllegalBehavior() {
      let params = {};
      searchIllegalBehavior(params).then(res => {
        for (var i in res.data.all) {
          this.option.series[0].data[0].value.push(res.data.all[i].regiontotal);
          this.option.radar.indicator.push({
            name: res.data.all[i].region,
            max: function(value) {
              return value.max;
            }
          });
        }
        for (var i in res.data.month) {
          this.option.series[0].data[1].value.push(
            res.data.month[i].regiontotal
          );
        }
        this.getRoda();
        this.getRoda = false;
      });
    },

    //热点事件
    getHotQuestion() {
      let params = {};
      searchHotQuestion(params).then(res => {
        for (var i in res.data.all) {
          console.log(res);

          this.optionOfHotIssues.series[0].data.push({
            value: res.data.all[i].regiontotal,
            name: res.data.all[i].region
          });
        }
        for (var i in res.data.month) {
          this.optionOfHotIssuesAgain.series[0].data.push({
            value: res.data.month[i].regiontotal,
            name: res.data.month[i].region
          });
        }
        this.getHotIssues();
      });
    },

    //投诉热点行业
    getComplaintRanking() {
      let params = {};
      searchComplaintRanking(params).then(res => {
        console.log(res);
        for (var i in res.data.all) {
          this.optionOfHotInd.xAxis[0].data.push(res.data.all[i].region);
          this.optionOfHotInd.series[0].data.push(res.data.all[i].regiontotal);
        }
        this.optionOfHotInd.xAxis[0].data.reverse();
        this.optionOfHotInd.series[0].data.reverse();

        for (var i in res.data.month) {
          this.optionOfHotIndOne.xAxis[0].data.push(res.data.month[i].region);
          this.optionOfHotIndOne.series[0].data.push(
            res.data.month[i].regiontotal
          );
        }
        this.optionOfHotIndOne.xAxis[0].data.reverse();
        this.optionOfHotIndOne.series[0].data.reverse();
        this.getHotIndustry();
        this.getHotIndustryOne();
      });
    },

    //投诉热点商家
    getComplaintsArea() {
      let params = {};
      searchTenBusiness(params).then(res => {
        this.dateOfTenBusiness = res.data;
        console.log(res);
        for (var i in res.data.all) {
          this.optionOfHotBus.yAxis.data.push(res.data.all[i].region);
          this.optionOfHotBus.series[0].data.push(res.data.all[i].regiontotal);
        }
        this.optionOfHotBus.yAxis.data.reverse();
        this.optionOfHotBus.series[0].data.reverse();

        for (var i in res.data.month) {
          this.optionOfHotBusOne.yAxis.data.push(res.data.month[i].region);
          this.optionOfHotBusOne.series[0].data.push(
            res.data.month[i].regiontotal
          );
        }
        this.optionOfHotBusOne.yAxis.data.reverse();
        this.optionOfHotBusOne.series[0].data.reverse();
        this.getHotBus();
        this.getHotBusOne();
      });
    },

    getHotIssuesOne() {
      let myChart = this.$echarts.init(document.getElementById("hotIssuesOne"));
      myChart.setOption(this.optionOfHotIssuesAgain);
      this.loadingOfHotIssuesOne = false;
    },

    getHotIssues() {
      let myChart = this.$echarts.init(document.getElementById("hotIssues"));
      myChart.setOption(this.optionOfHotIssues);
      this.loadingOfHotIssues = false;
    },

    getHotIndustry() {
      let myChart = this.$echarts.init(document.getElementById("hotIndustry"));
      myChart.setOption(this.optionOfHotInd);
      this.loadingOfAllIndustries = false;
    },
    getHotIndustryOne() {
      let hotIndustryCharts = this.$echarts.init(
        document.getElementById("hotIndustryOne")
      );
      hotIndustryCharts.setOption(this.optionOfHotIndOne);
      this.loadingOfAllIndustriesOne = false;
    },
    getHotBus() {
      let myChart = this.$echarts.init(document.getElementById("hotBus"));
      myChart.setOption(this.optionOfHotBus);
      this.loadingOfStore = false;
    },

    getHotBusOne() {
      let myChart = this.$echarts.init(document.getElementById("hotBusOne"));
      myChart.setOption(this.optionOfHotBusOne);
      this.loadingOfAvg = false;
    },

    getRoda() {
      let myChart = this.$echarts.init(document.getElementById("roda"));
      myChart.setOption(this.option);
      this.loading = false;
    },
    //---------------------------分割线----------------------------------

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
    handleClick(tab, event) {
      if (tab.label === "热点事件总量") {
        const interval = setInterval(() => {
          let hotIssues = this.$echarts.init(
            document.getElementById("hotIssues")
          );

          if (hotIssues) {
            this.getHotIssues();
            clearInterval(interval);
          }
        }, 200);
      } else if (tab.label === "本月热点事件") {
        const interval1 = setInterval(() => {
          let hotIssuesOneDom = this.$echarts.init(
            document.getElementById("hotIssuesOne")
          );

          if (hotIssuesOneDom) {
            this.getHotIssuesOne();
            clearInterval(interval1);
          }
        }, 200);
      } else if (tab.label === "投诉热点商家总量") {
        const intervalAllHotStore = setInterval(() => {
          let hotBusDom = this.$echarts.init(document.getElementById("hotBus"));

          if (hotBusDom) {
            this.getHotBus();
            clearInterval(intervalAllHotStore);
          }
        }, 200);
      } else if (tab.label === "本月投诉热点商家") {
        const intervalAvgHotStore = setInterval(() => {
          let hotBusDom = this.$echarts.init(
            document.getElementById("hotBusOne")
          );

          if (hotBusDom) {
            this.getHotBusOne();
            clearInterval(intervalAvgHotStore);
          }
        }, 200);
      } else if (tab.label === "投诉热点行业总量") {
        const intervalAvgHotStore = setInterval(() => {
          let hotIndustryDom = this.$echarts.init(
            document.getElementById("hotIndustry")
          );

          if (hotIndustryDom) {
            this.getHotIndustry();
            clearInterval(intervalAvgHotStore);
          }
        }, 200);
      } else if (tab.label === "本月投诉热点行业") {
        const intervalAvgHotStoreOne = setInterval(() => {
          let hotIndustryOneDom = this.$echarts.init(
            document.getElementById("hotIndustryOne")
          );
          if (hotIndustryOneDom) {
            this.getHotIndustryOne();
            clearInterval(intervalAvgHotStoreOne);
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
.colorRed {
  color: #ff4949;
}
.colorGreen {
  color: #1aad19;
}

.el-card {
  padding: 0 !important;
  border: none;
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
</style>