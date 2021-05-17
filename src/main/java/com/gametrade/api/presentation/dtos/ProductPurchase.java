package com.gametrade.api.presentation.dtos;

public class ProductPurchase {
	private Number productId;
	private Long loggedUserId;
	
	public Number getProductId() {
		return productId;
	}
	public void setProductId(Number productId) {
		this.productId = productId;
	}
	public Long getLoggedUserId() {
		return loggedUserId;
	}
	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	
	
}
