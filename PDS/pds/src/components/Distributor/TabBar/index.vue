<script setup>
import { ref } from "vue";
const active = ref(0);
</script>

<template>
  <div class="tabbar">
    <van-tabbar v-model="active">
      <van-tabbar-item icon="chart-trending-o" to="/distributor"
        >数据看板</van-tabbar-item
      >
      <van-tabbar-item icon="label-o" to="/dorder">订单管理</van-tabbar-item>
      <van-tabbar-item icon="qr" to="dpromotion">营销推广</van-tabbar-item>
      <van-tabbar-item icon="manager-o" to="dmy">个人信息</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<style scoped></style>
