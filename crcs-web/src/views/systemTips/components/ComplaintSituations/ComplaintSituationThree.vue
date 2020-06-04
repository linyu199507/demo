<template>
    <div class="complaint">
    <el-card >
        <el-card id="toOnes">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>装修建材类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="decorationdata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchDecorationData()">查询</el-button>
                                <el-tabs v-model="Decoration" @tab-click="DecorationClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="DecorationCategory"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="DecorationCategory"
                                                v-loading="loadingOfbuilding"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="DecorationProblem"
                                            v-loading="loadingOfbuildingproblem"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="DecorationProblem"

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

        <el-card id="toTwos">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>房屋类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="housedata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchHouseData()">查询</el-button>
                                <el-tabs v-model="HouseModel" @tab-click="HouseClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="HouseCategory"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="Housingcategory"
                                                v-loading="loadingOfhouse"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="DecorationProblem"
                                            v-loading="loadingOfhouseproblem"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="HousingcategoryProblem"

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

        <el-card id="toThrees">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>食品类产品具体投诉品类</p>
                                </div>
                                <el-date-picker
                                        v-model="fooddata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchFoodData()">查询</el-button>
                                <el-tabs v-model="FoodModel" @tab-click="foodClick">
                                    <el-tab-pane
                                            label="投诉品类"
                                            name="FoodCategory"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="Foodcategory"
                                                v-loading="loadingOfFood"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="HouseCategoryProblem"
                                            v-loading="loadingOfamountRecovered"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="FoodcategoryProblem"

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

        <el-card id="toFours">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>食品类产品具体投诉环节</p>
                                </div>
                                <el-date-picker
                                        v-model="foodlinkdata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchFoodLinkData()">查询</el-button>
                                <el-tabs v-model="Food" @tab-click="FoodLinkClick">
                                    <el-tab-pane
                                            label="投诉环节"
                                            name="FoodLink"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="FoodLink"
                                                v-if="Food ==='FoodLink'"
                                                v-loading="loadingOfFoodLink"
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

        <el-card id="toFires">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>药品类产品具体投诉环节</p>
                                </div>
                                <el-date-picker
                                        v-model="medicinedata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchMedicineData()">查询</el-button>
                                <el-tabs v-model="Medicine" @tab-click="MedicineLinkClick">
                                    <el-tab-pane
                                            label="投诉环节"
                                            name="MedicineLink"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="MedicineLink"
                                                v-loading="loadingOfMedicineLink"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="MedicineLinkProblem"
                                            v-loading="loadingOfDailyBrand"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="MedicineLinkProblem"
                                                v-if="Medicine ==='MedicineLinkProblem'"
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

        <el-card id="toSixs">
        <div class="businessVolume mt20" >
            <el-col class="businessVolume mt20">
                <el-row :gutter="20">
                    <el-col :xs="24" :sm="24" :md="16" :lg="24">
                        <div class="grid-content bg-purple doublePie">
                            <el-card class="box-card dealCart">
                                <div class="settlementRateTitle mapTitle">
                                    <p>保健品类产品具体投诉环节</p>
                                </div>
                                <el-date-picker
                                        v-model="healthfooddata"
                                        type="daterange"
                                        align="center"
                                        placeholder="选择日期范围"
                                        :picker-options="pickerOptionsOne"
                                ></el-date-picker>
                                <el-button type="primary" @click="searchHealthFoodData()">查询</el-button>
                                <el-tabs v-model="Health" @tab-click="HealthFoodLinkClick">
                                    <el-tab-pane
                                            label="投诉环节"
                                            name="HealthFoodLink"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="HealthFoodLink"
                                                v-if="Health ==='HealthFoodLink'"
                                                v-loading="loadingOfHealthFoodLink"
                                        ></div>
                                    </el-tab-pane>
                                    <el-tab-pane
                                            label="投诉问题"
                                            name="HealthFoodLinkProblem"
                                            v-loading="loadingOfDailyBrand"
                                            element-loading-text="拼命加载中"
                                    >
                                        <div
                                                class="amountRecovered"
                                                id="HealthFoodLinkProblem"
                                                v-if="Health ==='HealthFoodLinkProblem'"
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
    </el-card>
    </div>
</template>

