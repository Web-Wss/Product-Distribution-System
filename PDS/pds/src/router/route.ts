import { RouteRecordRaw } from "vue-router";

const Home = () => import("@/views/Home/index.vue")
const Cart = () => import("@/views/Cart/index.vue")
const My = () => import("@/views/My/index.vue")
const Detail = () => import("@/views/Detail/index.vue")
const Login = () => import("@/views/Login/index.vue")
const Address = () => import("@/views/My/components/Address/index.vue")
const Order = () => import("@/views/My/components/Order/index.vue")
const OrderDetail = () => import("@/views/My/components/OrderDetail/index.vue")
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component:Home,
    meta: {
      title: "首页",
      keepAlive: true,
    }
  },
  {
    path: "/cart",
    name: "Cart",
    component:Cart,
    meta: {
      title: "购物车"
    }
  },
  {
    path: "/my",
    name: "My",
    component:My,
    meta: {
      title: "我的"
    }
  },
  {
    path: "/address",
    name: "Address",
    component:Address,
    meta: {
      title: "地址管理"
    }
  },
  {
    path: "/order",
    name: "Order",
    component:Order,
    meta: {
      title: "我的订单"
    }
  },
  {
    path: "/orderDetail",
    name: "OrderDetail",
    component:OrderDetail,
    meta: {
      title: "订单详情"
    }
  },
  {
    path: "/detail",
    name: "Detail",
    component:Detail,
    meta: {
      title: "详情"
    }
  },
  {
    path: "/login",
    name: "Login",
    component:Login,
    meta: {
      title: "登录"
    }
  }
];

export default routes;