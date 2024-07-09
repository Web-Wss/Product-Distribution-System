<script setup>
import { getOrderDetailApi } from "@/apis/user/home";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();

// 返回按钮
const onClickLeft = () => history.back();
// 步骤
const stepList = ref([
  { id: 1, text: "已下单" },
  { id: 2, text: "分拣中" },
  { id: 3, text: "配送中" },
  { id: 4, text: "已完成" },
  { id: 5, text: "已取消" },
]);
const active = ref(1);

// 订单详细信息
const orderInfo = ref({
  orderListId: 2025001,
  ordersId: 2025001,
  goodsId: 1,
  goodsPrice: null,
  goodsNumber: 1,
  priceSubtotal: 15,
  address: {
    addressId: 1,
    userId: 2025001,
    addressInfo: "安徽省合肥市庐阳区XX小区",
    contacts: "位先生",
    phone: "13359074748",
    isDefault: 1,
  },
  orders: {
    ordersId: 2025001,
    userId: 2025001,
    addressId: 1,
    orderTotalPrice: 15,
    orderDiscountPrice: 0,
    orderActualPayment: 15,
    orderCreateTime: "2024-07-04 17:50:06",
    orderRemarks: "备注",
    orderStatus: 1,
    payType: null,
    completionTime: "2024-07-06 16:40:53",
    distributorId: 2025001,
  },
  goods: {
    goodsId: 1,
    goodsClassificationId: 4,
    goodsName: "星际导弹（每盒四根）",
    goodsPrice: 15,
    goodsOldPrice: 25,
    goodsCompany: "4根/盒",
    goodsTotalInventory: 36,
    goodsImg: "http://ywesc-img.webwss.cn/yh/1.png",
    goodsVideo: "http://ywesc-img.webwss.cn/yh/1.mp4",
    goodsStatus: 1,
    createTime: "2024-07-02 16:42:38",
    isDelete: 1,
    soldQuantity: null,
    remainingInventory: null,
  },
});

// 获取订单id
const orderId = ref(route.query.ordersId);
// 获取订单信息
const getOrderInfo = async () => {
  const res = await getOrderDetailApi(orderId.value);
  // console.log(res);
  orderInfo.value = res.data.data;
  active.value = res.data.data.orders.orderStatus - 1;
};
onMounted(() => {
  getOrderInfo();
});
</script>

<template>
  <div class="order-detail">
    <!-- 导航栏 -->
    <van-nav-bar
      fixed
      title="订单详情"
      left-text="返回"
      left-arrow
      placeholder
      @click-left="onClickLeft"
    />
    <!-- 步骤 -->
    <div class="step">
      <van-steps :active="active">
        <van-step v-for="item in stepList" :key="item.id">{{
          item.text
        }}</van-step>
      </van-steps>
    </div>

    <!-- 商品信息 -->
    <div class="order-info">
      <div class="order-info-item">
        <div class="goods_img">
          <img :src="orderInfo.goods.goodsImg" alt="" />
        </div>
        <div class="goods_name">
          <div class="name">{{ orderInfo.goods.goodsName }}</div>
          <div class="price">单价:￥{{ orderInfo.goodsPrice }}</div>
        </div>
        <div class="goods_num">X{{ orderInfo.goodsNumber }}</div>
        <div class="goods_total">￥{{ orderInfo.priceSubtotal }}</div>
      </div>
      <!-- 订单信息 -->
      <van-cell-group>
        <van-cell title="优惠金额">
          <van-tag type="success"
            >￥{{ orderInfo.orders.orderDiscountPrice }}</van-tag
          >
        </van-cell>
        <van-cell title="实付金额">
          <van-tag type="primary" size="large"
            >￥{{ orderInfo.orders.orderActualPayment }}</van-tag
          >
        </van-cell>
        <van-cell title="收货地址" :label="orderInfo.address.addressInfo" />
        <van-cell title="联系人" :value="orderInfo.address.contacts" />
        <van-cell title="联系方式" :value="orderInfo.address.phone" />
        <van-cell title="订单ID" :value="orderInfo.orders.ordersId" />
        <van-cell title="下单时间" :value="orderInfo.orders.orderCreateTime" />
        <van-cell title="分销商ID" :value="orderInfo.orders.distributorId" />
        <van-cell
          title="订单完成时间"
          :value="orderInfo.orders.completionTime"
        />
        <van-cell title="支付方式" :value="orderInfo.orders.payType" />
        <van-cell title="备注信息" :value="orderInfo.orders.orderRemarks" />
      </van-cell-group>
    </div>
  </div>
</template>

<style scoped lang="scss">
.order-detail {
  width: 100%;
  .step {
    width: 100%;
    position: sticky;
    top: 46px;
  }
  .order-info-item {
    padding: 0.5rem;
    display: flex;
    .goods_img {
      width: 25%;
      img {
        width: 100%;
        object-fit: cover;
      }
    }
    .goods_name {
      width: 50%;
      padding-left: 1rem;
      .name {
      }
      .price {
        padding-top: 0.4rem;
        color: red;
      }
    }
    .goods_num {
      text-align: center;
      line-height: 4rem;
      color: #8b8b8b;
      font-size: 0.8rem;
    }
    .goods_total {
      line-height: 4rem;
      flex: 1;
      text-align: center;
    }
  }
}
</style>
