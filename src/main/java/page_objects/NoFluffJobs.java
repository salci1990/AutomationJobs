package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NoFluffJobs extends PageObject {

    public NoFluffJobs(WebDriver driver){
        super(driver);
    }

    public List<Integer> titlesId = new ArrayList<Integer>();

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

    @FindBy(className = "new-icon")
    private List<WebElement> dataNew;

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

    public List<WebElement> dataNew(){
        return dataNew;
    }

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

    public List<String> getTextFromDataByIndex(List<String> elements, List<Integer> indexes){
        List<String> text = new ArrayList<String>();

        for (String element: elements) {
            for (Integer index: indexes) {
                if (elements.indexOf(element) == index){
                    text.add(element);
                }
            }
        }

        return text;
    }

    public List<String> getTextFromData(List<WebElement> elements){
        List<String> dataText = new ArrayList<String>();

        for (WebElement element: elements) {
            dataText.add(element.getText());
        }
        return  dataText;
    }
}