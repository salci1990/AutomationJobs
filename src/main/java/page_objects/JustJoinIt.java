package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class JustJoinIt extends PageObject {

    public JustJoinIt(WebDriver driver){
        super(driver);
    }

    @FindBy(className="offers-list")
    private List<WebElement> offerList;

    @FindBy(className="title")
    private List<WebElement> title;

    @FindBy(className = "ngdialog-close")
    private WebElement close;

    @FindBy(className = "company-name")
    private List<WebElement> companyName;

    @FindBy(className = "item")
    private List<WebElement> items;

    @FindBy(className = "age")
    private List<WebElement> data;

    public void viewElement(){
        System.out.println(offerList);
    }


    public int getNumberOfElements(){
        return title.size();
    }

    public String getTitle(int index){
        return title.get(index).getText();
    }

    public WebElement closeWindow(){
        return close;
    }

    public String getCompanyName(int index){
        return companyName.get(index).getText();
    }

    public String getLink(int index){

        return items.get(index).getAttribute("href");
    }

    public String getData(int index) {
        return data.get(index).getText();
    }
}
