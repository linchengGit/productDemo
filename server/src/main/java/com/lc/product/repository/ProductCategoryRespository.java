package com.lc.product.repository;

import com.lc.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lc
 * @date 2019/3/13
 */
public interface ProductCategoryRespository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeLst);
}
