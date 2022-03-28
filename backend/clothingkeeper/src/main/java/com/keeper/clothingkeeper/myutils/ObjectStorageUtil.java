package com.keeper.clothingkeeper.myutils;

/**
 * @program: clothingkeeper
 * @description: ObjectStorageUtil 对象存储obj-id换url工具类
 * @author: Vaskka
 * @create: 2020/3/7 4:45 PM
 **/

import com.keeper.clothingkeeper.myutils.cos.COSUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: fruit
 * @description: ObjectStorageUtil 对象存储工具类
 * @author: Vaskka
 * @create: 2020/1/15 6:57 PM
 **/

public class ObjectStorageUtil {

    /**
     * objectId 返回 objectUrl
     * @param objectId objectId
     * @return url
     */
    public static String getObjectUrl(final String prefix, final String objectId) {

        // condition null
        if (objectId == null) {
            return "";
        }

        String beforePrefix = COSUtil.getUrlFromObjectKey(objectId);
        beforePrefix = beforePrefix.replaceAll(objectId, "");

        return beforePrefix + prefix +
                "/" +
                objectId;
    }

    /**
     * 存储文件
     * @param uploadFile 上传的文件
     * @return String oid
     */
    public static String saveFile(final MultipartFile uploadFile, final String prefix) {

        // get object id
        String originObjectId =  getFileObjectId(uploadFile);
        String oidWithPrefix = joinObejctId(prefix, originObjectId);

        try {
            COSUtil.uploadWithInputStream(uploadFile.getInputStream(), oidWithPrefix);
        } catch (IOException ignore) {

        }

        // 只需将不带prefix的object_id返回即可
        return originObjectId;
    }

    /**
     * 使用 / join objectId
     * @param prefix 前缀
     * @param originObjectId object-id
     * @return String
     */
    private static String joinObejctId(String prefix, String originObjectId) {
        return prefix + "/" + originObjectId;
    }

    /**
     * 获取file-id
     * @param file file
     * @return String
     */
    private static String getFileObjectId(final MultipartFile file) {

        return CommonUtil.getRandomString32Length() +
                "-" +
                file.getOriginalFilename();
    }

}

