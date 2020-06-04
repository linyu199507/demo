<template>
    <div class="complaint">
        <el-card id="totalComplain">
            <div class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>全区投诉总量及调解</p>
                                </div>
                                <el-date-picker
                                        v-model="OneDate"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="getOneData()">查询</el-button>
                                <el-tabs v-model="activeRate" @tab-click="handleClick">
                                    <el-tab-pane
                                            label="投诉基本情况"
                                            name="settlementRate"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="settlementRate"
                                                id="settlementRate"
                                                v-loading="loadingOfsettlementRate"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="挽回金额"
                                            name="mediationRate"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="mediationRate"
                                                id="mediationRate"
                                                v-loading="loadingOfmediationRate"
                                        ></div>
                                       <!-- v-if="activeRate==='mediationRate'"-->
                                    </el-tab-pane>
                                </el-tabs>
                            </el-card>
                        </div>
                    </el-col>
                </el-row>
            </div>

        </el-card>
        <el-card id="changeComplain">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>各单位投诉量动态变化图</p>
                                </div>
                                <div
                                    class="amountRecovered"
                                    id="amountRecovered"
                                    v-loading="amountloading"
                                ></div>
                            </el-card>
                        </div>
                    </el-col>

                </el-row>
            </el-col>
        </div>
        </el-card>
        <el-card id="hotComplain">
            <div class="businessVolume mt20">
                <el-col class="businessVolume mt20">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="16" :lg="24">
                            <div class="grid-content bg-purple doublePie">
                                <el-card class="box-card dealCart_liny">
                                    <div class="settlementRateTitle mapTitle">
                                        <p>热点投诉性质</p>
                                    </div>
                                    <el-tabs v-model="NatureRate" @tab-click="NatureClick">
                                        <el-tab-pane
                                                label="热点性质投诉量"
                                                name="hotNatureOne"
                                                element-loading-text="拼命加载中"
                                        >
                                            <div
                                                    class="mediationRate"
                                                    id="hotNature"
                                                    v-if="NatureRate==='hotNatureOne'"
                                                    v-loading="loadingOfhotNatureOne"
                                            ></div>
                                        </el-tab-pane>
                                        <el-tab-pane
                                                label="投诉问题"
                                                name="hotNatureTwo"
                                                element-loading-text="拼命加载中"
                                        >
                                            <!--<el-date-picker-->
                                                    <!--v-model="hotNatureData"-->
                                                    <!--type="daterange"-->
                                                    <!--align="center"-->
                                                    <!--placeholder="选择日期范围"-->
                                                    <!--:picker-options="pickerOptionsOne"-->
                                            <!--&gt;</el-date-picker>-->
                                            <!--<el-button type="primary" @click="searchhotNature()">查询</el-button>-->

                                            <div
                                                    class="mediationRate"
                                                    id="hotNatureProblem"
                                                    v-if="NatureRate==='hotNatureTwo'"
                                                    v-loading="hotNatureProblemLoading"
                                            ></div>
                                        </el-tab-pane>
                                    </el-tabs>
                                </el-card>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
            </div>
        </el-card>
        <el-card id="topComplain10">
            <div class="businessVolume mt20">
                <el-col class="businessVolume mt20">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="16" :lg="24">
                            <div class="grid-content bg-purple doublePie">
                                <el-card class="box-card dealCart">
                                    <div class="settlementRateTitle mapTitle">
                                        <p>被投诉主体TOP10</p>
                                    </div>
                                    <el-date-picker
                                            v-model="subjectTenData"
                                            type="daterange"
                                            align="center"
                                            placeholder="选择日期范围"
                                            :picker-options="pickerOptionsOne"
                                    ></el-date-picker>
                                    <el-button type="primary" @click="searchSubjectTenData()">查询</el-button>

                                    <div
                                            class="amountRecovered"
                                            id="subjectTen"
                                            v-loading="subjectTenLoading"
                                    ></div>
                                </el-card>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
            </div>
        </el-card>
        <el-card id="hotComplain10">
            <div class="businessVolume mt20">
                <el-col class="businessVolume mt20">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="16" :lg="24">
                            <div class="grid-content bg-purple doublePie">
                                <el-card class="box-card dealCart">
                                    <div class="settlementRateTitle mapTitle">
                                        <p>被投诉热点商圈TOP10</p>
                                    </div>
                                    <el-date-picker
                                            v-model="businesscircleTenData"
                                            type="daterange"
                                            align="center"
                                            placeholder="选择日期范围"
                                            :picker-options="pickerOptionsOne"
                                    ></el-date-picker>
                                    <el-button type="primary" @click="searchBusinesscircleTenData()">查询</el-button>
                                    <div
                                            class="amountRecovered"
                                            id="businesscircleTen"
                                            v-loading="businesscircleTenLoading"
                                    ></div>
                                </el-card>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
            </div>
        </el-card>
    </div>
