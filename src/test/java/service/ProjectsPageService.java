package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Project;
import page.ProjectsPage;

@Log4j2
public class ProjectsPageService {

    private ProjectsPage projectsPage;

    @Step("Check project page is opened")
    public boolean isProjectPageOpened() {
        log.info("Check project page is opened");
        projectsPage = new ProjectsPage();
        return projectsPage.isProjectPageTitleDisplayed();
    }

    @Step("Create new public project")
    public CurrentProjectPageService createNewPublicProject(Project project) {
        log.info("Create new public project: " + project);
        projectsPage = new ProjectsPage();
        projectsPage.clickCreateProjectButton()
                .inputProjectName(project.getName())
                .inputProjectDescription(project.getDescription())
                .chooseAccessType(project.getAccessType())
                .submitProjectCreating();
        return new CurrentProjectPageService();
    }

    @Step("Create new private project")
    public CurrentProjectPageService createNewPrivateProject(Project project) {
        log.info("Create new private project: " + project);
        projectsPage = new ProjectsPage();
        projectsPage.clickCreateProjectButton()
                .inputProjectName(project.getName())
                .inputProjectDescription(project.getDescription())
                .chooseAccessType(project.getAccessType())
                .chooseAccessType(project.getMembersAccessType())
                .clickMemberAccessDropdown()
                .chooseOwnerGroupMember()
                .submitProjectCreating();
        return new CurrentProjectPageService();
    }

    @Step("Remove project")
    public ProjectsPageService removeProject(String title) {
        log.info("Remove project");
        CurrentProjectPageService currentProjectPageService = new CurrentProjectPageService();
        currentProjectPageService.openProjectsPage();
        projectsPage = new ProjectsPage();
        projectsPage.clickProjectMenu(title)
                .removeProject()
                .confirmRemoveProject();
        return this;
    }

    @Step("Check project is not on the page")
    public boolean isProjectNotDisplayed(String title) {
        log.info("Check project is not on the page");
        projectsPage = new ProjectsPage();
        return projectsPage.isProjectNotDisplayed(title);
    }
}
