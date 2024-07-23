import axios from "axios"
import storage from "./storage"

// 创建axios实例
const request = axios.create({
  baseURL: "/api",
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(config => {
  const user = storage.get("user")
  const distributor = storage.get("distributor")
  const admin = storage.get("admin")
  // 用户登录
  if(user != null && distributor == null && admin == null){
    config.headers.Authorization = user.token
    config.headers.Role = "user"
  }
  // 分销商登录
  if(distributor != null && user == null && admin == null){
    config.headers.Authorization = distributor.token
    config.headers.Role = "distributor"
  }
  // 管理员登录
  if(admin != null && user == null && distributor == null){
    config.headers.Authorization = admin.token
    config.headers.Role = "administrators"
  }
  // 在发送请求之前做些什么
  return config
}, (error) => {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 响应拦截器
request.interceptors.response.use(response => {

  // 对响应数据做点什么
  return response
}, (error) => {
  // 对响应错误做点什么
  return Promise.reject(error)
})

export default request;