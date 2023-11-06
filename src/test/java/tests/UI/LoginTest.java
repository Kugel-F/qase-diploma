package tests.UI;

import jdk.jfr.Description;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.LoginPageService;
import service.ProjectsPageService;
import tests.base.BaseTest;

import static page.LoginPage.EMAIL_LABEL;
import static page.LoginPage.PASSWORD_FIELD_LABEL;
import static utils.Constants.*;

public class LoginTest extends BaseTest {

    private LoginPageService loginPageService;

    @BeforeMethod
    @Description("Open login page")
    public void setUp() {
        loginPageService = new LoginPageService();
        loginPageService.openLoginPage();
    }

    @Test(priority = -1)
    @Description("Check sign into the www.qase.io with valid data")
    public void checkSuccessfulSignInWithValidDataTest() {
        User user = new User("mailundtest.1@gmail.com", ";sdt<TKFHECM13!");
        loginPageService.signIn(user);
        ProjectsPageService projectsPageService = new ProjectsPageService();
        boolean expectedPageIsOpened = projectsPageService.isProjectPageOpened();
        Assert.assertTrue(expectedPageIsOpened, "Sign in failed");
    }

    @Test()
    @Description("Check sign into the www.qase.io without workEmail input")
    public void checkEmailShouldBeRequired() {
        User user = new User(EMPTY_FIELD, ";sdt<TKFHECM13!");
        loginPageService.signIn(user);
        String actualMessage = loginPageService.getRequirementFulfillWorkEmailMessageText(EMAIL_LABEL);
        Assert.assertEquals(actualMessage, REQUIREMENT_FULFILL_MESSAGE, "Message is not been displayed");
    }

    @Test()
    @Description("Check sign into the www.qase.io without password input")
    public void checkPasswordShouldBeRequiredTest() {
        User user = new User("mailundtest.1@gmail.com", EMPTY_FIELD);
        loginPageService.signIn(user);
        String actualMessage = loginPageService.getRequirementFulfillPasswordMessageText(PASSWORD_FIELD_LABEL);
        Assert.assertEquals(actualMessage, REQUIREMENT_FULFILL_MESSAGE, "Message is not been displayed");
    }

    @Test()
    @Description("Check valid work email should be inputted")
    public void checkEmailShouldBeValidTest() {
        User user = new User("invalidEmail@gmail.com", ";sdt<TKFHECM13!");
        loginPageService.signIn(user);
        String actualMessage = loginPageService.getInvalidDataMessageText();
        Assert.assertEquals(actualMessage, INVALID_DATA_MESSAGE, "Wrong message is displayed");
    }

    @Test()
    @Description("Check valid password should be inputted")
    public void checkPasswordShouldBeValidTest() {
        User user = new User("mailundtest.1@gmail.com", "111InvalidPassword111!");
        loginPageService.signIn(user);
        String actualMessage = loginPageService.getInvalidDataMessageText();
        Assert.assertEquals(actualMessage, INVALID_DATA_MESSAGE, "Wrong message is displayed");
    }
}
