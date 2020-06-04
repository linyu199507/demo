<template>
    <div class="complaint">
        <el-card id="toOneth">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>交通工具类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="trafficdata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchTrafficData()">查询</el-button>
                                <el-tabs v-model="traffic" @tab-click="TrafficCategoryClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="trafficCategory"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficCategory"
                                                v-if="traffic === 'trafficCategory'"
                                                v-loading="loadingOftrafficCategory"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="trafficProbleam"
                                            v-loading="loadingOfamountRecovered"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficProbleam"
                                                v-if="traffic === 'trafficProbleam'"
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

        <el-card id="toTwoth">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>交通工具类产品具体投诉性质</p>
                                </div>
                                <el-date-picker
                                        v-model="trafficonedata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchTrafficOneData()">查询</el-button>
                                <el-tabs v-model="trafficOne" @tab-click="TrafficNatureClick">
                                    <el-tab-pane
                                            label="投诉性质"
                                            name="trafficNature"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficNature"
                                                v-if="trafficOne === 'trafficNature'"
                                                v-loading="loadingOftrafficNature"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="trafficNatureProblem"
                                            v-loading="loadingOfamountRecovered"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficNatureProblem"
                                                v-if="trafficOne === 'trafficNatureProblem'"
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

        <el-card id="toThreeth">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>交通工具类产品具体投诉品牌</p>
                                </div>
                                <el-date-picker
                                        v-model="trafficbranddata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchTrafficBrandData()">查询</el-button>
                                <el-tabs v-model="trafficTwo" @tab-click="TrafficBrandClick">
                                    <el-tab-pane
                                            label="投诉品牌"
                                            name="trafficBrand"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficBrand"
                                                v-if="trafficTwo === 'trafficBrand'"
                                                v-loading="loadingOftrafficBrand"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="trafficBrandProblem"
                                            v-loading="loadingOfamountRecovered"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="trafficBrandProblem"
                                                v-if="trafficTwo === 'trafficBrandProblem'"
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

        <el-card id="toFourth">
    <div class="businessVolume mt20" >
        <el-col class="businessVolume mt20">
            <el-row :gutter="20">
                <el-col :xs="24" :sm="24" :md="16" :lg="24">
                    <div class="grid-content bg-purple doublePie">
                        <el-card class="box-card dealCart">
                            <div class="settlementRateTitle mapTitle">
                                <p>化妆品类产品具体投诉环节</p>
                            </div>
                            <el-date-picker
                                    v-model="cosmeticsdata"
                                    type="daterange"
                                    align="center"
                                    placeholder="选择日期范围"
                                    :picker-options="pickerOptionsOne"
                            ></el-date-picker>
                            <el-button type="primary" @click="searchcosmeticsData()">查询</el-button>
                            <el-tabs v-model="Cosmetics" @tab-click="CosmeticsLinkClick">
                                <el-tab-pane
                                        label="投诉环节"
                                        name="CosmeticsLink"
                                        element-loading-text="拼命加载中"
                                >
                                    <div
                                            class="amountRecovered"
                                            id="CosmeticsLink"
                                            v-if="Cosmetics ==='CosmeticsLink'"
                                            v-loading="loadingOfCosmeticsLink"
                                    ></div>
                                </el-tab-pane>
                                <el-tab-pane
                                        label="投诉问题"
                                        name="CosmeticsLinkProblem"
                                        v-loading="loadingOfDailyBrand"
                                        element-loading-text="拼命加载中"
                                >
                                    <div
                                            class="amountRecovered"
                                            id="CosmeticsLinkProblem"
                                            v-if="Cosmetics ==='CosmeticsLinkProblem'"
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

        <el-card id="toFireth">
    <div class="businessVolume mt20" >
        <el-col class="businessVolume mt20">
            <el-row :gutter="20">
                <el-col :xs="24" :sm="24" :md="16" :lg="24">
                    <div class="grid-content bg-purple doublePie">
                        <el-card class="box-card dealCart">
                            <div class="settlementRateTitle mapTitle">
                                <p>医疗器械类产品具体投诉环节</p>
                            </div>
                            <el-date-picker
                                    v-model="hospitaldata"
                                    type="daterange"
                                    align="center"
                                    placeholder="选择日期范围"
                                    :picker-options="pickerOptionsOne"
                            ></el-date-picker>
                            <el-button type="primary" @click="searchHospitalData()">查询</el-button>
                            <el-tabs v-model="Hospital" @tab-click="HospitalLinkClick">
                                <el-tab-pane
                                        label="投诉环节"
                                        name="HospitalLink"
                                        element-loading-text="拼命加载中"
                                >
                                    <div
                                            class="amountRecovered"
                                            id="HospitalLink"
                                            v-if="Hospital ==='HospitalLink'"
                                            v-loading="loadingOfHospital"
                                    ></div>
                                </el-tab-pane>
                                <el-tab-pane
                                        label="投诉问题"
                                        name="HospitalLinkProblem"
                                        v-loading="loadingOfHospital"
                                        element-loading-text="拼命加载中"
                                >
                                    <div
                                            class="amountRecovered"
                                            id="HospitalLinkProblem"
                                            v-if="Hospital ==='HospitalLinkProblem'"
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


    </div>
