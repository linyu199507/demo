<template>
  <!-- 投诉举报情况 -->
  <div>
    <!-- 数据对比 -->
    <el-card>
      <div class="businessVolume mt20">
        <el-row :gutter="20">
          <!-- 左边 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
            <div class="grid-content bg-purple doublePie">
              <el-card class="box-card dealCart">
                <div class="settlementRateTitle mapTitle">
                  <p>业务类型分布</p>
                </div>
                  <el-date-picker
                          v-model="zoomData"
                          type="daterange"
                          align="center"
                          placeholder="选择日期范围"
                          :picker-options="pickerOptions2"
                          @change="getPickData"
                          v-loading="loadingBusinesstypetime"
                  ></el-date-picker>
                  <el-button type="primary" @click="getBusinessclass()">查询</el-button>
                    <div
                      class="amountRecovered"
                      id="amountRecovered"
                      v-loading="loadingAmountReacovered"
                    ></div>

                  <!--</el-tab-pane>-->
                <!--</el-tabs>-->
              </el-card>
            </div>
          </el-col>

          <!-- 右边 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
            <div class="theThird" v-loading="lodingOfHotSpotChange" element-loading-text="拼命加载中">
              <el-card class="box-card histogramCard">
                <div class="settlementRateTitle mapTitle">
                  <p>业务途经分布</p>
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
          <div class="businessVolume mt20">
          <el-row :gutter="20">
              <!-- 左边 -->
              <el-col :xs="24" :sm="24" :md="16" :lg="24">
                  <div class="grid-content bg-purple doublePie">
                      <el-card class="box-card dealCart">
                          <div class="settlementRateTitle mapTitle">
                              <p>全区维权办结情况及分布</p>
                          </div>
                          <el-date-picker
                                  v-model="finishData"
                                  type="daterange"
                                  align="center"
                                  placeholder="选择日期范围"
                                  :picker-options="pickerOptions2"
                                  @change="changeFinish"
                          ></el-date-picker>
                          <el-button type="primary" @click="getfinishData()">查询</el-button>
                          <el-tabs v-model="activeRate" @tab-click="handleClick">
                              <el-tab-pane
                                      label="办结总数"
                                      name="settlementRate"
                                      element-loading-text="拼命加载中"
                              >
                                  <div
                                          class="mediationRate"
                                          id="settlementRate"
                                          v-if="activeRate==='settlementRate'"
                                          v-loading="loadingOfsettlementRate"
                                  ></div>
                              </el-tab-pane>
                              <el-tab-pane
                                      label="办结率"
                                      name="mediationRate"
                                      element-loading-text="拼命加载中"
                              >
                                  <!--<el-date-picker-->
                                          <!--v-model="finishData"-->
                                          <!--type="daterange"-->
                                          <!--align="center"-->
                                          <!--placeholder="选择日期范围"-->
                                          <!--:picker-options="pickerOptions2"-->
                                          <!--@change="changeFinish"-->
                                  <!--&gt;</el-date-picker>-->
                                  <!--<el-button type="primary" @click="getfinishData()">查询</el-button>-->
                                  <div
                                          class="mediationRate"
                                          id="mediationRate"
                                          v-if="activeRate==='mediationRate'"
                                          v-loading="loadingOfmediationRate"
                                  ></div>
                              </el-tab-pane>
                          </el-tabs>
                      </el-card>
                  </div>
              </el-col>


          </el-row>
          </div>
      </div>
    </el-card>
  </div>


</template>
<script>
import {
    searchbustype,searchallArea,

} from "../../../../src/api/graphics";

