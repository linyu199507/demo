<template>
  <!-- 12345受理后的经济举报表单 -->
  <div class="mt30">
    <div class="order">
      <div class="title">定单信息</div>
      <div class="orderDetail">
        <div class="lang">
          语种:
          <span>{{orderInformation.seatslanguage}}</span>
        </div>
        <div class="about">
          <div class="accept">
            接听坐席:
            <span>{{orderInformation.seatsname}}</span>
          </div>
          <div class="gender">
            性别:
            <span>{{orderInformation.seatssex}}</span>
          </div>
        </div>
        <div class="register">
          登记时间:
          <span>{{$moment(orderInformation.seatsregistrationtime).format("YYYY-MM-DD HH:mm:ss")}}</span>
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
                        <div class="Procontent">{{economyFrom.rqsttitle}}</div>
                      </li>
                      <li>
                        <div class="Protitle">表单类型:</div>
                        <div class="Procontent">{{economyFrom.formtype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">表单模板:</div>
                        <div class="Procontent">{{economyFrom.accordtype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">投诉人姓名:</div>
                        <div class="Procontent">{{economyFrom.rqstperson}}</div>
                      </li>
                      <li>
                        <div class="Protitle">性别:</div>
                        <div class="Procontent">{{economyFrom.gender}}</div>
                      </li>
                      <li>
                        <div class="Protitle">来电号码:</div>
                        <div class="Procontent">{{economyFrom.rqstnumber}}</div>
                      </li>
                      <li>
                        <div class="Protitle">联系电话:</div>
                        <div class="Procontent">{{economyFrom.linknumber}}</div>
                      </li>
                      <li>
                        <div class="Protitle">反映类型:</div>
                        <div class="Procontent">{{economyFrom.usertype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">回复方式:</div>
                        <div class="Procontent">{{economyFrom.answertype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">类别:</div>
                        <div class="Procontent">{{economyFrom.providelb}}</div>
                      </li>
                      <li>
                        <div class="Protitle">工单分类:</div>
                        <div class="Procontent">{{economyFrom.focus}}</div>
                      </li>
                      <li>
                        <div class="Protitle">投诉人地址:</div>
                        <div class="Procontent">{{economyFrom.rqstaddress}}</div>
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
                        <div class="Procontent">{{economyFrom.subjectname}}</div>
                      </li>
                      <li>
                        <div class="Protitle">联系方式:</div>
                        <div class="Procontent">{{economyFrom.linktype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">经营地址:</div>
                        <div class="Procontent">{{economyFrom.subjectaddress}}</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!-- 涉及客体信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">涉及客体信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">商品/服务名称:</div>
                        <div class="Procontent">{{economyFrom.objname}}</div>
                      </li>
                      <li>
                        <div class="Protitle">商品品牌:</div>
                        <div class="Procontent">{{economyFrom.brand}}</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及客体分类:</div>
                        <div class="Procontent">{{economyFrom.objclassify}}</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及问题分类:</div>
                        <div class="Procontent">{{economyFrom.problemtype}}</div>
                      </li>
                      <li>
                        <div class="Protitle">关键字:</div>
                        <div class="Procontent">{{economyFrom.keyword}}</div>
                      </li>
                      <li>
                        <div class="Protitle">涉及金额(元):</div>
                        <div class="Procontent">{{economyFrom.objmoney}}</div>
                      </li>
                      <li>
                        <div class="Protitle">生产企业:</div>
                        <div class="Procontent">{{economyFrom.producecompany}}</div>
                      </li>
                      <li>
                        <div class="Protitle">是否网购:</div>
                        <div class="Procontent">{{economyFrom.isweb}}</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <!-- 业务具体信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">业务具体信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">涉嫌违法行为:</div>
                        <div class="Procontent">{{economyFrom.illegalrules}}</div>
                      </li>
                      <li>
                        <div class="Protitle">事件级别:</div>
                        <div class="Procontent">{{economyFrom.eventlevel}}</div>
                      </li>
                      <li>
                        <div class="Protitle">政务标签:</div>
                        <div class="Procontent">{{economyFrom.govlabel}}</div>
                      </li>
                      <li>
                        <div class="Protitle">问题描述:</div>
                        <div class="Procontent">{{economyFrom.problemdescribe}}</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!-- 处理相关信息 -->
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">处理相关信息</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">是否出现场:</div>
                        <div class="Procontent">{{economyFrom.isoutmall}}</div>
                      </li>
                      <li>
                        <div class="Protitle">是否反馈:</div>
                        <div class="Procontent">{{economyFrom.isfeedback}}</div>
                      </li>
                      <li>
                        <div class="Protitle">是否需要奖励:</div>
                        <div class="Procontent">{{economyFrom.isaward}}</div>
                      </li>
                      <li>
                        <div class="Protitle">相关附件:</div>
                        <div class="Procontent">
                          <ul>
                            <li v-for="(item,index) in materialinfoList" :key="index">
                              <span>{{index+1}}、</span>
                              {{item.attachfilename}}
                            </li>
                          </ul>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!-- 处办要求 -->
          <li v-show="nowIndex == 1">
            <div class="register">
              <div class="registerInfo">
                <div class="provider">
                  <div class="title">处办要求</div>
                  <div class="content">
                    <ul>
                      <li>
                        <div class="Protitle">应受理时限:</div>
                        <div
                          class="Procontent"
                        >{{$moment(handledemand.accepttimesurplus ).format("YYYY-MM-DD HH:mm:ss")}}</div>
                      </li>
                      <li>
                        <div class="Protitle">应受理到期时间:</div>
                        <div
                          class="Procontent"
                        >{{$moment(handledemand.accepttime).format("YYYY-MM-DD HH:mm:ss")}}</div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </li>
          <!-- 流程跟踪 -->
          <li v-show="nowIndex == 2">
            <flow
              orientation="vertical"
              :style="`height:${6.3*opinionList.length}rem`"
              class="mb100"
            >
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
                          <!-- <span>处理部门：</span>
                          {{item.handledept}}-->
                        </li>
                        <li>
                          <span>操作：</span>
                          {{item.operate}}
                        </li>
                        <li>
                          <span>处理时间：</span>
                          {{$moment(orderInformation.seatsregistrationtime).format("YYYY-MM-DD HH:mm:ss")}}
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
                          <!-- <span>处理意见：</span>
                          {{item.handleopinion}}-->
                        </li>
                      </ul>
                    </div>
                  </template>
                </flow-state>
                <flow-line is-done v-if="index+1!= opinionList.length"></flow-line>
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
        <!-- 当前节点为分派下级机关 -->
        <radio v-if="currentLink==='superiorProcessing'" :options="stepOptionsSix" v-model="stepValue"></radio>
        <radio v-if="currentLink==='regionAudit'" :options="stepleaderOne" v-model="stepValue"></radio>
        <radio v-if="currentLink==='leaderApr'" :options="stepleader" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerOrg' && tasklist.isaccept === '1'" :options="stepOptions" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerOrg' && tasklist.isaccept != '1'" :options="stepOptionsFive" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerDept' && tasklist.isregistercase === '是'" :options="stepOptionsThree" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerDept' && tasklist.isregistercase != '是'" :options="stepOptionsOne" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerPersonnel' && tasklist.isregistercase != '是'" :options="stepOptionsOne" v-model="stepValue"></radio>
        <radio v-if="currentLink==='moveToLowerPersonnel' && tasklist.isregistercase === '是'" :options="stepOptionsThree" v-model="stepValue"></radio>
        <radio v-if="currentLink==='accept' && tasklist.fromnodeid ==='moveToLowerOrg'" :options="stepOptionsSix" v-model="stepValue"></radio>
        <radio v-if="currentLink==='accept' && tasklist.fromnodeid ==='moveToLowerDept'" :options="stepOptionsOne" v-model="stepValue"></radio>
        <radio v-if="currentLink==='registerCase' && user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" :options="stepOptionsTwo" v-model="stepValue"></radio>
        <radio v-if="currentLink==='registerCase' && tasklist.fromnodeid != 'moveToLowerDept' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" :options="stepOptionsFour" v-model="stepValue"></radio>
        <radio v-if="currentLink==='registerCase' && tasklist.fromnodeid === 'moveToLowerDept' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" :options="stepOptionsServer" v-model="stepValue"></radio>
        <radio v-if="currentLink==='noRegisterCase' && user.unitID == 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" :options="stepOptionsTwo" v-model="stepValue"></radio>
        <radio v-if="currentLink==='noRegisterCase' && user.unitID != 'da00ef72-5b56-4f5b-88c2-4b038a7f41a0'" :options="stepOptionsFour" v-model="stepValue"></radio>
        <radio v-if="currentLink==='accept' && tasklist.fromnodeid === 'begin'" :options="stepOptions17" v-model="stepValue"></radio>
        <!--<radio-->
          <!--v-if="currentLink==='moveToLowerPersonnel'"-->
          <!--:options="stepOptionsTwo"-->
          <!--v-model="stepValue"-->
        <!--&gt;</radio>-->
      </group>
    </popup>
  </div>
</template>
<script>
import { listDetail ,findOrgUnitByRegionId} from '../api/api';

export default {
  data() {
    return {
      user:JSON.parse(localStorage.getItem('user')),
      unitList: [],
      stepleaderOne: ['处理反馈', '分派下级', '分派部门'],
      stepleader: ['审批通过', '审批不通过'],
      scrollItem: ['登记信息', '处办要求', '办理跟踪'],
      stepOptions: ['处理反馈', '分派部门', '分派人员'],
      stepOptionsFive: ['受理'],
      stepOptionsOne: ['立案处理'],
      stepOptionsSix: ['立案处理', '分派部门'],
      stepOptionsTwo: ['处理反馈', '分派下级', '分派部门', '分派人员'],
      stepOptionsFour: ['处理反馈', '分派部门', '分派人员'],
      stepOptionsThree: ['处理反馈'],
      stepOptionsServer: ['处理反馈', '分派人员'],
      stepOptions17: ['立案处理', '分派下级', '分派部门'],
      nowIndex: 0,
      caseguid: '',
      id: '',
       stepValue: '保存',
      // stepValue: '受理',
      show: false,
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      // 当前环节
      currentLink: '',
      // 经济表单
      economyFrom: {},
      tasklist: {},
      // 订单信息表单
      orderInformation: {},
      // 材料查询
      materialinfoList: [],
      // 流程跟踪
      opinionList: [],
      handledemand: {},
      // 系统来源
      systemsource: '',
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
      console.log(this.stepValue)
      if (this.stepValue === '意见填写') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.caseguid,
          currentLink: this.currentLink,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '立案处理') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
        };

        console.log(params);
        this.$router.push({
          path: '/main/Economy/economyOfCaseFiling',
          query: params,
        });
      } else if (this.stepValue === '处理反馈') {
        // console.log(this.systemsource);
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'economyform',
        };
        console.log(params)
        this.$router.push({
          path: '/main/DetailOf12345/processFeedback',
          query: params,
        });
      } else if (this.stepValue === '受理') {
        // console.log(this.systemsource);
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'economyform',
        };
        console.log(params)
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else if (this.stepValue === '分派下级') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,//当前节点
          systemsource: this.systemsource,
          nextNodeId: "moveToLowerOrg",
          fromType: 'economyform',
        };

        console.log(params);
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
          fromType: 'economyform',
        };

        console.log(params);
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
          fromType: 'economyform',
        };
        sessionStorage.setItem(
          'EconomyOrderInfo',
          JSON.stringify(this.orderInformation),
        );
        sessionStorage.setItem(
          'economyFormData',
          JSON.stringify(this.economyFrom),
        );
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      }else if (this.stepValue === '审批通过') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'economyform',
        };
        sessionStorage.setItem(
          'EconomyOrderInfo',
          JSON.stringify(this.orderInformation),
        );
        sessionStorage.setItem(
          'economyFormData',
          JSON.stringify(this.economyFrom),
        );
        this.$router.push({
          path: '/main/DetailOf12345/processFeedback',
          query: params,
        });
      }else if (this.stepValue === '审批不通过') {
        const params = {
          stepValue: this.stepValue,
          caseguid: this.$route.query.caseguid,
          id: this.$route.query.id,
          currentLink: this.currentLink,
          systemsource: this.systemsource,
          fromType: 'economyform',
        };
        sessionStorage.setItem(
          'EconomyOrderInfo',
          JSON.stringify(this.orderInformation),
        );
        sessionStorage.setItem(
          'economyFormData',
          JSON.stringify(this.economyFrom),
        );
        this.$router.push({
          path: '/main/DetailOf12345/processFeedback',
          query: params,
        });
      }
    },
    getEconomyDetail() {
      const params = {
        caseguid: this.caseguid,
        id: this.id,
        systemsource: '02',
      };
      listDetail(params).then((res) => {
        if (res.code === 0) {
          // console.log(res.data.tasklist.systemsource);
          // this.systemsource = res.$route.query;
          // console.log(this.$route.query);
          console.log(res.data.tasklist)
          this.orderInformation = res.data.customerinfo;
          this.economyFrom = res.data.economyform;
          this.materialinfoList = res.data.materialinfoList;
          this.opinionList = res.data.opinionList;
          this.tasklist = res.data.tasklist;
          console.log(this.tasklist.isaccept);
          console.log(this.tasklist.fromnodeid);
          console.log(this.$route.query.nextnodeid !== undefined);
          console.log(this.$route.query);
          if (this.$route.query.nextnodeid !== undefined) {
            // console.log(res.data.tasklist.nextnodeid);
            this.currentLink = this.$route.query.nextnodeid;
            console.log(this.currentLink);
            console.log(this.tasklist);


          } else {
            this.currentLink = res.data.tasklist.nextnodeid;
            console.log(this.currentLink);
          }
          // this.handledemand = res.data.handledemand;
        }
      });
    },
    findUnit() {
      console.log(this.user)
      // let regionId = this.user.regionID
      //获取下级单位列表
      findOrgUnitByRegionId({regionId: this.user.regionID}).then(data => {
      // findOrgUnitByRegionId({regionId: regionId}).then(data => {
        console.log(123)
        if (data.data) {
          this.unitList = data.data;
          console.log(this.unitList)
        }
      });
    },
  },
  mounted() {
    this.findUnit();
    console.log(this.$route.query);
    this.caseguid = this.$route.query.caseguid;
    this.id = this.$route.query.id;
    this.getEconomyDetail();
  },
};
</script>
<style lang="less" scoped>
@color: #666;
@fontStyleOfTitle: 26px;
@fontSize: 20px;
@backgroundColor: rgb(45, 161, 223);
.weui-cell {
  padding: 0 !important;
}

span {
  color: @color;
}
.mt30 {
  margin-top: 30px;
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
.weui-label {
  color: #000 !important;
}
textarea {
  font-family: "microsoft yahei" !important;
}
textarea:disabled {
  background: rgb(255, 255, 255);
}
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
