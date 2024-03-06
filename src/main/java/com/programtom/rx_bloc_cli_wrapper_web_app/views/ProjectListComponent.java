package com.programtom.rx_bloc_cli_wrapper_web_app.views;


import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.QueryResult;
import com.programtom.rx_bloc_cli_wrapper_web_app.services.ProjectService;
import com.programtom.rx_bloc_cli_wrapper_web_app.services.RxBlocCliService;
import com.programtom.rx_bloc_cli_wrapper_web_app.utils.SpringContext;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.frontend.installer.DefaultFileDownloader;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Route(value = "project")
@PageTitle("Project List")
public class ProjectListComponent extends BaseListComponent<Project> {


    ProjectService projectService = SpringContext.getBean(ProjectService.class);

    Text output = new Text("");

    public ProjectListComponent() {
        defaultInit();
        refreshStuff();

        add(output);
    }

    protected void addEdit(Project project) {
        new ProjectAddEditComponent(project, this);
    }

    @Override
    protected void defaultInit() {
        super.defaultInit();
    }

    @Override
    protected Project newModelInstance() {
        Project project = new Project();
        project.setUserId(getAppUser().getId());
        return project;
    }

    @Override
    protected void deleteRecord(Project project) {
        projectService.deleteRecord(project, getAppUser().getId());
        refresh();
    }


    @Override
    protected Component item(Project project) {
        VerticalLayout objectActions = objectActions(project);
        RxBlocCliService rxBlocCliService = SpringContext.getBean(RxBlocCliService.class);

        Button create = new Button("Create", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            output.setText("");
          File file = rxBlocCliService.create(project);
          if(file != null) {
              UI.getCurrent().getPage().reload();
          }
        });
        objectActions.add(create);

        File build = rxBlocCliService.build(project);
        if(build.exists()) {
            Button button = new Button("Download " + project.getProjectName());

            FileDownloadWrapper wrapper = new FileDownloadWrapper(
                    new StreamResource(build.getName(), () -> {
                        try {
                            return new FileInputStream(build);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
            wrapper.wrapComponent(button);
            objectActions.add(wrapper);
        }
        return new ProjectListItem(project, objectActions);
    }

    @Override
    public void save(Project object) {
        projectService.save(object);
    }

    @Override
    protected String displayName(Project object) {
        return object.getProjectName();
    }

    @Override
    protected Class<Project> modelClass() {
        return Project.class;
    }

    @Override
    protected QueryResult<Project> loadRecords(String search) {
        return projectService.selectByUserAndSearchPage(getAppUser().getId(), search, offset, PAGE_SIZE);
    }
}

