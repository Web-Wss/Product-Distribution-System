<script setup>
import {
  getAddressListApi,
  addAddressApi,
  editAddressApi,
  deleteAddressApi,
} from "@/apis/user/home";
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
  // console.log(res);
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

// 编辑地址信息
const editAddress = ref({
  userId: 0,
  addressId: 0,
  contacts: "",
  phone: "",
  addressInfo: "",
  isDefault: 0,
});
const show1 = ref(false);
const onEdit = (item, index) => {
  // console.log(item);
  editAddress.value.userId = userStore.userInfo.userId;
  editAddress.value.addressId = item.id;
  editAddress.value.contacts = item.name;
  editAddress.value.phone = item.tel;
  editAddress.value.addressInfo = item.address;
  editAddress.value.isDefault = 0;
  show1.value = true;
};
const editAddressBtn = async () => {
  // console.log(editAddress.value);
  const res = await editAddressApi(editAddress.value);
  if (res.data.code == 200) {
    showNotify({ type: "success", message: res.data.msg });
    getAddressList();
    show1.value = false;
  }
};

// 修改默认地址
const onSelect = async (item) => {
  editAddress.value.userId = userStore.userInfo.userId;
  editAddress.value.addressId = item.id;
  editAddress.value.contacts = item.name;
  editAddress.value.phone = item.tel;
  editAddress.value.addressInfo = item.address;
  editAddress.value.isDefault = 1;
  const res = await editAddressApi(editAddress.value);
  if (res.data.code == 200) {
    showNotify({ type: "success", message: res.data.msg });
    getAddressList();
    show1.value = false;
  }
};

// 删除地址信息
const deleteAddress = async () => {
  const res = await deleteAddressApi(editAddress.value.addressId);
  if (res.data.code == 200) {
    showNotify({ type: "success", message: res.data.msg });
    getAddressList();
    show1.value = false;
  }
};

onMounted(() => {
  getAddressList();
});

// 返回按钮
const onClickLeft = () => history.back();

const chosenAddressId = ref(2);
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
    @select="onSelect"
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

  <!-- 编辑地址信息 -->
  <van-dialog
    v-model:show="show1"
    title="编辑地址"
    show-cancel-button
    confirmButtonText="修改"
    @confirm="editAddressBtn"
  >
    <van-cell-group inset>
      <van-field
        required
        v-model="editAddress.contacts"
        label="联系人"
        placeholder="请输入联系人"
      />
      <van-field
        required
        v-model="editAddress.phone"
        label="联系方式"
        placeholder="请输入联系方式"
      />
      <van-field
        required
        v-model="editAddress.addressInfo"
        label="收货地址"
        placeholder="请输入收货地址"
      />
    </van-cell-group>
    <!-- 删除按钮 -->
    <div class="button" style="text-align: center">
      <van-button
        type="danger"
        size="small"
        style="margin-top: 10px"
        @click="deleteAddress"
        >删除</van-button
      >
    </div>
  </van-dialog>
</template>

<style scoped></style>
