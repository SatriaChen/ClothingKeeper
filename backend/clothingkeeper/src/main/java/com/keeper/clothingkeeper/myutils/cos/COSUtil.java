package com.keeper.clothingkeeper.myutils.cos;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.keeper.clothingkeeper.myutils.connstant.ThirdConst.TencentCOS.*;

/**
 * @program: clothingkeeper
 * @description: COSUtil COS工具类
 * @author: Vaskka
 * @create: 2020/3/7 4:42 PM
 **/

public class COSUtil {

    private static COSClient client;

    private static COSClient getInstance() {
        if (client == null) {
            COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
            Region region = new Region(REGION);
            ClientConfig clientConfig = new ClientConfig(region);
            client =  new COSClient(cred, clientConfig);
        }

        return client;
    }

    /**
     * 上传file输入流和对应的key
     * @param inputStream 输入流
     * @param key key
     */
    public static void uploadWithInputStream(InputStream inputStream, String key) throws IOException {

        ObjectMetadata objectMetadata = new ObjectMetadata();

        getInstance().putObject(BUCKET_NAME, key, inputStream, objectMetadata);

        inputStream.close();
    }

    /**
     * 上传本地file和对应的objectid
     * @param localFile File file
     * @param objectKey oid
     */
    public static void uploadWithLocalFile(File localFile, String objectKey) {

        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectKey, localFile);
        getInstance().putObject(putObjectRequest);
    }

    /**
     * 从objectid换取url
     * @param key objectid
     * @return String url
     */
    public static String getUrlFromObjectKey(String key) {

        COSCredentials cred = new AnonymousCOSCredentials();

        ClientConfig clientConfig = new ClientConfig(new Region(REGION));

        COSClient cosClient = new COSClient(cred, clientConfig);

        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(BUCKET_NAME, key, HttpMethodName.GET);
        URL url = cosClient.generatePresignedUrl(req);

        cosClient.shutdown();

        return url.toString().replace("http", "https");
    }

}
