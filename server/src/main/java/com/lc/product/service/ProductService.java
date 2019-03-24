package com.lc.product.service;

import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProdectInfoOutput;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.dto.CardDTO;

import java.util.List;

/**
 * @author lc
 * @date 2019/3/13
 */
public interface ProductService {


    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();


    /**
     * 获取商品列表（给订单服务）
     * @param productIdList
     * @return
     */
    List<ProdectInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param cardDTOS
     */
    void decreaseStock(List<DecreaseStockInput> cardDTOS);
}
