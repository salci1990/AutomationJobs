import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.BuldogJob;
import page_objects.JustJoinIt;
import page_objects.NoFluffJobs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAPP {

    private WebDriver driver;
    private WebDriverWait wait;
    private JustJoinIt justJoinIt;
    private NoFluffJobs noFluffJobs;
    private PrintWriter writer;
    private BuldogJob buldogJob;

    {
        try {
            writer = new PrintWriter("TesterOffers.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        justJoinIt = new JustJoinIt(driver);
        noFluffJobs = new NoFluffJobs(driver);
        buldogJob = new BuldogJob(driver);
        writer.println(StringDecorator.DOCType());
        writer.println(StringDecorator.openHeadAndTitle() + "TesterProgramuje.pl"
                + StringDecorator.closeHeadAndTitle());

    }

    @AfterClass
    public void tearDown() {
        writer.println(StringDecorator.closeHTML());
        writer.close();
        driver.quit();
    }

    @Test
    public void getJustJoinItTest() {
        driver.get("https://justjoin.it/all/testing");
        driver.manage().window().maximize();
        Actions builder = new Actions(driver);
        builder.moveToElement(justJoinIt.closeWindow()).click();

        justJoinIt.getAutomationIndex(justJoinIt.titles());
        List<String> titles = justJoinIt.getTextByIndex(justJoinIt.titles(), justJoinIt.titlesId);
        List<String> companies = justJoinIt.getTextByIndex(justJoinIt.companies(), justJoinIt.titlesId);
        List<String> links = justJoinIt.getLinkByIndex(justJoinIt.links(), justJoinIt.titlesId);
        List<String> data = justJoinIt.getTextByIndex(justJoinIt.data(), justJoinIt.titlesId);

        List<String> shortTitle = new ArrayList<>();
        for (String title: titles) {
            if(title.length() > 17){
                title = title.substring(0, 17);
                shortTitle.add(title + "...");
            }else{
                shortTitle.add(title);
            }
        }

        List<String> shortCompanies = new ArrayList<>();
        for (String company: companies) {
            if(company.length() > 10){
                company = company.substring(0, 10);
                shortCompanies.add(company);
            }else{
                shortCompanies.add(company);
            }
        }


        List<String> cities = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        for (String link: links) {
            driver.get(link);
            sleep(2000);

            WebElement city = driver.findElement(By.className("offer-address"));
            WebElement salary = driver.findElement(By.className("salary-row"));
            cities.add(city.getText());
            prices.add(salary.getText());
        }

        int numberOfElements = justJoinIt.titlesId.size();

        writer.println(StringDecorator.h1("JUSTJOINIT"));
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr() + StringDecorator.th25("Stanowisko")
                + StringDecorator.th15("Firma")
                + StringDecorator.th10("Dodano")
                + StringDecorator.th15("Lokalizacja")
                + StringDecorator.th20("Oferta")
                + StringDecorator.th10("Link")
                + StringDecorator.closeTr());

            for (int i = 0; i < numberOfElements; i++) {

                List<String> city = new ArrayList<>(Arrays.asList(cities.get(i).split(",")));

                writer.println(StringDecorator.openTr()
                        + StringDecorator.td25(shortTitle.get(i))
                        + StringDecorator.td15(shortCompanies.get(i).replace("\uE0AF", ""))
                        + StringDecorator.td10(data.get(i))
                        + StringDecorator.td15(city.get(city.size()-1))
                        + StringDecorator.td20(prices.get(i)
                        .replace("net/month", "")
                        .replace("gross/month", "")
                        .replace("PLN", "")
                        .replace(" ", ""))
                        + StringDecorator.td10(StringDecorator.openLink() + links.get(i)
                        + StringDecorator.closeLink())
                        + StringDecorator.closeTr());
        }
        writer.println(StringDecorator.closeTable());
    }

    @Test
    public void getNoFluffJobsTest() {
        driver.get("https://nofluffjobs.com/jobs/testing");
        driver.manage().window().maximize();
        sleep(2000);

        noFluffJobs.getAutomationIndex(noFluffJobs.titles());
        List<String> titles = noFluffJobs.getTextByIndex(noFluffJobs.titles(), noFluffJobs.titlesId);
        List<String> companies = noFluffJobs.getTextByIndex(noFluffJobs.companies(), noFluffJobs.titlesId);
        List<String> links = noFluffJobs.getLinkByIndex(noFluffJobs.links(), noFluffJobs.titlesId);

        List<String> shortTitle = new ArrayList<>();
        for (String title: titles) {
            if(title.length() > 17){
                title = title.substring(0, 17);
                shortTitle.add(title + "...");
            }else{
                shortTitle.add(title);
            }
        }

        List<String> shortCompanies = new ArrayList<>();
        for (String company: companies) {
            if(company.length() > 10){
                company = company.substring(0, 10);
                shortCompanies.add(company);
            }else{
                shortCompanies.add(company);
            }
        }

        List<WebElement> listOfData = new ArrayList<WebElement>();
        listOfData.addAll(noFluffJobs.dataNew());
        listOfData.addAll(noFluffJobs.data());

        List<String> dates = noFluffJobs.getTextByIndex(listOfData, noFluffJobs.titlesId);

        List<String> cities = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        for (String link: links) {
            driver.get(link);
            sleep(2000);

            WebElement city = driver.findElement(By.xpath("//span[contains(@class, 'location-label')]"));
            WebElement salary = driver.findElement(By.xpath("//div[contains(@class, 'row')]/div/h4"));
            cities.add(city.getText());
            prices.add(salary.getText());
        }

        int numberOfElements = noFluffJobs.titlesId.size();

        writer.println(StringDecorator.h1("NOFLUFFJOBS"));
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr() + StringDecorator.th25("Stanowisko")
                + StringDecorator.th15("Firma")
                + StringDecorator.th10("Dodano")
                + StringDecorator.th15("Lokalizacja")
                + StringDecorator.th20("Oferta")
                + StringDecorator.th10("Link")
                + StringDecorator.closeTr());

        for (int i = 0; i < numberOfElements; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td25(shortTitle.get(i))
                    + StringDecorator.td15(shortCompanies.get(i)
                    .replace("@", ""))
                    + StringDecorator.td10(dates.get(i))
                    + StringDecorator.td15(cities.get(i)
                    .replace("and", "")
                    .replace(", Pol", ""))
                    + StringDecorator.td20(prices.get(i)
                    .replace("PLN", "")
                    .replace(" ", ""))
                    + StringDecorator.td10(StringDecorator.openLink() + links.get(i)
                    + StringDecorator.closeLink())
                    + StringDecorator.closeTr());
        }

        writer.println(StringDecorator.closeTable());
    }

    @Test
    public void getBuldogJobTest() {
        driver.get("https://bulldogjob.pl/companies/jobs/s/job_type,tester");
        driver.manage().window().maximize();
        buldogJob.getAutomationIndex(buldogJob.titles());
        List<String> titles = buldogJob.getTextByIndex(buldogJob.titles(), buldogJob.titlesId);
        List<String> companies = buldogJob.getTextByIndex(buldogJob.companies(), buldogJob.titlesId);
        List<String> links = buldogJob.getLinkByIndex(buldogJob.links(), buldogJob.titlesId);
        List<String> data = buldogJob.getTextByIndex(buldogJob.data(), buldogJob.titlesId);

        List<String> shortTitle = new ArrayList<>();
        for (String title: titles) {
            if(title.length() > 17){
                title = title.substring(0, 17);
                shortTitle.add(title + "...");
            }else{
                shortTitle.add(title);
            }
        }

        List<String> shortCompanies = new ArrayList<>();
        for (String company: companies) {
            if(company.length() > 10){
                company = company.substring(0, 10);
                shortCompanies.add(company);
            }else{
                shortCompanies.add(company);
            }
        }

        List<String> cities = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        for (String link: links) {
            driver.get(link);
            sleep(2000);

            WebElement city = driver.findElement(By.xpath("//div[contains(@class, 'offer-header')]/div/ul/li[2]"));
            WebElement salary = driver.findElement(By.xpath("//div[contains(@class, 'offer-header')]/div/ul/li[3]"));
            cities.add(city.getText());
            prices.add(salary.getText());
        }

        int numberOfElements = buldogJob.titlesId.size();

        writer.println(StringDecorator.h1("BULLDOGJOB"));
        sleep(1000);
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr() + StringDecorator.th25("Stanowisko")
                + StringDecorator.th15("Firma")
                + StringDecorator.th10("Dodano")
                + StringDecorator.th15("Lokalizacja")
                + StringDecorator.th20("Oferta")
                + StringDecorator.th10("Link")
                + StringDecorator.closeTr());
        for (int i = 0; i < numberOfElements; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td25(shortTitle.get(i))
                    + StringDecorator.td15(shortCompanies.get(i))
                    + StringDecorator.td10(data.get(i))
                    + StringDecorator.td15(cities.get(i))
                    + StringDecorator.td20(prices.get(i)
                    .replace("Umowa dowolna", "n/a")
                    .replace("Umowa o pracę", "n/a")
                    .replace("netto / godzina", "")
                    .replace("netto / miesiąc", "")
                    .replace("netto / dzień", "")
                    .replace("PLN", "")
                    .replace(" ", ""))
                    + StringDecorator.td10(StringDecorator.openLink() + links.get(i)
                    + StringDecorator.closeLink())
                    + StringDecorator.closeTr());
        }
        writer.println(StringDecorator.closeTable());
    }

    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}