<!--咨询登记表单子组件-->
<template>
    <el-collapse v-model="activeCollapse">
        <el-collapse-item title="系统信息" name="1" style="font-weight: bold">
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="commommodule"
                    ref="commommodule">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">业务性质：</el-col>
                        <el-col :span="16" class="st">咨询</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">标题：</el-col>
                        <el-col :span="16" class="st">{{commommodule.rqsttitle}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">途径：</el-col>
                        <el-col :span="16" class="st">{{commommodule.appealsource}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">移送编号：</el-col>
                        <el-col :span="16"  class="st">{{commommodule.transfernumber}}</el-col>
                    </el-col>
                </el-row>

                <el-row  v-if="commommodule.appealsource === '信访系统'">
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">登记机构：</el-col>
                        <el-col :span="16" class="st">{{commommodule.registration}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">承办机构：</el-col>
                        <el-col :span="16" class="st">{{commommodule.undertake}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="">
                        <el-col :span="8" class="buff">满意度：</el-col>
                        <el-col :span="16" class="st">{{commommodule.satisfied}}</el-col>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8" v-if="showSpotCheck">
                        <el-col :span="8" class="buff">来函单位：</el-col>
                        <el-col :span="16" class="st">{{commommodule.incomingcommpany}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="2" title="提供方信息" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top: 20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">咨询方：</el-col>
                        <el-col :span="16" class="st">{{commommodule.name}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">咨询方电话：</el-col>
                        <el-col :span="16" class="st">{{commommodule.rqstphonenumber}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">性别：</el-col>
                        <el-col :span="16" class="st">{{commommodule.gender}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">是否职投：</el-col>
                        <el-col :span="16" class="st">{{commommodule.isprofessional}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">咨询方地址：</el-col>
                        <el-col :span="16" class="st">{{commommodule.consultantaddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="3" title="涉及主体信息" style="font-weight: bold">
            <el-form style="margin-top: 20px" label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">涉及主体名称：</el-col>
                        <el-col :span="16" class="st">{{commommodule.entname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">联系电话：</el-col>
                        <el-col :span="16" class="st">{{commommodule.linkphonenumber}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">地址：</el-col>
                        <el-col :span="16" class="st">{{commommodule.region}} {{commommodule.address}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="4" title="具体信息" style="font-weight: bold">
            <el-form style="margin-top: 20px" label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">分类：</el-col>
                        <el-col :span="16" class="st">{{commommodule.complaintstype}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">关键字：</el-col>
                        <el-col :span="16" class="st">{{commommodule.keyword}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">案件接收日期：</el-col>
                        <el-col :span="16" class="st" v-if="commommodule.acceptdate != null">{{$moment(this.commommodule.acceptdate).format("YYYY-MM-DD")}}</el-col>
                        <el-col :span="16" class="st" v-if="commommodule.acceptdate == null">{{commommodule.acceptdate}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">最终反馈日期：</el-col>
                        <el-col :span="16" class="st" v-if="commommodule.finalfeedbackdate != null">{{$moment(this.commommodule.finalfeedbackdate).format("YYYY-MM-DD")}}</el-col>
                        <el-col :span="16" class="st" v-if="commommodule.finalfeedbackdate == null">{{commommodule.finalfeedbackdate}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                        <el-col :span="3" class="buff" style="width: 15%">问题描述：</el-col>
                        <el-col :span="20" class="st">{{commommodule.rqstcontent}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                        <el-form-item label="相关附件：" label-width="130px">
                            <el-upload
                                    class="upload-demo"
                                    ref="upload"
                                    action="/crcs/filesUpload/upload"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :on-success="uploadSuccess"
                                    :file-list="fileList"
                                    :auto-upload="false"
                                    :data="this.requestMap"
                                    :before-upload="beforeUpload"

                            >
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
    </el-collapse>
</template>
<script>
    import {myMixin} from "../../../assets/mixin/mixin";
    import{getMaterialLists,deleteUploadFile}from"../../../api/api";
    export default {

        props:["commommodule"],

        mixins: [myMixin],
        data() {
            return {
                commommodule: {},
                rows: 5,
                fileList: [],
                fileListsAccept:[],
                showSpotCheck: false,
                activeCollapse: ["1", "2", "3", "4"],
                requestMap: {
                    caseguid: ""
                },
            };
        },
        mounted(){
            this.getMaterialLists();
            console.log(this.commommodule)
        },
        methods: {
            changeRegionInfo(value) {
                if (value === "其他") {
                    this.showSpotCheck = true;
                } else {
                    this.showSpotCheck = false;
                }
            },
            handlePreview(file) {
            console.log(file)
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
                // console.log(file)
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 20 <= 1;
                // console.log(file.size / 1024 / 1024 / 20);
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
                    ".pdf"
                ];
                if (arrays.indexOf(lastName) === -1 || isLt === false) {
                    this.$alert(
                        "不支持上传该格式的文件,请上传docx，doc，txt，xls，xlsx，jpg，png，pfg，flv，rmvb，mp4，mvb格式的且小于20M的文件",
                        "提示",
                        {
                            confirmButtonText: "确定"
                        }
                    );
                    return false;
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
            getMaterialLists() {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid:this.commommodule.caseguid
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
    };
</script>
<style lang="scss" scoped>
    .el-input {
        width: 224px;
    }

    .el-tabs .el-form .el-row {
        margin: 0 35px;
    }

    .bt {
        margin: 5px auto;
        font-size: 14px;
        color: #48576a;
        font-weight: bold;
        text-align: right;
    }

</style>
<style lang="scss">
    .el-form-item__label {
        font-size: 24px;
        font-weight: 600;
    }

    .buff {
        margin: 5px auto;
        font-size: 13px;
        color: #48576a;
        text-align: right;
    }

    .st {
        margin: 5px auto;
        font-size: 13px;
        color: #48576a;
        font-weight: normal;
        text-align: left;
    }


</style>


