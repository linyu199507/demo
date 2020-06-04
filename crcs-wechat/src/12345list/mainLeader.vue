<template>
  <div class>
    <!-- 搜索 -->
    <swiper class :list="swiperList" v-model="swiperIndex" @on-index-change="changeIndex"></swiper>
    <!--  -->
    <div class="Statistics">
      <div class="totalTitle">
        <span class="line"></span> 据本月顺德区消费维权一体化平台统计
      </div>
    </div>
    <div class="detailLists">
      <div class="hotStore">
        <!-- <div class="header">据本月顺德区消费维权智能服务平台统计：</div> -->
        <div class="HotStorecontent">
          <div class="hotStoreCharts" id="hotStoreCharts"></div>
        </div>
      </div>
      <div class="otherChartsEntry">
        <div class="hotIndustry" id="hotIndustry"></div>
      </div>
      <div class="changesCharts">
        <div class="trend" id="trend"></div>
      </div>
      <div class="regionClassificationEntry hotspot">
        <flexbox>
          <flexbox-item :span="6">
            <div class="flex-demo">本月投诉举报热点地区对比</div>
          </flexbox-item>
          <flexbox-item @click.native="$router.push({path:'/main/ComplaintHotSpot'})">
            <div class="flex-demo" style="text-align:right">查看详情>></div>
          </flexbox-item>
        </flexbox>
      </div>
      <div class="regionClassificationEntry caseComparison">
        <flexbox>
          <flexbox-item :span="6">
            <div class="flex-demo">本月同类型案件对比</div>
          </flexbox-item>
          <flexbox-item @click.native="$router.push({path:'/main/ComparisonOfSimilarCases'})">
            <div class="flex-demo" style="text-align:right">查看详情>></div>
          </flexbox-item>
        </flexbox>
      </div>
    </div>
  </div>
</template>
<script>
import {
  searchTenBusiness,
  searchComplaintRanking,
  searchHotQuestionChange
} from "../api/api";

