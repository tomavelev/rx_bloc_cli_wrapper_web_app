package com.programtom.rx_bloc_cli_wrapper_web_app.views.main;

import com.programtom.rx_bloc_cli_wrapper_web_app.i18n.RxBlocCliWrapperWebAppMessages;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.programtom.rx_bloc_cli_wrapper_web_app.views.ProjectListComponent;



@Route("main")
public class MainView extends AuthenticatedLayout {

    private final VerticalLayout content;

    public MainView() {
        content = new VerticalLayout();

        Button logoutButton = new Button("Logout", click -> {
            VaadinSession.getCurrent().getSession().removeAttribute("user");
            VaadinSession.getCurrent().getSession().setAttribute("user", null);
            UI.getCurrent().navigate(LoginView.class);
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        setAlignItems(Alignment.CENTER);
        Button project = new Button(RxBlocCliWrapperWebAppMessages.getString("project"));
        project.addClickListener(buttonClickEvent -> {
            content.removeAll();
            content.add(new ProjectListComponent());
        });
        horizontalLayout.add(project);

        add(horizontalLayout, content, logoutButton);
    }

}