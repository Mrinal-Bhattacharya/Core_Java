package com.mockito.java;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

	// @InjectMocks will automatically mock address object in Person class. It
	// will work with @Mock. If there is no @Mock in Address object then Person
	// class will contain null object.
	@InjectMocks
	private Person person;
	@Mock
	private Address address;

	@Test
	public void testForInjectMock() {
		// Address address=Mockito.mock(Address.class);//this will not work with
		// @InjectMocks
		Mockito.when(address.getStree(Mockito.anyString())).thenReturn("DLFTEST");
		String hello = person.sayHello("TEST");
		Mockito.verify(address).getStree(Mockito.anyString());
		Assert.assertEquals("DLFTEST", hello);
	}

}
