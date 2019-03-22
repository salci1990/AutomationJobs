package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BuldogJob extends PageObject {

    public BuldogJob(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "results-list")
    private List<WebElement> offerLists;

    @FindBy(className = "result-header-name")
    private List<WebElement> titles;

    @FindBy(className = "pop-black")
    private List<WebElement> companyNames;

    @FindBy(className = "result-header-name")
    private List<WebElement> items;

    @FindBy(className = "inline-block")
    private List<WebElement> data;


    public int getNumberOfElements() {
        return titles.size();
    }

    public String getTitle(int index) {
        return titles.get(index).getText();
    }

    public String getCompanyName(int index) {
        return companyNames.get(index).getText();
    }

    public String getLink(int index) {

        return items.get(index).getAttribute("href");

    }

    public String getData(int index) {
        return data.get(index).getText();
    }
}