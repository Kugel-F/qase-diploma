package tests.base;

import driver.DriverSingleton;
import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import service.CurrentProjectPageService;
import service.ProjectsServicePage;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        log.info("Close browser");
        DriverSingleton.getInstance().closeDriver();
    }

    @AfterMethod(onlyForGroups = {"Create new project"})
    @Description("Delete created test project")
    public void deleteTestProject() {
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openProjectsPage();
        ProjectsServicePage projectsServicePage = new ProjectsServicePage();
        projectsServicePage.removeProject(projectsServicePage.getProjectTitle());
    }
}
