import { RouteRecordRaw } from "vue-router";

const Home = () => import("@/views/Home/index.vue")
const Cart = () => import("@/views/Cart/index.vue")
const My = () => import("@/views/My/index.vue")
const Detail = () => import("@/views/Detail/index.vue")

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
    path: "/detail",
    name: "Detail",
    component:Detail,
    meta: {
      title: "详情"
    }
  }
];

export default routes;