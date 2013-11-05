/**
 * 
 */
package com.akqa.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class Submission {

	// -- attributes
	private Date submission;
	private String employee;
	private Date start;
	private Integer duration;
	private OfficeHours officeHours;
	
	// -- constructors
	/** Default constructor.
	 * 
	 * All times to use 24hr clock.
	 * 
	 * @param	submission	The start date and time in YYYY-MM-DD hh:mm:ss
	 * @param	id			The employee identifier submitting booking.
	 * @param	start		The start date and time in YYYY-MM-DD hh:mm
	 * @param	duration	The booking duration
	 * @throws ParseException 
	 */
	public Submission(	String submission,
						String id, 
						String start,
						Integer duration,
						OfficeHours officeHours) throws ParseException {
		DateFormat format = new SimpleDateFormat( "yyyy-MM-DD HH:mm:ss" );
		this.submission = format.parse(submission);
		this.employee = id;
		this.start = format.parse(start+":00");
		this.duration = duration;
		this.officeHours = officeHours;
	}

	// -- operations
	public Boolean checkBooking(List<Booking> bookings) {
		Boolean result = false;
		Booking proposed = new Booking(this.start, this.employee, this.duration);
		if(this.officeHours.checkHours(this.start, this.duration)) {
			for (Booking booking : bookings) {
				if(!booking.compareTo(proposed))
				{
					break;
				}
			}
			result = true;
		}
		return result;
	}
}
