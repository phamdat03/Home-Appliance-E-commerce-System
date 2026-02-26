package data.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class API {

    // Lấy last name trong fullname
    public static String getName(String fullname) {
        if (fullname == null || fullname.isEmpty()) return "";
        String[] arr = fullname.trim().split(" ");
        return arr[arr.length - 1];
    }

    // Băm mật khẩu theo MD5 (chuẩn UTF-8)
    public static String getMd5(String input) {
        if (input == null) return null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 Algorithm not available", e);
        }
    }
}
