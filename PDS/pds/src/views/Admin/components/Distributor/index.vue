<script setup>
import * as echarts from "echarts";
import { getdistributorlistApi } from "@/apis/admin";
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
  console.log(res.data.data);
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
    <van-grid :column-num="2" :gutter="10">
      <van-grid-item v-for="item in distributorList" :key="item.distributorId">
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
