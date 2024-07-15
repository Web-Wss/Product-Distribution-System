<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useAdminStore } from "@/store/admin";
import {
  editnoticeinfoApi,
  editpasswordApi,
  editsiteinfoApi,
  getsysteminfoApi,
} from "@/apis/admin";
const useAdmin = useAdminStore();

const router = useRouter();
const tel = ref("13359074748");
const name = ref("管理员");

const systemInfo = ref({
  website: {
    websiteId: 1,
    websitePath: "http://192.168.16.96:5173",
  },
  reductions: [
    {
      fullReductionId: 1,
      fullConditionPrice: 300,
      fullReductionAmount: 10,
    },
    {
      fullReductionId: 2,
      fullConditionPrice: 500,
      fullReductionAmount: 30,
    },
    {
      fullReductionId: 3,
      fullConditionPrice: 1000,
      fullReductionAmount: 100,
    },
  ],
  administrators: {
    administratorsId: 1,
    nickname: "管理员",
    phone: "13359074748",
    password: "123456",
  },
  notice: {
    noticeId: 1,
    noticeContent:
      "满300减10，满500减30，满1000减100，满200以上即可专人送货上门、满100可顺路送货上门（仅限合肥市），若系统出现卡顿，刷新页面即可恢复正常",
  },
});
const getSystemInfo = async () => {
  const res = await getsysteminfoApi();
  console.log(res);
  systemInfo.value = res.data.data;
  tel.value = res.data.data.administrators.phone;
  tel.name = res.data.data.administrators.nickname;
};

// 编辑站点信息
const show1 = ref(false);
const websitepath = ref("");
const editwebsite = () => {
  websitepath.value = systemInfo.value.website.websitePath;
  show1.value = true;
};
const editwebsiteBtn = async () => {
  const res = await editsiteinfoApi(websitepath.value);
  getSystemInfo();
  show1.value = false;
};

// 编辑通知信息
const show2 = ref(false);
const noticeContent = ref("");

const editNotice = () => {
  noticeContent.value = systemInfo.value.notice.noticeContent;
  show2.value = true;
};
const editNoticeBtn = async () => {
  const res = await editnoticeinfoApi(noticeContent.value);
  getSystemInfo();
  show2.value = false;
};

// 编辑登录密码
const show3 = ref(false);
const password = ref("");
const editPassword = () => {
  password.value = systemInfo.value.administrators.password;
  show3.value = true;
};
const editPasswordBtn = async () => {
  const res = await editpasswordApi(password.value);
  getSystemInfo();
  show3.value = false;
};

// 去满减规则页面
const goReduction = () => {
  console.log("去满减规则页面");
  router.push({
    path: "/areduction",
  });
};

// 退出登录
const logout = () => {
  useAdmin.clearAdminInfo;
  router.replace({
    path: "/login",
  });
};

onMounted(() => {
  getSystemInfo();
});
const onEdit = () => {
  console.log("点击了");
};
</script>

<template>
  <div class="system">
    <van-contact-card type="edit" :tel="tel" :name="name" @click="onEdit" />
    <van-cell
      is-link
      icon="shop-o"
      :label="systemInfo.website.websitePath"
      @click="editwebsite"
    >
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">站点信息</span>
      </template>
    </van-cell>
    <van-cell
      is-link
      icon="shop-o"
      :label="systemInfo.notice.noticeContent"
      @click="editNotice"
    >
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">通知管理</span>
      </template>
    </van-cell>
    <van-cell is-link icon="shop-o" @click="goReduction">
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">满减规则</span>
      </template>
    </van-cell>

    <van-cell
      is-link
      icon="shop-o"
      :label="systemInfo.administrators.password"
      @click="editPassword"
    >
      <!-- 使用 title 插槽来自定义标题 -->
      <template #title>
        <span class="custom-title">登录密码</span>
      </template>
    </van-cell>
    <!-- 退出登录 -->
    <div class="button">
      <van-button type="danger" @click="logout">退出登录</van-button>
    </div>
  </div>

  <!-- 编辑站点信息 -->
  <van-dialog
    v-model:show="show1"
    title="编辑站点信息"
    show-cancel-button
    @confirm="editwebsiteBtn"
  >
    <van-cell-group inset>
      <van-field
        v-model="websitepath"
        rows="2"
        autosize
        type="textarea"
        placeholder="请输入站点信息"
      />
    </van-cell-group>
  </van-dialog>

  <!-- 编辑通知信息 -->
  <van-dialog
    v-model:show="show2"
    title="编辑通知信息"
    show-cancel-button
    @confirm="editNoticeBtn"
  >
    <van-cell-group inset>
      <van-field
        v-model="noticeContent"
        rows="5"
        autosize
        type="textarea"
        placeholder="请输入通知信息信息"
      />
    </van-cell-group>
  </van-dialog>

  <!-- 编辑通知信息 -->
  <van-dialog
    v-model:show="show3"
    title="编辑登录密码"
    show-cancel-button
    @confirm="editPasswordBtn"
  >
    <van-cell-group inset>
      <van-field
        v-model="password"
        rows="2"
        autosize
        type="textarea"
        placeholder="请输入登录密码"
      />
    </van-cell-group>
  </van-dialog>
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
