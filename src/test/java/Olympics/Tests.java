package Olympics;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tests {
	WebDriver driver;
	WebElement max;
	WebElement maxGold;
	WebElement maxsilver;
	WebElement maxbronze;
	int rowOfchusenCountry;

	Random random = new Random();

	String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.";

	@BeforeMethod
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

		if (!actualGold.isEmpty())
			maxGold = actualGold.get(0);
		else

			for (int i = 0; i < actualGold.size() - 1; i++) {
				WebElement each = actualGold.get(i);

				if (Integer.parseInt(each.getText().trim()) > Integer.parseInt(max.getText().trim())) {
					maxGold = each;
				}
			}

		int maxi = actualGold.indexOf(maxGold);
		System.out.println("Gold max: " + maxGold.getText() + " ");

		assertEquals(names.get(maxi).getText(), " United States (USA)");

	}

	@Test(priority = 3)
	public void silverMedals() throws InterruptedException {

		Thread.sleep(1000);

		List<WebElement> actualGold = driver.findElements(
				By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)/td[3]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		if (!actualGold.isEmpty())
			maxsilver = actualGold.get(0);
		else

			for (int i = 0; i < actualGold.size() - 1; i++) {
				WebElement each = actualGold.get(i);

				if (Integer.parseInt(each.getText().trim()) > Integer.parseInt(maxsilver.getText().trim())) {
					maxsilver = each;
				}
			}

		int maxi = actualGold.indexOf(maxsilver);

		System.out.println("Silver max: " + maxsilver.getText() + " ");

		assertEquals(names.get(maxi).getText(), " United States (USA)");

	}

	@Test(priority = 4)
	public void bronzeMedals() throws InterruptedException {

		Thread.sleep(1000);

		List<WebElement> actualGold = driver.findElements(
				By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)/td[4]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		if (!actualGold.isEmpty())
			maxbronze = actualGold.get(0);
		else
			;

		for (int i = 0; i < actualGold.size() - 1; i++) {
			WebElement each = actualGold.get(i);

			if (Integer.parseInt(each.getText().trim()) > Integer.parseInt(maxbronze.getText().trim())) {
				maxbronze = each;
			}
		}

		int maxi = actualGold.indexOf(maxbronze);
		System.out.println("Bronze max: " + maxbronze.getText() + " ");

		assertEquals(names.get(maxi).getText(), " United States (USA)");

	}

	@Test(priority = 5)
	public void mostMedalsOveral() throws InterruptedException {
		Thread.sleep(1000);

		List<WebElement> actualGold = driver.findElements(
				By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)/td[5]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		if (!actualGold.isEmpty())
			max = actualGold.get(0);
		else

			for (int i = 0; i < actualGold.size() - 1; i++) {
				WebElement each = actualGold.get(i);

				if (Integer.parseInt(each.getText().trim()) > Integer.parseInt(max.getText().trim())) {
					max = each;
				}
			}

		int maxi = actualGold.indexOf(max);
		System.out.println("Overall: " + max.getText() + " ");

		assertEquals(names.get(maxi).getText(), " United States (USA)");

	}

	@Test(priority = 6)
	public void silverEqual18() throws InterruptedException {
		Thread.sleep(1000);
		boolean result18;
		List<WebElement> actualGold = driver.findElements(
				By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr)/td[3]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < actualGold.size() - 1; i++) {
			WebElement each = actualGold.get(i);

			if (Integer.parseInt(each.getText().trim()) == 18) {
				result.add(names.get(actualGold.indexOf(each)).getText());
				System.out.println("contryis with 18" + names.get(actualGold.indexOf(each)).getText());
			}
		}

		List<String> expected = Arrays.asList(" China (CHN)", " France (FRA)");
		for (int i = 0; i < result.size(); i++) {

			if (!result.get(i).equals(expected.get(i)))
				;
			result18 = true;
			assertTrue(result18);

		}
	}

	@Test(priority = 6)
	public void GetIndex() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> firstColumn = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		int index = random.nextInt(9) + 0;

		System.out.println(index);

		String chusenCountry = names.get(index).getText();

		System.out.println("choosen country" + chusenCountry + "Total count of countrys: " + names.size());

		for (int i = 0; i < names.size() - 1; i++) {

			if (names.get(i).getText().equals(chusenCountry)) {
				System.out.println("choosen country in loop " + names.get(i).getText());
				System.out.println(names.get(i).getText().equals(chusenCountry));

				rowOfchusenCountry = i + 1;
				System.out.println("choosen row: " + rowOfchusenCountry + "i:" + i);
			}

		}
		System.out.println("rowOfchusenCountry :" + rowOfchusenCountry);
		assertEquals(String.valueOf(rowOfchusenCountry), firstColumn.get(index).getText().trim());

	}

	@Test(priority = 6)
	public void GetSum18Bronze() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> ActualBronze = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<WebElement> names = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
		List<String> result = new ArrayList<String>();
		List<String> resultINT = new ArrayList<String>();

		List<Integer> resultunder18 = new ArrayList<Integer>();
		int sumOf18 = 18;
		int anotherLower18 = 0;
		int eachLower18 = 0;

		for (int i = 0; i < ActualBronze.size() - 1; i++) {

			WebElement each = ActualBronze.get(i);
			if (Integer.parseInt(each.getText().trim()) < sumOf18) {
				result.add(names.get(ActualBronze.indexOf(each)).getText());
				resultINT.add(each.getText().trim());
				System.out.println(names.get(ActualBronze.indexOf(each)).getText());
				System.out.println(resultINT);
			}
		}
		Outerloop: for (int i = 0; i < resultINT.size(); i++) {
			eachLower18 = Integer.parseInt(resultINT.get(i));
			System.out.println("outer loop:" + Integer.parseInt(resultINT.get(i)));
			for (int j = 0; j < resultINT.size(); j++) {
				anotherLower18 = Integer.parseInt(resultINT.get(j));

				System.out.println("inner loop:" + Integer.parseInt(resultINT.get(j)));
				if (eachLower18 + anotherLower18 == sumOf18 && i != j) {

					resultunder18.add(eachLower18);
					resultunder18.add(anotherLower18);
					System.out.println(eachLower18 + anotherLower18);
					System.out.println(resultunder18);
					break Outerloop;
				}
			}
		}
		result = new ArrayList();
		
		for (int q = 0; q < ActualBronze.size() - 1; q++) {

			WebElement each = ActualBronze.get(q);
			if (Integer.parseInt(each.getText().trim()) == eachLower18
					|| Integer.parseInt(each.getText().trim()) == anotherLower18) {

				result.add(names.get(ActualBronze.indexOf(each)).getText());
				System.out.println(names);
			}
		}

		List<String> expected = Arrays.asList(" Italy (ITA)", " Australia (AUS)");

		assertEquals(result, expected);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();

	}
}
