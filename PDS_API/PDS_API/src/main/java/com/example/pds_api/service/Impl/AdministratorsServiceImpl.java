package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pds_api.mapper.*;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.GoodsDTO;
import com.example.pds_api.model.VO.DistributorVO;
import com.example.pds_api.service.AdministratorsService;
import com.example.pds_api.utils.GetSevenDate;
import com.example.pds_api.utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators> implements AdministratorsService {

    @Resource
    private AdministratorsMapper administratorsMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private DistributorMapper distributorMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WebsiteMapper websiteMapper;
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private ReductionMapper reductionMapper;

    @Override
    public HashMap<String, Object> login(String phone, String password) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Administrators> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone)
                .eq("password", password);
        Administrators administrators = administratorsMapper.selectOne(queryWrapper);
        if (administrators != null){
            String token = JWTUtils.generateToken(phone);
            map.put("token", token);
            map.put("administrators", administrators);
            return map;
        }
        return null;
    }

    @Override
    public HashMap<String, Object> getDashboardData() {
        HashMap<String, Object> map = new HashMap<>();
        List<String> date = new ArrayList<>();
        List<Integer> price = new ArrayList<>();
        //        根据日期查询销售额
        List<String> sevenDate = GetSevenDate.getSevenDate();
        for (int i = sevenDate.size() - 1; i >= 0; i--){
            date.add(sevenDate.get(i));
//            根据日期查询销售额
            price.add(administratorsMapper.getDailyOrderTotalPrice("%"+ sevenDate.get(i) + "%"));
        }
//        获取订单数量
        map.put("orderCount", ordersMapper.selectCount(null));
//        获取商品数量
        map.put("goodsCount", goodsMapper.selectCount(null));
//        获取分销商数量
        map.put("distributorCount", distributorMapper.selectCount(null));
//        获取用户数量
        map.put("userCount", userMapper.selectCount(null));
//        获取订单总额
        administratorsMapper.getOrderTotalPrice();
        map.put("orderTotalPrice", administratorsMapper.getOrderTotalPrice());
        map.put("date", date);
        map.put("price", price);
        return map;
    }

    @Override
    public List<Goods> getGoodsList() {
        List<Goods> goods = goodsMapper.selectList(null);
        return goods;
    }

    @Override
    public Goods getGoodsInfoById(Integer goodsId) {
        Goods goodsInfoById = administratorsMapper.getGoodsInfoById(goodsId);
        return goodsInfoById;
    }

    @Override
    public Integer updateGoodsInfo(GoodsDTO goodsDTO) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        Goods goods = new Goods();
        goods.setGoodsName(goodsDTO.getGoodsName());
        goods.setGoodsClassificationId(goodsDTO.getGoodsClassificationId());
        goods.setGoodsPrice(goodsDTO.getGoodsPrice());
        goods.setGoodsOldPrice(goodsDTO.getGoodsOldPrice());
        goods.setGoodsCompany(goodsDTO.getGoodsCompany());
        queryWrapper.eq("goods_id", goodsDTO.getGoodsId());
//        查询该商品已售出数量
        Integer goodsOutNumber = administratorsMapper.getGoodsOutNumber(goodsDTO.getGoodsId());
//        设置商品总库存值
        Integer goodsTotalInventory = goodsOutNumber + goodsDTO.getRemainingInventory();
        goods.setGoodsTotalInventory(goodsTotalInventory);
