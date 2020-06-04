<!--报表查询结果-->
<template>
    <section class="query_results" v-loading="loading">
        <!--div class="operation">
            <el-button type="primary" size="small" @click="exportExcel">导出报表</el-button>
            <el-button type="primary" size="small" @click="newWindow">新窗口查看</el-button>
            <el-button type="primary" size="small" @click="$router.push('/tjfx/tjbb/bbcx')">返回查询</el-button>
        </div>-->
        <iframe width="100%" id="iframe" height='100%'
                :src="aicorgUrl"
                frameborder="0"></iframe>
    </section>
</template>

<script>
    export default {
        name: "QueryResults",
        data() {
            return {
                loading: true,
                tableName: undefined,
                aicorgUrl: `${process.env.AICORG_URL}/loginController/loginGet.do?userName=sdadmin@fsgs&password=123456`,
                reportUrl: "http://172.22.80.37:8082/aicorg",
                reportDownloadUrl: "http://172.22.80.37:8082/aicorg"
            };
        },
        created() {
            this.tableName = this.$route.query.tableName
            if (!this.tableName) {
                // this.$router.push('/xtgl/zjjg/yhgl')
                this.$router.push('/xtgl/zjjg')
            }
            this.reportUrl += `${this.tableName}&_t=0`
            this.reportDownloadUrl += `${this.tableName}&_t=0`
        },
        methods: {
            exportExcel() {
                window.open(this.reportDownloadUrl)
            },
            newWindow() {
                window.open(this.reportUrl)
            }
        },
        mounted() {
            let iframe = window.frames["iframe"]
            iframe.onload = () => {
                //加载完
                this.loading = false
                // let iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                // console.log(iframeWin.document.documentElement.scrollHeight);
                // console.log(iframeWin.document.body.scrollHeight);
                // if (iframeWin.document.body) {
                //     iframe.height = 20 + iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                // }

            }
        }
    }
</script>

<style lang="scss">
    .query_results {
        .operation {
            padding: 0 0 5px 5px;
        }

        #iframe {
            min-height: 80vh;
        }
    }


</style>
