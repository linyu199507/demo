<template>
  <!-- 同类型案件对比 -->
  <div class="bg">
    <div class="caseComparison">
      <card :header="{title:title}">
        <div slot="content" class="card-demo-flex card-demo-content01">
          <div class="vux-1px-r">
            <span>{{commom}}</span>
            <br />咨询类
          </div>
          <div class="vux-1px-r">
            <span>{{complaint}}</span>
            <br />投诉类
          </div>
          <div class="vux-1px-r">
            <span>{{economy}}</span>
            <br />举报类
          </div>
        </div>
      </card>
      <div class="line"></div>
      <div class="chart">
        <div class="barCharOfConsult" id="barCharOfConsult"></div>
      </div>
      <div class="chart">
        <div class="barCharOfComplpaints" id="barCharOfComplpaints"></div>
      </div>
      <div class="chart">
        <div class="barCharOfEconomy" id="barCharOfEconomy"></div>
      </div>
    </div>
  </div>
</template>
<script>

  import { searchBusinessTotal } from '../api/api';

export default {
  data() {
    return {
      commom:0,
      complaint:0,
      economy:0,
      title: "近六月同类型案件对比",
      barCharOption: {
        color: ["#5CADFF"],
        title: {
          text: "咨询类案件量对比",
          textStyle: {
            color: "#000",
            fontWeight: "normal",
            fontSize: "15px"
          },
          left: "10px"
        },
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "10%",
          right: "15%",
          bottom: "3%",
          containLabel: true
        },
        legend: {
          data: ["总量"],
          top: "10%"
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            saveAsImage: { show: true }
          }
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            data: [
              // "1月",
              // "2月",
              // "3月",
              // "4月",
              // "5月",
              // "6月",
              // "7月",
              // "8月",
              // "9月",
              // "10月",
              // "11月",
              // "12月"
            ]
          }
        ],
        yAxis: [
          {
            type: "value",
            max: function(value) {
              return value.max + 100;
            }
          }
        ],
        series: [
          {
            name: "案件数",
            type: "bar",
            data: [
              // 2.6,
              // 5.9,
              // 9.0,
              // 26.4,
              // 28.7,
              // 70.7,
              // 175.6,
              // 182.2,
              // 48.7,
              // 18.8,
              // 6.0,
              // 2.3
            ],
            markPoint: {
              data: [
                // { name: "年最高", value: Math.max(this.barCharOption.series[0].data), xAxis: 7, yAxis: 183 },
                // { name: "年最低", value: Math.min(this.barCharOption.series[0].data), xAxis: 11, yAxis: 3 }
              ]
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }]
            }
          }
        ]
      },
      barCharOptionOfComplaints: {
        color: ["#5CADFF"],
        title: {
          text: "投诉类案件量对比",
          textStyle: {
            color: "#000",
            fontWeight: "normal",
            fontSize: "15px"
          },
          left: "10px"
        },
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "10%",
          right: "15%",
          bottom: "3%",
          containLabel: true
        },
        legend: {
          data: ["总量"],
          top: "10%"
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            saveAsImage: { show: true }
          }
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            data: [
              // "1月",
              // "2月",
              // "3月",
              // "4月",
              // "5月",
              // "6月",
              // "7月",
              // "8月",
              // "9月",
              // "10月",
              // "11月",
              // "12月"
            ]
          }
        ],
        yAxis: [
          {
            type: "value",
            max: function(value) {
              return value.max + 100;
            }
          }
        ],
        series: [
          {
            name: "案件数",
            type: "bar",
            data: [
              // 2.6,
              // 5.9,
              // 9.0,
              // 26.4,
              // 28.7,
              // 70.7,
              // 175.6,
              // 182.2,
              // 48.7,
              // 18.8,
              // 6.0,
              // 2.3
            ],
            markPoint: {
              data: [
                { name: "年最高", value: 182.2, xAxis: 7, yAxis: 183 },
                { name: "年最低", value: 2.3, xAxis: 11, yAxis: 3 }
              ]
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }]
            }
          }
        ]
      },
      barCharOptionOfEconomy: {
        color: ["#5CADFF"],
        title: {
          text: "举报类案件量对比",
          textStyle: {
            color: "#000",
            fontWeight: "normal",
            fontSize: "15px"
          },
          left: "10px"
        },
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "10%",
          right: "15%",
          bottom: "3%",
          containLabel: true
        },
        legend: {
          data: ["总量"],
          top: "10%"
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            saveAsImage: { show: true }
          }
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            data: [
              // "1月",
              // "2月",
              // "3月",
              // "4月",
              // "5月",
              // "6月",
              // "7月",
              // "8月",
              // "9月",
              // "10月",
              // "11月",
              // "12月"
            ]
          }
        ],
        yAxis: [
          {
            type: "value",
            max: function(value) {
              return value.max + 100;
            }
          }
        ],
        series: [
          {
            name: "案件数",
            type: "bar",
            data: [
              // 2.6,
              // 5.9,
              // 9.0,
              // 26.4,
              // 28.7,
              // 70.7,
              // 175.6,
              // 182.2,
              // 48.7,
              // 18.8,
              // 6.0,
              // 2.3
            ],
            markPoint: {
              data: [
                { name: "年最高", value: 182.2, xAxis: 7, yAxis: 183 },
                { name: "年最低", value: 2.3, xAxis: 11, yAxis: 3 }
              ]
            },
            markLine: {
              data: [{ type: "average", name: "平均值" }]
            }
          }
        ],
      }
    };
  },
  mounted() {
    this.getBusinessTotal();




  },
  methods: {

    getBusinessTotal() {
      let params = {};
      searchBusinessTotal(params).then(res => {
        console.log(res)
        for (var key in res.data) {
          //咨询
          this.barCharOption.xAxis[0].data.push(res.data[key].monthstart);
          this.barCharOption.series[0].data.push(res.data[key].commom);
          //投诉
          this.barCharOptionOfComplaints.xAxis[0].data.push(res.data[key].monthstart);
          this.barCharOptionOfComplaints.series[0].data.push(res.data[key].complaint);
          //举报
          this.barCharOptionOfEconomy.xAxis[0].data.push(res.data[key].monthstart);
          this.barCharOptionOfEconomy.series[0].data.push(res.data[key].economy);
          //总数
          this.commom = this.commom + res.data[key].commom
          this.complaint = this.complaint + res.data[key].complaint
          this.economy = this.economy + res.data[key].economy
        }
        console.log(this.barCharOption.xAxis[0].data)
        console.log(this.barCharOption.series[0].data)
        this.drawBarChartOfConsult();
        this.drawBarChartOfComplaints();
        this.drawBarChartOfEconomy();
      });

    },


    drawBarChartOfConsult() {
      const barDom = this.$echarts.init(
        document.getElementById("barCharOfConsult")
      );
      barDom.setOption(this.barCharOption);
    },

    drawBarChartOfComplaints() {
      const complpaintsDom = this.$echarts.init(
        document.getElementById("barCharOfComplpaints")
      );
      complpaintsDom.setOption(this.barCharOptionOfComplaints);
    },
    drawBarChartOfEconomy() {
      const economyDom = this.$echarts.init(
        document.getElementById("barCharOfEconomy")
      );
      economyDom.setOption(this.barCharOptionOfEconomy);
    }
  }
};
</script>
<style lang="less" scoped>
.bg {
  background: #fff;
}
.line {
  height: 1px;
  width: 100%;
  background: #c7c7c7;
}
.barCharOfConsult,
.barCharOfComplpaints,
.barCharOfEconomy {
  width: 100%;
  height: 1000px;
}
.card-demo-flex {
  display: flex;
}
.card-demo-content01 {
  padding: 20px 0;
}
.card-padding {
  padding: 30px;
}
.card-demo-flex > div {
  flex: 1;
  text-align: center;
  font-size: 30px;
}
.card-demo-flex span {
  color: #f74c31;
}
.chart {
  background: #fff;
  margin-top: 40px;
}
</style>
