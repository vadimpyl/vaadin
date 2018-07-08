package com.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    //private TextField txtUsername = new TextField("Username");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        Button btnLogin = new Button("Login");
        TextField txtUsername = new TextField("Username");
        TextField txtPassword = new TextField("Password");
       /* txtUsername.setRequired(true);
        txtPassword.setRequired(true);
        txtPassword.setSecret(true);*/
        layout.addComponent(new Label("Hello World!"));
        //FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        layout.addComponent(username);
        PasswordField password = new PasswordField("Password");
        layout.addComponent(password);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
