<script setup>
import { ref } from "vue";
const activeNames = ref(["1"]);
const active = ref(1);
</script>

<template>
  <div class="order">
    <div class="list">
      <div class="item" v-for="item in 6" :key="item">
        <van-collapse v-model="activeNames">
          <van-collapse-item
            title="订单编号:2025001"
            :name="item"
            value="实付金额:188元"
          >
            <van-steps :active="active">
              <van-step>已下单</van-step>
              <van-step>分拣中</van-step>
              <van-step>配送中</van-step>
              <van-step>已完成</van-step>
              <van-step>已取消</van-step>
            </van-steps>
            <van-cell-group>
              <van-cell title="下单时间" value="2024-07-11 13:43:46" />
              <van-cell title="实际金额" value="188元" />
              <van-cell title="优惠金额" value="0元" />
              <van-cell title="实付金额" value="188元" />
            </van-cell-group>
            <div class="button" style="text-align: center; margin-top: 1rem">
              <van-button type="primary" size="small">查看订单详情</van-button>
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
  .item {
    margin-top: 1rem;
    width: 100%;
    background-color: aquamarine;
  }
}
</style>
