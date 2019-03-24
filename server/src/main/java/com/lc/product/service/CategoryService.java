package com.lc.product.service;

import com.lc.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author lc
 * @date 2019/3/13
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeLst);
}
