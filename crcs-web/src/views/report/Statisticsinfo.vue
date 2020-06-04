<template>
    <div>
        <div class="handle_btn">
            <el-button size="small" @click="toUploadOpen('word')" class="el-button--primary" style="float: left"><i class="fa fa-upload"></i>
                上传文件
            </el-button>
            <el-button type="danger" @click="handleDel" size="small" icon="delete">删除</el-button>
            <el-col style="height: 15px;"></el-col>
        </div>
        <el-table
                :data="listData"
                highlight-current-row
                :row-class-name="tableRowClassName"
                v-loading="complaintListLoading"
                element-loading-text="拼命加载中"
                @selection-change="selsChange"
                border
                style="width: 100%;"
                :row-style="{cursor:'pointer'}"
        >
            <el-table-column prop="select" type="selection" min-width="3%" align="center"></el-table-column>
            <el-table-column prop="index" label="序号" min-width="5%" align="center"
                             :formatter="formatIndex"></el-table-column>

            <el-table-column prop="filename" label="文件名称" min-width="15%" align="center"></el-table-column>
            <el-table-column
                    prop="uploadtime"
                    :formatter="formatDate"
                    label="上传时间"
                    min-width="10%"
                    align="center"
            ></el-table-column>
            <el-table-column
                    prop="handler"
                    label="上传者"
                    min-width="6%"
                    align="center"
                    header-align="center"
                    show-overflow-tooltip
            ></el-table-column>
            <el-table-column label="操作" min-width="8%" align="center">
                <template scope="scope">
                    <el-button size="small" @click="previewFile(scope.row)" type="primary"><i class="fa fa-download"></i> 下载</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
        ></el-pagination>
        <el-dialog
                :title="fileUploadText"
                :visible.sync="fileUploadVisible"
                size="tiny">
            <el-upload
                    :auto-upload="false"
                    :file-list="fileListsTwo"
                    ref="uploadTwo"
                    :data="this.requestMap"
                    :on-error="uploadFile"
                    :on-progress="progress"
                    :on-success="uploadSuccess"
                    :before-upload="beforeUpload"
                    :multiple="true"
                    action="/crcs/filesUpload/uploadStatisticsinfo"
            >
                <div slot="tip" class="el-upload__tip">已选取的文件，等待上传</div>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button size="small" type="success" @click="submitUpload('uploadTwo')">确认上传
                </el-button>
            </el-upload>
        </el-dialog>
    </div>
</template>
<script>
    import {searchStatisticsinfos,deleteStatisticsinfos} from "../../api/api";
    import moment from "moment";

    export default {
        data() {
            return {
                currentPage: 1,
                size: 10,
                total: 0,
                searchForm: {
                    number: "",
                    registerDate: "",
                    currentLink: ""
                },
                user:JSON.parse(localStorage.getItem("user")),
                listData:[],
                complaintListLoading: false,
                fileUploadText: '',
                fileUploadVisible: false,
                fileList: [],
                requestMap: {
                    handler:JSON.parse(localStorage.getItem("user")).name,
                    handleruserid:JSON.parse(localStorage.getItem("user")).primaryKey
                },
                fileListsTwo: [],
                list: [], //列表选中列id
            };
        },
        mounted() {
            this.getsldjList();
        },

        methods: {
            handleEdit(index, row) {
            },
            selsChange: function (sels) {
                // this.sels = sels;
                this.list = [];
                sels.map(item => {
                    this.list.push(item.id);
                });
            },
            getSearchList() {
                this.getsldjList();
            },
            handleSizeChange(val) {
                this.size = val;
                this.getsldjList();
            },
            formatIndex(row, column, cellValue) {
                return this.size * (this.currentPage - 1) + 1 + row.index;
            },
            tableRowClassName(row, index) {
                //把每一行的索引放进row
                row.index = index
            },
            //日期转换
            formatDate(row, column) {
                return this.$moment(row.uploadtime).format("YYYY-MM-DD HH:mm:ss");
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getsldjList();
            },
            //列表查询
            getsldjList() {
                let params = {
                    page: this.currentPage,
                    total: this.total,
                    size: this.size,
                };
                searchStatisticsinfos(params).then(res => {
                    if (res.code === 0) {
                        this.listData = res.data.records;
                        this.currentPage = res.data.current;
                        // this.current = res.data.current;
                        this.size = res.data.size;
                        this.total = res.data.total;
                    }
                });
            },
            toUploadOpen(type) {
                this.fileUploadVisible = true
            },
            uploadSuccess(response, file, fileList) {
                if (file.response.code === 0) {
                    this.$message({
                        message: "上传成功",
                        type: "success",
                        duration: "1000"
                    });
                    this.fileUploadVisible = false,
                    this.getsldjList();
                } else {
                    this.$message.error("上传失败" + file.response.msg);
                }
            },

            uploadFile(err, file, fileList) {
                // alert(err);
            },
            submitUpload(value) {
                if (this.$route.query.caseguid === undefined) {
                    this.requestMap.caseguid = this.randomId;
                } else {
                    this.requestMap.caseguid = this.$route.query.caseguid;
                }
                this.$refs[value].submit();
            },
            progress(event, file, fileList) {
                // console.log(event);
            },
            beforeUpload(file) {
                let fileName = file.name;
                let pos = fileName.lastIndexOf(".");
                const isLt = file.size / 1024 / 1024 / 20 <= 1;
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
                    ".zip"
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
            previewFile(file) {
                this.download(file.filesavepath)
            },
            download(url) {
                // window.location.href = `${process.env.Download_URL}` + url;
                window.open(`${process.env.Download_URL}` + url,"_blank")
            },
            //删除
            handleDel() {
                if (!this.list || this.list.length === 0) {
                    this.$message({
                        message: "至少勾选一条待办业务！",
                        type: "warning",
                        duration: "1000"
                    });
                    return;
                }
                let user = localStorage.getItem("user");
                let ids = this.list.toString();
                this.$confirm("确认删除该记录吗?", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.complaintListLoading = true;
                        let para = {
                            ids: ids,
                            user: JSON.parse(user)
                        };
                        deleteStatisticsinfos(para).then(res => {
                            this.complaintListLoading = false;
                            //NProgress.done();
                            this.$message({
                                message: "删除成功！",
                                type: "success",
                                duration: "1000"
                            });
                            this.getsldjList();
                        });
                    })
                    .catch(() => {});
            },
        }
    };
</script>
<style lang="scss" scoped>
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
        margin-bottom: 20px;
    }

    .el-input__inner {
        width: 200px !important;
    }

    .el-pagination {
        white-space: nowrap;
        padding: 2px 5px;
        color: #48576a;
        text-align: right;
        margin-top: 20px;
    }
</style>
