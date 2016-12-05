package com.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class LargeObjectFinalizer extends PhantomReference<Object> {
	public LargeObjectFinalizer(Object referent, ReferenceQueue<? super Object> q) {
		super(referent, q);
	}

	public void finalizeResources() {
		System.out.println("clearing ...");
	}
}
