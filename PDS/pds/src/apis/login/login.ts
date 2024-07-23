// 登录相关api
import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 用户登录
interface LoginParams {
  username: string;
  phone: string;
}

export function userLoginApi(data: LoginParams):AxiosPromise<any> {
  return request({
    url: "/login/userlogin",
    method: "post",
    data,
  });
}