/**
 * 
 */
package com.akqa.scheduler;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class SchedulerBuilderTest {

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
	 * Test method for {@link com.akqa.scheduler.Scheduler#Scheduler(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public final void testScheduler() throws ParseException {
		// test set up
		List<String> data = new ArrayList<String>();
		data.add("0900 1730");
		
		data.add("2011-03-17 10:17:06 EMP001");
		data.add("2011-03-21 09:00 2");
		data.add("2011-03-16 12:34:56 EMP002");
		data.add("2011-03-21 09:00 2");
		data.add("2011-03-16 09:28:23 EMP003");
		data.add("2011-03-22 14:00 2");
		data.add("2011-03-17 11:23:45 EMP004");
		data.add("2011-03-22 16:00 1");
		data.add("2011-03-15 17:29:12 EMP005");
		data.add("2011-03-21 16:00 3");
		
		ScheduleBuilder component = new ScheduleBuilder(data);
		
		// test execution
		component.execute();
		List<String> result = component.getBookings();
		// test verification
		assertNotNull(result);
		for(String b : result) {
			System.out.println(b);
		}
		assertTrue("Size was "+result.size(), result.size() == 5);
		assertEquals("2011-03-21", result.get(0));
		assertEquals("09:00 11:00 EMP002", result.get(1));
		assertEquals("2011-03-22", result.get(2));
		assertEquals("14:00 16:00 EMP003", result.get(3));
		assertEquals("16:00 17:00 EMP004", result.get(4));
	}

}
