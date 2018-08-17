package calendar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {

	/**
	 * Generate Random Tests that tests CalDay Class.
	 */
	@Test
	public void randomtest() throws Throwable {
		// There's only one function to test so I don't need to determine a random method to test
		final int NUM_TESTS = 100;
		long randomseed =System.currentTimeMillis(); //used your code for making a Random 
		Random random = new Random(randomseed);
		
		for (int tests = 0; tests < NUM_TESTS; tests++)
		{
			GregorianCalendar seedGreg = new GregorianCalendar(2098, 5, 15);
			CalDay dayUT = new CalDay(seedGreg);
			LinkedList<Appt> correctAppts= new LinkedList();
			
			//Each test will have a randomly lengthed day to test
			int lengthofDay = ValuesGenerator.getRandomIntBetween(random, 1, 100);
			
			for (int i = 0; i < lengthofDay; i++) 
			{
				//Generating random data
				String randTitle = ValuesGenerator.getString(random);
				String randDescription = ValuesGenerator.getString(random);
				int randStartHour = ValuesGenerator.getRandomIntBetween(random, -1, 24); //I include some bad values so that I can get valid!= true
				int randStartMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
				int randStartDay = ValuesGenerator.getRandomIntBetween(random, 0, 32);
				int randStartMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
				int randStartYear = ValuesGenerator.getRandomIntBetween(random, 0, 5000);

				//Creating new appt
				Appt appt = new Appt(randStartHour, randStartMinute, randStartDay, randStartMonth, randStartYear,
						randTitle, randDescription, "email dont matter here");

				//setting valid and adding appt to CalDay
				appt.setValid();
				dayUT.addAppt(appt);

				//adding the appt in the correct spot
				if(appt.getValid())
				{
					boolean addedAppt = false;

					for(int j = 0; j < correctAppts.size(); j++)
					{
						if(correctAppts.get(j).getStartHour() > appt.getStartHour())
						{
							correctAppts.add(j, appt);
							addedAppt = true;
							j+=100000; //this increment is to break out of the loop because when I add a thing it increases the size of the list so it's an infinite loop
						}
					}
					
					if(!addedAppt)
						correctAppts.add(appt);
				}
				
			//USEFUL IF ASSERTION FAILS!!
				/*
				System.out.println("iteration " + Integer.toString(i));
				for(int j = 0; j < correctAppts.size(); j++)
				{
					System.out.println("Correct: " + correctAppts.get(j).toString());
					System.out.println("Badjuju: " + dayUT.getAppts().get(j).toString());
					 
				}*/

				 
				for(int j = 0; j < correctAppts.size(); j++)
				{
					assertEquals(correctAppts.get(j).toString(), dayUT.getAppts().get(j).toString());
					 
				}

			}

			 System.out.println("Finished tests " + Integer.toString(tests + 1) + "/" + Integer.toString(NUM_TESTS));
		}
	}

}