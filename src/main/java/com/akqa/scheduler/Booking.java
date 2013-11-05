package com.akqa.scheduler;

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
		this.setStart(start);
		this.end = new Date(start.getTime() + (duration*3600000));
		this.employee = id;
	}
	
	// -- operations
	public Boolean compareTo(Booking booking) {
		Boolean result = false;
		System.out.println(this.start + " to " + this.end);
		System.out.println(booking.getStart() + " to " + booking.getEnd());
		if(
			(booking.getStart().after(this.end) || booking.getStart().before(this.start)) &&
			(booking.getEnd().before(this.start) || booking.getEnd().after(this.end))
		)
		{
			result = true;
		}
		return result;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
