<script setup>
import * as echarts from "echarts";
import { onMounted, ref } from "vue";
let myChart = ref();
let option = ref({});

onMounted(() => {
  init();
});
const init = () => {
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
      data: ["7/5", "7/6", "7/7", "7/8", "7/9", "7/10", "7/11"],
      name: "日期",
    },
    yAxis: {
      name: "金额",
    },
    series: [
      {
        type: "bar",
        data: [754, 375, 1739, 223, 1208, 979, 2012],
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
      <div class="span">45个</div>
    </div>
    <div class="item">
      订单总额
      <div class="span">8926元</div>
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
