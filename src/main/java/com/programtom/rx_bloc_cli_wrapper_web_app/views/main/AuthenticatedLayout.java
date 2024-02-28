package com.programtom.rx_bloc_cli_wrapper_web_app.views.main;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;

public class AuthenticatedLayout extends VerticalLayout implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (VaadinSession.getCurrent().getSession().getAttribute("user") == null) {
            beforeEnterEvent.forwardTo("");
        }

    }
}
