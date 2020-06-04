<!--级联选择器
注意：在整个options集合中value是唯一值，不能重复
使用方式：
1.引入组件之后
  components: {
    Cascader
  }
2. 编写 <cascader :options="data" v-model="checkList" @my-change='casecaderChange'/>
casecaderChange 是父组件 方法，接收参数现在已选择的对象
-->

<template>
  <div>
    <div class="my-cascader" @click="switchClick">
      <div class="nr">
        <el-tag
          type="primary"
          :closable="true"
          v-for="(item,index) in list"
          :key="item.value"
          @close="close(index)"
        >{{item.label}}</el-tag>
      </div>
      <i class="el-icon-arrow-down" v-show="!isShow"></i>
      <i class="el-icon-arrow-up" v-show="isShow"></i>
    </div>
    <transition name="fade">
      <div class="my-scrollbar" v-show="isShow">
        <ul>
          <li
            v-for="(item,index) in options"
            :key="item.value"
            @click="upChildren(item.children,index,0)"
            :class="getClass(item,index,0)"
          >
            <!-- &nbsp; -->
            {{item.label}}
          </li>
        </ul>
        <ul v-for="(children,indexP) in childrenList" :key="indexP+checkList.toString()">
          <li
            v-for="(item,index) in children"
            :key="item.value"
            @click="upChildren(item.children,index,indexP+1)"
            :class="getClass(item,index,indexP+1)"
          >
            <el-checkbox
              @change="onCheckbox($event,item)"
              :label="item.value"
              :checked="isChecked(item)"
            />
            &nbsp;
            {{item.label}}
          </li>
        </ul>
      </div>
    </transition>
    <div class="my-cover" v-show="isShow" @click="switchClick"></div>
  </div>
</template>

<script>
export default {
  props: {
    options: Array,
    checkList: Array,
    isMultiple :Boolean
  },
  model: {
    prop: "checkList"
  },
  data() {
    return {
      isShow: false,
      childrenList: [],
      activeList: [],
      activeIndex: null,
      list: []
    };
  },
  methods: {
    getClass(item, index, indexP) {
      let clazz = item.children ? "li-have" : "";
      if (this.activeList[indexP] === index) {
        clazz += " is-active";
      }
      return clazz;
    },
    close(index) {
      this.list.splice(index, 1);
      this.checkList.splice(index, 1);
      //触发父方法
      this.$emit('my-change',this.list)
    },
    switchClick() {
      this.isShow = !this.isShow;
    },
    onCheckbox(e, item) {
      //判断是否需要要重置
      // if (!this.isMultiple &&this.activeIndex != null && this.activeIndex !== this.activeList[0]) {
      //   this.checkList.splice(0);
      //   this.list = [];
      // }
      const checked = e.target.checked;
      const value = e.target.value;
      const key = item.value + item.label;
      if (checked) {
        //如果存在下级，则也勾选
        if (item.children && item.children.length > 0) {
          for (let element of item.children) {
            //不存在下级则存储起来最终值
            this.checkList.push(element.value);
            this.list.push(element);
          }
        } else {
          //不存在下级则存储起来最终值
          this.checkList.push(item.value);
          this.list.push(item);
        }
      } else {
        //如果存在下级，则也取消勾选
        if (item.children && item.children.length > 0) {
          for (let element of item.children) {
            let index = this.checkList.findIndex(
              value => element.value === value
            );
            if (index !== -1) {
              this.checkList.splice(index, 1);
              this.list.splice(index, 1);
            }
          }
        } else {
          //不存在下级则存储起来，则移除最终值
          let index = this.checkList.findIndex(value => item.value === value);
          if (index !== -1) {
            this.checkList.splice(index, 1);
            this.list.splice(index, 1);
          }
        }
      }
      this.activeIndex = this.activeList[0];
      //触发父方法
      this.$emit('my-change',this.list)
    },
    upChildren(children, index, indexP) {
      if (children && children.length > 0) {
        this.childrenList.splice(indexP, this.childrenList.length - indexP);
        this.activeList.splice(indexP, this.activeList.length - indexP);
        
        this.$set(this.childrenList, indexP, children);
        this.$set(this.activeList, indexP, index);
      }
    },
    isChecked(item) {
      let index = this.checkList.findIndex(value => value === item.value);
      if (index !== -1) {
        return true;
      } else {
        //判断是否子级选择了
        if (item.children && item.children.length > 0) {
          for (let element of item.children) {
            index = this.checkList.findIndex(value => value === element.value);
            if (index !== -1) {
              return true;
            }
          }
        }
      }
      return false;
    }
  },
  mounted() {
    console.log('67890')
  }
};
</script>

<style>
.my-cascader {
  min-height: 36px;
  z-index: 1009;
  cursor: pointer;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: #fff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #bfcbd9;
  box-sizing: border-box;
  color: #1f2d3d;
  font-size: inherit;
  line-height: 1;
  outline: 0;
  padding: 3px 10px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  display: flex;
  position: relative;
  align-items: center;
}
.my-cascader .nr {
  cursor: pointer;
  width: 100%;
  height: 100%;
}
.my-cascader i {
  color: #bfcbd9;
}
.my-cascader .el-tag {
  margin-right: 5px;
  margin-bottom: 5px;
}
.my-scrollbar {
  display: flex;
  position: absolute;
  z-index: 1020;
}
.my-scrollbar ul {
  min-width: 100px;
  height: 300px;
  border: 1px solid #d1dbe5;
  border-radius: 2px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  margin: 5px 0;
  list-style: none;
  padding: 6px 15px 6px 0;
  box-sizing: border-box;
  flex: 0 0 auto;
  overflow-y: scroll;
}
.my-scrollbar ul::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}

.my-scrollbar ul:not(:first-child) {
  margin-left: 3px;
}
.my-scrollbar li {
  font-size: 14px;
  padding: 8px 10px;
  position: relative;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: #48576a;
  height: 36px;
  line-height: 1.5;
  box-sizing: border-box;
  cursor: pointer;
}
.my-scrollbar .is-active {
  background-color: #20a0ff;
  color: #fff;
}
.my-scrollbar li:hover:not(.is-active) {
  background-color: #e4e8f1;
}
.my-scrollbar .li-have:after {
  font-family: element-icons;
  content: "\E606";
  font-size: 12px;
  transform: scale(0.8);
  color: #bfcbd9;
  position: absolute;
  right: 0px;
  margin-top: 1px;
}
.my-scrollbar .el-checkbox__label {
  display: none;
}
.my-cover {
  z-index: 1010;
  position: fixed;
  left: 0;
  top: 0;
  height: 100vh;
  width: 100vw;
}
</style>