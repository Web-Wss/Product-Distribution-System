<script setup>
import { onMounted, ref } from "vue";

import { useDistributorStore } from "@/store/distributor";
import {
  getorderlistApi,
  setordercompleteApi,
  updateorderstatusApi,
} from "@/apis/admin";
import { useRouter } from "vue-router";
const distributorStore = useDistributorStore();
const router = useRouter();
const activeNames = ref("1");
const active = ref(1);

// 订单列表
const orderList = ref([]);

const getOrderList = async () => {
  const res = await getorderlistApi();
  // console.log(res);
  orderList.value = res.data.data;
};

// 状态
const EditOrdersId = ref(0);
const showPicker = ref(false);
const columns = [
  { text: "已下单", value: "1" },
  { text: "分拣中", value: "2" },
  { text: "配送中", value: "3" },
];
const fieldValue = ref(["已下单", "分拣中", "配送中", "已完成"]);
const onConfirm = async ({ selectedOptions }) => {
  // 触发修改状态
  // console.log(selectedOptions[0].value, EditOrdersId.value);
  const res = await updateorderstatusApi(
    EditOrdersId.value,
    selectedOptions[0].value
  );
  getOrderList();
  showPicker.value = false;
};
// 修改为已完成
const show = ref(false);
const checked = ref("1");
const editComplate = async () => {
  const res = await setordercompleteApi(EditOrdersId.value, checked.value);
  // console.log(res);
  getOrderList();
};

// 去订单详情
const goOrderDetail = () => {
  router.push({
    path: "/orderDetail",
    query: {
      ordersId: EditOrdersId.value,
    },
  });
};

onMounted(() => {
  getOrderList();
});
</script>

<template>
  <div class="order">
    <div class="list">
      <div
        class="item"
        v-for="item in orderList"
        :key="item.ordersId"
        @click="EditOrdersId = item.ordersId"
      >
        <van-collapse v-model="activeNames" accordion>
          <van-collapse-item
            :title="'订单编号:' + item.ordersId"
            :name="item.ordersId"
            :value="'实付金额:' + item.orderActualPayment + '元'"
          >
            <van-steps :active="item.orderStatus - 1">
              <van-step>已下单</van-step>
              <van-step>分拣中</van-step>
              <van-step>配送中</van-step>
              <van-step>已完成</van-step>
            </van-steps>
            <van-cell-group>
              <van-cell title="下单时间" :value="item.orderCreateTime" />
              <van-cell title="实际金额" :value="item.orderTotalPrice + '元'" />
              <van-cell
                title="优惠金额"
                :value="item.orderDiscountPrice + '元'"
              />
              <van-cell
                title="实付金额"
                :value="item.orderActualPayment + '元'"
              />
            </van-cell-group>
            <div class="button" style="text-align: center; margin-top: 1rem">
              <van-field
                v-model="fieldValue[item.orderStatus - 1]"
                is-link
                readonly
                label="修改进度"
                placeholder="选择状态"
                @click="showPicker = true"
              />
              <van-popup v-model:show="showPicker" round position="bottom">
                <van-picker
                  :columns="columns"
                  @cancel="showPicker = false"
                  @confirm="onConfirm"
                />
              </van-popup>
              <br />
              <van-button type="primary" size="small" @click="show = true"
                >设置订单为已完成</van-button
              >
              <van-dialog
                v-model:show="show"
                title="修改订单状态为已完成"
                show-cancel-button
                @confirm="editComplate"
              >
                <van-radio-group v-model="checked" style="padding: 1rem">
                  <van-radio name="1">微信支付</van-radio>
                  <van-radio name="2" style="margin-top: 1rem"
                    >支付宝支付</van-radio
                  >
                  <van-radio name="3" style="margin-top: 1rem"
                    >现金支付</van-radio
                  >
                </van-radio-group>
              </van-dialog>
              <br />
              <br />
              <van-button type="primary" size="small" @click="goOrderDetail"
                >查看订单详情</van-button
              >
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
  padding-bottom: 50px;
  // margin-bottom: 50px;
  .item {
    padding-top: 1rem;
    width: 100%;
    // background-color: aquamarine;
  }
}
</style>
