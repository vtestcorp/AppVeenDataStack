package intern;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helperMethods.JsonUtils;

public class Names {
	public static void main(String[] args) 

	{

		System.setProperty("webdriver.chrome.driver","C:\\SDET\\Appveen\\AppVeenDataStack\\driver\\chromedriver.exe"); 
		WebDriver wd = new ChromeDriver();

		wd.get("https://qa.ds.appveen.com/author/#/auth");

		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.findElement(By.id("username")).sendKeys("test_ui_appadmin@appveen.com");
		wd.findElement(By.xpath("//body/odp-root[1]/odp-login[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/form[1]/div[1]/div[2]/button[1]")).click();

		wd.findElement(By.xpath("//input[@id='password']")).sendKeys("Veen@99%win");
		wd.findElement(By.xpath("//body/odp-root[1]/odp-login[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/form[1]/div[1]/div[2]/button[1]")).click();

		wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-service-manager[1]/div[1]/div[1]/div[2]/button[1]")).click();
		wd.findElement(By.xpath("//input[@id='name']")).sendKeys("internstring");
		wd.findElement(By.xpath("//button[@id='newServiceModalYes']")).click();


		JSONArray c = JsonUtils.getArrayValues("C:\\SDET\\Appveen\\AppVeenDataStack\\testData\\string_Text.json","definition");

		String g;
		String f;
		String min;
		String max;
		String pat;
		String des;
		String def;
		
		for (int i = 1; i < c.size(); i++)
		{

			JSONObject obj = (JSONObject) c.get(i);

			JSONObject d = (JSONObject) obj.get("properties");

			//System.out.println(d.get("name"));

			String e = d.get("name").toString();



			System.out.println(e);


			wd.findElement(By.xpath("//span[contains(text(),'New Attribute')]")).click();

			for(int j=i; j <= i; j++)
			{
				if(j>1) 
				{
					wd.findElement(By.xpath("/html[1]/body[1]/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[" + j + "]/odp-structure-field[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).click();
					wd.findElement(By.xpath("/html[1]/body[1]/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[" + j + "]/odp-structure-field[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(e);



					if(d.get("label")==null) 
					{

					}
					else
					{
						g = d.get("label").toString();
						System.out.println(g);


						wd.findElement(By.xpath("//input[@id='label']")).sendKeys(g);
					}

					
					if(d.get("errorMessage")==null) 
					{

					}
					else
					{
						f = d.get("errorMessage").toString();
						System.out.println(f);


						wd.findElement(By.xpath("//input[@id='errorMessage']")).sendKeys(f);
					}
					
					
					
					if(d.get("required")==null) {

					} else 
					{
						wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/odp-structure-field-properties[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/span[1]")).click();

					}
					
					
					if(d.get("createOnly")==null) 
					{

					} 
					else 
					{
						wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/odp-structure-field-properties[1]/div[1]/div[2]/div[1]/div[1]/div[2]/label[1]/span[1]")).click();

					}
					
					if(d.get("unique")==null) 
					{

					} 
					else 
					{
						wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/odp-structure-field-properties[1]/div[1]/div[2]/div[1]/div[1]/div[3]/label[1]/span[1]")).click();

					}
					
					if(d.get("minlength")==null) 
					{

					} 
					else 
					{
						min = d.get("minlength").toString();
						System.out.println(min);


						wd.findElement(By.xpath("//input[@id='_minLength']")).sendKeys(min);

					}
					
					if(d.get("maxlength")==null) 
					{

					} 
					else 
					{
						max = d.get("maxlength").toString();
						System.out.println(max);


						wd.findElement(By.xpath("//input[@id='_maxLength']")).sendKeys(max);

					}
					
					if(d.get("pattern")==null) 
					{

					} 
					else 
					{
						pat = d.get("pattern").toString();
						System.out.println(pat);

						wd.findElement(By.xpath("//input[@id='_pattern']")).sendKeys(pat);
					}
					
					if(d.get("_description")==null) 
					{

					} 
					else 
					{
						des = d.get("_description").toString();
						System.out.println(des);

						wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/odp-structure-field-properties[1]/div[1]/div[2]/div[1]/div[7]/div[2]/textarea[1]")).sendKeys(des);
					}
					
					if(d.get("readonly")==null) 
					{

					} 
					else 
					{
						wd.findElement(By.xpath("//body/odp-root[1]/odp-apps[1]/div[1]/div[2]/div[2]/odp-schema-builder[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[" +j+ "]/odp-structure-field[1]/div[1]/div[1]/div[2]/div[1]/span[1]")).click();
					}
					
					
					if(d.get("default")==null) 
					{

					} 
					else 
					{
						def = d.get("default").toString();
						System.out.println(def);

						wd.findElement(By.xpath("//input[@id='_default']")).sendKeys(def);
					}
					
					
				}
			}


		}
	}

}
