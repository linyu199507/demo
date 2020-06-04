
<template>
<!-- 消费表单受理后详情 -->
  <div>
    <div class="order">
      <div class="title">定单信息</div>
      <div class="orderDetail">

        <div class="about">
          <div class="accept">
            接听坐席:
            <span>{{customerinfo.seatsname}}</span>
          </div>
        </div>
        <div class="register">
          登记时间:
          <span>{{$moment(customerinfo.seatsregistrationtime).format("YYYY-MM-DD HH:mm:ss")}}</span>
        </div>
        <div class="gender">
          性别:
          <span>{{customerinfo.seatssex}}</span>
        </div>
        <div class="lang">
          语种:
          <span>{{customerinfo.seatslanguage}}</span>
        </div>
      </div>
    </div>

    <div>
      <ul class="tabs">
        <li
          class="li-tab"
          v-for="(item,index) in scrollItem"
          @click="toggleTabs(index)"
          :class="{active:index!=nowIndex}"
          :key="index"
        >{{item}}</li>
      </ul>
      <div class="formContent" style="margin-bottom:60px">
        <ul>
          <!-- 登记信息 -->
          <li v-show="nowIndex == 0">
            <div class="register">
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">提供方信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">标题:</div>
                        <div class="Procontent">{{commomform.seatssex}}</div>
                      </li>
                      <li>
                        <div class="Protitle">表单类型:</div>
                        <div class="Procontent">消费投诉表单</div>
                      </li>
                      <li>
                        <div class="Protitle">投诉人姓名:</div>
                        <div class="Procontent">杜浩五</div>
                      </li>
                      <li>
                        <div class="Protitle">性别:</div>
                        <div class="Procontent">男</div>
                      </li>
                      <li>
                        <div class="Protitle">来电号码:</div>
                        <div class="Procontent">13650738837</div>
                      </li>
                      <li>
                        <div class="Protitle">反馈类型:</div>
                        <div class="Procontent">企业</div>
                      </li>
                      <li>
                        <div class="Protitle">回复方式:</div>
                        <div class="Procontent">网上</div>
                      </li>
                      <li>
                        <div class="Protitle">类型:</div>
                        <div class="Procontent">外资投资企业-外商投资股份有限公司</div>
                      </li>
                      <li>
                        <div class="Protitle">工单分类:</div>
                        <div class="Procontent">普通</div>
                      </li>
                      <li>
                        <div class="Protitle">区域:</div>
                        <div class="Procontent">顺德区九江镇</div>
                      </li>
                      <li>
                        <div class="Protitle">投诉票据:</div>
                        <div class="Procontent">顺德区九江镇</div>
                      </li>
                      <li>
                        <div class="Protitle">民族:</div>
                        <div class="Procontent">汉</div>
                      </li>
                      <li>
                        <div class="Protitle">身份:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">残疾人标识:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">邮政编码:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">证件类型:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">证件号码:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">职业:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">工作单位:</div>
                        <div class="Procontent"></div>
                      </li>
                      <li>
                        <div class="Protitle">投诉人地址:</div>
                        <div class="Procontent">广东省广州市天河区中洲交易中心14层1409号</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">涉及主体信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">名称:</div>
                        <div class="Procontent">发派克旗舰店</div>
                      </li>
                      <li>
                        <div class="Protitle">联系方式:</div>
                        <div class="Procontent">13650738837</div>
                      </li>
                      <li>
                        <div class="Protitle">经营地址:</div>
                        <div class="Procontent">广东省天河区东风3路23巷34栋北门34号厂房</div>
                      </li>
                      <li>
                        <div class="Protitle">实际注册地址:</div>
                        <div class="Procontent">男</div>
                      </li>
                      <li>
                        <div class="Protitle">核查名称:</div>
                        <div class="Procontent">13650738837</div>
                      </li>
                      <li>
                        <div class="Protitle">核查经营地址:</div>
                        <div class="Procontent">企业</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">涉及客体信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">商品/服务名称:</div>
                        <div class="Procontent">沙发</div>
                      </li>
                      <li>
                        <div class="Protitle">商品品牌:</div>
                        <div class="Procontent">无</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及客体分类:</div>
                        <div class="Procontent">商品类-通讯器材-固定电话</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及问题分类:</div>
                        <div class="Procontent">其他</div>
                      </li>
                      <li>
                        <div class="Protitle">关键字:</div>
                        <div class="Procontent">网购 沙发</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及金额:</div>
                        <div class="Procontent">52330元</div>
                      </li>
                      <li>
                        <div class="Protitle">生产企业:</div>
                        <div class="Procontent">广东省xxxx集团</div>
                      </li>
                      <li>
                        <div class="Protitle">是否网购:</div>
                        <div class="Procontent">是</div>
                      </li>
                      <li>
                        <div class="Protitle">商品进口标识:</div>
                        <div class="Procontent">商品进口标识</div>
                      </li>
                      <li>
                        <div class="Protitle">型号规格:</div>
                        <div class="Procontent">型号规格</div>
                      </li>
                      <li>
                        <div class="Protitle">商品数量:</div>
                        <div class="Procontent">123214</div>
                      </li>
                      <li>
                        <div class="Protitle">计量单位:</div>
                        <div class="Procontent">件</div>
                      </li>
                      <li>
                        <div class="Protitle">商品/服务价格:</div>
                        <div class="Procontent">1243元</div>
                      </li>
                      <li>
                        <div class="Protitle">购买日期:</div>
                        <div class="Procontent">2019年11月11日</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">业务具体信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">事件级别:</div>
                        <div class="Procontent">二级</div>
                      </li>
                      <li>
                        <div class="Protitle">政务标签:</div>
                        <div class="Procontent">网购问题</div>
                      </li>
                      <li>
                        <div class="Protitle">问题描述:</div>
                        <div class="Procontent">
                          市民反映10月4号通过唯
                          品会购买了沙发套装（网购单号：19100467873735），下单时要求乳胶弹簧，收到货后发现货不对板，做工、质量很差，只有一点乳胶，其他以劣质海绵为主，申请了7天无理由退货，寄还货品后商家一直拖延不退款，与商家协商无果，现希望相关部门介入协调要求商家尽快退款已经承担1600元的物流费。
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">处理相关信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">投诉要求:</div>
                        <div class="Procontent">二级</div>
                      </li>
                      <li>
                        <div class="Protitle">是否出现场:</div>
                        <div class="Procontent">是</div>
                      </li>
                      <li>
                        <div class="Protitle">相关附件:</div>
                        <div class="Procontent">
                          文件1
                          文件2
                          文件3
                          文件4
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!--处办要求  -->
          <li v-show="nowIndex == 1">
            <div class="register">
              <div class="registerInfo">
                <div class="provider">
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">应受理时限:</div>
                        <div class="Procontent">2019-11-11 00:00:00</div>
                      </li>
                      <li>
                        <div class="Protitle">应受理到期时间:</div>
                        <div class="Procontent">2019-11-11 00:00:00</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!-- 处理信息 -->
          <!--<li v-show="nowIndex == 2">-->
            <!--<div class="register">-->
              <!--<div class="registerInfo">-->
                <!--<div class="provider">-->
                  <!--<div class="title">处理信息</div>-->
                  <!--<div class="content">-->
                    <!--<ul>-->
                      <!--<li>-->
                        <!--<div class="Protitle">登记单编号:</div>-->
                        <!--<div class="Procontent">2143453654768679870465</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">案由:</div>-->
                        <!--<div class="Procontent">2019-11-11 00:00:00</div>-->
                      <!--</li>-->
                    <!--</ul>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="registerInfo">-->
                <!--<div class="provider">-->
                  <!--<div class="title">涉及主体信息</div>-->
                  <!--<div class="content">-->
                    <!--<ul>-->
                      <!--<li>-->
                        <!--<div class="Protitle">是否具有营养执照:</div>-->
                        <!--<div class="Procontent">有</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">统一社会信用代码/注册号:</div>-->
                        <!--<div class="Procontent">32523525322436575767</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">所在行业类别:</div>-->
                        <!--<div class="Procontent">计算机</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">市场名称:</div>-->
                        <!--<div class="Procontent">市场名称</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">联系方式:</div>-->
                        <!--<div class="Procontent">电话</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">地址:</div>-->
                        <!--<div class="Procontent">广东省天河</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">是否转送其他部门:</div>-->
                        <!--<div class="Procontent">是</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">应急处置进展状态:</div>-->
                        <!--<div class="Procontent">二级</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">反馈登记人:</div>-->
                        <!--<div class="Procontent">众望通</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">协助单位类型:</div>-->
                        <!--<div class="Procontent">互联网</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">办案人员:</div>-->
                        <!--<div class="Procontent">秃顶大师</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">办案人员电话:</div>-->
                        <!--<div class="Procontent">自己自力更生</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">群体投诉案件:</div>-->
                        <!--<div class="Procontent">聚众吃夜宵</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">涉外投诉案件:</div>-->
                        <!--<div class="Procontent">偷渡去越南烧烤</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">是否转立案:</div>-->
                        <!--<div class="Procontent">是</div>-->
                      <!--</li>-->
                      <!--<li>-->
                        <!--<div class="Protitle">对投诉中发现违法行为实施行政处罚:</div>-->
                        <!--<div class="Procontent">偷吃夜宵者请顺德组吃大餐</div>-->
                      <!--</li>-->
                    <!--</ul>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</li>-->
          <!-- 流程跟踪 -->
          <li v-show="nowIndex == 3">
            <flow orientation="vertical" :style="`height:${6.3*flowList.length}rem`" class="mb100 mt100">
              <template v-for="(item,index) in flowList">
                <flow-state :state="index+1" is-done>
                  <template slot="title">
                    <div class="processContent">
                      <ul>
                        <li>
                          <span>步骤：</span> 受理登记
                        </li>
                        <li>
                          <span>处理人员：</span> 张倩华
                        </li>
                        <li>
                          <span>处理部门：</span>
                          消费维权业务队列
                        </li>
                        <li>
                          <span>操作：</span>
                          受理登记
                        </li>
                        <li>
                          <span>处理时间：</span>
                          2019-11-19 15:35:53
                        </li>
                        <li class="fontStyle">
                          <span>处理意见：</span>
                          版权声明版权声明版权声明版权声明版权声明版权声明版权声明版
                          权声明版权声明版权声明版权声明版权声明版权声明版权声明
                        </li>
                      </ul>
                    </div>
                  </template>
                </flow-state>
                <flow-line is-done v-if="index+1!=flowList.length"></flow-line>
              </template>
            </flow>
          </li>
        </ul>
      </div>
    </div>
    <!--  -->
    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">处理</x-button>
    </div>
    <!-- 弹窗 -->
    <popup v-model="show">
      <popup-header
        :left-text="('取消')"
        :right-text="('确定')"
        :title="('请选择操作步骤')"
        :show-bottom-border="false"
        @click.native="comfirm"
        @on-click-left="show = false"
        @on-click-right="show = false"
      ></popup-header>
      <group gutter="0">
        <radio v-if="currentLink === 'moveToLowerDept'" :options="stepOptionsOne" v-model="stepValue"></radio>
        <radio v-if="currentLink === 'moveToLowerOrg'" :options="stepOptions" v-model="stepValue"></radio>
        <radio v-if="currentLink === 'moveToLowerPersonnel'" :options="stepOptions" v-model="stepValue"></radio>
      </group>
    </popup>
  </div>
