# 商品分销系统（PDS）——开发阶段

### 文件说明

- PDS：项目前端文件目录
- PDS_API：项目后端文件目录

### 项目简介

商品分销系统是一个基于Vue3+Pinia+SpringBoot+MySQL开发的电商分销系统，提供了一个多端界面，包含用户端、管理员端和分销员端。

### 功能特点

- 用户端
  - 查看商品
  - 收藏商品
  - 加入购物车
  - 生成订单
  - ……
- 管理员端
  - 用户管理
  - 商品管理
  - 订单管理
  - 分销员管理
  - ……
- 分销员端
  - 商品管理
  - 订单管理
  - ……

### 技术栈

- 前端：Vue3+Pinia
- 后端：SpringBoot+MyBatis+MySQL

### 如何运行

- 克隆该仓库到本地
- 使用IDE打开项目
- 后端配置数据库连接：打开application.yml文件，配置数据库连接信息

```properties
datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/pds
    username: username
    password: password
```

- 运行后端项目：端口：8080
- 运行前端项目：端口：5173

访问应用程序：浏览器中输入：http://localhost:5173访问

### 贡献者

- <a href="https://github.com/Web-Wss" target="_blank">爽爽同学</a> — 主要开发者

### 联系方式

如有任何疑问或建议，欢迎联系项目主要开发者：

- 电子邮箱：1306822660@qq.com