<template>
  <div class>
    <!-- 搜索 -->
    <!-- <search
      @result-click="resultClick"
      @on-change="getResult"
      :results="results"
      v-model="search"
      position="absolute"
      auto-scroll-to-top
      @on-focus="onFocus"
      @on-cancel="onCancel"
      @on-submit="onSubmit"
      ref="search"
    ></search> -->
    <swiper class="" :list="swiperList" v-model="swiperIndex" @on-index-change="changeIndex"></swiper>
    <!--  -->
    <div class="detailLists">
      <ul>
        <li @click="goTodetailList">
          <div class="listTitle">待办列表</div>
          <div class="listImg">
            <img src="../assets/imgs/com.png" alt />
          </div>
        </li>
        <li>
          <div class="listTitle">已办列表</div>
          <div class="listImg">
            <img src="../assets/imgs/nodeal.png" alt />
          </div>
        </li>
        <li @click="goToRegistration">
          <div class="listTitle">维权登记</div>
          <div class="listImg">
            <img src="../assets/imgs/yellow.png" alt />
          </div>
        </li>
        <li @click="$router.push('/main/rights/list')">
          <div class="listTitle">维权列表</div>
          <div class="listImg">
            <img src="../assets/imgs/yellow.png" alt />
          </div>
        </li>
        <!-- /main/ComplaintHotSpot -->
        <li @click="$router.push({path:'/main/ComparisonOfSimilarCases'})">
        <!-- <li @click="$router.push({path:'/main/ComplaintHotSpot'})"> -->
          <div class="listTitle">工单预警</div>
          <div class="listImg">
            <img src="../assets/imgs/warning.png" alt />
          </div>
        </li>
        <li @click="$router.push({path:'/main/comprehensiveSearch'})">
        <!-- <li @click="$router.push({path:'/main/ComplaintHotSpot'})"> -->
          <div class="listTitle">综合查询</div>
          <div class="listImg">
            <img src="../assets/imgs/icon-search.png" alt />
          </div>
        </li>
      </ul>
    </div>
    <!-- 最近动态 -->
    <div class="news">
      <div class="header">
        <div class="headerName">近期工单动态</div>
        <div class="title">查看更多></div>
      </div>
      <div class="orderList">
        <panel :list="list" :type="type" @on-img-error="onImgError"></panel>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      type: '5',
      swiperList: [
        {
          url: 'javascript:',
          img:
            'http://5b0988e595225.cdn.sohucs.com/images/20171128/14f34de92e9e41d9877903c323f7f1d5.jpeg',
          title: '顺峰山',
        },
        {
          url: 'javascript:',
          img:
            'http://gss0.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=46a52040271f95caa6f596b6f9277e3e/962bd40735fae6cdaa268e5f0db30f2442a70f79.jpg',
          title: '清晖园',
        },
        {
          url: 'javascript:',
          img:
            'http://www.gzmzpx.com/public/uploads/gongc142/20160530143422_853.png', // 404
          title: '李小龙乐园',
          fallbackImg:
            'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw50iwj20ff0aaaci.jpg',
        },
      ],
      list: [
        {
          src:
            'http://img3.imgtn.bdimg.com/it/u=3657692909,1457109578&fm=26&gp=0.jpg',
          fallbackSrc: 'http://placeholder.qiniudn.com/60x60/3cc51f/ffffff',
          title: '侵权案件',
          desc:
            '由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。',
          url: '/component/cell',
        },
        {
          src:
            'http://img3.imgtn.bdimg.com/it/u=3657692909,1457109578&fm=26&gp=0.jpg',
          title: '垃圾回收',
          desc:
            '由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。',
          url: {
            path: '/component/radio',
            replace: false,
          },
        },
      ],
      swiperIndex: 0,
      results: [],
      search: '',
    };
  },
  methods: {
    goToRegistration() {
      // 维权服务站
      this.$router.push({ path: '/main/RegistrationForm' });
    },
    goTodetailList() {
      this.$router.push({ path: '/main/Pending' });
    },
    changeIndex(index) {
      this.demo01_index = index;
    },
    onImgError(item, $event) {
      console.log(item, $event);
    },

    resultClick(item) {
      window.alert(`you click the result item: ${JSON.stringify(item)}`);
    },
    getResult(val) {
      console.log('on-change', val);
      this.results = val ? getResult(this.value) : [];
    },
    onFocus() {
      console.log('on focus');
    },
    onCancel() {
      console.log('on cancel');
    },
    onSubmit() {
      this.$refs.search.setBlur();
      this.$vux.toast.show({
        type: 'text',
        position: 'top',
        text: 'on submit',
      });
    },
  },
  mounted() {},
};
</script>
<style lang="less" scoped>
.mt60 {
  margin-top: 0;
}
.detailLists {
  width: 94%;
  margin: 20px auto;
  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    li {
      width: 48%;
      display: flex;
      justify-content: space-around;
      align-items: center;
      height: 200px;
      background: #e4e7ed;
      border-radius: 10px;
      box-shadow: #eee 5px 5px 0px;
      font-size: 40px;
      margin-bottom: 20px;
      &:nth-child(2n + 1) {
        margin-right: 2%;
      }
      .listTitle {
        color: #666;
        &:hover {
          text-decoration: underline;
        }
      }
      .listImg {
        width: 100px;
        height: 100px;
        img {
          width: 100%;
        }
      }
    }
  }
}
.header {
  background: #fff;
  padding: 20px 0;
  display: flex;
  justify-content: space-between;
  .headerName {
    padding-left: 3%;
    font-size: 30px;
    font-weight: 600;
    color: #000;
  }
  .title {
    padding-right: 3%;
    color: #666;
  }
}
.news {
  margin-bottom: 120px;
}
</style>
<style lang="less">
.weui-search-bar__cancel-btn {
  color: rgb(45, 161, 223) !important;
}
.box1 {
  height: 100px;
  position: relative;
  width: 1490px;
}
.box1-item {
  width: 50%;
  height: 100px;
  background-color: #ccc;
  display: inline-block;
  margin-left: 15px;
  float: left;
  text-align: center;
  line-height: 100px;
}
.box1-item:first-child {
  margin-left: 0;
}
.weui-media-box__title {
  font-size: 16PX!important;
}
</style>
