<script setup>
import { onMounted, ref } from "vue";
import {
  getGoodsClassificationListApi,
  getHomeNoticeApi,
} from "@/apis/user/home";
import GoodsList from "./components/GoodsList/index.vue";
import TabBar from "@/components/TabBar/index.vue";
// 搜索内容
const value = ref("");
const searchValue = ref("");
const onSearch = () => {
  searchValue.value = value.value;
};

// 获取通知栏信息
const noticeContent = ref("");
const getNoticeContent = async () => {
  await getHomeNoticeApi().then((res) => {
    noticeContent.value = res.data.data.noticeContent;
  });
};

// 分类列表选中值
const active = ref(0);
// 设置tab值
const setClassificationValue = (value) => {
  // console.log(value);
  active.value = value.name;
};

// 获取产品分类列表
const goodsClassificationList = ref([]);
const getGoodsClassificationList = async () => {
  await getGoodsClassificationListApi().then((res) => {
    const newRes = [{ goodsClassificationName: "全部" }, ...res.data.data];
    goodsClassificationList.value = newRes;
  });
};

onMounted(() => {
  getNoticeContent();
  getGoodsClassificationList();
});
</script>

<template>
  <div class="home">
    <!-- 通知栏 -->
    <van-notice-bar left-icon="volume-o" :text="noticeContent" />
    <!-- 搜索框 -->
    <van-search
      v-model="value"
      placeholder="在此搜索烟花"
      shape="round"
      @search="onSearch"
    />
  </div>
  <!-- 分类 -->
  <van-tabs
    v-model:active="active"
    @click-tab="setClassificationValue"
    offset-top="94"
    sticky
  >
    <van-tab
      v-for="item in goodsClassificationList"
      :title="item.goodsClassificationName"
    >
      <GoodsList :classificationId="active" :searchValue="searchValue" />
    </van-tab>
  </van-tabs>

  <!-- 底部 -->
  <TabBar />
</template>

<style scoped lang="scss">
.home {
  position: sticky;
  z-index: 1;
  top: 0;
}
</style>
