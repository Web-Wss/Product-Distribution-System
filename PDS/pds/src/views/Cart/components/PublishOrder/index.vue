<script setup>
import {
  getCartListByUserIdAndSelectedStatusApi,
  getFullReductionRuleApi,
  createOrderApi,
} from "@/apis/user/home";
import { ref, onMounted } from "vue";
import { useUserStore } from "@/store/user";
import { useRouter } from "vue-router";
const router = useRouter();

const userStore = useUserStore();
// 返回按钮
const onClickLeft = () => history.back();
// 满减规则
const fullReductionRule = ref([]);
// 获取满减规则
const getFullReductionRule = async () => {
  const res = await getFullReductionRuleApi();
  fullReductionRule.value = res.data.data;
  // console.table(res.data.data);
};

// 备注
const remark = ref("");
// 满减金额
const orderDiscountPrice = ref(0);
// 实付金额
const orderActualPayment = ref(0);
// 提交订单信息

// 订单生成信息
const orderInfo = ref({
  orderGoodsList: [
    {
      cartId: 1762107422,
      userId: 2025001,
      goodsId: 6,
      goodsNumber: 1,
      goodsSelectedStatus: 1,
      goods: {
        goodsId: 6,
        goodsClassificationId: 4,
        goodsName: "小豌豆自动旋转加特林（礼盒装|每盒4根）",
        goodsPrice: 188,
        goodsOldPrice: 240,
        goodsCompany: "4根/盒",
        goodsTotalInventory: 7,
        goodsImg: "http://ywesc-img.webwss.cn/yh/6.png",
        goodsVideo: "http://ywesc-img.webwss.cn/yh/6.mp4",
        goodsStatus: 1,
        createTime: "2024-07-02 16:47:45",
        isDelete: 1,
        soldQuantity: null,
        remainingInventory: null,
      },
      goodsClassification: {
        goodsClassificationId: 4,
        goodsClassificationName: "手持类",
      },
    },
    {
      cartId: 1762107426,
      userId: 2025001,
      goodsId: 2,
      goodsNumber: 1,
      goodsSelectedStatus: 1,
      goods: {
        goodsId: 2,
        goodsClassificationId: 5,
        goodsName: "蝌蚪王",
        goodsPrice: 10,
        goodsOldPrice: 15,
        goodsCompany: "盒",
        goodsTotalInventory: 120,
        goodsImg: "http://ywesc-img.webwss.cn/yh/2.jpg",
        goodsVideo: "http://ywesc-img.webwss.cn/yh/2.mp4",
        goodsStatus: 1,
        createTime: "2024-07-02 16:43:34",
        isDelete: 1,
        soldQuantity: null,
        remainingInventory: null,
      },
      goodsClassification: {
        goodsClassificationId: 5,
        goodsClassificationName: "摔炮类",
      },
    },
  ],
  address: {
    addressId: 1,
    userId: 2025001,
    addressInfo: "安徽省合肥市庐阳区XX小区",
    contacts: "位先生",
    phone: "13359074748",
    isDefault: 1,
  },
  orderTotalPrice: 198,
});

// 获取订单信息
const getOrderInfo = async () => {
  const res = await getCartListByUserIdAndSelectedStatusApi(
    userStore.userInfo.userId
  );
  orderInfo.value = res.data.data;
  // 将订单金额和满减规则匹配，计算出满减金额和实付金额
  setTimeout(() => {
    fullReductionRule.value.forEach((item) => {
      console.log(item);
      if (item.fullConditionPrice <= orderInfo.value.orderTotalPrice) {
        console.log("执行了满减", item);
        orderDiscountPrice.value = item.fullReductionAmount;
        orderActualPayment.value =
          orderInfo.value.orderTotalPrice - item.fullReductionAmount;
      }
    });
    if (orderDiscountPrice.value == 0) {
      orderActualPayment.value = orderInfo.value.orderTotalPrice;
    }
  }, 500);
};
onMounted(() => {
  getFullReductionRule();
  getOrderInfo();
});
// 提交订单按钮
const onSubmit = async () => {
  // 重新获取订单信息
  const publishOrderInfo = {
    userId: userStore.userInfo.userId,
    orderDiscountPrice: orderDiscountPrice.value,
    orderActualPayment: orderActualPayment.value,
    addressId: orderInfo.value.address.addressId,
    orderGoodsList: orderInfo.value.orderGoodsList,
    orderTotalPrice: orderInfo.value.orderTotalPrice,
    remark: remark.value,
    distributorId: userStore.distributorId,
  };
  const res = await createOrderApi(publishOrderInfo);
  console.log(publishOrderInfo);
  // if (res.data.code == 200) {
  //   // 跳转到订单详情页面
  //   router.replace({
  //     path: "/order",
  //   });
  // }
};
</script>

<template>
  <div class="publish-order">
    <!-- 导航栏 -->
    <van-nav-bar
      fixed
      title="订单结算"
      left-text="返回"
      left-arrow
      placeholder
      @click-left="onClickLeft"
    />
    <!-- 收获地址 -->
    <van-cell
      v-if="orderInfo.address"
      :title="orderInfo.address.addressInfo"
      icon="location-o"
      :label="
        `${orderInfo.address.contacts}` + ` - ` + `${orderInfo.address.phone}`
      "
    >
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <van-icon name="arrow" class="search-icon" />
      </template>
    </van-cell>
    <van-cell
      v-if="!orderInfo.address"
      title="请选择收获地址"
      is-link
      to="address"
    />
    <!-- 订单列表 -->
    <van-card
      v-for="(item, index) in orderInfo.orderGoodsList"
      :key="item.cartId"
      :num="item.goodsNumber"
      :price="item.goods.goodsPrice"
      :title="item.goods.goodsName"
      :thumb="item.goods.goodsImg"
    >
      <template #tags>
        <van-tag plain type="primary">{{
          item.goodsClassification.goodsClassificationName
        }}</van-tag>
      </template>
    </van-card>
    <!-- 合计和优惠金额 -->
    <van-cell-group>
      <van-cell title="总计金额" :value="'￥' + orderInfo.orderTotalPrice" />
      <van-cell
        title="优惠金额"
        :value="'￥' + orderDiscountPrice"
        label="我是满减规则"
      />
    </van-cell-group>

    <!-- 备注 -->
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
    <!-- 底部 -->
    <van-submit-bar
      label="优惠后"
      :price="orderActualPayment * 100"
      button-text="生成订单"
      @submit="onSubmit"
      placeholder
      :disabled="orderInfo.address ? false : true"
    />
  </div>
</template>

<style scoped lang="scss">
.publish-order {
  width: 100%;
  background-color: #f9f9f9;
}
.search-icon {
  font-size: 16px;
  line-height: inherit;
}
span {
  margin-top: 0.4rem;
}
.van-card {
  background-color: white;
  margin-bottom: 1rem;
}
.bz {
  margin-bottom: 1rem;
}
</style>
