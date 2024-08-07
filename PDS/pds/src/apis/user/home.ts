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

// 获取产品列表根据分类id
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

export function getGoodsDetailByIdApi(goodsId: number): AxiosPromise<any> {
  return request({
    url: "/home/getgoodsdetailbyid",
    method: "POST",
    params: {
      goodsId
    }
  });
}

// 搜索商品，模糊搜索
export function searchGoodsApi(goodsName: string): AxiosPromise<any> {
  return request({
    url: "/home/searchgoods",
    method: "POST",
    params: {
      goodsName
    }
  });
}

// 添加商品到购物车
interface AddGoodsToCart {
  userId: number,
  goodsId:number,
  // goodsNum:number
  // remainingInventory:number
}
export function addGoodsToCartApi(data: AddGoodsToCart): AxiosPromise<any> {
  return request({
    url: "/goods/addgoodstocart",
    method: "POST",
    data
  });
}

// 获取购物车列表根据用户id
export function getCarListByUserIdApi(userId: number): AxiosPromise<any> {
  return request({
    url: "/goods/getcartlistbyuserid",
    method: "POST",
    params: {
      userId
    }
  });
}

// 获取购物车总价根据用户id
export function getCartTotalPriceByUserIdApi(userId: number): AxiosPromise<any> {
  return request({
    url: "/goods/getcarttotalpricebyuserid",
    method: "POST",
    params: {
      userId
    }
  });
}

// 修改购物车信息
interface UpdateCartInfo {
  cartId: number,
  goodsNum: number,
  remainingInventory: number
}
export function updateCartInfoApi(data: UpdateCartInfo): AxiosPromise<any> {
  return request({
    url: "/goods/updatecartinfo",
    method: "POST",
    data
  });
}

// 删除购物车信息根据购物车id
export function deleteCartInfoByCartIdApi(cartId: number): AxiosPromise<any> {
  return request({
    url: "/goods/deletecartbycartid",
    method: "POST",
    params: {
      cartId
    }
  });
}

// 获取满减规则
export function getFullReductionRuleApi(): AxiosPromise<any> {
  return request({
    url: "/goods/getreductionlist",
    method: "GET",
  });
}

// 获取默认收获地址
export function getDefaultAddressApi(userId:number): AxiosPromise<any> {
  return request({
    url: "/goods/getdefaultaddress",
    method: "GET",
    params: {
      userId
    }
  });
}

// 获取地址列表
export function getAddressListApi(userId:number): AxiosPromise<any> {
  return request({
    url: "/user/getaddresslist",
    method: "GET",
    params: {
      userId
    }
  });
}

// 新增地址
export function addAddressApi(data:object): AxiosPromise<any> {
  return request({
    url: "/user/addaddressinfo",
    method: "POST",
    data
  });
}

// 编辑地址信息
export function editAddressApi(data:object): AxiosPromise<any> {
  return request({
    url: "/user/updateaddressinfo",
    method: "POST",
    data
  });
}

// 删除地址信息
export function deleteAddressApi(addressId:number): AxiosPromise<any> {
  return request({
    url: "/user/deleteaddressinfo",
    method: "POST",
    params: { addressId }
  });
}

// 获取我的订单列表根据参数
export function getOrderListApi(userId:number,orderStatus:number): AxiosPromise<any> {
  return request({
    url: "/user/getmyorderlistbyparam",
    method: "POST",
    params: { userId,orderStatus }
  });
}

// 获取订单详情根据订单id
export function getOrderDetailApi(ordersId:number): AxiosPromise<any> {
  return request({
    url: "/user/getorderdetailbyorderid",
    method: "POST",
    params: { ordersId }
  });
}

// 获取生成订单信息根据用户id

export function getCartListByUserIdAndSelectedStatusApi(userId:number): AxiosPromise<any> {
  return request({
    url: "/goods/getcartlistbyuseridandselectedstatus",
    method: "POST",
    params: { userId }
  });
}

// 生成订单
export function createOrderApi(data:object): AxiosPromise<any> {
  return request({
    url: "/user/generateorder",
    method: "POST",
    data
  });
}
