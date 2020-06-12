package com.ds.bits;

import java.util.BitSet;

public class Bits {
	
	public static void main(String[] args) {
		int i=1;
		BitSet bitSet=new BitSet(16);
		bitSet.set(15);
		System.out.println(bitSet.cardinality());
		System.out.println(bitSet.toString());
		System.out.println("2 "+i);
		System.out.println(Integer.toBinaryString(2));
	}
	

}
