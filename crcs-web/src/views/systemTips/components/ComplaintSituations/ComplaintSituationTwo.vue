<template>
    <div class="complaint">
    <el-card id="toOne">
        <el-row :gutter="20">
            <el-col :xs="24" :sm="24" :md="16" :lg="24">
        <div class="businessVolume mt20">
            <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>家用电器类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="homedata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchHomeData()">查询</el-button>
                                <el-tabs v-model="appliances" @tab-click="appliancesClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="appliancesCategory"
                                            v-loading="loadingOfappliancesCategory"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="appliancesCategory"
                                                v-if="appliances==='appliancesCategory'">

                                        </div>
                                    </el-tab-pane>



                                    <el-tab-pane
                                            label="投诉问题"
                                            name="appliancesCategoryTwo"

                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="appliancesCategoryTwo"
                                                v-if="appliances==='appliancesCategoryTwo'"
                                                v-loading="loadingOfappliancesBrand"
                                        >

                                        </div>
                                    </el-tab-pane>
                                </el-tabs>
                            </el-card>
                        </div>
                    </el-col>
                </el-row>
            </el-col>
            </div>
        </div>
            </el-col>
        </el-row>
    </el-card>
    <el-card id="toTwo">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>家用电器类产品具体投诉性质</p>
                                </div>
                                <el-date-picker
                                        v-model="homecomplaintdata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchHomeComplaintDate()">查询</el-button>
                                <el-tabs v-model="appliancesOne" @tab-click="appliancesOneClick">
                                    <el-tab-pane
                                            label="投诉性质"
                                            name="appliancesNature"
                                            element-loading-text="拼命加载中">
                                        <div    height="485px"
                                                class="mediationRate"
                                                id="appliancesNature"
                                                v-if="appliancesOne==='appliancesNature'"
                                                v-loading="loadingOfhousehold">
                                        </div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="appliancesNatureTwo"
                                            v-loading="loadingOfappliancesNatureTwo"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="appliancesNatureTwo"
                                                v-if="appliancesOne==='appliancesNatureTwo'">

                                        </div>
                                    </el-tab-pane>
                                </el-tabs>
                            </el-card>
                        </div>
                    </el-col>
                </el-row>
            </el-col>
        </div>
    </el-card>
    <el-card id="toThree">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>家用电器类产品具体投诉品牌</p>
                                </div>
                                <el-date-picker
                                        v-model="homebranddata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchBrandDate()">查询</el-button>

                                <el-tabs v-model="appliancesTwo" @tab-click="appliancesBrandClick">
                                    <el-tab-pane
                                            label="投诉品牌"
                                            name="appliancesBrand"
                                            element-loading-text="拼命加载中">
                                        <div    height="485px"
                                                class="mediationRate"
                                                id="appliancesBrand"
                                                v-if="appliancesTwo==='appliancesBrand'"
                                                v-loading="loadingOfappliancesBrand">

                                        </div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="appliancesBrandOne"
                                            v-loading="loadingOfappliancesBrandOne"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="appliancesBrandOne"
                                                v-if="appliancesTwo==='appliancesBrandOne'">

                                        </div>
                                    </el-tab-pane>
                                </el-tabs>
                            </el-card>
                        </div>
                    </el-col>
                </el-row>
            </el-col>
        </div>
    </el-card>
    <el-card id="toFour">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>日用百货类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="dailydata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchDailyDate()">查询</el-button>
                                <el-tabs v-model="Daily" @tab-click="DailyCategoryClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="DailyCategory"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="DailyCategory"
                                                v-if="Daily==='DailyCategory'"
                                                v-loading="loadingOfDailyCategory">

                                        </div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="DailyProblean"
                                            v-loading="loadingOfDailyCategory"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="mediationRate"
                                                id="DailyProblean"
                                                v-if="Daily==='DailyProblean'">

                                        </div>
                                    </el-tab-pane>
                                    <!--<div-->
                                    <!--class="amountRecovered"-->
                                    <!--id="DailyCategory"-->

                                    <!--&gt;</div>-->
                                </el-tabs>
                            </el-card>
                        </div>
                    </el-col>

                </el-row>
            </el-col>
        </div>
    </el-card>
    <el-card id="toFire">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>日用百货类产品具体投诉性质</p>
                                </div>
                                <el-date-picker
                                        v-model="dailynaturedata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchDailyNatureDate()">查询</el-button>
                                <el-tabs v-model="DailyOne" @tab-click="DailyNatureClick">
                                    <el-tab-pane
                                            label="投诉性质"
                                            name="DailyNature"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="amountRecovered"
                                                id="DailyNature"
                                                v-if="DailyOne ==='DailyNature'"
                                                v-loading="loadingOfDailyNature"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="DailyNatureProblean"
                                            v-loading="loadingOfDailyNature"
                                            element-loading-text="拼命加载中">
                                        <div
                                                class="amountRecovered"
                                                id="DailyNatureProblean"
                                                v-if="DailyOne ==='DailyNatureProblean'"
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
    <el-card id="toSix">
        <div class="businessVolume mt20">
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>日用百货类产品具体投诉品牌</p>
                                </div>
                                <el-date-picker
                                        v-model="dailybranddata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchDailyBrandDate()">查询</el-button>
                                <el-tabs v-model="DailyTwo" @tab-click="DailyBrandClick">
                                    <el-tab-pane
                                            label="投诉品牌"
                                            name="DailyBrand"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="DailyBrand"
                                                v-if="DailyTwo ==='DailyBrand'"
                                                v-loading="loadingOfDailyBrand"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="DailyBrandProblean"
                                            v-loading="loadingOfDailyBrand"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="DailyBrandProblean"
                                                v-if="DailyTwo ==='DailyBrandProblean'"
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
        searchAppliancesCategory,searchAppliancesNature,searchAppliancesBrand
        ,searchDailyCategory,searchDailyNature,searchDailyBrand,
        searchMainTypeComplaint,
        }from "../../../../api/graphics";
    export default {
        name: "ComplaintSituationTwo",
        data(){
        return {
            dailybranddata:"",
            dailynaturedata:"",
            dailydata:"",
            homebranddata:"",
            firsthomedate:"",
            lasthomedate:"",
            firsthomecomplaintdate:"",
            lasthomecomplaintdate:"",
            firsthomebranddate:"",
            lasthomebranddate:"",
            firstdailyddate:"",
            lastdailydate:"",
            firstdailynaturedate:"",
            lastdailynaturedate:"",
            firstdailybranddate:"",
            lastdailybranddate:"",
            homedata:"",
            homecomplaintdata:"",
            loadingOfappliancesNatureTwo:false,
            loadingOfappliancesBrandOne:false,
            loadingOfDailyCategory:false,
            loadingOfDailyNature:false,
            loadingOfDailyBrand:false,
            DailyTwo:"DailyBrand",
            DailyOne:"DailyNature",
            Daily:"DailyCategory",
            appliancesTwo:"appliancesBrand",
            appliances: "appliancesCategory",
            loadingOfappliancesCategory: false,
            loadingOfappliancesBrand: false,
            appliancesOne: "appliancesNature",
            loadingOfappliancesNature: false,
            loadingOfhousehold: false,
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
            appliancesCategoryOption:{
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        // type: 'cross',
                        type: 'shadow',
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
                        name: '业务量',
                        min: 0,
                        // max: 250,
                        // interval: 50,
                        axisLabel: {
                            formatter: '{value} (件)',
                            show: true,
                            position: 'top'
                        },
                    },
                    {
                        type: 'value',
                        name: '比率',
                        min: -50,
                        max: 100,
                        // interval: 40,
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    },

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
                    },
                    {
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
            appliancesNatureOption:{
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
            appliancesCategoryTwoOption:{
                /*title:{
                    text:"投诉问题词云图",
                },*/
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
            appliancesBrandOption:{
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
            DailyCategoryOptionOne:{
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
                    data: ['业务量', ,'调解量','增长率','调解率']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel:{'interval':0,rotate: 25},
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
                        name: '调解量',
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
                    {
                        name: '调解率',
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
            DailyCategoryOption:{
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
            DailyBrandOption:{
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
            DailyNatureOption:{
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
            console.log("two");
            this.getAppliancesCategory();
            this.getappliancesCategoryTwodata();
            this.getAppliancesNature();
            this.getAppliancesBrand();
            this.getDailyCategory();
            this.getDayDate();
            this.getDailyNature();
            this.getDailyBrand();
        },
        methods:{
            appliancesClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("appliancesCategory");
                        if (settlementDom) {
                            this.drawappliancesCategory();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }
                else if (tab.label === "投诉问题") {
                    const interval2 = setInterval(() => {
                        let amountRecoveredDom = document.getElementById("appliancesCategoryTwo");
                        if (amountRecoveredDom) {
                            this.drawappliancesCategoryTwo();
                            clearInterval(interval2);
                        }
                    }, 200);
                }
            },
            appliancesOneClick(tab, event) {
                if (tab.label === "投诉性质") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("appliancesNature");
                        if (settlementDom) {
                            this.drawappliancesNature();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("appliancesNatureTwo");
                        if (mediationDom) {
                            this.drawappliancesNatureTwo();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            DailyNatureClick(tab, event) {
                if (tab.label === "投诉性质") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("DailyNature");
                        if (settlementDom) {
                            this.drawDailyNature();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("DailyNatureProblean");
                        if (mediationDom) {
                            this.drawDailyNatureProblean();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            DailyBrandClick(tab, event) {
                if (tab.label === "投诉品牌") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("DailyBrand");
                        if (settlementDom) {
                            this.drawDailyBrand();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("DailyBrandProblean");
                        if (mediationDom) {
                            this.drawDailyBrandProblean();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            appliancesBrandClick(tab, event) {
                if (tab.label === "投诉品牌") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("appliancesBrand");
                        if (settlementDom) {
                            this.drawappliancesBrand();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("appliancesBrandOne");
                        if (mediationDom) {
                            this.drawappliancesBrandOne();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            DailyCategoryClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("DailyCategory");
                        if (settlementDom) {
                            this.drawDailyCategory();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("DailyProblean");
                        if (mediationDom) {
                            clearInterval(interval1);
                            this.drawDailyProblean();
                            //clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            // 家用电器类产品具体投诉品类
            drawappliancesCategory() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesCategory")
                );
                mediationRate.setOption(this.appliancesCategoryOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 家用电器类产品具体投诉品类--投诉问题
            drawappliancesCategoryTwo() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesCategoryTwo")
                );
                mediationRate.setOption(this.appliancesCategoryTwoOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 家用电器类产品具体投诉性质--投诉性质
            drawappliancesNature() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesNature")
                );
                mediationRate.setOption(this.appliancesNatureOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 家用电器类产品具体投诉性质--投诉问题
            drawappliancesNatureTwo() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesNatureTwo")
                );
                mediationRate.setOption(this.appliancesCategoryTwoOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 家用电器类产品具体投诉品牌--投诉品牌
            drawappliancesBrand() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesBrand")
                );
                mediationRate.setOption(this.appliancesBrandOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 家用电器类产品具体投诉品牌--投诉问题
            drawappliancesBrandOne() {
                console.log("品牌")
                let mediationRate = this.$echarts.init(
                    document.getElementById("appliancesBrandOne")
                );
                mediationRate.setOption(this.appliancesCategoryTwoOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 日用百货类产品具体投诉品类
            drawDailyCategory() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyCategory")
                );
                mediationRate.setOption(this.DailyCategoryOptionOne, true);
                // this.loadingOfmediationRate = false;
            },
            // 日用百货类产品具体投诉品类--投诉问题
            drawDailyProblean() {

                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyProblean")
                );
                mediationRate.setOption(this.DailyCategoryOption, true);
            },

            // 日用百货类产品具体投诉性质
            drawDailyNature() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyNature")
                );
                mediationRate.setOption(this.DailyNatureOption, true);

                // this.loadingOfmediationRate = false;
            },
            // 日用百货类产品具体投诉性质--投诉问题
            drawDailyNatureProblean() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyNatureProblean")
                );
                mediationRate.setOption(this.DailyCategoryOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 日用百货类产品具体投诉品牌
            drawDailyBrand() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyBrand")
                );
                mediationRate.setOption(this.DailyBrandOption, true);
                // this.loadingOfmediationRate = false;
            },

            // 日用百货类产品具体投诉品牌--投诉问题
            drawDailyBrandProblean() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DailyBrandProblean")
                );
                mediationRate.setOption(this.DailyCategoryOption, true);
                // this.loadingOfmediationRate = false;
            },

            getAppliancesCategory() {
                this.loadingOfappliancesCategory=true
                this.appliancesCategoryOption.xAxis[0].data=[]
                this.appliancesCategoryOption.series[0].data=[]
                this.appliancesCategoryOption.series[1].data=[]
                this.appliancesCategoryOption.series[2].data=[]
                this.appliancesCategoryOption.series[3].data=[]
                let params = {
                    begindate:this.firsthomedate,
                    enddate:this.lasthomedate
                };
                console.log(params)
                searchAppliancesCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.appliancesCategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.appliancesCategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.appliancesCategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        if(res.data.this[key].endtotal === 0 && res.data.this[key].overalltotal ===0 ){
                            this.appliancesCategoryOption.series[3].data.push(0)
                        }else{
                            this.appliancesCategoryOption.series[3].data.push(Math.round((res.data.this[key].endtotal)/(res.data.this[key].overalltotal)*100));
                        }
                    }
                    for (var key in res.data.last) {
                        this.appliancesCategoryOption.series[2].data.push(res.data.last[key].overalltotal);
                    }
                    let one = this.appliancesCategoryOption.series[0].data
                    let two = this.appliancesCategoryOption.series[2].data

                    let listOfDateAdd = one.map(function(v, i){return v +  two[i];})
                    // console.log(listOfDateAdd)
                    let growthList = this.growth(listOfDateAdd,one,17)
                    // console.log(growthList)
                    this.appliancesCategoryOption.series[2].data = growthList
                    this.loadingOfappliancesCategory=false
                    this.drawappliancesCategory();
                });
            },
            getappliancesCategoryTwodata(){
                let params={
                    type:"0101"
                }
                this.loadingOfappliancesBrand=true
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.appliancesCategoryTwoOption.series[0].data.push(res.data[key])
                    }
                    this.loadingOfappliancesBrand=false
                    //this.drawTrafficBrandProblem();
                });
            },
            getAppliancesNature() {
                this.loadingOfhousehold=true
                this.appliancesNatureOption.xAxis[0].data=[]
                this.appliancesNatureOption.series[0].data=[]
                this.appliancesNatureOption.series[1].data=[]
                let params = {
                    begindate:this.firsthomecomplaintdate,
                    enddate:this.lasthomecomplaintdate,
                };
                console.log(params)
                searchAppliancesNature(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.appliancesNatureOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.appliancesNatureOption.series[0].data.push(res.data.this[key].overalltotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.appliancesNatureOption.series[1].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.appliancesNatureOption.series[1].data.push(100)
                        }else{
                            this.appliancesNatureOption.series[1].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOfhousehold=false
                    this.drawappliancesNature();
                });
            },
            getAppliancesBrand() {
                this.loadingOfappliancesBrand=true
                this.appliancesBrandOption.xAxis[0].data=[]
                this.appliancesBrandOption.series[0].data=[]
                this.appliancesBrandOption.series[1].data=[]
                this.appliancesBrandOption.series[2].data=[]
                this.appliancesBrandOption.series[3].data=[]
                let params = {
                    begindate:this.firsthomebranddate,
                    enddate:this.lasthomebranddate
                };
                searchAppliancesBrand(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.appliancesBrandOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.appliancesBrandOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.appliancesBrandOption.series[1].data.push(res.data.this[key].endtotal);
                        this.appliancesBrandOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.appliancesBrandOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.appliancesBrandOption.series[2].data.push(100)
                        }else{
                            this.appliancesBrandOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOfappliancesBrand=false
                    this.drawappliancesBrand();
                });
            },
            getDailyCategory() {
                this.loadingOfDailyCategory=true
                this.DailyCategoryOptionOne.xAxis[0].data=[]
                this.DailyCategoryOptionOne.series[0].data=[]
                this.DailyCategoryOptionOne.series[1].data=[]
                this.DailyCategoryOptionOne.series[2].data=[]
                this.DailyCategoryOptionOne.series[3].data=[]
                let params = {
                    begindate:this.firstdailyddate,
                    enddate:this.lastdailydate
                };
                searchDailyCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.DailyCategoryOptionOne.xAxis[0].data.push(res.data.this[key].bustype);
                        this.DailyCategoryOptionOne.series[0].data.push(res.data.this[key].overalltotal);
                        this.DailyCategoryOptionOne.series[1].data.push(res.data.this[key].endtotal);
                        this.DailyCategoryOptionOne.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.DailyCategoryOptionOne.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.DailyCategoryOptionOne.series[2].data.push(100)
                        }else{
                            this.DailyCategoryOptionOne.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOfDailyCategory=false
                    this.drawDailyCategory();
                });
            },
            getDayDate(){
                console.log("bbbbb")
                let params={
                    type:"0104"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.DailyCategoryOption.series[0].data.push(res.data[key])
                    }
                });
            },
            getDailyNature() {
                this.loadingOfDailyNature=true
                this.DailyNatureOption.xAxis[0].data=[]
                this.DailyNatureOption.series[0].data=[]
                this.DailyNatureOption.series[1].data=[]
                let params = {
                    begindate:this.firstdailynaturedate,
                    enddate:this.lastdailynaturedate
                };
                searchDailyNature(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.DailyNatureOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.DailyNatureOption.series[0].data.push(res.data.this[key].overalltotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.DailyNatureOption.series[1].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.DailyNatureOption.series[1].data.push(100)
                        }else{
                            this.DailyNatureOption.series[1].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOfDailyNature=false
                    this.drawDailyNature();
                });
            },

            getDailyBrand() {
                this.loadingOfDailyBrand=true
                this.DailyBrandOption.xAxis[0].data=[]
                this.DailyBrandOption.series[0].data=[]
                this.DailyBrandOption.series[1].data=[]
                this.DailyBrandOption.series[2].data=[]
                this.DailyBrandOption.series[3].data=[]
                let params = {
                    begindate:this.firstdailybranddate,
                    enddate:this.lastdailybranddate
                };
                console.log(params)
                searchDailyBrand(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.DailyBrandOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.DailyBrandOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.DailyBrandOption.series[1].data.push(res.data.this[key].endtotal);
                        this.DailyBrandOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.DailyBrandOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.DailyBrandOption.series[2].data.push(100)
                        }else{
                            this.DailyBrandOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                    }
                    this.loadingOfDailyBrand=false
                    this.drawDailyBrand();
                });
            },
            searchHomeData(){
                if(this.homedata[0]==null){
                    this.firsthomedate=this.homedata[0]
                    this.lasthomedate=this.homedata[1]
                    this.getAppliancesCategory()
                }else{
                    this.firsthomedate=this.$moment(this.homedata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthomedate=this.$moment(this.homedata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getAppliancesCategory()
                }

            },
            searchHomeComplaintDate(){
                if(this.homecomplaintdata[0]==null){
                    this.firsthomecomplaintdate=this.homecomplaintdata[0]
                    this.lasthomecomplaintdate=this.homecomplaintdata[1]
                    this.getAppliancesNature()
                }else{
                    this.firsthomecomplaintdate=this.$moment(this.homecomplaintdata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthomecomplaintdate=this.$moment(this.homecomplaintdata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getAppliancesNature()
                }
            },
            searchBrandDate(){
                if(this.homebranddata[0]==null){
                    this.firsthomebranddate=this.homebranddata[0]
                    this.lasthomebranddate=this.homebranddata[1]
                    this.getAppliancesBrand()
                }else{
                    this.firsthomebranddate=this.$moment(this.homebranddata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthomebranddate=this.$moment(this.homebranddata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getAppliancesBrand()
                }
            },
            searchDailyDate(){
                if(this.dailydata[0]==null){
                    this.firstdailyddate=this.dailydata[0]
                    this.lastdailydate=this.dailydata[1]
                    this.getDailyCategory()
                }else{
                    this.firstdailyddate=this.$moment(this.dailydata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastdailydate=this.$moment(this.dailydata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getDailyCategory()
                }
            },
            searchDailyNatureDate(){
                if(this.dailynaturedata[0]==null){
                    this.firstdailynaturedate=this.dailynaturedata[0]
                    this.firstdailynaturedate=this.dailynaturedata[1]
                    this.getDailyNature()
                }else{
                    this.firstdailynaturedate=this.$moment(this.dailynaturedata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastdailynaturedate=this.$moment(this.dailynaturedata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getDailyNature()
                }
            },
            searchDailyBrandDate(){
                if(this.dailybranddata[0]==null){
                    this.firstdailybranddate=this.dailybranddata[0]
                    this.lastdailybranddate=this.dailybranddata[1]
                    this.getDailyBrand()
                }else{
                    this.firstdailybranddate=this.$moment(this.dailybranddata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastdailybranddate=this.$moment(this.dailybranddata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getDailyBrand()
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
        width: auto;
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