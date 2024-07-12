import { RouteRecordRaw } from "vue-router";

const Home = () => import("@/views/Home/index.vue")
const Cart = () => import("@/views/Cart/index.vue")
const My = () => import("@/views/My/index.vue")
const Detail = () => import("@/views/Detail/index.vue")
const Login = () => import("@/views/Login/index.vue")
const Address = () => import("@/views/My/components/Address/index.vue")
const Order = () => import("@/views/My/components/Order/index.vue")
const OrderDetail = () => import("@/views/My/components/OrderDetail/index.vue")
const PublishOrder = () => import("@/views/Cart/components/PublishOrder/index.vue")

// 分销员
const Distributor = () => import("@/views/Distributor/index.vue")
const DData = () => import("@/views/Distributor/Data/index.vue")
const DOrder = () => import("@/views/Distributor/Order/index.vue")
const DPromotion = () => import("@/views/Distributor/Promotion/index.vue")
const DMy = () => import("@/views/Distributor/My/index.vue")
const DLogin = () => import("@/views/Distributor/Login/index.vue")
const CommissionDetail = () => import("@/views/Distributor/Promotion/components/CommissionDetail/index.vue")
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
    path: "/publishOrder",
    name: "PublishOrder",
    component:PublishOrder,
    meta: {
      title: "订单结算"
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
  },

  // 分销员
  {
    path: "/dlogin",
    name: "DLogin",
    component:DLogin,
    meta: {
      title: "分销商登录",
      keepAlive: true,
    }
  },
  {
    path: "/distributor",
    name: "Distributor",
    component:Distributor,
    meta: {
      title: "分销后台",
      keepAlive: true,
    },
    children: [
      {
        path: "/distributor",
        name: "DData",
        component:DData,
        meta: {
          title: "分销数据",
          keepAlive: true,
        }
      },
      {
        path: "/dorder",
        name: "DOrder",
        component:DOrder,
        meta: {
          title: "订单管理",
          keepAlive: true,
        }
      },
      {
        path: "/dpromotion",
        name: "DPromotion",
        component:DPromotion,
        meta: {
          title: "营销推广",
          keepAlive: true,
        }
      },
      {
        path: "/commissiondetail",
        name: "CommissionDetail",
        component:CommissionDetail,
        meta: {
          title: "佣金明细",
          keepAlive: true,
        }
      },
      {
        path: "/dmy",
        name: "DMy",
        component:DMy,
        meta: {
          title: "个人信息",
          keepAlive: true,
        }
      },
    ]
  },
];

export default routes;