<template>
<!-- 高频词统计 -->
      <el-row :gutter="25">
        <!-- 词 云 -->
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
              <el-card>
                <!-- <div class="title">
                  <p>词 云</p>
                </div> -->
                <div id="wordCloud" style="height:550px"></div>
              </el-card>
          </el-col>
          <el-col :xs="24" :sm="24" :md="16" :lg="12">
              <el-card>
                <div id="wordFreq" style="height:550px"></div>
              </el-card>
          </el-col>
      </el-row>
</template>
<script>
import 'echarts-wordcloud';
import { getTop100 } from "../../../../src/api/analyse";
export default {
 data() {
    return {
       howWords:[],
    }
  },
  methods:{
    async getTop100(){
       return await getTop100()
    },
    loadWordCloud(){
     //https://github.com/ecomfe/echarts-wordcloud
      let chart = this.$echarts.init(document.getElementById('wordCloud'));
      chart.setOption({
         title:{
            text:"词云图",
        },
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 22, //用来调整词之间的距离
            sizeRange: [13, 50],//用来调整字的大小范围
            rotationRange: [0,0],//用来调整词的旋转方向
            shape: 'cloud',
            left: 'center',
            top: 'center',
            width: '95%',
            height: '80%',
            right: null,
            bottom: null,
            //随机生成字体颜色
            textStyle: {
                normal: {
                    color: function() {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: this.howWords
        }]
      });
    },
    loadWordFreq(){
       let chart = this.$echarts.init(document.getElementById('wordFreq'));
      chart.setOption({
        color: [
          "#67e0e3",
        ],
          title: {
              text: '词 频'
          },
          tooltip: {
              trigger: 'axis',
              axisPointer: {
                  type: 'cross'
              }
          },
          legend: {
              right :'right',
              data: ['次数']
          },
          grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01]
          },
          yAxis: {
              type: 'category',
              data:this.howWords.map(item=>item.name),
              
          },
          series: [
              {
                  name: '次数',
                  type: 'bar',
                  data:this.howWords.map(item=>item.value),
                  label: {
                    show: true,
                    position: 'insideRight'
                  }
              },
          ],
          // 滚动条
          "dataZoom": [{
              type: 'slider',
              show: true,
              filterMode: "empty",
              disabled: false,
              yAxisIndex: 0,
              // top: '25%',
              right: '2%',
              bottom: '8%',
              width: 10,
              start: 80,
              end: 100,
              handleSize: '150%', // 滑动条的 左右2个滑动小块的大小
              handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
              textStyle: {
                  color: "#fff"
              },
              fillerColor: '#37a2da', // 拖动条的颜色
              borderColor: "none",
              backgroundColor: 'rgba(60, 98, 192, 0.302)',
              showDetail: false // 即拖拽时候是否显示详细数值信息 默认true
          }, ]
      });
    }
  },
  async mounted(){
      this.loadingwordCloud=true
    let {data}=await this.getTop100()
    this.howWords=data
      console.log(data)
    //渲染词云
    this.loadWordCloud()
    //渲染词频
    this.loadWordFreq()
  }
}
</script>
<style lang="scss" scoped>
@mixin color {
  color: rgb(4, 7, 12);
}
.title {
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