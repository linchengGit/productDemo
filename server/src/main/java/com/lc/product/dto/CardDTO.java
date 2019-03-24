package com.lc.product.dto;

import lombok.Data;

/**
 * @author lc
 * @date 2019/3/19
 */
@Data
public class CardDTO {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productStock;

    public CardDTO() {
    }

    public CardDTO(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }
}
