<script setup>
import { defineProps, ref, onMounted } from "vue";
import { getGoodsListByClassificationIdApi } from "@/apis/user/home";

const classificationId = defineProps(["classificationId"]);

// 获取产品列表根据分类id
const goodsList = ref([]);
const getGoodsList = async () => {
  await getGoodsListByClassificationIdApi(
    classificationId.classificationId
  ).then((res) => {
    // console.log(res);
    goodsList.value = res.data.data;
  });
};

onMounted(() => {
  getGoodsList();
});
</script>

<template>
  <div class="goods-list">
    <div
      class="list"
      v-for="item in goodsList"
      :key="item.goodsId"
      @click="
        $router.push({ path: '/detail', query: { goodsId: item.goodsId } })
      "
    >
      <div class="img">
        <img :src="item.goodsImg" alt="" />
      </div>
      <div class="id">编号：{{ item.goodsId }}</div>
      <div class="name">名称：{{ item.goodsName }}</div>
      <div class="bottom">
        <div class="left">
          <div class="price">价格：{{ item.goodsPrice }}元</div>
          <div class="old-price">市场价：{{ item.goodsOldPrice }}元</div>
          <div class="inventory">库存：{{ item.goodsTotalInventory }}</div>
        </div>
        <div class="right">
          <van-button class="btn" type="success" size="small"
            >+ 加购</van-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.goods-list {
  background-color: #f7f7f7;
  width: 100%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  .list {
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #fff;
    margin-top: 0.6rem;
    margin-bottom: 0.3rem;
    padding: 0.6rem;
    width: 40%;
    .img {
      width: 100%;
      img {
        width: 100%;
      }
    }
    .id {
      font-size: 0.8rem;
      color: rgb(91, 24, 235);
    }
    .name {
      font-size: 1rem;
      font-weight: 600;
    }
    .bottom {
      display: flex;
      .left {
        flex: 1;
        .price {
          color: red;
          font-size: 1.1rem;
        }
        .old-price {
          text-decoration: line-through;
          color: rgba(0, 0, 0, 0.6);
        }
      }
    }
  }
}
</style>
