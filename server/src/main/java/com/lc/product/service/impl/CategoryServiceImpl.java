package com.lc.product.service.impl;

import com.lc.product.dataobject.ProductCategory;
import com.lc.product.repository.ProductCategoryRespository;
import com.lc.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lc
 * @date 2019/3/13
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRespository productCategoryRespository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeLst) {
        return productCategoryRespository.findByCategoryTypeIn(categoryTypeLst);
    }
}
