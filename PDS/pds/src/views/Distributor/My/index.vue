<script setup>
import { onMounted, ref } from "vue";
import { useDistributorStore } from "@/store/distributor";
import { changePasswordApi } from "@/apis/distributor";
const distributorStore = useDistributorStore();

const tel = ref("18888888888");
const name = ref("分销员1");
const distributorId = ref(111111);
const onEdit = () => {
  console.log("点击了");
};

// 修改密码
const show = ref(false);
const editpasswordInfo = ref({
  phone: "",
  password: "",
  newPassword: "",
  newDPassword: "",
});
const editpassword = () => {
  editpasswordInfo.value.phone = distributorStore.distributorInfo.phone;
  show.value = true;
};
const editBtn = async () => {
  const res = await changePasswordApi(editpasswordInfo.value);
  console.log(res);
  if (res.data.code == 200) {
    show.value = false;
    showNotify({ type: "success", message: "密码修改成功" });
    editpasswordInfo.value = {
      phone: "",
      password: "",
      newPassword: "",
      newDPassword: "",
    };
  }
};

onMounted(() => {
  tel.value = distributorStore.distributorInfo.phone;
  name.value = distributorStore.distributorInfo.nickname;
  distributorId.value = distributorStore.distributorInfo.distributorId;
});
</script>

<template>
  <div class="my">
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      分销编号:{{ distributorId }}
    </van-divider>
    <van-contact-card type="edit" :tel="tel" :name="name" @click="onEdit" />
    <van-cell is-link icon="shop-o" @click="editpassword">
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

  <!-- 修改密码 -->
  <van-dialog
    v-model:show="show"
    title="修改密码"
    show-cancel-button
    confirmButtonText="修改"
    @confirm="editBtn"
  >
    <van-cell-group inset>
      <van-field
        v-model="editpasswordInfo.phone"
        label="手机号"
        placeholder="手机号"
        required
        disabled
      />
      <van-field
        v-model="editpasswordInfo.password"
        required
        type="password"
        label="原密码"
        placeholder="请输入原密码"
      />
      <van-field
        v-model="editpasswordInfo.newPassword"
        required
        type="password"
        label="新密码"
        placeholder="请输入新密码"
      />
      <van-field
        type="password"
        required
        v-model="editpasswordInfo.newDPassword"
        label="确认新密码"
        placeholder="请再次输入新密码"
      />
    </van-cell-group>
  </van-dialog>
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
