package com.app.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordSecurity {

    private static final int ITERATIONS = 1000;
    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SALT_BYTES = 64;
    private static final int HASH_BYTES = 128;


    public static String generetePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
        byte[] hash = pbkdf2(chars, salt, HASH_BYTES);
        return toHex(salt) + ":" + toHex(hash);
    }

    public static boolean validatePassword(String originalPassword, String storedHash, String storedSalt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] salt = fromHex(storedSalt);
        byte[] hash = fromHex(storedHash);
        byte[] testHash = pbkdf2(originalPassword.toCharArray(), salt, hash.length);
        return slowEquals(hash, testHash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance(SALT_ALGORITHM);
        byte[] salt = new byte[SALT_BYTES];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    private static boolean slowEquals(byte[] hash, byte[] testHash) {
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++)
            diff |= hash[i] ^ testHash[i];
        return diff == 0;
    }


}


