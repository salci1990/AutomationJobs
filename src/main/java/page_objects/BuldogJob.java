package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BuldogJob extends PageObject {

    public BuldogJob(WebDriver driver) {
        super(driver);
    }

    public List<Integer> titlesId = new ArrayList<Integer>();

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

    public List<WebElement> titles(){
        return titles;
    }

    public List<WebElement> companies(){
        return companyNames;
    }

    public List<WebElement> links(){
        return items;
    }

    public List<WebElement> data(){
        return data;
    }

    public String getCompanyName(int index) {
        return companyNames.get(index).getText();
    }

    public String getLink(int index) {

        return items.get(index).getAttribute("href");

    }

    public void getAutomationIndex(List<WebElement> titles ){
        for (WebElement title : titles) {

            String titlestr = title.getText();

            if(titlestr.toLowerCase().contains("automatyczny") ||
                    titlestr.toLowerCase().contains("automation") ||
                    titlestr.toLowerCase().contains("automatyzujący")) {
                titlesId.add(titles.indexOf(title));
            }
        }
    }

    public List<String> getTextByIndex(List<WebElement> elements, List<Integer> indexes){

        return indexes.stream()
                .filter(i -> i < elements.size())
                .map(elements::get)
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

    public List<String> getLinkByIndex(List<WebElement> elements, List<Integer> indexes) {
        return indexes.stream()
                .filter(i -> i < elements.size())
                .map(elements::get)
                .map(element -> element.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public String getData(int index) {
        return data.get(index).getText();
    }
}