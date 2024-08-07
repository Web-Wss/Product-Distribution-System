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

// 管理员
const Admin = () => import("@/views/Admin/index.vue")
const AData = () => import("@/views/Admin/components/Data/index.vue")
const AGoods = () => import("@/views/Admin/components/Goods/index.vue")
const AUser = () => import("@/views/Admin/components/User/index.vue")
const AUserOrder = () => import("@/views/Admin/components/User/components/UserOrder/index.vue")
const ADistributor = () => import("@/views/Admin/components/Distributor/index.vue")
const AOrder = () => import("@/views/Admin/components/Order/index.vue")
const ASystem = () => import("@/views/Admin/components/System/index.vue")
const AReduction = () => import("@/views/Admin/components/System/components/Reduction/index.vue")
const ALogin = () => import("@/views/Admin/components/Login/index.vue")
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component:Home,
    meta: {
      title: "首页",
      requireAuth: false,
    }
  },
  {
    path: "/cart",
    name: "Cart",
    component:Cart,
    meta: {
      title: "购物车",
      requireAuth: true
    }
  },
  {
    path: "/publishOrder",
    name: "PublishOrder",
    component:PublishOrder,
    meta: {
      title: "订单结算",
      requireAuth: true

    }
  },
  {
    path: "/my",
    name: "My",
    component:My,
    meta: {
      title: "我的",
      requireAuth: true

    }
  },
  {
    path: "/address",
    name: "Address",
    component:Address,
    meta: {
      title: "地址管理",
      requireAuth: true

    }
  },
  {
    path: "/order",
    name: "Order",
    component:Order,
    meta: {
      title: "我的订单",
      requireAuth: true

    }
  },
  {
    path: "/orderDetail",
    name: "OrderDetail",
    component:OrderDetail,
    meta: {
      title: "订单详情",
      requireAuth: false

    }
  },
  {
    path: "/detail",
    name: "Detail",
    component:Detail,
    meta: {
      title: "详情",
      requireAuth: false

    }
  },
  {
    path: "/login",
    name: "Login",
    component:Login,
    meta: {
      title: "登录",
      requireAuth: false
    }
  },

  // 分销员
  {
    path: "/dlogin",
    name: "DLogin",
    component:DLogin,
    meta: {
      title: "分销商登录",
      requireAuth: false
    }
  },
  {
    path: "/distributor",
    name: "Distributor",
    component:Distributor,
    meta: {
      title: "分销后台",
      requireAuth: true
    },
    children: [
      {
        path: "/distributor",
        name: "DData",
        component:DData,
        meta: {
          title: "分销数据",
      requireAuth: true
        }
      },
      {
        path: "/dorder",
        name: "DOrder",
        component:DOrder,
        meta: {
          title: "订单管理",
      requireAuth: true
        }
      },
      {
        path: "/dpromotion",
        name: "DPromotion",
        component:DPromotion,
        meta: {
          title: "营销推广",
      requireAuth: true
        }
      },
      {
        path: "/commissiondetail",
        name: "CommissionDetail",
        component:CommissionDetail,
        meta: {
          title: "佣金明细",
      requireAuth: true
        }
      },
      {
        path: "/dmy",
        name: "DMy",
        component:DMy,
        meta: {
          title: "个人信息",
      requireAuth: true
        }
      },
    ]
  },
  {
    path: "/alogin",
    name: "ALogin",
    component:ALogin,
    meta: {
      title: "管理员登陆",
      requireAuth: false
    }
  },
  {
    path: "/admin",
    name: "Admin",
    component:Admin,
    meta: {
      title: "管理员后台",
      requireAuth: true
    },
    children: [
      {
        path: "/admin",
        name: "AData",
        component:AData,
        meta: {
          title: "经营看板",
      requireAuth: true
        }
      },
      {
        path: "/agoods",
        name: "AGoods",
        component:AGoods,
        meta: {
          title: "商品中心",
      requireAuth: true
        }
      },
      {
        path: "/auser",
        name: "AUser",
        component:AUser,
        meta: {
          title: "用户中心",
      requireAuth: true
        }
      },
      {
        path: "/auserorder",
        name: "AUserOrder",
        component:AUserOrder,
        meta: {
          title: "用户订单",
      requireAuth: true
        }
      },
      {
        path: "/adistributor",
        name: "ADistributor",
        component:ADistributor,
        meta: {
          title: "分销管理",
      requireAuth: true
        }
      },
      {
        path: "/aorder",
        name: "AOrder",
        component:AOrder,
        meta: {
          title: "交易中心",
      requireAuth: true
        }
      },
      {
        path: "/asystem",
        name: "ASystem",
        component:ASystem,
        meta: {
          title: "系统管理",
      requireAuth: true
        }
      },
      {
        path: "/areduction",
        name: "AReduction",
        component:AReduction,
        meta: {
          title: "满减规则",
      requireAuth: true
        }
      },
   
    ]
  },
];

export default routes;