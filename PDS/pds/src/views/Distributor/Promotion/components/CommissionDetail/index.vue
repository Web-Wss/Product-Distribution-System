<script setup>
import { onMounted, ref } from "vue";
import { getCommissionDetailApi } from "@/apis/distributor";
import { useDistributorStore } from "@/store/distributor";
const distributorStore = useDistributorStore();

const list = ref([]);
const getList = async () => {
  const res = await getCommissionDetailApi(
    distributorStore.distributorInfo.distributorId
  );
  list.value = res.data.data;
};

onMounted(() => {
  getList();
});

const back = () => {
  history.back();
};
</script>

<template>
  <div class="commissiondetail">
    <van-nav-bar
      fixed
      title="佣金明细"
      left-text="返回"
      @click-left="back"
      placeholder
    />
    <!-- 数据 -->
    <div class="list">
      <van-cell-group>
        <van-cell
          :title="'订单编号:' + item.ordersId"
          :value="'+' + item.orderTotalPrice / 10 + '元'"
          v-for="item in list"
          :key="item.ordersId"
        />
      </van-cell-group>
    </div>
  </div>
</template>

<style scoped lang="scss">
.list {
  padding-bottom: 50px;
}
</style>
