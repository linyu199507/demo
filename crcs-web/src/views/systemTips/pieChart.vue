<!--功能正在开发提示页面-->
<template>
  <div class="row">
    <el-row>
      <!-- 广东地图 -->
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <div id="map" class="radius"></div>
        </div>
      </el-col>
      <!-- 折线图 -->

      <el-col :span="12">
        <div class="grid-content bg-purple">
         <div class="grid-content bg-purple-dark" id="line"></div>
        </div>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-row>
      <el-col :span="24">
         <div class="tableAnaly">
            <el-table
              :data="tableData"
              border
              style="width: 100%"
              :default-sort="{prop: 'date', order: 'descending'}"
            >
              <el-table-column prop="date" label="日期" sortable width="180"></el-table-column>
              <el-table-column prop="name" label="姓名" sortable width="180"></el-table-column>
              <el-table-column prop="address" label="地址" :formatter="formatter"></el-table-column>
            </el-table>
          </div>
      </el-col>
    </el-row>
    <!-- <i class="el-icon-information"></i> -->
    <!-- 功能正在开发中，敬请期待！ -->
  </div>
</template>

<script>
export default {
  name: "BeingDevelop",
  data() {
    return {
    
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ],
      // 折线图数据
      lineOption: {
        title: {
          text: "顺德监管局每月收到的投诉量统计分析",
          left: "center",
          top: 0
        },
        tooltip: {
          //设置tip提示

          trigger: "axis"
        },
        legend: {
          //设置区分（哪条线属于什么）
          top: 20,
          data: ["咨询登记", "消费投诉", "经济违法"]
        },
        color: ["#8AE09F", "#20a0ff", "#ff0000"],
        xAxis: {
          //设置x轴
          type: "category",
          boundaryGap: false, //坐标轴两边不留白
          data: [
            "2018-1-1",
            "2018-2-1",
            "2018-3-1",
            "2018-4-1",
            "2018-5-1",
            "2018-6-1",
            "2018-7-1",
            "2018-8-1",
            "2018-9-1",
            "2018-10-1",
            "2018-11-1",
            "2018-12-1",
            "2019-1-1",
            "2019-2-1",
            "2019-3-1",
            "2019-4-1",
            "2019-5-1",
            "2019-6-1",
            "2019-7-1"
          ],
          name: "月", //X轴 name
          nameTextStyle: {
            //坐标轴名称的文字样式
            color: "#97a8be",
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {
            //坐标轴轴线相关设置。
            lineStyle: {
              color: "#97a8be"
            }
          }
        },
        yAxis: {
          name: "每年收到的投诉量",
          nameTextStyle: {
            color: "#97a8be",
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: "#97a8be"
            }
          },
          type: "value"
        },
        dataZoom: [
          {
            type: "slider",
            show: true,
            xAxisIndex: [0],
            start: 1,
            end: 35
          },
          {
            type: "inside",
            xAxisIndex: [0],
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "咨询登记",
            data: [
              220,
              232,
              201,
              234,
              290,
              230,
              220,
              343,
              20,
              90,
              10,
              32,
              18,
              34,
              87,
              52,
              67,
              40
            ],
            type: "line", // 类型为折线图
            lineStyle: {
              // 线条样式 => 必须使用normal属性
              normal: {
                color: "#8AE09F"
              }
            }
          },
          {
            name: "消费投诉",
            data: [
              120,
              200,
              150,
              80,
              70,
              110,
              130,
              23,
              41,
              53,
              6,
              79,
              10,
              43,
              54,
              60,
              40,
              90,
              50
            ],
            type: "line",
            lineStyle: {
              normal: {
                color: "#20a0ff"
              }
            }
          },
          {
            name: "经济违法",
            data: [
              20,
              20,
              50,
              180,
              270,
              280,
              10,
              20,
              54,
              39,
              3,
              21,
              34,
              59,
              24,
              28,
              19,
              39,
              80,
              90
            ],
            type: "line",
            lineStyle: {
              normal: {
                color: "#ff0000"
              }
            }
          }
        ]
      },
      // 地图数据
      mapOptions:{
        title:{
          text: '广东各市的xx分布情况',
          left:"center"
        },
        tooltip: {
          // trigger: 'item',
          //   formatter: '{c}'
        },
        legend: {
          orient: "vertical",
          left: "center",
          data: [""]
        },
        selectedMode: "single",
        series: [
          {
            name: "广东各市的各项投诉情况",
            type: "map",
            mapType: "guangdong",
            itemStyle: {
              normal: {
                borderColor: "rgba(0, 0, 0, 0.2)"
              },
              emphasis: {
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            },
            showLegendSymbol: true,
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: [
              { name: "广州", value: this.randomData() },
              { name: "深圳", value: this.randomData() },
              { name: "珠海", value: this.randomData() },
              { name: "汕头", value: this.randomData() },
              { name: "佛山", value: this.randomData() },
              { name: "韶关", value: this.randomData() },
              { name: "湛江", value: this.randomData() },
              { name: "肇庆", value: this.randomData() },
              { name: "江门", value: this.randomData() },
              { name: "茂名", value: this.randomData() },
              { name: "惠州", value: this.randomData() },
              { name: "梅州", value: this.randomData() },
              { name: "汕尾", value: this.randomData() },
              { name: "梅州", value: this.randomData() },
              { name: "河源", value: this.randomData() },
              { name: "阳江", value: this.randomData() },
              { name: "清远", value: this.randomData() },
              { name: "东莞", value: this.randomData() },
              { name: "揭阳", value: this.randomData() },
              { name: "云浮", value: this.randomData() },
              { name: "中山", value: this.randomData() }
            ]
          }
        ]
      }
    };
  },
  methods: {
    // 饼形图，各项登记占比
    drawpie() {
      let myChart = this.$echarts.init(document.getElementById("main"));
      myChart.setOption({
        // 标题部分
        title: {
          text: "各项登记占比",
          left: "center",
          top: 20,
          textstyle: {
            color: "#20a0ff"
          }
        },
        // 鼠标经过显示各项占比
        tooltip: {
          trgger: "item",
          formatter: "{b} : {c} ({d}%)"
        },
        // 标记
        legend: {
          orient: "vertical",
          top: 20,
          x: "left",
          data: ["经济违法记录", "登记咨询", "消费投诉"]
        },
        series: [
          {
            name: "各项登记占比",
            type: "pie",
            radius: "55%",
            data: [
              { value: 300, name: "经济违法记录" },
              { value: 274, name: "登记咨询" },
              { value: 310, name: "消费投诉" }
            ],
            title: "各项"
          }
        ]
      });
    },
    // 广东格式的三项占比
    randomData() {
        return Math.round(Math.random() * 500);
    },
    drawMap() {
      let gdMap = this.$echarts.init(document.getElementById("map"));
      gdMap.setOption(this.mapOptions);
    },
    // 绘制折线图
    drawLine() {
      let lineChart = this.$echarts.init(document.getElementById("line"));
      lineChart.setOption(this.lineOption);
    },
    formatter(row, column) {
      return row.address;
    }
  },
  mounted() {
    this.drawMap();
    this.drawLine();
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

.bg-purple-light {
  height: 400px;
}
.bg-purple-dark {
  height: 400px;
}
.tableAnaly {
  margin-top: 100px;
}
</style>
