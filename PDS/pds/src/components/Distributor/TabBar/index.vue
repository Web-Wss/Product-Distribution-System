<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

// 获取地址栏参数
const router = useRouter();
const list = ref(["/distributor", "/dorder", "/dpromotion", "/dmy"]);
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