import moment from "moment";
import shundeMapJson from "../../../assets/json/shunde.json";
export default {
  data() {
    return {

      //linyu
      statisticsRegional: null,
      loading: false,
      loadingOfMap: false,
      lodingOfHotSpotChange: false,
      loadingOfsettlementRate: false ,
      loadingOfmediationRate: false,
      loadingAmountReacovered: false,
      loadingBusinesstype:false,
      loadingBusinesstypetime:false,
      listOfDate: {
        listOne: [],
        listTwo: [],
        listThree: [],
        listFour: [],
        listFive: [],
        listSix: []
      },
        startDate:"",
        endDate:"",
        startDatefinish:"",
        endDatefinish:"",


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

      activeRate: "settlementRate",
      zoomData: "",
      finishData:"",
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



      // amountRecovered
      // 各类型业务量
      amountReacoveredOption: {
        color: "#008080",
        // color: "#ff8f1f",
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
          name: "业务总量",
          type: "value",
          min: 0,
          axisLabel: {
            formatter: "{value}(件)",
            show: true
          },
          max: function(value) {
            return value.max + 200;
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


        settlementRateOption: {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['业务总量', '办结总量','同比','同比量']
            },
            grid: {
                left: '3%',
                right: '8%',
                bottom: '8%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    // min: 3000,
                    // max: 0,
                    axisLabel: {
                        formatter: "{value}(件)",
                        show: true
                    },
                },
                {
                    type: 'value',
                    name: '同比',
                    // min: -100,
                    // max: 100,
                    // interval: 20,
                    axisLabel: {
                        formatter: '{value} %'
                    }
                }
            ],
            series: [
                {
                    name: '业务总量',
                    type: 'bar',
                    data: [],
                    label: {
                        show: true,
                        position: 'top'
                    }
                },
                {
                    name: '办结总量',
                    type: 'bar',
                    data: [],
                    label: {
                        show: true,
                        position: 'top'
                    }
                },
                {
                    name: '同比',
                    type: 'line',
                    yAxisIndex: 1,
                    // data: [20, 22, 33, 45, 63, -12, 23, -24, 23, 15, -12, 62],
                    data: [],
                    label: {
                        show: true,
                        position: 'top'
                    }
                },
                {
                    name: '同比量',
                    type: 'line',
                    // yAxisIndex: 1,
                    data: [],
                    // data: [],
                    // label: {
                    //     show: true,
                    //     position: 'top'
                    // }
                },

            ]
        },
        // 办结率统计
        mediationRateOption: {
            color: "#BDB76B",
            // color: "#ff8f1f",
            legend: {
                show: true
            },
            tooltip: {},
            dataset: {
                source: [
                    // { product: "伦教", 办结率: 39 },
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
            series: [{ type: "bar", label: { show: true, position: "top" } }]
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
      },
        compare: function( property, desc ) {
            return function ( a, b ) {
                var value1 = a[ property ];
                var value2 = b[ property ];
                if ( desc == true ) {
                    //排序升序排列
                    return value1 - value2;
                } else {
                    return value2 - value1;
                }
            }
        },
    };
  },
  mounted() {
    // 业务类型工单量
      this.getallArea();
    this.getbustype();
    this.drawsettlement();
    this.drawMediationRate();
    this.putDataList();

    //------------------分割线----------------------

    // this.drawDoublePie();
    // this.drawMap();
  },
  methods: {
    //linyu

    //业务类型工单量
      getbustype() {
          this.amountReacoveredOption.dataset.source=[]
      let params = {
          start:this.startDate,
          end:this.endDate
      };
      console.log(params)
          this.loadingAmountReacovered=true
          searchbustype(params).then(res => {
        console.log(res);
        for (var key in res.data) {
          //金额
          let data = { product: res.data[key].bustype, 业务量: res.data[key].bustotal };
          this.amountReacoveredOption.dataset.source.push(data);
        }
          let dataOne = { product: '行政投诉', 业务量: 0 }
          let dataTwo = { product: '信访', 业务量: 0 }
          this.amountReacoveredOption.dataset.source.push(dataOne);
          this.amountReacoveredOption.dataset.source.push(dataTwo);
              this.loadingAmountReacovered=false
          this.drawAmountReacovered();

      });
    },


        getallArea() {
            this.settlementRateOption.xAxis[0].data = []
            this.settlementRateOption.series[0].data = []
            this.settlementRateOption.series[1].data = []
            this.settlementRateOption.series[2].data = []
            this.settlementRateOption.series[3].data = []
            this.mediationRateOption.dataset.source = []

          console.log("getallArea")
      let params = {
          begindate:this.startDatefinish,
          enddate:this.endDatefinish
      };
            console.log(params)
            this.loadingOfsettlementRate = true
            this.loadingOfmediationRate = true
          searchallArea(params).then(res => {
        console.log(res);
        for (var key in res.data.this) {
            this.settlementRateOption.xAxis[0].data.push(res.data.this[key].region)
            this.settlementRateOption.series[0].data.push(res.data.this[key].bustotal);
            this.settlementRateOption.series[1].data.push(res.data.this[key].endtotal);
            this.settlementRateOption.series[2].data.push(Math.round((res.data.this[key].bustotal-res.data.last[key].bustotal)/res.data.last[key].bustotal));
            // this.settlementRateOption.series[3].data.push(res.data.this[key].bustotal-res.data.last[key].bustotal);
            let data = {
                product: res.data.this[key].region,
                办结率: Math.round((res.data.this[key].endtotal) / (res.data.this[key].bustotal)*100)
            }
            this.mediationRateOption.dataset.source.push(data)

        }
              this.mediationRateOption.dataset.source.sort(this.compare('办结率'))
              this.loadingOfsettlementRate = false;
              this.loadingOfmediationRate = false;
              this.drawsettlement();
              this.drawMediationRate();


      });
    },

    putDataList(value) {
          console.log("2222222")
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


      handleClick(tab, event) {
          if (tab.label === "办结总数") {
              const interval = setInterval(() => {
                  let settlementDom = document.getElementById("settlementRate");
                  if (settlementDom) {
                      clearInterval(interval);
                      this.drawsettlement();

                      // this.loadingOfsettlementRate = false;

                  }
              }, 200);
          } else if (tab.label === "办结率") {
              const interval1 = setInterval(() => {
                  let mediationDom = document.getElementById("mediationRate");
                  if (mediationDom) {
                      this.drawMediationRate();
                      clearInterval(interval1);
                  }
              }, 200);
          }
          // else if (tab.label === "挽回金额统计") {
          //     const interval2 = setInterval(() => {
          //         let amountRecoveredDom = document.getElementById("amountRecovered");
          //         if (amountRecoveredDom) {
          //             this.drawAmountReacovered();
          //             clearInterval(interval2);
          //         }
          //     }, 200);
          // }
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
    drawDoublePie() {
      let drawChartOne = this.$echarts.init(
        document.getElementById("chartOne")
      );
      drawChartOne.setOption(this.option);
      this.loadingOfMap = false;
    },

    // 各类型业务量
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

      // 办结条形图
      drawsettlement() {
          let drawsettlement = this.$echarts.init(
              document.getElementById("settlementRate")
          );
          console.log(drawsettlement)

          drawsettlement.setOption(this.settlementRateOption, true);
           // this.loadingOfsettlementRate = false;
      },
      // 办结率统计
      drawMediationRate() {
          let mediationRate = this.$echarts.init(
              document.getElementById("mediationRate")
          );
          mediationRate.setOption(this.mediationRateOption, true);
          // this.loadingOfmediationRate = false;
      },
      getBusinessclass(){
          if(this.zoomData[0]==null){
              this.startDate = this.zoomData[0]
              this.endDate = this.zoomData[1]
              this.getbustype()
          }else {
              this.startDate = this.$moment(this.zoomData[0]).format("YYYY-MM-DD HH:mm:ss");
              this.endDate = this.$moment(this.zoomData[1]).format("YYYY-MM-DD HH:mm:ss");
              this.getbustype()
          }
      },
      getPickData() {

      },
      getfinishData(){
          //console.log(this.finishData[0])
          if(this.finishData[0]==null){
              this.startDatefinish=this.finishData[0]
              this.endDatefinish=this.finishData[1]
              this.getallArea('change');
              console.log(this.startDatefinish[0])
          }else{
              this.startDatefinish=this.$moment(this.finishData[0]).format("YYYY-MM-DD HH:mm:ss");
              this.endDatefinish=this.$moment(this.finishData[1]).format("YYYY-MM-DD HH:mm:ss");
              this.getallArea('change');
              console.log(this.startDatefinish[0])
          }

      },
      changeFinish(){

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
  height: 600px;
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
  height: 750px;

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