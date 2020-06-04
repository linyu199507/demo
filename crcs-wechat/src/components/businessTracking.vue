<template>
  <div>
    <x-table style="background-color:#fff;">
      <thead>
        <tr>
          <th>序号</th>
          <th>步骤</th>
          <th width="25%">
              <p>处理部门/</p>
              <p>处理人员</p>
          </th>
          <th>操作</th>
          <th>处理时间</th>
          <th width="20%">处理意见</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(each, index) in data" :key="index">
          <td><p>{{index+1}}</p></td>
          <td><p>{{each.activitytype?each.activitytype:each.nodename}}</p></td>
            <td>
              <p>{{each.handledept}}/</p>
              <p>{{each.handler}}</p>
            </td>
          <td><p>{{each.nodename}}</p></td>
          <td><p>{{each.handletime?moment(each.handletime).format('YYYY-MM-DD HH:mm:ss'):''}}</p></td>
          <td><p>{{each.handleopinion}}</p></td>
        </tr>
      </tbody>
    </x-table>
  </div>
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
    rowClick({ id }, event, column) {
      if (this.expands.indexOf(id) === -1) {
        this.expands.push(id);
      } else {
        this.expands.splice(this.expands.indexOf(id), 1);
      }
    }
  }
};
</script>

<style lang="less" scoped>
th p,td p{line-height: 15PX;}
</style>