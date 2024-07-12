import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 分销商登录
interface DistributorLoginParams {
  phone: string;
  password: string;
}
export function distributorLoginApi(data: DistributorLoginParams):AxiosPromise<any> {
  return request({
    url: "/distributor/login",
    method: "post",
    data,
  });
}

// 获取数据看板数据
export function getDataBoardApi(distributorId:number): AxiosPromise<any> {
  return request({
    url: "/distributor/getdashboarddata",
    method: "get",
    params: { distributorId },
  });
}

// 获取订单列表根据分销商id
export function getOrderListByDistributorIdApi(distributorId:number): AxiosPromise<any> {
  return request({
    url: "/distributor/getorderlistbydistributorid",
    method: "get",
    params: { distributorId },
  });
}

// 获取营销推广信息
export function getMarketingPromotionApi(distributorId:number): AxiosPromise<any> {
  return request({
    url: "/distributor/getmarketingpromotioninfo",
    method: "get",
    params: { distributorId },
  });
}

// 获取佣金明细
export function getCommissionDetailApi(distributorId:number): AxiosPromise<any> {
  return request({
    url: "/distributor/getcommissiondetail",
    method: "get",
    params: { distributorId },
  });
}

// 修改密码
interface ChangePasswordParams {
  oldPassword: string;
  newPassword: string;
  newDPassword:string
}
export function changePasswordApi(data: ChangePasswordParams):AxiosPromise<any> {
  return request({
    url: "/distributor/updatepassword",
    method: "post",
    data,
  });
}