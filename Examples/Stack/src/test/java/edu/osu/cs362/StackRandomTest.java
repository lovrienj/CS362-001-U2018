package edu.osu.cs362;


import org.junit.Test;
import static org.junit.Assert.*;


import java.util.NoSuchElementException;
import java.util.Random;

public class StackRandomTest {
    public static int RandInt(Random random){
    	int n = random.nextInt();
        return (int) n;
        }
    public static String RandMethod(Random random){
        String[] methodArray = new String[] {"push","pop","top"};//

    	int n = random.nextInt(3);
    	            
        return methodArray[n] ;
        }
	@Test 
	public void test() {
		  System.out.println("Testing Stack....");
		for (int k = 0; k < 100000; k++) {
			Stack st = new Stack();
			long randomseed = System.currentTimeMillis();
			Random random = new Random(randomseed);

			for (int i = 0; i < 50; i++) {
				String methodName = StackRandomTest.RandMethod(random);
				int n = StackRandomTest.RandInt(random);
				try {
					if (methodName.equals("push")){
						st.push(n);
						assertTrue(n==st.top());
					}
					else if (methodName.equals("pop")){
						int preSize=st.getSize();
						st.pop();
						assertTrue((preSize-1)==st.getSize());
					}
					else if (methodName.equals("top")){
						int l=st.top();
						assertTrue(l==st.top());
					}
					/// looks like we succeeded. Not good! Fail the test
				} catch (NoSuchElementException e) {
					// that's fine
				} catch (IllegalStateException e) {
					// that's fine
				}

			}
		}
		
		
		
		
	}
	

}
