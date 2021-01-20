package com.colourfur.security.utils;


import org.springframework.util.DigestUtils;

public class CustomPasswordEncoder{
    public static final String salt = "@^&%#$&@#HHJDbJBVYU#@*(@#RSHVHVSD@#(&#T$JHFV#@BNDSCOUOJDNKQEOIWEoisdhksbfsdfewr32";
    public static String encode(String charSequence) {
        byte[] saltBytes = salt.getBytes();
        byte[] passwordBytes = charSequence.getBytes();
        byte[] newByte = new byte[Math.min(saltBytes.length,passwordBytes.length)];
        for (int i = 0; i < newByte.length; i++){
            newByte[i] = (byte) (saltBytes[i] ^ passwordBytes[i]);
        }
        return DigestUtils.md5DigestAsHex(newByte);
    }

    public static boolean matches(String encodingStr, String encodedStr) {
        return encodedStr.equals(encode(encodingStr));
    }
}
