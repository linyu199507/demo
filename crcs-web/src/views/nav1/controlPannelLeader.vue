<template>
    <div class="controlPannel">
        <!-- 第一行卡片 -->
        <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="8">
                <div class="grid-content bg-purple">
                    <el-card :body-style="{ padding: '0px' }">
                        <div slot="header" class="clearfix header">
                            <span class="headerName">待办事项</span>
                            <!-- <el-button style="float: right;" type="primary"></el-button> -->
                            <!-- <el-button style="float: right;" type="primary"></el-button> -->
                            <!-- <el-button style="float: right;" type="primary" class="active"></el-button> -->
                        </div>
                        <div class="listName1 needDeal" v-if="user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                            <ul>
                                <li>
                                    <div class="cardOne">
                                        <div class="cardName">12345待办</div>
                                        <div class="cardNumber" @click="goOutSize()">{{accept}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">受理登记待办</div>
                                        <div class="cardNumber" @click="goToRegister()">{{register}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">12315待办</div>
                                        <div class="cardNumber" @click="goToRegister12315()">{{registerOf12315}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">即将到期</div>
                                        <div class="cardNumber" @click="goToExpected()">{{expected}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">新派工单列表</div>
                                        <div class="cardNumber" @click="goToRegisterNewForm()">{{registerOf12315}}</div>
                                    </div>
                                </li>
                                <li></li>
                            </ul>
                        </div>
                        <div class="listName1 needDeal" v-if="user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'">
                            <ul>
                                <li>
                                    <div class="cardTwo">
                                        <div class="cardName">12345待办</div>
                                        <div class="cardNumber" @click="goOutSize()">{{accept}}</div>
                                    </div>
                                    <div class="cardTwo">
                                        <div class="cardName">12315待办</div>
                                        <div class="cardNumber" @click="goToRegister12315()">{{registerOf12315}}</div>
                                    </div>
                                    <div class="cardTwo">
                                        <div class="cardName">受理登记待办</div>
                                        <div class="cardNumber" @click="goToRegister()">{{register}}</div>
                                    </div>
                                    <div class="cardTwo">
                                        <div class="cardName">新派工单列表</div>
                                        <div class="cardNumber" @click="goToRegisterNewForm()">{{xpgd}}</div>
                                    </div>
                                    <div class="cardTwo">
                                        <div class="cardName">即将到期</div>
                                        <div class="cardNumber" @click="goToExpected()">{{expected}}</div>
                                    </div>
                                </li>
                                <li></li>
                            </ul>
                        </div>
                    </el-card>
                </div>
            </el-col>

            <el-col :xs="24" :sm="12" :md="8" :lg="8">
                <div class="grid-content bg-purple-light">
                    <el-card :body-style="{ padding: '0px' }">
                        <div slot="header" class="clearfix header">
                            <span class="headerName">已办事项</span>
                        </div>
                        <div class="listName1">
                            <ul>
                                <li>
                                    <div class="cardOne">
                                        <div class="cardName">12345已办</div>
                                        <div class="cardNumber" @click="$router.push('/tjfx/zhcx/cqjlcx')">
                                            {{dealRegister}}
                                        </div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">受理登记已办</div>
                                        <div class="cardNumber" @click="$router.push('/tjfx/zhcx/sldjcx')">
                                            {{dealComplain}}
                                        </div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">12315已办</div>
                                        <div class="cardNumber" @click="$router.push('/tjfx/zhcx/wbjlcx')">
                                            {{dealEconomy}}
                                        </div>
                                    </div>
                                </li>
                                <li></li>
                            </ul>
                        </div>
                    </el-card>
                </div>
            </el-col>

            <el-col :xs="24" :sm="12" :md="8" :lg="8">
                <div class="grid-content bg-purple-light">
                    <el-card :body-style="{ padding: '0px' }">
                        <div slot="header" class="clearfix header">
                            <span class="headerName">工单事项</span>
                        </div>
                        <div class="listName1">
                            <ul>
                                <li>
                                    <div class="cardOne">
                                        <div class="cardName">工单红牌</div>
                                        <div class="cardNumber">{{redCard}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">工单黄牌</div>
                                        <div class="cardNumber">{{yellowCard}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">工单预警</div>
                                        <div class="cardNumber">{{warning}}</div>
                                    </div>
                                    <div class="cardOne">
                                        <div class="cardName">回填失败</div>
                                        <div class="cardNumber" @click="findBusState">{{BusState}}</div>
                                    </div>
                                </li>
                                <li></li>
                            </ul>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
        <!-- 第二行卡片 -->
        <el-row :gutter="20">
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

            <!-- 右边 -->
            <el-col :xs="24" :sm="24" :md="8" :lg="8">
                <div class="grid-content bg-purple" style="background:#fff">
                    <el-card class="box-card enterCard">
                        <div class="enterOfSearch">
                            <div class="enterName">
                                <span class="iconfont el-icon-erpchazhaobiaodanliebiao"></span> 工单查询入口
                            </div>
                            <div class="enterArrow" @click="searchFile()">
                                <span class="iconfont el-icon-erpright"></span>
                            </div>
                        </div>
                    </el-card>
                    <el-card class="box-card enterCard">
                        <div class="enterOfSearch">
                            <div class="enterName">
                                <span class="iconfont el-icon-erpshouye"></span>
                                <!--<span class="iconfont el-icon-erpshezhi"></span>-->
                                大屏展示入口
                            </div>
                            <div class="enterArrow" @click="gotoSystem('http://datav.aliyuncs.com/share/3d7423fdf4f0dde3222494c913c59bbd')">
                                <span class="iconfont el-icon-erpright"></span>
                            </div>
                        </div>
                    </el-card>
                    <el-card class="box-card enterCard hotTradeCard">
                        <div class="settlementRateTitle hotTradeTitle">
                            <p>投诉热点商家Top10</p>
                        </div>
                        <div class="searchTopTenData">
                            <el-date-picker
                                    v-model="searchTopTenData"
                                    type="daterange"
                                    align="right"
                                    placeholder="选择日期范围"
                                    @change="getTopTenData()"
                                    :picker-options="pickerOptions2"
                            ></el-date-picker>
                            <el-button type="primary" @click="getSearchTopTenData()">查询</el-button>
                        </div>
                        <div class="hotTrade" v-loading="loading">
                            <ul v-if="hotTradeList.length != 0">
                                <li v-for="(item,index) in hotTradeList" :key="index">
                                    <span class="top10">{{index + 1}}</span>
                                    {{item}}
                                </li>
                            </ul>
                            <div class="nullPic" v-else>
                                <div class="iconfont el-icon-erpzanwushuju"></div>
                                <div class="null">暂无数据</div>
                            </div>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
        <!-- 第三行卡片 -->
        <el-row :gutter="20">
            <!-- 左边 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="16">
                <div class="theThird">
                    <el-card class="box-card histogramCardOne">
                        <div class="zoomDataChart">
                            <el-date-picker
                                    v-model="histogramDate"
                                    type="daterange"
                                    align="right"
                                    placeholder="选择日期范围"
                                    @change="getround()"
                                    :picker-options="pickerOptions2"
                            ></el-date-picker>
                            <el-button type="primary" @click="getHistogramDate()">查询</el-button>
                        </div>
                        <div class="histogram" id="histogram"></div>
                    </el-card>
                </div>
            </el-col>
            <!-- 右边 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="8">
                <div class="theThird">
                    <el-card class="box-card settlementRateCard">
                        <div class="settlementRateTitle">
                            <p>办结率统计</p>
                        </div>
                        <div class="settlementRate">
                            <ul>
                                <li>
                                    <div class="settleRate" id="settleRateRegister"></div>
                                    <div class="registerName">咨询登记</div>
                                </li>
                                <li>
                                    <div class="settleRate" id="settleRateComplain"></div>

                                    <div class="registerName">消费投诉</div>
                                </li>
                                <li>
                                    <div class="settleRate" id="settleRateEconomy"></div>
                                    <div class="registerName">经济违法</div>
                                </li>
                            </ul>
                        </div>
                    </el-card>
                    <el-card class="box-card rateCard">
                        <div class="dealListTitle">
                            <p>在办统计</p>
                        </div>
                        <div class="dealList">
                            <ul>
                                <li>
                                    <div class="dealType">
                                        <div class="dealTitle">咨询登记</div>
                                        <div class="dealNumber">{{dealregisterNumber}}</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="dealType">
                                        <div class="dealTitle">消费投诉</div>
                                        <div class="dealNumber">{{dealComplainNumber}}</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="dealType">
                                        <div class="dealTitle">经济违法</div>
                                        <div class="dealNumber">{{dealEconomyNumber}}</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
        <!-- 第四行 -->
        <el-row :gutter="20">
            <!-- 左边 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="12">
                <div class="theThird">
                    <el-card class="box-card histogramCard">
                        <div class="settlementRateTitle mapTitle">
                            <p>投诉热点地区</p>
                        </div>
                        <div class="searchMapDate">
                            <el-date-picker
                                    v-model="mapDate"
                                    type="daterange"
                                    align="right"
                                    placeholder="选择日期范围"
                                    @change="getMap()"
                                    :picker-options="pickerOptions2"
                            ></el-date-picker>
                            <el-button type="primary" @click="getMapDate()">查询</el-button>
                        </div>
                        <div class="map" id="map"></div>
                    </el-card>
                </div>
            </el-col>
            <!-- 中间 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="12">
                <div class="theThird">
                    <el-card class="box-card histogramCard">
                        <div class="settlementRateTitle hotTradeTitle">
                            <p>投诉热点行业Top5</p>
                        </div>
                        <div class="searchHotDate">
                            <el-date-picker
                                    v-model="mapDate"
                                    type="daterange"
                                    align="right"
                                    placeholder="选择日期范围"
                                    :picker-options="pickerOptions2"
                            ></el-date-picker>
                            <el-button type="primary" @click="getMapDate()">查询</el-button>
                        </div>
                        <div class="hotTrade" id="hotTrade"></div>
                    </el-card>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    import moment from "moment";
    import shundeMapJson from "../../assets/json/shunde.json";
    import {
        totalFirstData,
        endListTasklist,
        getPercentage,
        getTrafficCount,
        dealListTotal,
        cardsCount,
        getTenBusiness,
        getHotZoom,
        getDealNum,
        searchRegionData,
        searchComplaintRanking,
        searchTenBusiness,
        searchComplaintsArea,
        searchBackOrder,
        searchBackOrderOfHome,
        sldjwarnData
    } from "../../api/api";
    import {getFlag, setFlag} from "../../utils/auth";

    export default {
        data() {
            return {
                isfeedbackCount:0,
                isregisterCount:0,
                isfinalbackCount:0,
                user: JSON.parse(localStorage.getItem("user")),
                regionTotalOne:null,
                regionTotalTwo:null,
                total: 0,
                // 在办统计数据
                dealregisterNumber: 0,
                dealComplainNumber: 0,
                dealEconomyNumber: 0,
                loading: true,
                currentDate: new Date(),
                redister201801: [],
                redister201802: [],
                redister201803: [],
                redister201804: [],
                redister201805: [],
                redister201806: [],
                redister201807: [],
                redister201808: [],
                redister201809: [],
                redister201810: [],
                redister201811: [],
                redister201812: [],
                // 柱状条形图的数据
                histogramData: [],
                // 第一行第一张卡片
                notAccept: 0,
                accept: 0,
                register: 0,
                registerOf12315: 0,
                expected: 0,
                sldjcount:0,
                feedbackcount:0,
                xpgd:0,
                // 第一行第二张卡片
                dealRegister: 0,
                dealComplain: 0,
                dealEconomy: 0,
                // 第一行第三张卡片
                redCard: 0,
                yellowCard: 0,
                warning: 0,
                BusState: 0,
                // 办结率的数据
                economyNumber: 0,
                complainNumber: 0,
                registerNumber: 0,
                // 获取代办书来给你
                dealData: 0,
                //  第二行卡片
                searchTopTenData: "",
                // 条形图
                histogramDate: "",
                // 点击筛选地图数据
                mapDate: "",
                value7: "",
                zoomData: "",
                // 被投诉前十的热点商家
                hotTradeList: [],
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
                // 柱状折线图
                histogramOption: {
                    title: {
                        top: "0",
                        text: "业务量统计",
                        textStyle: {
                            // color: "#333"
                            // color: "rgb()",
                            fontSize: "20"
                        }
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "cross",
                            crossStyle: {
                                color: "#999"
                            }
                        }
                    },
                    grid: {
                        left: "10%",
                        top: "30%"
                    },
                    toolbox: {
                        feature: {
                            magicType: {show: true, type: ["line", "bar"]},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        top: "20%",
                        data: ["处理量", "趋势"]
                    },
                    xAxis: [
                        {
                            type: "category",
                            data: [
                                "星期一",
                                "星期二",
                                "星期三",
                                "星期四",
                                "星期五",
                                "星期六",
                                "星期天"
                            ],
                            axisPointer: {
                                type: "shadow"
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: "value",
                            name: "处理量",
                            min: 0,
                            max: 5000,
                            interval: 500,
                            axisLabel: {
                                formatter: "{value} 单"
                            }
                        }
                        // },
                        // {
                        //   type: "value",
                        //   name: "趋势",
                        //   min: 0,
                        //   max: 5000,
                        //   interval: 500,
                        //   axisLabel: {
                        //     formatter: "{value} 单"
                        //   }
                        // }
                    ],
                    series: [
                        {
                            name: "处理量",
                            type: "bar",
                            color: "#1F8FFF",
                            data: []
                        }
                        // {
                        //   name: "趋势",
                        //   type: "line",
                        //   yAxisIndex: 1,
                        //   color: "rgb(255, 179, 102)",
                        //   data: [
                        //     2.0,
                        //     2.2,
                        //     3.3,
                        //     4.5,
                        //     6.3,
                        //     10.2,
                        //     20.3,
                        //     23.4,
                        //     23.0,
                        //     16.5,
                        //     12.0,
                        //     6.2
                        //   ]
                        // }
                    ]
                },
                // 条形图
                hotTradeOption: {
                    title: {},
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "shadow"
                        }
                    },
                    legend: {
                        data: ["总量"],
                        top: "6%"
                    },
                    grid: {
                        top: "13%",
                        left: "3%",
                        right: "8%",
                        bottom: "2%",
                        containLabel: true
                    },
                    xAxis: {
                        type: "value",
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: "category",
                        data: []//后台获取行业
                    },
                    series:
                        // {
                        //   name: "2018年",
                        //   type: "bar",
                        //   data: [18203, 23489, 29034, 104970, 131744],
                        //   color: "rgb(255, 179, 102)"
                        // },
                        {
                            name: "总量",
                            type: "bar",
                            data: [],//后台获取数据量
                            color: "#1F8FFF"
                        }

                },
                //顺德地图数据
                shundeMapOption: {
                    tooltip: {
                        trigger: "item",
                        formatter: "{b}<br/>{c} (单)"
                    },

                    visualMap: {
                        min: 10,
                        max: 2000,
                        // max: function(value) {
                        //     return value.max + 20;
                        // },
                        text: ["High", "Low"],
                        left: "right",
                        realtime: false,
                        calculable: true,
                        inRange: {
                            color: ["#e2f2f8", "#5CADFF", "#ffac38", "rgb(255, 179, 102)"]
                        }
                    },
                    series: [
                        {
                            name: "shunde",
                            type: "map",
                            mapType: "shunde",
                            top: "10%",
                            aspectScale: 0.85, //地图长度比
                            label: {
                                normal: {
                                    show: true,
                                    textStyle: {
                                        color: "#fff"
                                    }
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        color: "#333"
                                    }
                                }
                            },
                            data: []//本周投诉热点地区--数据绑定位置
                        }
                    ]
                },

                // 经济违法办结率数据
                economyOption: {
                    title: {
                        show: true,
                        text: "",
                        x: "center",
                        y: "center",
                        textStyle: {
                            fontSize: "15",
                            color: "#fff",
                            fontWeight: "normal"
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{d}%",
                        show: false
                    },
                    legend: {
                        orient: "vertical",
                        x: "left",
                        show: false
                    },
                    series: []
                },

                // 咨询登记办结率数据
                registerOption: {
                    title: {
                        show: true,
                        text: "",
                        x: "center",
                        y: "center",
                        textStyle: {
                            fontSize: "15",
                            color: "#fff",
                            fontWeight: "normal"
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{d}%",
                        show: false
                    },
                    legend: {
                        orient: "vertical",
                        x: "left",
                        show: false
                    },
                    series: []
                },

                // 消费投诉办结率数据
                complainOption: {
                    title: {
                        show: true,
                        text: "",
                        x: "center",
                        y: "center",
                        textStyle: {
                            fontSize: "15",
                            color: "#fff",
                            fontWeight: "normal"
                        }
                    },
                    tooltip: {
                        trigger: "item",
                        formatter: "{d}%",
                        show: false
                    },
                    legend: {
                        orient: "vertical",
                        x: "left",
                        show: false
                    },
                    series: []
                },

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
                }
            };
        },
        methods: {
            //回填失败工单
            getBackFillNumber(){
                let params = {};
                searchBackOrderOfHome(params).then(res => {
                    console.log(res)
                    this.BusState = res.data
                });
            },

            //投诉热点地区
            getComplaintsArea(){
                let params = {};
                searchComplaintsArea(params).then(res => {
                    console.log(res)
                    for(var key in res.data.all ){
                        let data = { name: res.data.all[key].region , value: res.data.all[key].regiontotal }
                        this.shundeMapOption.series[0].data.push(data)
                    }
                    console.log(this.shundeMapOption.series[0].data)
                    this.drawMap();
                });
            },
            //投诉热点前十商家
            getBusinessTen(){
                let params = {};
                searchTenBusiness(params).then(res => {
                    for (var i in res.data.all) {
                        this.hotTradeList.push(res.data.all[i].region)
                    }
                    this.loading = false;
                });
            },
            getComplaintRanking(){
                let params = {};
                searchComplaintRanking(params).then(res => {
                    for (var i in res.data.all) {
                        this.hotTradeOption.yAxis.data.push(res.data.all[i].region)
                        this.hotTradeOption.series.data.push(res.data.all[i].regiontotal)
                    }
                    // console.log(this.hotTradeOption.yAxis.data)
                    // console.log(this.hotTradeOption.series.data)
                    this.drawhotTrade();
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


            handleClick(tab, event) {
                // console.log(tab, event);
            },
            // 点击获取饼形图数据的事件
            getDoublepieOption() {
                let firstDate = this.crtTimeFtt(this.searchTopTenData[0]);
                let secondDate = this.crtTimeFtt(this.searchTopTenData[1]);

                let params = {
                    begindate: firstDate,
                    enddate: secondDate
                };
                // getHotZoom(params).then(() => {
                //     if (res.data.code == 0) {
                //         if (res.data.data != null || res.data.data != undefined) {
                //             getHotZoom(params).then(res => {
                //                 if (res.data.code === 0) {
                //                     if (res.data.data != null || res.data.data != undefined) {
                //                         this.shundeMapOption.series[0].data[0] = {
                //                             name: "伦教",
                //                             value: res.data.data.伦教[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[1] = {
                //                             name: "乐从",
                //                             value: res.data.data.乐从[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[2] = {
                //                             name: "勒流",
                //                             value: res.data.data.勒流[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[3] = {
                //                             name: "北滘镇",
                //                             value: res.data.data.北滘[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[4] = {
                //                             name: "均安",
                //                             value: res.data.data.均安[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[5] = {
                //                             name: "大良",
                //                             value: res.data.data.大良[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[6] = {
                //                             name: "容桂",
                //                             value: res.data.data.容桂[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[7] = {
                //                             name: "杏坛",
                //                             value: res.data.data.杏坛[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[8] = {
                //                             name: "陈村",
                //                             value: res.data.data.陈村[2].count
                //                         };
                //                         this.shundeMapOption.series[0].data[9] = {
                //                             name: "龙江",
                //                             value: res.data.data.龙江[2].count
                //                         };
                //                         this.drawMap();
                //                     }
                //                 } else {
                //                     alert("投诉热点地区" + res.data.msg);
                //                 }
                //             });
                //         }
                //     }
                // });
            },
            getround() {
                this.getHistogramDate();
            },
            // 点击获取条形图数据的事件
            getHistogramDate() {
                // var myDate = new Date();
                if (this.histogramDate[0] == null && this.histogramDate[1] == null) {
                    this.getTrafficCountDate();
                } else {
                    var currentDate = moment(this.histogramDate[0]).format("YYYY-MM-DD");
                    var endDate = moment(this.histogramDate[1]).format("YYYY-MM-DD");
                    this.getTrafficCountDate(currentDate, endDate);
                }
            },
            getDealNum() {
                let user = JSON.parse(localStorage.getItem("user"));
                let params = {user};
                getDealNum(params).then((res) => {
                    if (res.code === 0) {
                        this.dealRegister = res.data.bmfwybdb;
                        this.dealComplain = res.data.sldjybdb;
                        this.dealEconomy = res.data.ywclybdb;
                    } else {
                        // console.log(res.msg)
                    }
                })
            },
            drawDoublePie() {
                let drawChartOne = this.$echarts.init(
                    document.getElementById("chartOne")
                );
                drawChartOne.setOption(this.doublepieOption);
            },
            // 点击获取地图事件
            getMapDate() {
            },
            // 格式化日期
            crtTimeFtt(val) {
                if (val != null) {
                    var date = new Date(val);
                    var day = date.getDate() > 9 ? +date.getDate : "0" + date.getDate;
                    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + day;
                }
            },
            getTopTenData() {
                // this.getSearchTopTenData();
            },
            // 点击获取条形柱的事件
            getSearchTopTenData() {
                if (
                    this.searchTopTenData[0] == null &&
                    this.searchTopTenData[1] == null
                ) {
                    let myDate = new Date();
                    let currentDate = moment(myDate).format("YYYY-MM-DD");
                    let begin = this.getWeekDay(currentDate)[0];
                    let end = this.getWeekDay(currentDate)[
                    this.getWeekDay(currentDate).length - 1
                        ];
                    this.getSearchTopTenData1(begin, end);
                } else {
                    let firstDate = moment(this.searchTopTenData[0]).format("YYYY-MM-DD");
                    let endDate = moment(this.searchTopTenData[2]).format("YYYY-MM-DD");
                    this.getSearchTopTenData1(firstDate, endDate);
                }
            },
            // getSearchTopTenData1(firstDate, secondDate) {
            //     let params = {
            //         begindate: firstDate,
            //         enddate: secondDate
            //     };
            //     getTenBusiness(params).then(res => {
            //         if (res.data.code === 0) {
            //             if (res.data.data != null || res.data.data != undefined) {
            //                 this.hotTradeList = res.data.data;
            //                 this.hotTradeList.splice(9, 1);
            //                 this.loading = false;
            //             }
            //         } else {
            //             alert("投诉量前十的商家" + res.data.msg);
            //         }
            //     });
            // },
            // 柱状折线图
            drawHistogram() {
                let histogramChart = this.$echarts.init(
                    document.getElementById("histogram")
                );
                histogramChart.setOption(this.histogramOption);
            },
            // 条形图
            drawhotTrade() {
                let hotTrade = this.$echarts.init(document.getElementById("hotTrade"));
                hotTrade.setOption(this.hotTradeOption);
            },
            // 地图
            drawMap() {
                let shundeMap = this.$echarts.init(document.getElementById("map"));

                this.$echarts.registerMap("shunde", shundeMapJson, {});

                shundeMap.setOption(this.shundeMapOption);
            },
            goToDeal() {
                this.$router.push({path: "/wddb/dfpyw"});
            },
            // 12345
            goOutSize() {
                this.$router.push({path: "/wddb/12345db"});
            },
            goToRegister() {
                this.$router.push({path: "/wddb/bxtdb"});
            },
            goToRegister12315() {
                this.$router.push({path: "/wddb/12315db"});
            },
            goToExpected() {
                this.$router.push({path: "/tjfx/zhcx/expected"});
            },
            goToRegisterNewForm() {
                this.$router.push({path: "/wddb/xfpgd"});
            },
            searchFile() {
                this.$router.push({path: "/tjfx/zhcx/ywjlcx"});
            },
            gotoSystem(url) {
                // window.location.href = url

                this.$alert('您无此权限，请联系管理员', '提示', {
                    confirmButtonText: '确定',
                    // callback: action => {
                    //     this.$message({
                    //         type: 'info',
                    //         message: `action: ${ action }`
                    //     });
                    // }
                });
                // this.$router.push({path: "/xtgl/zjjg"});
            },
            findBusState() {
                if(this.user.primaryKey == '991804e6-2595-474d-b345-e93aab3729c2'){
                    this.$router.push({path: "/wddb/busState"});
                }
            },
            // 红黄牌
            getCardsCount() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                // console.log(user);
                let params = {user};
                cardsCount(params).then(res => {
                    if (res.code == 0) {
                        this.redCard = res.data.redCard;
                        this.yellowCard = res.data.yellowCard;
                        this.warning = res.data.warning;
                    } else {
                        // console.log(res.msg);
                    }
                });
            },
            // 获取前十的商家
            // getTenBusiness() {
            //     var myDate = new Date();
            //     var currentDate = moment(myDate).format("YYYY-MM-DD");
            //     var begin = this.getWeekDay(currentDate)[0];
            //     var end = this.getWeekDay(currentDate)[
            //     this.getWeekDay(currentDate).length - 1
            //         ];
            //     this.getTenBusiness1(begin, end);
            // },
            // getTenBusiness1(begin, end) {
            //     let params = {
            //         begindate: begin,
            //         enddate: end
            //     };
            //     getTenBusiness(params).then(res => {
            //         if (res.data.code === 0) {
            //             if (res.data.data != null || res.data.data != undefined) {
            //                 this.hotTradeList = res.data.data;
            //                 this.hotTradeList.splice(9, 1);
            //             }
            //         } else {
            //             alert(res.data.msg);
            //         }
            //     });
            // },
            //获取投诉热点地区
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
                // getHotZoom(params).then(res => {
                //     if (res.data.code === 0) {
                //         if (res.data.data != null || res.data.data != undefined) {
                //             // console.log(res);
                //             this.shundeMapOption.series[0].data[0] = {
                //                 name: "伦教",
                //                 value: res.data.data.伦教[2].count
                //             };
                //             this.shundeMapOption.series[0].data[1] = {
                //                 name: "乐从",
                //                 value: res.data.data.乐从[2].count
                //             };
                //             this.shundeMapOption.series[0].data[2] = {
                //                 name: "勒流",
                //                 value: res.data.data.勒流[2].count
                //             };
                //             this.shundeMapOption.series[0].data[3] = {
                //                 name: "北滘",
                //                 value: res.data.data.北滘[2].count
                //             };
                //             this.shundeMapOption.series[0].data[4] = {
                //                 name: "均安",
                //                 value: res.data.data.均安[2].count
                //             };
                //             this.shundeMapOption.series[0].data[5] = {
                //                 name: "大良",
                //                 value: res.data.data.大良[2].count
                //             };
                //             this.shundeMapOption.series[0].data[6] = {
                //                 name: "容桂",
                //                 value: res.data.data.容桂[2].count
                //             };
                //             this.shundeMapOption.series[0].data[7] = {
                //                 name: "杏坛",
                //                 value: res.data.data.杏坛[2].count
                //             };
                //             this.shundeMapOption.series[0].data[8] = {
                //                 name: "陈村",
                //                 value: res.data.data.陈村[2].count
                //             };
                //             this.shundeMapOption.series[0].data[9] = {
                //                 name: "龙江",
                //                 value: res.data.data.龙江[2].count
                //             };
                //             this.drawMap();
                //         }
                //     } else {
                //         alert("投诉热点地区" + res.data.msg);
                //     }
                // });
            },
            //工单已办
            goToDealList() {
                this.$router.push({path: "/controlpannelLeader/gdyb"});
            },
            goToSearch() {
                this.$router.push({path: "/tjfx/zhcx/ywjlcx"});
            },
            goToSetUp() {
                this.$router.push({path: "/xtgl/zjjg"});
            },
            // 经济违法办结率
            economyRate() {
                let economyRate = this.$echarts.init(
                    document.getElementById("settleRateEconomy")
                );
                economyRate.setOption(this.economyOption);
            },
            // 咨询登记办结率
            registerRate() {
                let registerRate = this.$echarts.init(
                    document.getElementById("settleRateRegister")
                );
                registerRate.setOption(this.registerOption);
            },
            // 消费投诉办结率
            complainRate() {
                let complainRate = this.$echarts.init(
                    document.getElementById("settleRateComplain")
                );
                complainRate.setOption(this.complainOption);
            },

            //工单已办记录数
            // endListSize() {
            //   var user = localStorage.getItem("user");
            //   user = JSON.parse(user);
            //   let params = { page: "1", total: "1", size: "1", nextnodeid: "end" };
            //   endListTasklist(params).then(res => {
            //     if (res.code == 0) {
            //       this.total = res.data.total; //总数量(已办结工单)
            //       //  console.log("工单已办总数="+this.total)
            //     }
            //   });
            // },
            // 获取领导桌面第一张卡片
            getDealData() {
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                let params = {user};
                totalFirstData(params).then(res => {
                    if (res.data.code === 0) {
                        if (res.data.data != null || res.data.data != undefined) {
                            this.notAccept = res.data.data.wsldjdb;
                            this.accept = res.data.data.bmfwdb;
                            this.register = res.data.data.sldjdb;
                            this.registerOf12315 = res.data.data.ywcldb;
                            this.xpgd = res.data.data.xpgd;
                            this.expected = res.data.data.expectedCount;
                            this.sldjcount=res.data.data.sldjendCount;
                            this.feedbackcount=res.data.data.feedbackCount;
                            console.log(this.feedbackcount)
                            //console.log(user)
                            //console.log(user.aicPermissions.system)
                            //console.log(user.aicPermissions.ywclBusiness)
                            if(user.aicPermissions.system != null && user.aicPermissions.ywclBusiness != null){
                                //如果有到期预警待办工单，则提醒用户
                                if (getFlag() == 'true' && this.expected > 0 && this.BusState > 0) {
                                    const h = this.$createElement;
                                    setFlag('false');
                                    this.$msgbox({
                                        title: '提示',
                                        message: h('p', null, [
                                                h('span', {style: 'color: red'}, '预警：'),
                                                h('span', null, '您有' + this.expected + '条12小时内到期的工单尚未处理，为避免绩效考核扣分，请务必在工单到期前30分钟处理！'),
                                                h('p', null),
                                                h('span', {style: 'color: red'}, '回填：'),
                                                h('span', null, '您有' + this.BusState + '条工单回填失败，请联系系统管理员进行处理！'),

                                            ],
                                        ),
                                        showCancelButton: true,
                                        confirmButtonText: '现在处理',
                                        cancelButtonText: '稍后处理',
                                        type: 'warning'
                                    }).then(() => {
                                        this.$router.push({path: "/tjfx/zhcx/expected"});
                                    });
                                }
                                if (getFlag() == 'true' && this.expected === 0 && this.BusState > 0) {
                                    const h = this.$createElement;
                                    setFlag('false');
                                    this.$msgbox({
                                        title: '提示',
                                        message: h('p', null, [
                                                h('span', {style: 'color: red'}, '回填：'),
                                                h('span', null, '您有' + this.BusState + '条工单回填失败，请联系系统管理员进行处理！'),

                                            ],
                                        ),
                                        showCancelButton: true,
                                        confirmButtonText: '查看',
                                        cancelButtonText: '确定',
                                        type: 'warning'
                                    }).then(() => {
                                        this.$router.push({path: "/wddb/busState"});
                                    });
                                }
                                if (getFlag() == 'true' && this.expected > 0 && this.BusState === 0) {
                                    const h = this.$createElement;
                                    setFlag('false');
                                    this.$msgbox({
                                        title: '提示',
                                        message: h('p', null, [
                                                h('span', {style: 'color: red'}, '预警：'),
                                                h('span', null, '您有' + this.expected + '条12小时内到期的工单尚未处理，为避免绩效考核扣分，请务必在工单到期前30分钟处理！'),

                                            ],
                                        ),
                                        showCancelButton: true,
                                        confirmButtonText: '现在处理',
                                        cancelButtonText: '稍后处理',
                                        type: 'warning'
                                    }).then(() => {
                                        this.$router.push({path: "/tjfx/zhcx/expected"});
                                    });
                                }
                            }
                            //msx 受理登记预警
                            if((this.sldjcount>0 || this.feedbackcount>0) && this.user.primaryKey != '80b309e6-bb1d-49ba-81e8-f87c542e4f2c'){
                                let data = ['你有'+this.sldjcount+'条即将到期工单未进行立案',this.feedbackcount+'条即将到期工单未进行初步反馈'];
                                //2.新建newDatas数组
                                let newDatas = [];
                                const h = this.$createElement;
                                //3.通过循环data数组，调用h方法，将每项值传给h,h('标签名',样式,具体内容)
                                for(let i in data){
                                    //4.将data数据push进newDatas数组中
                                    newDatas.push(h('p',null,data[i]));
                                };


                                console.log("hjkl;")
                                // const h = this.$createElement;
                                this.$msgbox({
                                    title: '受理登记预警',
                                    // message: h('p', null, [
                                    //     h('span', null, '你有'),
                                    //     h('span', { style: 'color: red' }, this.sldjcount),
                                    //     h('span', null, "条即将到期工单未进行立案"),
                                    //     h('span',null, "\\n"),
                                    //     h('span', { style: 'color: red' }, this.feedbackcount),
                                    //     h('span', null, "条即将到期工单未进行初步反馈\\n"),
                                    // ]),
                                    message:h('div',null, newDatas),
                                    showCancelButton: true,
                                    confirmButtonText: '去待办查看',
                                    cancelButtonText: '取消',
                                }).then(action => {
                                    this.$router.push({path: "/wddb/bxtdb"});
                                });
                            }
                        }
                    }
                });
            },
            getTrafficCountDate() {
                let myDate = new Date();
                let currentDate = moment(myDate).format("YYYY-MM-DD");
                let begin = this.getWeekDay(currentDate)[0];
                let end = this.getWeekDay(currentDate)[
                this.getWeekDay(currentDate).length - 1
                    ];
                //   let firstDate = this.crtTimeFtt(this.searchTopTenData[0]);
                // let secondDate = this.crtTimeFtt(this.searchTopTenData[1]);
                this.getTrafficCountDate1(begin, end);
            },
            getTrafficCountDate1(firstDate, secondDate) {
                let params = {
                    begindate: firstDate,
                    enddate: secondDate
                };
                getTrafficCount(params).then(res => {
                    if (res.data.code === 0) {
                        if (res.data.data != null || res.data.data == undefined) {
                            this.histogramData = res.data.data;
                            this.histogramOption.series[0].data[0] = this.histogramData.monday;
                            this.histogramOption.series[0].data[1] = this.histogramData.tuesday;
                            this.histogramOption.series[0].data[2] = this.histogramData.wednesday;
                            this.histogramOption.series[0].data[3] = this.histogramData.thursday;
                            this.histogramOption.series[0].data[4] = this.histogramData.friday;
                            this.histogramOption.series[0].data[5] = this.histogramData.saturday;
                            this.histogramOption.series[0].data[6] = this.histogramData.sunday;
                            this.drawHistogram();
                        }
                    } else {
                        alert("业务量" + res.data.msg);
                    }
                });
            },
            // 获取业务量统计
            getTrafficCount() {
                var myDate = new Date();
                var year = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
                var month = myDate.getMonth() + 1; //获取当前月份(0-11,0代表1月)
                var day = myDate.getDate(); //获取当前日(1-31)

                var currentDate = year + "-" + month + "-" + day;
                var begin = this.getWeekDay(currentDate)[0];
                var end = this.getWeekDay(currentDate)[
                this.getWeekDay(currentDate).length - 1
                    ];
                let params = {
                    begindate: begin,
                    enddate: end
                };
                getTrafficCount(params).then(res => {
                    if (res.data.code === 0) {
                        if (res.data.data != null || res.data.data == undefined) {
                            this.histogramData = res.data.data;
                            this.histogramOption.series[0].data[0] = this.histogramData.monday;
                            this.histogramOption.series[0].data[1] = this.histogramData.tuesday;
                            this.histogramOption.series[0].data[2] = this.histogramData.wednesday;
                            this.histogramOption.series[0].data[3] = this.histogramData.thursday;
                            this.histogramOption.series[0].data[4] = this.histogramData.friday;
                            this.histogramOption.series[0].data[5] = this.histogramData.saturday;
                            this.histogramOption.series[0].data[6] = this.histogramData.sunday;
                            this.drawHistogram();
                        }
                    } else {
                        alert("业务量" + res.data.msg);
                    }
                });
            },

            // 获取办结率
            getPercentage() {
                let params = {};
                getPercentage(params).then(res => {
                    if (res.data.code == 0) {
                        let dataList = res.data.data;
                        if (dataList.ecoTotal == 0) {
                            this.economyNumber = 0;
                        } else {
                            this.economyNumber = Math.round(
                                (dataList.ecoFinish / dataList.ecoTotal) * 100
                            );
                        }
                        if (dataList.comTotal == 0) {
                            this.complainNumber = 0
                        } else {
                            this.complainNumber = Math.round(
                                (dataList.comFinish / dataList.comTotal) * 100
                            );
                        }
                        if (dataList.commTotal == 0) {
                            this.registerNumber = 0;
                        } else {
                            this.registerNumber = Math.round(
                                (dataList.commFinish / dataList.commTotal) * 100
                            );
                        }
                        this.economyOption.title.text = this.economyNumber + "%";
                        this.registerOption.title.text = this.registerNumber + "%";
                        this.complainOption.title.text = this.complainNumber + "%";

                        this.economyOption.series = [
                            {
                                name: "",
                                type: "pie",
                                radius: ["65%", "85%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: this.economyNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#1f8fff",
                                            borderWidth: 10
                                        }
                                    },
                                    {
                                        value: 100 - this.economyNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#99ccff"
                                        }
                                    }
                                ]
                            },
                            {
                                name: "",
                                type: "pie",
                                radius: ["0", "50%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: 100,
                                        name: "",
                                        itemStyle: {
                                            color: "#007bf6",
                                            borderWidth: 10
                                        }
                                    }
                                ]
                            }
                        ];
                        this.registerOption.series = [
                            {
                                name: "",
                                type: "pie",
                                radius: ["65%", "85%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: this.registerNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#1f8fff",
                                            borderWidth: 10
                                        }
                                    },
                                    {
                                        value: 100 - this.registerNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#99ccff"
                                        }
                                    }
                                ]
                            },
                            {
                                name: "",
                                type: "pie",
                                radius: ["0", "50%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: 100,
                                        name: "",
                                        itemStyle: {
                                            color: "#007bf6",
                                            borderWidth: 10
                                        }
                                    }
                                ]
                            }
                        ];
                        this.complainOption.series = [
                            {
                                name: "",
                                type: "pie",
                                radius: ["65%", "85%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: this.complainNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#1f8fff",
                                            borderWidth: 10
                                        }
                                    },
                                    {
                                        value: 100 - this.complainNumber,
                                        name: "",
                                        itemStyle: {
                                            color: "#99ccff"
                                        }
                                    }
                                ]
                            },
                            {
                                name: "",
                                type: "pie",
                                radius: ["0", "50%"],
                                avoidLabelOverlap: true,
                                hoverAnimation: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: "center"
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data: [
                                    {
                                        value: 70,
                                        name: "",
                                        itemStyle: {
                                            color: "#007bf6",
                                            borderWidth: 10
                                        }
                                    }
                                ]
                            }
                        ];
                        this.economyRate();
                        this.registerRate();
                        this.complainRate();
                    }
                });
            },

            // 在办统计
            getdealListTotal() {
                let params = {};
                dealListTotal(params).then(res => {
                    if (res.data.code === 0) {
                        this.dealregisterNumber = res.data.data.comm;
                        this.dealComplainNumber = res.data.data.com;
                        this.dealEconomyNumber = res.data.data.eco;
                    } else {
                        // console.log(res.data.msg);
                    }
                });
            },
            getWeekDay(dateString) {
                let dateStringReg = /^\d{4}[/-]\d{1,2}[/-]\d{1,2}$/;

                if (dateString.match(dateStringReg)) {
                    let presentDate = new Date(dateString),
                        today = presentDate.getDay() !== 0 ? presentDate.getDay() : 7;

                    return Array.from(new Array(7), function (val, index) {
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
                    // var day =  date.getDate()>9?day: "0"+day;
                    // return (
                    //   date.getFullYear() +
                    //   "-" +
                    //   (date.getMonth() + 1) +
                    //   "-" +
                    //   date.getDate()>9?date.getDate(): "0"+date.getDate()
                    // );
                }
            },
            //受理登记首页预警
            getWarnCount(){
                var user = localStorage.getItem("user");
                user = JSON.parse(user);
                let params={
                    user
                }
                sldjwarnData(params).then(res=>{
                    console.log(res)
                    if(res.data.code===0){
                        this.isfeedbackCount=res.data.data.isfeedbackCount;
                        this.isregisterCount=res.data.data.isregisterCount;
                        this.isfinalbackCount=res.data.data.isfinalbackCount;
                        if((this.isfinalbackCount>0||this.isregisterCount>0||this.isfeedbackCount>0)&&this.user.primaryKey == '80b309e6-bb1d-49ba-81e8-f87c542e4f2c'){
                            const h = this.$createElement;
                            setFlag('false');
                            this.$msgbox({
                                title: '受理登记预警',
                                message: h('p', null, [
                                        h('span', null, '您有：'),
                                        h('span', {style: 'color: red'}, this.isfeedbackCount),
                                        h('span', null, '条即将到期工单还没有进行初步反馈；'),
                                        h('span', {style: 'color: red'}, this.isregisterCount),
                                        h('span', null, '条即将到期工单还没有进行立案处理；'),
                                        h('span', {style: 'color: red'}, this.isfinalbackCount),
                                        h('span', null, '条即将到期工单还没有进行最终反馈'),
                                    ],
                                ),
                                showCancelButton: true,
                                confirmButtonText: '去已办查看',
                                cancelButtonText: '确定',
                                type: 'warning'
                            }).then(() => {
                                this.$router.push({path: "/tjfx/zhcx/sldjcx"});
                            });
                        }

                    }
                })
            }
        },
        mounted() {
            this.economyOption.series = [
                {
                    name: "",
                    type: "pie",
                    radius: ["65%", "85%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: this.economyNumber,
                            name: "",
                            itemStyle: {
                                color: "#1f8fff",
                                borderWidth: 10
                            }
                        },
                        {
                            value: 100 - this.economyNumber,
                            name: "",
                            itemStyle: {
                                color: "#99ccff"
                            }
                        }
                    ]
                },
                {
                    name: "",
                    type: "pie",
                    radius: ["0", "50%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: 100,
                            name: "",
                            itemStyle: {
                                color: "#007bf6",
                                borderWidth: 10
                            }
                        }
                    ]
                }
            ];
            this.registerOption.series = [
                {
                    name: "",
                    type: "pie",
                    radius: ["65%", "85%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: this.registerNumber,
                            name: "",
                            itemStyle: {
                                color: "#1f8fff",
                                borderWidth: 10
                            }
                        },
                        {
                            value: 100 - this.registerNumber,
                            name: "",
                            itemStyle: {
                                color: "#99ccff"
                            }
                        }
                    ]
                },
                {
                    name: "",
                    type: "pie",
                    radius: ["0", "50%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: 100,
                            name: "",
                            itemStyle: {
                                color: "#007bf6",
                                borderWidth: 10
                            }
                        }
                    ]
                }
            ];
            this.complainOption.series = [
                {
                    name: "",
                    type: "pie",
                    radius: ["65%", "85%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: this.complainNumber,
                            name: "",
                            itemStyle: {
                                color: "#1f8fff",
                                borderWidth: 10
                            }
                        },
                        {
                            value: 100 - this.complainNumber,
                            name: "",
                            itemStyle: {
                                color: "#99ccff"
                            }
                        }
                    ]
                },
                {
                    name: "",
                    type: "pie",
                    radius: ["0", "50%"],
                    avoidLabelOverlap: true,
                    hoverAnimation: false,
                    label: {
                        normal: {
                            show: false,
                            position: "center"
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {
                            value: 70,
                            name: "",
                            itemStyle: {
                                color: "#007bf6",
                                borderWidth: 10
                            }
                        }
                    ]
                }
            ];

            this.getBackFillNumber();

            // this.endListSize();

            // this.histogramOption.series[0].data = this.histogramData;

            // this.drawDoublePie();
            // this.drawhotTrade();
            // this.drawMap();//本周投诉热点地区
            // this.drawHistogram();
            this.getHotZoom();
            // 获取代办数据topOne
            this.getDealData();
            this.drawHistogram();
            this.economyRate();
            this.registerRate();
            this.complainRate();
            // 获取在办统计
            this.getdealListTotal();
            // 红黄牌
            this.getCardsCount();
            // 前十的商家
            // this.getTenBusiness();
            // this.getSearchTopTenData();
            this.getDealNum();
            //this.getComplaintsArea();
            this.getBusinessTen();
            this.getComplaintRanking();
            this.getRegionData();
            this.getTrafficCount();
            // 获取办结率
            this.getPercentage();
            this.getWarnCount();
        }
    };
