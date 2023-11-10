package service.ui;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.ui.Project;
import page.ProjectModalPage;
import page.ProjectsPage;

@Log4j2
public class ProjectModalPageService {

    private ProjectModalPage projectModalPage;

    @Step("Create new public project")
    public CurrentProjectPageService createNewPublicProject(Project project) {
        log.info("Create new public project: " + project);
        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.clickCreateNewProjectButton();
        projectModalPage = new ProjectModalPage();
        projectModalPage.inputProjectName(project.getName())
                .inputProjectDescription(project.getDescription())
                .chooseAccessType(project.getAccessType())
                .submitProjectCreating();
        return new CurrentProjectPageService();
    }

    @Step("Create new private project")
    public CurrentProjectPageService createNewPrivateProject(Project project) {
        log.info("Create new private project: " + project);
        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.clickCreateNewProjectButton();
        projectModalPage = new ProjectModalPage();
        projectModalPage.inputProjectName(project.getName())
                .inputProjectDescription(project.getDescription())
                .chooseAccessType(project.getAccessType())
                .chooseAccessType(project.getMembersAccessType())
                .clickMemberAccessDropdown()
                .chooseOwnerGroupMember()
                .submitProjectCreating();
        return new CurrentProjectPageService();
    }
}
