<script setup>
import TabBar from "@/components/Admin/AdminTabBar/index.vue";
</script>

<template>
  <div>
    <router-view></router-view>
    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<style scoped></style>
