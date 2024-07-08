<script setup>
import { getAddressListApi, addAddressApi } from "@/apis/user/home";
import { onMounted, ref } from "vue";
import { useUserStore } from "@/store/user";

const userStore = useUserStore();

// 地址列表
const addressList = ref([]);
// 获取地址列表
const getAddressList = async () => {
  const res = await getAddressListApi(userStore.userInfo.userId);
  addressList.value = res.data.data.map((item) => ({
    id: item.addressId,
    name: item.contacts,
    tel: item.phone,
    address: item.addressInfo,
    isDefault: item.isDefault,
  }));
  addressList.value.forEach((item) => {
    if (item.isDefault == 1) {
      chosenAddressId.value = item.id;
    }
  });
};

// 新增地址相关
const show = ref(false);
const onAdd = () => {
  show.value = true;
};
const addAddressInfo = ref({
  contacts: "",
  phone: "",
  address: "",
  isDefault: 0,
});
const addAddressBtn = async () => {
  const addressDTO = {
    userId: userStore.userInfo.userId,
    contacts: addAddressInfo.value.contacts,
    phone: addAddressInfo.value.phone,
    addressInfo: addAddressInfo.value.address,
    isDefault: 0,
  };
  const res = await addAddressApi(addressDTO);
  console.log(res);
  if (res.data.code == 200) {
    showNotify({ type: "success", message: res.data.msg });
    addAddressInfo.value.contacts = "";
    addAddressInfo.value.phone = "";
    addAddressInfo.value.address = "";
    getAddressList();
    show.value = false;
  } else {
    showNotify({ type: "danger", message: res.data.msg });
  }
};

onMounted(() => {
  getAddressList();
});

// 返回按钮
const onClickLeft = () => history.back();

const chosenAddressId = ref(2);

const onEdit = (item, index) => showToast("编辑地址:" + index);
</script>

<template>
  <div class="address">
    <van-nav-bar
      title="收货地址"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
    />
  </div>
  <!-- 地址列表 -->
  <van-address-list
    v-model="chosenAddressId"
    :list="addressList"
    default-tag-text="默认"
    @add="onAdd"
    @edit="onEdit"
  />

  <!-- 新增地址弹框 -->
  <van-dialog
    v-model:show="show"
    title="新增地址"
    show-cancel-button
    confirmButtonText="新增"
    @confirm="addAddressBtn"
  >
    <van-cell-group inset>
      <van-field
        required
        v-model="addAddressInfo.contacts"
        label="联系人"
        placeholder="请输入联系人"
      />
      <van-field
        required
        v-model="addAddressInfo.phone"
        label="联系方式"
        placeholder="请输入联系方式"
      />
      <van-field
        required
        v-model="addAddressInfo.address"
        label="收货地址"
        placeholder="请输入收货地址"
      />
    </van-cell-group>
  </van-dialog>
</template>

<style scoped></style>
