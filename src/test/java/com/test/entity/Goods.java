package com.test.entity;

import java.io.Serializable;

public class Goods implements Serializable{
	private static final long serialVersionUID = 4323207636407348389L;

	private String name;
    private Float price;
    private String desc;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
	
}
