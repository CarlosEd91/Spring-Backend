package com.tienda.online.dto.response;

import java.util.List;

public class DataListResponse<T> {

	private List<T> data;
	private Integer size;

	public DataListResponse(List<T> data, Integer size) {
		super();
		this.data = data;
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
}
