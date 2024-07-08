<script setup>
import TabBar from "@/components/TabBar/index.vue";
import { useUserStore } from "@/store/user";
import storage from "@/utils/storage";
import { ref } from "vue";

const userStore = useUserStore();
// 用户登录信息
const user = userStore.userInfo;
const { username, phone } = user;

// 退出登录
const logout = () => {
  userStore.clearUserInfo();
};
</script>

<template>
  <div id="my">
    <!-- 图片 -->
    <div class="img">
      <img
        src="https://5b0988e595225.cdn.sohucs.com/images/20180214/618107708388440c94d3dd56573c94c3.gif"
        alt=""
      />
    </div>
    <van-contact-card
      type="edit"
      :name="username + '（先生/女士）'"
      :tel="phone"
      :editable="false"
    />
    <!-- 我的订单，收货地址 -->
    <van-cell title="我的订单" icon="orders-o" to="myorder">
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <van-icon name="arrow" class="search-icon" />
      </template>
    </van-cell>
    <van-cell title="收货地址" icon="logistics" to="address">
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <van-icon name="arrow" class="search-icon" />
      </template>
    </van-cell>
    <!-- <van-cell title="意见反馈" icon="comment-o">
      <template #right-icon>
        <van-icon name="arrow" class="search-icon" />
      </template>
    </van-cell> -->

    <!-- 退出登录 -->
    <div class="logout">
      <van-button round class="btn" type="danger" @click="logout"
        >退出登录</van-button
      >
    </div>
    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<style scoped>
.img {
  padding: 10px;
}
.img img {
  width: 100%;
  border-radius: 20px 20px 0 0;
}
.search-icon {
  font-size: 16px;
  line-height: inherit;
}

.logout {
  width: 80%;
  margin: 30px auto;
}

.logout .btn {
  width: 100%;
}
</style>
