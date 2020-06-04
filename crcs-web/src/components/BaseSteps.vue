<!--步骤条-->
<template>
  <el-steps :space="100" :active="1">
    <el-step title="步骤 1" icon="edit"></el-step>
    <el-step title="步骤 2" icon="upload"></el-step>
    <el-step title="步骤 3" icon="picture"></el-step>
  </el-steps>
</template>

<script>
export default {
  name: "BaseSteps"
};
</script>

<style lang="scss" scoped>

</style>