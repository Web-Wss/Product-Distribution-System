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

export function getGoodsListByClassificationIdApi(classificationId: number): AxiosPromise<any> {
  return request({
    url: "/home/getgoodslist",
    method: "get",
    params: {
      classificationId
    }
  });
}

// 查询商品详情根据id
interface GoodsDetail {
  goodsId: number,
  goodsClassificationId: number,
  goodsName: string,
  goodsPrice: number,
  goodsOldPrice: number,
  goodsCompany: string,
  goodsTotalInventory: number,
  goodsImg: string,
  goodsVideo: string,
  goodsStatus: number,
  createTime: string,
  isDelete: number
}
export function getGoodsDetailByIdApi(goodsId: number): AxiosPromise<GoodsDetail> {
  return request({
    url: "/home/getgoodsdetailbyid",
    method: "POST",
    params: {
      goodsId
    }
  });
}