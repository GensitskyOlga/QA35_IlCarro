import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("gensitskaya@bk.ru").withPassword("Od123456$"));
        }



    }
    @Test
    public void addCarSuccess(){
        Random random=new Random();
       int i= random.nextInt(1000)+100;

        Car car= Car.builder()
                .location("Haifa,Israel")
                .make("BMW")
                .model("M5")
                .year("2021")
                .engine("2,5")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("4")
                .seats("4")
                .clasS("T")
                .fuelConsumption("6,5")
                .carRegistrationNumber("115-25-"+i)
                .price("65")
                .distanceIncluded("500")
                .features("Type of features")
                .about("very good")
                .build();
app.helperCar().openCarForm();
app.helperCar().fillCarForm(car);
app.helperCar().attachPhoto("C:\\Users\\olga\\QA35\\QA35_IlCarro\\src\\test\\resources\\BMW.jpeg");


        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
    }

    @AfterMethod
    public void posCondition(){
        app.helperCar().returnToHomePage();
    }
}
