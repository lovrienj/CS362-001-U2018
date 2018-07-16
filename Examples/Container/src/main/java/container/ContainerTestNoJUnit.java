package container;
/** A class to test the class container. */
public class ContainerTestNoJUnit {
	 /** Runs the tests. */
	 public static void main(String[] args) {
		 Container c=new Container();
		 int r=c.put(0);
		 System.out.println("r= "+r +" , "+ c.size());
		 r=c.put(0);
		 System.out.println("r= "+r +" , "+ c.size());
		 r=c.remove(0);
		 System.out.println("r= "+r +" , "+ c.size());
	 }
}
