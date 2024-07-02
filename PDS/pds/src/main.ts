import { createApp } from 'vue'
import App from './App.vue'
import { getHomeNotice } from './apis/user/home'

getHomeNotice().then(res=>{
  console.log(res);
  
})

createApp(App).mount('#app')
