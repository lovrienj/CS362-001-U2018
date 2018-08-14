
package finalprojectB;

import org.junit.Test;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   /*
    * 
    * MANUAL TESTING
    * 
    */
   @Test
   public void testManual()
   {
	   //making a default urlValidator
	   UrlValidator urlValidator = new UrlValidator();
	   
	   /*Testing some valid urls*/
	   String validUrl;
	   
	   validUrl = "https://www.google.com";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "www.google.com";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https:www.google.com/hithere";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https:www.google.com";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "http:youtube.com";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https:youtube.com";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://userInfor@hostname:1234";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://userInfor@hostname:1234/path/specification";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://userInfor@hostname:1234/path/specification?query";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://userInfor@hostname:1234/path/specification?query#fragment";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   /*Testing some invalid urls*/
	   String invalidUrl;
	   
	   invalidUrl = "asdsdfjkl";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "www.no";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "badScheme://facebook.com";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "http://facebook.com//doubleslashes";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "http://facebook.com#fragment?beforequery";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "://leadingcolon.com";
	   assertFalse(urlValidator.isValid(invalidUrl));
	   
	   invalidUrl = "http:///C/myFiles";
	   assertFalse(urlValidator.isValid(invalidUrl));
   }
   


   /*
    * 
    * PARTITION TESTING
    * 
    */
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
