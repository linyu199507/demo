<template>
    <el-card class="comprehensive_query" >
        <div slot="header" class="title">
            密码修改
        </div>
        <el-form :model="dataForm" :rules="rules2" label-suffix="：" ref="dataForm" label-width="100px" style="width: 40%;margin: 0 auto;">
            <el-row>
                <el-col :xs="24" :sm="24" :md="{span:16,offset:4}" :lg="{span:14,offset:4}">
                    <el-form-item label="原密码" prop="oldpassword">
                        <el-input type="password" v-model="dataForm.oldpassword" placeholder="请输入原密码" class="input-text"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newpassword">
                        <el-input type="password" v-model="dataForm.newpassword" placeholder="请输入新密码" class="input-text"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkpassword">
                        <el-input type="password" v-model="dataForm.checkpassword" placeholder="请输入确认密码" class="input-text"></el-input>
                    </el-form-item>
                </el-col>

                <el-col style="text-align: center">
                    <el-button type="primary" @click="submitForm">保存</el-button>
                    <el-button @click="resetForm('dataForm')">重置</el-button>
                </el-col>
            </el-row>
        </el-form>
    </el-card>
</template>

<script>
    import {modifyPassword} from '../../api/api';

    export default {
        name: "modifypassword",
        data() {
            return {
                dataForm: {
                    oldpassword: '',
                    newpassword: '',
                    checkpassword: '',
                },
                rules2: {
                    oldpassword: [
                        {required: true, message: '请输入原密码', trigger: 'blur'},
                    ],
                    newpassword: [
                        {required: true, message: '请输入新密码', trigger: 'blur'},
                    ],
                    checkpassword: [
                        {required: true, message: '请输入确认密码', trigger: 'blur'},
                    ]
                },
                checked: true
            };
        },
        methods: {
            submitForm() {
                this.$refs.dataForm.validate((valid) => {
                    if (valid) {
                        if (this.dataForm.newpassword == this.dataForm.checkpassword) {
                            let user = JSON.parse(localStorage.getItem('user'));
                            var params = {oldpassword: this.dataForm.oldpassword, newpassword: this.dataForm.newpassword, userid: user.primaryKey};
                            modifyPassword(params).then(data => {
                                if (data.code === 0) {
                                    this.$alert('密码修改成功！', '提示', {
                                        type: 'success',
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            this.$router.push({path: '/login'});
                                        }
                                    });
                                } else {
                                    this.$alert('密码修改失败！', '提示', {
                                        type: 'error',
                                        confirmButtonText: '确定',
                                    });
                                }
                            });
                        } else {
                            this.$message({
                                message: '重复密码必须输入一致。',
                                type: 'error'
                            });
                        }

                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
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
