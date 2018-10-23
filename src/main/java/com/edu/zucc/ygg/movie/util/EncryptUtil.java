package com.edu.zucc.ygg.movie.util;

/*
 * Project Name: zc-collect-common
 * File Name: EncryptUtil.java
 * Class Name: EncryptUtil
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Class Name: EncryptUtil Description: 加密工具类
 *
 * @author jialiangli
 *
 */
public final class EncryptUtil {
    private EncryptUtil(){};
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);
    private static final int HEX_TWO_FIVE_FIVE = 0x00FF;
    private static final String AES = "AES";
    /**
     * 描述: MD5 加密
     *
     * @param rawInput
     * @param salt
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryptMd5(String rawInput, String salt) {
        String input = rawInput.toLowerCase();
        try {
            int middle = input.length() / 2;
            byte[] result = MessageDigest.getInstance("MD5").digest((input.substring(0, middle) + salt + input.substring(middle)).getBytes());
            StringBuilder strBuilder = new StringBuilder(result.length * 2);
            for (byte b : result) {
                String s = Integer.toHexString(b & HEX_TWO_FIVE_FIVE);
                if (1 == s.length()) {
                    strBuilder.append('0');
                }
                strBuilder.append(s);
            }
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * 描述：密码一次加密
//     */
//    public static String encryptMd5(String input) {
//        try {
//            byte[] result = MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
//            StringBuilder strBuilder = new StringBuilder(result.length * 2);
//            for (byte b : result) {
//                String s = Integer.toHexString(b & HEX_TWO_FIVE_FIVE);
//                if (1 == s.length()) {
//                    strBuilder.append('0');
//                }
//                strBuilder.append(s);
//            }
//            return strBuilder.toString();
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * AES加密
     *
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) {
        KeyGenerator kgen;
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encryptKey.getBytes());
            kgen = KeyGenerator.getInstance(AES);
            kgen.init(128, random);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
            return cipher.doFinal(content.getBytes("utf-8"));
        } catch (Exception e) {
            LOGGER.error("msg",e);
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param encryptBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(decryptKey.getBytes());

            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            kgen.init(128, random);

            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        } catch (Exception e) {
            LOGGER.error("msg", e);
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & HEX_TWO_FIVE_FIVE);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * AES加密
     *
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws NoSuchAlgorithmException
     */
    public static String aesEncrypt(String content, String encryptKey){
        return parseByte2HexStr(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptStr
     *            待解密的String
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) {
        return aesDecryptByBytes(parseHexStr2Byte(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(EncryptUtil.encryptMd5("123456", "ygg"));
    }

}
