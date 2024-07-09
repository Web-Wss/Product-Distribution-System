<script setup>
import { onMounted, ref, watchEffect } from "vue";
import OrderList from "../OrderList/index.vue";
import { getOrderListApi } from "@/apis/user/home";
import { useUserStore } from "@/store/user";
const userStore = useUserStore();

// 返回按钮
const onClickLeft = () => history.back();
// 标签页
const active = ref(0);
// 标签页列表
const tabList = ref([
  { name: "全部订单", status: 0 },
  { name: "已下单", status: 1 },
  { name: "分拣中", status: 2 },
  { name: "配送中", status: 3 },
  { name: "已完成", status: 4 },
  { name: "已取消", status: 5 },
]);

// 订单列表
const orderList = ref([]);
const getOrderList = async (status) => {
  const userId = userStore.userInfo.userId;
  // 获取订单列表
  const res = await getOrderListApi(userId, status);
  console.log(res);
  orderList.value = res.data.data;
};
const onClickTab = (e) => {
  console.log(e.name);
};

// 监听变化发送获取订单请求
watchEffect(() => {
  getOrderList(active.value);
});
// onMounted(() => {
//   getOrderList(active.value);
// });
</script>

<template>
  <div class="order">
    <!-- 导航栏 -->
    <van-nav-bar
      fixed
      title="我的订单"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
    <div class="header"></div>
    <!-- 标签 -->
    <van-tabs
      v-model:active="active"
      sticky
      offset-top="46px"
      @click-tab="onClickTab"
    >
      <van-tab
        :title="item.name"
        :id="item.status"
        v-for="item in tabList"
        :key="item.status"
      >
        <OrderList :orderList="orderList" />
      </van-tab>
    </van-tabs>
  </div>
</template>

<style scoped>
.header {
  margin-top: 46px;
}
</style>
