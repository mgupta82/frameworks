package com.maxis.gamingportal.pojo;

public class TestBean {
	
	private String gatewayName;
	
	private String restUrl;
	
	private String bannerImage;
	
	private String bannerUrl;
	
	public TestBean(){
		super();
	}

	public TestBean(String gatewayName, String restUrl,
			String bannerImage, String bannerUrl) {
		super();
		this.gatewayName = gatewayName;
		this.restUrl = restUrl;
		this.bannerImage = bannerImage;
		this.bannerUrl = bannerUrl;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	@Override
	public String toString() {
		return "TestBean [gatewayName=" + gatewayName
				+ ", restUrl=" + restUrl + ", bannerImage=" + bannerImage
				+ ", bannerUrl=" + bannerUrl + "]";
	}

}
