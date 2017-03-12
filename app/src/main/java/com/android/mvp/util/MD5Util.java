package com.android.mvp.util;

import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 字符串转化MD5
	 * */
	public static String ToMD5(String string) {
		java.security.MessageDigest alga;
		try {
			alga = java.security.MessageDigest.getInstance("MD5");
			alga.update(string.getBytes());
			byte[] digesta = alga.digest();
			String str = bytes2Hex(digesta); // to HexString
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 字节转MD5
	 * */
	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des.toUpperCase();
	}

}
