<script setup></script>

<template>
  <div class="promotion">
    <div class="header">
      <div class="item">
        分销订单总金额
        <div class="span">￥1000</div>
      </div>
      <div class="item">
        分销总佣金
        <div class="span">￥100</div>
      </div>
      <div class="item">
        可提现金额
        <div class="span">￥10</div>
      </div>
      <div class="item">
        已提现金额
        <div class="span">￥90</div>
      </div>
    </div>
    <!-- 二维码 -->
    <div class="img">
      <van-image
        width="200"
        height="200"
        src="https://img2.baidu.com/it/u=2030945992,3965956328&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
      />
      <div class="span">推广码</div>
    </div>

    <!--  -->
    <van-cell-group>
      <van-cell title="分销订单" is-link />
      <van-cell title="佣金明细" is-link />
    </van-cell-group>
  </div>
</template>

<style scoped lang="scss">
.header {
  // width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  color: white;
  text-align: center;
  padding: 0.6rem;
  font-size: 1.2rem;
  .item {
    padding-top: 1rem;
    border-radius: 10px;
    .span {
      padding: 0.6rem;
    }
  }
  .item:nth-child(1) {
    margin-top: 1rem;
    width: 48%;
    background-color: #fd6d6d;
  }
  .item:nth-child(2) {
    margin-top: 1rem;
    width: 48%;
    background-color: #00ceff;
  }
  .item:nth-child(3) {
    margin-top: 1rem;
    width: 48%;
    background-color: #379ffe;
  }
  .item:nth-child(4) {
    margin-top: 1rem;
    width: 48%;
    background-color: #00cf6d;
  }
}

.img {
  width: 100%;
  text-align: center;
  color: #00cf6d;
  font-weight: 600;
  padding-bottom: 1rem;
}
</style>
