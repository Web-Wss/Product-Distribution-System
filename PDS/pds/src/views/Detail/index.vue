<script setup>
import { getGoodsDetailByIdApi, addGoodsToCartApi } from "@/apis/user/home";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { useUserStore } from "@/store/user";

const route = useRoute();
const userStore = useUserStore();

const goodsId = route.query.goodsId;

// 返回
const onClickLeft = () => history.back();

// 底部
const onClickIcon = () => showToast("点击图标");

// 加购面板
const show = ref(false);
// 加购
const onClickButton = () => {
  cartInfo.value = {
    goodsId: goodsInfo.value.goodsId,
    goodsImg: goodsInfo.value.goodsImg,
    goodsName: goodsInfo.value.goodsName,
    goodsPrice: goodsInfo.value.goodsPrice,
    remainingInventory: goodsInfo.value.remainingInventory,
    goodsNumber: 1,
  };
  show.value = true;
};
const cartInfo = ref({
  goodsId: "",
  goodsImg: "",
  goodsName: "",
  goodsPrice: "",
  remainingInventory: "",
  goodsNumber: "",
});
// 面板加购按钮
const addCartToCart = async () => {
  // 加工加购数据
  const AddGoodsToCartData = {
    userId: userStore.userInfo.userId,
    goodsId: cartInfo.value.goodsId,
    goodsNumber: cartInfo.value.goodsNumber,
    remainingInventory: cartInfo.value.remainingInventory,
  };
  const res = await addGoodsToCartApi(AddGoodsToCartData);
  if (res.data.code === 200) {
    // console.log("加购成功");
    showNotify({ type: "success", message: "加购成功" });
    show.value = false;
    cartInfo.value = {
      goodsId: "",
      goodsImg: "",
      goodsName: "",
      goodsPrice: "",
      remainingInventory: "",
      goodsNumber: "",
    };
  }
};

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
      <div class="inventory">库存：{{ goodsInfo.remainingInventory }}</div>
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

  <!-- 加购面板 -->
  <van-action-sheet v-model:show="show" title="加购">
    <div class="content1">
      <div class="img">
        <img :src="cartInfo.goodsImg" alt="" />
      </div>
      <div class="center">
        <div class="name">{{ cartInfo.goodsName }}</div>
        <div class="inventory">剩余库存：{{ cartInfo.remainingInventory }}</div>
        <div class="price">单价：{{ cartInfo.goodsPrice }}元</div>
      </div>
      <div class="right">
        <div class="stepper">
          <van-stepper
            v-model="cartInfo.goodsNumber"
            theme="round"
            button-size="22"
            disable-input
            :min="1"
            :max="cartInfo.remainingInventory"
          />
        </div>
      </div>
    </div>
    <div class="button">
      <van-button class="btn" type="primary" @click="addCartToCart"
        >加入购物车</van-button
      >
    </div>
  </van-action-sheet>
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

//加购
// 加购面板
.content1 {
  padding: 16px 16px 16px;
  display: flex;
  justify-content: space-around;
  .img {
    width: 30%;
    img {
      width: 100%;
    }
  }
  .center {
    width: 40%;
    padding: 0.6rem;
    .name {
      font-size: 1.2rem;
      font-weight: 600;
    }
    .inventory {
      color: #898989;
    }
    .price {
      color: red;
    }
  }
  .right {
    padding: 2rem 0;
  }
}
.button {
  width: 100%;
  text-align: center;
  padding-bottom: 1rem;
}
</style>
