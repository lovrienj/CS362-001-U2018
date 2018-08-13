package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

	private static final long TestTimeout = 5 * 1000 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setValid","setRecurrence"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
    
    
    /** Test the IsValid Method**/
    public void testIsValid(Random random)
    {	
    	//declaring appt parts
		 int startHour = 0;
		 int startMinute = 0;
		 int startDay = 0;
		 int startMonth = 0;
		 int startYear = 0;
    	
    	//determining whether or not to make an invalid appointment
    	int invalid = ValuesGenerator.getRandomIntBetween(random, 0, 1);
    	
    	//making a valid appointmend
    	if(invalid == 0)
    	{
		 startHour=ValuesGenerator.getRandomIntBetween(random, 1, 11);
		 startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 11);
		 startDay=ValuesGenerator.getRandomIntBetween(random, 1, 28);
		 startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
		 startYear=ValuesGenerator.getRandomIntBetween(random, 1, 2018);
    	}
	 
    	//making invalid appointment
    	else
    	{
    	//Each one has a 1/6 chance of being the bad one. Then, they have a 50/50 shot at being postive too much or negative too much
    		int whichBadPart = ValuesGenerator.getRandomIntBetween(random, 0, 5);
    		int positive = ValuesGenerator.getRandomIntBetween(random, 0, 1);
    		
    		//bad hour
    		if(whichBadPart == 1)
    		{
    			
    			if(positive == 1)
        			startHour=ValuesGenerator.getRandomIntBetween(random, 60, 1000);
    			
    			else
        			startHour=ValuesGenerator.getRandomIntBetween(random,-99999, -1);
    		}
    		
    		//bad minute
    		else if(whichBadPart == 2)
    		{
    			if(positive == 1)
    				startMinute=ValuesGenerator.getRandomIntBetween(random, 60, 1000);
    			
    			else
    		   		startMinute=ValuesGenerator.getRandomIntBetween(random, -99999, -1);
    		}
    		
    		//bad day
    		else if(whichBadPart == 3)
    		{
    			if(positive == 1)
    				startDay=ValuesGenerator.getRandomIntBetween(random, 32, 1000);
    			
    			else
    				startDay=ValuesGenerator.getRandomIntBetween(random, -99999, 0);
    		}
    		
    		//bad month
    		else if(whichBadPart == 4)
    		{
    			if(positive == 1)
    				startMonth=ValuesGenerator.getRandomIntBetween(random, 13, 1000);
    			
    			else
    				startMonth=ValuesGenerator.getRandomIntBetween(random, -99999, 0);
    				
    		}
   		 
    		//bad year (only negative because no bad positive year
   		 	else
   		 	{
   		 		startYear=ValuesGenerator.getRandomIntBetween(random, -99999, 0);
   		 	}
    	}


		 //Construct a new Appointment object with the initial data	 
       Appt appt = new Appt(startHour,
                startMinute,
                startDay,
                startMonth,
                startYear,
                "Birthday",
               "My sweet birthday bash",
               "bethere@gmail.com");
       appt.setValid();
       
       //Asserting that isValid works properly
       if(invalid == 1)
    	   assertFalse(appt.getValid());
       
       else
    	   assertTrue(appt.getValid());
    }
    
    /*******
     * TESTING SET RECURRANCE
     */
    public void testSetRecurrence(Random random)
    {
    	Appt appt = new Appt(12, 30, 9, 9, 2018, "Hi", "here", "theis");
	
    	int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
    	int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
    	int recurBy=ApptRandomTest.RandomSelectRecur(random);
    	int recurIncrement = ValuesGenerator.RandInt(random);
    	int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
    	appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
    	
    	//testing the recurs
    	assertEquals(appt.getRecurBy(), recurBy);
    	assertEquals(appt.getRecurIncrement(), recurIncrement);
    	assertEquals(appt.getRecurNumber(), recurNumber);
    	
    	for(int i = 0; i < recurDays.length; i++)
    		assertEquals(appt.getRecurDays()[i], recurDays[i]);	   
    }
    
    
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setValid")){
						   	testIsValid(random);   
						}
					   else if (methodName.equals("setRecurrence")){
						   testSetRecurrence(random);
						}				
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	

	
}
