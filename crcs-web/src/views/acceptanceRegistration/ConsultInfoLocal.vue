<!--本系统通用表单子组件-->
<template>
    <el-collapse v-model="collapseModel" class="consult_info_local">
        <el-collapse-item name="formInfo">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="6">
                        1.表单信息
                    </el-col>
                    <el-col :span="16" style="color: #48576a;text-align: right">
                        工单号：{{task.tserialnum}}
                        <!--表单号：{{task.serialnum}}-->
                    </el-col>
                    <!--<el-col :span="2" style="color: #48576a;text-align: right">
                        表单号：{{task.serialnum}}
                    </el-col>-->
                </el-row>
            </div>
            <el-form label-width="150px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bts">业务性质：</el-col>
                        <el-col :span="14" class="st">咨询</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bts">标题：</el-col>
                        <el-col :span="14" class="st">{{data.rqsttitle}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bts">途径：</el-col>
                        <el-col :span="14" class="st">{{data.appealsource}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bts">移送编号：</el-col>
                        <el-col :span="14" class="st">{{data.transfernumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-row  v-if="data.appealsource === '信访系统'">
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bts">登记机构：</el-col>
                            <el-col :span="14" class="st">{{data.registration}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bts">承办机构：</el-col>
                            <el-col :span="14" class="st">{{data.undertake}}</el-col>
                        </el-col>
                        <el-col class="br"></el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="12" v-if="">
                            <el-col :span="8" class="bts">满意度：</el-col>
                            <el-col :span="14" class="st">{{data.satisfied}}</el-col>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                            <el-col :span="8" class="buff">来函单位：</el-col>
                            <el-col :span="16" class="st">{{data.incomingcommpany}}</el-col>
                        </el-col>
                    </el-row>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="subject">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        2.提供方信息
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                        <el-col :span="10" class="right">表单号：{{task.serialnum}}</el-col>
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="subjectForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">咨询方：</el-col>
                        <el-col :span="14" class="st">{{data.name}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">联系电话：</el-col>
                        <el-col :span="14" class="st">{{data.rqstnumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">性别：</el-col>
                        <el-col :span="14" class="st">{{data.gender}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">咨询方地址：</el-col>
                        <el-col :span="14" class="st">{{data.consultantaddress}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">是否职投：</el-col>
                        <el-col :span="14" class="st">{{data.isprofessional}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="keti">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        3.涉及主体信息
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="passengerForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">涉及主体名称：</el-col>
                        <el-col :span="14" class="st">{{data.entname}}</el-col>
                    </el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                    <!--<el-col :span="8" class="bt">市场主体类型：</el-col>-->
                    <!--<el-col :span="14" class="st">{{data.providetype}}</el-col>-->
                    <!--</el-col>-->
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">所在区域：</el-col>
                        <el-col :span="14" class="st">{{data.region}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">被举报对象地址：</el-col>
                        <el-col :span="14" class="st">{{data.address}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                    <!--<el-col :span="8" class="bt">联系人：</el-col>-->
                    <!--<el-col :span="14" class="st">{{data.reportedcontact}}</el-col>-->
                    <!--</el-col>-->
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">联系电话：</el-col>
                        <el-col :span="14" class="st">{{data.linknumber}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                    <!--<el-col :span="8" class="bt">社会信用代码：</el-col>-->
                    <!--<el-col :span="14" class="st">{{data.registerno}}</el-col>-->
                    <!--</el-col>-->
                    <!--<el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                    <!--<el-col :span="8" class="bt">行业分类：</el-col>-->
                    <!--<el-col :span="14" class="st">{{data.industryname}}</el-col>-->
                    <!--</el-col>-->
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="specific">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        4.业务信息
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <el-form label-width="150px" ref="businessForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">分类：</el-col>
                        <el-col :span="14" class="st">{{data.complaintstypecodeid}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">关键字：</el-col>
                        <el-col :span="14" class="st">{{data.keyword}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">案件接收日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.acceptdate == null">{{data.acceptdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.acceptdate != null">{{data.acceptdate?moment(data.acceptdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>

                    <el-col :xs="24" :sm="24" :md="24" :lg="12">
                        <el-col :span="8" class="bt">最终反馈日期：</el-col>
                        <el-col :span="14" class="st" v-if="data.finalfeedbackdate == null">{{data.finalfeedbackdate}}</el-col>
                        <el-col :span="14" class="st" v-if="data.finalfeedbackdate != null">{{data.finalfeedbackdate?moment(data.finalfeedbackdate).format('YYYY-MM-DD'):''}}</el-col>
                    </el-col>
                    <el-col class="br"></el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24">
                        <el-col :span="4" class="bt">举报内容：</el-col>
                        <el-col :span="20" class="st">{{data.rqstcontent}}</el-col>
                    </el-col>

                    <el-col class="br"></el-col>

                    <el-col :xs="24" :sm="24" :md="12" :lg="12">
                        <el-col :span="8" class="bt">相关附件：</el-col>
                        <el-col :span="16">
                            <el-upload
                                    :auto-upload="false"
                                    :file-list="fileList"
                                    ref="upload"
                                    :on-preview="previewFile"
                                    :data="this.requestMap"
                                    :on-success="uploadSuccess"
                                    :before-upload="beforeUpload"
                                    :multiple="true"
                                    action="/crcs/filesUpload/upload"
                                    class="upload-demo"
                                    :on-remove="handleRemove"
                            >
                                <el-button slot="trigger" size="mini" type="primary">选择附件</el-button>
                                <el-button size="mini" type="success"
                                           @click="submitUpload()"
                                >确认上传</el-button>
                            </el-upload>
                        </el-col>
                        <el-col :span="14" class="st"></el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="processing" v-if="casedata != null">
            <div slot="title" @click.stop="">
                <el-row class="el-accordion">
                    <el-col :span="12">
                        待审批处理反馈
                    </el-col>
                    <el-col :span="10" style="color: #48576a;text-align: right">
                    </el-col>
                </el-row>
            </div>
            <template>
                <el-form label-width="150px">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="12">
                            <el-col :span="8" class="bt">处理结果：</el-col>
                            <el-col :span="14" class="st">{{casedata.handleopinion}}</el-col>
                        </el-col>
                        <!--                            <el-col class="br"></el-col>-->
                        <!--                            <el-col :xs="24" :sm="24" :md="24" :lg="12">-->
                        <!--                                <el-col :span="8" class="bt">附件清单：</el-col>-->
                        <!--                                <el-col :span="14" class="st"></el-col>-->
                        <!--                            </el-col>-->
                    </el-row>
                </el-form>
            </template>
        </el-collapse-item>
    </el-collapse>

</template>
<script>
    import moment from 'moment'
    import {getMaterialLists,deleteUploadFile}from"../../api/api"
    export default {
        props: ['data','task','casedata',],
        name: 'consultInfoLocal',
        data() {
            return {
                moment,
                fileList:[],
                collapseModel: ['formInfo','subject', 'keti', 'handle', 'specific','processing'],
                requestMap: {
                    caseguid: ""
                },
            }
        },
        mounted(){
            console.log(this.task)
            console.log(this.data)
            this.getMaterialLists();
        },
        methods: {
            handlePreview(file) {

                if(file.status=="success"){
                    this.download(file.url)
                }
            },
            download(url){
                window.open(`${process.env.Download_URL}` + url);

            },
            uploadSuccess(response, file, fileList) {
                if (file.response.code === 0) {
                    this.$message({
                        message: "上传成功",
                        type: "success"
                    });
                } else {
                    this.$message.error("上传失败" + file.response.msg);
                }
            },
            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 200 <= 1;
                let lastName = fileName.substring(pos, fileName.length);
                let arrays = [
                    ".docx",
                    ".doc",
                    ".txt",
                    ".xls",
                    ".jpg",
                    ".png",
                    ".pfg",
                    ".flv",
                    ".rmvb",
                    ".mvb",
                    ".mp4",
                    ".xlsx",
                    ".pdf",
                    ".zip",
                    ".wps"
                ];
                if (arrays.indexOf(lastName) === -1 || isLt === false) {
                    this.$alert(
                        "不支持上传该格式的文件,请上传docx，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                        "提示",
                        {
                            confirmButtonText: "确定"
                        }
                    );
                    return false;
                }
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
                }
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            submitUpload(){
                this.requestMap.caseguid=this.task.caseguid;
                this.$refs.upload.submit();
            },
            getMaterialLists() {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.task.caseguid
                };
                console.log(params)
                getMaterialLists(params).then(res => {
                    if (res.code === 0) {
                        console.log(res)
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            //this.fileListsAccept.push(fileInfo);
                            this.fileList.push(fileInfo);
                        }
                    }
                });
            },
        }
    }
</script>

<style lang="scss">
    .consult_info_local {
        .el-collapse-item__header .el-collapse-item__header__arrow {
            position: absolute;
            top: 15px;
            right: 10px;
            z-index: 10;
            transform: rotate(90deg);
        }

        .el-collapse-item.is-active > .el-collapse-item__header .el-collapse-item__header__arrow {
            transform: rotate(-90deg);
        }

        .el-collapse-item__header {
            position: relative;
        }

        .bt {
            margin: 10px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .bts {
            margin: 10px auto;
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .st {
            margin: 10px auto;
            font-size: 13px;
        }

        .br {
            padding-bottom: 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #e8e8e8;
        }

        .form_bt {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
        }

        .el-form .el-row {
            margin: 0 !important;
        }
    }
</style>
