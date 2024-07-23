<script setup>
import {
  getCarListByUserIdApi,
  getCartTotalPriceByUserIdApi,
  updateCartInfoApi,
  deleteCartInfoByCartIdApi,
  getFullReductionRuleApi,
  getAddressListApi,
  getDefaultAddressApi,
  createOrderApi,
} from "@/apis/user/home";
import TabBar from "@/components/TabBar/index.vue";
import { onMounted, ref, watchEffect } from "vue";
import { useUserStore } from "@/store/user";
import { useRouter } from "vue-router";
// lodash
import { debounce } from "lodash";
const userStore = useUserStore();
const router = useRouter();

// 订单总金额
const totalPrice = ref(0);
const getCartTotalPriceByUserId = async () => {
  const res = await getCartTotalPriceByUserIdApi(userStore.userInfo.userId);
  totalPrice.value = res.data.data;
};
// 获取购物车列表根据用户id
const cartList = ref([]);

// 备注信息
const remark = ref("");
// 满减金额
const orderDiscountPrice = ref(0);
// 实付金额
const orderActualPayment = ref(0);
// 收获地址
const address = ref({
  // addressId: 1,
  // userId: "",
  // addressInfo: "",
  // contacts: "",
  // phone: "",
  // isDefault: "",
});
// 获取地址信息
const getAddress = async () => {
  const res = await getDefaultAddressApi(userStore.userInfo.userId);
  // console.log("地址", res);
  if (res.data.data == null) {
    return;
  }
  address.value = res.data.data;
};
// 获取满减规则
const getFullReductionRule = async () => {
  submitBtn.value = true;
  orderDiscountPrice.value = 0;
  orderActualPayment.value = 0;
  const res = await getFullReductionRuleApi();
  // console.table(res.data.data);
  setTimeout(() => {
    res.data.data.forEach((item) => {
      // console.log(item);
      if (item.fullConditionPrice <= totalPrice.value) {
        // console.log("执行了满减", item);
        orderDiscountPrice.value = item.fullReductionAmount;
        orderActualPayment.value = totalPrice.value - item.fullReductionAmount;
      }
    });
    if (orderDiscountPrice.value == 0) {
      orderActualPayment.value = totalPrice.value;
    }
    // submitBtn.value = false;
  }, 500);
};

// 删除按钮文字
const deleteText = ref("");
// 提交订单按钮是否可以点击
const submitBtn = ref(true);
// 提交订单按钮
const onSubmit = async () => {
  // 重新获取购物车列表
  const generateOrderDTO = {
    addressId: "",
    distributorId: "",
    orderActualPayment: "",
    orderDiscountPrice: "",
    orderTotalPrice: "",
    remark: "",
    userId: "",
  };
  generateOrderDTO.addressId = address.value.addressId;
  generateOrderDTO.distributorId = userStore.getDistributorId();
  generateOrderDTO.orderActualPayment = orderActualPayment.value;
  generateOrderDTO.orderDiscountPrice = orderDiscountPrice.value;
  generateOrderDTO.orderTotalPrice = totalPrice.value;
  generateOrderDTO.remark = remark.value;
  generateOrderDTO.userId = userStore.userInfo.userId;
  // console.log(generateOrderDTO);
  // 判断必填项是否已填写--收获地址、商品列表
  const res = await createOrderApi(generateOrderDTO);
  // console.log(res);
  getCarList();
  getCartTotalPriceByUserId();
  if (res.data.code !== 200) {
    // 库存更新，重新获取
    // showNotify({ type: "warning", message: "库存已更新，请重新下单" });
    showDialog({
      title: "下单失败！",
      message: "库存已更新，请重新下单",
      theme: "round-button",
    }).then(() => {
      // on close
    });
  }
};

// 监听必填数据，控制按钮状态
watchEffect(() => {
  if (
    address.value.addressId != undefined &&
    orderActualPayment.value !== 0 &&
    orderDiscountPrice.value !== null &&
    totalPrice.value != null &&
    cartList.value.length != 0
  ) {
    submitBtn.value = false;
  } else {
    submitBtn.value = true;
  }
});

const getCarList = async () => {
  submitBtn.value = true;
  const res = await getCarListByUserIdApi(userStore.userInfo.userId);
  // console.log(res);
  cartList.value = res.data.data;
  // 遍历购物车列表，判断是否有勾选
  deleteText.value = "";
  if (cartList.value.length > 0) {
    cartList.value.forEach((item) => {
      if (item.goodsSelectedStatus == 1) {
        deleteText.value = "删除已勾选的商品";
        submitBtn.value = false;
      }
    });
  }

  getFullReductionRule();
};

