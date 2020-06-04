<!--维权服务工单组件-->
<template>
    <el-collapse v-model="activeCollapse">
        <el-collapse-item title="站点信息" name="1" style="font-weight: bold">
            <el-form
                    label-width="120px"
                    style="margin-top: 20px"
                    :model="rightForm"
                    ref="rightForm">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">业务性质：</el-col>
                        <el-col :span="16" class="st">消费维权</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">标题：</el-col>
                        <el-col :span="16" class="st">{{rightForm.rqsttitle}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">途径：</el-col>
                        <el-col :span="16" class="st">维权服务站</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">登记时间：</el-col>
                        <el-col :span="16"  class="st">{{$moment(rightForm.registrationdate).format('YYYY-MM-DD HH:mm:ss')}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">所属辖区：</el-col>
                        <el-col :span="16"  class="st">{{rightForm.region}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">经营主体：</el-col>
                        <el-col :span="16"  class="st">{{rightForm.enterprise}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">登记人：</el-col>
                        <el-col :span="16"  class="st">{{rightForm.staffname}}</el-col>
                    </el-col>
                </el-row>


            </el-form>
        </el-collapse-item>
        <el-collapse-item name="2" title="投诉人信息" style="font-weight: bold">
            <el-form label-width="120px" style="margin-top: 20px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">投诉人姓名：</el-col>
                        <el-col :span="16" class="st">{{rightForm.name}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">投诉人电话：</el-col>
                        <el-col :span="16" class="st">{{rightForm.phone}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">投诉人地址：</el-col>
                        <el-col :span="16" class="st">{{rightForm.address}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="3" title="被投诉人信息" style="font-weight: bold">
            <el-form style="margin-top: 20px" label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">被投诉人名称：</el-col>
                        <el-col :span="16" class="st">{{rightForm.vendorname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">所属区：</el-col>
                        <el-col :span="16" class="st">{{rightForm.vendorregion}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">所在镇：</el-col>
                        <el-col :span="16" class="st">{{rightForm.vendortown}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">联系电话：</el-col>
                        <el-col :span="16" class="st">{{rightForm.vendorphone}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">具体地址：</el-col>
                        <el-col :span="16" class="st">{{rightForm.vendoraddress}}</el-col>
                    </el-col>
                </el-row>
            </el-form>
        </el-collapse-item>
        <el-collapse-item name="4" title="投诉货品或服务" style="font-weight: bold">
            <el-form style="margin-top: 20px" label-width="120px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">名称/类别：</el-col>
                        <el-col :span="16" class="st">{{rightForm.goodsname}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">型号：</el-col>
                        <el-col :span="16" class="st">{{rightForm.goodsmodel}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">投诉类型：</el-col>
                        <el-col :span="16" class="st">{{rightForm.goodstype}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">投诉性质：</el-col>
                        <el-col :span="16" class="st">{{rightForm.nature}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">数量：</el-col>
                        <el-col :span="16" class="st">{{rightForm.number}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">涉及金额：</el-col>
                        <el-col :span="16" class="st">{{rightForm.money}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">购买时间：</el-col>
                        <el-col :span="16" class="st" v-if="rightForm.shoppingdate != null">{{$moment(this.rightForm.shoppingdate).format("YYYY-MM-DD")}}</el-col>
                        <el-col :span="16" class="st" v-if="rightForm.shoppingdate == null">{{rightForm.shoppingdate}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="8">
                        <el-col :span="8" class="buff">发生时间：</el-col>
                        <el-col :span="16" class="st" v-if="rightForm.happendate != null">{{$moment(this.rightForm.happendate).format("YYYY-MM-DD")}}</el-col>
                        <el-col :span="16" class="st" v-if="rightForm.happendate == null">{{rightForm.happendate}}</el-col>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                        <el-col :span="3" class="buff" style="width: 15%">诉求内容：</el-col>
                        <el-col :span="16" class="st">{{rightForm.appeal}}</el-col>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="16" :lg="18">
                        <el-col :span="3" class="buff" style="width: 15%">个案详情：</el-col>
                        <el-col :span="16" class="st">{{rightForm.rqstcontent}}</el-col>
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
    import {myMixin} from "../../assets/mixin/mixin";
    import{getMaterialLists,deleteUploadFile}from"../../api/api";
    export default {

        props:["rightForm"],

        mixins: [myMixin],
        data() {
            return {
                rightForm: {},
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
            // this.getMaterialLists();
            // console.log(this.commommodule)
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
<style lang="scss" scoped>
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


