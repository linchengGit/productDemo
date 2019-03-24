package com.lc.product.repository;

import com.lc.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lc
 * @date 2019/3/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {

    @Autowired
    private ProductCategoryRespository productCategoryRespository;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryRespository.findByCategoryTypeIn(Arrays.asList(11, 22));
        assertTrue(list.size()>0);
    }
}