// 修改购物车信息
const loading = ref(0);
const updateCartInfo1 = async (item) => {
  submitBtn.value = true;
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
  // console.log(item);
  const data = {
    cartId: item.cartId,
    goodsNumber: item.goodsNumber,
    goodsSelectedStatus: item.goodsSelectedStatus,
  };
  const res = await updateCartInfoApi(data);
  // console.log(res);
  if (res.data.code == 200) {
    getCartTotalPriceByUserId();
    getCarList();
    loading.value = 1000;
  }
  getFullReductionRule();
};
const updateCartInfo = debounce(updateCartInfo1, 200);

// 修改数量
const onOverlimit = async (item) => {
  submitBtn.value = true;
  const res = showConfirmDialog({
    title: "删除",
    message: `确认删除${item.goods.goodsName}这个商品吗？`,
  })
    .then(() => {
      // on confirm
      const res = deleteCartInfoByCartIdApi(item.cartId);
      getCarList();
      getCartTotalPriceByUserId();
      getFullReductionRule();
    })
    .catch(() => {
      // on cancel
      // console.log("on cancel");
    });
  getCarList();
  getCartTotalPriceByUserId();
  getFullReductionRule();
};

// 删除勾选项
const deleteCartInfoByCartId = () => {
  submitBtn.value = true;
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
  getCarList();
  getCartTotalPriceByUserId();
  getFullReductionRule();
};

onMounted(() => {
  //  获取地址信息
  getAddress();
  // 获取购物车列表
  getCarList();
  // 获取购物车合计
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
      placeholder
    />

    <!-- 收获地址 -->
    <van-cell
      v-if="address.addressId"
      :title="address.addressInfo"
      icon="location-o"
      :label="`${address.contacts}` + ` - ` + `${address.phone}`"
      to="address"
    >
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <van-icon name="arrow" class="search-icon" />
      </template>
    </van-cell>
    <van-cell
      v-if="!address.addressId"
      title="请选择收获地址"
      is-link
      to="address"
    />

    <div class="content">
      <van-empty
        description="亲，购物车是空的哦，请加购您心仪的商品"
        v-show="cartList.length == 0"
      />
      <!-- 购物车列表 -->
      <div class="list" v-for="item in cartList" :key="item.cartId">
        <div class="wz" v-if="item.isSelected == 0">已售罄</div>
        <!-- 复选框 -->
        <div class="check">
          <van-checkbox
            :disabled="item.isSelected == 0 ? true : false"
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
            剩余库存:{{ item.goodsInventory.goodsInventorySum }}
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
            :show-plus="
              item.goodsInventory.goodsInventorySum !== item.goodsNumber
            "
            :max="item.goodsInventory.goodsInventorySum"
            @change="updateCartInfo(item)"
            @overlimit="onOverlimit(item)"
          />
        </div>
      </div>
    </div>

    <!-- 备注 -->
    <div class="bz">
      <van-cell-group inset class="bz">
        <van-field
          v-model="remark"
          rows="2"
          autosize
          label="备注"
          type="textarea"
          maxlength="50"
          placeholder="给商家留言"
          show-word-limit
        />
      </van-cell-group>
    </div>

    <van-submit-bar
      :price="orderActualPayment * 100"
      button-text="提交订单"
      @submit="onSubmit"
      :disabled="submitBtn"
      :tip="'订单总金额:' + totalPrice"
      :label="'优惠后'"
    >
      <template #default> 优惠金额:{{ orderDiscountPrice }} </template>
    </van-submit-bar>
  </div>
  <!-- 底部 -->
  <TabBar />
</template>

<style scoped lang="scss">
.cart {
  width: 100%;
  background-color: #f7f7f7;
  .content {
    // margin-top: 46px;
    margin-bottom: 100px;

    width: 100%;
    .list {
      position: relative;
      display: flex;
      align-items: center;
      width: 100%;
      background-color: white;
      margin-top: 6px;
      .wz {
        z-index: 9;
        background-color: #0000007d;
        position: absolute;
        width: 24%;
        left: 10.5%;
        text-align: center;
        color: white;
        font-size: 1.6rem;
      }
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

.bz {
  margin-bottom: 85px;
}
.van-submit-bar {
  bottom: 50px;
}
</style>
