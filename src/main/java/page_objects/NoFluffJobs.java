package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NoFluffJobs extends PageObject {

    public NoFluffJobs(WebDriver driver){
        super(driver);
    }

    @FindBy(className="category-offers")
    private List<WebElement> offerLists;

    @FindBy(className="posting-title")
    private List<WebElement> titles;

    @FindBy(className = "posting-company")
    private List<WebElement> companyNames;

    @FindBy(className = "list-item")
    private List<WebElement> items;

    @FindBy(className= "date")
    private List<WebElement> data;

    public void viewElement(){
        System.out.println(offerLists);
    }


    public int getNumberOfElements(){
        return titles.size();
    }

    public String getTitle(int index){
        return titles.get(index).getText();
    }

    public String getCompanyName(int index){
        return companyNames.get(index).getText();
    }

    public String getLink(int index){

        return items.get(index).getAttribute("href");
    }

    public String getData(int index) {
            return data.get(index).getText();
    }
}