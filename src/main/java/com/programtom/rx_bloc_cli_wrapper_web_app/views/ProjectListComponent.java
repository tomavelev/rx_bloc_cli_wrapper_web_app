package com.programtom.rx_bloc_cli_wrapper_web_app.views;


import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.QueryResult;
import com.programtom.rx_bloc_cli_wrapper_web_app.services.ProjectService;
import com.programtom.rx_bloc_cli_wrapper_web_app.services.RxBlocCliService;
import com.programtom.rx_bloc_cli_wrapper_web_app.utils.SpringContext;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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
        Button create = new Button("Create", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            RxBlocCliService rxBlocCliService = SpringContext.getBean(RxBlocCliService.class);
            output.setText("");
//            , s -> output.setText(output.getText() + s)
            rxBlocCliService.create(project);
        });
        objectActions.add(create);
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

