<template>
    <div>
        <!-- 图表统计 -->
        <el-card class="box-card chart">
            <el-tabs v-model="activeName" @tab-click="tabClick">
                <el-tab-pane name="One">
                    <template slot="label">
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                投诉热点基本信息<i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item><a href="#totalComplain" class="a-style">全区投诉总量及调解</a></el-dropdown-item>
                                <el-dropdown-item><a href="#changeComplain" class="a-style">各单位投诉量动态变化图</a></el-dropdown-item>
                                <el-dropdown-item><a href="#hotComplain" class="a-style">热点投诉性质</a></el-dropdown-item>
                                <el-dropdown-item><a href="#topComplain10" class="a-style">被投诉主体TOP10</a></el-dropdown-item>
                                <el-dropdown-item><a href="#hotComplain10" class="a-style">被投诉热点商圈TOP10</a></el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                    <ComplaintSituationOne v-if="activeName === 'One'"></ComplaintSituationOne>
                </el-tab-pane>


                <el-tab-pane  name="Two">
                    <template slot="label" >
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                家用电器与日用百货类<i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item><a href="#toOne" class="a-style">家用电器类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toTwo" class="a-style">家用电器类产品具体投诉性质</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toThree" class="a-style">家用电器类产品具体投诉品牌</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFour" class="a-style">日用百货类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFire" class="a-style">日用百货类产品具体投诉性质</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toSix" class="a-style">日用百货类产品具体投诉品牌</a></el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                    <ComplaintSituationTwo v-if="activeName === 'Two'"></ComplaintSituationTwo>
                </el-tab-pane>


                <el-tab-pane  name="Three">
                    <template slot="label">
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                房建与食药品类<i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item><a href="#toOnes" class="a-style">装修建材类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toTwos" class="a-style">房屋类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toThrees" class="a-style">食品类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFours" class="a-style">食品类产品具体投诉环节</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFires" class="a-style">药品类产品具体投诉环节</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toSixs" class="a-style">保健品类产品具体投诉环节</a></el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                    <ComplaintSituationThree v-if="activeName === 'Three'"></ComplaintSituationThree>
                </el-tab-pane>


                <el-tab-pane name="Four" :lazy=true>
                    <template slot="label">
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                交通工具与其他类<i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item><a href="#toOneth" class="a-style">交通工具类产品具体投诉品类</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toTwoth" class="a-style">交通工具类产品具体投诉性质</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toThreeth" class="a-style">交通工具类产品具体投诉品牌</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFourth" class="a-style">化妆品类产品具体投诉环节</a></el-dropdown-item>
                                <el-dropdown-item><a href="#toFireth" class="a-style">医疗器械类产品具体投诉环节</a></el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                <!--<el-tab-pane label="交通工具与其他类" name="Four" :lazy=true>-->
                    <ComplaintSituationFour v-if="activeName === 'Four'"></ComplaintSituationFour>
                </el-tab-pane>
            </el-tabs>
        </el-card>
    </div>
</template>
<script>
    import ComplaintSituationOne from "./ComplaintSituations/ComplaintSituationOne";
    import ComplaintSituationTwo from "./ComplaintSituations/ComplaintSituationTwo";
    import ComplaintSituationThree from "./ComplaintSituations/ComplaintSituationThree";
    import ComplaintSituationFour from "./ComplaintSituations/ComplaintSituationFour";

    export default {
        data() {
            return {
                activeName: "One",
                activeIndex: '1'
            };
        },
        components: {
            ComplaintSituationOne,
            ComplaintSituationTwo,
            ComplaintSituationThree,
            ComplaintSituationFour
        },
        methods: {
            tabClick(tab, event) {
                console.log(tab, event);
            },
          
        }
    };
</script>
<style lang="scss">

    .pie {
        width: 100%;
        height: 600px;
        margin: 0 auto;
    }
    a{
        text-decoration: none;
        color:#000;
    }
    .chart {
        .el-dropdown-link {
            height: 100%;
            display: block;
        }
    }
</style>
<style lang="scss" scoped>
  .a-style {
      display: block;
      width: 100%;
  }
</style>