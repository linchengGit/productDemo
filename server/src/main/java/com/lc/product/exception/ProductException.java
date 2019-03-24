package com.lc.product.exception;

import com.lc.product.enums.ResultEnum;

/**
 * @author lc
 * @date 2019/3/19
 */
public class ProductException extends RuntimeException {

    private int code;

    public ProductException(int code,String msg) {
        super(msg);
        this.code = code;
    }


    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();

    }
}
