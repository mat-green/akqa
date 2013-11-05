/**
 * 
 */
package com.akqa.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class OfficeHoursTest {

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
	 * Test method for {@link com.akqa.scheduler.OfficeHours#checkHours(java.sql.Timestamp, java.lang.Integer)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testCheckHours() throws ParseException {
		// Test set up
		OfficeHours component = new OfficeHours("09:00", "17:30");
		DateFormat format = new SimpleDateFormat( "hh:mm" );
		Date startOk = new Date(format.parse("09:00").getTime());
		Date startBad = new Date(format.parse("16:00").getTime());
		// Test execution
		Boolean okay = component.checkHours(startOk, 2);
		Boolean bad = component.checkHours(startBad, 2);
		// Test verification
		assertTrue("result was "+okay.booleanValue(), okay.booleanValue());
		assertFalse("result was "+bad.booleanValue(), bad.booleanValue());
	}

}
