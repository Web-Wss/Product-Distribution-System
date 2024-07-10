import App from './App.vue'
import { createApp } from 'vue'

import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

// 路由
import router from "@/router/index"
import { createPinia } from 'pinia'

const app = createApp(App)
const pinia = createPinia()
app.use(pinia)
pinia.use(piniaPluginPersistedstate)
app.use(router)

app.mount('#app')