</script>

<style lang="scss" scoped>
    @mixin themeColor {
        color: rgb(31, 143, 255);
    }

    @mixin color {
        color: rgb(4, 7, 12);
    }

    @mixin background {
        background: rgb(248, 248, 248);
    }

    @mixin themeBackgroundColor {
        background: rgb(31, 143, 255);
    }

    .el-row {
        margin-bottom: 20px;

        &:last-child {
            margin-bottom: 0;
        }
    }

    .el-card__header {
        background: #fff !important;
    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .el-button--primary {
        background: rgb(31, 143, 255);
        border-color: rgb(31, 143, 255);
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }

    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .searchMapDate,
    .searchHotDate {
        text-align: center;
        margin-top: 20px;
    }

    .header {
        .headerName {
            @include color();
            font-size: 20px;
        }

        .el-button--primary {
            width: 15px;
            height: 15px;
            @include background();
            border-color: rgb(153, 153, 153);
            border-radius: 50%;
            padding: 0;
            margin-left: 5px;
        }

        .active {
            background: rgb(31, 143, 255);
            border-color: rgb(31, 143, 255);
        }
    }

    .button {
        padding: 0;
        float: right;
    }

    .image {
        width: 100%;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both;
    }

    /* 没有图片的卡片样式 */
    .text {
        font-size: 14px;
    }

    .searchTopTenData {
        text-align: center;
        margin: 20px 0;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        width: 100%;
    }

    /* 控制面板第一行的样式 */

    .listName1 {
        position: relative;
        align-items: center;
        width: 100%;
        height: 170px;
        /* border-top: 3px solid #1f8fff; */
        ul {
            margin: 10px;
            padding: 0;

            li {
                display: flex;
                flex-wrap: wrap;
                list-style: none;

                .cardOne {
                    padding: 2%;
                    width: 44%;
                    border-radius: 5px;
                    margin-right: 3%;
                    margin-bottom: 10px;
                    @include background();

                    .cardName {
                        font-size: 12px;
                        margin-bottom: 10px;
                        @include color();
                    }

                    .cardNumber {
                        font-size: 20px;
                        @include themeColor();
                        margin-bottom: 10px;

                        &:hover {
                            text-decoration: underline;
                            @include themeColor();
                            cursor: pointer;
                        }
                    }
                }

                .cardTwo {
                    padding: 2%;
                    width: 26%;
                    border-radius: 5px;
                    margin-right: 3%;
                    margin-bottom: 10px;
                    @include background();

                    .cardName {
                        font-size: 12px;
                        margin-bottom: 10px;
                        @include color();
                    }

                    .cardNumber {
                        font-size: 20px;
                        @include themeColor();
                        margin-bottom: 10px;

                        &:hover {
                            text-decoration: underline;
                            @include themeColor();
                            cursor: pointer;
                        }
                    }
                }

                .cardOne:nth-child(2n) {
                    margin-right: 0;
                }
            }
        }
    }

    .listNameWaring {
        height: 236px;
        border: none !important;
    }

    /* .el-col {
      margin-top: 10px;
    } */
    /* 第二行卡片样式 */
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

    .hotTradeCard {
        height: 416px;
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

    /* 工单查询入口 */
    .enterCard {
        margin-bottom: 20px;

        .enterOfSearch {
            height: 30px;
            display: flex;
            align-items: center;
            justify-content: space-around;

            .enterName {
                font-size: 16px;
                text-align: left;
                width: 80%;
                @include color();
            }

            .enterArrow {
                width: 20%;
                text-align: right;
                cursor: pointer;

                span {
                    display: block;
                    font-size: 20px;
                    color: #99a9bf;
                    font-weight: 600;
                }

                &:hover {
                    span {
                        @include color();
                    }
                }
            }
        }
    }

    .nullPic {
        width: 100px;
        height: 100px;
        text-align: center;
        color: rgb(191, 203, 217);
        /* background: #000; */
        margin: 60px auto;

        .el-icon-erpzanwushuju {
            font-size: 100px;
            color: rgb(191, 203, 217)
        }
    }

    /* 在办类型 */
    .dealListTitle {
        /* color: #333; */
        @include color();
        font-weight: 600;

        & > p {
            text-align: left;
            font-size: 20px;
            margin-top: -3px;
            padding: 0;
        }
    }

    .dealList {
        width: 100%;
        height: 100%;
        margin-top: 30px;

        & > ul {
            margin: 0;
            padding: 0;
            list-style: none;
            display: flex;

            & > li {
                flex: 1;
                height: 150px;
                background: #1f8fff;
                margin-right: 20px;
                box-shadow: 5px 3px 5px 0px #ccc;

                .dealType {
                    height: 100%;
                    box-sizing: border-box;
                    align-items: center;
                    position: relative;

                    .dealTitle {
                        height: 70px;
                        line-height: 70px;
                        font-size: 20px;
                        color: #fff;
                        text-align: center;
                        vertical-align: text-bottom;
                        bottom: 0;
                    }

                    .dealNumber {
                        width: 95%;
                        margin: 0 auto;
                        height: 80px;
                        line-height: 80px;
                        font-weight: 600;
                        text-align: center;
                        font-size: 20px;
                        color: #fff;
                    }
                }
            }

            & > li:last-child {
                margin-right: 0;
            }
        }
    }

    .pie {
        width: 100%;
        height: 600px;
        margin: 0 auto;
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

    /* 第三行卡片 */
    /* .theThird { */
    .histogram {
        width: 100%;
        height: 600px;
    }

    .settlementRateTitle {
        /* color: #333; */
        /* color: #1f8fff; */
        @include color();
        font-weight: 600;

        & > p {
            text-align: left;
            font-size: 20px;
            margin-top: -3px;
            padding: 0;
        }
    }

    .settlementRate {
        width: 100%;

        & > ul {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
            height: 150px;
            display: flex;
            align-items: center;
            justify-content: space-evenly;
            margin-top: 30px;

            & > li {
                width: 100px;
                height: 100px;
                color: rgb(31, 143, 255);

                /* border-radius: 50%; */
                /* background: #99ccff; */
                /* margin-right: 20px; */
                /* box-shadow: 5px 3px 5px 0px #ccc; */
                text-align: center;

                .settleRate {
                    width: 100%;
                    height: 100%;
                    font-size: 16px;
                }

                .registerName {
                    text-align: center;
                }

                .settlementRateNumber {
                    height: 50px;
                    font-size: 16px;
                    font-weight: 600;
                    line-height: 30px;
                    color: #fff;
                }
            }

            & > li:last-child {
                margin-right: 0;
            }
        }
    }

    /* } */
    .histogramCard {
        height: 400px;
    }

    .settlementRateCard {
        height: 280px;
        margin-bottom: 40px;
    }

    .histogramCardOne {
        position: relative;
        height: 600px;
    }

    .hotTrade {
        height: 300px;

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            padding-top: 0;
            margin-top: 10px;

            & > li {
                width: 100%;
                font-size: 16px;
                padding-left: 10px;
                margin-bottom: 2px;
                color: #334455;

                &:first-child {
                    span {
                        background: #f54545 !important;
                    }
                }

                &:nth-child(2) {
                    span {
                        background: #ff8547 !important;
                    }
                }

                &:nth-child(3) {
                    span {
                        background: #ffac38 !important;
                    }
                }

                span {
                    color: #fff;
                    font-size: 14px;
                    line-height: 15px;
                    width: 15px;
                    height: 15px;
                    display: inline-block;
                    text-align: center;
                    margin-right: 5px;
                    background: #8eb9f5;
                }
            }
        }
    }

    .mapTitle p,
    .hotTradeTitle p {
        margin-bottom: 0 !important;
    }

    .rateCard {
        height: 278px;
    }

    /* 地图 */
    .map {
        width: 100%;
        height: 300px;
    }

    .el-loading-spinner {
        top: 0;
        margin-top: 0;
    }
</style>
