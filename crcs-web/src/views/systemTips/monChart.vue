<!--功能正在开发提示页面-->
<template>
  <div class="row">
    <!-- 各分局业务总量的统计及各类业务的业务量统计 -->
    <el-row>


        <!-- 左边 -->
        <el-col :xs="24" :sm="24" :md="16" :lg="16">
          <div class="grid-content bg-purple doublePie">
            <el-card class="box-card dealCart">
              <div class="zoomDataChart">
                <el-date-picker
                        v-model="zoomData"
                        type="daterange"
                        align="right"
                        placeholder="选择日期范围"
                        :picker-options="pickerOptions2"
                ></el-date-picker>
                <el-button type="primary" @click="getDoublepieOption()">查询</el-button>
              </div>
              <div id="chartOne" class="pie"></div>
            </el-card>
            <!-- <div class="tips">
              本月新增(减少)业务120单,其中咨询登记业务增加(减少)
              <span>10</span>单,消费投诉增加(减少)
              <span>90</span>单,经济违法增加
              <span>20</span>单;
            </div>-->
          </div>
        </el-col>


      <el-col :span="16" style="position:relative">
        <div class="dataPicker">
          <el-date-picker
            v-model="substationDate"
            type="daterange"
            align="right"
            placeholder="选择日期范围"
            @change="changesubstationDate()"
            :picker-options="pickerOptions2"
          ></el-date-picker>
          <el-button type="primary" class="inquireAbout" @click="getPliceTotal()">查询</el-button>
        </div>
        <div class="grid-content bg-purple">
          <div id="main" class="radius"></div>
        </div>
      </el-col>
      <!-- 各类业务的对比 -->
      <el-col :span="8">
        <div class="grid-content bg-purple" style="position:relative">
          <div class="dataPicker">
            <el-date-picker
              v-model="zoomLocationDate"
              type="daterange"
              align="right"
              placeholder="选择日期范围"
              @change="changeZoomDate()"
              :picker-options="pickerOptions2"
            ></el-date-picker>
            <el-button type="primary" class="inquireAbout" @click="searchZoomData()">查询</el-button>
          </div>
          <div class="rose" id="rose"></div>
        </div>
      </el-col>
    </el-row>

    <!-- 从数据库拿到数据最大的十个数据 -->
    <el-row style="margin-top:60px">
      <el-col :span="16" style="position:relative">
        <div class="dataPicker">
          <el-date-picker
            v-model="industryDate"
            type="daterange"
            align="right"
            placeholder="选择日期范围"
            :picker-options="pickerOptions2"
          ></el-date-picker>
          <el-button type="primary" class="inquireAbout" @click="getIndustryDate()">查询</el-button>
        </div>
        <div class="grid-content bg-purple">
          <div id="industry" class="industry"></div>
        </div>
      </el-col>
      <!-- 请求回来数据最大前十个行业的各业务量统计 -->
      <el-col :span="8">
        <div class="grid-content bg-purple" style="position:relative">
          <div class="dataPicker">
            <el-date-picker
              v-model="value7"
              type="daterange"
              align="right"
              placeholder="选择日期范围"
              :picker-options="pickerOptions2"
            ></el-date-picker>
            <el-button type="primary" class="inquireAbout">查询</el-button>
          </div>
          <div class="industryGDP" id="industryGDP"></div>
        </div>
      </el-col>
    </el-row>
    <!-- 获取各个品牌的业务量前十的数据 -->
    <el-row style="margin-top:60px">
      <el-col :span="16" style="position:relative">
        <div class="dataPicker">
          <el-date-picker
            v-model="brandDate"
            type="daterange"
            align="right"
            @change="refleshTopBrand()"
            placeholder="选择日期范围"
            :picker-options="pickerOptions2"
          ></el-date-picker>
          <el-button type="primary" class="inquireAbout" @click="getBrandDate()">查询</el-button>
        </div>
        <div class="grid-content bg-purple">
          <div id="brand" class="brand"></div>
        </div>
      </el-col>
      <!-- 请求回来数据最大前十个行业的各业务量统计 -->
      <el-col :span="8">
        <div class="grid-content bg-purple" style="position:relative">
          <div class="dataPicker">
            <el-date-picker
              v-model="brandValue"
              type="daterange"
              align="right"
              @change="refleshBrand()"
              placeholder="选择日期范围"
              :picker-options="pickerOptions2"
            ></el-date-picker>
            <el-button type="primary" class="inquireAbout" @click="getBrandGDPDate()">查询</el-button>
          </div>
          <div class="brandGDP" id="brandGDP"></div>
        </div>
      </el-col>
    </el-row>

    <!-- 办结率统计 -->
    <el-row>
      <el-col :span="24" style="height:650px">
        <div class="grid-content bg-purple-dark" style="position:relative">
          <div class="year" style="position:absolute;top: 40px;width:100%;z-index:2">
            <el-row>
              <el-col :span="4">
                <div class="grid-content bg-purple">
                  <el-button type="info" @click="change('2018')">2018年</el-button>
                  <el-button type="info" @click="change('2019')">2019年</el-button>
                </div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content bg-purple-light"></div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content bg-purple"></div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content bg-purple-light"></div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content bg-purple"></div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content bg-purple-light"></div>
              </el-col>
            </el-row>
          </div>
          <div class="histogram" id="histogram"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  getDate,
  getTenBrand,
  getBrandClassify,
  getCountByReginon,
  getByRegion,
  getCountByReginonFinish,

    searchRegionData,
    searchregionComplete,
    searchNatureAnalysis,
    searchIllegalBehavior,
    searchHotQuestion,
    searchOverallBusiness,
    searchHotQuestionChange,
} from "../../../src/api/api";
import moment from "moment";
export default {
  name: "BeingDevelop",
  data() {
    return {

        //时间2020年1月11日开启----------------------------------------------------

        // 双层嵌套的饼形
        doublepieOption: {
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
                    center: ["50%", "60%"],
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
                        // {value: 335, name: "咨询登记", selected: true},
                        // {value: 679, name: "消费投诉"},
                        // {value: 1548, name: "经济违法"}
                    ]
                },
                {
                    name: "地区",
                    type: "pie",
                    radius: ["40%", "55%"],
                    center: ["50%", "60%"],
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
                        // {value: 123, name: "乐从"},
                        // {value: 10086, name: "陈村"},
                        // {value: 10086, name: "勒流"},
                        // {value: 10086, name: "大良"},
                        // {value: 10086, name: "杏坛"},
                        // {value: 10086, name: "均安"},
                        // {value: 10086, name: "容桂"},
                        // {value: 10086, name: "龙江"},
                        // {value: 10086, name: "伦教"},
                        // {value: 10086, name: "北滘"}

                    ]
                }
            ]
        },
        zoomData: "",


        //时间2020年1月11日分割线---------------------------------------------------

      //各分局的业务量的统计，各类业务的业务量统计，占比数据
      totalOfficeOptionRegister: [],
      totalOfficeOptionComplain: [],
      totalOfficeOptionEconomy: [],
      brandValue: [],
      zoomArray: [],
      brandSeriesComm: [],
      brandSeriesComplain: [],
      brandSeriesEco: [],
      brandSeriesTitleX: [],
      registerDatee2018: {},
      complainform2018: {},
      Eoconomyform2018: {},
      registerDate2019: {},
      complainform2019: {},
      Eoconomyform2019: {},
      his: {},
      topTenBrandRegister: 0,
      topTenBrandComplain: 0,
      topTenBrandEconomy: 0,
      totalOfficeOption: [
        {
          color: ["#66B3FF", "#FFB366", "#FFD1A3", "#FF9429"],
          title: {
            text: "各分局的各类业务量的统计",
            textStyle: {
              // color: "rgb(31, 143, 255)"
              color: "rgb(4, 7, 12)",
              fontSize: "20"
            }
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              // 坐标轴指示器，坐标轴触发有效
              type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            top: "100",
            data: ["咨询登记", "消费投诉", "经济违法"]
          },
          grid: {
            top: 150,
            left: "3%",
            right: "10%",
            bottom: "3%",
            containLabel: true
          },
          xAxis: [
            {
              type: "category",
              name: "分局",
              data: [
                "乐从",
                "伦教",
                "勒流",
                "北滘",
                "均安",
                "大良",
                "容桂",
                "杏坛",
                "陈村",
                "龙江"
              ]
            }
          ],
          yAxis: [
            {
              type: "value",
              name: "各分局的业务量",
              max: 150,
              min: 0,
              interval: 30,
              axiosLabel: {
                formatter: "{value}单"
              }
            }
            // {
            //   type: "value",
            //   name: "趋势",
            //   min: 0,
            //   max: 150,
            //   interval: 30,
            //   axisLabel: {
            //     formatter: "{value}"
            //   }
            // }
          ],
          series: [
            {
              name: "咨询登记",
              type: "bar",
              stack: "业务量",
              itemStyle: {
                background: "#eee"
              },
              data: []
            },
            {
              name: "消费投诉",
              type: "bar",
              stack: "业务量",
              data: []
            },
            {
              name: "经济违法",
              type: "bar",
              stack: "业务量",
              data: []
            },
            // {
            //   name: "业务量变化趋势",
            //   type: "line",
            //   data: [120, 139, 140, 110, 113, 143, 134]
            // },
            {
              type: "pie",
              id: "pie",
              radius: "30%",
              center: ["50%", "25%"],
              label: {
                formatter: "{b}{@2012}({d%})"
              },
              encode: {
                itemName: "product",
                value: "2012",
                tooltip: "2012"
              }
            }
          ]
        }
      ],
      roseOptions: [
        {
          title: {
            text: "各业务量占比统计",
            left: "left",
            top: 0,
            textStyle: {
              // color: "rgb(31,143,255)"
              color: "rgb(4, 7, 12)",
              fontSize: "20"
            }
          },
          grid: {
            y: 150
          },
          legend: {
            top: 100,
            data: ["咨询登记", "消费投诉", "经济违法"]
          },
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          visualMap: {
            show: false,
            min: 0,
            max: 600,
            inRange: {
              // colorLightness: [0, 1]
            }
          },
          series: [
            {
              name: "各业务量占比率",
              type: "pie",
              radius: "55%",
              center: ["50%", "65%"],
              color: [
                "rgb(102, 179, 255)",
                "rgb(255, 179, 102)",
                "rgb(255, 209, 163)"
              ],
              data: [
                { value: 0, name: "咨询登记" },
                { value: 0, name: "消费投诉" },
                { value: 0, name: "经济违法" }
              ].sort(function(a, b) {
                return a.value - b.value;
              }),
              roseType: "radius",
              stillShowZeroSum: true,
              animationType: "scale",
              animationEasing: "elasticOut",
              animationDelay: function(idx) {
                return Math.random() * 200;
              }
            }
          ]
        }
      ],
      histogramData: {},
      // 2018年的月份
      data2018: [
        "2018-01-01",
        "2018-02-01",
        "2018-03-01",
        "2018-04-01",
        "2018-05-01",
        "2018-06-01",
        "2018-07-01",
        "2018-08-01",
        "2018-09-01",
        "2018-10-01",
        "2018-11-01",
        "2018-12-01"
      ],
      data2019: [
        "2019-01-01",
        "2019-02-01",
        "2019-03-01",
        "2019-04-01",
        "2019-05-01",
        "2019-06-01",
        "2019-07-01",
        "2019-08-01",
        "2019-09-01",
        "2019-10-01",
        "2019-11-01",
        "2019-12-01"
      ],
      // 最后一个表格
      LCZConsultation2018: [],
      //
      histogramOptions: {
        baseOption: {
          color: ["#66B3FF", "#FFB366", "#FFD1A3", "#FF9429"],
          // 时间线
          timeline: {
            // y: 0,
            axisType: "category",
            autoPlay: true,
            playInterval: 5000,
            data: [],
            label: {
              formatter: function(s) {
                return (
                  new Date(s).getFullYear() +
                  "年" +
                  (new Date(s).getMonth() + 1) +
                  "月"
                );
              }
            },
            itemStyle: {
              borderColor: "rgb(255, 179, 102)"
            },
            lineStyle: {
              color: "rgb(31, 143, 255)"
            },
            controlStyle: {
              borderColor: "rgb(31, 143, 255)"
            },
            checkpointStyle: {
              color: "rgb(255, 179, 102)",
              borderColor: "rgb(255, 209, 163)"
            }
          },
          title: {
            textStyle: {
              color: "rgb(31, 143, 255)"
            }
            // subtext: "数据来自国家统计局"
          },
          tooltip: {},
          legend: {
            x: "center",
            top: "20",
            data: ["咨询登记", "消费投诉", "经济违法"]
          },
          // 是否实时更新
          calculable: true,
          // 条形图的位置
          grid: {
            top: 200,
            left: "5%",
            right: "10%",
            bottom: 100,
            tooltip: {
              trigger: "axis",
              axisPointer: {
                type: "shadow",
                label: {
                  show: true,
                  formatter: function(params) {
                    return params.value.replace("\n", "");
                  }
                }
              }
            }
          },
          // x轴
          xAxis: [
            {
              type: "category",
              axisLabel: { interval: 0 },
              data: [
                "北滘",
                "伦教",
                "龙江",
                "容桂",
                "均安",
                "杏坛",
                "陈村",
                "乐从",
                "勒流",
                "大良"
              ],
              splitLine: { show: false }
            }
          ],
          yAxis: [
            {
              type: "value",
              min: 0,
              max: 100,
              name: "办结率（%）"
            }
          ],
          series: [
            { name: "咨询登记", type: "bar" },
            { name: "消费投诉", type: "bar" },
            { name: "经济违法", type: "bar" },
            {
              name: "办结率",
              type: "pie",
              center: ["75%", "20%"],
              radius: "28%",
              z: 100,
              tooltip: {
                trigger: "item",
                formatter: "{a}({d}%)"
              }
            }
          ]
        },
        options: [
          {
            title: {
              text: "01月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "02月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "03月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "04月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "05月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "06月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "07月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "08月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "09月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "10月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "11月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          },
          {
            title: {
              text: "12月顺德监管局办结率统计",
              textStyle: {
                color: "rgb(4, 7, 12)",
                fontSize: "20"
              }
            },
            series: []
          }
        ]
      },

      industryOptions: [
        {
          color: ["#66B3FF", "#FFB366", "#FFD1A3", "#FF9429"],
          title: {
            text: "本周各行业投诉量Top10的统计",
            textStyle: {
              color: "rgb(4, 7, 12)",
              fontSize: "20"
            }
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              // 坐标轴指示器，坐标轴触发有效
              type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            top: "100",
            left: "center",
            data: ["咨询登记", "消费投诉", "经济违法", "业务量变化趋势"]
          },
          grid: {
            top: 150,
            left: "3%",
            right: "10%",
            bottom: "3%",
            containLabel: true
          },

          xAxis: [
            {
              type: "category",
              name: "企业",
              data: [
                "美的集团",
                "碧桂园",
                "格兰仕",
                "海信科龙",
                "联塑",
                "顺联",
                "万家乐",
                "万和",
                "华润万家",
                "顺德皇朝"
              ]
            }
          ],
          yAxis: [
            {
              type: "value",
              name: "业务量",
              // max: 150,
              // interval: 30,
              axiosLabel: {
                formatter: "{value}单"
              }
            }
          ],
          series: [
            {
              name: "咨询登记",
              type: "bar",
              stack: "业务量",
              itemStyle: {
                background: "#eee"
              },
              data: [20, 32, 31, 34, 39, 33, 37]
            },
            {
              name: "消费投诉",
              type: "bar",
              stack: "业务量",
              data: [3, 21, 13, 22, 11, 19, 10]
            },
            {
              name: "经济违法",
              type: "bar",
              stack: "业务量",
              data: [28, 12, 32, 53, 26, 74, 78]
            },
            {
              type: "pie",
              id: "pie",
              radius: "30%",
              center: ["50%", "25%"],
              label: {
                formatter: "{b}{@2012}({d%})"
              },
              encode: {
                itemName: "product",
                value: "2012",
                tooltip: "2012"
              }
            }
          ]
        }
      ],
      // 前十行业的各类占比饼形图数据
      drawIndustryGDPOptions: {
        color: [
          "rgb(102, 179, 255)",
          "rgb(255, 179, 102)",
          "rgb(255, 209, 163)"
          // "rgba(102, 179, 255,0.5)",
          // "rgba(255, 179, 102,0.5)",
          // "rgba(255, 209, 163,0.2)"
        ],
        title: {
          text: "各行各业务量占比统计",
          left: "left",
          top: 0,
          textStyle: {
            color: "rgb(4, 7, 12)",
            fontSize: "20"
          }
        },
        legend: {
          top: 100,
          data: ["咨询登记", "消费投诉", "经济违法"]
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
          show: false,
          min: 0,
          max: 600,
          inRange: {
            // colorLightness: [0.8,1]
          }
        },
        series: [
          {
            name: "各业务量占比率",
            type: "pie",
            radius: "55%",
            center: ["50%", "65%"],
            data: [
              { value: 0, name: "咨询登记" },
              { value: 10, name: "消费投诉" },
              { value: 2, name: "经济违法" }
            ].sort(function(a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",

            animationType: "scale",
            animationEasing: "elasticOut",
            animationDelay: function(idx) {
              return Math.random() * 200;
            }
          }
        ]
      },
      // 业务量最大的十个品牌
      brandOptions: [
        {
          color: ["#66B3FF", "#FFB366", "#FFD1A3", "#FF9429"],
          title: {
            text: "本周各品牌投诉量Top10的统计",
            textStyle: {
              // color: "rgb(31, 143, 255)"
              color: "rgb(4, 7, 12)",
              fontSize: "20"
            }
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              // 坐标轴指示器，坐标轴触发有效
              type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            top: "100",
            left: "center",
            data: ["咨询登记", "消费投诉", "经济违法"]
          },
          grid: {
            top: "150",
            left: "3%",
            right: "10%",
            bottom: "3%",
            containLabel: true
          },

          xAxis: [
            {
              type: "category",
              name: "企业",
              data: []
            }
          ],
          yAxis: [
            {
              type: "value",
              name: "业务量",
              max: 150,
              interval: 30,
              axiosLabel: {
                formatter: "{value}单"
              }
            }
          ],
          series: [
            {
              name: "咨询登记",
              type: "bar",
              stack: "业务量",
              itemStyle: {
                background: "#eee"
              },
              data: []
            },
            {
              name: "消费投诉",
              type: "bar",
              stack: "业务量",
              data: []
            },
            {
              name: "经济违法",
              type: "bar",
              stack: "业务量",
              data: []
            },
            {
              type: "pie",
              id: "pie",
              radius: "30%",
              center: ["50%", "25%"],
              label: {
                formatter: "{b}{@2012}({d%})"
              },
              encode: {
                itemName: "product",
                value: "2012",
                tooltip: "2012"
              }
            }
          ]
        }
      ],
      brandGDPOptions: {
        color: [
          "rgb(102, 179, 255)",
          "rgb(255, 179, 102)",
          "rgb(255, 209, 163)"
        ],
        title: {
          text: "各品牌的各业务量占比",
          left: "left",
          top: 0,
          textStyle: {
            // color: "rgb(31,143,255)"
            color: "rgb(4, 7, 12)",
            fontSize: "20"
          }
        },
        legend: {
          top: 100,
          data: ["咨询登记", "消费投诉", "经济违法"]
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            // colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: "各业务量占比率",
            type: "pie",
            radius: "55%",
            center: ["50%", "65%"],
            data: [
              { value: 0, name: "咨询登记" },
              { value: 0, name: "消费投诉" },
              { value: 0, name: "经济违法" }
            ].sort(function(a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",

            animationType: "scale",
            animationEasing: "elasticOut",
            animationDelay: function(idx) {
              return Math.random() * 200;
            }
          }
        ]
      },
      // 时期选择器
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

      // 品牌搜索的
      brandDate: [],
      // 搜索业务量前十行业的
      industryDate: "",
      // 分局的数据
      substationDate: "",
      // 各分局的各类业务量占比
      zoomLocationDate: "",
      value6: "",
      value7: ""
    };
  },
  methods: {

      //投诉举报热点(商品服务类)变化情况统计统计分析
      getHotQuestionChange(){
          let params = {};
          searchHotQuestionChange(params).then(res => {
             console.log(res)
          });
      },

      //投诉举报热点事件(问题分类)统计分析
      getOverallBusiness(){
          let params = {};
          searchOverallBusiness(params).then(res => {
             console.log(res)
          });
      },

      //投诉举报热点事件(问题分类)统计分析
      getHotQuestion(){
          let params = {};
          searchHotQuestion(params).then(res => {
             console.log(res)
          });
      },

      //投诉、举报性质(客体分类)统计分析
      getIllegalBehavior(){
          let params = {};
          searchIllegalBehavior(params).then(res => {
             console.log(res)
          });
      },

      //投诉、举报性质(客体分类)统计分析
      getNatureAnalysis(){
          let params = {};
          searchNatureAnalysis(params).then(res => {
             console.log(res)
          });
      },

      //各地区办结率统计查询
      getRegionComplete(){
          let params = {};
          searchregionComplete(params).then(res => {
             console.log(res)
          });
      },

      getRegionData(){
          let params = {};
          searchRegionData(params).then(res => {
              this.regionTotalOne = [];
              this.regionTotalTwo = [];
              let data = {}
              // console.log(this.regionTotalOne)
              for (var key in res.data) {
                  if(key == '咨询'){
                      data = {value: res.data[key] , name: key,selected: true}
                      this.regionTotalTwo.push(data)
                  }else if(key == '投诉' || key == '举报'){
                      data = {value: res.data[key] , name: key}
                      this.regionTotalTwo.push(data)
                  }else if(key == '区局'){

                  }else{
                      data = {value: res.data[key] , name: key}
                      this.regionTotalOne.push(data)
                  }
              }
              this.doublepieOption.series[1].data = this.regionTotalOne
              this.doublepieOption.series[0].data = this.regionTotalTwo
              this.drawDoublePie()
          });
      },

      drawDoublePie() {
          let drawChartOne = this.$echarts.init(
              document.getElementById("chartOne")
          );
          drawChartOne.setOption(this.doublepieOption);
      },



      //  分割线---------------------------------------------------------------
    refleshBrand() {
      this.getBrandGDPDate();
    },
    getBrandGDPDate() {
      this.topTenBrandRegister = 0;
      this.topTenBrandEconomy = 0;
      this.topTenBrandComplain = 0;
      if (this.brandValue[0] == null && this.brandValue[1] == null) {
        this.getDrawBrand();
      } else {
        let firstDate = moment(this.brandValue[0]).format("YYYY-MM-DD");
        let enddate = moment(this.brandValue[1]).format("YYYY-MM-DD");
        let params = {
          begindate: firstDate,
          enddate: enddate
        };

        getBrandClassify(params).then(res => {
          if (res.data.code == 0) {
            if (res.data.data != null || res.data.data != undefined) {
              this.brandSeriesComm[0] = res.data.data.Subject0.Commomform;
              this.brandSeriesComm[1] = res.data.data.Subject1.Commomform;
              this.brandSeriesComm[2] = res.data.data.Subject2.Commomform;
              this.brandSeriesComm[3] = res.data.data.Subject3.Commomform;
              this.brandSeriesComm[4] = res.data.data.Subject4.Commomform;
              this.brandSeriesComm[5] = res.data.data.Subject5.Commomform;
              this.brandSeriesComm[6] = res.data.data.Subject6.Commomform;
              this.brandSeriesComm[7] = res.data.data.Subject7.Commomform;
              this.brandSeriesComm[8] = res.data.data.Subject8.Commomform;
              this.brandSeriesComm[9] = res.data.data.Subject9.Commomform;

              this.brandSeriesComplain[0] =
                res.data.data.Subject0.Complaintform;
              this.brandSeriesComplain[1] =
                res.data.data.Subject1.Complaintform;
              this.brandSeriesComplain[2] =
                res.data.data.Subject2.Complaintform;
              this.brandSeriesComplain[3] =
                res.data.data.Subject3.Complaintform;
              this.brandSeriesComplain[4] =
                res.data.data.Subject4.Complaintform;
              this.brandSeriesComplain[5] =
                res.data.data.Subject5.Complaintform;
              this.brandSeriesComplain[6] =
                res.data.data.Subject6.Complaintform;
              this.brandSeriesComplain[7] =
                res.data.data.Subject7.Complaintform;
              this.brandSeriesComplain[8] =
                res.data.data.Subject8.Complaintform;
              this.brandSeriesComplain[9] =
                res.data.data.Subject9.Complaintform;

              this.brandSeriesEco[0] = res.data.data.Subject0.Economyform;
              this.brandSeriesEco[1] = res.data.data.Subject1.Economyform;
              this.brandSeriesEco[2] = res.data.data.Subject2.Economyform;
              this.brandSeriesEco[3] = res.data.data.Subject3.Economyform;
              this.brandSeriesEco[4] = res.data.data.Subject4.Economyform;
              this.brandSeriesEco[5] = res.data.data.Subject5.Economyform;
              this.brandSeriesEco[6] = res.data.data.Subject6.Economyform;
              this.brandSeriesEco[7] = res.data.data.Subject7.Economyform;
              this.brandSeriesEco[8] = res.data.data.Subject8.Economyform;
              this.brandSeriesEco[9] = res.data.data.Subject9.Economyform;

              this.brandSeriesTitleX[0] = res.data.data.Subject0.subjectName;
              this.brandSeriesTitleX[1] = res.data.data.Subject1.subjectName;
              this.brandSeriesTitleX[2] = res.data.data.Subject2.subjectName;
              this.brandSeriesTitleX[3] = res.data.data.Subject3.subjectName;
              this.brandSeriesTitleX[4] = res.data.data.Subject4.subjectName;
              this.brandSeriesTitleX[5] = res.data.data.Subject5.subjectName;
              this.brandSeriesTitleX[6] = res.data.data.Subject6.subjectName;
              this.brandSeriesTitleX[7] = res.data.data.Subject7.subjectName;
              this.brandSeriesTitleX[8] = res.data.data.Subject8.subjectName;
              this.brandSeriesTitleX[9] = res.data.data.Subject9.subjectName;

              this.brandOptions[0].xAxis[0].data = this.brandSeriesTitleX;
              this.brandOptions[0].series[0].data = this.brandSeriesComm;
              this.brandOptions[0].series[1].data = this.brandSeriesComplain;
              this.brandOptions[0].series[2].data = this.brandSeriesEco;

              for (let i = 0; i < this.brandSeriesComm.length; i++) {
                this.topTenBrandRegister += this.brandSeriesComm[i];
              }

              for (let i = 0; i < this.brandSeriesEco.length; i++) {
                this.topTenBrandEconomy += this.brandSeriesEco[i];
              }
              for (let i = 0; i < this.brandSeriesComplain.length; i++) {
                this.topTenBrandComplain += this.brandSeriesComplain[i];
              }
              this.brandGDPOptions.series[0].data[0].value = this.topTenBrandRegister;
              this.brandGDPOptions.series[0].data[1].value = this.topTenBrandComplain;
              this.brandGDPOptions.series[0].data[2].value = this.topTenBrandEconomy;

              this.drawBrandGDP();
            }
          }
        });
      }
    },
    // 获取传入时间的当前周
    getWeekDay(dateString) {
      let dateStringReg = /^\d{4}[/-]\d{1,2}[/-]\d{1,2}$/;

      if (dateString.match(dateStringReg)) {
        let presentDate = new Date(dateString),
          today = presentDate.getDay() !== 0 ? presentDate.getDay() : 7;

        return Array.from(new Array(7), function(val, index) {
          return formatDate(
            new Date(
              presentDate.getTime() - (today - index - 1) * 24 * 60 * 60 * 1000
            )
          );
        });
      } else {
        throw new Error(
          'dateString should be like "yyyy-mm-dd" or "yyyy/mm/dd"'
        );
      }

      function formatDate(date) {
        let dataStr = moment(date).format("YYYY-MM-DD");
        return dataStr;
      }
    },
    refleshTopBrand() {
      let firstDate = moment(this.brandDate[0]).format("YYYY-MM-DD");
      let enddate = moment(this.brandDate[1]).format("YYYY-MM-DD");
      if (this.brandDate[0] == null && this.brandDate[1] == null) {
        this.getBrandClassify();
        // console.log(this.brandDate);
      } else {
        this.getBrandDate();
      }
    },
    // 各分局的各类业务量的统计
    changesubstationDate() {
      this.getPliceTotal();
    },
    getByRegion() {
      var myDate = new Date();
      var currentDate = moment(myDate).format("YYYY-MM-DD");
      var begin = this.getWeekDay(currentDate)[0];
      var end = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      this.getByRegion1(begin, end);
    },
    getByRegion1(begin, end) {
      let params = {
        begindate: begin,
        enddate: end
      };
      getByRegion(params).then(res => {
        // console.log(res);
        if (res.data.code == 0) {
          this.totalOfficeOption[0].series[0].data[0] =
            res.data.data.乐从[0].count;
          this.totalOfficeOption[0].series[0].data[1] =
            res.data.data.伦教[0].count;
          this.totalOfficeOption[0].series[0].data[2] =
            res.data.data.勒流[0].count;
          this.totalOfficeOption[0].series[0].data[3] =
            res.data.data.北滘[0].count;
          this.totalOfficeOption[0].series[0].data[4] =
            res.data.data.均安[0].count;
          this.totalOfficeOption[0].series[0].data[5] =
            res.data.data.大良[0].count;
          this.totalOfficeOption[0].series[0].data[6] =
            res.data.data.容桂[0].count;
          this.totalOfficeOption[0].series[0].data[7] =
            res.data.data.杏坛[0].count;
          this.totalOfficeOption[0].series[0].data[8] =
            res.data.data.陈村[0].count;
          this.totalOfficeOption[0].series[0].data[9] =
            res.data.data.龙江[0].count;

          this.totalOfficeOption[0].series[1].data[0] =
            res.data.data.乐从[2].count;
          this.totalOfficeOption[0].series[1].data[1] =
            res.data.data.伦教[2].count;
          this.totalOfficeOption[0].series[1].data[2] =
            res.data.data.勒流[2].count;
          this.totalOfficeOption[0].series[1].data[3] =
            res.data.data.北滘[2].count;
          this.totalOfficeOption[0].series[1].data[4] =
            res.data.data.均安[2].count;
          this.totalOfficeOption[0].series[1].data[5] =
            res.data.data.大良[2].count;
          this.totalOfficeOption[0].series[1].data[6] =
            res.data.data.容桂[2].count;
          this.totalOfficeOption[0].series[1].data[7] =
            res.data.data.杏坛[2].count;
          this.totalOfficeOption[0].series[1].data[8] =
            res.data.data.陈村[2].count;
          this.totalOfficeOption[0].series[1].data[9] =
            res.data.data.龙江[2].count;

          this.totalOfficeOption[0].series[2].data[0] =
            res.data.data.乐从[1].count;
          this.totalOfficeOption[0].series[2].data[1] =
            res.data.data.伦教[1].count;
          this.totalOfficeOption[0].series[2].data[2] =
            res.data.data.勒流[1].count;
          this.totalOfficeOption[0].series[2].data[3] =
            res.data.data.北滘[1].count;
          this.totalOfficeOption[0].series[2].data[4] =
            res.data.data.均安[1].count;
          this.totalOfficeOption[0].series[2].data[5] =
            res.data.data.大良[1].count;
          this.totalOfficeOption[0].series[2].data[6] =
            res.data.data.容桂[1].count;
          this.totalOfficeOption[0].series[2].data[7] =
            res.data.data.杏坛[1].count;
          this.totalOfficeOption[0].series[2].data[8] =
            res.data.data.陈村[1].count;
          this.totalOfficeOption[0].series[2].data[9] =
            res.data.data.龙江[1].count;

          this.getSubstationDate();
        }
      });
    },
    // 各分局的各类业务的各类业务量的统计
    getClassifyTotal() {
      let params = {};
      getDate(params).then(res => {
        // console.log(res);
      });
    },
    // 条形图，第一个
    // drawpie() {
    //   let myChart = this.$echarts.init(document.getElementById("main"));
    //   myChart.setOption(this.totalOfficeOption[0]);
    // },
    // 格式化数据
    dataFormatter(obj) {
      var pList = [
        "北滘",
        "伦教",
        "龙江",
        "容桂",
        "均安",
        "杏坛",
        "陈村",
        "乐从",
        "勒流",
        "大良"
      ];
      var temp;
      var ratio = 0;
      var sumArr = [];
      for (var month = 1; month < 13; month++) {
        var max = 0;
        var sum = 0;
        temp = obj[month];
        var totalPercentege = 0;
        // console.log(temp.length);
        for (var i = 1, l = temp.length; i < l; i++) {
          max = Math.max(max, temp[i]);
          sum += temp[i];
          obj[month][i] = {
            name: pList[i],
            value: temp[i]
          };
        }
        obj[month + "max"] = Math.floor(max / 100) * 100;
        obj[month + "sum"] = sum;
      }
      return obj;
    },

    getPliceTotal() {
      // console.log(this.substationDate);
      if (this.substationDate[0] == null && this.substationDate[1] == null) {
        this.getByRegion();
      } else {
        let firstDate = moment(this.substationDate[0]).format("YYYY-MM-DD");
        let endDate = moment(this.substationDate[1]).format("YYYY-MM-DD");
        this.getByRegion1(firstDate, endDate);
      }
    },
    // 饼形玫瑰图
    drawRose() {
      let roseChart = this.$echarts.init(document.getElementById("rose"));
      roseChart.setOption(this.roseOptions[0]);
    },
    // /柱状图
    histogram() {
      let histogram = this.$echarts.init(document.getElementById("histogram"));
      histogram.setOption(this.histogramOptions);
    },
    // 最后一个图表
    // 2018年
    getEveryMonthDate2018() {
      let currentDate = new Date();
      currentDate = moment(currentDate).format("YYYY-MM-DD");
      let firstDate = this.getWeekDay(currentDate)[0];
      let enddate = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      this.everyMonthDate2018(firstDate, enddate);
    },
    // 2019年
    getEveryMonthDate2019() {
      let currentDate = new Date();
      currentDate = moment(currentDate).format("YYYY-MM-DD");
      let firstDate = this.getWeekDay(currentDate)[0];
      let enddate = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      this.everyMonthDate2019(firstDate, enddate);
    },
    // 2018年
    everyMonthDate2018(begindate, endDate) {
      let params = {
        begindate: begindate,
        endDate: endDate
      };
      getCountByReginonFinish(params).then(res => {
        let map = {};
        let year;
        let mouth;
        let count;
        let formname;
        for (let i in res.data.data) {
          if (typeof res.data.data[i] == "number") {
            continue;
          }
          for (let item in res.data.data[i]) {
            year = res.data.data[i][item].years;
            mouth = res.data.data[i][item].mouths;
            count = res.data.data[i][item].count;
            formname = res.data.data[i][item].formname;
            if (!map[year]) {
              //初始化年
              map[year] = [];
            }
            if (!map[year][formname]) {
              //初始化类型
              map[year][formname] = [];
            }
            if (!map[year][formname][mouth - 1]) {
              //初始化月
              map[year][formname][mouth - 1] = [];
            }
            map[year][formname][mouth - 1].push(count);
          }
        }

        this.registerDate2018 = {
          1: map["2018"]["consultationform"][0],
          2: map["2018"]["consultationform"][1],
          3: map["2018"]["consultationform"][2],
          4: map["2018"]["consultationform"][3],
          5: map["2018"]["consultationform"][4],
          6: map["2018"]["consultationform"][5],
          7: map["2018"]["consultationform"][6],
          8: map["2018"]["consultationform"][7],
          9: map["2018"]["consultationform"][8],
          10: map["2018"]["consultationform"][9],
          11: map["2018"]["consultationform"][10],
          12: map["2018"]["consultationform"][11]
        };
        this.complainform2018 = {
          1: map["2018"]["complaintform"][0],
          2: map["2018"]["complaintform"][1],
          3: map["2018"]["complaintform"][2],
          4: map["2018"]["complaintform"][3],
          5: map["2018"]["complaintform"][4],
          6: map["2018"]["complaintform"][5],
          7: map["2018"]["complaintform"][6],
          8: map["2018"]["complaintform"][7],
          9: map["2018"]["complaintform"][8],
          10: map["2018"]["complaintform"][9],
          11: map["2018"]["complaintform"][10],
          12: map["2018"]["complaintform"][11]
        };
        this.Eoconomyform2018 = {
          1: map["2018"]["economyform"][0],
          2: map["2018"]["economyform"][1],
          3: map["2018"]["economyform"][2],
          4: map["2018"]["economyform"][3],
          5: map["2018"]["economyform"][4],
          6: map["2018"]["economyform"][5],
          7: map["2018"]["economyform"][6],
          8: map["2018"]["economyform"][7],
          9: map["2018"]["economyform"][8],
          10: map["2018"]["economyform"][9],
          11: map["2018"]["economyform"][10],
          12: map["2018"]["economyform"][11]
        };
        this.histogramData.dataRegister = this.dataFormatter(
          this.registerDate2018
        );
        this.histogramData.dataEconomy = this.dataFormatter(
          this.Eoconomyform2018
        );
        this.histogramData.dataComplain = this.dataFormatter(
          this.complainform2018
        );

        for (let i = 1; i <= 12; i++) {
          this.histogramOptions.options[i - 1].series = [
            { data: this.histogramData.dataComplain[`${i}`] },
            { data: this.histogramData.dataRegister[`${i}`] },
            { data: this.histogramData.dataEconomy[`${i}`] },
            {
              data: [
                {
                  name: "消费投诉",
                  value: Math.round(
                    (this.histogramData.dataComplain[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      100
                  )
                },
                {
                  name: "咨询登记",
                  value: Math.round(
                    (this.histogramData.dataRegister[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      100
                  )
                },
                {
                  name: "经济违法",
                  value: Math.round(
                    (this.histogramData.dataEconomy[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      10
                  )
                }
              ]
            }
          ];
        this.histogram();
          // console.log(this.histogramOptions.options[i - 1].series);
        }
      });
    },
    // 2019年
    everyMonthDate2019(begindate, endDate) {
      let params = {
        begindate: begindate,
        endDate: endDate
      };
      getCountByReginonFinish(params).then(res => {
        let map = {};
        let year;
        let mouth;
        let count;
        let formname;
        for (let i in res.data.data) {
          if (typeof res.data.data[i] == "number") {
            continue;
          }
          for (let item in res.data.data[i]) {
            year = res.data.data[i][item].years;
            mouth = res.data.data[i][item].mouths;
            count = res.data.data[i][item].count;
            formname = res.data.data[i][item].formname;
            if (!map[year]) {
              //初始化年
              map[year] = [];
            }
            if (!map[year][formname]) {
              //初始化类型
              map[year][formname] = [];
            }
            if (!map[year][formname][mouth - 1]) {
              //初始化月
              map[year][formname][mouth - 1] = [];
            }
            map[year][formname][mouth - 1].push(count);
          }
        }

        this.registerDate2019 = {
          1: map["2019"]["consultationform"][0],
          2: map["2019"]["consultationform"][1],
          3: map["2019"]["consultationform"][2],
          4: map["2019"]["consultationform"][3],
          5: map["2019"]["consultationform"][4],
          6: map["2019"]["consultationform"][5],
          7: map["2019"]["consultationform"][6],
          8: map["2019"]["consultationform"][7],
          9: map["2019"]["consultationform"][8],
          10: map["2019"]["consultationform"][9],
          11: map["2019"]["consultationform"][10],
          12: map["2019"]["consultationform"][11]
        };
        this.complainform2019 = {
          1: map["2019"]["complaintform"][0],
          2: map["2019"]["complaintform"][1],
          3: map["2019"]["complaintform"][2],
          4: map["2019"]["complaintform"][3],
          5: map["2019"]["complaintform"][4],
          6: map["2019"]["complaintform"][5],
          7: map["2019"]["complaintform"][6],
          8: map["2019"]["complaintform"][7],
          9: map["2019"]["complaintform"][8],
          10: map["2019"]["complaintform"][9],
          11: map["2019"]["complaintform"][10],
          12: map["2019"]["complaintform"][11]
        };
        this.Eoconomyform2019 = {
          1: map["2019"]["economyform"][0],
          2: map["2019"]["economyform"][1],
          3: map["2019"]["economyform"][2],
          4: map["2019"]["economyform"][3],
          5: map["2019"]["economyform"][4],
          6: map["2019"]["economyform"][5],
          7: map["2019"]["economyform"][6],
          8: map["2019"]["economyform"][7],
          9: map["2019"]["economyform"][8],
          10: map["2019"]["economyform"][9],
          11: map["2019"]["economyform"][10],
          12: map["2019"]["economyform"][11]
        };
        this.histogramData.dataRegister = this.dataFormatter(
          this.registerDate2019
        );
        this.histogramData.dataEconomy = this.dataFormatter(
          this.Eoconomyform2019
        );
        this.histogramData.dataComplain = this.dataFormatter(
          this.complainform2019
        );
        this.histogram();

        for (let i = 1; i <= 12; i++) {
          this.histogramOptions.options[i - 1].series = [
            { data: this.histogramData.dataComplain[`${i}`] },
            { data: this.histogramData.dataRegister[`${i}`] },
            { data: this.histogramData.dataEconomy[`${i}`] },
            {
              data: [
                {
                  name: "消费投诉",
                  value: Math.round(
                    (this.histogramData.dataComplain[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      100
                  )
                },
                {
                  name: "咨询登记",
                  value: Math.round(
                    (this.histogramData.dataRegister[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      100
                  )
                },
                {
                  name: "经济违法",
                  value: Math.round(
                    (this.histogramData.dataEconomy[`${i}` + "sum"] /
                      (this.histogramData.dataRegister[`${i}` + "sum"] +
                        this.histogramData.dataEconomy[`${i}` + "sum"] +
                        this.histogramData.dataComplain[`${i}` + "sum"])) *
                      10
                  )
                }
              ]
            }
          ];
        }
      });
    },
    // 更改数据的年份
   change(value) {
      if (value === "2018") {

        this.histogramOptions.baseOption.timeline.data = this.data2018;

        this.getEveryMonthDate2018()
        this.histogram();
      } else {
        this.histogramOptions.baseOption.timeline.data = this.data2019;
        this.getEveryMonthDate2019()
        this.histogram();
      }
    },
    // 获取品牌各业务量
    getBrandClassify() {
      var getDate = new Date();
      var currentDate = moment(getDate).format("YYYY-MM-DD");
      this.getWeekDay(currentDate);
      var begin = this.getWeekDay(currentDate)[0];
      var end = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      let params = {
        begindate: begin,
        enddate: end
      };
      getBrandClassify(params).then(res => {
        if (res.data.code == 0) {
          if (res.data.data != null || res.data.data != undefined) {
            // for(let i = 0;i< 10;i++){
            //   this.brandSeries[i] = this[`res.data.data.Subject${i}`].Commomform;
            // }
            this.brandSeriesComm[0] = res.data.data.Subject0.Commomform;
            this.brandSeriesComm[1] = res.data.data.Subject1.Commomform;
            this.brandSeriesComm[2] = res.data.data.Subject2.Commomform;
            this.brandSeriesComm[3] = res.data.data.Subject3.Commomform;
            this.brandSeriesComm[4] = res.data.data.Subject4.Commomform;
            this.brandSeriesComm[5] = res.data.data.Subject5.Commomform;
            this.brandSeriesComm[6] = res.data.data.Subject6.Commomform;
            this.brandSeriesComm[7] = res.data.data.Subject7.Commomform;
            this.brandSeriesComm[8] = res.data.data.Subject8.Commomform;
            this.brandSeriesComm[9] = res.data.data.Subject9.Commomform;

            this.brandSeriesComplain[0] = res.data.data.Subject0.Complaintform;
            this.brandSeriesComplain[1] = res.data.data.Subject1.Complaintform;
            this.brandSeriesComplain[2] = res.data.data.Subject2.Complaintform;
            this.brandSeriesComplain[3] = res.data.data.Subject3.Complaintform;
            this.brandSeriesComplain[4] = res.data.data.Subject4.Complaintform;
            this.brandSeriesComplain[5] = res.data.data.Subject5.Complaintform;
            this.brandSeriesComplain[6] = res.data.data.Subject6.Complaintform;
            this.brandSeriesComplain[7] = res.data.data.Subject7.Complaintform;
            this.brandSeriesComplain[8] = res.data.data.Subject8.Complaintform;
            this.brandSeriesComplain[9] = res.data.data.Subject9.Complaintform;

            this.brandSeriesEco[0] = res.data.data.Subject0.Economyform;
            this.brandSeriesEco[1] = res.data.data.Subject1.Economyform;
            this.brandSeriesEco[2] = res.data.data.Subject2.Economyform;
            this.brandSeriesEco[3] = res.data.data.Subject3.Economyform;
            this.brandSeriesEco[4] = res.data.data.Subject4.Economyform;
            this.brandSeriesEco[5] = res.data.data.Subject5.Economyform;
            this.brandSeriesEco[6] = res.data.data.Subject6.Economyform;
            this.brandSeriesEco[7] = res.data.data.Subject7.Economyform;
            this.brandSeriesEco[8] = res.data.data.Subject8.Economyform;
            this.brandSeriesEco[9] = res.data.data.Subject9.Economyform;

            this.brandSeriesTitleX[0] = res.data.data.Subject0.subjectName;
            this.brandSeriesTitleX[1] = res.data.data.Subject1.subjectName;
            this.brandSeriesTitleX[2] = res.data.data.Subject2.subjectName;
            this.brandSeriesTitleX[3] = res.data.data.Subject3.subjectName;
            this.brandSeriesTitleX[4] = res.data.data.Subject4.subjectName;
            this.brandSeriesTitleX[5] = res.data.data.Subject5.subjectName;
            this.brandSeriesTitleX[6] = res.data.data.Subject6.subjectName;
            this.brandSeriesTitleX[7] = res.data.data.Subject7.subjectName;
            this.brandSeriesTitleX[8] = res.data.data.Subject8.subjectName;
            this.brandSeriesTitleX[9] = res.data.data.Subject9.subjectName;

            this.brandOptions[0].xAxis[0].data = this.brandSeriesTitleX;
            this.brandOptions[0].series[0].data = this.brandSeriesComm;
            this.brandOptions[0].series[1].data = this.brandSeriesComplain;
            this.brandOptions[0].series[2].data = this.brandSeriesEco;

            this.drawBrand();
          }
        }
      });
    },

    // 被投诉业务量前十的行业
    topFive() {
      let topTen = this.$echarts.init(document.getElementById("industry"));
      topTen.setOption(this.industryOptions[0]);
    },
    // 前十行业的各类占比饼形图
    drawIndustryGDP() {
      let drawIndustryGDP = this.$echarts.init(
        document.getElementById("industryGDP")
      );
      drawIndustryGDP.setOption(this.drawIndustryGDPOptions);
    },
    // 业务量前十的十大品牌
    drawBrand() {
      let brand = this.$echarts.init(document.getElementById("brand"));
      brand.setOption(this.brandOptions[0]);
    },
    getDrawBrand() {
      var getDate = new Date();
      var currentDate = moment(getDate).format("YYYY-MM-DD");
      this.getWeekDay(currentDate);
      var begin = this.getWeekDay(currentDate)[0];
      var end = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      let params = {
        begindate: begin,
        enddate: end
      };
      getBrandClassify(params).then(res => {
        if (res.data.code == 0) {
          if (res.data.data != null || res.data.data != undefined) {
            this.brandSeriesComm[0] = res.data.data.Subject0.Commomform;
            this.brandSeriesComm[1] = res.data.data.Subject1.Commomform;
            this.brandSeriesComm[2] = res.data.data.Subject2.Commomform;
            this.brandSeriesComm[3] = res.data.data.Subject3.Commomform;
            this.brandSeriesComm[4] = res.data.data.Subject4.Commomform;
            this.brandSeriesComm[5] = res.data.data.Subject5.Commomform;
            this.brandSeriesComm[6] = res.data.data.Subject6.Commomform;
            this.brandSeriesComm[7] = res.data.data.Subject7.Commomform;
            this.brandSeriesComm[8] = res.data.data.Subject8.Commomform;
            this.brandSeriesComm[9] = res.data.data.Subject9.Commomform;

            this.brandSeriesComplain[0] = res.data.data.Subject0.Complaintform;
            this.brandSeriesComplain[1] = res.data.data.Subject1.Complaintform;
            this.brandSeriesComplain[2] = res.data.data.Subject2.Complaintform;
            this.brandSeriesComplain[3] = res.data.data.Subject3.Complaintform;
            this.brandSeriesComplain[4] = res.data.data.Subject4.Complaintform;
            this.brandSeriesComplain[5] = res.data.data.Subject5.Complaintform;
            this.brandSeriesComplain[6] = res.data.data.Subject6.Complaintform;
            this.brandSeriesComplain[7] = res.data.data.Subject7.Complaintform;
            this.brandSeriesComplain[8] = res.data.data.Subject8.Complaintform;
            this.brandSeriesComplain[9] = res.data.data.Subject9.Complaintform;

            this.brandSeriesEco[0] = res.data.data.Subject0.Economyform;
            this.brandSeriesEco[1] = res.data.data.Subject1.Economyform;
            this.brandSeriesEco[2] = res.data.data.Subject2.Economyform;
            this.brandSeriesEco[3] = res.data.data.Subject3.Economyform;
            this.brandSeriesEco[4] = res.data.data.Subject4.Economyform;
            this.brandSeriesEco[5] = res.data.data.Subject5.Economyform;
            this.brandSeriesEco[6] = res.data.data.Subject6.Economyform;
            this.brandSeriesEco[7] = res.data.data.Subject7.Economyform;
            this.brandSeriesEco[8] = res.data.data.Subject8.Economyform;
            this.brandSeriesEco[9] = res.data.data.Subject9.Economyform;

            this.brandSeriesTitleX[0] = res.data.data.Subject0.subjectName;
            this.brandSeriesTitleX[1] = res.data.data.Subject1.subjectName;
            this.brandSeriesTitleX[2] = res.data.data.Subject2.subjectName;
            this.brandSeriesTitleX[3] = res.data.data.Subject3.subjectName;
            this.brandSeriesTitleX[4] = res.data.data.Subject4.subjectName;
            this.brandSeriesTitleX[5] = res.data.data.Subject5.subjectName;
            this.brandSeriesTitleX[6] = res.data.data.Subject6.subjectName;
            this.brandSeriesTitleX[7] = res.data.data.Subject7.subjectName;
            this.brandSeriesTitleX[8] = res.data.data.Subject8.subjectName;
            this.brandSeriesTitleX[9] = res.data.data.Subject9.subjectName;

            this.brandOptions[0].xAxis[0].data = this.brandSeriesTitleX;
            this.brandOptions[0].series[0].data = this.brandSeriesComm;
            this.brandOptions[0].series[1].data = this.brandSeriesComplain;
            this.brandOptions[0].series[2].data = this.brandSeriesEco;

            for (let i = 0; i < this.brandSeriesComm.length; i++) {
              this.topTenBrandRegister += this.brandSeriesComm[i];
            }
            for (let i = 0; i < this.brandSeriesEco.length; i++) {
              this.topTenBrandEconomy += this.brandSeriesEco[i];
            }
            for (let i = 0; i < this.brandSeriesComplain.length; i++) {
              this.topTenBrandComplain += this.brandSeriesComplain[i];
            }
            this.brandGDPOptions.series[0].data[0].value = this.topTenBrandRegister;
            this.brandGDPOptions.series[0].data[1].value = this.topTenBrandComplain;
            this.brandGDPOptions.series[0].data[2].value = this.topTenBrandEconomy;

            this.drawBrandGDP();
          }
        }
      });
    },
    drawBrandGDP() {
      let drawBrandGDP = this.$echarts.init(
        document.getElementById("brandGDP")
      );
      drawBrandGDP.setOption(this.brandGDPOptions);
    },
    // 用户输入选择的时间提交修改表单数据及标题(被投诉的品牌)
    getBrandDate() {
      if (this.brandDate[0] == null && this.brandDate[1] == null) {
        this.getBrandClassify();
      } else {
        let firstDate = moment(this.brandDate[0]).format("YYYY-MM-DD");
        let enddate = moment(this.brandDate[1]).format("YYYY-MM-DD");
        let params = {
          begindate: firstDate,
          enddate: enddate
        };
        getBrandClassify(params).then(res => {
          if (res.data.code == 0) {
            if (res.data.data != null || res.data.data != undefined) {
              this.brandSeriesComm[0] = res.data.data.Subject0.Commomform;
              this.brandSeriesComm[1] = res.data.data.Subject1.Commomform;
              this.brandSeriesComm[2] = res.data.data.Subject2.Commomform;
              this.brandSeriesComm[3] = res.data.data.Subject3.Commomform;
              this.brandSeriesComm[4] = res.data.data.Subject4.Commomform;
              this.brandSeriesComm[5] = res.data.data.Subject5.Commomform;
              this.brandSeriesComm[6] = res.data.data.Subject6.Commomform;
              this.brandSeriesComm[7] = res.data.data.Subject7.Commomform;
              this.brandSeriesComm[8] = res.data.data.Subject8.Commomform;
              this.brandSeriesComm[9] = res.data.data.Subject9.Commomform;

              this.brandSeriesComplain[0] =
                res.data.data.Subject0.Complaintform;
              this.brandSeriesComplain[1] =
                res.data.data.Subject1.Complaintform;
              this.brandSeriesComplain[2] =
                res.data.data.Subject2.Complaintform;
              this.brandSeriesComplain[3] =
                res.data.data.Subject3.Complaintform;
              this.brandSeriesComplain[4] =
                res.data.data.Subject4.Complaintform;
              this.brandSeriesComplain[5] =
                res.data.data.Subject5.Complaintform;
              this.brandSeriesComplain[6] =
                res.data.data.Subject6.Complaintform;
              this.brandSeriesComplain[7] =
                res.data.data.Subject7.Complaintform;
              this.brandSeriesComplain[8] =
                res.data.data.Subject8.Complaintform;
              this.brandSeriesComplain[9] =
                res.data.data.Subject9.Complaintform;

              this.brandSeriesEco[0] = res.data.data.Subject0.Economyform;
              this.brandSeriesEco[1] = res.data.data.Subject1.Economyform;
              this.brandSeriesEco[2] = res.data.data.Subject2.Economyform;
              this.brandSeriesEco[3] = res.data.data.Subject3.Economyform;
              this.brandSeriesEco[4] = res.data.data.Subject4.Economyform;
              this.brandSeriesEco[5] = res.data.data.Subject5.Economyform;
              this.brandSeriesEco[6] = res.data.data.Subject6.Economyform;
              this.brandSeriesEco[7] = res.data.data.Subject7.Economyform;
              this.brandSeriesEco[8] = res.data.data.Subject8.Economyform;
              this.brandSeriesEco[9] = res.data.data.Subject9.Economyform;

              this.brandSeriesTitleX[0] = res.data.data.Subject0.subjectName;
              this.brandSeriesTitleX[1] = res.data.data.Subject1.subjectName;
              this.brandSeriesTitleX[2] = res.data.data.Subject2.subjectName;
              this.brandSeriesTitleX[3] = res.data.data.Subject3.subjectName;
              this.brandSeriesTitleX[4] = res.data.data.Subject4.subjectName;
              this.brandSeriesTitleX[5] = res.data.data.Subject5.subjectName;
              this.brandSeriesTitleX[6] = res.data.data.Subject6.subjectName;
              this.brandSeriesTitleX[7] = res.data.data.Subject7.subjectName;
              this.brandSeriesTitleX[8] = res.data.data.Subject8.subjectName;
              this.brandSeriesTitleX[9] = res.data.data.Subject9.subjectName;

              this.brandOptions[0].xAxis[0].data = this.brandSeriesTitleX;
              this.brandOptions[0].series[0].data = this.brandSeriesComm;
              this.brandOptions[0].series[1].data = this.brandSeriesComplain;
              this.brandOptions[0].series[2].data = this.brandSeriesEco;
              this.drawBrand();
            }
          }
        });
      }
    },
    // 被投诉的前十行业的业务量
    getIndustryDate() {
      this.industryOptions[1].title.text = this.industryDate;
      let topTen = this.$echarts.init(document.getElementById("industry"));
      topTen.setOption(this.industryOptions[1]);
    },
    // 分局的数据量
    getSubstationDate() {
      // this.totalOfficeOption[0].title.text = this.substationDate;

      let myChart = this.$echarts.init(document.getElementById("main"));
      // this.getByRegion();
      myChart.setOption(this.totalOfficeOption[0]);
    },
    // 根据日期获取数据
    getdate() {
      let currentDate = new Date();
      currentDate = moment(currentDate).format("YYYY-MM-DD");
      let firstDate = this.getWeekDay(currentDate)[0];
      let enddate = this.getWeekDay(currentDate)[
        this.getWeekDay(currentDate).length - 1
      ];
      let params = {
        begindate: firstDate,
        enddate: enddate
      };
      getDate(params).then(res => {
        if (res.data.code == 0) {
          this.roseOptions[0].series[0].data[0].value =
            res.data.data.Consultationform;
          this.roseOptions[0].series[0].data[1].value =
            res.data.data.Complaintform;
          this.roseOptions[0].series[0].data[2].value =
            res.data.data.Economyform;
          this.drawRose();
        }
      });
    },
    // 获取投诉量前十的品牌
    getTenBrand() {
      let params = {};
      getTenBrand(params).then(res => {
        if (res.data.code === 0) {
        }
      });
    },
    // 各分局的各类业务量的统计及趋势
    getCountByReginon() {
      let params = {};
      getCountByReginon(params).then(res => {
        if (res.data.code === 0) {
          for (let i in res.data.data) {
            let zoomList = {};
            zoomList[i] = res.data.data[i];
            this.zoomArray.push(zoomList);
          }
          // console.log(this.zoomArray);
        }
      });
    },
    crtTimeFtt(val) {
      if (val != null) {
        var date = new Date(val);
        return (
          date.getFullYear() +
          "-" +
          (date.getMonth() + 1) +
          "-" +
          date.getDate()
        );
      }
    },
    changeZoomDate() {
      this.searchZoomData();
    },
    searchZoomData() {
      if (
        this.zoomLocationDate[0] == null &&
        this.zoomLocationDate[1] == null
      ) {
        this.getdate();
      } else {
        let firstDate = moment(this.zoomLocationDate[0]).format("YYYY-MM-DD");
        let enddate = moment(this.zoomLocationDate[1]).format("YYYY-MM-DD");
        this.searchZoomData1(firstDate, enddate);
      }
    },
    searchZoomData1(firstDate, second) {
      let params = {
        begindate: firstDate,
        enddate: second
      };
      getDate(params).then(res => {
        // console.log(res);
        if (res.data.code == 0) {
          if (res.data.code == 0) {
            this.roseOptions[0].series[0].data[0].value =
              res.data.data.Consultationform;
            this.roseOptions[0].series[0].data[1].value =
              res.data.data.Complaintform;
            this.roseOptions[0].series[0].data[2].value =
              res.data.data.Economyform;
            this.drawRose();
          }
        }
      });
    }
  },
  mounted() {

      this.getHotQuestionChange();
      this.getOverallBusiness();
      this.getHotQuestion();
      this.getIllegalBehavior();
      this.getNatureAnalysis();
      this.getRegionData();
      this.getRegionComplete();

      // 2020年1月20号后开发内容请放置于分隔线内     分割线------------------------------------------------------
    this.drawBrand();


    // 日期的时间
    this.histogramOptions.baseOption.timeline.data = this.data2019;

    // this.change();
    // this.drawRose();
    // this.getEveryMonthDate2019();
    // // this.getEveryMonthDate1();
    // // this.drawpie();
    // this.histogram();
    // // 各行业的投诉
    // this.topFive();
    // // 各行业的各类业务量的统计
    // this.drawIndustryGDP();
    // // 投诉前十的品牌
    // // this.getBrandGDPDate();
    // this.getDrawBrand();
    // this.drawBrandGDP();
    // // this.searchZoomData();
    // this.getdate();
    // // this.getTenBrand();
    // this.getBrandClassify();
    // this.getCountByReginon();
    // this.searchZoomData();
    // this.getClassifyTotal();
    // this.getByRegion();
    // this.getSubstationDate();
    // this.getBrandClassify();
    // this.getPliceTotal();
    // this.getBrandDate();
  }
};
</script>

<style scoped>
.row {
  width: 100%;
  text-align: center;
  font-size: 30px;
  height: 350px;
  color: #262626;
}

.el-icon-information {
  color: #20a0ff;
  font-size: 30px;
  margin-right: 10px;
}
.radius {
  height: 400px;
}
.rose,
.industryGDP,
.brand,
.brandGDP {
  height: 400px;
}
.industry {
  height: 400px;
}
.bg-purple-dark {
  margin-top: 50px;
  height: 450px;
}
.histogram {
  width: 100%;
  height: 600px;
  /* background: #000; */
}
.year {
  height: 30px;
}
.el-button--info {
  cursor: pointer;
  background: rgb(31, 143, 255);
  border-color: rgb(31, 143, 255);
}
/* 时间选择器 */
.dataPicker {
  position: absolute;
  width: 100%;
  top: 40px;
  text-align: center;
  z-index: 2;
}
.inquireAbout {
  background: rgb(31, 143, 255);
  border-color: rgb(31, 143, 255);
}

  ----------2020先1月11号分割线下开发---------------

.zoomDataChart {
  position: absolute;
  top: 60px;
  left: 0;
  height: 40px;
  width: 100%;
  z-index: 2;
  text-align: center;
}


</style>
