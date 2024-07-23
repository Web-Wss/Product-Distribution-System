package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pds_api.mapper.DistributorMapper;
import com.example.pds_api.mapper.OrdersMapper;
import com.example.pds_api.mapper.WebsiteMapper;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Distributor;
import com.example.pds_api.model.Orders;
import com.example.pds_api.model.Website;
import com.example.pds_api.service.DistributorService;
import com.example.pds_api.utils.GetSevenDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DistributorServiceImpl extends ServiceImpl<DistributorMapper, Distributor> implements DistributorService {

    @Resource
    private DistributorMapper distributorMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private WebsiteMapper websiteMapper;


    @Override
    public Object getDashboardData(Integer distributorId) {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> param = new HashMap<>();
        List<String> date = new ArrayList<>();
        List<Integer> price = new ArrayList<>();
//        根据日期查询销售额
        List<String> sevenDate = GetSevenDate.getSevenDate();
        for (int i = sevenDate.size() - 1; i >= 0; i--){
            date.add(sevenDate.get(i));
//            根据分销商id和日期查询销售额
            param.put("distributorId", distributorId);
            param.put("date", "%"+ sevenDate.get(i) + "%");
            price.add(distributorMapper.getDailyOrderTotalPrice(param));
        }
//        获取订单数量
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("distributor_id", distributorId);
        map.put("orderNumber", ordersMapper.selectCount(queryWrapper));
//        获取订单总额
        List<Orders> orders = ordersMapper.selectList(queryWrapper);
        BigDecimal totalPrice = new BigDecimal(0);
        for (Orders order : orders){
            totalPrice = totalPrice.add(order.getOrderTotalPrice());
        }
        map.put("orderTotalPrice",totalPrice);
        map.put("date", date);
        map.put("price", price);
        return map;
    }

    @Override
    public Object getOrderListByDistributorId(Integer distributorId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("distributor_id", distributorId).orderByDesc("orders_id");
        List<Orders> orders = ordersMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    public HashMap<String, Object> getMarketingPromotionInfo(Integer distributorId) {
        HashMap<String, Object> map = new HashMap<>();
//        获取分销订单总金额
        BigDecimal totalPrice = new BigDecimal(0);
        List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().eq("distributor_id", distributorId));
        for (Orders order : orders){
            totalPrice = totalPrice.add(order.getOrderTotalPrice());
        }
        map.put("orderTotalPrice", totalPrice);
//        获取佣金比例
        Integer commissionRate = this.getOne(new QueryWrapper<Distributor>().eq("distributor_id", distributorId)).getCommissionRate();
        map.put("commissionRate", commissionRate);
//        获取已提现金额
        BigDecimal alreadyWithdrawn = this.getOne(new QueryWrapper<Distributor>().eq("distributor_id", distributorId)).getAlreadyWithdrawn();
        map.put("alreadyWithdrawn", alreadyWithdrawn);
//        获取分销总佣金
        BigDecimal commissionRate1 = new BigDecimal(commissionRate.toString());
        BigDecimal result = totalPrice.multiply(commissionRate1);
        map.put("commission", result);
//        获取网站地址
        Website website = websiteMapper.selectOne(new QueryWrapper<Website>().eq("website_id", 1));
        map.put("website", website);
        return map;
    }

    @Override
    public List<Orders> getCommissionDetail(Integer distributorId) {
        List<Orders> distributor_id = ordersMapper.selectList(new QueryWrapper<Orders>().eq("distributor_id", distributorId).orderByDesc("orders_id"));
        return distributor_id;
    }

    @Override
    public Integer updatePassword(LoginDTO loginDTO) {
        Distributor distributor1 = new Distributor();
//        判断旧密码是否正确
        QueryWrapper<Distributor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginDTO.getPhone())
                .eq("password", loginDTO.getPassword());
        Distributor distributor = distributorMapper.selectOne(queryWrapper);
        if (distributor == null){
            return 0;
        }
        if (loginDTO.getNewPassword().equals(loginDTO.getNewDPassword())){
            distributor1.setPassword(loginDTO.getNewPassword());
//            System.out.println("执行修改"+distributor1);
//            执行修改密码
            return distributorMapper.update(distributor1, new QueryWrapper<Distributor>().eq("phone", loginDTO.getPhone()));
        }
        return 0;
    }
}
