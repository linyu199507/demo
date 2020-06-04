<!---->
<template>
    <section class="preliminary_of_next" id="whole">
        <el-tabs
                type="border-card"
                style="width: 96%;margin: 15px auto;"
                v-model="tabActionName"
                v-loading="loading"
                element-loading-text="拼命加载中"
        >
            <el-tab-pane label="基本信息" name="tabMainContent">

                <div style="text-align: right;" >
                    <el-button type="primary" @click="changeToEconomy()">案件转举报</el-button>
                    <el-button type="primary" @click="triggerBus('CopOpinion')">意见填写</el-button>
                </div>


                <el-collapse v-model="collapseValues" class="accordion" style="margin: 15px auto;">
                    <el-collapse-item name="formContent">
                        <div slot="title" @click.stop>
                            <el-row class="accordion_title">
                                <el-col :span="12">
                                    <em>1.</em> 维权服务
                                </el-col>
                            </el-row>
                        </div>

                        <!--维权服务工单详情 -->
                        <rightsInfo  :rightForm="rightForm" />

                    </el-collapse-item>
                </el-collapse>
            </el-tab-pane>


            <!-- 意见填写 -->
            <el-tab-pane label="意见填写" name="CopOpinion" v-if="tabActionName==='CopOpinion'">
                <el-form :model="opinionForm" label-width="200px" ref="opinionForm" :rules="opinionFormRules"
                         class="text_bold" label-suffix="：">
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="意见" prop="opinion">
                                <el-input
                                        type="textarea"
                                        v-model="opinionForm.opinion"
                                        placeholder="请输入意见（3000字内）"
                                        :rows="5"
                                ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="24" :lg="16">
                            <el-form-item label="相关附件:">
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
                            </el-form-item>
                        </el-col>
                        <el-col class="centered">
                            <el-button type="primary" @click="copyOpinion" :loading="submitLoading">确定</el-button>
                            <el-button @click="cancelTab">取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

        </el-tabs>
    </section>
</template>

