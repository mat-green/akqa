/**
 * 
 */
package com.akqa.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	 * Test method for {@link com.akqa.scheduler.Booking#compareTo(com.akqa.scheduler.Booking)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testCompareTo() throws ParseException {
		// test set up
		DateFormat format = new SimpleDateFormat( "yyyy-MM-DD HH:mm" );
		Booking component = new Booking(format.parse("2011-03-21 09:00"), "EMP001", 2);
		Booking good = new Booking(format.parse("2011-03-21 12:00"), "EMP002", 1);
		Booking bad = new Booking(format.parse("2011-03-21 10:00"), "EMP003", 1);
		// test execution
		Boolean success = component.compareTo(good);
		Boolean failure = component.compareTo(bad);
		// test verification
		assertTrue(success.booleanValue());
		assertFalse(failure.booleanValue());
	}

}
