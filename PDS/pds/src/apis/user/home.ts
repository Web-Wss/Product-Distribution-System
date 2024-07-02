// 首页相关api
import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 获取首页通知信息
interface HomeNotice {
  noticeId: number;
  noticeContent: string;
}

export function getHomeNotice(): AxiosPromise<HomeNotice> {
  return request({
    url: "/home/test",
    method: "get",
  });
}