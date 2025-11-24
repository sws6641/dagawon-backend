package com.dagawon.web.common.util;

import java.security.SecureRandom;

public class NumberUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * 원하는 자릿수의 숫자 난수를 생성한다.
     * @param length 생성할 난수 자리수
     * @return 숫자 난수 문자열
     */
    public static String getRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("난수 길이는 1 이상이어야 합니다.");
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(secureRandom.nextInt(10)); // 0~9
        }

        return sb.toString();
    }

}
