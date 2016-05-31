package com.springcloud.sessionexample.common.model;

/**
 * Param bean
 * 
 * @author a525125
 *
 */
public class Param {

	String key;
	String value;

	public Param(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Param [key=" + key + ", value=" + value + "]";
	}

}
