
package finalprojectB;

import org.junit.Test;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.util.Random;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest  {
   /*
    * 
    * MANUAL TESTING
    * 
    */
   /*
   @Test
   public void testManual()
   {
	   //making a default urlValidator
	   UrlValidator urlValidator = new UrlValidator();
	   
	   //Testing some valid urls
	   String validUrl;
	   
	   validUrl = "https://www.google.com";
	  // assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://www.google.com/hithere";
	  // assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "http://youtube.com";
	   //assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://youtube.com";
	   //assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://192.168.0.1";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://192.168.0.1:1234";
	   assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://192.168.0.1:1234/path/specification";
	   //assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://192.168.0.1:1234/path/specification?query";
	  // assertTrue(urlValidator.isValid(validUrl));
	   
	   validUrl = "https://192.168.0.1:1234/path/specification?query#fragment";
	  // assertTrue(urlValidator.isValid(validUrl));
	   
	   //Testing some invalid urls
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
   }*/
   


   /*
    * 
    * PARTITION TESTING
    * 
    */
  
   //Checking big strings
	@Test
   public void BigStrings()
   {
	   System.out.println("TEST 6");
	   //testing fileTest
	   UrlValidator bigTest = new UrlValidator();
	   
	   assertTrue(bigTest.isValid("https://www.hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiilllllllllllllllllllllllllllllllll.com"));
	   assertTrue(bigTest.isValid("https://www.SLKDJFALKSDJFLAKSDJFLAKSDJFALKSDFJLAKSDFJALKSDJFLASKDFJALSKDFJLAKSDFJLAKSDFJALSKDFJASKLDFJASKLDFJFJASKLFJASDKLFJASKLFJASKLFJASDFKLJASDFKLJASDFKL"
			   +"ASDLKFJALSKDFJLAKSDJFLAKSDJFLAKSJDFLAKSDJFLAKSDFJALSKFJDALKSDJFALKSDJFLAKSJDFLAKSJFDALSKDFJASKLFJASKLASFJKASKLFJAKLSFAKLSFJASKLDFJAKLSFAKLSDFJAKLSJFAKLFLAKFASKLFAKLDFJASKLDFJAKL"
			   +"ALSKDJFALKSJDFLAKJSDFLAKSDJFLAKSDJFLAKSDFJALKSDFJASDIOADFKLDSFGJLAKSDJFIOAWEJKLIOSDKLWEIOSDKLWEIOKLSDIOWEKLSDIOWEKLIOSDKLWEIOSDKLWEIOSDKLWEIOSDKLWEIOSDKLIOWEKLSDIOWEKLSDIOWESDIOWEKL.com"));
	   assertFalse(bigTest.isValid("http://hi/therelslllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"));
	   
	   //making a REALLY big string (over 1000 chars)
	   String baseString = "asdfjlasdjfklasdjfklasdjkflasjdfkl";
	   for(int i = 0; i < 5; i++)
	   {
		   baseString += baseString;
	   }
	   
	   assertTrue(bigTest.isValid("https://www." + baseString + ".com"));
	   assertFalse(bigTest.isValid("https://www." + baseString +"/" + ".com"));;
	   assertFalse(bigTest.isValid("https://www." + baseString +"/" + ".com"));
	   assertFalse(bigTest.isValid("https://www." + "192.168.0.1" + baseString + ".com"));
   }
   
 //Checking small and null strings
	@Test
   public void SmallStrings()
   {
	   System.out.println("TEST 5");
	   //testing fileTest
	   UrlValidator smallTest = new UrlValidator();
	   
	   assertTrue(smallTest.isValid("https://h.com"));
	   assertFalse(smallTest.isValid("https://1.2."));
	   assertFalse(smallTest.isValid("")); //checking empty string
	   assertFalse(smallTest.isValid(null)); //checking null string
	   
   }
   
   //Checking the AnyAuthority setting and allowing no authority for file
   @Test
   public void AnyAuthority()
   {
	   System.out.println("TEST 4");
	   //testing fileTest
	   UrlValidator standard = new UrlValidator();
	   
	   assertTrue(standard.isValid("file///C/hi/there"));
	   assertFalse(standard.isValid("http///hi/there"));
	   
	   //testing AnyAuthority
	   long anyAuthOpt = 1 << 0;
	   UrlValidator anyAuth = new UrlValidator(anyAuthOpt);
	   
	   assertTrue(anyAuth.isValid("hergeshers://www.gmail.com"));
	   assertTrue(anyAuth.isValid("hergeshers://192.168.0.1"));
	   assertFalse(anyAuth.isValid("blah://1."));
	   assertFalse(anyAuth.isValid("blah://1.02"));
	   assertFalse(anyAuth.isValid("blah://www.google.com//doubleslash"));	   
   }
   
   //Testing ip's at the edge of the spectrum and are big
   @Test
   public void MaxIP(){
		 UrlValidator tester = new UrlValidator();

		   System.out.println("TEST 3");
		 //testing good high numbers
		 assertTrue(tester.isValid("https://255.255.255.255"));
		 assertTrue(tester.isValid("https://255.255.255.254"));
		 assertTrue(tester.isValid("https://255.255.254.255"));
		 assertTrue(tester.isValid("https://255.254.255.255"));
		 assertTrue(tester.isValid("https://254.255.255.255"));
		 
		 //testing bad high numbers
		 assertFalse(tester.isValid("https://256.255.255.255"));
		 assertFalse(tester.isValid("https://255.255.256.255"));
		 assertFalse(tester.isValid("https://253.255.255.256"));
		 assertFalse(tester.isValid("https://0.0.0.256"));
		 assertFalse(tester.isValid("https://256.256.256.256"));

   }
   
   //Testing ip's at the edge of the spectrum and are bsmall
   @Test
   public void MinIP(){
		 UrlValidator tester = new UrlValidator();

		   System.out.println("TEST 2");
		 //testing good high numbers
		 assertTrue(tester.isValid("https://0.0.0.0"));
		 assertTrue(tester.isValid("https://0.0.0.1"));
		 assertTrue(tester.isValid("https://1.2.3.4"));
		 assertTrue(tester.isValid("https://6.5.4.3"));
		 assertTrue(tester.isValid("https://1.1.1.0"));
		 
		 //testing bad high numbers
		 assertFalse(tester.isValid("https://-1.-1.-1.-1"));
		 assertFalse(tester.isValid("https://0.0.0.0"));
		 assertFalse(tester.isValid("https://-255.-255.-255.-255"));

   }
   
   @Test
   public void Ports() {
	   UrlValidator portTest = new UrlValidator();

	   System.out.println("TEST 1");
	   //testing good ports
	   assertTrue(portTest.isValid("https://192.168.0.1:12"));
	   assertTrue(portTest.isValid("https://192.168.0.1:13"));
	   assertTrue(portTest.isValid("https://192.168.0.1:65000"));
	   assertTrue(portTest.isValid("https://192.168.0.1:1"));
	   assertTrue(portTest.isValid("https://192.168.0.1:1567"));
	   
	   //testing bad ports
	   assertFalse(portTest.isValid("https://192.168.0.1:-1"));
	   assertFalse(portTest.isValid("https://192.168.0.1:100000"));
   }
   
   public void testIsValid()
   {

	   String [] goodSchemes = {};
	   String [] badSchemes = {};
	   String [] badDomains = {};
	   String [] goodDomains = {};
	   String [] goodPorts = {};
	   String [] badPorts = {};
	   String [] goodPaths = {};
	   String [] badPaths = {};
	   String [] goodFragments = {};
	   String [] badFragments = {};
	   
	   UrlValidator urlValidator = new UrlValidator();
	   Random random = new Random();
	   
	   int numTests = 100;
	   
	   
	   for(int i = 0; i < numTests; i++)
	   {
		   String urlToTest = "";
		   boolean goodUrl = true;
		   
		   //Good or bad strings added randomly
		   int goodString = random.nextInt(2);
		   int whichIndex;
		   
		   if(goodString == 1)//good scheme
		   {
			   whichIndex = random.nextInt(goodSchemes.length);
			   urlToTest += goodSchemes[whichIndex];
		   }
		   
		   else //bad Scheme
		   {
			   whichIndex = random.nextInt(badSchemes.length);
			   urlToTest += badSchemes[whichIndex];
			   goodUrl = false;
		   }
		   

		   goodString = random.nextInt(2);
		   
		   if(goodString == 1)//good Domain
		   {
			   whichIndex = random.nextInt(goodDomains.length);
			   urlToTest += goodDomains[whichIndex];
		   }
		   
		   else //bad Domains
		   {
			   whichIndex = random.nextInt(badDomains.length);
			   urlToTest += badDomains[whichIndex];
			   goodUrl = false;
		   }
		   

		   goodString = random.nextInt(2);
		   
		   if(goodString == 1)//good Ports
		   {
			   whichIndex = random.nextInt(goodPorts.length);
			   urlToTest += goodPorts[whichIndex];
		   }
		   
		   else //bad Ports
		   {
			   whichIndex = random.nextInt(badPorts.length);
			   urlToTest += badPorts[whichIndex];
			   goodUrl = false;
		   }
		   

		   goodString = random.nextInt(2); 
		   
		   if(goodString == 1)//good Paths 
		   {
			   whichIndex = random.nextInt(goodPaths.length);
			   urlToTest += goodPaths[whichIndex];
		   }
		   
		   else //bad Paths
		   {
			   whichIndex = random.nextInt(badPaths.length);
			   urlToTest += badPaths[whichIndex];
			   goodUrl = false;
		   }
		   

		   goodString = random.nextInt(2);
		   
		   if(goodString == 1)//good Fragments
		   {
			   whichIndex = random.nextInt(goodFragments.length);
			   urlToTest += goodSchemes[whichIndex];
		   }
		   
		   else //bad 
		   {
			   whichIndex = random.nextInt(badFragments.length);
			   urlToTest += badFragments[whichIndex];
			   goodUrl = false;
		   }
		   
		   
		   assertEquals(goodUrl, urlValidator.isValid(urlToTest));
	   }
	   

   }
   

}
