<script setup>
import { ref, onMounted } from "vue";
import { editreductionApi, getreductionApi } from "@/apis/admin/index";

const reductionList = ref([]);
const getReductionList = async () => {
  const res = await getreductionApi();
  // console.log(res);
  reductionList.value = res.data.data;
};

// 编辑满减信息
const show = ref(false);
const reductionId = ref(0);
const fullConditionPrice = ref("");
const fullReductionAmount = ref("");

const editBtn = (item) => {
  reductionId.value = item.fullReductionId;
  fullConditionPrice.value = item.fullConditionPrice;
  fullReductionAmount.value = item.fullReductionAmount;
  show.value = true;
};
const sendEditBtn = async () => {
  const res = await editreductionApi(
    reductionId.value,
    fullConditionPrice.value,
    fullReductionAmount.value
  );
  // console.log(res);
  getReductionList();
};

onMounted(() => {
  getReductionList();
});
</script>

<template>
  <div>
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      满减规则
    </van-divider>
    <van-swipe-cell v-for="item in reductionList" :key="item.fullReductionId">
      <template #left>
        <van-button square type="primary" text="编辑" @click="editBtn(item)" />
      </template>
      <van-cell
        :border="false"
        :title="'满' + item.fullConditionPrice"
        :value="'减' + item.fullReductionAmount"
      />
      <!-- <template #right>
        <van-button square type="danger" text="删除" />
      </template> -->
    </van-swipe-cell>
  </div>

  <!-- 编辑 -->
  <van-dialog
    v-model:show="show"
    title="编辑满减信息"
    show-cancel-button
    @confirm="sendEditBtn"
  >
    <van-cell-group inset>
      <van-field
        v-model="fullConditionPrice"
        label="满"
        placeholder="满多少?"
      />
      <van-field
        v-model="fullReductionAmount"
        label="减"
        placeholder="减多少?"
      />
    </van-cell-group>
  </van-dialog>
</template>

<style scoped></style>
