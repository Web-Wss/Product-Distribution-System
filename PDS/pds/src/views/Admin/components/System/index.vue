<script setup>
import { onMounted, ref } from "vue";
import { useDistributorStore } from "@/store/distributor";
const tel = ref("13359074748");
const name = ref("管理员");
const onEdit = () => {
  console.log("点击了");
};
</script>

<template>
  <div class="system">
    <van-contact-card type="edit" :tel="tel" :name="name" @click="onEdit" />
    <van-cell is-link icon="shop-o" @click="editpassword">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">站点信息</span>
      </template>
    </van-cell>
    <van-cell is-link icon="shop-o" @click="editpassword">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">通知管理</span>
      </template>
    </van-cell>
    <van-cell is-link icon="shop-o" @click="editpassword">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">满减规则</span>
      </template>
    </van-cell>
    <van-cell is-link icon="shop-o" @click="editpassword">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">满减规则</span>
      </template>
    </van-cell>
    <van-cell is-link icon="shop-o" @click="editpassword">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">登录密码</span>
      </template>
    </van-cell>
    <!-- 退出登录 -->
    <div class="button">
      <van-button type="danger">退出登录</van-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.custom-title {
  margin-right: 4px;
  vertical-align: middle;
}

.search-icon {
  font-size: 16px;
  line-height: inherit;
}

.button {
  position: absolute;
  bottom: 10rem;
  left: calc(50% - 44px);
}
</style>
