package com.akqa.scheduler;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubmissionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCheckBooking() throws ParseException {
		// Test set up
		OfficeHours hours = new OfficeHours("09:00", "17:30");
		Submission okaySubmission = new Submission("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", 2, hours);
		Submission badSubmission = new Submission("2011-03-15 17:29:12", "EMP005", "2011-03-21 16:00", 3, hours);
		List<Booking> current = new ArrayList<Booking>();
		// Test execution
		Boolean okay = okaySubmission.checkBooking(current);
		Boolean bad = badSubmission.checkBooking(current);
		// Test verification
		assertTrue(okay.booleanValue());
		assertFalse(bad.booleanValue());
	}

}
