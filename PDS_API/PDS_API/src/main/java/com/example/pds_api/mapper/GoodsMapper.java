package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("SELECT\n" +
            "\t\tg.*,\n" +
            "    IFNULL(SUM(o.goods_number), 0) AS sold_quantity,\n" +
            "    g.goods_total_inventory - IFNULL(SUM(o.goods_number), 0) AS remaining_inventory\n" +
            "FROM \n" +
            "    goods g\n" +
            "LEFT JOIN \n" +
            "    order_list o ON g.goods_id = o.goods_id\n" +
            "GROUP BY \n" +
            "   g.goods_id, g.goods_total_inventory;")
    List<Goods> selectGoodsWithInventory();

    @Select("  SELECT\n" +
            "    g.*,\n" +
            "    IFNULL(SUM(o.goods_number), 0) AS sold_quantity,\n" +
            "    g.goods_total_inventory - IFNULL(SUM(o.goods_number), 0) AS remaining_inventory\n" +
            "    FROM\n" +
            "    goods g\n" +
            "    LEFT JOIN\n" +
            "    order_list o ON g.goods_id = o.goods_id\n" +
            "    WHERE g.goods_classification_id = #{classificationId}\n" +
            "    GROUP BY\n" +
            "    g.goods_id, g.goods_total_inventory")
    List<Goods> selectGoodsWithInventoryByClassification(@RequestParam("classificationId")Integer classificationId);




}
