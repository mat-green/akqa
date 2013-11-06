package com.akqa.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** Office Hours
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class OfficeHours {
	
	// -- private attributes
	private Date start;
	private Date end;
	// -- constructors
	/** Default constructor
	 * @param	start	A string representing the start time as hh:mm as a 24hr clock.
	 * @param	end		A string representing the end time as hh:mm as a 24hr clock.
	 * @throws ParseException 
	 */
	public OfficeHours(String start, String end) throws ParseException {
		DateFormat format = new SimpleDateFormat( "HHmm" );
		this.start = new Date(format.parse(start).getTime());
		this.end = new Date(format.parse(end).getTime());
	}

	// -- operations
	/** Check hours
	 * 
	 * @param begins	The  start time of the meeting
	 * @param duration	The duration of the meeting.
	 * @return True or False if meeting is within office hours.
	 */
	public Boolean checkHours(Date start, Integer duration)
	{
		// TODO find better way to do this.
		Calendar startTime = new GregorianCalendar(1970,0,1,start.getHours(), start.getMinutes());
		Calendar endTime = new GregorianCalendar(1970,0,1,start.getHours() + duration, start.getMinutes());
		return (startTime.getTime().compareTo(this.start) >= 0 && endTime.getTime().compareTo(this.end) <= 0);
	}
}
