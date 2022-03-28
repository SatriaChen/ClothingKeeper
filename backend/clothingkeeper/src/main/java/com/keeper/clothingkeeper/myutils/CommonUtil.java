package com.keeper.clothingkeeper.myutils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @program: clothingkeeper
 * @description: CommonUtil 通用工具类
 * @author: Vaskka
 * @create: 2020/3/7 4:32 PM
 **/

public class CommonUtil {

    private static Random random = new Random();

    /**
     * 获取随机数
     * @return int
     */
    public static int rand() {
        return (int) (random.nextLong() % 0xFFFFF);
    }

    /**
     * 返回32位随机字符串
     * @return md5
     */
    public static String getRandomString32Length() {
        return getMD5(UUID.randomUUID().toString());
    }

    /**
     * MD5
     * @param str 待计算字符串
     * @return String 计算结果
     */
    public static String getMD5(String str) {
        return DigestUtils.md2Hex(str);
    }


}
