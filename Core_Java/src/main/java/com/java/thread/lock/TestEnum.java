package com.java.thread.lock;

public enum TestEnum {
	INSTANCE;
	int i = 0;

	int getInt() {
		return i;
	}
}
