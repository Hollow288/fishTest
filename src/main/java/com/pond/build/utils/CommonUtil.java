package com.pond.build.utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class CommonUtil {
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
}
