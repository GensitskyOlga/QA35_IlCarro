import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    
    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }
    @Test
    public void registrationSuccess(){
        System.out.println(System.currentTimeMillis());
        int i=(int) (System.currentTimeMillis()/1000)%3600;
        User user=new User().withName("Nina").withLastname("Ivanov").withEmail("nin"+i+"@gmail.com").withPassword("Nina123$");
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().withName("Nina").withLastname("Ivanov").withEmail("nina@mail.com").withPassword("nina");
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    
}
