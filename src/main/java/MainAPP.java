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
        int numberOfElements = justJoinIt.titlesId.size();

        writer.println("JUSTJOINIT");
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr()
                + StringDecorator.th("Stanowisko")
                + StringDecorator.th("Firma")
                + StringDecorator.th("Data")
                + StringDecorator.th("Link")
                + StringDecorator.closeTr());
        for (int i = 0; i < numberOfElements; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(titles.get(i))
                    + StringDecorator.td(companies.get(i).replace("\uE0AF", ""))
                    + StringDecorator.td(data.get(i))
                    + StringDecorator.td(StringDecorator.openLink() + links.get(i)
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

        List<WebElement> listOfData = new ArrayList<WebElement>();
        listOfData.addAll(noFluffJobs.dataNew());
        listOfData.addAll(noFluffJobs.data());

        List<String> dates = noFluffJobs.getTextByIndex(listOfData, noFluffJobs.titlesId);

        int numberOfElements = noFluffJobs.titlesId.size();

        writer.println("NOFLUFFJOBS");
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr());
        writer.println(StringDecorator.th("Stanowisko")
                + StringDecorator.th("Firma")
                + StringDecorator.th("Data")
                + StringDecorator.th("Link")
                + StringDecorator.closeTr());

        for (int i = 0; i < numberOfElements; i++) {

            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(titles.get(i))
                    + StringDecorator.td(companies.get(i).replace("@", ""))
                    + StringDecorator.td(dates.get(i))
                    + StringDecorator.td(StringDecorator.openLink() + links.get(i)
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
        int numberOfElements = buldogJob.titlesId.size();

        writer.println("BULDOGJOB");
        sleep(1000);
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr() + StringDecorator.th("Stanowisko")
                + StringDecorator.th("Firma")
                + StringDecorator.th("Dodano")
                + StringDecorator.th("Link")
                + StringDecorator.closeTr());
        for (int i = 0; i < numberOfElements; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(titles.get(i))
                    + StringDecorator.td(companies.get(i))
                    + StringDecorator.td(data.get(i))
                    + StringDecorator.td(StringDecorator.openLink() + links.get(i)
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