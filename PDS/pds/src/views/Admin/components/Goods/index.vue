<script setup>
import { getgoodsinfoApi, getgoodslistApi, updateGoodsApi } from "@/apis/admin";
import { onMounted, ref } from "vue";

// 商品列表
const goodsList = ref([]);

// 获取商品列表
const getGoodsList = async () => {
  const res = await getgoodslistApi();
  console.log(res);
  if (res.data.code == 200) {
    goodsList.value = res.data.data;
  }
};

// 编辑产品
const editGoodsInfo = ref({
  goodsId: "1", //id
  goodsClassificationId: "1", //分类
  goodsName: "商品名称", //名称
  goodsPrice: "10", //售价
  goodsOldPrice: "15", //市场价
  goodsCompany: "/个", //单位
  goodsTotalInventory: "58", //总库存
  remainingInventory: "5", //剩余库存
});
const show = ref(false);
const showEdit = async (id) => {
  // 根据id获取商品信息
  const res = await getgoodsinfoApi(id);
  console.log(res);
  if (res.data.code == 200) {
    editGoodsInfo.value = res.data.data;
    show.value = true;
  }
};
const editGoodsIngoBtn = async () => {
  const res = await updateGoodsApi(editGoodsInfo.value);
  console.log(res);
  if (res.data.code == 200) {
    showNotify({ type: "success", message: "商品信息修改成功" });
    show.value = false;
  }
};

onMounted(() => {
  getGoodsList();
});
</script>

<template>
  <div class="goods">
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      共有{{ goodsList.length }}件商品
    </van-divider>
    <van-grid :column-num="3" :gutter="10">
      <van-grid-item
        v-for="item in goodsList"
        :key="item.goodsId"
        @click="showEdit(item.goodsId)"
      >
        <div class="id">编号:{{ item.goodsId }}</div>
        <div class="img">
          <img :src="item.goodsImg" />
        </div>
        <div class="name">{{ item.goodsName }}</div>
      </van-grid-item>
    </van-grid>
  </div>

  <!-- 编辑界面 -->
  <van-dialog
    v-model:show="show"
    title="编辑商品信息"
    show-cancel-button
    confirmButtonText="修改"
    @confirm="editGoodsIngoBtn"
  >
    <van-form>
      <van-cell-group inset>
        <van-field
          disabled
          v-model="editGoodsInfo.goodsId"
          name="商品ID"
          label="商品ID"
          placeholder="商品ID"
        />
        <van-field
          v-model="editGoodsInfo.goodsClassificationId"
          name="商品分类ID"
          label="商品分类ID"
          placeholder="商品分类ID"
          :rules="[{ required: true, message: '请输入商品分类ID' }]"
        />
        <van-field
          v-model="editGoodsInfo.goodsName"
          name="商品名称"
          label="商品名称"
          placeholder="商品名称"
          :rules="[{ required: true, message: '请输入商品名称' }]"
        />
        <van-field
          v-model="editGoodsInfo.goodsPrice"
          name="商品价格"
          label="商品价格"
          placeholder="商品价格"
          :rules="[{ required: true, message: '请输入商品价格' }]"
        />
        <van-field
          v-model="editGoodsInfo.goodsOldPrice"
          name="商品市场价"
          label="商品市场价"
          placeholder="商品市场价"
          :rules="[{ required: true, message: '请输入商品市场价' }]"
        />
        <van-field
          v-model="editGoodsInfo.goodsCompany"
          name="商品单位"
          label="商品单位"
          placeholder="商品单位"
          :rules="[{ required: true, message: '请输入商品单位' }]"
        />
        <van-field
          v-model="editGoodsInfo.goodsTotalInventory"
          disabled
          name="商品总库存"
          label="商品总库存"
          placeholder="商品总库存"
          :rules="[{ required: true, message: '请输入商品总库存' }]"
        />
        <van-field
          v-model="editGoodsInfo.remainingInventory"
          name="商品剩余库存"
          label="商品剩余库存"
          placeholder="商品剩余库存"
          :rules="[{ required: true, message: '请输入商品剩余库存' }]"
        />
      </van-cell-group>
    </van-form>
  </van-dialog>
</template>

<style scoped lang="scss">
.id {
  color: red;
  margin-bottom: 1rem;
}
.img {
  width: 100%;
  height: 6rem;
  img {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
}
.name {
  margin-top: 1rem;
  font-size: 0.8rem;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
