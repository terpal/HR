package hr.softeng.com;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class test_ {

	private static HRModel model;  

	@Test
	public void test() {
		HRModel tester = new HRModel();
		Exception ex = null;
		try {
			tester.Login("nikos1", "1234");
	    } catch (Exception e) {
	        ex  = e;
	    }
	    assertEquals(null,ex);
	}
	
	@BeforeClass
		public static void testSetup(){
		model.dbconnect();
		System.out.println("Starting...");
	}
	
	@AfterClass
		public static void testCleanup(){
		model.disConn();
		System.out.println("Done!");
	}
}