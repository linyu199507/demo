<template>
    <!-- 投诉举报情况 -->
    <div>
        <!-- 数据对比 -->
        <el-card>
            <div class="businessVolume mt20">
                <div class="businessVolume mt20">
                    <el-row :gutter="20">
                        <!-- 左边 -->
                        <el-col :xs="24" :sm="24" :md="16" :lg="24">
                            <div class="grid-content bg-purple doublePie">
                                <el-card class="box-card dealCart">
                                    <div class="settlementRateTitle mapTitle">
                                        <p>全区举报基本情况</p>
                                    </div>
                                    <el-date-picker
                                            v-model="economysituationData"
                                            type="daterange"
                                            align="center"
                                            placeholder="选择日期范围"
                                            :picker-options="pickerOptions2"
                                    ></el-date-picker>
                                    <el-button type="primary" @click="getEconomySituation()">查询</el-button>
                                    <el-tabs v-model="activeRate" @tab-click="handleClick">
                                        <el-tab-pane
                                                label="举报基本情况"
                                                name="settlementRate"
                                                element-loading-text="拼命加载中"
                                        >
                                            <div
                                                    class="settlementRate"
                                                    id="settlementRate"
                                                    v-if="activeRate==='settlementRate'"
                                                    v-loading="loadingOfsettlementRate"
                                            ></div>
                                        </el-tab-pane>
                                        <!--<el-tab-pane-->
                                                <!--label="罚款金额"-->
                                                <!--name="mediationRate"-->

                                                <!--element-loading-text="拼命加载中"-->
                                        <!--&gt;-->
                                            <!--<div-->
                                                    <!--class="mediationRate"-->
                                                    <!--id="mediationRate"-->
                                                    <!--v-if="activeRate==='mediationRate'"-->
                                                    <!--v-loading="loadingOfmediationRate"-->
                                            <!--&gt;</div>-->
                                        <!--</el-tab-pane>-->
                                    </el-tabs>
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
                                            <p>举报立案具体情况</p>
                                        </div>
                                        <el-date-picker
                                                v-model="illegalData"
                                                type="daterange"
                                                align="center"
                                                placeholder="选择日期范围"
                                                :picker-options="pickerOptions2"
                                        ></el-date-picker>
                                        <el-button type="primary" @click="getIllegalData()">查询</el-button>
                                        <div
                                                class="amountRecovered"
                                                id="amountRecovered"
                                                v-loading="registerloading"
                                        ></div>
                                        <!--</el-tab-pane>-->
                                        <!--<el-tab-pane-->
                                        <!--label="罚款金额"-->
                                        <!--name="mediationRate"-->
                                        <!--v-loading="loadingOfmediationRate"-->
                                        <!--element-loading-text="拼命加载中"-->
                                        <!--&gt;-->
                                        <!--<div-->
                                        <!--class="mediationRate"-->
                                        <!--id="mediationRate"-->
                                        <!--v-if="activeRate==='mediationRate'"-->
                                        <!--&gt;</div>-->
                                        <!--</el-tab-pane>-->
                                        <!--</el-tabs>-->
                                    </el-card>
                                </div>
                            </el-col>


                        </el-row>
                    </div>
                </div>
            </div>
        </el-card>
    </div>


