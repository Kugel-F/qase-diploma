package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String ACCESS_TYPE = "//span[contains(text(),'%s')]" +
            "//ancestor::label[@class='WX5yZl U0ALzL dEwoMK']//input";
    private static final String PROJECT_MENU = "//a[contains(text(),'%s')]//ancestor::tr[@class='PX4acs']" +
            "//button[@class='j4xaa7 bB3U2Y TuZZEp']";
    private static final String PROJECT_TITLE = "//a[@class='MfvNFg' and contains(text(),'%s')]";

    @FindBy(xpath = "//h1[contains(text(),'Projects')]")
    WebElement projectPageTitle;
    @FindBy(xpath = "//button[@id='createButton']")
    WebElement createProjectButton;
    @FindBy(xpath = "//input[@id='project-name']")
    WebElement projectNameInput;
    @FindBy(xpath = "//textarea[@name='description-area']")
    WebElement projectDescription;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitNewProject;
    @FindBy(xpath = "//div[@class='JlJp6z']")
    WebElement memberAccessDropDown;
    @FindBy(xpath = "//input[@aria-autocomplete='list']")
    WebElement ownerGroupMember;
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    WebElement removeButton;
    @FindBy(xpath = "//span[contains(text(),'Delete project')]")
    WebElement confirmRemoveButton;

    public boolean isProjectPageTitleDisplayed() {
        log.info("Check project page title is displayed");
        return projectPageTitle.isDisplayed();
    }

    public ProjectsPage clickCreateProjectButton() {
        log.info("Click new project button");
        createProjectButton.click();
        return this;
    }

    public ProjectsPage inputProjectName(String name) {
        log.info("Input project name");
        projectNameInput.sendKeys(name);
        return this;
    }

    public ProjectsPage inputProjectDescription(String description) {
        log.info("Input project description");
        projectDescription.sendKeys(description);
        return this;
    }

    public ProjectsPage chooseAccessType(String type) {
        log.info("Choose project access type");
        driver.findElement(By.xpath(String.format(ACCESS_TYPE, type))).click();
        return this;
    }

    public CurrentProjectPage submitProjectCreating() {
        log.info("Submit new project creating");
        submitNewProject.click();
        return new CurrentProjectPage();
    }

    public ProjectsPage clickMemberAccessDropdown() {
        log.info("Open member's access dropdown");
        memberAccessDropDown.click();
        return this;
    }

    public ProjectsPage chooseOwnerGroupMember() {
        log.info("Choose owner's group member");
        ownerGroupMember.sendKeys(Keys.ENTER);
        return this;
    }

    public ProjectsPage clickProjectMenu(String title) {
        log.info("Click project's menu");
        driver.findElement(By.xpath(String.format(PROJECT_MENU, title))).click();
        return this;
    }

    public ProjectsPage removeProject() {
        log.info("Remove current project");
        removeButton.click();
        return this;
    }

    public ProjectsPage confirmRemoveProject() {
        log.info("Confirm remove project");
        confirmRemoveButton.click();
        return this;
    }

    public boolean isProjectNotDisplayed(String title) {
        log.info("Check project is not on the page");
        try {
            Waiter.waitElementInvisibleOf(driver.findElement(By.xpath(String.format(PROJECT_TITLE, title))));
        } catch (Exception exception) {
            log.debug("Project is on the page");
        }
        return true;
    }
}
