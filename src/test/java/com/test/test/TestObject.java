package com.test.test;

import com.test.entity.Goods;
import com.test.util.RedisUtil;
import com.test.util.SerializeUtil;

public class TestObject {

	 public static void main (String[] args) {
	        Goods g1 = new Goods();
	        g1.setName("苹果");
	        g1.setPrice(5f);
	        g1.setDesc("这里的苹果大又甜");
	         
	        Goods g2 = new Goods();
	        g2.setName("橘子");
	        g2.setPrice(3.5f);
	        g2.setDesc("这里的橘子水很多");
	         
	        RedisUtil.getJedis().set("g1".getBytes(), SerializeUtil.serialize(g1));
	        byte[] bg1 = RedisUtil.getJedis().get("g1".getBytes());
	        Goods rg1 = (Goods)SerializeUtil.unserialize(bg1);
	        System.out.println(rg1.getName());
	        System.out.println(rg1.getPrice());
	        System.out.println(rg1.getDesc());
	    }
}
