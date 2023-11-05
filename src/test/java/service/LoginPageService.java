package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.User;
import page.LoginPage;

@Log4j2
public class LoginPageService {

    private LoginPage loginPage;

    @Step("Open 'www.qase.io/login' page")
    public LoginPageService openLoginPage() {
        loginPage = new LoginPage();
        loginPage.openPage();
        return this;
    }

    @Step("Sign into the qase.io")
    public ProjectsServicePage signIn(User user) {
        log.info("Sign into the qase.io with user: " + user + " data");
        loginPage = new LoginPage();
        loginPage.emailInput(user.getEmail())
                .passwordInput(user.getPassword())
                .clickSignInButton();
        return new ProjectsServicePage();
    }

    @Step("Get message about fulfilling workEmail field")
    public String getRequirementFulfillWorkEmailMessageText(String label) {
        log.info("Get fulfilling email field message");
        loginPage = new LoginPage();
        return loginPage.getRequirementFulfillFieldMessageText(label);
    }

    @Step("Get message about fulfilling password field")
    public String getRequirementFulfillPasswordMessageText(String label) {
        log.info("Get fulfilling password field message");
        loginPage = new LoginPage();
        return loginPage.getRequirementFulfillFieldMessageText(label);
    }

    @Step("Get message while input invalid user's data")
    public String getInvalidDataMessageText() {
        log.info("Get invalid data message text");
        loginPage = new LoginPage();
        return loginPage.getInvalidDataMessageText();
    }
}
