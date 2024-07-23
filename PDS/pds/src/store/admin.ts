import storage from "@/utils/storage";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useAdminStore = defineStore("admin",()=>{

  // 管理管理员数据state
  const token = ref("")
  const adminInfo = ref({})

  const setAdminInfo = (token1: string ,adminInfo1: object) => {
    token.value = token1
    adminInfo.value = adminInfo1
  }

  // 退出时清除管理员信息
  const clearAdminInfo = () => {
    storage.removeAll()
    token.value = ""
    adminInfo.value = {}
    // console.log("管理员信息清除了");
  }

  return {
    token,
    adminInfo,
    setAdminInfo,
    clearAdminInfo
  }

},{persist: true})