</template>

<script>
    import {
        searchTrafficCategory,searchTrafficNature,searchTrafficBrand,searchMainTypeComplaint,
        searchCosmeticsLinkCategory,searchMedicalLinkCategory,searchComplainthotspotdata
    }from "../../../../api/graphics"
    export default {
        name: "ComplaintSituationFour",
        data(){
            return{
                trafficbranddata:"",
                trafficonedata:"",
                hospitaldata:"",
                trafficdata:"",
                cosmeticsdata:"",
                firstcosmeticsdata:"",
                lastcosmeticsdata:"",
                firsthospitaldata:"",
                lasthospitaldata:"",
                firsttrafficdata:"",
                lasttrafficdata:"",
                firsttrafficonedata:"",
                lasttrafficonedata:"",
                firsttrafficbranddata:"",
                lasttrafficbranddata:"",
                traffic:"trafficCategory",
                trafficOne:"trafficNature",
                trafficTwo:"trafficBrand",
                Hospital:'HospitalLink',
                Cosmetics:'CosmeticsLink',
                Hospital:'HospitalLink',
                loadingOfDailyBrand:false,
                loadingOfHospital:false,
                loadingOfamountRecovered:false,
                loadingOfCosmeticsLink:false,
                loadingOftrafficCategory:false,
                loadingOftrafficNature:false,
                loadingOftrafficBrand:false,
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
                CosmeticsLinkcategoryOption: {
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
                        data: ['业务量', '调解量', '增长率', '调解率','同比']
                    },
                    grid: {
                        left: '7%',
                        right: '7%',
                        bottom: '9%',
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
                                formatter: '{value} 件'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            // min: -50,
                            // max: 100,
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
                                position: 'top'
                            },
                        },
                        {
                            name: '调解量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },{

                            name: '调解率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
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
                    ]
                },
                CosmeticsLinkProblemcategoryOption:{
                    tooltip: {},
                    series: [{
                        type: 'wordCloud',
                        gridSize: 22, //用来调整词之间的距离
                        sizeRange: [13, 50],//用来调整字的大小范围
                        rotationRange: [0, 0],//用来调整词的旋转方向
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
                        data:[],
                    }]
                },

                HospitalLinkcategoryOption: {
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
                        data: ['业务量', '调解量', '增长率', '调解率','同比']
                    },
                    grid: {
                        left: '7%',
                        right: '7%',
                        bottom: '9%',
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
                                formatter: '{value} 件'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            // min: -50,
                            // max: 100,
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
                                position: 'top'
                            },
                        },
                        {
                            name: '调解量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },{

                            name: '调解率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
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
                    ]
                },
                HospitalLinkProblemcategoryOption:{
                    tooltip: {},
                    series: [{
                        type: 'wordCloud',
                        gridSize: 22, //用来调整词之间的距离
                        sizeRange: [13, 50],//用来调整字的大小范围
                        rotationRange: [0, 0],//用来调整词的旋转方向
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
                        data:[],
                    }]
                },
                TrafficOneOption:{
                    tooltip: {},
                    series: [{
                        type: 'wordCloud',
                        gridSize: 22, //用来调整词之间的距离
                        sizeRange: [13, 50],//用来调整字的大小范围
                        rotationRange: [0, 0],//用来调整词的旋转方向
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
                        data:[],
                    }]
                },

                trafficCategoryOption:{
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
                        data: ['业务量', '调解量', '增长率', '调解率']
                    },
                    grid: {
                        left: '7%',
                        right: '7%',
                        bottom: '9%',
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
                                formatter: '{value} 件'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            // min: -50,
                            // max: 100,
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
                                position: 'top'
                            },
                        },
                        {
                            name: '调解量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },{

                            name: '调解率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        }
                    ]
                },

                TrafficNatureOption:{
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
                        data: ['业务量','增长率']
                    },
                    grid: {
                        // left: '7%',
                        // right: '7%',
                        bottom: '5%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel:{
                                rotate :20
                                //     type: 'shadow'
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '总量',
                            // min: 0,
                            // max: 250,
                            // interval: 50,
                            axisLabel: {
                                formatter: '{value} (件)'
                            }
                        },
                        {
                            type: 'value',
                            name: '增长率',
                            // min: -100,
                            // max: 100,
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
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top',
                            },
                        }
                    ]
                },

                TrafficBrandOption:{
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
                        data: ['业务量', '调解量', '增长率', '调解率']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [],
                            axisPointer: {
                                type: 'shadow'
                            }
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
                                formatter: '{value} 件'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',
                            // min: -50,
                            // max: 100,
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
                                position: 'top'
                            },
                        },
                        {
                            name: '调解量',
                            type: 'bar',
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        },{

                            name: '调解率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [],
                            label: {
                                show: true,
                                position: 'top'
                            },
                        }
                    ]
                },

                TrafficBrandProblemOption: {
                    xAxis: {
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: [120, 200, 150, 80, 70, 110, 130],
                        type: 'bar'
                    }]

                },
                TrafficBrandProblemOptionOne:{
                    tooltip: {},
                    series: [{
                        type: 'wordCloud',
                        gridSize: 22, //用来调整词之间的距离
                        sizeRange: [13, 50],//用来调整字的大小范围
                        rotationRange: [0, 0],//用来调整词的旋转方向
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
                        data:[],
                    }]
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
            }
        },
        mounted(){
            console.log("four");
            this.getCosmeticsData();
            this.getHospitalLinkCategory();
            this.getHospitalData();
            this.getTrafficCategory();
            this.getTrafficOne();
            this.getTrafficNature();
            this.getTrafficBrand();
            this.getZxData();
            this.getCosmeticsLinkCategory();
        },
        methods:{
            getCosmeticsData(){
                let params={
                    type:"010407"
                }
                searchMainTypeComplaint(params).then(res=>{
                    console.log(res)
                    for(var key in res.data) {
                        this.CosmeticsLinkProblemcategoryOption.series[0].data.push(res.data[key])
                    }
                })
            },
            getHospitalData(){
                let params={
                    type:"0114"
                }
                searchMainTypeComplaint(params).then(res=>{
                    console.log(res)
                    for(var key in res.data) {
                        this.HospitalLinkProblemcategoryOption.series[0].data.push(res.data[key])
                    }
                })
            },
            getTrafficOne(){
                let params={
                    type:"0106"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.TrafficOneOption.series[0].data.push(res.data[key])
                    }
                });
            },
            getZxData(){
                let params={
                    type:"0108"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.TrafficBrandProblemOptionOne.series[0].data.push(res.data[key])
                    }
                });
            },
            //化妆品
            CosmeticsLinkClick(tab, event) {
                if (tab.label === "投诉环节") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("CosmeticsLink");
                        if (settlementDom) {
                            this.drawCosmeticsLink();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }else if(tab.label === "投诉问题"){
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("CosmeticsLinkProblem");
                        if (settlementDom) {
                            this.drawCosmeticsLinkProblem();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }
            },
            //医疗器械
            HospitalLinkClick(tab, event) {
                if (tab.label === "投诉环节") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("HospitalLink");
                        if (settlementDom) {
                            clearInterval(interval);
                            this.drawHospitalLink();
                            // this.loadingOfsettlementRate = true;
                        }
                    }, 200);
                }else if(tab.label === "投诉问题"){
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("HospitalLinkProblem");
                        if (settlementDom) {
                            clearInterval(interval);
                            this.drawHospitalLinkProblem();
                            // this.loadingOfsettlementRate = true;
                        }
                    }, 200);
                }
            },
            TrafficCategoryClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("trafficCategory");
                        if (settlementDom) {
                            this.drawTrafficCategory();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("trafficProbleam");
                        if (mediationDom) {
                            this.drawTrafficProbleam();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            TrafficNatureClick(tab, event) {
                if (tab.label === "投诉性质") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("trafficNature");
                        if (settlementDom) {
                            this.drawTrafficNature();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("trafficNatureProblem");
                        if (mediationDom) {
                            this.drawTrafficNatureProblem();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            TrafficBrandClick(tab, event) {
                if (tab.label === "投诉品牌") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("trafficBrand");
                        if (settlementDom) {
                            this.drawTrafficBrand();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("trafficBrandProblem");
                        if (mediationDom) {
                            this.drawTrafficBrandProblem();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            // 化妆品类投诉环节
            drawCosmeticsLink() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("CosmeticsLink")
                );
                mediationRate.setOption(this.CosmeticsLinkcategoryOption, true);
            },
            drawCosmeticsLinkProblem() {

                let mediationRate = this.$echarts.init(
                    document.getElementById("CosmeticsLinkProblem")
                );
                mediationRate.setOption(this.CosmeticsLinkProblemcategoryOption, true);
            },
            // 医疗器械类投诉环节
            drawHospitalLink() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("HospitalLink")
                );
                mediationRate.setOption(this.HospitalLinkcategoryOption, true);
            },
            drawHospitalLinkProblem() {

                let mediationRate = this.$echarts.init(
                    document.getElementById("HospitalLinkProblem")
                );
                mediationRate.setOption(this.HospitalLinkProblemcategoryOption, true);
            },

            // 交通工具类产品具体投诉品类
            drawTrafficCategory() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficCategory")
                );
                mediationRate.setOption(this.trafficCategoryOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 交通工具类产品具体投诉品类--投诉问题
            drawTrafficProbleam() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficProbleam")
                );
                mediationRate.setOption(this.TrafficOneOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 交通工具类产品具体投诉性质
            drawTrafficNature() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficNature")
                );
                mediationRate.setOption(this.TrafficNatureOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 交通工具类产品具体投诉性质--投诉问题
            drawTrafficNatureProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficNatureProblem")
                );
                mediationRate.setOption(this.TrafficOneOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 交通工具类产品具体投诉品牌
            drawTrafficBrand() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficBrand")
                );
                mediationRate.setOption(this.TrafficBrandOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 交通工具类产品具体投诉品牌--投诉内容
            drawTrafficBrandProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("trafficBrandProblem")
                );
                // mediationRate.setOption(this.TrafficBrandProblemOption, true);
                mediationRate.setOption(this.TrafficOneOption, true);
            },

            //化妆品投诉环节数据
            getCosmeticsLinkCategory() {
                this.loadingOfCosmeticsLink=true
                this.CosmeticsLinkcategoryOption.xAxis[0].data=[]
                this.CosmeticsLinkcategoryOption.series[0].data=[]
                this.CosmeticsLinkcategoryOption.series[1].data=[]
                this.CosmeticsLinkcategoryOption.series[2].data=[]
                this.CosmeticsLinkcategoryOption.series[3].data=[]
                this.CosmeticsLinkcategoryOption.series[4].data=[]
                let params = {
                    begindate:this.firstcosmeticsdata,
                    enddate:this.lastcosmeticsdata
                };
                searchCosmeticsLinkCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.CosmeticsLinkcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.CosmeticsLinkcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.CosmeticsLinkcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        this.CosmeticsLinkcategoryOption.series[4].data.push(res.data.this[key].toeconomytotal);//msx
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.CosmeticsLinkcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.CosmeticsLinkcategoryOption.series[2].data.push(100)
                        }else{
                            this.CosmeticsLinkcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.CosmeticsLinkcategoryOption.series[3].data.push(0)
                        }else{
                            this.CosmeticsLinkcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfCosmeticsLink=false
                    this.drawCosmeticsLink();
                });
            },
            //医疗器械投诉环节数据
            getHospitalLinkCategory() {
                this.loadingOfHospital=true
                this.HospitalLinkcategoryOption.xAxis[0].data=[]
                this.HospitalLinkcategoryOption.series[0].data=[]
                this.HospitalLinkcategoryOption.series[1].data=[]
                this.HospitalLinkcategoryOption.series[2].data=[]
                this.HospitalLinkcategoryOption.series[3].data=[]
                this.HospitalLinkcategoryOption.series[4].data=[]
                let params = {
                    begindate:this.firsthospitaldata,
                    enddate:this.lasthospitaldata
                };
                searchMedicalLinkCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.HospitalLinkcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.HospitalLinkcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.HospitalLinkcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        this.HospitalLinkcategoryOption.series[4].data.push(res.data.this[key].toeconomytotal);//msx
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.HospitalLinkcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.HospitalLinkcategoryOption.series[2].data.push(100)
                        }else{
                            this.HospitalLinkcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.HospitalLinkcategoryOption.series[3].data.push(0)
                        }else{
                            this.HospitalLinkcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfHospital=false
                    this.drawHospitalLink();
                });
            },
            getTrafficCategory() {
                this.loadingOftrafficCategory=true
                this.trafficCategoryOption.xAxis[0].data=[]
                this.trafficCategoryOption.series[0].data=[]
                this.trafficCategoryOption.series[1].data=[]
                this.trafficCategoryOption.series[2].data=[]
                this.trafficCategoryOption.series[3].data=[]
                let params = {
                    begindate:this.firsttrafficdata,
                    enddate:this.lasttrafficdata
                };
                searchTrafficCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.trafficCategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.trafficCategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.trafficCategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.trafficCategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.trafficCategoryOption.series[2].data.push(100)
                        }else{
                            this.trafficCategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.trafficCategoryOption.series[3].data.push(0)
                        }else{
                            this.trafficCategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOftrafficCategory=false
                    this.drawTrafficCategory();
                });
            },
            getTrafficNature() {
                this.loadingOftrafficNature=true
                this.TrafficNatureOption.xAxis[0].data=[]
                this.TrafficNatureOption.series[0].data=[]
                this.TrafficNatureOption.series[1].data=[]
                let params = {
                    begindate:this.firsttrafficonedata,
                    enddate:this.lasttrafficonedata
                };
                searchTrafficNature(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.TrafficNatureOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.TrafficNatureOption.series[0].data.push(res.data.this[key].overalltotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.TrafficNatureOption.series[1].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.TrafficNatureOption.series[1].data.push(100)
                        }else{
                            this.TrafficNatureOption.series[1].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOftrafficNature=false
                    this.drawTrafficNature();
                    this.drawTrafficNatureProblem();
                });
            },
            getTrafficBrand() {
                this.loadingOftrafficBrand=true
                this.TrafficBrandOption.xAxis[0].data=[]
                this.TrafficBrandOption.series[0].data=[]
                this.TrafficBrandOption.series[1].data=[]
                this.TrafficBrandOption.series[2].data=[]
                this.TrafficBrandOption.series[3].data=[]
                let params = {
                    begindate:this.firsttrafficbranddata,
                    enddate:this.lasttrafficbranddata
                };
                searchTrafficBrand(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.TrafficBrandOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.TrafficBrandOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.TrafficBrandOption.series[1].data.push(res.data.this[key].endtotal);
                        this.TrafficBrandOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.TrafficBrandOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.TrafficBrandOption.series[2].data.push(100)
                        }else{
                            this.TrafficBrandOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOftrafficBrand=false
                    this.drawTrafficBrand();
                });
            },
            searchcosmeticsData(){
                if(this.cosmeticsdata[0]==null){
                    this.firstcosmeticsdata=this.cosmeticsdata[0]
                    this.lastcosmeticsdata=this.cosmeticsdata[1]
                    this.getCosmeticsLinkCategory()
                }else{
                    this.firstcosmeticsdata=this.$moment(this.cosmeticsdata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastcosmeticsdata=this.$moment(this.cosmeticsdata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getCosmeticsLinkCategory()
                }
            },
            searchHospitalData(){
                if(this.hospitaldata[0]==null){
                    this.firsthospitaldata=this.hospitaldata[0]
                    this.lasthospitaldata=this.hospitaldata[1]
                    this.getHospitalLinkCategory()
                }else{
                    this.firsthospitaldata=this.$moment(this.hospitaldata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthospitaldata=this.$moment(this.hospitaldata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getHospitalLinkCategory()
                }
            },
            searchTrafficData(){
                if(this.trafficdata[0]==null){
                    this.firsttrafficdata=this.trafficdata[0]
                    this.lasttrafficdata=this.trafficdata[1]
                    this.getTrafficCategory()
                }else{
                    this.firsttrafficdata=this.$moment(this.trafficdata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasttrafficdata=this.$moment(this.trafficdata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getTrafficCategory()
                }
            },
            searchTrafficOneData(){
                if(this.trafficonedata[0]==null){
                    this.firsttrafficonedata=this.trafficonedata[0]
                    this.firsttrafficonedata=this.trafficonedata[1]
                    this.getTrafficNature()
                }else{
                    this.firsttrafficonedata=this.$moment(this.trafficonedata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasttrafficonedata=this.$moment(this.trafficonedata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getTrafficNature()
                }
            },
            searchTrafficBrandData(){
                if(this.trafficbranddata[0]==null){
                    this.firsttrafficbranddata=this.trafficbranddata[0]
                    this.lasttrafficbranddata=this.trafficbranddata[1]
                    this.getTrafficBrand()
                }else{
                    this.firsttrafficbranddata=this.$moment(this.trafficbranddata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasttrafficbranddata=this.$moment(this.trafficbranddata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getTrafficBrand()
                }
            },
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
        height: 650px;

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