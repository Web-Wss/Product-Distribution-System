<script setup>
import { onMounted, ref } from "vue";
import { showToast } from "vant";
import { getuserlistApi } from "@/apis/admin";
import { useRouter } from "vue-router";
const router = useRouter();

// 用户列表
const userList = ref([]);

// 获取用户列表
const getUserList = async () => {
  const res = await getuserlistApi();
  // console.log(res);
  res.data.data.forEach((item) => {
    item.tel = item.phone;
    item.name = item.username;
  });
  userList.value = res.data.data;
};

const goOrder = (item) => {
  // console.log(item);
  router.push({
    path: "/auserorder",
    query: {
      userId: item.userId,
    },
  });
};

onMounted(() => {
  getUserList();
});
</script>

<template>
  <div class="user">
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      共有{{ userList.length }}位用户
    </van-divider>
    <van-contact-card
      type="edit"
      v-for="item in userList"
      :tel="item.tel"
      :name="item.name"
      @click="goOrder(item)"
    />
  </div>
</template>

<style scoped></style>