export default {
  data() {
    return {
      type: "5",
      swiperList: [
        {
          url: "javascript:",
          img:
            "http://5b0988e595225.cdn.sohucs.com/images/20171128/14f34de92e9e41d9877903c323f7f1d5.jpeg",
          title: "顺峰山"
        },
        {
          url: "javascript:",
          img:
            "http://gss0.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=46a52040271f95caa6f596b6f9277e3e/962bd40735fae6cdaa268e5f0db30f2442a70f79.jpg",
          title: "清晖园"
        },
        {
          url: "javascript:",
          img:
            "http://www.gzmzpx.com/public/uploads/gongc142/20160530143422_853.png", // 404
          title: "李小龙乐园",
          fallbackImg:
            "https://ww1.sinaimg.cn/large/663d3650gy1fq66vw50iwj20ff0aaaci.jpg"
        }
      ],
      list: [
        {
          src:
            "http://img3.imgtn.bdimg.com/it/u=3657692909,1457109578&fm=26&gp=0.jpg",
          fallbackSrc: "http://placeholder.qiniudn.com/60x60/3cc51f/ffffff",
          title: "侵权案件",
          desc:
            "由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。",
          url: "/component/cell"
        },
        {
          src:
            "http://img3.imgtn.bdimg.com/it/u=3657692909,1457109578&fm=26&gp=0.jpg",
          title: "垃圾回收",
          desc:
            "由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。",
          url: {
            path: "/component/radio",
            replace: false
          }
        }
      ],
      hotStoreOptions: {
        color: "#63b1ff",
        title: {
          text: "本月投诉热点商家",
          textStyle: {
            fontSize: 14,
            fontWeight: "normal"
          },
          left: "center"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
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
            data: [],
            nameGap: 0,
            axisTick: {
              alignWithLabel: true
            },
            axisLabel: {
              rotate: "40"
            },
            name: "商家"
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "投诉次数",
            max: function(value) {
              return value.max + 100;
            },
            nameLocation: "end",
            axisLabel: {
              rotate: "40"
            }
          }
        ],
        series: [
          {
            name: "直接访问",
            type: "bar",
            barWidth: "60%",
            data: [],
            label: {
              show: true,
              position: "top",
              color: "#000"
            }
          }
        ]
      },
      HotIndustryOptions: {
        color: "#f98283",
        title: {
          text: "本月投诉热点行业",
          textStyle: {
            fontSize: 14,
            fontWeight: "normal"
          },
          left: "center"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        legend: {
          data: ["投诉量"],
          top: "10%"
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
            nameGap: 0,
            axisLabel: {
              rotate: "40"
            },
            axisTick: {
              alignWithLabel: true
            },
            axisLine: {
              onZero: false
            },
            // axisPointer: {
            //   label: {
            //     formatter: function(params) {
            //       return (
            //         "投诉量 " +
            //         params.value +
            //         (params.seriesData.length
            //           ? "：" + params.seriesData[0].data
            //           : "")
            //       );
            //     }
            //   }
            // },
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "投诉量",
            axisLabel: {
              rotate: "40"
            },
            max: function(value) {
              return value.max + 100;
            }
          }
        ],
        series: [
          {
            // name: "2016 降水量",
            type: "bar",
            barWidth: "60%",
            smooth: true,
            data: [
              // 3.9,
              // 5.9,
              // 11.1,
              // 18.7,
              // 48.3,
              // 69.2,
              // 231.6,
              // 46.6,
              // 55.4,
              // 18.4,
              // 10.3,
              // 0.7
            ],
            label: {
              show: true,
              position: "top",
              color: "#000"
            }
          }
        ]
      },
      swiperIndex: 0,
      results: [],
      changeLineOptions: {
        color: "#fdcc5e",
        title: {
          text: "本月举报投诉热点",
          textStyle: {
            fontSize: 14,
            fontWeight: "normal"
          },
          left: "center"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        grid: {
          left: "10%",
          right: "10%",
          containLabel: true
        },
        xAxis: {
          type: "category",
          name: "热点",
          nameGap: 0,
          data: [],
          axisLabel: {
            rotate: "40"
          },
          axisTick: {
            alignWithLabel: true
          }
        },
        yAxis: {
          type: "value",
          name: "举报投诉量",
          max: function(value) {
            return value.max + 100;
          },
          min: 0,
          axisLabel: {
            rotate: "40"
          }
        },
        series: [
          {
            data: [],
            type: "bar",
            barWidth: "60%",
            label: {
              show: true,
              position: "top",
              color: "#000"
            }
          }
        ]
      },

      search: ""
    };
  },
  mounted() {
    this.getHotQuestionChange();
    this.getTenBusiness();
    this.getComplaintRanking();
  },
  methods: {
    getHotQuestionChange() {
      let params = { type: "wechat" };
      searchHotQuestionChange(params).then(res => {
        console.log(res);
        for (var key in res.data) {
          this.changeLineOptions.xAxis.data.push(res.data[key].region);
          this.changeLineOptions.series[0].data.push(res.data[key].regiontotal);
        }
        // console.log(this.changeLineOptions.xAxis.data);
        // console.log(this.changeLineOptions.series[0].data);
        this.drawChangeLine();
      });
    },

    getComplaintRanking() {
      let params = {};
      searchComplaintRanking(params).then(res => {
        console.log(res);
        for (var key in res.data.month) {
          this.HotIndustryOptions.xAxis[0].data.push(
            res.data.month[key].region
          );
          this.HotIndustryOptions.series[0].data.push(
            res.data.month[key].regiontotal
          );
        }
        console.log(this.HotIndustryOptions.xAxis[0].data);
        console.log(this.HotIndustryOptions.series[0].data);
        this.drawHotIndustry();
      });
    },

    getTenBusiness() {
      let params = {};
      searchTenBusiness(params).then(res => {
        // console.log(res)
        for (var key in res.data.month) {
          this.hotStoreOptions.xAxis[0].data.push(res.data.month[key].region);
          this.hotStoreOptions.series[0].data.push(
            res.data.month[key].regiontotal
          );
        }
        this.hotStoreOptions.xAxis[0].data = this.hotStoreOptions.xAxis[0].data.slice(
          0,
          5
        );
        this.hotStoreOptions.series[0].data = this.hotStoreOptions.series[0].data.slice(
          0,
          5
        );
        this.drawHotStore();
      });
    },

    goTodetailList() {
      this.$router.push({ path: "/main/Pending" });
    },

    changeIndex(index) {
      this.demo01_index = index;
    },
    drawHotIndustry() {
      let hotIndustryDom = this.$echarts.init(
        document.getElementById("hotIndustry")
      );
      hotIndustryDom.setOption(this.HotIndustryOptions);
    },
    drawHotStore() {
      let getHotStoreDom = this.$echarts.init(
        document.getElementById("hotStoreCharts")
      );
      getHotStoreDom.setOption(this.hotStoreOptions);
    },
    drawChangeLine() {
      let getLineDom = this.$echarts.init(document.getElementById("trend"));
      getLineDom.setOption(this.changeLineOptions);
    },
    onImgError(item, $event) {
      console.log(item, $event);
    },

    resultClick(item) {
      window.alert(`you click the result item: ${JSON.stringify(item)}`);
    },
    getResult(val) {
      console.log("on-change", val);
      this.results = val ? getResult(this.value) : [];
    },
    onFocus() {
      console.log("on focus");
    },
    onCancel() {
      console.log("on cancel");
    },
    onSubmit() {
      this.$refs.search.setBlur();
      this.$vux.toast.show({
        type: "text",
        position: "top",
        text: "on submit"
      });
    }
  }
};
</script>
<style lang="less" scoped>
.hotspot {
  background-image: linear-gradient(
    to right,
    rgba(245, 68, 159, 1),
    rgba(250, 126, 126, 1)
  );
}

