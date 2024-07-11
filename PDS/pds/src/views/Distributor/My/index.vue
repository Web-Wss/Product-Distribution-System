<script setup>
import { ref } from "vue";
const tel = ref("18888888888");
const name = ref("分销员1");
const onEdit = () => {
  console.log("点击了");
};
</script>

<template>
  <div class="my">
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      分销编号:2025001
    </van-divider>
    <van-contact-card type="edit" :tel="tel" :name="name" @click="onEdit" />
    <van-cell is-link icon="shop-o">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">修改密码</span>
      </template>
    </van-cell>
    <!-- 退出登录 -->
    <div class="button">
      <van-button type="danger">退出登录</van-button>
    </div>
  </div>
</template>

<style scoped>
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
