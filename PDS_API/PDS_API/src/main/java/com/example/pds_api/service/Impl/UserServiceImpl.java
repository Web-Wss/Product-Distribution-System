package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.*;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsInventoryMapper goodsInventoryMapper;
    @Resource
    private OrderListMapper orderListMapper;

    @Override
    public List<Address> getAddressList(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return addressMapper.selectList(queryWrapper);
    }

    @Override
    public Integer addAddressInfo(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setContacts(addressDTO.getContacts());
        address.setPhone(addressDTO.getPhone());
        address.setUserId(addressDTO.getUserId());
        address.setIsDefault(addressDTO.getIsDefault());
        return addressMapper.insert(address);
    }

    @Override
    public Integer updateAddressInfo(AddressDTO addressDTO) {
        Address address = new Address();
        Address address_default = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setUserId(addressDTO.getUserId());
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setContacts(addressDTO.getContacts());
        address.setPhone(addressDTO.getPhone());
        address.setIsDefault(addressDTO.getIsDefault());
        //        判断是否只改默认地址
        if (addressDTO.getIsDefault() == 1){
            //        将该用户地址全部改为0
            address_default.setIsDefault(0);
            addressMapper.update(address_default,new QueryWrapper<Address>().eq("user_id",addressDTO.getUserId()));
        }
//        修改地址信息
        return addressMapper.update(address,new QueryWrapper<Address>().eq("address_id",addressDTO.getAddressId()));

    }

    @Override
    public Integer deleteAddressInfo(Integer addressId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address_id",addressId);
        return  addressMapper.delete(queryWrapper);
    }

    @Override
    public Object getMyOrderListByParam(Integer userId, Integer orderStatus) {
        //        判断是否带参，带参根据带参查询，不带参查询全部
        if (orderStatus == 0){
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .orderByDesc("order_create_time");
            return ordersMapper.selectList(queryWrapper);
        }else{
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .eq("order_status",orderStatus)
                    .orderByDesc("order_create_time");
            return ordersMapper.selectList(queryWrapper);
        }
    }

    @Override
    public List<OrderList> getOrderDetailByOrderId(Integer ordersId) {
        return  ordersMapper.getOrderListByOrderId(ordersId);
    }

    @Transactional
    @Override
    public Integer generateOrder(GenerateOrderDTO generateOrderDTO) throws InsufficientResourcesException {
        try {
            //        生成订单信息
//        定义一个数据存储子订单信息
            List<OrderList> orderList = new ArrayList<>();
//        1.获取购物车已选中商品信息
            List<Cart> cartList = userMapper.getCartSelectedGoods(generateOrderDTO.getUserId());
//        1.1.遍历购物车已选中商品信息,判断生成订单数量是否小于剩余库存
            for (Cart cart : cartList) {
//            商品库存
                Integer goodsInventorySum = goodsInventoryMapper.selectOne(new QueryWrapper<GoodsInventory>().eq("goods_id", cart.getGoodsId())).getGoodsInventorySum();
//                System.out.println("商品"+cart.getGoodsId() +"库存"+goodsInventorySum);
                GoodsInventory goodsInventory = new GoodsInventory();
                goodsInventory.setGoodsInventorySum(goodsInventorySum - cart.getGoodsNumber());
//                System.out.println("修改库存信息:"+goodsInventory);
                //        1.2.若小于则生成订单
                if (cart.getGoodsNumber() <= goodsInventorySum){
//                更新商品库存信息
                    int update = goodsInventoryMapper.update(goodsInventory, new QueryWrapper<GoodsInventory>().eq("goods_id", cart.getGoodsId()).gt("goods_inventory_sum", 0));
                    if (update > 0){
                        OrderList orderList_ = new OrderList();
                        orderList_.setGoodsId(cart.getGoodsId());
                        orderList_.setGoodsNumber(cart.getGoodsNumber());
                        orderList_.setGoodsPrice(cart.getGoods().getGoodsPrice());
                        orderList_.setPriceSubtotal(cart.getGoods().getGoodsPrice().multiply(BigDecimal.valueOf(cart.getGoodsNumber())));
                        orderList.add(orderList_);
                    }else{
                        throw new RuntimeException("库存不足");
                    }
                }else{
                    throw new RuntimeException("库存不足");
                }

            }
//            System.out.println("生成订单");
//        3.订单生成成功
//        生成订单
            Orders orders = new Orders();
            orders.setUserId(generateOrderDTO.getUserId());
            orders.setAddressId(generateOrderDTO.getAddressId());
            orders.setOrderTotalPrice(generateOrderDTO.getOrderTotalPrice());
            orders.setOrderDiscountPrice(generateOrderDTO.getOrderDiscountPrice());
            orders.setOrderActualPayment(generateOrderDTO.getOrderActualPayment());
            orders.setOrderRemarks(generateOrderDTO.getRemark());
            orders.setDistributorId(generateOrderDTO.getDistributorId());
            ordersMapper.insert(orders);
//        获取主订单表id
            Integer ordersId = orders.getOrdersId();
//        遍历子订单信息,插入子订单信息
//            System.out.println("子订单信息:"+orderList);
            for (OrderList orderList_ : orderList) {
                orderList_.setOrdersId(ordersId);
                orderListMapper.insert(orderList_);
                //        4.删除购物车已选中商品信息
                cartMapper.delete(new QueryWrapper<Cart>().eq("user_id",generateOrderDTO.getUserId())
                        .eq("goods_id",orderList_.getGoodsId()));
            }
            return 1;
        }catch (RuntimeException e){
            return -1;
        }finally {
            EditCartInfo(generateOrderDTO);
        }


    }

    private void EditCartInfo(GenerateOrderDTO generateOrderDTO) {
//        遍历该用户的购物车列表,并且查询商品库存,修改购物车信息
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",generateOrderDTO.getUserId())
                .eq("is_selected",1);
        List<Cart> cartList = cartMapper.selectList(cartQueryWrapper);
//        System.out.println("购物车信息:"+cartList);
        for (Cart cart : cartList) {
//            商品库存
            Integer goodsInventorySum = goodsInventoryMapper.selectOne(new QueryWrapper<GoodsInventory>().eq("goods_id", cart.getGoodsId())).getGoodsInventorySum();
//            System.out.println("库存"+goodsInventorySum);
//            若商品库存小于1,则将购物车选中状态和是否可选设为0,数量设为1
            if (goodsInventorySum < 1){
                cart.setIsSelected(0);
                cart.setGoodsSelectedStatus(0);
                cart.setGoodsNumber(1);
                cartMapper.update(cart,new QueryWrapper<Cart>().eq("cart_id",cart.getCartId()));
            }
//            判断购物车数量和商品数量
            if (cart.getGoodsNumber() > goodsInventorySum){
                cart.setGoodsNumber(goodsInventorySum);
                cartMapper.update(cart,new QueryWrapper<Cart>().eq("cart_id",cart.getCartId()));
            }

        }
    }


}
