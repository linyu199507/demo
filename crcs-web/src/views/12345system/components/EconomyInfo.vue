<!--经济违法详细子组件-->
<template>
    <el-tabs v-model="tabFormActionName">
        <el-tab-pane label="登记信息" name="tabRegister">
            <el-collapse v-model="collapseFormValues" class="accordion">
                <el-collapse-item name="provider">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">提供方信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                            <el-col :span="10" class="right">表单号：{{tasklist.serialnum}}</el-col>
                        </el-row>
                    </div>
                    <el-row class="item_row">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" class="item_col">
                            <el-col :span="3" class="label ml-15">标题：</el-col>
                            <el-col :span="18">{{economyform.rqsttitle}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">表单类型：</el-col>
                            <el-col :span="16">{{economyform.formtype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">表单模板：</el-col>
                            <el-col :span="16">{{economyform.accordtype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">投诉人姓名：</el-col>
                            <el-col :span="16">{{economyform.rqstperson}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">性别：</el-col>
                            <el-col :span="16">{{economyform.gender}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">投诉人地址：</el-col>
                            <el-col :span="16">{{economyform.rqstaddress}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">来电号码：</el-col>
                            <el-col :span="16">{{economyform.rqstnumber}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">联系电话：</el-col>
                            <el-col :span="16">{{economyform.linknumber}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">反映类型：</el-col>
                            <el-col :span="16">{{economyform.usertype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">回复方式：</el-col>
                            <el-col :span="16">{{economyform.answertype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">类别：</el-col>
                            <el-col :span="16">{{economyform.providelb}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">工单分类：</el-col>
                            <el-col :span="16">{{economyform.focus}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <!-- 涉及主体信息 -->
                <el-collapse-item name="subjectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">涉及主体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row class="item_row">
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">名称：</el-col>
                            <el-col :span="16">{{economyform.subjectname}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">联系方式：</el-col>
                            <el-col :span="16">{{economyform.subjectphone}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="16" :lg="16" class="item_col">
                            <el-col :span="4" class="label w110">经营地址：</el-col>
                            <el-col :span="19">{{economyform.subjectaddress}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <!-- 涉及客体信息 -->
                <el-collapse-item name="objectInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">涉及客体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row class="item_row">
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品/服务名称：</el-col>
                            <el-col :span="16">{{economyform.objname}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">商品品牌：</el-col>
                            <el-col :span="16">{{economyform.brand}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及客体分类：</el-col>
                            <el-col :span="16">{{economyform.objectcategory}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及问题分类：</el-col>
                            <el-col :span="16">{{economyform.problemtype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">关键字：</el-col>
                            <el-col :span="16">{{economyform.keyword}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉及金额：</el-col>
                            <el-col :span="16">{{economyform.objmoney}} 元</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">生产企业：</el-col>
                            <el-col :span="16">{{economyform.producecompany}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">是否网购：</el-col>
                            <el-col :span="16">{{economyform.isweb}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
                <!-- 业务具体信息 -->
                <el-collapse-item name="specificInformation">
                    <div slot="title" @click.stop>
                        <el-row class="min_title">
                            <el-col :span="12">业务具体信息</el-col>
                            <el-col :span="10" class="right"></el-col>
                        </el-row>
                    </div>
                    <el-row class="item_row">
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">涉嫌违法行为：</el-col>
                            <el-col :span="16">{{economyform.illegalrules}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">事件级别：</el-col>
                            <el-col :span="16">{{economyform.eventlevel}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">政务标签：</el-col>
                            <el-col :span="16">{{economyform.govlabel}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">业务分类：</el-col>
                            <el-col :span="16">{{economyform.businesstype}}</el-col>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="8" :lg="8" class="item_col">
                            <el-col :span="8" class="label">侵权分类：</el-col>
                            <el-col :span="16">{{economyform.tortclassify}}</el-col>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col class="item_col">
                            <el-col :span="4" class="label w110">问题描述：</el-col>
                            <el-col :span="18">{{economyform.problemdescribe}}</el-col>
                        </el-col>
                    </el-row>
                </el-collapse-item>
            </el-collapse>
        </el-tab-pane>
        <!--<el-tab-pane label="处理信息" name="tabHandle"></el-tab-pane>-->
    </el-tabs>
</template>

<script>

    import { getMaterialLists } from "../../../api/convenient12345";

    export default {
        name: "EconomyInfo",
        props: ["tasklist", "economyform", "casedata"],
        data() {
            return {
                // 材料列表
                fileLists: [],
                fileListsAccept:[],
                materialList: [],
                requestMap: {
                    caseguid: ""
                },
                collapseFormValues: [
                    "provider",
                    "subjectInformation",
                    "objectInformation",
                    "specificInformation",
                    "correlationInformation",
                    "processing",
                    "officeInfo"
                ],
                tabFormActionName: "tabRegister"
            };
        },
        methods: {

            previewFile(file) {
                if (file.response !== undefined) {
                    this.download(file.response.data.url)
                } else {
                    this.download(file.url)
                }
            },

            download(url){
                // window.location.href = `${process.env.Download_URL}` + url;
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
                    ".xlsx"
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

            submitUpload() {
                this.requestMap.caseguid = this.complaintform.caseguid;
                this.$refs.upload.submit();
            },

            getMaterialLists(caseguid) {
                let params = {
                    tserialnum: "",
                    rqsttitle: "",
                    begindate: "",
                    enddate: "",
                    size: 30,
                    page: 1,
                    caseguid: caseguid
                };
                getMaterialLists(params).then(res => {
                    if (res.code === 0) {
                        // this.fileList = res.data.records;
                        let fileInfo;
                        for (let i in res.data.records) {
                            fileInfo = {
                                name: res.data.records[i].attachfilename,
                                url: res.data.records[i].filesavepath
                            };
                            this.fileListsAccept.push(fileInfo);
                            this.fileLists.push(fileInfo);
                        }
                    }
                });
            },

            formatDate(value) {
                return value ? this.$moment(value).format("YYYY-MM-DD HH:mm:ss") : "";
            }
        },
        created() {
            let {id, caseguid} = this.$route.query;
            this.caseguid = this.$route.query.caseguid;
            this.getMaterialLists(this.caseguid);

        }
    };
</script>

<style lang="scss" scoped>
    .label {
        font-size: 13px;
        color: #48576a;
        font-weight: bold;
        text-align: right;
    }

    .w110 {
        width: 110px;
    }

    .item_col_last {
        border-bottom: none;
    }

    .item_row {
        /* border-bottom: 1px solid #e8e8e8; */
    }

    .item_col {
        padding: 5px 0 0px 0;
    }

    .bt {
        margin: 5px auto;
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
        margin: 5px auto;
        font-size: 13px;
        /*text-align: center;*/
    }

    .el-form .el-row {
        margin: 0 !important;
    }
</style>
