package com.example.pds_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.*;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private CartService cartService;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private ReductionMapper reductionMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderListMapper orderListMapper;

//    添加商品到购物车
    @PostMapping("/addgoodstocart")
    public Result addGoodsToCart(@RequestBody AddGoodsToCartDTO addGoodsToCartDTO) {
        Integer integer = cartService.addGoodsToCart(addGoodsToCartDTO);
        if (integer == 1) {
            return Result.success("添加成功");
        }
        if (integer == 0) {
            return Result.fail("添加失败");
        }
        return Result.fail("添加失败");
    }

//    获取购物车列表根据用户id
    @PostMapping("/getcartlistbyuserid")
    public Result getCartListByUserId(@RequestParam("userId") Integer userId) {
        List<Cart> carts = cartService.getCartListByUserId(userId);
        return Result.success(carts);
    }
//    获取购物车总价根据用户id
    @PostMapping("/getcarttotalpricebyuserid")
    public Result getCartTotalPriceByUserId(@RequestParam("userId") Integer userId) {
        Integer cartTotalPriceByUserId = cartService.getCartTotalPriceByUserId(userId);
        return Result.success(cartTotalPriceByUserId);
    }

//    修改购物车信息
    @PostMapping("/updatecartinfo")
    public Result updateCartInfo(@RequestBody CartDTO cartDTO) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        Cart cart = new Cart();
        cart.setGoodsNumber(cartDTO.getGoodsNumber());
        cart.setGoodsSelectedStatus(cartDTO.getGoodsSelectedStatus());
        cart.setCartId(cartDTO.getCartId());
        queryWrapper.eq("cart_id", cartDTO.getCartId());
        int update = cartMapper.update(cart, queryWrapper);
        if (update == 1) {
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }

//    删除购物车根据购物车id
    @PostMapping("/deletecartbycartid")
    public Result deleteCartByCartId(@RequestParam("cartId") Integer cartId) {
        int i = cartMapper.deleteById(cartId);
        if (i == 1) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }


//    获取生成订单信息根据用户id
    @PostMapping("/getcartlistbyuseridandselectedstatus")
    public Result getCartListByUserIdAndSelectedStatus(@RequestParam("userId") Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        Address address = new Address();
//        获取订单商品列表
        List<Cart> carts = cartService.getCartListByUserIdAndSelectedStatus(userId);
//        获取订单金额
        Integer cartTotalPriceByUserId = cartService.getCartTotalPriceByUserId(userId);
//        获取默认收获地址id和信息
        address = addressMapper.selectOne(new QueryWrapper<Address>().eq("user_id",userId).eq("is_default",1));
        map.put("orderGoodsList",carts);
        map.put("orderTotalPrice",cartTotalPriceByUserId);
        map.put("address",address);
        return Result.success("生成订单信息",map);
    }

//    获取满减规则列表
    @GetMapping("/getreductionlist")
    public Result getReductionList() {
        List<Reduction> reductions = reductionMapper.selectList(null);
        return Result.success("获取满减规则列表",reductions);
    }


//    生成订单
    @PostMapping("/generateorder")
    public Result generateOrder(@RequestBody GenerateOrderDTO generateOrderDTO) {
//        设置订单信息
        Orders orders = new Orders();
        Integer ordersId;
        orders.setUserId(generateOrderDTO.getUserId());
        orders.setAddressId(generateOrderDTO.getAddressId());
        orders.setOrderTotalPrice(generateOrderDTO.getOrderTotalPrice());
        orders.setOrderDiscountPrice(generateOrderDTO.getOrderDiscountPrice());
        orders.setOrderActualPayment(generateOrderDTO.getOrderActualPayment());
        orders.setOrderRemarks(generateOrderDTO.getRemark());
        orders.setDistributorId(generateOrderDTO.getDistributorId());
        System.out.println(orders);
        int insert = ordersMapper.insert(orders);
        if (insert == 1) {
            ordersId = orders.getOrdersId();
        }else {
            return Result.fail("生成订单失败");
        }
//        设置订单列表信息
        OrderList orderList = new OrderList();
        Cart cart1 = new Cart();
        for (Cart cart : generateOrderDTO.getOrderGoodsList()) {
            orderList.setOrdersId(ordersId);
            orderList.setGoodsId(cart.getGoodsId());
            orderList.setGoodsPrice(cart.getGoods().getGoodsPrice());
            orderList.setGoodsNumber(cart.getGoodsNumber());
            orderList.setPriceSubtotal(cart.getGoods().getGoodsPrice().multiply(new BigDecimal(cart.getGoodsNumber())));
            orderListMapper.insert(orderList);
//              删除购物车
            cartMapper.deleteById(cart.getCartId());
//            判断商品库存和生成订单数量比较，如果库存大于生成订单数量，则不做改变，如果库存小于等于生成订单数量，则将购物车中商品设置为不可勾选，并且已勾选的商品设为未勾选
            if(cart.getGoods().getRemainingInventory() <= cart.getGoodsNumber()) {
                cart1.setIsSelected(0);
                cart1.setGoodsSelectedStatus(0);
                cartMapper.update(cart1,new QueryWrapper<Cart>().eq("goods_id",cart.getGoodsId()));
            }
        }

        return Result.success("生成订单成功");
    }





}
