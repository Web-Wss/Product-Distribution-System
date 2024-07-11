<script setup>
import { userLoginApi } from "@/apis/login/login";
import { ref } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/store/user";
const userStore = useUserStore();

const router = useRouter();

const form = ref({
  username: "",
  phone: "",
});

// 提交表单
const onSubmit = async () => {
  showLoadingToast({
    message: "登录中...",
    forbidClick: true,
    duration: 2000,
  });
  const res = await userLoginApi(form.value);
  console.log(res);
  if (res.data.code === 400) {
    // console.log("登录失败", res.data.msg);
    showFailToast(res.data.msg);
  } else {
    userStore.setUserInfo(res.data.data.token, res.data.data.user);
    showSuccessToast("登录成功，正在跳转页面！");
    setTimeout(() => {
      router.replace("/");
    }, 1000);
  }
};
</script>

<template>
  <div id="login">
    <div class="logo">
      <van-image
        width="100"
        height="100"
        src="https://img0.baidu.com/it/u=863579937,397682646&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=426"
      />
      <br />
      <span>烟花线上选购平台</span>
    </div>
    <!-- 输入框 -->
    <van-form @submit="onSubmit">
      <van-field
        required
        clearable
        v-model="form.username"
        name="username"
        label="姓氏"
        placeholder="请输入您的姓氏，例如：徐"
        :rules="[{ required: true, message: '请输入您的姓氏' }]"
      />
      <van-field
        required
        clearable
        v-model="form.phone"
        name="phone"
        label="手机号"
        placeholder="请输入您的手机号，便于我们联系您"
        :rules="[{ required: true, message: '请输入您的手机号' }]"
      />

      <div style="margin: 36px">
        <van-button round block type="success" native-type="submit"
          >进入平台，开始选购</van-button
        >
      </div>
    </van-form>
  </div>
</template>

<style scoped>
.logo {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;
  width: 100%;
  height: 30vh;
}
.logo span {
  font-size: 18px;
  margin-bottom: 10px;
  color: #112983;
}

.register {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  height: 20vh;
  /* background-color: aqua; */
}
.register span {
  cursor: pointer;
  font-size: 14px;
  color: rgb(89, 116, 247);
}
</style>
