package firsttestngpackage;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class automateTestngClass {

	public static void main(String[] args) {
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { SecondTestNGFile.class });
		testng.addListener(tla);
		testng.run();

	}

}
