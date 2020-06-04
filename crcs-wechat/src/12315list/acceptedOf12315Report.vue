<template>
  <div class="mt30px">
    <!-- 业务基本信息 -->
    <div class="registerInfo">
      <div class="provider">
        <div class="title">
          <span></span> 系统信息
        </div>
        <div class="content">
          <ul>
            <li>
              <div class="Protitle">登记编号:</div>
              <div class="Procontent">1440606002019111458846178</div>
            </li>
            <li>
              <div class="Protitle">记录类型:</div>
              <div class="Procontent">举报</div>
            </li>
            <li>
              <div class="Protitle">登记日期:</div>
              <div class="Procontent">2019-11-14 00:00:00</div>
            </li>
            <li>
              <div class="Protitle">接收方式</div>
              <div class="Procontent">互联网平台</div>
            </li>
            <li>
              <div class="Protitle">来电号码:</div>
              <div class="Procontent">13650738837</div>
            </li>
          </ul>
        </div>
      </div>
    </div>

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
        <!-- 系统信息 -->
        <li v-show="nowIndex == 0">
          <div class="register">
            <div class="registerInfo">
              <div class="provider">
                <div class="title">
                  <span></span> 提供方信息
                </div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <div class="content">
                      <ul>
                        <!-- 投诉类 -->
                        <li>
                          <div class="Protitle">标题:</div>
                          <div class="Procontent">1440606002019111458846178</div>
                        </li>
                        <!-- 举报类 -->
                        <li>
                          <div class="Protitle">投诉人姓名/单位:</div>
                          <div class="Procontent">1440606002019111458846178</div>
                        </li>
                        <li>
                          <div class="Protitle">性别:</div>
                          <div class="Procontent">男</div>
                        </li>
                        <li>
                          <div class="Protitle">举报人联系电话:</div>
                          <div class="Procontent">13650738837</div>
                        </li>
                        <li>
                          <div class="Protitle">证件类型:</div>
                          <div class="Procontent">中华人民共和国居民身份证</div>
                        </li>
                        <li>
                          <div class="Protitle">证件号码:</div>
                          <div class="Procontent">1440606002019111458846178</div>
                        </li>
                        <li>
                          <div class="Protitle">提供方类型:</div>
                          <div class="Procontent">企业</div>
                        </li>
                        <li>
                          <div class="Protitle">年龄段:</div>
                          <div class="Procontent">中青年(19-65岁)</div>
                        </li>
                        <li>
                          <div class="Protitle">电子邮箱:</div>
                          <div class="Procontent">hui_ling_zheng@163.com</div>
                        </li>
                        <li>
                          <div class="Protitle">国籍:</div>
                          <div class="Procontent">中国</div>
                        </li>
                        <li>
                          <div class="Protitle">民族:</div>
                          <div class="Procontent">汉族</div>
                        </li>
                        <li>
                          <div class="Protitle">身份:</div>
                          <div class="Procontent">城镇</div>
                        </li>
                        <li>
                          <div class="Protitle">邮编:</div>
                          <div class="Procontent">510510</div>
                        </li>
                        <li>
                          <div class="Protitle">其他联系方式:</div>
                          <div class="Procontent">联系电话</div>
                        </li>
                        <li>
                          <div class="Protitle">投诉人地址:</div>
                          <div class="Procontent">广州市白云区黄石大道</div>
                        </li>
                      </ul>
                    </div>
                  </group>
                </div>
              </div>
            </div>

            <div class="registerInfo">
              <div class="provider">
                <div class="title">
                  <span></span> 涉及主体信息
                </div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <div class="content">
                      <ul>
                        <li>
                          <div class="Protitle">被举报对象名称:</div>
                          <div class="Procontent">佛山市正飞具有限公司</div>
                        </li>
                        <li>
                          <div class="Protitle">联系人:</div>
                          <div class="Procontent">vling</div>
                        </li>
                        <li>
                          <div class="Protitle">联系电话:</div>
                          <div class="Procontent">123</div>
                        </li>
                        <li>
                          <div class="Protitle">行业分类:</div>
                          <div class="Procontent">13650738837</div>
                        </li>
                        <li>
                          <div class="Protitle">社会信用代码:</div>
                          <div class="Procontent">1440606002019111458846178</div>
                        </li>
                        <li>
                          <div class="Protitle">市场主体类型:</div>
                          <div class="Procontent">自然人</div>
                        </li>
                        <li>
                          <div class="Protitle">被投诉对象地址:</div>
                          <div class="Procontent">佛山市顺德区乐从镇水藤村入村大道边北侧环镇西路东侧商铺6座之三、二楼204室（住所申报）</div>
                        </li>
                      </ul>
                    </div>
                  </group>
                </div>
              </div>
            </div>

            <!-- 投诉的业务信息 -->
            <div class="registerInfo">
              <div class="provider">
                <div class="title">
                  <span></span> 业务信息
                </div>
                <div class="content">
                  <group label-width="4.5em" label-margin-right="2em" label-align="right">
                    <div class="content">
                      <ul>
                        <li>
                          <div class="Protitle">客体类别:</div>
                          <div class="Procontent">豆类</div>
                        </li>
                        <li>
                          <div class="Protitle">商品/服务名称:</div>
                          <div class="Procontent">海马床垫 尊享版 1.8*2米 厚25厘米</div>
                        </li>
                        <li>
                          <div class="Protitle">商品品牌:</div>
                          <div class="Procontent">海马普伦</div>
                        </li>
                        <li>
                          <div class="Protitle">消费金额:</div>
                          <div class="Procontent">123元</div>
                        </li>
                        <li>
                          <div class="Protitle">订单号:</div>
                          <div class="Procontent">106491213205</div>
                        </li>
                        <li>
                          <div class="Protitle">销售方式:</div>
                          <div class="Procontent">网购</div>
                        </li>
                        <li>
                          <div class="Protitle">产品名称:</div>
                          <div class="Procontent">106491213205</div>
                        </li>
                        <li>
                          <div class="Protitle">批准文号:</div>
                          <div class="Procontent">2019-11-09 00:00:00</div>
                        </li>
                        <li>
                          <div class="Protitle">投诉问题类别:</div>
                          <div class="Procontent">含异物</div>
                        </li>
                        <li>
                          <div class="Protitle">产品规格:</div>
                          <div class="Procontent">含异物</div>
                        </li>
                        <li>
                          <div class="Protitle">生产厂家:</div>
                          <div class="Procontent">106491213205</div>
                        </li>
                        <li>
                          <div class="Protitle">产品批号:</div>
                          <div class="Procontent">106491213205</div>
                        </li>
                        <li>
                          <div class="Protitle">产品有效期:</div>
                          <div class="Procontent">2019-11-09 00:00:00</div>
                        </li>
                        <li>
                          <div class="Protitle">诉求内容:</div>
                          <div class="Procontent">退货</div>
                        </li>
                        <li>
                          <div class="Protitle">关键字:</div>
                          <div class="Procontent">2019-11-09 00:00:00</div>
                        </li>
                        <li>
                          <div class="Protitle">销售企业:</div>
                          <div class="Procontent">佛山市顺德区乐从镇水藤村入村大道边北侧环镇西路东侧商铺6座之三、二楼204室（住所申报）</div>
                        </li>

                        <li>
                          <div class="Protitle">举报内容:</div>
                          <div
                            class="Procontent"
                          >本人于2019.11.12 在佛山市海鸣纺织服装有限公司 所经营的阿里巴巴网店ID： 海鸣布艺 发现了 高端定制 S型百叶帘 铝合金轨道 省力拉珠款式 家居办公百叶窗帘 订单号是： 577987214477958600 产品描述 虚假宣传此产品环保防火。 这个店铺的违规经营已经违反了《广告法》第九条，第十一条、第二十八条以及《反不正当竞争法》以虚假或者引人误解的内容欺骗、误导消费者的，构成虚假广告。</div>
                        </li>
                        <li>
                          <div class="Protitle">附件信息:</div>
                          <div class="Procontent">
                            <ul>
                              <li>43543535</li>
                              <li>5435353</li>
                              <li>特退热贴</li>
                              <li>尔特瑞特也容易</li>
                            </ul>
                          </div>
                        </li>
                      </ul>
                    </div>
                  </group>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!-- 流程跟踪 -->
        <li v-show="nowIndex == 1">
          <flow orientation="vertical" :style="`height:${6.3*flowList.length}rem`" class="mb100">
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

    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">立案处理</x-button>
      <x-button type="default" mini @click.native="showChoose">取消</x-button>
    </div>

    <!-- 弹窗 -->
    <!-- <popup v-model="show">
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
    </popup> -->
  </div>
</template>
<script>
export default {
  data() {
    return {
      scrollItem: ['登记信息', '办理跟踪'],
      nowIndex: 0,
    //   show: false,
      stepValue: '',
    //   stepOptions: ['处理反馈', '分派人员'],
      flowList: [1, 2, 3, 4, 5, 6, 7, 8, 9],
    };
  },
  methods: {
    showChoose() {
      this.$router.push({ path: '/main/Economy/economyOfCaseFiling' });
    //   this.show = true;
    },
    toggleTabs(index) {
      this.nowIndex = index;
    },
    comfirm() {
      if (this.stepValue === '处理反馈') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/feedbackOf12315complain',
          query: params,
        });
      } else if (this.stepValue === '分派人员') {
        const params = {
          stepValue: this.stepValue,
        };
        this.$router.push({
          path: '/main/DetailOf12345/accept12345form',
          query: params,
        });
      } else {
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import url("../assets/font/common.less");
</style>
<style lang="less">
@import url("../assets/font/commonOfCommon.less");
</style>