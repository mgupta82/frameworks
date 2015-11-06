package com.maxis.gamingportal.pojo;

public class TestAdBean {
	
	private String uid;
	private String commonName;
	private String surName;
	
	public TestAdBean(){
		super();
	}
	
	public TestAdBean(String uid, String commonName, String surName) {
		super();
		this.uid = uid;
		this.commonName = commonName;
		this.surName = surName;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	@Override
	public String toString() {
		return "AdUserBean [uid=" + uid + ", commonName=" + commonName
				+ ", surName=" + surName + "]";
	}

}
