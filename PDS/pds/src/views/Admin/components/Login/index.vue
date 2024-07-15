<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

import { useAdminStore } from "@/store/admin";
import { adminloginApi } from "@/apis/admin/index";
const adminStore = useAdminStore();
const router = useRouter();

const form = ref({
  phone: "",
  password: "",
});

// 提交表单
const onSubmit = async () => {
  showLoadingToast({
    message: "登录中...",
    forbidClick: true,
    duration: 2000,
  });
  const res = await adminloginApi(form.value.phone, form.value.password);
  if (res.data.code === 400) {
    showFailToast(res.data.msg);
  } else {
    adminStore.setAdminInfo(res.data.data.token, res.data.data.administrators);
    showSuccessToast("登录成功，正在跳转页面！");
    setTimeout(() => {
      router.replace("/admin");
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
        src="https://img2.baidu.com/it/u=1466749310,1865746552&fm=253&fmt=auto&app=138&f=JPEG?w=285&h=285"
      />
      <br />
      <span>管理员登录界面</span>
    </div>
    <!-- 输入框 -->
    <van-form @submit="onSubmit">
      <van-field
        required
        clearable
        v-model="form.phone"
        name="phone"
        label="手机号"
        placeholder="请输入登录手机号"
        :rules="[{ required: true, message: '请输入登录手机号' }]"
      />
      <van-field
        required
        clearable
        v-model="form.password"
        name="password"
        label="密码"
        placeholder="请输入登录密码"
        :rules="[{ required: true, message: '请输入登录密码' }]"
      />

      <div style="margin: 36px">
        <van-button round block type="success" native-type="submit"
          >登录进入管理员后台</van-button
        >
      </div>
    </van-form>
  </div>
</template>

<style scoped lang="scss">
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
