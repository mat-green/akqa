/**
 * 
 */
package com.akqa.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class ScheduleBuilder {

	// -- attributes
	private List<Submission> submissions;
	private List<Booking> bookings;
	// -- constructors
	/** Default constructor.
	 * @param data	List of strings representing each line of the file.
	 * @throws ParseException Thrown due to incorrect time format.
	 */
	public ScheduleBuilder(List<String> data) throws ParseException {
		// parse opening times
		String[] opening = data.get(0).split(" ");
		OfficeHours hours = new OfficeHours(opening[0], opening[1]);
		
		// parse submissions
		this.submissions = new ArrayList<Submission>();
		for (int i = 1; i < data.size(); i++) {
			String[] line1 = data.get(i++).split(" ");
			String[] line2 = data.get(i).split(" ");
			
			Submission entry = new Submission(
				String.format("%s %s", line1[0], line1[1]), 
				line1[2], 
				String.format("%s %s", line2[0], line2[1]), 
				Integer.parseInt(line2[2]), 
				hours);
			
			this.submissions.add(entry);
		}
		// Sort submissions into date submitted
		Submission[] sort = this.submissions.toArray(new Submission[0]);
		int j;
		boolean flag = true; // set flag to true to begin first pass
		Submission temp;		 // holding variable

		while(flag) {
			flag = false;    //set flag to false awaiting a possible swap
			for(j = 0; j < sort.length-1; j++)
			{
				if (sort[j] != null && sort[j+1] != null && sort[j].getSubmission().after(sort[j+1].getSubmission())) {   // change to before for ascending sort
					temp = sort[j];                //swap elements
					sort[j] = sort[j+1];
					sort[j+1] = temp;
					flag = true;              //shows a swap occurred  
				}
			}
		}
		this.submissions = new ArrayList<Submission>();
		for (Submission submission : sort) {
			this.submissions.add(submission);
		}
		// Instantiate bookings
		this.bookings = new ArrayList<Booking>();
	}
	
	// -- operations
	public void execute() {
		for (Submission submission : this.submissions) {
			if(submission.checkBooking(this.bookings)) {
				this.bookings.add(submission.createBooking());
			}
		}
	}

	public List<String> getBookings() {
		List<String> result = new ArrayList<String>();
		// Sort the bookings into date order
		Booking[] sort = this.bookings.toArray(new Booking[0]);
		int j;
		boolean flag = true; // set flag to true to begin first pass
		Booking temp;		 // holding variable

		while(flag) {
			flag = false;    //set flag to false awaiting a possible swap
			for(j = 0; j < sort.length-1; j++)
			{
				if (sort[j].getStart().after(sort[j+1].getStart())) {   // change to before for ascending sort
					temp = sort[j];                //swap elements
					sort[j] = sort[j+1];
					sort[j+1] = temp;
					flag = true;              //shows a swap occurred  
				}
			}
		}
		// create out for printing
		DateFormat translate = new SimpleDateFormat("yyyy-MM-dd");
		String date = translate.format(sort[0].getStart());
		result.add(date);
		for (Booking booking : sort) {
			if(translate.format(booking.getStart()).equals(date))
			{
				result.add(booking.getBookingInfo());
			}
			else
			{
				date = translate.format(booking.getStart());
				result.add(date);
				result.add(booking.getBookingInfo());
			}
		}
		return result;
	}

}
