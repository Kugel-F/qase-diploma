package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectModalPage extends BasePage {

    private static final String ACCESS_TYPE = "//span[contains(text(),'%s')]" +
            "//ancestor::label[@class='WX5yZl U0ALzL dEwoMK']//input";

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

    public ProjectModalPage inputProjectName(String name) {
        log.info("Input project name");
        projectNameInput.sendKeys(name);
        return this;
    }

    public ProjectModalPage inputProjectDescription(String description) {
        log.info("Input project description");
        projectDescription.sendKeys(description);
        return this;
    }

    public ProjectModalPage chooseAccessType(String type) {
        log.info("Choose project access type");
        driver.findElement(By.xpath(String.format(ACCESS_TYPE, type))).click();
        return this;
    }

    public ProjectModalPage clickMemberAccessDropdown() {
        log.info("Open member's access dropdown");
        memberAccessDropDown.click();
        return this;
    }

    public ProjectModalPage chooseOwnerGroupMember() {
        log.info("Choose owner's group member");
        Waiter.waitElementVisibleOf(ownerGroupMember).sendKeys(Keys.ENTER);
        return this;
    }

    public CurrentProjectPage submitProjectCreating() {
        log.info("Submit new project creating");
        Waiter.waitElementVisibleOf(submitNewProject).click();
        return new CurrentProjectPage();
    }
}
