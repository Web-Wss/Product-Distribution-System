<script setup lang="ts">
import { ref } from "vue";

const active = ref(0);
</script>

<template>
  <router-view></router-view>
  <!-- 标签栏 -->
  <van-tabbar v-model="active" route>
    <van-tabbar-item icon="home-o" to="/">选购</van-tabbar-item>
    <van-tabbar-item icon="shopping-cart-o" to="/cart">购物车</van-tabbar-item>
    <van-tabbar-item icon="friends-o" to="/my">我的</van-tabbar-item>
  </van-tabbar>
</template>

<style scoped></style>
