<script setup>
import { getMarketingPromotionApi } from "@/apis/distributor";
import { onMounted, ref } from "vue";

import { useDistributorStore } from "@/store/distributor";
const distributorStore = useDistributorStore();

const info = ref({});
// 推广码信息
const text = ref("https://www.antdv.com/");
const nickname = ref("");

const getInfo = async () => {
  const res = await getMarketingPromotionApi(
    distributorStore.distributorInfo.distributorId
  );
  info.value = res.data.data;
  nickname.value = distributorStore.distributorInfo.nickname;
  // 设置推广码信息
  text.value =
    res.data.data.website.websitePath +
    "?distributorId=" +
    distributorStore.distributorInfo.distributorId;
  // console.log(res);
  // console.log(text.value);
};

onMounted(() => {
  getInfo();
});
</script>

<template>
  <div class="promotion">
    <div class="header">
      <div class="item">
        分销订单总金额
        <div class="span">￥{{ info.orderTotalPrice }}</div>
      </div>
      <div class="item">
        分销总佣金
        <div class="span">￥{{ info.commission / 100 }}</div>
      </div>
      <div class="item">
        已提现佣金
        <div class="span">￥{{ info.alreadyWithdrawn }}</div>
      </div>
      <div class="item">
        佣金比例
        <div class="span">{{ info.commissionRate }}％</div>
      </div>
    </div>
    <!-- 二维码 -->
    <div class="img">
      <!-- <van-image
        width="200"
        height="200"
        src="https://img2.baidu.com/it/u=2030945992,3965956328&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
      /> -->
      <a-space direction="vertical" align="center">
        <a-qrcode :value="text" />
      </a-space>
      <div class="span" style="margin-top: 1rem">{{ nickname }} 的推广码</div>
    </div>

    <!--  -->
    <van-cell-group>
      <van-cell title="分销订单" is-link to="dorder" />
      <van-cell title="佣金明细" is-link to="commissiondetail" />
    </van-cell-group>
  </div>
</template>

<style scoped lang="scss">
.header {
  // width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  color: white;
  text-align: center;
  padding: 0.6rem;
  font-size: 1.2rem;
  .item {
    padding-top: 1rem;
    border-radius: 10px;
    .span {
      padding: 0.6rem;
    }
  }
  .item:nth-child(1) {
    margin-top: 1rem;
    width: 48%;
    background-color: #fd6d6d;
  }
  .item:nth-child(2) {
    margin-top: 1rem;
    width: 48%;
    background-color: #00ceff;
  }
  .item:nth-child(3) {
    margin-top: 1rem;
    width: 48%;
    background-color: #379ffe;
  }
  .item:nth-child(4) {
    margin-top: 1rem;
    width: 48%;
    background-color: #00cf6d;
  }
}

.img {
  width: 100%;
  text-align: center;
  color: #00cf6d;
  font-weight: 600;
  padding-bottom: 1rem;
}
</style>
