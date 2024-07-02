<script setup>
import { onMounted, ref } from "vue";
import {
  getGoodsClassificationListApi,
  getHomeNoticeApi,
} from "@/apis/user/home";
// 获取通知栏信息
const noticeContent = ref("");
// 搜索内容
const value = ref("");
// 分类列表选中值
const active = ref(0);
const getNoticeContent = () => {
  getHomeNoticeApi().then((res) => {
    console.log(res);
    noticeContent.value = res.data.data.noticeContent;
  });
};

// 获取产品分类列表
const goodsClassificationList = ref([]);
const getGoodsClassificationList = () => {
  getGoodsClassificationListApi().then((res) => {
    console.log(res);
    goodsClassificationList.value = res.data.data;
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
    <van-search v-model="value" placeholder="在此搜索烟花" shape="round" />
  </div>
  <!-- 分类 -->
  <van-tabs v-model:active="active">
    <van-tab
      v-for="item in goodsClassificationList"
      :title="item.goodsClassificationName"
    >
      内容 {{ 1 }}
    </van-tab>
  </van-tabs>
</template>

<style scoped lang="scss">
@import "./index.scss";
</style>
