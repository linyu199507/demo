<template>
  <!-- 待分流的投诉类表单 -->
  <div class="mt30px">
    <!-- 业务基本信息 -->
    <div class="registerInfo">
      <div class="provider">
        <div class="title">业务基本信息</div>
        <div class="content">
          <ul>
            <li>
              <div class="Protitle">业务类别:</div>
              <div class="Procontent">投诉</div>
            </li>
            <li>
              <div class="Protitle">登记时间:</div>
              <div class="Procontent">2019-11-14 08:31:00</div>
            </li>
            <li>
              <div class="Protitle">客体类别:</div>
              <div class="Procontent">杜浩五</div>
            </li>
            <li>
              <div class="Protitle">姓名/单位:</div>
              <div class="Procontent">董希秀</div>
            </li>
            <li>
              <div class="Protitle">投诉人联系电话:</div>
              <div class="Procontent">13650738837</div>
            </li>
            <li>
              <div class="Protitle">投诉问题类别:</div>
              <div class="Procontent"></div>
            </li>
            <li>
              <div class="Protitle">被投诉对象名称:</div>
              <div class="Procontent">美的集团股份有限公司</div>
            </li>
            <li>
              <div class="Protitle">投诉对象地址:</div>
              <div class="Procontent">外资投资企业-外商投资股份有限公司</div>
            </li>
            <li>
              <div class="Protitle">投诉地址:</div>
              <div class="Procontent">佛山市顺德区北滘镇美的总部大楼B区26-28楼</div>
            </li>
            <li>
              <div class="Protitle">事发地:</div>
              <div class="Procontent">佛山市顺德区北滘镇美的总部大楼B区26-28楼</div>
            </li>
            <li>
              <div class="Protitle">附件清单:</div>
              <div class="Procontent"></div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 投诉人分流处理 -->
    <div class="dispatchedWhole">
      <div class="header">
        <span></span>
        <div class="title">投诉人分流处理</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <!-- 投诉 -->
        <datetime
          v-model="initialInspectionDate"
          v-if=""
          @on-change="change"
          title="初查反馈期限"
          placeholder="请选择反馈期限"
          value-align="right"
          @on-confirm="onConfirm"
        ></datetime>
        <span v-if="" class="tips">还剩 -6 个工作日(有管辖权的市场监管部门自收到消费者投诉之日起七个工作日内,告知投诉人是否受理)</span>
        <!-- 举报 -->
        <datetime
          v-model="initialInspectionDate"
          v-if=""
          @on-change="change"
          title="排查反馈期限"
          placeholder="请选择反馈期限"
          value-align="right"
          @on-confirm="onConfirm"
        ></datetime>
        <span v-if="" class="tips"> 还剩 -1 个工作日（有管辖权的市场监管部门收到举报之日起十五个工作日内予以核查，并决定是否受理）</span>


        <datetime
          v-model="deadlineForProcessing"
          @on-change="change"
          title="办理期限"
          placeholder="请选择办理期限"
          value-align="right"
          @on-confirm="onConfirm"
        ></datetime>
        <span class="tips">有管辖权的市场监管部门应当在受理消费者投诉之日起六十日内终结调解</span>
        <popup-picker
          title="分流机关"
          placeholder="请选择"
          :data="distributaryOrganOptions"
          v-model="distributaryOrgan"
          @on-change="changeOrgan"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="分流方向"
          placeholder="请选择"
          @on-change="changeDistributary"
          :data="diversionDirectionOptions"
          v-model="diversionDirection"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="接收部门"
          placeholder="请选择"
          :data="acceptDeptOptions"
          v-model="acceptDept"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="办结审批部门类型"
          placeholder="请选择"
          :data="deptTypeOptions"
          v-model= "deptType"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="附言"
          v-model="postscript"
          :show-counter="false"
          :rows="4"
          placeholder="请输入"
        ></x-textarea>

        <!-- 选择送审才出现的 -->
      </group>
    </div>

    <div class="dispatchedWhole" v-if="isOperation">
      <div class="header">
        <span></span>
        <div class="title">操作</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <radio
          :selected-label-style="{color: 'rgb(45, 161, 223)'}"
          :options="operationOptions"
          v-model="operation"
          @on-change="change"
        ></radio>
      </group>
    </div>

    <!-- 选择送审时出现 -->
    <div class="dispatchedWhole" v-if="trial">
      <div class="header">
        <span></span>
        <div class="title">送审相关</div>
      </div>
      <group label-width="4.5em" label-margin-right="2em" label-align="right">
        <popup-picker
          title="审批人"
          placeholder="必选"
          :data="approverOptions"
          v-model="approver"
          value-text-align="left"
        ></popup-picker>
        <popup-picker
          title="审批部门"
          placeholder="请选择"
          :data="approvalDepartmentOptions"
          v-model="approvalDepartment"
          value-text-align="left"
        ></popup-picker>
        <x-textarea
          title="送审附言"
          v-model="postscript"
          :show-counter="false"
          :rows="4"
          placeholder="请输入(必填)"
        ></x-textarea>
      </group>
    </div>

    <div class="detail" style="margin-bottom:60px">
      <x-button type="primary" mini @click.native="showChoose">处理</x-button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      operationOptions: ['分流', '送审'],
      distributaryOrganOptions: [
        ['本地市场监督管理机关', '监督ODR企业开展和解'],
      ],
      diversionDirectionOptions: [['向下分流', '自办', '横向分流']],
      feedbackPersonOptions: [['顺德区业务处理员']],
      acceptDeptOptions: [['市场监管组-技术部门']],
      deptTypeOptions: [['登记部门审批', '处理部门审批']],
      approverOptions: [['顺德区业务处理员']],
      approvalDepartmentOptions: [['业务部门']],
      operation: '分流',
      initialInspectionDate: '',
      deadlineForProcessing: '',
      postscript: '',
      distributaryOrgan: [],
      approvalDepartment: [],
      acceptDept: [],
      deptType: [],
      approver: [],
      diversionDirection: [],
      //   是否送审标识
      trial: false,
      // 是否有操作标识
      isOperation: true,
    };
  },
  methods: {
    change(value) {
      if (value === '送审') {
        this.trial = true;
      } else {
        this.trial = false;
      }
    },
    onConfirm() {},
    changeOrgan() {
      if (this.distributaryOrgan[0] === '本地市场监督管理机关') {
        this.diversionDirection[0] = '自办';
        console.log(this.diversionDirection);
      }
    },
    changeDistributary() {
      if (this.diversionDirection[0] === '自办') {
        this.isOperation = false;
      } else {
        this.isOperation = true;
      }
    },
  },
};
</script>
<style lang="less" scoped>
@fontStyleOfTitle: 26px;
@import url("../assets/font/common.less");
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
.tips {
  font-size: 20px;
  color: red;
}
</style>
<style lang="less">
@import url("../assets/font/commonOfCommon.less");
</style>