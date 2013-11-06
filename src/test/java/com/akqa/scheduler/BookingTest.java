/**
 * 
 */
package com.akqa.scheduler;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class BookingTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.akqa.scheduler.Booking#checkOverlap(com.akqa.scheduler.Booking)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testCheckOverlap() throws ParseException {
		// test set up
		DateFormat format = new SimpleDateFormat( "yyyy-MM-DD HH:mm" );
		Booking component = new Booking(format.parse("2011-03-21 09:00"), "EMP001", 2);
		Booking good = new Booking(format.parse("2011-03-21 12:00"), "EMP002", 1);
		Booking bad = new Booking(format.parse("2011-03-21 10:00"), "EMP003", 1);
		// test execution
		Boolean success = component.checkOverlap(good);
		Boolean failure = component.checkOverlap(bad);
		// test verification
		assertFalse(success.booleanValue());
		assertTrue(failure.booleanValue());
	}

	/**
	 * Test method for {@link com.akqa.scheduler.Booking#getBookingInfo()}.
	 * @throws ParseException From date format translation.
	 */
	@Test
	public final void testGetBookingInfo() throws ParseException {
		// test set up
		DateFormat translater = new SimpleDateFormat( "yyyy-MM-DD HH:mm" );
		Booking component = new Booking(translater.parse("2011-03-21 09:00"), "EMP001", 2);
		// test execution
		String result = component.getBookingInfo();
		// test verification
		assertEquals("09:00 11:00 EMP001", result);
	}
}
