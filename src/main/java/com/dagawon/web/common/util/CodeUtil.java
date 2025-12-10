package com.dagawon.web.common.util;
import java.security.SecureRandom;

public class CodeUtil {

    private static final SecureRandom random = new SecureRandom();

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER = "0123456789";
    private static final String ALL = LOWER + UPPER + NUMBER;

    /**
     * 지정한 자릿수의 인증코드 생성
     * (영소문자 + 영대문자 + 숫자 각 1개 이상 포함 보장)
     */
    public static String generateAuthCode(int length) {

        if (length < 3) {
            throw new IllegalArgumentException("길이는 최소 3 이상이어야 합니다. (소문자/대문자/숫자 각 1개씩 필요)");
        }

        StringBuilder sb = new StringBuilder(length);

        // 최소 1개씩 보장
        sb.append(LOWER.charAt(random.nextInt(LOWER.length())));
        sb.append(UPPER.charAt(random.nextInt(UPPER.length())));
        sb.append(NUMBER.charAt(random.nextInt(NUMBER.length())));

        // 나머지 자리 아무 문자 랜덤
        for (int i = 3; i < length; i++) {
            sb.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        // 순서 섞기
        return shuffle(sb.toString());
    }

    // 문자열 셔플
    private static String shuffle(String input) {
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j = random.nextInt(arr.length);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return new String(arr);
    }
}
