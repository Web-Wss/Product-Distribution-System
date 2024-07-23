// 导入router所需的方法
import { createRouter,createWebHashHistory } from 'vue-router'

// 导入路由页面配置
import routes from './route'
import storage from '@/utils/storage'

// 路由参数配置
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  // 判断前往的路由
  const ToUser = ["/orderDetail","/","/cart","/my","/detail","/address","/order","/publishorder"]
  const ToDistributor = ["/orderDetail","/distributor","/ddata","/dorder","/dpromotion","/dmy","/commissiondetail"]
  const ToAdmin = ["/orderDetail","/admin","/adata","/agoods","/auser","/auserorder","/adistributor","/aorder","/asystem","/areduction"]
  // 设置路由守卫，在进入页面之前，判断有token才能进行，否则跳转登录页
  // console.log(to);
  let token = "";
  // 判断进入的属于哪个角色的页面
  if (ToUser.includes(to.path)){
    // 要去用户相关页面
     token = storage.get("user") !== null ? storage.get("user").token : ""
  }
  if (ToDistributor.includes(to.path)){
    // 要去分销商相关页面
     token = storage.get("distributor") !== null ? storage.get("distributor").token : ""
  }
  if (ToAdmin.includes(to.path)){
    // 要去管理员相关页面
     token = storage.get("admin") !== null ? storage.get("admin").token : ""
  }
  
  // 判断要去的路由有没有requireAuth
  if (to.matched.some((r) => r.meta.requireAuth)) {
    if (token != "") {
      next(); //有token，进行request请求，后台还会验证token
    } else {
      router.push({
        path: "/login",
        // 将刚刚要去的路由path作为参数，方便直接跳转
        // query: { redirect: to.fullPath },
      });
    }
  } else {
    next(); //如果无需token，放行
  }
})

// 全局后置守卫
router.afterEach((to, from) => {
  // 设置页面标题
  document.title = to.meta.title as string
})

export default router