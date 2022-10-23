package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {

        typeCity(city);
        click(By.id("dates"));
        //   "10/25/2022"
        // "aaa/fff/r"   ["aaa"] ["fff"] ["r"]
        String[] from =dataFrom.split("/");  /// ["10"] ["25"] ["2022"]  from[1] ="25"

        // String locator1 ="//div[text()=' 25 ']";
        String locator = "//div[text()=' "+from[1]+" ']";

        ///  "how are you, Dolli?"      names [Dolli] [Molli] [lis]
        //  "how are you, "+names[2]+"?"      how are you, lis?

        click(By.xpath(locator));




        // "10/30/2022"
        String [] to = dataTo.split("/");   /// to[1]
        String locator2 = "//div[text()=' "+to[1]+" ']";

        click(By.xpath(locator2));

    }
    public void searchCurrentMonth2(String city, String dataFrom, String dataTo) {

        typeCity(city);
        click(By.id("dates"));

        String[] from =dataFrom.split("/");


        //String locator = "//div[text()=' "+from[1]+" ']";
        String locator = String.format("//div[text()=' %s ']",from[1]) ;
        click(By.xpath(locator));


        String [] to = dataTo.split("/");

        String locator2 =  String.format("//div[text()=' %s ']",to[1]);

        click(By.xpath(locator2));

    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
        //pause(500);
    }

    public boolean isListOfCarsAppeared() {
        pause(1000);
     return isElementPresent(By.xpath("(//div[@class='car-img-container ng-star-inserted'])[4]"));





    }

    public void searchNextMonth(String city, String dataFrom, String dataTo) {
        typeCity(city);
        click(By.id("dates"));
        click(By.xpath("//button[@aria-label='Next month']"));
        logger.info("Click on the button Next month");
        String[] from =dataFrom.split("/");


        //String locator = "//div[text()=' "+from[1]+" ']";
        String locator = String.format("//div[text()=' %s ']",from[0]) ;
        click(By.xpath(locator));


        String [] to = dataTo.split("/");

        String locator2 =  String.format("//div[text()=' %s ']",to[0]);

        click(By.xpath(locator2));

    }


    public void selectAnyPeriod(String city, String dataFrom, String dataTo) {
        typeCity(city);
        click(By.id("dates"));
        LocalDate now=LocalDate.now();
        LocalDate from=LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to=LocalDate.parse(dataTo,DateTimeFormatter.ofPattern("M/d/yyyy"));

        logger.info("year:"+now.getYear());
       logger.info("Day of Month-->:"+now.getDayOfMonth());
         logger.info("Month value-->"+now.getMonthValue());
//String [] from=dataFrom.split("/");from[2]="2022";
//int diffYear=Integer.parseInt(from[2])-now.getYear();

int diffYear;
int diffMonth;
diffYear=from.getYear()-now.getYear();
if(diffYear==0){
    diffMonth=from.getMonthValue()-now.getMonthValue();

}else{
    diffMonth=12- now.getMonthValue()+from.getMonthValue();
}
clickNextMonth(diffMonth);
String locator=String.format("//div[text()=' %s ']",from.getDayOfMonth());
click(By.xpath(locator));
diffYear=to.getYear()- from.getYear();
if (diffYear==0){
    diffMonth=to.getMonthValue()- from.getMonthValue();

}else {
    diffMonth=12-from.getMonthValue()+to.getMonthValue();

}
clickNextMonth(diffMonth);
locator=String.format("//div[text()=' %s ']",to.getDayOfMonth());
click(By.xpath(locator));

    }

    private void clickNextMonth(int count) {
        for (int i=0;i<count;i++ ){
            click(By.xpath("//button[@aria-label='Next month']"));
        }


    }

    public boolean isDataCorrect(String from, String to) {
        WebElement element=wd.findElement(By.cssSelector("input[aria-haspopup='dialog']"));
        System.out.println(element.getText());

        return true ;

    }

    public void typePeriodInPast(String city, String from, String to) {
        typeCity(city);
        click(By.id("dates"));
        click(By.cssSelector(".cdk-overlay-container"));
        type(By.id("dates"),from+"-"+to);
        click(By.cssSelector(".cdk-overlay-container"));

    }
}