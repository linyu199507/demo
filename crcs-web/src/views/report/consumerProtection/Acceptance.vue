<!--消保-受理处理举报情况统计表-->
<template>
    <section class="acceptance">
        <el-row class="title_info">
            <el-col :xs="24" :sm="24" :md="12" :lg="8">
                <table border="0">
                    <tr>
                        <th>填报单位：</th>
                        <td>广东省佛山市工商行政管理局消保</td>
                    </tr>
                </table>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="6">
                2017年年报
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="10">
                <table>
                    <tr>
                        <th>表&emsp;&emsp;号：</th>
                        <td style="padding-right: 60px;">消保 5 表</td>
                        <th>制定机关：</th>
                        <td>国家工商行政管理总局</td>
                    </tr>
                    <tr>
                        <th>备案机关：</th>
                        <td>国家统计局</td>
                        <th>备案文号：</th>
                        <td>国统办函[2013]35号</td>
                    </tr>
                    <tr>
                        <th>有效期至：</th>
                        <td>2016年3月</td>
                        <th>单&emsp;&emsp;位：</th>
                        <td>件、万元</td>
                    </tr>
                </table>
            </el-col>
        </el-row>
        <el-row class="table_row">
            <el-col>
                <table class="table" border="1" cellspacing="0">
                    <!--标题开始-->
                    <tr>
                        <th rowspan="5" style="min-width: 200px">项&emsp;目</th>
                        <th rowspan="5" style="width: 30px;">机器编号</th>
                        <th rowspan="5">受理举报案件总数（件）</th>
                        <th colspan="11">其中：已处理举报案件</th>
                    </tr>
                    <tr></tr>
                    <tr>
                        <th rowspan="3">小计<br/>（件）</th>
                        <th rowspan="3">案值<br/>（万元）</th>
                        <th rowspan="3">没收金额<br/>（万元）</th>
                        <th rowspan="3">罚款金额<br/>（万元）</th>
                        <th colspan="9">违法主体（件）</th>
                    </tr>
                    <tr>
                        <th rowspan="2" style="border-right: none;padding-top: 0;">
                            内资企业
                        </th>
                        <th style="border-left: none"></th>
                        <th rowspan="2">外商投资企业</th>
                        <th rowspan="2">个体工商户</th>
                        <th rowspan="2">农民专业合作社</th>
                        <th rowspan="2">自然人</th>
                        <th rowspan="2">其他</th>
                    </tr>
                    <tr>
                        <th>
                            私营企业
                        </th>
                    </tr>
                    <tr>
                        <th>甲</th>
                        <th>乙</th>
                        <th class="number_content">1</th>
                        <th class="number_content">2</th>
                        <th class="number_content">3</th>
                        <th class="number_content">4</th>
                        <th class="number_content">5</th>
                        <th class="number_content">6</th>
                        <th class="number_content">7</th>
                        <th class="number_content">8</th>
                        <th class="number_content">9</th>
                        <th class="number_content">10</th>
                        <th class="number_content">11</th>
                        <th class="number_content">12</th>
                    </tr>
                    <!--标题结束-->
                    <!--内容开始-->
                    <tr>
                        <th>合&emsp;计</th>
                        <th>1</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">1.违反消费者权益保护法规</th>
                        <th>2</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">2.违反产品质量管理法规</th>
                        <th>3</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">3.违反食品安全法规</th>
                        <th>4</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">4.违反反不正当竞争法规</th>
                        <th>5</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">4.违反反不正当竞争法规</th>
                        <th>5</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">5.违反企业、个体私营登记管理法规</th>
                        <th>6</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title_two">其中：无照经营</th>
                        <th>7</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title_three">超出核准登记的范围经营</th>
                        <th>8</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">6.违反商标管理法规</th>
                        <th>9</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title_two">其中：商标假冒</th>
                        <th>10</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title_three">商标侵权</th>
                        <th>11</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title">7.违反广告管理法规</th>
                        <th>12</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="left_title_two">其中：虚假广告</th>
                        <th>13</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title">8.违反合同管理法规</th>
                        <th>14</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title">9.违反广告管理法规</th>
                        <th>15</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title">10.违反市场监督管理法规</th>
                        <th>16</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title_two">其中：违反网络商品交易管理法规</th>
                        <th>17</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title">11.制售、传播非法出版物行为</th>
                        <th>18</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                   <tr>
                        <th class="left_title">12.其他</th>
                        <th>19</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <!--内容结束-->
                </table>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    export default {
        name: "CPAcceptance",
        data() {
            return {
            }
        },
        methods: {

        }
    }
</script>

<style scoped>
    .acceptance {
        /*box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)*/
    }

    @media (min-width: 992px) {
        .title_info {
            display: flex;
            align-items: flex-end;
        }
    }

    .table_row {
        overflow: auto;
    }

    .table {
        border: 1px solid #bfbfbf;
        border-collapse: collapse;
        margin-top: 10px;
        width: 100%;
    }

    .table th {
        background-color: #eef1f6;
        color: #1f2d3d;
    }

    .table td, .table th {
        padding: 10px;
    }

    .number_content {
        min-width: 60px;
    }
    .left_title{
        text-align: left;
    }
    .left_title_two{
        text-align: left;
        text-indent: 1em;
    }
    .left_title_three{
        text-align: left;
        text-indent: 2em;
    }
</style>
