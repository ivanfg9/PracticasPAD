package com.example.gymsy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ContrasenaEncriptada {
    private static final char characters[] = {'0','1','2','3','4','5','6','7','8','9','0', 'A', 'B',
    'C', 'D', 'E', 'F', 'a', 'b','c','d','e','f'};

    public static byte[] getSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }

    public static String hash(String contrasena, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(salt);
        messageDigest.update(contrasena.getBytes("UTF-8"));
        byte[] mdBytes = messageDigest.digest();
        String hash = byteArrayToHexString(mdBytes);

        return hash;
    }

    public static String byteArrayToHexString(byte[] bytes){
        StringBuffer hex = new StringBuffer(bytes.length);

        for(int i=0; i<bytes.length;i++){
            hex.append(characters[(bytes[i] & 0xF0)>>4]);
            hex.append(characters[bytes[i] & 0xF0]);
        }
        return hex.toString();
    }

    public static byte[] fromHexString(String s) {
        int length = (s.length() / 2);
        byte[] bytes = new byte[length];
        for(int i=0 ; i<length ; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(i * 2), 16) << 4)
                    | Character.digit(s.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

}
