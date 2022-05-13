package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.Courses;
import Pages.ErrorCapture;
import Pages.LangLearn;


public class AllTest {
	
	@Test
	public void testing() throws Exception
	{
		Courses ti= new Courses();
		LangLearn b= new LangLearn();
		ErrorCapture s= new ErrorCapture();
		b.driverSetup();
	    b.openUrl();
		ti.find();
		b.openUrl();
		b.learningLang();
		b.openUrl();
		s.error();
		b.closeBrowser();
	}

}