</template>

<script>
    import {
        searchComplaintdata,searchHotNature,searchDataOfTwelve,searchBusinesscircle,searchSubjectTen,searchEconomyIllegal,
    }from "../../../../api/graphics"
    export default {
        name: "Complaintshotspots",

        data(){
            return{
                businesscircleTenData:"",
                subjectTenData:"",
                hotNatureData:"",
                amountOneDate:"",
                OneDate:"",
                loadingOfhotNatureTwo:false,
                loadingOfmediationRate:false,
                loadingOfsettlementRate:false,
                businesscircleTenLoading:false,
                hotNatureProblemLoading:false,
                amountloading:false,
                NatureRate:"hotNatureOne",
                activeRate:"settlementRate",
                loadingOfhotNatureOne: false,
                loadingAmountReacovered:false,
                subjectTenLoading:false,
                amountRec: "amountRecovered",
                firstdate:"",
                lastdate:"",
                amountfirstdate:"",
                amountlastdate:"",
                hotnaturefirstdate:"",
                hotnaturelastdate:"",
                subjectTenfirstdate:"",
                subjectTenlastdate:"",
                businesscircleTenfirstdate:"",
                businesscircleTenlastdate:"",
                pickerOptionsOne: {
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
                settlementRateOption:  {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['投诉总数', '调解成功数', '诉转案','同比','调解率','诉转率'],
                        selected: {
                            '诉转案': false, '同比': false, '调解率': false, '诉转率': false
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '9%',
                        containLabel: true
                    },
                    xAxis: {
                        // type: 'value'
                        type: 'category',
                        data: []
                    },
                    yAxis: [
                        // type: 'category',
                        // data: []
                        {name:'业务量(件)', type: 'value'},
                        {name:'百分比(%)', type: 'value',min:-50,max:50}
                    ],
                    series: [
                        {
                            name: '投诉总数',
                            type: 'bar',
                            // stack: '总量',
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },
                        {
                            name: '调解成功数',
                            type: 'bar',
                            // stack: '总量',
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },
                        {
                            name: '诉转案',
                            type: 'bar',
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },
                        {
                            name: '同比',
                            type: 'line',
                            yAxisIndex: 1,
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },
                        {
                            name: '调解率',
                            type: 'line',
                            yAxisIndex: 1,
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },
                        {
                            name: '诉转率',
                            type: 'line',
                            color: 'black'  ,
                            yAxisIndex: 1,
                            label: {
                                show: true,
                                position: 'top'
                            },
                            data: []
                        },

                    ]
                } ,
                // 挽回金额统计
                mediationRateOption: {
                    color: "#ff8f1f",
                    legend: {
                        show: true
                    },
                    tooltip: {},
                    dataset: {
                        source: [

                        ]
                    },
                    xAxis: { type: "category" },
                    yAxis: {
                        name: "金额",
                        type: "value",
                        nameLocation: "end",
                        min: 0,
                        // max: 100,
                        max: function(value) {
                            return value.max + 20000;
                        },
                        // splitNumber: "10",
                        axisLabel: {
                            formatter: "{value}(元)",
                            show: true
                        }
                    },
                    series: [{ type: "bar", label: { show: true, position: "top" } }]
                },

                hotNatureOption:  {
                    baseOption: {
                        timeline: {
                            axisType: 'category',
                            // realtime: false,
                            // loop: false,
                            autoPlay: true,
                            // currentIndex: 2,
                            playInterval: 1000,
                            // controlStyle: {
                            //     position: 'left'
                            // },
                            data: [],
                            label: {
                                formatter : function(s) {
                                    // return (new Date(s)).getFullYear()+'-'+((new Date(s)).getUTCMonth()+1);
                                    // console.log(new Date(s)).getFullYear()+'-'+((new Date(s)).getMonth()+1)
                                    return (new Date(s)).getFullYear()+'-'+((new Date(s)).getMonth()+1);
                                }
                            }
                        },
                        title: {
                            subtext: '数据来自智能服务平台'
                        },
                        tooltip: {
                        },
                        legend: {
                            left: 'right',
                            data: [ '业务量','增长率'],
                            // selected: {
                            //     '增长率': false,
                            // }
                        },
                        calculable : true,
                        grid: {
                            top: 80,
                            bottom: 100,
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow',
                                    label: {
                                        show: true,
                                        formatter: function (params) {
                                            return params.value.replace('\n', '');
                                        }
                                    }
                                }
                            }
                        },
                        grid: {
                            // left: '3%',
                            // right: '4%',
                            bottom: '9%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type:'category',
                                axisLabel:{'interval':0,rotate: 20},
                                data:[],
                                splitLine: {show: false},
                            },
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                name: '业务量（件）',
                                show:true,
                                min:10
                            },
                            {
                                type: 'value',
                                name: '增长率(%)',
                                show:true,
                                min:0,
                                max:100
                            }
                        ],
                        series: [
                            {name: '业务量', type: 'bar'},
                            {name: '增长率', type: 'line',yAxisIndex: 1,}
                        ]
                    },
                    options: []
                },

                subjectTenOption:{
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        }
                    },
                    toolbox: {
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['业务量', '增长率']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel:{'interval':0,rotate: 15},
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '业务量',
                            // min: 0,
                            // max: 250,
                            // interval: 50,
                            axisLabel: {
                                formatter: '{value} (件)'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            min: -100,
                            // max: 25,
                            // interval: 5,
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '业务量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'top',
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            data: [],
                            yAxisIndex: 1,
                            label: {
                                show: true,
                                position: 'top',
                            },
                        },
                    ]
                },

                hotNatureProblemOption: {
                    color: "#008B8B",
                    title: {
                        text: '热点投诉问题',
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
                    }, ]
                },

                amountReacoveredOption: {
                    baseOption: {
                        timeline: {
                            axisType: 'category',
                            // realtime: false,
                            // loop: false,
                            autoPlay: true,
                            // currentIndex: 2,
                            playInterval: 1000,
                            // controlStyle: {
                            //     position: 'left'
                            // },
                            data: [
                                // '2002-01','2002-02','2002-03','2002-04','2002-05','2002-06','2002-07','2002-08','2002-09','2002-10',
                                // {
                                //     value: '2005-01-01',
                                //     tooltip: {
                                //         formatter: '{b} GDP达到一个高度'
                                //     },
                                //     symbol: 'diamond',
                                //     symbolSize: 16
                                // },
                            ],
                            label: {
                                formatter : function(s) {
                                    // return (new Date(s)).getFullYear();
                                    // return (new Date(s)).getFullYear()+'-'+((new Date(s)).getUTCMonth()+1);
                                    return (new Date(s)).getFullYear()+'-'+((new Date(s)).getMonth()+1);
                                }
                            }
                        },
                        title: {
                            subtext: '数据来自智能服务平台'
                        },
                        tooltip: {
                        },
                        legend: {
                            left: 'right',
                            data: [ '业务量','增长率'],
                            // selected: {
                            //     '增长率': false,
                            // }
                        },
                        calculable : true,
                        grid: {
                            top: 80,
                            bottom: 100,
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow',
                                    label: {
                                        show: true,
                                        formatter: function (params) {
                                            return params.value.replace('\n', '');
                                        }
                                    }
                                }
                            }
                        },
                        xAxis: [
                            {
                                type:'category',
                                axisLabel:{'interval':0},
                                data:[

                                ],
                                splitLine: {show: false}
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                name: '业务量（件）',
                                show:true,
                                min:10
                            },
                            {
                                type: 'value',
                                name: '增长率（%）',
                                show:true,
                                min:0,
                                max:100
                            }
                        ],
                        series: [
                            {name: '业务量', type: 'bar'},
                            {name: '增长率', type: 'line',yAxisIndex: 1}

                        ]
                    },
                    options: [
                        // {
                        //     // title: {text: '2002全国宏观经济指标'},
                        //     series:
                        //
                        //         {
                        //             label: { show: true, position: "top" },
                        //             data: [5.93,6.28,7.76,8.55,8.88,5.7,4.83,7,4.69,8.27]
                        //         },
                        // },
                    ]
                },

                businesscircleTenOption:{
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        }
                    },
                    toolbox: {
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['业务量', '增长率']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel:{'interval':0,rotate: 15},
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '业务量',
                            // min: 0,
                            // max: 250,
                            // interval: 50,
                            axisLabel: {
                                formatter: '{value} (件)'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            min: -100,
                            // max: 25,
                            // interval: 5,
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '业务量',
                            type: 'bar',
                            data: [],
                            //barWidth: 100, // 柱图宽度
                            barMaxWidth: 100, // 最大宽度
                            label: {
                                show: true,
                                position: 'top',
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            data: [],
                            yAxisIndex: 1,
                            label: {
                                show: true,
                                position: 'top',
                            },
                        },
                    ]
                },

                //对象排序
                compare: function( property, desc ) {
                    return function ( a, b ) {
                        var value1 = a[ property ];
                        var value2 = b[ property ];
                        if ( desc == true ) {
                            //排序升序排列
                            return value1 - value2;
                        } else {
                            return value2 - value1;
                        }
                    }
                },
                //linyu
                growth(lastList,thisList,number){
                    // console.log(lastList)
                    // console.log(thisList)
                    let dataList = []

                    for (var i = 0 ; i<= number-1 ; i++){
                        if(lastList[i] === 0 && thisList[i] != 0){
                            dataList.push(100)
                        }else if(thisList[i] === 0){
                            dataList.push(0)
                        }else{
                            dataList.push(Math.round(thisList[i]/lastList[i]*100))
                        }
                    }
                    return dataList;
                },

            };
        },
        mounted(){
            console.log("one");
            this.getComplaintdata();
            this.drawComplaintOfMounth();
            this.getHotNature();
            this.getHotNatureProblem();
            this.getSubjectTen();
            this.getBusinesscircleTen();
            this.getDataOfTwelve();
            console.log("end")
        },
        methods:{
                handleClick(tab, event) {
                    if (tab.label === "投诉基本情况") {
                        const interval = setInterval(() => {
                            let settlementDom = document.getElementById("settlementRate");
                            if (settlementDom) {
                                this.drawsettlement();
                                // this.loadingOfsettlementRate = true;
                                clearInterval(interval);

                            }
                        }, 200);
                    } else if (tab.label === "挽回金额") {
                        const interval1 = setInterval(() => {
                            let mediationDom = document.getElementById("mediationRate");
                            if (mediationDom) {
                                this.drawMediationRate();
                                clearInterval(interval1);
                            }
                        }, 200);
                    }
                },
                NatureClick(tab, event) {
                    if (tab.label === "热点性质投诉量") {
                        const interval = setInterval(() => {
                            let settlementDom = document.getElementById("settlementRate");
                            this.NatureRate==='hotNatureOne'
                            if (settlementDom) {
                                this.drawHotNature();
                                // this.loadingOfsettlementRate = true;
                                clearInterval(interval);
                            }
                        }, 200);
                    } else if (tab.label === "投诉问题") {
                        const interval1 = setInterval(() => {
                            let mediationDom = document.getElementById("hotNatureProblem");
                            this.NatureRate==='hotNatureTwo'
                            if (mediationDom) {
                                this.drawHotNatureProblem();
                                clearInterval(interval1);
                            }
                        }, 200);
                    }
                },
                // 办结条形图
                drawsettlement() {
                    let drawsettlement = this.$echarts.init(
                        document.getElementById("settlementRate")
                    );
                    drawsettlement.setOption(this.settlementRateOption, true);
                    // this.loadingOfsettlementRate = true;
                },
                // 挽回金额
                drawMediationRate() {
                    console.log("666")
                    let mediationRate = this.$echarts.init(
                        document.getElementById("mediationRate")
                    );
                    mediationRate.setOption(this.mediationRateOption, true);
                    // this.loadingOfmediationRate = false;
                },

                // 2、投诉量前12月具体情况
                drawComplaintOfMounth () {
                    let amountRecovered = this.$echarts.init(
                        document.getElementById("amountRecovered")
                    );
                    amountRecovered.setOption(this.amountReacoveredOption, true);
                    this.loadingAmountReacovered = false;
                },

                // 2、投诉热点性质--投诉问题
                drawHotNatureProblem () {
                    let amountRecovered = this.$echarts.init(
                        document.getElementById("hotNatureProblem")
                    );
                    amountRecovered.setOption(this.hotNatureProblemOption, true);
                    // this.loadingAmountReacovered = false;
                },

                drawHotNature() {
                    let hotNature = this.$echarts.init(
                        document.getElementById("hotNature")
                    );
                    hotNature.setOption(this.hotNatureOption, true);
                    // this.loadingOfmediationRate = false;
                },

                drawsubjectTen() {
                    let mediationRate = this.$echarts.init(
                        document.getElementById("subjectTen")
                    );
                    mediationRate.setOption(this.subjectTenOption, true);
                    // this.loadingOfmediationRate = false;
                },
                // 被投诉商圈Top10
                drawbusinesscircleTen() {
                    let mediationRate = this.$echarts.init(
                        document.getElementById("businesscircleTen")
                    );
                    mediationRate.setOption(this.businesscircleTenOption, true);
                    // this.loadingOfmediationRate = false;
                },

                getComplaintdata() {
                    this.loadingOfsettlementRate=true
                    this.loadingOfmediationRate=true
                    this.settlementRateOption.xAxis.data=[]
                    this.settlementRateOption.yAxis.data=[]
                    this.settlementRateOption.series[0].data=[]
                    this.settlementRateOption.series[1].data=[]
                    this.settlementRateOption.series[2].data=[]
                    this.settlementRateOption.series[3].data=[]
                    this.settlementRateOption.series[4].data=[]
                    this.settlementRateOption.series[5].data=[]
                    this.mediationRateOption.dataset.source=[]
                    let params = {
                        begindate:this.firstdate,
                        enddate:this.lastdate
                    };
                    console.log(params.begindate)

                    searchComplaintdata(params).then(res => {
                        console.log(res);
                        for (var key in res.data.this) {
                            this.settlementRateOption.xAxis.data .push(res.data.this[key].region)
                            this.settlementRateOption.series[0].data.push(res.data.this[key].overalltotal);
                            this.settlementRateOption.series[1].data.push(res.data.this[key].endtotal);
                            this.settlementRateOption.series[2].data.push(res.data.this[key].toeconomytotal);
                            this.settlementRateOption.series[3].data.push(res.data.this[key].toeconomytotal);
                            this.settlementRateOption.series[4].data.push(Math.round((res.data.this[key].endtotal/res.data.this[key].overalltotal)*100));
                            this.settlementRateOption.series[5].data.push(Math.round((res.data.this[key].toeconomytotal/res.data.this[key].overalltotal)*100));
                        }
                        // this.loadingOfsettlementRate=false
                        //       this.mediationRateOption.dataset.source.sort(this.compare('办结率'))
                        this.money = res.data.this
                        this.money.sort(this.compare('recovermoney'))
                        for (var key in this.money) {
                            let data = { product: this.money[key].region, 挽回金额: this.money[key].recovermoney };
                            this.mediationRateOption.dataset.source.push(data);
                        }
                        this.loadingOfsettlementRate=false
                        this.loadingOfmediationRate=false
                        this.drawsettlement();
                    });
                },
                getHotNature() {
                    let params = {};
                    this.loadingOfhotNatureOne=true
                    searchHotNature(params).then(res => {
                        console.log(res);
                        let Once = true;
                        let thisList;
                        for (var key in res.data) {
                            this.hotNatureOption.baseOption.timeline.data.push(key)
                            this.listOfDateOne = []

                            for (var liny in res.data[key]) {
                                this.hotNatureOption.baseOption.xAxis[0].data.push(res.data[key][liny].bustype)
                                this.listOfDateOne.push(res.data[key][liny].overalltotal)
                                // lastList = res.data[key]
                            }
                            if(Once){
                                this.listOfDateAdd = this.listOfDateOne
                                Once = false
                            }

                            thisList = this.listOfDateOne
                            this.listOfDateAdd = this.listOfDateAdd.map(function(v, i){return v + thisList[i];})
                            let growthList = this.growth(this.listOfDateAdd,thisList,20)
                            let data = {series:
                                    [
                                        {label: {show: true, position: "top"},data: this.listOfDateOne},
                                        {data: growthList}   //增长率
                                    ], }
                            this.hotNatureOption.options.push(data)
                        }
                        this.hotNatureOption.baseOption.xAxis[0].data = this.hotNatureOption.baseOption.xAxis[0].data.splice(0, 20)
                        this.loadingOfhotNatureOne=false
                        this.drawHotNature();
                    });
                },

                getHotNatureProblem() {
                    this.hotNatureProblemLoading=true
                    this.hotNatureProblemOption.yAxis.data=[]
                    this.hotNatureProblemOption.series[0].data=[]
                    let params = {
                        firstdate:this.hotnaturefirstdate,
                        lastdate:this.hotnaturelastdate
                    };

                    searchEconomyIllegal(params).then(res => {
                        console.log(res);
                        for (var key in res.data.this) {
                            this.hotNatureProblemOption.yAxis.data.push(res.data.this[key].bustype)
                            this.hotNatureProblemOption.series[0].data.push(res.data.this[key].recordtotal);
                        }
                        //       this.mediationRateOption.dataset.source.sort(this.compare('办结率'))
                        this.hotNatureProblemLoading=false
                        this.drawHotNatureProblem();

                    });
                },
                getSubjectTen() {
                    // this.subjectTenLoading=true
                    this.subjectTenOption.xAxis[0].data=[]
                    this.subjectTenOption.series[0].data=[]
                    this.subjectTenOption.series[1].data=[]
                    let params = {
                        begindate:this.subjectTenfirstdate,
                        enddate:this.subjectTenlastdate
                    };
                    console.log(params)
                    searchSubjectTen(params).then(res => {
                        console.log(res);
                        for (var key in res.data.this) {
                            this.subjectTenOption.xAxis[0].data.push(res.data.this[key].bustype);
                            this.subjectTenOption.series[0].data.push(res.data.this[key].overalltotal);
                            if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                                this.subjectTenOption.series[1].data.push(0)
                            }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                                this.subjectTenOption.series[1].data.push(100)
                            }else{
                                this.subjectTenOption.series[1].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                            }
                        }
                        // this.subjectTenLoading=false
                        this.drawsubjectTen();
                    });
                },

                getBusinesscircleTen() {
                    this.businesscircleTenLoading=true
                    this.businesscircleTenOption.xAxis[0].data=[]
                    this.businesscircleTenOption.series[0].data=[]
                    this.businesscircleTenOption.series[1].data=[]
                    let params = {
                        begindate:this.businesscircleTenfirstdate,
                        enddate:this.businesscircleTenlastdate
                    };
                    console.log(params)
                    searchBusinesscircle(params).then(res => {
                        console.log(res);
                        for (var key in res.data.this) {
                            this.businesscircleTenOption.xAxis[0].data.push(res.data.this[key].bustype);
                            this.businesscircleTenOption.series[0].data.push(res.data.this[key].overalltotal);
                            if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                                this.businesscircleTenOption.series[1].data.push(0)
                            }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                                this.businesscircleTenOption.series[1].data.push(100)
                            }else{
                                this.businesscircleTenOption.series[1].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                            }
                        }
                        this.businesscircleTenLoading=false
                        this.drawbusinesscircleTen();
                    });
                },

                getDataOfTwelve() {
                    let params = {
                    };
                    console.log(params)
                    this.amountloading=true
                    searchDataOfTwelve(params).then(res => {
                        console.log(res);
                        let Once = true;
                        let thisList;
                        for (var key in res.data) {
                            this.amountReacoveredOption.baseOption.timeline.data.push(key)
                            this.listOfDate = []
                            for (var liny in res.data[key]) {
                                this.amountReacoveredOption.baseOption.xAxis[0].data.push(res.data[key][liny].region)
                                this.listOfDate.push(res.data[key][liny].overalltotal)
                            }

                            if(Once){
                                this.listOfDateAddOne = this.listOfDate
                                Once = false
                            }

                            thisList = this.listOfDate
                            this.listOfDateAddOne = this.listOfDateAddOne.map(function(v, i){return v + thisList[i];})
                            let growthList = this.growth(this.listOfDateAddOne,thisList,10)
                            let data = {series:
                                    [
                                        {label: {show: true, position: "top"},data: this.listOfDate},
                                        {data: growthList}   //增长率
                                    ], }
                            this.amountReacoveredOption.options.push(data)
                        }
                        this.amountReacoveredOption.baseOption.xAxis[0].data = this.amountReacoveredOption.baseOption.xAxis[0].data.splice(0,10)
                        this.amountloading=false
                        this.drawComplaintOfMounth();

                    });
                },
            getOneData(){
                this.firstdate=this.$moment(this.OneDate[0]).format("YYYY-MM-DD HH:mm:ss");
                this.lastdate=this.$moment(this.OneDate[1]).format("YYYY-MM-DD HH:mm:ss");
                this.getComplaintdata();
            },
            getamountOneDate(){
                this.amountfirstdate=this.$moment(this.amountOneDate[0]).format("YYYY-MM-DD HH:mm:ss");
                this.amountlastdate=this.$moment(this.amountOneDate[1]).format("YYYY-MM-DD HH:mm:ss");
                this.getDataOfTwelve()
            },
            searchhotNature(){
                    this.hotnaturefirstdate=this.$moment(this.hotNatureData[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.hotnaturelastdate=this.$moment(this.hotNatureData[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getHotNatureProblem()
            },
            searchSubjectTenData(){
                    if(this.subjectTenData[0]==null){
                        this.subjectTenfirstdate=this.subjectTenData[0]
                        this.subjectTenlastdate=this.subjectTenData[1]
                        this.getSubjectTen()
                    }else {
                        this.subjectTenfirstdate = this.$moment(this.subjectTenData[0]).format("YYYY-MM-DD HH:mm:ss");
                        this.subjectTenlastdate = this.$moment(this.subjectTenData[1]).format("YYYY-MM-DD HH:mm:ss");
                        this.getSubjectTen()
                    }
            },
            searchBusinesscircleTenData(){
                if(this.businesscircleTenData[0]==null){
                    this.businesscircleTenfirstdate=this.businesscircleTenData[0]
                    this.businesscircleTenlastdate=this.businesscircleTenData[1]
                    this.getBusinesscircleTen()
                }else {
                    this.businesscircleTenfirstdate = this.$moment(this.businesscircleTenData[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.businesscircleTenlastdate = this.$moment(this.businesscircleTenData[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getBusinesscircleTen()
                }
            }
            },

        }
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
    .mediationRateOne {
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
        height: 750px;

        .el-tabs__item {
            font-size: 16px;
        }

        .el-card__body {
            height: 100%;
        }
    }
    .dealCart_liny {
        position: relative;
        height: 650px;

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
    .complaint {
        height: 700px;
        overflow-y: auto;
        background: #ccc;                                                     
    }
</style>