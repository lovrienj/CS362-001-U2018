/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.GregorianCalendar;

import calendar.Appt;
import calendar.CalDay;


public class CalDayTest{

  @Test(timeout = 4000)
  public void Constructor()  throws Throwable  {
	  int day = 15;
	  int month = 8;
	  int year = 1001;
	  
	  GregorianCalendar testDay = new GregorianCalendar(year, month, day);
	  CalDay calTest = new CalDay(testDay);
	  
	  String correctString = "\t --- 9/15/1001 --- \n --- -------- Appointments ------------ --- \n\n";
	  String outputString = calTest.toString();
	  assertEquals(correctString, outputString);
	  
	  CalDay badDay = new CalDay();
	  String correctNull = ("");
	  String outputNull = badDay.toString();
	  
	  assertEquals(correctNull, outputNull);

  }
  
  //This next test tests the add appt part (and toString() I guess too) And then, it tests the big one getFullInfomrationApp
  @Test(timeout = 4000)
  public void AddAppt()  throws Throwable  {
	  //Creating an early appt, late appt, and an invalid one
	  Appt validApptEarly = new Appt(0, 30, 15, 9, 2018, "Birthday", "It's my big birthday bash!", "wazzah@gmail.com");
	  validApptEarly.setValid();
	  Appt validApptLate = new Appt(12, 0, 15, 9, 2018, "Birthday 2.0", "It's my big birthday bash CONTINUED!", "wazzahhomie@gmail.com");
	  validApptLate.setValid();
	  Appt invalidAppt = new Appt(-100, 30, 15, 9, 2018, "inval", "inval", "inval");
	  invalidAppt.setValid();
	  

	  GregorianCalendar testDayGreg = new GregorianCalendar(2098, 5, 15);
	  CalDay testDay = new CalDay(testDayGreg);
	  
	  testDay.addAppt(validApptEarly);
	  testDay.addAppt(validApptLate);
	  testDay.addAppt(invalidAppt);

	  String correctOutput = "\t --- 6/15/2098 --- \n" + 
			" --- -------- Appointments ------------ --- \n" +
			"\t9/15/2018 at 12:30am ,Birthday, It's my big birthday bash!\n " + 
	  		"\t9/15/2018 at 12:0pm ,Birthday 2.0, It's my big birthday bash CONTINUED!\n \n";
	  String output = testDay.toString();
	  

	  assertEquals(correctOutput, output); 
	  
	  /*********
	   * Testing getFullInfomrationApp
	   */
	  String outputFull = testDay.getFullInfomrationApp(testDay); 
	  String correctFull = "6-15-2098 \n" + 
	  		"\t12:30AM Birthday It's my big birthday bash! \n" + 
	  		"\t12:00PM Birthday 2.0 It's my big birthday bash CONTINUED! ";
	  assertEquals(correctFull, outputFull);
	  
	  /*VERSION 2*/
	  
	  //testing appts.size()
	  assertEquals(2, testDay.getSizeAppts());
	  
	  
	   /*
	   * Gonna check with a loop! so now that one set of strings has passed, I'm gonna loop through a bunch of values!
	   */
	  
	  //Adding PM's
	  for(int i = 13; i < 24; i++)
	  {
		  Appt newAppt = new Appt(i, 0, 15, 9, 2018, "B-day", "Yas", "yee");
		  testDay.addAppt(newAppt);
		  correctFull += "\n\t" + Integer.toString(i % 12) + ":00PM B-day Yas ";
		  outputFull = testDay.getFullInfomrationApp(testDay);
		  assertEquals(correctFull, outputFull);
	  }
	   
	  //Adding AM's. These should come before the PM's so the strings are being inserted BEFORE the 12 pm
	  //Method for inserting new string into old string is from: https://stackoverflow.com/questions/5884353/insert-a-character-in-a-string-at-a-certain-position
	  
	  for(int i = 1; i < 12; i++)
	  {
		  Appt newAppt = new Appt(i, 30, 15, 9, 2018, "B-day", "Yas", "yee");
		  testDay.addAppt(newAppt);
		  String toInsert = "\t" + Integer.toString(i) + ":30AM B-day Yas \n";
		  
		  int needExtraRoom = (i > 10) ? 1 : 0; //extra room is so that I add 1 more space when I start having times of like 10 because that takes up 2 chars
		  int spotToInsert = 38 + i * 19 + needExtraRoom; //38 is the original 12:00 am, and 19 is the length of each new appointment I'm adding
		  correctFull = correctFull.substring(0, spotToInsert) + toInsert + correctFull.substring(spotToInsert, correctFull.length());
		  outputFull = testDay.getFullInfomrationApp(testDay);
		  
		  assertEquals(correctFull, outputFull);
	  }
	  
	  //Checking all the minutes
	  for(int i = 1; i < 60; i++)
	  {
		  Appt newAppt = new Appt(23, i, 15, 9, 2018, "B-day", "Yas", "yee");
		  testDay.addAppt(newAppt);
		  correctFull += "\n\t" + "11:";
		  
		  if(i < 10)
			  correctFull += "0";
		  
		  correctFull += Integer.toString(i) + "PM B-day Yas ";
		  outputFull = testDay.getFullInfomrationApp(testDay);
		  assertEquals(correctFull, outputFull);
	  }
	  
  }

  //Checking iterator for NULL
  @Test(timeout = 4000)
  public void NullIterator()  throws Throwable  {
	  CalDay badCalDay = new CalDay();
	  assertNull(badCalDay.iterator());
  }
  
}