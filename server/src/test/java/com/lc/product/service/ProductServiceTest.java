package com.lc.product.service;

import com.lc.product.ProductApplicationTests;
import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProdectInfoOutput;
import com.lc.product.dataobject.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lc
 * @date 2019/3/13
 */
@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        assertTrue(list.size()>0);
    }

    @Test
    public void findByProductIdIn() {
        List<ProdectInfoOutput> list = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
        assertTrue(list.size()==2);
    }

    @Test
    public void decreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}