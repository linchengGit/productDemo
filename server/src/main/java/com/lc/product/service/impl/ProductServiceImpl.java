package com.lc.product.service.impl;

import com.google.gson.Gson;
import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProdectInfoOutput;
import com.lc.product.dataobject.ProductInfo;
import com.lc.product.dto.CardDTO;
import com.lc.product.enums.ProductStatusEnum;
import com.lc.product.enums.ResultEnum;
import com.lc.product.exception.ProductException;
import com.lc.product.repository.ProductInfoRepository;
import com.lc.product.service.ProductService;
import com.lc.product.utils.JsonUtil;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lc
 * @date 2019/3/13
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return list;
    }

    @Override
    public List<ProdectInfoOutput> findList(List<String> productIdList) {
        List<ProductInfo> list = productInfoRepository.findByProductIdIn(productIdList);
        if (list != null && !list.isEmpty()) {
            List<ProdectInfoOutput> result = list.stream()
                    .map(e -> {
                        ProdectInfoOutput prodectInfoOutput = new ProdectInfoOutput();
                        BeanUtils.copyProperties(e, prodectInfoOutput);
                        return prodectInfoOutput;
                    }).collect(Collectors.toList());
            return result;
        }
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

        //发送MQ消息
        List<ProdectInfoOutput> prodectInfoOutputList = productInfoList.stream().map(e -> {
            ProdectInfoOutput prodectInfoOutput = new ProdectInfoOutput();
            BeanUtils.copyProperties(e, prodectInfoOutput);
            return prodectInfoOutput;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(prodectInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!optionalProductInfo.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optionalProductInfo.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);

        }
        return productInfoList;
    }


}
