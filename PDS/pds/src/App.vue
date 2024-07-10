<script setup lang="ts">
import { useUserStore } from "@/store/user";
const userStore = useUserStore();
// userStore.setDistributorId(2025001);
// 获取地址栏中问号后的参数和值
const query = new URLSearchParams(location.search);

const distributorId = query.get("distributorId");
if (distributorId) {
  userStore.setDistributorId(Number(distributorId));
} else {
  userStore.setDistributorId(0);
}
</script>

<template>
  <router-view></router-view>
</template>

<style scoped></style>
