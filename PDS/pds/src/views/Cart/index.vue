<script setup>
import {
  getCarListByUserIdApi,
  getCartTotalPriceByUserIdApi,
  updateCartInfoApi,
  deleteCartInfoByCartIdApi,
} from "@/apis/user/home";
import TabBar from "@/components/TabBar/index.vue";
import { onMounted, ref } from "vue";
import { useUserStore } from "@/store/user";
import { useRouter } from "vue-router";

// lodash
import { debounce } from "lodash";

const userStore = useUserStore();
const router = useRouter();
const onSubmit = () => {
  router.push("/publishOrder");
};

// 删除按钮文字
const deleteText = ref("");
// 提交订单按钮是否可以点击
const submitBtn = ref(true);

// 获取购物车列表根据用户id
const cartList = ref([]);
const getCarList = async () => {
  const res = await getCarListByUserIdApi(userStore.userInfo.userId);
  // console.log(res);
  cartList.value = res.data.data;
  // 遍历购物车列表，判断是否有勾选
  deleteText.value = "";
  submitBtn.value = true;

  cartList.value.forEach((item) => {
    if (item.goodsSelectedStatus == 1) {
      deleteText.value = "删除已勾选的商品";
      submitBtn.value = false;
    }
  });
};

// 计算合计
const totalPrice = ref(0);
const getCartTotalPriceByUserId = async () => {
  const res = await getCartTotalPriceByUserIdApi(userStore.userInfo.userId);
  totalPrice.value = res.data.data * 100;
};

// 复选框
// const checked = ref(false);
// 计步器
// const value = ref(1);

// 修改购物车信息
const loading = ref(0);
const updateCartInfo1 = async (item) => {
  showLoadingToast({
    message: "加载中...",
    forbidClick: true,
    duration: loading,
  });
  if (item.goodsSelectedStatus == false) {
    item.goodsSelectedStatus = 0;
  } else {
    item.goodsSelectedStatus = 1;
  }
  console.log(item);
  const data = {
    cartId: item.cartId,
    goodsNumber: item.goodsNumber,
    goodsSelectedStatus: item.goodsSelectedStatus,
  };
  const res = await updateCartInfoApi(data);
  console.log(res);
  if (res.data.code == 200) {
    getCartTotalPriceByUserId();
    getCarList();
    loading.value = 1000;
  }
};
const updateCartInfo = debounce(updateCartInfo1, 200);

// 修改数量
const onOverlimit = (item) => {
  showConfirmDialog({
    title: "删除",
    message: `确认删除${item.goods.goodsName}这个商品吗？`,
  })
    .then(() => {
      // on confirm
      const res = deleteCartInfoByCartIdApi(item.cartId);
      getCartTotalPriceByUserId();
      getCarList();
    })
    .catch(() => {
      // on cancel
      console.log("on cancel");
    });
};

// 删除勾选项
const deleteCartInfoByCartId = () => {
  showConfirmDialog({
    title: "删除",
    message: "确认删除已勾选的商品吗？",
  }).then(() => {
    // on confirm
    cartList.value.forEach((item) => {
      if (item.goodsSelectedStatus == 1) {
        const res = deleteCartInfoByCartIdApi(item.cartId);
      }
    });
    showLoadingToast({
      message: "正在删除中...",
      forbidClick: true,
      duration: loading,
    });
    setTimeout(() => {
      getCartTotalPriceByUserId();
      getCarList();
      loading.value = 1000;
    }, 200);
  });
};

onMounted(() => {
  getCarList();
  getCartTotalPriceByUserId();
});
</script>

<template>
  <div class="cart">
    <van-nav-bar
      fixed
      title="购物车"
      :left-text="deleteText"
      @click-left="deleteCartInfoByCartId"
    />

    <div class="content">
      <!-- <van-empty description="亲，购物车是空的哦，请加购您心仪的商品" /> -->
      <!-- 购物车列表 -->
      <div class="list" v-for="item in cartList" :key="item.cartId">
        <!-- 复选框 -->
        <div class="check">
          <van-checkbox
            v-model="item.goodsSelectedStatus"
            @click="updateCartInfo(item)"
          ></van-checkbox>
        </div>
        <!-- 图片 -->
        <div class="img">
          <img :src="item.goods.goodsImg" alt="" />
        </div>
        <div class="info">
          <div class="name">{{ item.goods.goodsName }}</div>
          <div class="inventory">
            剩余库存:{{ item.goods.remainingInventory }}
          </div>
          <div class="price">单价:{{ item.goods.goodsPrice }}</div>
          <div class="smallprice">
            小计:{{ item.goods.goodsPrice * item.goodsNumber }}
          </div>
        </div>
        <div class="stepper">
          <van-stepper
            v-model="item.goodsNumber"
            theme="round"
            button-size="22"
            disable-input
            :show-plus="item.goods.remainingInventory !== item.goodsNumber"
            :max="item.goods.remainingInventory"
            @change="updateCartInfo(item)"
            @overlimit="onOverlimit(item)"
          />
        </div>
      </div>
    </div>

    <van-submit-bar
      :price="totalPrice"
      button-text="提交订单"
      @submit="onSubmit"
      :disabled="submitBtn"
      tip="平台优惠活动在订单生成后自动进行"
    />
  </div>
  <!-- 底部 -->
  <TabBar />
</template>

<style scoped lang="scss">
.cart {
  width: 100%;
  background-color: #f7f7f7;
  .content {
    margin-top: 46px;
    margin-bottom: 100px;

    width: 100%;
    .list {
      display: flex;
      align-items: center;
      width: 100%;
      background-color: white;
      margin-top: 6px;
      .check {
        padding-left: 10px;
      }
      .img {
        width: 30%;
        text-align: center;
        img {
          width: 80%;
        }
      }
      .info {
        width: 38%;
        .name {
          font-size: 1.1rem;
        }
        .inventory {
          font-size: 1rem;
          color: #c1c1c1;
        }
        .price {
          color: red;
        }
        .smallprice {
          color: red;
          font-size: 1.3rem;
        }
      }
    }
  }
}
.van-submit-bar {
  bottom: 50px;
}
</style>
