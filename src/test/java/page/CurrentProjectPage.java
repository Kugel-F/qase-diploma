package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class CurrentProjectPage extends BasePage {

    @FindBy(xpath = "//div[@class='LALnEw']")
    WebElement projectTitle;
    @FindBy(xpath = "//a[@class='prLoyX' and @href='/projects']")
    WebElement projectsPageLink;

    public String getProjectTitle() {
        log.warn("Get new project title");
        return Waiter.waitVisibleOf(projectTitle).getText();
    }

    public ProjectsPage openProjectsPage() {
        log.info("Open projects page");
        projectsPageLink.click();
        return new ProjectsPage();
    }
}
