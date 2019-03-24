package com.lc.product.controller;

import com.lc.product.VO.ProductInfoVO;
import com.lc.product.VO.ProductVO;
import com.lc.product.VO.ResultVO;
import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProdectInfoOutput;
import com.lc.product.dataobject.ProductCategory;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.dto.CardDTO;
import com.lc.product.service.CategoryService;
import com.lc.product.service.ProductService;
import com.lc.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 * @author lc
 * @date 2019/3/12
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.从数据库查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        //1.查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3.从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType() == productCategory.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }


    /**
     * 获取商品列表（给订单服务）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProdectInfoOutput> listForOrder(@RequestBody List<String> productIdList){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return productService.findList(productIdList);
    }


    /**
     * 扣库存（给订单服务）
     * @param decreaseStockInputList
     * @return
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
         productService.decreaseStock(decreaseStockInputList);
    }
}
