package com.opensmart.utils;

import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Title: DES鍙�嗗姞瀵嗙畻娉曪細
 * @Description:
 * @author Administrator
 *
 */
public class DESEncrypt {

	private static String strDefaultKey = "c1#3E^0h";
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;
	
	static DESEncrypt desEncrypt;
	
	private static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 姣忎釜byte鐢ㄤ袱涓瓧绗︽墠鑳借〃绀猴紝鎵�浠ュ瓧绗︿覆鐨勯暱搴︽槸鏁扮粍闀垮害鐨勪袱鍊�
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 鎶婅礋鏁拌浆鎹负姝ｆ暟
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 灏忎簬0F鐨勬暟闇�瑕佸湪鍓嶉潰琛�0
			if (intTmp < 16) {
				sb.append("0");
			}
			
			//转化为16进制
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}
	
	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			// 截取数组转化为字符串
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	public DESEncrypt() throws Exception {
		this(strDefaultKey);
	}
	
	//生成Cipher对象,用密钥加密明文(plainText),生成密文(cipherText)
	public DESEncrypt(String strKey) {
		try{
			Key key = getKey(strKey.getBytes());
			//编码
			encryptCipher = Cipher.getInstance("DES");
			//操作模式为加密(Cipher.ENCRYPT_MODE),key为密钥
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			
			//解码
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
//			throw new BusinessException(Code.HH9999);
		}
	}
	
	public static DESEncrypt getInstance(){
		String strKey="dlgf8SpP";
		desEncrypt=new DESEncrypt(strKey);
		return desEncrypt;
	}
	
	public String encrypt(String strIn)  {
		try {
			return byteArr2HexStr(encrypt(strIn.getBytes()));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return strIn;
	}
	
	//得到加密后的字节数组
	private byte[] encrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException {
		return encryptCipher.doFinal(arrB);
	
	}

	private byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}
	
	public String decrypt (String strIn) {
		try {
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取key的前8位生成密钥
	private Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new SecretKeySpec(arrB, "DES");
		return key;
	}
	
	public static void main(String[] args) throws Exception {
		String key = "dlgf8SpP";
		String str = "532486";
		System.out.println("key:" + key);
		DESEncrypt desPlus2 = new DESEncrypt(key);
		String e2 = desPlus2.encrypt(str);
		System.out.println("编码:" + e2);
		
		String d2 = desPlus2.decrypt(e2);
        System.out.println("解码:" + d2);
	}
}
