/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test//(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertTrue("Test00: this should be a vaild appt: ", appt0.getValid());
  }
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
    Appt appt0 = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String string0 = appt0.toString();
      assertEquals(12, appt0.getStartMonth());
    appt0.setValid();


}

@Test(timeout = 4000)
public void test02()  throws Throwable  {
   Appt appt0 = new Appt(15, 30, 9, 13, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
   String string0 = appt0.toString();
     assertEquals(13, appt0.getStartMonth());
   appt0.setValid();


}
@Test(timeout = 4000)
public void test04()  throws Throwable  {
   Appt appt0 = new Appt(15, 30, 9, 0, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
   String string0 = appt0.toString();
     assertEquals(0, appt0.getStartMonth());
   appt0.setValid();


}
@Test(timeout = 4000)
public void test05()  throws Throwable  {
   Appt appt0 = new Appt(15, 30, 9, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
   String string0 = appt0.toString();
     assertEquals(1, appt0.getStartMonth());
   appt0.setValid();


}
@Test(timeout = 4000)
public void test06()  throws Throwable  {
   Appt appt0 = new Appt(15, 30, 9, -1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
   String string0 = appt0.toString();
     assertEquals(-1, appt0.getStartMonth());
   appt0.setValid();


}

}
