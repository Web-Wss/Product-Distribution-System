// 首页相关api
import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 获取首页通知信息
interface HomeNotice {
  noticeId: number;
  noticeContent: string;
}
export function getHomeNoticeApi(): AxiosPromise<HomeNotice> {
  return request({
    url: "/home/getnotice",
    method: "get",
  });
}

// 获取产品分类列表
interface GoodsClassification {
  goodsClassificationId: number;
  goodsClassificationName: string;
}
export function getGoodsClassificationListApi(): AxiosPromise<GoodsClassification> {
  return request({
    url: "/home/getgoodsclassificationlist",
    method: "get",
  });
}