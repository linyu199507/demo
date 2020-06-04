<template>
    <div>
        <!-- 图表统计 -->
        <el-card class="box-card">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <div class="swiper">
                    <el-carousel trigger="click" :interval="4000" type="card" height="350px">
                        <el-carousel-item v-for="(item,index) in imgArr" :key="index">
                            <img style="width:100%;height:100%;text-algin:center;" :src="item.src" :alt="item.name"/>
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <el-tab-pane label="投诉举报概况" name="first">
                <Complaints v-if="activeName === 'first'"></Complaints>
                </el-tab-pane>
                <el-tab-pane label="总体概况" name="One">
                    <OverallSituation v-if="activeName === 'One'"></OverallSituation>
                </el-tab-pane>
                <el-tab-pane label="举报热点情况" name="Two">
                    <EconomySituation v-if="activeName === 'Two'"></EconomySituation>
                </el-tab-pane>
                <el-tab-pane label="投诉热点情况" name="Three">
                    <ComplaintSituation id="EconomySituation"  @scroll="ScrollMethod" v-if="activeName === 'Three'" ></ComplaintSituation>
                </el-tab-pane>
                <el-tab-pane label="类型统计" name="second" :lazy=true>
                    <TypeStatistics v-if="activeName === 'second'"></TypeStatistics>
                </el-tab-pane>
                <el-tab-pane label="高频词统计" name="third" :lazy=true>
                    <HighFrequencyWordStatistics v-if="activeName === 'third'"></HighFrequencyWordStatistics>
                </el-tab-pane>
                <el-tab-pane label="诉求统计" name="fourth" :lazy=true>
                    <OpinionSnalysis v-if="activeName === 'fourth'"></OpinionSnalysis>
                </el-tab-pane>
                <el-tab-pane label="预警" name="five" :lazy=true>
                    <EarlyWarning v-if="activeName === 'five'"></EarlyWarning>
                </el-tab-pane>
                <el-tab-pane label="投诉热点类别" name="six" :lazy=true>
                    <ComplaintHotSpot v-if="activeName === 'six'"></ComplaintHotSpot>
                </el-tab-pane>
            </el-tabs>
        </el-card>
    </div>
</template>
<script>
    import Complaints from "./components/complaints";
    import EarlyWarning from "./components/earlyWarning";
    import HighFrequencyWordStatistics from "./components/highFrequencyWordStatistics";
    import TypeStatistics from "./components/typeStatistics";
    import OpinionSnalysis from "./components/opinionSnalysis";
    import OverallSituation from "./components/OverallSituation";
    import EconomySituation from "./components/EconomySituation";
    import ComplaintSituation from "./components/ComplaintSituation";
    import ComplaintHotSpot from "./components/ComplaintHotSpot";

    import img1 from "../../assets/banner1.jpg"
    import img2 from "../../assets/banner2.jpg"
    import img3 from "../../assets/banner3.jpg"
    import img4 from "../../assets/banner4.jpg"
    import img5 from "../../assets/banner5.jpg"
    import img6 from "../../assets/banner6.jpg"

    export default {
        data() {
            return {
                activeName: "One",
                imgArr: [
                    {name: 'banner1', src: img1},
                    {name: 'banner2', src: img2},
                    {name: 'banner3', src: img3},
                    {name: 'banner4', src: img4},
                    {name: 'banner5', src: img5},
                    {name: 'banner6', src: img6}
                ]
            };
        },
        components: {
            Complaints,
            EarlyWarning,
            HighFrequencyWordStatistics,
            TypeStatistics,
            OpinionSnalysis,
            OverallSituation,
            EconomySituation,
            ComplaintSituation,
            ComplaintHotSpot
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
           ScrollMethod(){
                let partotop = document.getElementById('EconomySituation').offsetTop;  //父元素到页面顶部距离
                let distance = document.getElementById('totalComplain').offsetTop;  //子元素到页面顶部的距离
                document.getElementById('totalComplain').scrollTop = 0;
                console.log(document.getElementById('EconomySituation').scrollTop=partotop)
                
                // console.log("父元素距离顶部的距离是---" + partotop);
                // console.log("子元素距离顶部的距离是---" + distance);
                // let scrolldis = document.getElementById('EconomySituation').scrollTop=distance - partotop;
                // document.getElementById('EconomySituation').scrollTop('0')
                // document.getElementById('totalComplain').scrollTop(scrolldis)
                // console.log('父容器可滚动距离---'+scrolldis);
            }
        }
    };
</script>
<style lang="scss">
    .pie {
        width: 100%;
        height: 600px;
        margin: 0 auto;
    }
</style>