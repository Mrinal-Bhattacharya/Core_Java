package com.ds.string;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ds.string.StringDS;

public class StringDSTest {
	@Test
	public void testIsUniqueChars2ReturnTrue() throws Exception {
		assertEquals(StringDS.isUniqueChars2("abcd"),true);
	}
	@Test
	public void testIsUniqueChars2ReturnFalse() throws Exception {
		assertEquals(StringDS.isUniqueChars2("abad"),false);
	}
	
	@Test
	public void testReverseString() throws Exception {
		assertEquals(StringDS.reverseRecurive("abad"),"daba");
	}
	@Test
	public void testForRemoveDuplicate() throws Exception {
		String str="abacab";
		String actual=StringDS.removeDuplicatesEff(str.toCharArray());
		//assertEquals(actual, "abc");
		
	}
	
	
}
