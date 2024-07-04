// 登录相关api
import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 用户登录
interface LoginParams {
  username: string;
  phone: string;
}
interface LoginResponse {
  code: number;
  data: {
    token: string;
    userInfo: {}
  };
  msg: string;
}
export function userLoginApi(data: LoginParams):AxiosPromise<LoginResponse> {
  return request({
    url: "/login/userlogin",
    method: "post",
    data,
  });
}