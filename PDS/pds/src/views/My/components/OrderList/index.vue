<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const props = defineProps({
  orderList: {
    type: Array,
    default: () => [],
  },
});
// 订单状态
const orderStatus = ["已下单", "分拣中", "配送中", "已完成", "已取消"];

// 去详情
const goDetail = (e) => {
  // console.log(e.ordersId);
  router.push({
    path: "/orderDetail",
    query: {
      ordersId: e.ordersId,
    },
  });
};
</script>

<template>
  <div class="order-list">
    <div class="item" v-for="(item, index) in orderList" :key="index">
      <div class="order-id">订单号:{{ item.ordersId }}</div>
      <div class="content">
        <div class="left">
          <div class="li createtime">
            <b>下单时间:</b><span>{{ item.orderCreateTime }}</span>
          </div>
          <div class="li total-price">
            <b>实际金额:</b><span>{{ item.orderTotalPrice }}元</span>
          </div>
          <div class="li discount-price">
            <b>优惠金额:</b><span>{{ item.orderDiscountPrice }}元</span>
          </div>
          <div class="li actual">
            <b>实付金额:</b><span>{{ item.orderActualPayment }}元</span>
          </div>
          <div class="li status">
            <b>订单状态:</b><span>{{ orderStatus[item.orderStatus - 1] }}</span>
          </div>
        </div>
        <div class="right">
          <!-- 查看详情按钮 -->
          <van-button type="primary" size="small" @click="goDetail(item)"
            >查看详情</van-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.order-list {
  padding-bottom: 1rem;
  width: 100%;
  background-color: #f7f7f7;
  .item {
    width: 90%;
    margin: 1rem auto;
    .content {
      font-size: 0.9rem;
      margin: 0 auto;
      background-color: white;
      display: flex;
      .left {
        padding: 0.2rem 1rem;
        width: 66%;
        .li {
          margin-top: 0.4rem;
        }
        span {
          color: #7b7b7b;
        }
      }
      .right {
        padding-top: 2rem;
      }
    }
  }
}
</style>
