package Olympics;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlimpicWithMap {
	WebDriver driver;
	String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.";
	Map<String, Integer> goldMap = new HashMap<>();
	int max = 0;
	String str;

	@BeforeClass
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("goes to website");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void SortTest() throws InterruptedException {

		List<WebElement> firstColumn = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		List<Integer> actualRanking = new ArrayList();

		for (int i = 0; i < firstColumn.size() - 1; i++) {
			actualRanking.add(Integer.parseInt(firstColumn.get(i).getText()));
		}

		List<Integer> expectedRanking = new ArrayList<Integer>(actualRanking);
		Collections.sort(expectedRanking);

		assertEquals(actualRanking, expectedRanking);

		driver.findElement(By.xpath("//table/thead/tr//th[.='NOC']")).click();

		List<WebElement> NOC = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<String> actualNOC = new ArrayList();

		for (int i = 0; i < actualNOC.size() - 1; i++) {
			actualNOC.add(NOC.get(i).getText());
		}

		List<String> expectedNOCRanking = new ArrayList<String>(actualNOC);
		Collections.sort(expectedNOCRanking);
		assertEquals(actualNOC, expectedNOCRanking);
		System.out.println("actualNOC" + actualNOC.toString());
		System.out.println("expectedNOCRanking" + expectedNOCRanking.toString());

		firstColumn = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		actualRanking = new ArrayList();

		for (int i = 0; i < firstColumn.size() - 1; i++) {
			actualRanking.add(Integer.parseInt(firstColumn.get(i).getText()));

		}

		expectedRanking = new ArrayList<Integer>(actualRanking);
		Collections.sort(expectedRanking);

		assertNotEquals(actualRanking, expectedRanking);

	}

	@Test(priority = 2)
	public void goldMedals() throws InterruptedException {
		Thread.sleep(1000);

		List<WebElement> actualGold = driver.findElements(
				By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)/td[2]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		// Map <String, Integer> goldMap = new HashMap<>();
		// Map<String, List<String>> map1 = new HashMap<String, List<String>>();
		// This is one instance of the map you want to store in the above list.

		for (int i = 0; i < names.size() - 1; i++) {

			goldMap.put(names.get(i).getText(), Integer.parseInt(actualGold.get(i).getText()));
			
			max = Collections.max(goldMap.values());
			for (Entry<String, Integer> each : goldMap.entrySet()) {
				if (each.getValue() == max) {
					str = each.getKey();
					System.out.println(str);

					assertEquals(str, " United States (USA)");

				}

			}
			
			// for (Entry<String, Integer> each: goldMap.entrySet()){
			// System.out.println(each.getValue());
			//

			// List<String> arraylist1 = new ArrayList<String>();
			// arraylist1.add("Text1");//And so on..
			// map1.put("key1",arraylist1);
			//

		}
	}
}