</template>
<script>
    import {
        searchEconomydata, searchEconomyIllegal,

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
                loadingOfsettlementRate: false,
                loadingOfmediationRate: false,
                loadingAmountReacovered: false,
                loadingOfamountRecovered: false,
                registerloading: false,
                listOfDate: {
                    listOne: [],
                    listTwo: [],
                    listThree: [],
                    listFour: [],
                    listFive: [],
                    listSix: []
                },
                startDate: "",
                endDate: "",
                economysituationData: "",
                illegalData: "",
                firstDate: "",
                lastDate: "",
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
                amountRec: "amountRecovered",
                zoomData: "",
                mapDate: "",

                amountReacoveredOption: {
                    color: "#008B8B",
                    title: {
                        text: '涉嫌违法情况',
                        subtext: '数据来自顺德区消费维权智能服务平台'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['总量']
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
                        data: []
                    },
                    series: [
                        {
                            name: '总量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'right'
                            }
                        },
                        // {
                        //     name: '2012年',
                        //     type: 'bar',
                        //     data: [19325, 23438, 31000, 121594, 134141, 681807]
                        // }
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
                        // fillerColor: '#37a2da', // 拖动条的颜色
                        fillerColor: '#008B8B', // 拖动条的颜色
                        borderColor: "none",
                        backgroundColor: 'rgba(60, 98, 192, 0.302)',
                        showDetail: false // 即拖拽时候是否显示详细数值信息 默认true
                    },]
                },

                settlementRateOption: {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['受理举报总数', '已处理数', '已立案数']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '9%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value'
                    },
                    yAxis: {
                        type: 'category',
                        data: []
                    },
                    series: [
                        {
                            name: '受理举报总数',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                position: 'insideRight'
                            },
                            data: []
                        },
                        {
                            name: '已处理数',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                position: 'insideRight'
                            },
                            data: []
                        },
                        {
                            name: '已立案数',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                position: 'insideRight'
                            },
                            data: []
                        },

                    ]
                },
                // 办结率统计
                mediationRateOption: {
                    color: "#ff8f1f",
                    legend: {
                        show: true
                    },
                    tooltip: {},
                    dataset: {
                        source: []
                    },
                    xAxis: {type: "category"},
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
                    series: [{type: "bar", label: {show: true, position: "top"}}]
                },

                //对象排序
                compare: function (property, desc) {
                    return function (a, b) {
                        var value1 = a[property];
                        var value2 = b[property];
                        if (desc == true) {
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
            //

            this.getEconomydata();
            this.getEconomyIllegal();
            // this.drawsettlement();


            //------------------分割线----------------------

            // this.drawDoublePie();
            // this.drawMap();
        },
        methods: {
            //linyu
            getEconomydata() {
                this.settlementRateOption.yAxis.data = []
                this.settlementRateOption.series[0].data = []
                this.settlementRateOption.series[1].data = []
                this.settlementRateOption.series[2].data = []
                let params = {
                    begindate: this.startDate,
                    enddate: this.endDate,
                };
                console.log(params)
                this.loadingOfsettlementRate = true
                searchEconomydata(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.settlementRateOption.yAxis.data.push(res.data.this[key].region)
                        this.settlementRateOption.series[0].data.push(res.data.this[key].accepttotal);
                        this.settlementRateOption.series[1].data.push(res.data.this[key].endtotal);
                        this.settlementRateOption.series[2].data.push(res.data.this[key].recordtotal);
                    }
                    //       this.mediationRateOption.dataset.source.sort(this.compare('办结率'))
                    this.loadingOfsettlementRate = false
                    this.drawsettlement();
                });
            },


            getEconomyIllegal() {
                //赋值为空重置旧图。防止渲染两个图形
                this.amountReacoveredOption.yAxis.data = []
                this.amountReacoveredOption.series[0].data = []
                let params = {
                    firstdate: this.firstDate,
                    lastdate: this.lastDate
                };
                console.log(params)
                this.registerloading = true
                searchEconomyIllegal(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.amountReacoveredOption.yAxis.data.push(res.data.this[key].bustype)
                        this.amountReacoveredOption.series[0].data.push(res.data.this[key].recordtotal);
                    }
                    //       this.mediationRateOption.dataset.source.sort(this.compare('办结率'))
                    this.registerloading = false
                    this.drawEconomyIllegal();

                });
            },


            handleClick(tab, event) {
                if (tab.label === "举报基本情况") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("settlementRate");
                        if (settlementDom) {
                            clearInterval(interval);
                            this.drawsettlement();
                            // this.loadingOfsettlementRate = true;

                        }
                    }, 200);
                } else if (tab.label === "罚款金额") {
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


            // 2、举报立案具体情况
            drawEconomyIllegal() {
                let amountRecovered = this.$echarts.init(
                    document.getElementById("amountRecovered")
                );
                amountRecovered.setOption(this.amountReacoveredOption, true);
                // this.loadingAmountReacovered = false;
            },


            // 办结条形图
            drawsettlement() {
                let drawsettlement = this.$echarts.init(
                    document.getElementById("settlementRate")
                );
                drawsettlement.setOption(this.settlementRateOption, true);
                // this.loadingOfsettlementRate = true;
            },
            // 办结率统计
            drawMediationRate() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("mediationRate")
                );
                mediationRate.setOption(this.mediationRateOption, true);
                // this.loadingOfmediationRate = false;
            },
            getEconomySituation() {
                if (this.economysituationData[0] == null) {
                    this.startDate = this.economysituationData[0]
                    this.endDate = this.economysituationData[1]
                    this.getEconomydata()
                } else {
                    this.startDate = this.$moment(this.economysituationData[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.endDate = this.$moment(this.economysituationData[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getEconomydata()
                }
            },
            getIllegalData() {
                if (this.illegalData[0] == null) {
                    this.firstDate = this.illegalData[0]
                    this.lastDate = this.illegalData[1]
                    this.getEconomyIllegal()
                } else {
                    this.firstDate = this.$moment(this.illegalData[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastDate = this.$moment(this.illegalData[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getEconomyIllegal()
                }
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
        height: 500px;
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