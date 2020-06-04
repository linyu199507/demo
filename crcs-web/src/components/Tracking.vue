<!--流程跟踪子组件-->
<template>
    <el-table
            :data="data"
            border
            style="font-weight: 500"
            @row-click="rowClick"
            :expand-row-keys="expands"
            row-key="id"
            :row-style="{cursor:'pointer'}"
    >
        <el-table-column type="index" label="序号" min-width="6%" align="center"></el-table-column>
        <el-table-column label="步骤" align="center" min-width="10%">
            <template
                    slot-scope="props"
            >{{ props.row.activitytype?props.row.activitytype:props.row.nodename }}
            </template>
        </el-table-column>
        <el-table-column label="处理部门" prop="handledept" align="center" show-overflow-tooltip
                         min-width="30%"></el-table-column>
        <el-table-column label="处理人员" prop="handler" align="center" show-overflow-tooltip
                         width="120px"></el-table-column>
        <el-table-column label="操作" align="center" prop="nodename" show-overflow-tooltip
                         width="120px"></el-table-column>
        <el-table-column label="处理时间" prop="handletime" :formatter="formData" align="center"
                         width="150px"></el-table-column>
        <el-table-column label="处理意见" prop="handleopinion" show-overflow-tooltip align="center"
                         min-width="34%"></el-table-column>
        <el-table-column type="expand" width="30px">
            <template slot-scope="props">
                <el-form class="demo-table-expand" label-width="100px" label-suffix="：">
                    <el-form-item label="步骤">
                        <span>{{ props.row.activitytype?props.row.activitytype:props.row.nodename }}</span>
                        <!-- <span>{{ props.row.nodename }}</span> -->
                    </el-form-item>
                    <el-form-item label="处理部门">
                        <span>{{ props.row.handledept }}</span>
                    </el-form-item>
                    <el-form-item label="处理人员">
                        <span>{{ props.row.handler }}</span>
                    </el-form-item>
                    <el-form-item label="操作">
                        <span>{{props.row.nodename}}</span>
                    </el-form-item>
                    <el-form-item label="处理时间">
                        <span>{{props.row.handletime?moment(props.row.handletime).format('YYYY-MM-DD HH:mm:ss'):''}}</span>
                    </el-form-item>
                    <br/>
                    <el-form-item label="处理意见" style="width:100%">
                        <span>{{ props.row.handleopinion }}</span>
                    </el-form-item>
                </el-form>
            </template>
        </el-table-column>
    </el-table>
</template>
<script>
    import moment from "moment";
    // 其他页面引用，使用方式：
    // components: {
    //   Tracking,
    // },
    //<tracking :data="list"/>
    export default {
        props: ["data"],
        name: "tracking",
        data() {
            return {
                moment,
                expands: []
            };
        },
        methods: {
            formData(row, column) {
                //时间转换
                if (row.handletime == null) {
                    return "";
                } else {
                    return moment(row.handletime).format("YYYY-MM-DD HH:mm:ss");
                }
            },
            rowClick({id}, event, column) {
                if (this.expands.indexOf(id) === -1) {
                    this.expands.push(id);
                } else {
                    this.expands.splice(this.expands.indexOf(id), 1);
                }
            }
        }
    };
</script>

<style lang="scss">
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
        float: left;
    }

    .el-tabs .el-form .el-row {
        margin-left: 20px;
    }
</style>
