package com.programtom.rx_bloc_cli_wrapper_web_app.views.main;

import com.programtom.rx_bloc_cli_wrapper_web_app.models.auth.Login;
import com.programtom.rx_bloc_cli_wrapper_web_app.services.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("rx_bloc_cli_wrapper_web_app")
@Route(value = "")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
//    private static final String URL = "/oauth2/authorization/google";

    @Autowired
    LoginService loginService;

    //    private TextField name;
//    private Button sayHello;
    public LoginView() {

//        add(new H1("rx_bloc_cli_wrapper_web_app"));
//        add(new Text("Under Construction"));
        EmailField email = new EmailField("Email");
        email.setValue("");
        email.addClassName("login-view-email-field-1");
        PasswordField password = new PasswordField("Password");
        password.setValue("");
        Anchor forgottenPassword = new Anchor("forgottenPassword", "Forgotten Password");
        Anchor register = new Anchor("register", "Register");
        Anchor termsAndConditions = new Anchor();
        Button login = getLogin(email, password);



        setAlignItems(Alignment.CENTER);
        add(email, password, forgottenPassword, login, register, termsAndConditions);

//        Anchor googleLoginButton = new Anchor(URL, "Login with Google");
//        googleLoginButton.getElement().setAttribute("router-ignore", true);
//        add(googleLoginButton);
////        name = new TextField("Your name");
////        sayHello = new Button("Say hello");
////        sayHello.addClickListener(e -> {
////            Notification.show("Hello " + name.getValue());
////        });
////        sayHello.addClickShortcut(Key.ENTER);
////
////        setMargin(true);
////        setVerticalComponentAlignment(Alignment.END, name, sayHello);
////
////        add(name, sayHello);
    }
//    https://vaadin.com/blog/implementing-sign-in-with-google-s-oauth-2-services
//    https://developers.google.com/identity/sign-in/web/sign-in
    //    https://github.com/googleapis/google-oauth-java-client/blob/main/docs/setup.md
//    https://developers.google.com/identity/sign-in/web/server-side-flow
    private Button getLogin(EmailField email, PasswordField password) {
        Button login = new Button("Login");
        login.addClickListener(buttonClickEvent -> {
            try {
      //             Login loginModel = loginService.findByEmail(email.getValue(), password.getValue(),
//                     VaadinRequest.getCurrent().getRemoteAddr(), VaadinRequest.getCurrent().getLocale().getLanguage());

                Login loginModel = new Login();
                loginModel.setId("12347865-1234-1234-1234-123451234123");
                loginModel.setEmail("sample@sample.sample");
     //   add(new Button("Log In with Google", buttonClickEvent -> getUI().get().getPage().setLocation("googleLogin")));

                VaadinSession.getCurrent().getSession().setAttribute("user", loginModel);

                UI.getCurrent().navigate("main");
            } catch (Exception e) {
                Notification.show("Failed: " + e.getMessage());
            }
        });
        return login;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if(VaadinSession.getCurrent().getSession().getAttribute("user") != null) {
            beforeEnterEvent.forwardTo("main");
//            UI.getCurrent().navigate("main");
        }

    }
}
