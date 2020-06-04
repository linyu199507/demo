<template>
  <!-- 投诉热点地区 -->
  <div>
    <div class="content">
      <div id="pie" class="pie"></div>
      <div class="lists">
        <x-table :cell-bordered="false" style="background-color:#fff;">
          <thead>
            <tr style="background-color: #F7F7F7">
              <th>地区</th>
              <th>总量</th>
              <th>增长率</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in dateOfList" :key="index">
              <td>{{item.region}}</td>
              <td>{{item.applealtocase}}</td>
              <td>{{Math.round(((item.applealtocase - item.monthsData)/item.monthsData)*100)+'%'}}</td>
            </tr>
          </tbody>
        </x-table>
      </div>
    </div>
  </div>
</template>
<script>

  import { searchComplaintsArea } from '../api/api';

export default {
  data() {
    return {
      dateOfList:null,
      option: {
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
        title: {
          text: "投诉举报地区分析",
          left: "center",
          top: 20,
          textStyle: {
            color: "#000"
          }
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
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: "地区总量",
            type: "pie",
            radius: "65%",
            center: ["50%", "50%"],
            data: [
              // { value: 335, name: "直接访问" },
              // { value: 310, name: "邮件营销" },
              // { value: 274, name: "联盟广告" },
              // { value: 235, name: "视频广告" },
              // { value: 400, name: "搜索引擎" }
            ].sort(function(a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",
            label: {
              color: "rgba(0, 0, 0, 0.5,0,)"
            },
            labelLine: {
              smooth: 0.2,
              length: 0,
              length2: 0
            },
            itemStyle: {
              color: "#5CADFF"
            },

            animationType: "scale",
            animationEasing: "elasticOut",
            animationDelay: function(idx) {
              return Math.random() * 200;
            }
          }
        ]
      }
    };
  },
  mounted() {
    this.drawPie();

    this.getComplaintsArea();
  },
  methods: {

    getComplaintsArea() {
      let params = {};
      searchComplaintsArea(params).then(res => {
        console.log(res)
        this.dateOfList = res.data.all
        console.log(this.dateOfList)
        for (var key in res.data.all) {
          let data = { name: res.data.all[key].region, value: res.data.all[key].applealtocase };
          this.option.series[0].data.push(data);
        }
        this.drawPie();

      });
    },

    drawPie() {
      const pieCharts = this.$echarts.init(document.getElementById("pie"));
      pieCharts.setOption(this.option);
    }
  }
};
</script>
<style lang="less" scoped>
.content,
.pie {
  width: 100%;
  margin: 0 auto;
  height: 800px;
}
</style>
