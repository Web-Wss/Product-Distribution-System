<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/store/user";
const userStore = useUserStore();

const router = useRouter();

const form = ref({
  phone: "",
  password: "",
});

// 提交表单
const onSubmit = async () => {};
</script>

<template>
  <div id="login">
    <div class="logo">
      <van-image
        width="100"
        height="100"
        src="https://img0.baidu.com/it/u=4285798312,3206961763&fm=253&fmt=auto&app=138&f=JPEG?w=243&h=243"
      />
      <br />
      <span>分销商登录界面</span>
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
          >登录进入分销商管理后台</van-button
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
