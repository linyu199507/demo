<template>
  <div>
    <div class="vux-demo">
      <img class="logo" src="../assets/vux_logo.png">
      <h1> </h1>
    </div>
    <group title="cell demo">
      <cell title="VUX" value="cool" is-link link='/hello-world'></cell>
    </group>
     <group>
      <datetime v-model="minuteListValue" format="YYYY-MM-DD HH:mm"></datetime>
    </group>
  </div>
</template>

<script>
import { Group, Cell, Datetime } from 'vux';

export default {
  components: {
    Group,
    Cell,
    Datetime,
  },
  data() {
    return {
      minuteListValue: '2017-06-12 09:00',
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      msg: 'Hello World!',
    };
  },
};
</script>

<style>
.vux-demo {
  text-align: center;
}
.logo {
  width: 200px;
  height: 200px;
}
</style>
