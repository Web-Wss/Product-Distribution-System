<script setup>
import * as echarts from "echarts";
import {
  adddistributorApi,
  deletedistributorApi,
  editdistributorinfoApi,
  getdistributorinfobyidApi,
  getdistributorlistApi,
} from "@/apis/admin";
import { onMounted, ref } from "vue";
let myChart = ref();
let option = ref({});

// 分销商列表
const distributorList = ref([]);
// 展示数据
const distributorIdList = ref([]);
const totalPriceList = ref([]);

// 获取数据
const getData = async () => {
  const res = await getdistributorlistApi();
  // console.log(res.data.data);
  if (res.data.code == 200) {
    distributorList.value = res.data.data.distributorList;
    // 处理展示数据
    res.data.data.distributorRanking.forEach((item) => {
      distributorIdList.value.push(item.distributorId);
      totalPriceList.value.push(item.totalOrderAmount);
    });
    init();
  }
};

// 编辑分销商信息
const show = ref(false);
const distributorInfo = ref({
  distributorId: 2025001,
  nickname: "分销商1",
  phone: "13359074748",
  password: "123",
  status: 1,
  createTime: "2024-07-12 09:16:42",
  commissionRate: 10,
  alreadyWithdrawn: 0,
  totalOrderAmount: null,
  commission: null,
  orderCount: null,
});
const EditDistributorInfo = async (distributorId) => {
  const res = await getdistributorinfobyidApi(distributorId);
  // console.log(res);
  distributorInfo.value = res.data.data;
  show.value = true;
};
const editDistributorBtn = async () => {
  const res = await editdistributorinfoApi(distributorInfo.value);
  if (res.data.code == 200) {
    show.value = false;
    distributorInfo.value = {};
    getData();
    init();
  }
};
// 删除分销商
const deleteDistributorBtn = () => {
  showConfirmDialog({
    title: "删除分销商",
    message: `您将要删除ID为${distributorInfo.value.distributorId}的分销商，该分销商将无法登录系统，请谨慎操作！`,
  })
    .then(() => {
      // on confirm
      const res = deletedistributorApi(distributorInfo.value.distributorId);
      show.value = false;
      getData();
      init();
    })
    .catch(() => {
      // on cancel
    });
  getData();
  init();
};

// 新增分销商信息
const addshow = ref(false);
const add = () => {
  addDistributorInfo.value = {
    nickname: "",
    phone: "",
    password: "",
    commissionRate: 0,
  };
  addshow.value = true;
};
const addDistributorInfo = ref({
  nickname: "",
  phone: "",
  password: "",
  commissionRate: 0,
});

const addDistributorBtn = async () => {
  const res = await adddistributorApi(addDistributorInfo.value);
  getData();
  init();
};

onMounted(() => {
  getData();
  init();
});

const init = () => {
  if (myChart.value) {
    myChart.value.dispose();
  }
  // 基于准备好的dom，初始化echarts实例
  myChart.value = echarts.init(document.getElementById("main"));
  option.value = {
    title: {
      text: "销售排名",
      left: "center",
      top: "10",
      subtext: "单位：元",
      textStyle: {
        color: "#f60",
        fontSize: 18,
        fontWeight: "bold",
      },
    },
    grid: {
      left: 50,
      right: 50,
    },
    xAxis: {
      data: distributorIdList,
      name: "编号",
      axisLabel: {
        // 旋转角度，正值为顺时针
        rotate: 45,
      },
    },
    yAxis: {
      name: "金额",
    },
    series: [
      {
        type: "bar",
        data: totalPriceList,
        label: {
          show: true,
          position: "top",
          formatter: function (data) {
            return data.value + "元";
          },
          color: "red",
          fontSize: 10,
        },
      },
    ],
  };
  myChart.value.setOption(option.value);
};
</script>

