package com.zlx.bangbang.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class DBUtil {
    public static void main(String[] args) {
        String password = "123456";
        System.out.println(DigestUtils.md5Hex(password));
    }
}
