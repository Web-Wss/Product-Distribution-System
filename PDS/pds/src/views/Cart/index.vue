<script setup>
import TabBar from "@/components/TabBar/index.vue";
import { ref } from "vue";

const onSubmit = () => showToast("点击按钮");

// 删除按钮文字
const deleteText = ref("");
// 复选框
const checked = ref(false);
// 计步器
const value = ref(1);
</script>

<template>
  <div class="cart">
    <van-nav-bar fixed title="购物车" :left-text="deleteText" />

    <div class="content">
      <!-- <van-empty description="亲，购物车是空的哦，请加购您心仪的商品" /> -->
      <!-- 购物车列表 -->
      <div class="list" v-for="i in 7" key="i">
        <!-- 复选框 -->
        <div class="check">
          <van-checkbox v-model="checked"></van-checkbox>
        </div>
        <!-- 图片 -->
        <div class="img">
          <img src="http://ywesc-img.webwss.cn/yh/1.png" alt="" />
        </div>
        <div class="info">
          <div class="name">星际导弹（每盒四根）</div>
          <div class="inventory">剩余库存:10</div>
          <div class="price">单价:1</div>
          <div class="smallprice">小计:</div>
        </div>
        <div class="stepper">
          <van-stepper
            v-model="value"
            theme="round"
            button-size="22"
            disable-input
          />
        </div>
      </div>
    </div>

    <van-submit-bar
      :price="3050"
      button-text="提交订单"
      @submit="onSubmit"
      tip="若选购商品总价满300及以上，平台将在生成订单时自动进行满减"
    />
  </div>
  <!-- 底部 -->
  <TabBar />
</template>

<style scoped lang="scss">
.cart {
  width: 100%;
  background-color: #f7f7f7;
  .content {
    margin-top: 46px;
    margin-bottom: 100px;

    width: 100%;
    .list {
      display: flex;
      align-items: center;
      width: 100%;
      background-color: white;
      margin-top: 6px;
      .check {
        padding-left: 10px;
      }
      .img {
        width: 30%;
        img {
          width: 100%;
        }
      }
      .info {
        width: 38%;
        .name {
          font-size: 1.1rem;
        }
        .inventory {
          font-size: 1rem;
          color: #c1c1c1;
        }
        .price {
          color: red;
        }
        .smallprice {
          color: red;
          font-size: 1.3rem;
        }
      }
    }
  }
}
.van-submit-bar {
  bottom: 50px;
}
</style>