</template>
<script>
  import { listDetail } from '../api/api';

export default {
  data() {
    return {
      scrollItem: ['登记信息', '处办要求', '流程跟踪'],
      stepOptionsOne: ['处理反馈'],
      stepOptionsOne: ['处理反馈',  '分派人员'],
      stepOptions: ['处理反馈', '分派部门',  '分派人员'],
      demo2: '登记信息',
      // 定单信息
      orderInfo: {},
      // 处办要求
      handledemand: {},
      customerinfo: {},
      commomform: {},
      currentLink: "",
      index: 0,
      nowIndex: 0,
      show: false,
      stepValue: '保存',
      caseguid: '',
      id: '',
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
    };
  },
  methods: {
    toggleTabs(index) {
      this.nowIndex = index;
    },
    showChoose() {
      this.show = true;
    },
    comfirm() {
      if (this.stepValue === '保存') {
      } else if (this.stepValue === '处理反馈') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'commommodule',
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派下级') {
        const params = {
          stepValue: this.stepValue,
        };
        console.log(params)
        return " ";
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派人员') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'commommodule',
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派部门') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          nextNodeId: "moveToLowerDept",
          fromType: 'commommodule',
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else {
        console.log(this.stepValue);
      }
    },
    getlistDetail() {
      const params = {
        caseguid: this.caseguid,
        id: this.id,
        systemsource: '02',
      };
      listDetail(params).then((res) => {
        console.log(res);
        console.log(this.$route.query);
        if (res.code === 0) {

          this.customerinfo = res.data.customerinfo;
          this.commomform = res.data.commomform;
          this.materialinfoList = res.data.materialinfoList;
          this.opinionList = res.data.opinionList;
          console.log(this.$route.query.nextnodeid !== undefined);
          console.log(this.$route.query);
          if (this.$route.query.nextnodeid !== undefined) {
            // console.log(res.data.tasklist.nextnodeid);
            this.currentLink = this.$route.query.nextnodeid;

            console.log(this.currentLink);

            // console.log(res.data);
            // console.log('=========');
            // console.log(this.$route.query);
          } else {
            this.currentLink = res.data.tasklist.nextnodeid;
            console.log(this.currentLink);
          }
          // this.handledemand = res.data.handledemand;
        }

      });
    },
  },
  mounted() {
    this.id = this.$route.query.id;
    this.caseguid = this.$route.query.caseguid;
    console.log(this.$route.query);
    this.getlistDetail();
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);
span {
  color: @color;
}
ul {
  li {
    list-style: none;
  }
}
.vux-swiper {
  height: auto !important;
  // height: 50rem !important;
}

.order {
  background: #fff;
  width: 90%;
  margin: 0 auto 20px;
  border-radius: 10px;
  padding: 20px;
  .title {
    font-size: @fontStyleOfTitle;
    margin-bottom: 20px;
    padding-bottom: 10px;
    border-bottom: 2px solid #ccc;
  }
}
.orderDetail {
  font-size: 26px;
  .accept {
    flex: 1;
  }
  .lang {
    color: #000;
    margin-bottom: 10px;
  }
  .gender {
    flex: 1;
  }
  .about {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin-bottom: 10px;
  }
  .register {
    margin-bottom: 10px;
  }
}
.scrollable .vux-tab-item {
  -webkit-box-flex: 0;
  flex: 0 0 33% !important;
}
.vux-tab {
  background: @backgroundColor !important;
}
.vux-tab .vux-tab-item.vux-tab-selected {
  color: #fff !important;
  border-bottom: 3px solid #fff !important;
}
.vux-tab-ink-bar {
  background: #fff !important;
}
.vux-tab .vux-tab-item {
  color: rgba(255, 255, 255, 0.7) !important;
}
.registerInfo {
  background: #fff;
  width: 90%;
  padding: 20px;
  margin: 20px auto;
  border-radius: 20px;
  .provider {
    .title {
      border-bottom: 1px solid #ccc;
      margin-bottom: 20px;
      padding-bottom: 10px;
      font-size: @fontStyleOfTitle;
    }
    .content {
      ul {
        list-style: none;
        margin: 0;
        padding: 0;
        li {
          display: flex;
          justify-content: space-between;
          // align-items: center;
          font-size: @fontStyleOfTitle;
          margin-bottom: 5px;
          .Protitle {
            flex: 1;
            text-align: left;
          }
          .Procontent {
            flex: 1;
            text-align: right;
            color: @color;
          }
        }
      }
    }
  }
}
// .weui-wepay-flow_vertical .weui-wepay-flow__bd {
//   width: 10px;
// }
// .weui-wepay-flow,
// .weui-wepay-flow-auto {
//   padding: 150px 40px 20px 40px !important;
// }
// .processContent {
//   background: #fff;
//   border-radius: 20px;
//   padding: 20px;
//   width: 500px;
//   font-size: @fontStyleOfTitle;
//   color: @color;

//   ul {
//     li {
//       span {
//         color: #000;
//       }
//     }
//   }
// }

.tabs {
  height: 100px;
  line-height: 100px;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  border-top: 2px solid @backgroundColor;
  margin-bottom: 50px;
}
.li-tab {
  flex: 1;
  text-align: center;
  background: @backgroundColor;
}
.active {
  background: #fff;
  color: @backgroundColor;
  font-weight: 600;
}
.weui-wepay-flow__li_done .weui-wepay-flow__state {
  background: @backgroundColor!important;
}
.weui-wepay-flow__process {
  background: @backgroundColor!important;
}
.fontStyle {
  width: 100%;
  display: block;
  white-space: initial;
}
.detail {
  text-align: center;
  margin-top: 100px;
}
.vux-popup-header-right,
.weui-search-bar__cancel-btn {
  color: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: @backgroundColor!important;
}
.weui-btn_primary {
  background: @backgroundColor!important;
}
</style>
<style lang="less">
@import url('../assets/font/commonOfCommon.less');
.vux-popup-header-right {
  color: rgb(45, 161, 223)!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: rgb(45, 161, 223)!important;

}
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
</style>