//        修改商品库存
        int update = goodsMapper.update(goods, queryWrapper);
        return update;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @Override
    public HashMap<String, Object> getDistributorList() {
        HashMap<String, Object> map = new HashMap<>();
//        获取分销商列表
        List<Distributor> distributors = distributorMapper.selectList(null);
        distributors.forEach(distributor -> {
            System.out.println("distributorId = " + distributor.getDistributorId());
//            通过分销商id查询该分销商已售出商品数量和总销售额
            Long distributor_id = ordersMapper.selectCount(new QueryWrapper<Orders>().eq("distributor_id", distributor.getDistributorId()));
            distributor.setOrderCount(Math.toIntExact(distributor_id));
            Integer distributorTotalOrderAmount = administratorsMapper.getDistributorTotalOrderAmount(distributor.getDistributorId());
            distributor.setTotalOrderAmount(distributorTotalOrderAmount);
            int commission = distributorTotalOrderAmount * distributor.getCommissionRate();
            distributor.setCommission(commission);
        });
        map.put("distributorList", distributors);
//        获取分销商排名
        List<DistributorVO> distributorRanking = administratorsMapper.getDistributorRanking();
        map.put("distributorRanking", distributorRanking);
        return map;
    }

    @Override
    public List<Orders> getOrderList() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("order_create_time");
        List<Orders> orders = ordersMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    public List<Orders> getOrderListByUserId(Integer userId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .orderByDesc("order_create_time");
        List<Orders> orders = ordersMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    public Integer updateOrderStatusByOrderId(Integer ordersId, Integer orderStatus) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orders_id", ordersId);
        Orders orders = new Orders();
        orders.setOrderStatus(orderStatus);
        int update = ordersMapper.update(orders, queryWrapper);
        return update;
    }

    @Override
    public Integer updateOrderStatusComplete(Integer ordersId, Integer pay) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orders_id", ordersId);
        Orders orders = new Orders();
        orders.setOrderStatus(4);
        orders.setPayType(pay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        orders.setCompletionTime(time);
        int update = ordersMapper.update(orders, queryWrapper);
        return update;
    }

    @Override
    public HashMap<String, Object> getSystemInfo() {
        HashMap<String, Object> map = new HashMap<>();
//        获取站点信息
        QueryWrapper<Website> websiteQueryWrapper = new QueryWrapper<>();
        websiteQueryWrapper.eq("website_id", 1);
        Website website = websiteMapper.selectOne(websiteQueryWrapper);
//        获取通知管理信息
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        noticeQueryWrapper.eq("notice_id", 1);
        Notice notice = noticeMapper.selectOne(noticeQueryWrapper);
//        获取满减规则
        List<Reduction> reductions = reductionMapper.selectList(null);
//        获取登录密码
        QueryWrapper<Administrators> administratorsQueryWrapper = new QueryWrapper<>();
        administratorsQueryWrapper.eq("administrators_id", 1);
        Administrators administrators = administratorsMapper.selectOne(administratorsQueryWrapper);
        map.put("website", website);
        map.put("notice", notice);
        map.put("reductions", reductions);
        map.put("administrators", administrators);
        return map;
    }

    @Override
    public Integer updateSiteInfo(String path) {
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("website_id", 1);
        Website website = new Website();
        website.setWebsitePath(path);
        int update = websiteMapper.update(website, queryWrapper);
        return update;
    }

    @Override
    public Integer updateNoticeInfo(String noticeContent) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notice_id", 1);
        Notice notice = new Notice();
        notice.setNoticeContent(noticeContent);
        int update = noticeMapper.update(notice, queryWrapper);
        return update;
    }

    @Override
    public List<Reduction> getReductionInfo() {
        return reductionMapper.selectList(null);
    }

    @Override
    public Integer updateReductionInfo(Integer reductionId, BigDecimal fullConditionPrice, BigDecimal fullReductionAmount) {
        QueryWrapper<Reduction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("full_reduction_id", reductionId);
        Reduction reduction = new Reduction();
        reduction.setFullConditionPrice(fullConditionPrice);
        reduction.setFullReductionAmount(fullReductionAmount);
        int update = reductionMapper.update(reduction, queryWrapper);
        return update;
    }

    @Override
    public Integer updatePassword(String password) {
        QueryWrapper<Administrators> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("administrators_id", 1);
        Administrators administrators = new Administrators();
        administrators.setPassword(password);
        int update = administratorsMapper.update(administrators, queryWrapper);
        return update;
    }


}
