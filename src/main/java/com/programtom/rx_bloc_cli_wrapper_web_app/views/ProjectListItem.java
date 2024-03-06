package com.programtom.rx_bloc_cli_wrapper_web_app.views;


import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ProjectListItem extends HorizontalLayout {


    public ProjectListItem(Project project, VerticalLayout actionItems
    ) {
        setClassName("Project");
        setWidth("100%");
        Span text = new Span(project.getProjectName());
        setFlexGrow(1, text);
        add(text, actionItems);    }
}

