package com.tienda.online.dto.response;

public class DataResponse<T> {

	private T data;

	public DataResponse(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
