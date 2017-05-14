package com.test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestDateDiff {

	public static void main(String[] args) {
		String format = "yyyy-MM-dd HH:mm:ss";
		String startTime="1991-12-21 05:03:02";
		String endTime="2016-05-04 18:56:32";
		System.out.println(dateDiff(startTime, endTime, format));
	}
	/**
	 * 计算差两个时间差多少
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 */
    public static String dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            System.out.println(diff);
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            return day + "天" + hour + "时" + min + "分" + sec + "秒";
        } catch (ParseException e) {
            return null;
        }
    }
}
