<script setup>
import { getDataBoardApi } from "@/apis/distributor";
import * as echarts from "echarts";
import { onMounted, ref } from "vue";
import { useDistributorStore } from "@/store/distributor";
const distributorStore = useDistributorStore();
let myChart = ref();
let option = ref({});

// 日期
const date = ref([]);
const price = ref([]);
const orderNumber = ref(0);
const orderTotalPrice = ref(0);

const getDataBoard = async () => {
  const res = await getDataBoardApi(
    distributorStore.distributorInfo.distributorId
  );
  // console.log(res);
  if (res.data.code == 200) {
    date.value = res.data.data.date;
    price.value = res.data.data.price;
    orderNumber.value = res.data.data.orderNumber;
    orderTotalPrice.value = res.data.data.orderTotalPrice;
    init();
  }
};

onMounted(() => {
  getDataBoard();
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
      text: "一周销售金额",
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
      data: date,
      name: "日期",
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
        data: price,
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
  <div id="main" class="echart-style"></div>
  <div class="info">
    <div class="item">
      订单数量
      <div class="span">{{ orderNumber }}个</div>
    </div>
    <div class="item">
      订单总额
      <div class="span">{{ orderTotalPrice }}元</div>
    </div>
  </div>
</template>

<style scoped lang="scss">
#main {
  margin: 0 auto;
  width: 90%;
  height: 50vh;
}

.info {
  width: 100%;
  display: flex;
  justify-content: space-around;
  .item {
    font-weight: 600;
    border-radius: 10px;
    padding: 0.2rem;
    color: white;
    text-align: center;
    font-size: 1.6rem;
    width: 40%;
    background-color: #857dff;
    .span {
      padding: 0.2rem;
      font-size: 1.2rem;
      text-align: center;
      color: white;
    }
  }
}
</style>
