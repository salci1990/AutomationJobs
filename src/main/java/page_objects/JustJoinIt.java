package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JustJoinIt extends PageObject {

    public JustJoinIt(WebDriver driver){
        super(driver);
    }

    public List<Integer> titlesId = new ArrayList<Integer>();

    @FindBy(className="offers-list")
    private List<WebElement> offerList;

    @FindBy(className="title")
    private List<WebElement> titles;

    @FindBy(className = "ngdialog-close")
    private WebElement close;

    @FindBy(className = "company-name")
    private List<WebElement> companyNames;

    @FindBy(className = "item")
    private List<WebElement> items;

    @FindBy(className = "age")
    private List<WebElement> data;

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

    public void viewElement(){
        System.out.println(offerList);
    }


    public int getNumberOfElements(){
        return titles.size();
    }

    public String getTitle(int index){
        return titles.get(index).getText();
    }

    public WebElement closeWindow(){
        return close;
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

    public List<String> getLinkByIndex(List<WebElement> elements, List<Integer> indexes){
        List<String> text = new ArrayList<String>();

        for (WebElement element: elements) {
            for (Integer index: indexes) {
                if (elements.indexOf(element) == index){
                    text.add(element.getAttribute("href"));
                }
            }
        }

        return text;
    }
}