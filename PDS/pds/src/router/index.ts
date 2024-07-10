// 导入router所需的方法
import { createRouter, createWebHistory,createWebHashHistory } from 'vue-router'

// 导入路由页面配置
import routes from './route'

// 路由参数配置
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  next()
})

// 全局后置守卫
router.afterEach((to, from) => {
  // 设置页面标题
  document.title = to.meta.title as string
})

export default router