<script setup>
import { getOrderListByDistributorIdApi } from "@/apis/distributor";
import { onMounted, ref } from "vue";
import { useDistributorStore } from "@/store/distributor";

import { useRouter } from "vue-router";
const router = useRouter();
const distributorStore = useDistributorStore();

const activeNames = ref(["1"]);
const active = ref(1);

// 订单列表
const orderList = ref([]);

const getOrderList = async () => {
  const res = await getOrderListByDistributorIdApi(
    distributorStore.distributorInfo.distributorId
  );
  console.log(res);
  orderList.value = res.data.data;
};

// 去订单详情
const goOrderDetail = (ordersId) => {
  console.log(ordersId);
  router.push({ path: "/orderDetail", query: { ordersId: ordersId } });
};

onMounted(() => {
  getOrderList();
});
</script>

<template>
  <div class="order">
    <div class="list">
      <div class="item" v-for="item in orderList" :key="item.ordersId">
        <van-collapse v-model="activeNames">
          <van-collapse-item
            :title="'订单编号:' + item.ordersId"
            :name="item.ordersId"
            :value="'实付金额:' + item.orderActualPayment + '元'"
          >
            <van-steps :active="item.orderStatus - 1">
              <van-step>已下单</van-step>
              <van-step>分拣中</van-step>
              <van-step>配送中</van-step>
              <van-step>已完成</van-step>
            </van-steps>
            <van-cell-group>
              <van-cell title="下单时间" :value="item.orderCreateTime" />
              <van-cell title="实际金额" :value="item.orderTotalPrice + '元'" />
              <van-cell
                title="优惠金额"
                :value="item.orderDiscountPrice + '元'"
              />
              <van-cell
                title="实付金额"
                :value="item.orderActualPayment + '元'"
              />
            </van-cell-group>
            <div class="button" style="text-align: center; margin-top: 1rem">
              <van-button
                type="primary"
                size="small"
                @click="goOrderDetail(item.ordersId)"
                >查看订单详情</van-button
              >
            </div>
          </van-collapse-item>
        </van-collapse>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.list {
  width: 100%;
  padding-bottom: 50px;
  // margin-bottom: 50px;
  .item {
    padding-top: 1rem;
    width: 100%;
    // background-color: aquamarine;
  }
}
</style>
