package com.lc.product.utils;

import com.lc.product.VO.ResultVO;

/**
 * @author lc
 * @date 2019/3/14
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
