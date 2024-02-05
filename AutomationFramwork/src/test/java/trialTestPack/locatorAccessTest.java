package trialTestPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class locatorAccessTest {
	public static Properties locatorString;
	public static void main(String[] args)
	{
		fetchPropertyFile("LogIn");
	}
	
	public static void initProperty()
	{
		
		File locFile = new File(System.getProperty("user.dir")+"/src/main/resources/propertyfiles/locators.properties");
		try {
			FileInputStream locInput = new FileInputStream(locFile);
			locatorString = new Properties();
			locatorString.load(locInput);
			locInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fetchPropertyFile(String label)
	{
		initProperty();
		String labelText = locatorString.getProperty(label);
		System.out.println(labelText);
	}
}
