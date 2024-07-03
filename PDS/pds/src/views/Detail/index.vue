<script setup>
import { getGoodsDetailByIdApi } from "@/apis/user/home";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const goodsId = route.query.goodsId;

// 返回
const onClickLeft = () => history.back();

// 底部
const onClickIcon = () => showToast("点击图标");
const onClickButton = () => showToast("点击按钮");

// 查询商品详情根据id
const goodsInfo = ref({});
const getGoodsInfo = async () => {
  await getGoodsDetailByIdApi(goodsId).then((res) => {
    goodsInfo.value = res.data.data;
  });
};
onMounted(() => {
  getGoodsInfo();
});
</script>

<template>
  <van-nav-bar
    fixed
    title="详情页"
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
  />
  <div class="detail">
    <!-- 图片 -->
    <div class="img">
      <img :src="goodsInfo.goodsImg" alt="" />
    </div>
    <!-- 详情 -->
    <div class="content">
      <div class="name">品名：{{ goodsInfo.goodsName }}</div>
      <div class="price">
        价格：{{ goodsInfo.goodsPrice }}元/{{ goodsInfo.goodsCompany }}
      </div>
      <div class="old-price">
        市场价：{{ goodsInfo.goodsOldPrice }}元/{{ goodsInfo.goodsCompany }}
      </div>
      <div class="inventory">库存：{{ goodsInfo.goodsTotalInventory }}</div>
    </div>
    <!-- 分割线 -->
    <!-- 视频 -->
    <van-divider
      :style="{ color: 'red', borderColor: 'red', padding: '0 16px' }"
    >
      下滑观看商品视频
    </van-divider>
    <div class="sp">
      <video
        width="100%"
        controls
        :src="goodsInfo.goodsVideo"
        :poster="goodsInfo.goodsImg"
      ></video>
    </div>
  </div>
  <!-- 底部 -->
  <van-action-bar>
    <van-action-bar-icon icon="home-o" text="选购" @click="onClickIcon" />
    <van-action-bar-icon icon="cart-o" text="购物车" @click="onClickIcon" />
    <van-action-bar-icon icon="manager-o" text="我的" @click="onClickIcon" />
    <van-action-bar-button
      type="danger"
      text="加入购物车"
      @click="onClickButton"
    />
  </van-action-bar>
</template>

<style scoped lang="scss">
.detail {
  margin-top: 46px;
  margin-bottom: 40px;
  .img {
    width: 100%;
    img {
      width: 100%;
    }
  }
  .content {
    padding: 10px;

    .name {
      font-weight: 600;
      font-size: 20px;
    }
    .price {
      font-size: 26px;
      color: red;
    }
    .old-price {
      text-decoration: line-through;
      font-size: 20px;
      color: #a2a2a2;
    }
    .inventory {
      color: #454545;
    }
  }

  // 视频
  .sp {
    width: 100%;
  }
}
</style>
