import storage from "@/utils/storage";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useDistributorStore = defineStore("distributor",()=>{

  // 管理分销商数据state
  const token = ref("")
  const distributorInfo = ref({})

  const setDistributoeInfo = (token1: string ,distributorInfo1: object) => {
    token.value = token1
    distributorInfo.value = distributorInfo1
  }

  // 退出时清除分销商信息
  const clearDistributorInfo = () => {
    storage.removeAll()
    token.value = ""
    distributorInfo.value = {}
    console.log("分销商信息清除了");
  }

  return {
    token,
    distributorInfo,
    setDistributoeInfo,
    clearDistributorInfo
  }

},{persist: true})