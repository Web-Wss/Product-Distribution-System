<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
// 获取地址栏参数
const router = useRouter();
const list = ref([
  "/admin",
  "/agoods",
  "/auser",
  "/adistributor",
  "/aorder",
  "/asystem",
]);
const active = ref(0);

// 修改active
onMounted(() => {
  list.value.forEach((item, index) => {
    if (item === router.currentRoute.value.path) {
      active.value = index;
    }
  });
});
</script>

<template>
  <div class="tabbar">
    <van-tabbar v-model="active" placeholder>
      <van-tabbar-item icon="chart-trending-o" to="/admin"
        >经营看板</van-tabbar-item
      >
      <van-tabbar-item icon="shop-o" to="agoods">商品中心</van-tabbar-item>
      <van-tabbar-item icon="user-circle-o" to="auser"
        >用户中心</van-tabbar-item
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
