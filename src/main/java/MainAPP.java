import org.openqa.selenium.WebDriver;
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
        writer.println(StringDecorator.openHeadAndTitle() + "TesterProgramuje.pl" + StringDecorator.closeHeadAndTitle());

    }

    @AfterClass
    public void tearDown() {
        //sleep(2000);
        writer.close();
        driver.quit();
    }

    @Test
    public void getJustJoinItTest() {
        driver.get("https://justjoin.it/all/testing");
        driver.manage().window().maximize();
        Actions builder = new Actions(driver);
        builder.moveToElement(justJoinIt.closeWindow()).click();

        int numberOfElements1 = justJoinIt.getNumberOfElements();
        writer.println("JUSTJOINIT");
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr()
                + StringDecorator.th("Stanowisko") + StringDecorator.th("Oferta")
                + StringDecorator.th("Dodano") + StringDecorator.th("Link")
                + StringDecorator.closeTr());
        for (int i = 0; i < numberOfElements1; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(StringDecorator.isAutomation(justJoinIt.getTitle(i)))
                    + StringDecorator.td(justJoinIt.getCompanyName(i).replace("\uE0AF", ""))
                    + StringDecorator.td(justJoinIt.getData(i))
                    + StringDecorator.td(StringDecorator.openLink() + justJoinIt.getLink(i)
                    + StringDecorator.closeLink())
                    + StringDecorator.closeTr());
        }
        writer.println(StringDecorator.closeTable());
    }

    @Test
    public void getNoFluffJobsTest() {
        driver.get("https://nofluffjobs.com/jobs/testing");
        driver.manage().window().maximize();
        sleep(1500);
        int numberOfElements2 = noFluffJobs.getNumberOfElements();

        writer.println("NOFLUFFJOBS");
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr());
        writer.println(StringDecorator.th("Stanowisko")
                + StringDecorator.th("Oferta")
                + StringDecorator.th("Link")
                + StringDecorator.closeTr());

        for (int i = 0; i < numberOfElements2; i++) {

            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(StringDecorator.isAutomation(noFluffJobs.getTitle(i)))
                    + StringDecorator.td(noFluffJobs.getCompanyName(i).replace("@", ""))
                    + StringDecorator.td(StringDecorator.openLink() + noFluffJobs.getLink(i)
                    + StringDecorator.closeLink())
                    + StringDecorator.closeTr());
        }

        writer.println(StringDecorator.closeTable());
    }

    @Test
    public void getBuldogJobTest() {
        driver.get("https://bulldogjob.pl/companies/jobs/s/job_type,tester");
        driver.manage().window().maximize();
        int numberOfElements3 = buldogJob.getNumberOfElements();
        writer.println("BULDOGJOB");
        sleep(1000);
        writer.println(StringDecorator.openTable());
        writer.println(StringDecorator.openTr() + StringDecorator.th("Stanowisko")
                + StringDecorator.th("Oferta")
                + StringDecorator.th("Dodano")
                + StringDecorator.th("Link")
                + StringDecorator.closeTr());
        for (int i = 0; i < numberOfElements3; i++) {
            writer.println(StringDecorator.openTr()
                    + StringDecorator.td(StringDecorator.isAutomation(buldogJob.getTitle(i)))
                    + StringDecorator.td(buldogJob.getCompanyName(i))
                    + StringDecorator.td(buldogJob.getData(i))
                    + StringDecorator.td(StringDecorator.openLink() + buldogJob.getLink(i)
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