<script setup>
import { defineProps, ref, onMounted, watchEffect, watch } from "vue";
import { useUserStore } from "@/store/user";
import {
  getGoodsListByClassificationIdApi,
  addGoodsToCartApi,
  searchGoodsApi,
} from "@/apis/user/home";
// lodash
import { debounce } from "lodash";
const userStore = useUserStore();
const props = defineProps(["classificationId", "searchValue"]);
// 搜索
const performSearch = (searchValue) => {
  console.log("子组件执行搜索", searchValue);
  searchGoodsApi(searchValue).then((res) => {
    // console.log(res);
    goodsList.value = res.data.data;
  });
};

watch(
  () => props.searchValue,
  (newValue) => {
    performSearch(newValue); // 当 searchValue 变化时执行搜索逻辑
  },
  { immediate: false } // 设定 immediate 为 false 来阻止初始执行
);

// 获取产品列表根据分类id
const goodsList = ref([]);
const getGoodsList = async () => {
  await getGoodsListByClassificationIdApi(props.classificationId).then(
    (res) => {
      console.log(res);
      goodsList.value = res.data.data;
    }
  );
};
// 已售罄按钮
const nobuy = () => {
  showToast({
    message: "该商品已售罄",
    position: "top",
  });
};

// 加购面板
const show = ref(false);
// 加购
const addCart = (item) => {
  cartInfo.value = {
    goodsId: item.goodsId,
    goodsImg: item.goodsImg,
    goodsName: item.goodsName,
    goodsPrice: item.goodsPrice,
    remainingInventory: item.remainingInventory,
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
          <div class="inventory">剩余库存：{{ item.remainingInventory }}</div>
        </div>
        <div class="right">
          <van-button
            v-if="item.remainingInventory > 0"
            class="btn"
            type="success"
            size="small"
            @click.stop="addCart(item)"
            >+ 加购</van-button
          >
          <van-button
            v-if="item.remainingInventory <= 0"
            class="btn"
            type="warning"
            size="small"
            @click.stop="nobuy"
            >已售罄</van-button
          >
        </div>
      </div>
    </div>
  </div>

  <!-- 加购面板 -->
  <van-action-sheet v-model:show="show" title="加购">
    <div class="content">
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
        .inventory {
          font-size: 0.8rem;
        }
      }
    }
  }
}

// 加购面板
.content {
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
