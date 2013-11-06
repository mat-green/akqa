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
	 * @param	submission	The start date and time in yyyy-MM-dd HH:mm:ss
	 * @param	id			The employee identifier submitting booking.
	 * @param	start		The start date and time in yyyy-MM-dd HH:mm
	 * @param	duration	The booking duration
	 * @throws ParseException 
	 */
	public Submission(	String submission,
						String id, 
						String start,
						Integer duration,
						OfficeHours officeHours) throws ParseException {
		DateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		this.submission = format.parse(submission);
		this.employee = id;
		this.start = format.parse(start+":00");
		this.duration = duration;
		this.officeHours = officeHours;
	}

	// -- operations
	/** Check to see if submission can be a booking.
	 * @param bookings Current bookings.
	 * @return True or False if the submission is valid.
	 */
	public Boolean checkBooking(List<Booking> bookings) {
		Boolean result = true;
		Booking proposed = new Booking(this.start, this.employee, this.duration);
		if(this.officeHours.checkHours(this.start, this.duration)) {
			for (Booking booking : bookings) {
				if(booking.checkOverlap(proposed))
				{
					result = false;
				}
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	/** Factory method to create a booking from the submission data.
	 * @return an Booking instance.
	 */
	public Booking createBooking() {
		return new Booking(this.start, this.employee, this.duration);
	}
	
	public Date getSubmission() {
		return submission;
	}

	public String toString() {
		return String.format("%s %s %s", start, duration, this.employee);
	}
	
}
