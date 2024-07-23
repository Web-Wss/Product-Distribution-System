package com.example.pds_api.service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.AddressMapper;
import com.example.pds_api.mapper.CartMapper;
import com.example.pds_api.mapper.GoodsInventoryMapper;
import com.example.pds_api.mapper.ReductionMapper;
import com.example.pds_api.model.Address;
import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.GoodsInventory;
import com.example.pds_api.model.Reduction;
import com.example.pds_api.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsInventoryMapper goodsInventoryMapper;
    @Resource
    private ReductionMapper reductionMapper;
    @Resource
    private AddressMapper addressMapper;

    @Override
    public Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO) {
//        根据用户id和商品id查询购物车中是否已有该商品
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", addGoodsToCartDTO.getUserId())
                .eq("goods_id", addGoodsToCartDTO.getGoodsId());
        Cart cartInfo = cartMapper.selectOne(cartQueryWrapper);
        if (cartInfo != null){
            //        如果有该记录,更新数量
            //        若库存小于更新数量,则设置该记录数量为库存数量
            QueryWrapper<GoodsInventory> goodsInventoryQueryWrapper = new QueryWrapper<>();
            goodsInventoryQueryWrapper.eq("goods_id", addGoodsToCartDTO.getGoodsId());
            Integer goodsUpdateNumber = (cartInfo.getGoodsNumber() + 1);
            if(goodsInventoryMapper.selectOne(goodsInventoryQueryWrapper).getGoodsInventorySum() <= goodsUpdateNumber){
                cartInfo.setGoodsNumber(goodsInventoryMapper.selectOne(goodsInventoryQueryWrapper).getGoodsInventorySum());
            }else{
                //        若库存大于等于更新数量,则设置该记录数量为更新数量
                cartInfo.setGoodsNumber(goodsUpdateNumber);
            }
//            执行更新
            return cartMapper.update(cartInfo, cartQueryWrapper);
        }
//        没有该记录,新增记录
        Cart cart = new Cart();
        cart.setUserId(addGoodsToCartDTO.getUserId());
        cart.setGoodsId(addGoodsToCartDTO.getGoodsId());
        cart.setGoodsNumber(1);
        cart.setGoodsSelectedStatus(1);
        cart.setIsSelected(1);
        return cartMapper.insert(cart);
    }

    @Override
    public List<Cart> getCartListByUserId(Integer userId) {
        return cartMapper.getCartListByUserId(userId);
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
    public List<Reduction> getReductionList() {
        return reductionMapper.selectList(null);
    }

    @Override
    public Object getDefaultAddress(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("is_default", 1);
        return addressMapper.selectOne(queryWrapper);
    }


}
