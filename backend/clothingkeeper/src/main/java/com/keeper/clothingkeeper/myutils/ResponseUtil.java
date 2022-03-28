package com.keeper.clothingkeeper.myutils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: clothingkeeper
 * @description: ResponseUtil http响应工具
 * @author: Vaskka
 * @create: 2020/3/7 8:03 PM
 **/

public class ResponseUtil {

    /**
     * 成功响应
     * @return json
     */
    public static Map<String, Object> success() {
        return constructMap();
    }

    /**
     * 成功响应
     * @param data data
     * @return json
     */
    public static Map<String, Object> success(Object data) {
        Map<String, Object> res = constructMap();
        res.put("data", data);

        return res;
    }

    /**
     * 构造Map基本结构
     * @return json
     */
    private static Map<String, Object> constructMap() {
        Map<String, Object> res = new HashMap<>();

        res.put("code", 0);
        res.put("msg", "success");

        return res;
    }
}
