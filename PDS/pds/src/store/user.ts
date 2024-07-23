import storage from "@/utils/storage";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user",()=>{

  // 管理用户数据state
  const token = ref("")
  const userInfo = ref({})
  const role = ref("")
  const distributorId = ref(0)

  const setUserInfo = (token1: string ,userInfo1: object,role1: string) => {
    token.value = token1
    userInfo.value = userInfo1
    role.value = role1
  }

  const setDistributorId = (distributorId1: number) => {
    distributorId.value = distributorId1
  }

  const getDistributorId = () => {
    return distributorId.value
  }

  // 退出时清除用户信息
  const clearUserInfo = () => {
    token.value = ""
    userInfo.value = {}
    // console.log("用户信息清除了");
    storage.removeAll()

  }

  return {
    role,
    token,
    userInfo,
    setUserInfo,
    clearUserInfo,
    distributorId,
    setDistributorId,
    getDistributorId
  }

},{persist: true})