<template>
  <div>
    <div class="order">
      <div class="title">定单信息</div>
      <div class="orderDetail">
        <div class="lang">
          语种:
          <span>{{customerinfo.seatslanguage}}</span>
        </div>
        <div class="about">
          <div class="accept">
            接听坐席:
            <span>{{customerinfo.seatsname}}</span>
          </div>
          <div class="gender">
            性别:
            <span>{{customerinfo.seatssex}}</span>
          </div>
        </div>
        <div class="register">
          登记时间:
          <span>{{$moment(customerinfo.seatsregistrationtime).format("YYYY-MM-DD HH:mm:ss")}}</span>
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
                        <div class="Procontent">{{commomform.rqsttitle}}</div>
                      </li>
                      <li>
                        <div class="Protitle">表单类型:</div>
                        <div class="Procontent">{{commomform.formtype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">姓名:</div>
                        <div class="Procontent">{{commomform.name}}</div>
                      </li>
                      <li>
                        <div class="Protitle">性别:</div>
                        <div class="Procontent">{{commomform.gender}}</div>
                      </li>
                      <li>
                        <div class="Protitle">表单模板:</div>
                        <div class="Procontent">{{commomform.formmodule}}</div>
                      </li>
                      <li>
                        <div class="Protitle">来电号码:</div>
                        <div class="Procontent">{{commomform.rqstphonenumber}}</div>
                      </li>
                      <li>
                        <div class="Protitle">联系电话:</div>
                        <div class="Procontent">{{commomform.linkphonenumber}}</div>
                      </li>
                      <li>
                        <div class="Protitle">是否有效:</div>
                        <div class="Procontent">{{commomform.isvalid}}</div>
                      </li>
                      <li>
                        <div class="Protitle">反映类型:</div>
                        <div class="Procontent">{{commomform.usertype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">回复方式:</div>
                        <div class="Procontent">{{commomform.answertype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">企业类型:</div>
                        <div class="Procontent">{{commomform.entitytype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">工单分类:</div>
                        <div class="Procontent">{{commomform.focus}}</div>
                      </li>
                      <li>
                        <div class="Protitle">企业全称:</div>
                        <div class="Procontent">{{commomform.entname}}</div>
                      </li>
                      <li>
                        <div class="Protitle">企业登记机关:</div>
                        <div class="Procontent">{{commomform.aicid}}</div>
                      </li>
                      <li>
                        <div class="Protitle">企业经营地址:</div>
                        <div class="Procontent">{{commomform.address}}</div>
                      </li>
                      <li>
                        <div class="Protitle">政务标签:</div>
                        <div class="Procontent">{{commomform.govlabel}}</div>
                      </li>
                      <li>
                        <div class="Protitle">问题描述:</div>
                        <div class="Procontent">{{commomform.rqstcontent}}</div>
                      </li>
                      <li>
                        <div class="Protitle">相关附件:</div>
                        <div class="Procontent"></div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!--<div class="registerInfo">-->
                <!--<div class="provider">-->
                  <!--<div class="title">待审批处理反馈</div>-->
                  <!--<div class="content">-->
                    <!--<ul>-->
                      <!--<li>-->
                        <!--<div class="Protitle">处理结果:</div>-->
                        <!--<div class="Procontent">企业</div>-->
                      <!--</li>-->
                    <!--</ul>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
            </div>
          </li>
          <!-- 流程跟踪 -->
          <li v-show="nowIndex == 1">
            <flow orientation="vertical" :style="`height:${6.3*opinionList.length}rem`" class="mb100">
              <template v-for="(item,index) in opinionList" style="margin-bottom1:rem">
                <flow-state :state="index+1" is-done>
                  <template slot="title">
                    <div class="processContent">
                      <ul>
                        <li>
                          <span>步骤：</span>
                          {{item.nodename}}
                        </li>
                        <li>
                          <span>处理人员：</span>
                          {{item.handler}}
                        </li>
                        <li>
                          <x-textarea
                            title="处理部门："
                            v-model="item.handledept"
                            :show-counter="false"
                            :rows="1"
                            placeholder="请输入"
                          ></x-textarea>
                        </li>
                        <li>
                          <span>操作：</span>
                          {{item.operate}}
                        </li>
                        <li>
                          <span>处理时间：</span>
                          {{$moment(customerinfo.seatsregistrationtime).format("YYYY-MM-DD HH:mm:ss")}}
                        </li>
                        <li class="fontStyle">
                          <x-textarea
                            title="处理意见："
                            v-model="item.handleopinion"
                            :show-counter="false"
                            :rows="4"
                            disabled
                            placeholder="请输入"
                          ></x-textarea>
                        </li>
                      </ul>
                    </div>
                  </template>
                </flow-state>
                <flow-line is-done v-if="index+1!=opinionList.length"></flow-line>
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
        <radio :options="stepOptions" v-model="stepValue"></radio>
      </group>
    </popup>
  </div>
</template>
<script>

  import { listDetail } from '../api/api';

export default {
  data() {
    return {
      customerinfo:{},
      commomform:{},
      // 流程跟踪
      opinionList: [],
      scrollItem: ["登记信息", "流程跟踪"],
      stepOptions: ["处理反馈", "分派部门"],
      demo2: "登记信息",
      index: 0,
      nowIndex: 0,
      show: false,
      stepValue: "保存",
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9]
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
      if (this.stepValue === "保存") {
      } else if (this.stepValue === "处理反馈") {
        const params = {
          stepValue: this.stepValue
        };
        this.$router.push({
          path: "/main/DetailOf12345/accept12345form",
          query: params
        });
      } else if (this.stepValue === "分派部门") {
        const params = {
          stepValue: this.stepValue
        };
        this.$router.push({
          path: "/main/DetailOf12345/accept12345form",
          query: params
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

.processContent {
  background: #fff;
  border-radius: 20px;
  padding: 20px;
  width: 500px;
  font-size: @fontStyleOfTitle;
  color: @color;

  ul {
    li {
      span {
        color: #000;
      }
    }
  }
}
.mb100 {
  margin-bottom: 300px;
}
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
@backgroundColor: rgb(45, 161, 223);
.weui-wepay-flow__process {
  background: @backgroundColor!important;
}
.weui-wepay-flow__li_done .weui-wepay-flow__state {
  background: @backgroundColor!important;
}
.weui-cells {
  font-size: 30px !important;
  margin-top: 0 !important;
  margin-bottom: 30px;
  left: 0 !important;
}
.vux-popup-header-right {
  color: rgb(45, 161, 223) !important;
}
.weui-search-bar__cancel-btn {
  color: @backgroundColor!important;
}
.weui-cells_radio .weui-check:checked + .weui-icon-checked:before {
  color: rgb(45, 161, 223) !important;
}
.weui-wepay-flow,
.weui-wepay-flow-auto {
  padding: 150px 40px 20px 40px !important;
}
.weui-wepay-flow_vertical .weui-wepay-flow__bd {
  width: 10px;
}
</style>