<template>
  <div class="distributor">
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      共有{{ distributorIdList.length }}个分销商
    </van-divider>
    <!-- 销售额排行 -->
    <div id="main" class="echart-style"></div>
    <div class="addbtn" style="text-align: center; margin-bottom: 20px">
      <van-button type="primary" @click="add">新增分销商</van-button>
    </div>
    <van-grid :column-num="2" :gutter="10">
      <van-grid-item
        v-for="item in distributorList"
        :key="item.distributorId"
        @click="EditDistributorInfo(item.distributorId)"
      >
        <div class="item id">
          分销商编号:<span class="span"> {{ item.distributorId }}</span>
        </div>
        <div class="item name">
          昵称:<span class="span">{{ item.nickname }}</span>
        </div>
        <div class="item order-num">
          订单数量:<span class="span">{{ item.orderCount }}</span>
        </div>
        <div class="item bl">
          佣金比例:<span class="span">{{ item.commissionRate }}%</span>
        </div>
        <div class="item order-total">
          订单总额:<span class="span">{{ item.totalOrderAmount }}</span>
        </div>
        <div class="item yj">
          分销佣金:<span class="span">{{ item.commission / 100 }}</span>
        </div>
        <div class="item phone">
          电话:<span class="span"> {{ item.phone }}</span>
        </div>
      </van-grid-item>
    </van-grid>
  </div>
  <!-- 分销商编辑 -->
  <van-dialog
    v-model:show="show"
    title="编辑分销商信息"
    show-cancel-button
    confirmButtonText="修改"
    @confirm="editDistributorBtn"
  >
    <van-form>
      <van-cell-group inset>
        <van-field
          disabled
          v-model="distributorInfo.distributorId"
          name="分销商ID"
          label="分销商ID"
          placeholder="分销商ID"
        />
        <van-field
          v-model="distributorInfo.nickname"
          name="昵称"
          label="昵称"
          placeholder="昵称"
          :rules="[{ required: true, message: '请输入昵称' }]"
        />
        <van-field
          v-model="distributorInfo.phone"
          name="手机号"
          label="手机号"
          placeholder="手机号"
          :rules="[{ required: true, message: '请输入手机号' }]"
        />
        <van-field
          v-model="distributorInfo.password"
          name="密码"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请输入密码' }]"
        />
        <van-field
          v-model="distributorInfo.commissionRate"
          name="佣金比例"
          label="佣金比例"
          placeholder="佣金比例"
          :rules="[{ required: true, message: '请输入佣金比例' }]"
        />
        <van-field
          v-model="distributorInfo.alreadyWithdrawn"
          name="已提现佣金"
          label="已提现佣金"
          placeholder="已提现佣金"
          :rules="[{ required: true, message: '请输入已提现佣金' }]"
        />
      </van-cell-group>
    </van-form>
    <div class="deletebtn" style="text-align: center; margin: 1rem">
      <van-button type="danger" @click="deleteDistributorBtn"
        >删除分销商</van-button
      >
    </div>
  </van-dialog>

  <!-- 新增分销商 -->
  <van-dialog
    v-model:show="addshow"
    title="新增分销商"
    show-cancel-button
    confirmButtonText="新增"
    @confirm="addDistributorBtn"
  >
    <van-form>
      <van-cell-group inset>
        <van-field
          v-model="addDistributorInfo.nickname"
          name="昵称"
          label="昵称"
          placeholder="昵称"
          :rules="[{ required: true, message: '请输入昵称' }]"
        />
        <van-field
          v-model="addDistributorInfo.phone"
          name="手机号"
          label="手机号"
          placeholder="手机号"
          :rules="[{ required: true, message: '请输入手机号' }]"
        />
        <van-field
          v-model="addDistributorInfo.password"
          name="密码"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请输入密码' }]"
        />
        <van-field
          v-model="addDistributorInfo.commissionRate"
          name="佣金比例"
          label="佣金比例"
          placeholder="佣金比例"
          :rules="[{ required: true, message: '请输入佣金比例' }]"
        />
      </van-cell-group>
    </van-form>
  </van-dialog>
</template>

<style scoped lang="scss">
#main {
  margin: 0 auto;
  width: 90%;
  height: 50vh;
}
.item {
  font-size: 0.9rem;
  .span {
    color: red;
  }
}
</style>
