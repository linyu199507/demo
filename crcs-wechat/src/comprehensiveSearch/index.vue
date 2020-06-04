<template>
    <div class="main-container">
        <header class="title">综合查询</header>
        <div class="main-content">
            <div class="form-list">
                <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <x-input title="表单号" v-model="form.serialNum" value-align="left" placeholder="请输入表单号"></x-input>
                    <x-input title="工单号" v-model="form.serialNum" value-align="left" placeholder="请输入工单号"></x-input>
                    <x-input title="标题" v-model="form.serialNum" value-align="left" placeholder="请输入标题"></x-input>
                    <datetime
                        v-model="form.registerDate1"
                        :format="'YYYY-MM-DD'"
                        title="登记时间（始）"
                        placeholder="请选择登记时间（始）"
                        value-align="right"></datetime>
                    <datetime @on-change="onchange"
                        v-model="form.registerDate2"
                        :format="'YYYY-MM-DD'"
                        title="登记时间（终）"
                        placeholder="请选择登记时间（终）"
                        value-align="right"></datetime>
                    <popup-picker
                        title="诉求来源"
                        placeholder="请选择诉求来源"
                        :data="sourceOptions"
                        :show-name="true"
                        v-model="form.source"
                        value-text-align="left"
                    ></popup-picker>
                    <popup-picker
                        title="业务性质"
                        placeholder="请选择业务性质"
                        :data="natureOptions"
                        :show-name="true"
                        v-model="form.nature"
                        value-text-align="left"
                        ></popup-picker>
                    <popup-picker
                        title="工单状态"
                        placeholder="请选择工单状态"
                        :data="statusOptions"
                        :show-name="true"
                        v-model="form.status"
                        value-text-align="left"
                        ></popup-picker>
                    <div class="submit">
                        <x-button type="primary" mini @click.native="submit">提交</x-button>
                    </div>
                </group>
            </div>
            
        </div>
    </div>
</template>

<script>
import moment from "moment";
export default {
    data(){
        return {
            index:0,
            form:{
                serialNum:'',
                tserialNum:'',
                title:'',
                registerDate1:'',
                registerDate2:'',
                source:[],
                nature:[],
                status:[],
            },
            sourceOptions:[[
                {value:'01',name:'受理登记'},
                {value:'02',name:'12345系统'},
                {value:'03',name:'12315系统'},
            ]],
            natureOptions:[[
                {value:'咨询',name:'咨询意见建议类'},
                {value:'投诉',name:'投诉类'},
                {value:'举报',name:'举报类'},
            ]],
            statusOptions:[[
                {value:'01',name:'处理中'},
                {value:'02',name:'处理完成'},
                {value:'03',name:'退回'},
            ]],
        }
    },
    methods:{
        submit(){
            let params = {
                        serialNum:this.form.serialNum,
                        tserialNum:this.form.tserialNum,
                        title:this.form.title,
                        registerDate1:this.form.registerDate1,
                        registerDate2:this.form.registerDate2,
                        source:this.form.source.length > 0 ? this.form.source[0] : '',
                        nature:this.form.nature.length > 0 ? this.form.nature[0] : '',
                        status:this.form.status.length > 0 ? this.form.status[0] : '',
            };
            // 参数非全空校验
            let flag = true;
            for(let key in params){
                if(typeof params[key] === 'string'){
                    if(params[key].trim() != ''){
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                return alert('请填写搜索条件');
            }
            sessionStorage.setItem('searchFormParam', JSON.stringify(params))
            this.$router.push({name:'comprehensiveSearchResult',});
        },
        
        onchange(){
            console.log(this.form)
        }
    }
}
</script>

<style lang="less" scoped>
ul,li{list-style: none;}
header.title{height:40PX;line-height:40PX;text-align: center;font-size: 18PX;border-bottom: 1PX solid rgb(233, 233, 233);box-shadow: 0 2PX 5PX #ccc;}
.submit{text-align: center;}
</style>