<script>
    import {
        searchMainTypeComplaint,searchMedicineLinkCategory,
        searchHouseCategory,searchDecoration,searchFoodCategory,searchFoodLinkCategory,
        searchHealthFoodLinkCategory
    }from"../../../../api/graphics"
    export default {
        name: "ComplaintSituationThree",
        data() {
            return {
                decorationdata:"",
                housedata:"",
                fooddata:"",
                foodlinkdata:"",
                medicinedata:"",
                healthfooddata:"",
                firstdecorationdata:"",
                lastdecorationdata:"",
                firsthousedata:"",
                lasthousedata:"",
                firstfooddata:"",
                lastfooddata:"",
                firstfoodlinkdata:"",
                lastfoodlinkdata:"",
                firstmedicinedata:"",
                lastmedicinedata:"",
                firsthealthfooddata:"",
                lasthealthfooddata:"",
                Health:"HealthFoodLink",
                Medicine:"MedicineLink",
                loadingOfDailyBrand: false,
                Food:'FoodLink',
                loadingOfamountRecovered:false,
                loadingOfbuilding:false,
                loadingOfbuildingproblem:false,
                loadingOfhouse:false,
                loadingOfhouseproblem:false,
                loadingOfHealthFoodLink:false,
                loadingOfMedicineLink:false,
                loadingOfFoodLink:false,
                Decoration:'DecorationCategory',
                HouseModel:'HouseCategory',
                FoodModel:'FoodCategory',
                loadingOfFood:false,
                DecorationCategoryOption:{
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
                //房屋类
                HousingcategoryOption: {
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
                HousingcategoryProblemOptionOne:{
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
                //食品类
                FoodcategoryOption: {
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
                FoodcategoryProblemOptionOne:{
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

                FoodLinkcategoryOption: {
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
                HealthFoodLinkcategoryOption: {
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
                HealthFoodLinkProblemcategoryOption:{
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
                MedicineLinkcategoryOption: {
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
                MedicineLinkProblemcategoryOption:{
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

                //------------------------分割线-------------------------------
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
            console.log("three");
            this.getDecorationCategoryOption();
            this.getZxData();
            this. getHouseCategory();
            this.getHouseData();
            this.getFoodData();
            this.getMedicineData();
            this.getFoodLinkCategory();
            this.getFoodCategory();
            this.getHealthFoodData();
            this.getMedicineLinkCategory();
            this.getHealthFoodLinkCategory()
        },
        methods:{
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
            getHouseData(){
                let params={
                    type:"0110"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.HousingcategoryProblemOptionOne.series[0].data.push(res.data[key])
                    }
                });
            },
            getFoodData(){
                let params={
                    type:"0105"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.FoodcategoryProblemOptionOne.series[0].data.push(res.data[key])
                    }
                });
            },
            getHealthFoodData(){
                let params={
                    type:"0112"
                }
                searchMainTypeComplaint(params).then(res=>{
                    console.log(res)
                    for(var key in res.data) {
                        this.HealthFoodLinkProblemcategoryOption.series[0].data.push(res.data[key])
                    }
                })
            },
            getMedicineData(){
                let params={
                    type:"0113"
                }
                searchMainTypeComplaint(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.MedicineLinkProblemcategoryOption.series[0].data.push(res.data[key])
                    }
                });
            },
            DecorationClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("DecorationCategory");
                        if (settlementDom) {
                            this.drawDecoration();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("DecorationProblem");
                        if (mediationDom) {
                            this.drawDecorationProblem();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            //房屋类
            HouseClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("Housingcategory");
                        if (settlementDom) {
                            this.drawHouse();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("HousingcategoryProblem");
                        if (mediationDom) {
                            this.drawHouseProblem();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },
            //食品类
            foodClick(tab, event) {
                if (tab.label === "投诉品类") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("Foodcategory");
                        if (settlementDom) {
                            this.drawFood();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                } else if (tab.label === "投诉问题") {
                    const interval1 = setInterval(() => {
                        let mediationDom = document.getElementById("FoodcategoryProblem");
                        if (mediationDom) {
                            this.drawFoodProblem();
                            clearInterval(interval1);
                        }
                    }, 200);
                }
            },

            FoodLinkClick(tab, event) {
                if (tab.label === "投诉环节") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("FoodLink");
                        if (settlementDom) {
                            this.drawFoodLink();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }
            },
            MedicineLinkClick(tab, event) {
                if (tab.label === "投诉环节") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("MedicineLink");
                        if (settlementDom) {
                            this.drawMedicineLink();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }else if(tab.label === "投诉问题"){
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("MedicineLinkProblem");
                        if (settlementDom) {
                            this.drawMedicineLinkProblem();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }
            },
            HealthFoodLinkClick(tab, event) {
                if (tab.label === "投诉环节") {
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("HealthFoodLink");
                        if (settlementDom) {
                            this.drawHealthFoodLink();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }else if(tab.label === "投诉问题"){
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("HealthFoodLinkProblem");
                        if (settlementDom) {
                            this.drawHealthFoodLinkProblem();
                            // this.loadingOfsettlementRate = true;
                            clearInterval(interval);
                        }
                    }, 200);
                }
            },
            drawDecoration() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("DecorationCategory")
                );
                //mediationRate.setOption(this.TrafficBrandProblemOption, true);
                mediationRate.setOption(this.DecorationCategoryOption, true);
            },

            // 装修建材类产品具体投诉品类--投诉问题
            drawDecorationProblem() {
                //8888888
                let mediationRate = this.$echarts.init(
                    document.getElementById("DecorationProblem")
                );
                mediationRate.setOption(this.TrafficBrandProblemOptionOne, true);
            },
            // 房屋类产品具体投诉品类
            drawHouse() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("Housingcategory")
                );
                mediationRate.setOption(this.HousingcategoryOption, true);
            },

            // 房屋类产品具体投诉品类--投诉问题
            drawHouseProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("HousingcategoryProblem")
                );
                mediationRate.setOption(this.HousingcategoryProblemOptionOne, true);
            },

            // 食品类产品具体投诉品类
            drawFood() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("Foodcategory")
                );
                mediationRate.setOption(this.FoodcategoryOption, true);
            },

            // 食品类产品具体投诉品类--投诉问题
            drawFoodProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("FoodcategoryProblem")
                );
                mediationRate.setOption(this.FoodcategoryProblemOptionOne, true);
            },
            // 食品类投诉环节
            drawFoodLink() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("FoodLink")
                );
                mediationRate.setOption(this.FoodLinkcategoryOption, true);
            },
            // 药品类投诉环节
            drawMedicineLink() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("MedicineLink")
                );
                mediationRate.setOption(this.MedicineLinkcategoryOption, true);
            },
            drawMedicineLinkProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("MedicineLinkProblem")
                );
                mediationRate.setOption(this.MedicineLinkProblemcategoryOption, true);
            },
            // 保健品类投诉环节
            drawHealthFoodLink() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("HealthFoodLink")
                );
                mediationRate.setOption(this.HealthFoodLinkcategoryOption, true);
            },
            drawHealthFoodLinkProblem() {

                let mediationRate = this.$echarts.init(
                    document.getElementById("HealthFoodLinkProblem")
                );
                mediationRate.setOption(this.HealthFoodLinkProblemcategoryOption, true);
            },

            getDecorationCategoryOption(){
                this.loadingOfbuilding=true
                this.DecorationCategoryOption.xAxis[0].data=[]
                this.DecorationCategoryOption.series[0].data=[]
                this.DecorationCategoryOption.series[1].data=[]
                this.DecorationCategoryOption.series[2].data=[]
                this.DecorationCategoryOption.series[3].data=[]
                let params = {
                    begindate:this.firstdecorationdata,
                    enddate:this.lastdecorationdata
                };
                searchDecoration(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.DecorationCategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.DecorationCategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.DecorationCategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.DecorationCategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.DecorationCategoryOption.series[2].data.push(100)
                        }else{
                            this.DecorationCategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.DecorationCategoryOption.series[3].data.push(0)
                        }else{
                            this.DecorationCategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfbuilding=false
                    this.drawDecoration();
                });
            },
            getHouseCategory() {
                this.loadingOfhouse=true
                this.HousingcategoryOption.xAxis[0].data=[]
                this.HousingcategoryOption.series[0].data=[]
                this.HousingcategoryOption.series[1].data=[]
                this.HousingcategoryOption.series[2].data=[]
                this.HousingcategoryOption.series[3].data=[]
                let params = {
                    begindate:this.firsthousedata,
                    enddate:this.lasthousedata
                };
                searchHouseCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.HousingcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.HousingcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.HousingcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.HousingcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.HousingcategoryOption.series[2].data.push(100)
                        }else{
                            this.HousingcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.HousingcategoryOption.series[3].data.push(0)
                        }else{
                            this.HousingcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfhouse=false
                    this.drawHouse();
                });
            },
            //食品
            getFoodCategory() {
                this.loadingOfFood=true
                this.FoodcategoryOption.xAxis[0].data=[]
                this.FoodcategoryOption.series[0].data=[]
                this.FoodcategoryOption.series[1].data=[]
                this.FoodcategoryOption.series[2].data=[]
                this.FoodcategoryOption.series[3].data=[]
                let params = {
                    begindate:this.firstfooddata,
                    enddate:this.lastfooddata
                };
                searchFoodCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.FoodcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.FoodcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.FoodcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.FoodcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.FoodcategoryOption.series[2].data.push(100)
                        }else{
                            this.FoodcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.FoodcategoryOption.series[3].data.push(0)
                        }else{
                            this.FoodcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfFood=false
                    this.drawFood();
                });
            },

            //投诉环节数据
            getFoodLinkCategory() {
                this.loadingOfFoodLink=true
                this.FoodLinkcategoryOption.xAxis[0].data=[]
                this.FoodLinkcategoryOption.series[0].data=[]
                this.FoodLinkcategoryOption.series[1].data=[]
                this.FoodLinkcategoryOption.series[2].data=[]
                this.FoodLinkcategoryOption.series[3].data=[]
                this.FoodLinkcategoryOption.series[4].data=[]
                let params = {
                    begindate:this.firstfoodlinkdata,
                    enddate:this.lastfoodlinkdata
                };
                searchFoodLinkCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.FoodLinkcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.FoodLinkcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.FoodLinkcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        this.FoodLinkcategoryOption.series[4].data.push(res.data.this[key].toeconomytotal);//msx
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.FoodLinkcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.FoodLinkcategoryOption.series[2].data.push(100)
                        }else{
                            this.FoodLinkcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.FoodLinkcategoryOption.series[3].data.push(0)
                        }else{
                            this.FoodLinkcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfFoodLink=false
                    this.drawFoodLink();
                });
            },
            //保健品投诉环节数据
            getHealthFoodLinkCategory() {
                this.loadingOfHealthFoodLink=true
                this.HealthFoodLinkcategoryOption.xAxis[0].data=[]
                this.HealthFoodLinkcategoryOption.series[0].data=[]
                this.HealthFoodLinkcategoryOption.series[1].data=[]
                this.HealthFoodLinkcategoryOption.series[2].data=[]
                this.HealthFoodLinkcategoryOption.series[3].data=[]
                this.HealthFoodLinkcategoryOption.series[4].data=[]
                let params = {
                    begindate:this.firsthealthfooddata,
                    enddate:this.lasthealthfooddata
                };
                searchHealthFoodLinkCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.HealthFoodLinkcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.HealthFoodLinkcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.HealthFoodLinkcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        this.HealthFoodLinkcategoryOption.series[4].data.push(res.data.this[key].toeconomytotal);//msx
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.HealthFoodLinkcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.HealthFoodLinkcategoryOption.series[2].data.push(100)
                        }else{
                            this.HealthFoodLinkcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.HealthFoodLinkcategoryOption.series[3].data.push(0)
                        }else{
                            this.HealthFoodLinkcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfHealthFoodLink=false
                    this.drawHealthFoodLink();
                });
            },
            //药品投诉环节数据
            getMedicineLinkCategory() {
                this.loadingOfMedicineLink=true
                this.MedicineLinkcategoryOption.xAxis[0].data=[]
                this.MedicineLinkcategoryOption.series[0].data=[]
                this.MedicineLinkcategoryOption.series[1].data=[]
                this.MedicineLinkcategoryOption.series[2].data=[]
                this.MedicineLinkcategoryOption.series[3].data=[]
                this.MedicineLinkcategoryOption.series[4].data=[]
                let params = {
                    begindate:this.firstmedicinedata,
                    enddate:this.lastmedicinedata
                };
                searchMedicineLinkCategory(params).then(res => {
                    console.log(res);
                    for (var key in res.data.this) {
                        this.MedicineLinkcategoryOption.xAxis[0].data.push(res.data.this[key].bustype);
                        this.MedicineLinkcategoryOption.series[0].data.push(res.data.this[key].overalltotal);
                        this.MedicineLinkcategoryOption.series[1].data.push(res.data.this[key].endtotal);
                        this.MedicineLinkcategoryOption.series[4].data.push(res.data.this[key].toeconomytotal);//msx
                        if(res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal != 0 || res.data.this[key].overalltotal === 0 && res.data.last[key].overalltotal === 0){
                            this.MedicineLinkcategoryOption.series[2].data.push(0)
                        }else if(res.data.this[key].overalltotal != 0 && res.data.last[key].overalltotal === 0){
                            this.MedicineLinkcategoryOption.series[2].data.push(100)
                        }else{
                            this.MedicineLinkcategoryOption.series[2].data.push(Math.round((res.data.this[key].overalltotal-res.data.last[key].overalltotal)/res.data.last[key].overalltotal*100));
                        }
                        if(res.data.this[key].endtotal === 0){
                            this.MedicineLinkcategoryOption.series[3].data.push(0)
                        }else{
                            this.MedicineLinkcategoryOption.series[3].data.push(Math.round(res.data.this[key].endtotal/res.data.this[key].overalltotal*100));
                        }
                    }
                    this.loadingOfMedicineLink=false
                    this.drawMedicineLink();
                });
            },
            searchDecorationData(){
                if(this.decorationdata[0]==null){
                    this.firstdecorationdata=this.decorationdata[0]
                    this.lastdecorationdata=this.decorationdata[1]
                    this.getDecorationCategoryOption()
                }else{
                    this.firstdecorationdata=this.$moment(this.decorationdata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastdecorationdata=this.$moment(this.decorationdata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getDecorationCategoryOption()
                }
            },
            searchHouseData(){
                if(this.housedata[0]==null){
                    this.firsthousedata=this.housedata[0]
                    this.lasthousedata=this.housedata[1]
                    this.getHouseCategory()
                }else{
                    this.firsthousedata=this.$moment(this.housedata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthousedata=this.$moment(this.housedata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getHouseCategory()
                }
            },
            searchFoodData(){
                if(this.fooddata[0]==null){
                    this.firstfooddata=this.fooddata[0]
                    this.lastfooddata=this.fooddata[1]
                    this.getFoodCategory()
                }else{
                    this.firstfooddata=this.$moment(this.fooddata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastfooddata=this.$moment(this.fooddata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getFoodCategory()
                }
            },
            searchFoodLinkData(){
                if(this.foodlinkdata[0]==null){
                    this.firstfoodlinkdata=this.foodlinkdata[0]
                    this.lastfoodlinkdata=this.foodlinkdata[1]
                    this.getFoodLinkCategory()
                }else{
                    this.firstfoodlinkdata=this.$moment(this.foodlinkdata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastfoodlinkdata=this.$moment(this.foodlinkdata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getFoodLinkCategory()
                }
            },
            searchMedicineData(){
                if(this.medicinedata[0]==null){
                    this.firstmedicinedata=this.medicinedata[0]
                    this.lastmedicinedata=this.medicinedata[1]
                    this.getMedicineLinkCategory()
                }else{
                    this.firstmedicinedata=this.$moment(this.medicinedata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lastmedicinedata=this.$moment(this.medicinedata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getMedicineLinkCategory()
                }
            },
            searchHealthFoodData(){
                if(this.healthfooddata[0]==null){
                    this.firsthealthfooddata=this.healthfooddata[0]
                    this.lasthealthfooddata=this.healthfooddata[1]
                    this.getHealthFoodLinkCategory()
                }else{
                    this.firsthealthfooddata=this.$moment(this.healthfooddata[0]).format("YYYY-MM-DD HH:mm:ss");
                    this.lasthealthfooddata=this.$moment(this.healthfooddata[1]).format("YYYY-MM-DD HH:mm:ss");
                    this.getHealthFoodLinkCategory()
                }
            }

        }
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
        height: 630px;

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