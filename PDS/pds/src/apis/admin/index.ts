import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 管理员登录
export function adminloginApi(phone: string,password: string): AxiosPromise<any> {
  return request({
    url: "/administrators/login",
    method: "post",
    params: { phone,password },
  });
}

// 获取数据看板数据
export function getdashboarddataApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getdashboarddata",
    method: "get",
  });
}

// 获取商品列表信息
export function getgoodslistApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getgoodslist",
    method: "GET",
  });
}

// 根据商品id获取商品信息包括剩余库存
export function getgoodsinfoApi(goodsId:number): AxiosPromise<any> {
  return request({
    url: "/administrators/getgoodsinfobyid",
    method: "GET",
    params: { goodsId },
  });
}

// 修改商品信息
interface UpdateGoodsParams {
  goodsId: number, //id
  goodsClassificationId: number, //分类
  goodsName: string, //名称
  goodsPrice: number, //售价
  goodsOldPrice: number, //市场价
  goodsCompany:string, //单位
  goodsTotalInventory: number, //总库存
  remainingInventory: number, //剩余库存
}
export function updateGoodsApi(data: UpdateGoodsParams):AxiosPromise<any> {
  return request({
    url: "/administrators/updategoodsinfo",
    method: "post",
    data,
  });
}

// 获取用户列表
export function getuserlistApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getuserlist",
    method: "get",
  });
}

// 获取分销商列表和分销商排名
export function getdistributorlistApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getdistributorlist",
    method: "get",
  });
}

// 获取订单列表
export function getorderlistApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getorderlist",
    method: "get",
  });
}

// 获取用户列表根据用户id
export function getuserinfobyidApi(userId: number): AxiosPromise<any> {
  return request({
    url: "/administrators/getorderlistbyuserid",
    method: "get",
    params: { userId },
  });
}

// 修改订单状态根据订单id
export function updateorderstatusApi(ordersId: number,orderStatus:number): AxiosPromise<any> {
  return request({
    url: "/administrators/updateorderstatusbyorderid",
    method: "POST",
    params: { ordersId,orderStatus },
  });
}

// 设置订单为已完成
export function setordercompleteApi(ordersId: number,payType:number): AxiosPromise<any> {
  return request({
    url: "/administrators/updateorderstatuscomplete",
    method: "POST",
    params: { ordersId ,payType},
  });
}

// 获取系统相关信息
export function getsysteminfoApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getsysteminfo",
    method: "get",
  });
}

// 编辑站点信息
export function editsiteinfoApi(path: string): AxiosPromise<any> {
  return request({
    url: "/administrators/updatesiteinfo",
    method: "post",
    params: { path },
  });
}

// 编辑通知信息
export function editnoticeinfoApi(noticeContent: string): AxiosPromise<any> {
  return request({
    url: "/administrators/updatenoticeinfo",
    method: "post",
    params: { noticeContent },
  });
}

// 获取满减规则信息
export function getreductionApi(): AxiosPromise<any> {
  return request({
    url: "/administrators/getreductioninfo",
    method: "get",
  });
}

// 编辑满减信息
export function editreductionApi(reductionId: number,fullConditionPrice:number,fullReductionAmount:number): AxiosPromise<any> {
  return request({
    url: "/administrators/updatereductioninfo",
    method: "post",
    params: { reductionId,fullConditionPrice,fullReductionAmount },
  });
}

// 修改登录密码
export function editpasswordApi(password:string): AxiosPromise<any> {
  return request({
    url: "/administrators/updatepassword",
    method: "post",
    params: { password },
  });
}