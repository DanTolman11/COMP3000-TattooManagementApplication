package com.tolman.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    /**
     * Converts by array into a readable hex string, padding with 0's as needed to allow correct
     * resolution of hex value
     */
    private static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Converts plain data string into a sha-512 hash, and then using the toHexString function
     * above converts into a readable hexstring
     */
    public static String hexDigest(String data) throws NoSuchAlgorithmException {
        return toHexString(MessageDigest.getInstance("SHA-512").digest(data.getBytes()));
    }
}
