import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("gensitskaya@bk.ru", "Od123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessModels() {

        User user = new User().withEmail("gensitskaya@bk.ru").withPassword("Od123456$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    //@Test
   // public void negativeWrongEmail() {

       // User user = new User().withEmail("gensitskayabk.ru").withPassword("Od123456$");
       // app.getHelperUser().openLoginFormHeader();
        //app.getHelperUser().fillLoginForm(user);
        //app.getHelperUser().submit();

   // }





    @Test
    public void negativeWrongPassword() {
        User user = new User().withEmail("gensitskaya@bk.ru").withPassword("123$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Wrong email or password");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
