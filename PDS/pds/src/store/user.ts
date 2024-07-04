import storage from "@/utils/storage";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user",()=>{

  // 管理用户数据state
  const token = ref("")
  const userInfo = ref({})

  const setUserInfo = (token1: string ,userInfo1: object) => {
    token.value = token1
    userInfo.value = userInfo1
  }

  // 退出时清除用户信息
  const clearUserInfo = () => {
    storage.removeAll()
    token.value = ""
    userInfo.value = {}
    console.log("用户信息清除了");
  }

  return {
    token,
    userInfo,
    setUserInfo,
    clearUserInfo
  }

},{persist: true})