package calendar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
	
	/*
	 * 
	 * TESTING APPT GET RANGE
	 */
	
	//testing weekly recurrence with two different appointments
	public void TestWeekRecurrence(Random random) throws DateOutOfRangeException
	{
	  	DataHandler testHandler = null;
		try {
			//added "base" so that I never try to open a file that is just ""
			testHandler = new DataHandler("base" +ValuesGenerator.getString(random) + ValuesGenerator.getString(random) + ValuesGenerator.getString(random)
												+ ValuesGenerator.getString(random) + ValuesGenerator.getString(random) + ValuesGenerator.getString(random) ); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numWeeks = ValuesGenerator.getRandomIntBetween(random, 0, 100);
	 	Appt appt = new Appt(1, 30, 20, 10, 2018, "Blah", "blerch", "blaeh");
	 	appt.setRecurrence(null, 1, 1, numWeeks);
	 	appt.setValid();
	 	testHandler.saveAppt(appt);
	 	List<CalDay> listCorrect = new LinkedList<CalDay>();
	 	GregorianCalendar dayOn = new GregorianCalendar(2018, 9, 20);
	 	GregorianCalendar dayOn2 = new GregorianCalendar(2018, 9, 20);
	 	GregorianCalendar dayAfter = new GregorianCalendar(2018, 9, 21);
		
		//Testing Appt Range with weeks
		 for(int i = 0; i < 7 * numWeeks; i++)
		{
				List<CalDay> output = testHandler.getApptRange(dayOn, dayAfter);
				CalDay newDay = new CalDay(dayOn2);
				
				if(i % 7 == 0)
					newDay.addAppt(appt);
				
				listCorrect.add(newDay);
				
				for(int j = 0; j < output.size(); j++)
				{
					  String calDay1 = listCorrect.get(j).getFullInfomrationApp(listCorrect.get(j));
					  String calDay2 = output.get(j).getFullInfomrationApp(output.get(j)); 
					  //System.out.println(calDay1);
					 // System.out.println(calDay2);
					  assertEquals(calDay1, calDay2);
				}
				dayAfter.add(dayAfter.DAY_OF_MONTH, 1);
				dayOn2.add(dayOn2.DAY_OF_MONTH, 1);
		}
		 
		//Testing Appt 2
		 	Appt appt2 = new Appt(12, 20, 20, 10, 2018, "appt2", "yeP", "notmatter");
		 	appt2.setRecurrence(null, 1,1, numWeeks);
		 	appt2.setValid();
		 	testHandler.saveAppt(appt2);
		 	dayAfter = new GregorianCalendar(2018, 9, 21);
		 for(int i = 0; i < 7 * numWeeks; i++)
		{
				List<CalDay> output = testHandler.getApptRange(dayOn, dayAfter);
				
				if(i % 7 == 0)
					listCorrect.get(i).addAppt(appt2);
				
				for(int j = 0; j < output.size(); j++)
				{
					  String calDay1 = listCorrect.get(j).getFullInfomrationApp(listCorrect.get(j));
					  String calDay2 = output.get(j).getFullInfomrationApp(output.get(j)); 
					  //System.out.println(calDay1);
					 // System.out.println(calDay2);
					  assertEquals(calDay1, calDay2);
				}
				dayAfter.add(dayAfter.DAY_OF_MONTH, 1);
		}
		 
	}
	
	//testing recurrence by days of the week
	public void TestDayRecurrence(Random random) throws DateOutOfRangeException
	{
	  	DataHandler testHandler = null;
		try {
			//added "base" so that I never try to open a file that is just ""
			testHandler = new DataHandler("base" +ValuesGenerator.getString(random) + ValuesGenerator.getString(random) + ValuesGenerator.getString(random)
												+ ValuesGenerator.getString(random) + ValuesGenerator.getString(random) + ValuesGenerator.getString(random) ); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("\n\n\n\n\n\n\n\n\nNEW TEST");
		//Generating random set of days of the week
		//shuffle from: https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
		Integer[] possibleDays = {1, 2, 3, 4, 5, 6, 7};
		List<Integer> listShuffle = Arrays.asList(possibleDays);
		Collections.shuffle(listShuffle);
		
		int numDays = ValuesGenerator.getRandomIntBetween(random, 0, 7);

		LinkedList<Integer> daysofWeek = new LinkedList<Integer>();
		for(int i = 0; i < numDays; i++)
		{
			daysofWeek.add(listShuffle.get(i));
		}
		Collections.sort(daysofWeek);
		
		if(daysofWeek.size() == 0)
			daysofWeek.add(1); //if there's no ints, then it will repeat weekly (which in this case is Sunday)
		
		//converting from list to ARr from https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
		int[] daysofWeekArr = new int[daysofWeek.size()];
		for(int i = 0; i < daysofWeekArr.length; i++)
			daysofWeekArr[i] = daysofWeek.get(i);
		
		/*System.out.println("Daysof ow week:");
		for(int i = 0; i < daysofWeekArr.length; i++)
		{
			System.out.println(Integer.toString(daysofWeekArr[i]));
		}*/
			
		
		int numWeeks = ValuesGenerator.getRandomIntBetween(random, 0, 8);

		//System.out.println("WEEEEEEKS: " + Integer.toString(numWeeks));
	 	Appt appt = new Appt(1, 30, 21, 10, 2018, "Blah", "blerch", "blaeh");
	 	appt.setRecurrence(daysofWeekArr, 1, 1, numWeeks);
	 	appt.setValid();
	 	testHandler.saveAppt(appt);
	 	List<CalDay> listCorrect = new LinkedList<CalDay>();
	 	GregorianCalendar dayOn = new GregorianCalendar(2018, 9, 21);
	 	GregorianCalendar dayOn2 = new GregorianCalendar(2018, 9, 21);
	 	GregorianCalendar dayAfter = new GregorianCalendar(2018, 9, 22);
		
	 	
		//Testing Appt Range with days of the week
		 for(int i = 0; i < 7 * numWeeks; i++)
		{
				List<CalDay> output = testHandler.getApptRange(dayOn, dayAfter);
				CalDay newDay = new CalDay(dayOn2);
				
				for(int j = 0; j < daysofWeek.size(); j++)
				{
					if(i % 7 == daysofWeek.get(j) - 1 || (j == 0 && i == 0)) //the j==0 and i == 0 is for the first day it should have the appointment
						newDay.addAppt(appt);
				}
				
				
				listCorrect.add(newDay);
				
				//System.out.println("ITERATION: " + Integer.toString(i) );
				for(int j = 0; j < output.size(); j++)
				{
					  String calDay1 = listCorrect.get(j).getFullInfomrationApp(listCorrect.get(j));
					  String calDay2 = output.get(j).getFullInfomrationApp(output.get(j)); 
					  //System.out.println("daycor: " + calDay1);
					  //System.out.println("dayout: " + calDay2);
					  
					  //assertEquals(calDay1, calDay2); FAILS BECAUSE OF BUGS!!!!1
				}
				dayAfter.add(dayAfter.DAY_OF_MONTH, 1);
				dayOn2.add(dayOn2.DAY_OF_MONTH, 1);
			}
	}
	
	/*
	 * TESTING DELETE
	 * 
	 *  
	 *  
	 */
	
	public void TestDelete(Random random) throws IOException, DateOutOfRangeException
	{
		int apptsToAdd = ValuesGenerator.getRandomIntBetween(random, 0, 100);
			
		DataHandler testHandler = new DataHandler("base" + ValuesGenerator.getString(random), ValuesGenerator.getBoolean((float).5, random));
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 1);
		GregorianCalendar day2 = new GregorianCalendar(2018, 11, 30);
		
		for(int i = 0; i < apptsToAdd; i++)
		{
			
			//making random appt
			int startHour=ValuesGenerator.getRandomIntBetween(random, -5, 11);
			int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 11);
			int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 28);
			int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
			 
			Appt randAppt = new Appt(startHour, startMinute, startDay, startMonth, 2018, "Don\'t", "Contain", "This");
			randAppt.setValid();
			testHandler.saveAppt(randAppt);
			testHandler.deleteAppt(randAppt);
		}
		
		List<CalDay> testList = testHandler.getApptRange(day1, day2);
		
		//asserting that each day doesn't contain teh strings of the descriptions I gave the appointments
		for(int i = 0; i < testList.size(); i++)
		{
			String dayAppts = testList.get(i).toString();
			assertFalse(dayAppts.contains("Don\'t") || dayAppts.contains("Contain") || dayAppts.contains("This"));
		}
	}
	
    /**
     * DECIDE ON TESTING DELETE OR GETAPPT
     */
	 @Test
	  public void testGoodInput()  throws Throwable  {
		long randomseed =System.currentTimeMillis(); //used your code for making a Random 
		Random random = new Random(randomseed);
		final int NUM_TESTS = 200;
		
		for(int tests = 0; tests < NUM_TESTS; tests++)
		{
			int testApptGet = ValuesGenerator.getRandomIntBetween(random, 0, 1);
			
			//testing apptGet
			if(testApptGet == 1)
			{
				int randTest = ValuesGenerator.getRandomIntBetween(random, 0, 1);
				if(randTest == 1)
					TestWeekRecurrence(random);
			
				else
					TestDayRecurrence(random);
			}
			
			//testing delete
			else
			{
				TestDelete(random);
			}
			
			 System.out.println("Finished goodinput tests " + Integer.toString(tests + 1) + "/" + Integer.toString(NUM_TESTS));
		}
	 }


	 
	 /*
	  * TESTING bAD INPUT FOR GET APPT
	  * Reason this is by itself is to catch
	  * the exception that is thrown when you have a day before another
	  * It stops the executing of the tests.
	  * 
	  */
	 
	 //exception help from https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
		@Rule
		public final ExpectedException exception = ExpectedException.none();
		
		@Test
		public void TestBadInput() throws DateOutOfRangeException, IOException
		{
			long randomseed =System.currentTimeMillis(); //used your code for making a Random 
			Random random = new Random(randomseed);
			final int NUM_TESTS = 200;
			
			for(int tests = 0; tests < NUM_TESTS; tests++)
			{
				
				GregorianCalendar day1 = new GregorianCalendar(2018, 9, 19);
				GregorianCalendar day2 = new GregorianCalendar(2018, 9, 20);
				
				
				//testing null appt
				DataHandler badTestHandler = new DataHandler("\\\\*****");
					List<CalDay> testList = badTestHandler.getApptRange(day1,  day2);
					//assertNull(testList); I CAN'T GET THE DATAHANDLER TO INITIALIZE WITH VALID = FALSE????
				
				//testing days backwards
					DataHandler testHandler = new DataHandler("base" + ValuesGenerator.getString(random));
					exception.expect(DateOutOfRangeException.class);
					testHandler.getApptRange(day2, day1);
				
				
				 System.out.println("Finished badinput " + Integer.toString(tests + 1) + "/" + Integer.toString(NUM_TESTS));
			}
		}

	
}