.caseComparison {
  background-image: linear-gradient(
    to right,
    rgba(250, 126, 126, 1),
    rgba(252, 204, 92, 1)
  );
  margin-bottom: 150px !important;
}
.regionClassificationEntry {
  width: 96%;
  margin: 0 auto;
  height: 120px;
  line-height: 120px;
  padding: 0 10px;
  // background: rgba(76,224,210, 1);
  margin-bottom: 20px;
  border-radius: 10px;
  color: #fff;
}
.mt60 {
  margin-top: 0;
}
.detailLists {
  width: 94%;
  margin: 20px auto;
  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    li {
      width: 48%;
      display: flex;
      justify-content: space-around;
      align-items: center;
      height: 200px;
      background: #e4e7ed;
      border-radius: 10px;
      box-shadow: #eee 5px 5px 0px;
      font-size: 40px;
      margin-bottom: 20px;
      &:nth-child(2n + 1) {
        margin-right: 2%;
      }
      .listTitle {
        color: #666;
        &:hover {
          text-decoration: underline;
        }
      }
      .listImg {
        width: 100px;
        height: 100px;
        img {
          width: 100%;
        }
      }
    }
  }
}
.header {
  background: #fff;
  padding: 20px 0;
  display: flex;
  justify-content: space-between;
  .headerName {
    padding-left: 3%;
    font-size: 30px;
    font-weight: 600;
    color: #000;
  }
  .title {
    padding-right: 3%;
    color: #666;
  }
}
.news {
  margin-bottom: 120px;
}
.header {
  background: #fbf9fe;
  font-size: 32px;
  color: #000;
}
.totalTitle {
  width: 94%;
  margin: 0 auto;
  height: 100px;
  line-height: 100px;
  font-size: 35px;
  padding-left: 10px;
  color: "#000";
  margin-top: 20px;
  border-radius: 10px;
  // background: rgba(92, 173, 255, 1);
  // background-image: linear-gradient(to right, rgba(92, 173, 255, 1), rgb(255, 255, 255));
}
.line {
  display: inline-block;
  width: 5px;
  height: 50px;
  background: #35383b;
  line-height: 300px;
  vertical-align: text-top;
}
</style>
<style lang="less">
.hotStoreCharts,
.hotIndustry,
.trend {
  width: 100%;
  height: 800px;
}
.weui-search-bar__cancel-btn {
  color: rgb(45, 161, 223) !important;
}
.HotStorecontent {
  padding: 20px 0 0 0;
  margin-bottom: 40px;
  background-image: linear-gradient(rgba(92, 173, 255, 1), rgb(255, 255, 255));
  border-radius: 10px;
}
.otherChartsEntry {
  padding: 20px 0 0 0;
  border-radius: 10px;
  margin-bottom: 40px;
  background-image: linear-gradient(rgba(248, 125, 126, 1), rgb(255, 255, 255));
}
.changesCharts {
  padding: 20px 0 0 0;
  border-radius: 10px;
  margin-bottom: 40px;
  background-image: linear-gradient(rgba(252, 202, 87, 1), rgb(255, 255, 255));
}
// 245,78,152
.box1 {
  height: 100px;
  position: relative;
  width: 1490px;
}
.box1-item {
  width: 50%;
  height: 100px;
  background-color: #ccc;
  display: inline-block;
  margin-left: 15px;
  float: left;
  text-align: center;
  line-height: 100px;
}
.box1-item:first-child {
  margin-left: 0;
}
.weui-media-box__title {
  font-size: 16px !important;
}
</style>
