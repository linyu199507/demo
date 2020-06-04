<template>
    <el-card class="comprehensive_query" >
        <div slot="header" class="title">
            基础数据修改
        </div>
        <el-form :model="basecodeform" :rules="rules2" label-suffix="：" ref="dataForm" label-width="100px" style="width: 40%;margin: 0 auto;">
            <el-row>
                <el-col :xs="24" :sm="24" :md="{span:16,offset:4}" :lg="{span:14,offset:4}">
                    <el-form-item label="类型" prop="类型">
                        <el-input type="text" v-model="basecodeform.type" placeholder="请输入类型" :disabled=" this.$route.query.type==null? false:true" class="input-text"></el-input>
                    </el-form-item>
                    <el-form-item label="ID" prop="ID">
                        <el-input type="text" v-model="basecodeform.codeid" placeholder="请输入ID" class="input-text"></el-input>
                    </el-form-item>
                    <el-form-item label="名称" prop="名称">
                        <el-input type="text" v-model="basecodeform.comment" placeholder="请输入名称" class="input-text"></el-input>
                    </el-form-item>
                </el-col>

                <el-col style="text-align: center">
                    <el-button type="primary" @click="submitForm">保存</el-button>
                    <el-button @click="resetForm">取消</el-button>
                </el-col>
            </el-row>
        </el-form>
    </el-card>
</template>

<script>
    import {updateBasicData} from '../../api/api';

    export default {
        name: "basicDataModify",
        data() {
            return {
                basecodeform: this.$route.query,
                rules2: {
                    type: [
                        {required: true, message: '请选择类型', trigger: 'blur'},
                    ],
                    codeid: [
                        {required: true, message: '请输入ID', trigger: 'blur'},
                    ],
                    comment: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ]
                },
                checked: true
            };
        },
        methods: {
            submitForm() {
                if(this.$route.query.orglevel==null || this.$route.query.orglevel=="" ){
                    this.basecodeform.orglevel="1"
                }else{
                    console.log(this.$route.query.orglevel)
                    this.basecodeform.orglevel=this.$route.query.orglevel
                }
                if (this.$route.query.parentid!=null && this.$route.query.parentid!="") {
                    this.basecodeform.parentid = this.$route.query.parentid
                    console.log(this.basecodeform.parentid)
                }else{
                    this.basecodeform.parentid=""
                    console.log(this.basecodeform.parentid)
                }
                let para = {
                    basecodeform:this.basecodeform,
                };
                console.log(para)
                updateBasicData(para).then((res) =>  {
                    console.log(res)
                    if (res.code === 0) {
                        this.$alert('数据修改成功！', '提示', {
                            type: 'success',
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$router.push({path: '/xtgl/jcsj'});
                            }
                        });
                    } else {
                        this.$alert('数据修改失败！', '提示', {
                            type: 'error',
                            confirmButtonText: '确定',
                        });
                    }
                });
            },
            resetForm() {
                this.$router.push({path: '/xtgl/jcsj'});
            },
        },
        //获取对应类型的值
        mounted() {
        }
    }
</script>

<style lang="scss">
    .comprehensive_query {
        margin: 20px 20px 0 20px;
        box-shadow: none;
        min-height: 70vh;

        .el-form {
            margin-left: -40px;
            margin-right: 40px;
        }

        .el-form-item {
            margin-bottom: 20px;
        }

        .el-form-item__content {
            line-height: normal;
        }

        .title {
            /*font-weight: bold;*/
            /*text-align: center;*/
        }

        .el-card__header {
            /*padding: 0;*/
        }

        .line {
            text-align: center;
            line-height: 36px;
            font-weight: bold;
        }
    }
</style>
