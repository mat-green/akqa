package com.akqa.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class Booking {

	// -- attributes
	private Date start;
	private Date end;
	private String employee;
	
	// -- constructors
	/** Default constructor.
	 * @param start		The start date of the booking
	 * @param id		The employee who made booking
	 * @param duration	The duration of the booking.
	 */
	public Booking(Date start, String id, Integer duration) {
		this.start = start;
		this.end = new Date(start.getTime() + (duration*3600000));
		this.employee = id;
	}
	
	// -- operations
	public Boolean checkOverlap(Booking booking) {
		Boolean result = false;
		if(
			((this.start.compareTo(booking.getStart()) < 0) &&
			 (this.end.compareTo(booking.getStart()) > 0)) ||
			((this.start.compareTo(booking.getStart()) == 0) &&
			 (this.end.compareTo(booking.getEnd()) == 0))
		)
		{
			result = true;
		}
		return result;
	}
	
	public String getBookingInfo() {
		DateFormat translator = new SimpleDateFormat("HH:mm");
		return String.format("%s %s %s", translator.format(start), translator.format(end), this.employee);
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}
	
	public String toString() {
		return String.format("%s %s %s", start, end, this.employee);
	}
}
