<script setup>
import { onMounted, ref } from "vue";

// 获取地址栏参数
const active = ref(0);
</script>

<template>
  <div class="tabbar">
    <van-tabbar v-model="active">
      <van-tabbar-item icon="chart-trending-o" to="/admin"
        >经营看板</van-tabbar-item
      >
      <van-tabbar-item icon="shop-o" to="agoods">商品中心</van-tabbar-item>
      <van-tabbar-item icon="user-circle-o" to="auser"
        >会员中心</van-tabbar-item
      >
      <van-tabbar-item icon="cluster-o" to="adistributor"
        >分销管理</van-tabbar-item
      >
      <van-tabbar-item icon="after-sale" to="aorder">交易中心</van-tabbar-item>
      <van-tabbar-item icon="setting-o" to="asystem">系统管理</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<style scoped></style>
