package com.lc.product.enums;

import lombok.Getter;

/**
 * @author lc
 * @date 2019/3/19
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(2,"库存有误"),
        ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
