
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{

  @Test(timeout = 4000)
  public void SaveAppt()  throws Throwable  {
	  System.out.println("CALENDAR DAYS");
	  DataHandler testHandler = new DataHandler("myTestFile");
	  //Creating an valid appt an invalid one
	  Appt validAppt= new Appt(0, 30, 15, 9, 2018, "Birthday", "It's my big birthday bash!", "wazzah@gmail.com");
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
	  List<CalDay>outputList = testHandler.getApptRange(testDayGreg, testDay2Greg);
	  
	  assertEquals(correctList, outputList);
	  
	  //Now, to test the delete function
	  boolean deleted = testHandler.deleteAppt(invalidAppt);
	  assertFalse(deleted);
	  
	  deleted = testHandler.deleteAppt(validAppt);
	  assertTrue(deleted);
	  
	  outputList = testHandler.getApptRange(testDay2Greg, testDayGreg);

	  System.out.println("CALENDAR DAYS");
	  assertTrue(outputList.isEmpty());
  }
  
  /**
   * 
   * Testing apptRecurence
   */
  @Test(timeout = 4000)
  public void Reccurence()  throws Throwable  {
		Appt recurTimes = new Appt(1, 30, 15, 9, 2018, "nothing", "nothing", "nothing");
		recurTimes.setValid();
		recurTimes.setRecurrence(null, 1, 1, 1);
		
		
		GregorianCalendar dayAppt = new GregorianCalendar(2018,9,15);
		GregorianCalendar weekAfter = new GregorianCalendar(2018,9,24);

		  DataHandler testHandler = new DataHandler();
		  
		  List<CalDay> daysOccurs = testHandler.getApptRange(dayAppt, weekAfter);
		  
		  System.out.println("CALENDAR DAYS");
		  for(int i = 0; i < daysOccurs.size(); i++)
		  {
			  System.out.println(daysOccurs.get(i).getFullInfomrationApp(daysOccurs.get(i)));
		  }
		  
		  //With a recurrence of once a week, this list should be longer than 1 because teh range should include at least on recurrence
		assertTrue(daysOccurs.size() > 1);
		
		/*VERSION 2*/
		//Testing with many different appointments!
		//Incrementation help from https://stackoverflow.com/questions/3300328/add-1-week-to-a-date-which-way-is-preferred
		
		//Testing up to 100 recurrences by week
		//List<CalDay> output = test.Handler.getApptRange
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < )
		}
		

  }

}
