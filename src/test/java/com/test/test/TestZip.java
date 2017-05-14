package com.test.test;

import com.cn.util.ZipUtil;

public class TestZip {

	public static void main(String[] args) throws Exception {
		String srcPathName1 = "C:/Users/sun/Desktop/test1/";
		String srcPathName2 = "C:/Users/sun/Desktop/test2/";
		String zipPath1 = "C:/Users/sun/Desktop/test1.zip";
		String zipPath = "C:/Users/sun/Desktop/test.zip";
		ZipUtil.compress(zipPath1,srcPathName1);
		ZipUtil.compress(zipPath,srcPathName1,srcPathName2);
		String sourceFile = "C:/Users/sun/Desktop/test.zip";
		String destDir = "C:/Users/sun/Desktop/test";
		ZipUtil.deCompress(sourceFile, destDir);
	}
}
