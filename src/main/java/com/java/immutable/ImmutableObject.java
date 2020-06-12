package com.java.immutable;

import java.util.Date;

public class ImmutableObject {
	private Date start;
	private Date end;

	public ImmutableObject(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
	}

	public Date start() {
		return (Date) start.clone();
	}

	public Date end() {
		return (Date) end.clone();
	}
}
//If arraylist containing Emp then we need to clone each and every Emp and then store in our immutable class.
//While sending collection send as Unmodifiable list or set.
