package com.pond.build.utils;

import org.apache.poi.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    //几位随机数
    public static String generateRandomNumericString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);  // 生成 0 到 9 之间的随机数字
            sb.append(digit);
        }

        return sb.toString();
    }


    //URL编码转义 minio
    public static String fileUrlEncoderChance(String originalPath){

        try {
            if(originalPath == null || originalPath.isBlank()){
                return "";
            }
            URL url = new URL(originalPath);
            String path = url.getPath();

            String fileName = path.substring(path.lastIndexOf('/') + 1);

            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            String baseUrl = originalPath.substring(0, originalPath.lastIndexOf('/') + 1);

            return baseUrl + encodedFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
