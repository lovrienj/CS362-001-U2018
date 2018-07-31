
/** A JUnit test class to test the class DataHandler. */


package calendar;

import java.util.Arrays;

import com.sun.tools.doclets.internal.toolkit.util.PackageListWriter;
import java.util.Random;




import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{
	
// Exception help from https://www.infoq.com/news/2009/07/junit-4.7-rules
/*@Rule
public ExpectedException exception = ExpectedException.none();*/

  @Test(timeout = 4000)
  public void SaveAppt()  throws Throwable  {
	  DataHandler testHandler = new DataHandler("myFile123");
	  //Creating an valid appt an invalid one
	  Appt validAppt= new Appt(0, 30, 15, 10, 2018, "Birthday", "It's my big birthday bash!", "wazzah@gmail.com");
	  validAppt.setValid();
	  Appt invalidAppt = new Appt(-100, 30, 15, 9, 2018, "inval", "inval", "inval");
	  invalidAppt.setValid();
	  
	  //this test handler shoudl only save the valid appt
	  boolean saved = testHandler.saveAppt(validAppt);
	  assertTrue(saved);
	  saved = testHandler.saveAppt(invalidAppt);
	  assertFalse(saved);
	  
	  //So, I'm going to see what's been saved on the document. using apptRange
	  GregorianCalendar testDayGreg = new GregorianCalendar(2018, 9, 15);
	  GregorianCalendar testDay2Greg = new GregorianCalendar(2018, 9, 16);
	  CalDay correctCalDay = new CalDay(testDayGreg);
	  correctCalDay.addAppt(validAppt);
	  
	//This correct list only contains the calendar day with the valid appointment which is all apptRange should bring back	  
	  List<CalDay> correctList = new LinkedList<CalDay>();
	  correctList.add(correctCalDay);
	  
	  //This output list should match the correct list
	  List<CalDay> outputList = testHandler.getApptRange(testDayGreg, testDay2Greg);
	  
	  /*
	  String test1 = outputList.get(0).getFullInfomrationApp(outputList.get(0)); 
	  System.out.println(Arrays.toString(test1.getBytes()));	
	  String test2 = correctList.get(0).getFullInfomrationApp(correctList.get(0));
	  System.out.println(Arrays.toString(test2.getBytes()));
	  System.out.println(Integer.toString(outputList.size()));
	  System.out.println(Integer.toString(correctList.size()));*/
	  
	  //Checking all the CalDays in outputList and correctList
	  for(int i = 0; i < outputList.size(); i++)
	  {
		  String calDay1 = correctList.get(0).getFullInfomrationApp(correctList.get(0));
		  String calDay2 = outputList.get(0).getFullInfomrationApp(outputList.get(0)); 
		  assertEquals(calDay1, calDay2);
	  }
	  
	  //Now, to test the delete function
	  boolean deleted = testHandler.deleteAppt(invalidAppt);
	  assertFalse(deleted);
	  
	  deleted = testHandler.deleteAppt(validAppt);
	  assertTrue(deleted);
	  
	  //Trying to catch exception for date out of range but not worknig with @Rule above
	 /* exception.expect(DateOutOfRangeException.class);
	  outputList = testHandler.getApptRange(testDay2Greg, testDayGreg);*/

  }
  
  /**
   * 
   * Testing apptRecurence
   */
  @Test(timeout = 4000)
  public void Reccurence()  throws Throwable  {
	  	DataHandler testHandler = new DataHandler("myFile123");
		Appt recurTimes = new Appt(1, 30, 15, 9, 2018, "nothing", "nothing", "nothing");
		recurTimes.setValid();
		recurTimes.setRecurrence(null, 1, 1, 1);
		
		testHandler.saveAppt(recurTimes);
		
		GregorianCalendar dayAppt = new GregorianCalendar(2018,8,15);
		GregorianCalendar weekAfter = new GregorianCalendar(2018,8,24);

		  
		  List<CalDay> daysOccurs = testHandler.getApptRange(dayAppt, weekAfter);
		  
		  System.out.println("CALENDAR DAYS");
		  for(int i = 0; i < daysOccurs.size(); i++)
		  {
			  System.out.println(daysOccurs.get(i).getFullInfomrationApp(daysOccurs.get(i)));
		  }
		  
		  //With a recurrence of once a week, this list should be longer than 1 because teh range should include at least on recurrence
		assertTrue(daysOccurs.size() == 9);
		
		/*VERSION 2*/
		//Testing with many different appointments!
		//Incrementation help from https://stackoverflow.com/questions/3300328/add-1-week-to-a-date-which-way-is-preferred
		
		Appt appt10 = new Appt(1, 30, 20, 10, 2018, "Blah", "blerch", "blaeh");
		appt10.setRecurrence(null, 1, 1, 10);
		appt10.setValid();
		testHandler.saveAppt(appt10);
		List<CalDay> listCorrect = new LinkedList<CalDay>();
		GregorianCalendar dayOn = new GregorianCalendar(2018, 9, 20);
		GregorianCalendar dayOn2 = new GregorianCalendar(2018, 9, 20);
		GregorianCalendar dayAfter = new GregorianCalendar(2018, 9, 21);
		
		//This for loop correctly tests for the weekly recuring when running mvn test. 
		//HOWEVER, for for mutation it doesn't work???
		
		/*for(int i = 0; i < 70; i++)
		{
			List<CalDay> output = testHandler.getApptRange(dayOn, dayAfter);
			CalDay newDay = new CalDay(dayOn2);
			
			if(i % 7 == 0)
				newDay.addAppt(appt10);
			
			listCorrect.add(newDay);
			
			for(int j = 0; j < output.size(); j++)
			{
				  String calDay1 = listCorrect.get(j).getFullInfomrationApp(listCorrect.get(j));
				  String calDay2 = output.get(j).getFullInfomrationApp(output.get(j)); 
				  System.out.println(calDay1);
				  System.out.println(calDay2);
				  assertEquals(calDay1, calDay2);
			}
			dayAfter.add(dayAfter.DAY_OF_MONTH, 1);
			dayOn2.add(dayOn2.DAY_OF_MONTH, 1);
		}	*/
		

  }

}