<script>

    import  {getMaterialLists,deleteUploadFile,rightsDetail}from "../../api/api"
    import {myMixin} from "../../assets/mixin/mixin"
    import rightsInfo from "./rightsInfo";

    export default {
        mixins: [myMixin],
        components: {
            rightsInfo,
        },
        data() {
            return {

                rightForm:{},
                opinionForm: {},
                exportLoading:false,
                user: JSON.parse(localStorage.getItem("user")),
                tabActionName: "tabMainContent",
                collapseValues: [
                    "orderInfo",
                    "formContent",
                    "officeRequirements",
                    "processTracking"
                ],
                formTypeStr: "",
                loading: false,
                opinionList: [],
                opinionFormRules: {
                    opinion: [{required: true, message: "请输入内容", trigger: "blur"}]
                },
                acceptFormRules: {
                    isAccep: [{required: true}],
                    reason: [
                        {required: true, message: "请选择不受理原因", trigger: "change"}
                    ],
                    mode: [
                        {required: true, message: "请选择受理方式", trigger: "change"}
                    ],
                    opinion: [
                        {required: true, message: "请输入内容", trigger: "blur"},
                        {max: 1000, message: "不能超出1000字符", trigger: "blur"}
                    ]
                },

            };
        },
        methods: {

            changeToEconomy(){

                localStorage.setItem("rightForm", JSON.stringify(this.rightForm));
                let params = {
                    type: "rightFormChangeEconomy"
                }
                // this.$router.resolve({
                //     path:'/sldj/zxdj',
                //     query:{
                //         type:"toChangeEconomy"
                //     }
                // })
                let url = this.$router.resolve({
                    path: "/sldj/zxdj",
                    query: params
                });
                window.open(url.href, "_blank");
                return;
            },


            //导出为Word格式
            exportToWord(){
                //query传参,另一页面进行获取方法
                const querystring = require('querystring');
                let str=   querystring.stringify({caseguid:this.$route.query.caseguid , formType:this.formType,tserialnum:this.tasklist.tserialnum,systemsource:this.tasklist.systemsource})
                window.open('/crcs/businessSearch/wordexport?'+str)

            },
            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            },

            triggerBus(value){
                if("tabBus" === value){
                    this.tabActionName = "tabOpinionOne";
                }else if("preliminary" === value){
                    this.tabActionName = "tabOpinionTwo";
                }else if("CopOpinion" === value){
                    this.tabActionName = "CopOpinion";
                }
            },


            cancelTab() {
                this.tabActionName = "tabMainContent";
            },



            copyOpinion() {
                //最终反馈意见填写

                let nextNodeId;
                if(this.$route.query.node === "collaborative"){
                    nextNodeId = "collaborative"
                }else{
                    nextNodeId = "copyToOpinion"
                }

                let validation = false;
                this.$refs.opinionForm.validate(valid => (validation = valid));
                if (!validation) return;
                this.$confirm("请确认无误提交?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.submitLoading = true;
                    let caseguid;
                    if ("commommodule" === this.formType) {
                        if (this.commomform != null) {
                            caseguid = this.commomform.caseguid;
                        } else {
                            caseguid = this.consultationform.caseguid;
                        }
                    } else if ("complaintmodule" === this.formType) {
                        caseguid = this.complaintform.caseguid;
                    } else if ("comeconomicmodule" === this.formType) {
                        caseguid = this.economyform.caseguid;
                    }
                    let params = {
                        user: this.user,
                        caseguid,
                        node: this.nodeStr,
                        nextNodeId: nextNodeId,
                        opinion: this.opinionForm.opinion,
                        ismain: "0"
                    };
                    nextWork(params).then(data => {
                        if (data.code == 0) {
                            this.$message({
                                message: '提交成功！',
                                type: 'success',
                                duration: '1000'
                            });
                            this.$router.push("/wddb/bxtdb");
                        } else {
                            this.errorAlert();
                        }
                        this.submitLoading = false;
                    })
                        .catch(data => {
                            this.errorAlert();
                            this.submitLoading = false;
                        });
                });
            },





            errorAlert() {
                this.$alert("提交失败！", "提示", {
                    confirmButtonText: "确定",
                    type: "error"
                });
            },
            getBaseInfo() {
                console.log('炯炯his的发送到发送到')
                this.loading = true;
                //获取id，否则跳转
                let {id, caseguid} = this.$route.query;
                if (!id || !caseguid) {
                    this.$router.push("/wddb/bxtdb");
                    return;
                }

                // //获取工单信息
                rightsDetail(id).then(res => {

                    console.log(res)

                    if (res.code === 0) {
                       if (res.data != null) {
                           this.rightForm = res.data
                           console.log(this.rightForm)
                        } else {
                            //找不到对的工单信息
                            this.$alert("找不到对应的工单信息!", "提示", {
                                confirmButtonText: "确定",
                                type: "warning"
                            });
                            this.$router.push("/wddb/bxtdb");
                        }
                    }
                    this.loading = false;
                })
                    .catch(error => {
                        console.error(error);
                        this.$message({
                            message: "请求失败",
                            type: "error"
                        });
                        this.loading = false;
                    });
            },



            //附件相关方法
            handleRemove(file, fileList) {
                console.log(file);
                console.log(fileList);
                let para={
                    filename:file.name,
                    fileurl:file.url
                }
                deleteUploadFile(para).then(res=>{

                })
            },
            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url);
                } else {
                    this.download(file.url);
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
            submitUpload(){
                this.requestMap.caseguid=this.$route.query.caseguid;
                console.log(this.requestMap.caseguid)
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
                    caseguid:this.$route.query.caseguid
                };
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

        },
        created() {
            // console.log(this.$route.query.id)
            // console.log(this.user)
            this.getBaseInfo();
            // this.getMaterialLists();
        }
    };
</script>
<style lang="scss" scoped>
    .preliminary_of_next {
        .btn {
            text-align: center;
            margin-top: 15px;
        }

        .label {
            font-size: 13px;
            color: #48576a;
            font-weight: bold;
            text-align: right;
        }

        .item_col {
            padding: 5px 0 0 0;
            /* border-bottom: 1px solid #e8e8e8; */
        }

        .el-form .el-row {
            margin: 0;
        }

        .bt {
            font-weight: bold;
            text-align: right;
        }
    }
</style>
