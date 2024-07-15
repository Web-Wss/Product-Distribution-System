# 商品分销系统（PDS）——（优化中）

### 文件说明

- PDS：项目前端文件目录
- PDS_API：项目后端文件目录
- PDS.sql：数据库文件

### 项目简介

商品分销系统是一个基于Vue3+Pinia+SpringBoot+MySQL开发的电商分销系统，提供了一个多端界面，包含用户端、管理员端和分销员端。

### 技术栈

- 前端：Vue3+TS+Pinia &&（echarts，ant-design-vue，vant）
- 后端：SpringBoot+MyBatis+MyBatisPlus+MySQL &&（Redis）

### 功能特点

- 用户端
  - 搜索商品、查看商品、商品加购
  - 查看购物车、修改购物车、提交订单、生成订单
  - 查看订单、收货地址
  - ……
- 管理员端
  - 经营看板、一周销售额统计
  - 商品中心、管理商品
  - 用户中心、管理用户
  - 分销管理、销售排名
  - 交易中心、交易管理
  - 系统管理、站点信息、通知管理、满减规则、登录密码
  - ……
- 分销员端
  - 数据看板、一周销售额统计
  - 订单管理
  - 营销推广、分销金额、佣金、推广码、分销订单、佣金明细
  - 个人信息、修改密码
  - ……

### 如何运行

- 克隆该仓库到本地
- 使用IDE打开项目
- 后端配置数据库连接：打开application.yml文件，配置数据库连接信息

```properties
datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/pds
    username: you-username
    password: you-password
```

- 运行后端项目：端口：8080
- 运行前端项目：端口：5173

访问应用程序：浏览器中输入：http://localhost:5173访问

**后台：**

用户登录：http://localhost:5173/#/login

分销商登录：http://localhost:5173/#/dlogin

管理员登录：http://localhost:5173/#/alogin

### 贡献者

- <a href="https://github.com/Web-Wss" target="_blank">爽爽同学</a> — 主要开发者

### 联系方式

如有任何疑问或建议，欢迎联系项目主要开发者：

- 电子邮箱：1306822660@qq.com