<template>
    <div>
        <el-card>
            <div class="businessVolume mt20">
                <div class="businessVolume mt20">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="24" :md="16" :lg="24">
                            <div class="grid-content bg-purple doublePie">
                                <el-card class="box-card dealCart">
                                    <div class="settlementRateTitle mapTitle">
                                        <p>投诉热点类别</p>
                                    </div>
                                    <el-tabs v-model="activeModel" @tab-click="handleClick">
                                        <el-tab-pane
                                                label="投诉热点类别"
                                                name="complainthotspot"
                                                element-loading-text="拼命加载中"
                                        >
                                            <div
                                                    class="mediationRate"
                                                    id="complainthotspot"
                                                    v-loading="loadingOfComplaint"
                                            ></div>
                                        </el-tab-pane>
                                        <el-tab-pane
                                                label="投诉问题"
                                                name="appliancesCategoryTwo"
                                                v-loading="complaintHotSpotProblemloading"
                                                element-loading-text="拼命加载中">
                                            <div
                                                    class="mediationRate"
                                                    id="complainthotspotproblem"
                                                    >

                                            </div>
                                        </el-tab-pane>
                                    </el-tabs>
                                </el-card>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </el-card>

    </div>
</template>

<script>
    import {searchComplainthotspotdata,searchComplaintHotWord}from "../../../api/graphics";
    export default {
        name: "ComplaintHotSpot",
        data(){
            return{
                activeModel:"complainthotspot",
                loadingOfComplaint:false,
                complaintHotSpotProblemloading: false,
                amountReacoveredOption:  {
                baseOption: {
                    timeline: {
                        axisType: 'category',
                        autoPlay: true,
                        playInterval: 1000,
                        data: [],
                        label: {
                            formatter : function(s) {
                                return (new Date(s)).getFullYear()+'-'+((new Date(s)).getMonth()+1);
                            }
                        }
                    },
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
                        data: ['业务量', '增长率', '调解量']
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

                            axisLabel: {
                                formatter: '{value} 件'
                            }
                        },
                        {
                            type: 'value',
                            name: '百分比',

                            axisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '业务量',
                            type: 'bar',
                            data: []
                        },
                        {
                            name: '增长率',
                            type: 'line',
                            data: []
                        },
                        {
                            name: '调解量',
                            type: 'bar',
                            data: []
                        }
                    ]
                },
                options: [],
                    tjoptions:[]
            },
                complainthotspotproblemOption:{

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
        mounted() {
            console.log("mounted");
            this.getComplainthotspotdata();
            this.getComplaintHotSpotProblemData();
        },
        methods:{
            handleClick(tab,event){
                if(tab.label === "投诉热点类别") {
                    console.log("进来tab")
                    const interval = setInterval(() => {
                        let settlementDom = document.getElementById("complainthotspot");
                        if (settlementDom) {
                            clearInterval(interval);
                            this.drawsettlement();

                        }
                    }, 200);
                }
                else if (tab.label === "投诉问题") {
                    const interval2 = setInterval(() => {
                        let amountRecoveredDom = document.getElementById("complainthotspotproblem");
                        if (amountRecoveredDom) {
                            this.drawComplaintHotSpotProblem();
                            clearInterval(interval2);
                        }
                    }, 200);
                }
            },
            // 办结条形图
            drawsettlement() {
                let drawsettlement = this.$echarts.init(
                    document.getElementById("complainthotspot")
                );
                drawsettlement.setOption(this.amountReacoveredOption, true);
            },
            drawComplaintHotSpotProblem() {
                let mediationRate = this.$echarts.init(
                    document.getElementById("complainthotspotproblem")
                );
                mediationRate.setOption(this.complainthotspotproblemOption, true);
            },
            getComplainthotspotdata() {
                this.loadingOfComplaint=true
                let params = {};
                searchComplainthotspotdata(params).then(res => {
                    console.log(res);
                    let Once = true;
                    let thisList;
                    for (var key in res.data) {
                        this.amountReacoveredOption.baseOption.timeline.data.push(key)
                        this.listOfDate = []
                        for (var liny in res.data[key]) {
                            this.amountReacoveredOption.baseOption.xAxis[0].data.push(res.data[key][liny].region);
                            this.amountReacoveredOption.baseOption.series[0].data.push(res.data[key][liny].overalltotal)//业务量

                            this.amountReacoveredOption.baseOption.series[2].data.push(res.data[key][liny].endtotal)//调解量
                            this.listOfDate.push(res.data[key][liny].overalltotal);
                            //调解率
                            /*if(res.data[key][liny].endtotal === 0){
                                this.amountReacoveredOption.baseOption.series[3].data.push(0)

                            }else{
                                this.amountReacoveredOption.baseOption.series[3].data.push(Math.round(res.data[key][liny].endtotal/res.data[key][liny].overalltotal*100));
                                console.log("调解率不为0")
                                console.log(Math.round(res.data[key][liny].endtotal/res.data[key][liny].overalltotal*100))
                            }*/

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
                    this.loadingOfComplaint=false
                    this.drawsettlement();
                });
                //this.drawsettlement();
            },
            getComplaintHotSpotProblemData(){
                let params={
                }
                searchComplaintHotWord(params).then(res => {
                    console.log(res)
                    for( var key in res.data ){
                        this.complainthotspotproblemOption.series[0].data.push(res.data[key])
                    }
                    //this.drawTrafficBrandProblem();
                });
            },
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
    .settlementRate
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
        //height: 700px;
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
</style>