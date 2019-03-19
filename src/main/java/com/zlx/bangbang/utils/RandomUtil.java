package com.zlx.bangbang.utils;

import java.util.Random;

public class RandomUtil {
    private static Random random;

    private static final char[] words = {'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    static {
        random = new Random();
    }

    public static String generateUserId() {
        return generateRandomString(6);
    }

    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(words[random.nextInt(words.length)]);
        }
        return builder.toString();
    }
}
