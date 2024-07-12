package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.*;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

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

    @Override
    public Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO) {
        Cart cart = new Cart();
        cart.setGoodsId(addGoodsToCartDTO.getGoodsId());
        cart.setGoodsNumber(addGoodsToCartDTO.getGoodsNumber());
        cart.setUserId(addGoodsToCartDTO.getUserId());
        cart.setGoodsSelectedStatus(1);
        Integer integer = 0;
//        根据用户id和商品id查询购物车表中是否已经有该商品，如果有，则更新数量，如果没有，则添加
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", addGoodsToCartDTO.getUserId())
                .eq("goods_id", addGoodsToCartDTO.getGoodsId());
        Cart cart1 = cartMapper.selectOne(wrapper);
        System.out.println("cart1"+cart1);
        if (cart1 != null) {
            System.out.println("进入修改操作");
            cart1.setGoodsNumber(cart1.getGoodsNumber() + cart.getGoodsNumber());
            //        判断cart1中商品数量是否大于剩余库存，如果大于，将cart1数量设置为最大库存，如果小于库存，则不做改变
            System.out.println("数量"+cart1);
            if (cart1.getGoodsNumber() > addGoodsToCartDTO.getRemainingInventory()) {
                cart1.setGoodsNumber(addGoodsToCartDTO.getRemainingInventory());
            }else{
                cart1.setGoodsNumber(cart1.getGoodsNumber());
            }
            //        修改购物车表中商品数量
            System.out.println("修改的数据"+cart1);
            integer = cartMapper.update(cart1,wrapper);
            System.out.println("修改后的数据"+integer);
        }

//        若是没有，则直接加购
        if (cart1 == null){
            integer = cartMapper.insert(cart);
        }
        return integer;
    }

    @Override
    public List<Cart> getCartListByUserId(Integer userId) {
        //        获取购物车信息根据用户id，包含商品信息
        List<Cart> cartListByUserId = cartMapper.getCartListByUserId(userId);
        return cartListByUserId;
    }

    @Override
    public Integer getCartTotalPriceByUserId(Integer userId) {
        return cartMapper.getCartTotalPriceByUserId(userId);
    }

    @Override
    public Integer updateCartInfo(CartDTO cartDTO) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        Cart cart = new Cart();
        cart.setGoodsNumber(cartDTO.getGoodsNumber());
        cart.setGoodsSelectedStatus(cartDTO.getGoodsSelectedStatus());
        cart.setCartId(cartDTO.getCartId());
        queryWrapper.eq("cart_id", cartDTO.getCartId());
        return  cartMapper.update(cart, queryWrapper);

    }

    @Override
    public Integer deleteCartByCartId(Integer cartId) {
        return cartMapper.deleteById(cartId);
    }

    @Override
    public HashMap<String, Object> getCartListByUserIdAndSelectedStatus(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        Address address = new Address();
//        获取订单商品列表
        List<Cart> carts = cartMapper.getCartListByUserIdAndSelectedStatus(userId);
//        获取订单金额
        Integer cartTotalPriceByUserId = cartMapper.getCartTotalPriceByUserId(userId);
//        获取默认收获地址id和信息
        address = addressMapper.selectOne(new QueryWrapper<Address>().eq("user_id", userId).eq("is_default", 1));
        map.put("orderGoodsList",carts);
        map.put("orderTotalPrice",cartTotalPriceByUserId);
        map.put("address",address);
        return map;
    }

    @Override
    public List<Reduction> getReductionList() {
        return reductionMapper.selectList(null);
    }

    @Override
    public Integer generateOrder(GenerateOrderDTO generateOrderDTO) {
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
            return 0;
        }
//        设置订单列表信息
        OrderList orderList = new OrderList();
        Cart cart1 = new Cart();
        for (Cart cart : generateOrderDTO.getOrderGoodsList()) {
            orderList.setOrderListId(null);
            orderList.setOrdersId(ordersId);
            orderList.setGoodsId(cart.getGoodsId());
            orderList.setGoodsPrice(cart.getGoods().getGoodsPrice());
            orderList.setGoodsNumber(cart.getGoodsNumber());
            orderList.setPriceSubtotal(cart.getGoods().getGoodsPrice().multiply(new BigDecimal(cart.getGoodsNumber())));
            System.out.println("插值"+orderList);

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
        return 1;
    }
}
