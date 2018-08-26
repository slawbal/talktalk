package com.sb.talktalk.frontend.components;

public interface DataAccess<T> {
	T getData();
	void setData(T data);
}
