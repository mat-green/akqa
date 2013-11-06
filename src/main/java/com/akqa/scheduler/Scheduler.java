package com.akqa.scheduler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author "Matthew Green<matthew@newedgeengineering.com>"
 *
 */
public class Scheduler {

	public Scheduler() {}
	
	/** Main Entry point
	 * @param args Array of parameters 
	 */
	public static void main(String[] args) {
		if(args.length > 0)
		{
			List<String> data = new ArrayList<String>();
			FileReader fr;
			try {
				fr = new FileReader(args[0]);
				BufferedReader br = new BufferedReader(fr);
				String currentRecord;
				while((currentRecord = br.readLine()) != null) {
					data.add(currentRecord);
				}
				br.close();
				ScheduleBuilder builder = new ScheduleBuilder(data);
				builder.execute();
				List<String> schedule = builder.getBookings();
				for (String row : schedule) {
					System.out.println(row);
				}
			} catch (FileNotFoundException e) {
				System.out.println(String.format("Unable find %s file to use as input.", args[0]));
			} catch (IOException e) {
				System.out.println(String.format("Error while reading %s file.", args[0]));
			} catch (ParseException e) {
				System.out.println(String.format("Incorrect date formats used in %s file.", args[0]));
			}
		}
		else
		{
			System.out.println("Usage:\n java com.akqa.scheduler.Scheduler input_file");
		}
	}
}
